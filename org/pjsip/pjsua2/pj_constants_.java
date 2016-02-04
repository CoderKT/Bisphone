package org.pjsip.pjsua2;

public final class pj_constants_ {
    public static final pj_constants_ PJ_FALSE;
    public static final pj_constants_ PJ_SUCCESS;
    public static final pj_constants_ PJ_TRUE;
    private static int swigNext;
    private static pj_constants_[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJ_SUCCESS = new pj_constants_("PJ_SUCCESS", pjsua2JNI.PJ_SUCCESS_get());
        PJ_TRUE = new pj_constants_("PJ_TRUE", pjsua2JNI.PJ_TRUE_get());
        PJ_FALSE = new pj_constants_("PJ_FALSE", pjsua2JNI.PJ_FALSE_get());
        swigValues = new pj_constants_[]{PJ_SUCCESS, PJ_TRUE, PJ_FALSE};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pj_constants_ swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pj_constants_.class + " with value " + i);
    }

    private pj_constants_(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pj_constants_(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pj_constants_(String str, pj_constants_ org_pjsip_pjsua2_pj_constants_) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pj_constants_.swigValue;
        swigNext = this.swigValue + 1;
    }
}
