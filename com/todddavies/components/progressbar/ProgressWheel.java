package com.todddavies.components.progressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import app.C0110R;

public class ProgressWheel extends View {
    private RectF f7163A;
    private RectF f7164B;
    private RectF f7165C;
    private int f7166D;
    private int f7167E;
    private Handler f7168F;
    private String f7169G;
    private String[] f7170H;
    int f7171a;
    boolean f7172b;
    private int f7173c;
    private int f7174d;
    private int f7175e;
    private int f7176f;
    private int f7177g;
    private int f7178h;
    private int f7179i;
    private int f7180j;
    private float f7181k;
    private int f7182l;
    private int f7183m;
    private int f7184n;
    private int f7185o;
    private int f7186p;
    private int f7187q;
    private int f7188r;
    private int f7189s;
    private int f7190t;
    private Paint f7191u;
    private Paint f7192v;
    private Paint f7193w;
    private Paint f7194x;
    private Paint f7195y;
    private RectF f7196z;

    /* renamed from: com.todddavies.components.progressbar.ProgressWheel.1 */
    class C09291 extends Handler {
        final /* synthetic */ ProgressWheel f7162a;

        C09291(ProgressWheel progressWheel) {
            this.f7162a = progressWheel;
        }

        public void handleMessage(Message message) {
            this.f7162a.invalidate();
            if (this.f7162a.f7172b) {
                ProgressWheel progressWheel = this.f7162a;
                progressWheel.f7171a += this.f7162a.f7166D;
                if (this.f7162a.f7171a > 360) {
                    this.f7162a.f7171a = 0;
                }
                this.f7162a.f7168F.sendEmptyMessageDelayed(0, (long) this.f7162a.f7167E);
            }
        }
    }

    public ProgressWheel(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f7173c = 0;
        this.f7174d = 0;
        this.f7175e = 100;
        this.f7176f = 80;
        this.f7177g = 60;
        this.f7178h = 20;
        this.f7179i = 20;
        this.f7180j = 20;
        this.f7181k = 0.0f;
        this.f7182l = 5;
        this.f7183m = 5;
        this.f7184n = 5;
        this.f7185o = 5;
        this.f7186p = -1442840576;
        this.f7187q = -1442840576;
        this.f7188r = 0;
        this.f7189s = -1428300323;
        this.f7190t = -16777216;
        this.f7191u = new Paint();
        this.f7192v = new Paint();
        this.f7193w = new Paint();
        this.f7194x = new Paint();
        this.f7195y = new Paint();
        this.f7196z = new RectF();
        this.f7163A = new RectF();
        this.f7164B = new RectF();
        this.f7165C = new RectF();
        this.f7166D = 2;
        this.f7167E = 0;
        this.f7168F = new C09291(this);
        this.f7171a = 0;
        this.f7172b = false;
        this.f7169G = "";
        this.f7170H = new String[0];
        m11353a(context.obtainStyledAttributes(attributeSet, C0110R.styleable.ProgressWheel));
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.f7174d = i;
        this.f7173c = i2;
        m11357d();
        m11356c();
        invalidate();
    }

    private void m11356c() {
        this.f7191u.setColor(this.f7186p);
        this.f7191u.setAntiAlias(true);
        this.f7191u.setStyle(Style.STROKE);
        this.f7191u.setStrokeWidth((float) this.f7178h);
        this.f7193w.setColor(this.f7189s);
        this.f7193w.setAntiAlias(true);
        this.f7193w.setStyle(Style.STROKE);
        this.f7193w.setStrokeWidth((float) this.f7179i);
        this.f7192v.setColor(this.f7188r);
        this.f7192v.setAntiAlias(true);
        this.f7192v.setStyle(Style.FILL);
        this.f7194x.setColor(this.f7190t);
        this.f7194x.setStyle(Style.FILL);
        this.f7194x.setAntiAlias(true);
        this.f7194x.setTextSize((float) this.f7180j);
        this.f7195y.setColor(this.f7187q);
        this.f7195y.setAntiAlias(true);
        this.f7195y.setStyle(Style.STROKE);
        this.f7195y.setStrokeWidth(this.f7181k);
    }

    private void m11357d() {
        int min = Math.min(this.f7174d, this.f7173c);
        int i = this.f7174d - min;
        min = this.f7173c - min;
        this.f7182l = getPaddingTop() + (min / 2);
        this.f7183m = (min / 2) + getPaddingBottom();
        this.f7184n = getPaddingLeft() + (i / 2);
        this.f7185o = getPaddingRight() + (i / 2);
        this.f7196z = new RectF((float) this.f7184n, (float) this.f7182l, (float) (getLayoutParams().width - this.f7185o), (float) (getLayoutParams().height - this.f7183m));
        this.f7163A = new RectF((float) (this.f7184n + this.f7178h), (float) (this.f7182l + this.f7178h), (float) ((getLayoutParams().width - this.f7185o) - this.f7178h), (float) ((getLayoutParams().height - this.f7183m) - this.f7178h));
        this.f7165C = new RectF((this.f7163A.left + (((float) this.f7179i) / 2.0f)) + (this.f7181k / 2.0f), (this.f7163A.top + (((float) this.f7179i) / 2.0f)) + (this.f7181k / 2.0f), (this.f7163A.right - (((float) this.f7179i) / 2.0f)) - (this.f7181k / 2.0f), (this.f7163A.bottom - (((float) this.f7179i) / 2.0f)) - (this.f7181k / 2.0f));
        this.f7164B = new RectF((this.f7163A.left - (((float) this.f7179i) / 2.0f)) - (this.f7181k / 2.0f), (this.f7163A.top - (((float) this.f7179i) / 2.0f)) - (this.f7181k / 2.0f), (this.f7163A.right + (((float) this.f7179i) / 2.0f)) + (this.f7181k / 2.0f), (this.f7163A.bottom + (((float) this.f7179i) / 2.0f)) + (this.f7181k / 2.0f));
        this.f7175e = ((getLayoutParams().width - this.f7185o) - this.f7178h) / 2;
        this.f7176f = (this.f7175e - this.f7178h) + 1;
    }

