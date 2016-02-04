package org.jivesoftware.smackx.ping.android;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.SystemClock;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.logging.Logger;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smack.util.Async;
import org.jivesoftware.smackx.ping.PingManager;

public class ServerPingWithAlarmManager extends Manager {
    private static final BroadcastReceiver ALARM_BROADCAST_RECEIVER;
    private static final Map<XMPPConnection, ServerPingWithAlarmManager> INSTANCES;
    private static final Logger LOGGER;
    private static final String PING_ALARM_ACTION = "org.igniterealtime.smackx.ping.ACTION";
    private static AlarmManager sAlarmManager;
    private static Context sContext;
    private static PendingIntent sPendingIntent;
    private boolean mEnabled;

    /* renamed from: org.jivesoftware.smackx.ping.android.ServerPingWithAlarmManager.1 */
    final class C10971 implements ConnectionCreationListener {
        C10971() {
        }

        public void connectionCreated(XMPPConnection xMPPConnection) {
            ServerPingWithAlarmManager.getInstanceFor(xMPPConnection);
        }
    }

    /* renamed from: org.jivesoftware.smackx.ping.android.ServerPingWithAlarmManager.2 */
    final class C10992 extends BroadcastReceiver {

        /* renamed from: org.jivesoftware.smackx.ping.android.ServerPingWithAlarmManager.2.1 */
        class C10981 implements Runnable {
            final /* synthetic */ PingManager val$pingManager;

            C10981(PingManager pingManager) {
                this.val$pingManager = pingManager;
            }

            public void run() {
                this.val$pingManager.pingServerIfNecessary();
            }
        }

        C10992() {
        }

        public void onReceive(Context context, Intent intent) {
            ServerPingWithAlarmManager.LOGGER.fine("Ping Alarm broadcast received");
            synchronized (ServerPingWithAlarmManager.class) {
                Set<Entry> hashSet = new HashSet(ServerPingWithAlarmManager.INSTANCES.entrySet());
            }
            for (Entry entry : hashSet) {
                XMPPConnection xMPPConnection = (XMPPConnection) entry.getKey();
                if (((ServerPingWithAlarmManager) entry.getValue()).isEnabled()) {
                    ServerPingWithAlarmManager.LOGGER.fine("Calling pingServerIfNecessary for connection " + xMPPConnection.getConnectionCounter());
                    Async.go(new C10981(PingManager.getInstanceFor(xMPPConnection)), "PingServerIfNecessary (" + xMPPConnection.getConnectionCounter() + ')');
                } else {
                    ServerPingWithAlarmManager.LOGGER.fine("NOT calling pingServerIfNecessary (disabled) on connection " + xMPPConnection.getConnectionCounter());
                }
            }
        }
    }

    static {
        LOGGER = Logger.getLogger(ServerPingWithAlarmManager.class.getName());
        INSTANCES = new WeakHashMap();
        XMPPConnectionRegistry.addConnectionCreationListener(new C10971());
        ALARM_BROADCAST_RECEIVER = new C10992();
    }

    public static synchronized ServerPingWithAlarmManager getInstanceFor(XMPPConnection xMPPConnection) {
        ServerPingWithAlarmManager serverPingWithAlarmManager;
        synchronized (ServerPingWithAlarmManager.class) {
            serverPingWithAlarmManager = (ServerPingWithAlarmManager) INSTANCES.get(xMPPConnection);
            if (serverPingWithAlarmManager == null) {
                serverPingWithAlarmManager = new ServerPingWithAlarmManager(xMPPConnection);
                INSTANCES.put(xMPPConnection, serverPingWithAlarmManager);
            }
        }
        return serverPingWithAlarmManager;
    }

    private ServerPingWithAlarmManager(XMPPConnection xMPPConnection) {
        super(xMPPConnection);
        this.mEnabled = true;
    }

    public void setEnabled(boolean z) {
        this.mEnabled = z;
    }

    public boolean isEnabled() {
        return this.mEnabled;
    }

    public static void onCreate(Context context) {
        sContext = context;
        context.registerReceiver(ALARM_BROADCAST_RECEIVER, new IntentFilter(PING_ALARM_ACTION));
        sAlarmManager = (AlarmManager) context.getSystemService("alarm");
        sPendingIntent = PendingIntent.getBroadcast(context, 0, new Intent(PING_ALARM_ACTION), 0);
        sAlarmManager.setInexactRepeating(2, SystemClock.elapsedRealtime() + 1800000, 1800000, sPendingIntent);
    }

    public static void onDestroy() {
        sContext.unregisterReceiver(ALARM_BROADCAST_RECEIVER);
        sAlarmManager.cancel(sPendingIntent);
    }
}
