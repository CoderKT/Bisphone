package org.pjsip.pjsua2;

public final class pjsua_buddy_status {
    public static final pjsua_buddy_status PJSUA_BUDDY_STATUS_OFFLINE;
    public static final pjsua_buddy_status PJSUA_BUDDY_STATUS_ONLINE;
    public static final pjsua_buddy_status PJSUA_BUDDY_STATUS_UNKNOWN;
    private static int swigNext;
    private static pjsua_buddy_status[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJSUA_BUDDY_STATUS_UNKNOWN = new pjsua_buddy_status("PJSUA_BUDDY_STATUS_UNKNOWN");
        PJSUA_BUDDY_STATUS_ONLINE = new pjsua_buddy_status("PJSUA_BUDDY_STATUS_ONLINE");
        PJSUA_BUDDY_STATUS_OFFLINE = new pjsua_buddy_status("PJSUA_BUDDY_STATUS_OFFLINE");
        swigValues = new pjsua_buddy_status[]{PJSUA_BUDDY_STATUS_UNKNOWN, PJSUA_BUDDY_STATUS_ONLINE, PJSUA_BUDDY_STATUS_OFFLINE};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pjsua_buddy_status swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pjsua_buddy_status.class + " with value " + i);
    }

    private pjsua_buddy_status(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pjsua_buddy_status(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsua_buddy_status(String str, pjsua_buddy_status org_pjsip_pjsua2_pjsua_buddy_status) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pjsua_buddy_status.swigValue;
        swigNext = this.swigValue + 1;
    }
}
