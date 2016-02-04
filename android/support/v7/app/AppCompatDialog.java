package android.support.v7.app;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.appcompat.C0057R;
import android.support.v7.view.ActionMode;
import android.support.v7.view.ActionMode.Callback;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class AppCompatDialog extends Dialog implements AppCompatCallback {
    private AppCompatDelegate f749a;

    public AppCompatDialog(Context context, int i) {
        super(context, m1996a(context, i));
        m1997a().m2008a(null);
    }

    protected void onCreate(Bundle bundle) {
        m1997a().m2015c();
        super.onCreate(bundle);
        m1997a().m2008a(bundle);
    }

    public void setContentView(int i) {
        m1997a().m2007a(i);
    }

    public void setContentView(View view) {
        m1997a().m2009a(view);
    }

    public void setContentView(View view, LayoutParams layoutParams) {
        m1997a().m2010a(view, layoutParams);
    }

    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        m1997a().m2011a(charSequence);
    }

    public void setTitle(int i) {
        super.setTitle(i);
        m1997a().m2011a(getContext().getString(i));
    }

    public void addContentView(View view, LayoutParams layoutParams) {
        m1997a().m2013b(view, layoutParams);
    }

    protected void onStop() {
        super.onStop();
        m1997a().m2006a();
    }

    public boolean m2000a(int i) {
        return m1997a().m2014b(i);
    }

    public void invalidateOptionsMenu() {
        m1997a().m2012b();
    }

    public AppCompatDelegate m1997a() {
        if (this.f749a == null) {
            this.f749a = AppCompatDelegate.m2004a((Dialog) this, (AppCompatCallback) this);
        }
        return this.f749a;
    }

    private static int m1996a(Context context, int i) {
        if (i != 0) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(C0057R.attr.dialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    public void m1999a(ActionMode actionMode) {
    }

    public void m2001b(ActionMode actionMode) {
    }

    public ActionMode m1998a(Callback callback) {
        return null;
    }
}
