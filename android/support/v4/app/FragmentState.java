package android.support.v4.app;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Log;

/* compiled from: Fragment */
final class FragmentState implements Parcelable {
    public static final Creator<FragmentState> CREATOR;
    final String f210a;
    final int f211b;
    final boolean f212c;
    final int f213d;
    final int f214e;
    final String f215f;
    final boolean f216g;
    final boolean f217h;
    final Bundle f218i;
    Bundle f219j;
    Fragment f220k;

    /* renamed from: android.support.v4.app.FragmentState.1 */
    final class Fragment implements Creator<FragmentState> {
        Fragment() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m445a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m446a(i);
        }

        public FragmentState m445a(Parcel parcel) {
            return new FragmentState(parcel);
        }

        public FragmentState[] m446a(int i) {
            return new FragmentState[i];
        }
    }

    public FragmentState(Fragment fragment) {
        this.f210a = fragment.getClass().getName();
        this.f211b = fragment.f114p;
        this.f212c = fragment.f123y;
        this.f213d = fragment.f89G;
        this.f214e = fragment.f90H;
        this.f215f = fragment.f91I;
        this.f216g = fragment.f94L;
        this.f217h = fragment.f93K;
        this.f218i = fragment.f116r;
    }

    public FragmentState(Parcel parcel) {
        boolean z;
        boolean z2 = true;
        this.f210a = parcel.readString();
        this.f211b = parcel.readInt();
        this.f212c = parcel.readInt() != 0;
        this.f213d = parcel.readInt();
        this.f214e = parcel.readInt();
        this.f215f = parcel.readString();
        if (parcel.readInt() != 0) {
            z = true;
        } else {
            z = false;
        }
        this.f216g = z;
        if (parcel.readInt() == 0) {
            z2 = false;
        }
        this.f217h = z2;
        this.f218i = parcel.readBundle();
        this.f219j = parcel.readBundle();
    }

    public Fragment m447a(FragmentHostCallback fragmentHostCallback, Fragment fragment) {
        if (this.f220k != null) {
            return this.f220k;
        }
        Context g = fragmentHostCallback.m285g();
        if (this.f218i != null) {
            this.f218i.setClassLoader(g.getClassLoader());
        }
        this.f220k = Fragment.m169a(g, this.f210a, this.f218i);
        if (this.f219j != null) {
            this.f219j.setClassLoader(g.getClassLoader());
            this.f220k.f112n = this.f219j;
        }
        this.f220k.m186a(this.f211b, fragment);
        this.f220k.f123y = this.f212c;
        this.f220k.f83A = true;
        this.f220k.f89G = this.f213d;
        this.f220k.f90H = this.f214e;
        this.f220k.f91I = this.f215f;
        this.f220k.f94L = this.f216g;
        this.f220k.f93K = this.f217h;
        this.f220k.f85C = fragmentHostCallback.f140d;
        if (FragmentManagerImpl.f178a) {
            Log.v("FragmentManager", "Instantiated fragment " + this.f220k);
        }
        return this.f220k;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2;
        int i3 = 1;
        parcel.writeString(this.f210a);
        parcel.writeInt(this.f211b);
        parcel.writeInt(this.f212c ? 1 : 0);
        parcel.writeInt(this.f213d);
        parcel.writeInt(this.f214e);
        parcel.writeString(this.f215f);
        if (this.f216g) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (!this.f217h) {
            i3 = 0;
        }
        parcel.writeInt(i3);
        parcel.writeBundle(this.f218i);
        parcel.writeBundle(this.f219j);
    }

    static {
        CREATOR = new Fragment();
    }
}
