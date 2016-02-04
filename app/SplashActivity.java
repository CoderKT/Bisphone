package app;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog.Builder;
import app.account.AccountManager;
import app.common.LocalizeActivity;
import app.common.entity.ContactEntity;
import app.database.DatabaseEvent;
import app.database.DatabaseHelper;
import app.database.UpgradeDatabase;
import app.database.datasource.ContactDataSource;
import app.home.HomeActivity;
import app.signin.LogoutReceiver;
import app.signin.ProfileInfo;
import app.signin.SignInActivity;
import app.util.SharedPreferencesUtil;
import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import de.greenrobot.event.EventBus;
import se.emilsjolander.stickylistheaders.C1128R;

public class SplashActivity extends LocalizeActivity {
    RoundCornerProgressBar f1954k;

    /* renamed from: app.SplashActivity.1 */
    class C01111 implements OnClickListener {
        final /* synthetic */ SplashActivity f1939a;

        C01111(SplashActivity splashActivity) {
            this.f1939a = splashActivity;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse("market://details?id=com.bistalk.bisphone"));
            this.f1939a.startActivity(Intent.createChooser(intent, this.f1939a.getString(2131296448)));
            this.f1939a.finish();
        }
    }

    /* renamed from: app.SplashActivity.2 */
    class C01122 implements OnClickListener {
        final /* synthetic */ SplashActivity f1940a;

        C01122(SplashActivity splashActivity) {
            this.f1940a = splashActivity;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            this.f1940a.finish();
        }
    }

    /* renamed from: app.SplashActivity.3 */
    class C01133 implements Runnable {
        final /* synthetic */ SplashActivity f1941a;

        C01133(SplashActivity splashActivity) {
            this.f1941a = splashActivity;
        }

        public void run() {
            Intent intent = new Intent(this.f1941a, SignInActivity.class);
            intent.addFlags(67108864);
            this.f1941a.startActivity(intent);
            this.f1941a.finish();
        }
    }

    /* renamed from: app.SplashActivity.4 */
    class C01144 implements Runnable {
        final /* synthetic */ DatabaseEvent f1942a;
        final /* synthetic */ SplashActivity f1943b;

        C01144(SplashActivity splashActivity, DatabaseEvent databaseEvent) {
            this.f1943b = splashActivity;
            this.f1942a = databaseEvent;
        }

        public void run() {
            if (((float) this.f1942a.f2294a) >= this.f1943b.f1954k.getProgress()) {
                this.f1943b.f1954k.setProgress(this.f1943b.f1954k.getProgress() + 1.0f);
                this.f1943b.onEvent(new DatabaseEvent(this.f1942a.f2294a + 1, this.f1942a.f2295b));
            }
        }
    }

    /* renamed from: app.SplashActivity.5 */
    /* synthetic */ class C01155 {
        static final /* synthetic */ int[] f1944a;

        static {
            f1944a = new int[EntryPoint.values().length];
            try {
                f1944a[EntryPoint.profile.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f1944a[EntryPoint.home.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f1944a[EntryPoint.verification.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public enum EntryPoint {
        verification,
        home,
        error,
        profile;

        public static EntryPoint m3918a(String str) {
            try {
                return valueOf(str);
            } catch (Exception e) {
                return error;
            }
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903103);
    }

    protected void onResume() {
        super.onResume();
        EventBus.m12779a().m12791a((Object) this);
        String a = SharedPreferencesUtil.m7052a("udf");
        if (DatabaseHelper.f2296a) {
            m3927j();
        } else if (a == null) {
            m3928k();
        } else if (a.charAt(0) == 'f') {
            m3927j();
            String[] split = a.split("-");
            new UpgradeDatabase(DatabaseHelper.m4499a(this).getWritableDatabase(), Integer.parseInt(split[1]), Integer.parseInt(split[2])).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        } else {
            m3928k();
        }
    }

    protected void onPause() {
        super.onPause();
        EventBus.m12779a().m12794c(this);
    }

    private void m3927j() {
        getWindow().addFlags(128);
        setRequestedOrientation(1);
        findViewById(2131755295).setVisibility(0);
        this.f1954k = (RoundCornerProgressBar) findViewById(2131755294);
        this.f1954k.setBackgroundLayoutColor(Color.parseColor("#D8D8D8"));
        this.f1954k.setProgressColor(Color.parseColor("#4A90E2"));
        this.f1954k.setVisibility(0);
    }

    private void m3928k() {
        EntryPoint a = EntryPoint.m3918a(SharedPreferencesUtil.m7052a(getString(2131296916)));
        if (System.currentTimeMillis() > 1461974400000L) {
            m3929l();
        } else if (AccountManager.m3934a().m3939e()) {
            switch (C01155.f1944a[a.ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    ContactEntity g = ContactDataSource.m4553a().m4581g();
                    if (g == null || g.m4209l() == null || g.m4209l().length() == 0) {
                        String a2 = SharedPreferencesUtil.m7052a(getResources().getString(2131296933));
                        if (a2 == null || a2.length() <= 3) {
                            m3931n();
                            return;
                        }
                        ContactDataSource.m4553a().m4578e();
                        ContactDataSource.m4553a().m4573c(SharedPreferencesUtil.m7052a(getResources().getString(2131296933)));
                        m3932o();
                        return;
                    }
                    m3932o();
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    m3931n();
                default:
                    if (SharedPreferencesUtil.m7052a(getString(2131296925)) != null) {
                        SharedPreferencesUtil.m7055a(getString(2131296916), EntryPoint.home.toString());
                        m3932o();
                        return;
                    }
                    m3933p();
            }
        } else {
            switch (C01155.f1944a[a.ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    m3933p();
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    m3930m();
                default:
                    m3930m();
            }
        }
    }

    private void m3929l() {
        Builder builder = new Builder(this, 2131558536);
        builder.m1980a(getResources().getString(2131296335));
        builder.m1986b(getResources().getString(2131296336));
        builder.m1975a(2131296624, new C01111(this));
        builder.m1989c(2130837729);
        builder.m1987b(getString(2131296380), new C01122(this));
        builder.m1992c();
    }

    private void m3930m() {
        setContentView(2130903103);
        new Handler().postDelayed(new C01133(this), 2000);
    }

    private void m3931n() {
        startActivity(new Intent(this, ProfileInfo.class));
        finish();
    }

    private void m3932o() {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

    private void m3933p() {
        Intent intent = new Intent(this, LogoutReceiver.class);
        intent.setAction("com.bistalk.bisphone.signin.LogoutReceiver.ACTION_LOGOUT");
        sendBroadcast(intent);
    }

    public void onBackPressed() {
    }

    public void onEvent(DatabaseEvent databaseEvent) {
        if (this.f1954k != null && !isFinishing() && ((float) databaseEvent.f2294a) >= this.f1954k.getProgress()) {
            this.f1954k.setProgress((float) databaseEvent.f2294a);
            if (databaseEvent.f2294a == 100) {
                m3928k();
            } else if (this.f1954k.getProgress() < ((float) databaseEvent.f2295b)) {
                new Handler().postDelayed(new C01144(this, databaseEvent), 5000);
            }
        }
    }
}
