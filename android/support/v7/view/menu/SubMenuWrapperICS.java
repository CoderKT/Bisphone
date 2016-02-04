package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.internal.view.SupportSubMenu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

class SubMenuWrapperICS extends MenuWrapperICS implements SubMenu {
    SubMenuWrapperICS(Context context, SupportSubMenu supportSubMenu) {
        super(context, supportSubMenu);
    }

    public SupportSubMenu m2538b() {
        return (SupportSubMenu) this.b;
    }

    public SubMenu setHeaderTitle(int i) {
        m2538b().setHeaderTitle(i);
        return this;
    }

    public SubMenu setHeaderTitle(CharSequence charSequence) {
        m2538b().setHeaderTitle(charSequence);
        return this;
    }

    public SubMenu setHeaderIcon(int i) {
        m2538b().setHeaderIcon(i);
        return this;
    }

    public SubMenu setHeaderIcon(Drawable drawable) {
        m2538b().setHeaderIcon(drawable);
        return this;
    }

    public SubMenu setHeaderView(View view) {
        m2538b().setHeaderView(view);
        return this;
    }

    public void clearHeader() {
        m2538b().clearHeader();
    }

    public SubMenu setIcon(int i) {
        m2538b().setIcon(i);
        return this;
    }

    public SubMenu setIcon(Drawable drawable) {
        m2538b().setIcon(drawable);
        return this;
    }

    public MenuItem getItem() {
        return m2373a(m2538b().getItem());
    }
}
