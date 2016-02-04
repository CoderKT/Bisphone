package org.jivesoftware.smackx.iqprivate.provider;

import org.jivesoftware.smackx.iqprivate.packet.PrivateData;
import org.xmlpull.v1.XmlPullParser;

public interface PrivateDataProvider {
    PrivateData parsePrivateData(XmlPullParser xmlPullParser);
}
