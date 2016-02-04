package com.google.android.gms.common.internal.safeparcel;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;
import org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager;

public class zzb {
    public static int m8455a(Parcel parcel) {
        return m8473b(parcel, 20293);
    }

    public static void m8456a(Parcel parcel, int i) {
        m8476c(parcel, i);
    }

    public static void m8457a(Parcel parcel, int i, byte b) {
        m8474b(parcel, i, 4);
        parcel.writeInt(b);
    }

    public static void m8458a(Parcel parcel, int i, double d) {
        m8474b(parcel, i, 8);
        parcel.writeDouble(d);
    }

    public static void m8459a(Parcel parcel, int i, float f) {
        m8474b(parcel, i, 4);
        parcel.writeFloat(f);
    }

    public static void m8460a(Parcel parcel, int i, int i2) {
        m8474b(parcel, i, 4);
        parcel.writeInt(i2);
    }

    public static void m8461a(Parcel parcel, int i, long j) {
        m8474b(parcel, i, 8);
        parcel.writeLong(j);
    }

    public static void m8462a(Parcel parcel, int i, Bundle bundle, boolean z) {
        if (bundle != null) {
            int b = m8473b(parcel, i);
            parcel.writeBundle(bundle);
            m8476c(parcel, b);
        } else if (z) {
            m8474b(parcel, i, 0);
        }
    }

    public static void m8463a(Parcel parcel, int i, IBinder iBinder, boolean z) {
        if (iBinder != null) {
            int b = m8473b(parcel, i);
            parcel.writeStrongBinder(iBinder);
            m8476c(parcel, b);
        } else if (z) {
            m8474b(parcel, i, 0);
        }
    }

    public static void m8464a(Parcel parcel, int i, Parcelable parcelable, int i2, boolean z) {
        if (parcelable != null) {
            int b = m8473b(parcel, i);
            parcelable.writeToParcel(parcel, i2);
            m8476c(parcel, b);
        } else if (z) {
            m8474b(parcel, i, 0);
        }
    }

    public static void m8465a(Parcel parcel, int i, Integer num, boolean z) {
        if (num != null) {
            m8474b(parcel, i, 4);
            parcel.writeInt(num.intValue());
        } else if (z) {
            m8474b(parcel, i, 0);
        }
    }

    public static void m8466a(Parcel parcel, int i, String str, boolean z) {
        if (str != null) {
            int b = m8473b(parcel, i);
            parcel.writeString(str);
            m8476c(parcel, b);
        } else if (z) {
            m8474b(parcel, i, 0);
        }
    }

    public static void m8467a(Parcel parcel, int i, List<Integer> list, boolean z) {
        if (list != null) {
            int b = m8473b(parcel, i);
            int size = list.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                parcel.writeInt(((Integer) list.get(i2)).intValue());
            }
            m8476c(parcel, b);
        } else if (z) {
            m8474b(parcel, i, 0);
        }
    }

    public static void m8468a(Parcel parcel, int i, short s) {
        m8474b(parcel, i, 4);
        parcel.writeInt(s);
    }

    public static void m8469a(Parcel parcel, int i, boolean z) {
        m8474b(parcel, i, 4);
        parcel.writeInt(z ? 1 : 0);
    }

    public static void m8470a(Parcel parcel, int i, byte[] bArr, boolean z) {
        if (bArr != null) {
            int b = m8473b(parcel, i);
            parcel.writeByteArray(bArr);
            m8476c(parcel, b);
        } else if (z) {
            m8474b(parcel, i, 0);
        }
    }

    public static <T extends Parcelable> void m8471a(Parcel parcel, int i, T[] tArr, int i2, boolean z) {
        if (tArr != null) {
            int b = m8473b(parcel, i);
            parcel.writeInt(r3);
            for (Parcelable parcelable : tArr) {
                if (parcelable == null) {
                    parcel.writeInt(0);
                } else {
                    m8472a(parcel, parcelable, i2);
                }
            }
            m8476c(parcel, b);
        } else if (z) {
            m8474b(parcel, i, 0);
        }
    }

    private static <T extends Parcelable> void m8472a(Parcel parcel, T t, int i) {
        int dataPosition = parcel.dataPosition();
        parcel.writeInt(1);
        int dataPosition2 = parcel.dataPosition();
        t.writeToParcel(parcel, i);
        int dataPosition3 = parcel.dataPosition();
        parcel.setDataPosition(dataPosition);
        parcel.writeInt(dataPosition3 - dataPosition2);
        parcel.setDataPosition(dataPosition3);
    }

    private static int m8473b(Parcel parcel, int i) {
        parcel.writeInt(-65536 | i);
        parcel.writeInt(0);
        return parcel.dataPosition();
    }

    private static void m8474b(Parcel parcel, int i, int i2) {
        if (i2 >= InBandBytestreamManager.MAXIMUM_BLOCK_SIZE) {
            parcel.writeInt(-65536 | i);
            parcel.writeInt(i2);
            return;
        }
        parcel.writeInt((i2 << 16) | i);
    }

    public static <T extends Parcelable> void m8475b(Parcel parcel, int i, List<T> list, boolean z) {
        if (list != null) {
            int b = m8473b(parcel, i);
            int size = list.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                Parcelable parcelable = (Parcelable) list.get(i2);
                if (parcelable == null) {
                    parcel.writeInt(0);
                } else {
                    m8472a(parcel, parcelable, 0);
                }
            }
            m8476c(parcel, b);
        } else if (z) {
            m8474b(parcel, i, 0);
        }
    }

    private static void m8476c(Parcel parcel, int i) {
        int dataPosition = parcel.dataPosition();
        int i2 = dataPosition - i;
        parcel.setDataPosition(i - 4);
        parcel.writeInt(i2);
        parcel.setDataPosition(dataPosition);
    }

    public static void m8477c(Parcel parcel, int i, List list, boolean z) {
        if (list != null) {
            int b = m8473b(parcel, i);
            parcel.writeList(list);
            m8476c(parcel, b);
        } else if (z) {
            m8474b(parcel, i, 0);
        }
    }
}
