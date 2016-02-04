package app.util;

import android.app.Activity;
import android.content.Context;
import android.os.IBinder;
import android.view.inputmethod.InputMethodManager;

public class KeyboardUtil {
    public static void m7031a(Context context, IBinder iBinder) {
        ((InputMethodManager) context.getSystemService("input_method")).hideSoftInputFromWindow(iBinder, 0);
    }

    public static void m7030a(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService("input_method");
        if (inputMethodManager != null && activity.getCurrentFocus() != null && activity.getCurrentFocus().getWindowToken() != null) {
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 2);
        }
    }
}
