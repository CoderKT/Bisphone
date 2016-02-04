package org.pjsip.pjsua2;

public final class pjsua_100rel_use {
    public static final pjsua_100rel_use PJSUA_100REL_MANDATORY;
    public static final pjsua_100rel_use PJSUA_100REL_NOT_USED;
    public static final pjsua_100rel_use PJSUA_100REL_OPTIONAL;
    private static int swigNext;
    private static pjsua_100rel_use[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJSUA_100REL_NOT_USED = new pjsua_100rel_use("PJSUA_100REL_NOT_USED");
        PJSUA_100REL_MANDATORY = new pjsua_100rel_use("PJSUA_100REL_MANDATORY");
        PJSUA_100REL_OPTIONAL = new pjsua_100rel_use("PJSUA_100REL_OPTIONAL");
        swigValues = new pjsua_100rel_use[]{PJSUA_100REL_NOT_USED, PJSUA_100REL_MANDATORY, PJSUA_100REL_OPTIONAL};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pjsua_100rel_use swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pjsua_100rel_use.class + " with value " + i);
    }

    private pjsua_100rel_use(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pjsua_100rel_use(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsua_100rel_use(String str, pjsua_100rel_use org_pjsip_pjsua2_pjsua_100rel_use) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pjsua_100rel_use.swigValue;
        swigNext = this.swigValue + 1;
    }
}
