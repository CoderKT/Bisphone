package com.edmodo.cropper;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.edmodo.cropper.cropwindow.CropOverlayView;
import com.edmodo.cropper.cropwindow.edge.Edge;
import com.edmodo.cropper.util.ImageViewUtil;

public class CropImageView extends FrameLayout {
    private static final Rect f5563a;
    private ImageView f5564b;
    private CropOverlayView f5565c;
    private Bitmap f5566d;
    private int f5567e;
    private int f5568f;
    private int f5569g;
    private int f5570h;
    private boolean f5571i;
    private int f5572j;
    private int f5573k;
    private int f5574l;

    static {
        f5563a = new Rect();
    }

    public CropImageView(Context context) {
        super(context);
        this.f5567e = 0;
        this.f5570h = 1;
        this.f5571i = false;
        this.f5572j = 1;
        this.f5573k = 1;
        this.f5574l = 0;
        m8222a(context);
    }

    public CropImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f5567e = 0;
        this.f5570h = 1;
        this.f5571i = false;
        this.f5572j = 1;
        this.f5573k = 1;
        this.f5574l = 0;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0615R.styleable.CropImageView, 0, 0);
        try {
            this.f5570h = obtainStyledAttributes.getInteger(0, 1);
            this.f5571i = obtainStyledAttributes.getBoolean(1, false);
            this.f5572j = obtainStyledAttributes.getInteger(2, 1);
            this.f5573k = obtainStyledAttributes.getInteger(3, 1);
            this.f5574l = obtainStyledAttributes.getResourceId(4, 0);
            m8222a(context);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public Parcelable onSaveInstanceState() {
        Parcelable bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        bundle.putInt("DEGREES_ROTATED", this.f5567e);
        return bundle;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            if (this.f5566d != null) {
                this.f5567e = bundle.getInt("DEGREES_ROTATED");
                int i = this.f5567e;
                m8223a(this.f5567e);
                this.f5567e = i;
            }
            super.onRestoreInstanceState(bundle.getParcelable("instanceState"));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        if (this.f5566d != null) {
            this.f5565c.setBitmapRect(ImageViewUtil.m8280a(this.f5566d, this));
            return;
        }
        this.f5565c.setBitmapRect(f5563a);
    }

    protected void onMeasure(int i, int i2) {
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size2 = MeasureSpec.getSize(i2);
        if (this.f5566d != null) {
            int width;
            int height;
            super.onMeasure(i, i2);
            if (size2 == 0) {
                size2 = this.f5566d.getHeight();
            }
            double d = Double.POSITIVE_INFINITY;
            double d2 = Double.POSITIVE_INFINITY;
            if (size < this.f5566d.getWidth()) {
                d = ((double) size) / ((double) this.f5566d.getWidth());
            }
            if (size2 < this.f5566d.getHeight()) {
                d2 = ((double) size2) / ((double) this.f5566d.getHeight());
            }
            if (d == Double.POSITIVE_INFINITY && d2 == Double.POSITIVE_INFINITY) {
                width = this.f5566d.getWidth();
                height = this.f5566d.getHeight();
            } else if (d <= d2) {
                height = (int) (d * ((double) this.f5566d.getHeight()));
                width = size;
            } else {
                width = (int) (((double) this.f5566d.getWidth()) * d2);
                height = size2;
            }
            width = m8221a(mode, size, width);
            size2 = m8221a(mode2, size2, height);
            this.f5568f = width;
            this.f5569g = size2;
            this.f5565c.setBitmapRect(ImageViewUtil.m8279a(this.f5566d.getWidth(), this.f5566d.getHeight(), this.f5568f, this.f5569g));
            setMeasuredDimension(this.f5568f, this.f5569g);
            return;
        }
        this.f5565c.setBitmapRect(f5563a);
        setMeasuredDimension(size, size2);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.f5568f > 0 && this.f5569g > 0) {
            LayoutParams layoutParams = getLayoutParams();
            layoutParams.width = this.f5568f;
            layoutParams.height = this.f5569g;
            setLayoutParams(layoutParams);
        }
    }

    public int getImageResource() {
        return this.f5574l;
    }

    public void setImageBitmap(Bitmap bitmap) {
        this.f5566d = bitmap;
        this.f5564b.setImageBitmap(this.f5566d);
        if (this.f5565c != null) {
            this.f5565c.m8233a();
        }
    }

    public void setImageResource(int i) {
        if (i != 0) {
            setImageBitmap(BitmapFactory.decodeResource(getResources(), i));
        }
    }

    public Bitmap getCroppedImage() {
        Rect a = ImageViewUtil.m8280a(this.f5566d, this.f5564b);
        float width = ((float) this.f5566d.getWidth()) / ((float) a.width());
        float height = ((float) this.f5566d.getHeight()) / ((float) a.height());
        float a2 = Edge.LEFT.m8242a() - ((float) a.left);
        float a3 = Edge.TOP.m8242a() - ((float) a.top);
        return Bitmap.createBitmap(this.f5566d, (int) (a2 * width), (int) (a3 * height), (int) (width * Edge.m8237b()), (int) (height * Edge.m8239c()));
    }

    public RectF getActualCropRect() {
        Rect a = ImageViewUtil.m8280a(this.f5566d, this.f5564b);
        float width = ((float) this.f5566d.getWidth()) / ((float) a.width());
        float height = ((float) this.f5566d.getHeight()) / ((float) a.height());
        float a2 = Edge.LEFT.m8242a() - ((float) a.left);
        float a3 = Edge.TOP.m8242a() - ((float) a.top);
        a2 *= width;
        a3 *= height;
        return new RectF(Math.max(0.0f, a2), Math.max(0.0f, a3), Math.min((float) this.f5566d.getWidth(), (width * Edge.m8237b()) + a2), Math.min((float) this.f5566d.getHeight(), (height * Edge.m8239c()) + a3));
    }

    public void setFixedAspectRatio(boolean z) {
        this.f5565c.setFixedAspectRatio(z);
    }

    public void setGuidelines(int i) {
        this.f5565c.setGuidelines(i);
    }

    public void m8223a(int i) {
        Matrix matrix = new Matrix();
        matrix.postRotate((float) i);
        this.f5566d = Bitmap.createBitmap(this.f5566d, 0, 0, this.f5566d.getWidth(), this.f5566d.getHeight(), matrix, true);
        setImageBitmap(this.f5566d);
        this.f5567e += i;
        this.f5567e %= 360;
    }

    private void m8222a(Context context) {
        View inflate = LayoutInflater.from(context).inflate(C0615R.layout.crop_image_view, this, true);
        this.f5564b = (ImageView) inflate.findViewById(C0615R.id.ImageView_image);
        setImageResource(this.f5574l);
        this.f5565c = (CropOverlayView) inflate.findViewById(C0615R.id.CropOverlayView);
        this.f5565c.m8234a(this.f5570h, this.f5571i, this.f5572j, this.f5573k);
    }

    private static int m8221a(int i, int i2, int i3) {
        if (i == 1073741824) {
            return i2;
        }
        return i == Integer.MIN_VALUE ? Math.min(i3, i2) : i3;
    }
}
