package app.messaging.stickers;

import android.view.View;
import android.widget.ListView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class OnlineStickersFragment$$ViewInjector<T extends OnlineStickersFragment> implements Injector<T> {
    public void inject(Finder finder, T t, Object obj) {
        t.f3942b = (ListView) finder.m7731a((View) finder.m7732a(obj, 2131755401, "field 'stickersListView'"), 2131755401, "field 'stickersListView'");
    }

    public void reset(T t) {
        t.f3942b = null;
    }
}
