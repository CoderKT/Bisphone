package org.jivesoftware.smack.util;

import java.util.Collection;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.NamedElement;

public class XmlStringBuilder implements Appendable, CharSequence {
    static final /* synthetic */ boolean $assertionsDisabled;
    public static final String RIGHT_ANGLE_BRACKET;
    private final LazyStringBuilder sb;

    static {
        $assertionsDisabled = !XmlStringBuilder.class.desiredAssertionStatus();
        RIGHT_ANGLE_BRACKET = Character.toString('>');
    }

    public XmlStringBuilder() {
        this.sb = new LazyStringBuilder();
    }

    public XmlStringBuilder(ExtensionElement extensionElement) {
        this();
        prelude(extensionElement);
    }

    public XmlStringBuilder(NamedElement namedElement) {
        this();
        halfOpenElement(namedElement.getElementName());
    }

    public XmlStringBuilder escapedElement(String str, String str2) {
        if ($assertionsDisabled || str2 != null) {
            openElement(str);
            append((CharSequence) str2);
            closeElement(str);
            return this;
        }
        throw new AssertionError();
    }

    public XmlStringBuilder element(String str, String str2) {
        if ($assertionsDisabled || str2 != null) {
            openElement(str);
            escape(str2);
            closeElement(str);
            return this;
        }
        throw new AssertionError();
    }

    public XmlStringBuilder element(String str, Enum<?> enumR) {
        if ($assertionsDisabled || enumR != null) {
            element(str, enumR.name());
            return this;
        }
        throw new AssertionError();
    }

    public XmlStringBuilder element(Element element) {
        if ($assertionsDisabled || element != null) {
            return append(element.toXML());
        }
        throw new AssertionError();
    }

    public XmlStringBuilder optElement(String str, String str2) {
        if (str2 != null) {
            element(str, str2);
        }
        return this;
    }

    public XmlStringBuilder optElement(Element element) {
        if (element != null) {
            append(element.toXML());
        }
        return this;
    }

    public XmlStringBuilder optElement(String str, Enum<?> enumR) {
        if (enumR != null) {
            element(str, (Enum) enumR);
        }
        return this;
    }

    public XmlStringBuilder optIntElement(String str, int i) {
        if (i >= 0) {
            element(str, String.valueOf(i));
        }
        return this;
    }

    public XmlStringBuilder halfOpenElement(String str) {
        if ($assertionsDisabled || StringUtils.isNotEmpty(str)) {
            this.sb.append('<').append((CharSequence) str);
            return this;
        }
        throw new AssertionError();
    }

    public XmlStringBuilder halfOpenElement(NamedElement namedElement) {
        return halfOpenElement(namedElement.getElementName());
    }

    public XmlStringBuilder openElement(String str) {
        halfOpenElement(str).rightAngleBracket();
        return this;
    }

    public XmlStringBuilder closeElement(String str) {
        this.sb.append((CharSequence) "</").append((CharSequence) str);
        rightAngleBracket();
        return this;
    }

    public XmlStringBuilder closeElement(NamedElement namedElement) {
        closeElement(namedElement.getElementName());
        return this;
    }

    public XmlStringBuilder closeEmptyElement() {
        this.sb.append((CharSequence) "/>");
        return this;
    }

    public XmlStringBuilder rightAngleBracket() {
        this.sb.append(RIGHT_ANGLE_BRACKET);
        return this;
    }

    @Deprecated
    public XmlStringBuilder rightAngelBracket() {
        return rightAngleBracket();
    }

    public XmlStringBuilder attribute(String str, String str2) {
        if ($assertionsDisabled || str2 != null) {
            this.sb.append(' ').append((CharSequence) str).append((CharSequence) "='");
            escape(str2);
            this.sb.append('\'');
            return this;
        }
        throw new AssertionError();
    }

    public XmlStringBuilder attribute(String str, Enum<?> enumR) {
        if ($assertionsDisabled || enumR != null) {
            attribute(str, enumR.name());
            return this;
        }
        throw new AssertionError();
    }

