package app.localization;

import java.util.Locale;

public enum Language {
    PERSIAN,
    ENGLISH,
    ARABIC;

    public static Language m5599a(Locale locale) {
        String language = locale.getLanguage();
        if (language.contains("fa")) {
            return PERSIAN;
        }
        if (language.contains("ar")) {
            return ARABIC;
        }
        return ENGLISH;
    }
}
