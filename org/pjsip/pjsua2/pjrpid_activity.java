package org.pjsip.pjsua2;

public final class pjrpid_activity {
    public static final pjrpid_activity PJRPID_ACTIVITY_AWAY;
    public static final pjrpid_activity PJRPID_ACTIVITY_BUSY;
    public static final pjrpid_activity PJRPID_ACTIVITY_UNKNOWN;
    private static int swigNext;
    private static pjrpid_activity[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJRPID_ACTIVITY_UNKNOWN = new pjrpid_activity("PJRPID_ACTIVITY_UNKNOWN");
        PJRPID_ACTIVITY_AWAY = new pjrpid_activity("PJRPID_ACTIVITY_AWAY");
        PJRPID_ACTIVITY_BUSY = new pjrpid_activity("PJRPID_ACTIVITY_BUSY");
        swigValues = new pjrpid_activity[]{PJRPID_ACTIVITY_UNKNOWN, PJRPID_ACTIVITY_AWAY, PJRPID_ACTIVITY_BUSY};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pjrpid_activity swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pjrpid_activity.class + " with value " + i);
    }

    private pjrpid_activity(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pjrpid_activity(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjrpid_activity(String str, pjrpid_activity org_pjsip_pjsua2_pjrpid_activity) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pjrpid_activity.swigValue;
        swigNext = this.swigValue + 1;
    }
}
