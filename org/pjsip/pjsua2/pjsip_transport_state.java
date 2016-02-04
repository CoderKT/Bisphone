package org.pjsip.pjsua2;

public final class pjsip_transport_state {
    public static final pjsip_transport_state PJSIP_TP_STATE_CONNECTED;
    public static final pjsip_transport_state PJSIP_TP_STATE_DESTROY;
    public static final pjsip_transport_state PJSIP_TP_STATE_DISCONNECTED;
    public static final pjsip_transport_state PJSIP_TP_STATE_SHUTDOWN;
    private static int swigNext;
    private static pjsip_transport_state[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJSIP_TP_STATE_CONNECTED = new pjsip_transport_state("PJSIP_TP_STATE_CONNECTED");
        PJSIP_TP_STATE_DISCONNECTED = new pjsip_transport_state("PJSIP_TP_STATE_DISCONNECTED");
        PJSIP_TP_STATE_SHUTDOWN = new pjsip_transport_state("PJSIP_TP_STATE_SHUTDOWN");
        PJSIP_TP_STATE_DESTROY = new pjsip_transport_state("PJSIP_TP_STATE_DESTROY");
        swigValues = new pjsip_transport_state[]{PJSIP_TP_STATE_CONNECTED, PJSIP_TP_STATE_DISCONNECTED, PJSIP_TP_STATE_SHUTDOWN, PJSIP_TP_STATE_DESTROY};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pjsip_transport_state swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pjsip_transport_state.class + " with value " + i);
    }

    private pjsip_transport_state(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pjsip_transport_state(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsip_transport_state(String str, pjsip_transport_state org_pjsip_pjsua2_pjsip_transport_state) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pjsip_transport_state.swigValue;
        swigNext = this.swigValue + 1;
    }
}
