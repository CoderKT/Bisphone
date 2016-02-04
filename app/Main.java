package app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.database.sqlite.SQLiteException;
import android.os.Handler;
import android.os.Looper;
import android.provider.ContactsContract.Contacts;
import android.support.multidex.MultiDex;
import android.widget.Toast;
import app.account.AccountManager;
import app.common.CustomImageLoader;
import app.common.entity.ConversationGroupEntity;
import app.contact.ContactComparatorService;
import app.database.DatabaseHelper;
import app.database.datasource.ContactDataSource;
import app.database.datasource.HistoryChannelDataSource;
import app.database.datasource.HistoryGroupDataSource;
import app.database.datasource.HistoryNormalMessageDataSource;
import app.localization.Localize;
import app.logger.DefaultLog;
import app.logger.ILog;
import app.notification.BadgeHandler;
import app.storage.Storage;
import app.util.SharedPreferencesUtil;
import app.xmpp.GroupManager;
import app.xmpp.packet.groupv2.SettingXE.NotificationState;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.jivesoftware.smackx.privacy.packet.PrivacyItem;

public class Main extends Application {
    public static ILog f1926a;
    public static Context f1927b;
    public static Configuration f1928c;
    public static HashMap<String, Boolean> f1929d;
    private static long f1930e;
    private static long f1931f;
    private static Boolean f1932g;
    private static Handler f1933h;
    private static Runnable f1934m;
    private boolean f1935i;
    private Timer f1936j;
    private TimerTask f1937k;
    private ContentObserver f1938l;

    /* renamed from: app.Main.1 */
    class C01041 extends TimerTask {
        final /* synthetic */ Main f1920a;

        C01041(Main main) {
            this.f1920a = main;
        }

        public void run() {
        }
    }

    /* renamed from: app.Main.2 */
    class C01062 extends ContentObserver {
        final /* synthetic */ Main f1922a;

        /* renamed from: app.Main.2.1 */
        class C01051 extends TimerTask {
            final /* synthetic */ C01062 f1921a;

            C01051(C01062 c01062) {
                this.f1921a = c01062;
            }

            public void run() {
                try {
                    if (!ContactDataSource.m4553a().m4580f()) {
                        return;
                    }
                } catch (SQLiteException e) {
                }
                Main.f1926a.m5683d("ContactsContract.Contacts Observer: Starting ContactComparatorService");
                if (!this.f1921a.f1922a.f1935i) {
                    this.f1921a.f1922a.startService(new Intent(Main.f1927b, ContactComparatorService.class));
                }
            }
        }

        C01062(Main main, Handler handler) {
            this.f1922a = main;
            super(handler);
        }

        public void onChange(boolean z) {
            super.onChange(z);
            Main.f1926a.m5685e("ContactsContract.Contacts Observer: Resetting Task");
            this.f1922a.f1937k.cancel();
            this.f1922a.f1937k = null;
            this.f1922a.f1937k = new C01051(this);
            this.f1922a.f1936j.schedule(this.f1922a.f1937k, 10000);
        }
    }

    /* renamed from: app.Main.3 */
    class C01073 implements Runnable {
        final /* synthetic */ Main f1923a;

        C01073(Main main) {
            this.f1923a = main;
        }

        public void run() {
            try {
                int b = (HistoryNormalMessageDataSource.m4727b(Main.f1927b) + HistoryGroupDataSource.m4697b(Main.f1927b)) + HistoryChannelDataSource.m4673b(Main.f1927b);
                if (b > 0) {
                    Main.f1926a.m5681c("Marked " + b + " UPLOADING messages as FAILED_TO_UPLOAD");
                }
                ContactDataSource.m4553a().m4583h();
            } catch (SQLiteException e) {
            }
        }
    }

    /* renamed from: app.Main.4 */
    final class C01084 implements Runnable {
        final /* synthetic */ String f1924a;
        final /* synthetic */ int f1925b;

        C01084(String str, int i) {
            this.f1924a = str;
            this.f1925b = i;
        }

        public void run() {
            Toast.makeText(Main.f1927b, this.f1924a, this.f1925b).show();
        }
    }

    /* renamed from: app.Main.5 */
    final class C01095 implements Runnable {
        C01095() {
        }

        public void run() {
            Main.m3911d();
        }
    }

    public Main() {
        this.f1935i = false;
        this.f1936j = new Timer();
        this.f1937k = new C01041(this);
        this.f1938l = new C01062(this, null);
    }

    static {
        f1930e = 0;
        f1931f = 0;
        f1932g = null;
        f1934m = new C01095();
    }

