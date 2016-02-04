package app.xmpp;

import android.text.TextUtils;

public class JabberId {
    private String f4934a;
    private String f4935b;
    private String f4936c;

    public JabberId(String str, String str2, String str3) {
        this.f4934a = str;
        this.f4935b = str2;
        this.f4936c = str3;
    }

    public String m7387a() {
        return this.f4934a;
    }

    public String m7388b() {
        return this.f4935b;
    }

    public String m7389c() {
        return this.f4936c;
    }

    public String m7390d() {
        return this.f4934a + "@" + this.f4935b;
    }

    public String m7391e() {
        if (this.f4936c == null) {
            return this.f4934a + "@" + this.f4935b;
        }
        return this.f4934a + "@" + this.f4935b + "/" + this.f4936c;
    }

    public String m7392f() {
        return this.f4934a + "@" + this.f4935b;
    }

    public static JabberId m7386a(String str) {
        if (str == null || !str.contains("@") || str.indexOf("@") != str.lastIndexOf("@") || str.indexOf("@") == 0 || str.indexOf("@") == str.length() - 1) {
            return null;
        }
        Object substring;
        Object substring2;
        if (!str.contains("/")) {
            substring = str.substring(0, str.indexOf("@"));
            substring2 = str.substring(str.indexOf("@") + 1, str.length());
            if (TextUtils.isEmpty(substring) || TextUtils.isEmpty(substring2)) {
                return null;
            }
            return new JabberId(substring, substring2, null);
        } else if (str.indexOf("/") != str.lastIndexOf("/") || str.indexOf("/") == 0 || str.indexOf("/") == str.length() - 1 || str.indexOf("@") > str.indexOf("/")) {
            return null;
        } else {
            Object substring3 = str.substring(0, str.indexOf("@"));
            substring = str.substring(str.indexOf("@") + 1, str.indexOf("/"));
            substring2 = str.substring(str.indexOf("/") + 1, str.length());
            if (TextUtils.isEmpty(substring3) || TextUtils.isEmpty(substring) || TextUtils.isEmpty(substring2)) {
                return null;
            }
            return new JabberId(substring3, substring, substring2);
        }
    }

    public String toString() {
        return m7391e();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        JabberId jabberId = (JabberId) obj;
        if (this.f4935b == null ? jabberId.f4935b != null : !this.f4935b.equals(jabberId.f4935b)) {
            return false;
        }
        if (this.f4934a != null) {
            if (this.f4934a.equals(jabberId.f4934a)) {
                return true;
            }
        } else if (jabberId.f4934a == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        if (this.f4934a != null) {
            hashCode = this.f4934a.hashCode();
        } else {
            hashCode = 0;
        }
        int i2 = hashCode * 31;
        if (this.f4935b != null) {
            hashCode = this.f4935b.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + i2) * 31;
        if (this.f4936c != null) {
            i = this.f4936c.hashCode();
        }
        return hashCode + i;
    }
}
