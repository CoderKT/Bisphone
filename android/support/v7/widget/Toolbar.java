package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MarginLayoutParamsCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.C0057R;
import android.support.v7.view.CollapsibleActionView;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.view.menu.MenuPresenter.Callback;
import android.support.v7.view.menu.SubMenuBuilder;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import app.C0110R;
import java.util.ArrayList;
import java.util.List;
import se.emilsjolander.stickylistheaders.C1128R;

public class Toolbar extends ViewGroup {
    private boolean f1845A;
    private final ArrayList<View> f1846B;
    private final ArrayList<View> f1847C;
    private final int[] f1848D;
    private OnMenuItemClickListener f1849E;
    private final android.support.v7.widget.ActionMenuView.OnMenuItemClickListener f1850F;
    private ToolbarWidgetWrapper f1851G;
    private ActionMenuPresenter f1852H;
    private ExpandedActionViewMenuPresenter f1853I;
    private Callback f1854J;
    private MenuBuilder.Callback f1855K;
    private boolean f1856L;
    private final Runnable f1857M;
    private final TintManager f1858N;
    View f1859a;
    private ActionMenuView f1860b;
    private TextView f1861c;
    private TextView f1862d;
    private ImageButton f1863e;
    private ImageView f1864f;
    private Drawable f1865g;
    private CharSequence f1866h;
    private ImageButton f1867i;
    private Context f1868j;
    private int f1869k;
    private int f1870l;
    private int f1871m;
    private int f1872n;
    private int f1873o;
    private int f1874p;
    private int f1875q;
    private int f1876r;
    private int f1877s;
    private final RtlSpacingHelper f1878t;
    private int f1879u;
    private CharSequence f1880v;
    private CharSequence f1881w;
    private int f1882x;
    private int f1883y;
    private boolean f1884z;

    /* renamed from: android.support.v7.widget.Toolbar.1 */
    class C00981 implements android.support.v7.widget.ActionMenuView.OnMenuItemClickListener {
        final /* synthetic */ Toolbar f1836a;

        C00981(Toolbar toolbar) {
            this.f1836a = toolbar;
        }

        public boolean m3776a(MenuItem menuItem) {
            if (this.f1836a.f1849E != null) {
                return this.f1836a.f1849E.m3785a(menuItem);
            }
            return false;
        }
    }

    /* renamed from: android.support.v7.widget.Toolbar.2 */
    class C00992 implements Runnable {
        final /* synthetic */ Toolbar f1837a;

        C00992(Toolbar toolbar) {
            this.f1837a = toolbar;
        }

        public void run() {
            this.f1837a.m3823d();
        }
    }

    /* renamed from: android.support.v7.widget.Toolbar.3 */
    class C01003 implements OnClickListener {
        final /* synthetic */ Toolbar f1838a;

        C01003(Toolbar toolbar) {
            this.f1838a = toolbar;
        }

        public void onClick(View view) {
            this.f1838a.m3827h();
        }
    }

    class ExpandedActionViewMenuPresenter implements MenuPresenter {
        MenuBuilder f1839a;
        MenuItemImpl f1840b;
        final /* synthetic */ Toolbar f1841c;

        private ExpandedActionViewMenuPresenter(Toolbar toolbar) {
            this.f1841c = toolbar;
        }

        public void m3777a(Context context, MenuBuilder menuBuilder) {
            if (!(this.f1839a == null || this.f1840b == null)) {
                this.f1839a.m2439d(this.f1840b);
            }
            this.f1839a = menuBuilder;
        }

        public void m3781b(boolean z) {
            Object obj = null;
            if (this.f1840b != null) {
                if (this.f1839a != null) {
                    int size = this.f1839a.size();
                    for (int i = 0; i < size; i++) {
                        if (this.f1839a.getItem(i) == this.f1840b) {
                            obj = 1;
                            break;
                        }
                    }
                }
                if (obj == null) {
                    m3783b(this.f1839a, this.f1840b);
                }
            }
        }

        public boolean m3780a(SubMenuBuilder subMenuBuilder) {
            return false;
        }

        public void m3778a(MenuBuilder menuBuilder, boolean z) {
        }

        public boolean m3782b() {
            return false;
        }

        public boolean m3779a(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
            this.f1841c.m3810p();
            if (this.f1841c.f1867i.getParent() != this.f1841c) {
                this.f1841c.addView(this.f1841c.f1867i);
            }
            this.f1841c.f1859a = menuItemImpl.getActionView();
            this.f1840b = menuItemImpl;
            if (this.f1841c.f1859a.getParent() != this.f1841c) {
                android.view.ViewGroup.LayoutParams i = this.f1841c.m3828i();
                i.a = 8388611 | (this.f1841c.f1872n & 112);
                i.f1842b = 2;
                this.f1841c.f1859a.setLayoutParams(i);
                this.f1841c.addView(this.f1841c.f1859a);
            }
            this.f1841c.m3829j();
            this.f1841c.requestLayout();
            menuItemImpl.m2477e(true);
            if (this.f1841c.f1859a instanceof CollapsibleActionView) {
                ((CollapsibleActionView) this.f1841c.f1859a).m2255a();
            }
            return true;
        }

        public boolean m3783b(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
            if (this.f1841c.f1859a instanceof CollapsibleActionView) {
                ((CollapsibleActionView) this.f1841c.f1859a).m2256b();
            }
            this.f1841c.removeView(this.f1841c.f1859a);
            this.f1841c.removeView(this.f1841c.f1867i);
            this.f1841c.f1859a = null;
            this.f1841c.m3830k();
            this.f1840b = null;
            this.f1841c.requestLayout();
            menuItemImpl.m2477e(false);
            return true;
        }
    }

