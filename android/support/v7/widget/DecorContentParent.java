package android.support.v7.widget;

import android.support.v7.view.menu.MenuPresenter.Callback;
import android.view.Menu;
import android.view.Window;

public interface DecorContentParent {
    void m2561a(int i);

    void m2562a(Menu menu, Callback callback);

    boolean m2563d();

    boolean m2564e();

    boolean m2565f();

    boolean m2566g();

    boolean m2567h();

    void m2568i();

    void m2569j();

    void setWindowCallback(Window.Callback callback);

    void setWindowTitle(CharSequence charSequence);
}
