package org.pjsip.pjsua2;

public final class pjmedia_file_player_option {
    public static final pjmedia_file_player_option PJMEDIA_FILE_NO_LOOP;
    private static int swigNext;
    private static pjmedia_file_player_option[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        PJMEDIA_FILE_NO_LOOP = new pjmedia_file_player_option("PJMEDIA_FILE_NO_LOOP", pjsua2JNI.PJMEDIA_FILE_NO_LOOP_get());
        swigValues = new pjmedia_file_player_option[]{PJMEDIA_FILE_NO_LOOP};
        swigNext = 0;
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static pjmedia_file_player_option swigToEnum(int i) {
        if (i < swigValues.length && i >= 0 && swigValues[i].swigValue == i) {
            return swigValues[i];
        }
        for (int i2 = 0; i2 < swigValues.length; i2++) {
            if (swigValues[i2].swigValue == i) {
                return swigValues[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + pjmedia_file_player_option.class + " with value " + i);
    }

    private pjmedia_file_player_option(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private pjmedia_file_player_option(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private pjmedia_file_player_option(String str, pjmedia_file_player_option org_pjsip_pjsua2_pjmedia_file_player_option) {
        this.swigName = str;
        this.swigValue = org_pjsip_pjsua2_pjmedia_file_player_option.swigValue;
        swigNext = this.swigValue + 1;
    }
}
