package org.pjsip.pjsua2;

public final class pjsua_create_media_transport_flag {
    public static final pjsua_create_media_transport_flag PJSUA_MED_TP_CLOSE_MEMBER;
    private static int swigNext;
    private static pjsua_create_media_transport_flag[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJSUA_MED_TP_CLOSE_MEMBER = new pjsua_create_media_transport_flag("PJSUA_MED_TP_CLOSE_MEMBER", pjsua2JNI.PJSUA_MED_TP_CLOSE_MEMBER_get());
        swigValues = new pjsua_create_media_transport_flag[]{PJSUA_MED_TP_CLOSE_MEMBER};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pjsua_create_media_transport_flag swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pjsua_create_media_transport_flag.class + " with value " + i);
    }

    private pjsua_create_media_transport_flag(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pjsua_create_media_transport_flag(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsua_create_media_transport_flag(String str, pjsua_create_media_transport_flag org_pjsip_pjsua2_pjsua_create_media_transport_flag) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pjsua_create_media_transport_flag.swigValue;
        swigNext = this.swigValue + 1;
    }
}
