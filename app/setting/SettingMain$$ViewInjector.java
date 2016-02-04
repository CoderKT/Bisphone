package app.setting;

import android.view.View;
import app.setting.SettingMain$$ViewInjector$SettingMain$.ViewInjector;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;
import butterknife.internal.DebouncingOnClickListener;

public class SettingMain$$ViewInjector<T extends SettingMain> implements Injector<T> {

    /* renamed from: app.setting.SettingMain$.ViewInjector.1 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ SettingMain f4376a;
        final /* synthetic */ SettingMain$$ViewInjector f4377b;

        ViewInjector(SettingMain$$ViewInjector settingMain$$ViewInjector, SettingMain settingMain) {
            this.f4377b = settingMain$$ViewInjector;
            this.f4376a = settingMain;
        }

        public void m6800a(View view) {
            this.f4376a.m6805j();
        }
    }

    /* renamed from: app.setting.SettingMain$.ViewInjector.2 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ SettingMain f4378a;
        final /* synthetic */ SettingMain$$ViewInjector f4379b;

        ViewInjector(SettingMain$$ViewInjector settingMain$$ViewInjector, SettingMain settingMain) {
            this.f4379b = settingMain$$ViewInjector;
            this.f4378a = settingMain;
        }

        public void m6801a(View view) {
            this.f4378a.m6806k();
        }
    }

    /* renamed from: app.setting.SettingMain$.ViewInjector.3 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ SettingMain f4380a;
        final /* synthetic */ SettingMain$$ViewInjector f4381b;

        ViewInjector(SettingMain$$ViewInjector settingMain$$ViewInjector, SettingMain settingMain) {
            this.f4381b = settingMain$$ViewInjector;
            this.f4380a = settingMain;
        }

        public void m6802a(View view) {
            this.f4380a.m6807l();
        }
    }

    /* renamed from: app.setting.SettingMain$.ViewInjector.4 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ SettingMain f4382a;
        final /* synthetic */ SettingMain$$ViewInjector f4383b;

        ViewInjector(SettingMain$$ViewInjector settingMain$$ViewInjector, SettingMain settingMain) {
            this.f4383b = settingMain$$ViewInjector;
            this.f4382a = settingMain;
        }

        public void m6803a(View view) {
            this.f4382a.m6808m();
        }
    }

    /* renamed from: app.setting.SettingMain$.ViewInjector.5 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ SettingMain f4384a;
        final /* synthetic */ SettingMain$$ViewInjector f4385b;

        ViewInjector(SettingMain$$ViewInjector settingMain$$ViewInjector, SettingMain settingMain) {
            this.f4385b = settingMain$$ViewInjector;
            this.f4384a = settingMain;
        }

        public void m6804a(View view) {
            this.f4384a.m6809n();
        }
    }

    public void inject(Finder finder, T t, Object obj) {
        ((View) finder.m7732a(obj, 2131755261, "method 'onChanelExploreClick'")).setOnClickListener(new ViewInjector(this, t));
        ((View) finder.m7732a(obj, 2131755259, "method 'onStickerClick'")).setOnClickListener(new ViewInjector(this, t));
        ((View) finder.m7732a(obj, 2131755253, "method 'onGeneralClick'")).setOnClickListener(new ViewInjector(this, t));
        ((View) finder.m7732a(obj, 2131755255, "method 'onMediaClick'")).setOnClickListener(new ViewInjector(this, t));
        ((View) finder.m7732a(obj, 2131755257, "method 'onMessageClick'")).setOnClickListener(new ViewInjector(this, t));
    }

    public void reset(T t) {
    }
}
