package app.messaging.vh;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import com.todddavies.components.progressbar.ProgressWheel;

public class Video$$ViewInjector<T extends Video> extends MessageViewHolder$$ViewInjector<T> {
    public void inject(Finder finder, T t, Object obj) {
        super.inject(finder, (MessageViewHolder) t, obj);
        t.f4113l = (ImageView) finder.m7731a((View) finder.m7732a(obj, 2131755486, "field 'videoImage'"), 2131755486, "field 'videoImage'");
        t.f4114m = (ImageView) finder.m7731a((View) finder.m7732a(obj, 2131755406, "field 'downloadBtn'"), 2131755406, "field 'downloadBtn'");
        t.f4115n = (ProgressWheel) finder.m7731a((View) finder.m7733b(obj, 2131755488, null), 2131755488, "field 'progressWheel'");
        t.f4116o = (ImageView) finder.m7731a((View) finder.m7732a(obj, 2131755487, "field 'playBtn'"), 2131755487, "field 'playBtn'");
        t.f4117p = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755490, "field 'mediaInfo'"), 2131755490, "field 'mediaInfo'");
        t.f4118q = (ImageView) finder.m7731a((View) finder.m7733b(obj, 2131755485, null), 2131755485, "field 'retryBtn'");
        t.f4119r = (ImageView) finder.m7731a((View) finder.m7733b(obj, 2131755489, null), 2131755489, "field 'stopDownloadBtn'");
        t.f4110K = (TextView) finder.m7731a((View) finder.m7733b(obj, 2131755479, null), 2131755479, "field 'caption'");
    }

    public void reset(T t) {
        super.reset((MessageViewHolder) t);
        t.f4113l = null;
        t.f4114m = null;
        t.f4115n = null;
        t.f4116o = null;
        t.f4117p = null;
        t.f4118q = null;
        t.f4119r = null;
        t.f4110K = null;
    }
}
