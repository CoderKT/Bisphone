package org.jivesoftware.smackx.xdata.packet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.NamedElement;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.jivesoftware.smackx.xdata.FormField;

public class DataForm implements ExtensionElement {
    public static final String ELEMENT = "x";
    public static final String NAMESPACE = "jabber:x:data";
    private final List<Element> extensionElements;
    private final List<FormField> fields;
    private List<String> instructions;
    private final List<Item> items;
    private ReportedData reportedData;
    private String title;
    private Type type;

    public class Item {
        public static final String ELEMENT = "item";
        private List<FormField> fields;

        public Item(List<FormField> list) {
            this.fields = new ArrayList();
            this.fields = list;
        }

        public List<FormField> getFields() {
            return Collections.unmodifiableList(new ArrayList(this.fields));
        }

        public CharSequence toXML() {
            CharSequence xmlStringBuilder = new XmlStringBuilder();
            xmlStringBuilder.openElement(ELEMENT);
            for (FormField toXML : getFields()) {
                xmlStringBuilder.append(toXML.toXML());
            }
            xmlStringBuilder.closeElement(ELEMENT);
            return xmlStringBuilder;
        }
    }

    public class ReportedData {
        public static final String ELEMENT = "reported";
        private List<FormField> fields;

        public ReportedData(List<FormField> list) {
            this.fields = new ArrayList();
            this.fields = list;
        }

        public List<FormField> getFields() {
            return Collections.unmodifiableList(new ArrayList(this.fields));
        }

        public CharSequence toXML() {
            CharSequence xmlStringBuilder = new XmlStringBuilder();
            xmlStringBuilder.openElement(ELEMENT);
            for (FormField toXML : getFields()) {
                xmlStringBuilder.append(toXML.toXML());
            }
            xmlStringBuilder.closeElement(ELEMENT);
            return xmlStringBuilder;
        }
    }

    public enum Type {
        form,
        submit,
        cancel,
        result;

        public static Type fromString(String str) {
            return valueOf(str.toLowerCase(Locale.US));
        }
    }

    public DataForm(Type type) {
        this.instructions = new ArrayList();
        this.items = new ArrayList();
        this.fields = new ArrayList();
        this.extensionElements = new ArrayList();
        this.type = type;
    }

    public Type getType() {
        return this.type;
    }

    public String getTitle() {
        return this.title;
    }

    public List<String> getInstructions() {
        List<String> unmodifiableList;
        synchronized (this.instructions) {
            unmodifiableList = Collections.unmodifiableList(new ArrayList(this.instructions));
        }
        return unmodifiableList;
    }

    public ReportedData getReportedData() {
        return this.reportedData;
    }

    public List<Item> getItems() {
        List<Item> unmodifiableList;
        synchronized (this.items) {
            unmodifiableList = Collections.unmodifiableList(new ArrayList(this.items));
        }
        return unmodifiableList;
    }

    public List<FormField> getFields() {
        List<FormField> unmodifiableList;
        synchronized (this.fields) {
            unmodifiableList = Collections.unmodifiableList(new ArrayList(this.fields));
        }
        return unmodifiableList;
    }

    public FormField getField(String str) {
        synchronized (this.fields) {
            for (FormField formField : this.fields) {
                if (str.equals(formField.getVariable())) {
                    return formField;
                }
            }
            return null;
        }
    }

    public String getElementName() {
        return ELEMENT;
    }

    public String getNamespace() {
        return NAMESPACE;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setInstructions(List<String> list) {
        this.instructions = list;
    }

    public void setReportedData(ReportedData reportedData) {
        this.reportedData = reportedData;
    }

    public void addField(FormField formField) {
        String variable = formField.getVariable();
        if (variable == null || getField(variable) == null) {
            synchronized (this.fields) {
                this.fields.add(formField);
            }
            return;
        }
        throw new IllegalArgumentException("This data form already contains a form field with the variable name '" + variable + "'");
    }

    public void addInstruction(String str) {
        synchronized (this.instructions) {
            this.instructions.add(str);
        }
    }

    public void addItem(Item item) {
        synchronized (this.items) {
            this.items.add(item);
        }
    }

    public void addExtensionElement(Element element) {
        this.extensionElements.add(element);
    }

    public List<Element> getExtensionElements() {
        return Collections.unmodifiableList(this.extensionElements);
    }

    public FormField getHiddenFormTypeField() {
        FormField field = getField(FormField.FORM_TYPE);
        return (field == null || field.getType() != org.jivesoftware.smackx.xdata.FormField.Type.hidden) ? null : field;
    }

    public boolean hasHiddenFormTypeField() {
        return getHiddenFormTypeField() != null;
    }

    public XmlStringBuilder toXML() {
        XmlStringBuilder xmlStringBuilder = new XmlStringBuilder((ExtensionElement) this);
        xmlStringBuilder.attribute("type", getType());
        xmlStringBuilder.rightAngleBracket();
        xmlStringBuilder.optElement("title", getTitle());
        for (String element : getInstructions()) {
            xmlStringBuilder.element("instructions", element);
        }
        if (getReportedData() != null) {
            xmlStringBuilder.append(getReportedData().toXML());
        }
        for (Item toXML : getItems()) {
            xmlStringBuilder.append(toXML.toXML());
        }
        for (FormField toXML2 : getFields()) {
            xmlStringBuilder.append(toXML2.toXML());
        }
        for (Element toXML3 : this.extensionElements) {
            xmlStringBuilder.append(toXML3.toXML());
        }
        xmlStringBuilder.closeElement((NamedElement) this);
        return xmlStringBuilder;
    }

    public static DataForm from(Stanza stanza) {
        return (DataForm) stanza.getExtension(ELEMENT, NAMESPACE);
    }
}
