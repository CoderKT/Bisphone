package org.jivesoftware.smackx.offline;

import java.util.ArrayList;
import java.util.List;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.PacketCollector.Configuration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.StanzaExtensionFilter;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.filter.StanzaTypeFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.disco.packet.DiscoverItems.Item;
import org.jivesoftware.smackx.offline.packet.OfflineMessageInfo;
import org.jivesoftware.smackx.offline.packet.OfflineMessageRequest;
import org.jivesoftware.smackx.xdata.Form;

public class OfflineMessageManager {
    private static final StanzaFilter PACKET_FILTER;
    private static final String namespace = "http://jabber.org/protocol/offline";
    private final XMPPConnection connection;

    /* renamed from: org.jivesoftware.smackx.offline.OfflineMessageManager.1 */
    class C10911 implements StanzaFilter {
        final /* synthetic */ List val$nodes;

        C10911(List list) {
            this.val$nodes = list;
        }

        public boolean accept(Stanza stanza) {
            return this.val$nodes.contains(((OfflineMessageInfo) stanza.getExtension(OfflineMessageRequest.ELEMENT, OfflineMessageManager.namespace)).getNode());
        }
    }

    static {
        PACKET_FILTER = new AndFilter(new StanzaExtensionFilter(new OfflineMessageInfo()), StanzaTypeFilter.MESSAGE);
    }

    public OfflineMessageManager(XMPPConnection xMPPConnection) {
        this.connection = xMPPConnection;
    }

    public boolean supportsFlexibleRetrieval() {
        return ServiceDiscoveryManager.getInstanceFor(this.connection).serverSupportsFeature(namespace);
    }

    public int getMessageCount() {
        Form formFrom = Form.getFormFrom(ServiceDiscoveryManager.getInstanceFor(this.connection).discoverInfo(null, namespace));
        if (formFrom != null) {
            return Integer.parseInt((String) formFrom.getField("number_of_messages").getValues().get(0));
        }
        return 0;
    }

    public List<OfflineMessageHeader> getHeaders() {
        List<OfflineMessageHeader> arrayList = new ArrayList();
        for (Item offlineMessageHeader : ServiceDiscoveryManager.getInstanceFor(this.connection).discoverItems(null, namespace).getItems()) {
            arrayList.add(new OfflineMessageHeader(offlineMessageHeader));
        }
        return arrayList;
    }

    public List<Message> getMessages(List<String> list) {
        List<Message> arrayList = new ArrayList();
        IQ offlineMessageRequest = new OfflineMessageRequest();
        for (String item : list) {
            OfflineMessageRequest.Item item2 = new OfflineMessageRequest.Item(item);
            item2.setAction("view");
            offlineMessageRequest.addItem(item2);
        }
        PacketCollector createPacketCollector = this.connection.createPacketCollector(new AndFilter(PACKET_FILTER, new C10911(list)));
        try {
            this.connection.createPacketCollectorAndSend(offlineMessageRequest).nextResultOrThrow();
            for (Object obj = (Message) createPacketCollector.nextResult(); obj != null; Message message = (Message) createPacketCollector.nextResult()) {
                arrayList.add(obj);
            }
            return arrayList;
        } finally {
            createPacketCollector.cancel();
        }
    }

    public List<Message> getMessages() {
        IQ offlineMessageRequest = new OfflineMessageRequest();
        offlineMessageRequest.setFetch(true);
        PacketCollector createPacketCollectorAndSend = this.connection.createPacketCollectorAndSend(offlineMessageRequest);
        Configuration collectorToReset = PacketCollector.newConfiguration().setStanzaFilter(PACKET_FILTER).setCollectorToReset(createPacketCollectorAndSend);
        List<Message> list = this.connection;
        PacketCollector createPacketCollector = list.createPacketCollector(collectorToReset);
        try {
            createPacketCollectorAndSend.nextResultOrThrow();
            createPacketCollector.cancel();
            list = new ArrayList(createPacketCollector.getCollectedCount());
            while (true) {
                Message message = (Message) createPacketCollector.pollResult();
                if (message == null) {
                    break;
                }
                list.add(message);
            }
            return list;
        } finally {
            createPacketCollector.cancel();
        }
    }

    public void deleteMessages(List<String> list) {
        IQ offlineMessageRequest = new OfflineMessageRequest();
        for (String item : list) {
            OfflineMessageRequest.Item item2 = new OfflineMessageRequest.Item(item);
            item2.setAction(Item.REMOVE_ACTION);
            offlineMessageRequest.addItem(item2);
        }
        this.connection.createPacketCollectorAndSend(offlineMessageRequest).nextResultOrThrow();
    }

    public void deleteMessages() {
        IQ offlineMessageRequest = new OfflineMessageRequest();
        offlineMessageRequest.setPurge(true);
        this.connection.createPacketCollectorAndSend(offlineMessageRequest).nextResultOrThrow();
    }
}
