package org.jivesoftware.smackx.pubsub.provider;

import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.jivesoftware.smackx.pubsub.packet.PubSub;
import org.jivesoftware.smackx.pubsub.packet.PubSubNamespace;
import org.xmlpull.v1.XmlPullParser;
import se.emilsjolander.stickylistheaders.C1128R;

public class PubSubProvider extends IQProvider<PubSub> {
    public PubSub parse(XmlPullParser xmlPullParser, int i) {
        Stanza pubSub = new PubSub(PubSubNamespace.valueOfFromXmlns(xmlPullParser.getNamespace()));
        while (true) {
            switch (xmlPullParser.next()) {
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    PacketParserUtils.addExtensionElement(pubSub, xmlPullParser);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    if (xmlPullParser.getDepth() != i) {
                        break;
                    }
                    return pubSub;
                default:
                    break;
            }
        }
    }
}
