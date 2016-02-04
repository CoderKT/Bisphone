package app.messaging.selector;

import android.view.View;
import android.widget.ProgressBar;
import app.view.CustomRecycleView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class SelectConversationFragment$$ViewInjector<T extends SelectConversationFragment> implements Injector<T> {
    public void inject(Finder finder, T t, Object obj) {
        t.f3843a = (CustomRecycleView) finder.m7731a((View) finder.m7732a(obj, 2131755334, "field 'recycleView'"), 2131755334, "field 'recycleView'");
        t.f3844b = (ProgressBar) finder.m7731a((View) finder.m7732a(obj, 2131755335, "field 'progressBar'"), 2131755335, "field 'progressBar'");
        t.f3845c = (View) finder.m7732a(obj, 2131755336, "field 'noConversationView'");
        t.f3846d = (View) finder.m7732a(obj, 2131755337, "field 'noCallView'");
    }

    public void reset(T t) {
        t.f3843a = null;
        t.f3844b = null;
        t.f3845c = null;
        t.f3846d = null;
    }
}
