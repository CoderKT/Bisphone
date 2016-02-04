package org.pjsip.pjsua2;

public class AudioMediaRecorder extends AudioMedia {
    private long swigCPtr;

    protected AudioMediaRecorder(long j, boolean z) {
        super(pjsua2JNI.AudioMediaRecorder_SWIGUpcast(j), z);
        this.swigCPtr = j;
    }

    protected static long getCPtr(AudioMediaRecorder audioMediaRecorder) {
        return audioMediaRecorder == null ? 0 : audioMediaRecorder.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_AudioMediaRecorder(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public AudioMediaRecorder() {
        this(pjsua2JNI.new_AudioMediaRecorder(), true);
    }

    public void createRecorder(String str, long j, SWIGTYPE_p_pj_ssize_t sWIGTYPE_p_pj_ssize_t, long j2) {
        pjsua2JNI.AudioMediaRecorder_createRecorder__SWIG_0(this.swigCPtr, this, str, j, SWIGTYPE_p_pj_ssize_t.getCPtr(sWIGTYPE_p_pj_ssize_t), j2);
    }

    public void createRecorder(String str, long j, SWIGTYPE_p_pj_ssize_t sWIGTYPE_p_pj_ssize_t) {
        pjsua2JNI.AudioMediaRecorder_createRecorder__SWIG_1(this.swigCPtr, this, str, j, SWIGTYPE_p_pj_ssize_t.getCPtr(sWIGTYPE_p_pj_ssize_t));
    }

    public void createRecorder(String str, long j) {
        pjsua2JNI.AudioMediaRecorder_createRecorder__SWIG_2(this.swigCPtr, this, str, j);
    }

    public void createRecorder(String str) {
        pjsua2JNI.AudioMediaRecorder_createRecorder__SWIG_3(this.swigCPtr, this, str);
    }

    public static AudioMediaRecorder typecastFromAudioMedia(AudioMedia audioMedia) {
        long AudioMediaRecorder_typecastFromAudioMedia = pjsua2JNI.AudioMediaRecorder_typecastFromAudioMedia(AudioMedia.getCPtr(audioMedia), audioMedia);
        return AudioMediaRecorder_typecastFromAudioMedia == 0 ? null : new AudioMediaRecorder(AudioMediaRecorder_typecastFromAudioMedia, false);
    }
}
