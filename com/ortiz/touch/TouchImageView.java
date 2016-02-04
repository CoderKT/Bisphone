package com.ortiz.touch;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.OverScroller;
import android.widget.Scroller;
import se.emilsjolander.stickylistheaders.C1128R;

public class TouchImageView extends ImageView {
    private OnTouchListener f7128A;
    private OnTouchImageViewListener f7129B;
    private float f7130a;
    private Matrix f7131b;
    private Matrix f7132c;
    private State f7133d;
    private float f7134e;
    private float f7135f;
    private float f7136g;
    private float f7137h;
    private float[] f7138i;
    private Context f7139j;
    private Fling f7140k;
    private ScaleType f7141l;
    private boolean f7142m;
    private boolean f7143n;
    private ZoomVariables f7144o;
    private int f7145p;
    private int f7146q;
    private int f7147r;
    private int f7148s;
    private float f7149t;
    private float f7150u;
    private float f7151v;
    private float f7152w;
    private ScaleGestureDetector f7153x;
    private GestureDetector f7154y;
    private OnDoubleTapListener f7155z;

    /* renamed from: com.ortiz.touch.TouchImageView.1 */
    /* synthetic */ class C09281 {
        static final /* synthetic */ int[] f7094a;

        static {
            f7094a = new int[ScaleType.values().length];
            try {
                f7094a[ScaleType.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f7094a[ScaleType.CENTER_CROP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f7094a[ScaleType.CENTER_INSIDE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f7094a[ScaleType.FIT_CENTER.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f7094a[ScaleType.FIT_XY.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    @TargetApi(9)
    class CompatScroller {
        Scroller f7095a;
        OverScroller f7096b;
        boolean f7097c;
        final /* synthetic */ TouchImageView f7098d;

        public CompatScroller(TouchImageView touchImageView, Context context) {
            this.f7098d = touchImageView;
            if (VERSION.SDK_INT < 9) {
                this.f7097c = true;
                this.f7095a = new Scroller(context);
                return;
            }
            this.f7097c = false;
            this.f7096b = new OverScroller(context);
        }

        public void m11287a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            if (this.f7097c) {
                this.f7095a.fling(i, i2, i3, i4, i5, i6, i7, i8);
            } else {
                this.f7096b.fling(i, i2, i3, i4, i5, i6, i7, i8);
            }
        }

        public void m11288a(boolean z) {
            if (this.f7097c) {
                this.f7095a.forceFinished(z);
            } else {
                this.f7096b.forceFinished(z);
            }
        }

        public boolean m11289a() {
            if (this.f7097c) {
                return this.f7095a.isFinished();
            }
            return this.f7096b.isFinished();
        }

        public boolean m11290b() {
            if (this.f7097c) {
                return this.f7095a.computeScrollOffset();
            }
            this.f7096b.computeScrollOffset();
            return this.f7096b.computeScrollOffset();
        }

        public int m11291c() {
            if (this.f7097c) {
                return this.f7095a.getCurrX();
            }
            return this.f7096b.getCurrX();
        }

        public int m11292d() {
            if (this.f7097c) {
                return this.f7095a.getCurrY();
            }
            return this.f7096b.getCurrY();
        }
    }

    class DoubleTapZoom implements Runnable {
        final /* synthetic */ TouchImageView f7099a;
        private long f7100b;
        private float f7101c;
        private float f7102d;
        private float f7103e;
        private float f7104f;
        private boolean f7105g;
        private AccelerateDecelerateInterpolator f7106h;
        private PointF f7107i;
        private PointF f7108j;

        DoubleTapZoom(TouchImageView touchImageView, float f, float f2, float f3, boolean z) {
            this.f7099a = touchImageView;
            this.f7106h = new AccelerateDecelerateInterpolator();
            touchImageView.setState(State.ANIMATE_ZOOM);
            this.f7100b = System.currentTimeMillis();
            this.f7101c = touchImageView.f7130a;
            this.f7102d = f;
            this.f7105g = z;
            PointF a = touchImageView.m11301a(f2, f3, false);
            this.f7103e = a.x;
            this.f7104f = a.y;
            this.f7107i = touchImageView.m11300a(this.f7103e, this.f7104f);
            this.f7108j = new PointF((float) (touchImageView.f7145p / 2), (float) (touchImageView.f7146q / 2));
        }

        public void run() {
            float a = m11293a();
            this.f7099a.m11306a(m11295b(a), this.f7103e, this.f7104f, this.f7105g);
            m11294a(a);
            this.f7099a.m11321e();
            this.f7099a.setImageMatrix(this.f7099a.f7131b);
            if (this.f7099a.f7129B != null) {
                this.f7099a.f7129B.m11297a();
            }
            if (a < 1.0f) {
                this.f7099a.m11312a((Runnable) this);
            } else {
                this.f7099a.setState(State.NONE);
            }
        }

        private void m11294a(float f) {
            float f2 = this.f7107i.x + ((this.f7108j.x - this.f7107i.x) * f);
            float f3 = this.f7107i.y + ((this.f7108j.y - this.f7107i.y) * f);
            PointF a = this.f7099a.m11300a(this.f7103e, this.f7104f);
            this.f7099a.f7131b.postTranslate(f2 - a.x, f3 - a.y);
        }

        private float m11293a() {
            return this.f7106h.getInterpolation(Math.min(1.0f, ((float) (System.currentTimeMillis() - this.f7100b)) / 500.0f));
        }

        private double m11295b(float f) {
            return ((double) (this.f7101c + ((this.f7102d - this.f7101c) * f))) / ((double) this.f7099a.f7130a);
        }
    }

    class Fling implements Runnable {
        CompatScroller f7109a;
        int f7110b;
        int f7111c;
        final /* synthetic */ TouchImageView f7112d;

        Fling(TouchImageView touchImageView, int i, int i2) {
            int i3;
            int i4;
            int k;
            int i5;
            this.f7112d = touchImageView;
            touchImageView.setState(State.FLING);
            this.f7109a = new CompatScroller(touchImageView, touchImageView.f7139j);
            touchImageView.f7131b.getValues(touchImageView.f7138i);
            int i6 = (int) touchImageView.f7138i[2];
            int i7 = (int) touchImageView.f7138i[5];
            if (touchImageView.getImageWidth() > ((float) touchImageView.f7145p)) {
                i3 = touchImageView.f7145p - ((int) touchImageView.getImageWidth());
                i4 = 0;
            } else {
                i4 = i6;
                i3 = i6;
            }
            if (touchImageView.getImageHeight() > ((float) touchImageView.f7146q)) {
                k = touchImageView.f7146q - ((int) touchImageView.getImageHeight());
                i5 = 0;
            } else {
                i5 = i7;
                k = i7;
            }
            this.f7109a.m11287a(i6, i7, i, i2, i3, i4, k, i5);
            this.f7110b = i6;
            this.f7111c = i7;
        }

        public void m11296a() {
            if (this.f7109a != null) {
                this.f7112d.setState(State.NONE);
                this.f7109a.m11288a(true);
            }
        }

        public void run() {
            if (this.f7112d.f7129B != null) {
                this.f7112d.f7129B.m11297a();
            }
            if (this.f7109a.m11289a()) {
                this.f7109a = null;
            } else if (this.f7109a.m11290b()) {
                int c = this.f7109a.m11291c();
                int d = this.f7109a.m11292d();
                int i = c - this.f7110b;
                int i2 = d - this.f7111c;
                this.f7110b = c;
                this.f7111c = d;
                this.f7112d.f7131b.postTranslate((float) i, (float) i2);
                this.f7112d.m11319d();
                this.f7112d.setImageMatrix(this.f7112d.f7131b);
                this.f7112d.m11312a((Runnable) this);
            }
        }
    }

    class GestureListener extends SimpleOnGestureListener {
        final /* synthetic */ TouchImageView f7113a;

        private GestureListener(TouchImageView touchImageView) {
            this.f7113a = touchImageView;
        }

        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            if (this.f7113a.f7155z != null) {
                return this.f7113a.f7155z.onSingleTapConfirmed(motionEvent);
            }
            return this.f7113a.performClick();
        }

        public void onLongPress(MotionEvent motionEvent) {
            this.f7113a.performLongClick();
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (this.f7113a.f7140k != null) {
                this.f7113a.f7140k.m11296a();
            }
            this.f7113a.f7140k = new Fling(this.f7113a, (int) f, (int) f2);
            this.f7113a.m11312a(this.f7113a.f7140k);
            return super.onFling(motionEvent, motionEvent2, f, f2);
        }

        public boolean onDoubleTap(MotionEvent motionEvent) {
            boolean onDoubleTap;
            if (this.f7113a.f7155z != null) {
                onDoubleTap = this.f7113a.f7155z.onDoubleTap(motionEvent);
            } else {
                onDoubleTap = false;
            }
            if (this.f7113a.f7133d != State.NONE) {
                return onDoubleTap;
            }
            this.f7113a.m11312a(new DoubleTapZoom(this.f7113a, this.f7113a.f7130a == this.f7113a.f7134e ? this.f7113a.f7135f : this.f7113a.f7134e, motionEvent.getX(), motionEvent.getY(), false));
            return true;
        }

        public boolean onDoubleTapEvent(MotionEvent motionEvent) {
            if (this.f7113a.f7155z != null) {
                return this.f7113a.f7155z.onDoubleTapEvent(motionEvent);
            }
            return false;
        }
    }

    public interface OnTouchImageViewListener {
        void m11297a();
    }

    class PrivateOnTouchListener implements OnTouchListener {
        final /* synthetic */ TouchImageView f7114a;
        private PointF f7115b;

        private PrivateOnTouchListener(TouchImageView touchImageView) {
            this.f7114a = touchImageView;
            this.f7115b = new PointF();
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            this.f7114a.f7153x.onTouchEvent(motionEvent);
            this.f7114a.f7154y.onTouchEvent(motionEvent);
            PointF pointF = new PointF(motionEvent.getX(), motionEvent.getY());
            if (this.f7114a.f7133d == State.NONE || this.f7114a.f7133d == State.DRAG || this.f7114a.f7133d == State.FLING) {
                switch (motionEvent.getAction()) {
                    case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                        this.f7115b.set(pointF);
                        if (this.f7114a.f7140k != null) {
                            this.f7114a.f7140k.m11296a();
                        }
                        this.f7114a.setState(State.DRAG);
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                        this.f7114a.setState(State.NONE);
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                        if (this.f7114a.f7133d == State.DRAG) {
                            float f = pointF.y - this.f7115b.y;
                            this.f7114a.f7131b.postTranslate(this.f7114a.m11315c(pointF.x - this.f7115b.x, (float) this.f7114a.f7145p, this.f7114a.getImageWidth()), this.f7114a.m11315c(f, (float) this.f7114a.f7146q, this.f7114a.getImageHeight()));
                            this.f7114a.m11319d();
                            this.f7115b.set(pointF.x, pointF.y);
                            break;
                        }
                        break;
                }
            }
            this.f7114a.setImageMatrix(this.f7114a.f7131b);
            if (this.f7114a.f7128A != null) {
                this.f7114a.f7128A.onTouch(view, motionEvent);
            }
            if (this.f7114a.f7129B != null) {
                this.f7114a.f7129B.m11297a();
            }
            return true;
        }
    }

    class ScaleListener extends SimpleOnScaleGestureListener {
        final /* synthetic */ TouchImageView f7116a;

        private ScaleListener(TouchImageView touchImageView) {
            this.f7116a = touchImageView;
        }

        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            this.f7116a.setState(State.ZOOM);
            return true;
        }

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            this.f7116a.m11306a((double) scaleGestureDetector.getScaleFactor(), scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY(), true);
            if (this.f7116a.f7129B != null) {
                this.f7116a.f7129B.m11297a();
            }
            return true;
        }

        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
            super.onScaleEnd(scaleGestureDetector);
            this.f7116a.setState(State.NONE);
            boolean z = false;
            float d = this.f7116a.f7130a;
            if (this.f7116a.f7130a > this.f7116a.f7135f) {
                d = this.f7116a.f7135f;
                z = true;
            } else if (this.f7116a.f7130a < this.f7116a.f7134e) {
                d = this.f7116a.f7134e;
                z = true;
            }
            if (z) {
                this.f7116a.m11312a(new DoubleTapZoom(this.f7116a, d, (float) (this.f7116a.f7145p / 2), (float) (this.f7116a.f7146q / 2), true));
            }
        }
    }

    enum State {
        NONE,
        DRAG,
        ZOOM,
        FLING,
        ANIMATE_ZOOM
    }

    class ZoomVariables {
        public float f7123a;
        public float f7124b;
        public float f7125c;
        public ScaleType f7126d;
        final /* synthetic */ TouchImageView f7127e;

        public ZoomVariables(TouchImageView touchImageView, float f, float f2, float f3, ScaleType scaleType) {
            this.f7127e = touchImageView;
            this.f7123a = f;
            this.f7124b = f2;
            this.f7125c = f3;
            this.f7126d = scaleType;
        }
    }

    public TouchImageView(Context context) {
        super(context);
        this.f7155z = null;
        this.f7128A = null;
        this.f7129B = null;
        m11308a(context);
    }

    public TouchImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f7155z = null;
        this.f7128A = null;
        this.f7129B = null;
        m11308a(context);
    }

    public TouchImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f7155z = null;
        this.f7128A = null;
        this.f7129B = null;
        m11308a(context);
    }

    private void m11308a(Context context) {
        super.setClickable(true);
        this.f7139j = context;
        this.f7153x = new ScaleGestureDetector(context, new ScaleListener());
        this.f7154y = new GestureDetector(context, new GestureListener());
        this.f7131b = new Matrix();
        this.f7132c = new Matrix();
        this.f7138i = new float[9];
        this.f7130a = 1.0f;
        if (this.f7141l == null) {
            this.f7141l = ScaleType.FIT_CENTER;
        }
        this.f7134e = 1.0f;
        this.f7135f = 3.0f;
        this.f7136g = 0.75f * this.f7134e;
        this.f7137h = 1.25f * this.f7135f;
        setImageMatrix(this.f7131b);
        setScaleType(ScaleType.MATRIX);
        setState(State.NONE);
        this.f7143n = false;
        super.setOnTouchListener(new PrivateOnTouchListener());
    }

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.f7128A = onTouchListener;
    }

