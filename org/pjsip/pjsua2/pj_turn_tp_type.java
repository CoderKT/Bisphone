package org.pjsip.pjsua2;

public final class pj_turn_tp_type {
    public static final pj_turn_tp_type PJ_TURN_TP_TCP;
    public static final pj_turn_tp_type PJ_TURN_TP_TLS;
    public static final pj_turn_tp_type PJ_TURN_TP_UDP;
    private static int swigNext;
    private static pj_turn_tp_type[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJ_TURN_TP_UDP = new pj_turn_tp_type("PJ_TURN_TP_UDP", pjsua2JNI.PJ_TURN_TP_UDP_get());
        PJ_TURN_TP_TCP = new pj_turn_tp_type("PJ_TURN_TP_TCP", pjsua2JNI.PJ_TURN_TP_TCP_get());
        PJ_TURN_TP_TLS = new pj_turn_tp_type("PJ_TURN_TP_TLS", pjsua2JNI.PJ_TURN_TP_TLS_get());
        swigValues = new pj_turn_tp_type[]{PJ_TURN_TP_UDP, PJ_TURN_TP_TCP, PJ_TURN_TP_TLS};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pj_turn_tp_type swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pj_turn_tp_type.class + " with value " + i);
    }

    private pj_turn_tp_type(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pj_turn_tp_type(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pj_turn_tp_type(String str, pj_turn_tp_type org_pjsip_pjsua2_pj_turn_tp_type) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pj_turn_tp_type.swigValue;
        swigNext = this.swigValue + 1;
    }
}
