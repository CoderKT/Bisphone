package app.signin;

import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import app.Main;
import app.SplashActivity.EntryPoint;
import app.common.Constants;
import app.util.CountryMapperUtil;
import app.util.DeviceUtils;
import app.util.DialogsUtil;
import app.util.KeyboardUtil;
import app.util.PhoneNumberUtil;
import app.util.SharedPreferencesUtil;
import butterknife.ButterKnife;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.StringEntity;
import java.util.HashMap;
import java.util.Map;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smackx.xhtmlim.XHTMLText;
import org.json.JSONObject;

public class SignInFragment extends Fragment {
    Button f4489a;
    EditText f4490b;
    EditText f4491c;
    EditText f4492d;
    ScrollView f4493e;
    private ProgressDialog f4494f;
    private TokenRequestLimitChecker f4495g;

    /* renamed from: app.signin.SignInFragment.1 */
    class C04751 implements OnEditorActionListener {
        final /* synthetic */ SignInFragment f4481a;

        C04751(SignInFragment signInFragment) {
            this.f4481a = signInFragment;
        }

        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (i != 4) {
                return false;
            }
            this.f4481a.m6890b();
            return true;
        }
    }

    /* renamed from: app.signin.SignInFragment.2 */
    class C04762 implements OnClickListener {
        final /* synthetic */ SignInFragment f4482a;

        C04762(SignInFragment signInFragment) {
            this.f4482a = signInFragment;
        }

        public void onClick(View view) {
            ObjectAnimator duration = ObjectAnimator.ofInt(this.f4482a.f4493e, "scrollY", new int[]{0, 250}).setDuration(0);
            duration.setStartDelay(500);
            duration.start();
        }
    }

    /* renamed from: app.signin.SignInFragment.3 */
    class C04773 implements OnFocusChangeListener {
        final /* synthetic */ SignInFragment f4483a;

        C04773(SignInFragment signInFragment) {
            this.f4483a = signInFragment;
        }

        public void onFocusChange(View view, boolean z) {
            if (z) {
                ObjectAnimator.ofInt(this.f4483a.f4493e, "scrollY", new int[]{0, 250}).setDuration(1500).start();
            } else {
                KeyboardUtil.m7031a(this.f4483a.getActivity(), this.f4483a.f4491c.getWindowToken());
            }
        }
    }

    /* renamed from: app.signin.SignInFragment.4 */
    class C04784 implements OnFocusChangeListener {
        final /* synthetic */ SignInFragment f4484a;

        C04784(SignInFragment signInFragment) {
            this.f4484a = signInFragment;
        }

        public void onFocusChange(View view, boolean z) {
            KeyboardUtil.m7031a(this.f4484a.getActivity(), this.f4484a.f4490b.getWindowToken());
            if (z) {
                this.f4484a.m6889a();
            }
        }
    }

    /* renamed from: app.signin.SignInFragment.5 */
    class C04795 implements TextWatcher {
        final /* synthetic */ SignInFragment f4485a;

        C04795(SignInFragment signInFragment) {
            this.f4485a = signInFragment;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (charSequence.length() <= 0 || charSequence.length() >= 15) {
                this.f4485a.f4489a.setClickable(false);
            } else {
                this.f4485a.f4489a.setClickable(true);
            }
        }

        public void afterTextChanged(Editable editable) {
        }
    }

    /* renamed from: app.signin.SignInFragment.6 */
    class C04806 implements DialogInterface.OnClickListener {
        final /* synthetic */ SignInFragment f4486a;

        C04806(SignInFragment signInFragment) {
            this.f4486a = signInFragment;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
            this.f4486a.m6886i();
            SharedPreferencesUtil.m7055a(this.f4486a.getString(2131296925), this.f4486a.m6876c());
            SharedPreferencesUtil.m7055a(this.f4486a.getString(2131296935), this.f4486a.m6878d());
            SharedPreferencesUtil.m7055a(this.f4486a.getString(2131296916), EntryPoint.verification.toString());
            this.f4486a.m6875b(this.f4486a.m6876c() + this.f4486a.m6878d());
        }
    }

    /* renamed from: app.signin.SignInFragment.7 */
    class C04817 extends JsonHttpResponseHandler {
        final /* synthetic */ SignInFragment f4487a;

        C04817(SignInFragment signInFragment) {
            this.f4487a = signInFragment;
        }

        public void m6867a(int i, Header[] headerArr, JSONObject jSONObject) {
            this.f4487a.m6887j();
            this.f4487a.f4495g.m6898c();
            Main.f1926a.m5681c("requestToken Response: " + jSONObject.toString());
            if (this.f4487a.isAdded()) {
                this.f4487a.getFragmentManager().beginTransaction().replace(2131755221, new VerificationFragment()).addToBackStack(XHTMLText.f8584A).commitAllowingStateLoss();
            }
        }

        public void m6866a(int i, Header[] headerArr, String str, Throwable th) {
            this.f4487a.m6887j();
            Main.f1926a.m5682c(th);
            this.f4487a.m6888k();
        }
    }

    /* renamed from: app.signin.SignInFragment.8 */
    class C04828 extends AsyncHttpResponseHandler {
        final /* synthetic */ SignInFragment f4488a;

        C04828(SignInFragment signInFragment) {
            this.f4488a = signInFragment;
        }

        public void m6868a(int i, Header[] headerArr, byte[] bArr) {
            if (this.f4488a.isAdded()) {
                String a = CountryMapperUtil.m7008a(Main.f1927b, new String(bArr));
                if (a == null) {
                    this.f4488a.m6872a(this.f4488a.getString(2131296940));
                } else {
                    this.f4488a.m6872a(a);
                }
            }
        }

        public void m6869a(int i, Header[] headerArr, byte[] bArr, Throwable th) {
            Main.f1926a.m5682c(th);
            if (this.f4488a.isAdded()) {
                this.f4488a.m6872a(this.f4488a.getString(2131296940));
            }
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2130903149, viewGroup, false);
        ButterKnife.m7744a(this, inflate);
        this.f4489a.setClickable(false);
        this.f4491c.setOnEditorActionListener(new C04751(this));
        this.f4491c.setOnClickListener(new C04762(this));
        this.f4491c.setOnFocusChangeListener(new C04773(this));
        this.f4490b.setOnFocusChangeListener(new C04784(this));
        this.f4495g = new TokenRequestLimitChecker(getActivity());
        m6885h();
        this.f4491c.addTextChangedListener(new C04795(this));
        return inflate;
    }

    public void onResume() {
        super.onResume();
        if (!this.f4495g.m6897b()) {
            Main.m3905a(this.f4495g.m6899d());
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        getActivity();
        if (i2 == -1 && i == 9562 && intent.hasExtra(Message.ELEMENT)) {
            m6872a(intent.getStringExtra(Message.ELEMENT));
        }
    }

    void m6889a() {
        Intent intent = new Intent(getActivity(), CountrySelectActivity.class);
        intent.putExtra("country", this.f4490b.getText().toString());
        startActivityForResult(intent, 9562);
    }

    void m6890b() {
        KeyboardUtil.m7031a(getActivity(), this.f4491c.getWindowToken());
        if (this.f4490b.getText().toString().equals("")) {
            m6882f();
        } else if (!PhoneNumberUtil.m7045a(m6876c(), m6878d())) {
            m6881e();
        } else if (this.f4495g.m6897b()) {
            m6884g();
        } else {
            Main.m3905a(this.f4495g.m6899d());
        }
    }

    private String m6876c() {
        return CountryMapperUtil.m7009b(getActivity(), this.f4490b.getText().toString());
    }

    private String m6878d() {
        String obj = this.f4491c.getText().toString();
        if (obj.startsWith("0")) {
            return obj.substring(1);
        }
        return obj;
    }

    private void m6872a(String str) {
        String b = CountryMapperUtil.m7009b(Main.f1927b, str);
        this.f4490b.setText(str);
        this.f4492d.setText("+" + b);
    }

    private void m6881e() {
        CharSequence string = getString(2131296757);
        new Builder(getActivity(), 2131558536).m1980a(string).m1986b(getString(2131296756)).m1987b(getString(2131296748), null).m1989c(2130837729).m1988b().show();
    }

    private void m6882f() {
        CharSequence string = getString(2131296751);
        new Builder(getActivity(), 2131558536).m1980a(string).m1986b(getString(2131296750)).m1987b(getString(2131296748), null).m1989c(2130837729).m1988b().show();
    }

    private void m6884g() {
        CharSequence string = getString(2131296744);
        AlertDialog b = new Builder(getActivity(), 2131558537).m1980a(string).m1986b(getString(2131296743) + "\n" + PhoneNumberUtil.m7046b(m6876c(), m6878d())).m1989c(2130837730).m1987b(getString(2131296746), null).m1981a(getString(2131296747), new C04806(this)).m1988b();
        b.setCanceledOnTouchOutside(false);
        b.show();
    }

    private void m6875b(String str) {
        HttpEntity stringEntity;
        String a = DeviceUtils.m7012a(Main.f1927b);
        Map hashMap = new HashMap();
        hashMap.put(getString(2131296540), a);
        hashMap.put(getString(2131296539), str);
        hashMap.put(getString(2131296535), "bisphone.com");
        hashMap.put(getString(2131296538), "sms");
        JSONObject jSONObject = new JSONObject(hashMap);
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.m10580a(20000);
        try {
            stringEntity = new StringEntity(jSONObject.toString());
        } catch (Throwable e) {
            Main.f1926a.m5682c(e);
            stringEntity = null;
        }
        asyncHttpClient.m10572a(getActivity(), Constants.m3957b(), stringEntity, "application/json", new C04817(this));
    }

    private void m6885h() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.m10580a(5000);
        asyncHttpClient.m10576a(Constants.m3956a(), new C04828(this));
    }

    private void m6886i() {
        if (isAdded()) {
            this.f4494f = new ProgressDialog(getActivity());
            this.f4494f.setMessage(getString(2131296414));
            this.f4494f.setCanceledOnTouchOutside(false);
            this.f4494f.setIndeterminate(true);
            this.f4494f.show();
        }
    }

    private void m6887j() {
        if (isAdded()) {
            this.f4494f.dismiss();
        }
    }

    private void m6888k() {
        if (isAdded()) {
            DialogsUtil.m7014a(getActivity());
        }
    }
}
