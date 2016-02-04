package android.support.v7.view.menu;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.view.menu.MenuPresenter.Callback;
import android.support.v7.view.menu.MenuView.ItemView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

public abstract class BaseMenuPresenter implements MenuPresenter {
    protected Context f1000a;
    protected Context f1001b;
    protected MenuBuilder f1002c;
    protected LayoutInflater f1003d;
    protected LayoutInflater f1004e;
    protected MenuView f1005f;
    private Callback f1006g;
    private int f1007h;
    private int f1008i;
    private int f1009j;

    public abstract void m2362a(MenuItemImpl menuItemImpl, ItemView itemView);

    public BaseMenuPresenter(Context context, int i, int i2) {
        this.f1000a = context;
        this.f1003d = LayoutInflater.from(context);
        this.f1007h = i;
        this.f1008i = i2;
    }

    public void m2360a(Context context, MenuBuilder menuBuilder) {
        this.f1001b = context;
        this.f1004e = LayoutInflater.from(this.f1001b);
        this.f1002c = menuBuilder;
    }

    public MenuView m2357a(ViewGroup viewGroup) {
        if (this.f1005f == null) {
            this.f1005f = (MenuView) this.f1003d.inflate(this.f1007h, viewGroup, false);
            this.f1005f.m2379a(this.f1002c);
            m2370b(true);
        }
        return this.f1005f;
    }

    public void m2370b(boolean z) {
        ViewGroup viewGroup = (ViewGroup) this.f1005f;
        if (viewGroup != null) {
            int i;
            if (this.f1002c != null) {
                this.f1002c.m2445j();
                ArrayList i2 = this.f1002c.m2444i();
                int size = i2.size();
                int i3 = 0;
                i = 0;
                while (i3 < size) {
                    int i4;
                    MenuItemImpl menuItemImpl = (MenuItemImpl) i2.get(i3);
                    if (m2365a(i, menuItemImpl)) {
                        View childAt = viewGroup.getChildAt(i);
                        MenuItemImpl itemData = childAt instanceof ItemView ? ((ItemView) childAt).getItemData() : null;
                        View a = m2358a(menuItemImpl, childAt, viewGroup);
                        if (menuItemImpl != itemData) {
                            a.setPressed(false);
                            ViewCompat.m1188q(a);
                        }
                        if (a != childAt) {
                            m2364a(a, i);
                        }
                        i4 = i + 1;
                    } else {
                        i4 = i;
                    }
                    i3++;
                    i = i4;
                }
            } else {
                i = 0;
            }
            while (i < viewGroup.getChildCount()) {
                if (!m2368a(viewGroup, i)) {
                    i++;
                }
            }
        }
    }

    protected void m2364a(View view, int i) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
        ((ViewGroup) this.f1005f).addView(view, i);
    }

    protected boolean m2368a(ViewGroup viewGroup, int i) {
        viewGroup.removeViewAt(i);
        return true;
    }

    public void m2363a(Callback callback) {
        this.f1006g = callback;
    }

    public Callback m2356a() {
        return this.f1006g;
    }

    public ItemView m2369b(ViewGroup viewGroup) {
        return (ItemView) this.f1003d.inflate(this.f1008i, viewGroup, false);
    }

    public View m2358a(MenuItemImpl menuItemImpl, View view, ViewGroup viewGroup) {
        ItemView itemView;
        if (view instanceof ItemView) {
            itemView = (ItemView) view;
        } else {
            itemView = m2369b(viewGroup);
        }
        m2362a(menuItemImpl, itemView);
        return (View) itemView;
    }

    public boolean m2365a(int i, MenuItemImpl menuItemImpl) {
        return true;
    }

    public void m2361a(MenuBuilder menuBuilder, boolean z) {
        if (this.f1006g != null) {
            this.f1006g.m2114a(menuBuilder, z);
        }
    }

    public boolean m2367a(SubMenuBuilder subMenuBuilder) {
        if (this.f1006g != null) {
            return this.f1006g.m2115a(subMenuBuilder);
        }
        return false;
    }

    public boolean m2371b() {
        return false;
    }

    public boolean m2366a(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    public boolean m2372b(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    public void m2359a(int i) {
        this.f1009j = i;
    }
}
