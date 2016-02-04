package app.signin;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import app.signin.VerificationFragment$$ViewInjector$VerificationFragment$.ViewInjector;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;
import butterknife.internal.DebouncingOnClickListener;

public class VerificationFragment$$ViewInjector<T extends VerificationFragment> implements Injector<T> {

    /* renamed from: app.signin.VerificationFragment$.ViewInjector.1 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ VerificationFragment f4497a;
        final /* synthetic */ VerificationFragment$$ViewInjector f4498b;

        ViewInjector(VerificationFragment$$ViewInjector verificationFragment$$ViewInjector, VerificationFragment verificationFragment) {
            this.f4498b = verificationFragment$$ViewInjector;
            this.f4497a = verificationFragment;
        }

        public void m6900a(View view) {
            this.f4497a.m6932b();
        }
    }

    /* renamed from: app.signin.VerificationFragment$.ViewInjector.2 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ VerificationFragment f4499a;
        final /* synthetic */ VerificationFragment$$ViewInjector f4500b;

        ViewInjector(VerificationFragment$$ViewInjector verificationFragment$$ViewInjector, VerificationFragment verificationFragment) {
            this.f4500b = verificationFragment$$ViewInjector;
            this.f4499a = verificationFragment;
        }

        public void m6901a(View view) {
            this.f4499a.m6933c();
        }
    }

    /* renamed from: app.signin.VerificationFragment$.ViewInjector.3 */
    class ViewInjector extends DebouncingOnClickListener {
        final /* synthetic */ VerificationFragment f4501a;
        final /* synthetic */ VerificationFragment$$ViewInjector f4502b;

        ViewInjector(VerificationFragment$$ViewInjector verificationFragment$$ViewInjector, VerificationFragment verificationFragment) {
            this.f4502b = verificationFragment$$ViewInjector;
            this.f4501a = verificationFragment;
        }

        public void m6902a(View view) {
            this.f4501a.m6934d();
        }
    }

    public void inject(Finder finder, T t, Object obj) {
        t.f4515a = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755438, "field 'topTextView'"), 2131755438, "field 'topTextView'");
        View view = (View) finder.m7732a(obj, 2131755413, "field 'nextBtn' and method 'onNextClicked'");
        t.f4516b = (Button) finder.m7731a(view, 2131755413, "field 'nextBtn'");
        view.setOnClickListener(new ViewInjector(this, t));
        view = (View) finder.m7732a(obj, 2131755445, "field 'resendBtn' and method 'onResendClicked'");
        t.f4517c = (Button) finder.m7731a(view, 2131755445, "field 'resendBtn'");
        view.setOnClickListener(new ViewInjector(this, t));
        view = (View) finder.m7732a(obj, 2131755444, "field 'requestCallBtn' and method 'onRequestCallClicked'");
        t.f4518d = (Button) finder.m7731a(view, 2131755444, "field 'requestCallBtn'");
        view.setOnClickListener(new ViewInjector(this, t));
        t.f4519e = (ProgressBar) finder.m7731a((View) finder.m7732a(obj, 2131755441, "field 'progressBar'"), 2131755441, "field 'progressBar'");
        t.f4520f = (EditText) finder.m7731a((View) finder.m7732a(obj, 2131755439, "field 'verificationCode'"), 2131755439, "field 'verificationCode'");
        t.f4521g = (ImageView) finder.m7731a((View) finder.m7732a(obj, 2131755440, "field 'checkedImageView'"), 2131755440, "field 'checkedImageView'");
        t.f4522h = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755442, "field 'countDownTextView'"), 2131755442, "field 'countDownTextView'");
        t.f4523i = (TextView) finder.m7731a((View) finder.m7732a(obj, 2131755443, "field 'checkBackLater'"), 2131755443, "field 'checkBackLater'");
        t.f4524j = (ScrollView) finder.m7731a((View) finder.m7732a(obj, 2131755424, "field 'scrollView'"), 2131755424, "field 'scrollView'");
    }

    public void reset(T t) {
        t.f4515a = null;
        t.f4516b = null;
        t.f4517c = null;
        t.f4518d = null;
        t.f4519e = null;
        t.f4520f = null;
        t.f4521g = null;
        t.f4522h = null;
        t.f4523i = null;
        t.f4524j = null;
    }
}
