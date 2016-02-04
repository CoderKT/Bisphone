package app.messaging.channel;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChannelModel implements Parcelable {
    public static final Creator<ChannelModel> CREATOR;
    private String f3514a;
    private String f3515b;
    private String f3516c;
    private long f3517d;
    private float f3518e;
    private boolean f3519f;
    private String f3520g;
    private String f3521h;
    private String f3522i;
    private String f3523j;
    private String f3524k;
    private boolean f3525l;
    private long f3526m;
    private int f3527n;
    private Boolean f3528o;
    private State f3529p;
    private final String f3530q;

    /* renamed from: app.messaging.channel.ChannelModel.1 */
    final class C03081 implements Creator<ChannelModel> {
        C03081() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m6115a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m6116a(i);
        }

        public ChannelModel m6115a(Parcel parcel) {
            return new ChannelModel(parcel);
        }

        public ChannelModel[] m6116a(int i) {
            return new ChannelModel[i];
        }
    }

    public class Builder {
    }

    public enum State {
        invited,
        joined,
        blocked
    }

    public String getId() {
        if (this.f3514a.contains("@channel.bisphone.com")) {
            return this.f3514a;
        }
        return this.f3514a + "@channel.bisphone.com";
    }

    public String getTitle() {
        return this.f3515b;
    }

    public String getLang() {
        return this.f3516c;
    }

    public long getFollowers() {
        return this.f3517d;
    }

    public float getPrice() {
        return this.f3518e;
    }

    public boolean m6117a() {
        return this.f3519f;
    }

    public String getAvatar() {
        return this.f3520g;
    }

    public String getCover() {
        return this.f3521h;
    }

    public String getTags() {
        return this.f3522i;
    }

    public String getDescription() {
        return this.f3524k;
    }

    public String getCreatedAt() {
        return this.f3523j;
    }

    public boolean m6118b() {
        return this.f3525l;
    }

    public long getTimestamp() {
        return this.f3526m;
    }

    public int getUnreadCount() {
        return this.f3527n;
    }

    public Boolean m6119c() {
        return this.f3528o;
    }

    public State getState() {
        return this.f3529p;
    }

    public void setId(String str) {
        this.f3514a = str;
    }

    public void setTitle(String str) {
        this.f3515b = str;
    }

    public void setLang(String str) {
        this.f3516c = str;
    }

    public void setFollowers(long j) {
        this.f3517d = j;
    }

    public void setPrice(float f) {
        this.f3518e = f;
    }

    public void setReadonly(boolean z) {
        this.f3519f = z;
    }

    public void setAvatar(String str) {
        this.f3520g = str;
    }

    public void setCover(String str) {
        this.f3521h = str;
    }

    public void setTags(String str) {
        this.f3522i = str;
    }

    public void setCreatedAt(String str) {
        this.f3523j = str;
    }

    public void setDescription(String str) {
        this.f3524k = str;
    }

    public void setMuted(boolean z) {
        this.f3525l = z;
    }

    public void setTimestamp(long j) {
        this.f3526m = j;
    }

    public void setUnreadCount(int i) {
        this.f3527n = i;
    }

    public void setFollowing(Boolean bool) {
        this.f3528o = bool;
    }

    public void setState(State state) {
        this.f3529p = state;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2;
        int i3 = 1;
        parcel.writeString(this.f3514a);
        parcel.writeString(this.f3515b);
        parcel.writeString(this.f3516c);
        parcel.writeLong(this.f3517d);
        parcel.writeFloat(this.f3518e);
        parcel.writeByte((byte) (this.f3519f ? 1 : 0));
        parcel.writeString(this.f3520g);
        parcel.writeString(this.f3521h);
        parcel.writeString(this.f3522i);
        parcel.writeString(this.f3523j);
        parcel.writeString(this.f3524k);
        if (this.f3525l) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        parcel.writeLong(this.f3526m);
        parcel.writeInt(this.f3527n);
        if (this.f3528o == null) {
            i3 = -1;
        } else if (!this.f3528o.booleanValue()) {
            i3 = 0;
        }
        parcel.writeInt(i3);
    }

    public ChannelModel() {
        this.f3530q = "@channel.bisphone.com";
    }

    public ChannelModel(Parcel parcel) {
        boolean z;
        Boolean bool;
        boolean z2 = true;
        this.f3530q = "@channel.bisphone.com";
        this.f3514a = parcel.readString();
        this.f3515b = parcel.readString();
        this.f3516c = parcel.readString();
        this.f3517d = parcel.readLong();
        this.f3518e = parcel.readFloat();
        this.f3519f = parcel.readByte() != null;
        this.f3520g = parcel.readString();
        this.f3521h = parcel.readString();
        this.f3522i = parcel.readString();
        this.f3523j = parcel.readString();
        this.f3524k = parcel.readString();
        if (parcel.readByte() != null) {
            z = true;
        } else {
            z = false;
        }
        this.f3525l = z;
        this.f3526m = parcel.readLong();
        this.f3527n = parcel.readInt();
        int readInt = parcel.readInt();
        if (readInt == -1) {
            bool = null;
        } else {
            if (readInt == 0) {
                z2 = false;
            }
            bool = Boolean.valueOf(z2);
        }
        this.f3528o = bool;
    }

    static {
        CREATOR = new C03081();
    }
}
