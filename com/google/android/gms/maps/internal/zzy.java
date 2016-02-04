package com.google.android.gms.maps.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.maps.internal.zzc.zza;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import se.emilsjolander.stickylistheaders.C1128R;

public class zzy {
    private static Context f6367a;
    private static zzc f6368b;

    public static zzc m10069a(Context context) {
        zzx.m8718a((Object) context);
        if (f6368b != null) {
            return f6368b;
        }
        m10074b(context);
        f6368b = m10075c(context);
        try {
            f6368b.m10003a(zze.m9023a(m10076d(context).getResources()), GooglePlayServicesUtil.f5644a);
            return f6368b;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    private static <T> T m10070a(Class<?> cls) {
        try {
            return cls.newInstance();
        } catch (InstantiationException e) {
            throw new IllegalStateException("Unable to instantiate the dynamic class " + cls.getName());
        } catch (IllegalAccessException e2) {
            throw new IllegalStateException("Unable to call the default constructor of " + cls.getName());
        }
    }

    private static <T> T m10071a(ClassLoader classLoader, String str) {
        try {
            return m10070a(((ClassLoader) zzx.m8718a((Object) classLoader)).loadClass(str));
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Unable to find dynamic class " + str);
        }
    }

    public static boolean m10072a() {
        return false;
    }

    private static Class<?> m10073b() {
        try {
            return Class.forName("com.google.android.gms.maps.internal.CreatorImpl");
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private static void m10074b(Context context) {
        int a = GooglePlayServicesUtil.m8318a(context);
        switch (a) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
            default:
                throw new GooglePlayServicesNotAvailableException(a);
        }
    }

    private static zzc m10075c(Context context) {
        if (m10072a()) {
            Log.i(zzy.class.getSimpleName(), "Making Creator statically");
            return (zzc) m10070a(m10073b());
        }
        Log.i(zzy.class.getSimpleName(), "Making Creator dynamically");
        return zza.m10015a((IBinder) m10071a(m10076d(context).getClassLoader(), "com.google.android.gms.maps.internal.CreatorImpl"));
    }

    private static Context m10076d(Context context) {
        if (f6367a == null) {
            if (m10072a()) {
                f6367a = context.getApplicationContext();
            } else {
                f6367a = GooglePlayServicesUtil.m8334c(context);
            }
        }
        return f6367a;
    }
}
