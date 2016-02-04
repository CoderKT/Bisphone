package org.pjsip.pjsua2;

public final class pjsua_invalid_id_const_ {
    public static final pjsua_invalid_id_const_ PJSUA_INVALID_ID;
    private static int swigNext;
    private static pjsua_invalid_id_const_[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJSUA_INVALID_ID = new pjsua_invalid_id_const_("PJSUA_INVALID_ID", pjsua2JNI.PJSUA_INVALID_ID_get());
        swigValues = new pjsua_invalid_id_const_[]{PJSUA_INVALID_ID};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pjsua_invalid_id_const_ swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pjsua_invalid_id_const_.class + " with value " + i);
    }

    private pjsua_invalid_id_const_(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pjsua_invalid_id_const_(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsua_invalid_id_const_(String str, pjsua_invalid_id_const_ org_pjsip_pjsua2_pjsua_invalid_id_const_) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pjsua_invalid_id_const_.swigValue;
        swigNext = this.swigValue + 1;
    }
}
