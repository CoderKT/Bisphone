package app.messaging.vh;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;

public class Audio$$ViewInjector<T extends Audio> extends MessageViewHolder$$ViewInjector<T> {
    public void inject(Finder finder, T t, Object obj) {
        super.inject(finder, (MessageViewHolder) t, obj);
        t.f4028l = (ImageView) finder.m7731a((View) finder.m7732a(obj, 2131755473, "field 'buttonDownload'"), 2131755473, "field 'buttonDownload'");
        t.f4029m = (ImageView) finder.m7731a((View) finder.m7732a(obj, 2131755470, "field 'buttonPlay'"), 2131755470, "field 'buttonPlay'");
        t.f4030n = (ImageView) finder.m7731a((View) finder.m7732a(obj, 2131755472, "field 'buttonUpload'"), 2131755472, "field 'buttonUpload'");
        t.f4031o = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755471, "field 'textTimer'"), 2131755471, "field 'textTimer'");
        t.f4032p = (SeekBar) finder.m7731a((View) finder.m7732a(obj, 2131755474, "field 'seekBar'"), 2131755474, "field 'seekBar'");
        t.f4033q = (ProgressBar) finder.m7731a((View) finder.m7732a(obj, 2131755294, "field 'progressBar'"), 2131755294, "field 'progressBar'");
        t.f4034r = (ImageView) finder.m7731a((View) finder.m7732a(obj, 2131755469, "field 'backgroundIV'"), 2131755469, "field 'backgroundIV'");
    }

    public void reset(T t) {
        super.reset((MessageViewHolder) t);
        t.f4028l = null;
        t.f4029m = null;
        t.f4030n = null;
        t.f4031o = null;
        t.f4032p = null;
        t.f4033q = null;
        t.f4034r = null;
    }
}
