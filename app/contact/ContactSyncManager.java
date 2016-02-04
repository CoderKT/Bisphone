package app.contact;

import app.Main;
import app.events.xmpp.SendSublistEvent;
import de.greenrobot.event.EventBus;

public class ContactSyncManager {
    private static ContactSyncManager f2291a;
    private volatile boolean f2292b;
    private volatile boolean f2293c;

    static {
        f2291a = null;
    }

    private ContactSyncManager() {
        this.f2292b = false;
        this.f2293c = false;
    }

    public static synchronized ContactSyncManager m4495a() {
        ContactSyncManager contactSyncManager;
        synchronized (ContactSyncManager.class) {
            if (f2291a == null) {
                f2291a = new ContactSyncManager();
            }
            contactSyncManager = f2291a;
        }
        return contactSyncManager;
    }

    public synchronized void m4496a(boolean z) {
        Main.f1926a.m5683d("ContactSyncManager, contactsReady: " + z + ", xmppReady: " + this.f2293c);
        this.f2292b = z;
        if (z && this.f2293c) {
            EventBus.m12779a().m12795d(new SendSublistEvent());
        }
    }

    public synchronized void m4498b(boolean z) {
        Main.f1926a.m5683d("ContactSyncManager, contactsReady: " + this.f2292b + ", xmppReady: " + z);
        this.f2293c = z;
        if (this.f2292b && z) {
            EventBus.m12779a().m12795d(new SendSublistEvent());
        }
    }

    public synchronized void m4497b() {
        this.f2293c = false;
        this.f2292b = false;
    }
}
