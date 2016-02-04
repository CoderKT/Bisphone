package com.edmodo.cropper.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.TypedValue;

public class PaintUtil {
    public static Paint m8283a(Context context) {
        float applyDimension = TypedValue.applyDimension(1, 3.0f, context.getResources().getDisplayMetrics());
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#AAFFFFFF"));
        paint.setStrokeWidth(applyDimension);
        paint.setStyle(Style.STROKE);
        return paint;
    }

    public static Paint m8282a() {
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#AAFFFFFF"));
        paint.setStrokeWidth(1.0f);
        return paint;
    }

    public static Paint m8285b(Context context) {
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#B0000000"));
        return paint;
    }

    public static Paint m8287c(Context context) {
        float applyDimension = TypedValue.applyDimension(1, 5.0f, context.getResources().getDisplayMetrics());
        Paint paint = new Paint();
        paint.setColor(-1);
        paint.setStrokeWidth(applyDimension);
        paint.setStyle(Style.STROKE);
        return paint;
    }

    public static float m8284b() {
        return 5.0f;
    }

    public static float m8286c() {
        return 3.0f;
    }
}
