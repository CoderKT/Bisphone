package app.home;

import android.view.View;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class HistoryFragment$$ViewInjector<T extends HistoryFragment> implements Injector<T> {
    public void inject(Finder finder, T t, Object obj) {
        t.f3025d = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755449, "field 'headerHistory'"), 2131755449, "field 'headerHistory'");
        t.f3026e = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755450, "field 'headerCall'"), 2131755450, "field 'headerCall'");
        t.f3027f = (View) finder.m7732a(obj, 2131755339, "field 'headerView'");
    }

    public void reset(T t) {
        t.f3025d = null;
        t.f3026e = null;
        t.f3027f = null;
    }
}
