package app.messaging.channel;

import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class ChannelCategoryDetailsActivity$$ViewInjector<T extends ChannelCategoryDetailsActivity> implements Injector<T> {
    public void inject(Finder finder, T t, Object obj) {
        t.f3419s = (ListView) finder.m7731a((View) finder.m7732a(obj, 2131755144, "field 'listView'"), 2131755144, "field 'listView'");
        t.f3420t = (ProgressBar) finder.m7731a((View) finder.m7732a(obj, 2131755145, "field 'emptyView'"), 2131755145, "field 'emptyView'");
    }

    public void reset(T t) {
        t.f3419s = null;
        t.f3420t = null;
    }
}
