package app.setting;

import android.view.View;
import android.widget.ListView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class SettingContentLanguage$$ViewInjector<T extends SettingContentLanguage> implements Injector<T> {
    public void inject(Finder finder, T t, Object obj) {
        t.f4353o = (ListView) finder.m7731a((View) finder.m7732a(obj, 2131755249, "field 'listview'"), 2131755249, "field 'listview'");
    }

    public void reset(T t) {
        t.f4353o = null;
    }
}
