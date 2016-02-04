package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.support.v4.view.TintableBackgroundView;
import android.support.v7.appcompat.C0057R;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.TextView;

public class AppCompatButton extends Button implements TintableBackgroundView {
    private final TintManager f1333a;
    private final AppCompatBackgroundHelper f1334b;
    private final AppCompatTextHelper f1335c;

    public AppCompatButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0057R.attr.buttonStyle);
    }

    public AppCompatButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1333a = TintManager.m3737a(getContext());
        this.f1334b = new AppCompatBackgroundHelper(this, this.f1333a);
        this.f1334b.m2774a(attributeSet, i);
        this.f1335c = AppCompatTextHelper.m2846a((TextView) this);
        this.f1335c.m2851a(attributeSet, i);
        this.f1335c.m2848a();
    }

    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        if (this.f1334b != null) {
            this.f1334b.m2770a(i);
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        if (this.f1334b != null) {
            this.f1334b.m2773a(drawable);
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        if (this.f1334b != null) {
            this.f1334b.m2771a(colorStateList);
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        return this.f1334b != null ? this.f1334b.m2769a() : null;
    }

    public void setSupportBackgroundTintMode(Mode mode) {
        if (this.f1334b != null) {
            this.f1334b.m2772a(mode);
        }
    }

    public Mode getSupportBackgroundTintMode() {
        return this.f1334b != null ? this.f1334b.m2775b() : null;
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.f1334b != null) {
            this.f1334b.m2777c();
        }
        if (this.f1335c != null) {
            this.f1335c.m2848a();
        }
    }

    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        if (this.f1335c != null) {
            this.f1335c.m2849a(context, i);
        }
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(Button.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(Button.class.getName());
    }

    public void setSupportAllCaps(boolean z) {
        if (this.f1335c != null) {
            this.f1335c.m2852a(z);
        }
    }
}
