package app.home;

import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import app.home.CallRecycleAdapter$CallViewHolder$$ViewInjector$CallRecycleAdapter$CallViewHolder$.ViewInjector;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;
import butterknife.internal.DebouncingOnClickListener;

public class CallRecycleAdapter$CallViewHolder$$ViewInjector<T extends CallViewHolder> implements Injector<T> {

    /* renamed from: app.home.CallRecycleAdapter$CallViewHolder$.ViewInjector.1 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ CallViewHolder f2792a;
        final /* synthetic */ CallRecycleAdapter$CallViewHolder$$ViewInjector f2793b;

        ViewInjector(CallRecycleAdapter$CallViewHolder$$ViewInjector callRecycleAdapter$CallViewHolder$$ViewInjector, CallViewHolder callViewHolder) {
            this.f2793b = callRecycleAdapter$CallViewHolder$$ViewInjector;
            this.f2792a = callViewHolder;
        }

        public void m5247a(View view) {
            this.f2792a.m5250A();
        }
    }

    /* renamed from: app.home.CallRecycleAdapter$CallViewHolder$.ViewInjector.2 */
    class ViewInjector implements OnLongClickListener {
        final /* synthetic */ CallViewHolder f2794a;
        final /* synthetic */ CallRecycleAdapter$CallViewHolder$$ViewInjector f2795b;

        ViewInjector(CallRecycleAdapter$CallViewHolder$$ViewInjector callRecycleAdapter$CallViewHolder$$ViewInjector, CallViewHolder callViewHolder) {
            this.f2795b = callRecycleAdapter$CallViewHolder$$ViewInjector;
            this.f2794a = callViewHolder;
        }

        public boolean onLongClick(View view) {
            return this.f2794a.m5251B();
        }
    }

    public void inject(Finder finder, T t, Object obj) {
        View view = (View) finder.m7732a(obj, 2131755540, "field 'mainView', method 'onRowClicked', and method 'onRowLongClicked'");
        t.f2796l = view;
        view.setOnClickListener(new ViewInjector(this, t));
        view.setOnLongClickListener(new ViewInjector(this, t));
        t.f2797m = (ImageView) finder.m7731a((View) finder.m7732a(obj, 2131755116, "field 'avatar'"), 2131755116, "field 'avatar'");
        t.f2798n = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755548, "field 'messageTextView'"), 2131755548, "field 'messageTextView'");
        t.f2799o = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755547, "field 'name'"), 2131755547, "field 'name'");
        t.f2800p = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755549, "field 'date'"), 2131755549, "field 'date'");
        t.f2801q = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755550, "field 'badge'"), 2131755550, "field 'badge'");
    }

    public void reset(T t) {
        t.f2796l = null;
        t.f2797m = null;
        t.f2798n = null;
        t.f2799o = null;
        t.f2800p = null;
        t.f2801q = null;
    }
}
