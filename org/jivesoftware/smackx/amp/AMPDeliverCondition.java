package org.jivesoftware.smackx.amp;

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smackx.amp.packet.AMPExtension.Condition;

public class AMPDeliverCondition implements Condition {
    public static final String NAME = "deliver";
    private final Value value;

    public enum Value {
        direct,
        forward,
        gateway,
        none,
        stored
    }

    public static boolean isSupported(XMPPConnection xMPPConnection) {
        return AMPManager.isConditionSupported(xMPPConnection, NAME);
    }

    public AMPDeliverCondition(Value value) {
        if (value == null) {
            throw new NullPointerException("Can't create AMPDeliverCondition with null value");
        }
        this.value = value;
    }

    public String getName() {
        return NAME;
    }

    public String getValue() {
        return this.value.toString();
    }
}
