package com.google.android.gms.location.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.location.LocationRequest;
import java.util.Collections;
import java.util.List;

public class LocationRequestInternal implements SafeParcelable {
    public static final zzm CREATOR;
    static final List<ClientIdentity> f6233a;
    LocationRequest f6234b;
    boolean f6235c;
    boolean f6236d;
    boolean f6237e;
    List<ClientIdentity> f6238f;
    String f6239g;
    boolean f6240h;
    private final int f6241i;

    static {
        f6233a = Collections.emptyList();
        CREATOR = new zzm();
    }

    LocationRequestInternal(int i, LocationRequest locationRequest, boolean z, boolean z2, boolean z3, List<ClientIdentity> list, String str, boolean z4) {
        this.f6241i = i;
        this.f6234b = locationRequest;
        this.f6235c = z;
        this.f6236d = z2;
        this.f6237e = z3;
        this.f6238f = list;
        this.f6239g = str;
        this.f6240h = z4;
    }

    @Deprecated
    public static LocationRequestInternal m9391a(LocationRequest locationRequest) {
        return m9392a(null, locationRequest);
    }

    public static LocationRequestInternal m9392a(String str, LocationRequest locationRequest) {
        return new LocationRequestInternal(1, locationRequest, false, true, true, f6233a, str, false);
    }

    int m9393a() {
        return this.f6241i;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof LocationRequestInternal)) {
            return false;
        }
        LocationRequestInternal locationRequestInternal = (LocationRequestInternal) obj;
        return zzw.m8717a(this.f6234b, locationRequestInternal.f6234b) && this.f6235c == locationRequestInternal.f6235c && this.f6236d == locationRequestInternal.f6236d && this.f6237e == locationRequestInternal.f6237e && this.f6240h == locationRequestInternal.f6240h && zzw.m8717a(this.f6238f, locationRequestInternal.f6238f);
    }

    public int hashCode() {
        return this.f6234b.hashCode();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.f6234b.toString());
        if (this.f6239g != null) {
            stringBuilder.append(" tag=").append(this.f6239g);
        }
        stringBuilder.append(" nlpDebug=").append(this.f6235c);
        stringBuilder.append(" trigger=").append(this.f6237e);
        stringBuilder.append(" restorePIListeners=").append(this.f6236d);
        stringBuilder.append(" hideAppOps=").append(this.f6240h);
        stringBuilder.append(" clients=").append(this.f6238f);
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzm.m9527a(this, parcel, i);
    }
}
