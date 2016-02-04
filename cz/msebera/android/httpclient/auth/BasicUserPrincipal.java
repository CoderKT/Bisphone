package cz.msebera.android.httpclient.auth;

import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.LangUtils;
import java.io.Serializable;
import java.security.Principal;

public final class BasicUserPrincipal implements Serializable, Principal {
    private final String f7235a;

    public BasicUserPrincipal(String str) {
        Args.m12722a((Object) str, "User name");
        this.f7235a = str;
    }

    public String getName() {
        return this.f7235a;
    }

    public int hashCode() {
        return LangUtils.m12767a(17, this.f7235a);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BasicUserPrincipal) {
            if (LangUtils.m12769a(this.f7235a, ((BasicUserPrincipal) obj).f7235a)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[principal: ");
        stringBuilder.append(this.f7235a);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
