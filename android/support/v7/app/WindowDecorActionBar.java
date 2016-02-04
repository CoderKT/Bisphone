package android.support.v7.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v4.view.ViewPropertyAnimatorUpdateListener;
import android.support.v7.app.ActionBar.OnMenuVisibilityListener;
import android.support.v7.appcompat.C0057R;
import android.support.v7.view.ActionBarPolicy;
import android.support.v7.view.ActionMode;
import android.support.v7.view.ActionMode.Callback;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.ViewPropertyAnimatorCompatSet;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.ActionBarContainer;
import android.support.v7.widget.ActionBarContextView;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.support.v7.widget.ActionBarOverlayLayout.ActionBarVisibilityCallback;
import android.support.v7.widget.DecorToolbar;
import android.support.v7.widget.ScrollingTabContainerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class WindowDecorActionBar extends ActionBar implements ActionBarVisibilityCallback {
    static final /* synthetic */ boolean f863h;
    private static final Interpolator f864i;
    private static final Interpolator f865j;
    private static final boolean f866k;
    private boolean f867A;
    private int f868B;
    private boolean f869C;
    private boolean f870D;
    private boolean f871E;
    private boolean f872F;
    private boolean f873G;
    private ViewPropertyAnimatorCompatSet f874H;
    private boolean f875I;
    ActionModeImpl f876a;
    ActionMode f877b;
    Callback f878c;
    boolean f879d;
    final ViewPropertyAnimatorListener f880e;
    final ViewPropertyAnimatorListener f881f;
    final ViewPropertyAnimatorUpdateListener f882g;
    private Context f883l;
    private Context f884m;
    private Activity f885n;
    private Dialog f886o;
    private ActionBarOverlayLayout f887p;
    private ActionBarContainer f888q;
    private DecorToolbar f889r;
    private ActionBarContextView f890s;
    private View f891t;
    private ScrollingTabContainerView f892u;
    private ArrayList<Object> f893v;
    private int f894w;
    private boolean f895x;
    private boolean f896y;
    private ArrayList<OnMenuVisibilityListener> f897z;

    /* renamed from: android.support.v7.app.WindowDecorActionBar.1 */
    class C00541 extends ViewPropertyAnimatorListenerAdapter {
        final /* synthetic */ WindowDecorActionBar f853a;

        C00541(WindowDecorActionBar windowDecorActionBar) {
            this.f853a = windowDecorActionBar;
        }

        public void m2160b(View view) {
            if (this.f853a.f869C && this.f853a.f891t != null) {
                ViewCompat.m1167b(this.f853a.f891t, 0.0f);
                ViewCompat.m1167b(this.f853a.f888q, 0.0f);
            }
            this.f853a.f888q.setVisibility(8);
            this.f853a.f888q.setTransitioning(false);
            this.f853a.f874H = null;
            this.f853a.m2231d();
            if (this.f853a.f887p != null) {
                ViewCompat.m1187p(this.f853a.f887p);
            }
        }
    }

    /* renamed from: android.support.v7.app.WindowDecorActionBar.2 */
    class C00552 extends ViewPropertyAnimatorListenerAdapter {
        final /* synthetic */ WindowDecorActionBar f854a;

        C00552(WindowDecorActionBar windowDecorActionBar) {
            this.f854a = windowDecorActionBar;
        }

        public void m2161b(View view) {
            this.f854a.f874H = null;
            this.f854a.f888q.requestLayout();
        }
    }

    /* renamed from: android.support.v7.app.WindowDecorActionBar.3 */
    class C00563 implements ViewPropertyAnimatorUpdateListener {
        final /* synthetic */ WindowDecorActionBar f855a;

        C00563(WindowDecorActionBar windowDecorActionBar) {
            this.f855a = windowDecorActionBar;
        }

        public void m2162a(View view) {
            ((View) this.f855a.f888q.getParent()).invalidate();
        }
    }

    public class ActionModeImpl extends ActionMode implements MenuBuilder.Callback {
        final /* synthetic */ WindowDecorActionBar f858a;
        private final Context f859b;
        private final MenuBuilder f860c;
        private Callback f861d;
        private WeakReference<View> f862e;

        public ActionModeImpl(WindowDecorActionBar windowDecorActionBar, Context context, Callback callback) {
            this.f858a = windowDecorActionBar;
            this.f859b = context;
            this.f861d = callback;
            this.f860c = new MenuBuilder(context).m2410a(1);
            this.f860c.m2418a((MenuBuilder.Callback) this);
        }

        public MenuInflater m2180a() {
            return new SupportMenuInflater(this.f859b);
        }

        public Menu m2187b() {
            return this.f860c;
        }

        public void m2190c() {
            if (this.f858a.f876a == this) {
                if (WindowDecorActionBar.m2209b(this.f858a.f870D, this.f858a.f871E, false)) {
                    this.f861d.m2119a(this);
                } else {
                    this.f858a.f877b = this;
                    this.f858a.f878c = this.f861d;
                }
                this.f861d = null;
                this.f858a.m2243j(false);
                this.f858a.f890s.m2556b();
                this.f858a.f889r.m2896a().sendAccessibilityEvent(32);
                this.f858a.f887p.setHideOnContentScrollEnabled(this.f858a.f879d);
                this.f858a.f876a = null;
            }
        }

        public void m2191d() {
            if (this.f858a.f876a == this) {
                this.f860c.m2442g();
                try {
                    this.f861d.m2122b(this, this.f860c);
                } finally {
                    this.f860c.m2443h();
                }
            }
        }

        public boolean m2192e() {
            this.f860c.m2442g();
            try {
                boolean a = this.f861d.m2120a((ActionMode) this, this.f860c);
                return a;
            } finally {
                this.f860c.m2443h();
            }
        }

        public void m2183a(View view) {
            this.f858a.f890s.setCustomView(view);
            this.f862e = new WeakReference(view);
        }

        public void m2184a(CharSequence charSequence) {
            this.f858a.f890s.setSubtitle(charSequence);
        }

        public void m2189b(CharSequence charSequence) {
            this.f858a.f890s.setTitle(charSequence);
        }

        public void m2181a(int i) {
            m2189b(this.f858a.f883l.getResources().getString(i));
        }

        public void m2188b(int i) {
            m2184a(this.f858a.f883l.getResources().getString(i));
        }

        public CharSequence m2193f() {
            return this.f858a.f890s.getTitle();
        }

        public CharSequence m2194g() {
            return this.f858a.f890s.getSubtitle();
        }

        public void m2185a(boolean z) {
            super.m2168a(z);
            this.f858a.f890s.setTitleOptional(z);
        }

        public boolean m2195h() {
            return this.f858a.f890s.m2558d();
        }

        public View m2196i() {
            return this.f862e != null ? (View) this.f862e.get() : null;
        }

        public boolean m2186a(MenuBuilder menuBuilder, MenuItem menuItem) {
            if (this.f861d != null) {
                return this.f861d.m2121a((ActionMode) this, menuItem);
            }
            return false;
        }

        public void m2182a(MenuBuilder menuBuilder) {
            if (this.f861d != null) {
                m2191d();
                this.f858a.f890s.m2555a();
            }
        }
    }

    static {
        boolean z = true;
        f863h = !WindowDecorActionBar.class.desiredAssertionStatus();
        f864i = new AccelerateInterpolator();
        f865j = new DecelerateInterpolator();
        if (VERSION.SDK_INT < 14) {
            z = false;
        }
        f866k = z;
    }

    public WindowDecorActionBar(Activity activity, boolean z) {
        this.f893v = new ArrayList();
        this.f894w = -1;
        this.f897z = new ArrayList();
        this.f868B = 0;
        this.f869C = true;
        this.f873G = true;
        this.f880e = new C00541(this);
        this.f881f = new C00552(this);
        this.f882g = new C00563(this);
        this.f885n = activity;
        View decorView = activity.getWindow().getDecorView();
        m2204a(decorView);
        if (!z) {
            this.f891t = decorView.findViewById(16908290);
        }
    }

    public WindowDecorActionBar(Dialog dialog) {
        this.f893v = new ArrayList();
        this.f894w = -1;
        this.f897z = new ArrayList();
        this.f868B = 0;
        this.f869C = true;
        this.f873G = true;
        this.f880e = new C00541(this);
        this.f881f = new C00552(this);
        this.f882g = new C00563(this);
        this.f886o = dialog;
        m2204a(dialog.getWindow().getDecorView());
    }

    private void m2204a(View view) {
        this.f887p = (ActionBarOverlayLayout) view.findViewById(C0057R.id.decor_content_parent);
        if (this.f887p != null) {
            this.f887p.setActionBarVisibilityCallback(this);
        }
        this.f889r = m2207b(view.findViewById(C0057R.id.action_bar));
        this.f890s = (ActionBarContextView) view.findViewById(C0057R.id.action_context_bar);
        this.f888q = (ActionBarContainer) view.findViewById(C0057R.id.action_bar_container);
        if (this.f889r == null || this.f890s == null || this.f888q == null) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used " + "with a compatible window decor layout");
        }
        this.f883l = this.f889r.m2905b();
        boolean z = (this.f889r.m2921o() & 4) != 0;
        if (z) {
            this.f895x = true;
        }
        ActionBarPolicy a = ActionBarPolicy.m2247a(this.f883l);
        if (a.m2253f() || z) {
            z = true;
        } else {
            z = false;
        }
        m2227a(z);
        m2219k(a.m2251d());
        TypedArray obtainStyledAttributes = this.f883l.obtainStyledAttributes(null, C0057R.styleable.ActionBar, C0057R.attr.actionBarStyle, 0);
        if (obtainStyledAttributes.getBoolean(C0057R.styleable.ActionBar_hideOnContentScroll, false)) {
            m2228b(true);
        }
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(C0057R.styleable.ActionBar_elevation, 0);
        if (dimensionPixelSize != 0) {
            m2223a((float) dimensionPixelSize);
        }
        obtainStyledAttributes.recycle();
    }

    private DecorToolbar m2207b(View view) {
        if (view instanceof DecorToolbar) {
            return (DecorToolbar) view;
        }
        if (view instanceof Toolbar) {
            return ((Toolbar) view).getWrapper();
        }
        throw new IllegalStateException(new StringBuilder().append("Can't make a decor toolbar out of ").append(view).toString() != null ? view.getClass().getSimpleName() : "null");
    }

    public void m2223a(float f) {
        ViewCompat.m1175d(this.f888q, f);
    }

    private void m2219k(boolean z) {
        boolean z2;
        boolean z3;
        boolean z4 = true;
        this.f867A = z;
        if (this.f867A) {
            this.f888q.setTabContainer(null);
            this.f889r.m2900a(this.f892u);
        } else {
            this.f889r.m2900a(null);
            this.f888q.setTabContainer(this.f892u);
        }
        if (m2233e() == 2) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (this.f892u != null) {
            if (z2) {
                this.f892u.setVisibility(0);
                if (this.f887p != null) {
                    ViewCompat.m1187p(this.f887p);
                }
            } else {
                this.f892u.setVisibility(8);
            }
        }
        DecorToolbar decorToolbar = this.f889r;
        if (this.f867A || !z2) {
            z3 = false;
        } else {
            z3 = true;
        }
        decorToolbar.m2904a(z3);
        ActionBarOverlayLayout actionBarOverlayLayout = this.f887p;
        if (this.f867A || !z2) {
            z4 = false;
        }
        actionBarOverlayLayout.setHasNonEmbeddedTabs(z4);
    }

    void m2231d() {
        if (this.f878c != null) {
            this.f878c.m2119a(this.f877b);
            this.f877b = null;
            this.f878c = null;
        }
    }

    public void m2224a(int i) {
        this.f868B = i;
    }

    public void m2232d(boolean z) {
        this.f875I = z;
        if (!z && this.f874H != null) {
            this.f874H.m2314b();
        }
    }

    public void m2234e(boolean z) {
        if (z != this.f896y) {
            this.f896y = z;
            int size = this.f897z.size();
            for (int i = 0; i < size; i++) {
                ((OnMenuVisibilityListener) this.f897z.get(i)).m1910a(z);
            }
        }
    }

    public void m2236f(boolean z) {
        m2225a(z ? 4 : 0, 4);
    }

    public void m2227a(boolean z) {
        this.f889r.m2907b(z);
    }

    public void m2226a(CharSequence charSequence) {
        this.f889r.m2903a(charSequence);
    }

    public void m2225a(int i, int i2) {
        int o = this.f889r.m2921o();
        if ((i2 & 4) != 0) {
            this.f895x = true;
        }
        this.f889r.m2908c((o & (i2 ^ -1)) | (i & i2));
    }

    public int m2233e() {
        return this.f889r.m2922p();
    }

    public ActionMode m2222a(Callback callback) {
        if (this.f876a != null) {
            this.f876a.m2190c();
        }
        this.f887p.setHideOnContentScrollEnabled(false);
        this.f890s.m2557c();
        ActionMode actionModeImpl = new ActionModeImpl(this, this.f890s.getContext(), callback);
        if (!actionModeImpl.m2192e()) {
            return null;
        }
        actionModeImpl.m2191d();
        this.f890s.m2554a(actionModeImpl);
        m2243j(true);
        this.f890s.sendAccessibilityEvent(32);
        this.f876a = actionModeImpl;
        return actionModeImpl;
    }

    public void m2238g(boolean z) {
        this.f869C = z;
    }

    private void m2217j() {
        if (!this.f872F) {
            this.f872F = true;
            if (this.f887p != null) {
                this.f887p.setShowingForActionMode(true);
            }
            m2220l(false);
        }
    }

    public void m2235f() {
        if (this.f871E) {
            this.f871E = false;
            m2220l(true);
        }
    }

    private void m2218k() {
        if (this.f872F) {
            this.f872F = false;
            if (this.f887p != null) {
                this.f887p.setShowingForActionMode(false);
            }
            m2220l(false);
        }
    }

    public void m2237g() {
        if (!this.f871E) {
            this.f871E = true;
            m2220l(true);
        }
    }

    public void m2228b(boolean z) {
        if (!z || this.f887p.m2587a()) {
            this.f879d = z;
            this.f887p.setHideOnContentScrollEnabled(z);
            return;
        }
        throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
    }

    private static boolean m2209b(boolean z, boolean z2, boolean z3) {
        if (z3) {
            return true;
        }
        if (z || z2) {
            return false;
        }
        return true;
    }

    private void m2220l(boolean z) {
        if (m2209b(this.f870D, this.f871E, this.f872F)) {
            if (!this.f873G) {
                this.f873G = true;
                m2240h(z);
            }
        } else if (this.f873G) {
            this.f873G = false;
            m2242i(z);
        }
    }

    public void m2240h(boolean z) {
        if (this.f874H != null) {
            this.f874H.m2314b();
        }
        this.f888q.setVisibility(0);
        if (this.f868B == 0 && f866k && (this.f875I || z)) {
            ViewCompat.m1167b(this.f888q, 0.0f);
            float f = (float) (-this.f888q.getHeight());
            if (z) {
                int[] iArr = new int[]{0, 0};
                this.f888q.getLocationInWindow(iArr);
                f -= (float) iArr[1];
            }
            ViewCompat.m1167b(this.f888q, f);
            ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet = new ViewPropertyAnimatorCompatSet();
            ViewPropertyAnimatorCompat c = ViewCompat.m1185n(this.f888q).m1406c(0.0f);
            c.m1401a(this.f882g);
            viewPropertyAnimatorCompatSet.m2309a(c);
            if (this.f869C && this.f891t != null) {
                ViewCompat.m1167b(this.f891t, f);
                viewPropertyAnimatorCompatSet.m2309a(ViewCompat.m1185n(this.f891t).m1406c(0.0f));
            }
            viewPropertyAnimatorCompatSet.m2312a(f865j);
            viewPropertyAnimatorCompatSet.m2308a(250);
            viewPropertyAnimatorCompatSet.m2311a(this.f881f);
            this.f874H = viewPropertyAnimatorCompatSet;
            viewPropertyAnimatorCompatSet.m2313a();
        } else {
            ViewCompat.m1171c(this.f888q, 1.0f);
            ViewCompat.m1167b(this.f888q, 0.0f);
            if (this.f869C && this.f891t != null) {
                ViewCompat.m1167b(this.f891t, 0.0f);
            }
            this.f881f.m1376b(null);
        }
        if (this.f887p != null) {
            ViewCompat.m1187p(this.f887p);
        }
    }

    public void m2242i(boolean z) {
        if (this.f874H != null) {
            this.f874H.m2314b();
        }
        if (this.f868B == 0 && f866k && (this.f875I || z)) {
            ViewCompat.m1171c(this.f888q, 1.0f);
            this.f888q.setTransitioning(true);
            ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet = new ViewPropertyAnimatorCompatSet();
            float f = (float) (-this.f888q.getHeight());
            if (z) {
                int[] iArr = new int[]{0, 0};
                this.f888q.getLocationInWindow(iArr);
                f -= (float) iArr[1];
            }
            ViewPropertyAnimatorCompat c = ViewCompat.m1185n(this.f888q).m1406c(f);
            c.m1401a(this.f882g);
            viewPropertyAnimatorCompatSet.m2309a(c);
            if (this.f869C && this.f891t != null) {
                viewPropertyAnimatorCompatSet.m2309a(ViewCompat.m1185n(this.f891t).m1406c(f));
            }
            viewPropertyAnimatorCompatSet.m2312a(f864i);
            viewPropertyAnimatorCompatSet.m2308a(250);
            viewPropertyAnimatorCompatSet.m2311a(this.f880e);
            this.f874H = viewPropertyAnimatorCompatSet;
            viewPropertyAnimatorCompatSet.m2313a();
            return;
        }
        this.f880e.m1376b(null);
    }

    public void m2243j(boolean z) {
        ViewPropertyAnimatorCompat a;
        ViewPropertyAnimatorCompat a2;
        if (z) {
            m2217j();
        } else {
            m2218k();
        }
        if (z) {
            a = this.f889r.m2895a(4, 100);
            a2 = this.f890s.m2553a(0, 200);
        } else {
            a2 = this.f889r.m2895a(0, 200);
            a = this.f890s.m2553a(8, 100);
        }
        ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet = new ViewPropertyAnimatorCompatSet();
        viewPropertyAnimatorCompatSet.m2310a(a, a2);
        viewPropertyAnimatorCompatSet.m2313a();
    }

    public Context m2221a() {
        if (this.f884m == null) {
            TypedValue typedValue = new TypedValue();
            this.f883l.getTheme().resolveAttribute(C0057R.attr.actionBarWidgetTheme, typedValue, true);
            int i = typedValue.resourceId;
            if (i != 0) {
                this.f884m = new ContextThemeWrapper(this.f883l, i);
            } else {
                this.f884m = this.f883l;
            }
        }
        return this.f884m;
    }

    public void m2239h() {
        if (this.f874H != null) {
            this.f874H.m2314b();
            this.f874H = null;
        }
    }

    public void m2241i() {
    }

    public boolean m2230c() {
        if (this.f889r == null || !this.f889r.m2909c()) {
            return false;
        }
        this.f889r.m2910d();
        return true;
    }

    public void m2229c(boolean z) {
        if (!this.f895x) {
            m2236f(z);
        }
    }
}
