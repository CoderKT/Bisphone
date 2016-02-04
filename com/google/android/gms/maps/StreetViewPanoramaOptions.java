package com.google.android.gms.maps;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.zza;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

public final class StreetViewPanoramaOptions implements SafeParcelable {
    public static final zzb CREATOR;
    private final int f6324a;
    private StreetViewPanoramaCamera f6325b;
    private String f6326c;
    private LatLng f6327d;
    private Integer f6328e;
    private Boolean f6329f;
    private Boolean f6330g;
    private Boolean f6331h;
    private Boolean f6332i;
    private Boolean f6333j;

    static {
        CREATOR = new zzb();
    }

    public StreetViewPanoramaOptions() {
        this.f6329f = Boolean.valueOf(true);
        this.f6330g = Boolean.valueOf(true);
        this.f6331h = Boolean.valueOf(true);
        this.f6332i = Boolean.valueOf(true);
        this.f6324a = 1;
    }

    StreetViewPanoramaOptions(int i, StreetViewPanoramaCamera streetViewPanoramaCamera, String str, LatLng latLng, Integer num, byte b, byte b2, byte b3, byte b4, byte b5) {
        this.f6329f = Boolean.valueOf(true);
        this.f6330g = Boolean.valueOf(true);
        this.f6331h = Boolean.valueOf(true);
        this.f6332i = Boolean.valueOf(true);
        this.f6324a = i;
        this.f6325b = streetViewPanoramaCamera;
        this.f6327d = latLng;
        this.f6328e = num;
        this.f6326c = str;
        this.f6329f = zza.m9996a(b);
        this.f6330g = zza.m9996a(b2);
        this.f6331h = zza.m9996a(b3);
        this.f6332i = zza.m9996a(b4);
        this.f6333j = zza.m9996a(b5);
    }

    int m9661a() {
        return this.f6324a;
    }

    byte m9662b() {
        return zza.m9995a(this.f6329f);
    }

    byte m9663c() {
        return zza.m9995a(this.f6330g);
    }

    byte m9664d() {
        return zza.m9995a(this.f6331h);
    }

    public int describeContents() {
        return 0;
    }

    byte m9665e() {
        return zza.m9995a(this.f6332i);
    }

    byte m9666f() {
        return zza.m9995a(this.f6333j);
    }

    public StreetViewPanoramaCamera m9667g() {
        return this.f6325b;
    }

    public LatLng m9668h() {
        return this.f6327d;
    }

    public Integer m9669i() {
        return this.f6328e;
    }

    public String m9670j() {
        return this.f6326c;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzb.m10478a(this, parcel, i);
    }
}
