package org.jivesoftware.smackx.offline.packet;

import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smackx.xdata.packet.DataForm.Item;
import org.xmlpull.v1.XmlPullParser;

public class OfflineMessageInfo implements ExtensionElement {
    private String node;

    public class Provider extends ExtensionElementProvider<OfflineMessageInfo> {
        public OfflineMessageInfo parse(XmlPullParser xmlPullParser, int i) {
            OfflineMessageInfo offlineMessageInfo = new OfflineMessageInfo();
            Object obj = null;
            while (obj == null) {
                int next = xmlPullParser.next();
                if (next == 2) {
                    if (xmlPullParser.getName().equals(Item.ELEMENT)) {
                        offlineMessageInfo.setNode(xmlPullParser.getAttributeValue("", "node"));
                    }
                } else if (next == 3 && xmlPullParser.getName().equals(OfflineMessageRequest.ELEMENT)) {
                    obj = 1;
                }
            }
            return offlineMessageInfo;
        }
    }

    public OfflineMessageInfo() {
        this.node = null;
    }

    public String getElementName() {
        return OfflineMessageRequest.ELEMENT;
    }

    public String getNamespace() {
        return OfflineMessageRequest.NAMESPACE;
    }

    public String getNode() {
        return this.node;
    }

    public void setNode(String str) {
        this.node = str;
    }

    public String toXML() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<").append(getElementName()).append(" xmlns=\"").append(getNamespace()).append("\">");
        if (getNode() != null) {
            stringBuilder.append("<item node=\"").append(getNode()).append("\"/>");
        }
        stringBuilder.append("</").append(getElementName()).append(">");
        return stringBuilder.toString();
    }
}
