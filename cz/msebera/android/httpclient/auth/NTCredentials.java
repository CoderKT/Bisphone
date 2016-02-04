package cz.msebera.android.httpclient.auth;

import cz.msebera.android.httpclient.util.LangUtils;
import java.io.Serializable;
import java.security.Principal;

public class NTCredentials implements Credentials, Serializable {
    private final NTUserPrincipal f7239a;
    private final String f7240b;
    private final String f7241c;

    public Principal m11443a() {
        return this.f7239a;
    }

    public String m11445c() {
        return this.f7239a.m11449b();
    }

    public String m11444b() {
        return this.f7240b;
    }

    public String m11446d() {
        return this.f7239a.m11448a();
    }

    public String m11447e() {
        return this.f7241c;
    }

    public int hashCode() {
        return LangUtils.m12767a(LangUtils.m12767a(17, this.f7239a), this.f7241c);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof NTCredentials) {
            NTCredentials nTCredentials = (NTCredentials) obj;
            if (LangUtils.m12769a(this.f7239a, nTCredentials.f7239a) && LangUtils.m12769a(this.f7241c, nTCredentials.f7241c)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[principal: ");
        stringBuilder.append(this.f7239a);
        stringBuilder.append("][workstation: ");
        stringBuilder.append(this.f7241c);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
