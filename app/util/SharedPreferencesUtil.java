package app.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import app.Main;

public class SharedPreferencesUtil {
    private static SharedPreferences f4619a;

    static {
        f4619a = PreferenceManager.getDefaultSharedPreferences(Main.f1927b);
    }

    public static boolean m7055a(String str, String str2) {
        return f4619a.edit().putString(str, str2).commit();
    }

    public static boolean m7054a(String str, Boolean bool) {
        return f4619a.edit().putBoolean(str, bool.booleanValue()).commit();
    }

    public static boolean m7053a(String str, int i) {
        return f4619a.edit().putInt(str, i).commit();
    }

    public static String m7052a(String str) {
        return m7058b(str, null);
    }

    public static String m7058b(String str, String str2) {
        return f4619a.getString(str, str2);
    }

    public static boolean m7056a(String str, boolean z) {
        return f4619a.getBoolean(str, z);
    }

    public static boolean m7060b(String str, Boolean bool) {
        return f4619a.getBoolean(str, bool.booleanValue());
    }

    public static int m7057b(String str, int i) {
        return f4619a.getInt(str, i);
    }

    public static String m7051a(int i, Context context) {
        return f4619a.getString(context.getString(i), null);
    }

    public static boolean m7059b(String str) {
        return f4619a.edit().remove(str).commit();
    }

    public static boolean m7061c(String str) {
        return f4619a.contains(str);
    }

    public static Boolean m7050a() {
        return Boolean.valueOf(f4619a.edit().clear().commit());
    }
}
