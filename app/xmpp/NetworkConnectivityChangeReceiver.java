package app.xmpp;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import app.Main;
import app.account.AccountManager;
import app.events.ConnectionListenerEvent;
import app.events.ConnectionListenerEvent.ConnectionType;
import app.events.net.NetworkConnectionEstablishedEvent;
import de.greenrobot.event.EventBus;

public class NetworkConnectivityChangeReceiver extends BroadcastReceiver {
    static boolean f4940a;
    private Handler f4941b;

    /* renamed from: app.xmpp.NetworkConnectivityChangeReceiver.1 */
    class C05491 implements Runnable {
        final /* synthetic */ Context f4937a;
        final /* synthetic */ NetworkConnectivityChangeReceiver f4938b;

        C05491(NetworkConnectivityChangeReceiver networkConnectivityChangeReceiver, Context context) {
            this.f4938b = networkConnectivityChangeReceiver;
            this.f4937a = context;
        }

        public void run() {
            if (!AccountManager.m3934a().m3939e()) {
                return;
            }
            if (this.f4938b.m7395a(this.f4937a, XMPPService.class)) {
                EventBus.m12779a().m12795d(new NetworkConnectionEstablishedEvent());
            } else {
                this.f4937a.startService(new Intent(this.f4937a, XMPPService.class));
            }
        }
    }

    /* renamed from: app.xmpp.NetworkConnectivityChangeReceiver.2 */
    class C05502 implements Runnable {
        final /* synthetic */ NetworkConnectivityChangeReceiver f4939a;

        C05502(NetworkConnectivityChangeReceiver networkConnectivityChangeReceiver) {
            this.f4939a = networkConnectivityChangeReceiver;
        }

        public void run() {
            NetworkConnectivityChangeReceiver.f4940a = false;
        }
    }

    public NetworkConnectivityChangeReceiver() {
        this.f4941b = new Handler();
    }

    static {
        f4940a = false;
    }

    public void onReceive(Context context, Intent intent) {
        boolean a = m7394a(context);
        if (a && !f4940a) {
            m7393a();
            Main.f1926a.m5681c("Connected");
            this.f4941b.postDelayed(new C05491(this, context), 1000);
        } else if (!a) {
            EventBus.m12779a().m12795d(new ConnectionListenerEvent(ConnectionType.waitingForNetwork));
        }
    }

    public static boolean m7394a(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return false;
        }
        return activeNetworkInfo.isConnectedOrConnecting();
    }

    private void m7393a() {
        f4940a = true;
        this.f4941b.postDelayed(new C05502(this), 2500);
    }

    private boolean m7395a(Context context, Class<?> cls) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager == null) {
            return false;
        }
        for (RunningServiceInfo runningServiceInfo : activityManager.getRunningServices(Integer.MAX_VALUE)) {
            if (cls.getName().equals(runningServiceInfo.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
