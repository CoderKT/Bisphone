package app.profile;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import app.profile.CallLogActivity$$ViewInjector$CallLogActivity$.ViewInjector;
import app.view.UnScrollableListView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;
import butterknife.internal.DebouncingOnClickListener;
import parallaxedScrollView.ParallaxScrollView;

public class CallLogActivity$$ViewInjector<T extends CallLogActivity> implements Injector<T> {

    /* renamed from: app.profile.CallLogActivity$.ViewInjector.1 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ CallLogActivity f4183a;
        final /* synthetic */ CallLogActivity$$ViewInjector f4184b;

        ViewInjector(CallLogActivity$$ViewInjector callLogActivity$$ViewInjector, CallLogActivity callLogActivity) {
            this.f4184b = callLogActivity$$ViewInjector;
            this.f4183a = callLogActivity;
        }

        public void m6668a(View view) {
            this.f4183a.m6689j();
        }
    }

    /* renamed from: app.profile.CallLogActivity$.ViewInjector.2 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ CallLogActivity f4185a;
        final /* synthetic */ CallLogActivity$$ViewInjector f4186b;

        ViewInjector(CallLogActivity$$ViewInjector callLogActivity$$ViewInjector, CallLogActivity callLogActivity) {
            this.f4186b = callLogActivity$$ViewInjector;
            this.f4185a = callLogActivity;
        }

        public void m6669a(View view) {
            this.f4185a.m6691l();
        }
    }

    /* renamed from: app.profile.CallLogActivity$.ViewInjector.3 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ CallLogActivity f4187a;
        final /* synthetic */ CallLogActivity$$ViewInjector f4188b;

        ViewInjector(CallLogActivity$$ViewInjector callLogActivity$$ViewInjector, CallLogActivity callLogActivity) {
            this.f4188b = callLogActivity$$ViewInjector;
            this.f4187a = callLogActivity;
        }

        public void m6670a(View view) {
            this.f4187a.m6693n();
        }
    }

    /* renamed from: app.profile.CallLogActivity$.ViewInjector.4 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ CallLogActivity f4189a;
        final /* synthetic */ CallLogActivity$$ViewInjector f4190b;

        ViewInjector(CallLogActivity$$ViewInjector callLogActivity$$ViewInjector, CallLogActivity callLogActivity) {
            this.f4190b = callLogActivity$$ViewInjector;
            this.f4189a = callLogActivity;
        }

        public void m6671a(View view) {
            this.f4189a.m6690k();
        }
    }

    /* renamed from: app.profile.CallLogActivity$.ViewInjector.5 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ CallLogActivity f4191a;
        final /* synthetic */ CallLogActivity$$ViewInjector f4192b;

        ViewInjector(CallLogActivity$$ViewInjector callLogActivity$$ViewInjector, CallLogActivity callLogActivity) {
            this.f4192b = callLogActivity$$ViewInjector;
            this.f4191a = callLogActivity;
        }

        public void m6672a(View view) {
            this.f4191a.m6692m();
        }
    }

    public void inject(Finder finder, T t, Object obj) {
        t.f4207o = (ParallaxScrollView) finder.m7731a((View) finder.m7732a(obj, 2131755131, "field 'parallaxScrollView'"), 2131755131, "field 'parallaxScrollView'");
        t.f4208p = (UnScrollableListView) finder.m7731a((View) finder.m7732a(obj, 2131755136, "field 'callLogListView'"), 2131755136, "field 'callLogListView'");
        t.f4209q = (ImageView) finder.m7731a((View) finder.m7732a(obj, 2131755133, "field 'avatar'"), 2131755133, "field 'avatar'");
        t.f4210r = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755134, "field 'nickName'"), 2131755134, "field 'nickName'");
        t.f4211s = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755135, "field 'phoneNumber'"), 2131755135, "field 'phoneNumber'");
        t.f4212t = (ProgressBar) finder.m7731a((View) finder.m7732a(obj, 2131755137, "field 'progressBar'"), 2131755137, "field 'progressBar'");
        View view = (View) finder.m7732a(obj, 2131755142, "field 'inViteUser' and method 'onInviteUserClicked'");
        t.f4213u = view;
        view.setOnClickListener(new ViewInjector(this, t));
        view = (View) finder.m7732a(obj, 2131755139, "field 'message' and method 'messageButtonClick'");
        t.f4214v = view;
        view.setOnClickListener(new ViewInjector(this, t));
        view = (View) finder.m7732a(obj, 2131755143, "field 'bisphoneOut' and method 'onBisPhoneOutClicked'");
        t.f4215w = view;
        view.setOnClickListener(new ViewInjector(this, t));
        view = (View) finder.m7732a(obj, 2131755140, "field 'call' and method 'callButtonClick'");
        t.f4216x = view;
        view.setOnClickListener(new ViewInjector(this, t));
        view = (View) finder.m7732a(obj, 2131755141, "field 'sendMyInfo' and method 'sendInfoMessage'");
        t.f4217y = view;
        view.setOnClickListener(new ViewInjector(this, t));
    }

    public void reset(T t) {
        t.f4207o = null;
        t.f4208p = null;
        t.f4209q = null;
        t.f4210r = null;
        t.f4211s = null;
        t.f4212t = null;
        t.f4213u = null;
        t.f4214v = null;
        t.f4215w = null;
        t.f4216x = null;
        t.f4217y = null;
    }
}
