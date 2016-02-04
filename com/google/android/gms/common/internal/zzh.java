package com.google.android.gms.common.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;

public class zzh implements OnClickListener {
    private final Activity f5756a;
    private final Fragment f5757b;
    private final Intent f5758c;
    private final int f5759d;

    public zzh(Activity activity, Intent intent, int i) {
        this.f5756a = activity;
        this.f5757b = null;
        this.f5758c = intent;
        this.f5759d = i;
    }

    public zzh(Fragment fragment, Intent intent, int i) {
        this.f5756a = null;
        this.f5757b = fragment;
        this.f5758c = intent;
        this.f5759d = i;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        try {
            if (this.f5758c != null && this.f5757b != null) {
                this.f5757b.m193a(this.f5758c, this.f5759d);
            } else if (this.f5758c != null) {
                this.f5756a.startActivityForResult(this.f5758c, this.f5759d);
            }
            dialogInterface.dismiss();
        } catch (ActivityNotFoundException e) {
            Log.e("SettingsRedirect", "Can't redirect to app settings for Google Play services");
        }
    }
}
