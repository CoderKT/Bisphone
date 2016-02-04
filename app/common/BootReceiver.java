package app.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import app.Main;
import app.account.AccountManager;
import app.xmpp.XMPPService;

public class BootReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction()) && AccountManager.m3934a().m3939e()) {
            Main.f1926a.m5681c("Start up event, account found, starting xmpp");
            context.startService(new Intent(context, XMPPService.class));
        }
    }
}
