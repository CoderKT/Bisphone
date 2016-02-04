package android.support.v4.text;

import android.util.Log;
import java.lang.reflect.Method;
import java.util.Locale;

class ICUCompatIcs {
    private static Method f363a;
    private static Method f364b;

    static {
        try {
            Class cls = Class.forName("libcore.icu.ICU");
            if (cls != null) {
                f363a = cls.getMethod("getScript", new Class[]{String.class});
                f364b = cls.getMethod("addLikelySubtags", new Class[]{String.class});
            }
        } catch (Throwable e) {
            f363a = null;
            f364b = null;
            Log.w("ICUCompatIcs", e);
        }
    }

    public static String m708a(Locale locale) {
        String b = m709b(locale);
        if (b != null) {
            return m707a(b);
        }
        return null;
    }

    private static String m707a(String str) {
        try {
            if (f363a != null) {
                return (String) f363a.invoke(null, new Object[]{str});
            }
        } catch (Throwable e) {
            Log.w("ICUCompatIcs", e);
        } catch (Throwable e2) {
            Log.w("ICUCompatIcs", e2);
        }
        return null;
    }

    private static String m709b(Locale locale) {
        String locale2 = locale.toString();
        try {
            if (f364b != null) {
                return (String) f364b.invoke(null, new Object[]{locale2});
            }
        } catch (Throwable e) {
            Log.w("ICUCompatIcs", e);
        } catch (Throwable e2) {
            Log.w("ICUCompatIcs", e2);
        }
        return locale2;
    }
}
