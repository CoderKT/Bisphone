package app.events.group;

import app.messaging.group.GroupListener;
import app.xmpp.packet.groupv2.SettingXE;

public class ChangeGroupSettingEvent {
    SettingXE f2423a;
    String f2424b;
    GroupListener f2425c;

    public GroupListener m4860a() {
        return this.f2425c;
    }

    public SettingXE m4864b() {
        return this.f2423a;
    }

    public String m4865c() {
        return this.f2424b;
    }

    public void m4862a(SettingXE settingXE) {
        this.f2423a = settingXE;
    }

    public void m4861a(GroupListener groupListener) {
        this.f2425c = groupListener;
    }

    public void m4863a(String str) {
        this.f2424b = str;
    }
}