    private void m11353a(TypedArray typedArray) {
        this.f7178h = (int) typedArray.getDimension(10, (float) this.f7178h);
        this.f7179i = (int) typedArray.getDimension(5, (float) this.f7179i);
        this.f7166D = (int) typedArray.getDimension(6, (float) this.f7166D);
        this.f7167E = typedArray.getInteger(7, this.f7167E);
        if (this.f7167E < 0) {
            this.f7167E = 0;
        }
        this.f7186p = typedArray.getColor(3, this.f7186p);
        this.f7177g = (int) typedArray.getDimension(11, (float) this.f7177g);
        this.f7180j = (int) typedArray.getDimension(2, (float) this.f7180j);
        this.f7190t = typedArray.getColor(1, this.f7190t);
        if (typedArray.hasValue(0)) {
            setText(typedArray.getString(0));
        }
        this.f7189s = typedArray.getColor(4, this.f7189s);
        this.f7188r = typedArray.getColor(8, this.f7188r);
        this.f7187q = typedArray.getColor(12, this.f7187q);
        this.f7181k = typedArray.getDimension(13, this.f7181k);
        typedArray.recycle();
    }

    protected void onDraw(Canvas canvas) {
        int i = 0;
        super.onDraw(canvas);
        canvas.drawArc(this.f7163A, 360.0f, 360.0f, false, this.f7193w);
        canvas.drawArc(this.f7164B, 360.0f, 360.0f, false, this.f7195y);
        canvas.drawArc(this.f7165C, 360.0f, 360.0f, false, this.f7195y);
        if (this.f7172b) {
            canvas.drawArc(this.f7163A, (float) (this.f7171a - 90), (float) this.f7177g, false, this.f7191u);
        } else {
            canvas.drawArc(this.f7163A, -90.0f, (float) this.f7171a, false, this.f7191u);
        }
        canvas.drawCircle(((this.f7163A.width() / 2.0f) + ((float) this.f7179i)) + ((float) this.f7184n), ((this.f7163A.height() / 2.0f) + ((float) this.f7179i)) + ((float) this.f7182l), (float) this.f7176f, this.f7192v);
        float descent = ((this.f7194x.descent() - this.f7194x.ascent()) / 2.0f) - this.f7194x.descent();
        String[] strArr = this.f7170H;
        int length = strArr.length;
        while (i < length) {
            String str = strArr[i];
            canvas.drawText(str, ((float) (getWidth() / 2)) - (this.f7194x.measureText(str) / 2.0f), ((float) (getHeight() / 2)) + descent, this.f7194x);
            i++;
        }
    }

    public void m11358a() {
        this.f7172b = false;
        this.f7171a = 0;
        this.f7168F.removeMessages(0);
    }

    public void m11359b() {
        this.f7172b = true;
        this.f7168F.sendEmptyMessage(0);
    }

    public void setProgress(float f) {
        float f2 = 360.0f * f;
        this.f7172b = false;
        this.f7171a = (int) f2;
        this.f7168F.sendEmptyMessage(0);
    }

    public void setText(String str) {
        this.f7169G = str;
        this.f7170H = this.f7169G.split("\n");
    }

    public int getCircleRadius() {
        return this.f7176f;
    }

    public void setCircleRadius(int i) {
        this.f7176f = i;
    }

    public int getBarLength() {
        return this.f7177g;
    }

    public void setBarLength(int i) {
        this.f7177g = i;
    }

    public int getBarWidth() {
        return this.f7178h;
    }

    public void setBarWidth(int i) {
        this.f7178h = i;
    }

    public int getTextSize() {
        return this.f7180j;
    }

    public void setTextSize(int i) {
        this.f7180j = i;
    }

    public int getPaddingTop() {
        return this.f7182l;
    }

    public void setPaddingTop(int i) {
        this.f7182l = i;
    }

    public int getPaddingBottom() {
        return this.f7183m;
    }

    public void setPaddingBottom(int i) {
        this.f7183m = i;
    }

    public int getPaddingLeft() {
        return this.f7184n;
    }

    public void setPaddingLeft(int i) {
        this.f7184n = i;
    }

    public int getPaddingRight() {
        return this.f7185o;
    }

    public void setPaddingRight(int i) {
        this.f7185o = i;
    }

    public int getBarColor() {
        return this.f7186p;
    }

    public void setBarColor(int i) {
        this.f7186p = i;
    }

    public int getCircleColor() {
        return this.f7188r;
    }

    public void setCircleColor(int i) {
        this.f7188r = i;
    }

    public int getRimColor() {
        return this.f7189s;
    }

    public void setRimColor(int i) {
        this.f7189s = i;
    }

    public Shader getRimShader() {
        return this.f7193w.getShader();
    }

    public void setRimShader(Shader shader) {
        this.f7193w.setShader(shader);
    }

    public int getTextColor() {
        return this.f7190t;
    }

    public void setTextColor(int i) {
        this.f7190t = i;
    }

    public int getSpinSpeed() {
        return this.f7166D;
    }

    public void setSpinSpeed(int i) {
        this.f7166D = i;
    }

    public int getRimWidth() {
        return this.f7179i;
    }

    public void setRimWidth(int i) {
        this.f7179i = i;
    }

    public int getDelayMillis() {
        return this.f7167E;
    }

    public void setDelayMillis(int i) {
        this.f7167E = i;
    }
}
