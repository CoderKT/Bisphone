package com.google.android.gms.location;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import java.util.Comparator;
import se.emilsjolander.stickylistheaders.C1128R;

public class DetectedActivity implements SafeParcelable {
    public static final DetectedActivityCreator CREATOR;
    public static final Comparator<DetectedActivity> f6175a;
    int f6176b;
    int f6177c;
    private final int f6178d;

    /* renamed from: com.google.android.gms.location.DetectedActivity.1 */
    final class C08721 implements Comparator<DetectedActivity> {
        C08721() {
        }

        public int m9335a(DetectedActivity detectedActivity, DetectedActivity detectedActivity2) {
            int compareTo = Integer.valueOf(detectedActivity2.m9339b()).compareTo(Integer.valueOf(detectedActivity.m9339b()));
            return compareTo == 0 ? Integer.valueOf(detectedActivity.m9338a()).compareTo(Integer.valueOf(detectedActivity2.m9338a())) : compareTo;
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m9335a((DetectedActivity) obj, (DetectedActivity) obj2);
        }
    }

    static {
        f6175a = new C08721();
        CREATOR = new DetectedActivityCreator();
    }

    public DetectedActivity(int i, int i2, int i3) {
        this.f6178d = i;
        this.f6176b = i2;
        this.f6177c = i3;
    }

    public static String m9336a(int i) {
        switch (i) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                return "IN_VEHICLE";
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                return "ON_BICYCLE";
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                return "ON_FOOT";
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                return "STILL";
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                return "UNKNOWN";
            case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                return "TILTING";
            case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                return "WALKING";
            case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                return "RUNNING";
            default:
                return Integer.toString(i);
        }
    }

    private int m9337b(int i) {
        return i > 15 ? 4 : i;
    }

    public int m9338a() {
        return m9337b(this.f6176b);
    }

    public int m9339b() {
        return this.f6177c;
    }

    public int m9340c() {
        return this.f6178d;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DetectedActivity detectedActivity = (DetectedActivity) obj;
        return this.f6176b == detectedActivity.f6176b && this.f6177c == detectedActivity.f6177c;
    }

    public int hashCode() {
        return zzw.m8715a(Integer.valueOf(this.f6176b), Integer.valueOf(this.f6177c));
    }

    public String toString() {
        return "DetectedActivity [type=" + m9336a(m9338a()) + ", confidence=" + this.f6177c + "]";
    }

    public void writeToParcel(Parcel parcel, int i) {
        DetectedActivityCreator.m9341a(this, parcel, i);
    }
}
