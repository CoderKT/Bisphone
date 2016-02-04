package android.support.v4.app;

import android.app.PendingIntent;
import android.os.Bundle;
import android.support.v4.app.RemoteInputCompatBase.RemoteInput;

public class NotificationCompatBase {

    public abstract class Action {

        public interface Factory {
        }

        public abstract int m513a();

        public abstract CharSequence m514b();

        public abstract PendingIntent m515c();

        public abstract Bundle m516d();

        public abstract RemoteInput[] m517f();
    }
}
