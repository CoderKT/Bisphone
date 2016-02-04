package org.jivesoftware.smackx.pubsub;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.EmptyResultIQ;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.disco.packet.DiscoverInfo;
import org.jivesoftware.smackx.disco.packet.DiscoverItems;
import org.jivesoftware.smackx.pubsub.packet.PubSub;
import org.jivesoftware.smackx.pubsub.packet.PubSubNamespace;
import org.jivesoftware.smackx.pubsub.util.NodeUtils;
import org.jivesoftware.smackx.xdata.Form;
import org.jivesoftware.smackx.xdata.FormField;

public final class PubSubManager {
    private XMPPConnection con;
    private Map<String, Node> nodeMap;
    private String to;

    public PubSubManager(XMPPConnection xMPPConnection) {
        this.nodeMap = new ConcurrentHashMap();
        this.con = xMPPConnection;
        this.to = "pubsub." + xMPPConnection.getServiceName();
    }

    public PubSubManager(XMPPConnection xMPPConnection, String str) {
        this.nodeMap = new ConcurrentHashMap();
        this.con = xMPPConnection;
        this.to = str;
    }

    public LeafNode createNode() {
        LeafNode leafNode = new LeafNode(this.con, ((NodeExtension) sendPubsubPacket(Type.set, new NodeExtension(PubSubElementType.CREATE), null).getExtension("create", PubSubNamespace.BASIC.getXmlns())).getNode());
        leafNode.setTo(this.to);
        this.nodeMap.put(leafNode.getId(), leafNode);
        return leafNode;
    }

    public LeafNode createNode(String str) {
        return (LeafNode) createNode(str, null);
    }

    public Node createNode(String str, Form form) {
        PubSub createPubsubPacket = PubSub.createPubsubPacket(this.to, Type.set, new NodeExtension(PubSubElementType.CREATE, str), null);
        boolean z = true;
        if (form != null) {
            createPubsubPacket.addExtension(new FormNode(FormNodeType.CONFIGURE, form));
            FormField field = form.getField(ConfigureNodeFields.node_type.getFieldName());
            if (field != null) {
                z = ((String) field.getValues().get(0)).equals(NodeType.leaf.toString());
            }
        }
        sendPubsubPacket(this.con, createPubsubPacket);
        Node leafNode = z ? new LeafNode(this.con, str) : new CollectionNode(this.con, str);
        leafNode.setTo(this.to);
        this.nodeMap.put(leafNode.getId(), leafNode);
        return leafNode;
    }

    public <T extends Node> T getNode(String str) {
        T t = (Node) this.nodeMap.get(str);
        if (t == null) {
            IQ discoverInfo = new DiscoverInfo();
            discoverInfo.setTo(this.to);
            discoverInfo.setNode(str);
            DiscoverInfo discoverInfo2 = (DiscoverInfo) this.con.createPacketCollectorAndSend(discoverInfo).nextResultOrThrow();
            if (discoverInfo2.hasIdentity(PubSub.ELEMENT, "leaf")) {
                t = new LeafNode(this.con, str);
            } else if (discoverInfo2.hasIdentity(PubSub.ELEMENT, "collection")) {
                t = new CollectionNode(this.con, str);
            } else {
                throw new AssertionError("PubSub service '" + this.to + "' returned disco info result for node '" + str + "', but it did not contain an Identity of type 'leaf' or 'collection' (and category 'pubsub'), which is not allowed according to XEP-60 5.3.");
            }
            t.setTo(this.to);
            this.nodeMap.put(str, t);
        }
        return t;
    }

    public DiscoverItems discoverNodes(String str) {
        IQ discoverItems = new DiscoverItems();
        if (str != null) {
            discoverItems.setNode(str);
        }
        discoverItems.setTo(this.to);
        return (DiscoverItems) this.con.createPacketCollectorAndSend(discoverItems).nextResultOrThrow();
    }

    public List<Subscription> getSubscriptions() {
        return ((SubscriptionsExtension) sendPubsubPacket(Type.get, new NodeExtension(PubSubElementType.SUBSCRIPTIONS), null).getExtension(PubSubElementType.SUBSCRIPTIONS.getElementName(), PubSubElementType.SUBSCRIPTIONS.getNamespace().getXmlns())).getSubscriptions();
    }

    public List<Affiliation> getAffiliations() {
        return ((AffiliationsExtension) sendPubsubPacket(Type.get, new NodeExtension(PubSubElementType.AFFILIATIONS), null).getExtension(PubSubElementType.AFFILIATIONS)).getAffiliations();
    }

    public void deleteNode(String str) {
        sendPubsubPacket(Type.set, new NodeExtension(PubSubElementType.DELETE, str), PubSubElementType.DELETE.getNamespace());
        this.nodeMap.remove(str);
    }

    public ConfigureForm getDefaultConfiguration() {
        return NodeUtils.getFormFromPacket(sendPubsubPacket(Type.get, new NodeExtension(PubSubElementType.DEFAULT), PubSubElementType.DEFAULT.getNamespace()), PubSubElementType.DEFAULT);
    }

    public DiscoverInfo getSupportedFeatures() {
        return ServiceDiscoveryManager.getInstanceFor(this.con).discoverInfo(this.to);
    }

    private PubSub sendPubsubPacket(Type type, ExtensionElement extensionElement, PubSubNamespace pubSubNamespace) {
        return sendPubsubPacket(this.con, this.to, type, Collections.singletonList(extensionElement), pubSubNamespace);
    }

    static PubSub sendPubsubPacket(XMPPConnection xMPPConnection, String str, Type type, List<ExtensionElement> list, PubSubNamespace pubSubNamespace) {
        PubSub pubSub = new PubSub(str, type, pubSubNamespace);
        for (ExtensionElement addExtension : list) {
            pubSub.addExtension(addExtension);
        }
        return sendPubsubPacket(xMPPConnection, pubSub);
    }

    static PubSub sendPubsubPacket(XMPPConnection xMPPConnection, PubSub pubSub) {
        IQ iq = (IQ) xMPPConnection.createPacketCollectorAndSend(pubSub).nextResultOrThrow();
        if (iq instanceof EmptyResultIQ) {
            return null;
        }
        return (PubSub) iq;
    }
}
