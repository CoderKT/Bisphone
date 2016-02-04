package org.jivesoftware.smack.util;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.packet.DefaultExtensionElement;
import org.jivesoftware.smack.packet.EmptyResultIQ;
import org.jivesoftware.smack.packet.ErrorIQ;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jivesoftware.smack.packet.Mechanisms;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Presence.Mode;
import org.jivesoftware.smack.packet.Session.Feature;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.packet.StartTls;
import org.jivesoftware.smack.packet.UnparsedIQ;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smack.provider.ProviderManager;
import org.jivesoftware.smack.sasl.packet.SaslStreamElements.SASLFailure;
import org.jivesoftware.smackx.muc.packet.MUCUser.Status;
import org.jivesoftware.smackx.privacy.packet.PrivacyItem;
import org.jivesoftware.smackx.xdatalayout.packet.DataLayout.Text;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import se.emilsjolander.stickylistheaders.C1128R;

public class PacketParserUtils {
    static final /* synthetic */ boolean $assertionsDisabled;
    public static final String FEATURE_XML_ROUNDTRIP = "http://xmlpull.org/v1/doc/features.html#xml-roundtrip";
    private static final Logger LOGGER;
    private static final XmlPullParserFactory XML_PULL_PARSER_FACTORY;
    public static final boolean XML_PULL_PARSER_SUPPORTS_ROUNDTRIP;

