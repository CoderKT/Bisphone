package android.support.v7.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v7.appcompat.C0057R;
import android.view.ViewConfiguration;

public class ActionBarPolicy {
    private Context f900a;

    public static ActionBarPolicy m2247a(Context context) {
        return new ActionBarPolicy(context);
    }

    private ActionBarPolicy(Context context) {
        this.f900a = context;
    }

    public int m2248a() {
        return this.f900a.getResources().getInteger(C0057R.integer.abc_max_action_buttons);
    }

    public boolean m2249b() {
        if (VERSION.SDK_INT < 19 && ViewConfigurationCompat.m1249b(ViewConfiguration.get(this.f900a))) {
            return false;
        }
        return true;
    }

    public int m2250c() {
        return this.f900a.getResources().getDisplayMetrics().widthPixels / 2;
    }

    public boolean m2251d() {
        if (this.f900a.getApplicationInfo().targetSdkVersion >= 16) {
            return this.f900a.getResources().getBoolean(C0057R.bool.abc_action_bar_embed_tabs);
        }
        return this.f900a.getResources().getBoolean(C0057R.bool.abc_action_bar_embed_tabs_pre_jb);
    }

    public int m2252e() {
        TypedArray obtainStyledAttributes = this.f900a.obtainStyledAttributes(null, C0057R.styleable.ActionBar, C0057R.attr.actionBarStyle, 0);
        int layoutDimension = obtainStyledAttributes.getLayoutDimension(C0057R.styleable.ActionBar_height, 0);
        Resources resources = this.f900a.getResources();
        if (!m2251d()) {
            layoutDimension = Math.min(layoutDimension, resources.getDimensionPixelSize(C0057R.dimen.abc_action_bar_stacked_max_height));
        }
        obtainStyledAttributes.recycle();
        return layoutDimension;
    }

    public boolean m2253f() {
        return this.f900a.getApplicationInfo().targetSdkVersion < 14;
    }

    public int m2254g() {
        return this.f900a.getResources().getDimensionPixelSize(C0057R.dimen.abc_action_bar_stacked_tab_max_width);
    }
}
