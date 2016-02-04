package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v7.appcompat.C0057R;
import android.support.v7.view.menu.ActionMenuItem;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPresenter;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window.Callback;

public class ToolbarWidgetWrapper implements DecorToolbar {
    private Toolbar f1890a;
    private int f1891b;
    private View f1892c;
    private View f1893d;
    private Drawable f1894e;
    private Drawable f1895f;
    private Drawable f1896g;
    private boolean f1897h;
    private CharSequence f1898i;
    private CharSequence f1899j;
    private CharSequence f1900k;
    private Callback f1901l;
    private boolean f1902m;
    private ActionMenuPresenter f1903n;
    private int f1904o;
    private final TintManager f1905p;
    private int f1906q;
    private Drawable f1907r;

    /* renamed from: android.support.v7.widget.ToolbarWidgetWrapper.1 */
    class C01021 implements OnClickListener {
        final ActionMenuItem f1885a;
        final /* synthetic */ ToolbarWidgetWrapper f1886b;

        C01021(ToolbarWidgetWrapper toolbarWidgetWrapper) {
            this.f1886b = toolbarWidgetWrapper;
            this.f1885a = new ActionMenuItem(this.f1886b.f1890a.getContext(), 0, 16908332, 0, 0, this.f1886b.f1898i);
        }

        public void onClick(View view) {
            if (this.f1886b.f1901l != null && this.f1886b.f1902m) {
                this.f1886b.f1901l.onMenuItemSelected(0, this.f1885a);
            }
        }
    }

    /* renamed from: android.support.v7.widget.ToolbarWidgetWrapper.2 */
    class C01032 extends ViewPropertyAnimatorListenerAdapter {
        final /* synthetic */ int f1887a;
        final /* synthetic */ ToolbarWidgetWrapper f1888b;
        private boolean f1889c;

        C01032(ToolbarWidgetWrapper toolbarWidgetWrapper, int i) {
            this.f1888b = toolbarWidgetWrapper;
            this.f1887a = i;
            this.f1889c = false;
        }

        public void m3831a(View view) {
            this.f1888b.f1890a.setVisibility(0);
        }

        public void m3832b(View view) {
            if (!this.f1889c) {
                this.f1888b.f1890a.setVisibility(this.f1887a);
            }
        }

        public void m3833c(View view) {
            this.f1889c = true;
        }
    }

    public ToolbarWidgetWrapper(Toolbar toolbar, boolean z) {
        this(toolbar, z, C0057R.string.abc_action_bar_up_description, C0057R.drawable.abc_ic_ab_back_mtrl_am_alpha);
    }

    public ToolbarWidgetWrapper(Toolbar toolbar, boolean z, int i, int i2) {
        this.f1904o = 0;
        this.f1906q = 0;
        this.f1890a = toolbar;
        this.f1898i = toolbar.getTitle();
        this.f1899j = toolbar.getSubtitle();
        this.f1897h = this.f1898i != null;
        this.f1896g = toolbar.getNavigationIcon();
        if (z) {
            TintTypedArray a = TintTypedArray.m3759a(toolbar.getContext(), null, C0057R.styleable.ActionBar, C0057R.attr.actionBarStyle, 0);
            CharSequence c = a.m3769c(C0057R.styleable.ActionBar_title);
            if (!TextUtils.isEmpty(c)) {
                m3857b(c);
            }
            c = a.m3769c(C0057R.styleable.ActionBar_subtitle);
            if (!TextUtils.isEmpty(c)) {
                m3861c(c);
            }
            Drawable a2 = a.m3762a(C0057R.styleable.ActionBar_logo);
            if (a2 != null) {
                m3860c(a2);
            }
            a2 = a.m3762a(C0057R.styleable.ActionBar_icon);
            if (this.f1896g == null && a2 != null) {
                m3846a(a2);
            }
            a2 = a.m3762a(C0057R.styleable.ActionBar_homeAsUpIndicator);
            if (a2 != null) {
                m3865d(a2);
            }
            m3859c(a.m3761a(C0057R.styleable.ActionBar_displayOptions, 0));
            int g = a.m3775g(C0057R.styleable.ActionBar_customNavigationLayout, 0);
            if (g != 0) {
                m3850a(LayoutInflater.from(this.f1890a.getContext()).inflate(g, this.f1890a, false));
                m3859c(this.f1891b | 16);
            }
            g = a.m3774f(C0057R.styleable.ActionBar_height, 0);
            if (g > 0) {
                LayoutParams layoutParams = this.f1890a.getLayoutParams();
                layoutParams.height = g;
                this.f1890a.setLayoutParams(layoutParams);
            }
            g = a.m3770d(C0057R.styleable.ActionBar_contentInsetStart, -1);
            int d = a.m3770d(C0057R.styleable.ActionBar_contentInsetEnd, -1);
            if (g >= 0 || d >= 0) {
                this.f1890a.m3815a(Math.max(g, 0), Math.max(d, 0));
            }
            g = a.m3775g(C0057R.styleable.ActionBar_titleTextStyle, 0);
            if (g != 0) {
                this.f1890a.m3816a(this.f1890a.getContext(), g);
            }
            g = a.m3775g(C0057R.styleable.ActionBar_subtitleTextStyle, 0);
            if (g != 0) {
                this.f1890a.m3820b(this.f1890a.getContext(), g);
            }
            int g2 = a.m3775g(C0057R.styleable.ActionBar_popupTheme, 0);
            if (g2 != 0) {
                this.f1890a.setPopupTheme(g2);
            }
            a.m3763a();
            this.f1905p = a.m3767b();
        } else {
            this.f1891b = m3839r();
            this.f1905p = TintManager.m3737a(toolbar.getContext());
        }
        m3864d(i);
        this.f1900k = this.f1890a.getNavigationContentDescription();
        m3856b(this.f1905p.m3753a(i2));
        this.f1890a.setNavigationOnClickListener(new C01021(this));
    }

