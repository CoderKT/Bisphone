package org.pjsip.pjsua2;

public class Endpoint {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected Endpoint(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(Endpoint endpoint) {
        return endpoint == null ? 0 : endpoint.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_Endpoint(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void swigDirectorDisconnect() {
        this.swigCMemOwn = false;
        delete();
    }

    public void swigReleaseOwnership() {
        this.swigCMemOwn = false;
        pjsua2JNI.Endpoint_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        pjsua2JNI.Endpoint_change_ownership(this, this.swigCPtr, true);
    }

    public void libDestroy(long j) {
        Runtime.getRuntime().gc();
        libDestroy_(j);
    }

    public void libDestroy() {
        Runtime.getRuntime().gc();
        libDestroy_();
    }

    public static Endpoint instance() {
        return new Endpoint(pjsua2JNI.Endpoint_instance(), false);
    }

    public Endpoint() {
        this(pjsua2JNI.new_Endpoint(), true);
        pjsua2JNI.Endpoint_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }

    public Version libVersion() {
        return new Version(pjsua2JNI.Endpoint_libVersion(this.swigCPtr, this), true);
    }

    public void libCreate() {
        pjsua2JNI.Endpoint_libCreate(this.swigCPtr, this);
    }

    public pjsua_state libGetState() {
        return pjsua_state.swigToEnum(pjsua2JNI.Endpoint_libGetState(this.swigCPtr, this));
    }

    public void libInit(EpConfig epConfig) {
        pjsua2JNI.Endpoint_libInit(this.swigCPtr, this, EpConfig.getCPtr(epConfig), epConfig);
    }

    public void libStart() {
        pjsua2JNI.Endpoint_libStart(this.swigCPtr, this);
    }

    public void libRegisterThread(String str) {
        pjsua2JNI.Endpoint_libRegisterThread(this.swigCPtr, this, str);
    }

    public boolean libIsThreadRegistered() {
        return pjsua2JNI.Endpoint_libIsThreadRegistered(this.swigCPtr, this);
    }

    public void libStopWorkerThreads() {
        pjsua2JNI.Endpoint_libStopWorkerThreads(this.swigCPtr, this);
    }

    public int libHandleEvents(long j) {
        return pjsua2JNI.Endpoint_libHandleEvents(this.swigCPtr, this, j);
    }

    public void libDestroy_(long j) {
        pjsua2JNI.Endpoint_libDestroy___SWIG_0(this.swigCPtr, this, j);
    }

    public void libDestroy_() {
        pjsua2JNI.Endpoint_libDestroy___SWIG_1(this.swigCPtr, this);
    }

    public String utilStrError(int i) {
        return pjsua2JNI.Endpoint_utilStrError(this.swigCPtr, this, i);
    }

    public void utilLogWrite(int i, String str, String str2) {
        pjsua2JNI.Endpoint_utilLogWrite__SWIG_0(this.swigCPtr, this, i, str, str2);
    }

    public void utilLogWrite(LogEntry logEntry) {
        pjsua2JNI.Endpoint_utilLogWrite__SWIG_1(this.swigCPtr, this, LogEntry.getCPtr(logEntry), logEntry);
    }

    public int utilVerifySipUri(String str) {
        return pjsua2JNI.Endpoint_utilVerifySipUri(this.swigCPtr, this, str);
    }

    public int utilVerifyUri(String str) {
        return pjsua2JNI.Endpoint_utilVerifyUri(this.swigCPtr, this, str);
    }

    public SWIGTYPE_p_void utilTimerSchedule(long j, SWIGTYPE_p_void sWIGTYPE_p_void) {
        long Endpoint_utilTimerSchedule = pjsua2JNI.Endpoint_utilTimerSchedule(this.swigCPtr, this, j, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
        return Endpoint_utilTimerSchedule == 0 ? null : new SWIGTYPE_p_void(Endpoint_utilTimerSchedule, false);
    }

    public void utilTimerCancel(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.Endpoint_utilTimerCancel(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public void utilAddPendingJob(PendingJob pendingJob) {
        pjsua2JNI.Endpoint_utilAddPendingJob(this.swigCPtr, this, PendingJob.getCPtr(pendingJob), pendingJob);
    }

    public IntVector utilSslGetAvailableCiphers() {
        return new IntVector(pjsua2JNI.Endpoint_utilSslGetAvailableCiphers(this.swigCPtr, this), true);
    }

    public void natDetectType() {
        pjsua2JNI.Endpoint_natDetectType(this.swigCPtr, this);
    }

    public pj_stun_nat_type natGetType() {
        return pj_stun_nat_type.swigToEnum(pjsua2JNI.Endpoint_natGetType(this.swigCPtr, this));
    }

    public void natCheckStunServers(StringVector stringVector, boolean z, SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.Endpoint_natCheckStunServers(this.swigCPtr, this, StringVector.getCPtr(stringVector), stringVector, z, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public void natCancelCheckStunServers(SWIGTYPE_p_void sWIGTYPE_p_void, boolean z) {
        pjsua2JNI.Endpoint_natCancelCheckStunServers__SWIG_0(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void), z);
    }

    public void natCancelCheckStunServers(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.Endpoint_natCancelCheckStunServers__SWIG_1(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public int transportCreate(pjsip_transport_type_e org_pjsip_pjsua2_pjsip_transport_type_e, TransportConfig transportConfig) {
        return pjsua2JNI.Endpoint_transportCreate(this.swigCPtr, this, org_pjsip_pjsua2_pjsip_transport_type_e.swigValue(), TransportConfig.getCPtr(transportConfig), transportConfig);
    }

    public IntVector transportEnum() {
        return new IntVector(pjsua2JNI.Endpoint_transportEnum(this.swigCPtr, this), true);
    }

    public TransportInfo transportGetInfo(int i) {
        return new TransportInfo(pjsua2JNI.Endpoint_transportGetInfo(this.swigCPtr, this, i), true);
    }

    public void transportSetEnable(int i, boolean z) {
        pjsua2JNI.Endpoint_transportSetEnable(this.swigCPtr, this, i, z);
    }

    public void transportClose(int i) {
        pjsua2JNI.Endpoint_transportClose(this.swigCPtr, this, i);
    }

    public void hangupAllCalls() {
        pjsua2JNI.Endpoint_hangupAllCalls(this.swigCPtr, this);
    }

    public void mediaAdd(AudioMedia audioMedia) {
        pjsua2JNI.Endpoint_mediaAdd(this.swigCPtr, this, AudioMedia.getCPtr(audioMedia), audioMedia);
    }

    public void mediaRemove(AudioMedia audioMedia) {
        pjsua2JNI.Endpoint_mediaRemove(this.swigCPtr, this, AudioMedia.getCPtr(audioMedia), audioMedia);
    }

    public boolean mediaExists(AudioMedia audioMedia) {
        return pjsua2JNI.Endpoint_mediaExists(this.swigCPtr, this, AudioMedia.getCPtr(audioMedia), audioMedia);
    }

    public long mediaMaxPorts() {
        return pjsua2JNI.Endpoint_mediaMaxPorts(this.swigCPtr, this);
    }

    public long mediaActivePorts() {
        return pjsua2JNI.Endpoint_mediaActivePorts(this.swigCPtr, this);
    }

    public AudioMediaVector mediaEnumPorts() {
        return new AudioMediaVector(pjsua2JNI.Endpoint_mediaEnumPorts(this.swigCPtr, this), false);
    }

    public AudDevManager audDevManager() {
        return new AudDevManager(pjsua2JNI.Endpoint_audDevManager(this.swigCPtr, this), false);
    }

    public CodecInfoVector codecEnum() {
        return new CodecInfoVector(pjsua2JNI.Endpoint_codecEnum(this.swigCPtr, this), false);
    }

    public void codecSetPriority(String str, short s) {
        pjsua2JNI.Endpoint_codecSetPriority(this.swigCPtr, this, str, s);
    }

    public SWIGTYPE_p_void codecGetParam(String str) {
        long Endpoint_codecGetParam = pjsua2JNI.Endpoint_codecGetParam(this.swigCPtr, this, str);
        return Endpoint_codecGetParam == 0 ? null : new SWIGTYPE_p_void(Endpoint_codecGetParam, false);
    }

    public void codecSetParam(String str, SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.Endpoint_codecSetParam(this.swigCPtr, this, str, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public void onNatDetectionComplete(OnNatDetectionCompleteParam onNatDetectionCompleteParam) {
        if (getClass() == Endpoint.class) {
            pjsua2JNI.Endpoint_onNatDetectionComplete(this.swigCPtr, this, OnNatDetectionCompleteParam.getCPtr(onNatDetectionCompleteParam), onNatDetectionCompleteParam);
        } else {
            pjsua2JNI.Endpoint_onNatDetectionCompleteSwigExplicitEndpoint(this.swigCPtr, this, OnNatDetectionCompleteParam.getCPtr(onNatDetectionCompleteParam), onNatDetectionCompleteParam);
        }
    }

    public void onNatCheckStunServersComplete(OnNatCheckStunServersCompleteParam onNatCheckStunServersCompleteParam) {
        if (getClass() == Endpoint.class) {
            pjsua2JNI.Endpoint_onNatCheckStunServersComplete(this.swigCPtr, this, OnNatCheckStunServersCompleteParam.getCPtr(onNatCheckStunServersCompleteParam), onNatCheckStunServersCompleteParam);
        } else {
            pjsua2JNI.Endpoint_onNatCheckStunServersCompleteSwigExplicitEndpoint(this.swigCPtr, this, OnNatCheckStunServersCompleteParam.getCPtr(onNatCheckStunServersCompleteParam), onNatCheckStunServersCompleteParam);
        }
    }

    public void onTransportState(OnTransportStateParam onTransportStateParam) {
        if (getClass() == Endpoint.class) {
            pjsua2JNI.Endpoint_onTransportState(this.swigCPtr, this, OnTransportStateParam.getCPtr(onTransportStateParam), onTransportStateParam);
        } else {
            pjsua2JNI.Endpoint_onTransportStateSwigExplicitEndpoint(this.swigCPtr, this, OnTransportStateParam.getCPtr(onTransportStateParam), onTransportStateParam);
        }
    }

    public void onTimer(OnTimerParam onTimerParam) {
        if (getClass() == Endpoint.class) {
            pjsua2JNI.Endpoint_onTimer(this.swigCPtr, this, OnTimerParam.getCPtr(onTimerParam), onTimerParam);
        } else {
            pjsua2JNI.Endpoint_onTimerSwigExplicitEndpoint(this.swigCPtr, this, OnTimerParam.getCPtr(onTimerParam), onTimerParam);
        }
    }

    public void onSelectAccount(OnSelectAccountParam onSelectAccountParam) {
        if (getClass() == Endpoint.class) {
            pjsua2JNI.Endpoint_onSelectAccount(this.swigCPtr, this, OnSelectAccountParam.getCPtr(onSelectAccountParam), onSelectAccountParam);
        } else {
            pjsua2JNI.Endpoint_onSelectAccountSwigExplicitEndpoint(this.swigCPtr, this, OnSelectAccountParam.getCPtr(onSelectAccountParam), onSelectAccountParam);
        }
    }
}
