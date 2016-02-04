package app.account;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AuthenticationService extends Service {
    private static final Object f1959a;
    private Authenticator f1960b;

    static {
        f1959a = new Object();
    }

    public void onCreate() {
        synchronized (f1959a) {
            if (this.f1960b == null) {
                this.f1960b = new Authenticator(this);
            }
        }
    }

    public IBinder onBind(Intent intent) {
        return this.f1960b.getIBinder();
    }
}
