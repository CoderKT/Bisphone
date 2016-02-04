package com.google.android.gms.common;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.AppOpsManager;
import android.app.Dialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller.SessionInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Bundle;
import android.os.UserManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import com.google.android.gms.C0648R;
import com.google.android.gms.common.internal.zzd;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzh;
import com.google.android.gms.internal.zzml;
import com.google.android.gms.internal.zzmx;
import java.util.concurrent.atomic.AtomicBoolean;
import se.emilsjolander.stickylistheaders.C1128R;

public final class GooglePlayServicesUtil {
    @Deprecated
    public static final int f5644a;
    public static boolean f5645b;
    public static boolean f5646c;
    static final AtomicBoolean f5647d;
    private static int f5648e;
    private static final Object f5649f;
    private static String f5650g;
    private static Integer f5651h;
    private static final AtomicBoolean f5652i;

    static {
        f5644a = m8328b();
        f5645b = false;
        f5646c = false;
        f5648e = -1;
        f5649f = new Object();
        f5650g = null;
        f5651h = null;
        f5647d = new AtomicBoolean();
        f5652i = new AtomicBoolean();
    }

    private GooglePlayServicesUtil() {
    }

    @Deprecated
    public static int m8318a(Context context) {
        if (zzd.f5743a) {
            return 0;
        }
        PackageManager packageManager = context.getPackageManager();
        try {
            context.getResources().getString(C0648R.string.common_google_play_services_unknown_issue);
        } catch (Throwable th) {
            Log.e("GooglePlayServicesUtil", "The Google Play services resources were not found. Check your project configuration to ensure that the resources are included.");
        }
        if (!"com.google.android.gms".equals(context.getPackageName())) {
            m8337f(context);
        }
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo("com.google.android.gms", 64);
            zzd a = zzd.m8977a();
            if (!zzml.m9298b(packageInfo.versionCode) && !zzml.m9297a(context)) {
                try {
                    if (a.m8979a(packageManager.getPackageInfo("com.android.vending", 8256), zzbz.f5908a) == null) {
                        Log.w("GooglePlayServicesUtil", "Google Play Store signature invalid.");
                        return 9;
                    }
                    if (a.m8979a(packageInfo, a.m8979a(packageManager.getPackageInfo("com.android.vending", 8256), zzbz.f5908a)) == null) {
                        Log.w("GooglePlayServicesUtil", "Google Play services signature invalid.");
                        return 9;
                    }
                } catch (NameNotFoundException e) {
                    Log.w("GooglePlayServicesUtil", "Google Play Store is neither installed nor updating.");
                    return 9;
                }
            } else if (a.m8979a(packageInfo, zzbz.f5908a) == null) {
                Log.w("GooglePlayServicesUtil", "Google Play services signature invalid.");
                return 9;
            }
            if (zzml.m9296a(packageInfo.versionCode) < zzml.m9296a(f5644a)) {
                Log.w("GooglePlayServicesUtil", "Google Play services out of date.  Requires " + f5644a + " but found " + packageInfo.versionCode);
                return 2;
            }
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            if (applicationInfo == null) {
                try {
                    applicationInfo = packageManager.getApplicationInfo("com.google.android.gms", 0);
                } catch (Throwable e2) {
                    Log.wtf("GooglePlayServicesUtil", "Google Play services missing when getting application info.", e2);
                    return 1;
                }
            }
            return !applicationInfo.enabled ? 3 : 0;
        } catch (NameNotFoundException e3) {
            Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
            return 1;
        }
    }

    @Deprecated
    public static Intent m8319a(int i) {
        return GoogleApiAvailability.m8308a().m8312a(null, i, null);
    }

    public static void m8320a(Activity activity, OnCancelListener onCancelListener, String str, Dialog dialog) {
        boolean z;
        try {
            z = activity instanceof FragmentActivity;
        } catch (NoClassDefFoundError e) {
            z = false;
        }
        if (z) {
            SupportErrorDialogFragment.m8338a(dialog, onCancelListener).m8339a(((FragmentActivity) activity).getSupportFragmentManager(), str);
        } else if (zzmx.m9304a()) {
            ErrorDialogFragment.m8307a(dialog, onCancelListener).show(activity.getFragmentManager(), str);
        } else {
            throw new RuntimeException("This Activity does not support Fragments.");
        }
    }

    public static boolean m8321a() {
        return f5645b ? f5646c : "user".equals(Build.TYPE);
    }

    public static boolean m8322a(int i, Activity activity, Fragment fragment, int i2, OnCancelListener onCancelListener) {
        Dialog b = m8329b(i, activity, fragment, i2, onCancelListener);
        if (b == null) {
            return false;
        }
        m8320a(activity, onCancelListener, "GooglePlayServicesErrorDialog", b);
        return true;
    }

    public static boolean m8323a(Context context, int i) {
        return m8324a(context, i, "com.google.android.gms") && m8327a(context.getPackageManager(), "com.google.android.gms");
    }

    public static boolean m8324a(Context context, int i, String str) {
        if (zzmx.m9309e()) {
            try {
                ((AppOpsManager) context.getSystemService("appops")).checkPackage(i, str);
                return true;
            } catch (SecurityException e) {
                return false;
            }
        }
        String[] packagesForUid = context.getPackageManager().getPackagesForUid(i);
        if (str == null || packagesForUid == null) {
            return false;
        }
        for (Object equals : packagesForUid) {
            if (str.equals(equals)) {
                return true;
            }
        }
        return false;
    }

    static boolean m8325a(Context context, String str) {
        if (zzmx.m9310f()) {
            for (SessionInfo appPackageName : context.getPackageManager().getPackageInstaller().getAllSessions()) {
                if (str.equals(appPackageName.getAppPackageName())) {
                    return true;
                }
            }
        }
        if (m8336e(context)) {
            return false;
        }
        try {
            return context.getPackageManager().getApplicationInfo(str, 8192).enabled;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static boolean m8326a(PackageManager packageManager) {
        boolean z = true;
        synchronized (f5649f) {
            if (f5648e == -1) {
                try {
                    PackageInfo packageInfo = packageManager.getPackageInfo("com.google.android.gms", 64);
                    if (zzd.m8977a().m8979a(packageInfo, zzc.f5959b[1]) != null) {
                        f5648e = 1;
                    } else {
                        f5648e = 0;
                    }
                } catch (NameNotFoundException e) {
                    f5648e = 0;
                }
            }
            if (f5648e == 0) {
                z = false;
            }
        }
        return z;
    }

    @Deprecated
    public static boolean m8327a(PackageManager packageManager, String str) {
        return zzd.m8977a().m8981a(packageManager, str);
    }

    private static int m8328b() {
        return 8115000;
    }

    private static Dialog m8329b(int i, Activity activity, Fragment fragment, int i2, OnCancelListener onCancelListener) {
        Builder builder = null;
        if (i == 0) {
            return null;
        }
        if (zzml.m9297a((Context) activity) && i == 2) {
            i = 42;
        }
        if (zzmx.m9307c()) {
            TypedValue typedValue = new TypedValue();
            activity.getTheme().resolveAttribute(16843529, typedValue, true);
            if ("Theme.Dialog.Alert".equals(activity.getResources().getResourceEntryName(typedValue.resourceId))) {
                builder = new Builder(activity, 5);
            }
        }
        if (builder == null) {
            builder = new Builder(activity);
        }
        builder.setMessage(zzg.m8547a(activity, i, m8335d(activity)));
        if (onCancelListener != null) {
            builder.setOnCancelListener(onCancelListener);
        }
        Intent a = GoogleApiAvailability.m8308a().m8312a(activity, i, "d");
        OnClickListener com_google_android_gms_common_internal_zzh = fragment == null ? new zzh(activity, a, i2) : new zzh(fragment, a, i2);
        CharSequence b = zzg.m8548b(activity, i);
        if (b != null) {
            builder.setPositiveButton(b, com_google_android_gms_common_internal_zzh);
        }
        CharSequence a2 = zzg.m8546a(activity, i);
        if (a2 != null) {
            builder.setTitle(a2);
        }
        return builder.create();
    }

    @Deprecated
    public static void m8330b(Context context) {
        if (!f5647d.getAndSet(true)) {
            try {
                ((NotificationManager) context.getSystemService("notification")).cancel(10436);
            } catch (SecurityException e) {
            }
        }
    }

    @Deprecated
    public static boolean m8331b(int i) {
        switch (i) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
            case C1128R.styleable.StickyListHeadersListView_android_listSelector /*9*/:
                return true;
            default:
                return false;
        }
    }

    @Deprecated
    public static boolean m8332b(Context context, int i) {
        return i == 18 ? true : i == 1 ? m8325a(context, "com.google.android.gms") : false;
    }

    public static boolean m8333b(PackageManager packageManager) {
        return m8326a(packageManager) || !m8321a();
    }

    public static Context m8334c(Context context) {
        try {
            return context.createPackageContext("com.google.android.gms", 3);
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    public static String m8335d(Context context) {
        Object obj = context.getApplicationInfo().name;
        if (!TextUtils.isEmpty(obj)) {
            return obj;
        }
        ApplicationInfo applicationInfo;
        String packageName = context.getPackageName();
        PackageManager packageManager = context.getApplicationContext().getPackageManager();
        try {
            applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
        } catch (NameNotFoundException e) {
            applicationInfo = null;
        }
        return applicationInfo != null ? packageManager.getApplicationLabel(applicationInfo).toString() : packageName;
    }

    public static boolean m8336e(Context context) {
        if (zzmx.m9308d()) {
            Bundle applicationRestrictions = ((UserManager) context.getSystemService("user")).getApplicationRestrictions(context.getPackageName());
            if (applicationRestrictions != null && "true".equals(applicationRestrictions.getString("restricted_profile"))) {
                return true;
            }
        }
        return false;
    }

    private static void m8337f(Context context) {
        if (!f5652i.get()) {
            Integer num;
            synchronized (f5649f) {
                if (f5650g == null) {
                    f5650g = context.getPackageName();
                    try {
                        Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
                        if (bundle != null) {
                            f5651h = Integer.valueOf(bundle.getInt("com.google.android.gms.version"));
                        } else {
                            f5651h = null;
                        }
                    } catch (Throwable e) {
                        Log.wtf("GooglePlayServicesUtil", "This should never happen.", e);
                    }
                } else if (!f5650g.equals(context.getPackageName())) {
                    throw new IllegalArgumentException("isGooglePlayServicesAvailable should only be called with Context from your application's package. A previous call used package '" + f5650g + "' and this call used package '" + context.getPackageName() + "'.");
                }
                num = f5651h;
            }
            if (num == null) {
                throw new IllegalStateException("A required meta-data tag in your app's AndroidManifest.xml does not exist.  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
            } else if (num.intValue() != f5644a) {
                throw new IllegalStateException("The meta-data tag in your app's AndroidManifest.xml does not have the right value.  Expected " + f5644a + " but" + " found " + num + ".  You must have the" + " following declaration within the <application> element: " + "    <meta-data android:name=\"" + "com.google.android.gms.version" + "\" android:value=\"@integer/google_play_services_version\" />");
            }
        }
    }
}