    public class LayoutParams extends android.support.v7.app.ActionBar.LayoutParams {
        int f1842b;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1842b = 0;
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.f1842b = 0;
            this.a = 8388627;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((android.support.v7.app.ActionBar.LayoutParams) layoutParams);
            this.f1842b = 0;
            this.f1842b = layoutParams.f1842b;
        }

        public LayoutParams(android.support.v7.app.ActionBar.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1842b = 0;
        }

        public LayoutParams(MarginLayoutParams marginLayoutParams) {
            super((android.view.ViewGroup.LayoutParams) marginLayoutParams);
            this.f1842b = 0;
            m3784a(marginLayoutParams);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1842b = 0;
        }

        void m3784a(MarginLayoutParams marginLayoutParams) {
            this.leftMargin = marginLayoutParams.leftMargin;
            this.topMargin = marginLayoutParams.topMargin;
            this.rightMargin = marginLayoutParams.rightMargin;
            this.bottomMargin = marginLayoutParams.bottomMargin;
        }
    }

    public interface OnMenuItemClickListener {
        boolean m3785a(MenuItem menuItem);
    }

    public class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR;
        int f1843a;
        boolean f1844b;

        /* renamed from: android.support.v7.widget.Toolbar.SavedState.1 */
        final class C01011 implements Creator<SavedState> {
            C01011() {
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return m3786a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return m3787a(i);
            }

            public SavedState m3786a(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] m3787a(int i) {
                return new SavedState[i];
            }
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.f1843a = parcel.readInt();
            this.f1844b = parcel.readInt() != 0;
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f1843a);
            parcel.writeInt(this.f1844b ? 1 : 0);
        }

        static {
            CREATOR = new C01011();
        }
    }

    protected /* synthetic */ android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return m3828i();
    }

    public /* synthetic */ android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return m3813a(attributeSet);
    }

    protected /* synthetic */ android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return m3814a(layoutParams);
    }

    public Toolbar(Context context) {
        this(context, null);
    }

    public Toolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0057R.attr.toolbarStyle);
    }

    public Toolbar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1878t = new RtlSpacingHelper();
        this.f1879u = 8388627;
        this.f1846B = new ArrayList();
        this.f1847C = new ArrayList();
        this.f1848D = new int[2];
        this.f1850F = new C00981(this);
        this.f1857M = new C00992(this);
        TintTypedArray a = TintTypedArray.m3759a(getContext(), attributeSet, C0057R.styleable.Toolbar, i, 0);
        this.f1870l = a.m3775g(C0057R.styleable.Toolbar_titleTextAppearance, 0);
        this.f1871m = a.m3775g(C0057R.styleable.Toolbar_subtitleTextAppearance, 0);
        this.f1879u = a.m3768c(C0057R.styleable.Toolbar_android_gravity, this.f1879u);
        this.f1872n = 48;
        int d = a.m3770d(C0057R.styleable.Toolbar_titleMargins, 0);
        this.f1877s = d;
        this.f1876r = d;
        this.f1875q = d;
        this.f1874p = d;
        d = a.m3770d(C0057R.styleable.Toolbar_titleMarginStart, -1);
        if (d >= 0) {
            this.f1874p = d;
        }
        d = a.m3770d(C0057R.styleable.Toolbar_titleMarginEnd, -1);
        if (d >= 0) {
            this.f1875q = d;
        }
        d = a.m3770d(C0057R.styleable.Toolbar_titleMarginTop, -1);
        if (d >= 0) {
            this.f1876r = d;
        }
        d = a.m3770d(C0057R.styleable.Toolbar_titleMarginBottom, -1);
        if (d >= 0) {
            this.f1877s = d;
        }
        this.f1873o = a.m3772e(C0057R.styleable.Toolbar_maxButtonHeight, -1);
        d = a.m3770d(C0057R.styleable.Toolbar_contentInsetStart, Integer.MIN_VALUE);
        int d2 = a.m3770d(C0057R.styleable.Toolbar_contentInsetEnd, Integer.MIN_VALUE);
        this.f1878t.m3652b(a.m3772e(C0057R.styleable.Toolbar_contentInsetLeft, 0), a.m3772e(C0057R.styleable.Toolbar_contentInsetRight, 0));
        if (!(d == Integer.MIN_VALUE && d2 == Integer.MIN_VALUE)) {
            this.f1878t.m3649a(d, d2);
        }
        this.f1865g = a.m3762a(C0057R.styleable.Toolbar_collapseIcon);
        this.f1866h = a.m3769c(C0057R.styleable.Toolbar_collapseContentDescription);
        CharSequence c = a.m3769c(C0057R.styleable.Toolbar_title);
        if (!TextUtils.isEmpty(c)) {
            setTitle(c);
        }
        c = a.m3769c(C0057R.styleable.Toolbar_subtitle);
        if (!TextUtils.isEmpty(c)) {
            setSubtitle(c);
        }
        this.f1868j = getContext();
        setPopupTheme(a.m3775g(C0057R.styleable.Toolbar_popupTheme, 0));
        Drawable a2 = a.m3762a(C0057R.styleable.Toolbar_navigationIcon);
        if (a2 != null) {
            setNavigationIcon(a2);
        }
        c = a.m3769c(C0057R.styleable.Toolbar_navigationContentDescription);
        if (!TextUtils.isEmpty(c)) {
            setNavigationContentDescription(c);
        }
        a2 = a.m3762a(C0057R.styleable.Toolbar_logo);
        if (a2 != null) {
            setLogo(a2);
        }
        c = a.m3769c(C0057R.styleable.Toolbar_logoDescription);
        if (!TextUtils.isEmpty(c)) {
            setLogoDescription(c);
        }
        if (a.m3773e(C0057R.styleable.Toolbar_titleTextColor)) {
            setTitleTextColor(a.m3765b(C0057R.styleable.Toolbar_titleTextColor, -1));
        }
        if (a.m3773e(C0057R.styleable.Toolbar_subtitleTextColor)) {
            setSubtitleTextColor(a.m3765b(C0057R.styleable.Toolbar_subtitleTextColor, -1));
        }
        a.m3763a();
        this.f1858N = a.m3767b();
    }

    public void setPopupTheme(int i) {
        if (this.f1869k != i) {
            this.f1869k = i;
            if (i == 0) {
                this.f1868j = getContext();
            } else {
                this.f1868j = new ContextThemeWrapper(getContext(), i);
            }
        }
    }

    public int getPopupTheme() {
        return this.f1869k;
    }

    public void onRtlPropertiesChanged(int i) {
        boolean z = true;
        if (VERSION.SDK_INT >= 17) {
            super.onRtlPropertiesChanged(i);
        }
        RtlSpacingHelper rtlSpacingHelper = this.f1878t;
        if (i != 1) {
            z = false;
        }
        rtlSpacingHelper.m3650a(z);
    }

    public void setLogo(int i) {
        setLogo(this.f1858N.m3753a(i));
    }

    public boolean m3819a() {
        return getVisibility() == 0 && this.f1860b != null && this.f1860b.m2675a();
    }

    public boolean m3821b() {
        return this.f1860b != null && this.f1860b.m2685g();
    }

    public boolean m3822c() {
        return this.f1860b != null && this.f1860b.m2686h();
    }

    public boolean m3823d() {
        return this.f1860b != null && this.f1860b.m2683e();
    }

    public boolean m3824e() {
        return this.f1860b != null && this.f1860b.m2684f();
    }

    public void m3817a(MenuBuilder menuBuilder, ActionMenuPresenter actionMenuPresenter) {
        if (menuBuilder != null || this.f1860b != null) {
            m3808n();
            MenuBuilder d = this.f1860b.m2682d();
            if (d != menuBuilder) {
                if (d != null) {
                    d.m2431b(this.f1852H);
                    d.m2431b(this.f1853I);
                }
                if (this.f1853I == null) {
                    this.f1853I = new ExpandedActionViewMenuPresenter();
                }
                actionMenuPresenter.m2634d(true);
                if (menuBuilder != null) {
                    menuBuilder.m2421a((MenuPresenter) actionMenuPresenter, this.f1868j);
                    menuBuilder.m2421a(this.f1853I, this.f1868j);
                } else {
                    actionMenuPresenter.m2620a(this.f1868j, null);
                    this.f1853I.m3777a(this.f1868j, null);
                    actionMenuPresenter.m2630b(true);
                    this.f1853I.m3781b(true);
                }
                this.f1860b.setPopupTheme(this.f1869k);
                this.f1860b.setPresenter(actionMenuPresenter);
                this.f1852H = actionMenuPresenter;
            }
        }
    }

    public void m3825f() {
        if (this.f1860b != null) {
            this.f1860b.m2687i();
        }
    }

    public void setLogo(Drawable drawable) {
        if (drawable != null) {
            m3806l();
            if (!m3805d(this.f1864f)) {
                m3795a(this.f1864f, true);
            }
        } else if (this.f1864f != null && m3805d(this.f1864f)) {
            removeView(this.f1864f);
            this.f1847C.remove(this.f1864f);
        }
        if (this.f1864f != null) {
            this.f1864f.setImageDrawable(drawable);
        }
    }

    public Drawable getLogo() {
        return this.f1864f != null ? this.f1864f.getDrawable() : null;
    }

    public void setLogoDescription(int i) {
        setLogoDescription(getContext().getText(i));
    }

    public void setLogoDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            m3806l();
        }
        if (this.f1864f != null) {
            this.f1864f.setContentDescription(charSequence);
        }
    }

    public CharSequence getLogoDescription() {
        return this.f1864f != null ? this.f1864f.getContentDescription() : null;
    }

    private void m3806l() {
        if (this.f1864f == null) {
            this.f1864f = new ImageView(getContext());
        }
    }

    public boolean m3826g() {
        return (this.f1853I == null || this.f1853I.f1840b == null) ? false : true;
    }

    public void m3827h() {
        MenuItemImpl menuItemImpl = this.f1853I == null ? null : this.f1853I.f1840b;
        if (menuItemImpl != null) {
            menuItemImpl.collapseActionView();
        }
    }

    public CharSequence getTitle() {
        return this.f1880v;
    }

    public void setTitle(int i) {
        setTitle(getContext().getText(i));
    }

    public void setTitle(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            if (this.f1861c == null) {
                Context context = getContext();
                this.f1861c = new TextView(context);
                this.f1861c.setSingleLine();
                this.f1861c.setEllipsize(TruncateAt.END);
                if (this.f1870l != 0) {
                    this.f1861c.setTextAppearance(context, this.f1870l);
                }
                if (this.f1882x != 0) {
                    this.f1861c.setTextColor(this.f1882x);
                }
            }
            if (!m3805d(this.f1861c)) {
                m3795a(this.f1861c, true);
            }
        } else if (this.f1861c != null && m3805d(this.f1861c)) {
            removeView(this.f1861c);
            this.f1847C.remove(this.f1861c);
        }
        if (this.f1861c != null) {
            this.f1861c.setText(charSequence);
        }
        this.f1880v = charSequence;
    }

    public CharSequence getSubtitle() {
        return this.f1881w;
    }

    public void setSubtitle(int i) {
        setSubtitle(getContext().getText(i));
    }

    public void setSubtitle(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            if (this.f1862d == null) {
                Context context = getContext();
                this.f1862d = new TextView(context);
                this.f1862d.setSingleLine();
                this.f1862d.setEllipsize(TruncateAt.END);
                if (this.f1871m != 0) {
                    this.f1862d.setTextAppearance(context, this.f1871m);
                }
                if (this.f1883y != 0) {
                    this.f1862d.setTextColor(this.f1883y);
                }
            }
            if (!m3805d(this.f1862d)) {
                m3795a(this.f1862d, true);
            }
        } else if (this.f1862d != null && m3805d(this.f1862d)) {
            removeView(this.f1862d);
            this.f1847C.remove(this.f1862d);
        }
        if (this.f1862d != null) {
            this.f1862d.setText(charSequence);
        }
        this.f1881w = charSequence;
    }

    public void m3816a(Context context, int i) {
        this.f1870l = i;
        if (this.f1861c != null) {
            this.f1861c.setTextAppearance(context, i);
        }
    }

    public void m3820b(Context context, int i) {
        this.f1871m = i;
        if (this.f1862d != null) {
            this.f1862d.setTextAppearance(context, i);
        }
    }

    public void setTitleTextColor(int i) {
        this.f1882x = i;
        if (this.f1861c != null) {
            this.f1861c.setTextColor(i);
        }
    }

    public void setSubtitleTextColor(int i) {
        this.f1883y = i;
        if (this.f1862d != null) {
            this.f1862d.setTextColor(i);
        }
    }

    public CharSequence getNavigationContentDescription() {
        return this.f1863e != null ? this.f1863e.getContentDescription() : null;
    }

    public void setNavigationContentDescription(int i) {
        setNavigationContentDescription(i != 0 ? getContext().getText(i) : null);
    }

    public void setNavigationContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            m3809o();
        }
        if (this.f1863e != null) {
            this.f1863e.setContentDescription(charSequence);
        }
    }

    public void setNavigationIcon(int i) {
        setNavigationIcon(this.f1858N.m3753a(i));
    }

    public void setNavigationIcon(Drawable drawable) {
        if (drawable != null) {
            m3809o();
            if (!m3805d(this.f1863e)) {
                m3795a(this.f1863e, true);
            }
        } else if (this.f1863e != null && m3805d(this.f1863e)) {
            removeView(this.f1863e);
            this.f1847C.remove(this.f1863e);
        }
        if (this.f1863e != null) {
            this.f1863e.setImageDrawable(drawable);
        }
    }

    public Drawable getNavigationIcon() {
        return this.f1863e != null ? this.f1863e.getDrawable() : null;
    }

    public void setNavigationOnClickListener(OnClickListener onClickListener) {
        m3809o();
        this.f1863e.setOnClickListener(onClickListener);
    }

    public Menu getMenu() {
        m3807m();
        return this.f1860b.getMenu();
    }

    public void setOverflowIcon(Drawable drawable) {
        m3807m();
        this.f1860b.setOverflowIcon(drawable);
    }

    public Drawable getOverflowIcon() {
        m3807m();
        return this.f1860b.getOverflowIcon();
    }

    private void m3807m() {
        m3808n();
        if (this.f1860b.m2682d() == null) {
            MenuBuilder menuBuilder = (MenuBuilder) this.f1860b.getMenu();
            if (this.f1853I == null) {
                this.f1853I = new ExpandedActionViewMenuPresenter();
            }
            this.f1860b.setExpandedActionViewsExclusive(true);
            menuBuilder.m2421a(this.f1853I, this.f1868j);
        }
    }

    private void m3808n() {
        if (this.f1860b == null) {
            this.f1860b = new ActionMenuView(getContext());
            this.f1860b.setPopupTheme(this.f1869k);
            this.f1860b.setOnMenuItemClickListener(this.f1850F);
            this.f1860b.m2674a(this.f1854J, this.f1855K);
            android.view.ViewGroup.LayoutParams i = m3828i();
            i.a = 8388613 | (this.f1872n & 112);
            this.f1860b.setLayoutParams(i);
            m3795a(this.f1860b, false);
        }
    }

    private MenuInflater getMenuInflater() {
        return new SupportMenuInflater(getContext());
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        this.f1849E = onMenuItemClickListener;
    }

    public void m3815a(int i, int i2) {
        this.f1878t.m3649a(i, i2);
    }

    public int getContentInsetStart() {
        return this.f1878t.m3653c();
    }

    public int getContentInsetEnd() {
        return this.f1878t.m3654d();
    }

    public int getContentInsetLeft() {
        return this.f1878t.m3648a();
    }

    public int getContentInsetRight() {
        return this.f1878t.m3651b();
    }

    private void m3809o() {
        if (this.f1863e == null) {
            this.f1863e = new ImageButton(getContext(), null, C0057R.attr.toolbarNavigationButtonStyle);
            android.view.ViewGroup.LayoutParams i = m3828i();
            i.a = 8388611 | (this.f1872n & 112);
            this.f1863e.setLayoutParams(i);
        }
    }

    private void m3810p() {
        if (this.f1867i == null) {
            this.f1867i = new ImageButton(getContext(), null, C0057R.attr.toolbarNavigationButtonStyle);
            this.f1867i.setImageDrawable(this.f1865g);
            this.f1867i.setContentDescription(this.f1866h);
            android.view.ViewGroup.LayoutParams i = m3828i();
            i.a = 8388611 | (this.f1872n & 112);
            i.f1842b = 2;
            this.f1867i.setLayoutParams(i);
            this.f1867i.setOnClickListener(new C01003(this));
        }
    }

    private void m3795a(View view, boolean z) {
        android.view.ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = m3828i();
        } else if (checkLayoutParams(layoutParams)) {
            LayoutParams layoutParams2 = (LayoutParams) layoutParams;
        } else {
            layoutParams = m3814a(layoutParams);
        }
        layoutParams.f1842b = 1;
        if (!z || this.f1859a == null) {
            addView(view, layoutParams);
            return;
        }
        view.setLayoutParams(layoutParams);
        this.f1847C.add(view);
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        if (!(this.f1853I == null || this.f1853I.f1840b == null)) {
            savedState.f1843a = this.f1853I.f1840b.getItemId();
        }
        savedState.f1844b = m3821b();
        return savedState;
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        Menu d = this.f1860b != null ? this.f1860b.m2682d() : null;
        if (!(savedState.f1843a == 0 || this.f1853I == null || d == null)) {
            MenuItem findItem = d.findItem(savedState.f1843a);
            if (findItem != null) {
                MenuItemCompat.m961b(findItem);
            }
        }
        if (savedState.f1844b) {
            m3811q();
        }
    }

    private void m3811q() {
        removeCallbacks(this.f1857M);
        post(this.f1857M);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.f1857M);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int a = MotionEventCompat.m987a(motionEvent);
        if (a == 0) {
            this.f1884z = false;
        }
        if (!this.f1884z) {
            boolean onTouchEvent = super.onTouchEvent(motionEvent);
            if (a == 0 && !onTouchEvent) {
                this.f1884z = true;
            }
        }
        if (a == 1 || a == 3) {
            this.f1884z = false;
        }
        return true;
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        int a = MotionEventCompat.m987a(motionEvent);
        if (a == 9) {
            this.f1845A = false;
        }
        if (!this.f1845A) {
            boolean onHoverEvent = super.onHoverEvent(motionEvent);
            if (a == 9 && !onHoverEvent) {
                this.f1845A = true;
            }
        }
        if (a == 10 || a == 3) {
            this.f1845A = false;
        }
        return true;
    }

    private void m3794a(View view, int i, int i2, int i3, int i4, int i5) {
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
        int childMeasureSpec = getChildMeasureSpec(i, (((getPaddingLeft() + getPaddingRight()) + marginLayoutParams.leftMargin) + marginLayoutParams.rightMargin) + i2, marginLayoutParams.width);
        int childMeasureSpec2 = getChildMeasureSpec(i3, (((getPaddingTop() + getPaddingBottom()) + marginLayoutParams.topMargin) + marginLayoutParams.bottomMargin) + i4, marginLayoutParams.height);
        int mode = MeasureSpec.getMode(childMeasureSpec2);
        if (mode != 1073741824 && i5 >= 0) {
            if (mode != 0) {
                i5 = Math.min(MeasureSpec.getSize(childMeasureSpec2), i5);
            }
            childMeasureSpec2 = MeasureSpec.makeMeasureSpec(i5, 1073741824);
        }
        view.measure(childMeasureSpec, childMeasureSpec2);
    }

    private int m3790a(View view, int i, int i2, int i3, int i4, int[] iArr) {
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
        int i5 = marginLayoutParams.leftMargin - iArr[0];
        int i6 = marginLayoutParams.rightMargin - iArr[1];
        int max = Math.max(0, i5) + Math.max(0, i6);
        iArr[0] = Math.max(0, -i5);
        iArr[1] = Math.max(0, -i6);
        view.measure(getChildMeasureSpec(i, ((getPaddingLeft() + getPaddingRight()) + max) + i2, marginLayoutParams.width), getChildMeasureSpec(i3, (((getPaddingTop() + getPaddingBottom()) + marginLayoutParams.topMargin) + marginLayoutParams.bottomMargin) + i4, marginLayoutParams.height));
        return view.getMeasuredWidth() + max;
    }

    private boolean m3812r() {
        if (!this.f1856L) {
            return false;
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (m3797a(childAt) && childAt.getMeasuredWidth() > 0 && childAt.getMeasuredHeight() > 0) {
                return false;
            }
        }
        return true;
    }

    protected void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int max;
        int i5 = 0;
        int i6 = 0;
        int[] iArr = this.f1848D;
        if (ViewUtils.m3901a(this)) {
            i3 = 0;
            i4 = 1;
        } else {
            i3 = 1;
            i4 = 0;
        }
        int i7 = 0;
        if (m3797a(this.f1863e)) {
            m3794a(this.f1863e, i, 0, i2, 0, this.f1873o);
            i7 = this.f1863e.getMeasuredWidth() + m3799b(this.f1863e);
            max = Math.max(0, this.f1863e.getMeasuredHeight() + m3802c(this.f1863e));
            i6 = ViewUtils.m3899a(0, ViewCompat.m1180i(this.f1863e));
            i5 = max;
        }
        if (m3797a(this.f1867i)) {
            m3794a(this.f1867i, i, 0, i2, 0, this.f1873o);
            i7 = this.f1867i.getMeasuredWidth() + m3799b(this.f1867i);
            i5 = Math.max(i5, this.f1867i.getMeasuredHeight() + m3802c(this.f1867i));
            i6 = ViewUtils.m3899a(i6, ViewCompat.m1180i(this.f1867i));
        }
        int contentInsetStart = getContentInsetStart();
        int max2 = 0 + Math.max(contentInsetStart, i7);
        iArr[i4] = Math.max(0, contentInsetStart - i7);
        i7 = 0;
        if (m3797a(this.f1860b)) {
            m3794a(this.f1860b, i, max2, i2, 0, this.f1873o);
            i7 = this.f1860b.getMeasuredWidth() + m3799b(this.f1860b);
            i5 = Math.max(i5, this.f1860b.getMeasuredHeight() + m3802c(this.f1860b));
            i6 = ViewUtils.m3899a(i6, ViewCompat.m1180i(this.f1860b));
        }
        contentInsetStart = getContentInsetEnd();
        max2 += Math.max(contentInsetStart, i7);
        iArr[i3] = Math.max(0, contentInsetStart - i7);
        if (m3797a(this.f1859a)) {
            max2 += m3790a(this.f1859a, i, max2, i2, 0, iArr);
            i5 = Math.max(i5, this.f1859a.getMeasuredHeight() + m3802c(this.f1859a));
            i6 = ViewUtils.m3899a(i6, ViewCompat.m1180i(this.f1859a));
        }
        if (m3797a(this.f1864f)) {
            max2 += m3790a(this.f1864f, i, max2, i2, 0, iArr);
            i5 = Math.max(i5, this.f1864f.getMeasuredHeight() + m3802c(this.f1864f));
            i6 = ViewUtils.m3899a(i6, ViewCompat.m1180i(this.f1864f));
        }
        i4 = getChildCount();
        i3 = 0;
        int i8 = i5;
        i5 = i6;
        while (i3 < i4) {
            View childAt = getChildAt(i3);
            if (((LayoutParams) childAt.getLayoutParams()).f1842b != 0) {
                i7 = i5;
                contentInsetStart = i8;
            } else if (m3797a(childAt)) {
                max2 += m3790a(childAt, i, max2, i2, 0, iArr);
                max = Math.max(i8, childAt.getMeasuredHeight() + m3802c(childAt));
                i7 = ViewUtils.m3899a(i5, ViewCompat.m1180i(childAt));
                contentInsetStart = max;
            } else {
                i7 = i5;
                contentInsetStart = i8;
            }
            i3++;
            i5 = i7;
            i8 = contentInsetStart;
        }
        contentInsetStart = 0;
        i7 = 0;
        i6 = this.f1876r + this.f1877s;
        max = this.f1874p + this.f1875q;
        if (m3797a(this.f1861c)) {
            m3790a(this.f1861c, i, max2 + max, i2, i6, iArr);
            contentInsetStart = m3799b(this.f1861c) + this.f1861c.getMeasuredWidth();
            i7 = this.f1861c.getMeasuredHeight() + m3802c(this.f1861c);
            i5 = ViewUtils.m3899a(i5, ViewCompat.m1180i(this.f1861c));
        }
        if (m3797a(this.f1862d)) {
            contentInsetStart = Math.max(contentInsetStart, m3790a(this.f1862d, i, max2 + max, i2, i6 + i7, iArr));
            i7 += this.f1862d.getMeasuredHeight() + m3802c(this.f1862d);
            i5 = ViewUtils.m3899a(i5, ViewCompat.m1180i(this.f1862d));
        }
        contentInsetStart += max2;
        i7 = Math.max(i8, i7) + (getPaddingTop() + getPaddingBottom());
        contentInsetStart = ViewCompat.m1153a(Math.max(contentInsetStart + (getPaddingLeft() + getPaddingRight()), getSuggestedMinimumWidth()), i, -16777216 & i5);
        i7 = ViewCompat.m1153a(Math.max(i7, getSuggestedMinimumHeight()), i2, i5 << 16);
        if (m3812r()) {
            i7 = 0;
        }
        setMeasuredDimension(contentInsetStart, i7);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        Object obj;
        int i5;
        int i6;
        int i7;
        int measuredHeight;
        int measuredWidth;
        if (ViewCompat.m1179h(this) == 1) {
            obj = 1;
        } else {
            obj = null;
        }
        int width = getWidth();
        int height = getHeight();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int i8 = width - paddingRight;
        int[] iArr = this.f1848D;
        iArr[1] = 0;
        iArr[0] = 0;
        int m = ViewCompat.m1184m(this);
        if (!m3797a(this.f1863e)) {
            i5 = paddingLeft;
        } else if (obj != null) {
            i8 = m3800b(this.f1863e, i8, iArr, m);
            i5 = paddingLeft;
        } else {
            i5 = m3791a(this.f1863e, paddingLeft, iArr, m);
        }
        if (m3797a(this.f1867i)) {
            if (obj != null) {
                i8 = m3800b(this.f1867i, i8, iArr, m);
            } else {
                i5 = m3791a(this.f1867i, i5, iArr, m);
            }
        }
        if (m3797a(this.f1860b)) {
            if (obj != null) {
                i5 = m3791a(this.f1860b, i5, iArr, m);
            } else {
                i8 = m3800b(this.f1860b, i8, iArr, m);
            }
        }
        iArr[0] = Math.max(0, getContentInsetLeft() - i5);
        iArr[1] = Math.max(0, getContentInsetRight() - ((width - paddingRight) - i8));
        i5 = Math.max(i5, getContentInsetLeft());
        i8 = Math.min(i8, (width - paddingRight) - getContentInsetRight());
        if (m3797a(this.f1859a)) {
            if (obj != null) {
                i8 = m3800b(this.f1859a, i8, iArr, m);
            } else {
                i5 = m3791a(this.f1859a, i5, iArr, m);
            }
        }
        if (!m3797a(this.f1864f)) {
            i6 = i8;
            i7 = i5;
        } else if (obj != null) {
            i6 = m3800b(this.f1864f, i8, iArr, m);
            i7 = i5;
        } else {
            i6 = i8;
            i7 = m3791a(this.f1864f, i5, iArr, m);
        }
        boolean a = m3797a(this.f1861c);
        boolean a2 = m3797a(this.f1862d);
        i5 = 0;
        if (a) {
            LayoutParams layoutParams = (LayoutParams) this.f1861c.getLayoutParams();
            i5 = 0 + (layoutParams.bottomMargin + (layoutParams.topMargin + this.f1861c.getMeasuredHeight()));
        }
        if (a2) {
            layoutParams = (LayoutParams) this.f1862d.getLayoutParams();
            measuredHeight = (layoutParams.bottomMargin + (layoutParams.topMargin + this.f1862d.getMeasuredHeight())) + i5;
        } else {
            measuredHeight = i5;
        }
        if (a || a2) {
            int paddingTop2;
            layoutParams = (LayoutParams) (a ? this.f1861c : this.f1862d).getLayoutParams();
            LayoutParams layoutParams2 = (LayoutParams) (a2 ? this.f1862d : this.f1861c).getLayoutParams();
            Object obj2 = ((!a || this.f1861c.getMeasuredWidth() <= 0) && (!a2 || this.f1862d.getMeasuredWidth() <= 0)) ? null : 1;
            switch (this.f1879u & 112) {
                case C0110R.styleable.Theme_homeAsUpIndicator /*48*/:
                    paddingTop2 = (layoutParams.topMargin + getPaddingTop()) + this.f1876r;
                    break;
                case C0110R.styleable.Theme_panelMenuListTheme /*80*/:
                    paddingTop2 = (((height - paddingBottom) - layoutParams2.bottomMargin) - this.f1877s) - measuredHeight;
                    break;
                default:
                    paddingTop2 = (((height - paddingTop) - paddingBottom) - measuredHeight) / 2;
                    if (paddingTop2 < layoutParams.topMargin + this.f1876r) {
                        i8 = layoutParams.topMargin + this.f1876r;
                    } else {
                        measuredHeight = (((height - paddingBottom) - measuredHeight) - paddingTop2) - paddingTop;
                        if (measuredHeight < layoutParams.bottomMargin + this.f1877s) {
                            i8 = Math.max(0, paddingTop2 - ((layoutParams2.bottomMargin + this.f1877s) - measuredHeight));
                        } else {
                            i8 = paddingTop2;
                        }
                    }
                    paddingTop2 = paddingTop + i8;
                    break;
            }
            if (obj != null) {
                i8 = (obj2 != null ? this.f1874p : 0) - iArr[1];
                i5 = i6 - Math.max(0, i8);
                iArr[1] = Math.max(0, -i8);
                if (a) {
                    layoutParams = (LayoutParams) this.f1861c.getLayoutParams();
                    measuredWidth = i5 - this.f1861c.getMeasuredWidth();
                    i6 = this.f1861c.getMeasuredHeight() + paddingTop2;
                    this.f1861c.layout(measuredWidth, paddingTop2, i5, i6);
                    paddingTop2 = i6 + layoutParams.bottomMargin;
                    i6 = measuredWidth - this.f1875q;
                } else {
                    i6 = i5;
                }
                if (a2) {
                    layoutParams = (LayoutParams) this.f1862d.getLayoutParams();
                    measuredWidth = layoutParams.topMargin + paddingTop2;
                    measuredHeight = this.f1862d.getMeasuredHeight() + measuredWidth;
                    this.f1862d.layout(i5 - this.f1862d.getMeasuredWidth(), measuredWidth, i5, measuredHeight);
                    i8 = layoutParams.bottomMargin + measuredHeight;
                    i8 = i5 - this.f1875q;
                } else {
                    i8 = i5;
                }
                if (obj2 != null) {
                    i8 = Math.min(i6, i8);
                } else {
                    i8 = i5;
                }
                i6 = i8;
            } else {
                i8 = (obj2 != null ? this.f1874p : 0) - iArr[0];
                i7 += Math.max(0, i8);
                iArr[0] = Math.max(0, -i8);
                if (a) {
                    layoutParams = (LayoutParams) this.f1861c.getLayoutParams();
                    i5 = this.f1861c.getMeasuredWidth() + i7;
                    measuredWidth = this.f1861c.getMeasuredHeight() + paddingTop2;
                    this.f1861c.layout(i7, paddingTop2, i5, measuredWidth);
                    i8 = layoutParams.bottomMargin + measuredWidth;
                    measuredWidth = i5 + this.f1875q;
                    i5 = i8;
                } else {
                    measuredWidth = i7;
                    i5 = paddingTop2;
                }
                if (a2) {
                    layoutParams = (LayoutParams) this.f1862d.getLayoutParams();
                    i5 += layoutParams.topMargin;
                    paddingTop2 = this.f1862d.getMeasuredWidth() + i7;
                    measuredHeight = this.f1862d.getMeasuredHeight() + i5;
                    this.f1862d.layout(i7, i5, paddingTop2, measuredHeight);
                    i8 = layoutParams.bottomMargin + measuredHeight;
                    i8 = this.f1875q + paddingTop2;
                } else {
                    i8 = i7;
                }
                if (obj2 != null) {
                    i7 = Math.max(measuredWidth, i8);
                }
            }
        }
        m3796a(this.f1846B, 3);
        int size = this.f1846B.size();
        i5 = i7;
        for (measuredWidth = 0; measuredWidth < size; measuredWidth++) {
            i5 = m3791a((View) this.f1846B.get(measuredWidth), i5, iArr, m);
        }
        m3796a(this.f1846B, 5);
        i7 = this.f1846B.size();
        for (measuredWidth = 0; measuredWidth < i7; measuredWidth++) {
            i6 = m3800b((View) this.f1846B.get(measuredWidth), i6, iArr, m);
        }
        m3796a(this.f1846B, 1);
        measuredWidth = m3792a(this.f1846B, iArr);
        i8 = ((((width - paddingLeft) - paddingRight) / 2) + paddingLeft) - (measuredWidth / 2);
        measuredWidth += i8;
        if (i8 < i5) {
            i8 = i5;
        } else if (measuredWidth > i6) {
            i8 -= measuredWidth - i6;
        }
        paddingLeft = this.f1846B.size();
        measuredWidth = i8;
        for (i5 = 0; i5 < paddingLeft; i5++) {
            measuredWidth = m3791a((View) this.f1846B.get(i5), measuredWidth, iArr, m);
        }
        this.f1846B.clear();
    }

    private int m3792a(List<View> list, int[] iArr) {
        int i = iArr[0];
        int i2 = iArr[1];
        int size = list.size();
        int i3 = 0;
        int i4 = 0;
        int i5 = i2;
        int i6 = i;
        while (i3 < size) {
            View view = (View) list.get(i3);
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            i6 = layoutParams.leftMargin - i6;
            i = layoutParams.rightMargin - i5;
            int max = Math.max(0, i6);
            int max2 = Math.max(0, i);
            i6 = Math.max(0, -i6);
            i5 = Math.max(0, -i);
            i3++;
            i4 += (view.getMeasuredWidth() + max) + max2;
        }
        return i4;
    }

    private int m3791a(View view, int i, int[] iArr, int i2) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int i3 = layoutParams.leftMargin - iArr[0];
        int max = Math.max(0, i3) + i;
        iArr[0] = Math.max(0, -i3);
        i3 = m3789a(view, i2);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(max, i3, max + measuredWidth, view.getMeasuredHeight() + i3);
        return (layoutParams.rightMargin + measuredWidth) + max;
    }

    private int m3800b(View view, int i, int[] iArr, int i2) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int i3 = layoutParams.rightMargin - iArr[1];
        int max = i - Math.max(0, i3);
        iArr[1] = Math.max(0, -i3);
        i3 = m3789a(view, i2);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(max - measuredWidth, i3, max, view.getMeasuredHeight() + i3);
        return max - (layoutParams.leftMargin + measuredWidth);
    }

    private int m3789a(View view, int i) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int measuredHeight = view.getMeasuredHeight();
        int i2 = i > 0 ? (measuredHeight - i) / 2 : 0;
        switch (m3788a(layoutParams.a)) {
            case C0110R.styleable.Theme_homeAsUpIndicator /*48*/:
                return getPaddingTop() - i2;
            case C0110R.styleable.Theme_panelMenuListTheme /*80*/:
                return (((getHeight() - getPaddingBottom()) - measuredHeight) - layoutParams.bottomMargin) - i2;
            default:
                int i3;
                int paddingTop = getPaddingTop();
                int paddingBottom = getPaddingBottom();
                int height = getHeight();
                i2 = (((height - paddingTop) - paddingBottom) - measuredHeight) / 2;
                if (i2 < layoutParams.topMargin) {
                    i3 = layoutParams.topMargin;
                } else {
                    measuredHeight = (((height - paddingBottom) - measuredHeight) - i2) - paddingTop;
                    i3 = measuredHeight < layoutParams.bottomMargin ? Math.max(0, i2 - (layoutParams.bottomMargin - measuredHeight)) : i2;
                }
                return i3 + paddingTop;
        }
    }

    private int m3788a(int i) {
        int i2 = i & 112;
        switch (i2) {
            case C1128R.styleable.StickyListHeadersListView_android_dividerHeight /*16*/:
            case C0110R.styleable.Theme_homeAsUpIndicator /*48*/:
            case C0110R.styleable.Theme_panelMenuListTheme /*80*/:
                return i2;
            default:
                return this.f1879u & 112;
        }
    }

    private void m3796a(List<View> list, int i) {
        int i2 = 1;
        int i3 = 0;
        if (ViewCompat.m1179h(this) != 1) {
            i2 = 0;
        }
        int childCount = getChildCount();
        int a = GravityCompat.m899a(i, ViewCompat.m1179h(this));
        list.clear();
        LayoutParams layoutParams;
        if (i2 != 0) {
            for (i3 = childCount - 1; i3 >= 0; i3--) {
                View childAt = getChildAt(i3);
                layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.f1842b == 0 && m3797a(childAt) && m3798b(layoutParams.a) == a) {
                    list.add(childAt);
                }
            }
            return;
        }
        while (i3 < childCount) {
            View childAt2 = getChildAt(i3);
            layoutParams = (LayoutParams) childAt2.getLayoutParams();
            if (layoutParams.f1842b == 0 && m3797a(childAt2) && m3798b(layoutParams.a) == a) {
                list.add(childAt2);
            }
            i3++;
        }
    }

    private int m3798b(int i) {
        int h = ViewCompat.m1179h(this);
        int a = GravityCompat.m899a(i, h) & 7;
        switch (a) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                return a;
            default:
                return h == 1 ? 5 : 3;
        }
    }

    private boolean m3797a(View view) {
        return (view == null || view.getParent() != this || view.getVisibility() == 8) ? false : true;
    }

    private int m3799b(View view) {
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
        return MarginLayoutParamsCompat.m931b(marginLayoutParams) + MarginLayoutParamsCompat.m930a(marginLayoutParams);
    }

    private int m3802c(View view) {
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.bottomMargin + marginLayoutParams.topMargin;
    }

    public LayoutParams m3813a(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    protected LayoutParams m3814a(android.view.ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LayoutParams) {
            return new LayoutParams((LayoutParams) layoutParams);
        }
        if (layoutParams instanceof android.support.v7.app.ActionBar.LayoutParams) {
            return new LayoutParams((android.support.v7.app.ActionBar.LayoutParams) layoutParams);
        }
        if (layoutParams instanceof MarginLayoutParams) {
            return new LayoutParams((MarginLayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }

    protected LayoutParams m3828i() {
        return new LayoutParams(-2, -2);
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof LayoutParams);
    }

    public DecorToolbar getWrapper() {
        if (this.f1851G == null) {
            this.f1851G = new ToolbarWidgetWrapper(this, true);
        }
        return this.f1851G;
    }

    void m3829j() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (!(((LayoutParams) childAt.getLayoutParams()).f1842b == 2 || childAt == this.f1860b)) {
                removeViewAt(childCount);
                this.f1847C.add(childAt);
            }
        }
    }

    void m3830k() {
        for (int size = this.f1847C.size() - 1; size >= 0; size--) {
            addView((View) this.f1847C.get(size));
        }
        this.f1847C.clear();
    }

    private boolean m3805d(View view) {
        return view.getParent() == this || this.f1847C.contains(view);
    }

    public void setCollapsible(boolean z) {
        this.f1856L = z;
        requestLayout();
    }

    public void m3818a(Callback callback, MenuBuilder.Callback callback2) {
        this.f1854J = callback;
        this.f1855K = callback2;
    }
}
