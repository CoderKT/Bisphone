package org.pjsip.pjsua2;

public class MediaConfig extends PersistentObject {
    private long swigCPtr;

    protected MediaConfig(long j, boolean z) {
        super(pjsua2JNI.MediaConfig_SWIGUpcast(j), z);
        this.swigCPtr = j;
    }

    protected static long getCPtr(MediaConfig mediaConfig) {
        return mediaConfig == null ? 0 : mediaConfig.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_MediaConfig(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public void setClockRate(long j) {
        pjsua2JNI.MediaConfig_clockRate_set(this.swigCPtr, this, j);
    }

    public long getClockRate() {
        return pjsua2JNI.MediaConfig_clockRate_get(this.swigCPtr, this);
    }

    public void setSndClockRate(long j) {
        pjsua2JNI.MediaConfig_sndClockRate_set(this.swigCPtr, this, j);
    }

    public long getSndClockRate() {
        return pjsua2JNI.MediaConfig_sndClockRate_get(this.swigCPtr, this);
    }

    public void setChannelCount(long j) {
        pjsua2JNI.MediaConfig_channelCount_set(this.swigCPtr, this, j);
    }

    public long getChannelCount() {
        return pjsua2JNI.MediaConfig_channelCount_get(this.swigCPtr, this);
    }

    public void setAudioFramePtime(long j) {
        pjsua2JNI.MediaConfig_audioFramePtime_set(this.swigCPtr, this, j);
    }

    public long getAudioFramePtime() {
        return pjsua2JNI.MediaConfig_audioFramePtime_get(this.swigCPtr, this);
    }

    public void setMaxMediaPorts(long j) {
        pjsua2JNI.MediaConfig_maxMediaPorts_set(this.swigCPtr, this, j);
    }

    public long getMaxMediaPorts() {
        return pjsua2JNI.MediaConfig_maxMediaPorts_get(this.swigCPtr, this);
    }

    public void setHasIoqueue(boolean z) {
        pjsua2JNI.MediaConfig_hasIoqueue_set(this.swigCPtr, this, z);
    }

    public boolean getHasIoqueue() {
        return pjsua2JNI.MediaConfig_hasIoqueue_get(this.swigCPtr, this);
    }

    public void setThreadCnt(long j) {
        pjsua2JNI.MediaConfig_threadCnt_set(this.swigCPtr, this, j);
    }

    public long getThreadCnt() {
        return pjsua2JNI.MediaConfig_threadCnt_get(this.swigCPtr, this);
    }

    public void setQuality(long j) {
        pjsua2JNI.MediaConfig_quality_set(this.swigCPtr, this, j);
    }

    public long getQuality() {
        return pjsua2JNI.MediaConfig_quality_get(this.swigCPtr, this);
    }

    public void setPtime(long j) {
        pjsua2JNI.MediaConfig_ptime_set(this.swigCPtr, this, j);
    }

    public long getPtime() {
        return pjsua2JNI.MediaConfig_ptime_get(this.swigCPtr, this);
    }

    public void setNoVad(boolean z) {
        pjsua2JNI.MediaConfig_noVad_set(this.swigCPtr, this, z);
    }

    public boolean getNoVad() {
        return pjsua2JNI.MediaConfig_noVad_get(this.swigCPtr, this);
    }

    public void setIlbcMode(long j) {
        pjsua2JNI.MediaConfig_ilbcMode_set(this.swigCPtr, this, j);
    }

    public long getIlbcMode() {
        return pjsua2JNI.MediaConfig_ilbcMode_get(this.swigCPtr, this);
    }

    public void setTxDropPct(long j) {
        pjsua2JNI.MediaConfig_txDropPct_set(this.swigCPtr, this, j);
    }

    public long getTxDropPct() {
        return pjsua2JNI.MediaConfig_txDropPct_get(this.swigCPtr, this);
    }

    public void setRxDropPct(long j) {
        pjsua2JNI.MediaConfig_rxDropPct_set(this.swigCPtr, this, j);
    }

    public long getRxDropPct() {
        return pjsua2JNI.MediaConfig_rxDropPct_get(this.swigCPtr, this);
    }

    public void setEcOptions(long j) {
        pjsua2JNI.MediaConfig_ecOptions_set(this.swigCPtr, this, j);
    }

    public long getEcOptions() {
        return pjsua2JNI.MediaConfig_ecOptions_get(this.swigCPtr, this);
    }

    public void setEcTailLen(long j) {
        pjsua2JNI.MediaConfig_ecTailLen_set(this.swigCPtr, this, j);
    }

    public long getEcTailLen() {
        return pjsua2JNI.MediaConfig_ecTailLen_get(this.swigCPtr, this);
    }

    public void setSndRecLatency(long j) {
        pjsua2JNI.MediaConfig_sndRecLatency_set(this.swigCPtr, this, j);
    }

    public long getSndRecLatency() {
        return pjsua2JNI.MediaConfig_sndRecLatency_get(this.swigCPtr, this);
    }

    public void setSndPlayLatency(long j) {
        pjsua2JNI.MediaConfig_sndPlayLatency_set(this.swigCPtr, this, j);
    }

    public long getSndPlayLatency() {
        return pjsua2JNI.MediaConfig_sndPlayLatency_get(this.swigCPtr, this);
    }

    public void setJbInit(int i) {
        pjsua2JNI.MediaConfig_jbInit_set(this.swigCPtr, this, i);
    }

    public int getJbInit() {
        return pjsua2JNI.MediaConfig_jbInit_get(this.swigCPtr, this);
    }

    public void setJbMinPre(int i) {
        pjsua2JNI.MediaConfig_jbMinPre_set(this.swigCPtr, this, i);
    }

    public int getJbMinPre() {
        return pjsua2JNI.MediaConfig_jbMinPre_get(this.swigCPtr, this);
    }

    public void setJbMaxPre(int i) {
        pjsua2JNI.MediaConfig_jbMaxPre_set(this.swigCPtr, this, i);
    }

    public int getJbMaxPre() {
        return pjsua2JNI.MediaConfig_jbMaxPre_get(this.swigCPtr, this);
    }

    public void setJbMax(int i) {
        pjsua2JNI.MediaConfig_jbMax_set(this.swigCPtr, this, i);
    }

    public int getJbMax() {
        return pjsua2JNI.MediaConfig_jbMax_get(this.swigCPtr, this);
    }

    public void setSndAutoCloseTime(int i) {
        pjsua2JNI.MediaConfig_sndAutoCloseTime_set(this.swigCPtr, this, i);
    }

    public int getSndAutoCloseTime() {
        return pjsua2JNI.MediaConfig_sndAutoCloseTime_get(this.swigCPtr, this);
    }

    public void setVidPreviewEnableNative(boolean z) {
        pjsua2JNI.MediaConfig_vidPreviewEnableNative_set(this.swigCPtr, this, z);
    }

    public boolean getVidPreviewEnableNative() {
        return pjsua2JNI.MediaConfig_vidPreviewEnableNative_get(this.swigCPtr, this);
    }

    public MediaConfig() {
        this(pjsua2JNI.new_MediaConfig(), true);
    }

    public void readObject(ContainerNode containerNode) {
        pjsua2JNI.MediaConfig_readObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public void writeObject(ContainerNode containerNode) {
        pjsua2JNI.MediaConfig_writeObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }
}
