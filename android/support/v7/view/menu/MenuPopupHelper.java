package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.appcompat.C0057R;
import android.support.v7.view.menu.MenuPresenter.Callback;
import android.support.v7.view.menu.MenuView.ItemView;
import android.support.v7.widget.ListPopupWindow;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.PopupWindow.OnDismissListener;
import java.util.ArrayList;

public class MenuPopupHelper implements MenuPresenter, OnKeyListener, OnGlobalLayoutListener, OnItemClickListener, OnDismissListener {
    static final int f1109a;
    boolean f1110b;
    private final Context f1111c;
    private final LayoutInflater f1112d;
    private final MenuBuilder f1113e;
    private final MenuAdapter f1114f;
    private final boolean f1115g;
    private final int f1116h;
    private final int f1117i;
    private final int f1118j;
    private View f1119k;
    private ListPopupWindow f1120l;
    private ViewTreeObserver f1121m;
    private Callback f1122n;
    private ViewGroup f1123o;
    private boolean f1124p;
    private int f1125q;
    private int f1126r;

    class MenuAdapter extends BaseAdapter {
        final /* synthetic */ MenuPopupHelper f1106a;
        private MenuBuilder f1107b;
        private int f1108c;

        public /* synthetic */ Object getItem(int i) {
            return m2504a(i);
        }

        public MenuAdapter(MenuPopupHelper menuPopupHelper, MenuBuilder menuBuilder) {
            this.f1106a = menuPopupHelper;
            this.f1108c = -1;
            this.f1107b = menuBuilder;
            m2505a();
        }

        public int getCount() {
            ArrayList l = this.f1106a.f1115g ? this.f1107b.m2447l() : this.f1107b.m2444i();
            if (this.f1108c < 0) {
                return l.size();
            }
            return l.size() - 1;
        }

        public MenuItemImpl m2504a(int i) {
            ArrayList l = this.f1106a.f1115g ? this.f1107b.m2447l() : this.f1107b.m2444i();
            if (this.f1108c >= 0 && i >= this.f1108c) {
                i++;
            }
            return (MenuItemImpl) l.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View inflate;
            if (view == null) {
                inflate = this.f1106a.f1112d.inflate(MenuPopupHelper.f1109a, viewGroup, false);
            } else {
                inflate = view;
            }
            ItemView itemView = (ItemView) inflate;
            if (this.f1106a.f1110b) {
                ((ListMenuItemView) inflate).setForceShowIcon(true);
            }
            itemView.m2336a(m2504a(i), 0);
            return inflate;
        }

        void m2505a() {
            MenuItemImpl r = this.f1106a.f1113e.m2453r();
            if (r != null) {
                ArrayList l = this.f1106a.f1113e.m2447l();
                int size = l.size();
                for (int i = 0; i < size; i++) {
                    if (((MenuItemImpl) l.get(i)) == r) {
                        this.f1108c = i;
                        return;
                    }
                }
            }
            this.f1108c = -1;
        }

        public void notifyDataSetChanged() {
            m2505a();
            super.notifyDataSetChanged();
        }
    }

    static {
        f1109a = C0057R.layout.abc_popup_menu_item_layout;
    }

    public MenuPopupHelper(Context context, MenuBuilder menuBuilder, View view) {
        this(context, menuBuilder, view, false, C0057R.attr.popupMenuStyle);
    }

    public MenuPopupHelper(Context context, MenuBuilder menuBuilder, View view, boolean z, int i) {
        this(context, menuBuilder, view, z, i, 0);
    }

