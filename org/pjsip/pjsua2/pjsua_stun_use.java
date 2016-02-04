package org.pjsip.pjsua2;

public final class pjsua_stun_use {
    public static final pjsua_stun_use PJSUA_STUN_USE_DEFAULT;
    public static final pjsua_stun_use PJSUA_STUN_USE_DISABLED;
    private static int swigNext;
    private static pjsua_stun_use[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJSUA_STUN_USE_DEFAULT = new pjsua_stun_use("PJSUA_STUN_USE_DEFAULT");
        PJSUA_STUN_USE_DISABLED = new pjsua_stun_use("PJSUA_STUN_USE_DISABLED");
        swigValues = new pjsua_stun_use[]{PJSUA_STUN_USE_DEFAULT, PJSUA_STUN_USE_DISABLED};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pjsua_stun_use swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pjsua_stun_use.class + " with value " + i);
    }

    private pjsua_stun_use(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pjsua_stun_use(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsua_stun_use(String str, pjsua_stun_use org_pjsip_pjsua2_pjsua_stun_use) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pjsua_stun_use.swigValue;
        swigNext = this.swigValue + 1;
    }
}