    public void setOnTouchImageViewListener(OnTouchImageViewListener onTouchImageViewListener) {
        this.f7129B = onTouchImageViewListener;
    }

    public void setOnDoubleTapListener(OnDoubleTapListener onDoubleTapListener) {
        this.f7155z = onDoubleTapListener;
    }

    public void setImageResource(int i) {
        super.setImageResource(i);
        m11317c();
        m11323f();
    }

    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        m11317c();
        m11323f();
    }

    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        m11317c();
        m11323f();
    }

    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        m11317c();
        m11323f();
    }

    public void setScaleType(ScaleType scaleType) {
        if (scaleType == ScaleType.FIT_START || scaleType == ScaleType.FIT_END) {
            throw new UnsupportedOperationException("TouchImageView does not support FIT_START or FIT_END");
        } else if (scaleType == ScaleType.MATRIX) {
            super.setScaleType(ScaleType.MATRIX);
        } else {
            this.f7141l = scaleType;
            if (this.f7143n) {
                setZoom(this);
            }
        }
    }

    public ScaleType getScaleType() {
        return this.f7141l;
    }

    public boolean m11339a() {
        return this.f7130a != 1.0f;
    }

    public RectF getZoomedRect() {
        if (this.f7141l == ScaleType.FIT_XY) {
            throw new UnsupportedOperationException("getZoomedRect() not supported with FIT_XY");
        }
        PointF a = m11301a(0.0f, 0.0f, true);
        PointF a2 = m11301a((float) this.f7145p, (float) this.f7146q, true);
        float intrinsicWidth = (float) getDrawable().getIntrinsicWidth();
        float intrinsicHeight = (float) getDrawable().getIntrinsicHeight();
        return new RectF(a.x / intrinsicWidth, a.y / intrinsicHeight, a2.x / intrinsicWidth, a2.y / intrinsicHeight);
    }

    private void m11317c() {
        if (this.f7131b != null && this.f7146q != 0 && this.f7145p != 0) {
            this.f7131b.getValues(this.f7138i);
            this.f7132c.setValues(this.f7138i);
            this.f7152w = this.f7150u;
            this.f7151v = this.f7149t;
            this.f7148s = this.f7146q;
            this.f7147r = this.f7145p;
        }
    }

    public Parcelable onSaveInstanceState() {
        Parcelable bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        bundle.putFloat("saveScale", this.f7130a);
        bundle.putFloat("matchViewHeight", this.f7150u);
        bundle.putFloat("matchViewWidth", this.f7149t);
        bundle.putInt("viewWidth", this.f7145p);
        bundle.putInt("viewHeight", this.f7146q);
        this.f7131b.getValues(this.f7138i);
        bundle.putFloatArray("matrix", this.f7138i);
        bundle.putBoolean("imageRendered", this.f7142m);
        return bundle;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.f7130a = bundle.getFloat("saveScale");
            this.f7138i = bundle.getFloatArray("matrix");
            this.f7132c.setValues(this.f7138i);
            this.f7152w = bundle.getFloat("matchViewHeight");
            this.f7151v = bundle.getFloat("matchViewWidth");
            this.f7148s = bundle.getInt("viewHeight");
            this.f7147r = bundle.getInt("viewWidth");
            this.f7142m = bundle.getBoolean("imageRendered");
            super.onRestoreInstanceState(bundle.getParcelable("instanceState"));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    protected void onDraw(Canvas canvas) {
        this.f7143n = true;
        this.f7142m = true;
        if (this.f7144o != null) {
            m11338a(this.f7144o.f7123a, this.f7144o.f7124b, this.f7144o.f7125c, this.f7144o.f7126d);
            this.f7144o = null;
        }
        super.onDraw(canvas);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        m11317c();
    }

    public float getMaxZoom() {
        return this.f7135f;
    }

    public void setMaxZoom(float f) {
        this.f7135f = f;
        this.f7137h = 1.25f * this.f7135f;
    }

    public float getMinZoom() {
        return this.f7134e;
    }

    public float getCurrentZoom() {
        return this.f7130a;
    }

    public void setMinZoom(float f) {
        this.f7134e = f;
        this.f7136g = 0.75f * this.f7134e;
    }

    public void m11341b() {
        this.f7130a = 1.0f;
        m11323f();
    }

    public void setZoom(float f) {
        m11337a(f, 0.5f, 0.5f);
    }

    public void m11337a(float f, float f2, float f3) {
        m11338a(f, f2, f3, this.f7141l);
    }

    public void m11338a(float f, float f2, float f3, ScaleType scaleType) {
        if (this.f7143n) {
            if (scaleType != this.f7141l) {
                setScaleType(scaleType);
            }
            m11341b();
            m11306a((double) f, (float) (this.f7145p / 2), (float) (this.f7146q / 2), true);
            this.f7131b.getValues(this.f7138i);
            this.f7138i[2] = -((getImageWidth() * f2) - (((float) this.f7145p) * 0.5f));
            this.f7138i[5] = -((getImageHeight() * f3) - (((float) this.f7146q) * 0.5f));
            this.f7131b.setValues(this.f7138i);
            m11319d();
            setImageMatrix(this.f7131b);
            return;
        }
        this.f7144o = new ZoomVariables(this, f, f2, f3, scaleType);
    }

    public void setZoom(TouchImageView touchImageView) {
        PointF scrollPosition = touchImageView.getScrollPosition();
        m11338a(touchImageView.getCurrentZoom(), scrollPosition.x, scrollPosition.y, touchImageView.getScaleType());
    }

    public PointF getScrollPosition() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return null;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        PointF a = m11301a((float) (this.f7145p / 2), (float) (this.f7146q / 2), true);
        a.x /= (float) intrinsicWidth;
        a.y /= (float) intrinsicHeight;
        return a;
    }

    private void m11319d() {
        this.f7131b.getValues(this.f7138i);
        float f = this.f7138i[2];
        float f2 = this.f7138i[5];
        f = m11313b(f, (float) this.f7145p, getImageWidth());
        f2 = m11313b(f2, (float) this.f7146q, getImageHeight());
        if (f != 0.0f || f2 != 0.0f) {
            this.f7131b.postTranslate(f, f2);
        }
    }

    private void m11321e() {
        m11319d();
        this.f7131b.getValues(this.f7138i);
        if (getImageWidth() < ((float) this.f7145p)) {
            this.f7138i[2] = (((float) this.f7145p) - getImageWidth()) / 2.0f;
        }
        if (getImageHeight() < ((float) this.f7146q)) {
            this.f7138i[5] = (((float) this.f7146q) - getImageHeight()) / 2.0f;
        }
        this.f7131b.setValues(this.f7138i);
    }

    private float m11313b(float f, float f2, float f3) {
        float f4;
        float f5;
        if (f3 <= f2) {
            f4 = f2 - f3;
            f5 = 0.0f;
        } else {
            f5 = f2 - f3;
            f4 = 0.0f;
        }
        if (f < f5) {
            return (-f) + f5;
        }
        if (f > f4) {
            return (-f) + f4;
        }
        return 0.0f;
    }

    private float m11315c(float f, float f2, float f3) {
        if (f3 <= f2) {
            return 0.0f;
        }
        return f;
    }

    private float getImageWidth() {
        return this.f7149t * this.f7130a;
    }

    private float getImageHeight() {
        return this.f7150u * this.f7130a;
    }

    protected void onMeasure(int i, int i2) {
        Drawable drawable = getDrawable();
        if (drawable == null || drawable.getIntrinsicWidth() == 0 || drawable.getIntrinsicHeight() == 0) {
            setMeasuredDimension(0, 0);
            return;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        int size = MeasureSpec.getSize(i);
        int mode = MeasureSpec.getMode(i);
        int size2 = MeasureSpec.getSize(i2);
        int mode2 = MeasureSpec.getMode(i2);
        this.f7145p = m11299a(mode, size, intrinsicWidth);
        this.f7146q = m11299a(mode2, size2, intrinsicHeight);
        setMeasuredDimension(this.f7145p, this.f7146q);
        m11323f();
    }

    private void m11323f() {
        Drawable drawable = getDrawable();
        if (drawable != null && drawable.getIntrinsicWidth() != 0 && drawable.getIntrinsicHeight() != 0 && this.f7131b != null && this.f7132c != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            float f = ((float) this.f7145p) / ((float) intrinsicWidth);
            float f2 = ((float) this.f7146q) / ((float) intrinsicHeight);
            switch (C09281.f7094a[this.f7141l.ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    f2 = 1.0f;
                    f = 1.0f;
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    f2 = Math.max(f, f2);
                    f = f2;
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    f2 = Math.min(1.0f, Math.min(f, f2));
                    f = f2;
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    break;
                default:
                    throw new UnsupportedOperationException("TouchImageView does not support FIT_START or FIT_END");
            }
            f2 = Math.min(f, f2);
            f = f2;
            float f3 = ((float) this.f7145p) - (((float) intrinsicWidth) * f);
            float f4 = ((float) this.f7146q) - (((float) intrinsicHeight) * f2);
            this.f7149t = ((float) this.f7145p) - f3;
            this.f7150u = ((float) this.f7146q) - f4;
            if (m11339a() || this.f7142m) {
                if (this.f7151v == 0.0f || this.f7152w == 0.0f) {
                    m11317c();
                }
                this.f7132c.getValues(this.f7138i);
                this.f7138i[0] = (this.f7149t / ((float) intrinsicWidth)) * this.f7130a;
                this.f7138i[4] = (this.f7150u / ((float) intrinsicHeight)) * this.f7130a;
                f = this.f7138i[2];
                float f5 = this.f7138i[5];
                m11307a(2, f, this.f7130a * this.f7151v, getImageWidth(), this.f7147r, this.f7145p, intrinsicWidth);
                m11307a(5, f5, this.f7152w * this.f7130a, getImageHeight(), this.f7148s, this.f7146q, intrinsicHeight);
                this.f7131b.setValues(this.f7138i);
            } else {
                this.f7131b.setScale(f, f2);
                this.f7131b.postTranslate(f3 / 2.0f, f4 / 2.0f);
                this.f7130a = 1.0f;
            }
            m11319d();
            setImageMatrix(this.f7131b);
        }
    }

    private int m11299a(int i, int i2, int i3) {
        switch (i) {
            case Integer.MIN_VALUE:
                return Math.min(i3, i2);
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                return i3;
            default:
                return i2;
        }
    }

    private void m11307a(int i, float f, float f2, float f3, int i2, int i3, int i4) {
        if (f3 < ((float) i3)) {
            this.f7138i[i] = (((float) i3) - (((float) i4) * this.f7138i[0])) * 0.5f;
        } else if (f > 0.0f) {
            this.f7138i[i] = -((f3 - ((float) i3)) * 0.5f);
        } else {
            this.f7138i[i] = -((((Math.abs(f) + (((float) i2) * 0.5f)) / f2) * f3) - (((float) i3) * 0.5f));
        }
    }

    private void setState(State state) {
        this.f7133d = state;
    }

    public boolean m11340a(int i) {
        return canScrollHorizontally(i);
    }

    public boolean canScrollHorizontally(int i) {
        this.f7131b.getValues(this.f7138i);
        float f = this.f7138i[2];
        if (getImageWidth() < ((float) this.f7145p)) {
            return false;
        }
        if (f >= -1.0f && i < 0) {
            return false;
        }
        if ((Math.abs(f) + ((float) this.f7145p)) + 1.0f < getImageWidth() || i <= 0) {
            return true;
        }
        return false;
    }

    private void m11306a(double d, float f, float f2, boolean z) {
        float f3;
        float f4;
        if (z) {
            f3 = this.f7136g;
            f4 = this.f7137h;
        } else {
            f3 = this.f7134e;
            f4 = this.f7135f;
        }
        float f5 = this.f7130a;
        this.f7130a = (float) (((double) this.f7130a) * d);
        if (this.f7130a > f4) {
            this.f7130a = f4;
            d = (double) (f4 / f5);
        } else if (this.f7130a < f3) {
            this.f7130a = f3;
            d = (double) (f3 / f5);
        }
        this.f7131b.postScale((float) d, (float) d, f, f2);
        m11321e();
    }

    private PointF m11301a(float f, float f2, boolean z) {
        this.f7131b.getValues(this.f7138i);
        float intrinsicWidth = (float) getDrawable().getIntrinsicWidth();
        float intrinsicHeight = (float) getDrawable().getIntrinsicHeight();
        float f3 = this.f7138i[2];
        float imageWidth = ((f - f3) * intrinsicWidth) / getImageWidth();
        f3 = ((f2 - this.f7138i[5]) * intrinsicHeight) / getImageHeight();
        if (z) {
            imageWidth = Math.min(Math.max(imageWidth, 0.0f), intrinsicWidth);
            f3 = Math.min(Math.max(f3, 0.0f), intrinsicHeight);
        }
        return new PointF(imageWidth, f3);
    }

    private PointF m11300a(float f, float f2) {
        this.f7131b.getValues(this.f7138i);
        float intrinsicWidth = f / ((float) getDrawable().getIntrinsicWidth());
        float intrinsicHeight = f2 / ((float) getDrawable().getIntrinsicHeight());
        return new PointF((intrinsicWidth * getImageWidth()) + this.f7138i[2], (intrinsicHeight * getImageHeight()) + this.f7138i[5]);
    }

    @TargetApi(16)
    private void m11312a(Runnable runnable) {
        if (VERSION.SDK_INT >= 16) {
            postOnAnimation(runnable);
        } else {
            postDelayed(runnable, 16);
        }
    }
}
