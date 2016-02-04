package android.support.v4.text;

import android.os.Build.VERSION;
import java.util.Locale;
import se.emilsjolander.stickylistheaders.C1128R;

public class TextUtilsCompat {
    public static final Locale f365a;
    private static final TextUtilsCompatImpl f366b;
    private static String f367c;
    private static String f368d;

    class TextUtilsCompatImpl {
        private TextUtilsCompatImpl() {
        }

        public int m711a(Locale locale) {
            if (!(locale == null || locale.equals(TextUtilsCompat.f365a))) {
                String a = ICUCompat.m705a(locale);
                if (a == null) {
                    return m710b(locale);
                }
                if (a.equalsIgnoreCase(TextUtilsCompat.f367c) || a.equalsIgnoreCase(TextUtilsCompat.f368d)) {
                    return 1;
                }
            }
            return 0;
        }

        private static int m710b(Locale locale) {
            switch (Character.getDirectionality(locale.getDisplayName(locale).charAt(0))) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    return 1;
                default:
                    return 0;
            }
        }
    }

    class TextUtilsCompatJellybeanMr1Impl extends TextUtilsCompatImpl {
        private TextUtilsCompatJellybeanMr1Impl() {
            super();
        }

        public int m712a(Locale locale) {
            return TextUtilsCompatJellybeanMr1.m716a(locale);
        }
    }

    static {
        if (VERSION.SDK_INT >= 17) {
            f366b = new TextUtilsCompatJellybeanMr1Impl();
        } else {
            f366b = new TextUtilsCompatImpl();
        }
        f365a = new Locale("", "");
        f367c = "Arab";
        f368d = "Hebr";
    }

    public static int m713a(Locale locale) {
        return f366b.m711a(locale);
    }
}
