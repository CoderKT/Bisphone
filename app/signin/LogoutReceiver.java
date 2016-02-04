package app.signin;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import app.Main;
import app.SplashActivity;
import app.account.AccountManager;
import app.common.CustomImageLoader;
import app.contact.ContactSyncManager;
import app.database.datasource.BroadcastListDataSource;
import app.database.datasource.BroadcastUsersDataSource;
import app.database.datasource.GroupUsersDatasource;
import app.database.provider.ChannelProvider;
import app.database.provider.ContactProvider;
import app.database.provider.ConversationGroupProvider;
import app.database.provider.ConversationNormalProvider;
import app.database.provider.HistoryChannelProvider;
import app.database.provider.HistoryGroupProvider;
import app.database.provider.HistoryOneToOneProvider;
import app.database.provider.StickerPackProvider;
import app.database.provider.StickerProvider;
import app.events.xmpp.XMPPDisconnectEvent;
import app.notification.NotificationCenter;
import app.storage.Storage;
import app.util.FileUtil;
import app.util.SharedPreferencesUtil;
import app.xmpp.GroupManager;
import de.greenrobot.event.EventBus;

public class LogoutReceiver extends BroadcastReceiver {

    /* renamed from: app.signin.LogoutReceiver.1 */
    class C04691 implements Runnable {
        final /* synthetic */ Context f4456a;
        final /* synthetic */ LogoutReceiver f4457b;

        C04691(LogoutReceiver logoutReceiver, Context context) {
            this.f4457b = logoutReceiver;
            this.f4456a = context;
        }

        public void run() {
            EventBus.m12779a().m12795d(new XMPPDisconnectEvent());
            ContentResolver contentResolver = this.f4456a.getContentResolver();
            contentResolver.delete(HistoryOneToOneProvider.f2383a, null, null);
            contentResolver.delete(HistoryGroupProvider.f2380a, null, null);
            contentResolver.delete(HistoryChannelProvider.f2377a, null, null);
            contentResolver.delete(ConversationNormalProvider.f2373a, null, null);
            contentResolver.delete(HistoryOneToOneProvider.f2383a, null, null);
            contentResolver.delete(ContactProvider.f2364a, null, null);
            contentResolver.delete(ConversationGroupProvider.f2368a, null, null);
            contentResolver.delete(StickerProvider.f2394a, null, null);
            contentResolver.delete(StickerPackProvider.f2391a, null, null);
            contentResolver.delete(ChannelProvider.f2360a, null, null);
            contentResolver.delete(ChannelProvider.f2360a, null, null);
            GroupUsersDatasource.m4639a().m4652b();
            BroadcastListDataSource.m4504a().m4512b();
            BroadcastUsersDataSource.m4519a().m4524b();
            FileUtil.m7018a(this.f4456a);
            CustomImageLoader.m4009a().m4021b();
            if (this.f4456a.getExternalCacheDir() != null) {
                FileUtil.m7021a(this.f4456a.getExternalCacheDir());
            }
            if (this.f4456a.getExternalFilesDir(null) != null) {
                FileUtil.m7021a(this.f4456a.getExternalFilesDir(null));
            }
            Storage.m6937a(this.f4456a);
            GroupManager.m7323a().m7369e();
        }
    }

    public void onReceive(Context context, Intent intent) {
        NotificationCenter.m6606a().m6629j();
        ContactSyncManager.m4495a().m4497b();
        new Thread(new C04691(this, context)).start();
        AccountManager.m3934a().m3936b();
        SharedPreferencesUtil.m7050a();
        Main.f1926a.m5679b("Logout: Restarting");
        Intent intent2 = new Intent(context, SplashActivity.class);
        intent2.addFlags(268435456);
        intent2.addFlags(32768);
        context.startActivity(intent2);
    }
}
