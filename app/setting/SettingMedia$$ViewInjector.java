package app.setting;

import android.view.View;
import android.widget.ImageView;
import app.setting.SettingMedia$$ViewInjector$SettingMedia$.ViewInjector;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;
import butterknife.internal.DebouncingOnClickListener;

public class SettingMedia$$ViewInjector<T extends SettingMedia> implements Injector<T> {

    /* renamed from: app.setting.SettingMedia$.ViewInjector.1 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ SettingMedia f4386a;
        final /* synthetic */ SettingMedia$$ViewInjector f4387b;

        ViewInjector(SettingMedia$$ViewInjector settingMedia$$ViewInjector, SettingMedia settingMedia) {
            this.f4387b = settingMedia$$ViewInjector;
            this.f4386a = settingMedia;
        }

        public void m6810a(View view) {
            this.f4386a.m6816j();
        }
    }

    /* renamed from: app.setting.SettingMedia$.ViewInjector.2 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ SettingMedia f4388a;
        final /* synthetic */ SettingMedia$$ViewInjector f4389b;

        ViewInjector(SettingMedia$$ViewInjector settingMedia$$ViewInjector, SettingMedia settingMedia) {
            this.f4389b = settingMedia$$ViewInjector;
            this.f4388a = settingMedia;
        }

        public void m6811a(View view) {
            this.f4388a.m6817k();
        }
    }

    /* renamed from: app.setting.SettingMedia$.ViewInjector.3 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ SettingMedia f4390a;
        final /* synthetic */ SettingMedia$$ViewInjector f4391b;

        ViewInjector(SettingMedia$$ViewInjector settingMedia$$ViewInjector, SettingMedia settingMedia) {
            this.f4391b = settingMedia$$ViewInjector;
            this.f4390a = settingMedia;
        }

        public void m6812a(View view) {
            this.f4390a.m6818l();
        }
    }

    /* renamed from: app.setting.SettingMedia$.ViewInjector.4 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ SettingMedia f4392a;
        final /* synthetic */ SettingMedia$$ViewInjector f4393b;

        ViewInjector(SettingMedia$$ViewInjector settingMedia$$ViewInjector, SettingMedia settingMedia) {
            this.f4393b = settingMedia$$ViewInjector;
            this.f4392a = settingMedia;
        }

        public void m6813a(View view) {
            this.f4392a.m6819m();
        }
    }

    public void inject(Finder finder, T t, Object obj) {
        View view = (View) finder.m7732a(obj, 2131755264, "field 'autoSaveImageView' and method 'onAutoSaveClicked'");
        t.f4401o = view;
        view.setOnClickListener(new ViewInjector(this, t));
        t.f4402p = (ImageView) finder.m7731a((View) finder.m7732a(obj, 2131755265, "field 'imageAutoSaveCB'"), 2131755265, "field 'imageAutoSaveCB'");
        view = (View) finder.m7732a(obj, 2131755266, "field 'autoSaveVideoView' and method 'onVideoAutoSave'");
        t.f4403q = view;
        view.setOnClickListener(new ViewInjector(this, t));
        t.f4404r = (ImageView) finder.m7731a((View) finder.m7732a(obj, 2131755267, "field 'videoAutoSaveCB'"), 2131755267, "field 'videoAutoSaveCB'");
        view = (View) finder.m7732a(obj, 2131755268, "field 'cleanUp' and method 'onCleanUpClicked'");
        t.f4405s = view;
        view.setOnClickListener(new ViewInjector(this, t));
        ((View) finder.m7732a(obj, 2131755263, "method 'onAutoMediClick'")).setOnClickListener(new ViewInjector(this, t));
    }

    public void reset(T t) {
        t.f4401o = null;
        t.f4402p = null;
        t.f4403q = null;
        t.f4404r = null;
        t.f4405s = null;
    }
}
