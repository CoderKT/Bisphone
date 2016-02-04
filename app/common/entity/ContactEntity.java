package app.common.entity;

import app.Main;
import app.account.AccountManager;
import java.util.Comparator;

public class ContactEntity implements Comparator<ContactEntity> {
    String f2106a;
    String f2107b;
    long f2108c;
    String f2109d;
    String f2110e;
    String f2111f;
    STATUS f2112g;
    TYPE f2113h;
    long f2114i;
    boolean f2115j;
    boolean f2116k;
    String f2117l;
    String f2118m;
    String f2119n;
    String f2120o;
    String f2121p;
    Long f2122q;

    public enum STATUS {
        ADDED,
        REMOVED,
        MODIFIED,
        SYNCED,
        MODIFIED_LOCAL
    }

    public enum TYPE {
        SELF,
        LOCAL,
        REMOTE
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return m4179a((ContactEntity) obj, (ContactEntity) obj2);
    }

    public int m4179a(ContactEntity contactEntity, ContactEntity contactEntity2) {
        return contactEntity2.m4200g().compareTo(contactEntity.m4200g());
    }

    public String toString() {
        return "+" + this.f2111f;
    }

    public static TYPE m4178a(String str) {
        if (str == null) {
            return null;
        }
        return TYPE.valueOf(str);
    }

    public String m4182a() {
        return this.f2121p;
    }

    public String m4189b() {
        return this.f2106a;
    }

    public String m4192c() {
        return this.f2107b;
    }

    public long m4193d() {
        return this.f2108c;
    }

    public String m4196e() {
        return this.f2109d;
    }

    public String m4198f() {
        return this.f2110e;
    }

    public String m4200g() {
        return this.f2111f;
    }

    public STATUS m4201h() {
        return this.f2112g;
    }

    public long m4203i() {
        return this.f2114i;
    }

    public boolean m4206j() {
        return this.f2115j;
    }

    public boolean m4208k() {
        return this.f2116k;
    }

    public String m4209l() {
        return this.f2117l;
    }

    public TYPE m4210m() {
        return this.f2113h;
    }

    public String m4211n() {
        return this.f2118m;
    }

    public String m4212o() {
        return this.f2119n;
    }

    public String m4213p() {
        return this.f2120o;
    }

    public Long m4214q() {
        return this.f2122q;
    }

    public String m4183a(boolean z) {
        if (AccountManager.m3934a().m3937c().equals(this.f2111f)) {
            return Main.f1927b.getString(2131296560);
        }
        if (this.f2109d != null) {
            return this.f2109d;
        }
        if (this.f2117l != null) {
            return this.f2117l;
        }
        if (z) {
            return "+" + this.f2111f;
        }
        return Main.f1927b.getString(2131296307);
    }

    public ContactEntity m4187b(String str) {
        this.f2106a = str;
        return this;
    }

    public ContactEntity m4190c(String str) {
        this.f2107b = str;
        return this;
    }

    public ContactEntity m4180a(long j) {
        this.f2108c = j;
        return this;
    }

    public ContactEntity m4194d(String str) {
        this.f2109d = str;
        return this;
    }

    public ContactEntity m4195e(String str) {
        this.f2110e = str;
        return this;
    }

    public ContactEntity m4197f(String str) {
        this.f2111f = str;
        return this;
    }

    public ContactEntity m4181a(STATUS status) {
        this.f2112g = status;
        return this;
    }

    public ContactEntity m4186b(long j) {
        this.f2114i = j;
        return this;
    }

    public ContactEntity m4188b(boolean z) {
        this.f2115j = z;
        return this;
    }

    public ContactEntity m4191c(boolean z) {
        this.f2116k = z;
        return this;
    }

    public ContactEntity m4199g(String str) {
        this.f2117l = str;
        return this;
    }

    public void m4202h(String str) {
        this.f2121p = str;
    }

    public void m4204i(String str) {
        this.f2118m = str;
    }

    public void m4205j(String str) {
        this.f2119n = str;
    }

    public void m4207k(String str) {
        this.f2120o = str;
    }

    public void m4185a(Long l) {
        this.f2122q = l;
    }

    public void m4184a(TYPE type) {
        this.f2113h = type;
    }
}
