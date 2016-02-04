package org.jivesoftware.smackx.ping.provider;

import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smackx.ping.packet.Ping;
import org.xmlpull.v1.XmlPullParser;

public class PingProvider extends IQProvider<Ping> {
    public Ping parse(XmlPullParser xmlPullParser, int i) {
        return new Ping();
    }
}
