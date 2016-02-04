package app.galley;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class MessagePreview implements Parcelable {
    public static final Creator<MessagePreview> CREATOR;
    String f2529a;
    String f2530b;
    String f2531c;
    int f2532d;
    long f2533e;

    /* renamed from: app.galley.MessagePreview.1 */
    final class C01401 implements Creator<MessagePreview> {
        C01401() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m4995a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m4996a(i);
        }

        public MessagePreview m4995a(Parcel parcel) {
            return new MessagePreview(parcel);
        }

        public MessagePreview[] m4996a(int i) {
            return new MessagePreview[i];
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f2529a);
        parcel.writeString(this.f2530b);
        parcel.writeString(this.f2531c);
        parcel.writeLong(this.f2533e);
        parcel.writeLong((long) this.f2532d);
    }

    public MessagePreview(String str, String str2, String str3, long j, int i) {
        this.f2529a = str;
        this.f2530b = str2;
        this.f2531c = str3;
        this.f2533e = j;
        this.f2532d = i;
    }

    public String m4997a() {
        return this.f2530b;
    }

    public String m4998b() {
        return this.f2529a;
    }

    MessagePreview(Parcel parcel) {
        this.f2532d = parcel.readInt();
        this.f2529a = parcel.readString();
        this.f2530b = parcel.readString();
        this.f2531c = parcel.readString();
        this.f2533e = parcel.readLong();
    }

    static {
        CREATOR = new C01401();
    }
}
