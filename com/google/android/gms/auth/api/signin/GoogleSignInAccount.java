package com.google.android.gms.auth.api.signin;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzmn;
import com.google.android.gms.internal.zzmp;
import org.json.JSONObject;

public class GoogleSignInAccount implements SafeParcelable {
    public static final Creator<GoogleSignInAccount> CREATOR;
    public static zzmn f5625a;
    final int f5626b;
    private String f5627c;
    private String f5628d;
    private String f5629e;
    private String f5630f;
    private Uri f5631g;
    private String f5632h;
    private long f5633i;

    static {
        CREATOR = new zzc();
        f5625a = zzmp.m9301a();
    }

    GoogleSignInAccount(int i, String str, String str2, String str3, String str4, Uri uri, String str5, long j) {
        this.f5626b = i;
        this.f5627c = zzx.m8720a(str);
        this.f5628d = str2;
        this.f5629e = str3;
        this.f5630f = str4;
        this.f5631g = uri;
        this.f5632h = str5;
        this.f5633i = j;
    }

    private JSONObject m8288i() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", m8289a());
            if (m8290b() != null) {
                jSONObject.put("tokenId", m8290b());
            }
            if (m8291c() != null) {
                jSONObject.put("email", m8291c());
            }
            if (m8292d() != null) {
                jSONObject.put("displayName", m8292d());
            }
            if (m8293e() != null) {
                jSONObject.put("photoUrl", m8293e().toString());
            }
            if (m8294f() != null) {
                jSONObject.put("serverAuthCode", m8294f());
            }
            jSONObject.put("expirationTime", this.f5633i);
            return jSONObject;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public String m8289a() {
        return this.f5627c;
    }

    public String m8290b() {
        return this.f5628d;
    }

    public String m8291c() {
        return this.f5629e;
    }

    public String m8292d() {
        return this.f5630f;
    }

    public int describeContents() {
        return 0;
    }

    public Uri m8293e() {
        return this.f5631g;
    }

    public boolean equals(Object obj) {
        return !(obj instanceof GoogleSignInAccount) ? false : ((GoogleSignInAccount) obj).m8296h().equals(m8296h());
    }

    public String m8294f() {
        return this.f5632h;
    }

    public long m8295g() {
        return this.f5633i;
    }

    public String m8296h() {
        return m8288i().toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzc.m8297a(this, parcel, i);
    }
}
