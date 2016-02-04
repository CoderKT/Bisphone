package android.support.v7.app;

import android.app.Activity;
import android.content.Context;
import android.support.v7.view.ActionMode;
import android.support.v7.view.WindowCallbackWrapper;
import android.support.v7.view.menu.MenuBuilder;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.Window.Callback;

abstract class AppCompatDelegateImplBase extends AppCompatDelegate {
    final Context f753a;
    final Window f754b;
    final Callback f755c;
    final Callback f756d;
    final AppCompatCallback f757e;
    ActionBar f758f;
    boolean f759g;
    boolean f760h;
    boolean f761i;
    boolean f762j;
    boolean f763k;
    private CharSequence f764l;
    private boolean f765m;

    class AppCompatWindowCallbackBase extends WindowCallbackWrapper {
        final /* synthetic */ AppCompatDelegateImplBase f752a;

        AppCompatWindowCallbackBase(AppCompatDelegateImplBase appCompatDelegateImplBase, Callback callback) {
            this.f752a = appCompatDelegateImplBase;
            super(callback);
        }

        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return this.f752a.m2021a(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
            return super.dispatchKeyShortcutEvent(keyEvent) || this.f752a.m2020a(keyEvent.getKeyCode(), keyEvent);
        }

        public boolean onCreatePanelMenu(int i, Menu menu) {
            if (i != 0 || (menu instanceof MenuBuilder)) {
                return super.onCreatePanelMenu(i, menu);
            }
            return false;
        }

        public void onContentChanged() {
        }

        public boolean onPreparePanel(int i, View view, Menu menu) {
            MenuBuilder menuBuilder;
            if (menu instanceof MenuBuilder) {
                menuBuilder = (MenuBuilder) menu;
            } else {
                menuBuilder = null;
            }
            if (i == 0 && menuBuilder == null) {
                return false;
            }
            if (menuBuilder != null) {
                menuBuilder.m2435c(true);
            }
            boolean onPreparePanel = super.onPreparePanel(i, view, menu);
            if (menuBuilder == null) {
                return onPreparePanel;
            }
            menuBuilder.m2435c(false);
            return onPreparePanel;
        }

        public boolean onMenuOpened(int i, Menu menu) {
            super.onMenuOpened(i, menu);
            this.f752a.m2023b(i, menu);
            return true;
        }

        public void onPanelClosed(int i, Menu menu) {
            super.onPanelClosed(i, menu);
            this.f752a.m2018a(i, menu);
        }
    }

    abstract ActionMode m2016a(ActionMode.Callback callback);

    abstract void m2018a(int i, Menu menu);

    abstract boolean m2020a(int i, KeyEvent keyEvent);

    abstract boolean m2021a(KeyEvent keyEvent);

    abstract void m2022b(CharSequence charSequence);

    abstract boolean m2023b(int i, Menu menu);

    abstract void m2024d();

    AppCompatDelegateImplBase(Context context, Window window, AppCompatCallback appCompatCallback) {
        this.f753a = context;
        this.f754b = window;
        this.f757e = appCompatCallback;
        this.f755c = this.f754b.getCallback();
        if (this.f755c instanceof AppCompatWindowCallbackBase) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        this.f756d = m2017a(this.f755c);
        this.f754b.setCallback(this.f756d);
    }

    Callback m2017a(Callback callback) {
        return new AppCompatWindowCallbackBase(this, callback);
    }

    public ActionBar m2025e() {
        m2024d();
        return this.f758f;
    }

    final ActionBar m2026f() {
        return this.f758f;
    }

    final Context m2027g() {
        Context context = null;
        ActionBar e = m2025e();
        if (e != null) {
            context = e.m1916a();
        }
        if (context == null) {
            return this.f753a;
        }
        return context;
    }

    public boolean m2028h() {
        return false;
    }

    final boolean m2029i() {
        return this.f765m;
    }

    final Callback m2030j() {
        return this.f754b.getCallback();
    }

    public final void m2019a(CharSequence charSequence) {
        this.f764l = charSequence;
        m2022b(charSequence);
    }

    final CharSequence m2031k() {
        if (this.f755c instanceof Activity) {
            return ((Activity) this.f755c).getTitle();
        }
        return this.f764l;
    }
}
