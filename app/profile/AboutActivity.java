package app.profile;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TextView;
import app.common.BaseActivity;

public class AboutActivity extends BaseActivity {
    TextView f4171o;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        m6666k();
        this.f4171o = (TextView) findViewById(2131755102);
        m6665j();
    }

    private void m6665j() {
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            this.f4171o.setText(String.format(getString(2131296310), new Object[]{packageInfo.versionName}));
        } catch (NameNotFoundException e) {
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        m6666k();
        m6665j();
    }

    private void m6666k() {
        if (getResources().getConfiguration().orientation == 2) {
            setContentView(2130903066);
        } else {
            setContentView(2130903065);
        }
    }

    protected void onPause() {
        super.onPause();
    }
}
