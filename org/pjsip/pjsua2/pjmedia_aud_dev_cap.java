package org.pjsip.pjsua2;

public final class pjmedia_aud_dev_cap {
    public static final pjmedia_aud_dev_cap PJMEDIA_AUD_DEV_CAP_CNG;
    public static final pjmedia_aud_dev_cap PJMEDIA_AUD_DEV_CAP_EC;
    public static final pjmedia_aud_dev_cap PJMEDIA_AUD_DEV_CAP_EC_TAIL;
    public static final pjmedia_aud_dev_cap PJMEDIA_AUD_DEV_CAP_EXT_FORMAT;
    public static final pjmedia_aud_dev_cap PJMEDIA_AUD_DEV_CAP_INPUT_LATENCY;
    public static final pjmedia_aud_dev_cap PJMEDIA_AUD_DEV_CAP_INPUT_ROUTE;
    public static final pjmedia_aud_dev_cap PJMEDIA_AUD_DEV_CAP_INPUT_SIGNAL_METER;
    public static final pjmedia_aud_dev_cap PJMEDIA_AUD_DEV_CAP_INPUT_VOLUME_SETTING;
    public static final pjmedia_aud_dev_cap PJMEDIA_AUD_DEV_CAP_MAX;
    public static final pjmedia_aud_dev_cap PJMEDIA_AUD_DEV_CAP_OUTPUT_LATENCY;
    public static final pjmedia_aud_dev_cap PJMEDIA_AUD_DEV_CAP_OUTPUT_ROUTE;
    public static final pjmedia_aud_dev_cap PJMEDIA_AUD_DEV_CAP_OUTPUT_SIGNAL_METER;
    public static final pjmedia_aud_dev_cap PJMEDIA_AUD_DEV_CAP_OUTPUT_VOLUME_SETTING;
    public static final pjmedia_aud_dev_cap PJMEDIA_AUD_DEV_CAP_PLC;
    public static final pjmedia_aud_dev_cap PJMEDIA_AUD_DEV_CAP_VAD;
    private static int swigNext;
    private static pjmedia_aud_dev_cap[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJMEDIA_AUD_DEV_CAP_EXT_FORMAT = new pjmedia_aud_dev_cap("PJMEDIA_AUD_DEV_CAP_EXT_FORMAT", pjsua2JNI.PJMEDIA_AUD_DEV_CAP_EXT_FORMAT_get());
        PJMEDIA_AUD_DEV_CAP_INPUT_LATENCY = new pjmedia_aud_dev_cap("PJMEDIA_AUD_DEV_CAP_INPUT_LATENCY", pjsua2JNI.PJMEDIA_AUD_DEV_CAP_INPUT_LATENCY_get());
        PJMEDIA_AUD_DEV_CAP_OUTPUT_LATENCY = new pjmedia_aud_dev_cap("PJMEDIA_AUD_DEV_CAP_OUTPUT_LATENCY", pjsua2JNI.PJMEDIA_AUD_DEV_CAP_OUTPUT_LATENCY_get());
        PJMEDIA_AUD_DEV_CAP_INPUT_VOLUME_SETTING = new pjmedia_aud_dev_cap("PJMEDIA_AUD_DEV_CAP_INPUT_VOLUME_SETTING", pjsua2JNI.PJMEDIA_AUD_DEV_CAP_INPUT_VOLUME_SETTING_get());
        PJMEDIA_AUD_DEV_CAP_OUTPUT_VOLUME_SETTING = new pjmedia_aud_dev_cap("PJMEDIA_AUD_DEV_CAP_OUTPUT_VOLUME_SETTING", pjsua2JNI.PJMEDIA_AUD_DEV_CAP_OUTPUT_VOLUME_SETTING_get());
        PJMEDIA_AUD_DEV_CAP_INPUT_SIGNAL_METER = new pjmedia_aud_dev_cap("PJMEDIA_AUD_DEV_CAP_INPUT_SIGNAL_METER", pjsua2JNI.PJMEDIA_AUD_DEV_CAP_INPUT_SIGNAL_METER_get());
        PJMEDIA_AUD_DEV_CAP_OUTPUT_SIGNAL_METER = new pjmedia_aud_dev_cap("PJMEDIA_AUD_DEV_CAP_OUTPUT_SIGNAL_METER", pjsua2JNI.PJMEDIA_AUD_DEV_CAP_OUTPUT_SIGNAL_METER_get());
        PJMEDIA_AUD_DEV_CAP_INPUT_ROUTE = new pjmedia_aud_dev_cap("PJMEDIA_AUD_DEV_CAP_INPUT_ROUTE", pjsua2JNI.PJMEDIA_AUD_DEV_CAP_INPUT_ROUTE_get());
        PJMEDIA_AUD_DEV_CAP_OUTPUT_ROUTE = new pjmedia_aud_dev_cap("PJMEDIA_AUD_DEV_CAP_OUTPUT_ROUTE", pjsua2JNI.PJMEDIA_AUD_DEV_CAP_OUTPUT_ROUTE_get());
        PJMEDIA_AUD_DEV_CAP_EC = new pjmedia_aud_dev_cap("PJMEDIA_AUD_DEV_CAP_EC", pjsua2JNI.PJMEDIA_AUD_DEV_CAP_EC_get());
        PJMEDIA_AUD_DEV_CAP_EC_TAIL = new pjmedia_aud_dev_cap("PJMEDIA_AUD_DEV_CAP_EC_TAIL", pjsua2JNI.PJMEDIA_AUD_DEV_CAP_EC_TAIL_get());
        PJMEDIA_AUD_DEV_CAP_VAD = new pjmedia_aud_dev_cap("PJMEDIA_AUD_DEV_CAP_VAD", pjsua2JNI.PJMEDIA_AUD_DEV_CAP_VAD_get());
        PJMEDIA_AUD_DEV_CAP_CNG = new pjmedia_aud_dev_cap("PJMEDIA_AUD_DEV_CAP_CNG", pjsua2JNI.PJMEDIA_AUD_DEV_CAP_CNG_get());
        PJMEDIA_AUD_DEV_CAP_PLC = new pjmedia_aud_dev_cap("PJMEDIA_AUD_DEV_CAP_PLC", pjsua2JNI.PJMEDIA_AUD_DEV_CAP_PLC_get());
        PJMEDIA_AUD_DEV_CAP_MAX = new pjmedia_aud_dev_cap("PJMEDIA_AUD_DEV_CAP_MAX", pjsua2JNI.PJMEDIA_AUD_DEV_CAP_MAX_get());
        swigValues = new pjmedia_aud_dev_cap[]{PJMEDIA_AUD_DEV_CAP_EXT_FORMAT, PJMEDIA_AUD_DEV_CAP_INPUT_LATENCY, PJMEDIA_AUD_DEV_CAP_OUTPUT_LATENCY, PJMEDIA_AUD_DEV_CAP_INPUT_VOLUME_SETTING, PJMEDIA_AUD_DEV_CAP_OUTPUT_VOLUME_SETTING, PJMEDIA_AUD_DEV_CAP_INPUT_SIGNAL_METER, PJMEDIA_AUD_DEV_CAP_OUTPUT_SIGNAL_METER, PJMEDIA_AUD_DEV_CAP_INPUT_ROUTE, PJMEDIA_AUD_DEV_CAP_OUTPUT_ROUTE, PJMEDIA_AUD_DEV_CAP_EC, PJMEDIA_AUD_DEV_CAP_EC_TAIL, PJMEDIA_AUD_DEV_CAP_VAD, PJMEDIA_AUD_DEV_CAP_CNG, PJMEDIA_AUD_DEV_CAP_PLC, PJMEDIA_AUD_DEV_CAP_MAX};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pjmedia_aud_dev_cap swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pjmedia_aud_dev_cap.class + " with value " + i);
    }

    private pjmedia_aud_dev_cap(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pjmedia_aud_dev_cap(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjmedia_aud_dev_cap(String str, pjmedia_aud_dev_cap org_pjsip_pjsua2_pjmedia_aud_dev_cap) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pjmedia_aud_dev_cap.swigValue;
        swigNext = this.swigValue + 1;
    }
}
