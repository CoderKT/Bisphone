package app.util;

import app.common.Constants;
import app.common.entity.HistoryEntity.Type;
import app.storage.Storage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import se.emilsjolander.stickylistheaders.C1128R;

public class Utils {

    /* renamed from: app.util.Utils.1 */
    /* synthetic */ class C05071 {
        static final /* synthetic */ int[] f4623a;

        static {
            f4623a = new int[Type.values().length];
            try {
                f4623a[Type.AUDIO.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f4623a[Type.VIDEO.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f4623a[Type.PHOTO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    @Deprecated
    public static String m7078a(String str) {
        return str + "@" + "bisphone.com";
    }

    public static String m7082b(String str) {
        return Constants.m3960e() + "?token=" + str;
    }

    public static boolean m7081a(double d, double d2, double d3) {
        return d == d2 || Math.abs(d - d2) < d3;
    }

    public static String m7079a(String str, Type type) {
        switch (C05071.f4623a[type.ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                return Storage.m6948e() + File.separator + str;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                return Storage.m6944c() + File.separator + str;
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                return Storage.m6941b() + File.separator + str;
            default:
                throw new IllegalStateException("type parameter " + type.toString() + "is not defined");
        }
    }

    public static String m7080a(ArrayList<String> arrayList) {
        Collections.sort(arrayList);
        Iterator it = arrayList.iterator();
        String str = "";
        while (it.hasNext()) {
            str = str + ((String) it.next()) + ",";
        }
        return "b_" + str.substring(0, str.length() - 1).hashCode();
    }

    public static String m7077a(double d) {
        if (d == 0.0d) {
            return "";
        }
        if (d < 1000.0d) {
            return ((int) d) + "";
        }
        return new DecimalFormat("##.#").format(d / 1000.0d) + "K";
    }
}
