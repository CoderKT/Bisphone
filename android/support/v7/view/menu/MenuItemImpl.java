package android.support.v7.view.menu;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.ActionProvider.VisibilityListener;
import android.support.v4.view.MenuItemCompat.OnActionExpandListener;
import android.support.v7.view.menu.MenuView.ItemView;
import android.support.v7.widget.TintManager;
import android.util.Log;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewDebug.CapturedViewProperty;
import android.widget.LinearLayout;
import app.C0110R;
import se.emilsjolander.stickylistheaders.C1128R;

public final class MenuItemImpl implements SupportMenuItem {
    private static String f1072w;
    private static String f1073x;
    private static String f1074y;
    private static String f1075z;
    private final int f1076a;
    private final int f1077b;
    private final int f1078c;
    private final int f1079d;
    private CharSequence f1080e;
    private CharSequence f1081f;
    private Intent f1082g;
    private char f1083h;
    private char f1084i;
    private Drawable f1085j;
    private int f1086k;
    private MenuBuilder f1087l;
    private SubMenuBuilder f1088m;
    private Runnable f1089n;
    private OnMenuItemClickListener f1090o;
    private int f1091p;
    private int f1092q;
    private View f1093r;
    private ActionProvider f1094s;
    private OnActionExpandListener f1095t;
    private boolean f1096u;
    private ContextMenuInfo f1097v;

    /* renamed from: android.support.v7.view.menu.MenuItemImpl.1 */
    class C00601 implements VisibilityListener {
        final /* synthetic */ MenuItemImpl f1071a;

        C00601(MenuItemImpl menuItemImpl) {
            this.f1071a = menuItemImpl;
        }

        public void m2458a(boolean z) {
            this.f1071a.f1087l.m2419a(this.f1071a);
        }
    }

    public /* synthetic */ MenuItem setActionView(int i) {
        return m2460a(i);
    }

    public /* synthetic */ MenuItem setActionView(View view) {
        return m2463a(view);
    }

    public /* synthetic */ MenuItem setShowAsActionFlags(int i) {
        return m2469b(i);
    }

    MenuItemImpl(MenuBuilder menuBuilder, int i, int i2, int i3, int i4, CharSequence charSequence, int i5) {
        this.f1086k = 0;
        this.f1091p = 16;
        this.f1092q = 0;
        this.f1096u = false;
        this.f1087l = menuBuilder;
        this.f1076a = i2;
        this.f1077b = i;
        this.f1078c = i3;
        this.f1079d = i4;
        this.f1080e = charSequence;
        this.f1092q = i5;
    }

    public boolean m2471b() {
        if ((this.f1090o != null && this.f1090o.onMenuItemClick(this)) || this.f1087l.m2425a(this.f1087l.m2451p(), (MenuItem) this)) {
            return true;
        }
        if (this.f1089n != null) {
            this.f1089n.run();
            return true;
        }
        if (this.f1082g != null) {
            try {
                this.f1087l.m2440e().startActivity(this.f1082g);
                return true;
            } catch (Throwable e) {
                Log.e("MenuItemImpl", "Can't find activity to handle intent; ignoring", e);
            }
        }
        if (this.f1094s == null || !this.f1094s.m893d()) {
            return false;
        }
        return true;
    }

    public boolean isEnabled() {
        return (this.f1091p & 16) != 0;
    }

    public MenuItem setEnabled(boolean z) {
        if (z) {
            this.f1091p |= 16;
        } else {
            this.f1091p &= -17;
        }
        this.f1087l.m2432b(false);
        return this;
    }

    public int getGroupId() {
        return this.f1077b;
    }

    @CapturedViewProperty
    public int getItemId() {
        return this.f1076a;
    }

    public int getOrder() {
        return this.f1078c;
    }

    public int m2472c() {
        return this.f1079d;
    }

    public Intent getIntent() {
        return this.f1082g;
    }

    public MenuItem setIntent(Intent intent) {
        this.f1082g = intent;
        return this;
    }

    public char getAlphabeticShortcut() {
        return this.f1084i;
    }

    public MenuItem setAlphabeticShortcut(char c) {
        if (this.f1084i != c) {
            this.f1084i = Character.toLowerCase(c);
            this.f1087l.m2432b(false);
        }
        return this;
    }

    public char getNumericShortcut() {
        return this.f1083h;
    }

    public MenuItem setNumericShortcut(char c) {
        if (this.f1083h != c) {
            this.f1083h = c;
            this.f1087l.m2432b(false);
        }
        return this;
    }

