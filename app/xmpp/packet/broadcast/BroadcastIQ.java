package app.xmpp.packet.broadcast;

import app.xmpp.JabberId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smackx.search.UserSearch;

public class BroadcastIQ extends IQ {
    final List<Item> f5056a;
    Message f5057b;

    class Item {
        String f5052a;
        final /* synthetic */ BroadcastIQ f5053b;
        private String f5054c;
        private String f5055d;

        public Item(BroadcastIQ broadcastIQ, String str) {
            this.f5053b = broadcastIQ;
            this.f5054c = "u";
            this.f5055d = "value";
            this.f5052a = str;
        }

        public CharSequence m7532a() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<").append(this.f5054c).append(" ").append(this.f5055d).append("='").append(new JabberId(this.f5052a, "bisphone.com", null).m7391e()).append("'/>");
            return stringBuilder.toString();
        }
    }

    public BroadcastIQ(ArrayList<String> arrayList, Message message) {
        super(UserSearch.ELEMENT, "http://bisphone.com/protocol/multicast");
        setType(Type.set);
        message.setTo(null);
        this.f5057b = message;
        List arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(new Item(this, (String) it.next()));
        }
        this.f5056a = arrayList2;
    }

    protected IQChildElementXmlStringBuilder getIQChildElementBuilder(IQChildElementXmlStringBuilder iQChildElementXmlStringBuilder) {
        iQChildElementXmlStringBuilder.rightAngleBracket();
        iQChildElementXmlStringBuilder.append(this.f5057b.toXML());
        iQChildElementXmlStringBuilder.halfOpenElement("receivers").rightAngleBracket();
        synchronized (this.f5056a) {
            for (Item a : this.f5056a) {
                iQChildElementXmlStringBuilder.append(a.m7532a());
            }
        }
        iQChildElementXmlStringBuilder.closeElement("receivers");
        return iQChildElementXmlStringBuilder;
    }
}
