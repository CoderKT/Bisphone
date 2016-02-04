package app.messaging.vh;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;

public class Map$$ViewInjector<T extends Map> extends MessageViewHolder$$ViewInjector<T> {
    public void inject(Finder finder, T t, Object obj) {
        super.inject(finder, (MessageViewHolder) t, obj);
        t.f4052l = (ImageView) finder.m7731a((View) finder.m7732a(obj, 2131755475, "field 'mapThumbnail'"), 2131755475, "field 'mapThumbnail'");
        t.f4053m = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755476, "field 'fullAddress'"), 2131755476, "field 'fullAddress'");
    }

    public void reset(T t) {
        super.reset((MessageViewHolder) t);
        t.f4052l = null;
        t.f4053m = null;
    }
}
