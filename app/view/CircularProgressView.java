package app.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import app.Main;

public class CircularProgressView extends View {
    Runnable f4633a;
    private Bitmap f4634b;
    private Point f4635c;
    private Paint f4636d;
    private Paint f4637e;
    private Paint f4638f;
    private Rect f4639g;
    private RectF f4640h;
    private Handler f4641i;
    private int f4642j;
    private int f4643k;
    private int f4644l;
    private int f4645m;
    private int f4646n;
    private int f4647o;
    private float f4648p;
    private boolean f4649q;
    private ProgressChangeListener f4650r;

    public interface ProgressChangeListener {
        void m6157a();
    }

    /* renamed from: app.view.CircularProgressView.1 */
    class C05091 implements Runnable {
        final /* synthetic */ CircularProgressView f4632a;

        C05091(CircularProgressView circularProgressView) {
            this.f4632a = circularProgressView;
        }

        public void run() {
            if (this.f4632a.f4642j < 360) {
                this.f4632a.f4642j = this.f4632a.f4642j + 1;
                this.f4632a.invalidate();
                this.f4632a.f4641i.postDelayed(this.f4632a.f4633a, (long) this.f4632a.f4645m);
                return;
            }
            this.f4632a.f4650r.m6157a();
            this.f4632a.f4641i.removeCallbacksAndMessages(null);
            Main.f1926a.m5677a("handler shut down");
        }
    }

    public CircularProgressView(Context context) {
        super(context);
        this.f4642j = 0;
        this.f4643k = 0;
        this.f4645m = 83;
        this.f4646n = 0;
        this.f4647o = 0;
        this.f4649q = false;
        this.f4633a = new C05091(this);
    }

    public CircularProgressView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4642j = 0;
        this.f4643k = 0;
        this.f4645m = 83;
        this.f4646n = 0;
        this.f4647o = 0;
        this.f4649q = false;
        this.f4633a = new C05091(this);
        m7105d();
        this.f4641i = new Handler();
    }

    public CircularProgressView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f4642j = 0;
        this.f4643k = 0;
        this.f4645m = 83;
        this.f4646n = 0;
        this.f4647o = 0;
        this.f4649q = false;
        this.f4633a = new C05091(this);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle((float) this.f4639g.centerX(), (float) this.f4639g.centerY(), (float) (this.f4639g.height() / 2), this.f4636d);
        canvas.drawArc(this.f4640h, 270.0f, (float) this.f4642j, true, this.f4638f);
        canvas.drawCircle((float) this.f4639g.centerX(), (float) this.f4639g.centerY(), this.f4648p, this.f4637e);
        canvas.drawBitmap(this.f4634b, (float) this.f4635c.x, (float) this.f4635c.y, this.f4637e);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        int min = (int) (((double) Math.min(i, i2)) * 0.55d);
        this.f4639g = new Rect((i - min) / 2, (i2 - min) / 2, i - ((i - min) / 2), i2 - ((i2 - min) / 2));
        this.f4640h = new RectF(this.f4639g);
        this.f4648p = (float) (((double) (this.f4639g.height() / 2)) * 0.92d);
        m7108f();
        m7109g();
        m7107e();
        invalidate();
        super.onSizeChanged(i, i2, i3, i4);
    }

    private void m7105d() {
        this.f4636d = new Paint();
        this.f4636d.setAntiAlias(true);
        this.f4636d.setStyle(Style.FILL_AND_STROKE);
        this.f4636d.setColor(-1);
        this.f4637e = new Paint();
        this.f4637e.setAntiAlias(true);
        this.f4637e.setStyle(Style.FILL_AND_STROKE);
        this.f4637e.setColor(this.f4644l);
        this.f4638f = new Paint();
        this.f4638f.setAntiAlias(true);
        this.f4638f.setStyle(Style.FILL_AND_STROKE);
        this.f4638f.setColor(getResources().getColor(2131689529));
    }

    private void m7107e() {
        this.f4635c = new Point();
        this.f4635c.x = this.f4639g.centerX() - (this.f4634b.getWidth() / 2);
        this.f4635c.y = this.f4639g.centerY() - (this.f4634b.getHeight() / 2);
    }

    private void m7108f() {
        this.f4647o = (int) (((double) (this.f4648p * 2.0f)) * 0.45d);
        this.f4646n = (int) (((float) this.f4634b.getWidth()) / (((float) this.f4634b.getHeight()) / ((float) this.f4647o)));
    }

    private void m7109g() {
        if (this.f4646n > 0 && this.f4647o > 0) {
            this.f4634b = Bitmap.createScaledBitmap(this.f4634b, this.f4646n, this.f4647o, true);
        }
    }

    public void setInnerCircleColor(int i) {
        this.f4644l = i;
        m7105d();
        invalidate();
    }

    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        Main.f1926a.m5683d("visibility cahnge " + i);
    }

    public void m7110a() {
        this.f4641i.removeCallbacksAndMessages(null);
        this.f4642j = 0;
        this.f4644l = getResources().getColor(2131689536);
        requestLayout();
        setBitmap(2130837778);
        m7105d();
        invalidate();
    }

    public void m7112b() {
        if (!this.f4649q) {
            this.f4649q = true;
            this.f4641i.post(this.f4633a);
        }
    }

    public void m7113c() {
        this.f4649q = false;
        invalidate();
        this.f4641i.removeCallbacksAndMessages(null);
        this.f4643k = this.f4642j / 20;
    }

    public void setBitmap(int i) {
        this.f4634b = BitmapFactory.decodeResource(getResources(), i);
        m7109g();
        invalidate();
    }

    public boolean m7111a(int i, int i2) {
        return this.f4639g.contains(i, i2);
    }

    public int getRecordTime() {
        return this.f4643k;
    }

    public void setProgressChangeListener(ProgressChangeListener progressChangeListener) {
        this.f4650r = progressChangeListener;
    }
}
