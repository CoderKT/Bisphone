package org.jivesoftware.smackx.pubsub;

import org.jivesoftware.smackx.xdata.Form;

public class FormNode extends NodeExtension {
    private Form configForm;

    public FormNode(FormNodeType formNodeType, Form form) {
        super(formNodeType.getNodeElement());
        if (form == null) {
            throw new IllegalArgumentException("Submit form cannot be null");
        }
        this.configForm = form;
    }

    public FormNode(FormNodeType formNodeType, String str, Form form) {
        super(formNodeType.getNodeElement(), str);
        if (form == null) {
            throw new IllegalArgumentException("Submit form cannot be null");
        }
        this.configForm = form;
    }

    public Form getForm() {
        return this.configForm;
    }

    public CharSequence toXML() {
        if (this.configForm == null) {
            return super.toXML();
        }
        StringBuilder stringBuilder = new StringBuilder("<");
        stringBuilder.append(getElementName());
        if (getNode() != null) {
            stringBuilder.append(" node='");
            stringBuilder.append(getNode());
            stringBuilder.append("'>");
        } else {
            stringBuilder.append('>');
        }
        stringBuilder.append(this.configForm.getDataFormToSend().toXML());
        stringBuilder.append("</");
        stringBuilder.append(getElementName() + '>');
        return stringBuilder.toString();
    }
}
