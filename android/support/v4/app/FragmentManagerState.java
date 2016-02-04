package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

/* compiled from: FragmentManager */
final class FragmentManagerState implements Parcelable {
    public static final Creator<FragmentManagerState> CREATOR;
    FragmentState[] f204a;
    int[] f205b;
    BackStackState[] f206c;

    /* renamed from: android.support.v4.app.FragmentManagerState.1 */
    final class FragmentManager implements Creator<FragmentManagerState> {
        FragmentManager() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m432a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m433a(i);
        }

        public FragmentManagerState m432a(Parcel parcel) {
            return new FragmentManagerState(parcel);
        }

        public FragmentManagerState[] m433a(int i) {
            return new FragmentManagerState[i];
        }
    }

    public FragmentManagerState(Parcel parcel) {
        this.f204a = (FragmentState[]) parcel.createTypedArray(FragmentState.CREATOR);
        this.f205b = parcel.createIntArray();
        this.f206c = (BackStackState[]) parcel.createTypedArray(BackStackState.CREATOR);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedArray(this.f204a, i);
        parcel.writeIntArray(this.f205b);
        parcel.writeTypedArray(this.f206c, i);
    }

    static {
        CREATOR = new FragmentManager();
    }
}
