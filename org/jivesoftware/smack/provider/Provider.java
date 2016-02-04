package org.jivesoftware.smack.provider;

import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.util.ParserUtils;
import org.xmlpull.v1.XmlPullParser;

public abstract class Provider<E extends Element> {
    public abstract E parse(XmlPullParser xmlPullParser, int i);

    public final E parse(XmlPullParser xmlPullParser) {
        ParserUtils.assertAtStartTag(xmlPullParser);
        int depth = xmlPullParser.getDepth();
        E parse = parse(xmlPullParser, depth);
        ParserUtils.forwardToEndTagOfDepth(xmlPullParser, depth);
        return parse;
    }
}
