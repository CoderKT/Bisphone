package org.pjsip.pjsua2;

public final class pj_log_decoration {
    public static final pj_log_decoration PJ_LOG_HAS_COLOR;
    public static final pj_log_decoration PJ_LOG_HAS_CR;
    public static final pj_log_decoration PJ_LOG_HAS_DAY_NAME;
    public static final pj_log_decoration PJ_LOG_HAS_DAY_OF_MON;
    public static final pj_log_decoration PJ_LOG_HAS_INDENT;
    public static final pj_log_decoration PJ_LOG_HAS_LEVEL_TEXT;
    public static final pj_log_decoration PJ_LOG_HAS_MICRO_SEC;
    public static final pj_log_decoration PJ_LOG_HAS_MONTH;
    public static final pj_log_decoration PJ_LOG_HAS_NEWLINE;
    public static final pj_log_decoration PJ_LOG_HAS_SENDER;
    public static final pj_log_decoration PJ_LOG_HAS_SPACE;
    public static final pj_log_decoration PJ_LOG_HAS_THREAD_ID;
    public static final pj_log_decoration PJ_LOG_HAS_THREAD_SWC;
    public static final pj_log_decoration PJ_LOG_HAS_TIME;
    public static final pj_log_decoration PJ_LOG_HAS_YEAR;
    private static int swigNext;
    private static pj_log_decoration[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJ_LOG_HAS_DAY_NAME = new pj_log_decoration("PJ_LOG_HAS_DAY_NAME", pjsua2JNI.PJ_LOG_HAS_DAY_NAME_get());
        PJ_LOG_HAS_YEAR = new pj_log_decoration("PJ_LOG_HAS_YEAR", pjsua2JNI.PJ_LOG_HAS_YEAR_get());
        PJ_LOG_HAS_MONTH = new pj_log_decoration("PJ_LOG_HAS_MONTH", pjsua2JNI.PJ_LOG_HAS_MONTH_get());
        PJ_LOG_HAS_DAY_OF_MON = new pj_log_decoration("PJ_LOG_HAS_DAY_OF_MON", pjsua2JNI.PJ_LOG_HAS_DAY_OF_MON_get());
        PJ_LOG_HAS_TIME = new pj_log_decoration("PJ_LOG_HAS_TIME", pjsua2JNI.PJ_LOG_HAS_TIME_get());
        PJ_LOG_HAS_MICRO_SEC = new pj_log_decoration("PJ_LOG_HAS_MICRO_SEC", pjsua2JNI.PJ_LOG_HAS_MICRO_SEC_get());
        PJ_LOG_HAS_SENDER = new pj_log_decoration("PJ_LOG_HAS_SENDER", pjsua2JNI.PJ_LOG_HAS_SENDER_get());
        PJ_LOG_HAS_NEWLINE = new pj_log_decoration("PJ_LOG_HAS_NEWLINE", pjsua2JNI.PJ_LOG_HAS_NEWLINE_get());
        PJ_LOG_HAS_CR = new pj_log_decoration("PJ_LOG_HAS_CR", pjsua2JNI.PJ_LOG_HAS_CR_get());
        PJ_LOG_HAS_SPACE = new pj_log_decoration("PJ_LOG_HAS_SPACE", pjsua2JNI.PJ_LOG_HAS_SPACE_get());
        PJ_LOG_HAS_COLOR = new pj_log_decoration("PJ_LOG_HAS_COLOR", pjsua2JNI.PJ_LOG_HAS_COLOR_get());
        PJ_LOG_HAS_LEVEL_TEXT = new pj_log_decoration("PJ_LOG_HAS_LEVEL_TEXT", pjsua2JNI.PJ_LOG_HAS_LEVEL_TEXT_get());
        PJ_LOG_HAS_THREAD_ID = new pj_log_decoration("PJ_LOG_HAS_THREAD_ID", pjsua2JNI.PJ_LOG_HAS_THREAD_ID_get());
        PJ_LOG_HAS_THREAD_SWC = new pj_log_decoration("PJ_LOG_HAS_THREAD_SWC", pjsua2JNI.PJ_LOG_HAS_THREAD_SWC_get());
        PJ_LOG_HAS_INDENT = new pj_log_decoration("PJ_LOG_HAS_INDENT", pjsua2JNI.PJ_LOG_HAS_INDENT_get());
        swigValues = new pj_log_decoration[]{PJ_LOG_HAS_DAY_NAME, PJ_LOG_HAS_YEAR, PJ_LOG_HAS_MONTH, PJ_LOG_HAS_DAY_OF_MON, PJ_LOG_HAS_TIME, PJ_LOG_HAS_MICRO_SEC, PJ_LOG_HAS_SENDER, PJ_LOG_HAS_NEWLINE, PJ_LOG_HAS_CR, PJ_LOG_HAS_SPACE, PJ_LOG_HAS_COLOR, PJ_LOG_HAS_LEVEL_TEXT, PJ_LOG_HAS_THREAD_ID, PJ_LOG_HAS_THREAD_SWC, PJ_LOG_HAS_INDENT};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pj_log_decoration swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pj_log_decoration.class + " with value " + i);
    }

    private pj_log_decoration(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pj_log_decoration(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pj_log_decoration(String str, pj_log_decoration org_pjsip_pjsua2_pj_log_decoration) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pj_log_decoration.swigValue;
        swigNext = this.swigValue + 1;
    }
}
