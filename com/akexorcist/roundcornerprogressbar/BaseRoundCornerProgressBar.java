package com.akexorcist.roundcornerprogressbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public abstract class BaseRoundCornerProgressBar extends LinearLayout {
    protected static final int f5263a;
    protected static final int f5264b;
    protected static final int f5265c;
    protected LinearLayout f5266d;
    protected LinearLayout f5267e;
    protected LinearLayout f5268f;
    protected float f5269g;
    protected float f5270h;
    protected float f5271i;
    private int f5272j;
    private int f5273k;
    private int f5274l;
    private int f5275m;
    private int f5276n;
    private int f5277o;
    private int f5278p;
    private boolean f5279q;
    private boolean f5280r;
    private boolean f5281s;
    private boolean f5282t;
    private boolean f5283u;
    private boolean f5284v;
    private boolean f5285w;

    /* renamed from: com.akexorcist.roundcornerprogressbar.BaseRoundCornerProgressBar.1 */
    class C05871 implements OnGlobalLayoutListener {
        final /* synthetic */ BaseRoundCornerProgressBar f5242a;

        C05871(BaseRoundCornerProgressBar baseRoundCornerProgressBar) {
            this.f5242a = baseRoundCornerProgressBar;
        }

        public void onGlobalLayout() {
            if (VERSION.SDK_INT >= 16) {
                this.f5242a.f5266d.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            } else {
                this.f5242a.f5266d.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
            this.f5242a.m7756b();
            this.f5242a.m7759d();
        }
    }

    class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR;
        float f5243a;
        float f5244b;
        float f5245c;
        int f5246d;
        int f5247e;
        int f5248f;
        int f5249g;
        int f5250h;
        int f5251i;
        int f5252j;
        int f5253k;
        int f5254l;
        int f5255m;
        boolean f5256n;
        boolean f5257o;
        boolean f5258p;
        boolean f5259q;
        boolean f5260r;
        boolean f5261s;
        boolean f5262t;

        /* renamed from: com.akexorcist.roundcornerprogressbar.BaseRoundCornerProgressBar.SavedState.1 */
        final class C05881 implements Creator<SavedState> {
            C05881() {
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return m7746a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return m7747a(i);
            }

            public SavedState m7746a(Parcel parcel) {
                return new SavedState(null);
            }

            public SavedState[] m7747a(int i) {
                return new SavedState[i];
            }
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            boolean z;
            boolean z2 = true;
            super(parcel);
            this.f5243a = parcel.readFloat();
            this.f5244b = parcel.readFloat();
            this.f5245c = parcel.readFloat();
            this.f5246d = parcel.readInt();
            this.f5247e = parcel.readInt();
            this.f5248f = parcel.readInt();
            this.f5249g = parcel.readInt();
            this.f5250h = parcel.readInt();
            this.f5251i = parcel.readInt();
            this.f5252j = parcel.readInt();
            this.f5253k = parcel.readInt();
            this.f5254l = parcel.readInt();
            this.f5255m = parcel.readInt();
            this.f5256n = parcel.readByte() != null;
            if (parcel.readByte() != null) {
                z = true;
            } else {
                z = false;
            }
            this.f5257o = z;
            if (parcel.readByte() != null) {
                z = true;
            } else {
                z = false;
            }
            this.f5258p = z;
            if (parcel.readByte() != null) {
                z = true;
            } else {
                z = false;
            }
            this.f5259q = z;
            if (parcel.readByte() != null) {
                z = true;
            } else {
                z = false;
            }
            this.f5260r = z;
            if (parcel.readByte() != null) {
                z = true;
            } else {
                z = false;
            }
            this.f5261s = z;
            if (parcel.readByte() == null) {
                z2 = false;
            }
            this.f5262t = z2;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int i2;
            int i3 = 1;
            super.writeToParcel(parcel, i);
            parcel.writeFloat(this.f5243a);
            parcel.writeFloat(this.f5244b);
            parcel.writeFloat(this.f5245c);
            parcel.writeInt(this.f5246d);
            parcel.writeInt(this.f5247e);
            parcel.writeInt(this.f5248f);
            parcel.writeInt(this.f5249g);
            parcel.writeInt(this.f5250h);
            parcel.writeInt(this.f5251i);
            parcel.writeInt(this.f5252j);
            parcel.writeByte((byte) (this.f5256n ? 1 : 0));
            if (this.f5257o) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            parcel.writeByte((byte) i2);
            if (this.f5258p) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            parcel.writeByte((byte) i2);
            if (this.f5259q) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            parcel.writeByte((byte) i2);
            if (this.f5260r) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            parcel.writeByte((byte) i2);
            if (this.f5261s) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            parcel.writeByte((byte) i2);
            if (!this.f5262t) {
                i3 = 0;
            }
            parcel.writeByte((byte) i3);
        }

        static {
            CREATOR = new C05881();
        }
    }

    protected abstract float m7750a(float f);

    protected abstract int m7751a();

    protected abstract void m7754a(TypedArray typedArray, DisplayMetrics displayMetrics);

    protected abstract float m7755b(float f);

    protected abstract void m7758c();

    protected abstract void setBackgroundLayoutSize(LinearLayout linearLayout);

    protected abstract void setGradientRadius(GradientDrawable gradientDrawable);

    static {
        f5263a = Color.parseColor("#ff7f7f7f");
        f5264b = Color.parseColor("#7f7f7f7f");
        f5265c = Color.parseColor("#ff5f5f5f");
    }

    @SuppressLint({"NewApi"})
    public BaseRoundCornerProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (isInEditMode()) {
            setBackgroundColor(f5265c);
            setGravity(17);
            int c = (int) m7757c(10.0f);
            setPadding(c, c, c, c);
            View textView = new TextView(context);
            textView.setText(getClass().getSimpleName());
            addView(textView);
            return;
        }
        this.f5279q = false;
        this.f5280r = false;
        this.f5281s = false;
        this.f5282t = false;
        this.f5283u = false;
        this.f5284v = false;
        this.f5285w = false;
        this.f5278p = f5265c;
        this.f5272j = 0;
        this.f5273k = (int) m7757c(30.0f);
        this.f5269g = 100.0f;
        this.f5270h = 0.0f;
        this.f5271i = 0.0f;
        this.f5274l = 10;
        this.f5275m = 5;
        this.f5276n = f5263a;
        this.f5277o = f5264b;
        this.f5278p = f5265c;
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(m7751a(), this);
        m7753a(context, attributeSet);
    }

    protected void m7753a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0589R.styleable.RoundCornerProgress);
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        this.f5274l = (int) TypedValue.applyDimension(1, (float) this.f5274l, displayMetrics);
        this.f5274l = (int) obtainStyledAttributes.getDimension(C0589R.styleable.RoundCornerProgress_rcBackgroundRadius, 10.0f);
        this.f5275m = (int) TypedValue.applyDimension(1, (float) this.f5275m, displayMetrics);
        this.f5275m = (int) obtainStyledAttributes.getDimension(C0589R.styleable.RoundCornerProgress_rcBackgroundPadding, 5.0f);
        this.f5266d = (LinearLayout) findViewById(C0589R.id.round_corner_progress_background);
        this.f5266d.setPadding(this.f5275m, this.f5275m, this.f5275m, this.f5275m);
        if (!this.f5282t) {
            setBackgroundLayoutColor(obtainStyledAttributes.getColor(C0589R.styleable.RoundCornerProgress_rcBackgroundColor, f5265c));
        }
        this.f5267e = (LinearLayout) findViewById(C0589R.id.round_corner_progress_progress);
        this.f5268f = (LinearLayout) findViewById(C0589R.id.round_corner_progress_secondary_progress);
        if (!this.f5283u) {
            m7752a(obtainStyledAttributes.getColor(C0589R.styleable.RoundCornerProgress_rcProgressColor, f5263a), obtainStyledAttributes.getColor(C0589R.styleable.RoundCornerProgress_rcSecondaryProgressColor, f5264b));
        }
        if (!this.f5281s) {
            this.f5269g = obtainStyledAttributes.getFloat(C0589R.styleable.RoundCornerProgress_rcMax, 100.0f);
        }
        if (!this.f5280r) {
            this.f5270h = obtainStyledAttributes.getFloat(C0589R.styleable.RoundCornerProgress_rcProgress, 0.0f);
            this.f5271i = obtainStyledAttributes.getFloat(C0589R.styleable.RoundCornerProgress_rcSecondaryProgress, 0.0f);
        }
        m7754a(obtainStyledAttributes, displayMetrics);
        obtainStyledAttributes.recycle();
    }

    private void m7748a(ViewGroup viewGroup, int i) {
        Drawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(i);
        setGradientRadius(gradientDrawable);
        if (VERSION.SDK_INT >= 16) {
            viewGroup.setBackground(gradientDrawable);
        } else {
            viewGroup.setBackgroundDrawable(gradientDrawable);
        }
    }

    public void setProgressColor(int i) {
        this.f5276n = i;
        m7748a(this.f5267e, i);
        if (!this.f5279q) {
            this.f5283u = true;
        }
    }

    public void m7752a(int i, int i2) {
        this.f5276n = i;
        this.f5277o = i2;
        m7748a(this.f5267e, i);
        m7748a(this.f5268f, i2);
        if (!this.f5279q) {
            this.f5283u = true;
        }
    }

    @SuppressLint({"NewApi"})
    public void setBackgroundLayoutColor(int i) {
        this.f5278p = i;
        Drawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(this.f5278p);
        gradientDrawable.setCornerRadius((float) this.f5274l);
        if (VERSION.SDK_INT >= 16) {
            this.f5266d.setBackground(gradientDrawable);
        } else {
            this.f5266d.setBackgroundDrawable(gradientDrawable);
        }
        if (!this.f5279q) {
            this.f5282t = true;
        }
    }

    public int getBackgroundLayoutColor() {
        return this.f5278p;
    }

    public int getProgressColor() {
        return this.f5276n;
    }

    public int getSecondaryProgressColor() {
        return this.f5277o;
    }

    protected void m7756b() {
        setProgress(this.f5270h);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setBackgroundLayoutSize(this.f5266d);
        m7758c();
        this.f5284v = true;
        this.f5279q = true;
        m7756b();
        m7759d();
    }

    public void setProgress(float f) {
        float f2 = 0.0f;
        float f3 = f > this.f5269g ? this.f5269g : f;
        if (f3 < 0.0f) {
            f3 = 0.0f;
        }
        this.f5270h = f3;
        if (this.f5284v) {
            if (!this.f5285w) {
                LayoutParams layoutParams = this.f5266d.getLayoutParams();
                layoutParams.width = this.f5272j;
                layoutParams.height = -1;
                this.f5266d.setLayoutParams(layoutParams);
                this.f5285w = true;
            }
            if (f3 > 0.0f) {
                f2 = this.f5269g / f3;
            }
            int a = (int) m7750a(f2);
            LayoutParams layoutParams2 = this.f5267e.getLayoutParams();
            layoutParams2.width = a;
            layoutParams2.height = -1;
            this.f5267e.setLayoutParams(layoutParams2);
        }
        if (!this.f5279q) {
            this.f5280r = true;
        }
    }

    public void setSecondaryProgress(float f) {
        float f2 = 0.0f;
        float f3 = f > this.f5269g ? this.f5269g : f;
        if (f3 < 0.0f) {
            f3 = 0.0f;
        }
        this.f5271i = f3;
        if (this.f5284v) {
            if (!this.f5285w) {
                LayoutParams layoutParams = this.f5266d.getLayoutParams();
                layoutParams.width = this.f5272j;
                layoutParams.height = -1;
                this.f5266d.setLayoutParams(layoutParams);
                this.f5285w = true;
            }
            if (f3 > 0.0f) {
                f2 = this.f5269g / f3;
            }
            int b = (int) m7755b(f2);
            LayoutParams layoutParams2 = this.f5268f.getLayoutParams();
            layoutParams2.width = b;
            layoutParams2.height = -1;
            this.f5268f.setLayoutParams(layoutParams2);
        }
        if (!this.f5279q) {
            this.f5280r = true;
        }
    }

    protected void m7759d() {
        setSecondaryProgress(this.f5271i);
    }

    public float getMax() {
        return this.f5269g;
    }

    public void setMax(float f) {
        if (!this.f5279q) {
            this.f5281s = true;
        }
        this.f5269g = f;
        setProgress(this.f5270h);
    }

    public float getProgress() {
        return this.f5270h;
    }

    public float getSecondaryProgress() {
        return this.f5271i;
    }

    public int getBackgroundWidth() {
        return this.f5272j;
    }

    public void setBackgroundWidth(int i) {
        this.f5272j = i;
    }

    public int getBackgroundHeight() {
        return this.f5273k;
    }

    public void setBackgroundHeight(int i) {
        this.f5273k = i;
    }

    public int getRadius() {
        return this.f5274l;
    }

    public void setRadius(int i) {
        this.f5274l = i;
    }

    public int getPadding() {
        return this.f5275m;
    }

    public void setPadding(int i) {
        this.f5275m = i;
    }

    public void setSecondaryProgressColor(int i) {
        this.f5277o = i;
    }

    @SuppressLint({"NewApi"})
    protected float m7757c(float f) {
        return (float) Math.round(((float) (getContext().getResources().getDisplayMetrics().densityDpi / 160)) * f);
    }

    public void invalidate() {
        super.invalidate();
        m7749e();
    }

    private void m7749e() {
        if (getLayoutParams().width == -1 || getLayoutParams().width == -2) {
            this.f5266d.getViewTreeObserver().addOnGlobalLayoutListener(new C05871(this));
            LayoutParams layoutParams = this.f5266d.getLayoutParams();
            layoutParams.width = -1;
            this.f5266d.setLayoutParams(layoutParams);
        }
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.f5246d = this.f5272j;
        savedState.f5247e = this.f5273k;
        savedState.f5248f = this.f5274l;
        savedState.f5249g = this.f5275m;
        savedState.f5250h = this.f5276n;
        savedState.f5251i = this.f5277o;
        savedState.f5252j = this.f5278p;
        savedState.f5243a = this.f5269g;
        savedState.f5244b = this.f5270h;
        savedState.f5245c = this.f5271i;
        savedState.f5256n = this.f5279q;
        savedState.f5257o = this.f5280r;
        savedState.f5258p = this.f5281s;
        savedState.f5259q = this.f5282t;
        savedState.f5260r = this.f5283u;
        savedState.f5261s = this.f5284v;
        savedState.f5262t = this.f5285w;
        return savedState;
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            super.onRestoreInstanceState(savedState.getSuperState());
            this.f5272j = savedState.f5246d;
            this.f5273k = savedState.f5247e;
            this.f5274l = savedState.f5248f;
            this.f5275m = savedState.f5249g;
            this.f5276n = savedState.f5250h;
            this.f5277o = savedState.f5251i;
            this.f5278p = savedState.f5252j;
            m7752a(this.f5276n, this.f5277o);
            this.f5269g = savedState.f5243a;
            this.f5270h = savedState.f5244b;
            this.f5271i = savedState.f5245c;
            this.f5279q = savedState.f5256n;
            this.f5280r = savedState.f5257o;
            this.f5281s = savedState.f5258p;
            this.f5282t = savedState.f5259q;
            this.f5283u = savedState.f5260r;
            this.f5284v = savedState.f5261s;
            this.f5285w = savedState.f5262t;
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }
}
