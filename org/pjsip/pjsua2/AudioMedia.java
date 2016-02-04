package org.pjsip.pjsua2;

public class AudioMedia extends Media {
    private long swigCPtr;

    protected AudioMedia(long j, boolean z) {
        super(pjsua2JNI.AudioMedia_SWIGUpcast(j), z);
        this.swigCPtr = j;
    }

    protected static long getCPtr(AudioMedia audioMedia) {
        return audioMedia == null ? 0 : audioMedia.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_AudioMedia(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public ConfPortInfo getPortInfo() {
        return new ConfPortInfo(pjsua2JNI.AudioMedia_getPortInfo(this.swigCPtr, this), true);
    }

    public int getPortId() {
        return pjsua2JNI.AudioMedia_getPortId(this.swigCPtr, this);
    }

    public static ConfPortInfo getPortInfoFromId(int i) {
        return new ConfPortInfo(pjsua2JNI.AudioMedia_getPortInfoFromId(i), true);
    }

    public void startTransmit(AudioMedia audioMedia) {
        pjsua2JNI.AudioMedia_startTransmit(this.swigCPtr, this, getCPtr(audioMedia), audioMedia);
    }

    public void stopTransmit(AudioMedia audioMedia) {
        pjsua2JNI.AudioMedia_stopTransmit(this.swigCPtr, this, getCPtr(audioMedia), audioMedia);
    }

    public void adjustRxLevel(float f) {
        pjsua2JNI.AudioMedia_adjustRxLevel(this.swigCPtr, this, f);
    }

    public void adjustTxLevel(float f) {
        pjsua2JNI.AudioMedia_adjustTxLevel(this.swigCPtr, this, f);
    }

    public long getRxLevel() {
        return pjsua2JNI.AudioMedia_getRxLevel(this.swigCPtr, this);
    }

    public long getTxLevel() {
        return pjsua2JNI.AudioMedia_getTxLevel(this.swigCPtr, this);
    }

    public static AudioMedia typecastFromMedia(Media media) {
        long AudioMedia_typecastFromMedia = pjsua2JNI.AudioMedia_typecastFromMedia(Media.getCPtr(media), media);
        return AudioMedia_typecastFromMedia == 0 ? null : new AudioMedia(AudioMedia_typecastFromMedia, false);
    }
}
