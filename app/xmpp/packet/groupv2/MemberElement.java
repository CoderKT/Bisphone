package app.xmpp.packet.groupv2;

import org.xmlpull.v1.XmlPullParser;

public class MemberElement {
    private String f5175a;
    private State f5176b;

    public enum State {
        invited,
        kicked,
        not_joined,
        joined;

        public static State m7657a(String str) {
            try {
                return valueOf(str.toLowerCase());
            } catch (Exception e) {
                return null;
            }
        }
    }

    public MemberElement(String str, State state) {
        this.f5175a = str;
        this.f5176b = state;
    }

    public String m7659a() {
        return this.f5175a;
    }

    public State m7660b() {
        return this.f5176b;
    }

    public CharSequence m7661c() {
        CharSequence stringBuilder = new StringBuilder();
        stringBuilder.append("<").append("m").append(" ").append("id").append("='").append(this.f5175a).append("'");
        if (this.f5176b != null) {
            stringBuilder.append(" ").append("s").append("='").append(this.f5176b).append("'");
        }
        stringBuilder.append("/>");
        return stringBuilder;
    }

    public static MemberElement m7658a(XmlPullParser xmlPullParser) {
        if (!"m".equals(xmlPullParser.getName())) {
            return null;
        }
        String attributeValue = xmlPullParser.getAttributeValue("", "id");
        State a = State.m7657a(xmlPullParser.getAttributeValue("", "s"));
        int next = xmlPullParser.next();
        while (true) {
            if (next == 3 && "m".equals(xmlPullParser.getName())) {
                return new MemberElement(attributeValue, a);
            }
            next = xmlPullParser.next();
        }
    }
}
