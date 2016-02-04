package org.pjsip.pjsua2;

public class ConfPortInfo {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected ConfPortInfo(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(ConfPortInfo confPortInfo) {
        return confPortInfo == null ? 0 : confPortInfo.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_ConfPortInfo(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void setPortId(int i) {
        pjsua2JNI.ConfPortInfo_portId_set(this.swigCPtr, this, i);
    }

    public int getPortId() {
        return pjsua2JNI.ConfPortInfo_portId_get(this.swigCPtr, this);
    }

    public void setName(String str) {
        pjsua2JNI.ConfPortInfo_name_set(this.swigCPtr, this, str);
    }

    public String getName() {
        return pjsua2JNI.ConfPortInfo_name_get(this.swigCPtr, this);
    }

    public void setFormat(MediaFormatAudio mediaFormatAudio) {
        pjsua2JNI.ConfPortInfo_format_set(this.swigCPtr, this, MediaFormatAudio.getCPtr(mediaFormatAudio), mediaFormatAudio);
    }

    public MediaFormatAudio getFormat() {
        long ConfPortInfo_format_get = pjsua2JNI.ConfPortInfo_format_get(this.swigCPtr, this);
        return ConfPortInfo_format_get == 0 ? null : new MediaFormatAudio(ConfPortInfo_format_get, false);
    }

    public void setTxLevelAdj(float f) {
        pjsua2JNI.ConfPortInfo_txLevelAdj_set(this.swigCPtr, this, f);
    }

    public float getTxLevelAdj() {
        return pjsua2JNI.ConfPortInfo_txLevelAdj_get(this.swigCPtr, this);
    }

    public void setRxLevelAdj(float f) {
        pjsua2JNI.ConfPortInfo_rxLevelAdj_set(this.swigCPtr, this, f);
    }

    public float getRxLevelAdj() {
        return pjsua2JNI.ConfPortInfo_rxLevelAdj_get(this.swigCPtr, this);
    }

    public void setListeners(IntVector intVector) {
        pjsua2JNI.ConfPortInfo_listeners_set(this.swigCPtr, this, IntVector.getCPtr(intVector), intVector);
    }

    public IntVector getListeners() {
        long ConfPortInfo_listeners_get = pjsua2JNI.ConfPortInfo_listeners_get(this.swigCPtr, this);
        return ConfPortInfo_listeners_get == 0 ? null : new IntVector(ConfPortInfo_listeners_get, false);
    }

    public ConfPortInfo() {
        this(pjsua2JNI.new_ConfPortInfo(), true);
    }
}
