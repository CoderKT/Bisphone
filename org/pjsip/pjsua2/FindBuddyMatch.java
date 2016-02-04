package org.pjsip.pjsua2;

public class FindBuddyMatch {
    protected boolean swigCMemOwn;
    private long swigCPtr;

    protected FindBuddyMatch(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(FindBuddyMatch findBuddyMatch) {
        return findBuddyMatch == null ? 0 : findBuddyMatch.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_FindBuddyMatch(this.swigCPtr);
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
        pjsua2JNI.FindBuddyMatch_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        pjsua2JNI.FindBuddyMatch_change_ownership(this, this.swigCPtr, true);
    }

    public boolean match(String str, Buddy buddy) {
        if (getClass() == FindBuddyMatch.class) {
            return pjsua2JNI.FindBuddyMatch_match(this.swigCPtr, this, str, Buddy.getCPtr(buddy), buddy);
        }
        return pjsua2JNI.FindBuddyMatch_matchSwigExplicitFindBuddyMatch(this.swigCPtr, this, str, Buddy.getCPtr(buddy), buddy);
    }

    public FindBuddyMatch() {
        this(pjsua2JNI.new_FindBuddyMatch(), true);
        pjsua2JNI.FindBuddyMatch_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }
}
