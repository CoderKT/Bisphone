package com.nispok.snackbar.layouts;

import android.content.Context;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;

public class SnackbarLayout extends LinearLayout {
    private int f6713a;
    private int f6714b;

    public SnackbarLayout(Context context) {
        super(context);
        this.f6713a = Integer.MAX_VALUE;
        this.f6714b = Integer.MAX_VALUE;
    }

    protected void onMeasure(int i, int i2) {
        if (this.f6713a < MeasureSpec.getSize(i)) {
            i = MeasureSpec.makeMeasureSpec(this.f6713a, MeasureSpec.getMode(i));
        }
        if (this.f6714b < MeasureSpec.getSize(i2)) {
            i2 = MeasureSpec.makeMeasureSpec(this.f6714b, MeasureSpec.getMode(i2));
        }
        super.onMeasure(i, i2);
    }

    public void setMaxWidth(int i) {
        this.f6713a = i;
        requestLayout();
    }

    public void setMaxHeight(int i) {
        this.f6714b = i;
        requestLayout();
    }
}
