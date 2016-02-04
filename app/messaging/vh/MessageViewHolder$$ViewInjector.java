package app.messaging.vh;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class MessageViewHolder$$ViewInjector<T extends MessageViewHolder> implements Injector<T> {
    public void inject(Finder finder, T t, Object obj) {
        t.f4020u = (LinearLayout) finder.m7731a((View) finder.m7732a(obj, 2131755462, "field 'parentView'"), 2131755462, "field 'parentView'");
        t.f4021v = (ViewGroup) finder.m7731a((View) finder.m7732a(obj, 2131755463, "field 'bubble'"), 2131755463, "field 'bubble'");
        t.f4022w = (ImageView) finder.m7731a((View) finder.m7732a(obj, 2131755116, "field 'avatar'"), 2131755116, "field 'avatar'");
        t.f4023x = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755451, "field 'time'"), 2131755451, "field 'time'");
        t.f4024y = (ImageView) finder.m7731a((View) finder.m7733b(obj, 2131755454, null), 2131755454, "field 'relatedMessageImageView'");
        t.f4025z = (TextView) finder.m7731a((View) finder.m7733b(obj, 2131755455, null), 2131755455, "field 'relatedMessageUsername'");
        t.f4010A = (TextView) finder.m7731a((View) finder.m7733b(obj, 2131755456, null), 2131755456, "field 'relatedMessageMessage'");
        t.f4011B = (RelativeLayout) finder.m7731a((View) finder.m7733b(obj, 2131755468, null), 2131755468, "field 'relatedMessageView'");
        t.f4012C = (TextView) finder.m7731a((View) finder.m7733b(obj, 2131755465, null), 2131755465, "field 'displayName'");
        t.f4013D = (ImageView) finder.m7731a((View) finder.m7733b(obj, 2131755452, null), 2131755452, "field 'messageStatus'");
        t.f4014E = (ImageView) finder.m7731a((View) finder.m7733b(obj, 2131755453, null), 2131755453, "field 'broadcastView'");
    }

    public void reset(T t) {
        t.f4020u = null;
        t.f4021v = null;
        t.f4022w = null;
        t.f4023x = null;
        t.f4024y = null;
        t.f4025z = null;
        t.f4010A = null;
        t.f4011B = null;
        t.f4012C = null;
        t.f4013D = null;
        t.f4014E = null;
    }
}
