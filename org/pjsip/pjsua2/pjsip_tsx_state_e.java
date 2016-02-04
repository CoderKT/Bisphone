package org.pjsip.pjsua2;

public final class pjsip_tsx_state_e {
    public static final pjsip_tsx_state_e PJSIP_TSX_STATE_CALLING;
    public static final pjsip_tsx_state_e PJSIP_TSX_STATE_COMPLETED;
    public static final pjsip_tsx_state_e PJSIP_TSX_STATE_CONFIRMED;
    public static final pjsip_tsx_state_e PJSIP_TSX_STATE_DESTROYED;
    public static final pjsip_tsx_state_e PJSIP_TSX_STATE_MAX;
    public static final pjsip_tsx_state_e PJSIP_TSX_STATE_NULL;
    public static final pjsip_tsx_state_e PJSIP_TSX_STATE_PROCEEDING;
    public static final pjsip_tsx_state_e PJSIP_TSX_STATE_TERMINATED;
    public static final pjsip_tsx_state_e PJSIP_TSX_STATE_TRYING;
    private static int swigNext;
    private static pjsip_tsx_state_e[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJSIP_TSX_STATE_NULL = new pjsip_tsx_state_e("PJSIP_TSX_STATE_NULL");
        PJSIP_TSX_STATE_CALLING = new pjsip_tsx_state_e("PJSIP_TSX_STATE_CALLING");
        PJSIP_TSX_STATE_TRYING = new pjsip_tsx_state_e("PJSIP_TSX_STATE_TRYING");
        PJSIP_TSX_STATE_PROCEEDING = new pjsip_tsx_state_e("PJSIP_TSX_STATE_PROCEEDING");
        PJSIP_TSX_STATE_COMPLETED = new pjsip_tsx_state_e("PJSIP_TSX_STATE_COMPLETED");
        PJSIP_TSX_STATE_CONFIRMED = new pjsip_tsx_state_e("PJSIP_TSX_STATE_CONFIRMED");
        PJSIP_TSX_STATE_TERMINATED = new pjsip_tsx_state_e("PJSIP_TSX_STATE_TERMINATED");
        PJSIP_TSX_STATE_DESTROYED = new pjsip_tsx_state_e("PJSIP_TSX_STATE_DESTROYED");
        PJSIP_TSX_STATE_MAX = new pjsip_tsx_state_e("PJSIP_TSX_STATE_MAX");
        swigValues = new pjsip_tsx_state_e[]{PJSIP_TSX_STATE_NULL, PJSIP_TSX_STATE_CALLING, PJSIP_TSX_STATE_TRYING, PJSIP_TSX_STATE_PROCEEDING, PJSIP_TSX_STATE_COMPLETED, PJSIP_TSX_STATE_CONFIRMED, PJSIP_TSX_STATE_TERMINATED, PJSIP_TSX_STATE_DESTROYED, PJSIP_TSX_STATE_MAX};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pjsip_tsx_state_e swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pjsip_tsx_state_e.class + " with value " + i);
    }

    private pjsip_tsx_state_e(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pjsip_tsx_state_e(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsip_tsx_state_e(String str, pjsip_tsx_state_e org_pjsip_pjsua2_pjsip_tsx_state_e) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pjsip_tsx_state_e.swigValue;
        swigNext = this.swigValue + 1;
    }
}
