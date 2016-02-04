package com.edmodo.cropper.cropwindow;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import com.edmodo.cropper.cropwindow.edge.Edge;
import com.edmodo.cropper.cropwindow.handle.Handle;
import com.edmodo.cropper.util.AspectRatioUtil;
import com.edmodo.cropper.util.HandleUtil;
import com.edmodo.cropper.util.PaintUtil;
import se.emilsjolander.stickylistheaders.C1128R;

public class CropOverlayView extends View {
    private static final float f5575a;
    private static final float f5576b;
    private static final float f5577c;
    private static final float f5578d;
    private Paint f5579e;
    private Paint f5580f;
    private Paint f5581g;
    private Paint f5582h;
    private Rect f5583i;
    private float f5584j;
    private float f5585k;
    private Pair<Float, Float> f5586l;
    private Handle f5587m;
    private boolean f5588n;
    private int f5589o;
    private int f5590p;
    private float f5591q;
    private int f5592r;
    private boolean f5593s;
    private float f5594t;
    private float f5595u;
    private float f5596v;

    static {
        f5575a = PaintUtil.m8284b();
        f5576b = PaintUtil.m8286c();
        f5577c = (f5575a / 2.0f) - (f5576b / 2.0f);
        f5578d = (f5575a / 2.0f) + f5577c;
    }

    public CropOverlayView(Context context) {
        super(context);
        this.f5588n = false;
        this.f5589o = 1;
        this.f5590p = 1;
        this.f5591q = ((float) this.f5589o) / ((float) this.f5590p);
        this.f5593s = false;
        m8225a(context);
    }

