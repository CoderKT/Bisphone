package org.pjsip.pjsua2;

public final class pjsua_state {
    public static final pjsua_state PJSUA_STATE_CLOSING;
    public static final pjsua_state PJSUA_STATE_CREATED;
    public static final pjsua_state PJSUA_STATE_INIT;
    public static final pjsua_state PJSUA_STATE_NULL;
    public static final pjsua_state PJSUA_STATE_RUNNING;
    public static final pjsua_state PJSUA_STATE_STARTING;
    private static int swigNext;
    private static pjsua_state[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJSUA_STATE_NULL = new pjsua_state("PJSUA_STATE_NULL");
        PJSUA_STATE_CREATED = new pjsua_state("PJSUA_STATE_CREATED");
        PJSUA_STATE_INIT = new pjsua_state("PJSUA_STATE_INIT");
        PJSUA_STATE_STARTING = new pjsua_state("PJSUA_STATE_STARTING");
        PJSUA_STATE_RUNNING = new pjsua_state("PJSUA_STATE_RUNNING");
        PJSUA_STATE_CLOSING = new pjsua_state("PJSUA_STATE_CLOSING");
        swigValues = new pjsua_state[]{PJSUA_STATE_NULL, PJSUA_STATE_CREATED, PJSUA_STATE_INIT, PJSUA_STATE_STARTING, PJSUA_STATE_RUNNING, PJSUA_STATE_CLOSING};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pjsua_state swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pjsua_state.class + " with value " + i);
    }

    private pjsua_state(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pjsua_state(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsua_state(String str, pjsua_state org_pjsip_pjsua2_pjsua_state) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pjsua_state.swigValue;
        swigNext = this.swigValue + 1;
    }
}
