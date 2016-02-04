package app.localization;

import android.content.res.Configuration;
import java.util.Locale;

public class Localize {
    private static Configuration f3117a;
    private static Language f3118b;
    private static BisPhoneCalender f3119c;

    static {
        f3117a = null;
        f3118b = Language.ENGLISH;
        f3119c = BisPhoneCalender.DEFAULT;
    }

    public static Configuration m5600a() {
        m5601b();
        m5604e();
        return f3117a;
    }

    public static void m5601b() {
        if (f3117a == null) {
            f3117a = new Configuration();
        }
        f3117a.locale = Locale.getDefault();
        f3118b = Language.m5599a(f3117a.locale);
        f3117a.locale = Locale.getDefault();
    }

    public static BisPhoneCalender m5602c() {
        return f3119c;
    }

    private static void m5604e() {
        if (f3117a == null) {
            return;
        }
        if (f3117a.locale.getLanguage().contains("fa")) {
            f3119c = BisPhoneCalender.HEJRI_SHAMSI;
        } else {
            f3119c = BisPhoneCalender.DEFAULT;
        }
    }

    public static Language m5603d() {
        return f3118b;
    }
}
