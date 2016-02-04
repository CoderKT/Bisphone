package com.fasterxml.jackson.core;

public final class Base64Variants {
    public static final Base64Variant MIME;
    public static final Base64Variant MIME_NO_LINEFEEDS;
    public static final Base64Variant MODIFIED_FOR_URL;
    public static final Base64Variant PEM;

    static {
        MIME = new Base64Variant("MIME", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '=', 76);
        MIME_NO_LINEFEEDS = new Base64Variant(MIME, "MIME-NO-LINEFEEDS", Integer.MAX_VALUE);
        PEM = new Base64Variant(MIME, "PEM", true, '=', 64);
        StringBuilder stringBuilder = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");
        stringBuilder.setCharAt(stringBuilder.indexOf("+"), '-');
        stringBuilder.setCharAt(stringBuilder.indexOf("/"), '_');
        MODIFIED_FOR_URL = new Base64Variant("MODIFIED-FOR-URL", stringBuilder.toString(), false, '\u0000', Integer.MAX_VALUE);
    }

    public static Base64Variant getDefaultVariant() {
        return MIME_NO_LINEFEEDS;
    }
}