    public void m3864d(int i) {
        if (i != this.f1906q) {
            this.f1906q = i;
            if (TextUtils.isEmpty(this.f1890a.getNavigationContentDescription())) {
                m3868e(this.f1906q);
            }
        }
    }

    public void m3856b(Drawable drawable) {
        if (this.f1907r != drawable) {
            this.f1907r = drawable;
            m3842u();
        }
    }

    private int m3839r() {
        if (this.f1890a.getNavigationIcon() != null) {
            return 15;
        }
        return 11;
    }

    public ViewGroup m3844a() {
        return this.f1890a;
    }

    public Context m3854b() {
        return this.f1890a.getContext();
    }

    public boolean m3862c() {
        return this.f1890a.m3826g();
    }

    public void m3863d() {
        this.f1890a.m3827h();
    }

    public void m3851a(Callback callback) {
        this.f1901l = callback;
    }

    public void m3852a(CharSequence charSequence) {
        if (!this.f1897h) {
            m3838e(charSequence);
        }
    }

    public CharSequence m3867e() {
        return this.f1890a.getTitle();
    }

    public void m3857b(CharSequence charSequence) {
        this.f1897h = true;
        m3838e(charSequence);
    }

    private void m3838e(CharSequence charSequence) {
        this.f1898i = charSequence;
        if ((this.f1891b & 8) != 0) {
            this.f1890a.setTitle(charSequence);
        }
    }

    public void m3861c(CharSequence charSequence) {
        this.f1899j = charSequence;
        if ((this.f1891b & 8) != 0) {
            this.f1890a.setSubtitle(charSequence);
        }
    }

    public void m3869f() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    public void m3870g() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    public void m3845a(int i) {
        m3846a(i != 0 ? this.f1905p.m3753a(i) : null);
    }

    public void m3846a(Drawable drawable) {
        this.f1894e = drawable;
        m3840s();
    }

    public void m3855b(int i) {
        m3860c(i != 0 ? this.f1905p.m3753a(i) : null);
    }

    public void m3860c(Drawable drawable) {
        this.f1895f = drawable;
        m3840s();
    }

    private void m3840s() {
        Drawable drawable = null;
        if ((this.f1891b & 2) != 0) {
            drawable = (this.f1891b & 1) != 0 ? this.f1895f != null ? this.f1895f : this.f1894e : this.f1894e;
        }
        this.f1890a.setLogo(drawable);
    }

    public boolean m3871h() {
        return this.f1890a.m3819a();
    }

    public boolean m3872i() {
        return this.f1890a.m3821b();
    }

    public boolean m3873j() {
        return this.f1890a.m3822c();
    }

