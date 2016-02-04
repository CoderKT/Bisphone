package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPresenter.Callback;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.Window;

public interface DecorToolbar {
    ViewPropertyAnimatorCompat m2895a(int i, long j);

    ViewGroup m2896a();

    void m2897a(int i);

    void m2898a(Drawable drawable);

    void m2899a(Callback callback, MenuBuilder.Callback callback2);

    void m2900a(ScrollingTabContainerView scrollingTabContainerView);

    void m2901a(Menu menu, Callback callback);

    void m2902a(Window.Callback callback);

    void m2903a(CharSequence charSequence);

    void m2904a(boolean z);

    Context m2905b();

    void m2906b(int i);

    void m2907b(boolean z);

    void m2908c(int i);

    boolean m2909c();

    void m2910d();

    CharSequence m2911e();

    void m2912f();

    void m2913g();

    boolean m2914h();

    boolean m2915i();

    boolean m2916j();

    boolean m2917k();

    boolean m2918l();

    void m2919m();

    void m2920n();

    int m2921o();

    int m2922p();

    Menu m2923q();
}
