package app.signin;

import android.view.View;
import android.widget.Button;
import app.signin.IntroductionFragment$$ViewInjector$IntroductionFragment$.ViewInjector;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;
import butterknife.internal.DebouncingOnClickListener;

public class IntroductionFragment$$ViewInjector<T extends IntroductionFragment> implements Injector<T> {

    /* renamed from: app.signin.IntroductionFragment$.ViewInjector.1 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ IntroductionFragment f4453a;
        final /* synthetic */ IntroductionFragment$$ViewInjector f4454b;

        ViewInjector(IntroductionFragment$$ViewInjector introductionFragment$$ViewInjector, IntroductionFragment introductionFragment) {
            this.f4454b = introductionFragment$$ViewInjector;
            this.f4453a = introductionFragment;
        }

        public void m6846a(View view) {
            this.f4453a.m6847a();
        }
    }

    public void inject(Finder finder, T t, Object obj) {
        View view = (View) finder.m7732a(obj, 2131755385, "field 'continue_btn' and method 'onContinue'");
        t.f4455a = (Button) finder.m7731a(view, 2131755385, "field 'continue_btn'");
        view.setOnClickListener(new ViewInjector(this, t));
    }

    public void reset(T t) {
        t.f4455a = null;
    }
}
