package org.pjsip.pjsua2;

public final class pjsua_sip_timer_use {
    public static final pjsua_sip_timer_use PJSUA_SIP_TIMER_ALWAYS;
    public static final pjsua_sip_timer_use PJSUA_SIP_TIMER_INACTIVE;
    public static final pjsua_sip_timer_use PJSUA_SIP_TIMER_OPTIONAL;
    public static final pjsua_sip_timer_use PJSUA_SIP_TIMER_REQUIRED;
    private static int swigNext;
    private static pjsua_sip_timer_use[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJSUA_SIP_TIMER_INACTIVE = new pjsua_sip_timer_use("PJSUA_SIP_TIMER_INACTIVE");
        PJSUA_SIP_TIMER_OPTIONAL = new pjsua_sip_timer_use("PJSUA_SIP_TIMER_OPTIONAL");
        PJSUA_SIP_TIMER_REQUIRED = new pjsua_sip_timer_use("PJSUA_SIP_TIMER_REQUIRED");
        PJSUA_SIP_TIMER_ALWAYS = new pjsua_sip_timer_use("PJSUA_SIP_TIMER_ALWAYS");
        swigValues = new pjsua_sip_timer_use[]{PJSUA_SIP_TIMER_INACTIVE, PJSUA_SIP_TIMER_OPTIONAL, PJSUA_SIP_TIMER_REQUIRED, PJSUA_SIP_TIMER_ALWAYS};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pjsua_sip_timer_use swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pjsua_sip_timer_use.class + " with value " + i);
    }

    private pjsua_sip_timer_use(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pjsua_sip_timer_use(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsua_sip_timer_use(String str, pjsua_sip_timer_use org_pjsip_pjsua2_pjsua_sip_timer_use) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pjsua_sip_timer_use.swigValue;
        swigNext = this.swigValue + 1;
    }
}
