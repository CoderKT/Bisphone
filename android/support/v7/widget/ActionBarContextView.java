package android.support.v7.widget;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.appcompat.C0057R;
import android.support.v7.view.ActionMode;
import android.support.v7.view.menu.MenuBuilder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ActionBarContextView extends AbsActionBarView {
    private CharSequence f1153g;
    private CharSequence f1154h;
    private View f1155i;
    private View f1156j;
    private LinearLayout f1157k;
    private TextView f1158l;
    private TextView f1159m;
    private int f1160n;
    private int f1161o;
    private boolean f1162p;
    private int f1163q;

    /* renamed from: android.support.v7.widget.ActionBarContextView.1 */
    class C00611 implements OnClickListener {
        final /* synthetic */ ActionMode f1151a;
        final /* synthetic */ ActionBarContextView f1152b;

        C00611(ActionBarContextView actionBarContextView, ActionMode actionMode) {
            this.f1152b = actionBarContextView;
            this.f1151a = actionMode;
        }

        public void onClick(View view) {
            this.f1151a.m2172c();
        }
    }

    public /* bridge */ /* synthetic */ ViewPropertyAnimatorCompat m2553a(int i, long j) {
        return super.m2548a(i, j);
    }

    public /* bridge */ /* synthetic */ int getAnimatedVisibility() {
        return super.getAnimatedVisibility();
    }

    public /* bridge */ /* synthetic */ int getContentHeight() {
        return super.getContentHeight();
    }

    public /* bridge */ /* synthetic */ boolean onHoverEvent(MotionEvent motionEvent) {
        return super.onHoverEvent(motionEvent);
    }

    public /* bridge */ /* synthetic */ boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    public /* bridge */ /* synthetic */ void setVisibility(int i) {
        super.setVisibility(i);
    }

    public ActionBarContextView(Context context) {
        this(context, null);
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0057R.attr.actionModeStyle);
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TintTypedArray a = TintTypedArray.m3759a(context, attributeSet, C0057R.styleable.ActionMode, i, 0);
        setBackgroundDrawable(a.m3762a(C0057R.styleable.ActionMode_background));
        this.f1160n = a.m3775g(C0057R.styleable.ActionMode_titleTextStyle, 0);
        this.f1161o = a.m3775g(C0057R.styleable.ActionMode_subtitleTextStyle, 0);
        this.e = a.m3774f(C0057R.styleable.ActionMode_height, 0);
        this.f1163q = a.m3775g(C0057R.styleable.ActionMode_closeItemLayout, C0057R.layout.abc_action_mode_close_item_material);
        a.m3763a();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.d != null) {
            this.d.m2636e();
            this.d.m2638g();
        }
    }

    public void setContentHeight(int i) {
        this.e = i;
    }

    public void setCustomView(View view) {
        if (this.f1156j != null) {
            removeView(this.f1156j);
        }
        this.f1156j = view;
        if (!(view == null || this.f1157k == null)) {
            removeView(this.f1157k);
            this.f1157k = null;
        }
        if (view != null) {
            addView(view);
        }
        requestLayout();
    }

    public void setTitle(CharSequence charSequence) {
        this.f1153g = charSequence;
        m2552e();
    }

    public void setSubtitle(CharSequence charSequence) {
        this.f1154h = charSequence;
        m2552e();
    }

    public CharSequence getTitle() {
        return this.f1153g;
    }

    public CharSequence getSubtitle() {
        return this.f1154h;
    }

    private void m2552e() {
        int i;
        int i2 = 8;
        Object obj = 1;
        if (this.f1157k == null) {
            LayoutInflater.from(getContext()).inflate(C0057R.layout.abc_action_bar_title_item, this);
            this.f1157k = (LinearLayout) getChildAt(getChildCount() - 1);
            this.f1158l = (TextView) this.f1157k.findViewById(C0057R.id.action_bar_title);
            this.f1159m = (TextView) this.f1157k.findViewById(C0057R.id.action_bar_subtitle);
            if (this.f1160n != 0) {
                this.f1158l.setTextAppearance(getContext(), this.f1160n);
            }
            if (this.f1161o != 0) {
                this.f1159m.setTextAppearance(getContext(), this.f1161o);
            }
        }
        this.f1158l.setText(this.f1153g);
        this.f1159m.setText(this.f1154h);
        Object obj2 = !TextUtils.isEmpty(this.f1153g) ? 1 : null;
        if (TextUtils.isEmpty(this.f1154h)) {
            obj = null;
        }
        TextView textView = this.f1159m;
        if (obj != null) {
            i = 0;
        } else {
            i = 8;
        }
        textView.setVisibility(i);
        LinearLayout linearLayout = this.f1157k;
        if (!(obj2 == null && obj == null)) {
            i2 = 0;
        }
        linearLayout.setVisibility(i2);
        if (this.f1157k.getParent() == null) {
            addView(this.f1157k);
        }
    }

    public void m2554a(ActionMode actionMode) {
        if (this.f1155i == null) {
            this.f1155i = LayoutInflater.from(getContext()).inflate(this.f1163q, this, false);
            addView(this.f1155i);
        } else if (this.f1155i.getParent() == null) {
            addView(this.f1155i);
        }
        this.f1155i.findViewById(C0057R.id.action_mode_close_button).setOnClickListener(new C00611(this, actionMode));
        MenuBuilder menuBuilder = (MenuBuilder) actionMode.m2169b();
        if (this.d != null) {
            this.d.m2637f();
        }
        this.d = new ActionMenuPresenter(getContext());
        this.d.m2633c(true);
        LayoutParams layoutParams = new LayoutParams(-2, -1);
        menuBuilder.m2421a(this.d, this.b);
        this.c = (ActionMenuView) this.d.m2618a((ViewGroup) this);
        this.c.setBackgroundDrawable(null);
        addView(this.c, layoutParams);
    }

    public void m2556b() {
        if (this.f1155i == null) {
            m2557c();
        }
    }

    public void m2557c() {
        removeAllViews();
        this.f1156j = null;
        this.c = null;
    }

    public boolean m2555a() {
        if (this.d != null) {
            return this.d.m2635d();
        }
        return false;
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(-1, -2);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new MarginLayoutParams(getContext(), attributeSet);
    }

    protected void onMeasure(int i, int i2) {
        int i3 = 1073741824;
        int i4 = 0;
        if (MeasureSpec.getMode(i) != 1073741824) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used " + "with android:layout_width=\"match_parent\" (or fill_parent)");
        } else if (MeasureSpec.getMode(i2) == 0) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used " + "with android:layout_height=\"wrap_content\"");
        } else {
            int a;
            int size = MeasureSpec.getSize(i);
            int size2 = this.e > 0 ? this.e : MeasureSpec.getSize(i2);
            int paddingTop = getPaddingTop() + getPaddingBottom();
            int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
            int i5 = size2 - paddingTop;
            int makeMeasureSpec = MeasureSpec.makeMeasureSpec(i5, Integer.MIN_VALUE);
            if (this.f1155i != null) {
                a = m2546a(this.f1155i, paddingLeft, makeMeasureSpec, 0);
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.f1155i.getLayoutParams();
                paddingLeft = a - (marginLayoutParams.rightMargin + marginLayoutParams.leftMargin);
            }
            if (this.c != null && this.c.getParent() == this) {
                paddingLeft = m2546a(this.c, paddingLeft, makeMeasureSpec, 0);
            }
            if (this.f1157k != null && this.f1156j == null) {
                if (this.f1162p) {
                    this.f1157k.measure(MeasureSpec.makeMeasureSpec(0, 0), makeMeasureSpec);
                    a = this.f1157k.getMeasuredWidth();
                    makeMeasureSpec = a <= paddingLeft ? 1 : 0;
                    if (makeMeasureSpec != 0) {
                        paddingLeft -= a;
                    }
                    this.f1157k.setVisibility(makeMeasureSpec != 0 ? 0 : 8);
                } else {
                    paddingLeft = m2546a(this.f1157k, paddingLeft, makeMeasureSpec, 0);
                }
            }
            if (this.f1156j != null) {
                int min;
                LayoutParams layoutParams = this.f1156j.getLayoutParams();
                if (layoutParams.width != -2) {
                    makeMeasureSpec = 1073741824;
                } else {
                    makeMeasureSpec = Integer.MIN_VALUE;
                }
                if (layoutParams.width >= 0) {
                    paddingLeft = Math.min(layoutParams.width, paddingLeft);
                }
                if (layoutParams.height == -2) {
                    i3 = Integer.MIN_VALUE;
                }
                if (layoutParams.height >= 0) {
                    min = Math.min(layoutParams.height, i5);
                } else {
                    min = i5;
                }
                this.f1156j.measure(MeasureSpec.makeMeasureSpec(paddingLeft, makeMeasureSpec), MeasureSpec.makeMeasureSpec(min, i3));
            }
            if (this.e <= 0) {
                makeMeasureSpec = getChildCount();
                size2 = 0;
                while (i4 < makeMeasureSpec) {
                    paddingLeft = getChildAt(i4).getMeasuredHeight() + paddingTop;
                    if (paddingLeft <= size2) {
                        paddingLeft = size2;
                    }
                    i4++;
                    size2 = paddingLeft;
                }
                setMeasuredDimension(size, size2);
                return;
            }
            setMeasuredDimension(size, size2);
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        boolean a = ViewUtils.m3901a(this);
        int paddingRight = a ? (i3 - i) - getPaddingRight() : getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingTop2 = ((i4 - i2) - getPaddingTop()) - getPaddingBottom();
        if (this.f1155i == null || this.f1155i.getVisibility() == 8) {
            i5 = paddingRight;
        } else {
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.f1155i.getLayoutParams();
            i5 = a ? marginLayoutParams.rightMargin : marginLayoutParams.leftMargin;
            int i6 = a ? marginLayoutParams.leftMargin : marginLayoutParams.rightMargin;
            i5 = AbsActionBarView.m2543a(paddingRight, i5, a);
            i5 = AbsActionBarView.m2543a(m2547a(this.f1155i, i5, paddingTop, paddingTop2, a) + i5, i6, a);
        }
        if (!(this.f1157k == null || this.f1156j != null || this.f1157k.getVisibility() == 8)) {
            i5 += m2547a(this.f1157k, i5, paddingTop, paddingTop2, a);
        }
        if (this.f1156j != null) {
            int a2 = m2547a(this.f1156j, i5, paddingTop, paddingTop2, a) + i5;
        }
        i5 = a ? getPaddingLeft() : (i3 - i) - getPaddingRight();
        if (this.c != null) {
            a2 = m2547a(this.c, i5, paddingTop, paddingTop2, !a) + i5;
        }
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (VERSION.SDK_INT < 14) {
            return;
        }
        if (accessibilityEvent.getEventType() == 32) {
            accessibilityEvent.setSource(this);
            accessibilityEvent.setClassName(getClass().getName());
            accessibilityEvent.setPackageName(getContext().getPackageName());
            accessibilityEvent.setContentDescription(this.f1153g);
            return;
        }
        super.onInitializeAccessibilityEvent(accessibilityEvent);
    }

    public void setTitleOptional(boolean z) {
        if (z != this.f1162p) {
            requestLayout();
        }
        this.f1162p = z;
    }

    public boolean m2558d() {
        return this.f1162p;
    }
}
