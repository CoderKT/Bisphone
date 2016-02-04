package app.xmpp.packet.groupv2;

import app.xmpp.packet.groupv2.SettingXE.NotificationState;

public class GetGroupSettingAckXE extends AbstractXE {
    private NotificationState f5140a;
    private Integer f5141b;

    public NotificationState m7601a() {
        return this.f5140a;
    }

    public void m7602a(NotificationState notificationState) {
        this.f5140a = notificationState;
    }

    public void m7603a(Integer num) {
        this.f5141b = num;
    }

    public String getElementName() {
        return "get-member-setting-ack";
    }

    public CharSequence toXML() {
        CharSequence stringBuilder = new StringBuilder();
        stringBuilder.append("<").append(getElementName()).append(" ").append("xmlns").append("='").append("bpn:group:v2").append("'>");
        if (this.f5140a != null) {
            stringBuilder.append("<").append("notif").append(" ").append("v").append("='").append(this.f5140a).append("'/>");
        }
        if (this.f5141b != null) {
            stringBuilder.append("<").append("temp-mute").append(" ").append("v").append("='").append(this.f5140a).append("'/>");
        }
        stringBuilder.append("</").append("get-member-setting-ack").append(">");
        return stringBuilder;
    }
}
