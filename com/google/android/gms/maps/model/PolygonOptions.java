package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.List;

public final class PolygonOptions implements SafeParcelable {
    public static final zzh CREATOR;
    private final int f6425a;
    private final List<LatLng> f6426b;
    private final List<List<LatLng>> f6427c;
    private float f6428d;
    private int f6429e;
    private int f6430f;
    private float f6431g;
    private boolean f6432h;
    private boolean f6433i;

    static {
        CREATOR = new zzh();
    }

    public PolygonOptions() {
        this.f6428d = 10.0f;
        this.f6429e = -16777216;
        this.f6430f = 0;
        this.f6431g = 0.0f;
        this.f6432h = true;
        this.f6433i = false;
        this.f6425a = 1;
        this.f6426b = new ArrayList();
        this.f6427c = new ArrayList();
    }

    PolygonOptions(int i, List<LatLng> list, List list2, float f, int i2, int i3, float f2, boolean z, boolean z2) {
        this.f6428d = 10.0f;
        this.f6429e = -16777216;
        this.f6430f = 0;
        this.f6431g = 0.0f;
        this.f6432h = true;
        this.f6433i = false;
        this.f6425a = i;
        this.f6426b = list;
        this.f6427c = list2;
        this.f6428d = f;
        this.f6429e = i2;
        this.f6430f = i3;
        this.f6431g = f2;
        this.f6432h = z;
        this.f6433i = z2;
    }

    int m10135a() {
        return this.f6425a;
    }

    List m10136b() {
        return this.f6427c;
    }

    public List<LatLng> m10137c() {
        return this.f6426b;
    }

    public float m10138d() {
        return this.f6428d;
    }

    public int describeContents() {
        return 0;
    }

    public int m10139e() {
        return this.f6429e;
    }

    public int m10140f() {
        return this.f6430f;
    }

    public float m10141g() {
        return this.f6431g;
    }

    public boolean m10142h() {
        return this.f6432h;
    }

    public boolean m10143i() {
        return this.f6433i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzh.m10448a(this, parcel, i);
    }
}
