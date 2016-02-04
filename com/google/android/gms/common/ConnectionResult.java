package com.google.android.gms.common;

import android.app.Activity;
import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import org.jivesoftware.smack.packet.Message;
import se.emilsjolander.stickylistheaders.C1128R;

public final class ConnectionResult implements SafeParcelable {
    public static final Creator<ConnectionResult> CREATOR;
    public static final ConnectionResult f5634a;
    final int f5635b;
    private final int f5636c;
    private final PendingIntent f5637d;
    private final String f5638e;

    static {
        f5634a = new ConnectionResult(0);
        CREATOR = new zzb();
    }

    public ConnectionResult(int i) {
        this(i, null, null);
    }

    ConnectionResult(int i, int i2, PendingIntent pendingIntent, String str) {
        this.f5635b = i;
        this.f5636c = i2;
        this.f5637d = pendingIntent;
        this.f5638e = str;
    }

    public ConnectionResult(int i, PendingIntent pendingIntent) {
        this(i, pendingIntent, null);
    }

    public ConnectionResult(int i, PendingIntent pendingIntent, String str) {
        this(1, i, pendingIntent, str);
    }

    static String m8300a(int i) {
        switch (i) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                return "SUCCESS";
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                return "SERVICE_MISSING";
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                return "SERVICE_VERSION_UPDATE_REQUIRED";
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                return "SERVICE_DISABLED";
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                return "SIGN_IN_REQUIRED";
            case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                return "INVALID_ACCOUNT";
            case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                return "RESOLUTION_REQUIRED";
            case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                return "NETWORK_ERROR";
            case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                return "INTERNAL_ERROR";
            case C1128R.styleable.StickyListHeadersListView_android_listSelector /*9*/:
                return "SERVICE_INVALID";
            case C1128R.styleable.StickyListHeadersListView_android_drawSelectorOnTop /*10*/:
                return "DEVELOPER_ERROR";
            case C1128R.styleable.StickyListHeadersListView_android_stackFromBottom /*11*/:
                return "LICENSE_CHECK_FAILED";
            case C1128R.styleable.StickyListHeadersListView_android_transcriptMode /*13*/:
                return "CANCELED";
            case C1128R.styleable.StickyListHeadersListView_android_cacheColorHint /*14*/:
                return "TIMEOUT";
            case C1128R.styleable.StickyListHeadersListView_android_divider /*15*/:
                return "INTERRUPTED";
            case C1128R.styleable.StickyListHeadersListView_android_dividerHeight /*16*/:
                return "API_UNAVAILABLE";
            case C1128R.styleable.StickyListHeadersListView_android_choiceMode /*17*/:
                return "SIGN_IN_FAILED";
            case C1128R.styleable.StickyListHeadersListView_android_fastScrollEnabled /*18*/:
                return "SERVICE_UPDATING";
            case C1128R.styleable.StickyListHeadersListView_android_overScrollMode /*19*/:
                return "SERVICE_MISSING_PERMISSION";
            default:
                return "UNKNOWN_ERROR_CODE(" + i + ")";
        }
    }

    public void m8301a(Activity activity, int i) {
        if (m8302a()) {
            activity.startIntentSenderForResult(this.f5637d.getIntentSender(), i, null, 0, 0, 0);
        }
    }

    public boolean m8302a() {
        return (this.f5636c == 0 || this.f5637d == null) ? false : true;
    }

    public boolean m8303b() {
        return this.f5636c == 0;
    }

    public int m8304c() {
        return this.f5636c;
    }

    public PendingIntent m8305d() {
        return this.f5637d;
    }

    public int describeContents() {
        return 0;
    }

    public String m8306e() {
        return this.f5638e;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ConnectionResult)) {
            return false;
        }
        ConnectionResult connectionResult = (ConnectionResult) obj;
        return this.f5636c == connectionResult.f5636c && zzw.m8717a(this.f5637d, connectionResult.f5637d) && zzw.m8717a(this.f5638e, connectionResult.f5638e);
    }

    public int hashCode() {
        return zzw.m8715a(Integer.valueOf(this.f5636c), this.f5637d, this.f5638e);
    }

    public String toString() {
        return zzw.m8716a((Object) this).m8714a("statusCode", m8300a(this.f5636c)).m8714a("resolution", this.f5637d).m8714a(Message.ELEMENT, this.f5638e).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzb.m8769a(this, parcel, i);
    }
}
