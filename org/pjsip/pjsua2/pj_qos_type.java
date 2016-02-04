package org.pjsip.pjsua2;

public final class pj_qos_type {
    public static final pj_qos_type PJ_QOS_TYPE_BACKGROUND;
    public static final pj_qos_type PJ_QOS_TYPE_BEST_EFFORT;
    public static final pj_qos_type PJ_QOS_TYPE_CONTROL;
    public static final pj_qos_type PJ_QOS_TYPE_VIDEO;
    public static final pj_qos_type PJ_QOS_TYPE_VOICE;
    private static int swigNext;
    private static pj_qos_type[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJ_QOS_TYPE_BEST_EFFORT = new pj_qos_type("PJ_QOS_TYPE_BEST_EFFORT");
        PJ_QOS_TYPE_BACKGROUND = new pj_qos_type("PJ_QOS_TYPE_BACKGROUND");
        PJ_QOS_TYPE_VIDEO = new pj_qos_type("PJ_QOS_TYPE_VIDEO");
        PJ_QOS_TYPE_VOICE = new pj_qos_type("PJ_QOS_TYPE_VOICE");
        PJ_QOS_TYPE_CONTROL = new pj_qos_type("PJ_QOS_TYPE_CONTROL");
        swigValues = new pj_qos_type[]{PJ_QOS_TYPE_BEST_EFFORT, PJ_QOS_TYPE_BACKGROUND, PJ_QOS_TYPE_VIDEO, PJ_QOS_TYPE_VOICE, PJ_QOS_TYPE_CONTROL};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pj_qos_type swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pj_qos_type.class + " with value " + i);
    }

    private pj_qos_type(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pj_qos_type(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pj_qos_type(String str, pj_qos_type org_pjsip_pjsua2_pj_qos_type) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pj_qos_type.swigValue;
        swigNext = this.swigValue + 1;
    }
}
