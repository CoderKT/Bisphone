package com.google.android.gms.common.stats;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Debug;
import android.os.Parcelable;
import android.os.Process;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.gms.common.internal.zzd;
import com.google.android.gms.common.stats.zzc.zza;
import com.google.android.gms.internal.zzmm;
import com.google.android.gms.internal.zzmy;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class zzb {
    private static final Object f5825a;
    private static zzb f5826b;
    private static Integer f5827h;
    private final List<String> f5828c;
    private final List<String> f5829d;
    private final List<String> f5830e;
    private final List<String> f5831f;
    private zze f5832g;
    private zze f5833i;

    static {
        f5825a = new Object();
    }

    private zzb() {
        if (m8761c() == zzd.f5842b) {
            this.f5828c = Collections.EMPTY_LIST;
            this.f5829d = Collections.EMPTY_LIST;
            this.f5830e = Collections.EMPTY_LIST;
            this.f5831f = Collections.EMPTY_LIST;
            return;
        }
        String str = (String) zza.f5835b.m9244c();
        this.f5828c = str == null ? Collections.EMPTY_LIST : Arrays.asList(str.split(","));
        str = (String) zza.f5836c.m9244c();
        this.f5829d = str == null ? Collections.EMPTY_LIST : Arrays.asList(str.split(","));
        str = (String) zza.f5837d.m9244c();
        this.f5830e = str == null ? Collections.EMPTY_LIST : Arrays.asList(str.split(","));
        str = (String) zza.f5838e.m9244c();
        this.f5831f = str == null ? Collections.EMPTY_LIST : Arrays.asList(str.split(","));
        this.f5832g = new zze(1024, ((Long) zza.f5839f.m9244c()).longValue());
        this.f5833i = new zze(1024, ((Long) zza.f5839f.m9244c()).longValue());
    }

    public static zzb m8753a() {
        synchronized (f5825a) {
            if (f5826b == null) {
                f5826b = new zzb();
            }
        }
        return f5826b;
    }

    private String m8754a(ServiceConnection serviceConnection) {
        return String.valueOf((((long) Process.myPid()) << 32) | ((long) System.identityHashCode(serviceConnection)));
    }

    private void m8755a(Context context, String str, int i, String str2, String str3, String str4, String str5) {
        Parcelable connectionEvent;
        long currentTimeMillis = System.currentTimeMillis();
        String str6 = null;
        if (!((m8761c() & zzd.f5846f) == 0 || i == 13)) {
            str6 = zzmy.m9311a(3, 5);
        }
        long j = 0;
        if ((m8761c() & zzd.f5848h) != 0) {
            j = Debug.getNativeHeapAllocatedSize();
        }
        if (i == 1 || i == 4 || i == 14) {
            connectionEvent = new ConnectionEvent(currentTimeMillis, i, null, null, null, null, str6, str, SystemClock.elapsedRealtime(), j);
        } else {
            connectionEvent = new ConnectionEvent(currentTimeMillis, i, str2, str3, str4, str5, str6, str, SystemClock.elapsedRealtime(), j);
        }
        context.startService(new Intent().setComponent(zzd.f5841a).putExtra("com.google.android.gms.common.stats.EXTRA_LOG_EVENT", connectionEvent));
    }

    private void m8756a(Context context, String str, String str2, Intent intent, int i) {
        String str3 = null;
        if (m8760b() && this.f5832g != null) {
            String str4;
            String str5;
            if (i != 4 && i != 1) {
                ServiceInfo b = m8759b(context, intent);
                if (b == null) {
                    Log.w("ConnectionTracker", String.format("Client %s made an invalid request %s", new Object[]{str2, intent.toUri(0)}));
                    return;
                }
                str4 = b.processName;
                str5 = b.name;
                str3 = zzmy.m9312a(context);
                if (m8758a(str3, str2, str4, str5)) {
                    this.f5832g.m8767a(str);
                } else {
                    return;
                }
            } else if (this.f5832g.m8768b(str)) {
                str5 = null;
                str4 = null;
            } else {
                return;
            }
            m8755a(context, str, i, str3, str2, str4, str5);
        }
    }

    private boolean m8757a(Context context, Intent intent) {
        ComponentName component = intent.getComponent();
        return (component == null || (zzd.f5743a && "com.google.android.gms".equals(component.getPackageName()))) ? false : zzmm.m9300a(context, component.getPackageName());
    }

    private boolean m8758a(String str, String str2, String str3, String str4) {
        return (this.f5828c.contains(str) || this.f5829d.contains(str2) || this.f5830e.contains(str3) || this.f5831f.contains(str4) || (str3.equals(str) && (m8761c() & zzd.f5847g) != 0)) ? false : true;
    }

    private static ServiceInfo m8759b(Context context, Intent intent) {
        List queryIntentServices = context.getPackageManager().queryIntentServices(intent, 128);
        if (queryIntentServices == null || queryIntentServices.size() == 0) {
            Log.w("ConnectionTracker", String.format("There are no handler of this intent: %s\n Stack trace: %s", new Object[]{intent.toUri(0), zzmy.m9311a(3, 20)}));
            return null;
        }
        if (queryIntentServices.size() > 1) {
            Log.w("ConnectionTracker", String.format("Multiple handlers found for this intent: %s\n Stack trace: %s", new Object[]{intent.toUri(0), zzmy.m9311a(3, 20)}));
            Iterator it = queryIntentServices.iterator();
            if (it.hasNext()) {
                Log.w("ConnectionTracker", ((ResolveInfo) it.next()).serviceInfo.name);
                return null;
            }
        }
        return ((ResolveInfo) queryIntentServices.get(0)).serviceInfo;
    }

    private boolean m8760b() {
        return zzd.f5743a && m8761c() != zzd.f5842b;
    }

    private static int m8761c() {
        if (f5827h == null) {
            try {
                f5827h = Integer.valueOf(zzmm.m9299a() ? ((Integer) zza.f5834a.m9244c()).intValue() : zzd.f5842b);
            } catch (SecurityException e) {
                f5827h = Integer.valueOf(zzd.f5842b);
            }
        }
        return f5827h.intValue();
    }

    public void m8762a(Context context, ServiceConnection serviceConnection) {
        context.unbindService(serviceConnection);
        m8756a(context, m8754a(serviceConnection), null, null, 1);
    }

    public void m8763a(Context context, ServiceConnection serviceConnection, String str, Intent intent) {
        m8756a(context, m8754a(serviceConnection), str, intent, 3);
    }

    public boolean m8764a(Context context, String str, Intent intent, ServiceConnection serviceConnection, int i) {
        if (m8757a(context, intent)) {
            Log.w("ConnectionTracker", "Attempted to bind to a service in a STOPPED package.");
            return false;
        }
        boolean bindService = context.bindService(intent, serviceConnection, i);
        if (bindService) {
            m8756a(context, m8754a(serviceConnection), str, intent, 2);
        }
        return bindService;
    }

    public void m8765b(Context context, ServiceConnection serviceConnection) {
        m8756a(context, m8754a(serviceConnection), null, null, 4);
    }
}
