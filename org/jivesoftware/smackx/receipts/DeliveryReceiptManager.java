package org.jivesoftware.smackx.receipts;

import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.filter.NotFilter;
import org.jivesoftware.smack.filter.StanzaExtensionFilter;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.filter.StanzaTypeFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import se.emilsjolander.stickylistheaders.C1128R;

public class DeliveryReceiptManager extends Manager {
    private static final StanzaListener AUTO_ADD_DELIVERY_RECEIPT_REQUESTS_LISTENER;
    private static final StanzaFilter MESSAGES_TO_REQUEST_RECEIPTS_FOR;
    private static final StanzaFilter MESSAGES_WITH_DELIVERY_RECEIPT;
    private static final StanzaFilter MESSAGES_WITH_DEVLIERY_RECEIPT_REQUEST;
    private static AutoReceiptMode defaultAutoReceiptMode;
    private static Map<XMPPConnection, DeliveryReceiptManager> instances;
    private AutoReceiptMode autoReceiptMode;
    private final Set<ReceiptReceivedListener> receiptReceivedListeners;

    /* renamed from: org.jivesoftware.smackx.receipts.DeliveryReceiptManager.1 */
    final class C11081 implements ConnectionCreationListener {
        C11081() {
        }

        public void connectionCreated(XMPPConnection xMPPConnection) {
            DeliveryReceiptManager.getInstanceFor(xMPPConnection);
        }
    }

    /* renamed from: org.jivesoftware.smackx.receipts.DeliveryReceiptManager.2 */
    class C11092 implements StanzaListener {
        C11092() {
        }

        public void processPacket(Stanza stanza) {
            DeliveryReceipt from = DeliveryReceipt.from((Message) stanza);
            for (ReceiptReceivedListener onReceiptReceived : DeliveryReceiptManager.this.receiptReceivedListeners) {
                onReceiptReceived.onReceiptReceived(stanza.getFrom(), stanza.getTo(), from.getId(), stanza);
            }
        }
    }

    /* renamed from: org.jivesoftware.smackx.receipts.DeliveryReceiptManager.3 */
    class C11103 implements StanzaListener {
        C11103() {
        }

        public void processPacket(Stanza stanza) {
            String from = stanza.getFrom();
            XMPPConnection access$100 = DeliveryReceiptManager.this.connection();
            switch (C11125.f8582xc3f23bee[DeliveryReceiptManager.this.autoReceiptMode.ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    return;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    if (!Roster.getInstanceFor(access$100).isSubscribedToMyPresence(from)) {
                        return;
                    }
                    break;
            }
            access$100.sendStanza(DeliveryReceiptManager.receiptMessageFor((Message) stanza));
        }
    }

    /* renamed from: org.jivesoftware.smackx.receipts.DeliveryReceiptManager.4 */
    final class C11114 implements StanzaListener {
        C11114() {
        }

        public void processPacket(Stanza stanza) {
            DeliveryReceiptRequest.addTo((Message) stanza);
        }
    }

