package org.pjsip.pjsua2;

public final class pjsip_event_id_e {
    public static final pjsip_event_id_e PJSIP_EVENT_RX_MSG;
    public static final pjsip_event_id_e PJSIP_EVENT_TIMER;
    public static final pjsip_event_id_e PJSIP_EVENT_TRANSPORT_ERROR;
    public static final pjsip_event_id_e PJSIP_EVENT_TSX_STATE;
    public static final pjsip_event_id_e PJSIP_EVENT_TX_MSG;
    public static final pjsip_event_id_e PJSIP_EVENT_UNKNOWN;
    public static final pjsip_event_id_e PJSIP_EVENT_USER;
    private static int swigNext;
    private static pjsip_event_id_e[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJSIP_EVENT_UNKNOWN = new pjsip_event_id_e("PJSIP_EVENT_UNKNOWN");
        PJSIP_EVENT_TIMER = new pjsip_event_id_e("PJSIP_EVENT_TIMER");
        PJSIP_EVENT_TX_MSG = new pjsip_event_id_e("PJSIP_EVENT_TX_MSG");
        PJSIP_EVENT_RX_MSG = new pjsip_event_id_e("PJSIP_EVENT_RX_MSG");
        PJSIP_EVENT_TRANSPORT_ERROR = new pjsip_event_id_e("PJSIP_EVENT_TRANSPORT_ERROR");
        PJSIP_EVENT_TSX_STATE = new pjsip_event_id_e("PJSIP_EVENT_TSX_STATE");
        PJSIP_EVENT_USER = new pjsip_event_id_e("PJSIP_EVENT_USER");
        swigValues = new pjsip_event_id_e[]{PJSIP_EVENT_UNKNOWN, PJSIP_EVENT_TIMER, PJSIP_EVENT_TX_MSG, PJSIP_EVENT_RX_MSG, PJSIP_EVENT_TRANSPORT_ERROR, PJSIP_EVENT_TSX_STATE, PJSIP_EVENT_USER};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pjsip_event_id_e swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pjsip_event_id_e.class + " with value " + i);
    }

    private pjsip_event_id_e(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pjsip_event_id_e(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsip_event_id_e(String str, pjsip_event_id_e org_pjsip_pjsua2_pjsip_event_id_e) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pjsip_event_id_e.swigValue;
        swigNext = this.swigValue + 1;
    }
}
