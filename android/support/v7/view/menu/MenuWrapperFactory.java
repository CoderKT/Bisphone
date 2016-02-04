package android.support.v7.view.menu;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.internal.view.SupportSubMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

public final class MenuWrapperFactory {
    public static Menu m2526a(Context context, SupportMenu supportMenu) {
        if (VERSION.SDK_INT >= 14) {
            return new MenuWrapperICS(context, supportMenu);
        }
        throw new UnsupportedOperationException();
    }

    public static MenuItem m2527a(Context context, SupportMenuItem supportMenuItem) {
        if (VERSION.SDK_INT >= 16) {
            return new MenuItemWrapperJB(context, supportMenuItem);
        }
        if (VERSION.SDK_INT >= 14) {
            return new MenuItemWrapperICS(context, supportMenuItem);
        }
        throw new UnsupportedOperationException();
    }

    public static SubMenu m2528a(Context context, SupportSubMenu supportSubMenu) {
        if (VERSION.SDK_INT >= 14) {
            return new SubMenuWrapperICS(context, supportSubMenu);
        }
        throw new UnsupportedOperationException();
    }
}
