package app.messaging.vh;

import android.view.View;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class TimestampViewHolder$$ViewInjector<T extends TimestampViewHolder> implements Injector<T> {
    public void inject(Finder finder, T t, Object obj) {
        t.f4097l = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755492, "field 'timestamp'"), 2131755492, "field 'timestamp'");
    }

    public void reset(T t) {
        t.f4097l = null;
    }
}
