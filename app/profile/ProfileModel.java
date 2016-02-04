package app.profile;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import app.common.entity.ContactEntity;
import app.common.entity.ContactEntity.TYPE;

public class ProfileModel implements Parcelable {
    public static final Creator<ProfileModel> CREATOR;
    private String f4246a;
    private String f4247b;
    private String f4248c;
    private String f4249d;
    private String f4250e;
    private String f4251f;
    private long f4252g;
    private TYPE f4253h;

    /* renamed from: app.profile.ProfileModel.1 */
    final class C04421 implements ClassLoaderCreator<ProfileModel> {
        C04421() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m6713a(parcel);
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
            return m6714a(parcel, classLoader);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m6715a(i);
        }

        public ProfileModel m6714a(Parcel parcel, ClassLoader classLoader) {
            return new ProfileModel(parcel);
        }

        public ProfileModel m6713a(Parcel parcel) {
            return new ProfileModel(parcel);
        }

        public ProfileModel[] m6715a(int i) {
            return new ProfileModel[i];
        }
    }

    public ProfileModel(String str, String str2, String str3, String str4, String str5, TYPE type, long j, String str6) {
        this.f4246a = str;
        this.f4247b = str2;
        this.f4248c = str3;
        this.f4249d = str4;
        this.f4253h = type;
        this.f4250e = str5;
        this.f4252g = j;
        this.f4251f = str6;
    }

    public String m6716a() {
        return this.f4246a;
    }

    public void m6717a(String str) {
        this.f4246a = str;
    }

    public String m6718b() {
        return this.f4248c;
    }

    public String m6720c() {
        return this.f4249d;
    }

    public String m6722d() {
        return this.f4250e;
    }

    public void m6719b(String str) {
        this.f4250e = str;
    }

    public TYPE m6723e() {
        return this.f4253h;
    }

    public String m6724f() {
        return this.f4251f;
    }

    public void m6721c(String str) {
        this.f4251f = str;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f4246a);
        parcel.writeString(this.f4247b);
        parcel.writeString(this.f4248c);
        parcel.writeString(this.f4249d);
        parcel.writeString(this.f4250e);
        parcel.writeString(this.f4253h == null ? null : this.f4253h.toString());
        parcel.writeLong(this.f4252g);
        parcel.writeString(this.f4251f);
    }

    public ProfileModel(Parcel parcel) {
        this.f4246a = parcel.readString();
        this.f4247b = parcel.readString();
        this.f4248c = parcel.readString();
        this.f4249d = parcel.readString();
        this.f4250e = parcel.readString();
        this.f4253h = ContactEntity.m4178a(parcel.readString());
        this.f4252g = parcel.readLong();
        this.f4251f = parcel.readString();
    }

    static {
        CREATOR = new C04421();
    }
}
