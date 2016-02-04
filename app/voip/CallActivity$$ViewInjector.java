package app.voip;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import app.voip.CallActivity$$ViewInjector$CallActivity$.ViewInjector;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;
import butterknife.internal.DebouncingOnClickListener;

public class CallActivity$$ViewInjector<T extends CallActivity> implements Injector<T> {

    /* renamed from: app.voip.CallActivity$.ViewInjector.1 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ CallActivity f4661a;
        final /* synthetic */ CallActivity$$ViewInjector f4662b;

        ViewInjector(CallActivity$$ViewInjector callActivity$$ViewInjector, CallActivity callActivity) {
            this.f4662b = callActivity$$ViewInjector;
            this.f4661a = callActivity;
        }

        public void m7125a(View view) {
            this.f4661a.m7140j();
        }
    }

    /* renamed from: app.voip.CallActivity$.ViewInjector.2 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ CallActivity f4663a;
        final /* synthetic */ CallActivity$$ViewInjector f4664b;

        ViewInjector(CallActivity$$ViewInjector callActivity$$ViewInjector, CallActivity callActivity) {
            this.f4664b = callActivity$$ViewInjector;
            this.f4663a = callActivity;
        }

        public void m7126a(View view) {
            this.f4663a.m7141k();
        }
    }

    /* renamed from: app.voip.CallActivity$.ViewInjector.3 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ CallActivity f4665a;
        final /* synthetic */ CallActivity$$ViewInjector f4666b;

        ViewInjector(CallActivity$$ViewInjector callActivity$$ViewInjector, CallActivity callActivity) {
            this.f4666b = callActivity$$ViewInjector;
            this.f4665a = callActivity;
        }

        public void m7127a(View view) {
            this.f4665a.m7142l();
        }
    }

    /* renamed from: app.voip.CallActivity$.ViewInjector.4 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ CallActivity f4667a;
        final /* synthetic */ CallActivity$$ViewInjector f4668b;

        ViewInjector(CallActivity$$ViewInjector callActivity$$ViewInjector, CallActivity callActivity) {
            this.f4668b = callActivity$$ViewInjector;
            this.f4667a = callActivity;
        }

        public void m7128a(View view) {
            this.f4667a.m7143m();
        }
    }

    /* renamed from: app.voip.CallActivity$.ViewInjector.5 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ CallActivity f4669a;
        final /* synthetic */ CallActivity$$ViewInjector f4670b;

        ViewInjector(CallActivity$$ViewInjector callActivity$$ViewInjector, CallActivity callActivity) {
            this.f4670b = callActivity$$ViewInjector;
            this.f4669a = callActivity;
        }

        public void m7129a(View view) {
            this.f4669a.m7144n();
        }
    }

    /* renamed from: app.voip.CallActivity$.ViewInjector.6 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ CallActivity f4671a;
        final /* synthetic */ CallActivity$$ViewInjector f4672b;

        ViewInjector(CallActivity$$ViewInjector callActivity$$ViewInjector, CallActivity callActivity) {
            this.f4672b = callActivity$$ViewInjector;
            this.f4671a = callActivity;
        }

        public void m7130a(View view) {
            this.f4671a.m7145o();
        }
    }

    /* renamed from: app.voip.CallActivity$.ViewInjector.7 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ CallActivity f4673a;
        final /* synthetic */ CallActivity$$ViewInjector f4674b;

        ViewInjector(CallActivity$$ViewInjector callActivity$$ViewInjector, CallActivity callActivity) {
            this.f4674b = callActivity$$ViewInjector;
            this.f4673a = callActivity;
        }

        public void m7131a(View view) {
            this.f4673a.m7146p();
        }
    }

    public void inject(Finder finder, T t, Object obj) {
        t.f4707o = (ViewGroup) finder.m7731a((View) finder.m7732a(obj, 2131755125, "field 'defaultLayout'"), 2131755125, "field 'defaultLayout'");
        t.f4708p = (ViewGroup) finder.m7731a((View) finder.m7732a(obj, 2131755121, "field 'ringerLayout'"), 2131755121, "field 'ringerLayout'");
        t.f4709q = (ImageView) finder.m7731a((View) finder.m7732a(obj, 2131755116, "field 'avatarImageView'"), 2131755116, "field 'avatarImageView'");
        t.f4710r = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755117, "field 'contactNameTV'"), 2131755117, "field 'contactNameTV'");
        t.f4711s = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755118, "field 'contactUsernameTV'"), 2131755118, "field 'contactUsernameTV'");
        t.f4712t = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755119, "field 'callStatusTV'"), 2131755119, "field 'callStatusTV'");
        t.f4713u = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755120, "field 'networkQualityTV'"), 2131755120, "field 'networkQualityTV'");
        View view = (View) finder.m7732a(obj, 2131755124, "field 'answerButton' and method 'onAcceptClicked'");
        t.f4714v = (ImageButton) finder.m7731a(view, 2131755124, "field 'answerButton'");
        view.setOnClickListener(new ViewInjector(this, t));
        view = (View) finder.m7732a(obj, 2131755122, "field 'declineButton' and method 'onRejectClicked'");
        t.f4715w = (ImageButton) finder.m7731a(view, 2131755122, "field 'declineButton'");
        view.setOnClickListener(new ViewInjector(this, t));
        view = (View) finder.m7732a(obj, 2131755123, "field 'declineWithMessageButton' and method 'onDeclineWithMessageClicked'");
        t.f4716x = (ImageButton) finder.m7731a(view, 2131755123, "field 'declineWithMessageButton'");
        view.setOnClickListener(new ViewInjector(this, t));
        view = (View) finder.m7732a(obj, 2131755130, "field 'hangupButton' and method 'onHangupClicked'");
        t.f4717y = (ImageButton) finder.m7731a(view, 2131755130, "field 'hangupButton'");
        view.setOnClickListener(new ViewInjector(this, t));
        view = (View) finder.m7732a(obj, 2131755128, "field 'holdButton' and method 'onHoldClicked'");
        t.f4718z = (ImageButton) finder.m7731a(view, 2131755128, "field 'holdButton'");
        view.setOnClickListener(new ViewInjector(this, t));
        view = (View) finder.m7732a(obj, 2131755126, "field 'speakerButton' and method 'onSpeakerClicked'");
        t.f4682A = (ImageButton) finder.m7731a(view, 2131755126, "field 'speakerButton'");
        view.setOnClickListener(new ViewInjector(this, t));
        view = (View) finder.m7732a(obj, 2131755127, "field 'muteButton' and method 'onMuteClicked'");
        t.f4683B = (ImageButton) finder.m7731a(view, 2131755127, "field 'muteButton'");
        view.setOnClickListener(new ViewInjector(this, t));
        t.f4684C = (ImageButton) finder.m7731a((View) finder.m7732a(obj, 2131755129, "field 'dialPadButton'"), 2131755129, "field 'dialPadButton'");
    }

    public void reset(T t) {
        t.f4707o = null;
        t.f4708p = null;
        t.f4709q = null;
        t.f4710r = null;
        t.f4711s = null;
        t.f4712t = null;
        t.f4713u = null;
        t.f4714v = null;
        t.f4715w = null;
        t.f4716x = null;
        t.f4717y = null;
        t.f4718z = null;
        t.f4682A = null;
        t.f4683B = null;
        t.f4684C = null;
    }
}
