package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.ActionProvider.SubUiVisibilityListener;
import android.support.v7.appcompat.C0057R;
import android.support.v7.transition.ActionBarTransition;
import android.support.v7.view.ActionBarPolicy;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.view.menu.ActionMenuItemView.PopupCallback;
import android.support.v7.view.menu.BaseMenuPresenter;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.view.menu.MenuPresenter.Callback;
import android.support.v7.view.menu.MenuView;
import android.support.v7.view.menu.MenuView.ItemView;
import android.support.v7.view.menu.SubMenuBuilder;
import android.support.v7.widget.ActionMenuView.ActionMenuChildView;
import android.support.v7.widget.ListPopupWindow.ForwardingListener;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import java.util.ArrayList;

class ActionMenuPresenter extends BaseMenuPresenter implements SubUiVisibilityListener {
    private ActionMenuPopupCallback f1208A;
    final PopupPresenterCallback f1209g;
    int f1210h;
    private OverflowMenuButton f1211i;
    private Drawable f1212j;
    private boolean f1213k;
    private boolean f1214l;
    private boolean f1215m;
    private int f1216n;
    private int f1217o;
    private int f1218p;
    private boolean f1219q;
    private boolean f1220r;
    private boolean f1221s;
    private boolean f1222t;
    private int f1223u;
    private final SparseBooleanArray f1224v;
    private View f1225w;
    private OverflowPopup f1226x;
    private ActionButtonSubmenu f1227y;
    private OpenOverflowRunnable f1228z;

    class ActionButtonSubmenu extends MenuPopupHelper {
        final /* synthetic */ ActionMenuPresenter f1195c;
        private SubMenuBuilder f1196d;

        public ActionButtonSubmenu(ActionMenuPresenter actionMenuPresenter, Context context, SubMenuBuilder subMenuBuilder) {
            boolean z = false;
            this.f1195c = actionMenuPresenter;
            super(context, subMenuBuilder, null, false, C0057R.attr.actionOverflowMenuStyle);
            this.f1196d = subMenuBuilder;
            if (!((MenuItemImpl) subMenuBuilder.getItem()).m2482j()) {
                m2515a(actionMenuPresenter.f1211i == null ? (View) actionMenuPresenter.f : actionMenuPresenter.f1211i);
            }
            m2514a(actionMenuPresenter.f1209g);
            int size = subMenuBuilder.size();
            for (int i = 0; i < size; i++) {
                MenuItem item = subMenuBuilder.getItem(i);
                if (item.isVisible() && item.getIcon() != null) {
                    z = true;
                    break;
                }
            }
            m2516a(z);
        }

        public void onDismiss() {
            super.onDismiss();
            this.f1195c.f1227y = null;
            this.f1195c.f1210h = 0;
        }
    }

    class ActionMenuPopupCallback extends PopupCallback {
        final /* synthetic */ ActionMenuPresenter f1197a;

        private ActionMenuPopupCallback(ActionMenuPresenter actionMenuPresenter) {
            this.f1197a = actionMenuPresenter;
        }

        public ListPopupWindow m2597a() {
            return this.f1197a.f1227y != null ? this.f1197a.f1227y.m2522c() : null;
        }
    }

    class OpenOverflowRunnable implements Runnable {
        final /* synthetic */ ActionMenuPresenter f1198a;
        private OverflowPopup f1199b;

        public OpenOverflowRunnable(ActionMenuPresenter actionMenuPresenter, OverflowPopup overflowPopup) {
            this.f1198a = actionMenuPresenter;
            this.f1199b = overflowPopup;
        }

        public void run() {
            this.f1198a.c.m2441f();
            View view = (View) this.f1198a.f;
            if (!(view == null || view.getWindowToken() == null || !this.f1199b.m2523d())) {
                this.f1198a.f1226x = this.f1199b;
            }
            this.f1198a.f1228z = null;
        }
    }

    class OverflowMenuButton extends AppCompatImageView implements ActionMenuChildView {
        final /* synthetic */ ActionMenuPresenter f1204a;
        private final float[] f1205b;

        /* renamed from: android.support.v7.widget.ActionMenuPresenter.OverflowMenuButton.1 */
        class C00661 extends ForwardingListener {
            final /* synthetic */ ActionMenuPresenter f1200a;
            final /* synthetic */ OverflowMenuButton f1201b;

