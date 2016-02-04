package android.support.v4.os;

import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public class ParcelableCompat {

    class CompatCreator<T> implements Creator<T> {
        final ParcelableCompatCreatorCallbacks<T> f359a;

        public CompatCreator(ParcelableCompatCreatorCallbacks<T> parcelableCompatCreatorCallbacks) {
            this.f359a = parcelableCompatCreatorCallbacks;
        }

        public T createFromParcel(Parcel parcel) {
            return this.f359a.m694a(parcel, null);
        }

        public T[] newArray(int i) {
            return this.f359a.m695a(i);
        }
    }

    public static <T> Creator<T> m693a(ParcelableCompatCreatorCallbacks<T> parcelableCompatCreatorCallbacks) {
        if (VERSION.SDK_INT >= 13) {
            return ParcelableCompatCreatorHoneycombMR2Stub.m696a(parcelableCompatCreatorCallbacks);
        }
        return new CompatCreator(parcelableCompatCreatorCallbacks);
    }
}
