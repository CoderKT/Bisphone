package org.jivesoftware.smackx.forward.provider;

import java.util.logging.Logger;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.jivesoftware.smackx.delay.packet.DelayInformation;
import org.jivesoftware.smackx.delay.provider.DelayInformationProvider;
import org.jivesoftware.smackx.forward.packet.Forwarded;
import org.xmlpull.v1.XmlPullParser;
import se.emilsjolander.stickylistheaders.C1128R;

public class ForwardedProvider extends ExtensionElementProvider<Forwarded> {
    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getLogger(ForwardedProvider.class.getName());
    }

    public Forwarded parse(XmlPullParser xmlPullParser, int i) {
        Stanza stanza = null;
        DelayInformation delayInformation = null;
        while (true) {
            switch (xmlPullParser.next()) {
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    String name = xmlPullParser.getName();
                    String namespace = xmlPullParser.getNamespace();
                    Object obj = -1;
                    switch (name.hashCode()) {
                        case 95467907:
                            if (name.equals(DelayInformation.ELEMENT)) {
                                obj = null;
                                break;
                            }
                            break;
                        case 954925063:
                            if (name.equals(Message.ELEMENT)) {
                                obj = 1;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                            if (!DelayInformation.NAMESPACE.equals(namespace)) {
                                LOGGER.warning("Namespace '" + namespace + "' does not match expected namespace '" + DelayInformation.NAMESPACE + "'");
                                break;
                            }
                            delayInformation = DelayInformationProvider.INSTANCE.parse(xmlPullParser, xmlPullParser.getDepth());
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                            stanza = PacketParserUtils.parseMessage(xmlPullParser);
                            break;
                        default:
                            LOGGER.warning("Unsupported forwarded packet type: " + name);
                            break;
                    }
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    if (xmlPullParser.getDepth() != i) {
                        break;
                    } else if (stanza != null) {
                        return new Forwarded(delayInformation, stanza);
                    } else {
                        throw new SmackException("forwarded extension must contain a packet");
                    }
                default:
                    break;
            }
        }
    }
}
