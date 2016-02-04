package org.pjsip.pjsua2;

public class pjsua2JNI {
    public static final native long AccountCallConfig_SWIGUpcast(long j);

    public static final native int AccountCallConfig_holdType_get(long j, AccountCallConfig accountCallConfig);

    public static final native void AccountCallConfig_holdType_set(long j, AccountCallConfig accountCallConfig, int i);

    public static final native int AccountCallConfig_prackUse_get(long j, AccountCallConfig accountCallConfig);

    public static final native void AccountCallConfig_prackUse_set(long j, AccountCallConfig accountCallConfig, int i);

    public static final native void AccountCallConfig_readObject(long j, AccountCallConfig accountCallConfig, long j2, ContainerNode containerNode);

    public static final native long AccountCallConfig_timerMinSESec_get(long j, AccountCallConfig accountCallConfig);

    public static final native void AccountCallConfig_timerMinSESec_set(long j, AccountCallConfig accountCallConfig, long j2);

    public static final native long AccountCallConfig_timerSessExpiresSec_get(long j, AccountCallConfig accountCallConfig);

    public static final native void AccountCallConfig_timerSessExpiresSec_set(long j, AccountCallConfig accountCallConfig, long j2);

    public static final native int AccountCallConfig_timerUse_get(long j, AccountCallConfig accountCallConfig);

    public static final native void AccountCallConfig_timerUse_set(long j, AccountCallConfig accountCallConfig, int i);

    public static final native void AccountCallConfig_writeObject(long j, AccountCallConfig accountCallConfig, long j2, ContainerNode containerNode);

    public static final native long AccountConfig_SWIGUpcast(long j);

    public static final native long AccountConfig_callConfig_get(long j, AccountConfig accountConfig);

    public static final native void AccountConfig_callConfig_set(long j, AccountConfig accountConfig, long j2, AccountCallConfig accountCallConfig);

    public static final native String AccountConfig_idUri_get(long j, AccountConfig accountConfig);

    public static final native void AccountConfig_idUri_set(long j, AccountConfig accountConfig, String str);

    public static final native long AccountConfig_mediaConfig_get(long j, AccountConfig accountConfig);

    public static final native void AccountConfig_mediaConfig_set(long j, AccountConfig accountConfig, long j2, AccountMediaConfig accountMediaConfig);

    public static final native long AccountConfig_mwiConfig_get(long j, AccountConfig accountConfig);

    public static final native void AccountConfig_mwiConfig_set(long j, AccountConfig accountConfig, long j2, AccountMwiConfig accountMwiConfig);

    public static final native long AccountConfig_natConfig_get(long j, AccountConfig accountConfig);

    public static final native void AccountConfig_natConfig_set(long j, AccountConfig accountConfig, long j2, AccountNatConfig accountNatConfig);

    public static final native long AccountConfig_presConfig_get(long j, AccountConfig accountConfig);

    public static final native void AccountConfig_presConfig_set(long j, AccountConfig accountConfig, long j2, AccountPresConfig accountPresConfig);

    public static final native int AccountConfig_priority_get(long j, AccountConfig accountConfig);

    public static final native void AccountConfig_priority_set(long j, AccountConfig accountConfig, int i);

    public static final native void AccountConfig_readObject(long j, AccountConfig accountConfig, long j2, ContainerNode containerNode);

    public static final native long AccountConfig_regConfig_get(long j, AccountConfig accountConfig);

    public static final native void AccountConfig_regConfig_set(long j, AccountConfig accountConfig, long j2, AccountRegConfig accountRegConfig);

    public static final native long AccountConfig_sipConfig_get(long j, AccountConfig accountConfig);

    public static final native void AccountConfig_sipConfig_set(long j, AccountConfig accountConfig, long j2, AccountSipConfig accountSipConfig);

    public static final native long AccountConfig_videoConfig_get(long j, AccountConfig accountConfig);

    public static final native void AccountConfig_videoConfig_set(long j, AccountConfig accountConfig, long j2, AccountVideoConfig accountVideoConfig);

    public static final native void AccountConfig_writeObject(long j, AccountConfig accountConfig, long j2, ContainerNode containerNode);

    public static final native int AccountInfo_id_get(long j, AccountInfo accountInfo);

    public static final native void AccountInfo_id_set(long j, AccountInfo accountInfo, int i);

    public static final native boolean AccountInfo_isDefault_get(long j, AccountInfo accountInfo);

    public static final native void AccountInfo_isDefault_set(long j, AccountInfo accountInfo, boolean z);

    public static final native String AccountInfo_onlineStatusText_get(long j, AccountInfo accountInfo);

    public static final native void AccountInfo_onlineStatusText_set(long j, AccountInfo accountInfo, String str);

    public static final native boolean AccountInfo_onlineStatus_get(long j, AccountInfo accountInfo);

    public static final native void AccountInfo_onlineStatus_set(long j, AccountInfo accountInfo, boolean z);

    public static final native int AccountInfo_regExpiresSec_get(long j, AccountInfo accountInfo);

    public static final native void AccountInfo_regExpiresSec_set(long j, AccountInfo accountInfo, int i);

    public static final native boolean AccountInfo_regIsActive_get(long j, AccountInfo accountInfo);

    public static final native void AccountInfo_regIsActive_set(long j, AccountInfo accountInfo, boolean z);

    public static final native boolean AccountInfo_regIsConfigured_get(long j, AccountInfo accountInfo);

    public static final native void AccountInfo_regIsConfigured_set(long j, AccountInfo accountInfo, boolean z);

    public static final native int AccountInfo_regLastErr_get(long j, AccountInfo accountInfo);

    public static final native void AccountInfo_regLastErr_set(long j, AccountInfo accountInfo, int i);

    public static final native String AccountInfo_regStatusText_get(long j, AccountInfo accountInfo);

    public static final native void AccountInfo_regStatusText_set(long j, AccountInfo accountInfo, String str);

    public static final native int AccountInfo_regStatus_get(long j, AccountInfo accountInfo);

    public static final native void AccountInfo_regStatus_set(long j, AccountInfo accountInfo, int i);

    public static final native String AccountInfo_uri_get(long j, AccountInfo accountInfo);

    public static final native void AccountInfo_uri_set(long j, AccountInfo accountInfo, String str);

    public static final native long AccountMediaConfig_SWIGUpcast(long j);

    public static final native int AccountMediaConfig_ipv6Use_get(long j, AccountMediaConfig accountMediaConfig);

    public static final native void AccountMediaConfig_ipv6Use_set(long j, AccountMediaConfig accountMediaConfig, int i);

    public static final native boolean AccountMediaConfig_lockCodecEnabled_get(long j, AccountMediaConfig accountMediaConfig);

    public static final native void AccountMediaConfig_lockCodecEnabled_set(long j, AccountMediaConfig accountMediaConfig, boolean z);

    public static final native void AccountMediaConfig_readObject(long j, AccountMediaConfig accountMediaConfig, long j2, ContainerNode containerNode);

    public static final native int AccountMediaConfig_srtpSecureSignaling_get(long j, AccountMediaConfig accountMediaConfig);

    public static final native void AccountMediaConfig_srtpSecureSignaling_set(long j, AccountMediaConfig accountMediaConfig, int i);

    public static final native int AccountMediaConfig_srtpUse_get(long j, AccountMediaConfig accountMediaConfig);

    public static final native void AccountMediaConfig_srtpUse_set(long j, AccountMediaConfig accountMediaConfig, int i);

    public static final native boolean AccountMediaConfig_streamKaEnabled_get(long j, AccountMediaConfig accountMediaConfig);

    public static final native void AccountMediaConfig_streamKaEnabled_set(long j, AccountMediaConfig accountMediaConfig, boolean z);

    public static final native long AccountMediaConfig_transportConfig_get(long j, AccountMediaConfig accountMediaConfig);

    public static final native void AccountMediaConfig_transportConfig_set(long j, AccountMediaConfig accountMediaConfig, long j2, TransportConfig transportConfig);

    public static final native void AccountMediaConfig_writeObject(long j, AccountMediaConfig accountMediaConfig, long j2, ContainerNode containerNode);

    public static final native long AccountMwiConfig_SWIGUpcast(long j);

    public static final native boolean AccountMwiConfig_enabled_get(long j, AccountMwiConfig accountMwiConfig);

    public static final native void AccountMwiConfig_enabled_set(long j, AccountMwiConfig accountMwiConfig, boolean z);

    public static final native long AccountMwiConfig_expirationSec_get(long j, AccountMwiConfig accountMwiConfig);

    public static final native void AccountMwiConfig_expirationSec_set(long j, AccountMwiConfig accountMwiConfig, long j2);

    public static final native void AccountMwiConfig_readObject(long j, AccountMwiConfig accountMwiConfig, long j2, ContainerNode containerNode);

    public static final native void AccountMwiConfig_writeObject(long j, AccountMwiConfig accountMwiConfig, long j2, ContainerNode containerNode);

    public static final native long AccountNatConfig_SWIGUpcast(long j);

