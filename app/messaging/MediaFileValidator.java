package app.messaging;

import app.Main;
import java.io.File;

public class MediaFileValidator {
    public static boolean m5897a(String str) {
        return m5896a(new File(str));
    }

    public static boolean m5896a(File file) {
        if (m5899c(file) && m5898b(file)) {
            return true;
        }
        return false;
    }

    private static boolean m5898b(File file) {
        if (file.length() <= 20971520) {
            return true;
        }
        Main.m3905a(Main.f1927b.getString(2131296845));
        return false;
    }

    private static boolean m5899c(File file) {
        if (file.exists()) {
            return true;
        }
        Main.m3905a(Main.f1927b.getString(2131296835));
        return false;
    }
}
