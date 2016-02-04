package com.google.android.gms.location;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class GestureRequest implements SafeParcelable {
    public static final zzb CREATOR;
    private static final List<Integer> f6182a;
    private static final List<Integer> f6183b;
    private static final List<Integer> f6184c;
    private static final List<Integer> f6185d;
    private final int f6186e;
    private final List<Integer> f6187f;

    static {
        f6182a = Collections.unmodifiableList(Arrays.asList(new Integer[]{Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5), Integer.valueOf(6), Integer.valueOf(7), Integer.valueOf(8), Integer.valueOf(9), Integer.valueOf(10), Integer.valueOf(11), Integer.valueOf(12), Integer.valueOf(13), Integer.valueOf(14), Integer.valueOf(15), Integer.valueOf(16), Integer.valueOf(17), Integer.valueOf(18), Integer.valueOf(19)}));
        f6183b = Collections.unmodifiableList(Arrays.asList(new Integer[]{Integer.valueOf(1)}));
        f6184c = Collections.unmodifiableList(Arrays.asList(new Integer[]{Integer.valueOf(2), Integer.valueOf(4), Integer.valueOf(6), Integer.valueOf(8), Integer.valueOf(10), Integer.valueOf(12), Integer.valueOf(14), Integer.valueOf(16), Integer.valueOf(18), Integer.valueOf(19)}));
        f6185d = Collections.unmodifiableList(Arrays.asList(new Integer[]{Integer.valueOf(3), Integer.valueOf(5), Integer.valueOf(7), Integer.valueOf(9), Integer.valueOf(11), Integer.valueOf(13), Integer.valueOf(15), Integer.valueOf(17)}));
        CREATOR = new zzb();
    }

    GestureRequest(int i, List<Integer> list) {
        this.f6186e = i;
        this.f6187f = list;
    }

    public int m9349a() {
        return this.f6186e;
    }

    public List<Integer> m9350b() {
        return this.f6187f;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzb.m9539a(this, parcel, i);
    }
}
