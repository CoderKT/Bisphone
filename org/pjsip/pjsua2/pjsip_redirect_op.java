package org.pjsip.pjsua2;

public final class pjsip_redirect_op {
    public static final pjsip_redirect_op PJSIP_REDIRECT_ACCEPT;
    public static final pjsip_redirect_op PJSIP_REDIRECT_ACCEPT_REPLACE;
    public static final pjsip_redirect_op PJSIP_REDIRECT_PENDING;
    public static final pjsip_redirect_op PJSIP_REDIRECT_REJECT;
    public static final pjsip_redirect_op PJSIP_REDIRECT_STOP;
    private static int swigNext;
    private static pjsip_redirect_op[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJSIP_REDIRECT_REJECT = new pjsip_redirect_op("PJSIP_REDIRECT_REJECT");
        PJSIP_REDIRECT_ACCEPT = new pjsip_redirect_op("PJSIP_REDIRECT_ACCEPT");
        PJSIP_REDIRECT_ACCEPT_REPLACE = new pjsip_redirect_op("PJSIP_REDIRECT_ACCEPT_REPLACE");
        PJSIP_REDIRECT_PENDING = new pjsip_redirect_op("PJSIP_REDIRECT_PENDING");
        PJSIP_REDIRECT_STOP = new pjsip_redirect_op("PJSIP_REDIRECT_STOP");
        swigValues = new pjsip_redirect_op[]{PJSIP_REDIRECT_REJECT, PJSIP_REDIRECT_ACCEPT, PJSIP_REDIRECT_ACCEPT_REPLACE, PJSIP_REDIRECT_PENDING, PJSIP_REDIRECT_STOP};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pjsip_redirect_op swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pjsip_redirect_op.class + " with value " + i);
    }

    private pjsip_redirect_op(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pjsip_redirect_op(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsip_redirect_op(String str, pjsip_redirect_op org_pjsip_pjsua2_pjsip_redirect_op) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pjsip_redirect_op.swigValue;
        swigNext = this.swigValue + 1;
    }
}
