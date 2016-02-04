package app.messaging.selector;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;

public class SelectGroupAdapter$ViewHolderJoined$$ViewInjector<T extends ViewHolderJoined> extends SelectGroupAdapter$ViewHolder$$ViewInjector<T> {
    public void inject(Finder finder, T t, Object obj) {
        super.inject(finder, (ViewHolder) t, obj);
        t.f3861f = (ImageView) finder.m7731a((View) finder.m7732a(obj, 2131755596, "field 'memberAvatar'"), 2131755596, "field 'memberAvatar'");
        t.f3862g = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755598, "field 'date'"), 2131755598, "field 'date'");
        t.f3863h = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755599, "field 'badge'"), 2131755599, "field 'badge'");
        t.f3864i = (ImageView) finder.m7731a((View) finder.m7732a(obj, 2131755594, "field 'groupAvatar'"), 2131755594, "field 'groupAvatar'");
    }

    public void reset(T t) {
        super.reset((ViewHolder) t);
        t.f3861f = null;
        t.f3862g = null;
        t.f3863h = null;
        t.f3864i = null;
    }
}
