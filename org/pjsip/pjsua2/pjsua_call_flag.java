package org.pjsip.pjsua2;

public final class pjsua_call_flag {
    public static final pjsua_call_flag PJSUA_CALL_INCLUDE_DISABLED_MEDIA;
    public static final pjsua_call_flag PJSUA_CALL_UNHOLD;
    public static final pjsua_call_flag PJSUA_CALL_UPDATE_CONTACT;
    private static int swigNext;
    private static pjsua_call_flag[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJSUA_CALL_UNHOLD = new pjsua_call_flag("PJSUA_CALL_UNHOLD", pjsua2JNI.PJSUA_CALL_UNHOLD_get());
        PJSUA_CALL_UPDATE_CONTACT = new pjsua_call_flag("PJSUA_CALL_UPDATE_CONTACT", pjsua2JNI.PJSUA_CALL_UPDATE_CONTACT_get());
        PJSUA_CALL_INCLUDE_DISABLED_MEDIA = new pjsua_call_flag("PJSUA_CALL_INCLUDE_DISABLED_MEDIA", pjsua2JNI.PJSUA_CALL_INCLUDE_DISABLED_MEDIA_get());
        swigValues = new pjsua_call_flag[]{PJSUA_CALL_UNHOLD, PJSUA_CALL_UPDATE_CONTACT, PJSUA_CALL_INCLUDE_DISABLED_MEDIA};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pjsua_call_flag swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pjsua_call_flag.class + " with value " + i);
    }

    private pjsua_call_flag(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pjsua_call_flag(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsua_call_flag(String str, pjsua_call_flag org_pjsip_pjsua2_pjsua_call_flag) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pjsua_call_flag.swigValue;
        swigNext = this.swigValue + 1;
    }
}
