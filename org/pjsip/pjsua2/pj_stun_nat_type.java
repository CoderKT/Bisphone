package org.pjsip.pjsua2;

public final class pj_stun_nat_type {
    public static final pj_stun_nat_type PJ_STUN_NAT_TYPE_BLOCKED;
    public static final pj_stun_nat_type PJ_STUN_NAT_TYPE_ERR_UNKNOWN;
    public static final pj_stun_nat_type PJ_STUN_NAT_TYPE_FULL_CONE;
    public static final pj_stun_nat_type PJ_STUN_NAT_TYPE_OPEN;
    public static final pj_stun_nat_type PJ_STUN_NAT_TYPE_PORT_RESTRICTED;
    public static final pj_stun_nat_type PJ_STUN_NAT_TYPE_RESTRICTED;
    public static final pj_stun_nat_type PJ_STUN_NAT_TYPE_SYMMETRIC;
    public static final pj_stun_nat_type PJ_STUN_NAT_TYPE_SYMMETRIC_UDP;
    public static final pj_stun_nat_type PJ_STUN_NAT_TYPE_UNKNOWN;
    private static int swigNext;
    private static pj_stun_nat_type[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJ_STUN_NAT_TYPE_UNKNOWN = new pj_stun_nat_type("PJ_STUN_NAT_TYPE_UNKNOWN");
        PJ_STUN_NAT_TYPE_ERR_UNKNOWN = new pj_stun_nat_type("PJ_STUN_NAT_TYPE_ERR_UNKNOWN");
        PJ_STUN_NAT_TYPE_OPEN = new pj_stun_nat_type("PJ_STUN_NAT_TYPE_OPEN");
        PJ_STUN_NAT_TYPE_BLOCKED = new pj_stun_nat_type("PJ_STUN_NAT_TYPE_BLOCKED");
        PJ_STUN_NAT_TYPE_SYMMETRIC_UDP = new pj_stun_nat_type("PJ_STUN_NAT_TYPE_SYMMETRIC_UDP");
        PJ_STUN_NAT_TYPE_FULL_CONE = new pj_stun_nat_type("PJ_STUN_NAT_TYPE_FULL_CONE");
        PJ_STUN_NAT_TYPE_SYMMETRIC = new pj_stun_nat_type("PJ_STUN_NAT_TYPE_SYMMETRIC");
        PJ_STUN_NAT_TYPE_RESTRICTED = new pj_stun_nat_type("PJ_STUN_NAT_TYPE_RESTRICTED");
        PJ_STUN_NAT_TYPE_PORT_RESTRICTED = new pj_stun_nat_type("PJ_STUN_NAT_TYPE_PORT_RESTRICTED");
        swigValues = new pj_stun_nat_type[]{PJ_STUN_NAT_TYPE_UNKNOWN, PJ_STUN_NAT_TYPE_ERR_UNKNOWN, PJ_STUN_NAT_TYPE_OPEN, PJ_STUN_NAT_TYPE_BLOCKED, PJ_STUN_NAT_TYPE_SYMMETRIC_UDP, PJ_STUN_NAT_TYPE_FULL_CONE, PJ_STUN_NAT_TYPE_SYMMETRIC, PJ_STUN_NAT_TYPE_RESTRICTED, PJ_STUN_NAT_TYPE_PORT_RESTRICTED};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pj_stun_nat_type swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pj_stun_nat_type.class + " with value " + i);
    }

    private pj_stun_nat_type(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pj_stun_nat_type(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pj_stun_nat_type(String str, pj_stun_nat_type org_pjsip_pjsua2_pj_stun_nat_type) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pj_stun_nat_type.swigValue;
        swigNext = this.swigValue + 1;
    }
}
