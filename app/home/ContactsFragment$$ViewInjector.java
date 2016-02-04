package app.home;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class ContactsFragment$$ViewInjector<T extends ContactsFragment> implements Injector<T> {
    public void inject(Finder finder, T t, Object obj) {
        t.f2933a = (ViewGroup) finder.m7731a((View) finder.m7732a(obj, 2131755338, "field 'parent'"), 2131755338, "field 'parent'");
        t.f2934b = (StickyListHeadersListView) finder.m7731a((View) finder.m7732a(obj, 2131755340, "field 'mListView'"), 2131755340, "field 'mListView'");
        t.f2935c = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755303, "field 'emptyHeaderBisphone'"), 2131755303, "field 'emptyHeaderBisphone'");
        t.f2936d = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755304, "field 'emptyHeaderAll'"), 2131755304, "field 'emptyHeaderAll'");
        t.f2937e = (View) finder.m7732a(obj, 2131755339, "field 'headerEmptyView'");
        t.f2938f = (ProgressBar) finder.m7731a((View) finder.m7732a(obj, 2131755341, "field 'progressBar'"), 2131755341, "field 'progressBar'");
    }

    public void reset(T t) {
        t.f2933a = null;
        t.f2934b = null;
        t.f2935c = null;
        t.f2936d = null;
        t.f2937e = null;
        t.f2938f = null;
    }
}
