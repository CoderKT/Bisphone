package org.pjsip.pjsua2;

public final class pj_file_access {
    public static final pj_file_access PJ_O_APPEND;
    public static final pj_file_access PJ_O_RDONLY;
    public static final pj_file_access PJ_O_RDWR;
    public static final pj_file_access PJ_O_WRONLY;
    private static int swigNext;
    private static pj_file_access[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJ_O_RDONLY = new pj_file_access("PJ_O_RDONLY", pjsua2JNI.PJ_O_RDONLY_get());
        PJ_O_WRONLY = new pj_file_access("PJ_O_WRONLY", pjsua2JNI.PJ_O_WRONLY_get());
        PJ_O_RDWR = new pj_file_access("PJ_O_RDWR", pjsua2JNI.PJ_O_RDWR_get());
        PJ_O_APPEND = new pj_file_access("PJ_O_APPEND", pjsua2JNI.PJ_O_APPEND_get());
        swigValues = new pj_file_access[]{PJ_O_RDONLY, PJ_O_WRONLY, PJ_O_RDWR, PJ_O_APPEND};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pj_file_access swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pj_file_access.class + " with value " + i);
    }

    private pj_file_access(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pj_file_access(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pj_file_access(String str, pj_file_access org_pjsip_pjsua2_pj_file_access) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pj_file_access.swigValue;
        swigNext = this.swigValue + 1;
    }
}