    /* renamed from: org.jivesoftware.smack.util.PacketParserUtils.1 */
    /* synthetic */ class C10291 {
        static final /* synthetic */ int[] $SwitchMap$org$jivesoftware$smack$packet$IQ$Type;

        static {
            $SwitchMap$org$jivesoftware$smack$packet$IQ$Type = new int[Type.values().length];
            try {
                $SwitchMap$org$jivesoftware$smack$packet$IQ$Type[Type.error.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$org$jivesoftware$smack$packet$IQ$Type[Type.result.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    static {
        boolean z = true;
        $assertionsDisabled = !PacketParserUtils.class.desiredAssertionStatus() ? true : $assertionsDisabled;
        LOGGER = Logger.getLogger(PacketParserUtils.class.getName());
        try {
            XML_PULL_PARSER_FACTORY = XmlPullParserFactory.newInstance();
            try {
                XML_PULL_PARSER_FACTORY.newPullParser().setFeature(FEATURE_XML_ROUNDTRIP, true);
            } catch (Throwable e) {
                LOGGER.log(Level.FINEST, "XmlPullParser does not support XML_ROUNDTRIP", e);
                z = $assertionsDisabled;
            }
            XML_PULL_PARSER_SUPPORTS_ROUNDTRIP = z;
        } catch (XmlPullParserException e2) {
            throw new AssertionError(e2);
        }
    }

    public static XmlPullParser getParserFor(String str) {
        return getParserFor(new StringReader(str));
    }

    public static XmlPullParser getParserFor(Reader reader) {
        XmlPullParser newXmppParser = newXmppParser(reader);
        for (int eventType = newXmppParser.getEventType(); eventType != 2; eventType = newXmppParser.next()) {
            if (eventType == 1) {
                throw new IllegalArgumentException("Document contains no start tag");
            }
        }
        return newXmppParser;
    }

    public static XmlPullParser getParserFor(String str, String str2) {
        XmlPullParser parserFor = getParserFor(str);
        while (true) {
            int eventType = parserFor.getEventType();
            String name = parserFor.getName();
            if (eventType == 2 && name.equals(str2)) {
                return parserFor;
            }
            if (eventType == 1) {
                break;
            }
            parserFor.next();
        }
        throw new IllegalArgumentException("Could not find start tag '" + str2 + "' in stanza: " + str);
    }

    public static Stanza parseStanza(String str) {
        return parseStanza(getParserFor(str));
    }

    public static Stanza parseStanza(XmlPullParser xmlPullParser) {
        ParserUtils.assertAtStartTag(xmlPullParser);
        String name = xmlPullParser.getName();
        Object obj = -1;
        switch (name.hashCode()) {
            case -1276666629:
                if (name.equals(Presence.ELEMENT)) {
                    obj = 2;
                    break;
                }
                break;
            case 3368:
                if (name.equals(IQ.IQ_ELEMENT)) {
                    obj = 1;
                    break;
                }
                break;
            case 954925063:
                if (name.equals(Message.ELEMENT)) {
                    obj = null;
                    break;
                }
                break;
        }
        switch (obj) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                return parseMessage(xmlPullParser);
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                return parseIQ(xmlPullParser);
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                return parsePresence(xmlPullParser);
            default:
                throw new IllegalArgumentException("Can only parse message, iq or presence, not " + name);
        }
    }

    public static XmlPullParser newXmppParser() {
        XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
        newPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
        if (XML_PULL_PARSER_SUPPORTS_ROUNDTRIP) {
            try {
                newPullParser.setFeature(FEATURE_XML_ROUNDTRIP, true);
            } catch (Throwable e) {
                LOGGER.log(Level.SEVERE, "XmlPullParser does not support XML_ROUNDTRIP, although it was first determined to be supported", e);
            }
        }
        return newPullParser;
    }

    public static XmlPullParser newXmppParser(Reader reader) {
        XmlPullParser newXmppParser = newXmppParser();
        newXmppParser.setInput(reader);
        return newXmppParser;
    }

    public static Message parseMessage(XmlPullParser xmlPullParser) {
        ParserUtils.assertAtStartTag(xmlPullParser);
        if ($assertionsDisabled || xmlPullParser.getName().equals(Message.ELEMENT)) {
            int depth = xmlPullParser.getDepth();
            Stanza message = new Message();
            message.setStanzaId(xmlPullParser.getAttributeValue("", "id"));
            message.setTo(xmlPullParser.getAttributeValue("", PrivacyItem.SUBSCRIPTION_TO));
            message.setFrom(xmlPullParser.getAttributeValue("", PrivacyItem.SUBSCRIPTION_FROM));
            String attributeValue = xmlPullParser.getAttributeValue("", "type");
            if (attributeValue != null) {
                message.setType(Message.Type.fromString(attributeValue));
            }
            attributeValue = getLanguageAttribute(xmlPullParser);
            if (attributeValue == null || "".equals(attributeValue.trim())) {
                attributeValue = Stanza.getDefaultLanguage();
            } else {
                message.setLanguage(attributeValue);
            }
            String str = null;
            while (true) {
                switch (xmlPullParser.next()) {
                    case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                        String name = xmlPullParser.getName();
                        String namespace = xmlPullParser.getNamespace();
                        Object obj = -1;
                        switch (name.hashCode()) {
                            case -1867885268:
                                if (name.equals("subject")) {
                                    obj = null;
                                    break;
                                }
                                break;
                            case -874443254:
                                if (name.equals("thread")) {
                                    obj = 2;
                                    break;
                                }
                                break;
                            case 3029410:
                                if (name.equals(Message.BODY)) {
                                    obj = 1;
                                    break;
                                }
                                break;
                            case 96784904:
                                if (name.equals(XMPPError.ERROR)) {
                                    obj = 3;
                                    break;
                                }
                                break;
                        }
                        String languageAttribute;
                        switch (obj) {
                            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                                languageAttribute = getLanguageAttribute(xmlPullParser);
                                if (languageAttribute == null) {
                                    languageAttribute = attributeValue;
                                }
                                name = parseElementText(xmlPullParser);
                                if (message.getSubject(languageAttribute) != null) {
                                    break;
                                }
                                message.addSubject(languageAttribute, name);
                                break;
                            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                                languageAttribute = getLanguageAttribute(xmlPullParser);
                                if (languageAttribute == null) {
                                    languageAttribute = attributeValue;
                                }
                                name = parseElementText(xmlPullParser);
                                if (message.getBody(languageAttribute) != null) {
                                    break;
                                }
                                message.addBody(languageAttribute, name);
                                break;
                            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                                if (str != null) {
                                    break;
                                }
                                str = xmlPullParser.nextText();
                                break;
                            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                                message.setError(parseError(xmlPullParser));
                                break;
                            default:
                                addExtensionElement(message, xmlPullParser, name, namespace);
                                break;
                        }
                    case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                        if (xmlPullParser.getDepth() != depth) {
                            break;
                        }
                        message.setThread(str);
                        return message;
                    default:
                        break;
                }
            }
        }
        throw new AssertionError();
    }

    public static String parseElementText(XmlPullParser xmlPullParser) {
        if (!$assertionsDisabled && xmlPullParser.getEventType() != 2) {
            throw new AssertionError();
        } else if (xmlPullParser.isEmptyElementTag()) {
            return "";
        } else {
            int next = xmlPullParser.next();
            if (next == 4) {
                String text = xmlPullParser.getText();
                if (xmlPullParser.next() == 3) {
                    return text;
                }
                throw new XmlPullParserException("Non-empty element tag contains child-elements, while Mixed Content (XML 3.2.2) is disallowed");
            } else if (next == 3) {
                return "";
            } else {
                throw new XmlPullParserException("Non-empty element tag not followed by text, while Mixed Content (XML 3.2.2) is disallowed");
            }
        }
    }

    public static CharSequence parseElement(XmlPullParser xmlPullParser) {
        return parseElement(xmlPullParser, $assertionsDisabled);
    }

    public static CharSequence parseElement(XmlPullParser xmlPullParser, boolean z) {
        if ($assertionsDisabled || xmlPullParser.getEventType() == 2) {
            return parseContentDepth(xmlPullParser, xmlPullParser.getDepth(), z);
        }
        throw new AssertionError();
    }

    public static CharSequence parseContent(XmlPullParser xmlPullParser) {
        if (!$assertionsDisabled && xmlPullParser.getEventType() != 2) {
            throw new AssertionError();
        } else if (xmlPullParser.isEmptyElementTag()) {
            return "";
        } else {
            xmlPullParser.next();
            return parseContentDepth(xmlPullParser, xmlPullParser.getDepth(), $assertionsDisabled);
        }
    }

    public static CharSequence parseContentDepth(XmlPullParser xmlPullParser, int i) {
        return parseContentDepth(xmlPullParser, i, $assertionsDisabled);
    }

    public static CharSequence parseContentDepth(XmlPullParser xmlPullParser, int i, boolean z) {
        if (xmlPullParser.getFeature(FEATURE_XML_ROUNDTRIP)) {
            return parseContentDepthWithRoundtrip(xmlPullParser, i, z);
        }
        return parseContentDepthWithoutRoundtrip(xmlPullParser, i, z);
    }

    private static CharSequence parseContentDepthWithoutRoundtrip(XmlPullParser xmlPullParser, int i, boolean z) {
        XmlStringBuilder xmlStringBuilder = new XmlStringBuilder();
        Object obj = null;
        int eventType = xmlPullParser.getEventType();
        String str = null;
        while (true) {
            Object obj2;
            switch (eventType) {
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    xmlStringBuilder.halfOpenElement(xmlPullParser.getName());
                    if (str == null || z) {
                        String namespace = xmlPullParser.getNamespace();
                        if (StringUtils.isNotEmpty(namespace)) {
                            xmlStringBuilder.attribute("xmlns", namespace);
                            str = xmlPullParser.getName();
                        }
                    }
                    for (eventType = 0; eventType < xmlPullParser.getAttributeCount(); eventType++) {
                        xmlStringBuilder.attribute(xmlPullParser.getAttributeName(eventType), xmlPullParser.getAttributeValue(eventType));
                    }
                    if (!xmlPullParser.isEmptyElementTag()) {
                        xmlStringBuilder.rightAngleBracket();
                        obj2 = obj;
                        break;
                    }
                    xmlStringBuilder.closeEmptyElement();
                    obj2 = 1;
                    continue;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    if (obj != null) {
                        obj2 = null;
                    } else {
                        xmlStringBuilder.closeElement(xmlPullParser.getName());
                        obj2 = obj;
                    }
                    if (str != null && str.equals(xmlPullParser.getName())) {
                        str = null;
                    }
                    if (xmlPullParser.getDepth() <= i) {
                        return xmlStringBuilder;
                    }
                    continue;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    xmlStringBuilder.escape(xmlPullParser.getText());
                    break;
            }
            obj2 = obj;
            Object obj3 = obj2;
            eventType = xmlPullParser.next();
            obj = obj3;
        }
    }

    private static CharSequence parseContentDepthWithRoundtrip(XmlPullParser xmlPullParser, int i, boolean z) {
        CharSequence stringBuilder = new StringBuilder();
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (!(eventType == 2 && xmlPullParser.isEmptyElementTag())) {
                CharSequence text = xmlPullParser.getText();
                if (eventType == 4) {
                    text = StringUtils.escapeForXML(text.toString());
                }
                stringBuilder.append(text);
            }
            if (eventType == 3 && xmlPullParser.getDepth() <= i) {
                return stringBuilder;
            }
            eventType = xmlPullParser.next();
        }
    }

    public static Presence parsePresence(XmlPullParser xmlPullParser) {
        ParserUtils.assertAtStartTag(xmlPullParser);
        int depth = xmlPullParser.getDepth();
        Presence.Type type = Presence.Type.available;
        String attributeValue = xmlPullParser.getAttributeValue("", "type");
        if (!(attributeValue == null || attributeValue.equals(""))) {
            type = Presence.Type.fromString(attributeValue);
        }
        Stanza presence = new Presence(type);
        presence.setTo(xmlPullParser.getAttributeValue("", PrivacyItem.SUBSCRIPTION_TO));
        presence.setFrom(xmlPullParser.getAttributeValue("", PrivacyItem.SUBSCRIPTION_FROM));
        presence.setStanzaId(xmlPullParser.getAttributeValue("", "id"));
        String languageAttribute = getLanguageAttribute(xmlPullParser);
        if (!(languageAttribute == null || "".equals(languageAttribute.trim()))) {
            presence.setLanguage(languageAttribute);
        }
        while (true) {
            switch (xmlPullParser.next()) {
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    String name = xmlPullParser.getName();
                    String namespace = xmlPullParser.getNamespace();
                    Object obj = -1;
                    switch (name.hashCode()) {
                        case -1165461084:
                            if (name.equals("priority")) {
                                obj = 1;
                                break;
                            }
                            break;
                        case -892481550:
                            if (name.equals(Status.ELEMENT)) {
                                obj = null;
                                break;
                            }
                            break;
                        case 3529469:
                            if (name.equals("show")) {
                                obj = 2;
                                break;
                            }
                            break;
                        case 96784904:
                            if (name.equals(XMPPError.ERROR)) {
                                obj = 3;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                            presence.setStatus(xmlPullParser.nextText());
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                            presence.setPriority(Integer.parseInt(xmlPullParser.nextText()));
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                            obj = xmlPullParser.nextText();
                            if (!StringUtils.isNotEmpty(obj)) {
                                LOGGER.warning("Empty or null mode text in presence show element form " + presence.getFrom() + " with id '" + presence.getStanzaId() + "' which is invalid according to RFC6121 4.7.2.1");
                                break;
                            }
                            presence.setMode(Mode.fromString(obj));
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                            presence.setError(parseError(xmlPullParser));
                            break;
                        default:
                            try {
                                addExtensionElement(presence, xmlPullParser, name, namespace);
                                break;
                            } catch (Throwable e) {
                                LOGGER.log(Level.WARNING, "Failed to parse extension packet in Presence packet. Attributes: from=" + presence.getFrom() + " id=" + presence.getStanzaId(), e);
                                break;
                            }
                    }
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    if (xmlPullParser.getDepth() != depth) {
                        break;
                    }
                    return presence;
                default:
                    break;
            }
        }
    }

    public static IQ parseIQ(XmlPullParser xmlPullParser) {
        XMPPError xMPPError = null;
        ParserUtils.assertAtStartTag(xmlPullParser);
        int depth = xmlPullParser.getDepth();
        String attributeValue = xmlPullParser.getAttributeValue("", "id");
        String attributeValue2 = xmlPullParser.getAttributeValue("", PrivacyItem.SUBSCRIPTION_TO);
        String attributeValue3 = xmlPullParser.getAttributeValue("", PrivacyItem.SUBSCRIPTION_FROM);
        Type fromString = Type.fromString(xmlPullParser.getAttributeValue("", "type"));
        IQ iq = null;
        while (true) {
            switch (xmlPullParser.next()) {
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    XMPPError parseError;
                    IQ iq2;
                    String name = xmlPullParser.getName();
                    String namespace = xmlPullParser.getNamespace();
                    Object obj = -1;
                    switch (name.hashCode()) {
                        case 96784904:
                            if (name.equals(XMPPError.ERROR)) {
                                obj = null;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                            parseError = parseError(xmlPullParser);
                            iq2 = iq;
                            break;
                        default:
                            IQProvider iQProvider = ProviderManager.getIQProvider(name, namespace);
                            XMPPError xMPPError2;
                            if (iQProvider == null) {
                                xMPPError2 = xMPPError;
                                iq2 = new UnparsedIQ(name, namespace, parseElement(xmlPullParser));
                                parseError = xMPPError2;
                                break;
                            }
                            xMPPError2 = xMPPError;
                            iq2 = (IQ) iQProvider.parse(xmlPullParser);
                            parseError = xMPPError2;
                            break;
                    }
                    iq = iq2;
                    xMPPError = parseError;
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    if (xmlPullParser.getDepth() != depth) {
                        break;
                    }
                    if (iq == null) {
                        switch (C10291.$SwitchMap$org$jivesoftware$smack$packet$IQ$Type[fromString.ordinal()]) {
                            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                                iq = new ErrorIQ(xMPPError);
                                break;
                            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                                iq = new EmptyResultIQ();
                                break;
                        }
                    }
                    iq.setStanzaId(attributeValue);
                    iq.setTo(attributeValue2);
                    iq.setFrom(attributeValue3);
                    iq.setType(fromString);
                    iq.setError(xMPPError);
                    return iq;
                default:
                    break;
            }
        }
    }

    public static Collection<String> parseMechanisms(XmlPullParser xmlPullParser) {
        Collection arrayList = new ArrayList();
        Object obj = null;
        while (obj == null) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("mechanism")) {
                    arrayList.add(xmlPullParser.nextText());
                }
            } else if (next == 3 && xmlPullParser.getName().equals(Mechanisms.ELEMENT)) {
                obj = 1;
            }
        }
        return arrayList;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.jivesoftware.smack.compress.packet.Compress.Feature parseCompressionFeature(org.xmlpull.v1.XmlPullParser r6) {
        /*
        r1 = 0;
        r2 = -1;
        r0 = $assertionsDisabled;
        if (r0 != 0) goto L_0x0013;
    L_0x0006:
        r0 = r6.getEventType();
        r3 = 2;
        if (r0 == r3) goto L_0x0013;
    L_0x000d:
        r0 = new java.lang.AssertionError;
        r0.<init>();
        throw r0;
    L_0x0013:
        r3 = r6.getDepth();
        r4 = new java.util.LinkedList;
        r4.<init>();
    L_0x001c:
        r0 = r6.next();
        switch(r0) {
            case 2: goto L_0x0024;
            case 3: goto L_0x0046;
            default: goto L_0x0023;
        };
    L_0x0023:
        goto L_0x001c;
    L_0x0024:
        r0 = r6.getName();
        r5 = r0.hashCode();
        switch(r5) {
            case -1077554975: goto L_0x003c;
            default: goto L_0x002f;
        };
    L_0x002f:
        r0 = r2;
    L_0x0030:
        switch(r0) {
            case 0: goto L_0x0034;
            default: goto L_0x0033;
        };
    L_0x0033:
        goto L_0x001c;
    L_0x0034:
        r0 = r6.nextText();
        r4.add(r0);
        goto L_0x001c;
    L_0x003c:
        r5 = "method";
        r0 = r0.equals(r5);
        if (r0 == 0) goto L_0x002f;
    L_0x0044:
        r0 = r1;
        goto L_0x0030;
    L_0x0046:
        r0 = r6.getName();
        r5 = r0.hashCode();
        switch(r5) {
            case 1431984486: goto L_0x006d;
            default: goto L_0x0051;
        };
    L_0x0051:
        r0 = r2;
    L_0x0052:
        switch(r0) {
            case 0: goto L_0x0056;
            default: goto L_0x0055;
        };
    L_0x0055:
        goto L_0x001c;
    L_0x0056:
        r0 = r6.getDepth();
        if (r0 != r3) goto L_0x001c;
    L_0x005c:
        r0 = $assertionsDisabled;
        if (r0 != 0) goto L_0x0077;
    L_0x0060:
        r0 = r6.getEventType();
        r1 = 3;
        if (r0 == r1) goto L_0x0077;
    L_0x0067:
        r0 = new java.lang.AssertionError;
        r0.<init>();
        throw r0;
    L_0x006d:
        r5 = "compression";
        r0 = r0.equals(r5);
        if (r0 == 0) goto L_0x0051;
    L_0x0075:
        r0 = r1;
        goto L_0x0052;
    L_0x0077:
        r0 = $assertionsDisabled;
        if (r0 != 0) goto L_0x0087;
    L_0x007b:
        r0 = r6.getDepth();
        if (r0 == r3) goto L_0x0087;
    L_0x0081:
        r0 = new java.lang.AssertionError;
        r0.<init>();
        throw r0;
    L_0x0087:
        r0 = new org.jivesoftware.smack.compress.packet.Compress$Feature;
        r0.<init>(r4);
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smack.util.PacketParserUtils.parseCompressionFeature(org.xmlpull.v1.XmlPullParser):org.jivesoftware.smack.compress.packet.Compress$Feature");
    }

    public static Map<String, String> parseDescriptiveTexts(XmlPullParser xmlPullParser, Map<String, String> map) {
        if (map == null) {
            map = new HashMap();
        }
        String str = (String) map.put(getLanguageAttribute(xmlPullParser), xmlPullParser.nextText());
        if ($assertionsDisabled || str == null) {
            return map;
        }
        throw new AssertionError();
    }

    public static SASLFailure parseSASLFailure(XmlPullParser xmlPullParser) {
        Map map = null;
        int depth = xmlPullParser.getDepth();
        String str = null;
        while (true) {
            switch (xmlPullParser.next()) {
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    if (xmlPullParser.getName().equals(Text.ELEMENT)) {
                        map = parseDescriptiveTexts(xmlPullParser, map);
                        break;
                    } else if ($assertionsDisabled || str == null) {
                        str = xmlPullParser.getName();
                        break;
                    } else {
                        throw new AssertionError();
                    }
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    if (xmlPullParser.getDepth() != depth) {
                        break;
                    }
                    return new SASLFailure(str, map);
                default:
                    break;
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.jivesoftware.smack.packet.StreamError parseStreamError(org.xmlpull.v1.XmlPullParser r10) {
        /*
        r4 = 0;
        r5 = -1;
        r0 = 0;
        r6 = r10.getDepth();
        r7 = new java.util.ArrayList;
        r7.<init>();
        r1 = r0;
        r2 = r0;
    L_0x000e:
        r3 = r10.next();
        switch(r3) {
            case 2: goto L_0x0016;
            case 3: goto L_0x0060;
            default: goto L_0x0015;
        };
    L_0x0015:
        goto L_0x000e;
    L_0x0016:
        r8 = r10.getName();
        r9 = r10.getNamespace();
        r3 = r9.hashCode();
        switch(r3) {
            case 904188284: goto L_0x002d;
            default: goto L_0x0025;
        };
    L_0x0025:
        r3 = r5;
    L_0x0026:
        switch(r3) {
            case 0: goto L_0x0037;
            default: goto L_0x0029;
        };
    L_0x0029:
        addExtensionElement(r7, r10, r8, r9);
        goto L_0x000e;
    L_0x002d:
        r3 = "urn:ietf:params:xml:ns:xmpp-streams";
        r3 = r9.equals(r3);
        if (r3 == 0) goto L_0x0025;
    L_0x0035:
        r3 = r4;
        goto L_0x0026;
    L_0x0037:
        r3 = r8.hashCode();
        switch(r3) {
            case 3556653: goto L_0x0051;
            default: goto L_0x003e;
        };
    L_0x003e:
        r3 = r5;
    L_0x003f:
        switch(r3) {
            case 0: goto L_0x005b;
            default: goto L_0x0042;
        };
    L_0x0042:
        r1 = org.jivesoftware.smack.packet.StreamError.Condition.fromString(r8);
        r3 = r10.isEmptyElementTag();
        if (r3 != 0) goto L_0x000e;
    L_0x004c:
        r0 = r10.nextText();
        goto L_0x000e;
    L_0x0051:
        r3 = "text";
        r3 = r8.equals(r3);
        if (r3 == 0) goto L_0x003e;
    L_0x0059:
        r3 = r4;
        goto L_0x003f;
    L_0x005b:
        r2 = parseDescriptiveTexts(r10, r2);
        goto L_0x000e;
    L_0x0060:
        r3 = r10.getDepth();
        if (r3 != r6) goto L_0x000e;
    L_0x0066:
        r3 = new org.jivesoftware.smack.packet.StreamError;
        r3.<init>(r1, r0, r2, r7);
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smack.util.PacketParserUtils.parseStreamError(org.xmlpull.v1.XmlPullParser):org.jivesoftware.smack.packet.StreamError");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.jivesoftware.smack.packet.XMPPError parseError(org.xmlpull.v1.XmlPullParser r12) {
        /*
        r7 = 0;
        r8 = -1;
        r2 = 0;
        r9 = r12.getDepth();
        r6 = new java.util.ArrayList;
        r6.<init>();
        r0 = "";
        r1 = "type";
        r0 = r12.getAttributeValue(r0, r1);
        r4 = org.jivesoftware.smack.packet.XMPPError.Type.fromString(r0);
        r0 = "";
        r1 = "by";
        r3 = r12.getAttributeValue(r0, r1);
        r1 = r2;
        r5 = r2;
    L_0x0022:
        r0 = r12.next();
        switch(r0) {
            case 2: goto L_0x002a;
            case 3: goto L_0x0074;
            default: goto L_0x0029;
        };
    L_0x0029:
        goto L_0x0022;
    L_0x002a:
        r10 = r12.getName();
        r11 = r12.getNamespace();
        r0 = r11.hashCode();
        switch(r0) {
            case 888780199: goto L_0x0041;
            default: goto L_0x0039;
        };
    L_0x0039:
        r0 = r8;
    L_0x003a:
        switch(r0) {
            case 0: goto L_0x004b;
            default: goto L_0x003d;
        };
    L_0x003d:
        addExtensionElement(r6, r12, r10, r11);
        goto L_0x0022;
    L_0x0041:
        r0 = "urn:ietf:params:xml:ns:xmpp-stanzas";
        r0 = r11.equals(r0);
        if (r0 == 0) goto L_0x0039;
    L_0x0049:
        r0 = r7;
        goto L_0x003a;
    L_0x004b:
        r0 = r10.hashCode();
        switch(r0) {
            case 3556653: goto L_0x0065;
            default: goto L_0x0052;
        };
    L_0x0052:
        r0 = r8;
    L_0x0053:
        switch(r0) {
            case 0: goto L_0x006f;
            default: goto L_0x0056;
        };
    L_0x0056:
        r1 = org.jivesoftware.smack.packet.XMPPError.Condition.fromString(r10);
        r0 = r12.isEmptyElementTag();
        if (r0 != 0) goto L_0x0022;
    L_0x0060:
        r2 = r12.nextText();
        goto L_0x0022;
    L_0x0065:
        r0 = "text";
        r0 = r10.equals(r0);
        if (r0 == 0) goto L_0x0052;
    L_0x006d:
        r0 = r7;
        goto L_0x0053;
    L_0x006f:
        r5 = parseDescriptiveTexts(r12, r5);
        goto L_0x0022;
    L_0x0074:
        r0 = r12.getDepth();
        if (r0 != r9) goto L_0x0022;
    L_0x007a:
        r0 = new org.jivesoftware.smack.packet.XMPPError;
        r0.<init>(r1, r2, r3, r4, r5, r6);
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smack.util.PacketParserUtils.parseError(org.xmlpull.v1.XmlPullParser):org.jivesoftware.smack.packet.XMPPError");
    }

    @Deprecated
    public static ExtensionElement parsePacketExtension(String str, String str2, XmlPullParser xmlPullParser) {
        return parseExtensionElement(str, str2, xmlPullParser);
    }

    public static ExtensionElement parseExtensionElement(String str, String str2, XmlPullParser xmlPullParser) {
        ParserUtils.assertAtStartTag(xmlPullParser);
        ExtensionElementProvider extensionProvider = ProviderManager.getExtensionProvider(str, str2);
        if (extensionProvider == null) {
            int depth = xmlPullParser.getDepth();
            ExtensionElement defaultExtensionElement = new DefaultExtensionElement(str, str2);
            while (true) {
                switch (xmlPullParser.next()) {
                    case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                        String name = xmlPullParser.getName();
                        if (!xmlPullParser.isEmptyElementTag()) {
                            if (xmlPullParser.next() != 4) {
                                break;
                            }
                            defaultExtensionElement.setValue(name, xmlPullParser.getText());
                            break;
                        }
                        defaultExtensionElement.setValue(name, "");
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                        if (xmlPullParser.getDepth() != depth) {
                            break;
                        }
                        return defaultExtensionElement;
                    default:
                        break;
                }
            }
        }
        return (ExtensionElement) extensionProvider.parse(xmlPullParser);
    }

    public static StartTls parseStartTlsFeature(XmlPullParser xmlPullParser) {
        if (!$assertionsDisabled && xmlPullParser.getEventType() != 2) {
            throw new AssertionError();
        } else if ($assertionsDisabled || xmlPullParser.getNamespace().equals(StartTls.NAMESPACE)) {
            int depth = xmlPullParser.getDepth();
            boolean z = $assertionsDisabled;
            while (true) {
                switch (xmlPullParser.next()) {
                    case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                        String name = xmlPullParser.getName();
                        Object obj = -1;
                        switch (name.hashCode()) {
                            case -393139297:
                                if (name.equals("required")) {
                                    obj = null;
                                    break;
                                }
                                break;
                        }
                        switch (obj) {
                            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                                z = true;
                                break;
                            default:
                                break;
                        }
                    case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                        if (xmlPullParser.getDepth() != depth) {
                            break;
                        } else if ($assertionsDisabled || xmlPullParser.getEventType() == 3) {
                            return new StartTls(z);
                        } else {
                            throw new AssertionError();
                        }
                        break;
                    default:
                        break;
                }
            }
        } else {
            throw new AssertionError();
        }
    }

    public static Feature parseSessionFeature(XmlPullParser xmlPullParser) {
        boolean z;
        ParserUtils.assertAtStartTag(xmlPullParser);
        int depth = xmlPullParser.getDepth();
        if (xmlPullParser.isEmptyElementTag()) {
            z = $assertionsDisabled;
        } else {
            z = false;
            while (true) {
                switch (xmlPullParser.next()) {
                    case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                        String name = xmlPullParser.getName();
                        Object obj = -1;
                        switch (name.hashCode()) {
                            case -79017120:
                                if (name.equals(Feature.OPTIONAL_ELEMENT)) {
                                    obj = null;
                                    break;
                                }
                                break;
                        }
                        switch (obj) {
                            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                                z = true;
                                break;
                            default:
                                continue;
                        }
                    case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                        if (xmlPullParser.getDepth() == depth) {
                            break;
                        }
                        continue;
                    default:
                        continue;
                }
            }
        }
        return new Feature(z);
    }

    private static String getLanguageAttribute(XmlPullParser xmlPullParser) {
        int i = 0;
        while (i < xmlPullParser.getAttributeCount()) {
            String attributeName = xmlPullParser.getAttributeName(i);
            if ("xml:lang".equals(attributeName) || ("lang".equals(attributeName) && "xml".equals(xmlPullParser.getAttributePrefix(i)))) {
                return xmlPullParser.getAttributeValue(i);
            }
            i++;
        }
        return null;
    }

    @Deprecated
    public static void addPacketExtension(Stanza stanza, XmlPullParser xmlPullParser) {
        addExtensionElement(stanza, xmlPullParser);
    }

    @Deprecated
    public static void addPacketExtension(Stanza stanza, XmlPullParser xmlPullParser, String str, String str2) {
        addExtensionElement(stanza, xmlPullParser, str, str2);
    }

    @Deprecated
    public static void addPacketExtension(Collection<ExtensionElement> collection, XmlPullParser xmlPullParser) {
        addExtensionElement((Collection) collection, xmlPullParser, xmlPullParser.getName(), xmlPullParser.getNamespace());
    }

    @Deprecated
    public static void addPacketExtension(Collection<ExtensionElement> collection, XmlPullParser xmlPullParser, String str, String str2) {
        addExtensionElement((Collection) collection, xmlPullParser, str, str2);
    }

    public static void addExtensionElement(Stanza stanza, XmlPullParser xmlPullParser) {
        ParserUtils.assertAtStartTag(xmlPullParser);
        addExtensionElement(stanza, xmlPullParser, xmlPullParser.getName(), xmlPullParser.getNamespace());
    }

    public static void addExtensionElement(Stanza stanza, XmlPullParser xmlPullParser, String str, String str2) {
        stanza.addExtension(parseExtensionElement(str, str2, xmlPullParser));
    }

    public static void addExtensionElement(Collection<ExtensionElement> collection, XmlPullParser xmlPullParser) {
        addExtensionElement((Collection) collection, xmlPullParser, xmlPullParser.getName(), xmlPullParser.getNamespace());
    }

    public static void addExtensionElement(Collection<ExtensionElement> collection, XmlPullParser xmlPullParser, String str, String str2) {
        collection.add(parseExtensionElement(str, str2, xmlPullParser));
    }
}
