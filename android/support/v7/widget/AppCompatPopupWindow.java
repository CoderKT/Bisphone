package android.support.v7.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.appcompat.C0057R;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.PopupWindow;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

public class AppCompatPopupWindow extends PopupWindow {
    private static final boolean f1363a;
    private boolean f1364b;

    /* renamed from: android.support.v7.widget.AppCompatPopupWindow.1 */
    final class C00691 implements OnScrollChangedListener {
        final /* synthetic */ Field f1360a;
        final /* synthetic */ PopupWindow f1361b;
        final /* synthetic */ OnScrollChangedListener f1362c;

        C00691(Field field, PopupWindow popupWindow, OnScrollChangedListener onScrollChangedListener) {
            this.f1360a = field;
            this.f1361b = popupWindow;
            this.f1362c = onScrollChangedListener;
        }

        public void onScrollChanged() {
            try {
                WeakReference weakReference = (WeakReference) this.f1360a.get(this.f1361b);
                if (weakReference != null && weakReference.get() != null) {
                    this.f1362c.onScrollChanged();
                }
            } catch (IllegalAccessException e) {
            }
        }
    }

    static {
        f1363a = VERSION.SDK_INT < 21;
    }

    public AppCompatPopupWindow(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TintTypedArray a = TintTypedArray.m3759a(context, attributeSet, C0057R.styleable.PopupWindow, i, 0);
        if (a.m3773e(C0057R.styleable.PopupWindow_overlapAnchor)) {
            m2789a(a.m3764a(C0057R.styleable.PopupWindow_overlapAnchor, false));
        }
        setBackgroundDrawable(a.m3762a(C0057R.styleable.PopupWindow_android_popupBackground));
        a.m3763a();
        if (VERSION.SDK_INT < 14) {
            m2788a((PopupWindow) this);
        }
    }

    public void showAsDropDown(View view, int i, int i2) {
        if (f1363a && this.f1364b) {
            i2 -= view.getHeight();
        }
        super.showAsDropDown(view, i, i2);
    }

    @TargetApi(19)
    public void showAsDropDown(View view, int i, int i2, int i3) {
        if (f1363a && this.f1364b) {
            i2 -= view.getHeight();
        }
        super.showAsDropDown(view, i, i2, i3);
    }

    public void update(View view, int i, int i2, int i3, int i4) {
        int height;
        if (f1363a && this.f1364b) {
            height = i2 - view.getHeight();
        } else {
            height = i2;
        }
        super.update(view, i, height, i3, i4);
    }

    private static void m2788a(PopupWindow popupWindow) {
        try {
            Field declaredField = PopupWindow.class.getDeclaredField("mAnchor");
            declaredField.setAccessible(true);
            Field declaredField2 = PopupWindow.class.getDeclaredField("mOnScrollChangedListener");
            declaredField2.setAccessible(true);
            declaredField2.set(popupWindow, new C00691(declaredField, popupWindow, (OnScrollChangedListener) declaredField2.get(popupWindow)));
        } catch (Throwable e) {
            Log.d("AppCompatPopupWindow", "Exception while installing workaround OnScrollChangedListener", e);
        }
    }

    public void m2789a(boolean z) {
        if (f1363a) {
            this.f1364b = z;
        } else {
            PopupWindowCompat.m1829a((PopupWindow) this, z);
        }
    }
}
