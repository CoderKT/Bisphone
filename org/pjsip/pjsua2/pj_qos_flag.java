package org.pjsip.pjsua2;

public final class pj_qos_flag {
    public static final pj_qos_flag PJ_QOS_PARAM_HAS_DSCP;
    public static final pj_qos_flag PJ_QOS_PARAM_HAS_SO_PRIO;
    public static final pj_qos_flag PJ_QOS_PARAM_HAS_WMM;
    private static int swigNext;
    private static pj_qos_flag[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJ_QOS_PARAM_HAS_DSCP = new pj_qos_flag("PJ_QOS_PARAM_HAS_DSCP", pjsua2JNI.PJ_QOS_PARAM_HAS_DSCP_get());
        PJ_QOS_PARAM_HAS_SO_PRIO = new pj_qos_flag("PJ_QOS_PARAM_HAS_SO_PRIO", pjsua2JNI.PJ_QOS_PARAM_HAS_SO_PRIO_get());
        PJ_QOS_PARAM_HAS_WMM = new pj_qos_flag("PJ_QOS_PARAM_HAS_WMM", pjsua2JNI.PJ_QOS_PARAM_HAS_WMM_get());
        swigValues = new pj_qos_flag[]{PJ_QOS_PARAM_HAS_DSCP, PJ_QOS_PARAM_HAS_SO_PRIO, PJ_QOS_PARAM_HAS_WMM};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pj_qos_flag swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pj_qos_flag.class + " with value " + i);
    }

    private pj_qos_flag(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pj_qos_flag(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pj_qos_flag(String str, pj_qos_flag org_pjsip_pjsua2_pj_qos_flag) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pj_qos_flag.swigValue;
        swigNext = this.swigValue + 1;
    }
}
