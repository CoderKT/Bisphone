package android.support.v4.os;

import android.os.Parcel;
import android.os.Parcelable.ClassLoaderCreator;

/* compiled from: ParcelableCompatHoneycombMR2 */
class ParcelableCompatCreatorHoneycombMR2<T> implements ClassLoaderCreator<T> {
    private final ParcelableCompatCreatorCallbacks<T> f360a;

    public ParcelableCompatCreatorHoneycombMR2(ParcelableCompatCreatorCallbacks<T> parcelableCompatCreatorCallbacks) {
        this.f360a = parcelableCompatCreatorCallbacks;
    }

    public T createFromParcel(Parcel parcel) {
        return this.f360a.m694a(parcel, null);
    }

    public T createFromParcel(Parcel parcel, ClassLoader classLoader) {
        return this.f360a.m694a(parcel, classLoader);
    }

    public T[] newArray(int i) {
        return this.f360a.m695a(i);
    }
}
