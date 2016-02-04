package org.pjsip.pjsua2;

public final class pjsip_transport_type_e {
    public static final pjsip_transport_type_e PJSIP_TRANSPORT_IPV6;
    public static final pjsip_transport_type_e PJSIP_TRANSPORT_LOOP;
    public static final pjsip_transport_type_e PJSIP_TRANSPORT_LOOP_DGRAM;
    public static final pjsip_transport_type_e PJSIP_TRANSPORT_SCTP;
    public static final pjsip_transport_type_e PJSIP_TRANSPORT_START_OTHER;
    public static final pjsip_transport_type_e PJSIP_TRANSPORT_TCP;
    public static final pjsip_transport_type_e PJSIP_TRANSPORT_TCP6;
    public static final pjsip_transport_type_e PJSIP_TRANSPORT_TLS;
    public static final pjsip_transport_type_e PJSIP_TRANSPORT_TLS6;
    public static final pjsip_transport_type_e PJSIP_TRANSPORT_UDP;
    public static final pjsip_transport_type_e PJSIP_TRANSPORT_UDP6;
    public static final pjsip_transport_type_e PJSIP_TRANSPORT_UNSPECIFIED;
    private static int swigNext;
    private static pjsip_transport_type_e[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJSIP_TRANSPORT_UNSPECIFIED = new pjsip_transport_type_e("PJSIP_TRANSPORT_UNSPECIFIED");
        PJSIP_TRANSPORT_UDP = new pjsip_transport_type_e("PJSIP_TRANSPORT_UDP");
        PJSIP_TRANSPORT_TCP = new pjsip_transport_type_e("PJSIP_TRANSPORT_TCP");
        PJSIP_TRANSPORT_TLS = new pjsip_transport_type_e("PJSIP_TRANSPORT_TLS");
        PJSIP_TRANSPORT_SCTP = new pjsip_transport_type_e("PJSIP_TRANSPORT_SCTP");
        PJSIP_TRANSPORT_LOOP = new pjsip_transport_type_e("PJSIP_TRANSPORT_LOOP");
        PJSIP_TRANSPORT_LOOP_DGRAM = new pjsip_transport_type_e("PJSIP_TRANSPORT_LOOP_DGRAM");
        PJSIP_TRANSPORT_START_OTHER = new pjsip_transport_type_e("PJSIP_TRANSPORT_START_OTHER");
        PJSIP_TRANSPORT_IPV6 = new pjsip_transport_type_e("PJSIP_TRANSPORT_IPV6", pjsua2JNI.PJSIP_TRANSPORT_IPV6_get());
        PJSIP_TRANSPORT_UDP6 = new pjsip_transport_type_e("PJSIP_TRANSPORT_UDP6", pjsua2JNI.PJSIP_TRANSPORT_UDP6_get());
        PJSIP_TRANSPORT_TCP6 = new pjsip_transport_type_e("PJSIP_TRANSPORT_TCP6", pjsua2JNI.PJSIP_TRANSPORT_TCP6_get());
        PJSIP_TRANSPORT_TLS6 = new pjsip_transport_type_e("PJSIP_TRANSPORT_TLS6", pjsua2JNI.PJSIP_TRANSPORT_TLS6_get());
        swigValues = new pjsip_transport_type_e[]{PJSIP_TRANSPORT_UNSPECIFIED, PJSIP_TRANSPORT_UDP, PJSIP_TRANSPORT_TCP, PJSIP_TRANSPORT_TLS, PJSIP_TRANSPORT_SCTP, PJSIP_TRANSPORT_LOOP, PJSIP_TRANSPORT_LOOP_DGRAM, PJSIP_TRANSPORT_START_OTHER, PJSIP_TRANSPORT_IPV6, PJSIP_TRANSPORT_UDP6, PJSIP_TRANSPORT_TCP6, PJSIP_TRANSPORT_TLS6};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pjsip_transport_type_e swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pjsip_transport_type_e.class + " with value " + i);
    }

    private pjsip_transport_type_e(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pjsip_transport_type_e(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsip_transport_type_e(String str, pjsip_transport_type_e org_pjsip_pjsua2_pjsip_transport_type_e) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pjsip_transport_type_e.swigValue;
        swigNext = this.swigValue + 1;
    }
}
