package app.messaging.channel;

import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class ChannelCategoryActivity$$ViewInjector<T extends ChannelCategoryActivity> implements Injector<T> {
    public void inject(Finder finder, T t, Object obj) {
        t.f3386q = (ListView) finder.m7731a((View) finder.m7732a(obj, 2131755144, "field 'listView'"), 2131755144, "field 'listView'");
        t.f3387r = (ProgressBar) finder.m7731a((View) finder.m7732a(obj, 2131755145, "field 'emptyView'"), 2131755145, "field 'emptyView'");
    }

    public void reset(T t) {
        t.f3386q = null;
        t.f3387r = null;
    }
}
