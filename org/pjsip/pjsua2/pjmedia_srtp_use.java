package org.pjsip.pjsua2;

public final class pjmedia_srtp_use {
    public static final pjmedia_srtp_use PJMEDIA_SRTP_DISABLED;
    public static final pjmedia_srtp_use PJMEDIA_SRTP_MANDATORY;
    public static final pjmedia_srtp_use PJMEDIA_SRTP_OPTIONAL;
    private static int swigNext;
    private static pjmedia_srtp_use[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJMEDIA_SRTP_DISABLED = new pjmedia_srtp_use("PJMEDIA_SRTP_DISABLED");
        PJMEDIA_SRTP_OPTIONAL = new pjmedia_srtp_use("PJMEDIA_SRTP_OPTIONAL");
        PJMEDIA_SRTP_MANDATORY = new pjmedia_srtp_use("PJMEDIA_SRTP_MANDATORY");
        swigValues = new pjmedia_srtp_use[]{PJMEDIA_SRTP_DISABLED, PJMEDIA_SRTP_OPTIONAL, PJMEDIA_SRTP_MANDATORY};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pjmedia_srtp_use swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pjmedia_srtp_use.class + " with value " + i);
    }

    private pjmedia_srtp_use(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pjmedia_srtp_use(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjmedia_srtp_use(String str, pjmedia_srtp_use org_pjsip_pjsua2_pjmedia_srtp_use) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pjmedia_srtp_use.swigValue;
        swigNext = this.swigValue + 1;
    }
}
