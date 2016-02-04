package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.dynamic.zzd.zza;

public final class MarkerOptions implements SafeParcelable {
    public static final zzf CREATOR;
    private final int f6407a;
    private LatLng f6408b;
    private String f6409c;
    private String f6410d;
    private BitmapDescriptor f6411e;
    private float f6412f;
    private float f6413g;
    private boolean f6414h;
    private boolean f6415i;
    private boolean f6416j;
    private float f6417k;
    private float f6418l;
    private float f6419m;
    private float f6420n;

    static {
        CREATOR = new zzf();
    }

    public MarkerOptions() {
        this.f6412f = 0.5f;
        this.f6413g = 1.0f;
        this.f6415i = true;
        this.f6416j = false;
        this.f6417k = 0.0f;
        this.f6418l = 0.5f;
        this.f6419m = 0.0f;
        this.f6420n = 1.0f;
        this.f6407a = 1;
    }

    MarkerOptions(int i, LatLng latLng, String str, String str2, IBinder iBinder, float f, float f2, boolean z, boolean z2, boolean z3, float f3, float f4, float f5, float f6) {
        this.f6412f = 0.5f;
        this.f6413g = 1.0f;
        this.f6415i = true;
        this.f6416j = false;
        this.f6417k = 0.0f;
        this.f6418l = 0.5f;
        this.f6419m = 0.0f;
        this.f6420n = 1.0f;
        this.f6407a = i;
        this.f6408b = latLng;
        this.f6409c = str;
        this.f6410d = str2;
        this.f6411e = iBinder == null ? null : new BitmapDescriptor(zza.m9022a(iBinder));
        this.f6412f = f;
        this.f6413g = f2;
        this.f6414h = z;
        this.f6415i = z2;
        this.f6416j = z3;
        this.f6417k = f3;
        this.f6418l = f4;
        this.f6419m = f5;
        this.f6420n = f6;
    }

    int m10117a() {
        return this.f6407a;
    }

    public MarkerOptions m10118a(BitmapDescriptor bitmapDescriptor) {
        this.f6411e = bitmapDescriptor;
        return this;
    }

    public MarkerOptions m10119a(LatLng latLng) {
        this.f6408b = latLng;
        return this;
    }

    public MarkerOptions m10120a(boolean z) {
        this.f6414h = z;
        return this;
    }

    IBinder m10121b() {
        return this.f6411e == null ? null : this.f6411e.m10077a().asBinder();
    }

    public LatLng m10122c() {
        return this.f6408b;
    }

    public String m10123d() {
        return this.f6409c;
    }

    public int describeContents() {
        return 0;
    }

    public String m10124e() {
        return this.f6410d;
    }

    public float m10125f() {
        return this.f6412f;
    }

    public float m10126g() {
        return this.f6413g;
    }

    public boolean m10127h() {
        return this.f6414h;
    }

    public boolean m10128i() {
        return this.f6415i;
    }

    public boolean m10129j() {
        return this.f6416j;
    }

    public float m10130k() {
        return this.f6417k;
    }

    public float m10131l() {
        return this.f6418l;
    }

    public float m10132m() {
        return this.f6419m;
    }

    public float m10133n() {
        return this.f6420n;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzf.m10442a(this, parcel, i);
    }
}
