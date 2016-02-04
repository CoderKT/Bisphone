package com.google.android.gms.maps;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.util.AttributeSet;
import com.google.android.gms.C0648R;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.zza;
import com.google.android.gms.maps.model.CameraPosition;

public final class GoogleMapOptions implements SafeParcelable {
    public static final zza CREATOR;
    private final int f6301a;
    private Boolean f6302b;
    private Boolean f6303c;
    private int f6304d;
    private CameraPosition f6305e;
    private Boolean f6306f;
    private Boolean f6307g;
    private Boolean f6308h;
    private Boolean f6309i;
    private Boolean f6310j;
    private Boolean f6311k;
    private Boolean f6312l;
    private Boolean f6313m;
    private Boolean f6314n;

    static {
        CREATOR = new zza();
    }

    public GoogleMapOptions() {
        this.f6304d = -1;
        this.f6301a = 1;
    }

    GoogleMapOptions(int i, byte b, byte b2, int i2, CameraPosition cameraPosition, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, byte b9, byte b10, byte b11) {
        this.f6304d = -1;
        this.f6301a = i;
        this.f6302b = zza.m9996a(b);
        this.f6303c = zza.m9996a(b2);
        this.f6304d = i2;
        this.f6305e = cameraPosition;
        this.f6306f = zza.m9996a(b3);
        this.f6307g = zza.m9996a(b4);
        this.f6308h = zza.m9996a(b5);
        this.f6309i = zza.m9996a(b6);
        this.f6310j = zza.m9996a(b7);
        this.f6311k = zza.m9996a(b8);
        this.f6312l = zza.m9996a(b9);
        this.f6313m = zza.m9996a(b10);
        this.f6314n = zza.m9996a(b11);
    }

