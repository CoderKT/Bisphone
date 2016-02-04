package app.messaging.group;

import app.common.entity.ContactEntity.TYPE;

public class GroupSettingModel {
    private String f3733a;
    private String f3734b;
    private String f3735c;
    private String f3736d;
    private String f3737e;
    private String f3738f;
    private boolean f3739g;
    private boolean f3740h;
    private TYPE f3741i;

    public GroupSettingModel() {
        this.f3733a = "";
        this.f3734b = "";
        this.f3735c = "";
        this.f3736d = "";
        this.f3737e = "";
        this.f3739g = false;
        this.f3741i = TYPE.LOCAL;
        this.f3738f = "";
    }

    public GroupSettingModel(String str, String str2, String str3, String str4, String str5, boolean z, TYPE type, String str6) {
        this.f3733a = str;
        this.f3734b = str2;
        this.f3735c = str3;
        this.f3736d = str4;
        this.f3737e = str5;
        this.f3739g = z;
        this.f3741i = type;
        this.f3738f = str6;
    }

    public String m6332a() {
        return this.f3736d;
    }

    public void m6333a(String str) {
        this.f3736d = str;
    }

    public String m6335b() {
        return this.f3734b;
    }

    public String m6337c() {
        return this.f3733a;
    }

    public String m6338d() {
        return this.f3737e;
    }

    public boolean m6339e() {
        return this.f3739g;
    }

    public TYPE m6340f() {
        return this.f3741i;
    }

    public String m6341g() {
        return this.f3738f;
    }

    public void m6336b(String str) {
        this.f3738f = str;
    }

    public boolean m6342h() {
        return this.f3740h;
    }

    public void m6334a(boolean z) {
        this.f3740h = z;
    }
}
