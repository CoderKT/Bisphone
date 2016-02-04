package com.google.android.gms.location;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import java.util.List;

public class ActivityRecognitionResult implements SafeParcelable {
    public static final ActivityRecognitionResultCreator CREATOR;
    List<DetectedActivity> f6170a;
    long f6171b;
    long f6172c;
    int f6173d;
    private final int f6174e;

    static {
        CREATOR = new ActivityRecognitionResultCreator();
    }

    public ActivityRecognitionResult(int i, List<DetectedActivity> list, long j, long j2, int i2) {
        this.f6174e = i;
        this.f6170a = list;
        this.f6171b = j;
        this.f6172c = j2;
        this.f6173d = i2;
    }

    public int m9331a() {
        return this.f6174e;
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
        ActivityRecognitionResult activityRecognitionResult = (ActivityRecognitionResult) obj;
        return this.f6171b == activityRecognitionResult.f6171b && this.f6172c == activityRecognitionResult.f6172c && this.f6173d == activityRecognitionResult.f6173d && zzw.m8717a(this.f6170a, activityRecognitionResult.f6170a);
    }

    public int hashCode() {
        return zzw.m8715a(Long.valueOf(this.f6171b), Long.valueOf(this.f6172c), Integer.valueOf(this.f6173d), this.f6170a);
    }

    public String toString() {
        return "ActivityRecognitionResult [probableActivities=" + this.f6170a + ", timeMillis=" + this.f6171b + ", elapsedRealtimeMillis=" + this.f6172c + "]";
    }

    public void writeToParcel(Parcel parcel, int i) {
        ActivityRecognitionResultCreator.m9332a(this, parcel, i);
    }
}
