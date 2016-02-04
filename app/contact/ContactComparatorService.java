package app.contact;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import app.Main;
import app.common.AddressBookHelper;
import app.database.datasource.ContactDataSource;
import app.events.ContactLocalSyncFinishedEvent;
import de.greenrobot.event.EventBus;

public class ContactComparatorService extends IntentService {
    public ContactComparatorService() {
        super("ContactComparatorService");
    }

    protected void onHandleIntent(Intent intent) {
        ContactSyncManager.m4495a().m4496a(false);
        if (m4494a()) {
            ContactDataSource.m4553a().m4567a(AddressBookHelper.m3943a((Context) this), intent.getBooleanExtra("remove_contact", false));
        }
        ContactSyncManager.m4495a().m4496a(true);
        EventBus.m12779a().m12796e(new ContactLocalSyncFinishedEvent());
    }

    private boolean m4494a() {
        try {
            long b = ContactDataSource.m4553a().m4568b();
            long b2 = AddressBookHelper.m3944b(this);
            Main.f1926a.m5681c("local contacts version sum: " + b);
            Main.f1926a.m5681c("device contacts version sum: " + b2);
            if (b != b2) {
                return true;
            }
            return false;
        } catch (SecurityException e) {
            return false;
        }
    }
}
