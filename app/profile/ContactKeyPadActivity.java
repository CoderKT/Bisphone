package app.profile;

import android.app.FragmentTransaction;
import android.os.Bundle;
import app.common.LocalizeActivity;
import app.voip.ContactKeyPadFragment;

public class ContactKeyPadActivity extends LocalizeActivity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(2130903157);
        setRequestedOrientation(1);
        setTitle(getString(2131296370));
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.add(2131755193, new ContactKeyPadFragment());
        beginTransaction.commit();
    }
}
