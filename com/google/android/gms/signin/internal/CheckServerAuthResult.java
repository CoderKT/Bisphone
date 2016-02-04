package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class CheckServerAuthResult implements SafeParcelable {
    public static final Creator<CheckServerAuthResult> CREATOR;
    final int f6487a;
    final boolean f6488b;
    final List<Scope> f6489c;

    static {
        CREATOR = new zzc();
    }

    CheckServerAuthResult(int i, boolean z, List<Scope> list) {
        this.f6487a = i;
        this.f6488b = z;
        this.f6489c = list;
    }

    public CheckServerAuthResult(boolean z, Set<Scope> set) {
        this(1, z, m10481a(set));
    }

    private static List<Scope> m10481a(Set<Scope> set) {
        return set == null ? Collections.emptyList() : Collections.unmodifiableList(new ArrayList(set));
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzc.m10488a(this, parcel, i);
    }
}
