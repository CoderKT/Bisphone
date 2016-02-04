package org.jivesoftware.smackx.bytestreams.ibb.provider;

import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smackx.bytestreams.ibb.packet.Data;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;
import org.xmlpull.v1.XmlPullParser;

public class DataPacketProvider {

    public class IQProvider extends org.jivesoftware.smack.provider.IQProvider<Data> {
        private static final PacketExtensionProvider packetExtensionProvider;

        static {
            packetExtensionProvider = new PacketExtensionProvider();
        }

        public Data parse(XmlPullParser xmlPullParser, int i) {
            return new Data((DataPacketExtension) packetExtensionProvider.parse(xmlPullParser));
        }
    }

    public class PacketExtensionProvider extends ExtensionElementProvider<DataPacketExtension> {
        public DataPacketExtension parse(XmlPullParser xmlPullParser, int i) {
            return new DataPacketExtension(xmlPullParser.getAttributeValue("", "sid"), Long.parseLong(xmlPullParser.getAttributeValue("", "seq")), xmlPullParser.nextText());
        }
    }
}
