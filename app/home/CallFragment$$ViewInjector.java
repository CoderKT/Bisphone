package app.home;

import android.view.View;
import android.widget.ProgressBar;
import app.view.CustomRecycleView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class CallFragment$$ViewInjector<T extends CallFragment> implements Injector<T> {
    public void inject(Finder finder, T t, Object obj) {
        t.f2776a = (CustomRecycleView) finder.m7731a((View) finder.m7732a(obj, 2131755334, "field 'recycleView'"), 2131755334, "field 'recycleView'");
        t.f2777b = (ProgressBar) finder.m7731a((View) finder.m7732a(obj, 2131755335, "field 'progressBar'"), 2131755335, "field 'progressBar'");
        t.f2778c = (View) finder.m7732a(obj, 2131755336, "field 'noConversationView'");
        t.f2779d = (View) finder.m7732a(obj, 2131755337, "field 'noCallView'");
    }

    public void reset(T t) {
        t.f2776a = null;
        t.f2777b = null;
        t.f2778c = null;
        t.f2779d = null;
    }
}
