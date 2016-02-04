package app.messaging.selector;

import android.view.View;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class SelectContactFragment$$ViewInjector<T extends SelectContactFragment> implements Injector<T> {
    public void inject(Finder finder, T t, Object obj) {
        t.f3804a = (ListView) finder.m7731a((View) finder.m7732a(obj, 2131755418, "field 'mListView'"), 2131755418, "field 'mListView'");
        t.f3805b = (EditText) finder.m7731a((View) finder.m7732a(obj, 2131755416, "field 'mSearchEditText'"), 2131755416, "field 'mSearchEditText'");
        t.f3806c = (ImageView) finder.m7731a((View) finder.m7732a(obj, 2131755417, "field 'clearSearch'"), 2131755417, "field 'clearSearch'");
        t.f3807d = (LinearLayout) finder.m7731a((View) finder.m7732a(obj, 2131755420, "field 'scrollViewParent'"), 2131755420, "field 'scrollViewParent'");
        t.f3808e = (HorizontalScrollView) finder.m7731a((View) finder.m7732a(obj, 2131755419, "field 'scrollView'"), 2131755419, "field 'scrollView'");
    }

    public void reset(T t) {
        t.f3804a = null;
        t.f3805b = null;
        t.f3806c = null;
        t.f3807d = null;
        t.f3808e = null;
    }
}
