package app.xmpp.packet.channel;

import org.jivesoftware.smackx.muc.packet.MUCInitialPresence.History;
import org.xmlpull.v1.XmlPullParser;

public class HistoryElement {
    private Type f5082a;
    private long f5083b;

    public enum Type {
        since,
        count;

        public static Type m7563a(String str) {
            try {
                return valueOf(str.toLowerCase());
            } catch (Exception e) {
                return since;
            }
        }
    }

    public HistoryElement(Type type, long j) {
        this.f5082a = type;
        this.f5083b = j;
    }

    public CharSequence m7565a() {
        CharSequence stringBuilder = new StringBuilder();
        stringBuilder.append("<").append(History.ELEMENT).append(" ").append("xmlns").append("='").append("http://bisphone.com/protocol/history").append("' ").append("type").append("='").append(this.f5082a).append("' ").append("value").append("='").append(this.f5083b).append("'/>");
        return stringBuilder;
    }

    public static HistoryElement m7564a(XmlPullParser xmlPullParser) {
        if (!History.ELEMENT.equals(xmlPullParser.getName())) {
            return null;
        }
        String attributeValue = xmlPullParser.getAttributeValue("", "type");
        String attributeValue2 = xmlPullParser.getAttributeValue("", "value");
        Object obj = null;
        while (obj == null) {
            if (xmlPullParser.next() == 3 && History.ELEMENT.equals(xmlPullParser.getName())) {
                obj = 1;
            }
        }
        return new HistoryElement(Type.m7563a(attributeValue), Long.valueOf(attributeValue2).longValue());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        HistoryElement historyElement = (HistoryElement) obj;
        if (this.f5083b != historyElement.f5083b) {
            return false;
        }
        if (this.f5082a != historyElement.f5082a) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((this.f5082a != null ? this.f5082a.hashCode() : 0) * 31) + ((int) (this.f5083b ^ (this.f5083b >>> 32)));
    }
}
