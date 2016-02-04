package org.jivesoftware.smackx.bytestreams.socks5;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeoutException;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.SmackException.FeatureNotSupportedException;
import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smack.XMPPException.XMPPErrorException;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.packet.XMPPError.Condition;
import org.jivesoftware.smackx.bytestreams.BytestreamListener;
import org.jivesoftware.smackx.bytestreams.BytestreamManager;
import org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream;
import org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream.StreamHost;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.disco.packet.DiscoverItems.Item;

public final class Socks5BytestreamManager extends Manager implements BytestreamManager {
    private static final String SESSION_ID_PREFIX = "js5_";
    private static final Map<XMPPConnection, Socks5BytestreamManager> managers;
    private static final Random randomGenerator;
    private final List<BytestreamListener> allRequestListeners;
    private List<String> ignoredBytestreamRequests;
    private final InitiationListener initiationListener;
    private String lastWorkingProxy;
    private final List<String> proxyBlacklist;
    private int proxyConnectionTimeout;
    private boolean proxyPrioritizationEnabled;
    private int targetResponseTimeout;
    private final Map<String, BytestreamListener> userListeners;

    /* renamed from: org.jivesoftware.smackx.bytestreams.socks5.Socks5BytestreamManager.1 */
    final class C10421 implements ConnectionCreationListener {
        C10421() {
        }

        public void connectionCreated(XMPPConnection xMPPConnection) {
            Socks5BytestreamManager.getBytestreamManager(xMPPConnection);
        }
    }

    static {
        XMPPConnectionRegistry.addConnectionCreationListener(new C10421());
        randomGenerator = new Random();
        managers = new WeakHashMap();
    }

    public static synchronized Socks5BytestreamManager getBytestreamManager(XMPPConnection xMPPConnection) {
        Socks5BytestreamManager socks5BytestreamManager;
        synchronized (Socks5BytestreamManager.class) {
            if (xMPPConnection == null) {
                socks5BytestreamManager = null;
            } else {
                socks5BytestreamManager = (Socks5BytestreamManager) managers.get(xMPPConnection);
                if (socks5BytestreamManager == null) {
                    socks5BytestreamManager = new Socks5BytestreamManager(xMPPConnection);
                    managers.put(xMPPConnection, socks5BytestreamManager);
                }
            }
        }
        return socks5BytestreamManager;
    }

    private Socks5BytestreamManager(XMPPConnection xMPPConnection) {
        super(xMPPConnection);
        this.userListeners = new ConcurrentHashMap();
        this.allRequestListeners = Collections.synchronizedList(new LinkedList());
        this.targetResponseTimeout = 10000;
        this.proxyConnectionTimeout = 10000;
        this.proxyBlacklist = Collections.synchronizedList(new LinkedList());
        this.lastWorkingProxy = null;
        this.proxyPrioritizationEnabled = true;
        this.ignoredBytestreamRequests = Collections.synchronizedList(new LinkedList());
        this.initiationListener = new InitiationListener(this);
        activate();
    }

    public void addIncomingBytestreamListener(BytestreamListener bytestreamListener) {
        this.allRequestListeners.add(bytestreamListener);
    }

    public void removeIncomingBytestreamListener(BytestreamListener bytestreamListener) {
        this.allRequestListeners.remove(bytestreamListener);
    }

    public void addIncomingBytestreamListener(BytestreamListener bytestreamListener, String str) {
        this.userListeners.put(str, bytestreamListener);
    }

    public void removeIncomingBytestreamListener(String str) {
        this.userListeners.remove(str);
    }

    public void ignoreBytestreamRequestOnce(String str) {
        this.ignoredBytestreamRequests.add(str);
    }