    /* renamed from: org.jivesoftware.smackx.receipts.DeliveryReceiptManager.5 */
    /* synthetic */ class C11125 {
        static final /* synthetic */ int[] f8582xc3f23bee;

        static {
            f8582xc3f23bee = new int[AutoReceiptMode.values().length];
            try {
                f8582xc3f23bee[AutoReceiptMode.disabled.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f8582xc3f23bee[AutoReceiptMode.ifIsSubscribed.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f8582xc3f23bee[AutoReceiptMode.always.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public enum AutoReceiptMode {
        disabled,
        ifIsSubscribed,
        always
    }

    static {
        MESSAGES_WITH_DEVLIERY_RECEIPT_REQUEST = new AndFilter(StanzaTypeFilter.MESSAGE, new StanzaExtensionFilter(new DeliveryReceiptRequest()));
        MESSAGES_WITH_DELIVERY_RECEIPT = new AndFilter(StanzaTypeFilter.MESSAGE, new StanzaExtensionFilter(DeliveryReceipt.ELEMENT, DeliveryReceipt.NAMESPACE));
        instances = new WeakHashMap();
        XMPPConnectionRegistry.addConnectionCreationListener(new C11081());
        defaultAutoReceiptMode = AutoReceiptMode.ifIsSubscribed;
        MESSAGES_TO_REQUEST_RECEIPTS_FOR = new AndFilter(MessageTypeFilter.NORMAL_OR_CHAT_OR_HEADLINE, new NotFilter(new StanzaExtensionFilter(DeliveryReceipt.ELEMENT, DeliveryReceipt.NAMESPACE)));
        AUTO_ADD_DELIVERY_RECEIPT_REQUESTS_LISTENER = new C11114();
    }

    public static void setDefaultAutoReceiptMode(AutoReceiptMode autoReceiptMode) {
        defaultAutoReceiptMode = autoReceiptMode;
    }

    private DeliveryReceiptManager(XMPPConnection xMPPConnection) {
        super(xMPPConnection);
        this.autoReceiptMode = defaultAutoReceiptMode;
        this.receiptReceivedListeners = new CopyOnWriteArraySet();
        ServiceDiscoveryManager.getInstanceFor(xMPPConnection).addFeature(DeliveryReceipt.NAMESPACE);
        xMPPConnection.addAsyncStanzaListener(new C11092(), MESSAGES_WITH_DELIVERY_RECEIPT);
        xMPPConnection.addAsyncStanzaListener(new C11103(), MESSAGES_WITH_DEVLIERY_RECEIPT_REQUEST);
    }

    public static synchronized DeliveryReceiptManager getInstanceFor(XMPPConnection xMPPConnection) {
        DeliveryReceiptManager deliveryReceiptManager;
        synchronized (DeliveryReceiptManager.class) {
            deliveryReceiptManager = (DeliveryReceiptManager) instances.get(xMPPConnection);
            if (deliveryReceiptManager == null) {
                deliveryReceiptManager = new DeliveryReceiptManager(xMPPConnection);
                instances.put(xMPPConnection, deliveryReceiptManager);
            }
        }
        return deliveryReceiptManager;
    }

    public boolean isSupported(String str) {
        return ServiceDiscoveryManager.getInstanceFor(connection()).supportsFeature(str, DeliveryReceipt.NAMESPACE);
    }

    public void setAutoReceiptMode(AutoReceiptMode autoReceiptMode) {
        this.autoReceiptMode = autoReceiptMode;
    }

    public AutoReceiptMode getAutoReceiptMode() {
        return this.autoReceiptMode;
    }

    public void addReceiptReceivedListener(ReceiptReceivedListener receiptReceivedListener) {
        this.receiptReceivedListeners.add(receiptReceivedListener);
    }

    public void removeReceiptReceivedListener(ReceiptReceivedListener receiptReceivedListener) {
        this.receiptReceivedListeners.remove(receiptReceivedListener);
    }

    public void autoAddDeliveryReceiptRequests() {
        connection().addPacketInterceptor(AUTO_ADD_DELIVERY_RECEIPT_REQUESTS_LISTENER, MESSAGES_TO_REQUEST_RECEIPTS_FOR);
    }

    public void dontAutoAddDeliveryReceiptRequests() {
        connection().removePacketInterceptor(AUTO_ADD_DELIVERY_RECEIPT_REQUESTS_LISTENER);
    }

    public static boolean hasDeliveryReceiptRequest(Message message) {
        return DeliveryReceiptRequest.from(message) != null;
    }

    @Deprecated
    public static String addDeliveryReceiptRequest(Message message) {
        return DeliveryReceiptRequest.addTo(message);
    }

    public static Message receiptMessageFor(Message message) {
        Message message2 = new Message(message.getFrom(), message.getType());
        message2.addExtension(new DeliveryReceipt(message.getStanzaId()));
        return message2;
    }
}
