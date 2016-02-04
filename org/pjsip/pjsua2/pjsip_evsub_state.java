package org.pjsip.pjsua2;

public final class pjsip_evsub_state {
    public static final pjsip_evsub_state PJSIP_EVSUB_STATE_ACCEPTED;
    public static final pjsip_evsub_state PJSIP_EVSUB_STATE_ACTIVE;
    public static final pjsip_evsub_state PJSIP_EVSUB_STATE_NULL;
    public static final pjsip_evsub_state PJSIP_EVSUB_STATE_PENDING;
    public static final pjsip_evsub_state PJSIP_EVSUB_STATE_SENT;
    public static final pjsip_evsub_state PJSIP_EVSUB_STATE_TERMINATED;
    public static final pjsip_evsub_state PJSIP_EVSUB_STATE_UNKNOWN;
    private static int swigNext;
    private static pjsip_evsub_state[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJSIP_EVSUB_STATE_NULL = new pjsip_evsub_state("PJSIP_EVSUB_STATE_NULL");
        PJSIP_EVSUB_STATE_SENT = new pjsip_evsub_state("PJSIP_EVSUB_STATE_SENT");
        PJSIP_EVSUB_STATE_ACCEPTED = new pjsip_evsub_state("PJSIP_EVSUB_STATE_ACCEPTED");
        PJSIP_EVSUB_STATE_PENDING = new pjsip_evsub_state("PJSIP_EVSUB_STATE_PENDING");
        PJSIP_EVSUB_STATE_ACTIVE = new pjsip_evsub_state("PJSIP_EVSUB_STATE_ACTIVE");
        PJSIP_EVSUB_STATE_TERMINATED = new pjsip_evsub_state("PJSIP_EVSUB_STATE_TERMINATED");
        PJSIP_EVSUB_STATE_UNKNOWN = new pjsip_evsub_state("PJSIP_EVSUB_STATE_UNKNOWN");
        swigValues = new pjsip_evsub_state[]{PJSIP_EVSUB_STATE_NULL, PJSIP_EVSUB_STATE_SENT, PJSIP_EVSUB_STATE_ACCEPTED, PJSIP_EVSUB_STATE_PENDING, PJSIP_EVSUB_STATE_ACTIVE, PJSIP_EVSUB_STATE_TERMINATED, PJSIP_EVSUB_STATE_UNKNOWN};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pjsip_evsub_state swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pjsip_evsub_state.class + " with value " + i);
    }

    private pjsip_evsub_state(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pjsip_evsub_state(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsip_evsub_state(String str, pjsip_evsub_state org_pjsip_pjsua2_pjsip_evsub_state) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pjsip_evsub_state.swigValue;
        swigNext = this.swigValue + 1;
    }
}
