package org.pjsip.pjsua2;

public final class pjmedia_type {
    public static final pjmedia_type PJMEDIA_TYPE_APPLICATION;
    public static final pjmedia_type PJMEDIA_TYPE_AUDIO;
    public static final pjmedia_type PJMEDIA_TYPE_NONE;
    public static final pjmedia_type PJMEDIA_TYPE_UNKNOWN;
    public static final pjmedia_type PJMEDIA_TYPE_VIDEO;
    private static int swigNext;
    private static pjmedia_type[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJMEDIA_TYPE_NONE = new pjmedia_type("PJMEDIA_TYPE_NONE");
        PJMEDIA_TYPE_AUDIO = new pjmedia_type("PJMEDIA_TYPE_AUDIO");
        PJMEDIA_TYPE_VIDEO = new pjmedia_type("PJMEDIA_TYPE_VIDEO");
        PJMEDIA_TYPE_APPLICATION = new pjmedia_type("PJMEDIA_TYPE_APPLICATION");
        PJMEDIA_TYPE_UNKNOWN = new pjmedia_type("PJMEDIA_TYPE_UNKNOWN");
        swigValues = new pjmedia_type[]{PJMEDIA_TYPE_NONE, PJMEDIA_TYPE_AUDIO, PJMEDIA_TYPE_VIDEO, PJMEDIA_TYPE_APPLICATION, PJMEDIA_TYPE_UNKNOWN};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pjmedia_type swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pjmedia_type.class + " with value " + i);
    }

    private pjmedia_type(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pjmedia_type(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjmedia_type(String str, pjmedia_type org_pjsip_pjsua2_pjmedia_type) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pjmedia_type.swigValue;
        swigNext = this.swigValue + 1;
    }
}
