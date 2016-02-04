package com.makeramen.roundedimageview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.ColorFilter;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import se.emilsjolander.stickylistheaders.C1128R;

public class RoundedImageView extends ImageView {
    public static final TileMode f6676a;
    static final /* synthetic */ boolean f6677b;
    private static final ScaleType[] f6678c;
    private final float[] f6679d;
    private Drawable f6680e;
    private ColorStateList f6681f;
    private float f6682g;
    private ColorFilter f6683h;
    private boolean f6684i;
    private Drawable f6685j;
    private boolean f6686k;
    private boolean f6687l;
    private boolean f6688m;
    private int f6689n;
    private ScaleType f6690o;
    private TileMode f6691p;
    private TileMode f6692q;

    /* renamed from: com.makeramen.roundedimageview.RoundedImageView.1 */
    /* synthetic */ class C08991 {
        static final /* synthetic */ int[] f6675a;

        static {
            f6675a = new int[ScaleType.values().length];
            try {
                f6675a[ScaleType.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f6675a[ScaleType.CENTER_CROP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f6675a[ScaleType.CENTER_INSIDE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f6675a[ScaleType.FIT_CENTER.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f6675a[ScaleType.FIT_START.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f6675a[ScaleType.FIT_END.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f6675a[ScaleType.FIT_XY.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    static {
        boolean z;
        if (RoundedImageView.class.desiredAssertionStatus()) {
            z = false;
        } else {
            z = true;
        }
        f6677b = z;
        f6676a = TileMode.CLAMP;
        f6678c = new ScaleType[]{ScaleType.MATRIX, ScaleType.FIT_XY, ScaleType.FIT_START, ScaleType.FIT_CENTER, ScaleType.FIT_END, ScaleType.CENTER, ScaleType.CENTER_CROP, ScaleType.CENTER_INSIDE};
    }

    public RoundedImageView(Context context) {
        super(context);
        this.f6679d = new float[]{0.0f, 0.0f, 0.0f, 0.0f};
        this.f6681f = ColorStateList.valueOf(-16777216);
        this.f6682g = 0.0f;
        this.f6683h = null;
        this.f6684i = false;
        this.f6686k = false;
        this.f6687l = false;
        this.f6688m = false;
        this.f6690o = ScaleType.FIT_CENTER;
        this.f6691p = f6676a;
        this.f6692q = f6676a;
    }

    public RoundedImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RoundedImageView(Context context, AttributeSet attributeSet, int i) {
        int i2;
        super(context, attributeSet, i);
        this.f6679d = new float[]{0.0f, 0.0f, 0.0f, 0.0f};
        this.f6681f = ColorStateList.valueOf(-16777216);
        this.f6682g = 0.0f;
        this.f6683h = null;
        this.f6684i = false;
        this.f6686k = false;
        this.f6687l = false;
        this.f6688m = false;
        this.f6690o = ScaleType.FIT_CENTER;
        this.f6691p = f6676a;
        this.f6692q = f6676a;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0897R.styleable.RoundedImageView, i, 0);
        int i3 = obtainStyledAttributes.getInt(C0897R.styleable.RoundedImageView_android_scaleType, -1);
        if (i3 >= 0) {
            setScaleType(f6678c[i3]);
        } else {
            setScaleType(ScaleType.FIT_CENTER);
        }
        float dimensionPixelSize = (float) obtainStyledAttributes.getDimensionPixelSize(C0897R.styleable.RoundedImageView_riv_corner_radius, -1);
        this.f6679d[0] = (float) obtainStyledAttributes.getDimensionPixelSize(C0897R.styleable.RoundedImageView_riv_corner_radius_top_left, -1);
        this.f6679d[1] = (float) obtainStyledAttributes.getDimensionPixelSize(C0897R.styleable.RoundedImageView_riv_corner_radius_top_right, -1);
        this.f6679d[2] = (float) obtainStyledAttributes.getDimensionPixelSize(C0897R.styleable.RoundedImageView_riv_corner_radius_bottom_right, -1);
        this.f6679d[3] = (float) obtainStyledAttributes.getDimensionPixelSize(C0897R.styleable.RoundedImageView_riv_corner_radius_bottom_left, -1);
        int length = this.f6679d.length;
        boolean z = false;
        for (i2 = 0; i2 < length; i2++) {
            if (this.f6679d[i2] < 0.0f) {
                this.f6679d[i2] = 0.0f;
            } else {
                z = true;
            }
        }
        if (!z) {
            float f;
            if (dimensionPixelSize < 0.0f) {
                f = 0.0f;
            } else {
                f = dimensionPixelSize;
            }
            i2 = this.f6679d.length;
            for (int i4 = 0; i4 < i2; i4++) {
                this.f6679d[i4] = f;
            }
        }
        this.f6682g = (float) obtainStyledAttributes.getDimensionPixelSize(C0897R.styleable.RoundedImageView_riv_border_width, -1);
        if (this.f6682g < 0.0f) {
            this.f6682g = 0.0f;
        }
        this.f6681f = obtainStyledAttributes.getColorStateList(C0897R.styleable.RoundedImageView_riv_border_color);
        if (this.f6681f == null) {
            this.f6681f = ColorStateList.valueOf(-16777216);
        }
        this.f6688m = obtainStyledAttributes.getBoolean(C0897R.styleable.RoundedImageView_riv_mutate_background, false);
        this.f6687l = obtainStyledAttributes.getBoolean(C0897R.styleable.RoundedImageView_riv_oval, false);
        i3 = obtainStyledAttributes.getInt(C0897R.styleable.RoundedImageView_riv_tile_mode, -2);
        if (i3 != -2) {
            setTileModeX(m10794a(i3));
            setTileModeY(m10794a(i3));
        }
        i3 = obtainStyledAttributes.getInt(C0897R.styleable.RoundedImageView_riv_tile_mode_x, -2);
        if (i3 != -2) {
            setTileModeX(m10794a(i3));
        }
        i3 = obtainStyledAttributes.getInt(C0897R.styleable.RoundedImageView_riv_tile_mode_y, -2);
        if (i3 != -2) {
            setTileModeY(m10794a(i3));
        }
        m10798b();
        m10797a(true);
        obtainStyledAttributes.recycle();
    }

    private static TileMode m10794a(int i) {
        switch (i) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                return TileMode.CLAMP;
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                return TileMode.REPEAT;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                return TileMode.MIRROR;
            default:
                return null;
        }
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        invalidate();
    }

    public ScaleType getScaleType() {
        return this.f6690o;
    }

    public void setScaleType(ScaleType scaleType) {
        if (!f6677b && scaleType == null) {
            throw new AssertionError();
        } else if (this.f6690o != scaleType) {
            this.f6690o = scaleType;
            switch (C08991.f6675a[scaleType.ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                    super.setScaleType(ScaleType.FIT_XY);
                    break;
                default:
                    super.setScaleType(scaleType);
                    break;
            }
            m10798b();
            m10797a(false);
            invalidate();
        }
    }

    public void setImageDrawable(Drawable drawable) {
        this.f6689n = 0;
        this.f6685j = RoundedDrawable.m10779a(drawable);
        m10798b();
        super.setImageDrawable(this.f6685j);
    }

    public void setImageBitmap(Bitmap bitmap) {
        this.f6689n = 0;
        this.f6685j = RoundedDrawable.m10780a(bitmap);
        m10798b();
        super.setImageDrawable(this.f6685j);
    }

    public void setImageResource(int i) {
        if (this.f6689n != i) {
            this.f6689n = i;
            this.f6685j = m10795a();
            m10798b();
            super.setImageDrawable(this.f6685j);
        }
    }

    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        setImageDrawable(getDrawable());
    }

    private Drawable m10795a() {
        Drawable drawable = null;
        Resources resources = getResources();
        if (resources == null) {
            return drawable;
        }
        if (this.f6689n != 0) {
            try {
                drawable = resources.getDrawable(this.f6689n);
            } catch (Throwable e) {
                Log.w("RoundedImageView", "Unable to find resource: " + this.f6689n, e);
                this.f6689n = 0;
            }
        }
        return RoundedDrawable.m10779a(drawable);
    }

    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    private void m10798b() {
        m10796a(this.f6685j);
    }

    private void m10797a(boolean z) {
        if (this.f6688m) {
            if (z) {
                this.f6680e = RoundedDrawable.m10779a(this.f6680e);
            }
            m10796a(this.f6680e);
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        if (this.f6683h != colorFilter) {
            this.f6683h = colorFilter;
            this.f6686k = true;
            this.f6684i = true;
            m10799c();
            invalidate();
        }
    }

    private void m10799c() {
        if (this.f6685j != null && this.f6684i) {
            this.f6685j = this.f6685j.mutate();
            if (this.f6686k) {
                this.f6685j.setColorFilter(this.f6683h);
            }
        }
    }

    private void m10796a(Drawable drawable) {
        if (drawable != null) {
            if (drawable instanceof RoundedDrawable) {
                ((RoundedDrawable) drawable).m10791a(this.f6690o).m10787a(this.f6682g).m10789a(this.f6681f).m10792a(this.f6687l).m10790a(this.f6691p).m10793b(this.f6692q);
                if (this.f6679d != null) {
                    ((RoundedDrawable) drawable).m10788a(this.f6679d[0], this.f6679d[1], this.f6679d[2], this.f6679d[3]);
                }
                m10799c();
            } else if (drawable instanceof LayerDrawable) {
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                int numberOfLayers = layerDrawable.getNumberOfLayers();
                for (int i = 0; i < numberOfLayers; i++) {
                    m10796a(layerDrawable.getDrawable(i));
                }
            }
        }
    }

    @Deprecated
    public void setBackgroundDrawable(Drawable drawable) {
        this.f6680e = drawable;
        m10797a(true);
        super.setBackgroundDrawable(this.f6680e);
    }

    public float getCornerRadius() {
        return getMaxCornerRadius();
    }

    public float getMaxCornerRadius() {
        float f = 0.0f;
        for (float max : this.f6679d) {
            f = Math.max(max, f);
        }
        return f;
    }

    public void setCornerRadiusDimen(int i) {
        float dimension = getResources().getDimension(i);
        m10800a(dimension, dimension, dimension, dimension);
    }

    public void setCornerRadius(float f) {
        m10800a(f, f, f, f);
    }

    public void m10800a(float f, float f2, float f3, float f4) {
        if (this.f6679d[0] != f || this.f6679d[1] != f2 || this.f6679d[2] != f4 || this.f6679d[3] != f3) {
            this.f6679d[0] = f;
            this.f6679d[1] = f2;
            this.f6679d[3] = f3;
            this.f6679d[2] = f4;
            m10798b();
            m10797a(false);
            invalidate();
        }
    }

    public float getBorderWidth() {
        return this.f6682g;
    }

    public void setBorderWidth(int i) {
        setBorderWidth(getResources().getDimension(i));
    }

    public void setBorderWidth(float f) {
        if (this.f6682g != f) {
            this.f6682g = f;
            m10798b();
            m10797a(false);
            invalidate();
        }
    }

    public int getBorderColor() {
        return this.f6681f.getDefaultColor();
    }

    public void setBorderColor(int i) {
        setBorderColor(ColorStateList.valueOf(i));
    }

    public ColorStateList getBorderColors() {
        return this.f6681f;
    }

    public void setBorderColor(ColorStateList colorStateList) {
        if (!this.f6681f.equals(colorStateList)) {
            if (colorStateList == null) {
                colorStateList = ColorStateList.valueOf(-16777216);
            }
            this.f6681f = colorStateList;
            m10798b();
            m10797a(false);
            if (this.f6682g > 0.0f) {
                invalidate();
            }
        }
    }

    public void setOval(boolean z) {
        this.f6687l = z;
        m10798b();
        m10797a(false);
        invalidate();
    }

    public TileMode getTileModeX() {
        return this.f6691p;
    }

    public void setTileModeX(TileMode tileMode) {
        if (this.f6691p != tileMode) {
            this.f6691p = tileMode;
            m10798b();
            m10797a(false);
            invalidate();
        }
    }

    public TileMode getTileModeY() {
        return this.f6692q;
    }

    public void setTileModeY(TileMode tileMode) {
        if (this.f6692q != tileMode) {
            this.f6692q = tileMode;
            m10798b();
            m10797a(false);
            invalidate();
        }
    }
}
