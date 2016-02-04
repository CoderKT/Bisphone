package app.setting;

import android.view.View;
import app.setting.SettingMessageActivity$$ViewInjector$SettingMessageActivity$.ViewInjector;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;
import butterknife.internal.DebouncingOnClickListener;

public class SettingMessageActivity$$ViewInjector<T extends SettingMessageActivity> implements Injector<T> {

    /* renamed from: app.setting.SettingMessageActivity$.ViewInjector.1 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ SettingMessageActivity f4426a;
        final /* synthetic */ SettingMessageActivity$$ViewInjector f4427b;

        ViewInjector(SettingMessageActivity$$ViewInjector settingMessageActivity$$ViewInjector, SettingMessageActivity settingMessageActivity) {
            this.f4427b = settingMessageActivity$$ViewInjector;
            this.f4426a = settingMessageActivity;
        }

        public void m6834a(View view) {
            this.f4426a.m6839j();
        }
    }

    /* renamed from: app.setting.SettingMessageActivity$.ViewInjector.2 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ SettingMessageActivity f4428a;
        final /* synthetic */ SettingMessageActivity$$ViewInjector f4429b;

        ViewInjector(SettingMessageActivity$$ViewInjector settingMessageActivity$$ViewInjector, SettingMessageActivity settingMessageActivity) {
            this.f4429b = settingMessageActivity$$ViewInjector;
            this.f4428a = settingMessageActivity;
        }

        public void m6835a(View view) {
            this.f4428a.m6840k();
        }
    }

    /* renamed from: app.setting.SettingMessageActivity$.ViewInjector.3 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ SettingMessageActivity f4430a;
        final /* synthetic */ SettingMessageActivity$$ViewInjector f4431b;

        ViewInjector(SettingMessageActivity$$ViewInjector settingMessageActivity$$ViewInjector, SettingMessageActivity settingMessageActivity) {
            this.f4431b = settingMessageActivity$$ViewInjector;
            this.f4430a = settingMessageActivity;
        }

        public void m6836a(View view) {
            this.f4430a.m6841l();
        }
    }

    public void inject(Finder finder, T t, Object obj) {
        ((View) finder.m7732a(obj, 2131755279, "method 'onChooseBackgroundClick'")).setOnClickListener(new ViewInjector(this, t));
        ((View) finder.m7732a(obj, 2131755280, "method 'onClearMessageClick'")).setOnClickListener(new ViewInjector(this, t));
        ((View) finder.m7732a(obj, 2131755281, "method 'onChooseTextSizeClick'")).setOnClickListener(new ViewInjector(this, t));
    }

    public void reset(T t) {
    }
}
