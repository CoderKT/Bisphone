package app.view;

import android.view.View;
import android.widget.ImageView;
import app.view.DialogImage$$ViewInjector$DialogImage$.ViewInjector;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;
import butterknife.internal.DebouncingOnClickListener;

public class DialogImage$$ViewInjector<T extends DialogImage> implements Injector<T> {

    /* renamed from: app.view.DialogImage$.ViewInjector.1 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ DialogImage f4653a;
        final /* synthetic */ DialogImage$$ViewInjector f4654b;

        ViewInjector(DialogImage$$ViewInjector dialogImage$$ViewInjector, DialogImage dialogImage) {
            this.f4654b = dialogImage$$ViewInjector;
            this.f4653a = dialogImage;
        }

        public void m7115a(View view) {
            this.f4653a.m7116a();
        }
    }

    public void inject(Finder finder, T t, Object obj) {
        View view = (View) finder.m7732a(obj, 2131755319, "field 'dialogImageView' and method 'onImageClicked'");
        t.f4655a = (ImageView) finder.m7731a(view, 2131755319, "field 'dialogImageView'");
        view.setOnClickListener(new ViewInjector(this, t));
    }

    public void reset(T t) {
        t.f4655a = null;
    }
}
