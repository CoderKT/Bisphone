package com.google.android.gms.common;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import app.C0110R;
import com.google.android.gms.C0648R;
import com.google.android.gms.common.internal.zzn;
import se.emilsjolander.stickylistheaders.C1128R;

public class GoogleApiAvailability {
    public static final int f5641a;
    private static final GoogleApiAvailability f5642b;

    static {
        f5641a = GooglePlayServicesUtil.f5644a;
        f5642b = new GoogleApiAvailability();
    }

    GoogleApiAvailability() {
    }

    public static GoogleApiAvailability m8308a() {
        return f5642b;
    }

    private String m8309b(Context context, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("gcore_");
        stringBuilder.append(f5641a);
        stringBuilder.append("-");
        if (!TextUtils.isEmpty(str)) {
            stringBuilder.append(str);
        }
        stringBuilder.append("-");
        if (context != null) {
            stringBuilder.append(context.getPackageName());
        }
        stringBuilder.append("-");
        if (context != null) {
            try {
                stringBuilder.append(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
            } catch (NameNotFoundException e) {
            }
        }
        return stringBuilder.toString();
    }

    public int m8310a(Context context) {
        int a = GooglePlayServicesUtil.m8318a(context);
        return GooglePlayServicesUtil.m8332b(context, a) ? 18 : a;
    }

    public Dialog m8311a(Activity activity, OnCancelListener onCancelListener) {
        View progressBar = new ProgressBar(activity, null, 16842874);
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(0);
        Builder builder = new Builder(activity);
        builder.setView(progressBar);
        String d = GooglePlayServicesUtil.m8335d(activity);
        builder.setMessage(activity.getResources().getString(C0648R.string.common_google_play_services_updating_text, new Object[]{d}));
        builder.setTitle(C0648R.string.common_google_play_services_updating_title);
        builder.setPositiveButton("", null);
        Dialog create = builder.create();
        GooglePlayServicesUtil.m8320a(activity, onCancelListener, "GooglePlayServicesUpdatingDialog", create);
        return create;
    }

    public Intent m8312a(Context context, int i, String str) {
        switch (i) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                return zzn.m8614a("com.google.android.gms", m8309b(context, str));
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                return zzn.m8613a("com.google.android.gms");
            case C0110R.styleable.Theme_dialogTheme /*42*/:
                return zzn.m8612a();
            default:
                return null;
        }
    }

    public final boolean m8313a(int i) {
        return GooglePlayServicesUtil.m8331b(i);
    }

    public boolean m8314a(Context context, int i) {
        return GooglePlayServicesUtil.m8332b(context, i);
    }

    public boolean m8315a(Context context, String str) {
        return GooglePlayServicesUtil.m8325a(context, str);
    }

    @Deprecated
    public Intent m8316b(int i) {
        return m8312a(null, i, null);
    }

    public void m8317b(Context context) {
        GooglePlayServicesUtil.m8330b(context);
    }
}
