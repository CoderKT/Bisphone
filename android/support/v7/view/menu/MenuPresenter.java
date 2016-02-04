package android.support.v7.view.menu;

import android.content.Context;

public interface MenuPresenter {

    public interface Callback {
        void m2114a(MenuBuilder menuBuilder, boolean z);

        boolean m2115a(MenuBuilder menuBuilder);
    }

    void m2349a(Context context, MenuBuilder menuBuilder);

    void m2350a(MenuBuilder menuBuilder, boolean z);

    boolean m2351a(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl);

    boolean m2352a(SubMenuBuilder subMenuBuilder);

    void m2353b(boolean z);

    boolean m2354b();

    boolean m2355b(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl);
}
