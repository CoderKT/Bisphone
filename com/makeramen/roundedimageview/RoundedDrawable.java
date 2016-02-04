package com.makeramen.roundedimageview;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;
import android.widget.ImageView.ScaleType;
import java.util.HashSet;
import java.util.Set;
import se.emilsjolander.stickylistheaders.C1128R;

public class RoundedDrawable extends Drawable {
    private final RectF f6655a;
    private final RectF f6656b;
    private final RectF f6657c;
    private final Bitmap f6658d;
    private final Paint f6659e;
    private final int f6660f;
    private final int f6661g;
    private final RectF f6662h;
    private final Paint f6663i;
    private final Matrix f6664j;
    private final RectF f6665k;
    private TileMode f6666l;
    private TileMode f6667m;
    private boolean f6668n;
    private float f6669o;
    private final boolean[] f6670p;
    private boolean f6671q;
    private float f6672r;
    private ColorStateList f6673s;
    private ScaleType f6674t;

    /* renamed from: com.makeramen.roundedimageview.RoundedDrawable.1 */
    /* synthetic */ class C08981 {
        static final /* synthetic */ int[] f6654a;

        static {
            f6654a = new int[ScaleType.values().length];
            try {
                f6654a[ScaleType.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f6654a[ScaleType.CENTER_CROP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f6654a[ScaleType.CENTER_INSIDE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f6654a[ScaleType.FIT_CENTER.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f6654a[ScaleType.FIT_END.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f6654a[ScaleType.FIT_START.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f6654a[ScaleType.FIT_XY.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    public RoundedDrawable(Bitmap bitmap) {
        this.f6655a = new RectF();
        this.f6656b = new RectF();
        this.f6657c = new RectF();
        this.f6662h = new RectF();
        this.f6664j = new Matrix();
        this.f6665k = new RectF();
        this.f6666l = TileMode.CLAMP;
        this.f6667m = TileMode.CLAMP;
        this.f6668n = true;
        this.f6669o = 0.0f;
        this.f6670p = new boolean[]{true, true, true, true};
        this.f6671q = false;
        this.f6672r = 0.0f;
        this.f6673s = ColorStateList.valueOf(-16777216);
        this.f6674t = ScaleType.FIT_CENTER;
        this.f6658d = bitmap;
        this.f6660f = bitmap.getWidth();
        this.f6661g = bitmap.getHeight();
        this.f6657c.set(0.0f, 0.0f, (float) this.f6660f, (float) this.f6661g);
        this.f6659e = new Paint();
        this.f6659e.setStyle(Style.FILL);
        this.f6659e.setAntiAlias(true);
        this.f6663i = new Paint();
        this.f6663i.setStyle(Style.STROKE);
        this.f6663i.setAntiAlias(true);
        this.f6663i.setColor(this.f6673s.getColorForState(getState(), -16777216));
        this.f6663i.setStrokeWidth(this.f6672r);
    }

    public static RoundedDrawable m10780a(Bitmap bitmap) {
        if (bitmap != null) {
            return new RoundedDrawable(bitmap);
        }
        return null;
    }

    public static Drawable m10779a(Drawable drawable) {
        if (drawable == null || (drawable instanceof RoundedDrawable)) {
            return drawable;
        }
        if (drawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            int numberOfLayers = layerDrawable.getNumberOfLayers();
            for (int i = 0; i < numberOfLayers; i++) {
                layerDrawable.setDrawableByLayerId(layerDrawable.getId(i), m10779a(layerDrawable.getDrawable(i)));
            }
            return layerDrawable;
        }
        Bitmap b = m10784b(drawable);
        if (b != null) {
            return new RoundedDrawable(b);
        }
        return drawable;
    }

    public static Bitmap m10784b(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        try {
            Bitmap createBitmap = Bitmap.createBitmap(Math.max(drawable.getIntrinsicWidth(), 2), Math.max(drawable.getIntrinsicHeight(), 2), Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return createBitmap;
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("RoundedDrawable", "Failed to create bitmap from drawable!");
            return null;
        }
    }

    public boolean isStateful() {
        return this.f6673s.isStateful();
    }

    protected boolean onStateChange(int[] iArr) {
        int colorForState = this.f6673s.getColorForState(iArr, 0);
        if (this.f6663i.getColor() == colorForState) {
            return super.onStateChange(iArr);
        }
        this.f6663i.setColor(colorForState);
        return true;
    }

    private void m10781a() {
        float f = 0.0f;
        float height;
        float width;
        switch (C08981.f6654a[this.f6674t.ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                this.f6662h.set(this.f6655a);
                this.f6662h.inset(this.f6672r / 2.0f, this.f6672r / 2.0f);
                this.f6664j.reset();
                this.f6664j.setTranslate((float) ((int) (((this.f6662h.width() - ((float) this.f6660f)) * 0.5f) + 0.5f)), (float) ((int) (((this.f6662h.height() - ((float) this.f6661g)) * 0.5f) + 0.5f)));
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                this.f6662h.set(this.f6655a);
                this.f6662h.inset(this.f6672r / 2.0f, this.f6672r / 2.0f);
                this.f6664j.reset();
                if (((float) this.f6660f) * this.f6662h.height() > this.f6662h.width() * ((float) this.f6661g)) {
                    height = this.f6662h.height() / ((float) this.f6661g);
                    width = (this.f6662h.width() - (((float) this.f6660f) * height)) * 0.5f;
                } else {
                    height = this.f6662h.width() / ((float) this.f6660f);
                    width = 0.0f;
                    f = (this.f6662h.height() - (((float) this.f6661g) * height)) * 0.5f;
                }
                this.f6664j.setScale(height, height);
                this.f6664j.postTranslate((float) ((int) (width + 0.5f)), (float) ((int) (f + 0.5f)));
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                this.f6664j.reset();
                if (((float) this.f6660f) > this.f6655a.width() || ((float) this.f6661g) > this.f6655a.height()) {
                    f = Math.min(this.f6655a.width() / ((float) this.f6660f), this.f6655a.height() / ((float) this.f6661g));
                } else {
                    f = 1.0f;
                }
                width = (float) ((int) (((this.f6655a.width() - (((float) this.f6660f) * f)) * 0.5f) + 0.5f));
                height = (float) ((int) (((this.f6655a.height() - (((float) this.f6661g) * f)) * 0.5f) + 0.5f));
                this.f6664j.setScale(f, f);
                this.f6664j.postTranslate(width, height);
                this.f6662h.set(this.f6657c);
                this.f6664j.mapRect(this.f6662h);
                this.f6662h.inset(this.f6672r / 2.0f, this.f6672r / 2.0f);
                this.f6664j.setRectToRect(this.f6657c, this.f6662h, ScaleToFit.FILL);
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                this.f6662h.set(this.f6657c);
                this.f6664j.setRectToRect(this.f6657c, this.f6655a, ScaleToFit.END);
                this.f6664j.mapRect(this.f6662h);
                this.f6662h.inset(this.f6672r / 2.0f, this.f6672r / 2.0f);
                this.f6664j.setRectToRect(this.f6657c, this.f6662h, ScaleToFit.FILL);
                break;
            case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                this.f6662h.set(this.f6657c);
                this.f6664j.setRectToRect(this.f6657c, this.f6655a, ScaleToFit.START);
                this.f6664j.mapRect(this.f6662h);
                this.f6662h.inset(this.f6672r / 2.0f, this.f6672r / 2.0f);
                this.f6664j.setRectToRect(this.f6657c, this.f6662h, ScaleToFit.FILL);
                break;
            case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                this.f6662h.set(this.f6655a);
                this.f6662h.inset(this.f6672r / 2.0f, this.f6672r / 2.0f);
                this.f6664j.reset();
                this.f6664j.setRectToRect(this.f6657c, this.f6662h, ScaleToFit.FILL);
                break;
            default:
                this.f6662h.set(this.f6657c);
                this.f6664j.setRectToRect(this.f6657c, this.f6655a, ScaleToFit.CENTER);
                this.f6664j.mapRect(this.f6662h);
                this.f6662h.inset(this.f6672r / 2.0f, this.f6672r / 2.0f);
                this.f6664j.setRectToRect(this.f6657c, this.f6662h, ScaleToFit.FILL);
                break;
        }
        this.f6656b.set(this.f6662h);
    }

    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.f6655a.set(rect);
        m10781a();
    }

    public void draw(Canvas canvas) {
        if (this.f6668n) {
            Shader bitmapShader = new BitmapShader(this.f6658d, this.f6666l, this.f6667m);
            if (this.f6666l == TileMode.CLAMP && this.f6667m == TileMode.CLAMP) {
                bitmapShader.setLocalMatrix(this.f6664j);
            }
            this.f6659e.setShader(bitmapShader);
            this.f6668n = false;
        }
        if (this.f6671q) {
            if (this.f6672r > 0.0f) {
                canvas.drawOval(this.f6656b, this.f6659e);
                canvas.drawOval(this.f6662h, this.f6663i);
                return;
            }
            canvas.drawOval(this.f6656b, this.f6659e);
        } else if (m10783a(this.f6670p)) {
            float f = this.f6669o;
            if (this.f6672r > 0.0f) {
                canvas.drawRoundRect(this.f6656b, f, f, this.f6659e);
                canvas.drawRoundRect(this.f6662h, f, f, this.f6663i);
                m10782a(canvas);
                m10785b(canvas);
                return;
            }
            canvas.drawRoundRect(this.f6656b, f, f, this.f6659e);
            m10782a(canvas);
        } else {
            canvas.drawRect(this.f6656b, this.f6659e);
            if (this.f6672r > 0.0f) {
                canvas.drawRect(this.f6662h, this.f6663i);
            }
        }
    }

    private void m10782a(Canvas canvas) {
        if (!m10786b(this.f6670p) && this.f6669o != 0.0f) {
            float f = this.f6656b.left;
            float f2 = this.f6656b.top;
            float width = this.f6656b.width() + f;
            float height = this.f6656b.height() + f2;
            float f3 = this.f6669o;
            if (!this.f6670p[0]) {
                this.f6665k.set(f, f2, f + f3, f2 + f3);
                canvas.drawRect(this.f6665k, this.f6659e);
            }
            if (!this.f6670p[1]) {
                this.f6665k.set(width - f3, f2, width, f3);
                canvas.drawRect(this.f6665k, this.f6659e);
            }
            if (!this.f6670p[2]) {
                this.f6665k.set(width - f3, height - f3, width, height);
                canvas.drawRect(this.f6665k, this.f6659e);
            }
            if (!this.f6670p[3]) {
                this.f6665k.set(f, height - f3, f3 + f, height);
                canvas.drawRect(this.f6665k, this.f6659e);
            }
        }
    }

    private void m10785b(Canvas canvas) {
        if (!m10786b(this.f6670p) && this.f6669o != 0.0f) {
            float f = this.f6656b.left;
            float f2 = this.f6656b.top;
            float width = f + this.f6656b.width();
            float height = f2 + this.f6656b.height();
            float f3 = this.f6669o;
            float f4 = this.f6672r / 2.0f;
            if (!this.f6670p[0]) {
                canvas.drawLine(f - f4, f2, f + f3, f2, this.f6663i);
                canvas.drawLine(f, f2 - f4, f, f2 + f3, this.f6663i);
            }
            if (!this.f6670p[1]) {
                canvas.drawLine((width - f3) - f4, f2, width, f2, this.f6663i);
                f2 -= f4;
                canvas.drawLine(width, r3, width, f2 + f3, this.f6663i);
            }
            if (!this.f6670p[2]) {
                canvas.drawLine((width - f3) - f4, height, width + f4, height, this.f6663i);
                canvas.drawLine(width, height - f3, width, height, this.f6663i);
            }
            if (!this.f6670p[3]) {
                canvas.drawLine(f - f4, height, f + f3, height, this.f6663i);
                canvas.drawLine(f, height - f3, f, height, this.f6663i);
            }
        }
    }

    public int getOpacity() {
        return -3;
    }

    public int getAlpha() {
        return this.f6659e.getAlpha();
    }

    public void setAlpha(int i) {
        this.f6659e.setAlpha(i);
        invalidateSelf();
    }

    public ColorFilter getColorFilter() {
        return this.f6659e.getColorFilter();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.f6659e.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public void setDither(boolean z) {
        this.f6659e.setDither(z);
        invalidateSelf();
    }

    public void setFilterBitmap(boolean z) {
        this.f6659e.setFilterBitmap(z);
        invalidateSelf();
    }

    public int getIntrinsicWidth() {
        return this.f6660f;
    }

    public int getIntrinsicHeight() {
        return this.f6661g;
    }

    public RoundedDrawable m10788a(float f, float f2, float f3, float f4) {
        boolean z = true;
        Set hashSet = new HashSet(4);
        hashSet.add(Float.valueOf(f));
        hashSet.add(Float.valueOf(f2));
        hashSet.add(Float.valueOf(f3));
        hashSet.add(Float.valueOf(f4));
        hashSet.remove(Float.valueOf(0.0f));
        if (hashSet.size() > 1) {
            throw new IllegalArgumentException("Multiple nonzero corner radii not yet supported.");
        }
        boolean z2;
        if (hashSet.isEmpty()) {
            this.f6669o = 0.0f;
        } else {
            float floatValue = ((Float) hashSet.iterator().next()).floatValue();
            if (Float.isInfinite(floatValue) || Float.isNaN(floatValue) || floatValue < 0.0f) {
                throw new IllegalArgumentException("Invalid radius value: " + floatValue);
            }
            this.f6669o = floatValue;
        }
        boolean[] zArr = this.f6670p;
        if (f > 0.0f) {
            z2 = true;
        } else {
            z2 = false;
        }
        zArr[0] = z2;
        zArr = this.f6670p;
        if (f2 > 0.0f) {
            z2 = true;
        } else {
            z2 = false;
        }
        zArr[1] = z2;
        zArr = this.f6670p;
        if (f3 > 0.0f) {
            z2 = true;
        } else {
            z2 = false;
        }
        zArr[2] = z2;
        boolean[] zArr2 = this.f6670p;
        if (f4 <= 0.0f) {
            z = false;
        }
        zArr2[3] = z;
        return this;
    }

    public RoundedDrawable m10787a(float f) {
        this.f6672r = f;
        this.f6663i.setStrokeWidth(this.f6672r);
        return this;
    }

    public RoundedDrawable m10789a(ColorStateList colorStateList) {
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(0);
        }
        this.f6673s = colorStateList;
        this.f6663i.setColor(this.f6673s.getColorForState(getState(), -16777216));
        return this;
    }

    public RoundedDrawable m10792a(boolean z) {
        this.f6671q = z;
        return this;
    }

    public RoundedDrawable m10791a(ScaleType scaleType) {
        if (scaleType == null) {
            scaleType = ScaleType.FIT_CENTER;
        }
        if (this.f6674t != scaleType) {
            this.f6674t = scaleType;
            m10781a();
        }
        return this;
    }

    public RoundedDrawable m10790a(TileMode tileMode) {
        if (this.f6666l != tileMode) {
            this.f6666l = tileMode;
            this.f6668n = true;
            invalidateSelf();
        }
        return this;
    }

    public RoundedDrawable m10793b(TileMode tileMode) {
        if (this.f6667m != tileMode) {
            this.f6667m = tileMode;
            this.f6668n = true;
            invalidateSelf();
        }
        return this;
    }

    private static boolean m10783a(boolean[] zArr) {
        for (boolean z : zArr) {
            if (z) {
                return true;
            }
        }
        return false;
    }

    private static boolean m10786b(boolean[] zArr) {
        for (boolean z : zArr) {
            if (z) {
                return false;
            }
        }
        return true;
    }
}