            C00661(OverflowMenuButton overflowMenuButton, View view, ActionMenuPresenter actionMenuPresenter) {
                this.f1201b = overflowMenuButton;
                this.f1200a = actionMenuPresenter;
                super(view);
            }

            public ListPopupWindow m2598a() {
                if (this.f1201b.f1204a.f1226x == null) {
                    return null;
                }
                return this.f1201b.f1204a.f1226x.m2522c();
            }

            public boolean m2599b() {
                this.f1201b.f1204a.m2635d();
                return true;
            }

            public boolean m2600c() {
                if (this.f1201b.f1204a.f1228z != null) {
                    return false;
                }
                this.f1201b.f1204a.m2636e();
                return true;
            }
        }

        public OverflowMenuButton(ActionMenuPresenter actionMenuPresenter, Context context) {
            this.f1204a = actionMenuPresenter;
            super(context, null, C0057R.attr.actionOverflowButtonStyle);
            this.f1205b = new float[2];
            setClickable(true);
            setFocusable(true);
            setVisibility(0);
            setEnabled(true);
            setOnTouchListener(new C00661(this, this, actionMenuPresenter));
        }

        public boolean performClick() {
            if (!super.performClick()) {
                playSoundEffect(0);
                this.f1204a.m2635d();
            }
            return true;
        }

        public boolean m2601c() {
            return false;
        }

        public boolean m2602d() {
            return false;
        }

        protected boolean setFrame(int i, int i2, int i3, int i4) {
            boolean frame = super.setFrame(i, i2, i3, i4);
            Drawable drawable = getDrawable();
            Drawable background = getBackground();
            if (!(drawable == null || background == null)) {
                int width = getWidth();
                int height = getHeight();
                int max = Math.max(width, height) / 2;
                width = (width + (getPaddingLeft() - getPaddingRight())) / 2;
                height = (height + (getPaddingTop() - getPaddingBottom())) / 2;
                DrawableCompat.m659a(background, width - max, height - max, width + max, height + max);
            }
            return frame;
        }
    }

    class OverflowPopup extends MenuPopupHelper {
        final /* synthetic */ ActionMenuPresenter f1206c;

        public OverflowPopup(ActionMenuPresenter actionMenuPresenter, Context context, MenuBuilder menuBuilder, View view, boolean z) {
            this.f1206c = actionMenuPresenter;
            super(context, menuBuilder, view, z, C0057R.attr.actionOverflowMenuStyle);
            m2511a(8388613);
            m2514a(actionMenuPresenter.f1209g);
        }

        public void onDismiss() {
            super.onDismiss();
            if (this.f1206c.c != null) {
                this.f1206c.c.close();
            }
            this.f1206c.f1226x = null;
        }
    }

    class PopupPresenterCallback implements Callback {
        final /* synthetic */ ActionMenuPresenter f1207a;

        private PopupPresenterCallback(ActionMenuPresenter actionMenuPresenter) {
            this.f1207a = actionMenuPresenter;
        }

        public boolean m2604a(MenuBuilder menuBuilder) {
            if (menuBuilder == null) {
                return false;
            }
            this.f1207a.f1210h = ((SubMenuBuilder) menuBuilder).getItem().getItemId();
            Callback a = this.f1207a.m2356a();
            return a != null ? a.m2115a(menuBuilder) : false;
        }

        public void m2603a(MenuBuilder menuBuilder, boolean z) {
            if (menuBuilder instanceof SubMenuBuilder) {
                ((SubMenuBuilder) menuBuilder).m2536p().m2424a(false);
            }
            Callback a = this.f1207a.m2356a();
            if (a != null) {
                a.m2114a(menuBuilder, z);
            }
        }
    }

    public ActionMenuPresenter(Context context) {
        super(context, C0057R.layout.abc_action_menu_layout, C0057R.layout.abc_action_menu_item_layout);
        this.f1224v = new SparseBooleanArray();
        this.f1209g = new PopupPresenterCallback();
    }

