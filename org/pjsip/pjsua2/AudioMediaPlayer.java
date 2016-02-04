package org.pjsip.pjsua2;

public class AudioMediaPlayer extends AudioMedia {
    private long swigCPtr;

    protected AudioMediaPlayer(long j, boolean z) {
        super(pjsua2JNI.AudioMediaPlayer_SWIGUpcast(j), z);
        this.swigCPtr = j;
    }

    protected static long getCPtr(AudioMediaPlayer audioMediaPlayer) {
        return audioMediaPlayer == null ? 0 : audioMediaPlayer.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_AudioMediaPlayer(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
        super.delete();
    }

    public AudioMediaPlayer() {
        this(pjsua2JNI.new_AudioMediaPlayer(), true);
    }

    public void createPlayer(String str, long j) {
        pjsua2JNI.AudioMediaPlayer_createPlayer__SWIG_0(this.swigCPtr, this, str, j);
    }

    public void createPlayer(String str) {
        pjsua2JNI.AudioMediaPlayer_createPlayer__SWIG_1(this.swigCPtr, this, str);
    }

    public void createPlaylist(StringVector stringVector, String str, long j) {
        pjsua2JNI.AudioMediaPlayer_createPlaylist__SWIG_0(this.swigCPtr, this, StringVector.getCPtr(stringVector), stringVector, str, j);
    }

    public void createPlaylist(StringVector stringVector, String str) {
        pjsua2JNI.AudioMediaPlayer_createPlaylist__SWIG_1(this.swigCPtr, this, StringVector.getCPtr(stringVector), stringVector, str);
    }

    public void createPlaylist(StringVector stringVector) {
        pjsua2JNI.AudioMediaPlayer_createPlaylist__SWIG_2(this.swigCPtr, this, StringVector.getCPtr(stringVector), stringVector);
    }

    public AudioMediaPlayerInfo getInfo() {
        return new AudioMediaPlayerInfo(pjsua2JNI.AudioMediaPlayer_getInfo(this.swigCPtr, this), true);
    }

    public long getPos() {
        return pjsua2JNI.AudioMediaPlayer_getPos(this.swigCPtr, this);
    }

    public void setPos(long j) {
        pjsua2JNI.AudioMediaPlayer_setPos(this.swigCPtr, this, j);
    }

    public static AudioMediaPlayer typecastFromAudioMedia(AudioMedia audioMedia) {
        long AudioMediaPlayer_typecastFromAudioMedia = pjsua2JNI.AudioMediaPlayer_typecastFromAudioMedia(AudioMedia.getCPtr(audioMedia), audioMedia);
        return AudioMediaPlayer_typecastFromAudioMedia == 0 ? null : new AudioMediaPlayer(AudioMediaPlayer_typecastFromAudioMedia, false);
    }

    public boolean onEof() {
        return pjsua2JNI.AudioMediaPlayer_onEof(this.swigCPtr, this);
    }
}
