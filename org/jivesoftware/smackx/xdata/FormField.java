package org.jivesoftware.smackx.xdata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jivesoftware.smack.packet.NamedElement;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.jivesoftware.smackx.xdatavalidation.packet.ValidateElement;
import se.emilsjolander.stickylistheaders.C1128R;

public class FormField implements NamedElement {
    public static final String ELEMENT = "field";
    public static final String FORM_TYPE = "FORM_TYPE";
    private String description;
    private String label;
    private final List<Option> options;
    private boolean required;
    private Type type;
    private ValidateElement validateElement;
    private final List<String> values;
    private final String variable;

    /* renamed from: org.jivesoftware.smackx.xdata.FormField.1 */
    /* synthetic */ class C11191 {
        static final /* synthetic */ int[] $SwitchMap$org$jivesoftware$smackx$xdata$FormField$Type;

        static {
            $SwitchMap$org$jivesoftware$smackx$xdata$FormField$Type = new int[Type.values().length];
            try {
                $SwitchMap$org$jivesoftware$smackx$xdata$FormField$Type[Type.bool.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
        }
    }

    public class Option implements NamedElement {
        public static final String ELEMENT = "option";
        private String label;
        private final String value;

        public Option(String str) {
            this.value = str;
        }

        public Option(String str, String str2) {
            this.label = str;
            this.value = str2;
        }

        public String getLabel() {
            return this.label;
        }

        public String getValue() {
            return this.value;
        }

        public String toString() {
            return getLabel();
        }

        public String getElementName() {
            return ELEMENT;
        }

        public XmlStringBuilder toXML() {
            XmlStringBuilder xmlStringBuilder = new XmlStringBuilder((NamedElement) this);
            xmlStringBuilder.optAttribute("label", getLabel());
            xmlStringBuilder.rightAngleBracket();
            xmlStringBuilder.element("value", getValue());
            xmlStringBuilder.closeElement((NamedElement) this);
            return xmlStringBuilder;
        }

        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            if (obj.getClass() != getClass()) {
                return false;
            }
            Option option = (Option) obj;
            if (!this.value.equals(option.value)) {
                return false;
            }
            if ((this.label == null ? "" : this.label).equals(option.label == null ? "" : option.label)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return (this.label == null ? 0 : this.label.hashCode()) + ((this.value.hashCode() + 37) * 37);
        }
    }

    public enum Type {
        bool,
        fixed,
        hidden,
        jid_multi,
        jid_single,
        list_multi,
        list_single,
        text_multi,
        text_private,
        text_single;

        public String toString() {
            switch (C11191.$SwitchMap$org$jivesoftware$smackx$xdata$FormField$Type[ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    return "boolean";
                default:
                    return name().replace('_', '-');
            }
        }

        public static Type fromString(String str) {
            if (str == null) {
                return null;
            }
            Object obj = -1;
            switch (str.hashCode()) {
                case 64711720:
                    if (str.equals("boolean")) {
                        obj = null;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                    return bool;
                default:
                    return valueOf(str.replace('-', '_'));
            }
        }
    }

    public FormField(String str) {
        this.required = false;
        this.options = new ArrayList();
        this.values = new ArrayList();
        this.variable = str;
    }

    public FormField() {
        this(null);
        this.type = Type.fixed;
    }

    public String getDescription() {
        return this.description;
    }

    public String getLabel() {
        return this.label;
    }

    public List<Option> getOptions() {
        List<Option> unmodifiableList;
        synchronized (this.options) {
            unmodifiableList = Collections.unmodifiableList(new ArrayList(this.options));
        }
        return unmodifiableList;
    }

    public boolean isRequired() {
        return this.required;
    }

    public Type getType() {
        return this.type;
    }

    public List<String> getValues() {
        List<String> unmodifiableList;
        synchronized (this.values) {
            unmodifiableList = Collections.unmodifiableList(new ArrayList(this.values));
        }
        return unmodifiableList;
    }

    public String getVariable() {
        return this.variable;
    }

    public ValidateElement getValidateElement() {
        return this.validateElement;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setLabel(String str) {
        this.label = str;
    }

    public void setRequired(boolean z) {
        this.required = z;
    }

    public void setValidateElement(ValidateElement validateElement) {
        validateElement.checkConsistency(this);
        this.validateElement = validateElement;
    }

    public void setType(Type type) {
        if (type == Type.fixed) {
            throw new IllegalArgumentException("Can not set type to fixed, use FormField constructor without arguments instead.");
        }
        this.type = type;
    }

    public void addValue(String str) {
        synchronized (this.values) {
            this.values.add(str);
        }
    }

    public void addValues(List<String> list) {
        synchronized (this.values) {
            this.values.addAll(list);
        }
    }

    protected void resetValues() {
        synchronized (this.values) {
            this.values.removeAll(new ArrayList(this.values));
        }
    }

    public void addOption(Option option) {
        synchronized (this.options) {
            this.options.add(option);
        }
    }

    public String getElementName() {
        return ELEMENT;
    }

    public XmlStringBuilder toXML() {
        XmlStringBuilder xmlStringBuilder = new XmlStringBuilder((NamedElement) this);
        xmlStringBuilder.optAttribute("label", getLabel());
        xmlStringBuilder.optAttribute("var", getVariable());
        xmlStringBuilder.optAttribute("type", getType());
        xmlStringBuilder.rightAngleBracket();
        xmlStringBuilder.optElement("desc", getDescription());
        xmlStringBuilder.condEmptyElement(isRequired(), "required");
        for (String element : getValues()) {
            xmlStringBuilder.element("value", element);
        }
        for (Option toXML : getOptions()) {
            xmlStringBuilder.append(toXML.toXML());
        }
        xmlStringBuilder.optElement(this.validateElement);
        xmlStringBuilder.closeElement((NamedElement) this);
        return xmlStringBuilder;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FormField)) {
            return false;
        }
        return toXML().equals(((FormField) obj).toXML());
    }

    public int hashCode() {
        return toXML().hashCode();
    }
}
