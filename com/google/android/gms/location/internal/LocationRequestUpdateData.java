package com.google.android.gms.location.internal;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.location.zzc;
import com.google.android.gms.location.zzd;
import com.google.android.gms.location.zzd.zza;

public class LocationRequestUpdateData implements SafeParcelable {
    public static final zzn CREATOR;
    int f6242a;
    LocationRequestInternal f6243b;
    zzd f6244c;
    PendingIntent f6245d;
    zzc f6246e;
    zzg f6247f;
    private final int f6248g;

    static {
        CREATOR = new zzn();
    }

    LocationRequestUpdateData(int i, int i2, LocationRequestInternal locationRequestInternal, IBinder iBinder, PendingIntent pendingIntent, IBinder iBinder2, IBinder iBinder3) {
        zzg com_google_android_gms_location_internal_zzg = null;
        this.f6248g = i;
        this.f6242a = i2;
        this.f6243b = locationRequestInternal;
        this.f6244c = iBinder == null ? null : zza.m9515a(iBinder);
        this.f6245d = pendingIntent;
        this.f6246e = iBinder2 == null ? null : zzc.zza.m9510a(iBinder2);
        if (iBinder3 != null) {
            com_google_android_gms_location_internal_zzg = zzg.zza.m9431a(iBinder3);
        }
        this.f6247f = com_google_android_gms_location_internal_zzg;
    }

    public static LocationRequestUpdateData m9394a(LocationRequestInternal locationRequestInternal, zzd com_google_android_gms_location_zzd, zzg com_google_android_gms_location_internal_zzg) {
        return new LocationRequestUpdateData(1, 1, locationRequestInternal, com_google_android_gms_location_zzd.asBinder(), null, null, com_google_android_gms_location_internal_zzg != null ? com_google_android_gms_location_internal_zzg.asBinder() : null);
    }

    public static LocationRequestUpdateData m9395a(zzc com_google_android_gms_location_zzc, zzg com_google_android_gms_location_internal_zzg) {
        return new LocationRequestUpdateData(1, 2, null, null, null, com_google_android_gms_location_zzc.asBinder(), com_google_android_gms_location_internal_zzg != null ? com_google_android_gms_location_internal_zzg.asBinder() : null);
    }

    public static LocationRequestUpdateData m9396a(zzd com_google_android_gms_location_zzd, zzg com_google_android_gms_location_internal_zzg) {
        return new LocationRequestUpdateData(1, 2, null, com_google_android_gms_location_zzd.asBinder(), null, null, com_google_android_gms_location_internal_zzg != null ? com_google_android_gms_location_internal_zzg.asBinder() : null);
    }

    int m9397a() {
        return this.f6248g;
    }

    IBinder m9398b() {
        return this.f6244c == null ? null : this.f6244c.asBinder();
    }

    IBinder m9399c() {
        return this.f6246e == null ? null : this.f6246e.asBinder();
    }

    IBinder m9400d() {
        return this.f6247f == null ? null : this.f6247f.asBinder();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzn.m9530a(this, parcel, i);
    }
}
