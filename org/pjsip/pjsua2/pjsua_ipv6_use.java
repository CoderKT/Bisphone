package org.pjsip.pjsua2;

public final class pjsua_ipv6_use {
    public static final pjsua_ipv6_use PJSUA_IPV6_DISABLED;
    public static final pjsua_ipv6_use PJSUA_IPV6_ENABLED;
    private static int swigNext;
    private static pjsua_ipv6_use[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJSUA_IPV6_DISABLED = new pjsua_ipv6_use("PJSUA_IPV6_DISABLED");
        PJSUA_IPV6_ENABLED = new pjsua_ipv6_use("PJSUA_IPV6_ENABLED");
        swigValues = new pjsua_ipv6_use[]{PJSUA_IPV6_DISABLED, PJSUA_IPV6_ENABLED};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pjsua_ipv6_use swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pjsua_ipv6_use.class + " with value " + i);
    }

    private pjsua_ipv6_use(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pjsua_ipv6_use(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsua_ipv6_use(String str, pjsua_ipv6_use org_pjsip_pjsua2_pjsua_ipv6_use) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pjsua_ipv6_use.swigValue;
        swigNext = this.swigValue + 1;
    }
}
