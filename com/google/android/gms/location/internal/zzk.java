package com.google.android.gms.location.internal;

import android.content.ContentProviderClient;
import android.content.Context;
import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.zzd;
import java.util.HashMap;
import java.util.Map;
import se.emilsjolander.stickylistheaders.C1128R;

public class zzk {
    private final zzp<zzi> f6273a;
    private final Context f6274b;
    private ContentProviderClient f6275c;
    private boolean f6276d;
    private Map<LocationListener, zzc> f6277e;
    private Map<Object, zza> f6278f;

    class zza extends com.google.android.gms.location.zzc.zza {
        private Handler f6270a;

        private void m9511a(int i, Object obj) {
            if (this.f6270a == null) {
                Log.e("LocationClientHelper", "Received a data in client after calling removeLocationUpdates.");
                return;
            }
            Message obtain = Message.obtain();
            obtain.what = i;
            obtain.obj = obj;
            this.f6270a.sendMessage(obtain);
        }

        public void m9512a(LocationAvailability locationAvailability) {
            m9511a(1, locationAvailability);
        }

        public void m9513a(LocationResult locationResult) {
            m9511a(0, locationResult);
        }
    }

    class zzb extends Handler {
        private final LocationListener f6271a;

        public zzb(LocationListener locationListener) {
            this.f6271a = locationListener;
        }

        public zzb(LocationListener locationListener, Looper looper) {
            super(looper);
            this.f6271a = locationListener;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    this.f6271a.m5634a(new Location((Location) message.obj));
                default:
                    Log.e("LocationClientHelper", "unknown message in LocationHandler.handleMessage");
            }
        }
    }

    class zzc extends com.google.android.gms.location.zzd.zza {
        private Handler f6272a;

        zzc(LocationListener locationListener, Looper looper) {
            if (looper == null) {
                zzx.m8723a(Looper.myLooper() != null, (Object) "Can't create handler inside thread that has not called Looper.prepare()");
            }
            this.f6272a = looper == null ? new zzb(locationListener) : new zzb(locationListener, looper);
        }

        public void m9516a(Location location) {
            if (this.f6272a == null) {
                Log.e("LocationClientHelper", "Received a location in client after calling removeLocationUpdates.");
                return;
            }
            Message obtain = Message.obtain();
            obtain.what = 1;
            obtain.obj = location;
            this.f6272a.sendMessage(obtain);
        }
    }

    public zzk(Context context, zzp<zzi> com_google_android_gms_location_internal_zzp_com_google_android_gms_location_internal_zzi) {
        this.f6275c = null;
        this.f6276d = false;
        this.f6277e = new HashMap();
        this.f6278f = new HashMap();
        this.f6274b = context;
        this.f6273a = com_google_android_gms_location_internal_zzp_com_google_android_gms_location_internal_zzi;
    }

    private zzc m9517a(LocationListener locationListener, Looper looper) {
        zzc com_google_android_gms_location_internal_zzk_zzc;
        synchronized (this.f6277e) {
            com_google_android_gms_location_internal_zzk_zzc = (zzc) this.f6277e.get(locationListener);
            if (com_google_android_gms_location_internal_zzk_zzc == null) {
                com_google_android_gms_location_internal_zzk_zzc = new zzc(locationListener, looper);
            }
            this.f6277e.put(locationListener, com_google_android_gms_location_internal_zzk_zzc);
        }
        return com_google_android_gms_location_internal_zzk_zzc;
    }

    public Location m9518a() {
        this.f6273a.m9416a();
        try {
            return ((zzi) this.f6273a.m9417c()).m9471b(this.f6274b.getPackageName());
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    public void m9519a(LocationRequest locationRequest, LocationListener locationListener, Looper looper, zzg com_google_android_gms_location_internal_zzg) {
        this.f6273a.m9416a();
        ((zzi) this.f6273a.m9417c()).m9465a(LocationRequestUpdateData.m9394a(LocationRequestInternal.m9391a(locationRequest), m9517a(locationListener, looper), com_google_android_gms_location_internal_zzg));
    }

    public void m9520a(boolean z) {
        this.f6273a.m9416a();
        ((zzi) this.f6273a.m9417c()).m9469a(z);
        this.f6276d = z;
    }

    public void m9521b() {
        try {
            synchronized (this.f6277e) {
                for (zzd com_google_android_gms_location_zzd : this.f6277e.values()) {
                    if (com_google_android_gms_location_zzd != null) {
                        ((zzi) this.f6273a.m9417c()).m9465a(LocationRequestUpdateData.m9396a(com_google_android_gms_location_zzd, null));
                    }
                }
                this.f6277e.clear();
                for (com.google.android.gms.location.zzc com_google_android_gms_location_zzc : this.f6278f.values()) {
                    if (com_google_android_gms_location_zzc != null) {
                        ((zzi) this.f6273a.m9417c()).m9465a(LocationRequestUpdateData.m9395a(com_google_android_gms_location_zzc, null));
                    }
                }
                this.f6278f.clear();
            }
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    public void m9522c() {
        if (this.f6276d) {
            try {
                m9520a(false);
            } catch (Throwable e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