    public void m2620a(Context context, MenuBuilder menuBuilder) {
        super.m2360a(context, menuBuilder);
        Resources resources = context.getResources();
        ActionBarPolicy a = ActionBarPolicy.m2247a(context);
        if (!this.f1215m) {
            this.f1214l = a.m2249b();
        }
        if (!this.f1221s) {
            this.f1216n = a.m2250c();
        }
        if (!this.f1219q) {
            this.f1218p = a.m2248a();
        }
        int i = this.f1216n;
        if (this.f1214l) {
            if (this.f1211i == null) {
                this.f1211i = new OverflowMenuButton(this, this.a);
                if (this.f1213k) {
                    this.f1211i.setImageDrawable(this.f1212j);
                    this.f1212j = null;
                    this.f1213k = false;
                }
                int makeMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
                this.f1211i.measure(makeMeasureSpec, makeMeasureSpec);
            }
            i -= this.f1211i.getMeasuredWidth();
        } else {
            this.f1211i = null;
        }
        this.f1217o = i;
        this.f1223u = (int) (56.0f * resources.getDisplayMetrics().density);
        this.f1225w = null;
    }

    public void m2621a(Configuration configuration) {
        if (!this.f1219q) {
            this.f1218p = this.b.getResources().getInteger(C0057R.integer.abc_max_action_buttons);
        }
        if (this.c != null) {
            this.c.m2432b(true);
        }
    }

    public void m2633c(boolean z) {
        this.f1214l = z;
        this.f1215m = true;
    }

    public void m2634d(boolean z) {
        this.f1222t = z;
    }

    public void m2622a(Drawable drawable) {
        if (this.f1211i != null) {
            this.f1211i.setImageDrawable(drawable);
            return;
        }
        this.f1213k = true;
        this.f1212j = drawable;
    }

    public Drawable m2632c() {
        if (this.f1211i != null) {
            return this.f1211i.getDrawable();
        }
        if (this.f1213k) {
            return this.f1212j;
        }
        return null;
    }

    public MenuView m2618a(ViewGroup viewGroup) {
        MenuView a = super.m2357a(viewGroup);
        ((ActionMenuView) a).setPresenter(this);
        return a;
    }

    public View m2619a(MenuItemImpl menuItemImpl, View view, ViewGroup viewGroup) {
        View actionView = menuItemImpl.getActionView();
        if (actionView == null || menuItemImpl.m2486n()) {
            actionView = super.m2358a(menuItemImpl, view, viewGroup);
        }
        actionView.setVisibility(menuItemImpl.isActionViewExpanded() ? 8 : 0);
        ActionMenuView actionMenuView = (ActionMenuView) viewGroup;
        LayoutParams layoutParams = actionView.getLayoutParams();
        if (!actionMenuView.checkLayoutParams(layoutParams)) {
            actionView.setLayoutParams(actionMenuView.m2672a(layoutParams));
        }
        return actionView;
    }

    public void m2624a(MenuItemImpl menuItemImpl, ItemView itemView) {
        itemView.m2336a(menuItemImpl, 0);
        ActionMenuItemView actionMenuItemView = (ActionMenuItemView) itemView;
        actionMenuItemView.setItemInvoker((ActionMenuView) this.f);
        if (this.f1208A == null) {
            this.f1208A = new ActionMenuPopupCallback();
        }
        actionMenuItemView.setPopupCallback(this.f1208A);
    }

    public boolean m2627a(int i, MenuItemImpl menuItemImpl) {
        return menuItemImpl.m2482j();
    }

    public void m2630b(boolean z) {
        int i;
        int i2 = 1;
        int i3 = 0;
        ViewGroup viewGroup = (ViewGroup) ((View) this.f).getParent();
        if (viewGroup != null) {
            ActionBarTransition.m2246a(viewGroup);
        }
        super.m2370b(z);
        ((View) this.f).requestLayout();
        if (this.c != null) {
            ArrayList k = this.c.m2446k();
            int size = k.size();
            for (i = 0; i < size; i++) {
                ActionProvider a = ((MenuItemImpl) k.get(i)).m2464a();
                if (a != null) {
                    a.m887a((SubUiVisibilityListener) this);
                }
            }
        }
        ArrayList l = this.c != null ? this.c.m2447l() : null;
        if (this.f1214l && l != null) {
            i = l.size();
            if (i == 1) {
                int i4;
                if (((MenuItemImpl) l.get(0)).isActionViewExpanded()) {
                    i4 = 0;
                } else {
                    i4 = 1;
                }
                i3 = i4;
            } else {
                if (i <= 0) {
                    i2 = 0;
                }
                i3 = i2;
            }
        }
        if (i3 != 0) {
            if (this.f1211i == null) {
                this.f1211i = new OverflowMenuButton(this, this.a);
            }
            viewGroup = (ViewGroup) this.f1211i.getParent();
            if (viewGroup != this.f) {
                if (viewGroup != null) {
                    viewGroup.removeView(this.f1211i);
                }
                ActionMenuView actionMenuView = (ActionMenuView) this.f;
                actionMenuView.addView(this.f1211i, actionMenuView.m2681c());
            }
        } else if (this.f1211i != null && this.f1211i.getParent() == this.f) {
            ((ViewGroup) this.f).removeView(this.f1211i);
        }
        ((ActionMenuView) this.f).setOverflowReserved(this.f1214l);
    }

