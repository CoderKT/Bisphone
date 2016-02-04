package org.jivesoftware.smackx.bytestreams.ibb.provider;

import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smackx.bytestreams.ibb.packet.Close;
import org.xmlpull.v1.XmlPullParser;

public class CloseIQProvider extends IQProvider<Close> {
    public Close parse(XmlPullParser xmlPullParser, int i) {
        return new Close(xmlPullParser.getAttributeValue("", "sid"));
    }
}
