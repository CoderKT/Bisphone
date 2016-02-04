package app.signin;

import android.os.Bundle;
import android.view.Menu;
import app.common.LocalizeActivity;
import app.util.SharedPreferencesUtil;
import org.jivesoftware.smackx.xhtmlim.XHTMLText;

public class SignInActivity extends LocalizeActivity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903100);
        SharedPreferencesUtil.m7054a("ggln", Boolean.valueOf(true));
        if (bundle == null) {
            getFragmentManager().beginTransaction().add(2131755221, new IntroductionFragment()).commit();
        }
        if (Boolean.valueOf(getIntent().getBooleanExtra("outside", false)).booleanValue()) {
            getFragmentManager().beginTransaction().replace(2131755221, new SignInFragment()).commit();
            getFragmentManager().beginTransaction().replace(2131755221, new VerificationFragment()).addToBackStack(XHTMLText.f8584A).commit();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(2131820568, menu);
        return true;
    }
}
