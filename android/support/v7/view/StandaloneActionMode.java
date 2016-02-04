package android.support.v7.view;

import android.content.Context;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuBuilder.Callback;
import android.support.v7.widget.ActionBarContextView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import java.lang.ref.WeakReference;

public class StandaloneActionMode extends ActionMode implements Callback {
    private Context f904a;
    private ActionBarContextView f905b;
    private ActionMode.Callback f906c;
    private WeakReference<View> f907d;
    private boolean f908e;
    private boolean f909f;
    private MenuBuilder f910g;

    public StandaloneActionMode(Context context, ActionBarContextView actionBarContextView, ActionMode.Callback callback, boolean z) {
        this.f904a = context;
        this.f905b = actionBarContextView;
        this.f906c = callback;
        this.f910g = new MenuBuilder(actionBarContextView.getContext()).m2410a(1);
        this.f910g.m2418a((Callback) this);
        this.f909f = z;
    }

    public void m2269b(CharSequence charSequence) {
        this.f905b.setTitle(charSequence);
    }

    public void m2264a(CharSequence charSequence) {
        this.f905b.setSubtitle(charSequence);
    }

    public void m2261a(int i) {
        m2269b(this.f904a.getString(i));
    }

    public void m2268b(int i) {
        m2264a(this.f904a.getString(i));
    }

    public void m2265a(boolean z) {
        super.m2168a(z);
        this.f905b.setTitleOptional(z);
    }

    public boolean m2274h() {
        return this.f905b.m2558d();
    }

    public void m2263a(View view) {
        this.f905b.setCustomView(view);
        this.f907d = view != null ? new WeakReference(view) : null;
    }

    public void m2271d() {
        this.f906c.m2122b(this, this.f910g);
    }

    public void m2270c() {
        if (!this.f908e) {
            this.f908e = true;
            this.f905b.sendAccessibilityEvent(32);
            this.f906c.m2119a(this);
        }
    }

    public Menu m2267b() {
        return this.f910g;
    }

    public CharSequence m2272f() {
        return this.f905b.getTitle();
    }

    public CharSequence m2273g() {
        return this.f905b.getSubtitle();
    }

    public View m2275i() {
        return this.f907d != null ? (View) this.f907d.get() : null;
    }

    public MenuInflater m2260a() {
        return new MenuInflater(this.f905b.getContext());
    }

    public boolean m2266a(MenuBuilder menuBuilder, MenuItem menuItem) {
        return this.f906c.m2121a((ActionMode) this, menuItem);
    }

    public void m2262a(MenuBuilder menuBuilder) {
        m2271d();
        this.f905b.m2555a();
    }
}
