package org.jivesoftware.smackx.muc.packet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder;

public class MUCAdmin extends IQ {
    public static final String ELEMENT = "query";
    public static final String NAMESPACE = "http://jabber.org/protocol/muc#admin";
    private final List<MUCItem> items;

    public MUCAdmin() {
        super(ELEMENT, NAMESPACE);
        this.items = new ArrayList();
    }

    public List<MUCItem> getItems() {
        List<MUCItem> unmodifiableList;
        synchronized (this.items) {
            unmodifiableList = Collections.unmodifiableList(new ArrayList(this.items));
        }
        return unmodifiableList;
    }

    public void addItem(MUCItem mUCItem) {
        synchronized (this.items) {
            this.items.add(mUCItem);
        }
    }

    protected IQChildElementXmlStringBuilder getIQChildElementBuilder(IQChildElementXmlStringBuilder iQChildElementXmlStringBuilder) {
        iQChildElementXmlStringBuilder.rightAngleBracket();
        synchronized (this.items) {
            for (MUCItem toXML : this.items) {
                iQChildElementXmlStringBuilder.append(toXML.toXML());
            }
        }
        return iQChildElementXmlStringBuilder;
    }
}
