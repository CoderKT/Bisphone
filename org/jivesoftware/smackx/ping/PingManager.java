package org.jivesoftware.smackx.ping;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.AbstractConnectionClosedListener;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.iqrequest.AbstractIqRequestHandler;
import org.jivesoftware.smack.iqrequest.IQRequestHandler.Mode;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jivesoftware.smack.util.SmackExecutorThreadFactory;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.ping.packet.Ping;

public class PingManager extends Manager {
    private static final Map<XMPPConnection, PingManager> INSTANCES;
    private static final Logger LOGGER;
    private static int defaultPingInterval;
    private final ScheduledExecutorService executorService;
    private ScheduledFuture<?> nextAutomaticPing;
    private final Set<PingFailedListener> pingFailedListeners;
    private int pingInterval;
    private final Runnable pingServerRunnable;

    /* renamed from: org.jivesoftware.smackx.ping.PingManager.1 */
    final class C10931 implements ConnectionCreationListener {
        C10931() {
        }

        public void connectionCreated(XMPPConnection xMPPConnection) {
            PingManager.getInstanceFor(xMPPConnection);
        }
    }

    /* renamed from: org.jivesoftware.smackx.ping.PingManager.2 */
    class C10942 extends AbstractIqRequestHandler {
        C10942(String str, String str2, Type type, Mode mode) {
            super(str, str2, type, mode);
        }

        public IQ handleIQRequest(IQ iq) {
            return ((Ping) iq).getPong();
        }
    }

    /* renamed from: org.jivesoftware.smackx.ping.PingManager.3 */
    class C10953 extends AbstractConnectionClosedListener {
        C10953() {
        }

        public void authenticated(XMPPConnection xMPPConnection, boolean z) {
            PingManager.this.maybeSchedulePingServerTask();
        }

        public void connectionTerminated() {
            PingManager.this.maybeStopPingServerTask();
        }
    }

    /* renamed from: org.jivesoftware.smackx.ping.PingManager.4 */
    class C10964 implements Runnable {
        C10964() {
        }

        public void run() {
            PingManager.LOGGER.fine("ServerPingTask run()");
            PingManager.this.pingServerIfNecessary();
        }
    }

    static {
        LOGGER = Logger.getLogger(PingManager.class.getName());
        INSTANCES = new WeakHashMap();
        XMPPConnectionRegistry.addConnectionCreationListener(new C10931());
        defaultPingInterval = 1800;
    }

    public static synchronized PingManager getInstanceFor(XMPPConnection xMPPConnection) {
        PingManager pingManager;
        synchronized (PingManager.class) {
            pingManager = (PingManager) INSTANCES.get(xMPPConnection);
            if (pingManager == null) {
                pingManager = new PingManager(xMPPConnection);
                INSTANCES.put(xMPPConnection, pingManager);
            }
        }
        return pingManager;
    }

    public static void setDefaultPingInterval(int i) {
        defaultPingInterval = i;
    }

    private PingManager(XMPPConnection xMPPConnection) {
        super(xMPPConnection);
        this.pingFailedListeners = Collections.synchronizedSet(new HashSet());
        this.pingInterval = defaultPingInterval;
        this.pingServerRunnable = new C10964();
        this.executorService = Executors.newSingleThreadScheduledExecutor(new SmackExecutorThreadFactory(xMPPConnection.getConnectionCounter(), "Ping"));
        ServiceDiscoveryManager.getInstanceFor(xMPPConnection).addFeature(Ping.NAMESPACE);
        xMPPConnection.registerIQRequestHandler(new C10942(Ping.ELEMENT, Ping.NAMESPACE, Type.get, Mode.async));
        xMPPConnection.addConnectionListener(new C10953());
        maybeSchedulePingServerTask();
    }

    public boolean ping(String str, long j) {
        XMPPConnection connection = connection();
        if (connection.isAuthenticated()) {
            try {
                connection.createPacketCollectorAndSend(new Ping(str)).nextResultOrThrow(j);
                return true;
            } catch (XMPPException e) {
                return str.equals(connection.getServiceName());
            }
        }
        throw new NotConnectedException();
    }

    public boolean ping(String str) {
        return ping(str, connection().getPacketReplyTimeout());
    }

    public boolean isPingSupported(String str) {
        return ServiceDiscoveryManager.getInstanceFor(connection()).supportsFeature(str, Ping.NAMESPACE);
    }

    public boolean pingMyServer() {
        return pingMyServer(true);
    }

    public boolean pingMyServer(boolean z) {
        return pingMyServer(z, connection().getPacketReplyTimeout());
    }

    public boolean pingMyServer(boolean z, long j) {
        boolean ping;
        try {
            ping = ping(connection().getServiceName(), j);
        } catch (NoResponseException e) {
            ping = false;
        }
        if (!ping && z) {
            for (PingFailedListener pingFailed : this.pingFailedListeners) {
                pingFailed.pingFailed();
            }
        }
        return ping;
    }

    public void setPingInterval(int i) {
        this.pingInterval = i;
        maybeSchedulePingServerTask();
    }

    public int getPingInterval() {
        return this.pingInterval;
    }

