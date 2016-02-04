package app.xmpp.packet.groupv2;

public class MembersXE extends AbstractXESimple {
    MemberState f5182a;

    public enum MemberState {
        invited,
        joined,
        kicked,
        left;

        public static MemberState m7662a(String str) {
            try {
                return valueOf(str.toLowerCase());
            } catch (Exception e) {
                return joined;
            }
        }
    }

    public MembersXE(MemberState memberState) {
        this.f5182a = memberState;
    }

    public MemberState m7663a() {
        return this.f5182a;
    }

    public String getElementName() {
        return "members";
    }

    public CharSequence toXML() {
        CharSequence stringBuilder = new StringBuilder();
        stringBuilder.append("<").append("members").append(" ").append("xmlns").append("='").append("bpn:group:v2").append("' >").append("<query").append(" ").append("s").append("='").append(this.f5182a).append("'/>");
        stringBuilder.append("</").append("members").append(">");
        return stringBuilder;
    }
}
