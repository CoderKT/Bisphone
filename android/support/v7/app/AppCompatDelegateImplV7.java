package android.support.v7.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.media.AudioManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.appcompat.C0057R;
import android.support.v7.view.ActionMode;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.view.StandaloneActionMode;
import android.support.v7.view.menu.ListMenuPresenter;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuBuilder.Callback;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.ActionBarContextView;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.ContentFrameLayout.OnAttachListener;
import android.support.v7.widget.DecorContentParent;
import android.support.v7.widget.FitWindowsViewGroup;
import android.support.v7.widget.FitWindowsViewGroup.OnFitSystemWindowsListener;
import android.support.v7.widget.TintManager;
import android.support.v7.widget.ViewStubCompat;
import android.support.v7.widget.ViewUtils;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.LayoutInflater.Factory;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import app.C0110R;
import se.emilsjolander.stickylistheaders.C1128R;

class AppCompatDelegateImplV7 extends AppCompatDelegateImplBase implements LayoutInflaterFactory, Callback {
    private boolean f766A;
    private PanelFeatureState[] f767B;
    private PanelFeatureState f768C;
    private boolean f769D;
    private boolean f770E;
    private int f771F;
    private final Runnable f772G;
    private boolean f773H;
    private Rect f774I;
    private Rect f775J;
    private AppCompatViewInflater f776K;
    ActionMode f777l;
    ActionBarContextView f778m;
    PopupWindow f779n;
    Runnable f780o;
    ViewPropertyAnimatorCompat f781p;
    private DecorContentParent f782q;
    private ActionMenuPresenterCallback f783r;
    private PanelMenuPresenterCallback f784s;
    private boolean f785t;
    private ViewGroup f786u;
    private ViewGroup f787v;
    private TextView f788w;
    private View f789x;
    private boolean f790y;
    private boolean f791z;

    /* renamed from: android.support.v7.app.AppCompatDelegateImplV7.1 */
    class C00451 implements Runnable {
        final /* synthetic */ AppCompatDelegateImplV7 f795a;

        C00451(AppCompatDelegateImplV7 appCompatDelegateImplV7) {
            this.f795a = appCompatDelegateImplV7;
        }

        public void run() {
            if ((this.f795a.f771F & 1) != 0) {
                this.f795a.m2062e(0);
            }
            if ((this.f795a.f771F & 4096) != 0) {
                this.f795a.m2062e(C0110R.styleable.Theme_spinnerStyle);
            }
            this.f795a.f770E = false;
            this.f795a.f771F = 0;
        }
    }

    /* renamed from: android.support.v7.app.AppCompatDelegateImplV7.2 */
    class C00462 implements OnApplyWindowInsetsListener {
        final /* synthetic */ AppCompatDelegateImplV7 f796a;

        C00462(AppCompatDelegateImplV7 appCompatDelegateImplV7) {
            this.f796a = appCompatDelegateImplV7;
        }

        public WindowInsetsCompat m2103a(View view, WindowInsetsCompat windowInsetsCompat) {
            int b = windowInsetsCompat.m1426b();
            int c = this.f796a.m2064f(b);
            if (b != c) {
                windowInsetsCompat = windowInsetsCompat.m1425a(windowInsetsCompat.m1424a(), c, windowInsetsCompat.m1427c(), windowInsetsCompat.m1428d());
            }
            return ViewCompat.m1155a(view, windowInsetsCompat);
        }
    }

    /* renamed from: android.support.v7.app.AppCompatDelegateImplV7.3 */
    class C00473 implements OnFitSystemWindowsListener {
        final /* synthetic */ AppCompatDelegateImplV7 f797a;

        C00473(AppCompatDelegateImplV7 appCompatDelegateImplV7) {
            this.f797a = appCompatDelegateImplV7;
        }

        public void m2105a(Rect rect) {
            rect.top = this.f797a.m2064f(rect.top);
        }
    }

    /* renamed from: android.support.v7.app.AppCompatDelegateImplV7.4 */
    class C00484 implements OnAttachListener {
        final /* synthetic */ AppCompatDelegateImplV7 f798a;

        C00484(AppCompatDelegateImplV7 appCompatDelegateImplV7) {
            this.f798a = appCompatDelegateImplV7;
        }

        public void m2108a() {
        }

        public void m2109b() {
            this.f798a.m2071r();
        }
    }

    /* renamed from: android.support.v7.app.AppCompatDelegateImplV7.5 */
    class C00505 implements Runnable {
        final /* synthetic */ AppCompatDelegateImplV7 f800a;

        /* renamed from: android.support.v7.app.AppCompatDelegateImplV7.5.1 */
        class C00491 extends ViewPropertyAnimatorListenerAdapter {
            final /* synthetic */ C00505 f799a;

            C00491(C00505 c00505) {
                this.f799a = c00505;
            }

            public void m2111b(View view) {
                ViewCompat.m1171c(this.f799a.f800a.f778m, 1.0f);
                this.f799a.f800a.f781p.m1400a(null);
                this.f799a.f800a.f781p = null;
            }

            public void m2110a(View view) {
                this.f799a.f800a.f778m.setVisibility(0);
            }
        }

        C00505(AppCompatDelegateImplV7 appCompatDelegateImplV7) {
            this.f800a = appCompatDelegateImplV7;
        }

        public void run() {
            this.f800a.f779n.showAtLocation(this.f800a.f778m, 55, 0, 0);
            this.f800a.m2069p();
            ViewCompat.m1171c(this.f800a.f778m, 0.0f);
            this.f800a.f781p = ViewCompat.m1185n(this.f800a.f778m).m1398a(1.0f);
            this.f800a.f781p.m1400a(new C00491(this));
        }
    }

    /* renamed from: android.support.v7.app.AppCompatDelegateImplV7.6 */
    class C00516 extends ViewPropertyAnimatorListenerAdapter {
        final /* synthetic */ AppCompatDelegateImplV7 f801a;

        C00516(AppCompatDelegateImplV7 appCompatDelegateImplV7) {
            this.f801a = appCompatDelegateImplV7;
        }

        public void m2113b(View view) {
            ViewCompat.m1171c(this.f801a.f778m, 1.0f);
            this.f801a.f781p.m1400a(null);
            this.f801a.f781p = null;
        }

        public void m2112a(View view) {
            this.f801a.f778m.setVisibility(0);
            this.f801a.f778m.sendAccessibilityEvent(32);
            if (this.f801a.f778m.getParent() != null) {
                ViewCompat.m1187p((View) this.f801a.f778m.getParent());
            }
        }
    }

    final class ActionMenuPresenterCallback implements MenuPresenter.Callback {
        final /* synthetic */ AppCompatDelegateImplV7 f802a;

        private ActionMenuPresenterCallback(AppCompatDelegateImplV7 appCompatDelegateImplV7) {
            this.f802a = appCompatDelegateImplV7;
        }

