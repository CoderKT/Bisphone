package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public final class BinderWrapper implements Parcelable {
    public static final Creator<BinderWrapper> CREATOR;
    private IBinder f5696a;

    /* renamed from: com.google.android.gms.common.internal.BinderWrapper.1 */
    final class C06501 implements Creator<BinderWrapper> {
        C06501() {
        }

        public BinderWrapper m8413a(Parcel parcel) {
            return new BinderWrapper(null);
        }

        public BinderWrapper[] m8414a(int i) {
            return new BinderWrapper[i];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m8413a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m8414a(i);
        }
    }

    static {
        CREATOR = new C06501();
    }

    public BinderWrapper() {
        this.f5696a = null;
    }

    public BinderWrapper(IBinder iBinder) {
        this.f5696a = null;
        this.f5696a = iBinder;
    }

    private BinderWrapper(Parcel parcel) {
        this.f5696a = null;
        this.f5696a = parcel.readStrongBinder();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStrongBinder(this.f5696a);
    }
}
