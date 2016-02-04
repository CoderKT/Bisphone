package me.grantland.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.text.Editable;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.SingleLineTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnLayoutChangeListener;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;

public class AutofitHelper {
    private TextView f8458a;
    private TextPaint f8459b;
    private float f8460c;
    private int f8461d;
    private float f8462e;
    private float f8463f;
    private float f8464g;
    private boolean f8465h;
    private boolean f8466i;
    private ArrayList<OnTextSizeChangeListener> f8467j;
    private TextWatcher f8468k;
    private OnLayoutChangeListener f8469l;

    class AutofitOnLayoutChangeListener implements OnLayoutChangeListener {
        final /* synthetic */ AutofitHelper f8456a;

        private AutofitOnLayoutChangeListener(AutofitHelper autofitHelper) {
            this.f8456a = autofitHelper;
        }

        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            this.f8456a.m13311d();
        }
    }

    class AutofitTextWatcher implements TextWatcher {
        final /* synthetic */ AutofitHelper f8457a;

        private AutofitTextWatcher(AutofitHelper autofitHelper) {
            this.f8457a = autofitHelper;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            this.f8457a.m13311d();
        }

        public void afterTextChanged(Editable editable) {
        }
    }

    public interface OnTextSizeChangeListener {
        void m13302a(float f, float f2);
    }

    public static AutofitHelper m13306a(TextView textView, AttributeSet attributeSet, int i) {
        AutofitHelper autofitHelper = new AutofitHelper(textView);
        boolean z = true;
        if (attributeSet != null) {
            Context context = textView.getContext();
            int b = (int) autofitHelper.m13320b();
            float a = autofitHelper.m13314a();
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0977R.styleable.AutofitTextView, i, 0);
            z = obtainStyledAttributes.getBoolean(C0977R.styleable.AutofitTextView_sizeToFit, true);
            b = obtainStyledAttributes.getDimensionPixelSize(C0977R.styleable.AutofitTextView_minTextSize, b);
            a = obtainStyledAttributes.getFloat(C0977R.styleable.AutofitTextView_precision, a);
            obtainStyledAttributes.recycle();
            autofitHelper.m13317a(0, (float) b).m13315a(a);
        }
        autofitHelper.m13319a(z);
        return autofitHelper;
    }

    private static void m13308a(TextView textView, TextPaint textPaint, float f, float f2, int i, float f3) {
        if (i > 0 && i != Integer.MAX_VALUE) {
            int width = (textView.getWidth() - textView.getPaddingLeft()) - textView.getPaddingRight();
            if (width > 0) {
                float f4;
                CharSequence text = textView.getText();
                TransformationMethod transformationMethod = textView.getTransformationMethod();
                if (transformationMethod != null) {
                    text = transformationMethod.getTransformation(text, textView);
                }
                Context context = textView.getContext();
                Resources system = Resources.getSystem();
                if (context != null) {
                    system = context.getResources();
                }
                DisplayMetrics displayMetrics = system.getDisplayMetrics();
                textPaint.set(textView.getPaint());
                textPaint.setTextSize(f2);
                if ((i != 1 || textPaint.measureText(text, 0, text.length()) <= ((float) width)) && m13305a(text, textPaint, f2, (float) width, displayMetrics) <= i) {
                    f4 = f2;
                } else {
                    f4 = m13303a(text, textPaint, (float) width, i, 0.0f, f2, f3, displayMetrics);
                }
                if (f4 >= f) {
                    f = f4;
                }
                textView.setTextSize(0, f);
            }
        }
    }

    private static float m13303a(CharSequence charSequence, TextPaint textPaint, float f, int i, float f2, float f3, float f4, DisplayMetrics displayMetrics) {
        StaticLayout staticLayout;
        int lineCount;
        float f5 = (f2 + f3) / 2.0f;
        textPaint.setTextSize(TypedValue.applyDimension(0, f5, displayMetrics));
        if (i != 1) {
            StaticLayout staticLayout2 = new StaticLayout(charSequence, textPaint, (int) f, Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
            staticLayout = staticLayout2;
            lineCount = staticLayout2.getLineCount();
        } else {
            staticLayout = null;
            lineCount = 1;
        }
        if (lineCount > i) {
            if (f3 - f2 < f4) {
                return f2;
            }
            return m13303a(charSequence, textPaint, f, i, f2, f5, f4, displayMetrics);
        } else if (lineCount < i) {
            return m13303a(charSequence, textPaint, f, i, f5, f3, f4, displayMetrics);
        } else {
            float measureText;
            if (i == 1) {
                measureText = textPaint.measureText(charSequence, 0, charSequence.length());
            } else {
                measureText = 0.0f;
                for (int i2 = 0; i2 < lineCount; i2++) {
                    if (staticLayout.getLineWidth(i2) > measureText) {
                        measureText = staticLayout.getLineWidth(i2);
                    }
                }
            }
            if (f3 - f2 < f4) {
                return f2;
            }
            if (measureText > f) {
                return m13303a(charSequence, textPaint, f, i, f2, f5, f4, displayMetrics);
            }
            return measureText < f ? m13303a(charSequence, textPaint, f, i, f5, f3, f4, displayMetrics) : f5;
        }
    }

    private static int m13305a(CharSequence charSequence, TextPaint textPaint, float f, float f2, DisplayMetrics displayMetrics) {
        textPaint.setTextSize(TypedValue.applyDimension(0, f, displayMetrics));
        return new StaticLayout(charSequence, textPaint, (int) f2, Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true).getLineCount();
    }

    private static int m13304a(TextView textView) {
        TransformationMethod transformationMethod = textView.getTransformationMethod();
        if (transformationMethod != null && (transformationMethod instanceof SingleLineTransformationMethod)) {
            return 1;
        }
        if (VERSION.SDK_INT >= 16) {
            return textView.getMaxLines();
        }
        return -1;
    }

    private AutofitHelper(TextView textView) {
        this.f8468k = new AutofitTextWatcher();
        this.f8469l = new AutofitOnLayoutChangeListener();
        float f = textView.getContext().getResources().getDisplayMetrics().scaledDensity;
        this.f8458a = textView;
        this.f8459b = new TextPaint();
        m13313e(textView.getTextSize());
        this.f8461d = m13304a(textView);
        this.f8462e = f * 8.0f;
        this.f8463f = this.f8460c;
        this.f8464g = 0.5f;
    }

    public AutofitHelper m13318a(OnTextSizeChangeListener onTextSizeChangeListener) {
        if (this.f8467j == null) {
            this.f8467j = new ArrayList();
        }
        this.f8467j.add(onTextSizeChangeListener);
        return this;
    }

    public float m13314a() {
        return this.f8464g;
    }

    public AutofitHelper m13315a(float f) {
        if (this.f8464g != f) {
            this.f8464g = f;
            m13311d();
        }
        return this;
    }

    public float m13320b() {
        return this.f8462e;
    }

    public AutofitHelper m13317a(int i, float f) {
        Context context = this.f8458a.getContext();
        Resources system = Resources.getSystem();
        if (context != null) {
            system = context.getResources();
        }
        m13310c(TypedValue.applyDimension(i, f, system.getDisplayMetrics()));
        return this;
    }

    private void m13310c(float f) {
        if (f != this.f8462e) {
            this.f8462e = f;
            m13311d();
        }
    }

    public float m13323c() {
        return this.f8463f;
    }

    public AutofitHelper m13321b(float f) {
        return m13322b(2, f);
    }

    public AutofitHelper m13322b(int i, float f) {
        Context context = this.f8458a.getContext();
        Resources system = Resources.getSystem();
        if (context != null) {
            system = context.getResources();
        }
        m13312d(TypedValue.applyDimension(i, f, system.getDisplayMetrics()));
        return this;
    }

    private void m13312d(float f) {
        if (f != this.f8463f) {
            this.f8463f = f;
            m13311d();
        }
    }

    public AutofitHelper m13316a(int i) {
        if (this.f8461d != i) {
            this.f8461d = i;
            m13311d();
        }
        return this;
    }

    public AutofitHelper m13319a(boolean z) {
        if (this.f8465h != z) {
            this.f8465h = z;
            if (z) {
                this.f8458a.addTextChangedListener(this.f8468k);
                this.f8458a.addOnLayoutChangeListener(this.f8469l);
                m13311d();
            } else {
                this.f8458a.removeTextChangedListener(this.f8468k);
                this.f8458a.removeOnLayoutChangeListener(this.f8469l);
                this.f8458a.setTextSize(0, this.f8460c);
            }
        }
        return this;
    }

    public void m13324c(int i, float f) {
        if (!this.f8466i) {
            Context context = this.f8458a.getContext();
            Resources system = Resources.getSystem();
            if (context != null) {
                system = context.getResources();
            }
            m13313e(TypedValue.applyDimension(i, f, system.getDisplayMetrics()));
        }
    }

    private void m13313e(float f) {
        if (this.f8460c != f) {
            this.f8460c = f;
        }
    }

    private void m13311d() {
        float textSize = this.f8458a.getTextSize();
        this.f8466i = true;
        m13308a(this.f8458a, this.f8459b, this.f8462e, this.f8463f, this.f8461d, this.f8464g);
        this.f8466i = false;
        float textSize2 = this.f8458a.getTextSize();
        if (textSize2 != textSize) {
            m13307a(textSize2, textSize);
        }
    }

    private void m13307a(float f, float f2) {
        if (this.f8467j != null) {
            Iterator it = this.f8467j.iterator();
            while (it.hasNext()) {
                ((OnTextSizeChangeListener) it.next()).m13302a(f, f2);
            }
        }
    }
}
