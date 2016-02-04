package org.jivesoftware.smackx.muc;

import java.util.Locale;

public enum MUCRole {
    moderator,
    none,
    participant,
    visitor;

    public static MUCRole fromString(String str) {
        if (str == null) {
            return null;
        }
        return valueOf(str.toLowerCase(Locale.US));
    }
}
