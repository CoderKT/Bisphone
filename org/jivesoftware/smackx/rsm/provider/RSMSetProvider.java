package org.jivesoftware.smackx.rsm.provider;

import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smackx.rsm.packet.RSMSet;

public class RSMSetProvider extends ExtensionElementProvider<RSMSet> {
    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.jivesoftware.smackx.rsm.packet.RSMSet parse(org.xmlpull.v1.XmlPullParser r12, int r13) {
        /*
        r11 = this;
        r7 = 0;
        r0 = -1;
        r8 = r0;
        r6 = r0;
        r5 = r7;
        r4 = r0;
        r3 = r0;
        r2 = r7;
        r1 = r7;
    L_0x0009:
        r9 = r12.next();
        switch(r9) {
            case 2: goto L_0x0011;
            case 3: goto L_0x0092;
            default: goto L_0x0010;
        };
    L_0x0010:
        goto L_0x0009;
    L_0x0011:
        r9 = r12.getName();
        r10 = r9.hashCode();
        switch(r10) {
            case -1392885889: goto L_0x0030;
            case 107876: goto L_0x0062;
            case 3314326: goto L_0x0058;
            case 92734940: goto L_0x0026;
            case 94851343: goto L_0x003a;
            case 97440432: goto L_0x0044;
            case 100346066: goto L_0x004e;
            default: goto L_0x001c;
        };
    L_0x001c:
        r9 = r0;
    L_0x001d:
        switch(r9) {
            case 0: goto L_0x0021;
            case 1: goto L_0x006c;
            case 2: goto L_0x0071;
            case 3: goto L_0x0076;
            case 4: goto L_0x0081;
            case 5: goto L_0x0086;
            case 6: goto L_0x008c;
            default: goto L_0x0020;
        };
    L_0x0020:
        goto L_0x0009;
    L_0x0021:
        r1 = r12.nextText();
        goto L_0x0009;
    L_0x0026:
        r10 = "after";
        r9 = r9.equals(r10);
        if (r9 == 0) goto L_0x001c;
    L_0x002e:
        r9 = 0;
        goto L_0x001d;
    L_0x0030:
        r10 = "before";
        r9 = r9.equals(r10);
        if (r9 == 0) goto L_0x001c;
    L_0x0038:
        r9 = 1;
        goto L_0x001d;
    L_0x003a:
        r10 = "count";
        r9 = r9.equals(r10);
        if (r9 == 0) goto L_0x001c;
    L_0x0042:
        r9 = 2;
        goto L_0x001d;
    L_0x0044:
        r10 = "first";
        r9 = r9.equals(r10);
        if (r9 == 0) goto L_0x001c;
    L_0x004c:
        r9 = 3;
        goto L_0x001d;
    L_0x004e:
        r10 = "index";
        r9 = r9.equals(r10);
        if (r9 == 0) goto L_0x001c;
    L_0x0056:
        r9 = 4;
        goto L_0x001d;
    L_0x0058:
        r10 = "last";
        r9 = r9.equals(r10);
        if (r9 == 0) goto L_0x001c;
    L_0x0060:
        r9 = 5;
        goto L_0x001d;
    L_0x0062:
        r10 = "max";
        r9 = r9.equals(r10);
        if (r9 == 0) goto L_0x001c;
    L_0x006a:
        r9 = 6;
        goto L_0x001d;
    L_0x006c:
        r2 = r12.nextText();
        goto L_0x0009;
    L_0x0071:
        r3 = org.jivesoftware.smack.util.ParserUtils.getIntegerFromNextText(r12);
        goto L_0x0009;
    L_0x0076:
        r7 = "index";
        r8 = org.jivesoftware.smack.util.ParserUtils.getIntegerAttribute(r12, r7, r0);
        r7 = r12.nextText();
        goto L_0x0009;
    L_0x0081:
        r4 = org.jivesoftware.smack.util.ParserUtils.getIntegerFromNextText(r12);
        goto L_0x0009;
    L_0x0086:
        r5 = r12.nextText();
        goto L_0x0009;
    L_0x008c:
        r6 = org.jivesoftware.smack.util.ParserUtils.getIntegerFromNextText(r12);
        goto L_0x0009;
    L_0x0092:
        r9 = r12.getDepth();
        if (r9 != r13) goto L_0x0009;
    L_0x0098:
        r0 = new org.jivesoftware.smackx.rsm.packet.RSMSet;
        r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8);
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smackx.rsm.provider.RSMSetProvider.parse(org.xmlpull.v1.XmlPullParser, int):org.jivesoftware.smackx.rsm.packet.RSMSet");
    }
}
