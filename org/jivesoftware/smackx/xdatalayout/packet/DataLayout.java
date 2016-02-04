package org.jivesoftware.smackx.xdatalayout.packet;

import java.util.ArrayList;
import java.util.List;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.NamedElement;
import org.jivesoftware.smack.util.XmlStringBuilder;

public class DataLayout implements ExtensionElement {
    public static final String ELEMENT = "page";
    public static final String NAMESPACE = "http://jabber.org/protocol/xdata-layout";
    private final String label;
    private final List<DataFormLayoutElement> pageLayout;

    public interface DataFormLayoutElement extends NamedElement {
    }

    public class Fieldref implements DataFormLayoutElement {
        public static final String ELEMENT = "fieldref";
        private final String var;

        public Fieldref(String str) {
            this.var = str;
        }

        public XmlStringBuilder toXML() {
            XmlStringBuilder xmlStringBuilder = new XmlStringBuilder((NamedElement) this);
            xmlStringBuilder.attribute("var", getVar());
            xmlStringBuilder.closeEmptyElement();
            return xmlStringBuilder;
        }

        public String getVar() {
            return this.var;
        }

        public String getElementName() {
            return ELEMENT;
        }
    }

    public class Reportedref implements DataFormLayoutElement {
        public static final String ELEMENT = "reportedref";

        public XmlStringBuilder toXML() {
            XmlStringBuilder xmlStringBuilder = new XmlStringBuilder((NamedElement) this);
            xmlStringBuilder.closeEmptyElement();
            return xmlStringBuilder;
        }

        public String getElementName() {
            return ELEMENT;
        }
    }

    public class Section implements DataFormLayoutElement {
        public static final String ELEMENT = "section";
        private final String label;
        private final List<DataFormLayoutElement> sectionLayout;

        public Section(String str) {
            this.sectionLayout = new ArrayList();
            this.label = str;
        }

        public List<DataFormLayoutElement> getSectionLayout() {
            return this.sectionLayout;
        }

        public XmlStringBuilder toXML() {
            XmlStringBuilder xmlStringBuilder = new XmlStringBuilder((NamedElement) this);
            xmlStringBuilder.optAttribute("label", getLabel());
            xmlStringBuilder.rightAngleBracket();
            DataLayout.walkList(xmlStringBuilder, getSectionLayout());
            xmlStringBuilder.closeElement(ELEMENT);
            return xmlStringBuilder;
        }

        public String getLabel() {
            return this.label;
        }

        public String getElementName() {
            return ELEMENT;
        }
    }

    public class Text implements DataFormLayoutElement {
        public static final String ELEMENT = "text";
        private final String text;

        public Text(String str) {
            this.text = str;
        }

        public XmlStringBuilder toXML() {
            XmlStringBuilder xmlStringBuilder = new XmlStringBuilder();
            xmlStringBuilder.element(ELEMENT, getText());
            return xmlStringBuilder;
        }

        public String getText() {
            return this.text;
        }

        public String getElementName() {
            return ELEMENT;
        }
    }

    public DataLayout(String str) {
        this.pageLayout = new ArrayList();
        this.label = str;
    }

    public List<DataFormLayoutElement> getPageLayout() {
        return this.pageLayout;
    }

    public String getLabel() {
        return this.label;
    }

    public String getElementName() {
        return ELEMENT;
    }

    public String getNamespace() {
        return NAMESPACE;
    }

    public XmlStringBuilder toXML() {
        XmlStringBuilder xmlStringBuilder = new XmlStringBuilder((ExtensionElement) this);
        xmlStringBuilder.optAttribute("label", getLabel());
        xmlStringBuilder.rightAngleBracket();
        walkList(xmlStringBuilder, getPageLayout());
        xmlStringBuilder.closeElement((NamedElement) this);
        return xmlStringBuilder;
    }

    private static void walkList(XmlStringBuilder xmlStringBuilder, List<DataFormLayoutElement> list) {
        for (DataFormLayoutElement toXML : list) {
            xmlStringBuilder.append(toXML.toXML());
        }
    }
}
