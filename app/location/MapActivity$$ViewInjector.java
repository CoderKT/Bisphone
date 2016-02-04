package app.location;

import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import app.location.MapActivity$$ViewInjector$MapActivity$.ViewInjector;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;
import butterknife.internal.DebouncingOnClickListener;

public class MapActivity$$ViewInjector<T extends MapActivity> implements Injector<T> {

    /* renamed from: app.location.MapActivity$.ViewInjector.1 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ MapActivity f3130a;
        final /* synthetic */ MapActivity$$ViewInjector f3131b;

        ViewInjector(MapActivity$$ViewInjector mapActivity$$ViewInjector, MapActivity mapActivity) {
            this.f3131b = mapActivity$$ViewInjector;
            this.f3130a = mapActivity;
        }

        public void m5619a(View view) {
            this.f3130a.m5670j();
        }
    }

    /* renamed from: app.location.MapActivity$.ViewInjector.2 */
    class ViewInjector implements OnLongClickListener {
        final /* synthetic */ MapActivity f3132a;
        final /* synthetic */ MapActivity$$ViewInjector f3133b;

        ViewInjector(MapActivity$$ViewInjector mapActivity$$ViewInjector, MapActivity mapActivity) {
            this.f3133b = mapActivity$$ViewInjector;
            this.f3132a = mapActivity;
        }

        public boolean onLongClick(View view) {
            return this.f3132a.m5671k();
        }
    }

    /* renamed from: app.location.MapActivity$.ViewInjector.3 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ MapActivity f3134a;
        final /* synthetic */ MapActivity$$ViewInjector f3135b;

        ViewInjector(MapActivity$$ViewInjector mapActivity$$ViewInjector, MapActivity mapActivity) {
            this.f3135b = mapActivity$$ViewInjector;
            this.f3134a = mapActivity;
        }

        public void m5620a(View view) {
            this.f3134a.m5672l();
        }
    }

    public void inject(Finder finder, T t, Object obj) {
        View view = (View) finder.m7732a(obj, 2131755204, "field 'mapImageView', method 'imageViewOnClick', and method 'imageViewOnLongClick'");
        t.f3149o = (ImageView) finder.m7731a(view, 2131755204, "field 'mapImageView'");
        view.setOnClickListener(new ViewInjector(this, t));
        view.setOnLongClickListener(new ViewInjector(this, t));
        t.f3150p = (FrameLayout) finder.m7731a((View) finder.m7732a(obj, 2131755203, "field 'mapImageViewLayout'"), 2131755203, "field 'mapImageViewLayout'");
        ((View) finder.m7732a(obj, 2131755206, "method 'myLocationOnClick'")).setOnClickListener(new ViewInjector(this, t));
    }

    public void reset(T t) {
        t.f3149o = null;
        t.f3150p = null;
    }
}