    public void onCreate() {
        super.onCreate();
        f1927b = getApplicationContext();
        f1928c = Localize.m5600a();
        if (SharedPreferencesUtil.m7058b("udf", PrivacyItem.SUBSCRIPTION_NONE).charAt(0) == 'f') {
            this.f1935i = true;
        }
        m3914g();
        f1926a = new DefaultLog(0);
        Fabric.m12896a((Context) this, new Crashlytics());
        AccountManager a = AccountManager.m3934a();
        if (a.m3939e()) {
            Crashlytics.m7881a(a.m3937c());
            Crashlytics.m7886b(a.m3937c());
        } else {
            Crashlytics.m7881a("Unknown");
            Crashlytics.m7886b("Unknown");
        }
        f1933h = new Handler(getMainLooper());
        CustomImageLoader.m4009a().m4015a(f1927b);
        f1929d = new HashMap();
        getApplicationContext().getContentResolver().registerContentObserver(Contacts.CONTENT_URI, true, this.f1938l);
        if (!this.f1935i) {
            new Handler().postDelayed(new C01073(this), 2000);
        }
        Storage.m6937a(f1927b);
        try {
            SharedPreferencesUtil.m7060b(getString(2131296928), Boolean.valueOf(false));
        } catch (ClassCastException e) {
            SharedPreferencesUtil.m7059b(getString(2131296928));
            SharedPreferencesUtil.m7054a(getString(2131296928), Boolean.valueOf(true));
        }
        if (SharedPreferencesUtil.m7052a(getResources().getString(2131296924)) == null || SharedPreferencesUtil.m7052a(getResources().getString(2131296924)).length() == 0) {
            m3917a();
        }
        if (SharedPreferencesUtil.m7058b(getResources().getString(2131296927), null) == null) {
            m3913f();
        }
    }

    private void m3913f() {
        SharedPreferencesUtil.m7055a(getResources().getString(2131296927), "0");
        SharedPreferencesUtil.m7055a(getResources().getString(2131296938), "2");
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        f1928c = Localize.m5600a();
        m3914g();
    }

    public static void m3905a(String str) {
        m3906a(str, true);
    }

    public static void m3906a(String str, boolean z) {
        int i = z ? 1 : 0;
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Toast.makeText(f1927b, str, i).show();
        } else {
            new Handler(f1927b.getMainLooper()).post(new C01084(str, i));
        }
    }

    private void m3914g() {
        f1927b.getResources().updateConfiguration(f1928c, f1927b.getResources().getDisplayMetrics());
    }

    public void m3917a() {
        int i = 0;
        String[] stringArray = f1927b.getResources().getStringArray(2131361793);
        String[] stringArray2 = f1927b.getResources().getStringArray(2131361797);
        String[] stringArray3 = f1927b.getResources().getStringArray(2131361798);
        String a = SharedPreferencesUtil.m7052a(f1927b.getResources().getString(2131296925));
        for (int i2 = 0; i2 < stringArray.length; i2++) {
            if (stringArray[i2].equals(a)) {
                while (i < stringArray3.length) {
                    if (stringArray2[i2].equals(stringArray3[i])) {
                        SharedPreferencesUtil.m7055a(f1927b.getResources().getString(2131296924), i + "-" + i2);
                        return;
                    }
                    i++;
                }
                return;
            }
        }
    }

    public static void m3907b() {
        if (m3912e()) {
            f1931f = System.currentTimeMillis();
            m3916i();
        }
        f1932g = Boolean.valueOf(false);
        f1933h.removeCallbacks(f1934m);
        m3915h();
        if (GroupManager.m7323a().f4910h != null && GroupManager.m7323a().f4910h.size() != 0 && GroupManager.m7323a().f4905c != null) {
            for (String str : GroupManager.m7323a().f4910h) {
                GroupManager.m7323a().m7340a(((Long) GroupManager.m7323a().f4905c.get(str)).longValue(), str, null);
            }
            GroupManager.m7323a().f4910h.clear();
        }
    }

    private static void m3915h() {
        List arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (ConversationGroupEntity conversationGroupEntity : GroupManager.m7323a().m7374h().values()) {
            if (!(conversationGroupEntity == null || conversationGroupEntity.m4283e() == null)) {
                if (conversationGroupEntity.m4283e().equals("REJECTING") || conversationGroupEntity.m4283e().equals("LEAVING") || conversationGroupEntity.m4283e().equals("LEFT_DELETE") || conversationGroupEntity.m4283e().equals("JOINING") || conversationGroupEntity.m4283e().equals("DESTROYING")) {
                    arrayList.add(conversationGroupEntity);
                } else if (conversationGroupEntity.m4295j() == NotificationState.disablePending) {
                    conversationGroupEntity.m4272a(NotificationState.disable);
                    arrayList2.add(conversationGroupEntity);
                } else if (conversationGroupEntity.m4295j() == NotificationState.soundPending) {
                    conversationGroupEntity.m4272a(NotificationState.sound);
                    arrayList2.add(conversationGroupEntity);
                } else if (conversationGroupEntity.m4295j() == NotificationState.vibratePending) {
                    conversationGroupEntity.m4272a(NotificationState.vibrate);
                    arrayList2.add(conversationGroupEntity);
                } else if (conversationGroupEntity.m4295j() == NotificationState.getPending) {
                    arrayList2.add(conversationGroupEntity);
                }
            }
        }
        GroupManager.m7323a().handlePendingGroupStatus(arrayList);
    }

    public static void m3910c() {
        if (!DatabaseHelper.f2296a) {
            f1933h.postDelayed(f1934m, 1000);
        }
    }

    private static void m3916i() {
        if (f1931f - f1930e > 1800000) {
            GroupManager.m7323a().m7371f();
        }
    }

    public static void m3911d() {
        f1932g = Boolean.valueOf(true);
        f1930e = System.currentTimeMillis();
        BadgeHandler.m6597a().m6604a(true);
    }

    public static boolean m3912e() {
        return f1932g == null || f1932g.booleanValue();
    }

    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.m10a((Context) this);
    }
}
