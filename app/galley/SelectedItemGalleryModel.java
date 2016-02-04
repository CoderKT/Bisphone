package app.galley;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import app.galley.external.GalleryItemModel;
import java.util.HashMap;
import java.util.Map.Entry;

public class SelectedItemGalleryModel implements Parcelable {
    public static final Creator<SelectedItemGalleryModel> CREATOR;
    HashMap<Long, GalleryItemModel> f2541a;

    /* renamed from: app.galley.SelectedItemGalleryModel.1 */
    final class C01411 implements Creator<SelectedItemGalleryModel> {
        C01411() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m5026a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m5027a(i);
        }

        public SelectedItemGalleryModel m5026a(Parcel parcel) {
            return new SelectedItemGalleryModel(null);
        }

        public SelectedItemGalleryModel[] m5027a(int i) {
            return new SelectedItemGalleryModel[i];
        }
    }

    public SelectedItemGalleryModel() {
        this.f2541a = new HashMap();
    }

    public HashMap<Long, GalleryItemModel> m5028a() {
        return this.f2541a;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int size = this.f2541a.size();
        parcel.writeInt(size);
        if (size > 0) {
            for (Entry entry : this.f2541a.entrySet()) {
                parcel.writeLong(((Long) entry.getKey()).longValue());
                parcel.writeParcelable((Parcelable) entry.getValue(), i);
            }
        }
    }

    static {
        CREATOR = new C01411();
    }

    private SelectedItemGalleryModel(Parcel parcel) {
        this.f2541a = new HashMap();
        int readInt = parcel.readInt();
        for (int i = 0; i < readInt; i++) {
            this.f2541a.put(Long.valueOf(parcel.readLong()), (GalleryItemModel) parcel.readParcelable(GalleryItemModel.class.getClassLoader()));
        }
    }
}
