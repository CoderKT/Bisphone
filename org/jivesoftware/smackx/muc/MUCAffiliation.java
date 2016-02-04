package org.jivesoftware.smackx.muc;

import java.util.Locale;

public enum MUCAffiliation {
    owner,
    admin,
    member,
    outcast,
    none;

    public static MUCAffiliation fromString(String str) {
        if (str == null) {
            return null;
        }
        return valueOf(str.toLowerCase(Locale.US));
    }
}
