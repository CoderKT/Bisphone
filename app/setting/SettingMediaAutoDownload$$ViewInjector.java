package app.setting;

import android.view.View;
import android.widget.TextView;
import app.setting.SettingMediaAutoDownload$$ViewInjector$SettingMediaAutoDownload$.ViewInjector;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;
import butterknife.internal.DebouncingOnClickListener;

public class SettingMediaAutoDownload$$ViewInjector<T extends SettingMediaAutoDownload> implements Injector<T> {

    /* renamed from: app.setting.SettingMediaAutoDownload$.ViewInjector.1 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ SettingMediaAutoDownload f4410a;
        final /* synthetic */ SettingMediaAutoDownload$$ViewInjector f4411b;

        ViewInjector(SettingMediaAutoDownload$$ViewInjector settingMediaAutoDownload$$ViewInjector, SettingMediaAutoDownload settingMediaAutoDownload) {
            this.f4411b = settingMediaAutoDownload$$ViewInjector;
            this.f4410a = settingMediaAutoDownload;
        }

        public void m6820a(View view) {
            this.f4410a.m6825j();
        }
    }

    /* renamed from: app.setting.SettingMediaAutoDownload$.ViewInjector.2 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ SettingMediaAutoDownload f4412a;
        final /* synthetic */ SettingMediaAutoDownload$$ViewInjector f4413b;

        ViewInjector(SettingMediaAutoDownload$$ViewInjector settingMediaAutoDownload$$ViewInjector, SettingMediaAutoDownload settingMediaAutoDownload) {
            this.f4413b = settingMediaAutoDownload$$ViewInjector;
            this.f4412a = settingMediaAutoDownload;
        }

        public void m6821a(View view) {
            this.f4412a.m6826k();
        }
    }

    public void inject(Finder finder, T t, Object obj) {
        t.f4414o = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755272, "field 'videoDesc'"), 2131755272, "field 'videoDesc'");
        t.f4415p = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755270, "field 'imageDesc'"), 2131755270, "field 'imageDesc'");
        ((View) finder.m7732a(obj, 2131755269, "method 'onImageclick'")).setOnClickListener(new ViewInjector(this, t));
        ((View) finder.m7732a(obj, 2131755271, "method 'onVideoclick'")).setOnClickListener(new ViewInjector(this, t));
    }

    public void reset(T t) {
        t.f4414o = null;
        t.f4415p = null;
    }
}
