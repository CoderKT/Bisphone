package org.jivesoftware.smackx.pubsub;

import org.jivesoftware.smack.packet.ExtensionElement;

public class Affiliation implements ExtensionElement {
    protected String node;
    protected Type type;

    public enum Type {
        member,
        none,
        outcast,
        owner,
        publisher
    }

    public Affiliation(String str, Type type) {
        this.node = str;
        this.type = type;
    }

    public String getNodeId() {
        return this.node;
    }

    public Type getType() {
        return this.type;
    }

    public String getElementName() {
        return "subscription";
    }

    public String getNamespace() {
        return null;
    }

    public String toXML() {
        StringBuilder stringBuilder = new StringBuilder("<");
        stringBuilder.append(getElementName());
        appendAttribute(stringBuilder, "node", this.node);
        appendAttribute(stringBuilder, "affiliation", this.type.toString());
        stringBuilder.append("/>");
        return stringBuilder.toString();
    }

    private void appendAttribute(StringBuilder stringBuilder, String str, String str2) {
        stringBuilder.append(" ");
        stringBuilder.append(str);
        stringBuilder.append("='");
        stringBuilder.append(str2);
        stringBuilder.append("'");
    }
}
