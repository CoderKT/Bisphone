package app.profile;

import android.view.View;
import android.widget.RelativeLayout;
import app.profile.SettingsActivity$$ViewInjector$SettingsActivity$.ViewInjector;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;
import butterknife.internal.DebouncingOnClickListener;

public class SettingsActivity$$ViewInjector<T extends SettingsActivity> implements Injector<T> {

    /* renamed from: app.profile.SettingsActivity$.ViewInjector.1 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ SettingsActivity f4292a;
        final /* synthetic */ SettingsActivity$$ViewInjector f4293b;

        ViewInjector(SettingsActivity$$ViewInjector settingsActivity$$ViewInjector, SettingsActivity settingsActivity) {
            this.f4293b = settingsActivity$$ViewInjector;
            this.f4292a = settingsActivity;
        }

        public void m6747a(View view) {
            this.f4292a.m6752j();
        }
    }

    /* renamed from: app.profile.SettingsActivity$.ViewInjector.2 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ SettingsActivity f4294a;
        final /* synthetic */ SettingsActivity$$ViewInjector f4295b;

        ViewInjector(SettingsActivity$$ViewInjector settingsActivity$$ViewInjector, SettingsActivity settingsActivity) {
            this.f4295b = settingsActivity$$ViewInjector;
            this.f4294a = settingsActivity;
        }

        public void m6748a(View view) {
            this.f4294a.m6753k();
        }
    }

    /* renamed from: app.profile.SettingsActivity$.ViewInjector.3 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ SettingsActivity f4296a;
        final /* synthetic */ SettingsActivity$$ViewInjector f4297b;

        ViewInjector(SettingsActivity$$ViewInjector settingsActivity$$ViewInjector, SettingsActivity settingsActivity) {
            this.f4297b = settingsActivity$$ViewInjector;
            this.f4296a = settingsActivity;
        }

        public void m6749a(View view) {
            this.f4296a.m6754l();
        }
    }

    public void inject(Finder finder, T t, Object obj) {
        View view = (View) finder.m7732a(obj, 2131755283, "field 'clearHistoryButton' and method 'onClearHistoryClicked'");
        t.f4302k = (RelativeLayout) finder.m7731a(view, 2131755283, "field 'clearHistoryButton'");
        view.setOnClickListener(new ViewInjector(this, t));
        view = (View) finder.m7732a(obj, 2131755284, "field 'signoutButton' and method 'onSignOutClicked'");
        t.f4303o = (RelativeLayout) finder.m7731a(view, 2131755284, "field 'signoutButton'");
        view.setOnClickListener(new ViewInjector(this, t));
        ((View) finder.m7732a(obj, 2131755282, "method 'onUpdateContactsClicked'")).setOnClickListener(new ViewInjector(this, t));
    }

    public void reset(T t) {
        t.f4302k = null;
        t.f4303o = null;
    }
}
