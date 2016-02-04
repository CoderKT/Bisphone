package org.jivesoftware.smackx.offline.packet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smackx.amp.packet.AMPExtension.Action;
import org.xmlpull.v1.XmlPullParser;

public class OfflineMessageRequest extends IQ {
    public static final String ELEMENT = "offline";
    public static final String NAMESPACE = "http://jabber.org/protocol/offline";
    private boolean fetch;
    private List<Item> items;
    private boolean purge;

    public class Item {
        private String action;
        private String jid;
        private String node;

        public Item(String str) {
            this.node = str;
        }

        public String getNode() {
            return this.node;
        }

        public String getAction() {
            return this.action;
        }

        public void setAction(String str) {
            this.action = str;
        }

        public String getJid() {
            return this.jid;
        }

        public void setJid(String str) {
            this.jid = str;
        }

        public String toXML() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<item");
            if (getAction() != null) {
                stringBuilder.append(" action=\"").append(getAction()).append("\"");
            }
            if (getJid() != null) {
                stringBuilder.append(" jid=\"").append(getJid()).append("\"");
            }
            if (getNode() != null) {
                stringBuilder.append(" node=\"").append(getNode()).append("\"");
            }
            stringBuilder.append("/>");
            return stringBuilder.toString();
        }
    }

    public class Provider extends IQProvider<OfflineMessageRequest> {
        public OfflineMessageRequest parse(XmlPullParser xmlPullParser, int i) {
            OfflineMessageRequest offlineMessageRequest = new OfflineMessageRequest();
            boolean z = false;
            while (!z) {
                int next = xmlPullParser.next();
                if (next == 2) {
                    if (xmlPullParser.getName().equals(org.jivesoftware.smackx.xdata.packet.DataForm.Item.ELEMENT)) {
                        offlineMessageRequest.addItem(parseItem(xmlPullParser));
                    } else if (xmlPullParser.getName().equals("purge")) {
                        offlineMessageRequest.setPurge(true);
                    } else if (xmlPullParser.getName().equals("fetch")) {
                        offlineMessageRequest.setFetch(true);
                    }
                } else if (next == 3 && xmlPullParser.getName().equals(OfflineMessageRequest.ELEMENT)) {
                    z = true;
                }
            }
            return offlineMessageRequest;
        }

        private Item parseItem(XmlPullParser xmlPullParser) {
            Object obj = null;
            Item item = new Item(xmlPullParser.getAttributeValue("", "node"));
            item.setAction(xmlPullParser.getAttributeValue("", Action.ATTRIBUTE_NAME));
            item.setJid(xmlPullParser.getAttributeValue("", "jid"));
            while (obj == null) {
                if (xmlPullParser.next() == 3 && xmlPullParser.getName().equals(org.jivesoftware.smackx.xdata.packet.DataForm.Item.ELEMENT)) {
                    obj = 1;
                }
            }
            return item;
        }
    }

    public OfflineMessageRequest() {
        super(ELEMENT, NAMESPACE);
        this.items = new ArrayList();
        this.purge = false;
        this.fetch = false;
    }

    public List<Item> getItems() {
        List<Item> unmodifiableList;
        synchronized (this.items) {
            unmodifiableList = Collections.unmodifiableList(new ArrayList(this.items));
        }
        return unmodifiableList;
    }

    public void addItem(Item item) {
        synchronized (this.items) {
            this.items.add(item);
        }
    }

    public boolean isPurge() {
        return this.purge;
    }

    public void setPurge(boolean z) {
        this.purge = z;
    }

    public boolean isFetch() {
        return this.fetch;
    }

    public void setFetch(boolean z) {
        this.fetch = z;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder getIQChildElementBuilder(org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder r4) {
        /*
        r3 = this;
        r4.rightAngleBracket();
        r2 = r3.items;
        monitor-enter(r2);
        r0 = 0;
        r1 = r0;
    L_0x0008:
        r0 = r3.items;	 Catch:{ all -> 0x0037 }
        r0 = r0.size();	 Catch:{ all -> 0x0037 }
        if (r1 >= r0) goto L_0x0023;
    L_0x0010:
        r0 = r3.items;	 Catch:{ all -> 0x0037 }
        r0 = r0.get(r1);	 Catch:{ all -> 0x0037 }
        r0 = (org.jivesoftware.smackx.offline.packet.OfflineMessageRequest.Item) r0;	 Catch:{ all -> 0x0037 }
        r0 = r0.toXML();	 Catch:{ all -> 0x0037 }
        r4.append(r0);	 Catch:{ all -> 0x0037 }
        r0 = r1 + 1;
        r1 = r0;
        goto L_0x0008;
    L_0x0023:
        monitor-exit(r2);	 Catch:{ all -> 0x0037 }
        r0 = r3.purge;
        if (r0 == 0) goto L_0x002d;
    L_0x0028:
        r0 = "<purge/>";
        r4.append(r0);
    L_0x002d:
        r0 = r3.fetch;
        if (r0 == 0) goto L_0x0036;
    L_0x0031:
        r0 = "<fetch/>";
        r4.append(r0);
    L_0x0036:
        return r4;
    L_0x0037:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0037 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smackx.offline.packet.OfflineMessageRequest.getIQChildElementBuilder(org.jivesoftware.smack.packet.IQ$IQChildElementXmlStringBuilder):org.jivesoftware.smack.packet.IQ$IQChildElementXmlStringBuilder");
    }
}
