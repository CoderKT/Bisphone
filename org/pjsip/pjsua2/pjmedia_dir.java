package org.pjsip.pjsua2;

public final class pjmedia_dir {
    public static final pjmedia_dir PJMEDIA_DIR_CAPTURE;
    public static final pjmedia_dir PJMEDIA_DIR_CAPTURE_PLAYBACK;
    public static final pjmedia_dir PJMEDIA_DIR_CAPTURE_RENDER;
    public static final pjmedia_dir PJMEDIA_DIR_DECODING;
    public static final pjmedia_dir PJMEDIA_DIR_ENCODING;
    public static final pjmedia_dir PJMEDIA_DIR_ENCODING_DECODING;
    public static final pjmedia_dir PJMEDIA_DIR_NONE;
    public static final pjmedia_dir PJMEDIA_DIR_PLAYBACK;
    public static final pjmedia_dir PJMEDIA_DIR_RENDER;
    private static int swigNext;
    private static pjmedia_dir[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJMEDIA_DIR_NONE = new pjmedia_dir("PJMEDIA_DIR_NONE", pjsua2JNI.PJMEDIA_DIR_NONE_get());
        PJMEDIA_DIR_ENCODING = new pjmedia_dir("PJMEDIA_DIR_ENCODING", pjsua2JNI.PJMEDIA_DIR_ENCODING_get());
        PJMEDIA_DIR_CAPTURE = new pjmedia_dir("PJMEDIA_DIR_CAPTURE", pjsua2JNI.PJMEDIA_DIR_CAPTURE_get());
        PJMEDIA_DIR_DECODING = new pjmedia_dir("PJMEDIA_DIR_DECODING", pjsua2JNI.PJMEDIA_DIR_DECODING_get());
        PJMEDIA_DIR_PLAYBACK = new pjmedia_dir("PJMEDIA_DIR_PLAYBACK", pjsua2JNI.PJMEDIA_DIR_PLAYBACK_get());
        PJMEDIA_DIR_RENDER = new pjmedia_dir("PJMEDIA_DIR_RENDER", pjsua2JNI.PJMEDIA_DIR_RENDER_get());
        PJMEDIA_DIR_ENCODING_DECODING = new pjmedia_dir("PJMEDIA_DIR_ENCODING_DECODING", pjsua2JNI.PJMEDIA_DIR_ENCODING_DECODING_get());
        PJMEDIA_DIR_CAPTURE_PLAYBACK = new pjmedia_dir("PJMEDIA_DIR_CAPTURE_PLAYBACK", pjsua2JNI.PJMEDIA_DIR_CAPTURE_PLAYBACK_get());
        PJMEDIA_DIR_CAPTURE_RENDER = new pjmedia_dir("PJMEDIA_DIR_CAPTURE_RENDER", pjsua2JNI.PJMEDIA_DIR_CAPTURE_RENDER_get());
        swigValues = new pjmedia_dir[]{PJMEDIA_DIR_NONE, PJMEDIA_DIR_ENCODING, PJMEDIA_DIR_CAPTURE, PJMEDIA_DIR_DECODING, PJMEDIA_DIR_PLAYBACK, PJMEDIA_DIR_RENDER, PJMEDIA_DIR_ENCODING_DECODING, PJMEDIA_DIR_CAPTURE_PLAYBACK, PJMEDIA_DIR_CAPTURE_RENDER};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pjmedia_dir swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pjmedia_dir.class + " with value " + i);
    }

    private pjmedia_dir(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pjmedia_dir(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjmedia_dir(String str, pjmedia_dir org_pjsip_pjsua2_pjmedia_dir) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pjmedia_dir.swigValue;
        swigNext = this.swigValue + 1;
    }
}
