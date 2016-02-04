package android.support.v7.widget;

import android.content.Context;
import android.support.v7.appcompat.C0057R;
import android.util.AttributeSet;
import android.widget.SeekBar;

public class AppCompatSeekBar extends SeekBar {
    private AppCompatSeekBarHelper f1373a;
    private TintManager f1374b;

    public AppCompatSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0057R.attr.seekBarStyle);
    }

    public AppCompatSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1374b = TintManager.m3737a(context);
        this.f1373a = new AppCompatSeekBarHelper(this, this.f1374b);
        this.f1373a.m2795a(attributeSet, i);
    }
}
