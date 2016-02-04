package org.jivesoftware.smack.packet;

import java.util.Locale;
import org.jivesoftware.smack.util.Objects;
import org.jivesoftware.smack.util.XmlStringBuilder;
import se.emilsjolander.stickylistheaders.C1128R;

public abstract class IQ extends Stanza {
    public static final String IQ_ELEMENT = "iq";
    public static final String QUERY_ELEMENT = "query";
    private final String childElementName;
    private final String childElementNamespace;
    private Type type;

    /* renamed from: org.jivesoftware.smack.packet.IQ.1 */
    /* synthetic */ class C10051 {
        static final /* synthetic */ int[] $SwitchMap$org$jivesoftware$smack$packet$IQ$Type;

        static {
            $SwitchMap$org$jivesoftware$smack$packet$IQ$Type = new int[Type.values().length];
            try {
                $SwitchMap$org$jivesoftware$smack$packet$IQ$Type[Type.get.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$org$jivesoftware$smack$packet$IQ$Type[Type.set.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public class IQChildElementXmlStringBuilder extends XmlStringBuilder {
        private final String element;
        private boolean isEmptyElement;

        private IQChildElementXmlStringBuilder(IQ iq) {
            this(iq.getChildElementName(), iq.getChildElementNamespace());
        }

        public IQChildElementXmlStringBuilder(ExtensionElement extensionElement) {
            this(extensionElement.getElementName(), extensionElement.getNamespace());
        }

        private IQChildElementXmlStringBuilder(String str, String str2) {
            prelude(str, str2);
            this.element = str;
        }

        public void setEmptyElement() {
            this.isEmptyElement = true;
        }
    }

    public enum Type {
        get,
        set,
        result,
        error;

        public static Type fromString(String str) {
            return valueOf(str.toLowerCase(Locale.US));
        }
    }

    protected abstract IQChildElementXmlStringBuilder getIQChildElementBuilder(IQChildElementXmlStringBuilder iQChildElementXmlStringBuilder);

    public IQ(IQ iq) {
        super((Stanza) iq);
        this.type = Type.get;
        this.type = iq.getType();
        this.childElementName = iq.childElementName;
        this.childElementNamespace = iq.childElementNamespace;
    }

    protected IQ(String str) {
        this(str, null);
    }

    protected IQ(String str, String str2) {
        this.type = Type.get;
        this.childElementName = str;
        this.childElementNamespace = str2;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = (Type) Objects.requireNonNull(type, "type must not be null");
    }

    public boolean isRequestIQ() {
        switch (C10051.$SwitchMap$org$jivesoftware$smack$packet$IQ$Type[this.type.ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                return true;
            default:
                return false;
        }
    }

    public final String getChildElementName() {
        return this.childElementName;
    }

    public final String getChildElementNamespace() {
        return this.childElementNamespace;
    }

    public final XmlStringBuilder toXML() {
        XmlStringBuilder xmlStringBuilder = new XmlStringBuilder();
        xmlStringBuilder.halfOpenElement(IQ_ELEMENT);
        addCommonAttributes(xmlStringBuilder);
        if (this.type == null) {
            xmlStringBuilder.attribute("type", "get");
        } else {
            xmlStringBuilder.attribute("type", this.type.toString());
        }
        xmlStringBuilder.rightAngleBracket();
        xmlStringBuilder.append(getChildElementXML());
        xmlStringBuilder.closeElement(IQ_ELEMENT);
        return xmlStringBuilder;
    }

    public final XmlStringBuilder getChildElementXML() {
        XmlStringBuilder xmlStringBuilder = new XmlStringBuilder();
        if (this.type == Type.error) {
            appendErrorIfExists(xmlStringBuilder);
        } else if (this.childElementName != null) {
            XmlStringBuilder iQChildElementBuilder = getIQChildElementBuilder(new IQChildElementXmlStringBuilder());
            if (iQChildElementBuilder != null) {
                xmlStringBuilder.append(iQChildElementBuilder);
                XmlStringBuilder extensionsXML = getExtensionsXML();
                if (iQChildElementBuilder.isEmptyElement) {
                    if (extensionsXML.length() == 0) {
                        xmlStringBuilder.closeEmptyElement();
                    } else {
                        xmlStringBuilder.rightAngleBracket();
                    }
                }
                xmlStringBuilder.append(extensionsXML);
                xmlStringBuilder.closeElement(iQChildElementBuilder.element);
            }
        }
        return xmlStringBuilder;
    }

    public static IQ createResultIQ(IQ iq) {
        return new EmptyResultIQ(iq);
    }

    public static ErrorIQ createErrorResponse(IQ iq, XMPPError xMPPError) {
        if (iq.getType() == Type.get || iq.getType() == Type.set) {
            ErrorIQ errorIQ = new ErrorIQ(xMPPError);
            errorIQ.setStanzaId(iq.getStanzaId());
            errorIQ.setFrom(iq.getTo());
            errorIQ.setTo(iq.getFrom());
            return errorIQ;
        }
        throw new IllegalArgumentException("IQ must be of type 'set' or 'get'. Original IQ: " + iq.toXML());
    }
}
