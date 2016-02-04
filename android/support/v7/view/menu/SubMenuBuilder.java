package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.view.menu.MenuBuilder.Callback;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

public class SubMenuBuilder extends MenuBuilder implements SubMenu {
    private MenuBuilder f1127d;
    private MenuItemImpl f1128e;

    public SubMenuBuilder(Context context, MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        super(context);
        this.f1127d = menuBuilder;
        this.f1128e = menuItemImpl;
    }

    public void setQwertyMode(boolean z) {
        this.f1127d.setQwertyMode(z);
    }

    public boolean m2532b() {
        return this.f1127d.m2433b();
    }

    public boolean m2533c() {
        return this.f1127d.m2436c();
    }

    public Menu m2537s() {
        return this.f1127d;
    }

    public MenuItem getItem() {
        return this.f1128e;
    }

    public void m2530a(Callback callback) {
        this.f1127d.m2418a(callback);
    }

    public MenuBuilder m2536p() {
        return this.f1127d;
    }

    boolean m2531a(MenuBuilder menuBuilder, MenuItem menuItem) {
        return super.m2425a(menuBuilder, menuItem) || this.f1127d.m2425a(menuBuilder, menuItem);
    }

    public SubMenu setIcon(Drawable drawable) {
        this.f1128e.setIcon(drawable);
        return this;
    }

    public SubMenu setIcon(int i) {
        this.f1128e.setIcon(i);
        return this;
    }

    public SubMenu setHeaderIcon(Drawable drawable) {
        super.m2411a(drawable);
        return this;
    }

    public SubMenu setHeaderIcon(int i) {
        super.m2411a(ContextCompat.m98a(m2440e(), i));
        return this;
    }

    public SubMenu setHeaderTitle(CharSequence charSequence) {
        super.m2413a(charSequence);
        return this;
    }

    public SubMenu setHeaderTitle(int i) {
        super.m2413a(m2440e().getResources().getString(i));
        return this;
    }

    public SubMenu setHeaderView(View view) {
        super.m2412a(view);
        return this;
    }

    public boolean m2534c(MenuItemImpl menuItemImpl) {
        return this.f1127d.m2437c(menuItemImpl);
    }

    public boolean m2535d(MenuItemImpl menuItemImpl) {
        return this.f1127d.m2439d(menuItemImpl);
    }

    public String m2529a() {
        int itemId = this.f1128e != null ? this.f1128e.getItemId() : 0;
        if (itemId == 0) {
            return null;
        }
        return super.m2416a() + ":" + itemId;
    }
}
