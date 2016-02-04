package com.google.android.gms.maps.model;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.util.AttributeSet;
import com.google.android.gms.C0648R;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzx;

public final class CameraPosition implements SafeParcelable {
    public static final zza CREATOR;
    public final LatLng f6375a;
    public final float f6376b;
    public final float f6377c;
    public final float f6378d;
    private final int f6379e;

    public final class Builder {
        private LatLng f6371a;
        private float f6372b;
        private float f6373c;
        private float f6374d;

        public Builder m10081a(float f) {
            this.f6372b = f;
            return this;
        }

        public Builder m10082a(LatLng latLng) {
            this.f6371a = latLng;
            return this;
        }

        public CameraPosition m10083a() {
            return new CameraPosition(this.f6371a, this.f6372b, this.f6373c, this.f6374d);
        }

        public Builder m10084b(float f) {
            this.f6373c = f;
            return this;
        }

        public Builder m10085c(float f) {
            this.f6374d = f;
            return this;
        }
    }

    static {
        CREATOR = new zza();
    }

    CameraPosition(int i, LatLng latLng, float f, float f2, float f3) {
        zzx.m8719a((Object) latLng, (Object) "null camera target");
        boolean z = 0.0f <= f2 && f2 <= 90.0f;
        zzx.m8724a(z, "Tilt needs to be between 0 and 90 inclusive: %s", Float.valueOf(f2));
        this.f6379e = i;
        this.f6375a = latLng;
        this.f6376b = f;
        this.f6377c = f2 + 0.0f;
        if (((double) f3) <= 0.0d) {
            f3 = (f3 % 360.0f) + 360.0f;
        }
        this.f6378d = f3 % 360.0f;
    }

    public CameraPosition(LatLng latLng, float f, float f2, float f3) {
        this(1, latLng, f, f2, f3);
    }

    public static CameraPosition m10086a(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return null;
        }
        TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, C0648R.styleable.MapAttrs);
        LatLng latLng = new LatLng((double) (obtainAttributes.hasValue(C0648R.styleable.MapAttrs_cameraTargetLat) ? obtainAttributes.getFloat(C0648R.styleable.MapAttrs_cameraTargetLat, 0.0f) : 0.0f), (double) (obtainAttributes.hasValue(C0648R.styleable.MapAttrs_cameraTargetLng) ? obtainAttributes.getFloat(C0648R.styleable.MapAttrs_cameraTargetLng, 0.0f) : 0.0f));
        Builder b = m10087b();
        b.m10082a(latLng);
        if (obtainAttributes.hasValue(C0648R.styleable.MapAttrs_cameraZoom)) {
            b.m10081a(obtainAttributes.getFloat(C0648R.styleable.MapAttrs_cameraZoom, 0.0f));
        }
        if (obtainAttributes.hasValue(C0648R.styleable.MapAttrs_cameraBearing)) {
            b.m10085c(obtainAttributes.getFloat(C0648R.styleable.MapAttrs_cameraBearing, 0.0f));
        }
        if (obtainAttributes.hasValue(C0648R.styleable.MapAttrs_cameraTilt)) {
            b.m10084b(obtainAttributes.getFloat(C0648R.styleable.MapAttrs_cameraTilt, 0.0f));
        }
        return b.m10083a();
    }

    public static Builder m10087b() {
        return new Builder();
    }

    int m10088a() {
        return this.f6379e;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CameraPosition)) {
            return false;
        }
        CameraPosition cameraPosition = (CameraPosition) obj;
        return this.f6375a.equals(cameraPosition.f6375a) && Float.floatToIntBits(this.f6376b) == Float.floatToIntBits(cameraPosition.f6376b) && Float.floatToIntBits(this.f6377c) == Float.floatToIntBits(cameraPosition.f6377c) && Float.floatToIntBits(this.f6378d) == Float.floatToIntBits(cameraPosition.f6378d);
    }

    public int hashCode() {
        return zzw.m8715a(this.f6375a, Float.valueOf(this.f6376b), Float.valueOf(this.f6377c), Float.valueOf(this.f6378d));
    }

    public String toString() {
        return zzw.m8716a((Object) this).m8714a("target", this.f6375a).m8714a("zoom", Float.valueOf(this.f6376b)).m8714a("tilt", Float.valueOf(this.f6377c)).m8714a("bearing", Float.valueOf(this.f6378d)).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zza.m10427a(this, parcel, i);
    }
}
