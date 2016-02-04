package android.support.v7.widget;

import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.SeekBar;

class AppCompatSeekBarHelper extends AppCompatProgressBarHelper {
    private static final int[] f1375b;
    private final SeekBar f1376c;

    static {
        f1375b = new int[]{16843074};
    }

    AppCompatSeekBarHelper(SeekBar seekBar, TintManager tintManager) {
        super(seekBar, tintManager);
        this.f1376c = seekBar;
    }

    void m2795a(AttributeSet attributeSet, int i) {
        super.m2794a(attributeSet, i);
        TintTypedArray a = TintTypedArray.m3759a(this.f1376c.getContext(), attributeSet, f1375b, i, 0);
        Drawable b = a.m3766b(0);
        if (b != null) {
            this.f1376c.setThumb(b);
        }
        a.m3763a();
    }
}
