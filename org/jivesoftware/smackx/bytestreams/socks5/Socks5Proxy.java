package org.jivesoftware.smackx.bytestreams.socks5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager;

public class Socks5Proxy {
    private static final Logger LOGGER;
    private static boolean localSocks5ProxyEnabled;
    private static int localSocks5ProxyPort;
    private static Socks5Proxy socks5Server;
    private final List<String> allowedConnections;
    private final Map<String, Socket> connectionMap;
    private final Set<String> localAddresses;
    private Socks5ServerProcess serverProcess;
    private ServerSocket serverSocket;
    private Thread serverThread;

    class Socks5ServerProcess implements Runnable {
        private Socks5ServerProcess() {
        }

        public void run() {
            while (true) {
                Socket socket = null;
                try {
                    if (!Socks5Proxy.this.serverSocket.isClosed() && !Thread.currentThread().isInterrupted()) {
                        establishConnection(Socks5Proxy.this.serverSocket.accept());
                    } else {
                        return;
                    }
                } catch (SocketException e) {
                } catch (Exception e2) {
                    if (socket != null) {
                        try {
                            socket.close();
                        } catch (IOException e3) {
                        }
                    }
                }
            }
        }

        private void establishConnection(Socket socket) {
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            if (dataInputStream.read() != 5) {
                throw new SmackException("Only SOCKS5 supported");
            }
            int i;
            byte[] bArr = new byte[dataInputStream.read()];
            dataInputStream.readFully(bArr);
            byte[] bArr2 = new byte[2];
            bArr2[0] = (byte) 5;
            for (byte b : bArr) {
                if (b == null) {
                    i = 1;
                    break;
                }
            }
            byte b2 = (byte) 0;
            if (i == 0) {
                bArr2[1] = (byte) -1;
                dataOutputStream.write(bArr2);
                dataOutputStream.flush();
                throw new SmackException("Authentication method not supported");
            }
            bArr2[1] = (byte) 0;
            dataOutputStream.write(bArr2);
            dataOutputStream.flush();
            byte[] receiveSocks5Message = Socks5Utils.receiveSocks5Message(dataInputStream);
            String str = new String(receiveSocks5Message, 5, receiveSocks5Message[4]);
            if (Socks5Proxy.this.allowedConnections.contains(str)) {
                receiveSocks5Message[1] = (byte) 0;
                dataOutputStream.write(receiveSocks5Message);
                dataOutputStream.flush();
                Socks5Proxy.this.connectionMap.put(str, socket);
                return;
            }
            receiveSocks5Message[1] = (byte) 5;
            dataOutputStream.write(receiveSocks5Message);
            dataOutputStream.flush();
            throw new SmackException("Connection is not allowed");
        }
    }

    static {
        LOGGER = Logger.getLogger(Socks5Proxy.class.getName());
        localSocks5ProxyEnabled = true;
        localSocks5ProxyPort = -7777;
    }

    private Socks5Proxy() {
        this.connectionMap = new ConcurrentHashMap();
        this.allowedConnections = Collections.synchronizedList(new LinkedList());
        this.localAddresses = new LinkedHashSet(4);
        this.serverProcess = new Socks5ServerProcess();
        try {
            Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
            Collection hashSet = new HashSet();
            Iterator it = Collections.list(networkInterfaces).iterator();
            while (it.hasNext()) {
                Iterator it2 = Collections.list(((NetworkInterface) it.next()).getInetAddresses()).iterator();
                while (it2.hasNext()) {
                    hashSet.add(((InetAddress) it2.next()).getHostAddress());
                }
            }
            if (hashSet.isEmpty()) {
                throw new IllegalStateException("Could not determine any local host address");
            }
            replaceLocalAddresses(hashSet);
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    public static boolean isLocalSocks5ProxyEnabled() {
        return localSocks5ProxyEnabled;
    }

    public static void setLocalSocks5ProxyEnabled(boolean z) {
        localSocks5ProxyEnabled = z;
    }

    public static int getLocalSocks5ProxyPort() {
        return localSocks5ProxyPort;
    }

    public static void setLocalSocks5ProxyPort(int i) {
        if (Math.abs(i) > InBandBytestreamManager.MAXIMUM_BLOCK_SIZE) {
            throw new IllegalArgumentException("localSocks5ProxyPort must be within (-65535,65535)");
        }
        localSocks5ProxyPort = i;
    }

    public static synchronized Socks5Proxy getSocks5Proxy() {
        Socks5Proxy socks5Proxy;
        synchronized (Socks5Proxy.class) {
            if (socks5Server == null) {
                socks5Server = new Socks5Proxy();
            }
            if (isLocalSocks5ProxyEnabled()) {
                socks5Server.start();
            }
            socks5Proxy = socks5Server;
        }
        return socks5Proxy;
    }

    public synchronized void start() {
        if (!isRunning()) {
            try {
                if (getLocalSocks5ProxyPort() < 0) {
                    int abs = Math.abs(getLocalSocks5ProxyPort());
                    int i = 0;
                    while (i < InBandBytestreamManager.MAXIMUM_BLOCK_SIZE - abs) {
                        try {
                            this.serverSocket = new ServerSocket(abs + i);
                            break;
                        } catch (IOException e) {
                            i++;
                        }
                    }
                } else {
                    this.serverSocket = new ServerSocket(getLocalSocks5ProxyPort());
                }
                if (this.serverSocket != null) {
                    this.serverThread = new Thread(this.serverProcess);
                    this.serverThread.start();
                }
            } catch (Throwable e2) {
                LOGGER.log(Level.SEVERE, "couldn't setup local SOCKS5 proxy on port " + getLocalSocks5ProxyPort(), e2);
            }
        }
    }

    public synchronized void stop() {
        if (isRunning()) {
            try {
                this.serverSocket.close();
            } catch (IOException e) {
            }
            if (this.serverThread != null && this.serverThread.isAlive()) {
                try {
                    this.serverThread.interrupt();
                    this.serverThread.join();
                } catch (InterruptedException e2) {
                }
            }
            this.serverThread = null;
            this.serverSocket = null;
        }
    }

    public void addLocalAddress(String str) {
        if (str != null) {
            synchronized (this.localAddresses) {
                this.localAddresses.add(str);
            }
        }
    }

    public boolean removeLocalAddress(String str) {
        boolean remove;
        synchronized (this.localAddresses) {
            remove = this.localAddresses.remove(str);
        }
        return remove;
    }

    public List<String> getLocalAddresses() {
        List linkedList;
        synchronized (this.localAddresses) {
            linkedList = new LinkedList(this.localAddresses);
        }
        return linkedList;
    }

    public void replaceLocalAddresses(Collection<String> collection) {
        if (collection == null) {
            throw new IllegalArgumentException("list must not be null");
        }
        synchronized (this.localAddresses) {
            this.localAddresses.clear();
            this.localAddresses.addAll(collection);
        }
    }

    public int getPort() {
        if (isRunning()) {
            return this.serverSocket.getLocalPort();
        }
        return -1;
    }

    protected Socket getSocket(String str) {
        return (Socket) this.connectionMap.get(str);
    }

    protected void addTransfer(String str) {
        this.allowedConnections.add(str);
    }

    protected void removeTransfer(String str) {
        this.allowedConnections.remove(str);
        this.connectionMap.remove(str);
    }

    public boolean isRunning() {
        return this.serverSocket != null;
    }
}
