package android.support.v4.view;

import android.os.Build.VERSION;
import android.support.v4.internal.view.SupportMenuItem;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

public class MenuItemCompat {
    static final MenuVersionImpl f437a;

    interface MenuVersionImpl {
        MenuItem m934a(MenuItem menuItem, View view);

        View m935a(MenuItem menuItem);

        void m936a(MenuItem menuItem, int i);

        MenuItem m937b(MenuItem menuItem, int i);

        boolean m938b(MenuItem menuItem);

        boolean m939c(MenuItem menuItem);
    }

    class BaseMenuVersionImpl implements MenuVersionImpl {
        BaseMenuVersionImpl() {
        }

        public void m942a(MenuItem menuItem, int i) {
        }

        public MenuItem m940a(MenuItem menuItem, View view) {
            return menuItem;
        }

        public MenuItem m943b(MenuItem menuItem, int i) {
            return menuItem;
        }

        public View m941a(MenuItem menuItem) {
            return null;
        }

        public boolean m944b(MenuItem menuItem) {
            return false;
        }

        public boolean m945c(MenuItem menuItem) {
            return false;
        }
    }

    class HoneycombMenuVersionImpl implements MenuVersionImpl {
        HoneycombMenuVersionImpl() {
        }

        public void m948a(MenuItem menuItem, int i) {
            MenuItemCompatHoneycomb.m965a(menuItem, i);
        }

        public MenuItem m946a(MenuItem menuItem, View view) {
            return MenuItemCompatHoneycomb.m963a(menuItem, view);
        }

        public MenuItem m949b(MenuItem menuItem, int i) {
            return MenuItemCompatHoneycomb.m966b(menuItem, i);
        }

        public View m947a(MenuItem menuItem) {
            return MenuItemCompatHoneycomb.m964a(menuItem);
        }

        public boolean m950b(MenuItem menuItem) {
            return false;
        }

        public boolean m951c(MenuItem menuItem) {
            return false;
        }
    }

    class IcsMenuVersionImpl extends HoneycombMenuVersionImpl {
        IcsMenuVersionImpl() {
        }

        public boolean m952b(MenuItem menuItem) {
            return MenuItemCompatIcs.m967a(menuItem);
        }

        public boolean m953c(MenuItem menuItem) {
            return MenuItemCompatIcs.m968b(menuItem);
        }
    }

    public interface OnActionExpandListener {
        boolean m954a(MenuItem menuItem);

        boolean m955b(MenuItem menuItem);
    }

    static {
        int i = VERSION.SDK_INT;
        if (i >= 14) {
            f437a = new IcsMenuVersionImpl();
        } else if (i >= 11) {
            f437a = new HoneycombMenuVersionImpl();
        } else {
            f437a = new BaseMenuVersionImpl();
        }
    }

    public static void m959a(MenuItem menuItem, int i) {
        if (menuItem instanceof SupportMenuItem) {
            ((SupportMenuItem) menuItem).setShowAsAction(i);
        } else {
            f437a.m936a(menuItem, i);
        }
    }

    public static MenuItem m957a(MenuItem menuItem, View view) {
        if (menuItem instanceof SupportMenuItem) {
            return ((SupportMenuItem) menuItem).setActionView(view);
        }
        return f437a.m934a(menuItem, view);
    }

    public static MenuItem m960b(MenuItem menuItem, int i) {
        if (menuItem instanceof SupportMenuItem) {
            return ((SupportMenuItem) menuItem).setActionView(i);
        }
        return f437a.m937b(menuItem, i);
    }

    public static View m958a(MenuItem menuItem) {
        if (menuItem instanceof SupportMenuItem) {
            return ((SupportMenuItem) menuItem).getActionView();
        }
        return f437a.m935a(menuItem);
    }

    public static MenuItem m956a(MenuItem menuItem, ActionProvider actionProvider) {
        if (menuItem instanceof SupportMenuItem) {
            return ((SupportMenuItem) menuItem).m686a(actionProvider);
        }
        Log.w("MenuItemCompat", "setActionProvider: item does not implement SupportMenuItem; ignoring");
        return menuItem;
    }

    public static boolean m961b(MenuItem menuItem) {
        if (menuItem instanceof SupportMenuItem) {
            return ((SupportMenuItem) menuItem).expandActionView();
        }
        return f437a.m938b(menuItem);
    }

    public static boolean m962c(MenuItem menuItem) {
        if (menuItem instanceof SupportMenuItem) {
            return ((SupportMenuItem) menuItem).isActionViewExpanded();
        }
        return f437a.m939c(menuItem);
    }
}
