package android.support.v7.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.appcompat.C0057R;
import android.support.v7.view.ActionMode;
import android.support.v7.view.ActionMode.Callback;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;

public abstract class ActionBar {

    public class LayoutParams extends MarginLayoutParams {
        public int f641a;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f641a = 0;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0057R.styleable.ActionBarLayout);
            this.f641a = obtainStyledAttributes.getInt(C0057R.styleable.ActionBarLayout_android_layout_gravity, 0);
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.f641a = 0;
            this.f641a = 8388627;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super(layoutParams);
            this.f641a = 0;
            this.f641a = layoutParams.f641a;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f641a = 0;
        }
    }

    public interface OnMenuVisibilityListener {
        void m1910a(boolean z);
    }

    public abstract class Tab {
        public abstract Drawable m1911a();

        public abstract CharSequence m1912b();

        public abstract View m1913c();

        public abstract void m1914d();

        public abstract CharSequence m1915e();
    }

    public void m1920a(boolean z) {
    }

    public Context m1916a() {
        return null;
    }

    public void m1922b(boolean z) {
        if (z) {
            throw new UnsupportedOperationException("Hide on content scroll is not supported in this action bar configuration.");
        }
    }

    public void m1918a(float f) {
        if (f != 0.0f) {
            throw new UnsupportedOperationException("Setting a non-zero elevation is not supported in this action bar configuration.");
        }
    }

    public void m1924c(boolean z) {
    }

    public void m1926d(boolean z) {
    }

    public void m1927e(boolean z) {
    }

    public ActionMode m1917a(Callback callback) {
        return null;
    }

    public boolean m1923b() {
        return false;
    }

    public boolean m1921a(int i, KeyEvent keyEvent) {
        return false;
    }

    public boolean m1925c() {
        return false;
    }

    public void m1919a(CharSequence charSequence) {
    }
}