    public static final native int AccountNatConfig_contactRewriteMethod_get(long j, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_contactRewriteMethod_set(long j, AccountNatConfig accountNatConfig, int i);

    public static final native int AccountNatConfig_contactRewriteUse_get(long j, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_contactRewriteUse_set(long j, AccountNatConfig accountNatConfig, int i);

    public static final native int AccountNatConfig_contactUseSrcPort_get(long j, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_contactUseSrcPort_set(long j, AccountNatConfig accountNatConfig, int i);

    public static final native boolean AccountNatConfig_iceAggressiveNomination_get(long j, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_iceAggressiveNomination_set(long j, AccountNatConfig accountNatConfig, boolean z);

    public static final native boolean AccountNatConfig_iceAlwaysUpdate_get(long j, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_iceAlwaysUpdate_set(long j, AccountNatConfig accountNatConfig, boolean z);

    public static final native boolean AccountNatConfig_iceEnabled_get(long j, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_iceEnabled_set(long j, AccountNatConfig accountNatConfig, boolean z);

    public static final native int AccountNatConfig_iceMaxHostCands_get(long j, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_iceMaxHostCands_set(long j, AccountNatConfig accountNatConfig, int i);

    public static final native boolean AccountNatConfig_iceNoRtcp_get(long j, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_iceNoRtcp_set(long j, AccountNatConfig accountNatConfig, boolean z);

    public static final native long AccountNatConfig_iceNominatedCheckDelayMsec_get(long j, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_iceNominatedCheckDelayMsec_set(long j, AccountNatConfig accountNatConfig, long j2);

    public static final native int AccountNatConfig_iceWaitNominationTimeoutMsec_get(long j, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_iceWaitNominationTimeoutMsec_set(long j, AccountNatConfig accountNatConfig, int i);

    public static final native int AccountNatConfig_mediaStunUse_get(long j, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_mediaStunUse_set(long j, AccountNatConfig accountNatConfig, int i);

    public static final native void AccountNatConfig_readObject(long j, AccountNatConfig accountNatConfig, long j2, ContainerNode containerNode);

    public static final native int AccountNatConfig_sdpNatRewriteUse_get(long j, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_sdpNatRewriteUse_set(long j, AccountNatConfig accountNatConfig, int i);

    public static final native String AccountNatConfig_sipOutboundInstanceId_get(long j, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_sipOutboundInstanceId_set(long j, AccountNatConfig accountNatConfig, String str);

    public static final native String AccountNatConfig_sipOutboundRegId_get(long j, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_sipOutboundRegId_set(long j, AccountNatConfig accountNatConfig, String str);

    public static final native int AccountNatConfig_sipOutboundUse_get(long j, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_sipOutboundUse_set(long j, AccountNatConfig accountNatConfig, int i);

    public static final native int AccountNatConfig_sipStunUse_get(long j, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_sipStunUse_set(long j, AccountNatConfig accountNatConfig, int i);

    public static final native int AccountNatConfig_turnConnType_get(long j, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_turnConnType_set(long j, AccountNatConfig accountNatConfig, int i);

    public static final native boolean AccountNatConfig_turnEnabled_get(long j, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_turnEnabled_set(long j, AccountNatConfig accountNatConfig, boolean z);

    public static final native int AccountNatConfig_turnPasswordType_get(long j, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_turnPasswordType_set(long j, AccountNatConfig accountNatConfig, int i);

    public static final native String AccountNatConfig_turnPassword_get(long j, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_turnPassword_set(long j, AccountNatConfig accountNatConfig, String str);

    public static final native String AccountNatConfig_turnServer_get(long j, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_turnServer_set(long j, AccountNatConfig accountNatConfig, String str);

    public static final native String AccountNatConfig_turnUserName_get(long j, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_turnUserName_set(long j, AccountNatConfig accountNatConfig, String str);

    public static final native String AccountNatConfig_udpKaData_get(long j, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_udpKaData_set(long j, AccountNatConfig accountNatConfig, String str);

    public static final native long AccountNatConfig_udpKaIntervalSec_get(long j, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_udpKaIntervalSec_set(long j, AccountNatConfig accountNatConfig, long j2);

    public static final native int AccountNatConfig_viaRewriteUse_get(long j, AccountNatConfig accountNatConfig);

    public static final native void AccountNatConfig_viaRewriteUse_set(long j, AccountNatConfig accountNatConfig, int i);

    public static final native void AccountNatConfig_writeObject(long j, AccountNatConfig accountNatConfig, long j2, ContainerNode containerNode);

    public static final native long AccountPresConfig_SWIGUpcast(long j);

    public static final native long AccountPresConfig_headers_get(long j, AccountPresConfig accountPresConfig);

    public static final native void AccountPresConfig_headers_set(long j, AccountPresConfig accountPresConfig, long j2, SipHeaderVector sipHeaderVector);

    public static final native String AccountPresConfig_pidfTupleId_get(long j, AccountPresConfig accountPresConfig);

    public static final native void AccountPresConfig_pidfTupleId_set(long j, AccountPresConfig accountPresConfig, String str);

    public static final native boolean AccountPresConfig_publishEnabled_get(long j, AccountPresConfig accountPresConfig);

    public static final native void AccountPresConfig_publishEnabled_set(long j, AccountPresConfig accountPresConfig, boolean z);

    public static final native boolean AccountPresConfig_publishQueue_get(long j, AccountPresConfig accountPresConfig);

    public static final native void AccountPresConfig_publishQueue_set(long j, AccountPresConfig accountPresConfig, boolean z);

    public static final native long AccountPresConfig_publishShutdownWaitMsec_get(long j, AccountPresConfig accountPresConfig);

    public static final native void AccountPresConfig_publishShutdownWaitMsec_set(long j, AccountPresConfig accountPresConfig, long j2);

    public static final native void AccountPresConfig_readObject(long j, AccountPresConfig accountPresConfig, long j2, ContainerNode containerNode);

    public static final native void AccountPresConfig_writeObject(long j, AccountPresConfig accountPresConfig, long j2, ContainerNode containerNode);

    public static final native long AccountRegConfig_SWIGUpcast(long j);

    public static final native long AccountRegConfig_delayBeforeRefreshSec_get(long j, AccountRegConfig accountRegConfig);

    public static final native void AccountRegConfig_delayBeforeRefreshSec_set(long j, AccountRegConfig accountRegConfig, long j2);

    public static final native boolean AccountRegConfig_dropCallsOnFail_get(long j, AccountRegConfig accountRegConfig);

    public static final native void AccountRegConfig_dropCallsOnFail_set(long j, AccountRegConfig accountRegConfig, boolean z);

    public static final native long AccountRegConfig_firstRetryIntervalSec_get(long j, AccountRegConfig accountRegConfig);

    public static final native void AccountRegConfig_firstRetryIntervalSec_set(long j, AccountRegConfig accountRegConfig, long j2);

    public static final native long AccountRegConfig_headers_get(long j, AccountRegConfig accountRegConfig);

    public static final native void AccountRegConfig_headers_set(long j, AccountRegConfig accountRegConfig, long j2, SipHeaderVector sipHeaderVector);

    public static final native long AccountRegConfig_proxyUse_get(long j, AccountRegConfig accountRegConfig);

    public static final native void AccountRegConfig_proxyUse_set(long j, AccountRegConfig accountRegConfig, long j2);

    public static final native void AccountRegConfig_readObject(long j, AccountRegConfig accountRegConfig, long j2, ContainerNode containerNode);

    public static final native boolean AccountRegConfig_registerOnAdd_get(long j, AccountRegConfig accountRegConfig);

    public static final native void AccountRegConfig_registerOnAdd_set(long j, AccountRegConfig accountRegConfig, boolean z);

    public static final native String AccountRegConfig_registrarUri_get(long j, AccountRegConfig accountRegConfig);

    public static final native void AccountRegConfig_registrarUri_set(long j, AccountRegConfig accountRegConfig, String str);

    public static final native long AccountRegConfig_retryIntervalSec_get(long j, AccountRegConfig accountRegConfig);

    public static final native void AccountRegConfig_retryIntervalSec_set(long j, AccountRegConfig accountRegConfig, long j2);

    public static final native long AccountRegConfig_timeoutSec_get(long j, AccountRegConfig accountRegConfig);

    public static final native void AccountRegConfig_timeoutSec_set(long j, AccountRegConfig accountRegConfig, long j2);

    public static final native long AccountRegConfig_unregWaitSec_get(long j, AccountRegConfig accountRegConfig);

    public static final native void AccountRegConfig_unregWaitSec_set(long j, AccountRegConfig accountRegConfig, long j2);

    public static final native void AccountRegConfig_writeObject(long j, AccountRegConfig accountRegConfig, long j2, ContainerNode containerNode);

    public static final native long AccountSipConfig_SWIGUpcast(long j);

    public static final native long AccountSipConfig_authCreds_get(long j, AccountSipConfig accountSipConfig);

    public static final native void AccountSipConfig_authCreds_set(long j, AccountSipConfig accountSipConfig, long j2, AuthCredInfoVector authCredInfoVector);

    public static final native String AccountSipConfig_authInitialAlgorithm_get(long j, AccountSipConfig accountSipConfig);

    public static final native void AccountSipConfig_authInitialAlgorithm_set(long j, AccountSipConfig accountSipConfig, String str);

    public static final native boolean AccountSipConfig_authInitialEmpty_get(long j, AccountSipConfig accountSipConfig);

    public static final native void AccountSipConfig_authInitialEmpty_set(long j, AccountSipConfig accountSipConfig, boolean z);

    public static final native String AccountSipConfig_contactForced_get(long j, AccountSipConfig accountSipConfig);

    public static final native void AccountSipConfig_contactForced_set(long j, AccountSipConfig accountSipConfig, String str);

    public static final native String AccountSipConfig_contactParams_get(long j, AccountSipConfig accountSipConfig);

    public static final native void AccountSipConfig_contactParams_set(long j, AccountSipConfig accountSipConfig, String str);

    public static final native String AccountSipConfig_contactUriParams_get(long j, AccountSipConfig accountSipConfig);

    public static final native void AccountSipConfig_contactUriParams_set(long j, AccountSipConfig accountSipConfig, String str);

    public static final native long AccountSipConfig_proxies_get(long j, AccountSipConfig accountSipConfig);

    public static final native void AccountSipConfig_proxies_set(long j, AccountSipConfig accountSipConfig, long j2, StringVector stringVector);

    public static final native void AccountSipConfig_readObject(long j, AccountSipConfig accountSipConfig, long j2, ContainerNode containerNode);

    public static final native int AccountSipConfig_transportId_get(long j, AccountSipConfig accountSipConfig);

    public static final native void AccountSipConfig_transportId_set(long j, AccountSipConfig accountSipConfig, int i);

    public static final native void AccountSipConfig_writeObject(long j, AccountSipConfig accountSipConfig, long j2, ContainerNode containerNode);

    public static final native long AccountVideoConfig_SWIGUpcast(long j);

    public static final native boolean AccountVideoConfig_autoShowIncoming_get(long j, AccountVideoConfig accountVideoConfig);

    public static final native void AccountVideoConfig_autoShowIncoming_set(long j, AccountVideoConfig accountVideoConfig, boolean z);

    public static final native boolean AccountVideoConfig_autoTransmitOutgoing_get(long j, AccountVideoConfig accountVideoConfig);

    public static final native void AccountVideoConfig_autoTransmitOutgoing_set(long j, AccountVideoConfig accountVideoConfig, boolean z);

    public static final native int AccountVideoConfig_defaultCaptureDevice_get(long j, AccountVideoConfig accountVideoConfig);

    public static final native void AccountVideoConfig_defaultCaptureDevice_set(long j, AccountVideoConfig accountVideoConfig, int i);

    public static final native int AccountVideoConfig_defaultRenderDevice_get(long j, AccountVideoConfig accountVideoConfig);

    public static final native void AccountVideoConfig_defaultRenderDevice_set(long j, AccountVideoConfig accountVideoConfig, int i);

    public static final native long AccountVideoConfig_rateControlBandwidth_get(long j, AccountVideoConfig accountVideoConfig);

    public static final native void AccountVideoConfig_rateControlBandwidth_set(long j, AccountVideoConfig accountVideoConfig, long j2);

    public static final native int AccountVideoConfig_rateControlMethod_get(long j, AccountVideoConfig accountVideoConfig);

    public static final native void AccountVideoConfig_rateControlMethod_set(long j, AccountVideoConfig accountVideoConfig, int i);

    public static final native void AccountVideoConfig_readObject(long j, AccountVideoConfig accountVideoConfig, long j2, ContainerNode containerNode);

    public static final native long AccountVideoConfig_windowFlags_get(long j, AccountVideoConfig accountVideoConfig);

    public static final native void AccountVideoConfig_windowFlags_set(long j, AccountVideoConfig accountVideoConfig, long j2);

    public static final native void AccountVideoConfig_writeObject(long j, AccountVideoConfig accountVideoConfig, long j2, ContainerNode containerNode);

    public static final native void Account_addBuddy(long j, Account account, long j2, Buddy buddy);

    public static final native void Account_change_ownership(Account account, long j, boolean z);

    public static final native void Account_create__SWIG_0(long j, Account account, long j2, AccountConfig accountConfig, boolean z);

    public static final native void Account_create__SWIG_1(long j, Account account, long j2, AccountConfig accountConfig);

    public static final native void Account_director_connect(Account account, long j, boolean z, boolean z2);

    public static final native long Account_enumBuddies(long j, Account account);

    public static final native long Account_findBuddy__SWIG_0(long j, Account account, String str, long j2, FindBuddyMatch findBuddyMatch);

    public static final native long Account_findBuddy__SWIG_1(long j, Account account, String str);

    public static final native int Account_getId(long j, Account account);

    public static final native long Account_getInfo(long j, Account account);

    public static final native boolean Account_isDefault(long j, Account account);

    public static final native boolean Account_isValid(long j, Account account);

    public static final native long Account_lookup(int i);

    public static final native void Account_modify(long j, Account account, long j2, AccountConfig accountConfig);

    public static final native void Account_onIncomingCall(long j, Account account, long j2, OnIncomingCallParam onIncomingCallParam);

    public static final native void Account_onIncomingCallSwigExplicitAccount(long j, Account account, long j2, OnIncomingCallParam onIncomingCallParam);

    public static final native void Account_onIncomingSubscribe(long j, Account account, long j2, OnIncomingSubscribeParam onIncomingSubscribeParam);

    public static final native void Account_onIncomingSubscribeSwigExplicitAccount(long j, Account account, long j2, OnIncomingSubscribeParam onIncomingSubscribeParam);

    public static final native void Account_onInstantMessage(long j, Account account, long j2, OnInstantMessageParam onInstantMessageParam);

    public static final native void Account_onInstantMessageStatus(long j, Account account, long j2, OnInstantMessageStatusParam onInstantMessageStatusParam);

    public static final native void Account_onInstantMessageStatusSwigExplicitAccount(long j, Account account, long j2, OnInstantMessageStatusParam onInstantMessageStatusParam);

    public static final native void Account_onInstantMessageSwigExplicitAccount(long j, Account account, long j2, OnInstantMessageParam onInstantMessageParam);

    public static final native void Account_onMwiInfo(long j, Account account, long j2, OnMwiInfoParam onMwiInfoParam);

    public static final native void Account_onMwiInfoSwigExplicitAccount(long j, Account account, long j2, OnMwiInfoParam onMwiInfoParam);

    public static final native void Account_onRegStarted(long j, Account account, long j2, OnRegStartedParam onRegStartedParam);

    public static final native void Account_onRegStartedSwigExplicitAccount(long j, Account account, long j2, OnRegStartedParam onRegStartedParam);

    public static final native void Account_onRegState(long j, Account account, long j2, OnRegStateParam onRegStateParam);

    public static final native void Account_onRegStateSwigExplicitAccount(long j, Account account, long j2, OnRegStateParam onRegStateParam);

    public static final native void Account_onTypingIndication(long j, Account account, long j2, OnTypingIndicationParam onTypingIndicationParam);

    public static final native void Account_onTypingIndicationSwigExplicitAccount(long j, Account account, long j2, OnTypingIndicationParam onTypingIndicationParam);

    public static final native void Account_presNotify(long j, Account account, long j2, PresNotifyParam presNotifyParam);

    public static final native void Account_removeBuddy(long j, Account account, long j2, Buddy buddy);

    public static final native void Account_setDefault(long j, Account account);

    public static final native void Account_setOnlineStatus(long j, Account account, long j2, PresenceStatus presenceStatus);

    public static final native void Account_setRegistration(long j, Account account, boolean z);

    public static final native void Account_setTransport(long j, Account account, int i);

    public static final native String AudDevManager_capName(long j, AudDevManager audDevManager, int i);

    public static final native long AudDevManager_enumDev(long j, AudDevManager audDevManager);

    public static final native int AudDevManager_getCaptureDev(long j, AudDevManager audDevManager);

    public static final native long AudDevManager_getCaptureDevMedia(long j, AudDevManager audDevManager);

    public static final native boolean AudDevManager_getCng(long j, AudDevManager audDevManager);

    public static final native long AudDevManager_getDevCount(long j, AudDevManager audDevManager);

    public static final native long AudDevManager_getDevInfo(long j, AudDevManager audDevManager, int i);

    public static final native long AudDevManager_getEcTail(long j, AudDevManager audDevManager);

    public static final native long AudDevManager_getExtFormat(long j, AudDevManager audDevManager);

    public static final native long AudDevManager_getInputLatency(long j, AudDevManager audDevManager);

    public static final native int AudDevManager_getInputRoute(long j, AudDevManager audDevManager);

    public static final native long AudDevManager_getInputSignal(long j, AudDevManager audDevManager);

    public static final native long AudDevManager_getInputVolume(long j, AudDevManager audDevManager);

    public static final native long AudDevManager_getOutputLatency(long j, AudDevManager audDevManager);

    public static final native int AudDevManager_getOutputRoute(long j, AudDevManager audDevManager);

    public static final native long AudDevManager_getOutputSignal(long j, AudDevManager audDevManager);

    public static final native long AudDevManager_getOutputVolume(long j, AudDevManager audDevManager);

    public static final native int AudDevManager_getPlaybackDev(long j, AudDevManager audDevManager);

    public static final native long AudDevManager_getPlaybackDevMedia(long j, AudDevManager audDevManager);

    public static final native boolean AudDevManager_getPlc(long j, AudDevManager audDevManager);

    public static final native boolean AudDevManager_getVad(long j, AudDevManager audDevManager);

    public static final native int AudDevManager_lookupDev(long j, AudDevManager audDevManager, String str, String str2);

    public static final native void AudDevManager_refreshDevs(long j, AudDevManager audDevManager);

    public static final native void AudDevManager_setCaptureDev(long j, AudDevManager audDevManager, int i);

    public static final native void AudDevManager_setCng__SWIG_0(long j, AudDevManager audDevManager, boolean z, boolean z2);

    public static final native void AudDevManager_setCng__SWIG_1(long j, AudDevManager audDevManager, boolean z);

    public static final native void AudDevManager_setEcOptions(long j, AudDevManager audDevManager, long j2, long j3);

    public static final native void AudDevManager_setExtFormat__SWIG_0(long j, AudDevManager audDevManager, long j2, MediaFormatAudio mediaFormatAudio, boolean z);

    public static final native void AudDevManager_setExtFormat__SWIG_1(long j, AudDevManager audDevManager, long j2, MediaFormatAudio mediaFormatAudio);

    public static final native void AudDevManager_setInputLatency__SWIG_0(long j, AudDevManager audDevManager, long j2, boolean z);

    public static final native void AudDevManager_setInputLatency__SWIG_1(long j, AudDevManager audDevManager, long j2);

    public static final native void AudDevManager_setInputRoute__SWIG_0(long j, AudDevManager audDevManager, int i, boolean z);

    public static final native void AudDevManager_setInputRoute__SWIG_1(long j, AudDevManager audDevManager, int i);

    public static final native void AudDevManager_setInputVolume__SWIG_0(long j, AudDevManager audDevManager, long j2, boolean z);

    public static final native void AudDevManager_setInputVolume__SWIG_1(long j, AudDevManager audDevManager, long j2);

    public static final native long AudDevManager_setNoDev(long j, AudDevManager audDevManager);

    public static final native void AudDevManager_setNullDev(long j, AudDevManager audDevManager);

    public static final native void AudDevManager_setOutputLatency__SWIG_0(long j, AudDevManager audDevManager, long j2, boolean z);

    public static final native void AudDevManager_setOutputLatency__SWIG_1(long j, AudDevManager audDevManager, long j2);

    public static final native void AudDevManager_setOutputRoute__SWIG_0(long j, AudDevManager audDevManager, int i, boolean z);

    public static final native void AudDevManager_setOutputRoute__SWIG_1(long j, AudDevManager audDevManager, int i);

    public static final native void AudDevManager_setOutputVolume__SWIG_0(long j, AudDevManager audDevManager, long j2, boolean z);

    public static final native void AudDevManager_setOutputVolume__SWIG_1(long j, AudDevManager audDevManager, long j2);

    public static final native void AudDevManager_setPlaybackDev(long j, AudDevManager audDevManager, int i);

    public static final native void AudDevManager_setPlc__SWIG_0(long j, AudDevManager audDevManager, boolean z, boolean z2);

    public static final native void AudDevManager_setPlc__SWIG_1(long j, AudDevManager audDevManager, boolean z);

    public static final native void AudDevManager_setVad__SWIG_0(long j, AudDevManager audDevManager, boolean z, boolean z2);

    public static final native void AudDevManager_setVad__SWIG_1(long j, AudDevManager audDevManager, boolean z);

    public static final native boolean AudDevManager_sndIsActive(long j, AudDevManager audDevManager);

    public static final native void AudioDevInfoVector_add(long j, AudioDevInfoVector audioDevInfoVector, long j2, AudioDevInfo audioDevInfo);

    public static final native long AudioDevInfoVector_capacity(long j, AudioDevInfoVector audioDevInfoVector);

    public static final native void AudioDevInfoVector_clear(long j, AudioDevInfoVector audioDevInfoVector);

    public static final native long AudioDevInfoVector_get(long j, AudioDevInfoVector audioDevInfoVector, int i);

    public static final native boolean AudioDevInfoVector_isEmpty(long j, AudioDevInfoVector audioDevInfoVector);

    public static final native void AudioDevInfoVector_reserve(long j, AudioDevInfoVector audioDevInfoVector, long j2);

    public static final native void AudioDevInfoVector_set(long j, AudioDevInfoVector audioDevInfoVector, int i, long j2, AudioDevInfo audioDevInfo);

    public static final native long AudioDevInfoVector_size(long j, AudioDevInfoVector audioDevInfoVector);

    public static final native long AudioDevInfo_caps_get(long j, AudioDevInfo audioDevInfo);

    public static final native void AudioDevInfo_caps_set(long j, AudioDevInfo audioDevInfo, long j2);

    public static final native long AudioDevInfo_defaultSamplesPerSec_get(long j, AudioDevInfo audioDevInfo);

    public static final native void AudioDevInfo_defaultSamplesPerSec_set(long j, AudioDevInfo audioDevInfo, long j2);

    public static final native String AudioDevInfo_driver_get(long j, AudioDevInfo audioDevInfo);

    public static final native void AudioDevInfo_driver_set(long j, AudioDevInfo audioDevInfo, String str);

    public static final native long AudioDevInfo_extFmt_get(long j, AudioDevInfo audioDevInfo);

    public static final native void AudioDevInfo_extFmt_set(long j, AudioDevInfo audioDevInfo, long j2, MediaFormatVector mediaFormatVector);

    public static final native long AudioDevInfo_inputCount_get(long j, AudioDevInfo audioDevInfo);

    public static final native void AudioDevInfo_inputCount_set(long j, AudioDevInfo audioDevInfo, long j2);

    public static final native String AudioDevInfo_name_get(long j, AudioDevInfo audioDevInfo);

    public static final native void AudioDevInfo_name_set(long j, AudioDevInfo audioDevInfo, String str);

    public static final native long AudioDevInfo_outputCount_get(long j, AudioDevInfo audioDevInfo);

    public static final native void AudioDevInfo_outputCount_set(long j, AudioDevInfo audioDevInfo, long j2);

    public static final native long AudioDevInfo_routes_get(long j, AudioDevInfo audioDevInfo);

    public static final native void AudioDevInfo_routes_set(long j, AudioDevInfo audioDevInfo, long j2);

    public static final native int AudioMediaPlayerInfo_formatId_get(long j, AudioMediaPlayerInfo audioMediaPlayerInfo);

    public static final native void AudioMediaPlayerInfo_formatId_set(long j, AudioMediaPlayerInfo audioMediaPlayerInfo, int i);

    public static final native long AudioMediaPlayerInfo_payloadBitsPerSample_get(long j, AudioMediaPlayerInfo audioMediaPlayerInfo);

    public static final native void AudioMediaPlayerInfo_payloadBitsPerSample_set(long j, AudioMediaPlayerInfo audioMediaPlayerInfo, long j2);

    public static final native long AudioMediaPlayerInfo_sizeBytes_get(long j, AudioMediaPlayerInfo audioMediaPlayerInfo);

    public static final native void AudioMediaPlayerInfo_sizeBytes_set(long j, AudioMediaPlayerInfo audioMediaPlayerInfo, long j2);

    public static final native long AudioMediaPlayerInfo_sizeSamples_get(long j, AudioMediaPlayerInfo audioMediaPlayerInfo);

    public static final native void AudioMediaPlayerInfo_sizeSamples_set(long j, AudioMediaPlayerInfo audioMediaPlayerInfo, long j2);

    public static final native long AudioMediaPlayer_SWIGUpcast(long j);

    public static final native void AudioMediaPlayer_createPlayer__SWIG_0(long j, AudioMediaPlayer audioMediaPlayer, String str, long j2);

    public static final native void AudioMediaPlayer_createPlayer__SWIG_1(long j, AudioMediaPlayer audioMediaPlayer, String str);

    public static final native void AudioMediaPlayer_createPlaylist__SWIG_0(long j, AudioMediaPlayer audioMediaPlayer, long j2, StringVector stringVector, String str, long j3);

    public static final native void AudioMediaPlayer_createPlaylist__SWIG_1(long j, AudioMediaPlayer audioMediaPlayer, long j2, StringVector stringVector, String str);

    public static final native void AudioMediaPlayer_createPlaylist__SWIG_2(long j, AudioMediaPlayer audioMediaPlayer, long j2, StringVector stringVector);

    public static final native long AudioMediaPlayer_getInfo(long j, AudioMediaPlayer audioMediaPlayer);

    public static final native long AudioMediaPlayer_getPos(long j, AudioMediaPlayer audioMediaPlayer);

    public static final native boolean AudioMediaPlayer_onEof(long j, AudioMediaPlayer audioMediaPlayer);

    public static final native void AudioMediaPlayer_setPos(long j, AudioMediaPlayer audioMediaPlayer, long j2);

    public static final native long AudioMediaPlayer_typecastFromAudioMedia(long j, AudioMedia audioMedia);

    public static final native long AudioMediaRecorder_SWIGUpcast(long j);

    public static final native void AudioMediaRecorder_createRecorder__SWIG_0(long j, AudioMediaRecorder audioMediaRecorder, String str, long j2, long j3, long j4);

    public static final native void AudioMediaRecorder_createRecorder__SWIG_1(long j, AudioMediaRecorder audioMediaRecorder, String str, long j2, long j3);

    public static final native void AudioMediaRecorder_createRecorder__SWIG_2(long j, AudioMediaRecorder audioMediaRecorder, String str, long j2);

    public static final native void AudioMediaRecorder_createRecorder__SWIG_3(long j, AudioMediaRecorder audioMediaRecorder, String str);

    public static final native long AudioMediaRecorder_typecastFromAudioMedia(long j, AudioMedia audioMedia);

    public static final native void AudioMediaVector_add(long j, AudioMediaVector audioMediaVector, long j2, AudioMedia audioMedia);

    public static final native long AudioMediaVector_capacity(long j, AudioMediaVector audioMediaVector);

    public static final native void AudioMediaVector_clear(long j, AudioMediaVector audioMediaVector);

    public static final native long AudioMediaVector_get(long j, AudioMediaVector audioMediaVector, int i);

    public static final native boolean AudioMediaVector_isEmpty(long j, AudioMediaVector audioMediaVector);

    public static final native void AudioMediaVector_reserve(long j, AudioMediaVector audioMediaVector, long j2);

    public static final native void AudioMediaVector_set(long j, AudioMediaVector audioMediaVector, int i, long j2, AudioMedia audioMedia);

    public static final native long AudioMediaVector_size(long j, AudioMediaVector audioMediaVector);

    public static final native long AudioMedia_SWIGUpcast(long j);

    public static final native void AudioMedia_adjustRxLevel(long j, AudioMedia audioMedia, float f);

    public static final native void AudioMedia_adjustTxLevel(long j, AudioMedia audioMedia, float f);

    public static final native int AudioMedia_getPortId(long j, AudioMedia audioMedia);

    public static final native long AudioMedia_getPortInfo(long j, AudioMedia audioMedia);

    public static final native long AudioMedia_getPortInfoFromId(int i);

    public static final native long AudioMedia_getRxLevel(long j, AudioMedia audioMedia);

    public static final native long AudioMedia_getTxLevel(long j, AudioMedia audioMedia);

    public static final native void AudioMedia_startTransmit(long j, AudioMedia audioMedia, long j2, AudioMedia audioMedia2);

    public static final native void AudioMedia_stopTransmit(long j, AudioMedia audioMedia, long j2, AudioMedia audioMedia2);

    public static final native long AudioMedia_typecastFromMedia(long j, Media media);

    public static final native void AuthCredInfoVector_add(long j, AuthCredInfoVector authCredInfoVector, long j2, AuthCredInfo authCredInfo);

    public static final native long AuthCredInfoVector_capacity(long j, AuthCredInfoVector authCredInfoVector);

    public static final native void AuthCredInfoVector_clear(long j, AuthCredInfoVector authCredInfoVector);

    public static final native long AuthCredInfoVector_get(long j, AuthCredInfoVector authCredInfoVector, int i);

    public static final native boolean AuthCredInfoVector_isEmpty(long j, AuthCredInfoVector authCredInfoVector);

    public static final native void AuthCredInfoVector_reserve(long j, AuthCredInfoVector authCredInfoVector, long j2);

    public static final native void AuthCredInfoVector_set(long j, AuthCredInfoVector authCredInfoVector, int i, long j2, AuthCredInfo authCredInfo);

    public static final native long AuthCredInfoVector_size(long j, AuthCredInfoVector authCredInfoVector);

    public static final native long AuthCredInfo_SWIGUpcast(long j);

    public static final native String AuthCredInfo_akaAmf_get(long j, AuthCredInfo authCredInfo);

    public static final native void AuthCredInfo_akaAmf_set(long j, AuthCredInfo authCredInfo, String str);

    public static final native String AuthCredInfo_akaK_get(long j, AuthCredInfo authCredInfo);

    public static final native void AuthCredInfo_akaK_set(long j, AuthCredInfo authCredInfo, String str);

    public static final native String AuthCredInfo_akaOp_get(long j, AuthCredInfo authCredInfo);

    public static final native void AuthCredInfo_akaOp_set(long j, AuthCredInfo authCredInfo, String str);

    public static final native int AuthCredInfo_dataType_get(long j, AuthCredInfo authCredInfo);

    public static final native void AuthCredInfo_dataType_set(long j, AuthCredInfo authCredInfo, int i);

    public static final native String AuthCredInfo_data_get(long j, AuthCredInfo authCredInfo);

    public static final native void AuthCredInfo_data_set(long j, AuthCredInfo authCredInfo, String str);

    public static final native void AuthCredInfo_readObject(long j, AuthCredInfo authCredInfo, long j2, ContainerNode containerNode);

    public static final native String AuthCredInfo_realm_get(long j, AuthCredInfo authCredInfo);

    public static final native void AuthCredInfo_realm_set(long j, AuthCredInfo authCredInfo, String str);

    public static final native String AuthCredInfo_scheme_get(long j, AuthCredInfo authCredInfo);

    public static final native void AuthCredInfo_scheme_set(long j, AuthCredInfo authCredInfo, String str);

    public static final native String AuthCredInfo_username_get(long j, AuthCredInfo authCredInfo);

    public static final native void AuthCredInfo_username_set(long j, AuthCredInfo authCredInfo, String str);

    public static final native void AuthCredInfo_writeObject(long j, AuthCredInfo authCredInfo, long j2, ContainerNode containerNode);

    public static final native long BuddyConfig_SWIGUpcast(long j);

    public static final native void BuddyConfig_readObject(long j, BuddyConfig buddyConfig, long j2, ContainerNode containerNode);

    public static final native boolean BuddyConfig_subscribe_get(long j, BuddyConfig buddyConfig);

    public static final native void BuddyConfig_subscribe_set(long j, BuddyConfig buddyConfig, boolean z);

    public static final native String BuddyConfig_uri_get(long j, BuddyConfig buddyConfig);

    public static final native void BuddyConfig_uri_set(long j, BuddyConfig buddyConfig, String str);

    public static final native void BuddyConfig_writeObject(long j, BuddyConfig buddyConfig, long j2, ContainerNode containerNode);

    public static final native String BuddyInfo_contact_get(long j, BuddyInfo buddyInfo);

    public static final native void BuddyInfo_contact_set(long j, BuddyInfo buddyInfo, String str);

    public static final native boolean BuddyInfo_presMonitorEnabled_get(long j, BuddyInfo buddyInfo);

    public static final native void BuddyInfo_presMonitorEnabled_set(long j, BuddyInfo buddyInfo, boolean z);

    public static final native long BuddyInfo_presStatus_get(long j, BuddyInfo buddyInfo);

    public static final native void BuddyInfo_presStatus_set(long j, BuddyInfo buddyInfo, long j2, PresenceStatus presenceStatus);

    public static final native String BuddyInfo_subStateName_get(long j, BuddyInfo buddyInfo);

    public static final native void BuddyInfo_subStateName_set(long j, BuddyInfo buddyInfo, String str);

    public static final native int BuddyInfo_subState_get(long j, BuddyInfo buddyInfo);

    public static final native void BuddyInfo_subState_set(long j, BuddyInfo buddyInfo, int i);

    public static final native int BuddyInfo_subTermCode_get(long j, BuddyInfo buddyInfo);

    public static final native void BuddyInfo_subTermCode_set(long j, BuddyInfo buddyInfo, int i);

    public static final native String BuddyInfo_subTermReason_get(long j, BuddyInfo buddyInfo);

    public static final native void BuddyInfo_subTermReason_set(long j, BuddyInfo buddyInfo, String str);

    public static final native String BuddyInfo_uri_get(long j, BuddyInfo buddyInfo);

    public static final native void BuddyInfo_uri_set(long j, BuddyInfo buddyInfo, String str);

    public static final native void BuddyVector_add(long j, BuddyVector buddyVector, long j2, Buddy buddy);

    public static final native long BuddyVector_capacity(long j, BuddyVector buddyVector);

    public static final native void BuddyVector_clear(long j, BuddyVector buddyVector);

    public static final native long BuddyVector_get(long j, BuddyVector buddyVector, int i);

    public static final native boolean BuddyVector_isEmpty(long j, BuddyVector buddyVector);

    public static final native void BuddyVector_reserve(long j, BuddyVector buddyVector, long j2);

    public static final native void BuddyVector_set(long j, BuddyVector buddyVector, int i, long j2, Buddy buddy);

    public static final native long BuddyVector_size(long j, BuddyVector buddyVector);

    public static final native void Buddy_change_ownership(Buddy buddy, long j, boolean z);

    public static final native void Buddy_create(long j, Buddy buddy, long j2, Account account, long j3, BuddyConfig buddyConfig);

    public static final native void Buddy_director_connect(Buddy buddy, long j, boolean z, boolean z2);

    public static final native long Buddy_getInfo(long j, Buddy buddy);

    public static final native boolean Buddy_isValid(long j, Buddy buddy);

    public static final native void Buddy_onBuddyState(long j, Buddy buddy);

    public static final native void Buddy_onBuddyStateSwigExplicitBuddy(long j, Buddy buddy);

    public static final native void Buddy_sendInstantMessage(long j, Buddy buddy, long j2, SendInstantMessageParam sendInstantMessageParam);

    public static final native void Buddy_sendTypingIndication(long j, Buddy buddy, long j2, SendTypingIndicationParam sendTypingIndicationParam);

    public static final native void Buddy_subscribePresence(long j, Buddy buddy, boolean z);

    public static final native void Buddy_updatePresence(long j, Buddy buddy);

    public static final native int CallInfo_accId_get(long j, CallInfo callInfo);

    public static final native void CallInfo_accId_set(long j, CallInfo callInfo, int i);

    public static final native String CallInfo_callIdString_get(long j, CallInfo callInfo);

    public static final native void CallInfo_callIdString_set(long j, CallInfo callInfo, String str);

    public static final native long CallInfo_connectDuration_get(long j, CallInfo callInfo);

    public static final native void CallInfo_connectDuration_set(long j, CallInfo callInfo, long j2, TimeVal timeVal);

    public static final native int CallInfo_id_get(long j, CallInfo callInfo);

    public static final native void CallInfo_id_set(long j, CallInfo callInfo, int i);

    public static final native String CallInfo_lastReason_get(long j, CallInfo callInfo);

    public static final native void CallInfo_lastReason_set(long j, CallInfo callInfo, String str);

    public static final native int CallInfo_lastStatusCode_get(long j, CallInfo callInfo);

    public static final native void CallInfo_lastStatusCode_set(long j, CallInfo callInfo, int i);

    public static final native String CallInfo_localContact_get(long j, CallInfo callInfo);

    public static final native void CallInfo_localContact_set(long j, CallInfo callInfo, String str);

    public static final native String CallInfo_localUri_get(long j, CallInfo callInfo);

    public static final native void CallInfo_localUri_set(long j, CallInfo callInfo, String str);

    public static final native long CallInfo_media_get(long j, CallInfo callInfo);

    public static final native void CallInfo_media_set(long j, CallInfo callInfo, long j2, CallMediaInfoVector callMediaInfoVector);

    public static final native long CallInfo_provMedia_get(long j, CallInfo callInfo);

    public static final native void CallInfo_provMedia_set(long j, CallInfo callInfo, long j2, CallMediaInfoVector callMediaInfoVector);

    public static final native long CallInfo_remAudioCount_get(long j, CallInfo callInfo);

    public static final native void CallInfo_remAudioCount_set(long j, CallInfo callInfo, long j2);

    public static final native boolean CallInfo_remOfferer_get(long j, CallInfo callInfo);

    public static final native void CallInfo_remOfferer_set(long j, CallInfo callInfo, boolean z);

    public static final native long CallInfo_remVideoCount_get(long j, CallInfo callInfo);

    public static final native void CallInfo_remVideoCount_set(long j, CallInfo callInfo, long j2);

    public static final native String CallInfo_remoteContact_get(long j, CallInfo callInfo);

    public static final native void CallInfo_remoteContact_set(long j, CallInfo callInfo, String str);

    public static final native String CallInfo_remoteUri_get(long j, CallInfo callInfo);

    public static final native void CallInfo_remoteUri_set(long j, CallInfo callInfo, String str);

    public static final native int CallInfo_role_get(long j, CallInfo callInfo);

    public static final native void CallInfo_role_set(long j, CallInfo callInfo, int i);

    public static final native long CallInfo_setting_get(long j, CallInfo callInfo);

    public static final native void CallInfo_setting_set(long j, CallInfo callInfo, long j2, CallSetting callSetting);

    public static final native String CallInfo_stateText_get(long j, CallInfo callInfo);

    public static final native void CallInfo_stateText_set(long j, CallInfo callInfo, String str);

    public static final native int CallInfo_state_get(long j, CallInfo callInfo);

    public static final native void CallInfo_state_set(long j, CallInfo callInfo, int i);

    public static final native long CallInfo_totalDuration_get(long j, CallInfo callInfo);

    public static final native void CallInfo_totalDuration_set(long j, CallInfo callInfo, long j2, TimeVal timeVal);

    public static final native void CallMediaInfoVector_add(long j, CallMediaInfoVector callMediaInfoVector, long j2, CallMediaInfo callMediaInfo);

    public static final native long CallMediaInfoVector_capacity(long j, CallMediaInfoVector callMediaInfoVector);

    public static final native void CallMediaInfoVector_clear(long j, CallMediaInfoVector callMediaInfoVector);

    public static final native long CallMediaInfoVector_get(long j, CallMediaInfoVector callMediaInfoVector, int i);

    public static final native boolean CallMediaInfoVector_isEmpty(long j, CallMediaInfoVector callMediaInfoVector);

    public static final native void CallMediaInfoVector_reserve(long j, CallMediaInfoVector callMediaInfoVector, long j2);

    public static final native void CallMediaInfoVector_set(long j, CallMediaInfoVector callMediaInfoVector, int i, long j2, CallMediaInfo callMediaInfo);

    public static final native long CallMediaInfoVector_size(long j, CallMediaInfoVector callMediaInfoVector);

    public static final native int CallMediaInfo_audioConfSlot_get(long j, CallMediaInfo callMediaInfo);

    public static final native void CallMediaInfo_audioConfSlot_set(long j, CallMediaInfo callMediaInfo, int i);

    public static final native int CallMediaInfo_dir_get(long j, CallMediaInfo callMediaInfo);

    public static final native void CallMediaInfo_dir_set(long j, CallMediaInfo callMediaInfo, int i);

    public static final native long CallMediaInfo_index_get(long j, CallMediaInfo callMediaInfo);

    public static final native void CallMediaInfo_index_set(long j, CallMediaInfo callMediaInfo, long j2);

    public static final native int CallMediaInfo_status_get(long j, CallMediaInfo callMediaInfo);

    public static final native void CallMediaInfo_status_set(long j, CallMediaInfo callMediaInfo, int i);

    public static final native int CallMediaInfo_type_get(long j, CallMediaInfo callMediaInfo);

    public static final native void CallMediaInfo_type_set(long j, CallMediaInfo callMediaInfo, int i);

    public static final native int CallMediaInfo_videoCapDev_get(long j, CallMediaInfo callMediaInfo);

    public static final native void CallMediaInfo_videoCapDev_set(long j, CallMediaInfo callMediaInfo, int i);

    public static final native int CallMediaInfo_videoIncomingWindowId_get(long j, CallMediaInfo callMediaInfo);

    public static final native void CallMediaInfo_videoIncomingWindowId_set(long j, CallMediaInfo callMediaInfo, int i);

    public static final native long CallOpParam_opt_get(long j, CallOpParam callOpParam);

    public static final native void CallOpParam_opt_set(long j, CallOpParam callOpParam, long j2, CallSetting callSetting);

    public static final native long CallOpParam_options_get(long j, CallOpParam callOpParam);

    public static final native void CallOpParam_options_set(long j, CallOpParam callOpParam, long j2);

    public static final native String CallOpParam_reason_get(long j, CallOpParam callOpParam);

    public static final native void CallOpParam_reason_set(long j, CallOpParam callOpParam, String str);

    public static final native int CallOpParam_statusCode_get(long j, CallOpParam callOpParam);

    public static final native void CallOpParam_statusCode_set(long j, CallOpParam callOpParam, int i);

    public static final native long CallOpParam_txOption_get(long j, CallOpParam callOpParam);

    public static final native void CallOpParam_txOption_set(long j, CallOpParam callOpParam, long j2, SipTxOption sipTxOption);

    public static final native String CallSendRequestParam_method_get(long j, CallSendRequestParam callSendRequestParam);

    public static final native void CallSendRequestParam_method_set(long j, CallSendRequestParam callSendRequestParam, String str);

    public static final native long CallSendRequestParam_txOption_get(long j, CallSendRequestParam callSendRequestParam);

    public static final native void CallSendRequestParam_txOption_set(long j, CallSendRequestParam callSendRequestParam, long j2, SipTxOption sipTxOption);

    public static final native long CallSetting_audioCount_get(long j, CallSetting callSetting);

    public static final native void CallSetting_audioCount_set(long j, CallSetting callSetting, long j2);

    public static final native long CallSetting_flag_get(long j, CallSetting callSetting);

    public static final native void CallSetting_flag_set(long j, CallSetting callSetting, long j2);

    public static final native boolean CallSetting_isEmpty(long j, CallSetting callSetting);

    public static final native long CallSetting_reqKeyframeMethod_get(long j, CallSetting callSetting);

    public static final native void CallSetting_reqKeyframeMethod_set(long j, CallSetting callSetting, long j2);

    public static final native long CallSetting_videoCount_get(long j, CallSetting callSetting);

    public static final native void CallSetting_videoCount_set(long j, CallSetting callSetting, long j2);

    public static final native int CallVidSetStreamParam_capDev_get(long j, CallVidSetStreamParam callVidSetStreamParam);

    public static final native void CallVidSetStreamParam_capDev_set(long j, CallVidSetStreamParam callVidSetStreamParam, int i);

    public static final native int CallVidSetStreamParam_dir_get(long j, CallVidSetStreamParam callVidSetStreamParam);

    public static final native void CallVidSetStreamParam_dir_set(long j, CallVidSetStreamParam callVidSetStreamParam, int i);

    public static final native int CallVidSetStreamParam_medIdx_get(long j, CallVidSetStreamParam callVidSetStreamParam);

    public static final native void CallVidSetStreamParam_medIdx_set(long j, CallVidSetStreamParam callVidSetStreamParam, int i);

    public static final native void Call_answer(long j, Call call, long j2, CallOpParam callOpParam);

    public static final native void Call_change_ownership(Call call, long j, boolean z);

    public static final native void Call_dialDtmf(long j, Call call, String str);

    public static final native void Call_director_connect(Call call, long j, boolean z, boolean z2);

    public static final native String Call_dump(long j, Call call, boolean z, String str);

    public static final native int Call_getId(long j, Call call);

    public static final native long Call_getInfo(long j, Call call);

    public static final native long Call_getMedTransportInfo(long j, Call call, long j2);

    public static final native long Call_getMedia(long j, Call call, long j2);

    public static final native int Call_getRemNatType(long j, Call call);

    public static final native long Call_getStreamInfo(long j, Call call, long j2);

    public static final native long Call_getStreamStat(long j, Call call, long j2);

    public static final native long Call_getUserData(long j, Call call);

    public static final native void Call_hangup(long j, Call call, long j2, CallOpParam callOpParam);

    public static final native boolean Call_hasMedia(long j, Call call);

    public static final native boolean Call_isActive(long j, Call call);

    public static final native long Call_lookup(int i);

    public static final native void Call_makeCall(long j, Call call, String str, long j2, CallOpParam callOpParam);

    public static final native void Call_onCallMediaEvent(long j, Call call, long j2, OnCallMediaEventParam onCallMediaEventParam);

    public static final native void Call_onCallMediaEventSwigExplicitCall(long j, Call call, long j2, OnCallMediaEventParam onCallMediaEventParam);

    public static final native void Call_onCallMediaState(long j, Call call, long j2, OnCallMediaStateParam onCallMediaStateParam);

    public static final native void Call_onCallMediaStateSwigExplicitCall(long j, Call call, long j2, OnCallMediaStateParam onCallMediaStateParam);

    public static final native void Call_onCallMediaTransportState(long j, Call call, long j2, OnCallMediaTransportStateParam onCallMediaTransportStateParam);

    public static final native void Call_onCallMediaTransportStateSwigExplicitCall(long j, Call call, long j2, OnCallMediaTransportStateParam onCallMediaTransportStateParam);

    public static final native int Call_onCallRedirected(long j, Call call, long j2, OnCallRedirectedParam onCallRedirectedParam);

    public static final native int Call_onCallRedirectedSwigExplicitCall(long j, Call call, long j2, OnCallRedirectedParam onCallRedirectedParam);

    public static final native void Call_onCallReplaceRequest(long j, Call call, long j2, OnCallReplaceRequestParam onCallReplaceRequestParam);

    public static final native void Call_onCallReplaceRequestSwigExplicitCall(long j, Call call, long j2, OnCallReplaceRequestParam onCallReplaceRequestParam);

    public static final native void Call_onCallReplaced(long j, Call call, long j2, OnCallReplacedParam onCallReplacedParam);

    public static final native void Call_onCallReplacedSwigExplicitCall(long j, Call call, long j2, OnCallReplacedParam onCallReplacedParam);

    public static final native void Call_onCallRxOffer(long j, Call call, long j2, OnCallRxOfferParam onCallRxOfferParam);

    public static final native void Call_onCallRxOfferSwigExplicitCall(long j, Call call, long j2, OnCallRxOfferParam onCallRxOfferParam);

    public static final native void Call_onCallSdpCreated(long j, Call call, long j2, OnCallSdpCreatedParam onCallSdpCreatedParam);

    public static final native void Call_onCallSdpCreatedSwigExplicitCall(long j, Call call, long j2, OnCallSdpCreatedParam onCallSdpCreatedParam);

    public static final native void Call_onCallState(long j, Call call, long j2, OnCallStateParam onCallStateParam);

    public static final native void Call_onCallStateSwigExplicitCall(long j, Call call, long j2, OnCallStateParam onCallStateParam);

    public static final native void Call_onCallTransferRequest(long j, Call call, long j2, OnCallTransferRequestParam onCallTransferRequestParam);

    public static final native void Call_onCallTransferRequestSwigExplicitCall(long j, Call call, long j2, OnCallTransferRequestParam onCallTransferRequestParam);

    public static final native void Call_onCallTransferStatus(long j, Call call, long j2, OnCallTransferStatusParam onCallTransferStatusParam);

    public static final native void Call_onCallTransferStatusSwigExplicitCall(long j, Call call, long j2, OnCallTransferStatusParam onCallTransferStatusParam);

    public static final native void Call_onCallTsxState(long j, Call call, long j2, OnCallTsxStateParam onCallTsxStateParam);

    public static final native void Call_onCallTsxStateSwigExplicitCall(long j, Call call, long j2, OnCallTsxStateParam onCallTsxStateParam);

    public static final native void Call_onCreateMediaTransport(long j, Call call, long j2, OnCreateMediaTransportParam onCreateMediaTransportParam);

    public static final native void Call_onCreateMediaTransportSwigExplicitCall(long j, Call call, long j2, OnCreateMediaTransportParam onCreateMediaTransportParam);

    public static final native void Call_onDtmfDigit(long j, Call call, long j2, OnDtmfDigitParam onDtmfDigitParam);

    public static final native void Call_onDtmfDigitSwigExplicitCall(long j, Call call, long j2, OnDtmfDigitParam onDtmfDigitParam);

    public static final native void Call_onInstantMessage(long j, Call call, long j2, OnInstantMessageParam onInstantMessageParam);

    public static final native void Call_onInstantMessageStatus(long j, Call call, long j2, OnInstantMessageStatusParam onInstantMessageStatusParam);

    public static final native void Call_onInstantMessageStatusSwigExplicitCall(long j, Call call, long j2, OnInstantMessageStatusParam onInstantMessageStatusParam);

    public static final native void Call_onInstantMessageSwigExplicitCall(long j, Call call, long j2, OnInstantMessageParam onInstantMessageParam);

    public static final native void Call_onStreamCreated(long j, Call call, long j2, OnStreamCreatedParam onStreamCreatedParam);

    public static final native void Call_onStreamCreatedSwigExplicitCall(long j, Call call, long j2, OnStreamCreatedParam onStreamCreatedParam);

    public static final native void Call_onStreamDestroyed(long j, Call call, long j2, OnStreamDestroyedParam onStreamDestroyedParam);

    public static final native void Call_onStreamDestroyedSwigExplicitCall(long j, Call call, long j2, OnStreamDestroyedParam onStreamDestroyedParam);

    public static final native void Call_onTypingIndication(long j, Call call, long j2, OnTypingIndicationParam onTypingIndicationParam);

    public static final native void Call_onTypingIndicationSwigExplicitCall(long j, Call call, long j2, OnTypingIndicationParam onTypingIndicationParam);

    public static final native void Call_processMediaUpdate(long j, Call call, long j2, OnCallMediaStateParam onCallMediaStateParam);

    public static final native void Call_processRedirect(long j, Call call, int i);

    public static final native void Call_processStateChange(long j, Call call, long j2, OnCallStateParam onCallStateParam);

    public static final native void Call_reinvite(long j, Call call, long j2, CallOpParam callOpParam);

    public static final native int Call_remoteHasCap(long j, Call call, int i, String str, String str2);

    public static final native void Call_sendInstantMessage(long j, Call call, long j2, SendInstantMessageParam sendInstantMessageParam);

    public static final native void Call_sendRequest(long j, Call call, long j2, CallSendRequestParam callSendRequestParam);

    public static final native void Call_sendTypingIndication(long j, Call call, long j2, SendTypingIndicationParam sendTypingIndicationParam);

    public static final native void Call_setHold(long j, Call call, long j2, CallOpParam callOpParam);

    public static final native void Call_setUserData(long j, Call call, long j2);

    public static final native void Call_update(long j, Call call, long j2, CallOpParam callOpParam);

    public static final native int Call_vidGetStreamIdx(long j, Call call);

    public static final native void Call_vidSetStream(long j, Call call, int i, long j2, CallVidSetStreamParam callVidSetStreamParam);

    public static final native boolean Call_vidStreamIsRunning(long j, Call call, int i, int i2);

    public static final native void Call_xfer(long j, Call call, String str, long j2, CallOpParam callOpParam);

    public static final native void Call_xferReplaces(long j, Call call, long j2, Call call2, long j3, CallOpParam callOpParam);

    public static final native void CodecInfoVector_add(long j, CodecInfoVector codecInfoVector, long j2, CodecInfo codecInfo);

    public static final native long CodecInfoVector_capacity(long j, CodecInfoVector codecInfoVector);

    public static final native void CodecInfoVector_clear(long j, CodecInfoVector codecInfoVector);

    public static final native long CodecInfoVector_get(long j, CodecInfoVector codecInfoVector, int i);

    public static final native boolean CodecInfoVector_isEmpty(long j, CodecInfoVector codecInfoVector);

    public static final native void CodecInfoVector_reserve(long j, CodecInfoVector codecInfoVector, long j2);

    public static final native void CodecInfoVector_set(long j, CodecInfoVector codecInfoVector, int i, long j2, CodecInfo codecInfo);

    public static final native long CodecInfoVector_size(long j, CodecInfoVector codecInfoVector);

    public static final native String CodecInfo_codecId_get(long j, CodecInfo codecInfo);

    public static final native void CodecInfo_codecId_set(long j, CodecInfo codecInfo, String str);

    public static final native String CodecInfo_desc_get(long j, CodecInfo codecInfo);

    public static final native void CodecInfo_desc_set(long j, CodecInfo codecInfo, String str);

    public static final native short CodecInfo_priority_get(long j, CodecInfo codecInfo);

    public static final native void CodecInfo_priority_set(long j, CodecInfo codecInfo, short s);

    public static final native long ConfPortInfo_format_get(long j, ConfPortInfo confPortInfo);

    public static final native void ConfPortInfo_format_set(long j, ConfPortInfo confPortInfo, long j2, MediaFormatAudio mediaFormatAudio);

    public static final native long ConfPortInfo_listeners_get(long j, ConfPortInfo confPortInfo);

    public static final native void ConfPortInfo_listeners_set(long j, ConfPortInfo confPortInfo, long j2, IntVector intVector);

    public static final native String ConfPortInfo_name_get(long j, ConfPortInfo confPortInfo);

    public static final native void ConfPortInfo_name_set(long j, ConfPortInfo confPortInfo, String str);

    public static final native int ConfPortInfo_portId_get(long j, ConfPortInfo confPortInfo);

    public static final native void ConfPortInfo_portId_set(long j, ConfPortInfo confPortInfo, int i);

    public static final native float ConfPortInfo_rxLevelAdj_get(long j, ConfPortInfo confPortInfo);

    public static final native void ConfPortInfo_rxLevelAdj_set(long j, ConfPortInfo confPortInfo, float f);

    public static final native float ConfPortInfo_txLevelAdj_get(long j, ConfPortInfo confPortInfo);

    public static final native void ConfPortInfo_txLevelAdj_set(long j, ConfPortInfo confPortInfo, float f);

    public static final native boolean ContainerNode_hasUnread(long j, ContainerNode containerNode);

    public static final native long ContainerNode_readArray__SWIG_0(long j, ContainerNode containerNode, String str);

    public static final native long ContainerNode_readArray__SWIG_1(long j, ContainerNode containerNode);

    public static final native boolean ContainerNode_readBool__SWIG_0(long j, ContainerNode containerNode, String str);

    public static final native boolean ContainerNode_readBool__SWIG_1(long j, ContainerNode containerNode);

    public static final native long ContainerNode_readContainer__SWIG_0(long j, ContainerNode containerNode, String str);

    public static final native long ContainerNode_readContainer__SWIG_1(long j, ContainerNode containerNode);

    public static final native int ContainerNode_readInt__SWIG_0(long j, ContainerNode containerNode, String str);

    public static final native int ContainerNode_readInt__SWIG_1(long j, ContainerNode containerNode);

    public static final native float ContainerNode_readNumber__SWIG_0(long j, ContainerNode containerNode, String str);

    public static final native float ContainerNode_readNumber__SWIG_1(long j, ContainerNode containerNode);

    public static final native void ContainerNode_readObject(long j, ContainerNode containerNode, long j2, PersistentObject persistentObject);

    public static final native long ContainerNode_readStringVector__SWIG_0(long j, ContainerNode containerNode, String str);

    public static final native long ContainerNode_readStringVector__SWIG_1(long j, ContainerNode containerNode);

    public static final native String ContainerNode_readString__SWIG_0(long j, ContainerNode containerNode, String str);

    public static final native String ContainerNode_readString__SWIG_1(long j, ContainerNode containerNode);

    public static final native String ContainerNode_unreadName(long j, ContainerNode containerNode);

    public static final native void ContainerNode_writeBool(long j, ContainerNode containerNode, String str, boolean z);

    public static final native void ContainerNode_writeInt(long j, ContainerNode containerNode, String str, int i);

    public static final native long ContainerNode_writeNewArray(long j, ContainerNode containerNode, String str);

    public static final native long ContainerNode_writeNewContainer(long j, ContainerNode containerNode, String str);

    public static final native void ContainerNode_writeNumber(long j, ContainerNode containerNode, String str, float f);

    public static final native void ContainerNode_writeObject(long j, ContainerNode containerNode, long j2, PersistentObject persistentObject);

    public static final native void ContainerNode_writeString(long j, ContainerNode containerNode, String str, String str2);

    public static final native void ContainerNode_writeStringVector(long j, ContainerNode containerNode, String str, long j2, StringVector stringVector);

    public static final native long Endpoint_audDevManager(long j, Endpoint endpoint);

    public static final native void Endpoint_change_ownership(Endpoint endpoint, long j, boolean z);

    public static final native long Endpoint_codecEnum(long j, Endpoint endpoint);

    public static final native long Endpoint_codecGetParam(long j, Endpoint endpoint, String str);

    public static final native void Endpoint_codecSetParam(long j, Endpoint endpoint, String str, long j2);

    public static final native void Endpoint_codecSetPriority(long j, Endpoint endpoint, String str, short s);

    public static final native void Endpoint_director_connect(Endpoint endpoint, long j, boolean z, boolean z2);

    public static final native void Endpoint_hangupAllCalls(long j, Endpoint endpoint);

    public static final native long Endpoint_instance();

    public static final native void Endpoint_libCreate(long j, Endpoint endpoint);

    public static final native void Endpoint_libDestroy___SWIG_0(long j, Endpoint endpoint, long j2);

    public static final native void Endpoint_libDestroy___SWIG_1(long j, Endpoint endpoint);

    public static final native int Endpoint_libGetState(long j, Endpoint endpoint);

    public static final native int Endpoint_libHandleEvents(long j, Endpoint endpoint, long j2);

    public static final native void Endpoint_libInit(long j, Endpoint endpoint, long j2, EpConfig epConfig);

    public static final native boolean Endpoint_libIsThreadRegistered(long j, Endpoint endpoint);

    public static final native void Endpoint_libRegisterThread(long j, Endpoint endpoint, String str);

    public static final native void Endpoint_libStart(long j, Endpoint endpoint);

    public static final native void Endpoint_libStopWorkerThreads(long j, Endpoint endpoint);

    public static final native long Endpoint_libVersion(long j, Endpoint endpoint);

    public static final native long Endpoint_mediaActivePorts(long j, Endpoint endpoint);

    public static final native void Endpoint_mediaAdd(long j, Endpoint endpoint, long j2, AudioMedia audioMedia);

    public static final native long Endpoint_mediaEnumPorts(long j, Endpoint endpoint);

    public static final native boolean Endpoint_mediaExists(long j, Endpoint endpoint, long j2, AudioMedia audioMedia);

    public static final native long Endpoint_mediaMaxPorts(long j, Endpoint endpoint);

    public static final native void Endpoint_mediaRemove(long j, Endpoint endpoint, long j2, AudioMedia audioMedia);

    public static final native void Endpoint_natCancelCheckStunServers__SWIG_0(long j, Endpoint endpoint, long j2, boolean z);

    public static final native void Endpoint_natCancelCheckStunServers__SWIG_1(long j, Endpoint endpoint, long j2);

    public static final native void Endpoint_natCheckStunServers(long j, Endpoint endpoint, long j2, StringVector stringVector, boolean z, long j3);

    public static final native void Endpoint_natDetectType(long j, Endpoint endpoint);

    public static final native int Endpoint_natGetType(long j, Endpoint endpoint);

    public static final native void Endpoint_onNatCheckStunServersComplete(long j, Endpoint endpoint, long j2, OnNatCheckStunServersCompleteParam onNatCheckStunServersCompleteParam);

    public static final native void Endpoint_onNatCheckStunServersCompleteSwigExplicitEndpoint(long j, Endpoint endpoint, long j2, OnNatCheckStunServersCompleteParam onNatCheckStunServersCompleteParam);

    public static final native void Endpoint_onNatDetectionComplete(long j, Endpoint endpoint, long j2, OnNatDetectionCompleteParam onNatDetectionCompleteParam);

    public static final native void Endpoint_onNatDetectionCompleteSwigExplicitEndpoint(long j, Endpoint endpoint, long j2, OnNatDetectionCompleteParam onNatDetectionCompleteParam);

    public static final native void Endpoint_onSelectAccount(long j, Endpoint endpoint, long j2, OnSelectAccountParam onSelectAccountParam);

    public static final native void Endpoint_onSelectAccountSwigExplicitEndpoint(long j, Endpoint endpoint, long j2, OnSelectAccountParam onSelectAccountParam);

    public static final native void Endpoint_onTimer(long j, Endpoint endpoint, long j2, OnTimerParam onTimerParam);

    public static final native void Endpoint_onTimerSwigExplicitEndpoint(long j, Endpoint endpoint, long j2, OnTimerParam onTimerParam);

    public static final native void Endpoint_onTransportState(long j, Endpoint endpoint, long j2, OnTransportStateParam onTransportStateParam);

    public static final native void Endpoint_onTransportStateSwigExplicitEndpoint(long j, Endpoint endpoint, long j2, OnTransportStateParam onTransportStateParam);

    public static final native void Endpoint_transportClose(long j, Endpoint endpoint, int i);

    public static final native int Endpoint_transportCreate(long j, Endpoint endpoint, int i, long j2, TransportConfig transportConfig);

    public static final native long Endpoint_transportEnum(long j, Endpoint endpoint);

    public static final native long Endpoint_transportGetInfo(long j, Endpoint endpoint, int i);

    public static final native void Endpoint_transportSetEnable(long j, Endpoint endpoint, int i, boolean z);

    public static final native void Endpoint_utilAddPendingJob(long j, Endpoint endpoint, long j2, PendingJob pendingJob);

    public static final native void Endpoint_utilLogWrite__SWIG_0(long j, Endpoint endpoint, int i, String str, String str2);

    public static final native void Endpoint_utilLogWrite__SWIG_1(long j, Endpoint endpoint, long j2, LogEntry logEntry);

    public static final native long Endpoint_utilSslGetAvailableCiphers(long j, Endpoint endpoint);

    public static final native String Endpoint_utilStrError(long j, Endpoint endpoint, int i);

    public static final native void Endpoint_utilTimerCancel(long j, Endpoint endpoint, long j2);

    public static final native long Endpoint_utilTimerSchedule(long j, Endpoint endpoint, long j2, long j3);

    public static final native int Endpoint_utilVerifySipUri(long j, Endpoint endpoint, String str);

    public static final native int Endpoint_utilVerifyUri(long j, Endpoint endpoint, String str);

    public static final native long EpConfig_SWIGUpcast(long j);

    public static final native long EpConfig_logConfig_get(long j, EpConfig epConfig);

    public static final native void EpConfig_logConfig_set(long j, EpConfig epConfig, long j2, LogConfig logConfig);

    public static final native long EpConfig_medConfig_get(long j, EpConfig epConfig);

    public static final native void EpConfig_medConfig_set(long j, EpConfig epConfig, long j2, MediaConfig mediaConfig);

    public static final native void EpConfig_readObject(long j, EpConfig epConfig, long j2, ContainerNode containerNode);

    public static final native long EpConfig_uaConfig_get(long j, EpConfig epConfig);

    public static final native void EpConfig_uaConfig_set(long j, EpConfig epConfig, long j2, UaConfig uaConfig);

    public static final native void EpConfig_writeObject(long j, EpConfig epConfig, long j2, ContainerNode containerNode);

    public static final native String Error_info__SWIG_0(long j, Error error, boolean z);

    public static final native String Error_info__SWIG_1(long j, Error error);

    public static final native String Error_reason_get(long j, Error error);

    public static final native void Error_reason_set(long j, Error error, String str);

    public static final native String Error_srcFile_get(long j, Error error);

    public static final native void Error_srcFile_set(long j, Error error, String str);

    public static final native int Error_srcLine_get(long j, Error error);

    public static final native void Error_srcLine_set(long j, Error error, int i);

    public static final native int Error_status_get(long j, Error error);

    public static final native void Error_status_set(long j, Error error, int i);

    public static final native String Error_title_get(long j, Error error);

    public static final native void Error_title_set(long j, Error error, String str);

    public static final native void FindBuddyMatch_change_ownership(FindBuddyMatch findBuddyMatch, long j, boolean z);

    public static final native void FindBuddyMatch_director_connect(FindBuddyMatch findBuddyMatch, long j, boolean z, boolean z2);

    public static final native boolean FindBuddyMatch_match(long j, FindBuddyMatch findBuddyMatch, String str, long j2, Buddy buddy);

    public static final native boolean FindBuddyMatch_matchSwigExplicitFindBuddyMatch(long j, FindBuddyMatch findBuddyMatch, String str, long j2, Buddy buddy);

    public static final native int INVALID_ID_get();

    public static final native void IntVector_add(long j, IntVector intVector, int i);

    public static final native long IntVector_capacity(long j, IntVector intVector);

    public static final native void IntVector_clear(long j, IntVector intVector);

    public static final native int IntVector_get(long j, IntVector intVector, int i);

    public static final native boolean IntVector_isEmpty(long j, IntVector intVector);

    public static final native void IntVector_reserve(long j, IntVector intVector, long j2);

    public static final native void IntVector_set(long j, IntVector intVector, int i, int i2);

    public static final native long IntVector_size(long j, IntVector intVector);

    public static final native long JbufState_avgBurst_get(long j, JbufState jbufState);

    public static final native void JbufState_avgBurst_set(long j, JbufState jbufState, long j2);

    public static final native long JbufState_avgDelayMsec_get(long j, JbufState jbufState);

    public static final native void JbufState_avgDelayMsec_set(long j, JbufState jbufState, long j2);

    public static final native long JbufState_burst_get(long j, JbufState jbufState);

    public static final native void JbufState_burst_set(long j, JbufState jbufState, long j2);

    public static final native long JbufState_devDelayMsec_get(long j, JbufState jbufState);

    public static final native void JbufState_devDelayMsec_set(long j, JbufState jbufState, long j2);

    public static final native long JbufState_discard_get(long j, JbufState jbufState);

    public static final native void JbufState_discard_set(long j, JbufState jbufState, long j2);

    public static final native long JbufState_empty_get(long j, JbufState jbufState);

    public static final native void JbufState_empty_set(long j, JbufState jbufState, long j2);

    public static final native long JbufState_frameSize_get(long j, JbufState jbufState);

    public static final native void JbufState_frameSize_set(long j, JbufState jbufState, long j2);

    public static final native long JbufState_lost_get(long j, JbufState jbufState);

    public static final native void JbufState_lost_set(long j, JbufState jbufState, long j2);

    public static final native long JbufState_maxDelayMsec_get(long j, JbufState jbufState);

    public static final native void JbufState_maxDelayMsec_set(long j, JbufState jbufState, long j2);

    public static final native long JbufState_maxPrefetch_get(long j, JbufState jbufState);

    public static final native void JbufState_maxPrefetch_set(long j, JbufState jbufState, long j2);

    public static final native long JbufState_minDelayMsec_get(long j, JbufState jbufState);

    public static final native void JbufState_minDelayMsec_set(long j, JbufState jbufState, long j2);

    public static final native long JbufState_minPrefetch_get(long j, JbufState jbufState);

    public static final native void JbufState_minPrefetch_set(long j, JbufState jbufState, long j2);

    public static final native long JbufState_prefetch_get(long j, JbufState jbufState);

    public static final native void JbufState_prefetch_set(long j, JbufState jbufState, long j2);

    public static final native long JbufState_size_get(long j, JbufState jbufState);

    public static final native void JbufState_size_set(long j, JbufState jbufState, long j2);

    public static final native long JsonDocument_SWIGUpcast(long j);

    public static final native long JsonDocument_getRootContainer(long j, JsonDocument jsonDocument);

    public static final native void JsonDocument_loadFile(long j, JsonDocument jsonDocument, String str);

    public static final native void JsonDocument_loadString(long j, JsonDocument jsonDocument, String str);

    public static final native void JsonDocument_saveFile(long j, JsonDocument jsonDocument, String str);

    public static final native String JsonDocument_saveString(long j, JsonDocument jsonDocument);

    public static final native long LogConfig_SWIGUpcast(long j);

    public static final native long LogConfig_consoleLevel_get(long j, LogConfig logConfig);

    public static final native void LogConfig_consoleLevel_set(long j, LogConfig logConfig, long j2);

    public static final native long LogConfig_decor_get(long j, LogConfig logConfig);

    public static final native void LogConfig_decor_set(long j, LogConfig logConfig, long j2);

    public static final native long LogConfig_fileFlags_get(long j, LogConfig logConfig);

    public static final native void LogConfig_fileFlags_set(long j, LogConfig logConfig, long j2);

    public static final native String LogConfig_filename_get(long j, LogConfig logConfig);

    public static final native void LogConfig_filename_set(long j, LogConfig logConfig, String str);

    public static final native long LogConfig_level_get(long j, LogConfig logConfig);

    public static final native void LogConfig_level_set(long j, LogConfig logConfig, long j2);

    public static final native long LogConfig_msgLogging_get(long j, LogConfig logConfig);

    public static final native void LogConfig_msgLogging_set(long j, LogConfig logConfig, long j2);

    public static final native void LogConfig_readObject(long j, LogConfig logConfig, long j2, ContainerNode containerNode);

    public static final native void LogConfig_writeObject(long j, LogConfig logConfig, long j2, ContainerNode containerNode);

    public static final native long LogConfig_writer_get(long j, LogConfig logConfig);

    public static final native void LogConfig_writer_set(long j, LogConfig logConfig, long j2, LogWriter logWriter);

    public static final native int LogEntry_level_get(long j, LogEntry logEntry);

    public static final native void LogEntry_level_set(long j, LogEntry logEntry, int i);

    public static final native String LogEntry_msg_get(long j, LogEntry logEntry);

    public static final native void LogEntry_msg_set(long j, LogEntry logEntry, String str);

    public static final native int LogEntry_threadId_get(long j, LogEntry logEntry);

    public static final native void LogEntry_threadId_set(long j, LogEntry logEntry, int i);

    public static final native String LogEntry_threadName_get(long j, LogEntry logEntry);

    public static final native void LogEntry_threadName_set(long j, LogEntry logEntry, String str);

    public static final native void LogWriter_change_ownership(LogWriter logWriter, long j, boolean z);

    public static final native void LogWriter_director_connect(LogWriter logWriter, long j, boolean z, boolean z2);

    public static final native void LogWriter_write(long j, LogWriter logWriter, long j2, LogEntry logEntry);

    public static final native int MathStat_last_get(long j, MathStat mathStat);

    public static final native void MathStat_last_set(long j, MathStat mathStat, int i);

    public static final native int MathStat_max_get(long j, MathStat mathStat);

    public static final native void MathStat_max_set(long j, MathStat mathStat, int i);

    public static final native int MathStat_mean_get(long j, MathStat mathStat);

    public static final native void MathStat_mean_set(long j, MathStat mathStat, int i);

    public static final native int MathStat_min_get(long j, MathStat mathStat);

    public static final native void MathStat_min_set(long j, MathStat mathStat, int i);

    public static final native int MathStat_n_get(long j, MathStat mathStat);

    public static final native void MathStat_n_set(long j, MathStat mathStat, int i);

    public static final native long MediaConfig_SWIGUpcast(long j);

    public static final native long MediaConfig_audioFramePtime_get(long j, MediaConfig mediaConfig);

    public static final native void MediaConfig_audioFramePtime_set(long j, MediaConfig mediaConfig, long j2);

    public static final native long MediaConfig_channelCount_get(long j, MediaConfig mediaConfig);

    public static final native void MediaConfig_channelCount_set(long j, MediaConfig mediaConfig, long j2);

    public static final native long MediaConfig_clockRate_get(long j, MediaConfig mediaConfig);

    public static final native void MediaConfig_clockRate_set(long j, MediaConfig mediaConfig, long j2);

    public static final native long MediaConfig_ecOptions_get(long j, MediaConfig mediaConfig);

    public static final native void MediaConfig_ecOptions_set(long j, MediaConfig mediaConfig, long j2);

    public static final native long MediaConfig_ecTailLen_get(long j, MediaConfig mediaConfig);

    public static final native void MediaConfig_ecTailLen_set(long j, MediaConfig mediaConfig, long j2);

    public static final native boolean MediaConfig_hasIoqueue_get(long j, MediaConfig mediaConfig);

    public static final native void MediaConfig_hasIoqueue_set(long j, MediaConfig mediaConfig, boolean z);

    public static final native long MediaConfig_ilbcMode_get(long j, MediaConfig mediaConfig);

    public static final native void MediaConfig_ilbcMode_set(long j, MediaConfig mediaConfig, long j2);

    public static final native int MediaConfig_jbInit_get(long j, MediaConfig mediaConfig);

    public static final native void MediaConfig_jbInit_set(long j, MediaConfig mediaConfig, int i);

    public static final native int MediaConfig_jbMaxPre_get(long j, MediaConfig mediaConfig);

    public static final native void MediaConfig_jbMaxPre_set(long j, MediaConfig mediaConfig, int i);

    public static final native int MediaConfig_jbMax_get(long j, MediaConfig mediaConfig);

    public static final native void MediaConfig_jbMax_set(long j, MediaConfig mediaConfig, int i);

    public static final native int MediaConfig_jbMinPre_get(long j, MediaConfig mediaConfig);

    public static final native void MediaConfig_jbMinPre_set(long j, MediaConfig mediaConfig, int i);

    public static final native long MediaConfig_maxMediaPorts_get(long j, MediaConfig mediaConfig);

    public static final native void MediaConfig_maxMediaPorts_set(long j, MediaConfig mediaConfig, long j2);

    public static final native boolean MediaConfig_noVad_get(long j, MediaConfig mediaConfig);

    public static final native void MediaConfig_noVad_set(long j, MediaConfig mediaConfig, boolean z);

    public static final native long MediaConfig_ptime_get(long j, MediaConfig mediaConfig);

    public static final native void MediaConfig_ptime_set(long j, MediaConfig mediaConfig, long j2);

    public static final native long MediaConfig_quality_get(long j, MediaConfig mediaConfig);

    public static final native void MediaConfig_quality_set(long j, MediaConfig mediaConfig, long j2);

    public static final native void MediaConfig_readObject(long j, MediaConfig mediaConfig, long j2, ContainerNode containerNode);

    public static final native long MediaConfig_rxDropPct_get(long j, MediaConfig mediaConfig);

    public static final native void MediaConfig_rxDropPct_set(long j, MediaConfig mediaConfig, long j2);

    public static final native int MediaConfig_sndAutoCloseTime_get(long j, MediaConfig mediaConfig);

    public static final native void MediaConfig_sndAutoCloseTime_set(long j, MediaConfig mediaConfig, int i);

    public static final native long MediaConfig_sndClockRate_get(long j, MediaConfig mediaConfig);

    public static final native void MediaConfig_sndClockRate_set(long j, MediaConfig mediaConfig, long j2);

    public static final native long MediaConfig_sndPlayLatency_get(long j, MediaConfig mediaConfig);

    public static final native void MediaConfig_sndPlayLatency_set(long j, MediaConfig mediaConfig, long j2);

    public static final native long MediaConfig_sndRecLatency_get(long j, MediaConfig mediaConfig);

    public static final native void MediaConfig_sndRecLatency_set(long j, MediaConfig mediaConfig, long j2);

    public static final native long MediaConfig_threadCnt_get(long j, MediaConfig mediaConfig);

    public static final native void MediaConfig_threadCnt_set(long j, MediaConfig mediaConfig, long j2);

    public static final native long MediaConfig_txDropPct_get(long j, MediaConfig mediaConfig);

    public static final native void MediaConfig_txDropPct_set(long j, MediaConfig mediaConfig, long j2);

    public static final native boolean MediaConfig_vidPreviewEnableNative_get(long j, MediaConfig mediaConfig);

    public static final native void MediaConfig_vidPreviewEnableNative_set(long j, MediaConfig mediaConfig, boolean z);

    public static final native void MediaConfig_writeObject(long j, MediaConfig mediaConfig, long j2, ContainerNode containerNode);

    public static final native long MediaEvent_pjMediaEvent_get(long j, MediaEvent mediaEvent);

    public static final native void MediaEvent_pjMediaEvent_set(long j, MediaEvent mediaEvent, long j2);

    public static final native int MediaEvent_type_get(long j, MediaEvent mediaEvent);

    public static final native void MediaEvent_type_set(long j, MediaEvent mediaEvent, int i);

    public static final native long MediaFmtChangedEvent_newHeight_get(long j, MediaFmtChangedEvent mediaFmtChangedEvent);

    public static final native void MediaFmtChangedEvent_newHeight_set(long j, MediaFmtChangedEvent mediaFmtChangedEvent, long j2);

    public static final native long MediaFmtChangedEvent_newWidth_get(long j, MediaFmtChangedEvent mediaFmtChangedEvent);

    public static final native void MediaFmtChangedEvent_newWidth_set(long j, MediaFmtChangedEvent mediaFmtChangedEvent, long j2);

    public static final native long MediaFormatAudio_SWIGUpcast(long j);

    public static final native long MediaFormatAudio_avgBps_get(long j, MediaFormatAudio mediaFormatAudio);

    public static final native void MediaFormatAudio_avgBps_set(long j, MediaFormatAudio mediaFormatAudio, long j2);

    public static final native long MediaFormatAudio_bitsPerSample_get(long j, MediaFormatAudio mediaFormatAudio);

    public static final native void MediaFormatAudio_bitsPerSample_set(long j, MediaFormatAudio mediaFormatAudio, long j2);

    public static final native long MediaFormatAudio_channelCount_get(long j, MediaFormatAudio mediaFormatAudio);

    public static final native void MediaFormatAudio_channelCount_set(long j, MediaFormatAudio mediaFormatAudio, long j2);

    public static final native long MediaFormatAudio_clockRate_get(long j, MediaFormatAudio mediaFormatAudio);

    public static final native void MediaFormatAudio_clockRate_set(long j, MediaFormatAudio mediaFormatAudio, long j2);

    public static final native long MediaFormatAudio_frameTimeUsec_get(long j, MediaFormatAudio mediaFormatAudio);

    public static final native void MediaFormatAudio_frameTimeUsec_set(long j, MediaFormatAudio mediaFormatAudio, long j2);

    public static final native long MediaFormatAudio_maxBps_get(long j, MediaFormatAudio mediaFormatAudio);

    public static final native void MediaFormatAudio_maxBps_set(long j, MediaFormatAudio mediaFormatAudio, long j2);

    public static final native void MediaFormatVector_add(long j, MediaFormatVector mediaFormatVector, long j2, MediaFormat mediaFormat);

    public static final native long MediaFormatVector_capacity(long j, MediaFormatVector mediaFormatVector);

    public static final native void MediaFormatVector_clear(long j, MediaFormatVector mediaFormatVector);

    public static final native long MediaFormatVector_get(long j, MediaFormatVector mediaFormatVector, int i);

    public static final native boolean MediaFormatVector_isEmpty(long j, MediaFormatVector mediaFormatVector);

    public static final native void MediaFormatVector_reserve(long j, MediaFormatVector mediaFormatVector, long j2);

    public static final native void MediaFormatVector_set(long j, MediaFormatVector mediaFormatVector, int i, long j2, MediaFormat mediaFormat);

    public static final native long MediaFormatVector_size(long j, MediaFormatVector mediaFormatVector);

    public static final native long MediaFormatVideo_SWIGUpcast(long j);

    public static final native long MediaFormatVideo_avgBps_get(long j, MediaFormatVideo mediaFormatVideo);

    public static final native void MediaFormatVideo_avgBps_set(long j, MediaFormatVideo mediaFormatVideo, long j2);

    public static final native int MediaFormatVideo_fpsDenum_get(long j, MediaFormatVideo mediaFormatVideo);

    public static final native void MediaFormatVideo_fpsDenum_set(long j, MediaFormatVideo mediaFormatVideo, int i);

    public static final native int MediaFormatVideo_fpsNum_get(long j, MediaFormatVideo mediaFormatVideo);

    public static final native void MediaFormatVideo_fpsNum_set(long j, MediaFormatVideo mediaFormatVideo, int i);

    public static final native long MediaFormatVideo_height_get(long j, MediaFormatVideo mediaFormatVideo);

    public static final native void MediaFormatVideo_height_set(long j, MediaFormatVideo mediaFormatVideo, long j2);

    public static final native long MediaFormatVideo_maxBps_get(long j, MediaFormatVideo mediaFormatVideo);

    public static final native void MediaFormatVideo_maxBps_set(long j, MediaFormatVideo mediaFormatVideo, long j2);

    public static final native long MediaFormatVideo_width_get(long j, MediaFormatVideo mediaFormatVideo);

    public static final native void MediaFormatVideo_width_set(long j, MediaFormatVideo mediaFormatVideo, long j2);

    public static final native long MediaFormat_id_get(long j, MediaFormat mediaFormat);

    public static final native void MediaFormat_id_set(long j, MediaFormat mediaFormat, long j2);

    public static final native int MediaFormat_type_get(long j, MediaFormat mediaFormat);

    public static final native void MediaFormat_type_set(long j, MediaFormat mediaFormat, int i);

    public static final native String MediaTransportInfo_srcRtcpName_get(long j, MediaTransportInfo mediaTransportInfo);

    public static final native void MediaTransportInfo_srcRtcpName_set(long j, MediaTransportInfo mediaTransportInfo, String str);

    public static final native String MediaTransportInfo_srcRtpName_get(long j, MediaTransportInfo mediaTransportInfo);

    public static final native void MediaTransportInfo_srcRtpName_set(long j, MediaTransportInfo mediaTransportInfo, String str);

    public static final native int Media_getType(long j, Media media);

    public static final native long OnCallMediaEventParam_ev_get(long j, OnCallMediaEventParam onCallMediaEventParam);

    public static final native void OnCallMediaEventParam_ev_set(long j, OnCallMediaEventParam onCallMediaEventParam, long j2, MediaEvent mediaEvent);

    public static final native long OnCallMediaEventParam_medIdx_get(long j, OnCallMediaEventParam onCallMediaEventParam);

    public static final native void OnCallMediaEventParam_medIdx_set(long j, OnCallMediaEventParam onCallMediaEventParam, long j2);

    public static final native long OnCallMediaTransportStateParam_medIdx_get(long j, OnCallMediaTransportStateParam onCallMediaTransportStateParam);

    public static final native void OnCallMediaTransportStateParam_medIdx_set(long j, OnCallMediaTransportStateParam onCallMediaTransportStateParam, long j2);

    public static final native int OnCallMediaTransportStateParam_sipErrorCode_get(long j, OnCallMediaTransportStateParam onCallMediaTransportStateParam);

    public static final native void OnCallMediaTransportStateParam_sipErrorCode_set(long j, OnCallMediaTransportStateParam onCallMediaTransportStateParam, int i);

    public static final native int OnCallMediaTransportStateParam_state_get(long j, OnCallMediaTransportStateParam onCallMediaTransportStateParam);

    public static final native void OnCallMediaTransportStateParam_state_set(long j, OnCallMediaTransportStateParam onCallMediaTransportStateParam, int i);

    public static final native int OnCallMediaTransportStateParam_status_get(long j, OnCallMediaTransportStateParam onCallMediaTransportStateParam);

    public static final native void OnCallMediaTransportStateParam_status_set(long j, OnCallMediaTransportStateParam onCallMediaTransportStateParam, int i);

    public static final native long OnCallRedirectedParam_e_get(long j, OnCallRedirectedParam onCallRedirectedParam);

    public static final native void OnCallRedirectedParam_e_set(long j, OnCallRedirectedParam onCallRedirectedParam, long j2, SipEvent sipEvent);

    public static final native String OnCallRedirectedParam_targetUri_get(long j, OnCallRedirectedParam onCallRedirectedParam);

    public static final native void OnCallRedirectedParam_targetUri_set(long j, OnCallRedirectedParam onCallRedirectedParam, String str);

    public static final native long OnCallReplaceRequestParam_opt_get(long j, OnCallReplaceRequestParam onCallReplaceRequestParam);

    public static final native void OnCallReplaceRequestParam_opt_set(long j, OnCallReplaceRequestParam onCallReplaceRequestParam, long j2, CallSetting callSetting);

    public static final native long OnCallReplaceRequestParam_rdata_get(long j, OnCallReplaceRequestParam onCallReplaceRequestParam);

    public static final native void OnCallReplaceRequestParam_rdata_set(long j, OnCallReplaceRequestParam onCallReplaceRequestParam, long j2, SipRxData sipRxData);

    public static final native String OnCallReplaceRequestParam_reason_get(long j, OnCallReplaceRequestParam onCallReplaceRequestParam);

    public static final native void OnCallReplaceRequestParam_reason_set(long j, OnCallReplaceRequestParam onCallReplaceRequestParam, String str);

    public static final native int OnCallReplaceRequestParam_statusCode_get(long j, OnCallReplaceRequestParam onCallReplaceRequestParam);

    public static final native void OnCallReplaceRequestParam_statusCode_set(long j, OnCallReplaceRequestParam onCallReplaceRequestParam, int i);

    public static final native int OnCallReplacedParam_newCallId_get(long j, OnCallReplacedParam onCallReplacedParam);

    public static final native void OnCallReplacedParam_newCallId_set(long j, OnCallReplacedParam onCallReplacedParam, int i);

    public static final native long OnCallRxOfferParam_offer_get(long j, OnCallRxOfferParam onCallRxOfferParam);

    public static final native void OnCallRxOfferParam_offer_set(long j, OnCallRxOfferParam onCallRxOfferParam, long j2, SdpSession sdpSession);

    public static final native long OnCallRxOfferParam_opt_get(long j, OnCallRxOfferParam onCallRxOfferParam);

    public static final native void OnCallRxOfferParam_opt_set(long j, OnCallRxOfferParam onCallRxOfferParam, long j2, CallSetting callSetting);

    public static final native int OnCallRxOfferParam_statusCode_get(long j, OnCallRxOfferParam onCallRxOfferParam);

    public static final native void OnCallRxOfferParam_statusCode_set(long j, OnCallRxOfferParam onCallRxOfferParam, int i);

    public static final native long OnCallSdpCreatedParam_remSdp_get(long j, OnCallSdpCreatedParam onCallSdpCreatedParam);

    public static final native void OnCallSdpCreatedParam_remSdp_set(long j, OnCallSdpCreatedParam onCallSdpCreatedParam, long j2, SdpSession sdpSession);

    public static final native long OnCallSdpCreatedParam_sdp_get(long j, OnCallSdpCreatedParam onCallSdpCreatedParam);

    public static final native void OnCallSdpCreatedParam_sdp_set(long j, OnCallSdpCreatedParam onCallSdpCreatedParam, long j2, SdpSession sdpSession);

    public static final native long OnCallStateParam_e_get(long j, OnCallStateParam onCallStateParam);

    public static final native void OnCallStateParam_e_set(long j, OnCallStateParam onCallStateParam, long j2, SipEvent sipEvent);

    public static final native String OnCallTransferRequestParam_dstUri_get(long j, OnCallTransferRequestParam onCallTransferRequestParam);

    public static final native void OnCallTransferRequestParam_dstUri_set(long j, OnCallTransferRequestParam onCallTransferRequestParam, String str);

    public static final native long OnCallTransferRequestParam_opt_get(long j, OnCallTransferRequestParam onCallTransferRequestParam);

    public static final native void OnCallTransferRequestParam_opt_set(long j, OnCallTransferRequestParam onCallTransferRequestParam, long j2, CallSetting callSetting);

    public static final native int OnCallTransferRequestParam_statusCode_get(long j, OnCallTransferRequestParam onCallTransferRequestParam);

    public static final native void OnCallTransferRequestParam_statusCode_set(long j, OnCallTransferRequestParam onCallTransferRequestParam, int i);

    public static final native boolean OnCallTransferStatusParam_cont_get(long j, OnCallTransferStatusParam onCallTransferStatusParam);

    public static final native void OnCallTransferStatusParam_cont_set(long j, OnCallTransferStatusParam onCallTransferStatusParam, boolean z);

    public static final native boolean OnCallTransferStatusParam_finalNotify_get(long j, OnCallTransferStatusParam onCallTransferStatusParam);

    public static final native void OnCallTransferStatusParam_finalNotify_set(long j, OnCallTransferStatusParam onCallTransferStatusParam, boolean z);

    public static final native String OnCallTransferStatusParam_reason_get(long j, OnCallTransferStatusParam onCallTransferStatusParam);

    public static final native void OnCallTransferStatusParam_reason_set(long j, OnCallTransferStatusParam onCallTransferStatusParam, String str);

    public static final native int OnCallTransferStatusParam_statusCode_get(long j, OnCallTransferStatusParam onCallTransferStatusParam);

    public static final native void OnCallTransferStatusParam_statusCode_set(long j, OnCallTransferStatusParam onCallTransferStatusParam, int i);

    public static final native long OnCallTsxStateParam_e_get(long j, OnCallTsxStateParam onCallTsxStateParam);

    public static final native void OnCallTsxStateParam_e_set(long j, OnCallTsxStateParam onCallTsxStateParam, long j2, SipEvent sipEvent);

    public static final native long OnCreateMediaTransportParam_flags_get(long j, OnCreateMediaTransportParam onCreateMediaTransportParam);

    public static final native void OnCreateMediaTransportParam_flags_set(long j, OnCreateMediaTransportParam onCreateMediaTransportParam, long j2);

    public static final native long OnCreateMediaTransportParam_mediaIdx_get(long j, OnCreateMediaTransportParam onCreateMediaTransportParam);

    public static final native void OnCreateMediaTransportParam_mediaIdx_set(long j, OnCreateMediaTransportParam onCreateMediaTransportParam, long j2);

    public static final native long OnCreateMediaTransportParam_mediaTp_get(long j, OnCreateMediaTransportParam onCreateMediaTransportParam);

    public static final native void OnCreateMediaTransportParam_mediaTp_set(long j, OnCreateMediaTransportParam onCreateMediaTransportParam, long j2);

    public static final native String OnDtmfDigitParam_digit_get(long j, OnDtmfDigitParam onDtmfDigitParam);

    public static final native void OnDtmfDigitParam_digit_set(long j, OnDtmfDigitParam onDtmfDigitParam, String str);

    public static final native int OnIncomingCallParam_callId_get(long j, OnIncomingCallParam onIncomingCallParam);

    public static final native void OnIncomingCallParam_callId_set(long j, OnIncomingCallParam onIncomingCallParam, int i);

    public static final native long OnIncomingCallParam_rdata_get(long j, OnIncomingCallParam onIncomingCallParam);

    public static final native void OnIncomingCallParam_rdata_set(long j, OnIncomingCallParam onIncomingCallParam, long j2, SipRxData sipRxData);

    public static final native int OnIncomingSubscribeParam_code_get(long j, OnIncomingSubscribeParam onIncomingSubscribeParam);

    public static final native void OnIncomingSubscribeParam_code_set(long j, OnIncomingSubscribeParam onIncomingSubscribeParam, int i);

    public static final native String OnIncomingSubscribeParam_fromUri_get(long j, OnIncomingSubscribeParam onIncomingSubscribeParam);

    public static final native void OnIncomingSubscribeParam_fromUri_set(long j, OnIncomingSubscribeParam onIncomingSubscribeParam, String str);

    public static final native long OnIncomingSubscribeParam_rdata_get(long j, OnIncomingSubscribeParam onIncomingSubscribeParam);

    public static final native void OnIncomingSubscribeParam_rdata_set(long j, OnIncomingSubscribeParam onIncomingSubscribeParam, long j2, SipRxData sipRxData);

    public static final native String OnIncomingSubscribeParam_reason_get(long j, OnIncomingSubscribeParam onIncomingSubscribeParam);

    public static final native void OnIncomingSubscribeParam_reason_set(long j, OnIncomingSubscribeParam onIncomingSubscribeParam, String str);

    public static final native long OnIncomingSubscribeParam_srvPres_get(long j, OnIncomingSubscribeParam onIncomingSubscribeParam);

    public static final native void OnIncomingSubscribeParam_srvPres_set(long j, OnIncomingSubscribeParam onIncomingSubscribeParam, long j2);

    public static final native long OnIncomingSubscribeParam_txOption_get(long j, OnIncomingSubscribeParam onIncomingSubscribeParam);

    public static final native void OnIncomingSubscribeParam_txOption_set(long j, OnIncomingSubscribeParam onIncomingSubscribeParam, long j2, SipTxOption sipTxOption);

    public static final native String OnInstantMessageParam_contactUri_get(long j, OnInstantMessageParam onInstantMessageParam);

    public static final native void OnInstantMessageParam_contactUri_set(long j, OnInstantMessageParam onInstantMessageParam, String str);

    public static final native String OnInstantMessageParam_contentType_get(long j, OnInstantMessageParam onInstantMessageParam);

    public static final native void OnInstantMessageParam_contentType_set(long j, OnInstantMessageParam onInstantMessageParam, String str);

    public static final native String OnInstantMessageParam_fromUri_get(long j, OnInstantMessageParam onInstantMessageParam);

    public static final native void OnInstantMessageParam_fromUri_set(long j, OnInstantMessageParam onInstantMessageParam, String str);

    public static final native String OnInstantMessageParam_msgBody_get(long j, OnInstantMessageParam onInstantMessageParam);

    public static final native void OnInstantMessageParam_msgBody_set(long j, OnInstantMessageParam onInstantMessageParam, String str);

    public static final native long OnInstantMessageParam_rdata_get(long j, OnInstantMessageParam onInstantMessageParam);

    public static final native void OnInstantMessageParam_rdata_set(long j, OnInstantMessageParam onInstantMessageParam, long j2, SipRxData sipRxData);

    public static final native String OnInstantMessageParam_toUri_get(long j, OnInstantMessageParam onInstantMessageParam);

    public static final native void OnInstantMessageParam_toUri_set(long j, OnInstantMessageParam onInstantMessageParam, String str);

    public static final native int OnInstantMessageStatusParam_code_get(long j, OnInstantMessageStatusParam onInstantMessageStatusParam);

    public static final native void OnInstantMessageStatusParam_code_set(long j, OnInstantMessageStatusParam onInstantMessageStatusParam, int i);

    public static final native String OnInstantMessageStatusParam_msgBody_get(long j, OnInstantMessageStatusParam onInstantMessageStatusParam);

    public static final native void OnInstantMessageStatusParam_msgBody_set(long j, OnInstantMessageStatusParam onInstantMessageStatusParam, String str);

    public static final native long OnInstantMessageStatusParam_rdata_get(long j, OnInstantMessageStatusParam onInstantMessageStatusParam);

    public static final native void OnInstantMessageStatusParam_rdata_set(long j, OnInstantMessageStatusParam onInstantMessageStatusParam, long j2, SipRxData sipRxData);

    public static final native String OnInstantMessageStatusParam_reason_get(long j, OnInstantMessageStatusParam onInstantMessageStatusParam);

    public static final native void OnInstantMessageStatusParam_reason_set(long j, OnInstantMessageStatusParam onInstantMessageStatusParam, String str);

    public static final native String OnInstantMessageStatusParam_toUri_get(long j, OnInstantMessageStatusParam onInstantMessageStatusParam);

    public static final native void OnInstantMessageStatusParam_toUri_set(long j, OnInstantMessageStatusParam onInstantMessageStatusParam, String str);

    public static final native long OnInstantMessageStatusParam_userData_get(long j, OnInstantMessageStatusParam onInstantMessageStatusParam);

    public static final native void OnInstantMessageStatusParam_userData_set(long j, OnInstantMessageStatusParam onInstantMessageStatusParam, long j2);

    public static final native long OnMwiInfoParam_rdata_get(long j, OnMwiInfoParam onMwiInfoParam);

    public static final native void OnMwiInfoParam_rdata_set(long j, OnMwiInfoParam onMwiInfoParam, long j2, SipRxData sipRxData);

    public static final native int OnMwiInfoParam_state_get(long j, OnMwiInfoParam onMwiInfoParam);

    public static final native void OnMwiInfoParam_state_set(long j, OnMwiInfoParam onMwiInfoParam, int i);

    public static final native String OnNatCheckStunServersCompleteParam_addr_get(long j, OnNatCheckStunServersCompleteParam onNatCheckStunServersCompleteParam);

    public static final native void OnNatCheckStunServersCompleteParam_addr_set(long j, OnNatCheckStunServersCompleteParam onNatCheckStunServersCompleteParam, String str);

    public static final native String OnNatCheckStunServersCompleteParam_name_get(long j, OnNatCheckStunServersCompleteParam onNatCheckStunServersCompleteParam);

    public static final native void OnNatCheckStunServersCompleteParam_name_set(long j, OnNatCheckStunServersCompleteParam onNatCheckStunServersCompleteParam, String str);

    public static final native int OnNatCheckStunServersCompleteParam_status_get(long j, OnNatCheckStunServersCompleteParam onNatCheckStunServersCompleteParam);

    public static final native void OnNatCheckStunServersCompleteParam_status_set(long j, OnNatCheckStunServersCompleteParam onNatCheckStunServersCompleteParam, int i);

    public static final native long OnNatCheckStunServersCompleteParam_userData_get(long j, OnNatCheckStunServersCompleteParam onNatCheckStunServersCompleteParam);

    public static final native void OnNatCheckStunServersCompleteParam_userData_set(long j, OnNatCheckStunServersCompleteParam onNatCheckStunServersCompleteParam, long j2);

    public static final native String OnNatDetectionCompleteParam_natTypeName_get(long j, OnNatDetectionCompleteParam onNatDetectionCompleteParam);

    public static final native void OnNatDetectionCompleteParam_natTypeName_set(long j, OnNatDetectionCompleteParam onNatDetectionCompleteParam, String str);

    public static final native int OnNatDetectionCompleteParam_natType_get(long j, OnNatDetectionCompleteParam onNatDetectionCompleteParam);

    public static final native void OnNatDetectionCompleteParam_natType_set(long j, OnNatDetectionCompleteParam onNatDetectionCompleteParam, int i);

    public static final native String OnNatDetectionCompleteParam_reason_get(long j, OnNatDetectionCompleteParam onNatDetectionCompleteParam);

    public static final native void OnNatDetectionCompleteParam_reason_set(long j, OnNatDetectionCompleteParam onNatDetectionCompleteParam, String str);

    public static final native int OnNatDetectionCompleteParam_status_get(long j, OnNatDetectionCompleteParam onNatDetectionCompleteParam);

    public static final native void OnNatDetectionCompleteParam_status_set(long j, OnNatDetectionCompleteParam onNatDetectionCompleteParam, int i);

    public static final native boolean OnRegStartedParam_renew_get(long j, OnRegStartedParam onRegStartedParam);

    public static final native void OnRegStartedParam_renew_set(long j, OnRegStartedParam onRegStartedParam, boolean z);

    public static final native int OnRegStateParam_code_get(long j, OnRegStateParam onRegStateParam);

    public static final native void OnRegStateParam_code_set(long j, OnRegStateParam onRegStateParam, int i);

    public static final native int OnRegStateParam_expiration_get(long j, OnRegStateParam onRegStateParam);

    public static final native void OnRegStateParam_expiration_set(long j, OnRegStateParam onRegStateParam, int i);

    public static final native long OnRegStateParam_rdata_get(long j, OnRegStateParam onRegStateParam);

    public static final native void OnRegStateParam_rdata_set(long j, OnRegStateParam onRegStateParam, long j2, SipRxData sipRxData);

    public static final native String OnRegStateParam_reason_get(long j, OnRegStateParam onRegStateParam);

    public static final native void OnRegStateParam_reason_set(long j, OnRegStateParam onRegStateParam, String str);

    public static final native int OnRegStateParam_status_get(long j, OnRegStateParam onRegStateParam);

    public static final native void OnRegStateParam_status_set(long j, OnRegStateParam onRegStateParam, int i);

    public static final native int OnSelectAccountParam_accountIndex_get(long j, OnSelectAccountParam onSelectAccountParam);

    public static final native void OnSelectAccountParam_accountIndex_set(long j, OnSelectAccountParam onSelectAccountParam, int i);

    public static final native long OnSelectAccountParam_rdata_get(long j, OnSelectAccountParam onSelectAccountParam);

    public static final native void OnSelectAccountParam_rdata_set(long j, OnSelectAccountParam onSelectAccountParam, long j2, SipRxData sipRxData);

    public static final native long OnStreamCreatedParam_pPort_get(long j, OnStreamCreatedParam onStreamCreatedParam);

    public static final native void OnStreamCreatedParam_pPort_set(long j, OnStreamCreatedParam onStreamCreatedParam, long j2);

    public static final native long OnStreamCreatedParam_streamIdx_get(long j, OnStreamCreatedParam onStreamCreatedParam);

    public static final native void OnStreamCreatedParam_streamIdx_set(long j, OnStreamCreatedParam onStreamCreatedParam, long j2);

    public static final native long OnStreamCreatedParam_stream_get(long j, OnStreamCreatedParam onStreamCreatedParam);

    public static final native void OnStreamCreatedParam_stream_set(long j, OnStreamCreatedParam onStreamCreatedParam, long j2);

    public static final native long OnStreamDestroyedParam_streamIdx_get(long j, OnStreamDestroyedParam onStreamDestroyedParam);

    public static final native void OnStreamDestroyedParam_streamIdx_set(long j, OnStreamDestroyedParam onStreamDestroyedParam, long j2);

    public static final native long OnStreamDestroyedParam_stream_get(long j, OnStreamDestroyedParam onStreamDestroyedParam);

    public static final native void OnStreamDestroyedParam_stream_set(long j, OnStreamDestroyedParam onStreamDestroyedParam, long j2);

    public static final native long OnTimerParam_msecDelay_get(long j, OnTimerParam onTimerParam);

    public static final native void OnTimerParam_msecDelay_set(long j, OnTimerParam onTimerParam, long j2);

    public static final native long OnTimerParam_userData_get(long j, OnTimerParam onTimerParam);

    public static final native void OnTimerParam_userData_set(long j, OnTimerParam onTimerParam, long j2);

    public static final native long OnTransportStateParam_hnd_get(long j, OnTransportStateParam onTransportStateParam);

    public static final native void OnTransportStateParam_hnd_set(long j, OnTransportStateParam onTransportStateParam, long j2);

    public static final native int OnTransportStateParam_lastError_get(long j, OnTransportStateParam onTransportStateParam);

    public static final native void OnTransportStateParam_lastError_set(long j, OnTransportStateParam onTransportStateParam, int i);

    public static final native int OnTransportStateParam_state_get(long j, OnTransportStateParam onTransportStateParam);

    public static final native void OnTransportStateParam_state_set(long j, OnTransportStateParam onTransportStateParam, int i);

    public static final native String OnTypingIndicationParam_contactUri_get(long j, OnTypingIndicationParam onTypingIndicationParam);

    public static final native void OnTypingIndicationParam_contactUri_set(long j, OnTypingIndicationParam onTypingIndicationParam, String str);

    public static final native String OnTypingIndicationParam_fromUri_get(long j, OnTypingIndicationParam onTypingIndicationParam);

    public static final native void OnTypingIndicationParam_fromUri_set(long j, OnTypingIndicationParam onTypingIndicationParam, String str);

    public static final native boolean OnTypingIndicationParam_isTyping_get(long j, OnTypingIndicationParam onTypingIndicationParam);

    public static final native void OnTypingIndicationParam_isTyping_set(long j, OnTypingIndicationParam onTypingIndicationParam, boolean z);

    public static final native long OnTypingIndicationParam_rdata_get(long j, OnTypingIndicationParam onTypingIndicationParam);

    public static final native void OnTypingIndicationParam_rdata_set(long j, OnTypingIndicationParam onTypingIndicationParam, long j2, SipRxData sipRxData);

    public static final native String OnTypingIndicationParam_toUri_get(long j, OnTypingIndicationParam onTypingIndicationParam);

    public static final native void OnTypingIndicationParam_toUri_set(long j, OnTypingIndicationParam onTypingIndicationParam, String str);

    public static final native int PJMEDIA_AUD_DEV_CAP_CNG_get();

    public static final native int PJMEDIA_AUD_DEV_CAP_EC_TAIL_get();

    public static final native int PJMEDIA_AUD_DEV_CAP_EC_get();

    public static final native int PJMEDIA_AUD_DEV_CAP_EXT_FORMAT_get();

    public static final native int PJMEDIA_AUD_DEV_CAP_INPUT_LATENCY_get();

    public static final native int PJMEDIA_AUD_DEV_CAP_INPUT_ROUTE_get();

    public static final native int PJMEDIA_AUD_DEV_CAP_INPUT_SIGNAL_METER_get();

    public static final native int PJMEDIA_AUD_DEV_CAP_INPUT_VOLUME_SETTING_get();

    public static final native int PJMEDIA_AUD_DEV_CAP_MAX_get();

    public static final native int PJMEDIA_AUD_DEV_CAP_OUTPUT_LATENCY_get();

    public static final native int PJMEDIA_AUD_DEV_CAP_OUTPUT_ROUTE_get();

    public static final native int PJMEDIA_AUD_DEV_CAP_OUTPUT_SIGNAL_METER_get();

    public static final native int PJMEDIA_AUD_DEV_CAP_OUTPUT_VOLUME_SETTING_get();

    public static final native int PJMEDIA_AUD_DEV_CAP_PLC_get();

    public static final native int PJMEDIA_AUD_DEV_CAP_VAD_get();

    public static final native int PJMEDIA_AUD_DEV_ROUTE_BLUETOOTH_get();

    public static final native int PJMEDIA_AUD_DEV_ROUTE_DEFAULT_get();

    public static final native int PJMEDIA_AUD_DEV_ROUTE_EARPIECE_get();

    public static final native int PJMEDIA_AUD_DEV_ROUTE_LOUDSPEAKER_get();

    public static final native int PJMEDIA_DIR_CAPTURE_PLAYBACK_get();

    public static final native int PJMEDIA_DIR_CAPTURE_RENDER_get();

    public static final native int PJMEDIA_DIR_CAPTURE_get();

    public static final native int PJMEDIA_DIR_DECODING_get();

    public static final native int PJMEDIA_DIR_ENCODING_DECODING_get();

    public static final native int PJMEDIA_DIR_ENCODING_get();

    public static final native int PJMEDIA_DIR_NONE_get();

    public static final native int PJMEDIA_DIR_PLAYBACK_get();

    public static final native int PJMEDIA_DIR_RENDER_get();

    public static final native int PJMEDIA_EVENT_FMT_CHANGED_get();

    public static final native int PJMEDIA_EVENT_KEYFRAME_FOUND_get();

    public static final native int PJMEDIA_EVENT_KEYFRAME_MISSING_get();

    public static final native int PJMEDIA_EVENT_MOUSE_BTN_DOWN_get();

    public static final native int PJMEDIA_EVENT_ORIENT_CHANGED_get();

    public static final native int PJMEDIA_EVENT_WND_CLOSED_get();

    public static final native int PJMEDIA_EVENT_WND_CLOSING_get();

    public static final native int PJMEDIA_EVENT_WND_RESIZED_get();

    public static final native int PJMEDIA_FILE_NO_LOOP_get();

    public static final native int PJMEDIA_FILE_WRITE_ALAW_get();

    public static final native int PJMEDIA_FILE_WRITE_PCM_get();

    public static final native int PJMEDIA_FILE_WRITE_ULAW_get();

    public static final native int PJMEDIA_FORMAT_ALAW_get();

    public static final native int PJMEDIA_FORMAT_AMR_get();

    public static final native int PJMEDIA_FORMAT_AYUV_get();

    public static final native int PJMEDIA_FORMAT_BGRA_get();

    public static final native int PJMEDIA_FORMAT_DIB_get();

    public static final native int PJMEDIA_FORMAT_G729_get();

    public static final native int PJMEDIA_FORMAT_GBRP_get();

    public static final native int PJMEDIA_FORMAT_H261_get();

    public static final native int PJMEDIA_FORMAT_H263P_get();

    public static final native int PJMEDIA_FORMAT_H263_get();

    public static final native int PJMEDIA_FORMAT_H264_get();

    public static final native int PJMEDIA_FORMAT_I420JPEG_get();

    public static final native int PJMEDIA_FORMAT_I420_get();

    public static final native int PJMEDIA_FORMAT_I422JPEG_get();

    public static final native int PJMEDIA_FORMAT_I422_get();

    public static final native int PJMEDIA_FORMAT_ILBC_get();

    public static final native int PJMEDIA_FORMAT_IYUV_get();

    public static final native int PJMEDIA_FORMAT_L16_get();

    public static final native int PJMEDIA_FORMAT_MJPEG_get();

    public static final native int PJMEDIA_FORMAT_MPEG1VIDEO_get();

    public static final native int PJMEDIA_FORMAT_MPEG2VIDEO_get();

    public static final native int PJMEDIA_FORMAT_MPEG4_get();

    public static final native int PJMEDIA_FORMAT_PCMA_get();

    public static final native int PJMEDIA_FORMAT_PCMU_get();

    public static final native int PJMEDIA_FORMAT_PCM_get();

    public static final native int PJMEDIA_FORMAT_RGB24_get();

    public static final native int PJMEDIA_FORMAT_RGB32_get();

    public static final native int PJMEDIA_FORMAT_RGBA_get();

    public static final native int PJMEDIA_FORMAT_ULAW_get();

    public static final native int PJMEDIA_FORMAT_UYVY_get();

    public static final native int PJMEDIA_FORMAT_YUY2_get();

    public static final native int PJMEDIA_FORMAT_YV12_get();

    public static final native int PJMEDIA_FORMAT_YVYU_get();

    public static final native int PJMEDIA_TP_PROTO_NONE_get();

    public static final native int PJMEDIA_VID_DEFAULT_CAPTURE_DEV_get();

    public static final native int PJMEDIA_VID_DEFAULT_RENDER_DEV_get();

    public static final native int PJMEDIA_VID_INVALID_DEV_get();

    public static final native int PJMEDIA_VID_STREAM_RC_NONE_get();

    public static final native int PJMEDIA_VID_STREAM_RC_SIMPLE_BLOCKING_get();

    public static final native int PJSIP_AC_AMBIGUOUS_get();

    public static final native int PJSIP_CRED_DATA_DIGEST_get();

    public static final native int PJSIP_CRED_DATA_EXT_AKA_get();

    public static final native int PJSIP_CRED_DATA_PLAIN_PASSWD_get();

    public static final native int PJSIP_DIALOG_CAP_SUPPORTED_get();

    public static final native int PJSIP_DIALOG_CAP_UNKNOWN_get();

    public static final native int PJSIP_DIALOG_CAP_UNSUPPORTED_get();

    public static final native int PJSIP_SC_ACCEPTED_get();

    public static final native int PJSIP_SC_ADDRESS_INCOMPLETE_get();

    public static final native int PJSIP_SC_ALTERNATIVE_SERVICE_get();

    public static final native int PJSIP_SC_BAD_EVENT_get();

    public static final native int PJSIP_SC_BAD_EXTENSION_get();

    public static final native int PJSIP_SC_BAD_GATEWAY_get();

    public static final native int PJSIP_SC_BAD_REQUEST_get();

    public static final native int PJSIP_SC_BUSY_EVERYWHERE_get();

    public static final native int PJSIP_SC_BUSY_HERE_get();

    public static final native int PJSIP_SC_CALL_BEING_FORWARDED_get();

    public static final native int PJSIP_SC_CALL_TSX_DOES_NOT_EXIST_get();

    public static final native int PJSIP_SC_DECLINE_get();

    public static final native int PJSIP_SC_DOES_NOT_EXIST_ANYWHERE_get();

    public static final native int PJSIP_SC_EXTENSION_REQUIRED_get();

    public static final native int PJSIP_SC_FORBIDDEN_get();

    public static final native int PJSIP_SC_GONE_get();

    public static final native int PJSIP_SC_INTERNAL_SERVER_ERROR_get();

    public static final native int PJSIP_SC_INTERVAL_TOO_BRIEF_get();

    public static final native int PJSIP_SC_LOOP_DETECTED_get();

    public static final native int PJSIP_SC_MESSAGE_TOO_LARGE_get();

    public static final native int PJSIP_SC_METHOD_NOT_ALLOWED_get();

    public static final native int PJSIP_SC_MOVED_PERMANENTLY_get();

    public static final native int PJSIP_SC_MOVED_TEMPORARILY_get();

    public static final native int PJSIP_SC_MULTIPLE_CHOICES_get();

    public static final native int PJSIP_SC_NOT_ACCEPTABLE_ANYWHERE_get();

    public static final native int PJSIP_SC_NOT_ACCEPTABLE_HERE_get();

    public static final native int PJSIP_SC_NOT_ACCEPTABLE_get();

    public static final native int PJSIP_SC_NOT_FOUND_get();

    public static final native int PJSIP_SC_NOT_IMPLEMENTED_get();

    public static final native int PJSIP_SC_OK_get();

    public static final native int PJSIP_SC_PAYMENT_REQUIRED_get();

    public static final native int PJSIP_SC_PRECONDITION_FAILURE_get();

    public static final native int PJSIP_SC_PROGRESS_get();

    public static final native int PJSIP_SC_PROXY_AUTHENTICATION_REQUIRED_get();

    public static final native int PJSIP_SC_QUEUED_get();

    public static final native int PJSIP_SC_REQUEST_ENTITY_TOO_LARGE_get();

    public static final native int PJSIP_SC_REQUEST_PENDING_get();

    public static final native int PJSIP_SC_REQUEST_TERMINATED_get();

    public static final native int PJSIP_SC_REQUEST_TIMEOUT_get();

    public static final native int PJSIP_SC_REQUEST_UPDATED_get();

    public static final native int PJSIP_SC_REQUEST_URI_TOO_LONG_get();

    public static final native int PJSIP_SC_RINGING_get();

    public static final native int PJSIP_SC_SERVER_TIMEOUT_get();

    public static final native int PJSIP_SC_SERVICE_UNAVAILABLE_get();

    public static final native int PJSIP_SC_SESSION_TIMER_TOO_SMALL_get();

    public static final native int PJSIP_SC_TEMPORARILY_UNAVAILABLE_get();

    public static final native int PJSIP_SC_TOO_MANY_HOPS_get();

    public static final native int PJSIP_SC_TRYING_get();

    public static final native int PJSIP_SC_TSX_TIMEOUT_get();

    public static final native int PJSIP_SC_TSX_TRANSPORT_ERROR_get();

    public static final native int PJSIP_SC_UNAUTHORIZED_get();

    public static final native int PJSIP_SC_UNDECIPHERABLE_get();

    public static final native int PJSIP_SC_UNSUPPORTED_MEDIA_TYPE_get();

    public static final native int PJSIP_SC_UNSUPPORTED_URI_SCHEME_get();

    public static final native int PJSIP_SC_USE_PROXY_get();

    public static final native int PJSIP_SC_VERSION_NOT_SUPPORTED_get();

    public static final native int PJSIP_SC__force_32bit_get();

    public static final native int PJSIP_SSLV23_METHOD_get();

    public static final native int PJSIP_SSLV2_METHOD_get();

    public static final native int PJSIP_SSLV3_METHOD_get();

    public static final native int PJSIP_SSL_UNSPECIFIED_METHOD_get();

    public static final native int PJSIP_TLSV1_METHOD_get();

    public static final native int PJSIP_TRANSPORT_DATAGRAM_get();

    public static final native int PJSIP_TRANSPORT_IPV6_get();

    public static final native int PJSIP_TRANSPORT_RELIABLE_get();

    public static final native int PJSIP_TRANSPORT_SECURE_get();

    public static final native int PJSIP_TRANSPORT_TCP6_get();

    public static final native int PJSIP_TRANSPORT_TLS6_get();

    public static final native int PJSIP_TRANSPORT_UDP6_get();

    public static final native int PJSIP_UAC_ROLE_get();

    public static final native int PJSIP_UAS_ROLE_get();

    public static final native int PJSUA_CALL_INCLUDE_DISABLED_MEDIA_get();

    public static final native int PJSUA_CALL_UNHOLD_get();

    public static final native int PJSUA_CALL_UPDATE_CONTACT_get();

    public static final native int PJSUA_DESTROY_NO_NETWORK_get();

    public static final native int PJSUA_DESTROY_NO_RX_MSG_get();

    public static final native int PJSUA_DESTROY_NO_TX_MSG_get();

    public static final native int PJSUA_INVALID_ID_get();

    public static final native int PJSUA_MED_TP_CLOSE_MEMBER_get();

    public static final native int PJSUA_VID_REQ_KEYFRAME_RTCP_PLI_get();

    public static final native int PJSUA_VID_REQ_KEYFRAME_SIP_INFO_get();

    public static final native int PJ_FALSE_get();

    public static final native int PJ_LOG_HAS_COLOR_get();

    public static final native int PJ_LOG_HAS_CR_get();

    public static final native int PJ_LOG_HAS_DAY_NAME_get();

    public static final native int PJ_LOG_HAS_DAY_OF_MON_get();

    public static final native int PJ_LOG_HAS_INDENT_get();

    public static final native int PJ_LOG_HAS_LEVEL_TEXT_get();

    public static final native int PJ_LOG_HAS_MICRO_SEC_get();

    public static final native int PJ_LOG_HAS_MONTH_get();

    public static final native int PJ_LOG_HAS_NEWLINE_get();

    public static final native int PJ_LOG_HAS_SENDER_get();

    public static final native int PJ_LOG_HAS_SPACE_get();

    public static final native int PJ_LOG_HAS_THREAD_ID_get();

    public static final native int PJ_LOG_HAS_THREAD_SWC_get();

    public static final native int PJ_LOG_HAS_TIME_get();

    public static final native int PJ_LOG_HAS_YEAR_get();

    public static final native int PJ_O_APPEND_get();

    public static final native int PJ_O_RDONLY_get();

    public static final native int PJ_O_RDWR_get();

    public static final native int PJ_O_WRONLY_get();

    public static final native int PJ_QOS_PARAM_HAS_DSCP_get();

    public static final native int PJ_QOS_PARAM_HAS_SO_PRIO_get();

    public static final native int PJ_QOS_PARAM_HAS_WMM_get();

    public static final native int PJ_SSL_CK_DES_192_EDE3_CBC_WITH_MD5_get();

    public static final native int PJ_SSL_CK_DES_64_CBC_WITH_MD5_get();

    public static final native int PJ_SSL_CK_IDEA_128_CBC_WITH_MD5_get();

    public static final native int PJ_SSL_CK_RC2_128_CBC_EXPORT40_WITH_MD5_get();

    public static final native int PJ_SSL_CK_RC2_128_CBC_WITH_MD5_get();

    public static final native int PJ_SSL_CK_RC4_128_EXPORT40_WITH_MD5_get();

    public static final native int PJ_SSL_CK_RC4_128_WITH_MD5_get();

    public static final native int PJ_SSL_FORTEZZA_KEA_WITH_FORTEZZA_CBC_SHA_get();

    public static final native int PJ_SSL_FORTEZZA_KEA_WITH_NULL_SHA_get();

    public static final native int PJ_SSL_FORTEZZA_KEA_WITH_RC4_128_SHA_get();

    public static final native int PJ_SUCCESS_get();

    public static final native int PJ_TLS_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA_get();

    public static final native int PJ_TLS_DHE_DSS_WITH_3DES_EDE_CBC_SHA_get();

    public static final native int PJ_TLS_DHE_DSS_WITH_AES_128_CBC_SHA256_get();

    public static final native int PJ_TLS_DHE_DSS_WITH_AES_128_CBC_SHA_get();

    public static final native int PJ_TLS_DHE_DSS_WITH_AES_256_CBC_SHA256_get();

    public static final native int PJ_TLS_DHE_DSS_WITH_AES_256_CBC_SHA_get();

    public static final native int PJ_TLS_DHE_DSS_WITH_DES_CBC_SHA_get();

    public static final native int PJ_TLS_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA_get();

    public static final native int PJ_TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA_get();

    public static final native int PJ_TLS_DHE_RSA_WITH_AES_128_CBC_SHA256_get();

    public static final native int PJ_TLS_DHE_RSA_WITH_AES_128_CBC_SHA_get();

    public static final native int PJ_TLS_DHE_RSA_WITH_AES_256_CBC_SHA256_get();

    public static final native int PJ_TLS_DHE_RSA_WITH_AES_256_CBC_SHA_get();

    public static final native int PJ_TLS_DHE_RSA_WITH_DES_CBC_SHA_get();

    public static final native int PJ_TLS_DH_DSS_EXPORT_WITH_DES40_CBC_SHA_get();

    public static final native int PJ_TLS_DH_DSS_WITH_3DES_EDE_CBC_SHA_get();

    public static final native int PJ_TLS_DH_DSS_WITH_AES_128_CBC_SHA256_get();

    public static final native int PJ_TLS_DH_DSS_WITH_AES_128_CBC_SHA_get();

    public static final native int PJ_TLS_DH_DSS_WITH_AES_256_CBC_SHA256_get();

    public static final native int PJ_TLS_DH_DSS_WITH_AES_256_CBC_SHA_get();

    public static final native int PJ_TLS_DH_DSS_WITH_DES_CBC_SHA_get();

    public static final native int PJ_TLS_DH_RSA_EXPORT_WITH_DES40_CBC_SHA_get();

    public static final native int PJ_TLS_DH_RSA_WITH_3DES_EDE_CBC_SHA_get();

    public static final native int PJ_TLS_DH_RSA_WITH_AES_128_CBC_SHA256_get();

    public static final native int PJ_TLS_DH_RSA_WITH_AES_128_CBC_SHA_get();

    public static final native int PJ_TLS_DH_RSA_WITH_AES_256_CBC_SHA256_get();

    public static final native int PJ_TLS_DH_RSA_WITH_AES_256_CBC_SHA_get();

    public static final native int PJ_TLS_DH_RSA_WITH_DES_CBC_SHA_get();

    public static final native int PJ_TLS_DH_anon_EXPORT_WITH_DES40_CBC_SHA_get();

    public static final native int PJ_TLS_DH_anon_EXPORT_WITH_RC4_40_MD5_get();

    public static final native int PJ_TLS_DH_anon_WITH_3DES_EDE_CBC_SHA_get();

    public static final native int PJ_TLS_DH_anon_WITH_AES_128_CBC_SHA256_get();

    public static final native int PJ_TLS_DH_anon_WITH_AES_128_CBC_SHA_get();

    public static final native int PJ_TLS_DH_anon_WITH_AES_256_CBC_SHA256_get();

    public static final native int PJ_TLS_DH_anon_WITH_AES_256_CBC_SHA_get();

    public static final native int PJ_TLS_DH_anon_WITH_DES_CBC_SHA_get();

    public static final native int PJ_TLS_DH_anon_WITH_RC4_128_MD5_get();

    public static final native int PJ_TLS_NULL_WITH_NULL_NULL_get();

    public static final native int PJ_TLS_RSA_EXPORT_WITH_DES40_CBC_SHA_get();

    public static final native int PJ_TLS_RSA_EXPORT_WITH_RC2_CBC_40_MD5_get();

    public static final native int PJ_TLS_RSA_EXPORT_WITH_RC4_40_MD5_get();

    public static final native int PJ_TLS_RSA_WITH_3DES_EDE_CBC_SHA_get();

    public static final native int PJ_TLS_RSA_WITH_AES_128_CBC_SHA256_get();

    public static final native int PJ_TLS_RSA_WITH_AES_128_CBC_SHA_get();

    public static final native int PJ_TLS_RSA_WITH_AES_256_CBC_SHA256_get();

    public static final native int PJ_TLS_RSA_WITH_AES_256_CBC_SHA_get();

    public static final native int PJ_TLS_RSA_WITH_DES_CBC_SHA_get();

    public static final native int PJ_TLS_RSA_WITH_IDEA_CBC_SHA_get();

    public static final native int PJ_TLS_RSA_WITH_NULL_MD5_get();

    public static final native int PJ_TLS_RSA_WITH_NULL_SHA256_get();

    public static final native int PJ_TLS_RSA_WITH_NULL_SHA_get();

    public static final native int PJ_TLS_RSA_WITH_RC4_128_MD5_get();

    public static final native int PJ_TLS_RSA_WITH_RC4_128_SHA_get();

    public static final native int PJ_TLS_UNKNOWN_CIPHER_get();

    public static final native int PJ_TRUE_get();

    public static final native int PJ_TURN_TP_TCP_get();

    public static final native int PJ_TURN_TP_TLS_get();

    public static final native int PJ_TURN_TP_UDP_get();

    public static final native void PendingJob_execute(long j, PendingJob pendingJob, boolean z);

    public static final native long PersistentDocument_getRootContainer(long j, PersistentDocument persistentDocument);

    public static final native boolean PersistentDocument_hasUnread(long j, PersistentDocument persistentDocument);

    public static final native void PersistentDocument_loadFile(long j, PersistentDocument persistentDocument, String str);

    public static final native void PersistentDocument_loadString(long j, PersistentDocument persistentDocument, String str);

    public static final native long PersistentDocument_readArray__SWIG_0(long j, PersistentDocument persistentDocument, String str);

    public static final native long PersistentDocument_readArray__SWIG_1(long j, PersistentDocument persistentDocument);

    public static final native boolean PersistentDocument_readBool__SWIG_0(long j, PersistentDocument persistentDocument, String str);

    public static final native boolean PersistentDocument_readBool__SWIG_1(long j, PersistentDocument persistentDocument);

    public static final native long PersistentDocument_readContainer__SWIG_0(long j, PersistentDocument persistentDocument, String str);

    public static final native long PersistentDocument_readContainer__SWIG_1(long j, PersistentDocument persistentDocument);

    public static final native int PersistentDocument_readInt__SWIG_0(long j, PersistentDocument persistentDocument, String str);

    public static final native int PersistentDocument_readInt__SWIG_1(long j, PersistentDocument persistentDocument);

    public static final native float PersistentDocument_readNumber__SWIG_0(long j, PersistentDocument persistentDocument, String str);

    public static final native float PersistentDocument_readNumber__SWIG_1(long j, PersistentDocument persistentDocument);

    public static final native void PersistentDocument_readObject(long j, PersistentDocument persistentDocument, long j2, PersistentObject persistentObject);

    public static final native long PersistentDocument_readStringVector__SWIG_0(long j, PersistentDocument persistentDocument, String str);

    public static final native long PersistentDocument_readStringVector__SWIG_1(long j, PersistentDocument persistentDocument);

    public static final native String PersistentDocument_readString__SWIG_0(long j, PersistentDocument persistentDocument, String str);

    public static final native String PersistentDocument_readString__SWIG_1(long j, PersistentDocument persistentDocument);

    public static final native void PersistentDocument_saveFile(long j, PersistentDocument persistentDocument, String str);

    public static final native String PersistentDocument_saveString(long j, PersistentDocument persistentDocument);

    public static final native String PersistentDocument_unreadName(long j, PersistentDocument persistentDocument);

    public static final native void PersistentDocument_writeBool(long j, PersistentDocument persistentDocument, String str, boolean z);

    public static final native void PersistentDocument_writeInt(long j, PersistentDocument persistentDocument, String str, int i);

    public static final native long PersistentDocument_writeNewArray(long j, PersistentDocument persistentDocument, String str);

    public static final native long PersistentDocument_writeNewContainer(long j, PersistentDocument persistentDocument, String str);

    public static final native void PersistentDocument_writeNumber(long j, PersistentDocument persistentDocument, String str, float f);

    public static final native void PersistentDocument_writeObject(long j, PersistentDocument persistentDocument, long j2, PersistentObject persistentObject);

    public static final native void PersistentDocument_writeString(long j, PersistentDocument persistentDocument, String str, String str2);

    public static final native void PersistentDocument_writeStringVector(long j, PersistentDocument persistentDocument, String str, long j2, StringVector stringVector);

    public static final native void PersistentObject_readObject(long j, PersistentObject persistentObject, long j2, ContainerNode containerNode);

    public static final native void PersistentObject_writeObject(long j, PersistentObject persistentObject, long j2, ContainerNode containerNode);

    public static final native String PresNotifyParam_reason_get(long j, PresNotifyParam presNotifyParam);

    public static final native void PresNotifyParam_reason_set(long j, PresNotifyParam presNotifyParam, String str);

    public static final native long PresNotifyParam_srvPres_get(long j, PresNotifyParam presNotifyParam);

    public static final native void PresNotifyParam_srvPres_set(long j, PresNotifyParam presNotifyParam, long j2);

    public static final native String PresNotifyParam_stateStr_get(long j, PresNotifyParam presNotifyParam);

    public static final native void PresNotifyParam_stateStr_set(long j, PresNotifyParam presNotifyParam, String str);

    public static final native int PresNotifyParam_state_get(long j, PresNotifyParam presNotifyParam);

    public static final native void PresNotifyParam_state_set(long j, PresNotifyParam presNotifyParam, int i);

    public static final native long PresNotifyParam_txOption_get(long j, PresNotifyParam presNotifyParam);

    public static final native void PresNotifyParam_txOption_set(long j, PresNotifyParam presNotifyParam, long j2, SipTxOption sipTxOption);

    public static final native boolean PresNotifyParam_withBody_get(long j, PresNotifyParam presNotifyParam);

    public static final native void PresNotifyParam_withBody_set(long j, PresNotifyParam presNotifyParam, boolean z);

    public static final native int PresenceStatus_activity_get(long j, PresenceStatus presenceStatus);

    public static final native void PresenceStatus_activity_set(long j, PresenceStatus presenceStatus, int i);

    public static final native String PresenceStatus_note_get(long j, PresenceStatus presenceStatus);

    public static final native void PresenceStatus_note_set(long j, PresenceStatus presenceStatus, String str);

    public static final native String PresenceStatus_rpidId_get(long j, PresenceStatus presenceStatus);

    public static final native void PresenceStatus_rpidId_set(long j, PresenceStatus presenceStatus, String str);

    public static final native String PresenceStatus_statusText_get(long j, PresenceStatus presenceStatus);

    public static final native void PresenceStatus_statusText_set(long j, PresenceStatus presenceStatus, String str);

    public static final native int PresenceStatus_status_get(long j, PresenceStatus presenceStatus);

    public static final native void PresenceStatus_status_set(long j, PresenceStatus presenceStatus, int i);

    public static final native String RtcpSdes_cname_get(long j, RtcpSdes rtcpSdes);

    public static final native void RtcpSdes_cname_set(long j, RtcpSdes rtcpSdes, String str);

    public static final native String RtcpSdes_email_get(long j, RtcpSdes rtcpSdes);

    public static final native void RtcpSdes_email_set(long j, RtcpSdes rtcpSdes, String str);

    public static final native String RtcpSdes_loc_get(long j, RtcpSdes rtcpSdes);

    public static final native void RtcpSdes_loc_set(long j, RtcpSdes rtcpSdes, String str);

    public static final native String RtcpSdes_name_get(long j, RtcpSdes rtcpSdes);

    public static final native void RtcpSdes_name_set(long j, RtcpSdes rtcpSdes, String str);

    public static final native String RtcpSdes_note_get(long j, RtcpSdes rtcpSdes);

    public static final native void RtcpSdes_note_set(long j, RtcpSdes rtcpSdes, String str);

    public static final native String RtcpSdes_phone_get(long j, RtcpSdes rtcpSdes);

    public static final native void RtcpSdes_phone_set(long j, RtcpSdes rtcpSdes, String str);

    public static final native String RtcpSdes_tool_get(long j, RtcpSdes rtcpSdes);

    public static final native void RtcpSdes_tool_set(long j, RtcpSdes rtcpSdes, String str);

    public static final native long RtcpStat_peerSdes_get(long j, RtcpStat rtcpStat);

    public static final native void RtcpStat_peerSdes_set(long j, RtcpStat rtcpStat, long j2, RtcpSdes rtcpSdes);

    public static final native int RtcpStat_rtpTxLastSeq_get(long j, RtcpStat rtcpStat);

    public static final native void RtcpStat_rtpTxLastSeq_set(long j, RtcpStat rtcpStat, int i);

    public static final native long RtcpStat_rtpTxLastTs_get(long j, RtcpStat rtcpStat);

    public static final native void RtcpStat_rtpTxLastTs_set(long j, RtcpStat rtcpStat, long j2);

    public static final native long RtcpStat_rttUsec_get(long j, RtcpStat rtcpStat);

    public static final native void RtcpStat_rttUsec_set(long j, RtcpStat rtcpStat, long j2, MathStat mathStat);

    public static final native long RtcpStat_rxIpdvUsec_get(long j, RtcpStat rtcpStat);

    public static final native void RtcpStat_rxIpdvUsec_set(long j, RtcpStat rtcpStat, long j2, MathStat mathStat);

    public static final native long RtcpStat_rxRawJitterUsec_get(long j, RtcpStat rtcpStat);

    public static final native void RtcpStat_rxRawJitterUsec_set(long j, RtcpStat rtcpStat, long j2, MathStat mathStat);

    public static final native long RtcpStat_rxStat_get(long j, RtcpStat rtcpStat);

    public static final native void RtcpStat_rxStat_set(long j, RtcpStat rtcpStat, long j2, RtcpStreamStat rtcpStreamStat);

    public static final native long RtcpStat_start_get(long j, RtcpStat rtcpStat);

    public static final native void RtcpStat_start_set(long j, RtcpStat rtcpStat, long j2, TimeVal timeVal);

    public static final native long RtcpStat_txStat_get(long j, RtcpStat rtcpStat);

    public static final native void RtcpStat_txStat_set(long j, RtcpStat rtcpStat, long j2, RtcpStreamStat rtcpStreamStat);

    public static final native long RtcpStreamStat_bytes_get(long j, RtcpStreamStat rtcpStreamStat);

    public static final native void RtcpStreamStat_bytes_set(long j, RtcpStreamStat rtcpStreamStat, long j2);

    public static final native long RtcpStreamStat_discard_get(long j, RtcpStreamStat rtcpStreamStat);

    public static final native void RtcpStreamStat_discard_set(long j, RtcpStreamStat rtcpStreamStat, long j2);

    public static final native long RtcpStreamStat_dup_get(long j, RtcpStreamStat rtcpStreamStat);

    public static final native void RtcpStreamStat_dup_set(long j, RtcpStreamStat rtcpStreamStat, long j2);

    public static final native long RtcpStreamStat_jitterUsec_get(long j, RtcpStreamStat rtcpStreamStat);

    public static final native void RtcpStreamStat_jitterUsec_set(long j, RtcpStreamStat rtcpStreamStat, long j2, MathStat mathStat);

    public static final native long RtcpStreamStat_lossPeriodUsec_get(long j, RtcpStreamStat rtcpStreamStat);

    public static final native void RtcpStreamStat_lossPeriodUsec_set(long j, RtcpStreamStat rtcpStreamStat, long j2, MathStat mathStat);

    public static final native long RtcpStreamStat_loss_get(long j, RtcpStreamStat rtcpStreamStat);

    public static final native void RtcpStreamStat_loss_set(long j, RtcpStreamStat rtcpStreamStat, long j2);

    public static final native long RtcpStreamStat_pkt_get(long j, RtcpStreamStat rtcpStreamStat);

    public static final native void RtcpStreamStat_pkt_set(long j, RtcpStreamStat rtcpStreamStat, long j2);

    public static final native long RtcpStreamStat_reorder_get(long j, RtcpStreamStat rtcpStreamStat);

    public static final native void RtcpStreamStat_reorder_set(long j, RtcpStreamStat rtcpStreamStat, long j2);

    public static final native long RtcpStreamStat_updateCount_get(long j, RtcpStreamStat rtcpStreamStat);

    public static final native void RtcpStreamStat_updateCount_set(long j, RtcpStreamStat rtcpStreamStat, long j2);

    public static final native long RtcpStreamStat_update_get(long j, RtcpStreamStat rtcpStreamStat);

    public static final native void RtcpStreamStat_update_set(long j, RtcpStreamStat rtcpStreamStat, long j2, TimeVal timeVal);

    public static final native long RxMsgEvent_rdata_get(long j, RxMsgEvent rxMsgEvent);

    public static final native void RxMsgEvent_rdata_set(long j, RxMsgEvent rxMsgEvent, long j2, SipRxData sipRxData);

    public static final native int SUCCESS_get();

    public static final native long SdpSession_pjSdpSession_get(long j, SdpSession sdpSession);

    public static final native void SdpSession_pjSdpSession_set(long j, SdpSession sdpSession, long j2);

    public static final native String SdpSession_wholeSdp_get(long j, SdpSession sdpSession);

    public static final native void SdpSession_wholeSdp_set(long j, SdpSession sdpSession, String str);

    public static final native String SendInstantMessageParam_contentType_get(long j, SendInstantMessageParam sendInstantMessageParam);

    public static final native void SendInstantMessageParam_contentType_set(long j, SendInstantMessageParam sendInstantMessageParam, String str);

    public static final native String SendInstantMessageParam_content_get(long j, SendInstantMessageParam sendInstantMessageParam);

    public static final native void SendInstantMessageParam_content_set(long j, SendInstantMessageParam sendInstantMessageParam, String str);

    public static final native long SendInstantMessageParam_txOption_get(long j, SendInstantMessageParam sendInstantMessageParam);

    public static final native void SendInstantMessageParam_txOption_set(long j, SendInstantMessageParam sendInstantMessageParam, long j2, SipTxOption sipTxOption);

    public static final native long SendInstantMessageParam_userData_get(long j, SendInstantMessageParam sendInstantMessageParam);

    public static final native void SendInstantMessageParam_userData_set(long j, SendInstantMessageParam sendInstantMessageParam, long j2);

    public static final native boolean SendTypingIndicationParam_isTyping_get(long j, SendTypingIndicationParam sendTypingIndicationParam);

    public static final native void SendTypingIndicationParam_isTyping_set(long j, SendTypingIndicationParam sendTypingIndicationParam, boolean z);

    public static final native long SendTypingIndicationParam_txOption_get(long j, SendTypingIndicationParam sendTypingIndicationParam);

    public static final native void SendTypingIndicationParam_txOption_set(long j, SendTypingIndicationParam sendTypingIndicationParam, long j2, SipTxOption sipTxOption);

    public static final native long SipEvent_pjEvent_get(long j, SipEvent sipEvent);

    public static final native void SipEvent_pjEvent_set(long j, SipEvent sipEvent, long j2);

    public static final native int SipEvent_type_get(long j, SipEvent sipEvent);

    public static final native void SipEvent_type_set(long j, SipEvent sipEvent, int i);

    public static final native void SipHeaderVector_add(long j, SipHeaderVector sipHeaderVector, long j2, SipHeader sipHeader);

    public static final native long SipHeaderVector_capacity(long j, SipHeaderVector sipHeaderVector);

    public static final native void SipHeaderVector_clear(long j, SipHeaderVector sipHeaderVector);

    public static final native long SipHeaderVector_get(long j, SipHeaderVector sipHeaderVector, int i);

    public static final native boolean SipHeaderVector_isEmpty(long j, SipHeaderVector sipHeaderVector);

    public static final native void SipHeaderVector_reserve(long j, SipHeaderVector sipHeaderVector, long j2);

    public static final native void SipHeaderVector_set(long j, SipHeaderVector sipHeaderVector, int i, long j2, SipHeader sipHeader);

    public static final native long SipHeaderVector_size(long j, SipHeaderVector sipHeaderVector);

    public static final native String SipHeader_hName_get(long j, SipHeader sipHeader);

    public static final native void SipHeader_hName_set(long j, SipHeader sipHeader, String str);

    public static final native String SipHeader_hValue_get(long j, SipHeader sipHeader);

    public static final native void SipHeader_hValue_set(long j, SipHeader sipHeader, String str);

    public static final native String SipMediaType_subType_get(long j, SipMediaType sipMediaType);

    public static final native void SipMediaType_subType_set(long j, SipMediaType sipMediaType, String str);

    public static final native String SipMediaType_type_get(long j, SipMediaType sipMediaType);

    public static final native void SipMediaType_type_set(long j, SipMediaType sipMediaType, String str);

    public static final native void SipMultipartPartVector_add(long j, SipMultipartPartVector sipMultipartPartVector, long j2, SipMultipartPart sipMultipartPart);

    public static final native long SipMultipartPartVector_capacity(long j, SipMultipartPartVector sipMultipartPartVector);

    public static final native void SipMultipartPartVector_clear(long j, SipMultipartPartVector sipMultipartPartVector);

    public static final native long SipMultipartPartVector_get(long j, SipMultipartPartVector sipMultipartPartVector, int i);

    public static final native boolean SipMultipartPartVector_isEmpty(long j, SipMultipartPartVector sipMultipartPartVector);

    public static final native void SipMultipartPartVector_reserve(long j, SipMultipartPartVector sipMultipartPartVector, long j2);

    public static final native void SipMultipartPartVector_set(long j, SipMultipartPartVector sipMultipartPartVector, int i, long j2, SipMultipartPart sipMultipartPart);

    public static final native long SipMultipartPartVector_size(long j, SipMultipartPartVector sipMultipartPartVector);

    public static final native String SipMultipartPart_body_get(long j, SipMultipartPart sipMultipartPart);

    public static final native void SipMultipartPart_body_set(long j, SipMultipartPart sipMultipartPart, String str);

    public static final native long SipMultipartPart_contentType_get(long j, SipMultipartPart sipMultipartPart);

    public static final native void SipMultipartPart_contentType_set(long j, SipMultipartPart sipMultipartPart, long j2, SipMediaType sipMediaType);

    public static final native long SipMultipartPart_headers_get(long j, SipMultipartPart sipMultipartPart);

    public static final native void SipMultipartPart_headers_set(long j, SipMultipartPart sipMultipartPart, long j2, SipHeaderVector sipHeaderVector);

    public static final native String SipRxData_info_get(long j, SipRxData sipRxData);

    public static final native void SipRxData_info_set(long j, SipRxData sipRxData, String str);

    public static final native long SipRxData_pjRxData_get(long j, SipRxData sipRxData);

    public static final native void SipRxData_pjRxData_set(long j, SipRxData sipRxData, long j2);

    public static final native String SipRxData_srcAddress_get(long j, SipRxData sipRxData);

    public static final native void SipRxData_srcAddress_set(long j, SipRxData sipRxData, String str);

    public static final native String SipRxData_wholeMsg_get(long j, SipRxData sipRxData);

    public static final native void SipRxData_wholeMsg_set(long j, SipRxData sipRxData, String str);

    public static final native long SipTransaction_lastTx_get(long j, SipTransaction sipTransaction);

    public static final native void SipTransaction_lastTx_set(long j, SipTransaction sipTransaction, long j2, SipTxData sipTxData);

    public static final native String SipTransaction_method_get(long j, SipTransaction sipTransaction);

    public static final native void SipTransaction_method_set(long j, SipTransaction sipTransaction, String str);

    public static final native long SipTransaction_pjTransaction_get(long j, SipTransaction sipTransaction);

    public static final native void SipTransaction_pjTransaction_set(long j, SipTransaction sipTransaction, long j2);

    public static final native int SipTransaction_role_get(long j, SipTransaction sipTransaction);

    public static final native void SipTransaction_role_set(long j, SipTransaction sipTransaction, int i);

    public static final native int SipTransaction_state_get(long j, SipTransaction sipTransaction);

    public static final native void SipTransaction_state_set(long j, SipTransaction sipTransaction, int i);

    public static final native int SipTransaction_statusCode_get(long j, SipTransaction sipTransaction);

    public static final native void SipTransaction_statusCode_set(long j, SipTransaction sipTransaction, int i);

    public static final native String SipTransaction_statusText_get(long j, SipTransaction sipTransaction);

    public static final native void SipTransaction_statusText_set(long j, SipTransaction sipTransaction, String str);

    public static final native String SipTxData_dstAddress_get(long j, SipTxData sipTxData);

    public static final native void SipTxData_dstAddress_set(long j, SipTxData sipTxData, String str);

    public static final native String SipTxData_info_get(long j, SipTxData sipTxData);

    public static final native void SipTxData_info_set(long j, SipTxData sipTxData, String str);

    public static final native long SipTxData_pjTxData_get(long j, SipTxData sipTxData);

    public static final native void SipTxData_pjTxData_set(long j, SipTxData sipTxData, long j2);

    public static final native String SipTxData_wholeMsg_get(long j, SipTxData sipTxData);

    public static final native void SipTxData_wholeMsg_set(long j, SipTxData sipTxData, String str);

    public static final native String SipTxOption_contentType_get(long j, SipTxOption sipTxOption);

    public static final native void SipTxOption_contentType_set(long j, SipTxOption sipTxOption, String str);

    public static final native long SipTxOption_headers_get(long j, SipTxOption sipTxOption);

    public static final native void SipTxOption_headers_set(long j, SipTxOption sipTxOption, long j2, SipHeaderVector sipHeaderVector);

    public static final native boolean SipTxOption_isEmpty(long j, SipTxOption sipTxOption);

    public static final native String SipTxOption_msgBody_get(long j, SipTxOption sipTxOption);

    public static final native void SipTxOption_msgBody_set(long j, SipTxOption sipTxOption, String str);

    public static final native long SipTxOption_multipartContentType_get(long j, SipTxOption sipTxOption);

    public static final native void SipTxOption_multipartContentType_set(long j, SipTxOption sipTxOption, long j2, SipMediaType sipMediaType);

    public static final native long SipTxOption_multipartParts_get(long j, SipTxOption sipTxOption);

    public static final native void SipTxOption_multipartParts_set(long j, SipTxOption sipTxOption, long j2, SipMultipartPartVector sipMultipartPartVector);

    public static final native String SipTxOption_targetUri_get(long j, SipTxOption sipTxOption);

    public static final native void SipTxOption_targetUri_set(long j, SipTxOption sipTxOption, String str);

    public static final native long StreamInfo_codecClockRate_get(long j, StreamInfo streamInfo);

    public static final native void StreamInfo_codecClockRate_set(long j, StreamInfo streamInfo, long j2);

    public static final native String StreamInfo_codecName_get(long j, StreamInfo streamInfo);

    public static final native void StreamInfo_codecName_set(long j, StreamInfo streamInfo, String str);

    public static final native long StreamInfo_codecParam_get(long j, StreamInfo streamInfo);

    public static final native void StreamInfo_codecParam_set(long j, StreamInfo streamInfo, long j2);

    public static final native int StreamInfo_dir_get(long j, StreamInfo streamInfo);

    public static final native void StreamInfo_dir_set(long j, StreamInfo streamInfo, int i);

    public static final native int StreamInfo_proto_get(long j, StreamInfo streamInfo);

    public static final native void StreamInfo_proto_set(long j, StreamInfo streamInfo, int i);

    public static final native String StreamInfo_remoteRtcpAddress_get(long j, StreamInfo streamInfo);

    public static final native void StreamInfo_remoteRtcpAddress_set(long j, StreamInfo streamInfo, String str);

    public static final native String StreamInfo_remoteRtpAddress_get(long j, StreamInfo streamInfo);

    public static final native void StreamInfo_remoteRtpAddress_set(long j, StreamInfo streamInfo, String str);

    public static final native long StreamInfo_rxPt_get(long j, StreamInfo streamInfo);

    public static final native void StreamInfo_rxPt_set(long j, StreamInfo streamInfo, long j2);

    public static final native long StreamInfo_txPt_get(long j, StreamInfo streamInfo);

    public static final native void StreamInfo_txPt_set(long j, StreamInfo streamInfo, long j2);

    public static final native int StreamInfo_type_get(long j, StreamInfo streamInfo);

    public static final native void StreamInfo_type_set(long j, StreamInfo streamInfo, int i);

    public static final native long StreamStat_jbuf_get(long j, StreamStat streamStat);

    public static final native void StreamStat_jbuf_set(long j, StreamStat streamStat, long j2, JbufState jbufState);

    public static final native long StreamStat_rtcp_get(long j, StreamStat streamStat);

    public static final native void StreamStat_rtcp_set(long j, StreamStat streamStat, long j2, RtcpStat rtcpStat);

    public static final native void StringVector_add(long j, StringVector stringVector, String str);

    public static final native long StringVector_capacity(long j, StringVector stringVector);

    public static final native void StringVector_clear(long j, StringVector stringVector);

    public static final native String StringVector_get(long j, StringVector stringVector, int i);

    public static final native boolean StringVector_isEmpty(long j, StringVector stringVector);

    public static final native void StringVector_reserve(long j, StringVector stringVector, long j2);

    public static final native void StringVector_set(long j, StringVector stringVector, int i, String str);

    public static final native long StringVector_size(long j, StringVector stringVector);

    public static final native int TimeVal_msec_get(long j, TimeVal timeVal);

    public static final native void TimeVal_msec_set(long j, TimeVal timeVal, int i);

    public static final native int TimeVal_sec_get(long j, TimeVal timeVal);

    public static final native void TimeVal_sec_set(long j, TimeVal timeVal, int i);

    public static final native long TimerEvent_entry_get(long j, TimerEvent timerEvent);

    public static final native void TimerEvent_entry_set(long j, TimerEvent timerEvent, long j2);

    public static final native String TlsConfig_CaListFile_get(long j, TlsConfig tlsConfig);

    public static final native void TlsConfig_CaListFile_set(long j, TlsConfig tlsConfig, String str);

    public static final native long TlsConfig_SWIGUpcast(long j);

    public static final native String TlsConfig_certFile_get(long j, TlsConfig tlsConfig);

    public static final native void TlsConfig_certFile_set(long j, TlsConfig tlsConfig, String str);

    public static final native long TlsConfig_ciphers_get(long j, TlsConfig tlsConfig);

    public static final native void TlsConfig_ciphers_set(long j, TlsConfig tlsConfig, long j2, IntVector intVector);

    public static final native int TlsConfig_method_get(long j, TlsConfig tlsConfig);

    public static final native void TlsConfig_method_set(long j, TlsConfig tlsConfig, int i);

    public static final native long TlsConfig_msecTimeout_get(long j, TlsConfig tlsConfig);

    public static final native void TlsConfig_msecTimeout_set(long j, TlsConfig tlsConfig, long j2);

    public static final native String TlsConfig_password_get(long j, TlsConfig tlsConfig);

    public static final native void TlsConfig_password_set(long j, TlsConfig tlsConfig, String str);

    public static final native String TlsConfig_privKeyFile_get(long j, TlsConfig tlsConfig);

    public static final native void TlsConfig_privKeyFile_set(long j, TlsConfig tlsConfig, String str);

    public static final native boolean TlsConfig_qosIgnoreError_get(long j, TlsConfig tlsConfig);

    public static final native void TlsConfig_qosIgnoreError_set(long j, TlsConfig tlsConfig, boolean z);

    public static final native long TlsConfig_qosParams_get(long j, TlsConfig tlsConfig);

    public static final native void TlsConfig_qosParams_set(long j, TlsConfig tlsConfig, long j2, pj_qos_params org_pjsip_pjsua2_pj_qos_params);

    public static final native int TlsConfig_qosType_get(long j, TlsConfig tlsConfig);

    public static final native void TlsConfig_qosType_set(long j, TlsConfig tlsConfig, int i);

    public static final native void TlsConfig_readObject(long j, TlsConfig tlsConfig, long j2, ContainerNode containerNode);

    public static final native boolean TlsConfig_requireClientCert_get(long j, TlsConfig tlsConfig);

    public static final native void TlsConfig_requireClientCert_set(long j, TlsConfig tlsConfig, boolean z);

    public static final native boolean TlsConfig_verifyClient_get(long j, TlsConfig tlsConfig);

    public static final native void TlsConfig_verifyClient_set(long j, TlsConfig tlsConfig, boolean z);

    public static final native boolean TlsConfig_verifyServer_get(long j, TlsConfig tlsConfig);

    public static final native void TlsConfig_verifyServer_set(long j, TlsConfig tlsConfig, boolean z);

    public static final native void TlsConfig_writeObject(long j, TlsConfig tlsConfig, long j2, ContainerNode containerNode);

    public static final native void ToneDescVector_add(long j, ToneDescVector toneDescVector, long j2, ToneDesc toneDesc);

    public static final native long ToneDescVector_capacity(long j, ToneDescVector toneDescVector);

    public static final native void ToneDescVector_clear(long j, ToneDescVector toneDescVector);

    public static final native long ToneDescVector_get(long j, ToneDescVector toneDescVector, int i);

    public static final native boolean ToneDescVector_isEmpty(long j, ToneDescVector toneDescVector);

    public static final native void ToneDescVector_reserve(long j, ToneDescVector toneDescVector, long j2);

    public static final native void ToneDescVector_set(long j, ToneDescVector toneDescVector, int i, long j2, ToneDesc toneDesc);

    public static final native long ToneDescVector_size(long j, ToneDescVector toneDescVector);

    public static final native long ToneDesc_SWIGUpcast(long j);

    public static final native String ToneDigitMapDigit_digit_get(long j, ToneDigitMapDigit toneDigitMapDigit);

    public static final native void ToneDigitMapDigit_digit_set(long j, ToneDigitMapDigit toneDigitMapDigit, String str);

    public static final native int ToneDigitMapDigit_freq1_get(long j, ToneDigitMapDigit toneDigitMapDigit);

    public static final native void ToneDigitMapDigit_freq1_set(long j, ToneDigitMapDigit toneDigitMapDigit, int i);

    public static final native int ToneDigitMapDigit_freq2_get(long j, ToneDigitMapDigit toneDigitMapDigit);

    public static final native void ToneDigitMapDigit_freq2_set(long j, ToneDigitMapDigit toneDigitMapDigit, int i);

    public static final native void ToneDigitMapVector_add(long j, ToneDigitMapVector toneDigitMapVector, long j2, ToneDigitMapDigit toneDigitMapDigit);

    public static final native long ToneDigitMapVector_capacity(long j, ToneDigitMapVector toneDigitMapVector);

    public static final native void ToneDigitMapVector_clear(long j, ToneDigitMapVector toneDigitMapVector);

    public static final native long ToneDigitMapVector_get(long j, ToneDigitMapVector toneDigitMapVector, int i);

    public static final native boolean ToneDigitMapVector_isEmpty(long j, ToneDigitMapVector toneDigitMapVector);

    public static final native void ToneDigitMapVector_reserve(long j, ToneDigitMapVector toneDigitMapVector, long j2);

    public static final native void ToneDigitMapVector_set(long j, ToneDigitMapVector toneDigitMapVector, int i, long j2, ToneDigitMapDigit toneDigitMapDigit);

    public static final native long ToneDigitMapVector_size(long j, ToneDigitMapVector toneDigitMapVector);

    public static final native void ToneDigitVector_add(long j, ToneDigitVector toneDigitVector, long j2, ToneDigit toneDigit);

    public static final native long ToneDigitVector_capacity(long j, ToneDigitVector toneDigitVector);

    public static final native void ToneDigitVector_clear(long j, ToneDigitVector toneDigitVector);

    public static final native long ToneDigitVector_get(long j, ToneDigitVector toneDigitVector, int i);

    public static final native boolean ToneDigitVector_isEmpty(long j, ToneDigitVector toneDigitVector);

    public static final native void ToneDigitVector_reserve(long j, ToneDigitVector toneDigitVector, long j2);

    public static final native void ToneDigitVector_set(long j, ToneDigitVector toneDigitVector, int i, long j2, ToneDigit toneDigit);

    public static final native long ToneDigitVector_size(long j, ToneDigitVector toneDigitVector);

    public static final native long ToneDigit_SWIGUpcast(long j);

    public static final native long ToneGenerator_SWIGUpcast(long j);

    public static final native void ToneGenerator_createToneGenerator__SWIG_0(long j, ToneGenerator toneGenerator, long j2, long j3);

    public static final native void ToneGenerator_createToneGenerator__SWIG_1(long j, ToneGenerator toneGenerator, long j2);

    public static final native void ToneGenerator_createToneGenerator__SWIG_2(long j, ToneGenerator toneGenerator);

    public static final native long ToneGenerator_getDigitMap(long j, ToneGenerator toneGenerator);

    public static final native boolean ToneGenerator_isBusy(long j, ToneGenerator toneGenerator);

    public static final native void ToneGenerator_playDigits__SWIG_0(long j, ToneGenerator toneGenerator, long j2, ToneDigitVector toneDigitVector, boolean z);

    public static final native void ToneGenerator_playDigits__SWIG_1(long j, ToneGenerator toneGenerator, long j2, ToneDigitVector toneDigitVector);

    public static final native void ToneGenerator_play__SWIG_0(long j, ToneGenerator toneGenerator, long j2, ToneDescVector toneDescVector, boolean z);

    public static final native void ToneGenerator_play__SWIG_1(long j, ToneGenerator toneGenerator, long j2, ToneDescVector toneDescVector);

    public static final native void ToneGenerator_rewind(long j, ToneGenerator toneGenerator);

    public static final native void ToneGenerator_setDigitMap(long j, ToneGenerator toneGenerator, long j2, ToneDigitMapVector toneDigitMapVector);

    public static final native void ToneGenerator_stop(long j, ToneGenerator toneGenerator);

    public static final native long TransportConfig_SWIGUpcast(long j);

    public static final native String TransportConfig_boundAddress_get(long j, TransportConfig transportConfig);

    public static final native void TransportConfig_boundAddress_set(long j, TransportConfig transportConfig, String str);

    public static final native long TransportConfig_portRange_get(long j, TransportConfig transportConfig);

    public static final native void TransportConfig_portRange_set(long j, TransportConfig transportConfig, long j2);

    public static final native long TransportConfig_port_get(long j, TransportConfig transportConfig);

    public static final native void TransportConfig_port_set(long j, TransportConfig transportConfig, long j2);

    public static final native String TransportConfig_publicAddress_get(long j, TransportConfig transportConfig);

    public static final native void TransportConfig_publicAddress_set(long j, TransportConfig transportConfig, String str);

    public static final native long TransportConfig_qosParams_get(long j, TransportConfig transportConfig);

    public static final native void TransportConfig_qosParams_set(long j, TransportConfig transportConfig, long j2, pj_qos_params org_pjsip_pjsua2_pj_qos_params);

    public static final native int TransportConfig_qosType_get(long j, TransportConfig transportConfig);

    public static final native void TransportConfig_qosType_set(long j, TransportConfig transportConfig, int i);

    public static final native void TransportConfig_readObject(long j, TransportConfig transportConfig, long j2, ContainerNode containerNode);

    public static final native long TransportConfig_tlsConfig_get(long j, TransportConfig transportConfig);

    public static final native void TransportConfig_tlsConfig_set(long j, TransportConfig transportConfig, long j2, TlsConfig tlsConfig);

    public static final native void TransportConfig_writeObject(long j, TransportConfig transportConfig, long j2, ContainerNode containerNode);

    public static final native long TransportInfo_flags_get(long j, TransportInfo transportInfo);

    public static final native void TransportInfo_flags_set(long j, TransportInfo transportInfo, long j2);

    public static final native int TransportInfo_id_get(long j, TransportInfo transportInfo);

    public static final native void TransportInfo_id_set(long j, TransportInfo transportInfo, int i);

    public static final native String TransportInfo_info_get(long j, TransportInfo transportInfo);

    public static final native void TransportInfo_info_set(long j, TransportInfo transportInfo, String str);

    public static final native String TransportInfo_localAddress_get(long j, TransportInfo transportInfo);

    public static final native void TransportInfo_localAddress_set(long j, TransportInfo transportInfo, String str);

    public static final native String TransportInfo_localName_get(long j, TransportInfo transportInfo);

    public static final native void TransportInfo_localName_set(long j, TransportInfo transportInfo, String str);

    public static final native String TransportInfo_typeName_get(long j, TransportInfo transportInfo);

    public static final native void TransportInfo_typeName_set(long j, TransportInfo transportInfo, String str);

    public static final native int TransportInfo_type_get(long j, TransportInfo transportInfo);

    public static final native void TransportInfo_type_set(long j, TransportInfo transportInfo, int i);

    public static final native long TransportInfo_usageCount_get(long j, TransportInfo transportInfo);

    public static final native void TransportInfo_usageCount_set(long j, TransportInfo transportInfo, long j2);

    public static final native int TsxStateEvent_prevState_get(long j, TsxStateEvent tsxStateEvent);

    public static final native void TsxStateEvent_prevState_set(long j, TsxStateEvent tsxStateEvent, int i);

    public static final native long TsxStateEvent_tsx_get(long j, TsxStateEvent tsxStateEvent);

    public static final native void TsxStateEvent_tsx_set(long j, TsxStateEvent tsxStateEvent, long j2, SipTransaction sipTransaction);

    public static final native int TsxStateEvent_type_get(long j, TsxStateEvent tsxStateEvent);

    public static final native void TsxStateEvent_type_set(long j, TsxStateEvent tsxStateEvent, int i);

    public static final native long TxErrorEvent_tdata_get(long j, TxErrorEvent txErrorEvent);

    public static final native void TxErrorEvent_tdata_set(long j, TxErrorEvent txErrorEvent, long j2, SipTxData sipTxData);

    public static final native long TxErrorEvent_tsx_get(long j, TxErrorEvent txErrorEvent);

    public static final native void TxErrorEvent_tsx_set(long j, TxErrorEvent txErrorEvent, long j2, SipTransaction sipTransaction);

    public static final native long TxMsgEvent_tdata_get(long j, TxMsgEvent txMsgEvent);

    public static final native void TxMsgEvent_tdata_set(long j, TxMsgEvent txMsgEvent, long j2, SipTxData sipTxData);

    public static final native long UaConfig_SWIGUpcast(long j);

    public static final native boolean UaConfig_mainThreadOnly_get(long j, UaConfig uaConfig);

    public static final native void UaConfig_mainThreadOnly_set(long j, UaConfig uaConfig, boolean z);

    public static final native long UaConfig_maxCalls_get(long j, UaConfig uaConfig);

    public static final native void UaConfig_maxCalls_set(long j, UaConfig uaConfig, long j2);

    public static final native boolean UaConfig_mwiUnsolicitedEnabled_get(long j, UaConfig uaConfig);

    public static final native void UaConfig_mwiUnsolicitedEnabled_set(long j, UaConfig uaConfig, boolean z);

    public static final native long UaConfig_nameserver_get(long j, UaConfig uaConfig);

    public static final native void UaConfig_nameserver_set(long j, UaConfig uaConfig, long j2, StringVector stringVector);

    public static final native int UaConfig_natTypeInSdp_get(long j, UaConfig uaConfig);

    public static final native void UaConfig_natTypeInSdp_set(long j, UaConfig uaConfig, int i);

    public static final native void UaConfig_readObject(long j, UaConfig uaConfig, long j2, ContainerNode containerNode);

    public static final native boolean UaConfig_stunIgnoreFailure_get(long j, UaConfig uaConfig);

    public static final native void UaConfig_stunIgnoreFailure_set(long j, UaConfig uaConfig, boolean z);

    public static final native long UaConfig_stunServer_get(long j, UaConfig uaConfig);

    public static final native void UaConfig_stunServer_set(long j, UaConfig uaConfig, long j2, StringVector stringVector);

    public static final native long UaConfig_threadCnt_get(long j, UaConfig uaConfig);

    public static final native void UaConfig_threadCnt_set(long j, UaConfig uaConfig, long j2);

    public static final native String UaConfig_userAgent_get(long j, UaConfig uaConfig);

    public static final native void UaConfig_userAgent_set(long j, UaConfig uaConfig, String str);

    public static final native void UaConfig_writeObject(long j, UaConfig uaConfig, long j2, ContainerNode containerNode);

    public static final native long UserEvent_user1_get(long j, UserEvent userEvent);

    public static final native void UserEvent_user1_set(long j, UserEvent userEvent, long j2);

    public static final native long UserEvent_user2_get(long j, UserEvent userEvent);

    public static final native void UserEvent_user2_set(long j, UserEvent userEvent, long j2);

    public static final native long UserEvent_user3_get(long j, UserEvent userEvent);

    public static final native void UserEvent_user3_set(long j, UserEvent userEvent, long j2);

    public static final native long UserEvent_user4_get(long j, UserEvent userEvent);

    public static final native void UserEvent_user4_set(long j, UserEvent userEvent, long j2);

    public static final native String Version_full_get(long j, Version version);

    public static final native void Version_full_set(long j, Version version, String str);

    public static final native int Version_major_get(long j, Version version);

    public static final native void Version_major_set(long j, Version version, int i);

    public static final native int Version_minor_get(long j, Version version);

    public static final native void Version_minor_set(long j, Version version, int i);

    public static final native long Version_numeric_get(long j, Version version);

    public static final native void Version_numeric_set(long j, Version version, long j2);

    public static final native int Version_rev_get(long j, Version version);

    public static final native void Version_rev_set(long j, Version version, int i);

    public static final native String Version_suffix_get(long j, Version version);

    public static final native void Version_suffix_set(long j, Version version, String str);

    public static final native void delete_Account(long j);

    public static final native void delete_AccountCallConfig(long j);

    public static final native void delete_AccountConfig(long j);

    public static final native void delete_AccountInfo(long j);

    public static final native void delete_AccountMediaConfig(long j);

    public static final native void delete_AccountMwiConfig(long j);

    public static final native void delete_AccountNatConfig(long j);

    public static final native void delete_AccountPresConfig(long j);

    public static final native void delete_AccountRegConfig(long j);

    public static final native void delete_AccountSipConfig(long j);

    public static final native void delete_AccountVideoConfig(long j);

    public static final native void delete_AudioDevInfo(long j);

    public static final native void delete_AudioDevInfoVector(long j);

    public static final native void delete_AudioMedia(long j);

    public static final native void delete_AudioMediaPlayer(long j);

    public static final native void delete_AudioMediaPlayerInfo(long j);

    public static final native void delete_AudioMediaRecorder(long j);

    public static final native void delete_AudioMediaVector(long j);

    public static final native void delete_AuthCredInfo(long j);

    public static final native void delete_AuthCredInfoVector(long j);

    public static final native void delete_Buddy(long j);

    public static final native void delete_BuddyConfig(long j);

    public static final native void delete_BuddyInfo(long j);

    public static final native void delete_BuddyVector(long j);

    public static final native void delete_Call(long j);

    public static final native void delete_CallInfo(long j);

    public static final native void delete_CallMediaInfo(long j);

    public static final native void delete_CallMediaInfoVector(long j);

    public static final native void delete_CallOpParam(long j);

    public static final native void delete_CallSendRequestParam(long j);

    public static final native void delete_CallSetting(long j);

    public static final native void delete_CallVidSetStreamParam(long j);

    public static final native void delete_CodecInfo(long j);

    public static final native void delete_CodecInfoVector(long j);

    public static final native void delete_ConfPortInfo(long j);

    public static final native void delete_ContainerNode(long j);

    public static final native void delete_Endpoint(long j);

    public static final native void delete_EpConfig(long j);

    public static final native void delete_Error(long j);

    public static final native void delete_FindBuddyMatch(long j);

    public static final native void delete_IntVector(long j);

    public static final native void delete_JbufState(long j);

    public static final native void delete_JsonDocument(long j);

    public static final native void delete_LogConfig(long j);

    public static final native void delete_LogEntry(long j);

    public static final native void delete_LogWriter(long j);

    public static final native void delete_MathStat(long j);

    public static final native void delete_Media(long j);

    public static final native void delete_MediaConfig(long j);

    public static final native void delete_MediaEvent(long j);

    public static final native void delete_MediaFmtChangedEvent(long j);

    public static final native void delete_MediaFormat(long j);

    public static final native void delete_MediaFormatAudio(long j);

    public static final native void delete_MediaFormatVector(long j);

    public static final native void delete_MediaFormatVideo(long j);

    public static final native void delete_MediaTransportInfo(long j);

    public static final native void delete_OnCallMediaEventParam(long j);

    public static final native void delete_OnCallMediaStateParam(long j);

    public static final native void delete_OnCallMediaTransportStateParam(long j);

    public static final native void delete_OnCallRedirectedParam(long j);

    public static final native void delete_OnCallReplaceRequestParam(long j);

    public static final native void delete_OnCallReplacedParam(long j);

    public static final native void delete_OnCallRxOfferParam(long j);

    public static final native void delete_OnCallSdpCreatedParam(long j);

    public static final native void delete_OnCallStateParam(long j);

    public static final native void delete_OnCallTransferRequestParam(long j);

    public static final native void delete_OnCallTransferStatusParam(long j);

    public static final native void delete_OnCallTsxStateParam(long j);

    public static final native void delete_OnCreateMediaTransportParam(long j);

    public static final native void delete_OnDtmfDigitParam(long j);

    public static final native void delete_OnIncomingCallParam(long j);

    public static final native void delete_OnIncomingSubscribeParam(long j);

    public static final native void delete_OnInstantMessageParam(long j);

    public static final native void delete_OnInstantMessageStatusParam(long j);

    public static final native void delete_OnMwiInfoParam(long j);

    public static final native void delete_OnNatCheckStunServersCompleteParam(long j);

    public static final native void delete_OnNatDetectionCompleteParam(long j);

    public static final native void delete_OnRegStartedParam(long j);

    public static final native void delete_OnRegStateParam(long j);

    public static final native void delete_OnSelectAccountParam(long j);

    public static final native void delete_OnStreamCreatedParam(long j);

    public static final native void delete_OnStreamDestroyedParam(long j);

    public static final native void delete_OnTimerParam(long j);

    public static final native void delete_OnTransportStateParam(long j);

    public static final native void delete_OnTypingIndicationParam(long j);

    public static final native void delete_PendingJob(long j);

    public static final native void delete_PersistentDocument(long j);

    public static final native void delete_PersistentObject(long j);

    public static final native void delete_PresNotifyParam(long j);

    public static final native void delete_PresenceStatus(long j);

    public static final native void delete_RtcpSdes(long j);

    public static final native void delete_RtcpStat(long j);

    public static final native void delete_RtcpStreamStat(long j);

    public static final native void delete_RxMsgEvent(long j);

    public static final native void delete_SdpSession(long j);

    public static final native void delete_SendInstantMessageParam(long j);

    public static final native void delete_SendTypingIndicationParam(long j);

    public static final native void delete_SipEvent(long j);

    public static final native void delete_SipHeader(long j);

    public static final native void delete_SipHeaderVector(long j);

    public static final native void delete_SipMediaType(long j);

    public static final native void delete_SipMultipartPart(long j);

    public static final native void delete_SipMultipartPartVector(long j);

    public static final native void delete_SipRxData(long j);

    public static final native void delete_SipTransaction(long j);

    public static final native void delete_SipTxData(long j);

    public static final native void delete_SipTxOption(long j);

    public static final native void delete_StreamInfo(long j);

    public static final native void delete_StreamStat(long j);

    public static final native void delete_StringVector(long j);

    public static final native void delete_TimeVal(long j);

    public static final native void delete_TimerEvent(long j);

    public static final native void delete_TlsConfig(long j);

    public static final native void delete_ToneDesc(long j);

    public static final native void delete_ToneDescVector(long j);

    public static final native void delete_ToneDigit(long j);

    public static final native void delete_ToneDigitMapDigit(long j);

    public static final native void delete_ToneDigitMapVector(long j);

    public static final native void delete_ToneDigitVector(long j);

    public static final native void delete_ToneGenerator(long j);

    public static final native void delete_TransportConfig(long j);

    public static final native void delete_TransportInfo(long j);

    public static final native void delete_TsxStateEvent(long j);

    public static final native void delete_TxErrorEvent(long j);

    public static final native void delete_TxMsgEvent(long j);

    public static final native void delete_UaConfig(long j);

    public static final native void delete_UserEvent(long j);

    public static final native void delete_Version(long j);

    public static final native void delete_pj_qos_params(long j);

    public static final native void delete_pjmedia_tone_desc(long j);

    public static final native void delete_pjmedia_tone_digit(long j);

    public static final native void delete_pjmedia_tone_digit_map(long j);

    public static final native long new_Account();

    public static final native long new_AccountCallConfig();

    public static final native long new_AccountConfig();

    public static final native long new_AccountInfo();

    public static final native long new_AccountMediaConfig();

    public static final native long new_AccountMwiConfig();

    public static final native long new_AccountNatConfig();

    public static final native long new_AccountPresConfig();

    public static final native long new_AccountRegConfig();

    public static final native long new_AccountSipConfig();

    public static final native long new_AccountVideoConfig();

    public static final native long new_AudioDevInfo();

    public static final native long new_AudioDevInfoVector__SWIG_0();

    public static final native long new_AudioDevInfoVector__SWIG_1(long j);

    public static final native long new_AudioMediaPlayer();

    public static final native long new_AudioMediaPlayerInfo();

    public static final native long new_AudioMediaRecorder();

    public static final native long new_AudioMediaVector__SWIG_0();

    public static final native long new_AudioMediaVector__SWIG_1(long j);

    public static final native long new_AuthCredInfoVector__SWIG_0();

    public static final native long new_AuthCredInfoVector__SWIG_1(long j);

    public static final native long new_AuthCredInfo__SWIG_0();

    public static final native long new_AuthCredInfo__SWIG_1(String str, String str2, String str3, int i, String str4);

    public static final native long new_Buddy();

    public static final native long new_BuddyConfig();

    public static final native long new_BuddyInfo();

    public static final native long new_BuddyVector__SWIG_0();

    public static final native long new_BuddyVector__SWIG_1(long j);

    public static final native long new_CallInfo();

    public static final native long new_CallMediaInfo();

    public static final native long new_CallMediaInfoVector__SWIG_0();

    public static final native long new_CallMediaInfoVector__SWIG_1(long j);

    public static final native long new_CallOpParam__SWIG_0(boolean z);

    public static final native long new_CallOpParam__SWIG_1();

    public static final native long new_CallSendRequestParam();

    public static final native long new_CallSetting__SWIG_0(long j);

    public static final native long new_CallSetting__SWIG_1();

    public static final native long new_CallVidSetStreamParam();

    public static final native long new_Call__SWIG_0(long j, Account account, int i);

    public static final native long new_Call__SWIG_1(long j, Account account);

    public static final native long new_CodecInfo();

    public static final native long new_CodecInfoVector__SWIG_0();

    public static final native long new_CodecInfoVector__SWIG_1(long j);

    public static final native long new_ConfPortInfo();

    public static final native long new_ContainerNode();

    public static final native long new_Endpoint();

    public static final native long new_EpConfig();

    public static final native long new_Error__SWIG_0();

    public static final native long new_Error__SWIG_1(int i, String str, String str2, String str3, int i2);

    public static final native long new_FindBuddyMatch();

    public static final native long new_IntVector__SWIG_0();

    public static final native long new_IntVector__SWIG_1(long j);

    public static final native long new_JbufState();

    public static final native long new_JsonDocument();

    public static final native long new_LogConfig();

    public static final native long new_LogEntry();

    public static final native long new_LogWriter();

    public static final native long new_MathStat();

    public static final native long new_MediaConfig();

    public static final native long new_MediaEvent();

    public static final native long new_MediaFmtChangedEvent();

    public static final native long new_MediaFormat();

    public static final native long new_MediaFormatAudio();

    public static final native long new_MediaFormatVector__SWIG_0();

    public static final native long new_MediaFormatVector__SWIG_1(long j);

    public static final native long new_MediaFormatVideo();

    public static final native long new_MediaTransportInfo();

    public static final native long new_OnCallMediaEventParam();

    public static final native long new_OnCallMediaStateParam();

    public static final native long new_OnCallMediaTransportStateParam();

    public static final native long new_OnCallRedirectedParam();

    public static final native long new_OnCallReplaceRequestParam();

    public static final native long new_OnCallReplacedParam();

    public static final native long new_OnCallRxOfferParam();

    public static final native long new_OnCallSdpCreatedParam();

    public static final native long new_OnCallStateParam();

    public static final native long new_OnCallTransferRequestParam();

    public static final native long new_OnCallTransferStatusParam();

    public static final native long new_OnCallTsxStateParam();

    public static final native long new_OnCreateMediaTransportParam();

    public static final native long new_OnDtmfDigitParam();

    public static final native long new_OnIncomingCallParam();

    public static final native long new_OnIncomingSubscribeParam();

    public static final native long new_OnInstantMessageParam();

    public static final native long new_OnInstantMessageStatusParam();

    public static final native long new_OnMwiInfoParam();

    public static final native long new_OnNatCheckStunServersCompleteParam();

    public static final native long new_OnNatDetectionCompleteParam();

    public static final native long new_OnRegStartedParam();

    public static final native long new_OnRegStateParam();

    public static final native long new_OnSelectAccountParam();

    public static final native long new_OnStreamCreatedParam();

    public static final native long new_OnStreamDestroyedParam();

    public static final native long new_OnTimerParam();

    public static final native long new_OnTransportStateParam();

    public static final native long new_OnTypingIndicationParam();

    public static final native long new_PresNotifyParam();

    public static final native long new_PresenceStatus();

    public static final native long new_RtcpSdes();

    public static final native long new_RtcpStat();

    public static final native long new_RtcpStreamStat();

    public static final native long new_RxMsgEvent();

    public static final native long new_SdpSession();

    public static final native long new_SendInstantMessageParam();

    public static final native long new_SendTypingIndicationParam();

    public static final native long new_SipEvent();

    public static final native long new_SipHeader();

    public static final native long new_SipHeaderVector__SWIG_0();

    public static final native long new_SipHeaderVector__SWIG_1(long j);

    public static final native long new_SipMediaType();

    public static final native long new_SipMultipartPart();

    public static final native long new_SipMultipartPartVector__SWIG_0();

    public static final native long new_SipMultipartPartVector__SWIG_1(long j);

    public static final native long new_SipRxData();

    public static final native long new_SipTransaction();

    public static final native long new_SipTxData();

    public static final native long new_SipTxOption();

    public static final native long new_StreamInfo();

    public static final native long new_StreamStat();

    public static final native long new_StringVector__SWIG_0();

    public static final native long new_StringVector__SWIG_1(long j);

    public static final native long new_TimeVal();

    public static final native long new_TimerEvent();

    public static final native long new_TlsConfig();

    public static final native long new_ToneDesc();

    public static final native long new_ToneDescVector__SWIG_0();

    public static final native long new_ToneDescVector__SWIG_1(long j);

    public static final native long new_ToneDigit();

    public static final native long new_ToneDigitMapDigit();

    public static final native long new_ToneDigitMapVector__SWIG_0();

    public static final native long new_ToneDigitMapVector__SWIG_1(long j);

    public static final native long new_ToneDigitVector__SWIG_0();

    public static final native long new_ToneDigitVector__SWIG_1(long j);

    public static final native long new_ToneGenerator();

    public static final native long new_TransportConfig();

    public static final native long new_TransportInfo();

    public static final native long new_TsxStateEvent();

    public static final native long new_TxErrorEvent();

    public static final native long new_TxMsgEvent();

    public static final native long new_UaConfig();

    public static final native long new_UserEvent();

    public static final native long new_Version();

    public static final native long new_pj_qos_params();

    public static final native long new_pjmedia_tone_desc();

    public static final native long new_pjmedia_tone_digit();

    public static final native long new_pjmedia_tone_digit_map();

    public static final native short pj_qos_params_dscp_val_get(long j, pj_qos_params org_pjsip_pjsua2_pj_qos_params);

    public static final native void pj_qos_params_dscp_val_set(long j, pj_qos_params org_pjsip_pjsua2_pj_qos_params, short s);

    public static final native short pj_qos_params_flags_get(long j, pj_qos_params org_pjsip_pjsua2_pj_qos_params);

    public static final native void pj_qos_params_flags_set(long j, pj_qos_params org_pjsip_pjsua2_pj_qos_params, short s);

    public static final native short pj_qos_params_so_prio_get(long j, pj_qos_params org_pjsip_pjsua2_pj_qos_params);

    public static final native void pj_qos_params_so_prio_set(long j, pj_qos_params org_pjsip_pjsua2_pj_qos_params, short s);

    public static final native int pj_qos_params_wmm_prio_get(long j, pj_qos_params org_pjsip_pjsua2_pj_qos_params);

    public static final native void pj_qos_params_wmm_prio_set(long j, pj_qos_params org_pjsip_pjsua2_pj_qos_params, int i);

    public static final native short pjmedia_tone_desc_flags_get(long j, pjmedia_tone_desc org_pjsip_pjsua2_pjmedia_tone_desc);

    public static final native void pjmedia_tone_desc_flags_set(long j, pjmedia_tone_desc org_pjsip_pjsua2_pjmedia_tone_desc, short s);

    public static final native short pjmedia_tone_desc_freq1_get(long j, pjmedia_tone_desc org_pjsip_pjsua2_pjmedia_tone_desc);

    public static final native void pjmedia_tone_desc_freq1_set(long j, pjmedia_tone_desc org_pjsip_pjsua2_pjmedia_tone_desc, short s);

    public static final native short pjmedia_tone_desc_freq2_get(long j, pjmedia_tone_desc org_pjsip_pjsua2_pjmedia_tone_desc);

    public static final native void pjmedia_tone_desc_freq2_set(long j, pjmedia_tone_desc org_pjsip_pjsua2_pjmedia_tone_desc, short s);

    public static final native short pjmedia_tone_desc_off_msec_get(long j, pjmedia_tone_desc org_pjsip_pjsua2_pjmedia_tone_desc);

    public static final native void pjmedia_tone_desc_off_msec_set(long j, pjmedia_tone_desc org_pjsip_pjsua2_pjmedia_tone_desc, short s);

    public static final native short pjmedia_tone_desc_on_msec_get(long j, pjmedia_tone_desc org_pjsip_pjsua2_pjmedia_tone_desc);

    public static final native void pjmedia_tone_desc_on_msec_set(long j, pjmedia_tone_desc org_pjsip_pjsua2_pjmedia_tone_desc, short s);

    public static final native short pjmedia_tone_desc_volume_get(long j, pjmedia_tone_desc org_pjsip_pjsua2_pjmedia_tone_desc);

    public static final native void pjmedia_tone_desc_volume_set(long j, pjmedia_tone_desc org_pjsip_pjsua2_pjmedia_tone_desc, short s);

    public static final native char pjmedia_tone_digit_digit_get(long j, pjmedia_tone_digit org_pjsip_pjsua2_pjmedia_tone_digit);

    public static final native void pjmedia_tone_digit_digit_set(long j, pjmedia_tone_digit org_pjsip_pjsua2_pjmedia_tone_digit, char c);

    public static final native long pjmedia_tone_digit_map_count_get(long j, pjmedia_tone_digit_map org_pjsip_pjsua2_pjmedia_tone_digit_map);

    public static final native void pjmedia_tone_digit_map_count_set(long j, pjmedia_tone_digit_map org_pjsip_pjsua2_pjmedia_tone_digit_map, long j2);

    public static final native short pjmedia_tone_digit_off_msec_get(long j, pjmedia_tone_digit org_pjsip_pjsua2_pjmedia_tone_digit);

    public static final native void pjmedia_tone_digit_off_msec_set(long j, pjmedia_tone_digit org_pjsip_pjsua2_pjmedia_tone_digit, short s);

    public static final native short pjmedia_tone_digit_on_msec_get(long j, pjmedia_tone_digit org_pjsip_pjsua2_pjmedia_tone_digit);

    public static final native void pjmedia_tone_digit_on_msec_set(long j, pjmedia_tone_digit org_pjsip_pjsua2_pjmedia_tone_digit, short s);

    public static final native short pjmedia_tone_digit_volume_get(long j, pjmedia_tone_digit org_pjsip_pjsua2_pjmedia_tone_digit);

    public static final native void pjmedia_tone_digit_volume_set(long j, pjmedia_tone_digit org_pjsip_pjsua2_pjmedia_tone_digit, short s);

    private static final native void swig_module_init();

    public static void SwigDirector_Buddy_onBuddyState(Buddy buddy) {
        buddy.onBuddyState();
    }

    public static boolean SwigDirector_FindBuddyMatch_match(FindBuddyMatch findBuddyMatch, String str, long j) {
        return findBuddyMatch.match(str, new Buddy(j, false));
    }

    public static void SwigDirector_Account_onIncomingCall(Account account, long j) {
        account.onIncomingCall(new OnIncomingCallParam(j, false));
    }

    public static void SwigDirector_Account_onRegStarted(Account account, long j) {
        account.onRegStarted(new OnRegStartedParam(j, false));
    }

    public static void SwigDirector_Account_onRegState(Account account, long j) {
        account.onRegState(new OnRegStateParam(j, false));
    }

    public static void SwigDirector_Account_onIncomingSubscribe(Account account, long j) {
        account.onIncomingSubscribe(new OnIncomingSubscribeParam(j, false));
    }

    public static void SwigDirector_Account_onInstantMessage(Account account, long j) {
        account.onInstantMessage(new OnInstantMessageParam(j, false));
    }

    public static void SwigDirector_Account_onInstantMessageStatus(Account account, long j) {
        account.onInstantMessageStatus(new OnInstantMessageStatusParam(j, false));
    }

    public static void SwigDirector_Account_onTypingIndication(Account account, long j) {
        account.onTypingIndication(new OnTypingIndicationParam(j, false));
    }

    public static void SwigDirector_Account_onMwiInfo(Account account, long j) {
        account.onMwiInfo(new OnMwiInfoParam(j, false));
    }

    public static void SwigDirector_Call_onCallState(Call call, long j) {
        call.onCallState(new OnCallStateParam(j, false));
    }

    public static void SwigDirector_Call_onCallTsxState(Call call, long j) {
        call.onCallTsxState(new OnCallTsxStateParam(j, false));
    }

    public static void SwigDirector_Call_onCallMediaState(Call call, long j) {
        call.onCallMediaState(new OnCallMediaStateParam(j, false));
    }

    public static void SwigDirector_Call_onCallSdpCreated(Call call, long j) {
        call.onCallSdpCreated(new OnCallSdpCreatedParam(j, false));
    }

    public static void SwigDirector_Call_onStreamCreated(Call call, long j) {
        call.onStreamCreated(new OnStreamCreatedParam(j, false));
    }

    public static void SwigDirector_Call_onStreamDestroyed(Call call, long j) {
        call.onStreamDestroyed(new OnStreamDestroyedParam(j, false));
    }

    public static void SwigDirector_Call_onDtmfDigit(Call call, long j) {
        call.onDtmfDigit(new OnDtmfDigitParam(j, false));
    }

    public static void SwigDirector_Call_onCallTransferRequest(Call call, long j) {
        call.onCallTransferRequest(new OnCallTransferRequestParam(j, false));
    }

    public static void SwigDirector_Call_onCallTransferStatus(Call call, long j) {
        call.onCallTransferStatus(new OnCallTransferStatusParam(j, false));
    }

    public static void SwigDirector_Call_onCallReplaceRequest(Call call, long j) {
        call.onCallReplaceRequest(new OnCallReplaceRequestParam(j, false));
    }

    public static void SwigDirector_Call_onCallReplaced(Call call, long j) {
        call.onCallReplaced(new OnCallReplacedParam(j, false));
    }

    public static void SwigDirector_Call_onCallRxOffer(Call call, long j) {
        call.onCallRxOffer(new OnCallRxOfferParam(j, false));
    }

    public static void SwigDirector_Call_onInstantMessage(Call call, long j) {
        call.onInstantMessage(new OnInstantMessageParam(j, false));
    }

    public static void SwigDirector_Call_onInstantMessageStatus(Call call, long j) {
        call.onInstantMessageStatus(new OnInstantMessageStatusParam(j, false));
    }

    public static void SwigDirector_Call_onTypingIndication(Call call, long j) {
        call.onTypingIndication(new OnTypingIndicationParam(j, false));
    }

    public static int SwigDirector_Call_onCallRedirected(Call call, long j) {
        return call.onCallRedirected(new OnCallRedirectedParam(j, false)).swigValue();
    }

    public static void SwigDirector_Call_onCallMediaTransportState(Call call, long j) {
        call.onCallMediaTransportState(new OnCallMediaTransportStateParam(j, false));
    }

    public static void SwigDirector_Call_onCallMediaEvent(Call call, long j) {
        call.onCallMediaEvent(new OnCallMediaEventParam(j, false));
    }

    public static void SwigDirector_Call_onCreateMediaTransport(Call call, long j) {
        call.onCreateMediaTransport(new OnCreateMediaTransportParam(j, false));
    }

    public static void SwigDirector_LogWriter_write(LogWriter logWriter, long j) {
        logWriter.write(new LogEntry(j, false));
    }

    public static void SwigDirector_Endpoint_onNatDetectionComplete(Endpoint endpoint, long j) {
        endpoint.onNatDetectionComplete(new OnNatDetectionCompleteParam(j, false));
    }

    public static void SwigDirector_Endpoint_onNatCheckStunServersComplete(Endpoint endpoint, long j) {
        endpoint.onNatCheckStunServersComplete(new OnNatCheckStunServersCompleteParam(j, false));
    }

    public static void SwigDirector_Endpoint_onTransportState(Endpoint endpoint, long j) {
        endpoint.onTransportState(new OnTransportStateParam(j, false));
    }

    public static void SwigDirector_Endpoint_onTimer(Endpoint endpoint, long j) {
        endpoint.onTimer(new OnTimerParam(j, false));
    }

    public static void SwigDirector_Endpoint_onSelectAccount(Endpoint endpoint, long j) {
        endpoint.onSelectAccount(new OnSelectAccountParam(j, false));
    }

    static {
        swig_module_init();
    }
}
