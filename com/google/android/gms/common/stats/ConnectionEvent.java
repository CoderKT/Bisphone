package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class ConnectionEvent extends zzf implements SafeParcelable {
    public static final Creator<ConnectionEvent> CREATOR;
    final int f5813a;
    private final long f5814d;
    private int f5815e;
    private final String f5816f;
    private final String f5817g;
    private final String f5818h;
    private final String f5819i;
    private final String f5820j;
    private final String f5821k;
    private final long f5822l;
    private final long f5823m;
    private long f5824n;

    static {
        CREATOR = new zza();
    }

    ConnectionEvent(int i, long j, int i2, String str, String str2, String str3, String str4, String str5, String str6, long j2, long j3) {
        this.f5813a = i;
        this.f5814d = j;
        this.f5815e = i2;
        this.f5816f = str;
        this.f5817g = str2;
        this.f5818h = str3;
        this.f5819i = str4;
        this.f5824n = -1;
        this.f5820j = str5;
        this.f5821k = str6;
        this.f5822l = j2;
        this.f5823m = j3;
    }

    public ConnectionEvent(long j, int i, String str, String str2, String str3, String str4, String str5, String str6, long j2, long j3) {
        this(1, j, i, str, str2, str3, str4, str5, str6, j2, j3);
    }

    public long m8738a() {
        return this.f5814d;
    }

    public int m8739b() {
        return this.f5815e;
    }

    public String m8740c() {
        return this.f5816f;
    }

    public String m8741d() {
        return this.f5817g;
    }

    public int describeContents() {
        return 0;
    }

    public String m8742e() {
        return this.f5818h;
    }

    public String m8743f() {
        return this.f5819i;
    }

    public String m8744g() {
        return this.f5820j;
    }

    public String m8745h() {
        return this.f5821k;
    }

    public long m8746i() {
        return this.f5824n;
    }

    public long m8747j() {
        return this.f5823m;
    }

    public long m8748k() {
        return this.f5822l;
    }

    public String m8749l() {
        return "\t" + m8740c() + "/" + m8741d() + "\t" + m8742e() + "/" + m8743f() + "\t" + (this.f5820j == null ? "" : this.f5820j) + "\t" + m8747j();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zza.m8750a(this, parcel, i);
    }
}
