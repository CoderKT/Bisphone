package app.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class ContactsAdapter$ViewHolder$$ViewInjector<T extends ViewHolder> implements Injector<T> {
    public void inject(Finder finder, T t, Object obj) {
        t.f2908a = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755569, "field 'contactNameTextView'"), 2131755569, "field 'contactNameTextView'");
        t.f2909b = (ImageView) finder.m7731a((View) finder.m7732a(obj, 2131755116, "field 'avatar'"), 2131755116, "field 'avatar'");
        t.f2910c = (ImageView) finder.m7731a((View) finder.m7732a(obj, 2131755571, "field 'appIcon'"), 2131755571, "field 'appIcon'");
    }

    public void reset(T t) {
        t.f2908a = null;
        t.f2909b = null;
        t.f2910c = null;
    }
}
