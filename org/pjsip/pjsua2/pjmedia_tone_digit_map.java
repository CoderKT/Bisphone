package org.pjsip.pjsua2;

public class pjmedia_tone_digit_map {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected pjmedia_tone_digit_map(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(pjmedia_tone_digit_map org_pjsip_pjsua2_pjmedia_tone_digit_map) {
        return org_pjsip_pjsua2_pjmedia_tone_digit_map == null ? 0 : org_pjsip_pjsua2_pjmedia_tone_digit_map.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_pjmedia_tone_digit_map(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setCount(long j) {
        pjsua2JNI.pjmedia_tone_digit_map_count_set(this.swigCPtr, this, j);
    }

    public long getCount() {
        return pjsua2JNI.pjmedia_tone_digit_map_count_get(this.swigCPtr, this);
    }

    public pjmedia_tone_digit_map() {
        this(pjsua2JNI.new_pjmedia_tone_digit_map(), true);
    }
}
