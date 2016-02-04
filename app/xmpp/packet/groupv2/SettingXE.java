package app.xmpp.packet.groupv2;

public class SettingXE extends AbstractXE {
    private NotificationState f5199a;
    private Integer f5200b;

    public enum NotificationState {
        disable,
        vibrate,
        sound,
        disablePending,
        vibratePending,
        soundPending,
        getPending;

        public static NotificationState m7675a(String str) {
            try {
                return valueOf(str.toLowerCase());
            } catch (Exception e) {
                return sound;
            }
        }
    }

    public NotificationState m7676a() {
        return this.f5199a;
    }

    public void m7677a(NotificationState notificationState) {
        this.f5199a = notificationState;
    }

    public void m7678a(Integer num) {
        this.f5200b = num;
    }

    public String getElementName() {
        return "set-member-setting";
    }

    public CharSequence toXML() {
        CharSequence stringBuilder = new StringBuilder();
        stringBuilder.append("<").append(getElementName()).append(" ").append("xmlns").append("='").append("bpn:group:v2").append("'>");
        if (this.f5199a != null) {
            stringBuilder.append("<").append("notif").append(" ").append("v").append("='").append(this.f5199a).append("'/>");
        }
        if (this.f5200b != null) {
            stringBuilder.append("<").append("temp-mute").append(" ").append("v").append("='").append(this.f5199a).append("'/>");
        }
        stringBuilder.append("</").append("set-member-setting").append(">");
        return stringBuilder;
    }
}
