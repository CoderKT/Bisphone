package android.support.v4.view;

import android.content.Context;
import android.util.Log;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

public abstract class ActionProvider {
    private final Context f427a;
    private SubUiVisibilityListener f428b;
    private VisibilityListener f429c;

    public interface SubUiVisibilityListener {
        void m883a(boolean z);
    }

    public interface VisibilityListener {
        void m884a(boolean z);
    }

    public abstract View m885a();

    public ActionProvider(Context context) {
        this.f427a = context;
    }

    public View m886a(MenuItem menuItem) {
        return m885a();
    }

    public boolean m891b() {
        return false;
    }

    public boolean m892c() {
        return true;
    }

    public boolean m893d() {
        return false;
    }

    public boolean m894e() {
        return false;
    }

    public void m889a(SubMenu subMenu) {
    }

    public void m890a(boolean z) {
        if (this.f428b != null) {
            this.f428b.m883a(z);
        }
    }

    public void m887a(SubUiVisibilityListener subUiVisibilityListener) {
        this.f428b = subUiVisibilityListener;
    }

    public void m888a(VisibilityListener visibilityListener) {
        if (!(this.f429c == null || visibilityListener == null)) {
            Log.w("ActionProvider(support)", "setVisibilityListener: Setting a new ActionProvider.VisibilityListener when one is already set. Are you reusing this " + getClass().getSimpleName() + " instance while it is still in use somewhere else?");
        }
        this.f429c = visibilityListener;
    }

    public void m895f() {
        this.f429c = null;
        this.f428b = null;
    }
}
