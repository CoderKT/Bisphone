package app.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import app.database.datasource.ContactDataSource;
import app.signin.LogoutReceiver;

public class BaseActivity extends LocalizeActivity {
    protected Activity f1977k;

    protected void onCreate(Bundle bundle) {
        if (!ContactDataSource.m4553a().m4580f()) {
            sendBroadcast(new Intent(this, LogoutReceiver.class).setAction("com.bistalk.bisphone.signin.LogoutReceiver.ACTION_LOGOUT"));
            finish();
        }
        super.onCreate(bundle);
        this.f1977k = this;
        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                finish();
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
