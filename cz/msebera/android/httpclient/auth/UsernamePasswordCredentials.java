package cz.msebera.android.httpclient.auth;

import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.LangUtils;
import java.io.Serializable;
import java.security.Principal;

public class UsernamePasswordCredentials implements Credentials, Serializable {
    private final BasicUserPrincipal f7245a;
    private final String f7246b;

    public UsernamePasswordCredentials(String str) {
        Args.m12722a((Object) str, "Username:password string");
        int indexOf = str.indexOf(58);
        if (indexOf >= 0) {
            this.f7245a = new BasicUserPrincipal(str.substring(0, indexOf));
            this.f7246b = str.substring(indexOf + 1);
            return;
        }
        this.f7245a = new BasicUserPrincipal(str);
        this.f7246b = null;
    }

    public Principal m11450a() {
        return this.f7245a;
    }

    public String m11451b() {
        return this.f7246b;
    }

    public int hashCode() {
        return this.f7245a.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof UsernamePasswordCredentials) {
            if (LangUtils.m12769a(this.f7245a, ((UsernamePasswordCredentials) obj).f7245a)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return this.f7245a.toString();
    }
}
