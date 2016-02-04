package app.util;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.view.View;

public class Background {
    @TargetApi(16)
    public static void m6968a(Drawable drawable, View view) {
        if (VERSION.SDK_INT < 16) {
            view.setBackgroundDrawable(drawable);
        } else {
            view.setBackground(drawable);
        }
    }
}
