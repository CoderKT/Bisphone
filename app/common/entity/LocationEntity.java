package app.common.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class LocationEntity implements Parcelable {
    public static final Creator<LocationEntity> CREATOR;
    private double f2266a;
    private double f2267b;
    private String f2268c;
    private String f2269d;
    private String f2270e;

    /* renamed from: app.common.entity.LocationEntity.1 */
    final class C01281 implements Creator<LocationEntity> {
        C01281() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m4460a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m4461a(i);
        }

        public LocationEntity m4460a(Parcel parcel) {
            return new LocationEntity(parcel);
        }

        public LocationEntity[] m4461a(int i) {
            return new LocationEntity[i];
        }
    }

    public double m4462a() {
        return this.f2266a;
    }

    public void m4463a(double d) {
        this.f2266a = d;
    }

    public double m4465b() {
        return this.f2267b;
    }

    public void m4466b(double d) {
        this.f2267b = d;
    }

    public String m4468c() {
        return this.f2268c;
    }

    public void m4464a(String str) {
        this.f2268c = str;
    }

    public String m4470d() {
        return this.f2269d;
    }

    public void m4467b(String str) {
        this.f2269d = str;
    }

    public LocationEntity(Parcel parcel) {
        this.f2266a = parcel.readDouble();
        this.f2267b = parcel.readDouble();
        this.f2268c = parcel.readString();
        this.f2269d = parcel.readString();
        this.f2270e = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.f2266a);
        parcel.writeDouble(this.f2267b);
        parcel.writeString(this.f2268c);
        parcel.writeString(this.f2269d);
        parcel.writeString(this.f2270e);
    }

    static {
        CREATOR = new C01281();
    }

    public String m4471e() {
        return this.f2270e;
    }

    public void m4469c(String str) {
        this.f2270e = str;
    }
}
