package app.messaging.vh;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import com.todddavies.components.progressbar.ProgressWheel;

public class Photo$$ViewInjector<T extends Photo> extends MessageViewHolder$$ViewInjector<T> {
    public void inject(Finder finder, T t, Object obj) {
        super.inject(finder, (MessageViewHolder) t, obj);
        t.f4082l = (ImageView) finder.m7731a((View) finder.m7732a(obj, 2131755478, "field 'messageImage'"), 2131755478, "field 'messageImage'");
        t.f4083m = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755479, "field 'caption'"), 2131755479, "field 'caption'");
        t.f4084n = (ImageView) finder.m7731a((View) finder.m7732a(obj, 2131755480, "field 'buttonDownload'"), 2131755480, "field 'buttonDownload'");
        t.f4085o = (ImageView) finder.m7731a((View) finder.m7732a(obj, 2131755481, "field 'buttonUpload'"), 2131755481, "field 'buttonUpload'");
        t.f4086p = (ImageView) finder.m7731a((View) finder.m7732a(obj, 2131755482, "field 'buttonCancel'"), 2131755482, "field 'buttonCancel'");
        t.f4087q = (ProgressWheel) finder.m7731a((View) finder.m7732a(obj, 2131755483, "field 'progressWheel'"), 2131755483, "field 'progressWheel'");
    }

    public void reset(T t) {
        super.reset((MessageViewHolder) t);
        t.f4082l = null;
        t.f4083m = null;
        t.f4084n = null;
        t.f4085o = null;
        t.f4086p = null;
        t.f4087q = null;
    }
}
