package org.pjsip.pjsua2;

public final class pjsua_call_media_status {
    public static final pjsua_call_media_status PJSUA_CALL_MEDIA_ACTIVE;
    public static final pjsua_call_media_status PJSUA_CALL_MEDIA_ERROR;
    public static final pjsua_call_media_status PJSUA_CALL_MEDIA_LOCAL_HOLD;
    public static final pjsua_call_media_status PJSUA_CALL_MEDIA_NONE;
    public static final pjsua_call_media_status PJSUA_CALL_MEDIA_REMOTE_HOLD;
    private static int swigNext;
    private static pjsua_call_media_status[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJSUA_CALL_MEDIA_NONE = new pjsua_call_media_status("PJSUA_CALL_MEDIA_NONE");
        PJSUA_CALL_MEDIA_ACTIVE = new pjsua_call_media_status("PJSUA_CALL_MEDIA_ACTIVE");
        PJSUA_CALL_MEDIA_LOCAL_HOLD = new pjsua_call_media_status("PJSUA_CALL_MEDIA_LOCAL_HOLD");
        PJSUA_CALL_MEDIA_REMOTE_HOLD = new pjsua_call_media_status("PJSUA_CALL_MEDIA_REMOTE_HOLD");
        PJSUA_CALL_MEDIA_ERROR = new pjsua_call_media_status("PJSUA_CALL_MEDIA_ERROR");
        swigValues = new pjsua_call_media_status[]{PJSUA_CALL_MEDIA_NONE, PJSUA_CALL_MEDIA_ACTIVE, PJSUA_CALL_MEDIA_LOCAL_HOLD, PJSUA_CALL_MEDIA_REMOTE_HOLD, PJSUA_CALL_MEDIA_ERROR};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pjsua_call_media_status swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pjsua_call_media_status.class + " with value " + i);
    }

    private pjsua_call_media_status(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pjsua_call_media_status(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsua_call_media_status(String str, pjsua_call_media_status org_pjsip_pjsua2_pjsua_call_media_status) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pjsua_call_media_status.swigValue;
        swigNext = this.swigValue + 1;
    }
}
