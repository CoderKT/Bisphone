package app.util;

import android.content.Context;

public class PixelConverter {
    public static float m7048a(float f, Context context) {
        return (((float) context.getResources().getDisplayMetrics().densityDpi) / 160.0f) * f;
    }
}
