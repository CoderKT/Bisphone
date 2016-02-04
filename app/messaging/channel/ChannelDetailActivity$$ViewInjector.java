package app.messaging.channel;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import app.messaging.channel.ChannelDetailActivity$$ViewInjector$ChannelDetailActivity$.ViewInjector;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;
import butterknife.internal.DebouncingOnClickListener;

public class ChannelDetailActivity$$ViewInjector<T extends ChannelDetailActivity> implements Injector<T> {

    /* renamed from: app.messaging.channel.ChannelDetailActivity$.ViewInjector.1 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ ChannelDetailActivity f3474a;
        final /* synthetic */ ChannelDetailActivity$$ViewInjector f3475b;

        ViewInjector(ChannelDetailActivity$$ViewInjector channelDetailActivity$$ViewInjector, ChannelDetailActivity channelDetailActivity) {
            this.f3475b = channelDetailActivity$$ViewInjector;
            this.f3474a = channelDetailActivity;
        }

        public void m6100a(View view) {
            this.f3474a.m6110j();
        }
    }

    /* renamed from: app.messaging.channel.ChannelDetailActivity$.ViewInjector.2 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ ChannelDetailActivity f3476a;
        final /* synthetic */ ChannelDetailActivity$$ViewInjector f3477b;

        ViewInjector(ChannelDetailActivity$$ViewInjector channelDetailActivity$$ViewInjector, ChannelDetailActivity channelDetailActivity) {
            this.f3477b = channelDetailActivity$$ViewInjector;
            this.f3476a = channelDetailActivity;
        }

        public void m6101a(View view) {
            this.f3476a.m6111k();
        }
    }

    public void inject(Finder finder, T t, Object obj) {
        t.f3489o = (ImageView) finder.m7731a((View) finder.m7732a(obj, 2131755149, "field 'avatar'"), 2131755149, "field 'avatar'");
        t.f3490p = (ImageView) finder.m7731a((View) finder.m7732a(obj, 2131755146, "field 'cover'"), 2131755146, "field 'cover'");
        t.f3491q = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755155, "field 'description'"), 2131755155, "field 'description'");
        View view = (View) finder.m7732a(obj, 2131755150, "field 'followBox' and method 'onfollowClicked'");
        t.f3492r = view;
        view.setOnClickListener(new ViewInjector(this, t));
        t.f3493s = (ProgressBar) finder.m7731a((View) finder.m7732a(obj, 2131755151, "field 'progressBar'"), 2131755151, "field 'progressBar'");
        t.f3494t = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755152, "field 'followtitle'"), 2131755152, "field 'followtitle'");
        t.f3495u = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755148, "field 'title'"), 2131755148, "field 'title'");
        t.f3496v = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755147, "field 'followersNum'"), 2131755147, "field 'followersNum'");
        view = (View) finder.m7732a(obj, 2131755153, "field 'channelHistoryDetails' and method 'onMessageHistoryClicked'");
        t.f3497w = view;
        view.setOnClickListener(new ViewInjector(this, t));
    }

    public void reset(T t) {
        t.f3489o = null;
        t.f3490p = null;
        t.f3491q = null;
        t.f3492r = null;
        t.f3493s = null;
        t.f3494t = null;
        t.f3495u = null;
        t.f3496v = null;
        t.f3497w = null;
    }
}
