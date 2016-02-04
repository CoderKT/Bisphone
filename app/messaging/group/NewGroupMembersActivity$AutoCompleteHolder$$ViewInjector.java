package app.messaging.group;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class NewGroupMembersActivity$AutoCompleteHolder$$ViewInjector<T extends AutoCompleteHolder> implements Injector<T> {
    public void inject(Finder finder, T t, Object obj) {
        t.f3747a = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755117, "field 'contactNameTextView'"), 2131755117, "field 'contactNameTextView'");
        t.f3748b = (ImageView) finder.m7731a((View) finder.m7732a(obj, 2131755116, "field 'avatar'"), 2131755116, "field 'avatar'");
    }

    public void reset(T t) {
        t.f3747a = null;
        t.f3748b = null;
    }
}
