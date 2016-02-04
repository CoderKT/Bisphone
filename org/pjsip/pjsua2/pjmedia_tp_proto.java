package org.pjsip.pjsua2;

public final class pjmedia_tp_proto {
    public static final pjmedia_tp_proto PJMEDIA_TP_PROTO_NONE;
    public static final pjmedia_tp_proto PJMEDIA_TP_PROTO_RTP_AVP;
    public static final pjmedia_tp_proto PJMEDIA_TP_PROTO_RTP_SAVP;
    public static final pjmedia_tp_proto PJMEDIA_TP_PROTO_UNKNOWN;
    private static int swigNext;
    private static pjmedia_tp_proto[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJMEDIA_TP_PROTO_NONE = new pjmedia_tp_proto("PJMEDIA_TP_PROTO_NONE", pjsua2JNI.PJMEDIA_TP_PROTO_NONE_get());
        PJMEDIA_TP_PROTO_RTP_AVP = new pjmedia_tp_proto("PJMEDIA_TP_PROTO_RTP_AVP");
        PJMEDIA_TP_PROTO_RTP_SAVP = new pjmedia_tp_proto("PJMEDIA_TP_PROTO_RTP_SAVP");
        PJMEDIA_TP_PROTO_UNKNOWN = new pjmedia_tp_proto("PJMEDIA_TP_PROTO_UNKNOWN");
        swigValues = new pjmedia_tp_proto[]{PJMEDIA_TP_PROTO_NONE, PJMEDIA_TP_PROTO_RTP_AVP, PJMEDIA_TP_PROTO_RTP_SAVP, PJMEDIA_TP_PROTO_UNKNOWN};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pjmedia_tp_proto swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pjmedia_tp_proto.class + " with value " + i);
    }

    private pjmedia_tp_proto(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pjmedia_tp_proto(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjmedia_tp_proto(String str, pjmedia_tp_proto org_pjsip_pjsua2_pjmedia_tp_proto) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pjmedia_tp_proto.swigValue;
        swigNext = this.swigValue + 1;
    }
}
