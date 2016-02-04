package app.messaging.vh;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class GroupStatusHolder$$ViewInjector<T extends GroupStatusHolder> implements Injector<T> {
    public void inject(Finder finder, T t, Object obj) {
        t.f4044l = (LinearLayout) finder.m7731a((View) finder.m7732a(obj, 2131755466, "field 'parentView'"), 2131755466, "field 'parentView'");
        t.f4045m = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755467, "field 'groupStatus'"), 2131755467, "field 'groupStatus'");
    }

    public void reset(T t) {
        t.f4044l = null;
        t.f4045m = null;
    }
}
