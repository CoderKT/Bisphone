package android.support.v7.view;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public abstract class ActionMode {
    private Object f856a;
    private boolean f857b;

    public interface Callback {
        void m2119a(ActionMode actionMode);

        boolean m2120a(ActionMode actionMode, Menu menu);

        boolean m2121a(ActionMode actionMode, MenuItem menuItem);

        boolean m2122b(ActionMode actionMode, Menu menu);
    }

    public abstract MenuInflater m2163a();

    public abstract void m2164a(int i);

    public abstract void m2165a(View view);

    public abstract void m2166a(CharSequence charSequence);

    public abstract Menu m2169b();

    public abstract void m2170b(int i);

    public abstract void m2171b(CharSequence charSequence);

    public abstract void m2172c();

    public abstract void m2173d();

    public abstract CharSequence m2174f();

    public abstract CharSequence m2175g();

    public abstract View m2177i();

    public void m2167a(Object obj) {
        this.f856a = obj;
    }

    public Object m2178j() {
        return this.f856a;
    }

    public void m2168a(boolean z) {
        this.f857b = z;
    }

    public boolean m2179k() {
        return this.f857b;
    }

    public boolean m2176h() {
        return false;
    }
}