    public synchronized void disableService() {
        XMPPConnection connection = connection();
        connection.unregisterIQRequestHandler(this.initiationListener);
        this.initiationListener.shutdown();
        this.allRequestListeners.clear();
        this.userListeners.clear();
        this.lastWorkingProxy = null;
        this.proxyBlacklist.clear();
        this.ignoredBytestreamRequests.clear();
        managers.remove(connection);
        if (managers.size() == 0) {
            Socks5Proxy.getSocks5Proxy().stop();
        }
        ServiceDiscoveryManager instanceFor = ServiceDiscoveryManager.getInstanceFor(connection);
        if (instanceFor != null) {
            instanceFor.removeFeature(Bytestream.NAMESPACE);
        }
    }

    public int getTargetResponseTimeout() {
        if (this.targetResponseTimeout <= 0) {
            this.targetResponseTimeout = 10000;
        }
        return this.targetResponseTimeout;
    }

    public void setTargetResponseTimeout(int i) {
        this.targetResponseTimeout = i;
    }

    public int getProxyConnectionTimeout() {
        if (this.proxyConnectionTimeout <= 0) {
            this.proxyConnectionTimeout = 10000;
        }
        return this.proxyConnectionTimeout;
    }

    public void setProxyConnectionTimeout(int i) {
        this.proxyConnectionTimeout = i;
    }

    public boolean isProxyPrioritizationEnabled() {
        return this.proxyPrioritizationEnabled;
    }

    public void setProxyPrioritizationEnabled(boolean z) {
        this.proxyPrioritizationEnabled = z;
    }

    public Socks5BytestreamSession establishSession(String str) {
        return establishSession(str, getNextSessionID());
    }

    public Socks5BytestreamSession establishSession(String str, String str2) {
        XMPPConnection connection = connection();
        if (supportsSocks5(str)) {
            XMPPErrorException xMPPErrorException;
            List arrayList = new ArrayList();
            try {
                arrayList.addAll(determineProxies());
                xMPPErrorException = null;
            } catch (XMPPErrorException e) {
                xMPPErrorException = e;
            }
            List<Object> determineStreamHostInfos = determineStreamHostInfos(arrayList);
            if (!determineStreamHostInfos.isEmpty()) {
                String createDigest = Socks5Utils.createDigest(str2, connection.getUser(), str);
                if (this.proxyPrioritizationEnabled && this.lastWorkingProxy != null) {
                    for (Object obj : determineStreamHostInfos) {
                        if (obj.getJID().equals(this.lastWorkingProxy)) {
                            break;
                        }
                    }
                    Object obj2 = null;
                    if (obj2 != null) {
                        determineStreamHostInfos.remove(obj2);
                        determineStreamHostInfos.add(0, obj2);
                    }
                }
                Socks5Proxy socks5Proxy = Socks5Proxy.getSocks5Proxy();
                try {
                    socks5Proxy.addTransfer(createDigest);
                    IQ createBytestreamInitiation = createBytestreamInitiation(str2, str, determineStreamHostInfos);
                    StreamHost streamHost = createBytestreamInitiation.getStreamHost(((Bytestream) connection.createPacketCollectorAndSend(createBytestreamInitiation).nextResultOrThrow((long) getTargetResponseTimeout())).getUsedHost().getJID());
                    if (streamHost == null) {
                        throw new SmackException("Remote user responded with unknown host");
                    }
                    Socket socket = new Socks5ClientForInitiator(streamHost, createDigest, connection, str2, str).getSocket(getProxyConnectionTimeout());
                    this.lastWorkingProxy = streamHost.getJID();
                    Socks5BytestreamSession socks5BytestreamSession = new Socks5BytestreamSession(socket, streamHost.getJID().equals(connection.getUser()));
                    socks5Proxy.removeTransfer(createDigest);
                    return socks5BytestreamSession;
                } catch (TimeoutException e2) {
                    throw new IOException("Timeout while connecting to SOCKS5 proxy");
                } catch (Throwable th) {
                    socks5Proxy.removeTransfer(createDigest);
                }
            } else if (xMPPErrorException != null) {
                throw xMPPErrorException;
            } else {
                throw new SmackException("no SOCKS5 proxies available");
            }
        }
        throw new FeatureNotSupportedException("SOCKS5 Bytestream", str);
    }