    public boolean m2629a(ViewGroup viewGroup, int i) {
        if (viewGroup.getChildAt(i) == this.f1211i) {
            return false;
        }
        return super.m2368a(viewGroup, i);
    }

    public boolean m2628a(SubMenuBuilder subMenuBuilder) {
        if (!subMenuBuilder.hasVisibleItems()) {
            return false;
        }
        SubMenuBuilder subMenuBuilder2 = subMenuBuilder;
        while (subMenuBuilder2.m2537s() != this.c) {
            subMenuBuilder2 = (SubMenuBuilder) subMenuBuilder2.m2537s();
        }
        View a = m2609a(subMenuBuilder2.getItem());
        if (a == null) {
            if (this.f1211i == null) {
                return false;
            }
            a = this.f1211i;
        }
        this.f1210h = subMenuBuilder.getItem().getItemId();
        this.f1227y = new ActionButtonSubmenu(this, this.b, subMenuBuilder);
        this.f1227y.m2515a(a);
        this.f1227y.m2510a();
        super.m2367a(subMenuBuilder);
        return true;
    }

    private View m2609a(MenuItem menuItem) {
        ViewGroup viewGroup = (ViewGroup) this.f;
        if (viewGroup == null) {
            return null;
        }
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if ((childAt instanceof ItemView) && ((ItemView) childAt).getItemData() == menuItem) {
                return childAt;
            }
        }
        return null;
    }

    public boolean m2635d() {
        if (!this.f1214l || m2639h() || this.c == null || this.f == null || this.f1228z != null || this.c.m2447l().isEmpty()) {
            return false;
        }
        this.f1228z = new OpenOverflowRunnable(this, new OverflowPopup(this, this.b, this.c, this.f1211i, true));
        ((View) this.f).post(this.f1228z);
        super.m2367a(null);
        return true;
    }

    public boolean m2636e() {
        if (this.f1228z == null || this.f == null) {
            MenuPopupHelper menuPopupHelper = this.f1226x;
            if (menuPopupHelper == null) {
                return false;
            }
            menuPopupHelper.m2524e();
            return true;
        }
        ((View) this.f).removeCallbacks(this.f1228z);
        this.f1228z = null;
        return true;
    }

    public boolean m2637f() {
        return m2636e() | m2638g();
    }

    public boolean m2638g() {
        if (this.f1227y == null) {
            return false;
        }
        this.f1227y.m2524e();
        return true;
    }

    public boolean m2639h() {
        return this.f1226x != null && this.f1226x.m2525f();
    }

    public boolean m2640i() {
        return this.f1228z != null || m2639h();
    }

    public boolean m2631b() {
        int i;
        ArrayList i2 = this.c.m2444i();
        int size = i2.size();
        int i3 = this.f1218p;
        int i4 = this.f1217o;
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
        ViewGroup viewGroup = (ViewGroup) this.f;
        int i5 = 0;
        int i6 = 0;
        Object obj = null;
        int i7 = 0;
        while (i7 < size) {
            MenuItemImpl menuItemImpl = (MenuItemImpl) i2.get(i7);
            if (menuItemImpl.m2484l()) {
                i5++;
            } else if (menuItemImpl.m2483k()) {
                i6++;
            } else {
                obj = 1;
            }
            if (this.f1222t && menuItemImpl.isActionViewExpanded()) {
                i = 0;
            } else {
                i = i3;
            }
            i7++;
            i3 = i;
        }
        if (this.f1214l && (r4 != null || i5 + i6 > i3)) {
            i3--;
        }
        i7 = i3 - i5;
        SparseBooleanArray sparseBooleanArray = this.f1224v;
        sparseBooleanArray.clear();
        i = 0;
        if (this.f1220r) {
            i = i4 / this.f1223u;
            i6 = ((i4 % this.f1223u) / i) + this.f1223u;
        } else {
            i6 = 0;
        }
        int i8 = 0;
        i3 = 0;
        int i9 = i;
        while (i8 < size) {
            menuItemImpl = (MenuItemImpl) i2.get(i8);
            int i10;
            if (menuItemImpl.m2484l()) {
                View a = m2619a(menuItemImpl, this.f1225w, viewGroup);
                if (this.f1225w == null) {
                    this.f1225w = a;
                }
                if (this.f1220r) {
                    i9 -= ActionMenuView.m2667a(a, i6, i9, makeMeasureSpec, 0);
                } else {
                    a.measure(makeMeasureSpec, makeMeasureSpec);
                }
                i5 = a.getMeasuredWidth();
                i10 = i4 - i5;
                if (i3 != 0) {
                    i5 = i3;
                }
                i3 = menuItemImpl.getGroupId();
                if (i3 != 0) {
                    sparseBooleanArray.put(i3, true);
                }
                menuItemImpl.m2475d(true);
                i = i10;
                i3 = i7;
            } else if (menuItemImpl.m2483k()) {
                boolean z;
                int groupId = menuItemImpl.getGroupId();
                boolean z2 = sparseBooleanArray.get(groupId);
                boolean z3 = (i7 > 0 || z2) && i4 > 0 && (!this.f1220r || i9 > 0);
                if (z3) {
                    View a2 = m2619a(menuItemImpl, this.f1225w, viewGroup);
                    if (this.f1225w == null) {
                        this.f1225w = a2;
                    }
                    boolean z4;
                    if (this.f1220r) {
                        int a3 = ActionMenuView.m2667a(a2, i6, i9, makeMeasureSpec, 0);
                        i10 = i9 - a3;
                        if (a3 == 0) {
                            i9 = 0;
                        } else {
                            z4 = z3;
                        }
                        i5 = i10;
                    } else {
                        a2.measure(makeMeasureSpec, makeMeasureSpec);
                        boolean z5 = z3;
                        i5 = i9;
                        z4 = z5;
                    }
                    i10 = a2.getMeasuredWidth();
                    i4 -= i10;
                    if (i3 == 0) {
                        i3 = i10;
                    }
                    if (this.f1220r) {
                        z = i9 & (i4 >= 0 ? 1 : 0);
                        i10 = i3;
                        i3 = i5;
                    } else {
                        z = i9 & (i4 + i3 > 0 ? 1 : 0);
                        i10 = i3;
                        i3 = i5;
                    }
                } else {
                    z = z3;
                    i10 = i3;
                    i3 = i9;
                }
                if (z && groupId != 0) {
                    sparseBooleanArray.put(groupId, true);
                    i9 = i7;
                } else if (z2) {
                    sparseBooleanArray.put(groupId, false);
                    i5 = i7;
                    for (i7 = 0; i7 < i8; i7++) {
                        MenuItemImpl menuItemImpl2 = (MenuItemImpl) i2.get(i7);
                        if (menuItemImpl2.getGroupId() == groupId) {
                            if (menuItemImpl2.m2482j()) {
                                i5++;
                            }
                            menuItemImpl2.m2475d(false);
                        }
                    }
                    i9 = i5;
                } else {
                    i9 = i7;
                }
                if (z) {
                    i9--;
                }
                menuItemImpl.m2475d(z);
                i5 = i10;
                i = i4;
                int i11 = i3;
                i3 = i9;
                i9 = i11;
            } else {
                menuItemImpl.m2475d(false);
                i5 = i3;
                i = i4;
                i3 = i7;
            }
            i8++;
            i4 = i;
            i7 = i3;
            i3 = i5;
        }
        return true;
    }

    public void m2623a(MenuBuilder menuBuilder, boolean z) {
        m2637f();
        super.m2361a(menuBuilder, z);
    }

    public void m2626a(boolean z) {
        if (z) {
            super.m2367a(null);
        } else {
            this.c.m2424a(false);
        }
    }

    public void m2625a(ActionMenuView actionMenuView) {
        this.f = actionMenuView;
        actionMenuView.m2673a(this.c);
    }
}
