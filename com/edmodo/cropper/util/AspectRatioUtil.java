package com.edmodo.cropper.util;

import android.graphics.Rect;

public class AspectRatioUtil {
    public static float m8264a(float f, float f2, float f3, float f4) {
        return (f3 - f) / (f4 - f2);
    }

    public static float m8265a(Rect rect) {
        return ((float) rect.width()) / ((float) rect.height());
    }

    public static float m8267b(float f, float f2, float f3, float f4) {
        return f2 - ((f3 - f) * f4);
    }

    public static float m8268c(float f, float f2, float f3, float f4) {
        return f3 - ((f2 - f) / f4);
    }

    public static float m8269d(float f, float f2, float f3, float f4) {
        return ((f3 - f2) * f4) + f;
    }

    public static float m8270e(float f, float f2, float f3, float f4) {
        return ((f3 - f) / f4) + f2;
    }

    public static float m8263a(float f, float f2, float f3) {
        return (f2 - f) * f3;
    }

    public static float m8266b(float f, float f2, float f3) {
        return (f2 - f) / f3;
    }
}
