package app.common;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;
import app.Main;
import app.util.PermissionUtil;
import app.xmpp.XMPPService;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.Snackbar.SnackbarDuration;
import com.nispok.snackbar.SnackbarManager;
import com.nispok.snackbar.listeners.ActionClickListener;
import se.emilsjolander.stickylistheaders.C1128R;

public class LocalizeActivity extends FragmentActivity {
    private static IntentFilter f1950k;
    Handler f1951l;
    ProgressDialog f1952m;
    protected BroadcastReceiver f1953n;

    /* renamed from: app.common.LocalizeActivity.1 */
    class C01191 implements ActionClickListener {
        final /* synthetic */ LocalizeActivity f2015a;

        C01191(LocalizeActivity localizeActivity) {
            this.f2015a = localizeActivity;
        }

        public void m4054a(Snackbar snackbar) {
            snackbar.m10875b();
        }
    }

    /* renamed from: app.common.LocalizeActivity.2 */
    class C01202 extends BroadcastReceiver {
        final /* synthetic */ LocalizeActivity f2016a;

        C01202(LocalizeActivity localizeActivity) {
            this.f2016a = localizeActivity;
        }

        public void onReceive(Context context, Intent intent) {
            String stringExtra = intent.getStringExtra("result_key");
            if (stringExtra != null) {
                if (intent.hasExtra("result_migration")) {
                    this.f2016a.m3920a(context, intent.getBooleanExtra("result_migration", false));
                } else {
                    this.f2016a.m3922a(stringExtra);
                }
            }
        }
    }

    public LocalizeActivity() {
        this.f1953n = new C01202(this);
    }

    static {
        f1950k = new IntentFilter();
        f1950k.addAction("result_key");
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        m3924g();
        setRequestedOrientation(2);
        this.f1951l = new Handler(Looper.getMainLooper());
    }

    protected void onResume() {
        super.onResume();
        Main.m3907b();
    }

    protected void onPause() {
        super.onPause();
        Main.m3910c();
    }

    protected void onStart() {
        super.onStart();
        registerReceiver(this.f1953n, f1950k);
    }

    protected void onStop() {
        super.onStop();
        unregisterReceiver(this.f1953n);
    }

    public void setContentView(int i) {
        super.setContentView(i);
        m3923f();
    }

    protected void m3923f() {
    }

    protected void m3924g() {
        getBaseContext().getResources().updateConfiguration(Main.f1928c, getBaseContext().getResources().getDisplayMetrics());
    }

    protected void m3922a(String str) {
        SnackbarManager.m10886a(Snackbar.m10825a((Context) this).m10868a((CharSequence) str).m10865a(getResources().getColor(2131689628)).m10869a(true).m10866a(SnackbarDuration.LENGTH_INDEFINITE).m10881d(false).m10873b(getResources().getString(2131296394)).m10867a(new C01191(this)));
    }

    private void m3919a(Context context) {
        startService(new Intent(context, XMPPService.class));
    }

    private void m3920a(Context context, boolean z) {
        if (z) {
            m3919a(context);
            m3926i();
            return;
        }
        m3925h();
    }

    public void m3925h() {
        if (!isFinishing()) {
            try {
                this.f1952m = new ProgressDialog(this);
                this.f1952m.setMessage(getResources().getString(2131296485));
                this.f1952m.setCanceledOnTouchOutside(false);
                this.f1952m.setCancelable(false);
                this.f1952m.setIndeterminate(true);
                this.f1952m.show();
            } catch (Exception e) {
            }
        }
    }

    public void m3926i() {
        if (this.f1952m != null && !isFinishing()) {
            this.f1952m.dismiss();
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (iArr.length != 0 && iArr[0] != 0) {
            switch (i) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    PermissionUtil.m7043a((Activity) this, getString(2131296641), getString(2131296640));
                default:
            }
        }
    }
}
