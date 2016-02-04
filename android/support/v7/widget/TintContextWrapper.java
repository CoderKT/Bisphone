package android.support.v7.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

class TintContextWrapper extends ContextWrapper {
    private Resources f1815a;

    class TintResources extends ResourcesWrapper {
        private final TintManager f1814a;

        public TintResources(Resources resources, TintManager tintManager) {
            super(resources);
            this.f1814a = tintManager;
        }

        public Drawable getDrawable(int i) {
            Drawable drawable = super.getDrawable(i);
            if (drawable != null) {
                this.f1814a.m3755a(i, drawable);
            }
            return drawable;
        }
    }

    public static Context m3730a(Context context) {
        if (context instanceof TintContextWrapper) {
            return context;
        }
        return new TintContextWrapper(context);
    }

    private TintContextWrapper(Context context) {
        super(context);
    }

    public Resources getResources() {
        if (this.f1815a == null) {
            this.f1815a = new TintResources(super.getResources(), TintManager.m3737a((Context) this));
        }
        return this.f1815a;
    }
}
