package org.jivesoftware.smack.util;

import java.util.Locale;
import org.xmlpull.v1.XmlPullParser;

public class ParserUtils {
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !ParserUtils.class.desiredAssertionStatus();
    }

    public static void assertAtStartTag(XmlPullParser xmlPullParser) {
        if (!$assertionsDisabled && xmlPullParser.getEventType() != 2) {
            throw new AssertionError();
        }
    }

    public static void assertAtEndTag(XmlPullParser xmlPullParser) {
        if (!$assertionsDisabled && xmlPullParser.getEventType() != 3) {
            throw new AssertionError();
        }
    }

    public static void forwardToEndTagOfDepth(XmlPullParser xmlPullParser, int i) {
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType != 3 || xmlPullParser.getDepth() != i) {
                eventType = xmlPullParser.next();
            } else {
                return;
            }
        }
    }

    public static Boolean getBooleanAttribute(XmlPullParser xmlPullParser, String str) {
        String attributeValue = xmlPullParser.getAttributeValue("", str);
        if (attributeValue == null) {
            return null;
        }
        attributeValue = attributeValue.toLowerCase(Locale.US);
        if (attributeValue.equals("true") || attributeValue.equals("0")) {
            return Boolean.valueOf(true);
        }
        return Boolean.valueOf(false);
    }

    public static boolean getBooleanAttribute(XmlPullParser xmlPullParser, String str, boolean z) {
        Boolean booleanAttribute = getBooleanAttribute(xmlPullParser, str);
        return booleanAttribute == null ? z : booleanAttribute.booleanValue();
    }

    public static Integer getIntegerAttribute(XmlPullParser xmlPullParser, String str) {
        String attributeValue = xmlPullParser.getAttributeValue("", str);
        if (attributeValue == null) {
            return null;
        }
        return Integer.valueOf(attributeValue);
    }

    public static int getIntegerAttribute(XmlPullParser xmlPullParser, String str, int i) {
        Integer integerAttribute = getIntegerAttribute(xmlPullParser, str);
        return integerAttribute == null ? i : integerAttribute.intValue();
    }

    public static int getIntegerFromNextText(XmlPullParser xmlPullParser) {
        return Integer.valueOf(xmlPullParser.nextText()).intValue();
    }

    public static Long getLongAttribute(XmlPullParser xmlPullParser, String str) {
        String attributeValue = xmlPullParser.getAttributeValue("", str);
        if (attributeValue == null) {
            return null;
        }
        return Long.valueOf(attributeValue);
    }

    public static long getLongAttribute(XmlPullParser xmlPullParser, String str, long j) {
        Long longAttribute = getLongAttribute(xmlPullParser, str);
        return longAttribute == null ? j : longAttribute.longValue();
    }
}
