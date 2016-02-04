package app.home;

import android.view.View;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class ContactsAdapter$ViewHolderHeader$$ViewInjector<T extends ViewHolderHeader> implements Injector<T> {
    public void inject(Finder finder, T t, Object obj) {
        t.f2918a = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755305, "field 'headerText'"), 2131755305, "field 'headerText'");
    }

    public void reset(T t) {
        t.f2918a = null;
    }
}
