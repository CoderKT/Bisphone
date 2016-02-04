package android.support.v4.view;

import android.view.MenuItem;
import android.view.View;

class MenuItemCompatHoneycomb {
    public static void m965a(MenuItem menuItem, int i) {
        menuItem.setShowAsAction(i);
    }

    public static MenuItem m963a(MenuItem menuItem, View view) {
        return menuItem.setActionView(view);
    }

    public static MenuItem m966b(MenuItem menuItem, int i) {
        return menuItem.setActionView(i);
    }

    public static View m964a(MenuItem menuItem) {
        return menuItem.getActionView();
    }
}
