package app.signin;

import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings.Secure;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import app.Main;
import app.SplashActivity.EntryPoint;
import app.account.AccountManager;
import app.common.Constants;
import app.common.CustomDialogFragment.Builder;
import app.util.DeviceUtils;
import app.util.DialogsUtil;
import app.util.KeyboardUtil;
import app.util.PhoneNumberUtil;
import app.util.SharedPreferencesUtil;
import butterknife.ButterKnife;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.client.HttpResponseException;
import cz.msebera.android.httpclient.entity.StringEntity;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class VerificationFragment extends Fragment {
    TextView f4515a;
    Button f4516b;
    Button f4517c;
    Button f4518d;
    ProgressBar f4519e;
    EditText f4520f;
    ImageView f4521g;
    TextView f4522h;
    TextView f4523i;
    ScrollView f4524j;
    TokenRequestLimitChecker f4525k;
    ExtendedCountDown f4526l;
    private final long f4527m;
    private final long f4528n;
    private ProgressDialog f4529o;
    private SMSReceiver f4530p;

    /* renamed from: app.signin.VerificationFragment.1 */
    class C04831 implements OnFocusChangeListener {
        final /* synthetic */ VerificationFragment f4503a;

        C04831(VerificationFragment verificationFragment) {
            this.f4503a = verificationFragment;
        }

        public void onFocusChange(View view, boolean z) {
            if (z) {
                ObjectAnimator.ofInt(this.f4503a.f4524j, "scrollY", new int[]{0, 250}).setDuration(1500).start();
            } else {
                KeyboardUtil.m7031a(this.f4503a.getActivity(), this.f4503a.f4520f.getWindowToken());
            }
        }
    }

    /* renamed from: app.signin.VerificationFragment.2 */
    class C04842 implements OnClickListener {
        final /* synthetic */ VerificationFragment f4504a;

        C04842(VerificationFragment verificationFragment) {
            this.f4504a = verificationFragment;
        }

        public void onClick(View view) {
            ObjectAnimator duration = ObjectAnimator.ofInt(this.f4504a.f4524j, "scrollY", new int[]{0, 250}).setDuration(0);
            duration.setStartDelay(500);
            duration.start();
        }
    }

    /* renamed from: app.signin.VerificationFragment.3 */
    class C04853 implements TextWatcher {
        final /* synthetic */ VerificationFragment f4505a;

        C04853(VerificationFragment verificationFragment) {
            this.f4505a = verificationFragment;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (charSequence.length() < 4) {
                this.f4505a.f4516b.setClickable(false);
            } else {
                this.f4505a.f4516b.setClickable(true);
            }
        }

        public void afterTextChanged(Editable editable) {
        }
    }

    /* renamed from: app.signin.VerificationFragment.4 */
    class C04864 extends JsonHttpResponseHandler {
        final /* synthetic */ VerificationFragment f4506a;

        C04864(VerificationFragment verificationFragment) {
            this.f4506a = verificationFragment;
        }

        public void m6904a(int i, Header[] headerArr, JSONObject jSONObject) {
            if (this.f4506a.isAdded()) {
                this.f4506a.m6926i();
                Main.f1926a.m5681c("verifyToken Response: " + jSONObject.toString());
                try {
                    String string = jSONObject.getString(this.f4506a.getString(2131296539));
                    if (string.contains("@")) {
                        string = string.substring(0, string.indexOf("@"));
                    }
                    String string2 = jSONObject.getString(this.f4506a.getString(2131296536));
                    SharedPreferencesUtil.m7055a(this.f4506a.getString(2131296916), EntryPoint.home.toString());
                    if (this.f4506a.m6914a(string, string2)) {
                        KeyboardUtil.m7030a(this.f4506a.getActivity());
                        this.f4506a.m6923f();
                    }
                } catch (Throwable e) {
                    Main.f1926a.m5682c(e);
                    this.f4506a.m6927j();
                }
            }
        }

        public void m6903a(int i, Header[] headerArr, String str, Throwable th) {
            if (this.f4506a.isAdded()) {
                this.f4506a.m6926i();
                Main.f1926a.m5682c(th);
                if (th instanceof HttpResponseException) {
                    Main.m3905a(this.f4506a.getString(2131296827));
                } else {
                    this.f4506a.m6927j();
                }
            }
        }
    }

    /* renamed from: app.signin.VerificationFragment.5 */
    class C04875 implements DialogInterface.OnClickListener {
        final /* synthetic */ VerificationFragment f4507a;

        C04875(VerificationFragment verificationFragment) {
            this.f4507a = verificationFragment;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            this.f4507a.getFragmentManager().popBackStackImmediate();
        }
    }

    /* renamed from: app.signin.VerificationFragment.6 */
    class C04886 implements DialogInterface.OnClickListener {
        final /* synthetic */ VerificationFragment f4508a;

        C04886(VerificationFragment verificationFragment) {
            this.f4508a = verificationFragment;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            this.f4508a.m6925h();
            this.f4508a.m6921e();
        }
    }

    /* renamed from: app.signin.VerificationFragment.7 */
    class C04897 implements DialogInterface.OnClickListener {
        final /* synthetic */ VerificationFragment f4509a;

        C04897(VerificationFragment verificationFragment) {
            this.f4509a = verificationFragment;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            this.f4509a.getFragmentManager().popBackStackImmediate();
        }
    }

    /* renamed from: app.signin.VerificationFragment.8 */
    class C04918 implements DialogInterface.OnClickListener {
        final /* synthetic */ VerificationFragment f4511a;

        /* renamed from: app.signin.VerificationFragment.8.1 */
        class C04901 extends JsonHttpResponseHandler {
            final /* synthetic */ C04918 f4510a;

            C04901(C04918 c04918) {
                this.f4510a = c04918;
            }

            public void m6906a(int i, Header[] headerArr, JSONObject jSONObject) {
                if (this.f4510a.f4511a.isAdded()) {
                    this.f4510a.f4511a.m6926i();
                    Main.f1926a.m5681c("response: " + jSONObject.toString());
                    this.f4510a.f4511a.f4525k.m6898c();
                    this.f4510a.f4511a.m6910a(14525);
                }
            }

            public void m6905a(int i, Header[] headerArr, String str, Throwable th) {
                if (this.f4510a.f4511a.isAdded()) {
                    Main.f1926a.m5682c(th);
                    this.f4510a.f4511a.m6926i();
                    this.f4510a.f4511a.m6927j();
                }
            }
        }

        C04918(VerificationFragment verificationFragment) {
            this.f4511a = verificationFragment;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            HttpEntity stringEntity;
            this.f4511a.m6925h();
            JSONObject a = this.f4511a.m6915b("call");
            AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
            asyncHttpClient.m10580a(20000);
            try {
                stringEntity = new StringEntity(a.toString());
            } catch (Throwable e) {
                Main.f1926a.m5682c(e);
                stringEntity = null;
            }
            asyncHttpClient.m10572a(this.f4511a.getActivity(), Constants.m3957b(), stringEntity, "application/json", new C04901(this));
        }
    }

    /* renamed from: app.signin.VerificationFragment.9 */
    class C04929 extends JsonHttpResponseHandler {
        final /* synthetic */ VerificationFragment f4512a;

        C04929(VerificationFragment verificationFragment) {
            this.f4512a = verificationFragment;
        }

        public void m6908a(int i, Header[] headerArr, JSONObject jSONObject) {
            this.f4512a.m6926i();
            Main.f1926a.m5681c("response: " + jSONObject.toString());
            this.f4512a.f4525k.m6898c();
            this.f4512a.m6910a(14521);
        }

        public void m6907a(int i, Header[] headerArr, String str, Throwable th) {
            Main.f1926a.m5682c(th);
            this.f4512a.m6926i();
            this.f4512a.m6927j();
        }
    }

    class ExtendedCountDown extends CountDownTimer {
        int f4513a;
        final /* synthetic */ VerificationFragment f4514b;

        public ExtendedCountDown(VerificationFragment verificationFragment, long j, long j2) {
            this.f4514b = verificationFragment;
            super(j, j2);
            this.f4513a = ((int) j) / 1000;
        }

        public void onTick(long j) {
            this.f4513a--;
            this.f4514b.m6916b(this.f4513a);
        }

        public void onFinish() {
            if (!this.f4514b.isAdded()) {
                return;
            }
            if (this.f4514b.f4525k.m6897b()) {
                this.f4514b.m6910a(14522);
            } else {
                this.f4514b.m6910a(14523);
            }
        }
    }

    public VerificationFragment() {
        this.f4527m = 60000;
        this.f4528n = 1000;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2130903152, viewGroup, false);
        ButterKnife.m7744a(this, inflate);
        this.f4516b.setClickable(false);
        this.f4530p = new SMSReceiver(this);
        this.f4526l = new ExtendedCountDown(this, 60000, 1000);
        this.f4525k = new TokenRequestLimitChecker(getActivity());
        this.f4519e.setMax(60);
        this.f4520f.setOnFocusChangeListener(new C04831(this));
        this.f4520f.setOnClickListener(new C04842(this));
        this.f4520f.addTextChangedListener(new C04853(this));
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (isAdded()) {
            m6910a(14521);
        }
    }

    public void onResume() {
        super.onResume();
        this.f4530p.m6862a();
    }

    public void onPause() {
        this.f4530p.m6863b();
        super.onPause();
    }

    public void onDestroy() {
        m6929l();
        m6930a();
        super.onDestroy();
    }

    public void m6930a() {
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                m6930a();
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    void m6932b() {
        HttpEntity stringEntity;
        KeyboardUtil.m7031a(getActivity(), this.f4520f.getWindowToken());
        m6925h();
        Map hashMap = new HashMap();
        String a = DeviceUtils.m7012a(Main.f1927b);
        String str = SharedPreferencesUtil.m7051a(2131296925, getActivity()) + SharedPreferencesUtil.m7051a(2131296935, getActivity());
        String obj = this.f4520f.getText().toString();
        hashMap.put(getString(2131296539), str);
        hashMap.put(getString(2131296540), a);
        hashMap.put(getString(2131296537), obj);
        hashMap.put(getString(2131296535), "bisphone.com");
        JSONObject jSONObject = new JSONObject(hashMap);
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.m10580a(20000);
        try {
            stringEntity = new StringEntity(jSONObject.toString());
        } catch (Throwable e) {
            Main.f1926a.m5682c(e);
            stringEntity = null;
        }
        asyncHttpClient.m10572a(getActivity(), Constants.m3958c(), stringEntity, "application/json", new C04864(this));
    }

    void m6933c() {
        String g = m6924g();
        String string = getString(2131296880);
        new Builder().m3969a(string).m3973b(getString(2131296873) + g).m3970a(getString(2131296876), new C04886(this)).m3974b(getString(2131296877), new C04875(this)).m3971a(true).m3972a().show(getFragmentManager(), "Resend_Token_Tag");
    }

    void m6934d() {
        String g = m6924g();
        String format = String.format("%s?", new Object[]{getString(2131296879)});
        new Builder().m3969a(format).m3973b(getString(2131296873) + g).m3970a(getString(2131296876), new C04918(this)).m3974b(getString(2131296877), new C04897(this)).m3971a(true).m3972a().show(getFragmentManager(), "Resend_Token_Tag");
    }

    public void m6931a(String str) {
        Main.f1926a.m5681c("token: " + str);
        this.f4520f.setText(str);
        m6929l();
        m6910a(14524);
        m6926i();
    }

    private void m6921e() {
        HttpEntity stringEntity;
        JSONObject b = m6915b("sms");
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.m10580a(20000);
        try {
            stringEntity = new StringEntity(b.toString());
        } catch (Throwable e) {
            Main.f1926a.m5682c(e);
            stringEntity = null;
        }
        asyncHttpClient.m10572a(getActivity(), Constants.m3957b(), stringEntity, "application/json", new C04929(this));
    }

    private JSONObject m6915b(String str) {
        String string = Secure.getString(Main.f1927b.getContentResolver(), "android_id");
        Map hashMap = new HashMap();
        hashMap.put("uuid", string);
        hashMap.put("username", SharedPreferencesUtil.m7051a(2131296925, Main.f1927b) + SharedPreferencesUtil.m7051a(2131296935, Main.f1927b));
        hashMap.put("host", "bisphone.com");
        hashMap.put(getString(2131296538), str);
        JSONObject jSONObject = new JSONObject(hashMap);
        Main.f1926a.m5683d(jSONObject.toString());
        return jSONObject;
    }

    private void m6923f() {
        Intent intent = new Intent(getActivity(), ProfileInfo.class);
        intent.putExtra("FirstRun", true);
        startActivity(intent);
        getActivity().finish();
    }

    private boolean m6914a(String str, String str2) {
        return AccountManager.m3934a().m3935a(str, str2);
    }

    private String m6924g() {
        return PhoneNumberUtil.m7046b(SharedPreferencesUtil.m7052a(getString(2131296925)), SharedPreferencesUtil.m7052a(getString(2131296935)));
    }

    private void m6910a(int i) {
        if (isAdded()) {
            switch (i) {
                case 14521:
                    m6928k();
                    this.f4516b.setVisibility(0);
                    this.f4516b.animate().alpha(1.0f).setDuration(1500);
                    this.f4517c.setEnabled(false);
                    this.f4517c.animate().alpha(0.5f).setDuration(1500);
                    this.f4519e.setVisibility(0);
                    this.f4519e.animate().alpha(1.0f).setDuration(1500);
                    this.f4522h.setVisibility(0);
                    this.f4522h.animate().alpha(1.0f).setDuration(1500);
                    this.f4518d.animate().alpha(0.5f).setDuration(1500);
                    this.f4518d.setEnabled(false);
                    this.f4523i.setVisibility(4);
                    this.f4515a.setAlpha(0.0f);
                    this.f4515a.setText(getResources().getString(2131296886));
                    this.f4515a.animate().alpha(1.0f).setDuration(1500).setStartDelay(0);
                case 14522:
                    this.f4516b.setVisibility(0);
                    this.f4516b.animate().alpha(1.0f).setDuration(1500);
                    this.f4517c.setVisibility(0);
                    this.f4517c.animate().alpha(1.0f).setDuration(1500);
                    this.f4517c.setEnabled(true);
                    this.f4519e.animate().alpha(0.0f).setDuration(1500);
                    this.f4519e.setVisibility(8);
                    this.f4522h.animate().alpha(0.0f).setDuration(1500);
                    this.f4522h.setVisibility(8);
                    this.f4518d.setVisibility(0);
                    this.f4518d.animate().alpha(1.0f).setDuration(1500);
                    this.f4518d.setEnabled(true);
                    this.f4523i.setVisibility(4);
                    this.f4515a.setText(getString(2131296885));
                    this.f4515a.setAlpha(0.0f);
                    this.f4515a.animate().alpha(1.0f).setDuration(1500).setStartDelay(1500);
                case 14523:
                    this.f4516b.setVisibility(0);
                    this.f4516b.setAlpha(1.0f);
                    this.f4517c.setEnabled(false);
                    this.f4517c.animate().alpha(0.5f).setDuration(1500);
                    this.f4519e.setVisibility(8);
                    this.f4522h.setVisibility(8);
                    this.f4518d.animate().alpha(0.5f).setDuration(1500);
                    this.f4518d.setEnabled(false);
                    this.f4523i.setText(this.f4525k.m6899d());
                    this.f4523i.setVisibility(0);
                    this.f4515a.setText(getString(2131296884));
                case 14524:
                    this.f4516b.setVisibility(0);
                    this.f4516b.setAlpha(1.0f);
                    this.f4517c.setEnabled(false);
                    this.f4517c.animate().alpha(0.5f).setDuration(1500);
                    this.f4519e.setVisibility(8);
                    this.f4522h.setVisibility(8);
                    this.f4518d.animate().alpha(0.5f).setDuration(1500);
                    this.f4518d.setEnabled(false);
                    this.f4523i.setVisibility(4);
                    this.f4521g.setVisibility(0);
                case 14525:
                    m6928k();
                    this.f4516b.setVisibility(0);
                    this.f4516b.animate().alpha(1.0f).setDuration(1500);
                    this.f4517c.setEnabled(false);
                    this.f4517c.animate().alpha(0.5f).setDuration(1500);
                    this.f4519e.setVisibility(0);
                    this.f4519e.animate().alpha(1.0f).setDuration(1500);
                    this.f4522h.setVisibility(0);
                    this.f4522h.animate().alpha(1.0f).setDuration(1500);
                    this.f4518d.animate().alpha(0.5f).setDuration(1500);
                    this.f4518d.setEnabled(false);
                    this.f4523i.setVisibility(4);
                    this.f4515a.setAlpha(0.0f);
                    this.f4515a.setText(getResources().getString(2131296872));
                    this.f4515a.animate().alpha(1.0f).setDuration(1500).setStartDelay(0);
                default:
                    throw new IllegalArgumentException("Not a valid UI Mode");
            }
        }
    }

    private void m6916b(int i) {
        this.f4519e.setProgress(i);
        this.f4522h.setText(i + "");
    }

    private void m6925h() {
        if (isAdded()) {
            this.f4529o = new ProgressDialog(getActivity());
            this.f4529o.setMessage(getString(2131296644));
            this.f4529o.setCanceledOnTouchOutside(false);
            this.f4529o.setIndeterminate(true);
            this.f4529o.show();
        }
    }

    private void m6926i() {
        if (isAdded() && this.f4529o != null) {
            this.f4529o.dismiss();
        }
    }

    private void m6927j() {
        if (isAdded()) {
            DialogsUtil.m7014a(getActivity());
        }
    }

    private void m6928k() {
        this.f4526l = new ExtendedCountDown(this, 60000, 1000);
        this.f4526l.start();
    }

    private void m6929l() {
        if (this.f4526l != null) {
            this.f4526l.cancel();
        }
    }
}
