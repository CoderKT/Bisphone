package org.pjsip.pjsua2;

public final class pjsua_vid_req_keyframe_method {
    public static final pjsua_vid_req_keyframe_method PJSUA_VID_REQ_KEYFRAME_RTCP_PLI;
    public static final pjsua_vid_req_keyframe_method PJSUA_VID_REQ_KEYFRAME_SIP_INFO;
    private static int swigNext;
    private static pjsua_vid_req_keyframe_method[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJSUA_VID_REQ_KEYFRAME_SIP_INFO = new pjsua_vid_req_keyframe_method("PJSUA_VID_REQ_KEYFRAME_SIP_INFO", pjsua2JNI.PJSUA_VID_REQ_KEYFRAME_SIP_INFO_get());
        PJSUA_VID_REQ_KEYFRAME_RTCP_PLI = new pjsua_vid_req_keyframe_method("PJSUA_VID_REQ_KEYFRAME_RTCP_PLI", pjsua2JNI.PJSUA_VID_REQ_KEYFRAME_RTCP_PLI_get());
        swigValues = new pjsua_vid_req_keyframe_method[]{PJSUA_VID_REQ_KEYFRAME_SIP_INFO, PJSUA_VID_REQ_KEYFRAME_RTCP_PLI};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pjsua_vid_req_keyframe_method swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pjsua_vid_req_keyframe_method.class + " with value " + i);
    }

    private pjsua_vid_req_keyframe_method(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pjsua_vid_req_keyframe_method(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsua_vid_req_keyframe_method(String str, pjsua_vid_req_keyframe_method org_pjsip_pjsua2_pjsua_vid_req_keyframe_method) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pjsua_vid_req_keyframe_method.swigValue;
        swigNext = this.swigValue + 1;
    }
}
