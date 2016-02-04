package android.support.v7.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.util.SimpleArrayMap;
import android.support.v7.view.ActionMode.Callback;
import android.support.v7.view.menu.MenuWrapperFactory;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import java.util.ArrayList;

@TargetApi(11)
public class SupportActionModeWrapper extends ActionMode {
    final Context f915a;
    final ActionMode f916b;

    public class CallbackWrapper implements Callback {
        final ActionMode.Callback f911a;
        final Context f912b;
        final ArrayList<SupportActionModeWrapper> f913c;
        final SimpleArrayMap<Menu, Menu> f914d;

        public CallbackWrapper(Context context, ActionMode.Callback callback) {
            this.f912b = context;
            this.f911a = callback;
            this.f913c = new ArrayList();
            this.f914d = new SimpleArrayMap();
        }

        public boolean m2278a(ActionMode actionMode, Menu menu) {
            return this.f911a.onCreateActionMode(m2280b(actionMode), m2276a(menu));
        }

        public boolean m2281b(ActionMode actionMode, Menu menu) {
            return this.f911a.onPrepareActionMode(m2280b(actionMode), m2276a(menu));
        }

        public boolean m2279a(ActionMode actionMode, MenuItem menuItem) {
            return this.f911a.onActionItemClicked(m2280b(actionMode), MenuWrapperFactory.m2527a(this.f912b, (SupportMenuItem) menuItem));
        }

        public void m2277a(ActionMode actionMode) {
            this.f911a.onDestroyActionMode(m2280b(actionMode));
        }

        private Menu m2276a(Menu menu) {
            Menu menu2 = (Menu) this.f914d.get(menu);
            if (menu2 != null) {
                return menu2;
            }
            menu2 = MenuWrapperFactory.m2526a(this.f912b, (SupportMenu) menu);
            this.f914d.put(menu, menu2);
            return menu2;
        }

        public ActionMode m2280b(ActionMode actionMode) {
            int size = this.f913c.size();
            for (int i = 0; i < size; i++) {
                SupportActionModeWrapper supportActionModeWrapper = (SupportActionModeWrapper) this.f913c.get(i);
                if (supportActionModeWrapper != null && supportActionModeWrapper.f916b == actionMode) {
                    return supportActionModeWrapper;
                }
            }
            ActionMode supportActionModeWrapper2 = new SupportActionModeWrapper(this.f912b, actionMode);
            this.f913c.add(supportActionModeWrapper2);
            return supportActionModeWrapper2;
        }
    }

    public SupportActionModeWrapper(Context context, ActionMode actionMode) {
        this.f915a = context;
        this.f916b = actionMode;
    }

    public Object getTag() {
        return this.f916b.m2178j();
    }

    public void setTag(Object obj) {
        this.f916b.m2167a(obj);
    }

    public void setTitle(CharSequence charSequence) {
        this.f916b.m2171b(charSequence);
    }

    public void setSubtitle(CharSequence charSequence) {
        this.f916b.m2166a(charSequence);
    }

    public void invalidate() {
        this.f916b.m2173d();
    }

    public void finish() {
        this.f916b.m2172c();
    }

    public Menu getMenu() {
        return MenuWrapperFactory.m2526a(this.f915a, (SupportMenu) this.f916b.m2169b());
    }

    public CharSequence getTitle() {
        return this.f916b.m2174f();
    }

    public void setTitle(int i) {
        this.f916b.m2164a(i);
    }

    public CharSequence getSubtitle() {
        return this.f916b.m2175g();
    }

    public void setSubtitle(int i) {
        this.f916b.m2170b(i);
    }

    public View getCustomView() {
        return this.f916b.m2177i();
    }

    public void setCustomView(View view) {
        this.f916b.m2165a(view);
    }

    public MenuInflater getMenuInflater() {
        return this.f916b.m2163a();
    }

    public boolean getTitleOptionalHint() {
        return this.f916b.m2179k();
    }

    public void setTitleOptionalHint(boolean z) {
        this.f916b.m2168a(z);
    }

    public boolean isTitleOptional() {
        return this.f916b.m2176h();
    }
}
