package com.google.android.gms.common.internal.safeparcel;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;
import org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager;

public class zza {

    public class zza extends RuntimeException {
        public zza(String str, Parcel parcel) {
            super(str + " Parcel: pos=" + parcel.dataPosition() + " size=" + parcel.dataSize());
        }
    }

    public static int m8431a(int i) {
        return InBandBytestreamManager.MAXIMUM_BLOCK_SIZE & i;
    }

    public static int m8432a(Parcel parcel) {
        return parcel.readInt();
    }

    public static int m8433a(Parcel parcel, int i) {
        return (i & -65536) != -65536 ? (i >> 16) & InBandBytestreamManager.MAXIMUM_BLOCK_SIZE : parcel.readInt();
    }

    public static <T extends Parcelable> T m8434a(Parcel parcel, int i, Creator<T> creator) {
        int a = m8433a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        Parcelable parcelable = (Parcelable) creator.createFromParcel(parcel);
        parcel.setDataPosition(a + dataPosition);
        return parcelable;
    }

    private static void m8435a(Parcel parcel, int i, int i2) {
        int a = m8433a(parcel, i);
        if (a != i2) {
            throw new zza("Expected size " + i2 + " got " + a + " (0x" + Integer.toHexString(a) + ")", parcel);
        }
    }

    private static void m8436a(Parcel parcel, int i, int i2, int i3) {
        if (i2 != i3) {
            throw new zza("Expected size " + i3 + " got " + i2 + " (0x" + Integer.toHexString(i2) + ")", parcel);
        }
    }

    public static void m8437a(Parcel parcel, int i, List list, ClassLoader classLoader) {
        int a = m8433a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a != 0) {
            parcel.readList(list, classLoader);
            parcel.setDataPosition(a + dataPosition);
        }
    }

    public static int m8438b(Parcel parcel) {
        int a = m8432a(parcel);
        int a2 = m8433a(parcel, a);
        int dataPosition = parcel.dataPosition();
        if (m8431a(a) != 20293) {
            throw new zza("Expected object header. Got 0x" + Integer.toHexString(a), parcel);
        }
        a = dataPosition + a2;
        if (a >= dataPosition && a <= parcel.dataSize()) {
            return a;
        }
        throw new zza("Size read is invalid start=" + dataPosition + " end=" + a, parcel);
    }

    public static void m8439b(Parcel parcel, int i) {
        parcel.setDataPosition(m8433a(parcel, i) + parcel.dataPosition());
    }

    public static <T> T[] m8440b(Parcel parcel, int i, Creator<T> creator) {
        int a = m8433a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        T[] createTypedArray = parcel.createTypedArray(creator);
        parcel.setDataPosition(a + dataPosition);
        return createTypedArray;
    }

    public static <T> ArrayList<T> m8441c(Parcel parcel, int i, Creator<T> creator) {
        int a = m8433a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        ArrayList<T> createTypedArrayList = parcel.createTypedArrayList(creator);
        parcel.setDataPosition(a + dataPosition);
        return createTypedArrayList;
    }

    public static boolean m8442c(Parcel parcel, int i) {
        m8435a(parcel, i, 4);
        return parcel.readInt() != 0;
    }

    public static byte m8443d(Parcel parcel, int i) {
        m8435a(parcel, i, 4);
        return (byte) parcel.readInt();
    }

    public static short m8444e(Parcel parcel, int i) {
        m8435a(parcel, i, 4);
        return (short) parcel.readInt();
    }

    public static int m8445f(Parcel parcel, int i) {
        m8435a(parcel, i, 4);
        return parcel.readInt();
    }

    public static Integer m8446g(Parcel parcel, int i) {
        int a = m8433a(parcel, i);
        if (a == 0) {
            return null;
        }
        m8436a(parcel, i, a, 4);
        return Integer.valueOf(parcel.readInt());
    }

    public static long m8447h(Parcel parcel, int i) {
        m8435a(parcel, i, 8);
        return parcel.readLong();
    }

    public static float m8448i(Parcel parcel, int i) {
        m8435a(parcel, i, 4);
        return parcel.readFloat();
    }

    public static double m8449j(Parcel parcel, int i) {
        m8435a(parcel, i, 8);
        return parcel.readDouble();
    }

    public static String m8450k(Parcel parcel, int i) {
        int a = m8433a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        String readString = parcel.readString();
        parcel.setDataPosition(a + dataPosition);
        return readString;
    }

    public static IBinder m8451l(Parcel parcel, int i) {
        int a = m8433a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        IBinder readStrongBinder = parcel.readStrongBinder();
        parcel.setDataPosition(a + dataPosition);
        return readStrongBinder;
    }

    public static Bundle m8452m(Parcel parcel, int i) {
        int a = m8433a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        Bundle readBundle = parcel.readBundle();
        parcel.setDataPosition(a + dataPosition);
        return readBundle;
    }

    public static byte[] m8453n(Parcel parcel, int i) {
        int a = m8433a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        byte[] createByteArray = parcel.createByteArray();
        parcel.setDataPosition(a + dataPosition);
        return createByteArray;
    }

    public static ArrayList<Integer> m8454o(Parcel parcel, int i) {
        int a = m8433a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        ArrayList<Integer> arrayList = new ArrayList();
        int readInt = parcel.readInt();
        for (int i2 = 0; i2 < readInt; i2++) {
            arrayList.add(Integer.valueOf(parcel.readInt()));
        }
        parcel.setDataPosition(dataPosition + a);
        return arrayList;
    }
}
