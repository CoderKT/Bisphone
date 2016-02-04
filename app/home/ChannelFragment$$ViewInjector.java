package app.home;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class ChannelFragment$$ViewInjector<T extends ChannelFragment> implements Injector<T> {
    public void inject(Finder finder, T t, Object obj) {
        t.f2844a = (ListView) finder.m7731a((View) finder.m7732a(obj, 2131755332, "field 'channelsListView'"), 2131755332, "field 'channelsListView'");
        t.f2845b = (ViewGroup) finder.m7731a((View) finder.m7732a(obj, 2131755331, "field 'parent'"), 2131755331, "field 'parent'");
        t.f2846c = (ProgressBar) finder.m7731a((View) finder.m7732a(obj, 2131755333, "field 'progressBar'"), 2131755333, "field 'progressBar'");
    }

    public void reset(T t) {
        t.f2844a = null;
        t.f2845b = null;
        t.f2846c = null;
    }
}
