package app.home;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import app.home.ChannelAdapter$ViewHolder$$ViewInjector$ChannelAdapter$ViewHolder$.ViewInjector;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;
import butterknife.internal.DebouncingOnClickListener;

public class ChannelAdapter$ViewHolder$$ViewInjector<T extends ViewHolder> implements Injector<T> {

    /* renamed from: app.home.ChannelAdapter$ViewHolder$.ViewInjector.1 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ ViewHolder f2815a;
        final /* synthetic */ ChannelAdapter$ViewHolder$$ViewInjector f2816b;

        ViewInjector(ChannelAdapter$ViewHolder$$ViewInjector channelAdapter$ViewHolder$$ViewInjector, ViewHolder viewHolder) {
            this.f2816b = channelAdapter$ViewHolder$$ViewInjector;
            this.f2815a = viewHolder;
        }

        public void m5275a(View view) {
            this.f2815a.m5279b();
        }
    }

    /* renamed from: app.home.ChannelAdapter$ViewHolder$.ViewInjector.2 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ ViewHolder f2817a;
        final /* synthetic */ ChannelAdapter$ViewHolder$$ViewInjector f2818b;

        ViewInjector(ChannelAdapter$ViewHolder$$ViewInjector channelAdapter$ViewHolder$$ViewInjector, ViewHolder viewHolder) {
            this.f2818b = channelAdapter$ViewHolder$$ViewInjector;
            this.f2817a = viewHolder;
        }

        public void m5276a(View view) {
            this.f2817a.m5278a();
        }
    }

    /* renamed from: app.home.ChannelAdapter$ViewHolder$.ViewInjector.3 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ ViewHolder f2819a;
        final /* synthetic */ ChannelAdapter$ViewHolder$$ViewInjector f2820b;

        ViewInjector(ChannelAdapter$ViewHolder$$ViewInjector channelAdapter$ViewHolder$$ViewInjector, ViewHolder viewHolder) {
            this.f2820b = channelAdapter$ViewHolder$$ViewInjector;
            this.f2819a = viewHolder;
        }

        public void m5277a(View view) {
            this.f2819a.m5280c();
        }
    }

    public void inject(Finder finder, T t, Object obj) {
        t.f2828a = (ImageView) finder.m7731a((View) finder.m7732a(obj, 2131755116, "field 'avatar'"), 2131755116, "field 'avatar'");
        t.f2829b = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755552, "field 'channelTitle'"), 2131755552, "field 'channelTitle'");
        t.f2830c = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755554, "field 'timeStamp'"), 2131755554, "field 'timeStamp'");
        t.f2831d = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755555, "field 'unreadCount'"), 2131755555, "field 'unreadCount'");
        t.f2832e = (ViewGroup) finder.m7731a((View) finder.m7732a(obj, 2131755551, "field 'normalLayout'"), 2131755551, "field 'normalLayout'");
        t.f2833f = (ViewGroup) finder.m7731a((View) finder.m7732a(obj, 2131755556, "field 'actionsLayout'"), 2131755556, "field 'actionsLayout'");
        View view = (View) finder.m7732a(obj, 2131755559, "field 'followUnFollow' and method 'onUnfollowClicked'");
        t.f2834g = (Button) finder.m7731a(view, 2131755559, "field 'followUnFollow'");
        view.setOnClickListener(new ViewInjector(this, t));
        ((View) finder.m7732a(obj, 2131755557, "method 'onCancelClicked'")).setOnClickListener(new ViewInjector(this, t));
        ((View) finder.m7732a(obj, 2131755558, "method 'onDeleteClicked'")).setOnClickListener(new ViewInjector(this, t));
    }

    public void reset(T t) {
        t.f2828a = null;
        t.f2829b = null;
        t.f2830c = null;
        t.f2831d = null;
        t.f2832e = null;
        t.f2833f = null;
        t.f2834g = null;
    }
}
