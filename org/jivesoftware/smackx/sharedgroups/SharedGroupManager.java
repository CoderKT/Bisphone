package org.jivesoftware.smackx.sharedgroups;

import java.util.List;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jivesoftware.smackx.sharedgroups.packet.SharedGroupsInfo;

public class SharedGroupManager {
    public static List<String> getSharedGroups(XMPPConnection xMPPConnection) {
        IQ sharedGroupsInfo = new SharedGroupsInfo();
        sharedGroupsInfo.setType(Type.get);
        return ((SharedGroupsInfo) xMPPConnection.createPacketCollectorAndSend(sharedGroupsInfo).nextResultOrThrow()).getGroups();
    }
}
