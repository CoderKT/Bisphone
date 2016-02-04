package org.jivesoftware.smack.packet.id;

import java.util.concurrent.atomic.AtomicLong;
import org.jivesoftware.smack.util.StringUtils;

public class StanzaIdUtil {
    private static final AtomicLong ID;
    private static final String PREFIX;

    static {
        PREFIX = StringUtils.randomString(5) + "-";
        ID = new AtomicLong();
    }

    public static String newStanzaId() {
        return PREFIX + Long.toString(ID.incrementAndGet());
    }
}
