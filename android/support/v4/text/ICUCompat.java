package android.support.v4.text;

import android.os.Build.VERSION;
import java.util.Locale;

public class ICUCompat {
    private static final ICUCompatImpl f361a;

    interface ICUCompatImpl {
        String m701a(Locale locale);
    }

    class ICUCompatImplBase implements ICUCompatImpl {
        ICUCompatImplBase() {
        }

        public String m702a(Locale locale) {
            return null;
        }
    }

    class ICUCompatImplIcs implements ICUCompatImpl {
        ICUCompatImplIcs() {
        }

        public String m703a(Locale locale) {
            return ICUCompatIcs.m708a(locale);
        }
    }

    class ICUCompatImplLollipop implements ICUCompatImpl {
        ICUCompatImplLollipop() {
        }

        public String m704a(Locale locale) {
            return ICUCompatApi23.m706a(locale);
        }
    }

    static {
        int i = VERSION.SDK_INT;
        if (i >= 21) {
            f361a = new ICUCompatImplLollipop();
        } else if (i >= 14) {
            f361a = new ICUCompatImplIcs();
        } else {
            f361a = new ICUCompatImplBase();
        }
    }

    public static String m705a(Locale locale) {
        return f361a.m701a(locale);
    }
}
