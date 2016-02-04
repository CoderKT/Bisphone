package org.pjsip.pjsua2;

public class AudioDevInfo {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected AudioDevInfo(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(AudioDevInfo audioDevInfo) {
        return audioDevInfo == null ? 0 : audioDevInfo.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_AudioDevInfo(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setName(String str) {
        pjsua2JNI.AudioDevInfo_name_set(this.swigCPtr, this, str);
    }

    public String getName() {
        return pjsua2JNI.AudioDevInfo_name_get(this.swigCPtr, this);
    }

    public void setInputCount(long j) {
        pjsua2JNI.AudioDevInfo_inputCount_set(this.swigCPtr, this, j);
    }

    public long getInputCount() {
        return pjsua2JNI.AudioDevInfo_inputCount_get(this.swigCPtr, this);
    }

    public void setOutputCount(long j) {
        pjsua2JNI.AudioDevInfo_outputCount_set(this.swigCPtr, this, j);
    }

    public long getOutputCount() {
        return pjsua2JNI.AudioDevInfo_outputCount_get(this.swigCPtr, this);
    }

    public void setDefaultSamplesPerSec(long j) {
        pjsua2JNI.AudioDevInfo_defaultSamplesPerSec_set(this.swigCPtr, this, j);
    }

    public long getDefaultSamplesPerSec() {
        return pjsua2JNI.AudioDevInfo_defaultSamplesPerSec_get(this.swigCPtr, this);
    }

    public void setDriver(String str) {
        pjsua2JNI.AudioDevInfo_driver_set(this.swigCPtr, this, str);
    }

    public String getDriver() {
        return pjsua2JNI.AudioDevInfo_driver_get(this.swigCPtr, this);
    }

    public void setCaps(long j) {
        pjsua2JNI.AudioDevInfo_caps_set(this.swigCPtr, this, j);
    }

    public long getCaps() {
        return pjsua2JNI.AudioDevInfo_caps_get(this.swigCPtr, this);
    }

    public void setRoutes(long j) {
        pjsua2JNI.AudioDevInfo_routes_set(this.swigCPtr, this, j);
    }

    public long getRoutes() {
        return pjsua2JNI.AudioDevInfo_routes_get(this.swigCPtr, this);
    }

    public void setExtFmt(MediaFormatVector mediaFormatVector) {
        pjsua2JNI.AudioDevInfo_extFmt_set(this.swigCPtr, this, MediaFormatVector.getCPtr(mediaFormatVector), mediaFormatVector);
    }

    public MediaFormatVector getExtFmt() {
        long AudioDevInfo_extFmt_get = pjsua2JNI.AudioDevInfo_extFmt_get(this.swigCPtr, this);
        return AudioDevInfo_extFmt_get == 0 ? null : new MediaFormatVector(AudioDevInfo_extFmt_get, false);
    }

    public AudioDevInfo() {
        this(pjsua2JNI.new_AudioDevInfo(), true);
    }
}
