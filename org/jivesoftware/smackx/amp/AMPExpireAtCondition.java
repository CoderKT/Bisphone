package org.jivesoftware.smackx.amp;

import java.util.Date;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smackx.amp.packet.AMPExtension.Condition;
import org.jxmpp.util.XmppDateTime;

public class AMPExpireAtCondition implements Condition {
    public static final String NAME = "expire-at";
    private final String value;

    public static boolean isSupported(XMPPConnection xMPPConnection) {
        return AMPManager.isConditionSupported(xMPPConnection, NAME);
    }

    public AMPExpireAtCondition(Date date) {
        if (date == null) {
            throw new NullPointerException("Can't create AMPExpireAtCondition with null value");
        }
        this.value = XmppDateTime.m13428a(date);
    }

    public AMPExpireAtCondition(String str) {
        if (str == null) {
            throw new NullPointerException("Can't create AMPExpireAtCondition with null value");
        }
        this.value = str;
    }

    public String getName() {
        return NAME;
    }

    public String getValue() {
        return this.value;
    }
}
