package app.home;

import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import app.home.ChatRecycleAdapter$ChatViewHolder$$ViewInjector$ChatRecycleAdapter$ChatViewHolder$.ViewInjector;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;
import butterknife.internal.DebouncingOnClickListener;

public class ChatRecycleAdapter$ChatViewHolder$$ViewInjector<T extends ChatViewHolder> implements Injector<T> {

    /* renamed from: app.home.ChatRecycleAdapter$ChatViewHolder$.ViewInjector.1 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ ChatViewHolder f2882a;
        final /* synthetic */ ChatRecycleAdapter$ChatViewHolder$$ViewInjector f2883b;

        ViewInjector(ChatRecycleAdapter$ChatViewHolder$$ViewInjector chatRecycleAdapter$ChatViewHolder$$ViewInjector, ChatViewHolder chatViewHolder) {
            this.f2883b = chatRecycleAdapter$ChatViewHolder$$ViewInjector;
            this.f2882a = chatViewHolder;
        }

        public void m5338a(View view) {
            this.f2882a.m5341A();
        }
    }

    /* renamed from: app.home.ChatRecycleAdapter$ChatViewHolder$.ViewInjector.2 */
    class ViewInjector implements OnLongClickListener {
        final /* synthetic */ ChatViewHolder f2884a;
        final /* synthetic */ ChatRecycleAdapter$ChatViewHolder$$ViewInjector f2885b;

        ViewInjector(ChatRecycleAdapter$ChatViewHolder$$ViewInjector chatRecycleAdapter$ChatViewHolder$$ViewInjector, ChatViewHolder chatViewHolder) {
            this.f2885b = chatRecycleAdapter$ChatViewHolder$$ViewInjector;
            this.f2884a = chatViewHolder;
        }

        public boolean onLongClick(View view) {
            return this.f2884a.m5342B();
        }
    }

    public void inject(Finder finder, T t, Object obj) {
        View view = (View) finder.m7732a(obj, 2131755540, "field 'mainView', method 'onRowClicked', and method 'onRowLongClicked'");
        t.f2886l = view;
        view.setOnClickListener(new ViewInjector(this, t));
        view.setOnLongClickListener(new ViewInjector(this, t));
        t.f2887m = (ImageView) finder.m7731a((View) finder.m7732a(obj, 2131755116, "field 'avatar'"), 2131755116, "field 'avatar'");
        t.f2888n = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755548, "field 'message'"), 2131755548, "field 'message'");
        t.f2889o = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755547, "field 'nameTextView'"), 2131755547, "field 'nameTextView'");
        t.f2890p = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755549, "field 'date'"), 2131755549, "field 'date'");
        t.f2891q = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755550, "field 'badge'"), 2131755550, "field 'badge'");
    }

    public void reset(T t) {
        t.f2886l = null;
        t.f2887m = null;
        t.f2888n = null;
        t.f2889o = null;
        t.f2890p = null;
        t.f2891q = null;
    }
}
