package org.pjsip.pjsua2;

public final class pjmedia_format_id {
    public static final pjmedia_format_id PJMEDIA_FORMAT_ALAW;
    public static final pjmedia_format_id PJMEDIA_FORMAT_AMR;
    public static final pjmedia_format_id PJMEDIA_FORMAT_AYUV;
    public static final pjmedia_format_id PJMEDIA_FORMAT_BGRA;
    public static final pjmedia_format_id PJMEDIA_FORMAT_DIB;
    public static final pjmedia_format_id PJMEDIA_FORMAT_G729;
    public static final pjmedia_format_id PJMEDIA_FORMAT_GBRP;
    public static final pjmedia_format_id PJMEDIA_FORMAT_H261;
    public static final pjmedia_format_id PJMEDIA_FORMAT_H263;
    public static final pjmedia_format_id PJMEDIA_FORMAT_H263P;
    public static final pjmedia_format_id PJMEDIA_FORMAT_H264;
    public static final pjmedia_format_id PJMEDIA_FORMAT_I420;
    public static final pjmedia_format_id PJMEDIA_FORMAT_I420JPEG;
    public static final pjmedia_format_id PJMEDIA_FORMAT_I422;
    public static final pjmedia_format_id PJMEDIA_FORMAT_I422JPEG;
    public static final pjmedia_format_id PJMEDIA_FORMAT_ILBC;
    public static final pjmedia_format_id PJMEDIA_FORMAT_IYUV;
    public static final pjmedia_format_id PJMEDIA_FORMAT_L16;
    public static final pjmedia_format_id PJMEDIA_FORMAT_MJPEG;
    public static final pjmedia_format_id PJMEDIA_FORMAT_MPEG1VIDEO;
    public static final pjmedia_format_id PJMEDIA_FORMAT_MPEG2VIDEO;
    public static final pjmedia_format_id PJMEDIA_FORMAT_MPEG4;
    public static final pjmedia_format_id PJMEDIA_FORMAT_PCM;
    public static final pjmedia_format_id PJMEDIA_FORMAT_PCMA;
    public static final pjmedia_format_id PJMEDIA_FORMAT_PCMU;
    public static final pjmedia_format_id PJMEDIA_FORMAT_RGB24;
    public static final pjmedia_format_id PJMEDIA_FORMAT_RGB32;
    public static final pjmedia_format_id PJMEDIA_FORMAT_RGBA;
    public static final pjmedia_format_id PJMEDIA_FORMAT_ULAW;
    public static final pjmedia_format_id PJMEDIA_FORMAT_UYVY;
    public static final pjmedia_format_id PJMEDIA_FORMAT_YUY2;
    public static final pjmedia_format_id PJMEDIA_FORMAT_YV12;
    public static final pjmedia_format_id PJMEDIA_FORMAT_YVYU;
    private static int swigNext;
    private static pjmedia_format_id[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJMEDIA_FORMAT_L16 = new pjmedia_format_id("PJMEDIA_FORMAT_L16", pjsua2JNI.PJMEDIA_FORMAT_L16_get());
        PJMEDIA_FORMAT_PCM = new pjmedia_format_id("PJMEDIA_FORMAT_PCM", pjsua2JNI.PJMEDIA_FORMAT_PCM_get());
        PJMEDIA_FORMAT_PCMA = new pjmedia_format_id("PJMEDIA_FORMAT_PCMA", pjsua2JNI.PJMEDIA_FORMAT_PCMA_get());
        PJMEDIA_FORMAT_ALAW = new pjmedia_format_id("PJMEDIA_FORMAT_ALAW", pjsua2JNI.PJMEDIA_FORMAT_ALAW_get());
        PJMEDIA_FORMAT_PCMU = new pjmedia_format_id("PJMEDIA_FORMAT_PCMU", pjsua2JNI.PJMEDIA_FORMAT_PCMU_get());
        PJMEDIA_FORMAT_ULAW = new pjmedia_format_id("PJMEDIA_FORMAT_ULAW", pjsua2JNI.PJMEDIA_FORMAT_ULAW_get());
        PJMEDIA_FORMAT_AMR = new pjmedia_format_id("PJMEDIA_FORMAT_AMR", pjsua2JNI.PJMEDIA_FORMAT_AMR_get());
        PJMEDIA_FORMAT_G729 = new pjmedia_format_id("PJMEDIA_FORMAT_G729", pjsua2JNI.PJMEDIA_FORMAT_G729_get());
        PJMEDIA_FORMAT_ILBC = new pjmedia_format_id("PJMEDIA_FORMAT_ILBC", pjsua2JNI.PJMEDIA_FORMAT_ILBC_get());
        PJMEDIA_FORMAT_RGB24 = new pjmedia_format_id("PJMEDIA_FORMAT_RGB24", pjsua2JNI.PJMEDIA_FORMAT_RGB24_get());
        PJMEDIA_FORMAT_RGBA = new pjmedia_format_id("PJMEDIA_FORMAT_RGBA", pjsua2JNI.PJMEDIA_FORMAT_RGBA_get());
        PJMEDIA_FORMAT_BGRA = new pjmedia_format_id("PJMEDIA_FORMAT_BGRA", pjsua2JNI.PJMEDIA_FORMAT_BGRA_get());
        PJMEDIA_FORMAT_RGB32 = new pjmedia_format_id("PJMEDIA_FORMAT_RGB32", pjsua2JNI.PJMEDIA_FORMAT_RGB32_get());
        PJMEDIA_FORMAT_DIB = new pjmedia_format_id("PJMEDIA_FORMAT_DIB", pjsua2JNI.PJMEDIA_FORMAT_DIB_get());
        PJMEDIA_FORMAT_GBRP = new pjmedia_format_id("PJMEDIA_FORMAT_GBRP", pjsua2JNI.PJMEDIA_FORMAT_GBRP_get());
        PJMEDIA_FORMAT_AYUV = new pjmedia_format_id("PJMEDIA_FORMAT_AYUV", pjsua2JNI.PJMEDIA_FORMAT_AYUV_get());
        PJMEDIA_FORMAT_YUY2 = new pjmedia_format_id("PJMEDIA_FORMAT_YUY2", pjsua2JNI.PJMEDIA_FORMAT_YUY2_get());
        PJMEDIA_FORMAT_UYVY = new pjmedia_format_id("PJMEDIA_FORMAT_UYVY", pjsua2JNI.PJMEDIA_FORMAT_UYVY_get());
        PJMEDIA_FORMAT_YVYU = new pjmedia_format_id("PJMEDIA_FORMAT_YVYU", pjsua2JNI.PJMEDIA_FORMAT_YVYU_get());
        PJMEDIA_FORMAT_I420 = new pjmedia_format_id("PJMEDIA_FORMAT_I420", pjsua2JNI.PJMEDIA_FORMAT_I420_get());
        PJMEDIA_FORMAT_IYUV = new pjmedia_format_id("PJMEDIA_FORMAT_IYUV", pjsua2JNI.PJMEDIA_FORMAT_IYUV_get());
        PJMEDIA_FORMAT_YV12 = new pjmedia_format_id("PJMEDIA_FORMAT_YV12", pjsua2JNI.PJMEDIA_FORMAT_YV12_get());
        PJMEDIA_FORMAT_I422 = new pjmedia_format_id("PJMEDIA_FORMAT_I422", pjsua2JNI.PJMEDIA_FORMAT_I422_get());
        PJMEDIA_FORMAT_I420JPEG = new pjmedia_format_id("PJMEDIA_FORMAT_I420JPEG", pjsua2JNI.PJMEDIA_FORMAT_I420JPEG_get());
        PJMEDIA_FORMAT_I422JPEG = new pjmedia_format_id("PJMEDIA_FORMAT_I422JPEG", pjsua2JNI.PJMEDIA_FORMAT_I422JPEG_get());
        PJMEDIA_FORMAT_H261 = new pjmedia_format_id("PJMEDIA_FORMAT_H261", pjsua2JNI.PJMEDIA_FORMAT_H261_get());
        PJMEDIA_FORMAT_H263 = new pjmedia_format_id("PJMEDIA_FORMAT_H263", pjsua2JNI.PJMEDIA_FORMAT_H263_get());
        PJMEDIA_FORMAT_H263P = new pjmedia_format_id("PJMEDIA_FORMAT_H263P", pjsua2JNI.PJMEDIA_FORMAT_H263P_get());
        PJMEDIA_FORMAT_H264 = new pjmedia_format_id("PJMEDIA_FORMAT_H264", pjsua2JNI.PJMEDIA_FORMAT_H264_get());
        PJMEDIA_FORMAT_MJPEG = new pjmedia_format_id("PJMEDIA_FORMAT_MJPEG", pjsua2JNI.PJMEDIA_FORMAT_MJPEG_get());
        PJMEDIA_FORMAT_MPEG1VIDEO = new pjmedia_format_id("PJMEDIA_FORMAT_MPEG1VIDEO", pjsua2JNI.PJMEDIA_FORMAT_MPEG1VIDEO_get());
        PJMEDIA_FORMAT_MPEG2VIDEO = new pjmedia_format_id("PJMEDIA_FORMAT_MPEG2VIDEO", pjsua2JNI.PJMEDIA_FORMAT_MPEG2VIDEO_get());
        PJMEDIA_FORMAT_MPEG4 = new pjmedia_format_id("PJMEDIA_FORMAT_MPEG4", pjsua2JNI.PJMEDIA_FORMAT_MPEG4_get());
        swigValues = new pjmedia_format_id[]{PJMEDIA_FORMAT_L16, PJMEDIA_FORMAT_PCM, PJMEDIA_FORMAT_PCMA, PJMEDIA_FORMAT_ALAW, PJMEDIA_FORMAT_PCMU, PJMEDIA_FORMAT_ULAW, PJMEDIA_FORMAT_AMR, PJMEDIA_FORMAT_G729, PJMEDIA_FORMAT_ILBC, PJMEDIA_FORMAT_RGB24, PJMEDIA_FORMAT_RGBA, PJMEDIA_FORMAT_BGRA, PJMEDIA_FORMAT_RGB32, PJMEDIA_FORMAT_DIB, PJMEDIA_FORMAT_GBRP, PJMEDIA_FORMAT_AYUV, PJMEDIA_FORMAT_YUY2, PJMEDIA_FORMAT_UYVY, PJMEDIA_FORMAT_YVYU, PJMEDIA_FORMAT_I420, PJMEDIA_FORMAT_IYUV, PJMEDIA_FORMAT_YV12, PJMEDIA_FORMAT_I422, PJMEDIA_FORMAT_I420JPEG, PJMEDIA_FORMAT_I422JPEG, PJMEDIA_FORMAT_H261, PJMEDIA_FORMAT_H263, PJMEDIA_FORMAT_H263P, PJMEDIA_FORMAT_H264, PJMEDIA_FORMAT_MJPEG, PJMEDIA_FORMAT_MPEG1VIDEO, PJMEDIA_FORMAT_MPEG2VIDEO, PJMEDIA_FORMAT_MPEG4};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pjmedia_format_id swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pjmedia_format_id.class + " with value " + i);
    }

    private pjmedia_format_id(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pjmedia_format_id(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjmedia_format_id(String str, pjmedia_format_id org_pjsip_pjsua2_pjmedia_format_id) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pjmedia_format_id.swigValue;
        swigNext = this.swigValue + 1;
    }
}
