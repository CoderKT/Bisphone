package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;

/* compiled from: BackStackRecord */
final class BackStackState implements Parcelable {
    public static final Creator<BackStackState> CREATOR;
    final int[] f70a;
    final int f71b;
    final int f72c;
    final String f73d;
    final int f74e;
    final int f75f;
    final CharSequence f76g;
    final int f77h;
    final CharSequence f78i;
    final ArrayList<String> f79j;
    final ArrayList<String> f80k;

    /* renamed from: android.support.v4.app.BackStackState.1 */
    final class BackStackRecord implements Creator<BackStackState> {
        BackStackRecord() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m164a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m165a(i);
        }

        public BackStackState m164a(Parcel parcel) {
            return new BackStackState(parcel);
        }

        public BackStackState[] m165a(int i) {
            return new BackStackState[i];
        }
    }

    public BackStackState(BackStackRecord backStackRecord) {
        int i = 0;
        for (Op op = backStackRecord.f50c; op != null; op = op.f34a) {
            if (op.f42i != null) {
                i += op.f42i.size();
            }
        }
        this.f70a = new int[(i + (backStackRecord.f52e * 7))];
        if (backStackRecord.f59l) {
            i = 0;
            for (Op op2 = backStackRecord.f50c; op2 != null; op2 = op2.f34a) {
                int i2 = i + 1;
                this.f70a[i] = op2.f36c;
                int i3 = i2 + 1;
                this.f70a[i2] = op2.f37d != null ? op2.f37d.f114p : -1;
                int i4 = i3 + 1;
                this.f70a[i3] = op2.f38e;
                i2 = i4 + 1;
                this.f70a[i4] = op2.f39f;
                i4 = i2 + 1;
                this.f70a[i2] = op2.f40g;
                i2 = i4 + 1;
                this.f70a[i4] = op2.f41h;
                if (op2.f42i != null) {
                    int size = op2.f42i.size();
                    i4 = i2 + 1;
                    this.f70a[i2] = size;
                    i2 = 0;
                    while (i2 < size) {
                        i3 = i4 + 1;
                        this.f70a[i4] = ((Fragment) op2.f42i.get(i2)).f114p;
                        i2++;
                        i4 = i3;
                    }
                    i = i4;
                } else {
                    i = i2 + 1;
                    this.f70a[i2] = 0;
                }
            }
            this.f71b = backStackRecord.f57j;
            this.f72c = backStackRecord.f58k;
            this.f73d = backStackRecord.f61n;
            this.f74e = backStackRecord.f63p;
            this.f75f = backStackRecord.f64q;
            this.f76g = backStackRecord.f65r;
            this.f77h = backStackRecord.f66s;
            this.f78i = backStackRecord.f67t;
            this.f79j = backStackRecord.f68u;
            this.f80k = backStackRecord.f69v;
            return;
        }
        throw new IllegalStateException("Not on back stack");
    }

    public BackStackState(Parcel parcel) {
        this.f70a = parcel.createIntArray();
        this.f71b = parcel.readInt();
        this.f72c = parcel.readInt();
        this.f73d = parcel.readString();
        this.f74e = parcel.readInt();
        this.f75f = parcel.readInt();
        this.f76g = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.f77h = parcel.readInt();
        this.f78i = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.f79j = parcel.createStringArrayList();
        this.f80k = parcel.createStringArrayList();
    }

    public BackStackRecord m166a(FragmentManagerImpl fragmentManagerImpl) {
        BackStackRecord backStackRecord = new BackStackRecord(fragmentManagerImpl);
        int i = 0;
        int i2 = 0;
        while (i2 < this.f70a.length) {
            Op op = new Op();
            int i3 = i2 + 1;
            op.f36c = this.f70a[i2];
            if (FragmentManagerImpl.f178a) {
                Log.v("FragmentManager", "Instantiate " + backStackRecord + " op #" + i + " base fragment #" + this.f70a[i3]);
            }
            int i4 = i3 + 1;
            i2 = this.f70a[i3];
            if (i2 >= 0) {
                op.f37d = (Fragment) fragmentManagerImpl.f184f.get(i2);
            } else {
                op.f37d = null;
            }
            i3 = i4 + 1;
            op.f38e = this.f70a[i4];
            i4 = i3 + 1;
            op.f39f = this.f70a[i3];
            i3 = i4 + 1;
            op.f40g = this.f70a[i4];
            int i5 = i3 + 1;
            op.f41h = this.f70a[i3];
            i4 = i5 + 1;
            int i6 = this.f70a[i5];
            if (i6 > 0) {
                op.f42i = new ArrayList(i6);
                i3 = 0;
                while (i3 < i6) {
                    if (FragmentManagerImpl.f178a) {
                        Log.v("FragmentManager", "Instantiate " + backStackRecord + " set remove fragment #" + this.f70a[i4]);
                    }
                    i5 = i4 + 1;
                    op.f42i.add((Fragment) fragmentManagerImpl.f184f.get(this.f70a[i4]));
                    i3++;
                    i4 = i5;
                }
            }
            backStackRecord.m154a(op);
            i++;
            i2 = i4;
        }
        backStackRecord.f57j = this.f71b;
        backStackRecord.f58k = this.f72c;
        backStackRecord.f61n = this.f73d;
        backStackRecord.f63p = this.f74e;
        backStackRecord.f59l = true;
        backStackRecord.f64q = this.f75f;
        backStackRecord.f65r = this.f76g;
        backStackRecord.f66s = this.f77h;
        backStackRecord.f67t = this.f78i;
        backStackRecord.f68u = this.f79j;
        backStackRecord.f69v = this.f80k;
        backStackRecord.m153a(1);
        return backStackRecord;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeIntArray(this.f70a);
        parcel.writeInt(this.f71b);
        parcel.writeInt(this.f72c);
        parcel.writeString(this.f73d);
        parcel.writeInt(this.f74e);
        parcel.writeInt(this.f75f);
        TextUtils.writeToParcel(this.f76g, parcel, 0);
        parcel.writeInt(this.f77h);
        TextUtils.writeToParcel(this.f78i, parcel, 0);
        parcel.writeStringList(this.f79j);
        parcel.writeStringList(this.f80k);
    }

    static {
        CREATOR = new BackStackRecord();
    }
}
