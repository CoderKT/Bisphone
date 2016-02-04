package cz.msebera.android.httpclient.client.utils;

import cz.msebera.android.httpclient.util.Args;
import java.lang.ref.SoftReference;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public final class DateUtils {
    public static final TimeZone f7305a;
    private static final String[] f7306b;
    private static final Date f7307c;

    final class DateFormatHolder {
        private static final ThreadLocal<SoftReference<Map<String, SimpleDateFormat>>> f7304a;

        /* renamed from: cz.msebera.android.httpclient.client.utils.DateUtils.DateFormatHolder.1 */
        final class C09341 extends ThreadLocal<SoftReference<Map<String, SimpleDateFormat>>> {
            C09341() {
            }

            protected /* synthetic */ Object initialValue() {
                return m11566a();
            }

            protected SoftReference<Map<String, SimpleDateFormat>> m11566a() {
                return new SoftReference(new HashMap());
            }
        }

        static {
            f7304a = new C09341();
        }

        public static SimpleDateFormat m11567a(String str) {
            Map map;
            Map map2 = (Map) ((SoftReference) f7304a.get()).get();
            if (map2 == null) {
                HashMap hashMap = new HashMap();
                f7304a.set(new SoftReference(hashMap));
                map = hashMap;
            } else {
                map = map2;
            }
            SimpleDateFormat simpleDateFormat = (SimpleDateFormat) map.get(str);
            if (simpleDateFormat != null) {
                return simpleDateFormat;
            }
            simpleDateFormat = new SimpleDateFormat(str, Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            map.put(str, simpleDateFormat);
            return simpleDateFormat;
        }
    }

    static {
        f7306b = new String[]{"EEE, dd MMM yyyy HH:mm:ss zzz", "EEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy"};
        f7305a = TimeZone.getTimeZone("GMT");
        Calendar instance = Calendar.getInstance();
        instance.setTimeZone(f7305a);
        instance.set(2000, 0, 1, 0, 0, 0);
        instance.set(14, 0);
        f7307c = instance.getTime();
    }

    public static Date m11568a(String str, String[] strArr) {
        return m11569a(str, strArr, null);
    }

    public static Date m11569a(String str, String[] strArr, Date date) {
        Args.m12722a((Object) str, "Date value");
        if (strArr == null) {
            strArr = f7306b;
        }
        if (date == null) {
            date = f7307c;
        }
        if (str.length() > 1 && str.startsWith("'") && str.endsWith("'")) {
            str = str.substring(1, str.length() - 1);
        }
        for (String a : r6) {
            SimpleDateFormat a2 = DateFormatHolder.m11567a(a);
            a2.set2DigitYearStart(date);
            ParsePosition parsePosition = new ParsePosition(0);
            Date parse = a2.parse(str, parsePosition);
            if (parsePosition.getIndex() != 0) {
                return parse;
            }
        }
        return null;
    }
}
