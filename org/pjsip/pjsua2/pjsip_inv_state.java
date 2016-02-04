package org.pjsip.pjsua2;

public final class pjsip_inv_state {
    public static final pjsip_inv_state PJSIP_INV_STATE_CALLING;
    public static final pjsip_inv_state PJSIP_INV_STATE_CONFIRMED;
    public static final pjsip_inv_state PJSIP_INV_STATE_CONNECTING;
    public static final pjsip_inv_state PJSIP_INV_STATE_DISCONNECTED;
    public static final pjsip_inv_state PJSIP_INV_STATE_EARLY;
    public static final pjsip_inv_state PJSIP_INV_STATE_INCOMING;
    public static final pjsip_inv_state PJSIP_INV_STATE_NULL;
    private static int swigNext;
    private static pjsip_inv_state[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJSIP_INV_STATE_NULL = new pjsip_inv_state("PJSIP_INV_STATE_NULL");
        PJSIP_INV_STATE_CALLING = new pjsip_inv_state("PJSIP_INV_STATE_CALLING");
        PJSIP_INV_STATE_INCOMING = new pjsip_inv_state("PJSIP_INV_STATE_INCOMING");
        PJSIP_INV_STATE_EARLY = new pjsip_inv_state("PJSIP_INV_STATE_EARLY");
        PJSIP_INV_STATE_CONNECTING = new pjsip_inv_state("PJSIP_INV_STATE_CONNECTING");
        PJSIP_INV_STATE_CONFIRMED = new pjsip_inv_state("PJSIP_INV_STATE_CONFIRMED");
        PJSIP_INV_STATE_DISCONNECTED = new pjsip_inv_state("PJSIP_INV_STATE_DISCONNECTED");
        swigValues = new pjsip_inv_state[]{PJSIP_INV_STATE_NULL, PJSIP_INV_STATE_CALLING, PJSIP_INV_STATE_INCOMING, PJSIP_INV_STATE_EARLY, PJSIP_INV_STATE_CONNECTING, PJSIP_INV_STATE_CONFIRMED, PJSIP_INV_STATE_DISCONNECTED};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pjsip_inv_state swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pjsip_inv_state.class + " with value " + i);
    }

    private pjsip_inv_state(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pjsip_inv_state(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsip_inv_state(String str, pjsip_inv_state org_pjsip_pjsua2_pjsip_inv_state) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pjsip_inv_state.swigValue;
        swigNext = this.swigValue + 1;
    }
}