    public static GoogleMapOptions m9612a(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return null;
        }
        TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, C0648R.styleable.MapAttrs);
        GoogleMapOptions googleMapOptions = new GoogleMapOptions();
        if (obtainAttributes.hasValue(C0648R.styleable.MapAttrs_mapType)) {
            googleMapOptions.m9614a(obtainAttributes.getInt(C0648R.styleable.MapAttrs_mapType, -1));
        }
        if (obtainAttributes.hasValue(C0648R.styleable.MapAttrs_zOrderOnTop)) {
            googleMapOptions.m9616a(obtainAttributes.getBoolean(C0648R.styleable.MapAttrs_zOrderOnTop, false));
        }
        if (obtainAttributes.hasValue(C0648R.styleable.MapAttrs_useViewLifecycle)) {
            googleMapOptions.m9618b(obtainAttributes.getBoolean(C0648R.styleable.MapAttrs_useViewLifecycle, false));
        }
        if (obtainAttributes.hasValue(C0648R.styleable.MapAttrs_uiCompass)) {
            googleMapOptions.m9622d(obtainAttributes.getBoolean(C0648R.styleable.MapAttrs_uiCompass, true));
        }
        if (obtainAttributes.hasValue(C0648R.styleable.MapAttrs_uiRotateGestures)) {
            googleMapOptions.m9630h(obtainAttributes.getBoolean(C0648R.styleable.MapAttrs_uiRotateGestures, true));
        }
        if (obtainAttributes.hasValue(C0648R.styleable.MapAttrs_uiScrollGestures)) {
            googleMapOptions.m9624e(obtainAttributes.getBoolean(C0648R.styleable.MapAttrs_uiScrollGestures, true));
        }
        if (obtainAttributes.hasValue(C0648R.styleable.MapAttrs_uiTiltGestures)) {
            googleMapOptions.m9628g(obtainAttributes.getBoolean(C0648R.styleable.MapAttrs_uiTiltGestures, true));
        }
        if (obtainAttributes.hasValue(C0648R.styleable.MapAttrs_uiZoomGestures)) {
            googleMapOptions.m9626f(obtainAttributes.getBoolean(C0648R.styleable.MapAttrs_uiZoomGestures, true));
        }
        if (obtainAttributes.hasValue(C0648R.styleable.MapAttrs_uiZoomControls)) {
            googleMapOptions.m9620c(obtainAttributes.getBoolean(C0648R.styleable.MapAttrs_uiZoomControls, true));
        }
        if (obtainAttributes.hasValue(C0648R.styleable.MapAttrs_liteMode)) {
            googleMapOptions.m9632i(obtainAttributes.getBoolean(C0648R.styleable.MapAttrs_liteMode, false));
        }
        if (obtainAttributes.hasValue(C0648R.styleable.MapAttrs_uiMapToolbar)) {
            googleMapOptions.m9634j(obtainAttributes.getBoolean(C0648R.styleable.MapAttrs_uiMapToolbar, true));
        }
        if (obtainAttributes.hasValue(C0648R.styleable.MapAttrs_ambientEnabled)) {
            googleMapOptions.m9636k(obtainAttributes.getBoolean(C0648R.styleable.MapAttrs_ambientEnabled, false));
        }
        googleMapOptions.m9615a(CameraPosition.m10086a(context, attributeSet));
        obtainAttributes.recycle();
        return googleMapOptions;
    }

    int m9613a() {
        return this.f6301a;
    }

    public GoogleMapOptions m9614a(int i) {
        this.f6304d = i;
        return this;
    }

    public GoogleMapOptions m9615a(CameraPosition cameraPosition) {
        this.f6305e = cameraPosition;
        return this;
    }

    public GoogleMapOptions m9616a(boolean z) {
        this.f6302b = Boolean.valueOf(z);
        return this;
    }

    byte m9617b() {
        return zza.m9995a(this.f6302b);
    }

    public GoogleMapOptions m9618b(boolean z) {
        this.f6303c = Boolean.valueOf(z);
        return this;
    }

    byte m9619c() {
        return zza.m9995a(this.f6303c);
    }

    public GoogleMapOptions m9620c(boolean z) {
        this.f6306f = Boolean.valueOf(z);
        return this;
    }

    byte m9621d() {
        return zza.m9995a(this.f6306f);
    }

    public GoogleMapOptions m9622d(boolean z) {
        this.f6307g = Boolean.valueOf(z);
        return this;
    }

    public int describeContents() {
        return 0;
    }

    byte m9623e() {
        return zza.m9995a(this.f6307g);
    }

    public GoogleMapOptions m9624e(boolean z) {
        this.f6308h = Boolean.valueOf(z);
        return this;
    }

    byte m9625f() {
        return zza.m9995a(this.f6308h);
    }

    public GoogleMapOptions m9626f(boolean z) {
        this.f6309i = Boolean.valueOf(z);
        return this;
    }

    byte m9627g() {
        return zza.m9995a(this.f6309i);
    }

    public GoogleMapOptions m9628g(boolean z) {
        this.f6310j = Boolean.valueOf(z);
        return this;
    }

    byte m9629h() {
        return zza.m9995a(this.f6310j);
    }

    public GoogleMapOptions m9630h(boolean z) {
        this.f6311k = Boolean.valueOf(z);
        return this;
    }

    byte m9631i() {
        return zza.m9995a(this.f6311k);
    }

    public GoogleMapOptions m9632i(boolean z) {
        this.f6312l = Boolean.valueOf(z);
        return this;
    }

    byte m9633j() {
        return zza.m9995a(this.f6312l);
    }

    public GoogleMapOptions m9634j(boolean z) {
        this.f6313m = Boolean.valueOf(z);
        return this;
    }

    byte m9635k() {
        return zza.m9995a(this.f6313m);
    }

    public GoogleMapOptions m9636k(boolean z) {
        this.f6314n = Boolean.valueOf(z);
        return this;
    }

    byte m9637l() {
        return zza.m9995a(this.f6314n);
    }

    public int m9638m() {
        return this.f6304d;
    }

    public CameraPosition m9639n() {
        return this.f6305e;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zza.m10475a(this, parcel, i);
    }
}
