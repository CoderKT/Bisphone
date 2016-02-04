package me.grantland.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import me.grantland.widget.AutofitHelper.OnTextSizeChangeListener;

public class AutofitTextView extends TextView implements OnTextSizeChangeListener {
    private AutofitHelper f8470a;

    public AutofitTextView(Context context) {
        super(context);
        m13325a(context, null, 0);
    }

    public AutofitTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m13325a(context, attributeSet, 0);
    }

    public AutofitTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m13325a(context, attributeSet, i);
    }

    private void m13325a(Context context, AttributeSet attributeSet, int i) {
        this.f8470a = AutofitHelper.m13306a(this, attributeSet, i).m13318a((OnTextSizeChangeListener) this);
    }

    public void setTextSize(int i, float f) {
        super.setTextSize(i, f);
        if (this.f8470a != null) {
            this.f8470a.m13324c(i, f);
        }
    }

    public void setLines(int i) {
        super.setLines(i);
        if (this.f8470a != null) {
            this.f8470a.m13316a(i);
        }
    }

    public void setMaxLines(int i) {
        super.setMaxLines(i);
        if (this.f8470a != null) {
            this.f8470a.m13316a(i);
        }
    }

    public AutofitHelper getAutofitHelper() {
        return this.f8470a;
    }

    public void setSizeToFit(boolean z) {
        this.f8470a.m13319a(z);
    }

    public float getMaxTextSize() {
        return this.f8470a.m13323c();
    }

    public void setMaxTextSize(float f) {
        this.f8470a.m13321b(f);
    }

    public float getMinTextSize() {
        return this.f8470a.m13320b();
    }

    public void setMinTextSize(int i) {
        this.f8470a.m13317a(2, (float) i);
    }

    public float getPrecision() {
        return this.f8470a.m13314a();
    }

    public void setPrecision(float f) {
        this.f8470a.m13315a(f);
    }

    public void m13326a(float f, float f2) {
    }
}