        public boolean m2117a(MenuBuilder menuBuilder) {
            Window.Callback j = this.f802a.m2030j();
            if (j != null) {
                j.onMenuOpened(C0110R.styleable.Theme_spinnerStyle, menuBuilder);
            }
            return true;
        }

        public void m2116a(MenuBuilder menuBuilder, boolean z) {
            this.f802a.m2052b(menuBuilder);
        }
    }

    class ActionModeCallbackWrapperV7 implements ActionMode.Callback {
        final /* synthetic */ AppCompatDelegateImplV7 f804a;
        private ActionMode.Callback f805b;

        /* renamed from: android.support.v7.app.AppCompatDelegateImplV7.ActionModeCallbackWrapperV7.1 */
        class C00521 extends ViewPropertyAnimatorListenerAdapter {
            final /* synthetic */ ActionModeCallbackWrapperV7 f803a;

            C00521(ActionModeCallbackWrapperV7 actionModeCallbackWrapperV7) {
                this.f803a = actionModeCallbackWrapperV7;
            }

            public void m2118b(View view) {
                this.f803a.f804a.f778m.setVisibility(8);
                if (this.f803a.f804a.f779n != null) {
                    this.f803a.f804a.f779n.dismiss();
                } else if (this.f803a.f804a.f778m.getParent() instanceof View) {
                    ViewCompat.m1187p((View) this.f803a.f804a.f778m.getParent());
                }
                this.f803a.f804a.f778m.removeAllViews();
                this.f803a.f804a.f781p.m1400a(null);
                this.f803a.f804a.f781p = null;
            }
        }

        public ActionModeCallbackWrapperV7(AppCompatDelegateImplV7 appCompatDelegateImplV7, ActionMode.Callback callback) {
            this.f804a = appCompatDelegateImplV7;
            this.f805b = callback;
        }

        public boolean m2124a(ActionMode actionMode, Menu menu) {
            return this.f805b.m2120a(actionMode, menu);
        }

        public boolean m2126b(ActionMode actionMode, Menu menu) {
            return this.f805b.m2122b(actionMode, menu);
        }

        public boolean m2125a(ActionMode actionMode, MenuItem menuItem) {
            return this.f805b.m2121a(actionMode, menuItem);
        }

        public void m2123a(ActionMode actionMode) {
            this.f805b.m2119a(actionMode);
            if (this.f804a.f779n != null) {
                this.f804a.b.getDecorView().removeCallbacks(this.f804a.f780o);
            }
            if (this.f804a.f778m != null) {
                this.f804a.m2069p();
                this.f804a.f781p = ViewCompat.m1185n(this.f804a.f778m).m1398a(0.0f);
                this.f804a.f781p.m1400a(new C00521(this));
            }
            if (this.f804a.e != null) {
                this.f804a.e.m1995b(this.f804a.f777l);
            }
            this.f804a.f777l = null;
        }
    }

    class ListMenuDecorView extends ContentFrameLayout {
        final /* synthetic */ AppCompatDelegateImplV7 f814a;

        public ListMenuDecorView(AppCompatDelegateImplV7 appCompatDelegateImplV7, Context context) {
            this.f814a = appCompatDelegateImplV7;
            super(context);
        }

        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return this.f814a.m2084a(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getAction() != 0 || !m2129a((int) motionEvent.getX(), (int) motionEvent.getY())) {
                return super.onInterceptTouchEvent(motionEvent);
            }
            this.f814a.m2056c(0);
            return true;
        }

        public void setBackgroundResource(int i) {
            setBackgroundDrawable(TintManager.m3736a(getContext(), i));
        }