    public MenuItem setShortcut(char c, char c2) {
        this.f1083h = c;
        this.f1084i = Character.toLowerCase(c2);
        this.f1087l.m2432b(false);
        return this;
    }

    char m2474d() {
        return this.f1087l.m2433b() ? this.f1084i : this.f1083h;
    }

    String m2476e() {
        char d = m2474d();
        if (d == '\u0000') {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder(f1072w);
        switch (d) {
            case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                stringBuilder.append(f1074y);
                break;
            case C1128R.styleable.StickyListHeadersListView_android_drawSelectorOnTop /*10*/:
                stringBuilder.append(f1073x);
                break;
            case C0110R.styleable.Theme_actionModeCutDrawable /*32*/:
                stringBuilder.append(f1075z);
                break;
            default:
                stringBuilder.append(d);
                break;
        }
        return stringBuilder.toString();
    }

    boolean m2478f() {
        return this.f1087l.m2436c() && m2474d() != '\u0000';
    }

    public SubMenu getSubMenu() {
        return this.f1088m;
    }

    public boolean hasSubMenu() {
        return this.f1088m != null;
    }

    public void m2466a(SubMenuBuilder subMenuBuilder) {
        this.f1088m = subMenuBuilder;
        subMenuBuilder.setHeaderTitle(getTitle());
    }

    @CapturedViewProperty
    public CharSequence getTitle() {
        return this.f1080e;
    }

    CharSequence m2465a(ItemView itemView) {
        return (itemView == null || !itemView.m2337a()) ? getTitle() : getTitleCondensed();
    }

    public MenuItem setTitle(CharSequence charSequence) {
        this.f1080e = charSequence;
        this.f1087l.m2432b(false);
        if (this.f1088m != null) {
            this.f1088m.setHeaderTitle(charSequence);
        }
        return this;
    }

    public MenuItem setTitle(int i) {
        return setTitle(this.f1087l.m2440e().getString(i));
    }

    public CharSequence getTitleCondensed() {
        CharSequence charSequence = this.f1081f != null ? this.f1081f : this.f1080e;
        if (VERSION.SDK_INT >= 18 || charSequence == null || (charSequence instanceof String)) {
            return charSequence;
        }
        return charSequence.toString();
    }

    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f1081f = charSequence;
        if (charSequence == null) {
            CharSequence charSequence2 = this.f1080e;
        }
        this.f1087l.m2432b(false);
        return this;
    }

    public Drawable getIcon() {
        if (this.f1085j != null) {
            return this.f1085j;
        }
        if (this.f1086k == 0) {
            return null;
        }
        Drawable a = TintManager.m3736a(this.f1087l.m2440e(), this.f1086k);
        this.f1086k = 0;
        this.f1085j = a;
        return a;
    }

    public MenuItem setIcon(Drawable drawable) {
        this.f1086k = 0;
        this.f1085j = drawable;
        this.f1087l.m2432b(false);
        return this;
    }

    public MenuItem setIcon(int i) {
        this.f1085j = null;
        this.f1086k = i;
        this.f1087l.m2432b(false);
        return this;
    }

    public boolean isCheckable() {
        return (this.f1091p & 1) == 1;
    }

    public MenuItem setCheckable(boolean z) {
        int i = this.f1091p;
        this.f1091p = (z ? 1 : 0) | (this.f1091p & -2);
        if (i != this.f1091p) {
            this.f1087l.m2432b(false);
        }
        return this;
    }

    public void m2468a(boolean z) {
        this.f1091p = (z ? 4 : 0) | (this.f1091p & -5);
    }

    public boolean m2479g() {
        return (this.f1091p & 4) != 0;
    }

    public boolean isChecked() {
        return (this.f1091p & 2) == 2;
    }

    public MenuItem setChecked(boolean z) {
        if ((this.f1091p & 4) != 0) {
            this.f1087l.m2422a((MenuItem) this);
        } else {
            m2470b(z);
        }
        return this;
    }

    void m2470b(boolean z) {
        int i;
        int i2 = this.f1091p;
        int i3 = this.f1091p & -3;
        if (z) {
            i = 2;
        } else {
            i = 0;
        }
        this.f1091p = i | i3;
        if (i2 != this.f1091p) {
            this.f1087l.m2432b(false);
        }
    }

