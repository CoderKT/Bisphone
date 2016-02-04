package app.signin;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import app.signin.SignInFragment$$ViewInjector$SignInFragment$.ViewInjector;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;
import butterknife.internal.DebouncingOnClickListener;

public class SignInFragment$$ViewInjector<T extends SignInFragment> implements Injector<T> {

    /* renamed from: app.signin.SignInFragment$.ViewInjector.1 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ SignInFragment f4477a;
        final /* synthetic */ SignInFragment$$ViewInjector f4478b;

        ViewInjector(SignInFragment$$ViewInjector signInFragment$$ViewInjector, SignInFragment signInFragment) {
            this.f4478b = signInFragment$$ViewInjector;
            this.f4477a = signInFragment;
        }

        public void m6864a(View view) {
            this.f4477a.m6890b();
        }
    }

    /* renamed from: app.signin.SignInFragment$.ViewInjector.2 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ SignInFragment f4479a;
        final /* synthetic */ SignInFragment$$ViewInjector f4480b;

        ViewInjector(SignInFragment$$ViewInjector signInFragment$$ViewInjector, SignInFragment signInFragment) {
            this.f4480b = signInFragment$$ViewInjector;
            this.f4479a = signInFragment;
        }

        public void m6865a(View view) {
            this.f4479a.m6889a();
        }
    }

    public void inject(Finder finder, T t, Object obj) {
        View view = (View) finder.m7732a(obj, 2131755423, "field 'letsStart' and method 'onLetsStartClicked'");
        t.f4489a = (Button) finder.m7731a(view, 2131755423, "field 'letsStart'");
        view.setOnClickListener(new ViewInjector(this, t));
        view = (View) finder.m7732a(obj, 2131755425, "field 'countryEditText' and method 'startCountryListActivity'");
        t.f4490b = (EditText) finder.m7731a(view, 2131755425, "field 'countryEditText'");
        view.setOnClickListener(new ViewInjector(this, t));
        t.f4491c = (EditText) finder.m7731a((View) finder.m7732a(obj, 2131755427, "field 'phoneEditText'"), 2131755427, "field 'phoneEditText'");
        t.f4492d = (EditText) finder.m7731a((View) finder.m7732a(obj, 2131755426, "field 'dialingCodeEditText'"), 2131755426, "field 'dialingCodeEditText'");
        t.f4493e = (ScrollView) finder.m7731a((View) finder.m7732a(obj, 2131755424, "field 'scrollView'"), 2131755424, "field 'scrollView'");
    }

    public void reset(T t) {
        t.f4489a = null;
        t.f4490b = null;
        t.f4491c = null;
        t.f4492d = null;
        t.f4493e = null;
    }
}
