package cz.msebera.android.httpclient.auth;

import cz.msebera.android.httpclient.util.LangUtils;
import java.io.Serializable;
import java.security.Principal;

public class NTUserPrincipal implements Serializable, Principal {
    private final String f7242a;
    private final String f7243b;
    private final String f7244c;

    public String getName() {
        return this.f7244c;
    }

    public String m11448a() {
        return this.f7243b;
    }

    public String m11449b() {
        return this.f7242a;
    }

    public int hashCode() {
        return LangUtils.m12767a(LangUtils.m12767a(17, this.f7242a), this.f7243b);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof NTUserPrincipal) {
            NTUserPrincipal nTUserPrincipal = (NTUserPrincipal) obj;
            if (LangUtils.m12769a(this.f7242a, nTUserPrincipal.f7242a) && LangUtils.m12769a(this.f7243b, nTUserPrincipal.f7243b)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return this.f7244c;
    }
}
