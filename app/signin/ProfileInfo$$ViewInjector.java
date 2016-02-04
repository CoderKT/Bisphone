package app.signin;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import app.signin.ProfileInfo$$ViewInjector$ProfileInfo$.ViewInjector;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;
import butterknife.internal.DebouncingOnClickListener;

public class ProfileInfo$$ViewInjector<T extends ProfileInfo> implements Injector<T> {

    /* renamed from: app.signin.ProfileInfo$.ViewInjector.1 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ ProfileInfo f4458a;
        final /* synthetic */ ProfileInfo$$ViewInjector f4459b;

        ViewInjector(ProfileInfo$$ViewInjector profileInfo$$ViewInjector, ProfileInfo profileInfo) {
            this.f4459b = profileInfo$$ViewInjector;
            this.f4458a = profileInfo;
        }

        public void m6848a(View view) {
            this.f4458a.m6856k();
        }
    }

    /* renamed from: app.signin.ProfileInfo$.ViewInjector.2 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ ProfileInfo f4460a;
        final /* synthetic */ ProfileInfo$$ViewInjector f4461b;

        ViewInjector(ProfileInfo$$ViewInjector profileInfo$$ViewInjector, ProfileInfo profileInfo) {
            this.f4461b = profileInfo$$ViewInjector;
            this.f4460a = profileInfo;
        }

        public void m6849a(View view) {
            this.f4460a.m6855j();
        }
    }

    public void inject(Finder finder, T t, Object obj) {
        View view = (View) finder.m7732a(obj, 2131755413, "field 'btn' and method 'startHomeActivity'");
        t.f4466k = (Button) finder.m7731a(view, 2131755413, "field 'btn'");
        view.setOnClickListener(new ViewInjector(this, t));
        t.f4467o = (EditText) finder.m7731a((View) finder.m7732a(obj, 2131755412, "field 'nickName'"), 2131755412, "field 'nickName'");
        view = (View) finder.m7732a(obj, 2131755411, "field 'avatarImageView' and method 'OnImageClick'");
        t.f4468p = (ImageView) finder.m7731a(view, 2131755411, "field 'avatarImageView'");
        view.setOnClickListener(new ViewInjector(this, t));
    }

    public void reset(T t) {
        t.f4466k = null;
        t.f4467o = null;
        t.f4468p = null;
    }
}
