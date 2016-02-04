package org.pjsip.pjsua2;

public final class pjmedia_vid_dev_std_index {
    public static final pjmedia_vid_dev_std_index PJMEDIA_VID_DEFAULT_CAPTURE_DEV;
    public static final pjmedia_vid_dev_std_index PJMEDIA_VID_DEFAULT_RENDER_DEV;
    public static final pjmedia_vid_dev_std_index PJMEDIA_VID_INVALID_DEV;
    private static int swigNext;
    private static pjmedia_vid_dev_std_index[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJMEDIA_VID_DEFAULT_CAPTURE_DEV = new pjmedia_vid_dev_std_index("PJMEDIA_VID_DEFAULT_CAPTURE_DEV", pjsua2JNI.PJMEDIA_VID_DEFAULT_CAPTURE_DEV_get());
        PJMEDIA_VID_DEFAULT_RENDER_DEV = new pjmedia_vid_dev_std_index("PJMEDIA_VID_DEFAULT_RENDER_DEV", pjsua2JNI.PJMEDIA_VID_DEFAULT_RENDER_DEV_get());
        PJMEDIA_VID_INVALID_DEV = new pjmedia_vid_dev_std_index("PJMEDIA_VID_INVALID_DEV", pjsua2JNI.PJMEDIA_VID_INVALID_DEV_get());
        swigValues = new pjmedia_vid_dev_std_index[]{PJMEDIA_VID_DEFAULT_CAPTURE_DEV, PJMEDIA_VID_DEFAULT_RENDER_DEV, PJMEDIA_VID_INVALID_DEV};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pjmedia_vid_dev_std_index swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pjmedia_vid_dev_std_index.class + " with value " + i);
    }

    private pjmedia_vid_dev_std_index(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pjmedia_vid_dev_std_index(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjmedia_vid_dev_std_index(String str, pjmedia_vid_dev_std_index org_pjsip_pjsua2_pjmedia_vid_dev_std_index) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pjmedia_vid_dev_std_index.swigValue;
        swigNext = this.swigValue + 1;
    }
}
