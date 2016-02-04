package org.pjsip.pjsua2;

public final class pjsip_ssl_method {
    public static final pjsip_ssl_method PJSIP_SSLV23_METHOD;
    public static final pjsip_ssl_method PJSIP_SSLV2_METHOD;
    public static final pjsip_ssl_method PJSIP_SSLV3_METHOD;
    public static final pjsip_ssl_method PJSIP_SSL_UNSPECIFIED_METHOD;
    public static final pjsip_ssl_method PJSIP_TLSV1_METHOD;
    private static int swigNext;
    private static pjsip_ssl_method[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJSIP_SSL_UNSPECIFIED_METHOD = new pjsip_ssl_method("PJSIP_SSL_UNSPECIFIED_METHOD", pjsua2JNI.PJSIP_SSL_UNSPECIFIED_METHOD_get());
        PJSIP_TLSV1_METHOD = new pjsip_ssl_method("PJSIP_TLSV1_METHOD", pjsua2JNI.PJSIP_TLSV1_METHOD_get());
        PJSIP_SSLV2_METHOD = new pjsip_ssl_method("PJSIP_SSLV2_METHOD", pjsua2JNI.PJSIP_SSLV2_METHOD_get());
        PJSIP_SSLV3_METHOD = new pjsip_ssl_method("PJSIP_SSLV3_METHOD", pjsua2JNI.PJSIP_SSLV3_METHOD_get());
        PJSIP_SSLV23_METHOD = new pjsip_ssl_method("PJSIP_SSLV23_METHOD", pjsua2JNI.PJSIP_SSLV23_METHOD_get());
        swigValues = new pjsip_ssl_method[]{PJSIP_SSL_UNSPECIFIED_METHOD, PJSIP_TLSV1_METHOD, PJSIP_SSLV2_METHOD, PJSIP_SSLV3_METHOD, PJSIP_SSLV23_METHOD};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pjsip_ssl_method swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pjsip_ssl_method.class + " with value " + i);
    }

    private pjsip_ssl_method(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pjsip_ssl_method(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsip_ssl_method(String str, pjsip_ssl_method org_pjsip_pjsua2_pjsip_ssl_method) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pjsip_ssl_method.swigValue;
        swigNext = this.swigValue + 1;
    }
}
