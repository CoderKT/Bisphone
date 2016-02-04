package android.support.v7.widget;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.support.v4.graphics.drawable.DrawableWrapper;
import android.util.AttributeSet;
import android.widget.ProgressBar;

class AppCompatProgressBarHelper {
    private static final int[] f1365b;
    final TintManager f1366a;
    private final ProgressBar f1367c;
    private Bitmap f1368d;

    static {
        f1365b = new int[]{16843067, 16843068};
    }

    AppCompatProgressBarHelper(ProgressBar progressBar, TintManager tintManager) {
        this.f1367c = progressBar;
        this.f1366a = tintManager;
    }

    void m2794a(AttributeSet attributeSet, int i) {
        TintTypedArray a = TintTypedArray.m3759a(this.f1367c.getContext(), attributeSet, f1365b, i, 0);
        Drawable b = a.m3766b(0);
        if (b != null) {
            this.f1367c.setIndeterminateDrawable(m2790a(b));
        }
        b = a.m3766b(1);
        if (b != null) {
            this.f1367c.setProgressDrawable(m2791a(b, false));
        }
        a.m3763a();
    }

    private Drawable m2791a(Drawable drawable, boolean z) {
        int i = 0;
        Drawable a;
        if (drawable instanceof DrawableWrapper) {
            a = ((DrawableWrapper) drawable).m681a();
            if (a != null) {
                ((DrawableWrapper) drawable).m682a(m2791a(a, z));
            }
        } else if (drawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            int numberOfLayers = layerDrawable.getNumberOfLayers();
            Drawable[] drawableArr = new Drawable[numberOfLayers];
            for (int i2 = 0; i2 < numberOfLayers; i2++) {
                boolean z2;
                int id = layerDrawable.getId(i2);
                Drawable drawable2 = layerDrawable.getDrawable(i2);
                if (id == 16908301 || id == 16908303) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                drawableArr[i2] = m2791a(drawable2, z2);
            }
            a = new LayerDrawable(drawableArr);
            while (i < numberOfLayers) {
                a.setId(i, layerDrawable.getId(i));
                i++;
            }
            return a;
        } else if (drawable instanceof BitmapDrawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            if (this.f1368d == null) {
                this.f1368d = bitmap;
            }
            Drawable shapeDrawable = new ShapeDrawable(m2792b());
            shapeDrawable.getPaint().setShader(new BitmapShader(bitmap, TileMode.REPEAT, TileMode.CLAMP));
            return z ? new ClipDrawable(shapeDrawable, 3, 1) : shapeDrawable;
        }
        return drawable;
    }

    private Drawable m2790a(Drawable drawable) {
        if (!(drawable instanceof AnimationDrawable)) {
            return drawable;
        }
        AnimationDrawable animationDrawable = (AnimationDrawable) drawable;
        int numberOfFrames = animationDrawable.getNumberOfFrames();
        Drawable animationDrawable2 = new AnimationDrawable();
        animationDrawable2.setOneShot(animationDrawable.isOneShot());
        for (int i = 0; i < numberOfFrames; i++) {
            Drawable a = m2791a(animationDrawable.getFrame(i), true);
            a.setLevel(10000);
            animationDrawable2.addFrame(a, animationDrawable.getDuration(i));
        }
        animationDrawable2.setLevel(10000);
        return animationDrawable2;
    }

    private Shape m2792b() {
        return new RoundRectShape(new float[]{5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f}, null, null);
    }

    Bitmap m2793a() {
        return this.f1368d;
    }
}
