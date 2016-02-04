package android.support.v7.app;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar.OnMenuVisibilityListener;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.widget.DecorToolbar;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window.Callback;
import app.C0110R;
import java.util.ArrayList;

class ToolbarActionBar extends ActionBar {
    private DecorToolbar f847a;
    private Callback f848b;
    private boolean f849c;
    private boolean f850d;
    private ArrayList<OnMenuVisibilityListener> f851e;
    private final Runnable f852f;

    /* renamed from: android.support.v7.app.ToolbarActionBar.1 */
    class C00531 implements Runnable {
        final /* synthetic */ ToolbarActionBar f843a;

        public void run() {
            this.f843a.m2157d();
        }
    }

    final class ActionMenuPresenterCallback implements MenuPresenter.Callback {
        final /* synthetic */ ToolbarActionBar f844a;
        private boolean f845b;

        private ActionMenuPresenterCallback(ToolbarActionBar toolbarActionBar) {
            this.f844a = toolbarActionBar;
        }

        public boolean m2143a(MenuBuilder menuBuilder) {
            if (this.f844a.f848b == null) {
                return false;
            }
            this.f844a.f848b.onMenuOpened(C0110R.styleable.Theme_spinnerStyle, menuBuilder);
            return true;
        }

        public void m2142a(MenuBuilder menuBuilder, boolean z) {
            if (!this.f845b) {
                this.f845b = true;
                this.f844a.f847a.m2920n();
                if (this.f844a.f848b != null) {
                    this.f844a.f848b.onPanelClosed(C0110R.styleable.Theme_spinnerStyle, menuBuilder);
                }
                this.f845b = false;
            }
        }
    }

    final class MenuBuilderCallback implements MenuBuilder.Callback {
        final /* synthetic */ ToolbarActionBar f846a;

        private MenuBuilderCallback(ToolbarActionBar toolbarActionBar) {
            this.f846a = toolbarActionBar;
        }

        public boolean m2145a(MenuBuilder menuBuilder, MenuItem menuItem) {
            return false;
        }

        public void m2144a(MenuBuilder menuBuilder) {
            if (this.f846a.f848b == null) {
                return;
            }
            if (this.f846a.f847a.m2915i()) {
                this.f846a.f848b.onPanelClosed(C0110R.styleable.Theme_spinnerStyle, menuBuilder);
            } else if (this.f846a.f848b.onPreparePanel(0, null, menuBuilder)) {
                this.f846a.f848b.onMenuOpened(C0110R.styleable.Theme_spinnerStyle, menuBuilder);
            }
        }
    }

    public void m2152a(boolean z) {
    }

    public void m2150a(float f) {
        ViewCompat.m1175d(this.f847a.m2896a(), f);
    }

    public Context m2149a() {
        return this.f847a.m2905b();
    }

    public void m2155c(boolean z) {
    }

    public void m2158d(boolean z) {
    }

    public void m2151a(CharSequence charSequence) {
        this.f847a.m2903a(charSequence);
    }

    public boolean m2154b() {
        this.f847a.m2896a().removeCallbacks(this.f852f);
        ViewCompat.m1163a(this.f847a.m2896a(), this.f852f);
        return true;
    }

    public boolean m2156c() {
        if (!this.f847a.m2909c()) {
            return false;
        }
        this.f847a.m2910d();
        return true;
    }

    void m2157d() {
        Menu e = m2148e();
        MenuBuilder menuBuilder = e instanceof MenuBuilder ? (MenuBuilder) e : null;
        if (menuBuilder != null) {
            menuBuilder.m2442g();
        }
        try {
            e.clear();
            if (!(this.f848b.onCreatePanelMenu(0, e) && this.f848b.onPreparePanel(0, null, e))) {
                e.clear();
            }
            if (menuBuilder != null) {
                menuBuilder.m2443h();
            }
        } catch (Throwable th) {
            if (menuBuilder != null) {
                menuBuilder.m2443h();
            }
        }
    }

    public boolean m2153a(int i, KeyEvent keyEvent) {
        Menu e = m2148e();
        if (e != null) {
            boolean z;
            if (KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1) {
                z = true;
            } else {
                z = false;
            }
            e.setQwertyMode(z);
            e.performShortcut(i, keyEvent, 0);
        }
        return true;
    }

    public void m2159e(boolean z) {
        if (z != this.f850d) {
            this.f850d = z;
            int size = this.f851e.size();
            for (int i = 0; i < size; i++) {
                ((OnMenuVisibilityListener) this.f851e.get(i)).m1910a(z);
            }
        }
    }

    private Menu m2148e() {
        if (!this.f849c) {
            this.f847a.m2899a(new ActionMenuPresenterCallback(), new MenuBuilderCallback());
            this.f849c = true;
        }
        return this.f847a.m2923q();
    }
}