    public void registerPingFailedListener(PingFailedListener pingFailedListener) {
        this.pingFailedListeners.add(pingFailedListener);
    }

    public void unregisterPingFailedListener(PingFailedListener pingFailedListener) {
        this.pingFailedListeners.remove(pingFailedListener);
    }

    private void maybeSchedulePingServerTask() {
        maybeSchedulePingServerTask(0);
    }

    private synchronized void maybeSchedulePingServerTask(int i) {
        maybeStopPingServerTask();
        if (this.pingInterval > 0) {
            int i2 = this.pingInterval - i;
            LOGGER.fine("Scheduling ServerPingTask in " + i2 + " seconds (pingInterval=" + this.pingInterval + ", delta=" + i + ")");
            this.nextAutomaticPing = this.executorService.schedule(this.pingServerRunnable, (long) i2, TimeUnit.SECONDS);
        }
    }

    private void maybeStopPingServerTask() {
        if (this.nextAutomaticPing != null) {
            this.nextAutomaticPing.cancel(true);
            this.nextAutomaticPing = null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void pingServerIfNecessary() {
        /*
        r6 = this;
        r1 = 0;
        monitor-enter(r6);
        r0 = r6.connection();	 Catch:{ all -> 0x002a }
        if (r0 != 0) goto L_0x000a;
    L_0x0008:
        monitor-exit(r6);
        return;
    L_0x000a:
        r2 = r6.pingInterval;	 Catch:{ all -> 0x002a }
        if (r2 <= 0) goto L_0x0008;
    L_0x000e:
        r2 = r0.getLastStanzaReceived();	 Catch:{ all -> 0x002a }
        r4 = 0;
        r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r4 <= 0) goto L_0x002d;
    L_0x0018:
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x002a }
        r2 = r4 - r2;
        r4 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r2 = r2 / r4;
        r2 = (int) r2;	 Catch:{ all -> 0x002a }
        r3 = r6.pingInterval;	 Catch:{ all -> 0x002a }
        if (r2 >= r3) goto L_0x002d;
    L_0x0026:
        r6.maybeSchedulePingServerTask(r2);	 Catch:{ all -> 0x002a }
        goto L_0x0008;
    L_0x002a:
        r0 = move-exception;
        monitor-exit(r6);
        throw r0;
    L_0x002d:
        r0 = r0.isAuthenticated();	 Catch:{ all -> 0x002a }
        if (r0 == 0) goto L_0x0071;
    L_0x0033:
        r2 = r1;
        r0 = r1;
    L_0x0035:
        r3 = 3;
        if (r2 >= r3) goto L_0x0046;
    L_0x0038:
        if (r2 == 0) goto L_0x003f;
    L_0x003a:
        r4 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        java.lang.Thread.sleep(r4);	 Catch:{ InterruptedException -> 0x0079 }
    L_0x003f:
        r0 = 0;
        r0 = r6.pingMyServer(r0);	 Catch:{ SmackException -> 0x005e }
    L_0x0044:
        if (r0 == 0) goto L_0x006a;
    L_0x0046:
        if (r0 != 0) goto L_0x006d;
    L_0x0048:
        r0 = r6.pingFailedListeners;	 Catch:{ all -> 0x002a }
        r1 = r0.iterator();	 Catch:{ all -> 0x002a }
    L_0x004e:
        r0 = r1.hasNext();	 Catch:{ all -> 0x002a }
        if (r0 == 0) goto L_0x0008;
    L_0x0054:
        r0 = r1.next();	 Catch:{ all -> 0x002a }
        r0 = (org.jivesoftware.smackx.ping.PingFailedListener) r0;	 Catch:{ all -> 0x002a }
        r0.pingFailed();	 Catch:{ all -> 0x002a }
        goto L_0x004e;
    L_0x005e:
        r0 = move-exception;
        r3 = LOGGER;	 Catch:{ all -> 0x002a }
        r4 = java.util.logging.Level.WARNING;	 Catch:{ all -> 0x002a }
        r5 = "SmackError while pinging server";
        r3.log(r4, r5, r0);	 Catch:{ all -> 0x002a }
        r0 = r1;
        goto L_0x0044;
    L_0x006a:
        r2 = r2 + 1;
        goto L_0x0035;
    L_0x006d:
        r6.maybeSchedulePingServerTask();	 Catch:{ all -> 0x002a }
        goto L_0x0008;
    L_0x0071:
        r0 = LOGGER;	 Catch:{ all -> 0x002a }
        r1 = "XMPPConnection was not authenticated";
        r0.warning(r1);	 Catch:{ all -> 0x002a }
        goto L_0x0008;
    L_0x0079:
        r0 = move-exception;
        goto L_0x0008;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smackx.ping.PingManager.pingServerIfNecessary():void");
    }

    protected void finalize() {
        LOGGER.fine("finalizing PingManager: Shutting down executor service");
        try {
            this.executorService.shutdown();
        } catch (Throwable th) {
            LOGGER.log(Level.WARNING, "finalize() threw throwable", th);
        } finally {
            super.finalize();
        }
    }
}
