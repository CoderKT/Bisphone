package app.messaging.stickers;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

class OnlinePackEntity implements Parcelable {
    public static final Creator<OnlinePackEntity> CREATOR;
    int f3901a;
    String f3902b;
    String f3903c;
    Boolean f3904d;
    Boolean f3905e;

    /* renamed from: app.messaging.stickers.OnlinePackEntity.1 */
    final class C03641 implements Creator<OnlinePackEntity> {
        C03641() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m6432a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m6433a(i);
        }

        public OnlinePackEntity m6432a(Parcel parcel) {
            return new OnlinePackEntity(parcel);
        }

        public OnlinePackEntity[] m6433a(int i) {
            return new OnlinePackEntity[i];
        }
    }

    OnlinePackEntity(int i, String str, String str2, boolean z, boolean z2) {
        this.f3901a = i;
        this.f3902b = str;
        this.f3903c = str2;
        this.f3904d = Boolean.valueOf(z);
        this.f3905e = Boolean.valueOf(z2);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = 1;
        parcel.writeInt(this.f3901a);
        parcel.writeString(this.f3902b);
        parcel.writeString(this.f3903c);
        int i3 = this.f3904d == null ? -1 : this.f3904d.booleanValue() ? 1 : 0;
        parcel.writeInt(i3);
        if (!this.f3905e.booleanValue()) {
            i2 = 0;
        }
        parcel.writeInt(i2);
    }

    public OnlinePackEntity(Parcel parcel) {
        Boolean bool;
        boolean z = true;
        this.f3901a = parcel.readInt();
        this.f3902b = parcel.readString();
        this.f3903c = parcel.readString();
        if (parcel.readInt() == -1) {
            bool = null;
        } else {
            bool = Boolean.valueOf(parcel.readInt() != 0);
        }
        this.f3904d = bool;
        if (parcel.readInt() != 1) {
            z = false;
        }
        this.f3905e = Boolean.valueOf(z);
    }

    static {
        CREATOR = new C03641();
    }
}
