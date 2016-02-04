package app.messaging.channel;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.Comparator;

public class ChannelDetailAdapterModel implements Parcelable, Comparator<ChannelDetailAdapterModel> {
    public static final Creator<ChannelDetailAdapterModel> CREATOR;
    ChannelModel f3501a;
    Boolean f3502b;

    /* renamed from: app.messaging.channel.ChannelDetailAdapterModel.1 */
    final class C03071 implements Creator<ChannelDetailAdapterModel> {
        C03071() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m6112a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m6113a(i);
        }

        public ChannelDetailAdapterModel m6112a(Parcel parcel) {
            return new ChannelDetailAdapterModel(parcel);
        }

        public ChannelDetailAdapterModel[] m6113a(int i) {
            return new ChannelDetailAdapterModel[i];
        }
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return m6114a((ChannelDetailAdapterModel) obj, (ChannelDetailAdapterModel) obj2);
    }

    public ChannelDetailAdapterModel(ChannelModel channelModel, boolean z) {
        this.f3501a = channelModel;
        this.f3502b = Boolean.valueOf(z);
    }

    public int m6114a(ChannelDetailAdapterModel channelDetailAdapterModel, ChannelDetailAdapterModel channelDetailAdapterModel2) {
        return (int) (channelDetailAdapterModel2.f3501a.getFollowers() - channelDetailAdapterModel.f3501a.getFollowers());
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f3501a, i);
        int i2 = this.f3502b == null ? -1 : this.f3502b.booleanValue() ? 1 : 0;
        parcel.writeInt(i2);
    }

    public ChannelDetailAdapterModel(Parcel parcel) {
        this.f3501a = new ChannelModel();
        this.f3501a = (ChannelModel) parcel.readParcelable(ChannelModel.class.getClassLoader());
        int readInt = parcel.readInt();
        if (readInt == -1) {
            this.f3502b = null;
        } else if (readInt == 0) {
            this.f3502b = Boolean.valueOf(false);
        } else {
            this.f3502b = Boolean.valueOf(true);
        }
    }

    static {
        CREATOR = new C03071();
    }
}
