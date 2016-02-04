package app.messaging.group;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class NewGroupMembersActivity$ViewHolder$$ViewInjector<T extends ViewHolder> implements Injector<T> {
    public void inject(Finder finder, T t, Object obj) {
        t.f3756a = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755117, "field 'contactNameTextView'"), 2131755117, "field 'contactNameTextView'");
        t.f3757b = (ImageView) finder.m7731a((View) finder.m7732a(obj, 2131755116, "field 'avatar'"), 2131755116, "field 'avatar'");
        t.f3758c = (ImageView) finder.m7731a((View) finder.m7732a(obj, 2131755606, "field 'removeContact'"), 2131755606, "field 'removeContact'");
        t.f3759d = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755570, "field 'contactPhone'"), 2131755570, "field 'contactPhone'");
    }

    public void reset(T t) {
        t.f3756a = null;
        t.f3757b = null;
        t.f3758c = null;
        t.f3759d = null;
    }
}
