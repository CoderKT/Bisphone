package app.setting;

import android.view.View;
import android.widget.ImageView;
import app.setting.SettingMediaAutoDownloadDetail$$ViewInjector$SettingMediaAutoDownloadDetail$.ViewInjector;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;
import butterknife.internal.DebouncingOnClickListener;

public class SettingMediaAutoDownloadDetail$$ViewInjector<T extends SettingMediaAutoDownloadDetail> implements Injector<T> {

    /* renamed from: app.setting.SettingMediaAutoDownloadDetail$.ViewInjector.1 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ SettingMediaAutoDownloadDetail f4416a;
        final /* synthetic */ SettingMediaAutoDownloadDetail$$ViewInjector f4417b;

        ViewInjector(SettingMediaAutoDownloadDetail$$ViewInjector settingMediaAutoDownloadDetail$$ViewInjector, SettingMediaAutoDownloadDetail settingMediaAutoDownloadDetail) {
            this.f4417b = settingMediaAutoDownloadDetail$$ViewInjector;
            this.f4416a = settingMediaAutoDownloadDetail;
        }

        public void m6827a(View view) {
            this.f4416a.m6831j();
        }
    }

    /* renamed from: app.setting.SettingMediaAutoDownloadDetail$.ViewInjector.2 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ SettingMediaAutoDownloadDetail f4418a;
        final /* synthetic */ SettingMediaAutoDownloadDetail$$ViewInjector f4419b;

        ViewInjector(SettingMediaAutoDownloadDetail$$ViewInjector settingMediaAutoDownloadDetail$$ViewInjector, SettingMediaAutoDownloadDetail settingMediaAutoDownloadDetail) {
            this.f4419b = settingMediaAutoDownloadDetail$$ViewInjector;
            this.f4418a = settingMediaAutoDownloadDetail;
        }

        public void m6828a(View view) {
            this.f4418a.m6832k();
        }
    }

    /* renamed from: app.setting.SettingMediaAutoDownloadDetail$.ViewInjector.3 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ SettingMediaAutoDownloadDetail f4420a;
        final /* synthetic */ SettingMediaAutoDownloadDetail$$ViewInjector f4421b;

        ViewInjector(SettingMediaAutoDownloadDetail$$ViewInjector settingMediaAutoDownloadDetail$$ViewInjector, SettingMediaAutoDownloadDetail settingMediaAutoDownloadDetail) {
            this.f4421b = settingMediaAutoDownloadDetail$$ViewInjector;
            this.f4420a = settingMediaAutoDownloadDetail;
        }

        public void m6829a(View view) {
            this.f4420a.m6833l();
        }
    }

    public void inject(Finder finder, T t, Object obj) {
        t.f4422o = (ImageView) finder.m7731a((View) finder.m7732a(obj, 2131755274, "field 'never'"), 2131755274, "field 'never'");
        t.f4423p = (ImageView) finder.m7731a((View) finder.m7732a(obj, 2131755278, "field 'wifiAndCellular'"), 2131755278, "field 'wifiAndCellular'");
        t.f4424q = (ImageView) finder.m7731a((View) finder.m7732a(obj, 2131755276, "field 'wifi'"), 2131755276, "field 'wifi'");
        ((View) finder.m7732a(obj, 2131755277, "method 'onBothclick'")).setOnClickListener(new ViewInjector(this, t));
        ((View) finder.m7732a(obj, 2131755273, "method 'onNeverclick'")).setOnClickListener(new ViewInjector(this, t));
        ((View) finder.m7732a(obj, 2131755275, "method 'onWificlick'")).setOnClickListener(new ViewInjector(this, t));
    }

    public void reset(T t) {
        t.f4422o = null;
        t.f4423p = null;
        t.f4424q = null;
    }
}
