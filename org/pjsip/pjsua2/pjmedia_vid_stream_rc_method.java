package org.pjsip.pjsua2;

public final class pjmedia_vid_stream_rc_method {
    public static final pjmedia_vid_stream_rc_method PJMEDIA_VID_STREAM_RC_NONE;
    public static final pjmedia_vid_stream_rc_method PJMEDIA_VID_STREAM_RC_SIMPLE_BLOCKING;
    private static int swigNext;
    private static pjmedia_vid_stream_rc_method[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJMEDIA_VID_STREAM_RC_NONE = new pjmedia_vid_stream_rc_method("PJMEDIA_VID_STREAM_RC_NONE", pjsua2JNI.PJMEDIA_VID_STREAM_RC_NONE_get());
        PJMEDIA_VID_STREAM_RC_SIMPLE_BLOCKING = new pjmedia_vid_stream_rc_method("PJMEDIA_VID_STREAM_RC_SIMPLE_BLOCKING", pjsua2JNI.PJMEDIA_VID_STREAM_RC_SIMPLE_BLOCKING_get());
        swigValues = new pjmedia_vid_stream_rc_method[]{PJMEDIA_VID_STREAM_RC_NONE, PJMEDIA_VID_STREAM_RC_SIMPLE_BLOCKING};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pjmedia_vid_stream_rc_method swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pjmedia_vid_stream_rc_method.class + " with value " + i);
    }

    private pjmedia_vid_stream_rc_method(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pjmedia_vid_stream_rc_method(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjmedia_vid_stream_rc_method(String str, pjmedia_vid_stream_rc_method org_pjsip_pjsua2_pjmedia_vid_stream_rc_method) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pjmedia_vid_stream_rc_method.swigValue;
        swigNext = this.swigValue + 1;
    }
}
