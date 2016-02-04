package org.jivesoftware.smack.provider;

import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class ProviderFileLoader implements ProviderLoader {
    private static final Logger LOGGER;
    private List<Exception> exceptions;
    private final Collection<ExtensionProviderInfo> extProviders;
    private final Collection<IQProviderInfo> iqProviders;
    private final Collection<StreamFeatureProviderInfo> sfProviders;

    static {
        LOGGER = Logger.getLogger(ProviderFileLoader.class.getName());
    }

    public ProviderFileLoader(InputStream inputStream) {
        this(inputStream, ProviderFileLoader.class.getClassLoader());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ProviderFileLoader(java.io.InputStream r12, java.lang.ClassLoader r13) {
        /*
        r11 = this;
        r2 = 2;
        r1 = 1;
        r11.<init>();
        r0 = new java.util.LinkedList;
        r0.<init>();
        r11.iqProviders = r0;
        r0 = new java.util.LinkedList;
        r0.<init>();
        r11.extProviders = r0;
        r0 = new java.util.LinkedList;
        r0.<init>();
        r11.sfProviders = r0;
        r0 = new java.util.LinkedList;
        r0.<init>();
        r11.exceptions = r0;
        r0 = org.xmlpull.v1.XmlPullParserFactory.newInstance();	 Catch:{ Exception -> 0x0103 }
        r3 = r0.newPullParser();	 Catch:{ Exception -> 0x0103 }
        r0 = "http://xmlpull.org/v1/doc/features.html#process-namespaces";
        r4 = 1;
        r3.setFeature(r0, r4);	 Catch:{ Exception -> 0x0103 }
        r0 = "UTF-8";
        r3.setInput(r12, r0);	 Catch:{ Exception -> 0x0103 }
        r0 = r3.getEventType();	 Catch:{ Exception -> 0x0103 }
    L_0x0038:
        if (r0 != r2) goto L_0x008b;
    L_0x003a:
        r4 = r3.getName();	 Catch:{ Exception -> 0x0103 }
        r0 = "smackProviders";
        r0 = r0.equals(r4);	 Catch:{ IllegalArgumentException -> 0x00dc }
        if (r0 != 0) goto L_0x008b;
    L_0x0046:
        r3.next();	 Catch:{ IllegalArgumentException -> 0x00dc }
        r3.next();	 Catch:{ IllegalArgumentException -> 0x00dc }
        r5 = r3.nextText();	 Catch:{ IllegalArgumentException -> 0x00dc }
        r3.next();	 Catch:{ IllegalArgumentException -> 0x00dc }
        r3.next();	 Catch:{ IllegalArgumentException -> 0x00dc }
        r6 = r3.nextText();	 Catch:{ IllegalArgumentException -> 0x00dc }
        r3.next();	 Catch:{ IllegalArgumentException -> 0x00dc }
        r3.next();	 Catch:{ IllegalArgumentException -> 0x00dc }
        r7 = r3.nextText();	 Catch:{ IllegalArgumentException -> 0x00dc }
        r8 = r13.loadClass(r7);	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        r0 = -1;
        r9 = r4.hashCode();	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        switch(r9) {
            case -797518000: goto L_0x009f;
            case 80611175: goto L_0x00a9;
            case 1834143545: goto L_0x0095;
            default: goto L_0x0070;
        };	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
    L_0x0070:
        switch(r0) {
            case 0: goto L_0x00b3;
            case 1: goto L_0x0160;
            case 2: goto L_0x0199;
            default: goto L_0x0073;
        };	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
    L_0x0073:
        r0 = LOGGER;	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        r5 = new java.lang.StringBuilder;	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        r5.<init>();	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        r6 = "Unknown provider type: ";
        r5 = r5.append(r6);	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        r5 = r5.append(r4);	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        r5 = r5.toString();	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        r0.warning(r5);	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
    L_0x008b:
        r0 = r3.next();	 Catch:{ Exception -> 0x0103 }
        if (r0 != r1) goto L_0x0038;
    L_0x0091:
        r12.close();	 Catch:{ Exception -> 0x01ab }
    L_0x0094:
        return;
    L_0x0095:
        r9 = "iqProvider";
        r9 = r4.equals(r9);	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        if (r9 == 0) goto L_0x0070;
    L_0x009d:
        r0 = 0;
        goto L_0x0070;
    L_0x009f:
        r9 = "extensionProvider";
        r9 = r4.equals(r9);	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        if (r9 == 0) goto L_0x0070;
    L_0x00a7:
        r0 = r1;
        goto L_0x0070;
    L_0x00a9:
        r9 = "streamFeatureProvider";
        r9 = r4.equals(r9);	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        if (r9 == 0) goto L_0x0070;
    L_0x00b1:
        r0 = r2;
        goto L_0x0070;
    L_0x00b3:
        r0 = org.jivesoftware.smack.provider.IQProvider.class;
        r0 = r0.isAssignableFrom(r8);	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        if (r0 == 0) goto L_0x011a;
    L_0x00bb:
        r9 = r11.iqProviders;	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        r10 = new org.jivesoftware.smack.provider.IQProviderInfo;	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        r0 = r8.newInstance();	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        r0 = (org.jivesoftware.smack.provider.IQProvider) r0;	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        r10.<init>(r5, r6, r0);	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        r9.add(r10);	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        goto L_0x008b;
    L_0x00cc:
        r0 = move-exception;
        r5 = LOGGER;	 Catch:{ IllegalArgumentException -> 0x00dc }
        r6 = java.util.logging.Level.SEVERE;	 Catch:{ IllegalArgumentException -> 0x00dc }
        r7 = "Could not find provider class";
        r5.log(r6, r7, r0);	 Catch:{ IllegalArgumentException -> 0x00dc }
        r5 = r11.exceptions;	 Catch:{ IllegalArgumentException -> 0x00dc }
        r5.add(r0);	 Catch:{ IllegalArgumentException -> 0x00dc }
        goto L_0x008b;
    L_0x00dc:
        r0 = move-exception;
        r5 = LOGGER;	 Catch:{ Exception -> 0x0103 }
        r6 = java.util.logging.Level.SEVERE;	 Catch:{ Exception -> 0x0103 }
        r7 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0103 }
        r7.<init>();	 Catch:{ Exception -> 0x0103 }
        r8 = "Invalid provider type found [";
        r7 = r7.append(r8);	 Catch:{ Exception -> 0x0103 }
        r4 = r7.append(r4);	 Catch:{ Exception -> 0x0103 }
        r7 = "] when expecting iqProvider or extensionProvider";
        r4 = r4.append(r7);	 Catch:{ Exception -> 0x0103 }
        r4 = r4.toString();	 Catch:{ Exception -> 0x0103 }
        r5.log(r6, r4, r0);	 Catch:{ Exception -> 0x0103 }
        r4 = r11.exceptions;	 Catch:{ Exception -> 0x0103 }
        r4.add(r0);	 Catch:{ Exception -> 0x0103 }
        goto L_0x008b;
    L_0x0103:
        r0 = move-exception;
        r1 = LOGGER;	 Catch:{ all -> 0x015b }
        r2 = java.util.logging.Level.SEVERE;	 Catch:{ all -> 0x015b }
        r3 = "Unknown error occurred while parsing provider file";
        r1.log(r2, r3, r0);	 Catch:{ all -> 0x015b }
        r1 = r11.exceptions;	 Catch:{ all -> 0x015b }
        r1.add(r0);	 Catch:{ all -> 0x015b }
        r12.close();	 Catch:{ Exception -> 0x0117 }
        goto L_0x0094;
    L_0x0117:
        r0 = move-exception;
        goto L_0x0094;
    L_0x011a:
        r0 = r11.exceptions;	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        r5 = new java.lang.IllegalArgumentException;	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        r6 = new java.lang.StringBuilder;	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        r6.<init>();	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        r6 = r6.append(r7);	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        r8 = " is not a IQProvider";
        r6 = r6.append(r8);	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        r6 = r6.toString();	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        r5.<init>(r6);	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        r0.add(r5);	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        goto L_0x008b;
    L_0x0139:
        r0 = move-exception;
        r5 = LOGGER;	 Catch:{ IllegalArgumentException -> 0x00dc }
        r6 = java.util.logging.Level.SEVERE;	 Catch:{ IllegalArgumentException -> 0x00dc }
        r8 = new java.lang.StringBuilder;	 Catch:{ IllegalArgumentException -> 0x00dc }
        r8.<init>();	 Catch:{ IllegalArgumentException -> 0x00dc }
        r9 = "Could not instanciate ";
        r8 = r8.append(r9);	 Catch:{ IllegalArgumentException -> 0x00dc }
        r7 = r8.append(r7);	 Catch:{ IllegalArgumentException -> 0x00dc }
        r7 = r7.toString();	 Catch:{ IllegalArgumentException -> 0x00dc }
        r5.log(r6, r7, r0);	 Catch:{ IllegalArgumentException -> 0x00dc }
        r5 = r11.exceptions;	 Catch:{ IllegalArgumentException -> 0x00dc }
        r5.add(r0);	 Catch:{ IllegalArgumentException -> 0x00dc }
        goto L_0x008b;
    L_0x015b:
        r0 = move-exception;
        r12.close();	 Catch:{ Exception -> 0x01ae }
    L_0x015f:
        throw r0;
    L_0x0160:
        r0 = org.jivesoftware.smack.provider.ExtensionElementProvider.class;
        r0 = r0.isAssignableFrom(r8);	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        if (r0 == 0) goto L_0x017a;
    L_0x0168:
        r9 = r11.extProviders;	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        r10 = new org.jivesoftware.smack.provider.ExtensionProviderInfo;	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        r0 = r8.newInstance();	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        r0 = (org.jivesoftware.smack.provider.ExtensionElementProvider) r0;	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        r10.<init>(r5, r6, r0);	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        r9.add(r10);	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        goto L_0x008b;
    L_0x017a:
        r0 = r11.exceptions;	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        r5 = new java.lang.IllegalArgumentException;	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        r6 = new java.lang.StringBuilder;	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        r6.<init>();	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        r6 = r6.append(r7);	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        r8 = " is not a PacketExtensionProvider";
        r6 = r6.append(r8);	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        r6 = r6.toString();	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        r5.<init>(r6);	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        r0.add(r5);	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        goto L_0x008b;
    L_0x0199:
        r9 = r11.sfProviders;	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        r10 = new org.jivesoftware.smack.provider.StreamFeatureProviderInfo;	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        r0 = r8.newInstance();	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        r0 = (org.jivesoftware.smack.provider.ExtensionElementProvider) r0;	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        r10.<init>(r5, r6, r0);	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        r9.add(r10);	 Catch:{ ClassNotFoundException -> 0x00cc, InstantiationException -> 0x0139 }
        goto L_0x008b;
    L_0x01ab:
        r0 = move-exception;
        goto L_0x0094;
    L_0x01ae:
        r1 = move-exception;
        goto L_0x015f;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smack.provider.ProviderFileLoader.<init>(java.io.InputStream, java.lang.ClassLoader):void");
    }

    public Collection<IQProviderInfo> getIQProviderInfo() {
        return this.iqProviders;
    }

    public Collection<ExtensionProviderInfo> getExtensionProviderInfo() {
        return this.extProviders;
    }

    public Collection<StreamFeatureProviderInfo> getStreamFeatureProviderInfo() {
        return this.sfProviders;
    }

    public List<Exception> getLoadingExceptions() {
        return Collections.unmodifiableList(this.exceptions);
    }
}
