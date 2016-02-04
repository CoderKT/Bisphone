package app.home;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class GroupRecycleAdapter$ViewHolder$$ViewInjector<T extends ViewHolder> implements Injector<T> {
    public void inject(Finder finder, T t, Object obj) {
        t.f2951l = (ImageView) finder.m7731a((View) finder.m7732a(obj, 2131755594, "field 'groupAvatar'"), 2131755594, "field 'groupAvatar'");
        t.f2952m = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755597, "field 'message'"), 2131755597, "field 'message'");
        t.f2953n = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755595, "field 'name'"), 2131755595, "field 'name'");
        t.f2954o = (ViewGroup) finder.m7731a((View) finder.m7732a(obj, 2131755593, "field 'normalLayout'"), 2131755593, "field 'normalLayout'");
        t.f2955p = (ViewGroup) finder.m7731a((View) finder.m7732a(obj, 2131755600, "field 'actionsLayout'"), 2131755600, "field 'actionsLayout'");
    }

    public void reset(T t) {
        t.f2951l = null;
        t.f2952m = null;
        t.f2953n = null;
        t.f2954o = null;
        t.f2955p = null;
    }
}
