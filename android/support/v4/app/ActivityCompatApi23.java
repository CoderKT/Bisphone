package android.support.v4.app;

import android.app.Activity;

/* compiled from: ActivityCompat23 */
class ActivityCompatApi23 {

    /* compiled from: ActivityCompat23 */
    public interface RequestPermissionsRequestCodeValidator {
        void b_(int i);
    }

    public static void m105a(Activity activity, String[] strArr, int i) {
        if (activity instanceof RequestPermissionsRequestCodeValidator) {
            ((RequestPermissionsRequestCodeValidator) activity).b_(i);
        }
        activity.requestPermissions(strArr, i);
    }
}
