package app.messaging.selector;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import app.messaging.selector.SelectContactAdapter.ViewHolder;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class SelectContactAdapter$ViewHolder$$ViewInjector<T extends ViewHolder> implements Injector<T> {
    public void inject(Finder finder, T t, Object obj) {
        t.f3774a = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755569, "field 'contactNameTextView'"), 2131755569, "field 'contactNameTextView'");
        t.f3775b = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755570, "field 'contactPhone'"), 2131755570, "field 'contactPhone'");
        t.f3776c = (ImageView) finder.m7731a((View) finder.m7732a(obj, 2131755116, "field 'avatar'"), 2131755116, "field 'avatar'");
        t.f3777d = (CheckBox) finder.m7731a((View) finder.m7732a(obj, 2131755611, "field 'checkBox'"), 2131755611, "field 'checkBox'");
    }

    public void reset(T t) {
        t.f3774a = null;
        t.f3775b = null;
        t.f3776c = null;
        t.f3777d = null;
    }
}
