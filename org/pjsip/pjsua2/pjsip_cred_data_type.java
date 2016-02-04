package org.pjsip.pjsua2;

public final class pjsip_cred_data_type {
    public static final pjsip_cred_data_type PJSIP_CRED_DATA_DIGEST;
    public static final pjsip_cred_data_type PJSIP_CRED_DATA_EXT_AKA;
    public static final pjsip_cred_data_type PJSIP_CRED_DATA_PLAIN_PASSWD;
    private static int swigNext;
    private static pjsip_cred_data_type[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJSIP_CRED_DATA_PLAIN_PASSWD = new pjsip_cred_data_type("PJSIP_CRED_DATA_PLAIN_PASSWD", pjsua2JNI.PJSIP_CRED_DATA_PLAIN_PASSWD_get());
        PJSIP_CRED_DATA_DIGEST = new pjsip_cred_data_type("PJSIP_CRED_DATA_DIGEST", pjsua2JNI.PJSIP_CRED_DATA_DIGEST_get());
        PJSIP_CRED_DATA_EXT_AKA = new pjsip_cred_data_type("PJSIP_CRED_DATA_EXT_AKA", pjsua2JNI.PJSIP_CRED_DATA_EXT_AKA_get());
        swigValues = new pjsip_cred_data_type[]{PJSIP_CRED_DATA_PLAIN_PASSWD, PJSIP_CRED_DATA_DIGEST, PJSIP_CRED_DATA_EXT_AKA};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pjsip_cred_data_type swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pjsip_cred_data_type.class + " with value " + i);
    }

    private pjsip_cred_data_type(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pjsip_cred_data_type(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjsip_cred_data_type(String str, pjsip_cred_data_type org_pjsip_pjsua2_pjsip_cred_data_type) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pjsip_cred_data_type.swigValue;
        swigNext = this.swigValue + 1;
    }
}
