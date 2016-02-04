package app.common.runnabe;

import android.app.Activity;
import app.storage.Storage;
import java.lang.ref.WeakReference;

public class RunnableStorageAlert implements Runnable {
    private final WeakReference<Activity> f2289a;

    public RunnableStorageAlert(Activity activity) {
        this.f2289a = new WeakReference(activity);
    }

    public void run() {
        Activity activity = (Activity) this.f2289a.get();
        if (activity != null) {
            Storage.m6945c(activity);
        }
    }
}
