package org.jivesoftware.smackx.pubsub;

import java.util.Collections;
import java.util.List;

public class AffiliationsExtension extends NodeExtension {
    protected List<Affiliation> items;

    public AffiliationsExtension() {
        super(PubSubElementType.AFFILIATIONS);
        this.items = Collections.emptyList();
    }

    public AffiliationsExtension(List<Affiliation> list) {
        super(PubSubElementType.AFFILIATIONS);
        this.items = Collections.emptyList();
        this.items = list;
    }

    public List<Affiliation> getAffiliations() {
        return this.items;
    }

    public CharSequence toXML() {
        if (this.items == null || this.items.size() == 0) {
            return super.toXML();
        }
        StringBuilder stringBuilder = new StringBuilder("<");
        stringBuilder.append(getElementName());
        stringBuilder.append(">");
        for (Affiliation toXML : this.items) {
            stringBuilder.append(toXML.toXML());
        }
        stringBuilder.append("</");
        stringBuilder.append(getElementName());
        stringBuilder.append(">");
        return stringBuilder.toString();
    }
}
