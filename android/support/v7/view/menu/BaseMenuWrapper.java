package android.support.v7.view.menu;

import android.content.Context;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.internal.view.SupportSubMenu;
import android.support.v4.util.ArrayMap;
import android.view.MenuItem;
import android.view.SubMenu;
import java.util.Iterator;
import java.util.Map;

abstract class BaseMenuWrapper<T> extends BaseWrapper<T> {
    final Context f1011a;
    private Map<SupportMenuItem, MenuItem> f1012c;
    private Map<SupportSubMenu, SubMenu> f1013d;

    BaseMenuWrapper(Context context, T t) {
        super(t);
        this.f1011a = context;
    }

    final MenuItem m2373a(MenuItem menuItem) {
        if (!(menuItem instanceof SupportMenuItem)) {
            return menuItem;
        }
        SupportMenuItem supportMenuItem = (SupportMenuItem) menuItem;
        if (this.f1012c == null) {
            this.f1012c = new ArrayMap();
        }
        MenuItem menuItem2 = (MenuItem) this.f1012c.get(menuItem);
        if (menuItem2 != null) {
            return menuItem2;
        }
        menuItem2 = MenuWrapperFactory.m2527a(this.f1011a, supportMenuItem);
        this.f1012c.put(supportMenuItem, menuItem2);
        return menuItem2;
    }

    final SubMenu m2374a(SubMenu subMenu) {
        if (!(subMenu instanceof SupportSubMenu)) {
            return subMenu;
        }
        SupportSubMenu supportSubMenu = (SupportSubMenu) subMenu;
        if (this.f1013d == null) {
            this.f1013d = new ArrayMap();
        }
        SubMenu subMenu2 = (SubMenu) this.f1013d.get(supportSubMenu);
        if (subMenu2 != null) {
            return subMenu2;
        }
        subMenu2 = MenuWrapperFactory.m2528a(this.f1011a, supportSubMenu);
        this.f1013d.put(supportSubMenu, subMenu2);
        return subMenu2;
    }

    final void m2375a() {
        if (this.f1012c != null) {
            this.f1012c.clear();
        }
        if (this.f1013d != null) {
            this.f1013d.clear();
        }
    }

    final void m2376a(int i) {
        if (this.f1012c != null) {
            Iterator it = this.f1012c.keySet().iterator();
            while (it.hasNext()) {
                if (i == ((MenuItem) it.next()).getGroupId()) {
                    it.remove();
                }
            }
        }
    }

    final void m2377b(int i) {
        if (this.f1012c != null) {
            Iterator it = this.f1012c.keySet().iterator();
            while (it.hasNext()) {
                if (i == ((MenuItem) it.next()).getItemId()) {
                    it.remove();
                    return;
                }
            }
        }
    }
}
