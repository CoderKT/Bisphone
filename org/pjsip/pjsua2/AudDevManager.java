package org.pjsip.pjsua2;

public class AudDevManager {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected AudDevManager(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(AudDevManager audDevManager) {
        return audDevManager == null ? 0 : audDevManager.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                throw new UnsupportedOperationException("C++ destructor does not have public access");
            }
            this.swigCPtr = 0;
        }
    }

    public int getCaptureDev() {
        return pjsua2JNI.AudDevManager_getCaptureDev(this.swigCPtr, this);
    }

    public AudioMedia getCaptureDevMedia() {
        return new AudioMedia(pjsua2JNI.AudDevManager_getCaptureDevMedia(this.swigCPtr, this), false);
    }

    public int getPlaybackDev() {
        return pjsua2JNI.AudDevManager_getPlaybackDev(this.swigCPtr, this);
    }

    public AudioMedia getPlaybackDevMedia() {
        return new AudioMedia(pjsua2JNI.AudDevManager_getPlaybackDevMedia(this.swigCPtr, this), false);
    }

    public void setCaptureDev(int i) {
        pjsua2JNI.AudDevManager_setCaptureDev(this.swigCPtr, this, i);
    }

    public void setPlaybackDev(int i) {
        pjsua2JNI.AudDevManager_setPlaybackDev(this.swigCPtr, this, i);
    }

    public AudioDevInfoVector enumDev() {
        return new AudioDevInfoVector(pjsua2JNI.AudDevManager_enumDev(this.swigCPtr, this), false);
    }

    public void setNullDev() {
        pjsua2JNI.AudDevManager_setNullDev(this.swigCPtr, this);
    }

    public SWIGTYPE_p_p_void setNoDev() {
        long AudDevManager_setNoDev = pjsua2JNI.AudDevManager_setNoDev(this.swigCPtr, this);
        return AudDevManager_setNoDev == 0 ? null : new SWIGTYPE_p_p_void(AudDevManager_setNoDev, false);
    }

    public void setEcOptions(long j, long j2) {
        pjsua2JNI.AudDevManager_setEcOptions(this.swigCPtr, this, j, j2);
    }

    public long getEcTail() {
        return pjsua2JNI.AudDevManager_getEcTail(this.swigCPtr, this);
    }

    public boolean sndIsActive() {
        return pjsua2JNI.AudDevManager_sndIsActive(this.swigCPtr, this);
    }

    public void refreshDevs() {
        pjsua2JNI.AudDevManager_refreshDevs(this.swigCPtr, this);
    }

    public long getDevCount() {
        return pjsua2JNI.AudDevManager_getDevCount(this.swigCPtr, this);
    }

    public AudioDevInfo getDevInfo(int i) {
        return new AudioDevInfo(pjsua2JNI.AudDevManager_getDevInfo(this.swigCPtr, this, i), true);
    }

    public int lookupDev(String str, String str2) {
        return pjsua2JNI.AudDevManager_lookupDev(this.swigCPtr, this, str, str2);
    }

    public String capName(pjmedia_aud_dev_cap org_pjsip_pjsua2_pjmedia_aud_dev_cap) {
        return pjsua2JNI.AudDevManager_capName(this.swigCPtr, this, org_pjsip_pjsua2_pjmedia_aud_dev_cap.swigValue());
    }

    public void setExtFormat(MediaFormatAudio mediaFormatAudio, boolean z) {
        pjsua2JNI.AudDevManager_setExtFormat__SWIG_0(this.swigCPtr, this, MediaFormatAudio.getCPtr(mediaFormatAudio), mediaFormatAudio, z);
    }

    public void setExtFormat(MediaFormatAudio mediaFormatAudio) {
        pjsua2JNI.AudDevManager_setExtFormat__SWIG_1(this.swigCPtr, this, MediaFormatAudio.getCPtr(mediaFormatAudio), mediaFormatAudio);
    }

    public MediaFormatAudio getExtFormat() {
        return new MediaFormatAudio(pjsua2JNI.AudDevManager_getExtFormat(this.swigCPtr, this), true);
    }

    public void setInputLatency(long j, boolean z) {
        pjsua2JNI.AudDevManager_setInputLatency__SWIG_0(this.swigCPtr, this, j, z);
    }

    public void setInputLatency(long j) {
        pjsua2JNI.AudDevManager_setInputLatency__SWIG_1(this.swigCPtr, this, j);
    }

    public long getInputLatency() {
        return pjsua2JNI.AudDevManager_getInputLatency(this.swigCPtr, this);
    }

    public void setOutputLatency(long j, boolean z) {
        pjsua2JNI.AudDevManager_setOutputLatency__SWIG_0(this.swigCPtr, this, j, z);
    }

    public void setOutputLatency(long j) {
        pjsua2JNI.AudDevManager_setOutputLatency__SWIG_1(this.swigCPtr, this, j);
    }

    public long getOutputLatency() {
        return pjsua2JNI.AudDevManager_getOutputLatency(this.swigCPtr, this);
    }

    public void setInputVolume(long j, boolean z) {
        pjsua2JNI.AudDevManager_setInputVolume__SWIG_0(this.swigCPtr, this, j, z);
    }

    public void setInputVolume(long j) {
        pjsua2JNI.AudDevManager_setInputVolume__SWIG_1(this.swigCPtr, this, j);
    }

    public long getInputVolume() {
        return pjsua2JNI.AudDevManager_getInputVolume(this.swigCPtr, this);
    }

    public void setOutputVolume(long j, boolean z) {
        pjsua2JNI.AudDevManager_setOutputVolume__SWIG_0(this.swigCPtr, this, j, z);
    }

    public void setOutputVolume(long j) {
        pjsua2JNI.AudDevManager_setOutputVolume__SWIG_1(this.swigCPtr, this, j);
    }

    public long getOutputVolume() {
        return pjsua2JNI.AudDevManager_getOutputVolume(this.swigCPtr, this);
    }

    public long getInputSignal() {
        return pjsua2JNI.AudDevManager_getInputSignal(this.swigCPtr, this);
    }

    public long getOutputSignal() {
        return pjsua2JNI.AudDevManager_getOutputSignal(this.swigCPtr, this);
    }

    public void setInputRoute(pjmedia_aud_dev_route org_pjsip_pjsua2_pjmedia_aud_dev_route, boolean z) {
        pjsua2JNI.AudDevManager_setInputRoute__SWIG_0(this.swigCPtr, this, org_pjsip_pjsua2_pjmedia_aud_dev_route.swigValue(), z);
    }

    public void setInputRoute(pjmedia_aud_dev_route org_pjsip_pjsua2_pjmedia_aud_dev_route) {
        pjsua2JNI.AudDevManager_setInputRoute__SWIG_1(this.swigCPtr, this, org_pjsip_pjsua2_pjmedia_aud_dev_route.swigValue());
    }

    public pjmedia_aud_dev_route getInputRoute() {
        return pjmedia_aud_dev_route.swigToEnum(pjsua2JNI.AudDevManager_getInputRoute(this.swigCPtr, this));
    }

    public void setOutputRoute(pjmedia_aud_dev_route org_pjsip_pjsua2_pjmedia_aud_dev_route, boolean z) {
        pjsua2JNI.AudDevManager_setOutputRoute__SWIG_0(this.swigCPtr, this, org_pjsip_pjsua2_pjmedia_aud_dev_route.swigValue(), z);
    }

    public void setOutputRoute(pjmedia_aud_dev_route org_pjsip_pjsua2_pjmedia_aud_dev_route) {
        pjsua2JNI.AudDevManager_setOutputRoute__SWIG_1(this.swigCPtr, this, org_pjsip_pjsua2_pjmedia_aud_dev_route.swigValue());
    }

    public pjmedia_aud_dev_route getOutputRoute() {
        return pjmedia_aud_dev_route.swigToEnum(pjsua2JNI.AudDevManager_getOutputRoute(this.swigCPtr, this));
    }

    public void setVad(boolean z, boolean z2) {
        pjsua2JNI.AudDevManager_setVad__SWIG_0(this.swigCPtr, this, z, z2);
    }

    public void setVad(boolean z) {
        pjsua2JNI.AudDevManager_setVad__SWIG_1(this.swigCPtr, this, z);
    }

    public boolean getVad() {
        return pjsua2JNI.AudDevManager_getVad(this.swigCPtr, this);
    }

    public void setCng(boolean z, boolean z2) {
        pjsua2JNI.AudDevManager_setCng__SWIG_0(this.swigCPtr, this, z, z2);
    }

    public void setCng(boolean z) {
        pjsua2JNI.AudDevManager_setCng__SWIG_1(this.swigCPtr, this, z);
    }

    public boolean getCng() {
        return pjsua2JNI.AudDevManager_getCng(this.swigCPtr, this);
    }

    public void setPlc(boolean z, boolean z2) {
        pjsua2JNI.AudDevManager_setPlc__SWIG_0(this.swigCPtr, this, z, z2);
    }

    public void setPlc(boolean z) {
        pjsua2JNI.AudDevManager_setPlc__SWIG_1(this.swigCPtr, this, z);
    }

    public boolean getPlc() {
        return pjsua2JNI.AudDevManager_getPlc(this.swigCPtr, this);
    }
}