    private boolean supportsSocks5(String str) {
        return ServiceDiscoveryManager.getInstanceFor(connection()).supportsFeature(str, Bytestream.NAMESPACE);
    }

    private List<String> determineProxies() {
        XMPPConnection connection = connection();
        ServiceDiscoveryManager instanceFor = ServiceDiscoveryManager.getInstanceFor(connection);
        List<String> arrayList = new ArrayList();
        for (Item item : instanceFor.discoverItems(connection.getServiceName()).getItems()) {
            if (!this.proxyBlacklist.contains(item.getEntityID())) {
                try {
                    if (instanceFor.discoverInfo(item.getEntityID()).hasIdentity("proxy", "bytestreams")) {
                        arrayList.add(item.getEntityID());
                    } else {
                        this.proxyBlacklist.add(item.getEntityID());
                    }
                } catch (NoResponseException e) {
                    this.proxyBlacklist.add(item.getEntityID());
                } catch (XMPPErrorException e2) {
                    this.proxyBlacklist.add(item.getEntityID());
                }
            }
        }
        return arrayList;
    }

    private List<StreamHost> determineStreamHostInfos(List<String> list) {
        XMPPConnection connection = connection();
        List<StreamHost> arrayList = new ArrayList();
        Collection localStreamHost = getLocalStreamHost();
        if (localStreamHost != null) {
            arrayList.addAll(localStreamHost);
        }
        for (String str : list) {
            try {
                arrayList.addAll(((Bytestream) connection.createPacketCollectorAndSend(createStreamHostRequest(str)).nextResultOrThrow()).getStreamHosts());
            } catch (Exception e) {
                this.proxyBlacklist.add(str);
            }
        }
        return arrayList;
    }

    private Bytestream createStreamHostRequest(String str) {
        Bytestream bytestream = new Bytestream();
        bytestream.setType(Type.get);
        bytestream.setTo(str);
        return bytestream;
    }

    private List<StreamHost> getLocalStreamHost() {
        XMPPConnection connection = connection();
        Socks5Proxy socks5Proxy = Socks5Proxy.getSocks5Proxy();
        if (!socks5Proxy.isRunning()) {
            return null;
        }
        List<String> localAddresses = socks5Proxy.getLocalAddresses();
        if (localAddresses.isEmpty()) {
            return null;
        }
        int port = socks5Proxy.getPort();
        List<StreamHost> arrayList = new ArrayList();
        for (String str : localAddresses) {
            for (String startsWith : new String[]{"127.0.0.1", "0:0:0:0:0:0:0:1", "::1"}) {
                if (str.startsWith(startsWith)) {
                    break;
                }
            }
            arrayList.add(new StreamHost(connection.getUser(), str, port));
        }
        return arrayList;
    }

    private Bytestream createBytestreamInitiation(String str, String str2, List<StreamHost> list) {
        Bytestream bytestream = new Bytestream(str);
        for (StreamHost addStreamHost : list) {
            bytestream.addStreamHost(addStreamHost);
        }
        bytestream.setType(Type.set);
        bytestream.setTo(str2);
        return bytestream;
    }

    protected void replyRejectPacket(IQ iq) {
        connection().sendStanza(IQ.createErrorResponse(iq, new XMPPError(Condition.not_acceptable)));
    }

    private void activate() {
        connection().registerIQRequestHandler(this.initiationListener);
        enableService();
    }

    private void enableService() {
        ServiceDiscoveryManager.getInstanceFor(connection()).addFeature(Bytestream.NAMESPACE);
    }

    private String getNextSessionID() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SESSION_ID_PREFIX);
        stringBuilder.append(Math.abs(randomGenerator.nextLong()));
        return stringBuilder.toString();
    }

    protected XMPPConnection getConnection() {
        return connection();
    }

    protected BytestreamListener getUserListener(String str) {
        return (BytestreamListener) this.userListeners.get(str);
    }

    protected List<BytestreamListener> getAllRequestListeners() {
        return this.allRequestListeners;
    }

    protected List<String> getIgnoredBytestreamRequests() {
        return this.ignoredBytestreamRequests;
    }
}
