package app.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import app.view.CustomRecycleView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class GroupsFragment$$ViewInjector<T extends GroupsFragment> implements Injector<T> {
    public void inject(Finder finder, T t, Object obj) {
        t.f3011a = (CustomRecycleView) finder.m7731a((View) finder.m7732a(obj, 2131755388, "field 'recycleView'"), 2131755388, "field 'recycleView'");
        t.f3012b = (ImageView) finder.m7731a((View) finder.m7732a(obj, 2131755389, "field 'noGroupImageView'"), 2131755389, "field 'noGroupImageView'");
        t.f3013c = (ProgressBar) finder.m7731a((View) finder.m7732a(obj, 2131755390, "field 'progressBar'"), 2131755390, "field 'progressBar'");
    }

    public void reset(T t) {
        t.f3011a = null;
        t.f3012b = null;
        t.f3013c = null;
    }
}
