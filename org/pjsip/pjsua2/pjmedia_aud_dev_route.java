package org.pjsip.pjsua2;

public final class pjmedia_aud_dev_route {
    public static final pjmedia_aud_dev_route PJMEDIA_AUD_DEV_ROUTE_BLUETOOTH;
    public static final pjmedia_aud_dev_route PJMEDIA_AUD_DEV_ROUTE_DEFAULT;
    public static final pjmedia_aud_dev_route PJMEDIA_AUD_DEV_ROUTE_EARPIECE;
    public static final pjmedia_aud_dev_route PJMEDIA_AUD_DEV_ROUTE_LOUDSPEAKER;
    private static int swigNext;
    private static pjmedia_aud_dev_route[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJMEDIA_AUD_DEV_ROUTE_DEFAULT = new pjmedia_aud_dev_route("PJMEDIA_AUD_DEV_ROUTE_DEFAULT", pjsua2JNI.PJMEDIA_AUD_DEV_ROUTE_DEFAULT_get());
        PJMEDIA_AUD_DEV_ROUTE_LOUDSPEAKER = new pjmedia_aud_dev_route("PJMEDIA_AUD_DEV_ROUTE_LOUDSPEAKER", pjsua2JNI.PJMEDIA_AUD_DEV_ROUTE_LOUDSPEAKER_get());
        PJMEDIA_AUD_DEV_ROUTE_EARPIECE = new pjmedia_aud_dev_route("PJMEDIA_AUD_DEV_ROUTE_EARPIECE", pjsua2JNI.PJMEDIA_AUD_DEV_ROUTE_EARPIECE_get());
        PJMEDIA_AUD_DEV_ROUTE_BLUETOOTH = new pjmedia_aud_dev_route("PJMEDIA_AUD_DEV_ROUTE_BLUETOOTH", pjsua2JNI.PJMEDIA_AUD_DEV_ROUTE_BLUETOOTH_get());
        swigValues = new pjmedia_aud_dev_route[]{PJMEDIA_AUD_DEV_ROUTE_DEFAULT, PJMEDIA_AUD_DEV_ROUTE_LOUDSPEAKER, PJMEDIA_AUD_DEV_ROUTE_EARPIECE, PJMEDIA_AUD_DEV_ROUTE_BLUETOOTH};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pjmedia_aud_dev_route swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pjmedia_aud_dev_route.class + " with value " + i);
    }

    private pjmedia_aud_dev_route(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pjmedia_aud_dev_route(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjmedia_aud_dev_route(String str, pjmedia_aud_dev_route org_pjsip_pjsua2_pjmedia_aud_dev_route) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pjmedia_aud_dev_route.swigValue;
        swigNext = this.swigValue + 1;
    }
}
