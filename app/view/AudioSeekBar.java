package app.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.SeekBar;

public class AudioSeekBar extends SeekBar {
    private boolean f4626a;

    public AudioSeekBar(Context context) {
        super(context);
        this.f4626a = true;
    }

    public AudioSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4626a = true;
    }

    public AudioSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f4626a = true;
    }

    public synchronized void setProgress(int i) {
        if (this.f4626a) {
            super.setProgress(i);
        }
    }
}
