package app.setting;

import android.view.View;
import app.setting.SettingGeneral$$ViewInjector$SettingGeneral$.ViewInjector;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;
import butterknife.internal.DebouncingOnClickListener;

public class SettingGeneral$$ViewInjector<T extends SettingGeneral> implements Injector<T> {

    /* renamed from: app.setting.SettingGeneral$.ViewInjector.1 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ SettingGeneral f4367a;
        final /* synthetic */ SettingGeneral$$ViewInjector f4368b;

        ViewInjector(SettingGeneral$$ViewInjector settingGeneral$$ViewInjector, SettingGeneral settingGeneral) {
            this.f4368b = settingGeneral$$ViewInjector;
            this.f4367a = settingGeneral;
        }

        public void m6792a(View view) {
            this.f4367a.m6797j();
        }
    }

    /* renamed from: app.setting.SettingGeneral$.ViewInjector.2 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ SettingGeneral f4369a;
        final /* synthetic */ SettingGeneral$$ViewInjector f4370b;

        ViewInjector(SettingGeneral$$ViewInjector settingGeneral$$ViewInjector, SettingGeneral settingGeneral) {
            this.f4370b = settingGeneral$$ViewInjector;
            this.f4369a = settingGeneral;
        }

        public void m6793a(View view) {
            this.f4369a.m6798k();
        }
    }

    /* renamed from: app.setting.SettingGeneral$.ViewInjector.3 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ SettingGeneral f4371a;
        final /* synthetic */ SettingGeneral$$ViewInjector f4372b;

        ViewInjector(SettingGeneral$$ViewInjector settingGeneral$$ViewInjector, SettingGeneral settingGeneral) {
            this.f4372b = settingGeneral$$ViewInjector;
            this.f4371a = settingGeneral;
        }

        public void m6794a(View view) {
            this.f4371a.m6799l();
        }
    }

    public void inject(Finder finder, T t, Object obj) {
        ((View) finder.m7732a(obj, 2131755251, "method 'onContentLanguageClicked'")).setOnClickListener(new ViewInjector(this, t));
        ((View) finder.m7732a(obj, 2131755252, "method 'onDeactivateClicked'")).setOnClickListener(new ViewInjector(this, t));
        ((View) finder.m7732a(obj, 2131755250, "method 'onUpdateContactClicked'")).setOnClickListener(new ViewInjector(this, t));
    }

    public void reset(T t) {
    }
}