    public XmlStringBuilder attribute(String str, int i) {
        if ($assertionsDisabled || str != null) {
            return attribute(str, String.valueOf(i));
        }
        throw new AssertionError();
    }

    public XmlStringBuilder optAttribute(String str, String str2) {
        if (str2 != null) {
            attribute(str, str2);
        }
        return this;
    }

    public XmlStringBuilder optAttribute(String str, Enum<?> enumR) {
        if (enumR != null) {
            attribute(str, enumR.toString());
        }
        return this;
    }

    public XmlStringBuilder optIntAttribute(String str, int i) {
        if (i >= 0) {
            attribute(str, Integer.toString(i));
        }
        return this;
    }

    public XmlStringBuilder optLongAttribute(String str, Long l) {
        if (l != null && l.longValue() >= 0) {
            attribute(str, Long.toString(l.longValue()));
        }
        return this;
    }

    public XmlStringBuilder optBooleanAttribute(String str, boolean z) {
        if (z) {
            this.sb.append(' ').append((CharSequence) str).append((CharSequence) "='true'");
        }
        return this;
    }

    public XmlStringBuilder xmlnsAttribute(String str) {
        optAttribute("xmlns", str);
        return this;
    }

    public XmlStringBuilder xmllangAttribute(String str) {
        optAttribute("xml:lang", str);
        return this;
    }

    public XmlStringBuilder escape(String str) {
        if ($assertionsDisabled || str != null) {
            this.sb.append(StringUtils.escapeForXML(str));
            return this;
        }
        throw new AssertionError();
    }

    public XmlStringBuilder prelude(ExtensionElement extensionElement) {
        return prelude(extensionElement.getElementName(), extensionElement.getNamespace());
    }

    public XmlStringBuilder prelude(String str, String str2) {
        halfOpenElement(str);
        xmlnsAttribute(str2);
        return this;
    }

    public XmlStringBuilder optAppend(CharSequence charSequence) {
        if (charSequence != null) {
            append(charSequence);
        }
        return this;
    }

    public XmlStringBuilder optAppend(Element element) {
        if (element != null) {
            append(element.toXML());
        }
        return this;
    }

    public XmlStringBuilder append(XmlStringBuilder xmlStringBuilder) {
        if ($assertionsDisabled || xmlStringBuilder != null) {
            this.sb.append(xmlStringBuilder.sb);
            return this;
        }
        throw new AssertionError();
    }

    public XmlStringBuilder append(Collection<? extends Element> collection) {
        for (Element toXML : collection) {
            append(toXML.toXML());
        }
        return this;
    }

    public XmlStringBuilder emptyElement(Enum<?> enumR) {
        return emptyElement(enumR.name());
    }

    public XmlStringBuilder emptyElement(String str) {
        halfOpenElement(str);
        return closeEmptyElement();
    }

    public XmlStringBuilder condEmptyElement(boolean z, String str) {
        if (z) {
            emptyElement(str);
        }
        return this;
    }

    public XmlStringBuilder condAttribute(boolean z, String str, String str2) {
        if (z) {
            attribute(str, str2);
        }
        return this;
    }

    public XmlStringBuilder append(CharSequence charSequence) {
        if ($assertionsDisabled || charSequence != null) {
            this.sb.append(charSequence);
            return this;
        }
        throw new AssertionError();
    }

    public XmlStringBuilder append(CharSequence charSequence, int i, int i2) {
        if ($assertionsDisabled || charSequence != null) {
            this.sb.append(charSequence, i, i2);
            return this;
        }
        throw new AssertionError();
    }

    public XmlStringBuilder append(char c) {
        this.sb.append(c);
        return this;
    }

    public int length() {
        return this.sb.length();
    }

    public char charAt(int i) {
        return this.sb.charAt(i);
    }

    public CharSequence subSequence(int i, int i2) {
        return this.sb.subSequence(i, i2);
    }

    public String toString() {
        return this.sb.toString();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CharSequence)) {
            return false;
        }
        return toString().equals(((CharSequence) obj).toString());
    }

    public int hashCode() {
        return toString().hashCode();
    }
}
