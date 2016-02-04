package app.messaging.vh;

import android.view.View;
import android.widget.ImageView;
import butterknife.ButterKnife.Finder;

public class Sticker$$ViewInjector<T extends Sticker> extends MessageViewHolder$$ViewInjector<T> {
    public void inject(Finder finder, T t, Object obj) {
        super.inject(finder, (MessageViewHolder) t, obj);
        t.f4092l = (ImageView) finder.m7731a((View) finder.m7732a(obj, 2131755484, "field 'stickerImage'"), 2131755484, "field 'stickerImage'");
    }

    public void reset(T t) {
        super.reset((MessageViewHolder) t);
        t.f4092l = null;
    }
}
