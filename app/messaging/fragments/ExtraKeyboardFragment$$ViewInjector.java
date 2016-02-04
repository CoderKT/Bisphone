package app.messaging.fragments;

import android.view.View;
import android.widget.GridView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class ExtraKeyboardFragment$$ViewInjector<T extends ExtraKeyboardFragment> implements Injector<T> {
    public void inject(Finder finder, T t, Object obj) {
        t.f3562a = (GridView) finder.m7731a((View) finder.m7732a(obj, 2131755378, "field 'extraMenuGridView'"), 2131755378, "field 'extraMenuGridView'");
    }

    public void reset(T t) {
        t.f3562a = null;
    }
}
