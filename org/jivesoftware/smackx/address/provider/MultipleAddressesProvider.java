package org.jivesoftware.smackx.address.provider;

import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smackx.address.packet.MultipleAddresses;
import org.jivesoftware.smackx.address.packet.MultipleAddresses.Address;
import org.jivesoftware.smackx.address.packet.MultipleAddresses.Type;
import org.xmlpull.v1.XmlPullParser;
import se.emilsjolander.stickylistheaders.C1128R;

public class MultipleAddressesProvider extends ExtensionElementProvider<MultipleAddresses> {
    public MultipleAddresses parse(XmlPullParser xmlPullParser, int i) {
        MultipleAddresses multipleAddresses = new MultipleAddresses();
        while (true) {
            switch (xmlPullParser.next()) {
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    String name = xmlPullParser.getName();
                    Object obj = -1;
                    switch (name.hashCode()) {
                        case -1147692044:
                            if (name.equals(Address.ELEMENT)) {
                                obj = null;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                            multipleAddresses.addAddress(Type.valueOf(xmlPullParser.getAttributeValue("", "type")), xmlPullParser.getAttributeValue("", "jid"), xmlPullParser.getAttributeValue("", "node"), xmlPullParser.getAttributeValue("", "desc"), "true".equals(xmlPullParser.getAttributeValue("", "delivered")), xmlPullParser.getAttributeValue("", "uri"));
                            break;
                        default:
                            break;
                    }
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    if (xmlPullParser.getDepth() != i) {
                        break;
                    }
                    return multipleAddresses;
                default:
                    break;
            }
        }
    }
}
