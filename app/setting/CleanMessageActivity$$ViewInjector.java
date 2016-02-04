package app.setting;

import android.view.View;
import app.setting.CleanMessageActivity$$ViewInjector$CleanMessageActivity$.ViewInjector;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;
import butterknife.internal.DebouncingOnClickListener;

public class CleanMessageActivity$$ViewInjector<T extends CleanMessageActivity> implements Injector<T> {

    /* renamed from: app.setting.CleanMessageActivity$.ViewInjector.1 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ CleanMessageActivity f4310a;
        final /* synthetic */ CleanMessageActivity$$ViewInjector f4311b;

        ViewInjector(CleanMessageActivity$$ViewInjector cleanMessageActivity$$ViewInjector, CleanMessageActivity cleanMessageActivity) {
            this.f4311b = cleanMessageActivity$$ViewInjector;
            this.f4310a = cleanMessageActivity;
        }

        public void m6755a(View view) {
            this.f4310a.m6768j();
        }
    }

    /* renamed from: app.setting.CleanMessageActivity$.ViewInjector.2 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ CleanMessageActivity f4312a;
        final /* synthetic */ CleanMessageActivity$$ViewInjector f4313b;

        ViewInjector(CleanMessageActivity$$ViewInjector cleanMessageActivity$$ViewInjector, CleanMessageActivity cleanMessageActivity) {
            this.f4313b = cleanMessageActivity$$ViewInjector;
            this.f4312a = cleanMessageActivity;
        }

        public void m6756a(View view) {
            this.f4312a.m6769k();
        }
    }

    /* renamed from: app.setting.CleanMessageActivity$.ViewInjector.3 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ CleanMessageActivity f4314a;
        final /* synthetic */ CleanMessageActivity$$ViewInjector f4315b;

        ViewInjector(CleanMessageActivity$$ViewInjector cleanMessageActivity$$ViewInjector, CleanMessageActivity cleanMessageActivity) {
            this.f4315b = cleanMessageActivity$$ViewInjector;
            this.f4314a = cleanMessageActivity;
        }

        public void m6757a(View view) {
            this.f4314a.m6770l();
        }
    }

    /* renamed from: app.setting.CleanMessageActivity$.ViewInjector.4 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ CleanMessageActivity f4316a;
        final /* synthetic */ CleanMessageActivity$$ViewInjector f4317b;

        ViewInjector(CleanMessageActivity$$ViewInjector cleanMessageActivity$$ViewInjector, CleanMessageActivity cleanMessageActivity) {
            this.f4317b = cleanMessageActivity$$ViewInjector;
            this.f4316a = cleanMessageActivity;
        }

        public void m6758a(View view) {
            this.f4316a.m6771m();
        }
    }

    /* renamed from: app.setting.CleanMessageActivity$.ViewInjector.5 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ CleanMessageActivity f4318a;
        final /* synthetic */ CleanMessageActivity$$ViewInjector f4319b;

        ViewInjector(CleanMessageActivity$$ViewInjector cleanMessageActivity$$ViewInjector, CleanMessageActivity cleanMessageActivity) {
            this.f4319b = cleanMessageActivity$$ViewInjector;
            this.f4318a = cleanMessageActivity;
        }

        public void m6759a(View view) {
            this.f4318a.m6772n();
        }
    }

    public void inject(Finder finder, T t, Object obj) {
        ((View) finder.m7732a(obj, 2131755156, "method 'onCleanOneToOneClick'")).setOnClickListener(new ViewInjector(this, t));
        ((View) finder.m7732a(obj, 2131755157, "method 'onCleanGroupClick'")).setOnClickListener(new ViewInjector(this, t));
        ((View) finder.m7732a(obj, 2131755158, "method 'onCleanChannelClick'")).setOnClickListener(new ViewInjector(this, t));
        ((View) finder.m7732a(obj, 2131755159, "method 'onCleanBroadcastClick'")).setOnClickListener(new ViewInjector(this, t));
        ((View) finder.m7732a(obj, 2131755160, "method 'onCleanAllClick'")).setOnClickListener(new ViewInjector(this, t));
    }

    public void reset(T t) {
    }
}
