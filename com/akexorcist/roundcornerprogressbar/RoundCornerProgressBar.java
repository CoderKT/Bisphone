package com.akexorcist.roundcornerprogressbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;

public class RoundCornerProgressBar extends BaseRoundCornerProgressBar {
    @SuppressLint({"NewApi"})
    public RoundCornerProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected int m7761a() {
        return C0589R.layout.round_corner_layout;
    }

    protected void m7762a(TypedArray typedArray, DisplayMetrics displayMetrics) {
    }

    public void setBackgroundLayoutSize(LinearLayout linearLayout) {
        int measuredWidth;
        int measuredHeight;
        if (VERSION.SDK_INT >= 11) {
            measuredWidth = linearLayout.getMeasuredWidth();
            measuredHeight = linearLayout.getMeasuredHeight();
        } else {
            measuredWidth = linearLayout.getWidth();
            measuredHeight = linearLayout.getHeight();
        }
        if (measuredHeight - (getPadding() * 2) == 0) {
            measuredHeight = (int) m7757c(30.0f);
        }
        setBackgroundWidth(measuredWidth);
        setBackgroundHeight(measuredHeight);
    }

    protected void setGradientRadius(GradientDrawable gradientDrawable) {
        int radius = getRadius() - (getPadding() / 2);
        gradientDrawable.setCornerRadii(new float[]{(float) radius, (float) radius, (float) radius, (float) radius, (float) radius, (float) radius, (float) radius, (float) radius});
    }

    protected void m7764c() {
    }

    protected float m7760a(float f) {
        return f > 0.0f ? (float) ((int) (((float) (getBackgroundWidth() - (getPadding() * 2))) / f)) : 0.0f;
    }

    protected float m7763b(float f) {
        return f > 0.0f ? (float) ((int) (((float) (getBackgroundWidth() - (getPadding() * 2))) / f)) : 0.0f;
    }
}
