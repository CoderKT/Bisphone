package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.appcompat.C0057R;
import android.support.v7.view.ActionBarPolicy;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ScrollingTabContainerView extends HorizontalScrollView implements OnItemSelectedListener {
    private static final Interpolator f1729j;
    Runnable f1730a;
    int f1731b;
    int f1732c;
    private TabClickListener f1733d;
    private LinearLayoutCompat f1734e;
    private Spinner f1735f;
    private boolean f1736g;
    private int f1737h;
    private int f1738i;

    /* renamed from: android.support.v7.widget.ScrollingTabContainerView.1 */
    class C00971 implements Runnable {
        final /* synthetic */ View f1719a;
        final /* synthetic */ ScrollingTabContainerView f1720b;

        C00971(ScrollingTabContainerView scrollingTabContainerView, View view) {
            this.f1720b = scrollingTabContainerView;
            this.f1719a = view;
        }

        public void run() {
            this.f1720b.smoothScrollTo(this.f1719a.getLeft() - ((this.f1720b.getWidth() - this.f1719a.getWidth()) / 2), 0);
            this.f1720b.f1730a = null;
        }
    }

    class TabAdapter extends BaseAdapter {
        final /* synthetic */ ScrollingTabContainerView f1721a;

        private TabAdapter(ScrollingTabContainerView scrollingTabContainerView) {
            this.f1721a = scrollingTabContainerView;
        }

        public int getCount() {
            return this.f1721a.f1734e.getChildCount();
        }

        public Object getItem(int i) {
            return ((TabView) this.f1721a.f1734e.getChildAt(i)).m3660b();
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                return this.f1721a.m3662a((Tab) getItem(i), true);
            }
            ((TabView) view).m3659a((Tab) getItem(i));
            return view;
        }
    }

    class TabClickListener implements OnClickListener {
        final /* synthetic */ ScrollingTabContainerView f1722a;

        private TabClickListener(ScrollingTabContainerView scrollingTabContainerView) {
            this.f1722a = scrollingTabContainerView;
        }

        public void onClick(View view) {
            ((TabView) view).m3660b().m1914d();
            int childCount = this.f1722a.f1734e.getChildCount();
            for (int i = 0; i < childCount; i++) {
                boolean z;
                View childAt = this.f1722a.f1734e.getChildAt(i);
                if (childAt == view) {
                    z = true;
                } else {
                    z = false;
                }
                childAt.setSelected(z);
            }
        }
    }

    class TabView extends LinearLayoutCompat implements OnLongClickListener {
        final /* synthetic */ ScrollingTabContainerView f1723a;
        private final int[] f1724b;
        private Tab f1725c;
        private TextView f1726d;
        private ImageView f1727e;
        private View f1728f;

        public TabView(ScrollingTabContainerView scrollingTabContainerView, Context context, Tab tab, boolean z) {
            this.f1723a = scrollingTabContainerView;
            super(context, null, C0057R.attr.actionBarTabStyle);
            this.f1724b = new int[]{16842964};
            this.f1725c = tab;
            TintTypedArray a = TintTypedArray.m3759a(context, null, this.f1724b, C0057R.attr.actionBarTabStyle, 0);
            if (a.m3773e(0)) {
                setBackgroundDrawable(a.m3762a(0));
            }
            a.m3763a();
            if (z) {
                setGravity(8388627);
            }
            m3658a();
        }

        public void m3659a(Tab tab) {
            this.f1725c = tab;
            m3658a();
        }

        public void setSelected(boolean z) {
            Object obj = isSelected() != z ? 1 : null;
            super.setSelected(z);
            if (obj != null && z) {
                sendAccessibilityEvent(4);
            }
        }

        public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            accessibilityEvent.setClassName(Tab.class.getName());
        }

        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            if (VERSION.SDK_INT >= 14) {
                accessibilityNodeInfo.setClassName(Tab.class.getName());
            }
        }

        public void onMeasure(int i, int i2) {
            super.onMeasure(i, i2);
            if (this.f1723a.f1731b > 0 && getMeasuredWidth() > this.f1723a.f1731b) {
                super.onMeasure(MeasureSpec.makeMeasureSpec(this.f1723a.f1731b, 1073741824), i2);
            }
        }

        public void m3658a() {
            Tab tab = this.f1725c;
            View c = tab.m1913c();
            if (c != null) {
                TabView parent = c.getParent();
                if (parent != this) {
                    if (parent != null) {
                        parent.removeView(c);
                    }
                    addView(c);
                }
                this.f1728f = c;
                if (this.f1726d != null) {
                    this.f1726d.setVisibility(8);
                }
                if (this.f1727e != null) {
                    this.f1727e.setVisibility(8);
                    this.f1727e.setImageDrawable(null);
                    return;
                }
                return;
            }
            boolean z;
            if (this.f1728f != null) {
                removeView(this.f1728f);
                this.f1728f = null;
            }
            Drawable a = tab.m1911a();
            CharSequence b = tab.m1912b();
            if (a != null) {
                if (this.f1727e == null) {
                    View imageView = new ImageView(getContext());
                    LayoutParams layoutParams = new LinearLayoutCompat.LayoutParams(-2, -2);
                    layoutParams.f1231h = 16;
                    imageView.setLayoutParams(layoutParams);
                    addView(imageView, 0);
                    this.f1727e = imageView;
                }
                this.f1727e.setImageDrawable(a);
                this.f1727e.setVisibility(0);
            } else if (this.f1727e != null) {
                this.f1727e.setVisibility(8);
                this.f1727e.setImageDrawable(null);
            }
            if (TextUtils.isEmpty(b)) {
                z = false;
            } else {
                z = true;
            }
            if (z) {
                if (this.f1726d == null) {
                    imageView = new AppCompatTextView(getContext(), null, C0057R.attr.actionBarTabTextStyle);
                    imageView.setEllipsize(TruncateAt.END);
                    layoutParams = new LinearLayoutCompat.LayoutParams(-2, -2);
                    layoutParams.f1231h = 16;
                    imageView.setLayoutParams(layoutParams);
                    addView(imageView);
                    this.f1726d = imageView;
                }
                this.f1726d.setText(b);
                this.f1726d.setVisibility(0);
            } else if (this.f1726d != null) {
                this.f1726d.setVisibility(8);
                this.f1726d.setText(null);
            }
            if (this.f1727e != null) {
                this.f1727e.setContentDescription(tab.m1915e());
            }
            if (z || TextUtils.isEmpty(tab.m1915e())) {
                setOnLongClickListener(null);
                setLongClickable(false);
                return;
            }
            setOnLongClickListener(this);
        }

        public boolean onLongClick(View view) {
            int[] iArr = new int[2];
            getLocationOnScreen(iArr);
            Context context = getContext();
            int width = getWidth();
            int height = getHeight();
            int i = context.getResources().getDisplayMetrics().widthPixels;
            Toast makeText = Toast.makeText(context, this.f1725c.m1915e(), 0);
            makeText.setGravity(49, (iArr[0] + (width / 2)) - (i / 2), height);
            makeText.show();
            return true;
        }

        public Tab m3660b() {
            return this.f1725c;
        }
    }

    static {
        f1729j = new DecelerateInterpolator();
    }

    public void onMeasure(int i, int i2) {
        int i3 = 1;
        int mode = MeasureSpec.getMode(i);
        boolean z = mode == 1073741824;
        setFillViewport(z);
        int childCount = this.f1734e.getChildCount();
        if (childCount <= 1 || !(mode == 1073741824 || mode == Integer.MIN_VALUE)) {
            this.f1731b = -1;
        } else {
            if (childCount > 2) {
                this.f1731b = (int) (((float) MeasureSpec.getSize(i)) * 0.4f);
            } else {
                this.f1731b = MeasureSpec.getSize(i) / 2;
            }
            this.f1731b = Math.min(this.f1731b, this.f1732c);
        }
        mode = MeasureSpec.makeMeasureSpec(this.f1737h, 1073741824);
        if (z || !this.f1736g) {
            i3 = 0;
        }
        if (i3 != 0) {
            this.f1734e.measure(0, mode);
            if (this.f1734e.getMeasuredWidth() > MeasureSpec.getSize(i)) {
                m3665b();
            } else {
                m3666c();
            }
        } else {
            m3666c();
        }
        i3 = getMeasuredWidth();
        super.onMeasure(i, mode);
        int measuredWidth = getMeasuredWidth();
        if (z && i3 != measuredWidth) {
            setTabSelected(this.f1738i);
        }
    }

    private boolean m3664a() {
        return this.f1735f != null && this.f1735f.getParent() == this;
    }

    public void setAllowCollapse(boolean z) {
        this.f1736g = z;
    }

    private void m3665b() {
        if (!m3664a()) {
            if (this.f1735f == null) {
                this.f1735f = m3667d();
            }
            removeView(this.f1734e);
            addView(this.f1735f, new LayoutParams(-2, -1));
            if (this.f1735f.getAdapter() == null) {
                this.f1735f.setAdapter(new TabAdapter());
            }
            if (this.f1730a != null) {
                removeCallbacks(this.f1730a);
                this.f1730a = null;
            }
            this.f1735f.setSelection(this.f1738i);
        }
    }

    private boolean m3666c() {
        if (m3664a()) {
            removeView(this.f1735f);
            addView(this.f1734e, new LayoutParams(-2, -1));
            setTabSelected(this.f1735f.getSelectedItemPosition());
        }
        return false;
    }

    public void setTabSelected(int i) {
        this.f1738i = i;
        int childCount = this.f1734e.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            boolean z;
            View childAt = this.f1734e.getChildAt(i2);
            if (i2 == i) {
                z = true;
            } else {
                z = false;
            }
            childAt.setSelected(z);
            if (z) {
                m3668a(i);
            }
        }
        if (this.f1735f != null && i >= 0) {
            this.f1735f.setSelection(i);
        }
    }

    public void setContentHeight(int i) {
        this.f1737h = i;
        requestLayout();
    }

    private Spinner m3667d() {
        Spinner appCompatSpinner = new AppCompatSpinner(getContext(), null, C0057R.attr.actionDropDownStyle);
        appCompatSpinner.setLayoutParams(new LinearLayoutCompat.LayoutParams(-2, -1));
        appCompatSpinner.setOnItemSelectedListener(this);
        return appCompatSpinner;
    }

    protected void onConfigurationChanged(Configuration configuration) {
        if (VERSION.SDK_INT >= 8) {
            super.onConfigurationChanged(configuration);
        }
        ActionBarPolicy a = ActionBarPolicy.m2247a(getContext());
        setContentHeight(a.m2252e());
        this.f1732c = a.m2254g();
    }

    public void m3668a(int i) {
        View childAt = this.f1734e.getChildAt(i);
        if (this.f1730a != null) {
            removeCallbacks(this.f1730a);
        }
        this.f1730a = new C00971(this, childAt);
        post(this.f1730a);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f1730a != null) {
            post(this.f1730a);
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.f1730a != null) {
            removeCallbacks(this.f1730a);
        }
    }

    private TabView m3662a(Tab tab, boolean z) {
        TabView tabView = new TabView(this, getContext(), tab, z);
        if (z) {
            tabView.setBackgroundDrawable(null);
            tabView.setLayoutParams(new AbsListView.LayoutParams(-1, this.f1737h));
        } else {
            tabView.setFocusable(true);
            if (this.f1733d == null) {
                this.f1733d = new TabClickListener();
            }
            tabView.setOnClickListener(this.f1733d);
        }
        return tabView;
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        ((TabView) view).m3660b().m1914d();
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}
