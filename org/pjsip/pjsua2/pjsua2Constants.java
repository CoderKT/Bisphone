package org.pjsip.pjsua2;

public interface pjsua2Constants {
    public static final int INVALID_ID;
    public static final int SUCCESS;

    static {
        INVALID_ID = pjsua2JNI.INVALID_ID_get();
        SUCCESS = pjsua2JNI.SUCCESS_get();
    }
}
