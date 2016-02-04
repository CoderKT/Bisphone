package app.profile;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import app.profile.ProfileViewer$$ViewInjector$ProfileViewer$.ViewInjector;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;
import butterknife.internal.DebouncingOnClickListener;

public class ProfileViewer$$ViewInjector<T extends ProfileViewer> implements Injector<T> {

    /* renamed from: app.profile.ProfileViewer$.ViewInjector.1 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ ProfileViewer f4254a;
        final /* synthetic */ ProfileViewer$$ViewInjector f4255b;

        ViewInjector(ProfileViewer$$ViewInjector profileViewer$$ViewInjector, ProfileViewer profileViewer) {
            this.f4255b = profileViewer$$ViewInjector;
            this.f4254a = profileViewer;
        }

        public void m6725a(View view) {
            this.f4254a.m6740j();
        }
    }

    /* renamed from: app.profile.ProfileViewer$.ViewInjector.2 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ ProfileViewer f4256a;
        final /* synthetic */ ProfileViewer$$ViewInjector f4257b;

        ViewInjector(ProfileViewer$$ViewInjector profileViewer$$ViewInjector, ProfileViewer profileViewer) {
            this.f4257b = profileViewer$$ViewInjector;
            this.f4256a = profileViewer;
        }

        public void m6726a(View view) {
            this.f4256a.m6743m();
        }
    }

    /* renamed from: app.profile.ProfileViewer$.ViewInjector.3 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ ProfileViewer f4258a;
        final /* synthetic */ ProfileViewer$$ViewInjector f4259b;

        ViewInjector(ProfileViewer$$ViewInjector profileViewer$$ViewInjector, ProfileViewer profileViewer) {
            this.f4259b = profileViewer$$ViewInjector;
            this.f4258a = profileViewer;
        }

        public void m6727a(View view) {
            this.f4258a.m6742l();
        }
    }

    /* renamed from: app.profile.ProfileViewer$.ViewInjector.4 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ ProfileViewer f4260a;
        final /* synthetic */ ProfileViewer$$ViewInjector f4261b;

        ViewInjector(ProfileViewer$$ViewInjector profileViewer$$ViewInjector, ProfileViewer profileViewer) {
            this.f4261b = profileViewer$$ViewInjector;
            this.f4260a = profileViewer;
        }

        public void m6728a(View view) {
            this.f4260a.m6746p();
        }
    }

    /* renamed from: app.profile.ProfileViewer$.ViewInjector.5 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ ProfileViewer f4262a;
        final /* synthetic */ ProfileViewer$$ViewInjector f4263b;

        ViewInjector(ProfileViewer$$ViewInjector profileViewer$$ViewInjector, ProfileViewer profileViewer) {
            this.f4263b = profileViewer$$ViewInjector;
            this.f4262a = profileViewer;
        }

        public void m6729a(View view) {
            this.f4262a.m6745o();
        }
    }

    /* renamed from: app.profile.ProfileViewer$.ViewInjector.6 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ ProfileViewer f4264a;
        final /* synthetic */ ProfileViewer$$ViewInjector f4265b;

        ViewInjector(ProfileViewer$$ViewInjector profileViewer$$ViewInjector, ProfileViewer profileViewer) {
            this.f4265b = profileViewer$$ViewInjector;
            this.f4264a = profileViewer;
        }

        public void m6730a(View view) {
            this.f4264a.m6744n();
        }
    }

    /* renamed from: app.profile.ProfileViewer$.ViewInjector.7 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ ProfileViewer f4266a;
        final /* synthetic */ ProfileViewer$$ViewInjector f4267b;

        ViewInjector(ProfileViewer$$ViewInjector profileViewer$$ViewInjector, ProfileViewer profileViewer) {
            this.f4267b = profileViewer$$ViewInjector;
            this.f4266a = profileViewer;
        }

        public void m6731a(View view) {
            this.f4266a.m6741k();
        }
    }

    public void inject(Finder finder, T t, Object obj) {
        View view = (View) finder.m7732a(obj, 2131755510, "field 'avatar' and method 'onProfileClick'");
        t.f4280o = (ImageView) finder.m7731a(view, 2131755510, "field 'avatar'");
        view.setOnClickListener(new ViewInjector(this, t));
        t.f4281p = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755515, "field 'nickName'"), 2131755515, "field 'nickName'");
        t.f4282q = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755516, "field 'status'"), 2131755516, "field 'status'");
        view = (View) finder.m7732a(obj, 2131755517, "field 'freeText' and method 'messageButtonClick'");
        t.f4283r = (Button) finder.m7731a(view, 2131755517, "field 'freeText'");
        view.setOnClickListener(new ViewInjector(this, t));
        view = (View) finder.m7732a(obj, 2131755518, "field 'freeCall' and method 'callButtonClick'");
        t.f4284s = (Button) finder.m7731a(view, 2131755518, "field 'freeCall'");
        view.setOnClickListener(new ViewInjector(this, t));
        view = (View) finder.m7732a(obj, 2131755520, "field 'shareInfo' and method 'sendInfoMessage'");
        t.f4285t = (Button) finder.m7731a(view, 2131755520, "field 'shareInfo'");
        view.setOnClickListener(new ViewInjector(this, t));
        view = (View) finder.m7732a(obj, 2131755519, "field 'inviteUser' and method 'onInviteClicked'");
        t.f4286u = (Button) finder.m7731a(view, 2131755519, "field 'inviteUser'");
        view.setOnClickListener(new ViewInjector(this, t));
        t.f4287v = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755523, "field 'contactNumber'"), 2131755523, "field 'contactNumber'");
        view = (View) finder.m7732a(obj, 2131755521, "field 'mainPhoneLayout' and method 'onBisPhoneOutClicked'");
        t.f4288w = (LinearLayout) finder.m7731a(view, 2131755521, "field 'mainPhoneLayout'");
        view.setOnClickListener(new ViewInjector(this, t));
        t.f4289x = (LinearLayout) finder.m7731a((View) finder.m7732a(obj, 2131755525, "field 'otherPhoneLayout'"), 2131755525, "field 'otherPhoneLayout'");
        t.f4290y = (LinearLayout) finder.m7731a((View) finder.m7732a(obj, 2131755526, "field 'profileOtherParent'"), 2131755526, "field 'profileOtherParent'");
        view = (View) finder.m7732a(obj, 2131755527, "field 'fullSizeImage' and method 'onFullSizeImageClicked'");
        t.f4291z = (ImageView) finder.m7731a(view, 2131755527, "field 'fullSizeImage'");
        view.setOnClickListener(new ViewInjector(this, t));
    }

    public void reset(T t) {
        t.f4280o = null;
        t.f4281p = null;
        t.f4282q = null;
        t.f4283r = null;
        t.f4284s = null;
        t.f4285t = null;
        t.f4286u = null;
        t.f4287v = null;
        t.f4288w = null;
        t.f4289x = null;
        t.f4290y = null;
        t.f4291z = null;
    }
}
