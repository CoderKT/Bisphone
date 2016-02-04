package org.pjsip.pjsua2;

public final class pjsua_med_tp_st {
    public static final pjsua_med_tp_st PJSUA_MED_TP_CREATING;
    public static final pjsua_med_tp_st PJSUA_MED_TP_DISABLED;
    public static final pjsua_med_tp_st PJSUA_MED_TP_IDLE;
    public static final pjsua_med_tp_st PJSUA_MED_TP_INIT;
    public static final pjsua_med_tp_st PJSUA_MED_TP_NULL;
    public static final pjsua_med_tp_st PJSUA_MED_TP_RUNNING;
    private static int swigNext;
    private static pjsua_med_tp_st[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJSUA_MED_TP_NULL = new pjsua_med_tp_st("PJSUA_MED_TP_NULL");
        PJSUA_MED_TP_CREATING = new pjsua_med_tp_st("PJSUA_MED_TP_CREATING");
        PJSUA_MED_TP_IDLE = new pjsua_med_tp_st("PJSUA_MED_TP_IDLE");
        PJSUA_MED_TP_INIT = new pjsua_med_tp_st("PJSUA_MED_TP_INIT");
        PJSUA_MED_TP_RUNNING = new pjsua_med_tp_st("PJSUA_MED_TP_RUNNING");
        PJSUA_MED_TP_DISABLED = new pjsua_med_tp_st("PJSUA_MED_TP_DISABLED");
        swigValues = new pjsua_med_tp_st[]{PJSUA_MED_TP_NULL, PJSUA_MED_TP_CREATING, PJSUA_MED_TP_IDLE, PJSUA_MED_TP_INIT, PJSUA_MED_TP_RUNNING, PJSUA_MED_TP_DISABLED};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pjsua_med_tp_st swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pjsua_med_tp_st.class + " with value " + i);
    }

    private pjsua_med_tp_st(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pjsua_med_tp_st(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsua_med_tp_st(String str, pjsua_med_tp_st org_pjsip_pjsua2_pjsua_med_tp_st) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pjsua_med_tp_st.swigValue;
        swigNext = this.swigValue + 1;
    }
}
