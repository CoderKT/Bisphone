package org.pjsip.pjsua2;

public final class pjsua_call_vid_strm_op {
    public static final pjsua_call_vid_strm_op PJSUA_CALL_VID_STRM_ADD;
    public static final pjsua_call_vid_strm_op PJSUA_CALL_VID_STRM_CHANGE_CAP_DEV;
    public static final pjsua_call_vid_strm_op PJSUA_CALL_VID_STRM_CHANGE_DIR;
    public static final pjsua_call_vid_strm_op PJSUA_CALL_VID_STRM_NO_OP;
    public static final pjsua_call_vid_strm_op PJSUA_CALL_VID_STRM_REMOVE;
    public static final pjsua_call_vid_strm_op PJSUA_CALL_VID_STRM_SEND_KEYFRAME;
    public static final pjsua_call_vid_strm_op PJSUA_CALL_VID_STRM_START_TRANSMIT;
    public static final pjsua_call_vid_strm_op PJSUA_CALL_VID_STRM_STOP_TRANSMIT;
    private static int swigNext;
    private static pjsua_call_vid_strm_op[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJSUA_CALL_VID_STRM_NO_OP = new pjsua_call_vid_strm_op("PJSUA_CALL_VID_STRM_NO_OP");
        PJSUA_CALL_VID_STRM_ADD = new pjsua_call_vid_strm_op("PJSUA_CALL_VID_STRM_ADD");
        PJSUA_CALL_VID_STRM_REMOVE = new pjsua_call_vid_strm_op("PJSUA_CALL_VID_STRM_REMOVE");
        PJSUA_CALL_VID_STRM_CHANGE_DIR = new pjsua_call_vid_strm_op("PJSUA_CALL_VID_STRM_CHANGE_DIR");
        PJSUA_CALL_VID_STRM_CHANGE_CAP_DEV = new pjsua_call_vid_strm_op("PJSUA_CALL_VID_STRM_CHANGE_CAP_DEV");
        PJSUA_CALL_VID_STRM_START_TRANSMIT = new pjsua_call_vid_strm_op("PJSUA_CALL_VID_STRM_START_TRANSMIT");
        PJSUA_CALL_VID_STRM_STOP_TRANSMIT = new pjsua_call_vid_strm_op("PJSUA_CALL_VID_STRM_STOP_TRANSMIT");
        PJSUA_CALL_VID_STRM_SEND_KEYFRAME = new pjsua_call_vid_strm_op("PJSUA_CALL_VID_STRM_SEND_KEYFRAME");
        swigValues = new pjsua_call_vid_strm_op[]{PJSUA_CALL_VID_STRM_NO_OP, PJSUA_CALL_VID_STRM_ADD, PJSUA_CALL_VID_STRM_REMOVE, PJSUA_CALL_VID_STRM_CHANGE_DIR, PJSUA_CALL_VID_STRM_CHANGE_CAP_DEV, PJSUA_CALL_VID_STRM_START_TRANSMIT, PJSUA_CALL_VID_STRM_STOP_TRANSMIT, PJSUA_CALL_VID_STRM_SEND_KEYFRAME};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pjsua_call_vid_strm_op swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pjsua_call_vid_strm_op.class + " with value " + i);
    }

    private pjsua_call_vid_strm_op(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pjsua_call_vid_strm_op(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsua_call_vid_strm_op(String str, pjsua_call_vid_strm_op org_pjsip_pjsua2_pjsua_call_vid_strm_op) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pjsua_call_vid_strm_op.swigValue;
        swigNext = this.swigValue + 1;
    }
}
