package com.google.android.gms.location;

import android.os.Parcel;
import android.os.SystemClock;
import app.C0110R;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;

public final class LocationRequest implements SafeParcelable {
    public static final LocationRequestCreator CREATOR;
    int f6193a;
    long f6194b;
    long f6195c;
    boolean f6196d;
    long f6197e;
    int f6198f;
    float f6199g;
    long f6200h;
    private final int f6201i;

    static {
        CREATOR = new LocationRequestCreator();
    }

    public LocationRequest() {
        this.f6201i = 1;
        this.f6193a = C0110R.styleable.Theme_checkboxStyle;
        this.f6194b = 3600000;
        this.f6195c = 600000;
        this.f6196d = false;
        this.f6197e = Long.MAX_VALUE;
        this.f6198f = Integer.MAX_VALUE;
        this.f6199g = 0.0f;
        this.f6200h = 0;
    }

    LocationRequest(int i, int i2, long j, long j2, boolean z, long j3, int i3, float f, long j4) {
        this.f6201i = i;
        this.f6193a = i2;
        this.f6194b = j;
        this.f6195c = j2;
        this.f6196d = z;
        this.f6197e = j3;
        this.f6198f = i3;
        this.f6199g = f;
        this.f6200h = j4;
    }

    public static LocationRequest m9356a() {
        return new LocationRequest();
    }

    public static String m9357b(int i) {
        switch (i) {
            case C0110R.styleable.Theme_buttonStyle /*100*/:
                return "PRIORITY_HIGH_ACCURACY";
            case C0110R.styleable.Theme_checkboxStyle /*102*/:
                return "PRIORITY_BALANCED_POWER_ACCURACY";
            case C0110R.styleable.Theme_editTextStyle /*104*/:
                return "PRIORITY_LOW_POWER";
            case C0110R.styleable.Theme_radioButtonStyle /*105*/:
                return "PRIORITY_NO_POWER";
            default:
                return "???";
        }
    }

    private static void m9358b(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("invalid interval: " + j);
        }
    }

    private static void m9359c(int i) {
        switch (i) {
            case C0110R.styleable.Theme_buttonStyle /*100*/:
            case C0110R.styleable.Theme_checkboxStyle /*102*/:
            case C0110R.styleable.Theme_editTextStyle /*104*/:
            case C0110R.styleable.Theme_radioButtonStyle /*105*/:
            default:
                throw new IllegalArgumentException("invalid quality: " + i);
        }
    }

    public LocationRequest m9360a(int i) {
        m9359c(i);
        this.f6193a = i;
        return this;
    }

    public LocationRequest m9361a(long j) {
        m9358b(j);
        this.f6194b = j;
        if (!this.f6196d) {
            this.f6195c = (long) (((double) this.f6194b) / 6.0d);
        }
        return this;
    }

    int m9362b() {
        return this.f6201i;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LocationRequest)) {
            return false;
        }
        LocationRequest locationRequest = (LocationRequest) obj;
        return this.f6193a == locationRequest.f6193a && this.f6194b == locationRequest.f6194b && this.f6195c == locationRequest.f6195c && this.f6196d == locationRequest.f6196d && this.f6197e == locationRequest.f6197e && this.f6198f == locationRequest.f6198f && this.f6199g == locationRequest.f6199g;
    }

    public int hashCode() {
        return zzw.m8715a(Integer.valueOf(this.f6193a), Long.valueOf(this.f6194b), Long.valueOf(this.f6195c), Boolean.valueOf(this.f6196d), Long.valueOf(this.f6197e), Integer.valueOf(this.f6198f), Float.valueOf(this.f6199g));
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Request[").append(m9357b(this.f6193a));
        if (this.f6193a != C0110R.styleable.Theme_radioButtonStyle) {
            stringBuilder.append(" requested=");
            stringBuilder.append(this.f6194b).append("ms");
        }
        stringBuilder.append(" fastest=");
        stringBuilder.append(this.f6195c).append("ms");
        if (this.f6200h > this.f6194b) {
            stringBuilder.append(" maxWait=");
            stringBuilder.append(this.f6200h).append("ms");
        }
        if (this.f6197e != Long.MAX_VALUE) {
            long elapsedRealtime = this.f6197e - SystemClock.elapsedRealtime();
            stringBuilder.append(" expireIn=");
            stringBuilder.append(elapsedRealtime).append("ms");
        }
        if (this.f6198f != Integer.MAX_VALUE) {
            stringBuilder.append(" num=").append(this.f6198f);
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        LocationRequestCreator.m9363a(this, parcel, i);
    }
}
