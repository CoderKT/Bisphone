package io.fabric.sdk.android.services.common;

import org.jivesoftware.smack.tcp.XMPPTCPConnection.PacketWriter;

public class ResponseParser {
    public static int m13104a(int i) {
        if (i >= 200 && i <= 299) {
            return 0;
        }
        if (i >= 300 && i <= 399) {
            return 1;
        }
        if (i >= 400 && i <= 499) {
            return 0;
        }
        if (i >= PacketWriter.QUEUE_SIZE) {
            return 1;
        }
        return 1;
    }
}
