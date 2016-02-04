package com.google.android.gms.common.api;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;

public final class Status implements Result, SafeParcelable {
    public static final Creator<Status> CREATOR;
    public static final Status f5682a;
    public static final Status f5683b;
    public static final Status f5684c;
    public static final Status f5685d;
    public static final Status f5686e;
    private final int f5687f;
    private final int f5688g;
    private final String f5689h;
    private final PendingIntent f5690i;

    static {
        f5682a = new Status(0);
        f5683b = new Status(14);
        f5684c = new Status(8);
        f5685d = new Status(15);
        f5686e = new Status(16);
        CREATOR = new zzd();
    }

    public Status(int i) {
        this(i, null);
    }

    Status(int i, int i2, String str, PendingIntent pendingIntent) {
        this.f5687f = i;
        this.f5688g = i2;
        this.f5689h = str;
        this.f5690i = pendingIntent;
    }

    public Status(int i, String str) {
        this(1, i, str, null);
    }

    public Status(int i, String str, PendingIntent pendingIntent) {
        this(1, i, str, pendingIntent);
    }

    private String m8397g() {
        return this.f5689h != null ? this.f5689h : CommonStatusCodes.m8363a(this.f5688g);
    }

    public Status m8398a() {
        return this;
    }

    PendingIntent m8399b() {
        return this.f5690i;
    }

    public String m8400c() {
        return this.f5689h;
    }

    int m8401d() {
        return this.f5687f;
    }

    public int describeContents() {
        return 0;
    }

    public boolean m8402e() {
        return this.f5688g <= 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Status)) {
            return false;
        }
        Status status = (Status) obj;
        return this.f5687f == status.f5687f && this.f5688g == status.f5688g && zzw.m8717a(this.f5689h, status.f5689h) && zzw.m8717a(this.f5690i, status.f5690i);
    }

    public int m8403f() {
        return this.f5688g;
    }

    public int hashCode() {
        return zzw.m8715a(Integer.valueOf(this.f5687f), Integer.valueOf(this.f5688g), this.f5689h, this.f5690i);
    }

    public String toString() {
        return zzw.m8716a((Object) this).m8714a("statusCode", m8397g()).m8714a("resolution", this.f5690i).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzd.m8410a(this, parcel, i);
    }
}
