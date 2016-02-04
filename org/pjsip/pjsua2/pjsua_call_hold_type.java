package org.pjsip.pjsua2;

public final class pjsua_call_hold_type {
    public static final pjsua_call_hold_type PJSUA_CALL_HOLD_TYPE_RFC2543;
    public static final pjsua_call_hold_type PJSUA_CALL_HOLD_TYPE_RFC3264;
    private static int swigNext;
    private static pjsua_call_hold_type[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJSUA_CALL_HOLD_TYPE_RFC3264 = new pjsua_call_hold_type("PJSUA_CALL_HOLD_TYPE_RFC3264");
        PJSUA_CALL_HOLD_TYPE_RFC2543 = new pjsua_call_hold_type("PJSUA_CALL_HOLD_TYPE_RFC2543");
        swigValues = new pjsua_call_hold_type[]{PJSUA_CALL_HOLD_TYPE_RFC3264, PJSUA_CALL_HOLD_TYPE_RFC2543};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pjsua_call_hold_type swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pjsua_call_hold_type.class + " with value " + i);
    }

    private pjsua_call_hold_type(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pjsua_call_hold_type(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsua_call_hold_type(String str, pjsua_call_hold_type org_pjsip_pjsua2_pjsua_call_hold_type) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pjsua_call_hold_type.swigValue;
        swigNext = this.swigValue + 1;
    }
}
