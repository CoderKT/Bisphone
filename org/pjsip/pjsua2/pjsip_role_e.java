package org.pjsip.pjsua2;

public final class pjsip_role_e {
    public static final pjsip_role_e PJSIP_ROLE_UAC;
    public static final pjsip_role_e PJSIP_ROLE_UAS;
    public static final pjsip_role_e PJSIP_UAC_ROLE;
    public static final pjsip_role_e PJSIP_UAS_ROLE;
    private static int swigNext;
    private static pjsip_role_e[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJSIP_ROLE_UAC = new pjsip_role_e("PJSIP_ROLE_UAC");
        PJSIP_ROLE_UAS = new pjsip_role_e("PJSIP_ROLE_UAS");
        PJSIP_UAC_ROLE = new pjsip_role_e("PJSIP_UAC_ROLE", pjsua2JNI.PJSIP_UAC_ROLE_get());
        PJSIP_UAS_ROLE = new pjsip_role_e("PJSIP_UAS_ROLE", pjsua2JNI.PJSIP_UAS_ROLE_get());
        swigValues = new pjsip_role_e[]{PJSIP_ROLE_UAC, PJSIP_ROLE_UAS, PJSIP_UAC_ROLE, PJSIP_UAS_ROLE};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pjsip_role_e swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pjsip_role_e.class + " with value " + i);
    }

    private pjsip_role_e(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pjsip_role_e(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsip_role_e(String str, pjsip_role_e org_pjsip_pjsua2_pjsip_role_e) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pjsip_role_e.swigValue;
        swigNext = this.swigValue + 1;
    }
}
