package app.messaging.selector;

import android.view.View;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class SelectGroupAdapter$ViewHolder$$ViewInjector<T extends ViewHolder> implements Injector<T> {
    public void inject(Finder finder, T t, Object obj) {
        t.f3856a = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755597, "field 'message'"), 2131755597, "field 'message'");
        t.f3857b = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755595, "field 'name'"), 2131755595, "field 'name'");
    }

    public void reset(T t) {
        t.f3856a = null;
        t.f3857b = null;
    }
}
