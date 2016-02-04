package com.google.android.gms.location.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Locale;
import se.emilsjolander.stickylistheaders.C1128R;

public class ParcelableGeofence implements SafeParcelable {
    public static final zzo CREATOR;
    private final int f6249a;
    private final String f6250b;
    private final long f6251c;
    private final short f6252d;
    private final double f6253e;
    private final double f6254f;
    private final float f6255g;
    private final int f6256h;
    private final int f6257i;
    private final int f6258j;

    static {
        CREATOR = new zzo();
    }

    public ParcelableGeofence(int i, String str, int i2, short s, double d, double d2, float f, long j, int i3, int i4) {
        m9404a(str);
        m9403a(f);
        m9402a(d, d2);
        int a = m9401a(i2);
        this.f6249a = i;
        this.f6252d = s;
        this.f6250b = str;
        this.f6253e = d;
        this.f6254f = d2;
        this.f6255g = f;
        this.f6251c = j;
        this.f6256h = a;
        this.f6257i = i3;
        this.f6258j = i4;
    }

    private static int m9401a(int i) {
        int i2 = i & 7;
        if (i2 != 0) {
            return i2;
        }
        throw new IllegalArgumentException("No supported transition specified: " + i);
    }

    private static void m9402a(double d, double d2) {
        if (d > 90.0d || d < -90.0d) {
            throw new IllegalArgumentException("invalid latitude: " + d);
        } else if (d2 > 180.0d || d2 < -180.0d) {
            throw new IllegalArgumentException("invalid longitude: " + d2);
        }
    }

    private static void m9403a(float f) {
        if (f <= 0.0f) {
            throw new IllegalArgumentException("invalid radius: " + f);
        }
    }

    private static void m9404a(String str) {
        if (str == null || str.length() > 100) {
            throw new IllegalArgumentException("requestId is null or too long: " + str);
        }
    }

    private static String m9405b(int i) {
        switch (i) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                return "CIRCLE";
            default:
                return null;
        }
    }

    public int m9406a() {
        return this.f6249a;
    }

    public short m9407b() {
        return this.f6252d;
    }

    public double m9408c() {
        return this.f6253e;
    }

    public double m9409d() {
        return this.f6254f;
    }

    public int describeContents() {
        zzo com_google_android_gms_location_internal_zzo = CREATOR;
        return 0;
    }

    public float m9410e() {
        return this.f6255g;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ParcelableGeofence)) {
            return false;
        }
        ParcelableGeofence parcelableGeofence = (ParcelableGeofence) obj;
        return this.f6255g != parcelableGeofence.f6255g ? false : this.f6253e != parcelableGeofence.f6253e ? false : this.f6254f != parcelableGeofence.f6254f ? false : this.f6252d == parcelableGeofence.f6252d;
    }

    public String m9411f() {
        return this.f6250b;
    }

    public long m9412g() {
        return this.f6251c;
    }

    public int m9413h() {
        return this.f6256h;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.f6253e);
        int i = ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) + 31;
        long doubleToLongBits2 = Double.doubleToLongBits(this.f6254f);
        return (((((((i * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31) + Float.floatToIntBits(this.f6255g)) * 31) + this.f6252d) * 31) + this.f6256h;
    }

    public int m9414i() {
        return this.f6257i;
    }

    public int m9415j() {
        return this.f6258j;
    }

    public String toString() {
        return String.format(Locale.US, "Geofence[%s id:%s transitions:%d %.6f, %.6f %.0fm, resp=%ds, dwell=%dms, @%d]", new Object[]{m9405b(this.f6252d), this.f6250b, Integer.valueOf(this.f6256h), Double.valueOf(this.f6253e), Double.valueOf(this.f6254f), Float.valueOf(this.f6255g), Integer.valueOf(this.f6257i / 1000), Integer.valueOf(this.f6258j), Long.valueOf(this.f6251c)});
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzo com_google_android_gms_location_internal_zzo = CREATOR;
        zzo.m9533a(this, parcel, i);
    }
}
