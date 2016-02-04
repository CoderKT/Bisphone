package app.util;

public class PhoneNumberUtil {
    public static boolean m7045a(String str, String str2) {
        String c = m7047c(str, str2);
        return c != null && c.length() >= 9 && c.length() <= 15;
    }

    public static String m7046b(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("+").append(str).append(" ").append(str2.substring(0, 3)).append("-").append(str2.substring(3, 6)).append("-").append(str2.substring(6));
        return stringBuilder.toString();
    }

    public static String m7047c(String str, String str2) {
        if (str == null || str.length() == 0 || str2 == null || str2.length() == 0) {
            return null;
        }
        boolean startsWith = str2.startsWith("+");
        String replaceAll = str2.replaceAll("[^\\d]", "");
        if (replaceAll.length() == 0 || replaceAll.equals("0") || replaceAll.equals("00")) {
            return null;
        }
        if (startsWith) {
            return replaceAll;
        }
        if (replaceAll.length() > 1 && replaceAll.substring(0, 2).equals("00")) {
            return replaceAll.substring(2);
        }
        if (replaceAll.length() <= 0 || !replaceAll.startsWith("0")) {
            return str + replaceAll;
        }
        return str + replaceAll.substring(1);
    }
}
