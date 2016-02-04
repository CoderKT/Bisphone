package android.support.v7.app;

import android.app.Dialog;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;

public abstract class AppCompatDelegate {
    public abstract void m2006a();

    public abstract void m2007a(int i);

    public abstract void m2008a(Bundle bundle);

    public abstract void m2009a(View view);

    public abstract void m2010a(View view, LayoutParams layoutParams);

    public abstract void m2011a(CharSequence charSequence);

    public abstract void m2012b();

    public abstract void m2013b(View view, LayoutParams layoutParams);

    public abstract boolean m2014b(int i);

    public abstract void m2015c();

    public static AppCompatDelegate m2004a(Dialog dialog, AppCompatCallback appCompatCallback) {
        return m2005a(dialog.getContext(), dialog.getWindow(), appCompatCallback);
    }

    private static AppCompatDelegate m2005a(Context context, Window window, AppCompatCallback appCompatCallback) {
        int i = VERSION.SDK_INT;
        if (i >= 23) {
            return new AppCompatDelegateImplV23(context, window, appCompatCallback);
        }
        if (i >= 14) {
            return new AppCompatDelegateImplV14(context, window, appCompatCallback);
        }
        if (i >= 11) {
            return new AppCompatDelegateImplV11(context, window, appCompatCallback);
        }
        return new AppCompatDelegateImplV7(context, window, appCompatCallback);
    }

    AppCompatDelegate() {
    }
}
