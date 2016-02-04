package org.jivesoftware.smackx.pubsub;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jivesoftware.smackx.disco.packet.DiscoverItems;
import org.jivesoftware.smackx.pubsub.ItemsExtension.ItemsElementType;
import org.jivesoftware.smackx.pubsub.packet.PubSub;

public class LeafNode extends Node {
    LeafNode(XMPPConnection xMPPConnection, String str) {
        super(xMPPConnection, str);
    }

    public DiscoverItems discoverItems() {
        IQ discoverItems = new DiscoverItems();
        discoverItems.setTo(this.to);
        discoverItems.setNode(getId());
        return (DiscoverItems) this.con.createPacketCollectorAndSend(discoverItems).nextResultOrThrow();
    }

    public <T extends Item> List<T> getItems() {
        return getItems((List) null, (List) null);
    }

    public <T extends Item> List<T> getItems(String str) {
        return getItems(createPubsubPacket(Type.get, new GetItemsRequest(getId(), str)));
    }

    public <T extends Item> List<T> getItems(Collection<String> collection) {
        List arrayList = new ArrayList(collection.size());
        for (String item : collection) {
            arrayList.add(new Item(item));
        }
        return getItems(createPubsubPacket(Type.get, new ItemsExtension(ItemsElementType.items, getId(), arrayList)));
    }

    public <T extends Item> List<T> getItems(int i) {
        return getItems(createPubsubPacket(Type.get, new GetItemsRequest(getId(), i)));
    }

    public <T extends Item> List<T> getItems(int i, String str) {
        return getItems(createPubsubPacket(Type.get, new GetItemsRequest(getId(), str, i)));
    }

    public <T extends Item> List<T> getItems(List<ExtensionElement> list, List<ExtensionElement> list2) {
        PubSub createPubsubPacket = createPubsubPacket(Type.get, new GetItemsRequest(getId()));
        createPubsubPacket.addExtensions(list);
        return getItems(createPubsubPacket, (List) list2);
    }

    private <T extends Item> List<T> getItems(PubSub pubSub) {
        return getItems(pubSub, null);
    }

    private <T extends Item> List<T> getItems(PubSub pubSub, List<ExtensionElement> list) {
        PubSub pubSub2 = (PubSub) this.con.createPacketCollectorAndSend(pubSub).nextResultOrThrow();
        ItemsExtension itemsExtension = (ItemsExtension) pubSub2.getExtension(PubSubElementType.ITEMS);
        if (list != null) {
            list.addAll(pubSub2.getExtensions());
        }
        return itemsExtension.getItems();
    }

    public void publish() {
        this.con.sendStanza(createPubsubPacket(Type.set, new NodeExtension(PubSubElementType.PUBLISH, getId())));
    }

    public <T extends Item> void publish(T t) {
        Collection arrayList = new ArrayList(1);
        if (t == null) {
            t = new Item();
        }
        arrayList.add(t);
        publish(arrayList);
    }

    public <T extends Item> void publish(Collection<T> collection) {
        this.con.sendStanza(createPubsubPacket(Type.set, new PublishItem(getId(), (Collection) collection)));
    }

    public void send() {
        this.con.createPacketCollectorAndSend(createPubsubPacket(Type.set, new NodeExtension(PubSubElementType.PUBLISH, getId()))).nextResultOrThrow();
    }

    public <T extends Item> void send(T t) {
        Collection arrayList = new ArrayList(1);
        if (t == null) {
            t = new Item();
        }
        arrayList.add(t);
        send(arrayList);
    }

    public <T extends Item> void send(Collection<T> collection) {
        this.con.createPacketCollectorAndSend(createPubsubPacket(Type.set, new PublishItem(getId(), (Collection) collection))).nextResultOrThrow();
    }

    public void deleteAllItems() {
        this.con.createPacketCollectorAndSend(createPubsubPacket(Type.set, new NodeExtension(PubSubElementType.PURGE_OWNER, getId()), PubSubElementType.PURGE_OWNER.getNamespace())).nextResultOrThrow();
    }

    public void deleteItem(String str) {
        Collection arrayList = new ArrayList(1);
        arrayList.add(str);
        deleteItem(arrayList);
    }

    public void deleteItem(Collection<String> collection) {
        List arrayList = new ArrayList(collection.size());
        for (String item : collection) {
            arrayList.add(new Item(item));
        }
        this.con.createPacketCollectorAndSend(createPubsubPacket(Type.set, new ItemsExtension(ItemsElementType.retract, getId(), arrayList))).nextResultOrThrow();
    }
}