    public boolean isVisible() {
        if (this.f1094s == null || !this.f1094s.m891b()) {
            if ((this.f1091p & 8) != 0) {
                return false;
            }
            return true;
        } else if ((this.f1091p & 8) == 0 && this.f1094s.m892c()) {
            return true;
        } else {
            return false;
        }
    }

    boolean m2473c(boolean z) {
        int i = this.f1091p;
        this.f1091p = (z ? 0 : 8) | (this.f1091p & -9);
        if (i != this.f1091p) {
            return true;
        }
        return false;
    }

    public MenuItem setVisible(boolean z) {
        if (m2473c(z)) {
            this.f1087l.m2419a(this);
        }
        return this;
    }

    public MenuItem setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        this.f1090o = onMenuItemClickListener;
        return this;
    }

    public String toString() {
        return this.f1080e != null ? this.f1080e.toString() : null;
    }

    void m2467a(ContextMenuInfo contextMenuInfo) {
        this.f1097v = contextMenuInfo;
    }

    public ContextMenuInfo getMenuInfo() {
        return this.f1097v;
    }

    public void m2480h() {
        this.f1087l.m2430b(this);
    }

    public boolean m2481i() {
        return this.f1087l.m2452q();
    }

    public boolean m2482j() {
        return (this.f1091p & 32) == 32;
    }

    public boolean m2483k() {
        return (this.f1092q & 1) == 1;
    }

    public boolean m2484l() {
        return (this.f1092q & 2) == 2;
    }

    public void m2475d(boolean z) {
        if (z) {
            this.f1091p |= 32;
        } else {
            this.f1091p &= -33;
        }
    }

    public boolean m2485m() {
        return (this.f1092q & 4) == 4;
    }

    public void setShowAsAction(int i) {
        switch (i & 3) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                this.f1092q = i;
                this.f1087l.m2430b(this);
            default:
                throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
        }
    }

    public SupportMenuItem m2463a(View view) {
        this.f1093r = view;
        this.f1094s = null;
        if (view != null && view.getId() == -1 && this.f1076a > 0) {
            view.setId(this.f1076a);
        }
        this.f1087l.m2430b(this);
        return this;
    }

    public SupportMenuItem m2460a(int i) {
        Context e = this.f1087l.m2440e();
        m2463a(LayoutInflater.from(e).inflate(i, new LinearLayout(e), false));
        return this;
    }

    public View getActionView() {
        if (this.f1093r != null) {
            return this.f1093r;
        }
        if (this.f1094s == null) {
            return null;
        }
        this.f1093r = this.f1094s.m886a((MenuItem) this);
        return this.f1093r;
    }

    public MenuItem setActionProvider(android.view.ActionProvider actionProvider) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setActionProvider()");
    }

    public android.view.ActionProvider getActionProvider() {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.getActionProvider()");
    }

    public ActionProvider m2464a() {
        return this.f1094s;
    }

    public SupportMenuItem m2461a(ActionProvider actionProvider) {
        if (this.f1094s != null) {
            this.f1094s.m895f();
        }
        this.f1093r = null;
        this.f1094s = actionProvider;
        this.f1087l.m2432b(true);
        if (this.f1094s != null) {
            this.f1094s.m888a(new C00601(this));
        }
        return this;
    }

    public SupportMenuItem m2469b(int i) {
        setShowAsAction(i);
        return this;
    }

    public boolean expandActionView() {
        if (!m2486n()) {
            return false;
        }
        if (this.f1095t == null || this.f1095t.m954a(this)) {
            return this.f1087l.m2437c(this);
        }
        return false;
    }

    public boolean collapseActionView() {
        if ((this.f1092q & 8) == 0) {
            return false;
        }
        if (this.f1093r == null) {
            return true;
        }
        if (this.f1095t == null || this.f1095t.m955b(this)) {
            return this.f1087l.m2439d(this);
        }
        return false;
    }

    public SupportMenuItem m2462a(OnActionExpandListener onActionExpandListener) {
        this.f1095t = onActionExpandListener;
        return this;
    }

    public boolean m2486n() {
        if ((this.f1092q & 8) == 0) {
            return false;
        }
        if (this.f1093r == null && this.f1094s != null) {
            this.f1093r = this.f1094s.m886a((MenuItem) this);
        }
        if (this.f1093r != null) {
            return true;
        }
        return false;
    }

    public void m2477e(boolean z) {
        this.f1096u = z;
        this.f1087l.m2432b(false);
    }

    public boolean isActionViewExpanded() {
        return this.f1096u;
    }

    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setOnActionExpandListener()");
    }
}