    public boolean m3874k() {
        return this.f1890a.m3823d();
    }

    public boolean m3875l() {
        return this.f1890a.m3824e();
    }

    public void m3876m() {
        this.f1902m = true;
    }

    public void m3849a(Menu menu, MenuPresenter.Callback callback) {
        if (this.f1903n == null) {
            this.f1903n = new ActionMenuPresenter(this.f1890a.getContext());
            this.f1903n.m2359a(C0057R.id.action_menu_presenter);
        }
        this.f1903n.m2363a(callback);
        this.f1890a.m3817a((MenuBuilder) menu, this.f1903n);
    }

    public void m3877n() {
        this.f1890a.m3825f();
    }

    public int m3878o() {
        return this.f1891b;
    }

    public void m3859c(int i) {
        int i2 = this.f1891b ^ i;
        this.f1891b = i;
        if (i2 != 0) {
            if ((i2 & 4) != 0) {
                if ((i & 4) != 0) {
                    m3842u();
                    m3841t();
                } else {
                    this.f1890a.setNavigationIcon(null);
                }
            }
            if ((i2 & 3) != 0) {
                m3840s();
            }
            if ((i2 & 8) != 0) {
                if ((i & 8) != 0) {
                    this.f1890a.setTitle(this.f1898i);
                    this.f1890a.setSubtitle(this.f1899j);
                } else {
                    this.f1890a.setTitle(null);
                    this.f1890a.setSubtitle(null);
                }
            }
            if ((i2 & 16) != 0 && this.f1893d != null) {
                if ((i & 16) != 0) {
                    this.f1890a.addView(this.f1893d);
                } else {
                    this.f1890a.removeView(this.f1893d);
                }
            }
        }
    }

    public void m3848a(ScrollingTabContainerView scrollingTabContainerView) {
        if (this.f1892c != null && this.f1892c.getParent() == this.f1890a) {
            this.f1890a.removeView(this.f1892c);
        }
        this.f1892c = scrollingTabContainerView;
        if (scrollingTabContainerView != null && this.f1904o == 2) {
            this.f1890a.addView(this.f1892c, 0);
            Toolbar.LayoutParams layoutParams = (Toolbar.LayoutParams) this.f1892c.getLayoutParams();
            layoutParams.width = -2;
            layoutParams.height = -2;
            layoutParams.a = 8388691;
            scrollingTabContainerView.setAllowCollapse(true);
        }
    }

    public void m3853a(boolean z) {
        this.f1890a.setCollapsible(z);
    }

    public void m3858b(boolean z) {
    }

    public int m3879p() {
        return this.f1904o;
    }

    public void m3850a(View view) {
        if (!(this.f1893d == null || (this.f1891b & 16) == 0)) {
            this.f1890a.removeView(this.f1893d);
        }
        this.f1893d = view;
        if (view != null && (this.f1891b & 16) != 0) {
            this.f1890a.addView(this.f1893d);
        }
    }

    public ViewPropertyAnimatorCompat m3843a(int i, long j) {
        return ViewCompat.m1185n(this.f1890a).m1398a(i == 0 ? 1.0f : 0.0f).m1399a(j).m1400a(new C01032(this, i));
    }

    public void m3865d(Drawable drawable) {
        this.f1896g = drawable;
        m3842u();
    }

    public void m3866d(CharSequence charSequence) {
        this.f1900k = charSequence;
        m3841t();
    }

    public void m3868e(int i) {
        m3866d(i == 0 ? null : m3854b().getString(i));
    }

    private void m3841t() {
        if ((this.f1891b & 4) == 0) {
            return;
        }
        if (TextUtils.isEmpty(this.f1900k)) {
            this.f1890a.setNavigationContentDescription(this.f1906q);
        } else {
            this.f1890a.setNavigationContentDescription(this.f1900k);
        }
    }

    private void m3842u() {
        if ((this.f1891b & 4) != 0) {
            this.f1890a.setNavigationIcon(this.f1896g != null ? this.f1896g : this.f1907r);
        }
    }

    public void m3847a(MenuPresenter.Callback callback, MenuBuilder.Callback callback2) {
        this.f1890a.m3818a(callback, callback2);
    }

    public Menu m3880q() {
        return this.f1890a.getMenu();
    }
}
