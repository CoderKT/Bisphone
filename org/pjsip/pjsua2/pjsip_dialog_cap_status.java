package org.pjsip.pjsua2;

public final class pjsip_dialog_cap_status {
    public static final pjsip_dialog_cap_status PJSIP_DIALOG_CAP_SUPPORTED;
    public static final pjsip_dialog_cap_status PJSIP_DIALOG_CAP_UNKNOWN;
    public static final pjsip_dialog_cap_status PJSIP_DIALOG_CAP_UNSUPPORTED;
    private static int swigNext;
    private static pjsip_dialog_cap_status[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJSIP_DIALOG_CAP_UNSUPPORTED = new pjsip_dialog_cap_status("PJSIP_DIALOG_CAP_UNSUPPORTED", pjsua2JNI.PJSIP_DIALOG_CAP_UNSUPPORTED_get());
        PJSIP_DIALOG_CAP_SUPPORTED = new pjsip_dialog_cap_status("PJSIP_DIALOG_CAP_SUPPORTED", pjsua2JNI.PJSIP_DIALOG_CAP_SUPPORTED_get());
        PJSIP_DIALOG_CAP_UNKNOWN = new pjsip_dialog_cap_status("PJSIP_DIALOG_CAP_UNKNOWN", pjsua2JNI.PJSIP_DIALOG_CAP_UNKNOWN_get());
        swigValues = new pjsip_dialog_cap_status[]{PJSIP_DIALOG_CAP_UNSUPPORTED, PJSIP_DIALOG_CAP_SUPPORTED, PJSIP_DIALOG_CAP_UNKNOWN};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pjsip_dialog_cap_status swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pjsip_dialog_cap_status.class + " with value " + i);
    }

    private pjsip_dialog_cap_status(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pjsip_dialog_cap_status(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsip_dialog_cap_status(String str, pjsip_dialog_cap_status org_pjsip_pjsua2_pjsip_dialog_cap_status) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pjsip_dialog_cap_status.swigValue;
        swigNext = this.swigValue + 1;
    }
}
