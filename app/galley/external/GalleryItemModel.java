package app.galley.external;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class GalleryItemModel implements Parcelable {
    public static final Creator<GalleryItemModel> CREATOR;
    long f2572a;
    int f2573b;
    String f2574c;
    String f2575d;
    Bitmap f2576e;
    String f2577f;
    boolean f2578g;

    /* renamed from: app.galley.external.GalleryItemModel.1 */
    final class C01481 implements Creator<GalleryItemModel> {
        C01481() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m5057a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m5058a(i);
        }

        public GalleryItemModel m5057a(Parcel parcel) {
            return new GalleryItemModel(parcel);
        }

        public GalleryItemModel[] m5058a(int i) {
            return new GalleryItemModel[i];
        }
    }

    public GalleryItemModel(long j, String str, String str2, String str3, int i) {
        this.f2572a = j;
        this.f2575d = str;
        this.f2577f = str2;
        this.f2574c = str3;
        this.f2573b = i;
        this.f2576e = null;
        this.f2578g = false;
    }

    public long m5059a() {
        return this.f2572a;
    }

    public int m5061b() {
        return this.f2573b;
    }

    public String m5062c() {
        return this.f2574c;
    }

    public String m5063d() {
        return this.f2575d;
    }

    public String m5064e() {
        return this.f2577f;
    }

    public void m5060a(String str) {
        this.f2577f = str;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f2573b);
        parcel.writeLong(this.f2572a);
        parcel.writeString(this.f2575d);
        parcel.writeString(this.f2577f);
        parcel.writeString(this.f2574c);
        parcel.writeByte((byte) (this.f2578g ? 1 : 0));
    }

    public GalleryItemModel(Parcel parcel) {
        this.f2573b = parcel.readInt();
        this.f2572a = parcel.readLong();
        this.f2575d = parcel.readString();
        this.f2577f = parcel.readString();
        this.f2574c = parcel.readString();
        this.f2578g = parcel.readByte() != null;
        this.f2576e = null;
    }

    static {
        CREATOR = new C01481();
    }
}
