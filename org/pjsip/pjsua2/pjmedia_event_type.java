package org.pjsip.pjsua2;

public final class pjmedia_event_type {
    public static final pjmedia_event_type PJMEDIA_EVENT_FMT_CHANGED;
    public static final pjmedia_event_type PJMEDIA_EVENT_KEYFRAME_FOUND;
    public static final pjmedia_event_type PJMEDIA_EVENT_KEYFRAME_MISSING;
    public static final pjmedia_event_type PJMEDIA_EVENT_MOUSE_BTN_DOWN;
    public static final pjmedia_event_type PJMEDIA_EVENT_NONE;
    public static final pjmedia_event_type PJMEDIA_EVENT_ORIENT_CHANGED;
    public static final pjmedia_event_type PJMEDIA_EVENT_WND_CLOSED;
    public static final pjmedia_event_type PJMEDIA_EVENT_WND_CLOSING;
    public static final pjmedia_event_type PJMEDIA_EVENT_WND_RESIZED;
    private static int swigNext;
    private static pjmedia_event_type[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJMEDIA_EVENT_NONE = new pjmedia_event_type("PJMEDIA_EVENT_NONE");
        PJMEDIA_EVENT_FMT_CHANGED = new pjmedia_event_type("PJMEDIA_EVENT_FMT_CHANGED", pjsua2JNI.PJMEDIA_EVENT_FMT_CHANGED_get());
        PJMEDIA_EVENT_WND_CLOSING = new pjmedia_event_type("PJMEDIA_EVENT_WND_CLOSING", pjsua2JNI.PJMEDIA_EVENT_WND_CLOSING_get());
        PJMEDIA_EVENT_WND_CLOSED = new pjmedia_event_type("PJMEDIA_EVENT_WND_CLOSED", pjsua2JNI.PJMEDIA_EVENT_WND_CLOSED_get());
        PJMEDIA_EVENT_WND_RESIZED = new pjmedia_event_type("PJMEDIA_EVENT_WND_RESIZED", pjsua2JNI.PJMEDIA_EVENT_WND_RESIZED_get());
        PJMEDIA_EVENT_MOUSE_BTN_DOWN = new pjmedia_event_type("PJMEDIA_EVENT_MOUSE_BTN_DOWN", pjsua2JNI.PJMEDIA_EVENT_MOUSE_BTN_DOWN_get());
        PJMEDIA_EVENT_KEYFRAME_FOUND = new pjmedia_event_type("PJMEDIA_EVENT_KEYFRAME_FOUND", pjsua2JNI.PJMEDIA_EVENT_KEYFRAME_FOUND_get());
        PJMEDIA_EVENT_KEYFRAME_MISSING = new pjmedia_event_type("PJMEDIA_EVENT_KEYFRAME_MISSING", pjsua2JNI.PJMEDIA_EVENT_KEYFRAME_MISSING_get());
        PJMEDIA_EVENT_ORIENT_CHANGED = new pjmedia_event_type("PJMEDIA_EVENT_ORIENT_CHANGED", pjsua2JNI.PJMEDIA_EVENT_ORIENT_CHANGED_get());
        swigValues = new pjmedia_event_type[]{PJMEDIA_EVENT_NONE, PJMEDIA_EVENT_FMT_CHANGED, PJMEDIA_EVENT_WND_CLOSING, PJMEDIA_EVENT_WND_CLOSED, PJMEDIA_EVENT_WND_RESIZED, PJMEDIA_EVENT_MOUSE_BTN_DOWN, PJMEDIA_EVENT_KEYFRAME_FOUND, PJMEDIA_EVENT_KEYFRAME_MISSING, PJMEDIA_EVENT_ORIENT_CHANGED};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pjmedia_event_type swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pjmedia_event_type.class + " with value " + i);
    }

    private pjmedia_event_type(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pjmedia_event_type(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjmedia_event_type(String str, pjmedia_event_type org_pjsip_pjsua2_pjmedia_event_type) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pjmedia_event_type.swigValue;
        swigNext = this.swigValue + 1;
    }
}
