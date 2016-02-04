package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.model.internal.zzi;
import com.google.android.gms.maps.model.internal.zzi.zza;

public final class TileOverlayOptions implements SafeParcelable {
    public static final zzo CREATOR;
    private final int f6465a;
    private zzi f6466b;
    private TileProvider f6467c;
    private boolean f6468d;
    private float f6469e;
    private boolean f6470f;

    /* renamed from: com.google.android.gms.maps.model.TileOverlayOptions.1 */
    class C08821 implements TileProvider {
        final /* synthetic */ TileOverlayOptions f6463a;
        private final zzi f6464c;

        C08821(TileOverlayOptions tileOverlayOptions) {
            this.f6463a = tileOverlayOptions;
            this.f6464c = this.f6463a.f6466b;
        }
    }

    static {
        CREATOR = new zzo();
    }

    public TileOverlayOptions() {
        this.f6468d = true;
        this.f6470f = true;
        this.f6465a = 1;
    }

    TileOverlayOptions(int i, IBinder iBinder, boolean z, float f, boolean z2) {
        this.f6468d = true;
        this.f6470f = true;
        this.f6465a = i;
        this.f6466b = zza.m10426a(iBinder);
        this.f6467c = this.f6466b == null ? null : new C08821(this);
        this.f6468d = z;
        this.f6469e = f;
        this.f6470f = z2;
    }

    int m10160a() {
        return this.f6465a;
    }

    IBinder m10161b() {
        return this.f6466b.asBinder();
    }

    public float m10162c() {
        return this.f6469e;
    }

    public boolean m10163d() {
        return this.f6468d;
    }

    public int describeContents() {
        return 0;
    }

    public boolean m10164e() {
        return this.f6470f;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzo.m10469a(this, parcel, i);
    }
}
