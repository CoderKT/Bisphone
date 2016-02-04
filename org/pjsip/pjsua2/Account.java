package org.pjsip.pjsua2;

public class Account {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected Account(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(Account account) {
        return account == null ? 0 : account.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_Account(this.swigCPtr);
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
        pjsua2JNI.Account_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        pjsua2JNI.Account_change_ownership(this, this.swigCPtr, true);
    }

    public Account() {
        this(pjsua2JNI.new_Account(), true);
        pjsua2JNI.Account_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }

    public void create(AccountConfig accountConfig, boolean z) {
        pjsua2JNI.Account_create__SWIG_0(this.swigCPtr, this, AccountConfig.getCPtr(accountConfig), accountConfig, z);
    }

    public void create(AccountConfig accountConfig) {
        pjsua2JNI.Account_create__SWIG_1(this.swigCPtr, this, AccountConfig.getCPtr(accountConfig), accountConfig);
    }

    public void modify(AccountConfig accountConfig) {
        pjsua2JNI.Account_modify(this.swigCPtr, this, AccountConfig.getCPtr(accountConfig), accountConfig);
    }

    public boolean isValid() {
        return pjsua2JNI.Account_isValid(this.swigCPtr, this);
    }

    public void setDefault() {
        pjsua2JNI.Account_setDefault(this.swigCPtr, this);
    }

    public boolean isDefault() {
        return pjsua2JNI.Account_isDefault(this.swigCPtr, this);
    }

    public int getId() {
        return pjsua2JNI.Account_getId(this.swigCPtr, this);
    }

    public static Account lookup(int i) {
        long Account_lookup = pjsua2JNI.Account_lookup(i);
        return Account_lookup == 0 ? null : new Account(Account_lookup, false);
    }

    public AccountInfo getInfo() {
        return new AccountInfo(pjsua2JNI.Account_getInfo(this.swigCPtr, this), true);
    }

    public void setRegistration(boolean z) {
        pjsua2JNI.Account_setRegistration(this.swigCPtr, this, z);
    }

    public void setOnlineStatus(PresenceStatus presenceStatus) {
        pjsua2JNI.Account_setOnlineStatus(this.swigCPtr, this, PresenceStatus.getCPtr(presenceStatus), presenceStatus);
    }

    public void setTransport(int i) {
        pjsua2JNI.Account_setTransport(this.swigCPtr, this, i);
    }

    public void presNotify(PresNotifyParam presNotifyParam) {
        pjsua2JNI.Account_presNotify(this.swigCPtr, this, PresNotifyParam.getCPtr(presNotifyParam), presNotifyParam);
    }

    public BuddyVector enumBuddies() {
        return new BuddyVector(pjsua2JNI.Account_enumBuddies(this.swigCPtr, this), false);
    }

    public Buddy findBuddy(String str, FindBuddyMatch findBuddyMatch) {
        long Account_findBuddy__SWIG_0 = pjsua2JNI.Account_findBuddy__SWIG_0(this.swigCPtr, this, str, FindBuddyMatch.getCPtr(findBuddyMatch), findBuddyMatch);
        return Account_findBuddy__SWIG_0 == 0 ? null : new Buddy(Account_findBuddy__SWIG_0, false);
    }

    public Buddy findBuddy(String str) {
        long Account_findBuddy__SWIG_1 = pjsua2JNI.Account_findBuddy__SWIG_1(this.swigCPtr, this, str);
        return Account_findBuddy__SWIG_1 == 0 ? null : new Buddy(Account_findBuddy__SWIG_1, false);
    }

    public void addBuddy(Buddy buddy) {
        pjsua2JNI.Account_addBuddy(this.swigCPtr, this, Buddy.getCPtr(buddy), buddy);
    }

    public void removeBuddy(Buddy buddy) {
        pjsua2JNI.Account_removeBuddy(this.swigCPtr, this, Buddy.getCPtr(buddy), buddy);
    }

    public void onIncomingCall(OnIncomingCallParam onIncomingCallParam) {
        if (getClass() == Account.class) {
            pjsua2JNI.Account_onIncomingCall(this.swigCPtr, this, OnIncomingCallParam.getCPtr(onIncomingCallParam), onIncomingCallParam);
        } else {
            pjsua2JNI.Account_onIncomingCallSwigExplicitAccount(this.swigCPtr, this, OnIncomingCallParam.getCPtr(onIncomingCallParam), onIncomingCallParam);
        }
    }

    public void onRegStarted(OnRegStartedParam onRegStartedParam) {
        if (getClass() == Account.class) {
            pjsua2JNI.Account_onRegStarted(this.swigCPtr, this, OnRegStartedParam.getCPtr(onRegStartedParam), onRegStartedParam);
        } else {
            pjsua2JNI.Account_onRegStartedSwigExplicitAccount(this.swigCPtr, this, OnRegStartedParam.getCPtr(onRegStartedParam), onRegStartedParam);
        }
    }

    public void onRegState(OnRegStateParam onRegStateParam) {
        if (getClass() == Account.class) {
            pjsua2JNI.Account_onRegState(this.swigCPtr, this, OnRegStateParam.getCPtr(onRegStateParam), onRegStateParam);
        } else {
            pjsua2JNI.Account_onRegStateSwigExplicitAccount(this.swigCPtr, this, OnRegStateParam.getCPtr(onRegStateParam), onRegStateParam);
        }
    }

    public void onIncomingSubscribe(OnIncomingSubscribeParam onIncomingSubscribeParam) {
        if (getClass() == Account.class) {
            pjsua2JNI.Account_onIncomingSubscribe(this.swigCPtr, this, OnIncomingSubscribeParam.getCPtr(onIncomingSubscribeParam), onIncomingSubscribeParam);
        } else {
            pjsua2JNI.Account_onIncomingSubscribeSwigExplicitAccount(this.swigCPtr, this, OnIncomingSubscribeParam.getCPtr(onIncomingSubscribeParam), onIncomingSubscribeParam);
        }
    }

    public void onInstantMessage(OnInstantMessageParam onInstantMessageParam) {
        if (getClass() == Account.class) {
            pjsua2JNI.Account_onInstantMessage(this.swigCPtr, this, OnInstantMessageParam.getCPtr(onInstantMessageParam), onInstantMessageParam);
        } else {
            pjsua2JNI.Account_onInstantMessageSwigExplicitAccount(this.swigCPtr, this, OnInstantMessageParam.getCPtr(onInstantMessageParam), onInstantMessageParam);
        }
    }

    public void onInstantMessageStatus(OnInstantMessageStatusParam onInstantMessageStatusParam) {
        if (getClass() == Account.class) {
            pjsua2JNI.Account_onInstantMessageStatus(this.swigCPtr, this, OnInstantMessageStatusParam.getCPtr(onInstantMessageStatusParam), onInstantMessageStatusParam);
        } else {
            pjsua2JNI.Account_onInstantMessageStatusSwigExplicitAccount(this.swigCPtr, this, OnInstantMessageStatusParam.getCPtr(onInstantMessageStatusParam), onInstantMessageStatusParam);
        }
    }

    public void onTypingIndication(OnTypingIndicationParam onTypingIndicationParam) {
        if (getClass() == Account.class) {
            pjsua2JNI.Account_onTypingIndication(this.swigCPtr, this, OnTypingIndicationParam.getCPtr(onTypingIndicationParam), onTypingIndicationParam);
        } else {
            pjsua2JNI.Account_onTypingIndicationSwigExplicitAccount(this.swigCPtr, this, OnTypingIndicationParam.getCPtr(onTypingIndicationParam), onTypingIndicationParam);
        }
    }

    public void onMwiInfo(OnMwiInfoParam onMwiInfoParam) {
        if (getClass() == Account.class) {
            pjsua2JNI.Account_onMwiInfo(this.swigCPtr, this, OnMwiInfoParam.getCPtr(onMwiInfoParam), onMwiInfoParam);
        } else {
            pjsua2JNI.Account_onMwiInfoSwigExplicitAccount(this.swigCPtr, this, OnMwiInfoParam.getCPtr(onMwiInfoParam), onMwiInfoParam);
        }
    }
}
