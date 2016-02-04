package android.support.v4.text;

import android.util.Log;
import java.lang.reflect.Method;
import java.util.Locale;

public class ICUCompatApi23 {
    private static Method f362a;

    static {
        try {
            f362a = Class.forName("libcore.icu.ICU").getMethod("addLikelySubtags", new Class[]{Locale.class});
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    public static String m706a(Locale locale) {
        try {
            return ((Locale) f362a.invoke(null, new Object[]{locale})).getScript();
        } catch (Throwable e) {
            Log.w("ICUCompatIcs", e);
            return locale.getScript();
        } catch (Throwable e2) {
            Log.w("ICUCompatIcs", e2);
            return locale.getScript();
        }
    }
}