    public CropOverlayView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f5588n = false;
        this.f5589o = 1;
        this.f5590p = 1;
        this.f5591q = ((float) this.f5589o) / ((float) this.f5590p);
        this.f5593s = false;
        m8225a(context);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        m8228a(this.f5583i);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        m8227a(canvas, this.f5583i);
        if (m8231b()) {
            if (this.f5592r == 2) {
                m8226a(canvas);
            } else if (this.f5592r == 1) {
                if (this.f5587m != null) {
                    m8226a(canvas);
                }
            } else if (this.f5592r == 0) {
            }
        }
        canvas.drawRect(Edge.LEFT.m8242a(), Edge.TOP.m8242a(), Edge.RIGHT.m8242a(), Edge.BOTTOM.m8242a(), this.f5579e);
        m8230b(canvas);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled()) {
            return false;
        }
        switch (motionEvent.getAction()) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                m8224a(motionEvent.getX(), motionEvent.getY());
                return true;
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                getParent().requestDisallowInterceptTouchEvent(false);
                m8232c();
                return true;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                m8229b(motionEvent.getX(), motionEvent.getY());
                getParent().requestDisallowInterceptTouchEvent(true);
                return true;
            default:
                return false;
        }
    }

    public void setBitmapRect(Rect rect) {
        this.f5583i = rect;
        m8228a(this.f5583i);
    }

    public void m8233a() {
        if (this.f5593s) {
            m8228a(this.f5583i);
            invalidate();
        }
    }

    public void setGuidelines(int i) {
        if (i < 0 || i > 2) {
            throw new IllegalArgumentException("Guideline value must be set between 0 and 2. See documentation.");
        }
        this.f5592r = i;
        if (this.f5593s) {
            m8228a(this.f5583i);
            invalidate();
        }
    }

    public void setFixedAspectRatio(boolean z) {
        this.f5588n = z;
        if (this.f5593s) {
            m8228a(this.f5583i);
            invalidate();
        }
    }

    public void setAspectRatioX(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Cannot set aspect ratio value to a number less than or equal to 0.");
        }
        this.f5589o = i;
        this.f5591q = ((float) this.f5589o) / ((float) this.f5590p);
        if (this.f5593s) {
            m8228a(this.f5583i);
            invalidate();
        }
    }

    public void setAspectRatioY(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Cannot set aspect ratio value to a number less than or equal to 0.");
        }
        this.f5590p = i;
        this.f5591q = ((float) this.f5589o) / ((float) this.f5590p);
        if (this.f5593s) {
            m8228a(this.f5583i);
            invalidate();
        }
    }

    public void m8234a(int i, boolean z, int i2, int i3) {
        if (i < 0 || i > 2) {
            throw new IllegalArgumentException("Guideline value must be set between 0 and 2. See documentation.");
        }
        this.f5592r = i;
        this.f5588n = z;
        if (i2 <= 0) {
            throw new IllegalArgumentException("Cannot set aspect ratio value to a number less than or equal to 0.");
        }
        this.f5589o = i2;
        this.f5591q = ((float) this.f5589o) / ((float) this.f5590p);
        if (i3 <= 0) {
            throw new IllegalArgumentException("Cannot set aspect ratio value to a number less than or equal to 0.");
        }
        this.f5590p = i3;
        this.f5591q = ((float) this.f5589o) / ((float) this.f5590p);
    }

    private void m8225a(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.f5584j = HandleUtil.m8271a(context);
        this.f5585k = TypedValue.applyDimension(1, 6.0f, displayMetrics);
        this.f5579e = PaintUtil.m8283a(context);
        this.f5580f = PaintUtil.m8282a();
        this.f5582h = PaintUtil.m8285b(context);
        this.f5581g = PaintUtil.m8287c(context);
        this.f5595u = TypedValue.applyDimension(1, f5577c, displayMetrics);
        this.f5594t = TypedValue.applyDimension(1, f5578d, displayMetrics);
        this.f5596v = TypedValue.applyDimension(1, 20.0f, displayMetrics);
        this.f5592r = 1;
    }

    private void m8228a(Rect rect) {
        if (!this.f5593s) {
            this.f5593s = true;
        }
        float width;
        float height;
        if (!this.f5588n) {
            width = ((float) rect.width()) * 0.1f;
            height = ((float) rect.height()) * 0.1f;
            Edge.LEFT.m8244a(((float) rect.left) + width);
            Edge.TOP.m8244a(((float) rect.top) + height);
            Edge.RIGHT.m8244a(((float) rect.right) - width);
            Edge.BOTTOM.m8244a(((float) rect.bottom) - height);
        } else if (AspectRatioUtil.m8265a(rect) > this.f5591q) {
            Edge.TOP.m8244a((float) rect.top);
            Edge.BOTTOM.m8244a((float) rect.bottom);
            width = ((float) getWidth()) / 2.0f;
            height = Math.max(40.0f, AspectRatioUtil.m8263a(Edge.TOP.m8242a(), Edge.BOTTOM.m8242a(), this.f5591q));
            if (height == 40.0f) {
                this.f5591q = 40.0f / (Edge.BOTTOM.m8242a() - Edge.TOP.m8242a());
            }
            height /= 2.0f;
            Edge.LEFT.m8244a(width - height);
            Edge.RIGHT.m8244a(width + height);
        } else {
            Edge.LEFT.m8244a((float) rect.left);
            Edge.RIGHT.m8244a((float) rect.right);
            width = ((float) getHeight()) / 2.0f;
            height = Math.max(40.0f, AspectRatioUtil.m8266b(Edge.LEFT.m8242a(), Edge.RIGHT.m8242a(), this.f5591q));
            if (height == 40.0f) {
                this.f5591q = (Edge.RIGHT.m8242a() - Edge.LEFT.m8242a()) / 40.0f;
            }
            height /= 2.0f;
            Edge.TOP.m8244a(width - height);
            Edge.BOTTOM.m8244a(width + height);
        }
    }

    public static boolean m8231b() {
        if (Math.abs(Edge.LEFT.m8242a() - Edge.RIGHT.m8242a()) < 100.0f || Math.abs(Edge.TOP.m8242a() - Edge.BOTTOM.m8242a()) < 100.0f) {
            return false;
        }
        return true;
    }

    private void m8226a(Canvas canvas) {
        float a = Edge.LEFT.m8242a();
        float a2 = Edge.TOP.m8242a();
        float a3 = Edge.RIGHT.m8242a();
        float a4 = Edge.BOTTOM.m8242a();
        float b = Edge.m8237b() / 3.0f;
        float f = a + b;
        canvas.drawLine(f, a2, f, a4, this.f5580f);
        f = a3 - b;
        canvas.drawLine(f, a2, f, a4, this.f5580f);
        float c = Edge.m8239c() / 3.0f;
        b = a2 + c;
        canvas.drawLine(a, b, a3, b, this.f5580f);
        b = a4 - c;
        canvas.drawLine(a, b, a3, b, this.f5580f);
    }

    private void m8227a(Canvas canvas, Rect rect) {
        float a = Edge.LEFT.m8242a();
        float a2 = Edge.TOP.m8242a();
        float a3 = Edge.RIGHT.m8242a();
        float a4 = Edge.BOTTOM.m8242a();
        canvas.drawRect((float) rect.left, (float) rect.top, (float) rect.right, a2, this.f5582h);
        canvas.drawRect((float) rect.left, a4, (float) rect.right, (float) rect.bottom, this.f5582h);
        canvas.drawRect((float) rect.left, a2, a, a4, this.f5582h);
        canvas.drawRect(a3, a2, (float) rect.right, a4, this.f5582h);
    }

    private void m8230b(Canvas canvas) {
        float a = Edge.LEFT.m8242a();
        float a2 = Edge.TOP.m8242a();
        float a3 = Edge.RIGHT.m8242a();
        float a4 = Edge.BOTTOM.m8242a();
        canvas.drawLine(a - this.f5595u, a2 - this.f5594t, a - this.f5595u, a2 + this.f5596v, this.f5581g);
        canvas.drawLine(a, a2 - this.f5595u, a + this.f5596v, a2 - this.f5595u, this.f5581g);
        canvas.drawLine(a3 + this.f5595u, a2 - this.f5594t, a3 + this.f5595u, a2 + this.f5596v, this.f5581g);
        canvas.drawLine(a3, a2 - this.f5595u, a3 - this.f5596v, a2 - this.f5595u, this.f5581g);
        canvas.drawLine(a - this.f5595u, a4 + this.f5594t, a - this.f5595u, a4 - this.f5596v, this.f5581g);
        canvas.drawLine(a, a4 + this.f5595u, a + this.f5596v, a4 + this.f5595u, this.f5581g);
        canvas.drawLine(a3 + this.f5595u, a4 + this.f5594t, a3 + this.f5595u, a4 - this.f5596v, this.f5581g);
        canvas.drawLine(a3, a4 + this.f5595u, a3 - this.f5596v, a4 + this.f5595u, this.f5581g);
    }

    private void m8224a(float f, float f2) {
        float a = Edge.LEFT.m8242a();
        float a2 = Edge.TOP.m8242a();
        float a3 = Edge.RIGHT.m8242a();
        float a4 = Edge.BOTTOM.m8242a();
        this.f5587m = HandleUtil.m8273a(f, f2, a, a2, a3, a4, this.f5584j);
        if (this.f5587m != null) {
            this.f5586l = HandleUtil.m8272a(this.f5587m, f, f2, a, a2, a3, a4);
            invalidate();
        }
    }

    private void m8232c() {
        if (this.f5587m != null) {
            this.f5587m = null;
            invalidate();
        }
    }

    private void m8229b(float f, float f2) {
        if (this.f5587m != null) {
            float floatValue = f + ((Float) this.f5586l.first).floatValue();
            float floatValue2 = f2 + ((Float) this.f5586l.second).floatValue();
            if (this.f5588n) {
                this.f5587m.m8259a(floatValue, floatValue2, this.f5591q, this.f5583i, this.f5585k);
            } else {
                this.f5587m.m8260a(floatValue, floatValue2, this.f5583i, this.f5585k);
            }
            invalidate();
        }
    }
}
