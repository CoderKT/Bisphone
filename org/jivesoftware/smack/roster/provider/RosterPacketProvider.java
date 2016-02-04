package org.jivesoftware.smack.roster.provider;

import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smack.roster.packet.RosterPacket;

public class RosterPacketProvider extends IQProvider<RosterPacket> {
    public static final RosterPacketProvider INSTANCE;

    static {
        INSTANCE = new RosterPacketProvider();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.jivesoftware.smack.roster.packet.RosterPacket parse(org.xmlpull.v1.XmlPullParser r8, int r9) {
        /*
        r7 = this;
        r4 = 1;
        r2 = 0;
        r3 = -1;
        r5 = new org.jivesoftware.smack.roster.packet.RosterPacket;
        r5.<init>();
        r0 = 0;
        r1 = "";
        r6 = "ver";
        r1 = r8.getAttributeValue(r1, r6);
        r5.setVersion(r1);
    L_0x0014:
        r1 = r8.next();
        switch(r1) {
            case 2: goto L_0x001c;
            case 3: goto L_0x008e;
            default: goto L_0x001b;
        };
    L_0x001b:
        goto L_0x0014;
    L_0x001c:
        r1 = r8.getName();
        r6 = r1.hashCode();
        switch(r6) {
            case 3242771: goto L_0x0063;
            case 98629247: goto L_0x006d;
            default: goto L_0x0027;
        };
    L_0x0027:
        r1 = r3;
    L_0x0028:
        switch(r1) {
            case 0: goto L_0x002c;
            case 1: goto L_0x007a;
            default: goto L_0x002b;
        };
    L_0x002b:
        goto L_0x0014;
    L_0x002c:
        r0 = "";
        r1 = "jid";
        r0 = r8.getAttributeValue(r0, r1);
        r1 = "";
        r6 = "name";
        r6 = r8.getAttributeValue(r1, r6);
        r1 = new org.jivesoftware.smack.roster.packet.RosterPacket$Item;
        r1.<init>(r0, r6);
        r0 = "";
        r6 = "ask";
        r0 = r8.getAttributeValue(r0, r6);
        r0 = org.jivesoftware.smack.roster.packet.RosterPacket.ItemStatus.fromString(r0);
        r1.setItemStatus(r0);
        r0 = "";
        r6 = "subscription";
        r0 = r8.getAttributeValue(r0, r6);
        if (r0 == 0) goto L_0x0077;
    L_0x005a:
        r0 = org.jivesoftware.smack.roster.packet.RosterPacket.ItemType.valueOf(r0);
        r1.setItemType(r0);
        r0 = r1;
        goto L_0x0014;
    L_0x0063:
        r6 = "item";
        r1 = r1.equals(r6);
        if (r1 == 0) goto L_0x0027;
    L_0x006b:
        r1 = r2;
        goto L_0x0028;
    L_0x006d:
        r6 = "group";
        r1 = r1.equals(r6);
        if (r1 == 0) goto L_0x0027;
    L_0x0075:
        r1 = r4;
        goto L_0x0028;
    L_0x0077:
        r0 = "none";
        goto L_0x005a;
    L_0x007a:
        r1 = r8.nextText();
        if (r1 == 0) goto L_0x0014;
    L_0x0080:
        r6 = r1.trim();
        r6 = r6.length();
        if (r6 <= 0) goto L_0x0014;
    L_0x008a:
        r0.addGroupName(r1);
        goto L_0x0014;
    L_0x008e:
        r1 = r8.getName();
        r6 = r1.hashCode();
        switch(r6) {
            case 3242771: goto L_0x00a4;
            case 107944136: goto L_0x00ae;
            default: goto L_0x0099;
        };
    L_0x0099:
        r1 = r3;
    L_0x009a:
        switch(r1) {
            case 0: goto L_0x009f;
            case 1: goto L_0x00b8;
            default: goto L_0x009d;
        };
    L_0x009d:
        goto L_0x0014;
    L_0x009f:
        r5.addRosterItem(r0);
        goto L_0x0014;
    L_0x00a4:
        r6 = "item";
        r1 = r1.equals(r6);
        if (r1 == 0) goto L_0x0099;
    L_0x00ac:
        r1 = r2;
        goto L_0x009a;
    L_0x00ae:
        r6 = "query";
        r1 = r1.equals(r6);
        if (r1 == 0) goto L_0x0099;
    L_0x00b6:
        r1 = r4;
        goto L_0x009a;
    L_0x00b8:
        return r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smack.roster.provider.RosterPacketProvider.parse(org.xmlpull.v1.XmlPullParser, int):org.jivesoftware.smack.roster.packet.RosterPacket");
    }
}
