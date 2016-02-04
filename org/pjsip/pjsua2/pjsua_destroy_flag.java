package org.pjsip.pjsua2;

public final class pjsua_destroy_flag {
    public static final pjsua_destroy_flag PJSUA_DESTROY_NO_NETWORK;
    public static final pjsua_destroy_flag PJSUA_DESTROY_NO_RX_MSG;
    public static final pjsua_destroy_flag PJSUA_DESTROY_NO_TX_MSG;
    private static int swigNext;
    private static pjsua_destroy_flag[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJSUA_DESTROY_NO_RX_MSG = new pjsua_destroy_flag("PJSUA_DESTROY_NO_RX_MSG", pjsua2JNI.PJSUA_DESTROY_NO_RX_MSG_get());
        PJSUA_DESTROY_NO_TX_MSG = new pjsua_destroy_flag("PJSUA_DESTROY_NO_TX_MSG", pjsua2JNI.PJSUA_DESTROY_NO_TX_MSG_get());
        PJSUA_DESTROY_NO_NETWORK = new pjsua_destroy_flag("PJSUA_DESTROY_NO_NETWORK", pjsua2JNI.PJSUA_DESTROY_NO_NETWORK_get());
        swigValues = new pjsua_destroy_flag[]{PJSUA_DESTROY_NO_RX_MSG, PJSUA_DESTROY_NO_TX_MSG, PJSUA_DESTROY_NO_NETWORK};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pjsua_destroy_flag swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pjsua_destroy_flag.class + " with value " + i);
    }

    private pjsua_destroy_flag(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pjsua_destroy_flag(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsua_destroy_flag(String str, pjsua_destroy_flag org_pjsip_pjsua2_pjsua_destroy_flag) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pjsua_destroy_flag.swigValue;
        swigNext = this.swigValue + 1;
    }
}
