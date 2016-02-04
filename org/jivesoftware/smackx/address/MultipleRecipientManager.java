package org.jivesoftware.smackx.address;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.SmackException.FeatureNotSupportedException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smackx.address.packet.MultipleAddresses;
import org.jivesoftware.smackx.address.packet.MultipleAddresses.Address;
import org.jivesoftware.smackx.address.packet.MultipleAddresses.Type;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jxmpp.util.XmppStringUtils;

public class MultipleRecipientManager {

    class PacketCopy extends Stanza {
        private CharSequence text;

        public PacketCopy(CharSequence charSequence) {
            this.text = charSequence;
        }

        public CharSequence toXML() {
            return this.text;
        }
    }

    public static void send(XMPPConnection xMPPConnection, Stanza stanza, Collection<String> collection, Collection<String> collection2, Collection<String> collection3) {
        send(xMPPConnection, stanza, collection, collection2, collection3, null, null, false);
    }

    public static void send(XMPPConnection xMPPConnection, Stanza stanza, Collection<String> collection, Collection<String> collection2, Collection<String> collection3, String str, String str2, boolean z) {
        if (collection != null && collection.size() == 1 && ((collection2 == null || collection2.isEmpty()) && ((collection3 == null || collection3.isEmpty()) && !z && StringUtils.isNullOrEmpty(str) && StringUtils.isNullOrEmpty(str2)))) {
            stanza.setTo((String) collection.iterator().next());
            xMPPConnection.sendStanza(stanza);
            return;
        }
        String multipleRecipienServiceAddress = getMultipleRecipienServiceAddress(xMPPConnection);
        if (multipleRecipienServiceAddress != null) {
            sendThroughService(xMPPConnection, stanza, collection, collection2, collection3, str, str2, z, multipleRecipienServiceAddress);
        } else if (z || ((str != null && str.trim().length() > 0) || (str2 != null && str2.trim().length() > 0))) {
            throw new FeatureNotSupportedException("Extended Stanza Addressing");
        } else {
            sendToIndividualRecipients(xMPPConnection, stanza, collection, collection2, collection3);
        }
    }

    public static void reply(XMPPConnection xMPPConnection, Message message, Message message2) {
        MultipleRecipientInfo multipleRecipientInfo = getMultipleRecipientInfo(message);
        if (multipleRecipientInfo == null) {
            throw new SmackException("Original message does not contain multiple recipient info");
        } else if (multipleRecipientInfo.shouldNotReply()) {
            throw new SmackException("Original message should not be replied");
        } else if (multipleRecipientInfo.getReplyRoom() != null) {
            throw new SmackException("Reply should be sent through a room");
        } else {
            if (message.getThread() != null) {
                message2.setThread(message.getThread());
            }
            Address replyAddress = multipleRecipientInfo.getReplyAddress();
            if (replyAddress == null || replyAddress.getJid() == null) {
                Collection arrayList = new ArrayList(multipleRecipientInfo.getTOAddresses().size());
                Collection arrayList2 = new ArrayList(multipleRecipientInfo.getCCAddresses().size());
                for (Address replyAddress2 : multipleRecipientInfo.getTOAddresses()) {
                    arrayList.add(replyAddress2.getJid());
                }
                for (Address replyAddress22 : multipleRecipientInfo.getCCAddresses()) {
                    arrayList2.add(replyAddress22.getJid());
                }
                if (!(arrayList.contains(message.getFrom()) || arrayList2.contains(message.getFrom()))) {
                    arrayList.add(message.getFrom());
                }
                String user = xMPPConnection.getUser();
                if (!(arrayList.remove(user) || arrayList2.remove(user))) {
                    user = XmppStringUtils.m13447d(user);
                    arrayList.remove(user);
                    arrayList2.remove(user);
                }
                send(xMPPConnection, message2, arrayList, arrayList2, null, null, null, false);
                return;
            }
            message2.setTo(replyAddress22.getJid());
            xMPPConnection.sendStanza(message2);
        }
    }

    public static MultipleRecipientInfo getMultipleRecipientInfo(Stanza stanza) {
        MultipleAddresses multipleAddresses = (MultipleAddresses) stanza.getExtension(MultipleAddresses.ELEMENT, MultipleAddresses.NAMESPACE);
        return multipleAddresses == null ? null : new MultipleRecipientInfo(multipleAddresses);
    }

    private static void sendToIndividualRecipients(XMPPConnection xMPPConnection, Stanza stanza, Collection<String> collection, Collection<String> collection2, Collection<String> collection3) {
        if (collection != null) {
            for (String to : collection) {
                stanza.setTo(to);
                xMPPConnection.sendStanza(new PacketCopy(stanza.toXML()));
            }
        }
        if (collection2 != null) {
            for (String to2 : collection2) {
                stanza.setTo(to2);
                xMPPConnection.sendStanza(new PacketCopy(stanza.toXML()));
            }
        }
        if (collection3 != null) {
            for (String to22 : collection3) {
                stanza.setTo(to22);
                xMPPConnection.sendStanza(new PacketCopy(stanza.toXML()));
            }
        }
    }

    private static void sendThroughService(XMPPConnection xMPPConnection, Stanza stanza, Collection<String> collection, Collection<String> collection2, Collection<String> collection3, String str, String str2, boolean z, String str3) {
        MultipleAddresses multipleAddresses = new MultipleAddresses();
        if (collection != null) {
            for (String addAddress : collection) {
                multipleAddresses.addAddress(Type.to, addAddress, null, null, false, null);
            }
        }
        if (collection2 != null) {
            for (String addAddress2 : collection2) {
                multipleAddresses.addAddress(Type.to, addAddress2, null, null, false, null);
            }
        }
        if (collection3 != null) {
            for (String addAddress22 : collection3) {
                multipleAddresses.addAddress(Type.bcc, addAddress22, null, null, false, null);
            }
        }
        if (z) {
            multipleAddresses.setNoReply();
        } else {
            if (str != null && str.trim().length() > 0) {
                multipleAddresses.addAddress(Type.replyto, str, null, null, false, null);
            }
            if (str2 != null && str2.trim().length() > 0) {
                multipleAddresses.addAddress(Type.replyroom, str2, null, null, false, null);
            }
        }
        stanza.setTo(str3);
        stanza.addExtension(multipleAddresses);
        xMPPConnection.sendStanza(stanza);
    }

    private static String getMultipleRecipienServiceAddress(XMPPConnection xMPPConnection) {
        List findServices = ServiceDiscoveryManager.getInstanceFor(xMPPConnection).findServices(MultipleAddresses.NAMESPACE, true, true);
        if (findServices.size() > 0) {
            return (String) findServices.get(0);
        }
        return null;
    }
}
