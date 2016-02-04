package app.common;

public class GroupV2RolesPrivilege {
    private boolean f2010a;
    private boolean f2011b;
    private boolean f2012c;
    private boolean f2013d;
    private boolean f2014e;

    public GroupV2RolesPrivilege() {
        this.f2013d = true;
        this.f2014e = false;
        this.f2010a = false;
        this.f2012c = false;
        this.f2011b = false;
    }

    public boolean m4046a() {
        return this.f2010a;
    }

    public boolean m4048b() {
        return this.f2011b;
    }

    public boolean m4050c() {
        return this.f2012c;
    }

    public boolean m4052d() {
        return this.f2014e;
    }

    public void m4045a(boolean z) {
        this.f2010a = z;
    }

    public void m4047b(boolean z) {
        this.f2011b = z;
    }

    public void m4049c(boolean z) {
        this.f2012c = z;
    }

    public void m4051d(boolean z) {
        this.f2014e = z;
    }
}