    public MenuPopupHelper(Context context, MenuBuilder menuBuilder, View view, boolean z, int i, int i2) {
        this.f1126r = 0;
        this.f1111c = context;
        this.f1112d = LayoutInflater.from(context);
        this.f1113e = menuBuilder;
        this.f1114f = new MenuAdapter(this, this.f1113e);
        this.f1115g = z;
        this.f1117i = i;
        this.f1118j = i2;
        Resources resources = context.getResources();
        this.f1116h = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(C0057R.dimen.abc_config_prefDialogWidth));
        this.f1119k = view;
        menuBuilder.m2421a((MenuPresenter) this, context);
    }

    public void m2515a(View view) {
        this.f1119k = view;
    }

    public void m2516a(boolean z) {
        this.f1110b = z;
    }

    public void m2511a(int i) {
        this.f1126r = i;
    }

    public void m2510a() {
        if (!m2523d()) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
    }

    public ListPopupWindow m2522c() {
        return this.f1120l;
    }

    public boolean m2523d() {
        boolean z = false;
        this.f1120l = new ListPopupWindow(this.f1111c, null, this.f1117i, this.f1118j);
        this.f1120l.m2811a((OnDismissListener) this);
        this.f1120l.m2809a((OnItemClickListener) this);
        this.f1120l.m2810a(this.f1114f);
        this.f1120l.m2812a(true);
        View view = this.f1119k;
        if (view == null) {
            return false;
        }
        if (this.f1121m == null) {
            z = true;
        }
        this.f1121m = view.getViewTreeObserver();
        if (z) {
            this.f1121m.addOnGlobalLayoutListener(this);
        }
        this.f1120l.m2808a(view);
        this.f1120l.m2817d(this.f1126r);
        if (!this.f1124p) {
            this.f1125q = m2509g();
            this.f1124p = true;
        }
        this.f1120l.m2821f(this.f1125q);
        this.f1120l.m2823g(2);
        this.f1120l.m2814c();
        this.f1120l.m2830m().setOnKeyListener(this);
        return true;
    }

    public void m2524e() {
        if (m2525f()) {
            this.f1120l.m2826i();
        }
    }

    public void onDismiss() {
        this.f1120l = null;
        this.f1113e.close();
        if (this.f1121m != null) {
            if (!this.f1121m.isAlive()) {
                this.f1121m = this.f1119k.getViewTreeObserver();
            }
            this.f1121m.removeGlobalOnLayoutListener(this);
            this.f1121m = null;
        }
    }

    public boolean m2525f() {
        return this.f1120l != null && this.f1120l.m2828k();
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        MenuAdapter menuAdapter = this.f1114f;
        menuAdapter.f1107b.m2426a(menuAdapter.m2504a(i), 0);
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i != 82) {
            return false;
        }
        m2524e();
        return true;
    }

    private int m2509g() {
        ListAdapter listAdapter = this.f1114f;
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
        int makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(0, 0);
        int count = listAdapter.getCount();
        int i = 0;
        int i2 = 0;
        View view = null;
        int i3 = 0;
        while (i < count) {
            View view2;
            int itemViewType = listAdapter.getItemViewType(i);
            if (itemViewType != i2) {
                i2 = itemViewType;
                view2 = null;
            } else {
                view2 = view;
            }
            if (this.f1123o == null) {
                this.f1123o = new FrameLayout(this.f1111c);
            }
            view = listAdapter.getView(i, view2, this.f1123o);
            view.measure(makeMeasureSpec, makeMeasureSpec2);
            itemViewType = view.getMeasuredWidth();
            if (itemViewType >= this.f1116h) {
                return this.f1116h;
            }
            if (itemViewType <= i3) {
                itemViewType = i3;
            }
            i++;
            i3 = itemViewType;
        }
        return i3;
    }

    public void onGlobalLayout() {
        if (m2525f()) {
            View view = this.f1119k;
            if (view == null || !view.isShown()) {
                m2524e();
            } else if (m2525f()) {
                this.f1120l.m2814c();
            }
        }
    }

    public void m2512a(Context context, MenuBuilder menuBuilder) {
    }

    public void m2519b(boolean z) {
        this.f1124p = false;
        if (this.f1114f != null) {
            this.f1114f.notifyDataSetChanged();
        }
    }

    public void m2514a(Callback callback) {
        this.f1122n = callback;
    }

    public boolean m2518a(SubMenuBuilder subMenuBuilder) {
        if (subMenuBuilder.hasVisibleItems()) {
            boolean z;
            MenuPopupHelper menuPopupHelper = new MenuPopupHelper(this.f1111c, subMenuBuilder, this.f1119k);
            menuPopupHelper.m2514a(this.f1122n);
            int size = subMenuBuilder.size();
            for (int i = 0; i < size; i++) {
                MenuItem item = subMenuBuilder.getItem(i);
                if (item.isVisible() && item.getIcon() != null) {
                    z = true;
                    break;
                }
            }
            z = false;
            menuPopupHelper.m2516a(z);
            if (menuPopupHelper.m2523d()) {
                if (this.f1122n == null) {
                    return true;
                }
                this.f1122n.m2115a(subMenuBuilder);
                return true;
            }
        }
        return false;
    }

    public void m2513a(MenuBuilder menuBuilder, boolean z) {
        if (menuBuilder == this.f1113e) {
            m2524e();
            if (this.f1122n != null) {
                this.f1122n.m2114a(menuBuilder, z);
            }
        }
    }

    public boolean m2520b() {
        return false;
    }

    public boolean m2517a(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    public boolean m2521b(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }
}
