package parallaxedScrollView;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import app.C0110R;
import java.util.ArrayList;
import java.util.Iterator;

public class ParallaxScrollView extends ScrollView {
    private int f8641a;
    private float f8642b;
    private float f8643c;
    private float f8644d;
    private ArrayList<ParallaxedView> f8645e;

    public class ScrollViewParallaxedItem extends ParallaxedView {
        final /* synthetic */ ParallaxScrollView f8640a;

        public ScrollViewParallaxedItem(ParallaxScrollView parallaxScrollView, View view) {
            this.f8640a = parallaxScrollView;
            super(view);
        }

        protected void m13459a(View view, float f) {
            view.offsetTopAndBottom(((int) f) - this.d);
            this.d = (int) f;
        }
    }

    public ParallaxScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f8641a = 1;
        this.f8642b = 1.9f;
        this.f8643c = 1.9f;
        this.f8644d = -1.0f;
        this.f8645e = new ArrayList();
        m13461a(context, attributeSet);
    }

    public ParallaxScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f8641a = 1;
        this.f8642b = 1.9f;
        this.f8643c = 1.9f;
        this.f8644d = -1.0f;
        this.f8645e = new ArrayList();
        m13461a(context, attributeSet);
    }

    public ParallaxScrollView(Context context) {
        super(context);
        this.f8641a = 1;
        this.f8642b = 1.9f;
        this.f8643c = 1.9f;
        this.f8644d = -1.0f;
        this.f8645e = new ArrayList();
    }

    protected void m13461a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0110R.styleable.ParallaxScroll);
        this.f8643c = obtainStyledAttributes.getFloat(0, 1.9f);
        this.f8644d = obtainStyledAttributes.getFloat(1, -1.0f);
        this.f8642b = obtainStyledAttributes.getFloat(2, 1.9f);
        this.f8641a = obtainStyledAttributes.getInt(3, 1);
        obtainStyledAttributes.recycle();
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        m13460a();
    }

    private void m13460a() {
        int i = 0;
        if (getChildCount() > 0 && (getChildAt(0) instanceof ViewGroup)) {
            ViewGroup viewGroup = (ViewGroup) getChildAt(0);
            int min = Math.min(this.f8641a, viewGroup.getChildCount());
            while (i < min) {
                this.f8645e.add(new ScrollViewParallaxedItem(this, viewGroup.getChildAt(i)));
                i++;
            }
        }
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        float f = this.f8643c;
        float f2 = this.f8644d;
        Iterator it = this.f8645e.iterator();
        float f3 = f;
        f = f2;
        while (it.hasNext()) {
            ParallaxedView parallaxedView = (ParallaxedView) it.next();
            parallaxedView.m13454a(((float) i2) / f3);
            f3 *= this.f8642b;
            if (f != -1.0f) {
                parallaxedView.m13457b(i2 <= 0 ? 1.0f : 100.0f / (((float) i2) * f));
                f /= this.f8644d;
            }
            parallaxedView.m13453a();
        }
    }
}
