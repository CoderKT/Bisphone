package org.jivesoftware.smackx.receipts;

import org.jivesoftware.smack.packet.Stanza;

public interface ReceiptReceivedListener {
    void onReceiptReceived(String str, String str2, String str3, Stanza stanza);
}
