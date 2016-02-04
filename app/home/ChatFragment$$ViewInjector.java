package app.home;

import android.view.View;
import android.widget.ProgressBar;
import app.home.ChatFragment$$ViewInjector$ChatFragment$.ViewInjector;
import app.view.CustomRecycleView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;
import butterknife.internal.DebouncingOnClickListener;

public class ChatFragment$$ViewInjector<T extends ChatFragment> implements Injector<T> {

    /* renamed from: app.home.ChatFragment$.ViewInjector.1 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ ChatFragment f2850a;
        final /* synthetic */ ChatFragment$$ViewInjector f2851b;

        ViewInjector(ChatFragment$$ViewInjector chatFragment$$ViewInjector, ChatFragment chatFragment) {
            this.f2851b = chatFragment$$ViewInjector;
            this.f2850a = chatFragment;
        }

        public void m5291a(View view) {
            this.f2850a.m5337d();
        }
    }

    public void inject(Finder finder, T t, Object obj) {
        t.f2864a = (CustomRecycleView) finder.m7731a((View) finder.m7732a(obj, 2131755334, "field 'recycleView'"), 2131755334, "field 'recycleView'");
        t.f2865b = (ProgressBar) finder.m7731a((View) finder.m7732a(obj, 2131755335, "field 'progressBar'"), 2131755335, "field 'progressBar'");
        t.f2866c = (View) finder.m7732a(obj, 2131755336, "field 'noConversationView'");
        t.f2867d = (View) finder.m7732a(obj, 2131755337, "field 'noCallView'");
        ((View) finder.m7732a(obj, 2131755329, "method 'onEmptyChatClicked'")).setOnClickListener(new ViewInjector(this, t));
    }

    public void reset(T t) {
        t.f2864a = null;
        t.f2865b = null;
        t.f2866c = null;
        t.f2867d = null;
    }
}