        private boolean m2129a(int i, int i2) {
            return i < -5 || i2 < -5 || i > getWidth() + 5 || i2 > getHeight() + 5;
        }
    }

    final class PanelFeatureState {
        int f815a;
        int f816b;
        int f817c;
        int f818d;
        int f819e;
        int f820f;
        ViewGroup f821g;
        View f822h;
        View f823i;
        MenuBuilder f824j;
        ListMenuPresenter f825k;
        Context f826l;
        boolean f827m;
        boolean f828n;
        boolean f829o;
        public boolean f830p;
        boolean f831q;
        boolean f832r;
        Bundle f833s;

        PanelFeatureState(int i) {
            this.f815a = i;
            this.f831q = false;
        }

        public boolean m2133a() {
            if (this.f822h == null) {
                return false;
            }
            if (this.f823i != null || this.f825k.m2392a().getCount() > 0) {
                return true;
            }
            return false;
        }

        void m2131a(Context context) {
            TypedValue typedValue = new TypedValue();
            Theme newTheme = context.getResources().newTheme();
            newTheme.setTo(context.getTheme());
            newTheme.resolveAttribute(C0057R.attr.actionBarPopupTheme, typedValue, true);
            if (typedValue.resourceId != 0) {
                newTheme.applyStyle(typedValue.resourceId, true);
            }
            newTheme.resolveAttribute(C0057R.attr.panelMenuListTheme, typedValue, true);
            if (typedValue.resourceId != 0) {
                newTheme.applyStyle(typedValue.resourceId, true);
            } else {
                newTheme.applyStyle(C0057R.style.Theme_AppCompat_CompactMenu, true);
            }
            Context contextThemeWrapper = new ContextThemeWrapper(context, 0);
            contextThemeWrapper.getTheme().setTo(newTheme);
            this.f826l = contextThemeWrapper;
            TypedArray obtainStyledAttributes = contextThemeWrapper.obtainStyledAttributes(C0057R.styleable.Theme);
            this.f816b = obtainStyledAttributes.getResourceId(C0057R.styleable.Theme_panelBackground, 0);
            this.f820f = obtainStyledAttributes.getResourceId(C0057R.styleable.Theme_android_windowAnimationStyle, 0);
            obtainStyledAttributes.recycle();
        }

        void m2132a(MenuBuilder menuBuilder) {
            if (menuBuilder != this.f824j) {
                if (this.f824j != null) {
                    this.f824j.m2431b(this.f825k);
                }
                this.f824j = menuBuilder;
                if (menuBuilder != null && this.f825k != null) {
                    menuBuilder.m2420a(this.f825k);
                }
            }
        }

        MenuView m2130a(MenuPresenter.Callback callback) {
            if (this.f824j == null) {
                return null;
            }
            if (this.f825k == null) {
                this.f825k = new ListMenuPresenter(this.f826l, C0057R.layout.abc_list_menu_item_layout);
                this.f825k.m2395a(callback);
                this.f824j.m2420a(this.f825k);
            }
            return this.f825k.m2391a(this.f821g);
        }
    }

    final class PanelMenuPresenterCallback implements MenuPresenter.Callback {
        final /* synthetic */ AppCompatDelegateImplV7 f834a;

        private PanelMenuPresenterCallback(AppCompatDelegateImplV7 appCompatDelegateImplV7) {
            this.f834a = appCompatDelegateImplV7;
        }

        public void m2134a(MenuBuilder menuBuilder, boolean z) {
            Menu menu;
            Menu p = menuBuilder.m2451p();
            boolean z2 = p != menuBuilder;
            AppCompatDelegateImplV7 appCompatDelegateImplV7 = this.f834a;
            if (z2) {
                menu = p;
            }
            PanelFeatureState a = appCompatDelegateImplV7.m2037a(menu);
            if (a == null) {
                return;
            }
            if (z2) {
                this.f834a.m2038a(a.f815a, a, p);
                this.f834a.m2040a(a, true);
                return;
            }
            this.f834a.m2040a(a, z);
        }

        public boolean m2135a(MenuBuilder menuBuilder) {
            if (menuBuilder == null && this.f834a.g) {
                Window.Callback j = this.f834a.m2030j();
                if (!(j == null || this.f834a.m2029i())) {
                    j.onMenuOpened(C0110R.styleable.Theme_spinnerStyle, menuBuilder);
                }
            }
            return true;
        }
    }

    AppCompatDelegateImplV7(Context context, Window window, AppCompatCallback appCompatCallback) {
        super(context, window, appCompatCallback);
        this.f781p = null;
        this.f772G = new C00451(this);
    }

    public void m2077a(Bundle bundle) {
        this.f786u = (ViewGroup) this.b.getDecorView();
        if ((this.c instanceof Activity) && NavUtils.m506a((Activity) this.c) != null) {
            ActionBar f = m2026f();
            if (f == null) {
                this.f773H = true;
            } else {
                f.m1924c(true);
            }
        }
    }

    public void m2096d() {
        m2066m();
        if (this.g && this.f == null) {
            if (this.c instanceof Activity) {
                this.f = new WindowDecorActionBar((Activity) this.c, this.h);
            } else if (this.c instanceof Dialog) {
                this.f = new WindowDecorActionBar((Dialog) this.c);
            }
            if (this.f != null) {
                this.f.m1924c(this.f773H);
            }
        }
    }

    public void m2074a() {
        ActionBar e = m2025e();
        if (e != null) {
            e.m1926d(false);
        }
    }

    public void m2079a(View view) {
        m2066m();
        ViewGroup viewGroup = (ViewGroup) this.f787v.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.c.onContentChanged();
    }

    public void m2075a(int i) {
        m2066m();
        ViewGroup viewGroup = (ViewGroup) this.f787v.findViewById(16908290);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.a).inflate(i, viewGroup);
        this.c.onContentChanged();
    }

    public void m2080a(View view, LayoutParams layoutParams) {
        m2066m();
        ViewGroup viewGroup = (ViewGroup) this.f787v.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.c.onContentChanged();
    }

    public void m2088b(View view, LayoutParams layoutParams) {
        m2066m();
        ((ViewGroup) this.f787v.findViewById(16908290)).addView(view, layoutParams);
        this.c.onContentChanged();
    }

    private void m2066m() {
        if (!this.f785t) {
            this.f787v = m2067n();
            CharSequence k = m2031k();
            if (!TextUtils.isEmpty(k)) {
                m2089b(k);
            }
            m2068o();
            m2081a(this.f787v);
            this.f785t = true;
            PanelFeatureState a = m2035a(0, false);
            if (!m2029i()) {
                if (a == null || a.f824j == null) {
                    m2059d(C0110R.styleable.Theme_spinnerStyle);
                }
            }
        }
    }

    private ViewGroup m2067n() {
        TypedArray obtainStyledAttributes = this.a.obtainStyledAttributes(C0057R.styleable.Theme);
        if (obtainStyledAttributes.hasValue(C0057R.styleable.Theme_windowActionBar)) {
            View view;
            if (obtainStyledAttributes.getBoolean(C0057R.styleable.Theme_windowNoTitle, false)) {
                m2090b(1);
            } else if (obtainStyledAttributes.getBoolean(C0057R.styleable.Theme_windowActionBar, false)) {
                m2090b((int) C0110R.styleable.Theme_spinnerStyle);
            }
            if (obtainStyledAttributes.getBoolean(C0057R.styleable.Theme_windowActionBarOverlay, false)) {
                m2090b((int) C0110R.styleable.Theme_switchStyle);
            }
            if (obtainStyledAttributes.getBoolean(C0057R.styleable.Theme_windowActionModeOverlay, false)) {
                m2090b(10);
            }
            this.j = obtainStyledAttributes.getBoolean(C0057R.styleable.Theme_android_windowIsFloating, false);
            obtainStyledAttributes.recycle();
            LayoutInflater from = LayoutInflater.from(this.a);
            if (this.k) {
                View view2;
                if (this.i) {
                    view2 = (ViewGroup) from.inflate(C0057R.layout.abc_screen_simple_overlay_action_mode, null);
                } else {
                    view2 = (ViewGroup) from.inflate(C0057R.layout.abc_screen_simple, null);
                }
                if (VERSION.SDK_INT >= 21) {
                    ViewCompat.m1162a(view2, new C00462(this));
                    view = view2;
                } else {
                    ((FitWindowsViewGroup) view2).setOnFitSystemWindowsListener(new C00473(this));
                    view = view2;
                }
            } else if (this.j) {
                r0 = (ViewGroup) from.inflate(C0057R.layout.abc_dialog_title_material, null);
                this.h = false;
                this.g = false;
                view = r0;
            } else if (this.g) {
                Context contextThemeWrapper;
                TypedValue typedValue = new TypedValue();
                this.a.getTheme().resolveAttribute(C0057R.attr.actionBarTheme, typedValue, true);
                if (typedValue.resourceId != 0) {
                    contextThemeWrapper = new ContextThemeWrapper(this.a, typedValue.resourceId);
                } else {
                    contextThemeWrapper = this.a;
                }
                r0 = (ViewGroup) LayoutInflater.from(contextThemeWrapper).inflate(C0057R.layout.abc_screen_toolbar, null);
                this.f782q = (DecorContentParent) r0.findViewById(C0057R.id.decor_content_parent);
                this.f782q.setWindowCallback(m2030j());
                if (this.h) {
                    this.f782q.m2561a(C0110R.styleable.Theme_switchStyle);
                }
                if (this.f790y) {
                    this.f782q.m2561a(2);
                }
                if (this.f791z) {
                    this.f782q.m2561a(5);
                }
                view = r0;
            } else {
                view = null;
            }
            if (view == null) {
                throw new IllegalArgumentException("AppCompat does not support the current theme features: { windowActionBar: " + this.g + ", windowActionBarOverlay: " + this.h + ", android:windowIsFloating: " + this.j + ", windowActionModeOverlay: " + this.i + ", windowNoTitle: " + this.k + " }");
            }
            if (this.f782q == null) {
                this.f788w = (TextView) view.findViewById(C0057R.id.title);
            }
            ViewUtils.m3902b(view);
            ViewGroup viewGroup = (ViewGroup) this.b.findViewById(16908290);
            ContentFrameLayout contentFrameLayout = (ContentFrameLayout) view.findViewById(C0057R.id.action_bar_activity_content);
            while (viewGroup.getChildCount() > 0) {
                View childAt = viewGroup.getChildAt(0);
                viewGroup.removeViewAt(0);
                contentFrameLayout.addView(childAt);
            }
            this.b.setContentView(view);
            viewGroup.setId(-1);
            contentFrameLayout.setId(16908290);
            if (viewGroup instanceof FrameLayout) {
                ((FrameLayout) viewGroup).setForeground(null);
            }
            contentFrameLayout.setAttachListener(new C00484(this));
            return view;
        }
        obtainStyledAttributes.recycle();
        throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
    }

    void m2081a(ViewGroup viewGroup) {
    }

    private void m2068o() {
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) this.f787v.findViewById(16908290);
        contentFrameLayout.m2127a(this.f786u.getPaddingLeft(), this.f786u.getPaddingTop(), this.f786u.getPaddingRight(), this.f786u.getPaddingBottom());
        TypedArray obtainStyledAttributes = this.a.obtainStyledAttributes(C0057R.styleable.Theme);
        obtainStyledAttributes.getValue(C0057R.styleable.Theme_windowMinWidthMajor, contentFrameLayout.getMinWidthMajor());
        obtainStyledAttributes.getValue(C0057R.styleable.Theme_windowMinWidthMinor, contentFrameLayout.getMinWidthMinor());
        if (obtainStyledAttributes.hasValue(C0057R.styleable.Theme_windowFixedWidthMajor)) {
            obtainStyledAttributes.getValue(C0057R.styleable.Theme_windowFixedWidthMajor, contentFrameLayout.getFixedWidthMajor());
        }
        if (obtainStyledAttributes.hasValue(C0057R.styleable.Theme_windowFixedWidthMinor)) {
            obtainStyledAttributes.getValue(C0057R.styleable.Theme_windowFixedWidthMinor, contentFrameLayout.getFixedWidthMinor());
        }
        if (obtainStyledAttributes.hasValue(C0057R.styleable.Theme_windowFixedHeightMajor)) {
            obtainStyledAttributes.getValue(C0057R.styleable.Theme_windowFixedHeightMajor, contentFrameLayout.getFixedHeightMajor());
        }
        if (obtainStyledAttributes.hasValue(C0057R.styleable.Theme_windowFixedHeightMinor)) {
            obtainStyledAttributes.getValue(C0057R.styleable.Theme_windowFixedHeightMinor, contentFrameLayout.getFixedHeightMinor());
        }
        obtainStyledAttributes.recycle();
        contentFrameLayout.requestLayout();
    }

    public boolean m2090b(int i) {
        int g = m2065g(i);
        if (this.k && g == C0110R.styleable.Theme_spinnerStyle) {
            return false;
        }
        if (this.g && g == 1) {
            this.g = false;
        }
        switch (g) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                m2070q();
                this.k = true;
                return true;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                m2070q();
                this.f790y = true;
                return true;
            case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                m2070q();
                this.f791z = true;
                return true;
            case C1128R.styleable.StickyListHeadersListView_android_drawSelectorOnTop /*10*/:
                m2070q();
                this.i = true;
                return true;
            case C0110R.styleable.Theme_spinnerStyle /*108*/:
                m2070q();
                this.g = true;
                return true;
            case C0110R.styleable.Theme_switchStyle /*109*/:
                m2070q();
                this.h = true;
                return true;
            default:
                return this.b.requestFeature(g);
        }
    }

    void m2089b(CharSequence charSequence) {
        if (this.f782q != null) {
            this.f782q.setWindowTitle(charSequence);
        } else if (m2026f() != null) {
            m2026f().m1919a(charSequence);
        } else if (this.f788w != null) {
            this.f788w.setText(charSequence);
        }
    }

    void m2076a(int i, Menu menu) {
        if (i == C0110R.styleable.Theme_spinnerStyle) {
            ActionBar e = m2025e();
            if (e != null) {
                e.m1927e(false);
            }
        } else if (i == 0) {
            PanelFeatureState a = m2035a(i, true);
            if (a.f829o) {
                m2040a(a, false);
            }
        }
    }

    boolean m2092b(int i, Menu menu) {
        if (i != C0110R.styleable.Theme_spinnerStyle) {
            return false;
        }
        ActionBar e = m2025e();
        if (e == null) {
            return true;
        }
        e.m1927e(true);
        return true;
    }

    public boolean m2083a(MenuBuilder menuBuilder, MenuItem menuItem) {
        Window.Callback j = m2030j();
        if (!(j == null || m2029i())) {
            PanelFeatureState a = m2037a(menuBuilder.m2451p());
            if (a != null) {
                return j.onMenuItemSelected(a.f815a, menuItem);
            }
        }
        return false;
    }

    public void m2078a(MenuBuilder menuBuilder) {
        m2045a(menuBuilder, true);
    }

    public ActionMode m2085b(ActionMode.Callback callback) {
        if (callback == null) {
            throw new IllegalArgumentException("ActionMode callback can not be null.");
        }
        if (this.f777l != null) {
            this.f777l.m2172c();
        }
        ActionMode.Callback actionModeCallbackWrapperV7 = new ActionModeCallbackWrapperV7(this, callback);
        ActionBar e = m2025e();
        if (e != null) {
            this.f777l = e.m1917a(actionModeCallbackWrapperV7);
            if (!(this.f777l == null || this.e == null)) {
                this.e.m1994a(this.f777l);
            }
        }
        if (this.f777l == null) {
            this.f777l = m2072a(actionModeCallbackWrapperV7);
        }
        return this.f777l;
    }

    public void m2087b() {
        ActionBar e = m2025e();
        if (e == null || !e.m1923b()) {
            m2059d(0);
        }
    }

    ActionMode m2072a(ActionMode.Callback callback) {
        ActionMode actionMode;
        m2069p();
        if (this.f777l != null) {
            this.f777l.m2172c();
        }
        ActionMode.Callback actionModeCallbackWrapperV7 = new ActionModeCallbackWrapperV7(this, callback);
        if (this.e == null || m2029i()) {
            actionMode = null;
        } else {
            try {
                actionMode = this.e.m1993a(actionModeCallbackWrapperV7);
            } catch (AbstractMethodError e) {
                actionMode = null;
            }
        }
        if (actionMode != null) {
            this.f777l = actionMode;
        } else {
            if (this.f778m == null) {
                if (this.j) {
                    Context contextThemeWrapper;
                    TypedValue typedValue = new TypedValue();
                    Theme theme = this.a.getTheme();
                    theme.resolveAttribute(C0057R.attr.actionBarTheme, typedValue, true);
                    if (typedValue.resourceId != 0) {
                        Theme newTheme = this.a.getResources().newTheme();
                        newTheme.setTo(theme);
                        newTheme.applyStyle(typedValue.resourceId, true);
                        contextThemeWrapper = new ContextThemeWrapper(this.a, 0);
                        contextThemeWrapper.getTheme().setTo(newTheme);
                    } else {
                        contextThemeWrapper = this.a;
                    }
                    this.f778m = new ActionBarContextView(contextThemeWrapper);
                    this.f779n = new PopupWindow(contextThemeWrapper, null, C0057R.attr.actionModePopupWindowStyle);
                    PopupWindowCompat.m1827a(this.f779n, 2);
                    this.f779n.setContentView(this.f778m);
                    this.f779n.setWidth(-1);
                    contextThemeWrapper.getTheme().resolveAttribute(C0057R.attr.actionBarSize, typedValue, true);
                    this.f778m.setContentHeight(TypedValue.complexToDimensionPixelSize(typedValue.data, contextThemeWrapper.getResources().getDisplayMetrics()));
                    this.f779n.setHeight(-2);
                    this.f780o = new C00505(this);
                } else {
                    ViewStubCompat viewStubCompat = (ViewStubCompat) this.f787v.findViewById(C0057R.id.action_mode_bar_stub);
                    if (viewStubCompat != null) {
                        viewStubCompat.setLayoutInflater(LayoutInflater.from(m2027g()));
                        this.f778m = (ActionBarContextView) viewStubCompat.m3898a();
                    }
                }
            }
            if (this.f778m != null) {
                boolean z;
                m2069p();
                this.f778m.m2557c();
                Context context = this.f778m.getContext();
                ActionBarContextView actionBarContextView = this.f778m;
                if (this.f779n == null) {
                    z = true;
                } else {
                    z = false;
                }
                ActionMode standaloneActionMode = new StandaloneActionMode(context, actionBarContextView, actionModeCallbackWrapperV7, z);
                if (callback.m2120a(standaloneActionMode, standaloneActionMode.m2169b())) {
                    standaloneActionMode.m2173d();
                    this.f778m.m2554a(standaloneActionMode);
                    this.f777l = standaloneActionMode;
                    ViewCompat.m1171c(this.f778m, 0.0f);
                    this.f781p = ViewCompat.m1185n(this.f778m).m1398a(1.0f);
                    this.f781p.m1400a(new C00516(this));
                    if (this.f779n != null) {
                        this.b.getDecorView().post(this.f780o);
                    }
                } else {
                    this.f777l = null;
                }
            }
        }
        if (!(this.f777l == null || this.e == null)) {
            this.e.m1994a(this.f777l);
        }
        return this.f777l;
    }

    private void m2069p() {
        if (this.f781p != null) {
            this.f781p.m1405b();
        }
    }

    boolean m2097l() {
        if (this.f777l != null) {
            this.f777l.m2172c();
            return true;
        }
        ActionBar e = m2025e();
        if (e == null || !e.m1925c()) {
            return false;
        }
        return true;
    }

    boolean m2082a(int i, KeyEvent keyEvent) {
        ActionBar e = m2025e();
        if (e != null && e.m1921a(i, keyEvent)) {
            return true;
        }
        if (this.f768C == null || !m2047a(this.f768C, keyEvent.getKeyCode(), keyEvent, 1)) {
            if (this.f768C == null) {
                PanelFeatureState a = m2035a(0, true);
                m2054b(a, keyEvent);
                boolean a2 = m2047a(a, keyEvent.getKeyCode(), keyEvent, 1);
                a.f827m = false;
                if (a2) {
                    return true;
                }
            }
            return false;
        } else if (this.f768C == null) {
            return true;
        } else {
            this.f768C.f828n = true;
            return true;
        }
    }

    boolean m2084a(KeyEvent keyEvent) {
        boolean z = true;
        if (keyEvent.getKeyCode() == 82 && this.c.dispatchKeyEvent(keyEvent)) {
            return true;
        }
        int keyCode = keyEvent.getKeyCode();
        if (keyEvent.getAction() != 0) {
            z = false;
        }
        return z ? m2095c(keyCode, keyEvent) : m2091b(keyCode, keyEvent);
    }

    boolean m2091b(int i, KeyEvent keyEvent) {
        switch (i) {
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                boolean z = this.f769D;
                this.f769D = false;
                PanelFeatureState a = m2035a(0, false);
                if (a == null || !a.f829o) {
                    if (m2097l()) {
                        return true;
                    }
                } else if (z) {
                    return true;
                } else {
                    m2040a(a, true);
                    return true;
                }
                break;
            case C0110R.styleable.Theme_colorPrimary /*82*/:
                m2063e(0, keyEvent);
                return true;
        }
        return false;
    }

    boolean m2095c(int i, KeyEvent keyEvent) {
        boolean z = true;
        switch (i) {
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                if ((keyEvent.getFlags() & 128) == 0) {
                    z = false;
                }
                this.f769D = z;
                break;
            case C0110R.styleable.Theme_colorPrimary /*82*/:
                m2061d(0, keyEvent);
                return true;
        }
        if (VERSION.SDK_INT < 11) {
            m2082a(i, keyEvent);
        }
        return false;
    }

    public View m2093c(View view, String str, Context context, AttributeSet attributeSet) {
        boolean z;
        boolean z2 = VERSION.SDK_INT < 21;
        if (this.f776K == null) {
            this.f776K = new AppCompatViewInflater();
        }
        if (z2 && this.f785t && m2049a((ViewParent) view)) {
            z = true;
        } else {
            z = false;
        }
        return this.f776K.m2141a(view, str, context, attributeSet, z, z2, true);
    }

    private boolean m2049a(ViewParent viewParent) {
        if (viewParent == null) {
            return false;
        }
        ViewParent viewParent2 = viewParent;
        while (viewParent2 != null) {
            if (viewParent2 == this.f786u || !(viewParent2 instanceof View) || ViewCompat.m1192u((View) viewParent2)) {
                return false;
            }
            viewParent2 = viewParent2.getParent();
        }
        return true;
    }

    public void m2094c() {
        LayoutInflater from = LayoutInflater.from(this.a);
        if (from.getFactory() == null) {
            LayoutInflaterCompat.m919a(from, this);
        } else {
            Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
        }
    }

    public final View m2073a(View view, String str, Context context, AttributeSet attributeSet) {
        View b = m2086b(view, str, context, attributeSet);
        return b != null ? b : m2093c(view, str, context, attributeSet);
    }

    View m2086b(View view, String str, Context context, AttributeSet attributeSet) {
        if (this.c instanceof Factory) {
            View onCreateView = ((Factory) this.c).onCreateView(str, context, attributeSet);
            if (onCreateView != null) {
                return onCreateView;
            }
        }
        return null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m2039a(android.support.v7.app.AppCompatDelegateImplV7.PanelFeatureState r11, android.view.KeyEvent r12) {
        /*
        r10 = this;
        r1 = -1;
        r3 = 0;
        r9 = 1;
        r2 = -2;
        r0 = r11.f829o;
        if (r0 != 0) goto L_0x000e;
    L_0x0008:
        r0 = r10.m2029i();
        if (r0 == 0) goto L_0x000f;
    L_0x000e:
        return;
    L_0x000f:
        r0 = r11.f815a;
        if (r0 != 0) goto L_0x0034;
    L_0x0013:
        r4 = r10.a;
        r0 = r4.getResources();
        r0 = r0.getConfiguration();
        r0 = r0.screenLayout;
        r0 = r0 & 15;
        r5 = 4;
        if (r0 != r5) goto L_0x0048;
    L_0x0024:
        r0 = r9;
    L_0x0025:
        r4 = r4.getApplicationInfo();
        r4 = r4.targetSdkVersion;
        r5 = 11;
        if (r4 < r5) goto L_0x004a;
    L_0x002f:
        r4 = r9;
    L_0x0030:
        if (r0 == 0) goto L_0x0034;
    L_0x0032:
        if (r4 != 0) goto L_0x000e;
    L_0x0034:
        r0 = r10.m2030j();
        if (r0 == 0) goto L_0x004c;
    L_0x003a:
        r4 = r11.f815a;
        r5 = r11.f824j;
        r0 = r0.onMenuOpened(r4, r5);
        if (r0 != 0) goto L_0x004c;
    L_0x0044:
        r10.m2040a(r11, r9);
        goto L_0x000e;
    L_0x0048:
        r0 = r3;
        goto L_0x0025;
    L_0x004a:
        r4 = r3;
        goto L_0x0030;
    L_0x004c:
        r0 = r10.a;
        r4 = "window";
        r0 = r0.getSystemService(r4);
        r8 = r0;
        r8 = (android.view.WindowManager) r8;
        if (r8 == 0) goto L_0x000e;
    L_0x0059:
        r0 = r10.m2054b(r11, r12);
        if (r0 == 0) goto L_0x000e;
    L_0x005f:
        r0 = r11.f821g;
        if (r0 == 0) goto L_0x0067;
    L_0x0063:
        r0 = r11.f831q;
        if (r0 == 0) goto L_0x00f1;
    L_0x0067:
        r0 = r11.f821g;
        if (r0 != 0) goto L_0x00df;
    L_0x006b:
        r0 = r10.m2046a(r11);
        if (r0 == 0) goto L_0x000e;
    L_0x0071:
        r0 = r11.f821g;
        if (r0 == 0) goto L_0x000e;
    L_0x0075:
        r0 = r10.m2058c(r11);
        if (r0 == 0) goto L_0x000e;
    L_0x007b:
        r0 = r11.m2133a();
        if (r0 == 0) goto L_0x000e;
    L_0x0081:
        r0 = r11.f822h;
        r0 = r0.getLayoutParams();
        if (r0 != 0) goto L_0x0103;
    L_0x0089:
        r0 = new android.view.ViewGroup$LayoutParams;
        r0.<init>(r2, r2);
        r1 = r0;
    L_0x008f:
        r0 = r11.f816b;
        r4 = r11.f821g;
        r4.setBackgroundResource(r0);
        r0 = r11.f822h;
        r0 = r0.getParent();
        if (r0 == 0) goto L_0x00a9;
    L_0x009e:
        r4 = r0 instanceof android.view.ViewGroup;
        if (r4 == 0) goto L_0x00a9;
    L_0x00a2:
        r0 = (android.view.ViewGroup) r0;
        r4 = r11.f822h;
        r0.removeView(r4);
    L_0x00a9:
        r0 = r11.f821g;
        r4 = r11.f822h;
        r0.addView(r4, r1);
        r0 = r11.f822h;
        r0 = r0.hasFocus();
        if (r0 != 0) goto L_0x00bd;
    L_0x00b8:
        r0 = r11.f822h;
        r0.requestFocus();
    L_0x00bd:
        r1 = r2;
    L_0x00be:
        r11.f828n = r3;
        r0 = new android.view.WindowManager$LayoutParams;
        r3 = r11.f818d;
        r4 = r11.f819e;
        r5 = 1002; // 0x3ea float:1.404E-42 double:4.95E-321;
        r6 = 8519680; // 0x820000 float:1.1938615E-38 double:4.209281E-317;
        r7 = -3;
        r0.<init>(r1, r2, r3, r4, r5, r6, r7);
        r1 = r11.f817c;
        r0.gravity = r1;
        r1 = r11.f820f;
        r0.windowAnimations = r1;
        r1 = r11.f821g;
        r8.addView(r1, r0);
        r11.f829o = r9;
        goto L_0x000e;
    L_0x00df:
        r0 = r11.f831q;
        if (r0 == 0) goto L_0x0075;
    L_0x00e3:
        r0 = r11.f821g;
        r0 = r0.getChildCount();
        if (r0 <= 0) goto L_0x0075;
    L_0x00eb:
        r0 = r11.f821g;
        r0.removeAllViews();
        goto L_0x0075;
    L_0x00f1:
        r0 = r11.f823i;
        if (r0 == 0) goto L_0x0101;
    L_0x00f5:
        r0 = r11.f823i;
        r0 = r0.getLayoutParams();
        if (r0 == 0) goto L_0x0101;
    L_0x00fd:
        r0 = r0.width;
        if (r0 == r1) goto L_0x00be;
    L_0x0101:
        r1 = r2;
        goto L_0x00be;
    L_0x0103:
        r1 = r0;
        goto L_0x008f;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.app.AppCompatDelegateImplV7.a(android.support.v7.app.AppCompatDelegateImplV7$PanelFeatureState, android.view.KeyEvent):void");
    }

    private boolean m2046a(PanelFeatureState panelFeatureState) {
        panelFeatureState.m2131a(m2027g());
        panelFeatureState.f821g = new ListMenuDecorView(this, panelFeatureState.f826l);
        panelFeatureState.f817c = 81;
        return true;
    }

    private void m2045a(MenuBuilder menuBuilder, boolean z) {
        if (this.f782q == null || !this.f782q.m2563d() || (ViewConfigurationCompat.m1249b(ViewConfiguration.get(this.a)) && !this.f782q.m2565f())) {
            PanelFeatureState a = m2035a(0, true);
            a.f831q = true;
            m2040a(a, false);
            m2039a(a, null);
            return;
        }
        Window.Callback j = m2030j();
        if (this.f782q.m2564e() && z) {
            this.f782q.m2567h();
            if (!m2029i()) {
                j.onPanelClosed(C0110R.styleable.Theme_spinnerStyle, m2035a(0, true).f824j);
            }
        } else if (j != null && !m2029i()) {
            if (this.f770E && (this.f771F & 1) != 0) {
                this.f786u.removeCallbacks(this.f772G);
                this.f772G.run();
            }
            PanelFeatureState a2 = m2035a(0, true);
            if (a2.f824j != null && !a2.f832r && j.onPreparePanel(0, a2.f823i, a2.f824j)) {
                j.onMenuOpened(C0110R.styleable.Theme_spinnerStyle, a2.f824j);
                this.f782q.m2566g();
            }
        }
    }

    private boolean m2053b(PanelFeatureState panelFeatureState) {
        Context contextThemeWrapper;
        MenuBuilder menuBuilder;
        Context context = this.a;
        if ((panelFeatureState.f815a == 0 || panelFeatureState.f815a == C0110R.styleable.Theme_spinnerStyle) && this.f782q != null) {
            TypedValue typedValue = new TypedValue();
            Theme theme = context.getTheme();
            theme.resolveAttribute(C0057R.attr.actionBarTheme, typedValue, true);
            Theme theme2 = null;
            if (typedValue.resourceId != 0) {
                theme2 = context.getResources().newTheme();
                theme2.setTo(theme);
                theme2.applyStyle(typedValue.resourceId, true);
                theme2.resolveAttribute(C0057R.attr.actionBarWidgetTheme, typedValue, true);
            } else {
                theme.resolveAttribute(C0057R.attr.actionBarWidgetTheme, typedValue, true);
            }
            if (typedValue.resourceId != 0) {
                if (theme2 == null) {
                    theme2 = context.getResources().newTheme();
                    theme2.setTo(theme);
                }
                theme2.applyStyle(typedValue.resourceId, true);
            }
            Theme theme3 = theme2;
            if (theme3 != null) {
                contextThemeWrapper = new ContextThemeWrapper(context, 0);
                contextThemeWrapper.getTheme().setTo(theme3);
                menuBuilder = new MenuBuilder(contextThemeWrapper);
                menuBuilder.m2418a((Callback) this);
                panelFeatureState.m2132a(menuBuilder);
                return true;
            }
        }
        contextThemeWrapper = context;
        menuBuilder = new MenuBuilder(contextThemeWrapper);
        menuBuilder.m2418a((Callback) this);
        panelFeatureState.m2132a(menuBuilder);
        return true;
    }

    private boolean m2058c(PanelFeatureState panelFeatureState) {
        if (panelFeatureState.f823i != null) {
            panelFeatureState.f822h = panelFeatureState.f823i;
            return true;
        } else if (panelFeatureState.f824j == null) {
            return false;
        } else {
            if (this.f784s == null) {
                this.f784s = new PanelMenuPresenterCallback();
            }
            panelFeatureState.f822h = (View) panelFeatureState.m2130a(this.f784s);
            return panelFeatureState.f822h != null;
        }
    }

    private boolean m2054b(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        if (m2029i()) {
            return false;
        }
        if (panelFeatureState.f827m) {
            return true;
        }
        if (!(this.f768C == null || this.f768C == panelFeatureState)) {
            m2040a(this.f768C, false);
        }
        Window.Callback j = m2030j();
        if (j != null) {
            panelFeatureState.f823i = j.onCreatePanelView(panelFeatureState.f815a);
        }
        boolean z = panelFeatureState.f815a == 0 || panelFeatureState.f815a == C0110R.styleable.Theme_spinnerStyle;
        if (z && this.f782q != null) {
            this.f782q.m2568i();
        }
        if (panelFeatureState.f823i == null && !(z && (m2026f() instanceof ToolbarActionBar))) {
            if (panelFeatureState.f824j == null || panelFeatureState.f832r) {
                if (panelFeatureState.f824j == null && (!m2053b(panelFeatureState) || panelFeatureState.f824j == null)) {
                    return false;
                }
                if (z && this.f782q != null) {
                    if (this.f783r == null) {
                        this.f783r = new ActionMenuPresenterCallback();
                    }
                    this.f782q.m2562a(panelFeatureState.f824j, this.f783r);
                }
                panelFeatureState.f824j.m2442g();
                if (j.onCreatePanelMenu(panelFeatureState.f815a, panelFeatureState.f824j)) {
                    panelFeatureState.f832r = false;
                } else {
                    panelFeatureState.m2132a(null);
                    if (!z || this.f782q == null) {
                        return false;
                    }
                    this.f782q.m2562a(null, this.f783r);
                    return false;
                }
            }
            panelFeatureState.f824j.m2442g();
            if (panelFeatureState.f833s != null) {
                panelFeatureState.f824j.m2429b(panelFeatureState.f833s);
                panelFeatureState.f833s = null;
            }
            if (j.onPreparePanel(0, panelFeatureState.f823i, panelFeatureState.f824j)) {
                if (KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1) {
                    z = true;
                } else {
                    z = false;
                }
                panelFeatureState.f830p = z;
                panelFeatureState.f824j.setQwertyMode(panelFeatureState.f830p);
                panelFeatureState.f824j.m2443h();
            } else {
                if (z && this.f782q != null) {
                    this.f782q.m2562a(null, this.f783r);
                }
                panelFeatureState.f824j.m2443h();
                return false;
            }
        }
        panelFeatureState.f827m = true;
        panelFeatureState.f828n = false;
        this.f768C = panelFeatureState;
        return true;
    }

    private void m2052b(MenuBuilder menuBuilder) {
        if (!this.f766A) {
            this.f766A = true;
            this.f782q.m2569j();
            Window.Callback j = m2030j();
            if (!(j == null || m2029i())) {
                j.onPanelClosed(C0110R.styleable.Theme_spinnerStyle, menuBuilder);
            }
            this.f766A = false;
        }
    }

    private void m2056c(int i) {
        m2040a(m2035a(i, true), true);
    }

    private void m2040a(PanelFeatureState panelFeatureState, boolean z) {
        if (z && panelFeatureState.f815a == 0 && this.f782q != null && this.f782q.m2564e()) {
            m2052b(panelFeatureState.f824j);
            return;
        }
        WindowManager windowManager = (WindowManager) this.a.getSystemService("window");
        if (!(windowManager == null || !panelFeatureState.f829o || panelFeatureState.f821g == null)) {
            windowManager.removeView(panelFeatureState.f821g);
            if (z) {
                m2038a(panelFeatureState.f815a, panelFeatureState, null);
            }
        }
        panelFeatureState.f827m = false;
        panelFeatureState.f828n = false;
        panelFeatureState.f829o = false;
        panelFeatureState.f822h = null;
        panelFeatureState.f831q = true;
        if (this.f768C == panelFeatureState) {
            this.f768C = null;
        }
    }

    private boolean m2061d(int i, KeyEvent keyEvent) {
        if (keyEvent.getRepeatCount() == 0) {
            PanelFeatureState a = m2035a(i, true);
            if (!a.f829o) {
                return m2054b(a, keyEvent);
            }
        }
        return false;
    }

    private boolean m2063e(int i, KeyEvent keyEvent) {
        boolean z = true;
        if (this.f777l != null) {
            return false;
        }
        PanelFeatureState a = m2035a(i, true);
        if (i != 0 || this.f782q == null || !this.f782q.m2563d() || ViewConfigurationCompat.m1249b(ViewConfiguration.get(this.a))) {
            boolean z2;
            if (a.f829o || a.f828n) {
                z2 = a.f829o;
                m2040a(a, true);
                z = z2;
            } else {
                if (a.f827m) {
                    if (a.f832r) {
                        a.f827m = false;
                        z2 = m2054b(a, keyEvent);
                    } else {
                        z2 = true;
                    }
                    if (z2) {
                        m2039a(a, keyEvent);
                    }
                }
                z = false;
            }
        } else if (this.f782q.m2564e()) {
            z = this.f782q.m2567h();
        } else {
            if (!m2029i() && m2054b(a, keyEvent)) {
                z = this.f782q.m2566g();
            }
            z = false;
        }
        if (z) {
            AudioManager audioManager = (AudioManager) this.a.getSystemService("audio");
            if (audioManager != null) {
                audioManager.playSoundEffect(0);
            } else {
                Log.w("AppCompatDelegate", "Couldn't get audio manager");
            }
        }
        return z;
    }

    private void m2038a(int i, PanelFeatureState panelFeatureState, Menu menu) {
        if (menu == null) {
            if (panelFeatureState == null && i >= 0 && i < this.f767B.length) {
                panelFeatureState = this.f767B[i];
            }
            if (panelFeatureState != null) {
                menu = panelFeatureState.f824j;
            }
        }
        if ((panelFeatureState == null || panelFeatureState.f829o) && !m2029i()) {
            this.c.onPanelClosed(i, menu);
        }
    }

    private PanelFeatureState m2037a(Menu menu) {
        PanelFeatureState[] panelFeatureStateArr = this.f767B;
        int length = panelFeatureStateArr != null ? panelFeatureStateArr.length : 0;
        for (int i = 0; i < length; i++) {
            PanelFeatureState panelFeatureState = panelFeatureStateArr[i];
            if (panelFeatureState != null && panelFeatureState.f824j == menu) {
                return panelFeatureState;
            }
        }
        return null;
    }

    private PanelFeatureState m2035a(int i, boolean z) {
        Object obj = this.f767B;
        if (obj == null || obj.length <= i) {
            Object obj2 = new PanelFeatureState[(i + 1)];
            if (obj != null) {
                System.arraycopy(obj, 0, obj2, 0, obj.length);
            }
            this.f767B = obj2;
            obj = obj2;
        }
        PanelFeatureState panelFeatureState = obj[i];
        if (panelFeatureState != null) {
            return panelFeatureState;
        }
        panelFeatureState = new PanelFeatureState(i);
        obj[i] = panelFeatureState;
        return panelFeatureState;
    }

    private boolean m2047a(PanelFeatureState panelFeatureState, int i, KeyEvent keyEvent, int i2) {
        boolean z = false;
        if (!keyEvent.isSystem()) {
            if ((panelFeatureState.f827m || m2054b(panelFeatureState, keyEvent)) && panelFeatureState.f824j != null) {
                z = panelFeatureState.f824j.performShortcut(i, keyEvent, i2);
            }
            if (z && (i2 & 1) == 0 && this.f782q == null) {
                m2040a(panelFeatureState, true);
            }
        }
        return z;
    }

    private void m2059d(int i) {
        this.f771F |= 1 << i;
        if (!this.f770E && this.f786u != null) {
            ViewCompat.m1163a(this.f786u, this.f772G);
            this.f770E = true;
        }
    }

    private void m2062e(int i) {
        PanelFeatureState a = m2035a(i, true);
        if (a.f824j != null) {
            Bundle bundle = new Bundle();
            a.f824j.m2417a(bundle);
            if (bundle.size() > 0) {
                a.f833s = bundle;
            }
            a.f824j.m2442g();
            a.f824j.clear();
        }
        a.f832r = true;
        a.f831q = true;
        if ((i == C0110R.styleable.Theme_spinnerStyle || i == 0) && this.f782q != null) {
            a = m2035a(0, false);
            if (a != null) {
                a.f827m = false;
                m2054b(a, null);
            }
        }
    }

    private int m2064f(int i) {
        int i2;
        int i3 = 1;
        int i4 = 0;
        if (this.f778m == null || !(this.f778m.getLayoutParams() instanceof MarginLayoutParams)) {
            i2 = 0;
        } else {
            int i5;
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.f778m.getLayoutParams();
            if (this.f778m.isShown()) {
                if (this.f774I == null) {
                    this.f774I = new Rect();
                    this.f775J = new Rect();
                }
                Rect rect = this.f774I;
                Rect rect2 = this.f775J;
                rect.set(0, i, 0, 0);
                ViewUtils.m3900a(this.f787v, rect, rect2);
                if (marginLayoutParams.topMargin != (rect2.top == 0 ? i : 0)) {
                    marginLayoutParams.topMargin = i;
                    if (this.f789x == null) {
                        this.f789x = new View(this.a);
                        this.f789x.setBackgroundColor(this.a.getResources().getColor(C0057R.color.abc_input_method_navigation_guard));
                        this.f787v.addView(this.f789x, -1, new LayoutParams(-1, i));
                        i5 = 1;
                    } else {
                        LayoutParams layoutParams = this.f789x.getLayoutParams();
                        if (layoutParams.height != i) {
                            layoutParams.height = i;
                            this.f789x.setLayoutParams(layoutParams);
                        }
                        i5 = 1;
                    }
                } else {
                    i5 = 0;
                }
                if (this.f789x == null) {
                    i3 = 0;
                }
                if (!(this.i || i3 == 0)) {
                    i = 0;
                }
                int i6 = i5;
                i5 = i3;
                i3 = i6;
            } else if (marginLayoutParams.topMargin != 0) {
                marginLayoutParams.topMargin = 0;
                i5 = 0;
            } else {
                i3 = 0;
                i5 = 0;
            }
            if (i3 != 0) {
                this.f778m.setLayoutParams(marginLayoutParams);
            }
            i2 = i5;
        }
        if (this.f789x != null) {
            View view = this.f789x;
            if (i2 == 0) {
                i4 = 8;
            }
            view.setVisibility(i4);
        }
        return i;
    }

    private void m2070q() {
        if (this.f785t) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    private int m2065g(int i) {
        if (i == 8) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
            return C0110R.styleable.Theme_spinnerStyle;
        } else if (i != 9) {
            return i;
        } else {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
            return C0110R.styleable.Theme_switchStyle;
        }
    }

    private void m2071r() {
        if (this.f782q != null) {
            this.f782q.m2569j();
        }
        if (this.f779n != null) {
            this.f786u.removeCallbacks(this.f780o);
            if (this.f779n.isShowing()) {
                try {
                    this.f779n.dismiss();
                } catch (IllegalArgumentException e) {
                }
            }
            this.f779n = null;
        }
        m2069p();
        PanelFeatureState a = m2035a(0, false);
        if (a != null && a.f824j != null) {
            a.f824j.close();
        }
    }
}
