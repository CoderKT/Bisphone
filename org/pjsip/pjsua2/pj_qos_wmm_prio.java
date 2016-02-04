package org.pjsip.pjsua2;

public final class pj_qos_wmm_prio {
    public static final pj_qos_wmm_prio PJ_QOS_WMM_PRIO_BULK;
    public static final pj_qos_wmm_prio PJ_QOS_WMM_PRIO_BULK_EFFORT;
    public static final pj_qos_wmm_prio PJ_QOS_WMM_PRIO_VIDEO;
    public static final pj_qos_wmm_prio PJ_QOS_WMM_PRIO_VOICE;
    private static int swigNext;
    private static pj_qos_wmm_prio[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJ_QOS_WMM_PRIO_BULK_EFFORT = new pj_qos_wmm_prio("PJ_QOS_WMM_PRIO_BULK_EFFORT");
        PJ_QOS_WMM_PRIO_BULK = new pj_qos_wmm_prio("PJ_QOS_WMM_PRIO_BULK");
        PJ_QOS_WMM_PRIO_VIDEO = new pj_qos_wmm_prio("PJ_QOS_WMM_PRIO_VIDEO");
        PJ_QOS_WMM_PRIO_VOICE = new pj_qos_wmm_prio("PJ_QOS_WMM_PRIO_VOICE");
        swigValues = new pj_qos_wmm_prio[]{PJ_QOS_WMM_PRIO_BULK_EFFORT, PJ_QOS_WMM_PRIO_BULK, PJ_QOS_WMM_PRIO_VIDEO, PJ_QOS_WMM_PRIO_VOICE};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pj_qos_wmm_prio swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pj_qos_wmm_prio.class + " with value " + i);
    }

    private pj_qos_wmm_prio(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pj_qos_wmm_prio(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pj_qos_wmm_prio(String str, pj_qos_wmm_prio org_pjsip_pjsua2_pj_qos_wmm_prio) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pj_qos_wmm_prio.swigValue;
        swigNext = this.swigValue + 1;
    }
}
