package org.pjsip.pjsua2;

public final class pjsip_transport_flags_e {
    public static final pjsip_transport_flags_e PJSIP_TRANSPORT_DATAGRAM;
    public static final pjsip_transport_flags_e PJSIP_TRANSPORT_RELIABLE;
    public static final pjsip_transport_flags_e PJSIP_TRANSPORT_SECURE;
    private static int swigNext;
    private static pjsip_transport_flags_e[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJSIP_TRANSPORT_RELIABLE = new pjsip_transport_flags_e("PJSIP_TRANSPORT_RELIABLE", pjsua2JNI.PJSIP_TRANSPORT_RELIABLE_get());
        PJSIP_TRANSPORT_SECURE = new pjsip_transport_flags_e("PJSIP_TRANSPORT_SECURE", pjsua2JNI.PJSIP_TRANSPORT_SECURE_get());
        PJSIP_TRANSPORT_DATAGRAM = new pjsip_transport_flags_e("PJSIP_TRANSPORT_DATAGRAM", pjsua2JNI.PJSIP_TRANSPORT_DATAGRAM_get());
        swigValues = new pjsip_transport_flags_e[]{PJSIP_TRANSPORT_RELIABLE, PJSIP_TRANSPORT_SECURE, PJSIP_TRANSPORT_DATAGRAM};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pjsip_transport_flags_e swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pjsip_transport_flags_e.class + " with value " + i);
    }

    private pjsip_transport_flags_e(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pjsip_transport_flags_e(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsip_transport_flags_e(String str, pjsip_transport_flags_e org_pjsip_pjsua2_pjsip_transport_flags_e) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pjsip_transport_flags_e.swigValue;
        swigNext = this.swigValue + 1;
    }
}
