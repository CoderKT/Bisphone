package com.google.android.gms.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import com.google.android.gms.common.GoogleApiAvailability;

abstract class zzll extends BroadcastReceiver {
    protected Context f6057a;

    zzll() {
    }

    public static <T extends zzll> T m9166a(Context context, T t) {
        return m9167a(context, t, GoogleApiAvailability.m8308a());
    }

    public static <T extends zzll> T m9167a(Context context, T t, GoogleApiAvailability googleApiAvailability) {
        IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
        intentFilter.addDataScheme("package");
        context.registerReceiver(t, intentFilter);
        t.f6057a = context;
        if (googleApiAvailability.m8315a(context, "com.google.android.gms")) {
            return t;
        }
        t.m9168a();
        t.m9169b();
        return null;
    }

    protected abstract void m9168a();

    public synchronized void m9169b() {
        if (this.f6057a != null) {
            this.f6057a.unregisterReceiver(this);
        }
        this.f6057a = null;
    }

    public void onReceive(Context context, Intent intent) {
        Uri data = intent.getData();
        Object obj = null;
        if (data != null) {
            obj = data.getSchemeSpecificPart();
        }
        if ("com.google.android.gms".equals(obj)) {
            m9168a();
            m9169b();
        }
    }
}
