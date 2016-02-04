package org.jivesoftware.smackx.iqprivate;

import java.util.Hashtable;
import java.util.Map;
import java.util.WeakHashMap;
import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smackx.iqprivate.packet.DefaultPrivateData;
import org.jivesoftware.smackx.iqprivate.packet.PrivateData;
import org.jivesoftware.smackx.iqprivate.packet.PrivateDataIQ;
import org.jivesoftware.smackx.iqprivate.provider.PrivateDataProvider;
import org.jivesoftware.smackx.search.UserSearch;
import org.xmlpull.v1.XmlPullParser;

public class PrivateDataManager extends Manager {
    private static final Map<XMPPConnection, PrivateDataManager> instances;
    private static Map<String, PrivateDataProvider> privateDataProviders;

    public class PrivateDataIQProvider extends IQProvider<PrivateDataIQ> {
        public PrivateDataIQ parse(XmlPullParser xmlPullParser, int i) {
            PrivateData privateData = null;
            Object obj = null;
            while (obj == null) {
                Object obj2;
                PrivateData privateData2;
                Object obj3;
                int next = xmlPullParser.next();
                if (next == 2) {
                    String name = xmlPullParser.getName();
                    String namespace = xmlPullParser.getNamespace();
                    PrivateDataProvider privateDataProvider = PrivateDataManager.getPrivateDataProvider(name, namespace);
                    if (privateDataProvider != null) {
                        privateData = privateDataProvider.parsePrivateData(xmlPullParser);
                    } else {
                        privateData = new DefaultPrivateData(name, namespace);
                        Object obj4 = null;
                        while (obj4 == null) {
                            int next2 = xmlPullParser.next();
                            if (next2 == 2) {
                                String name2 = xmlPullParser.getName();
                                if (xmlPullParser.isEmptyElementTag()) {
                                    privateData.setValue(name2, "");
                                } else if (xmlPullParser.next() == 4) {
                                    privateData.setValue(name2, xmlPullParser.getText());
                                }
                            } else if (next2 == 3 && xmlPullParser.getName().equals(name)) {
                                obj4 = 1;
                            }
                        }
                    }
                    obj2 = obj;
                    privateData2 = privateData;
                    obj3 = obj2;
                } else if (next == 3 && xmlPullParser.getName().equals(UserSearch.ELEMENT)) {
                    privateData2 = privateData;
                    int i2 = 1;
                } else {
                    obj2 = obj;
                    privateData2 = privateData;
                    obj3 = obj2;
                }
                obj2 = obj3;
                privateData = privateData2;
                obj = obj2;
            }
            return new PrivateDataIQ(privateData);
        }
    }

    static {
        instances = new WeakHashMap();
        privateDataProviders = new Hashtable();
    }

    public static synchronized PrivateDataManager getInstanceFor(XMPPConnection xMPPConnection) {
        PrivateDataManager privateDataManager;
        synchronized (PrivateDataManager.class) {
            privateDataManager = (PrivateDataManager) instances.get(xMPPConnection);
            if (privateDataManager == null) {
                privateDataManager = new PrivateDataManager(xMPPConnection);
            }
        }
        return privateDataManager;
    }

    public static PrivateDataProvider getPrivateDataProvider(String str, String str2) {
        return (PrivateDataProvider) privateDataProviders.get(getProviderKey(str, str2));
    }

    public static void addPrivateDataProvider(String str, String str2, PrivateDataProvider privateDataProvider) {
        privateDataProviders.put(getProviderKey(str, str2), privateDataProvider);
    }

    public static void removePrivateDataProvider(String str, String str2) {
        privateDataProviders.remove(getProviderKey(str, str2));
    }

    private PrivateDataManager(XMPPConnection xMPPConnection) {
        super(xMPPConnection);
        instances.put(xMPPConnection, this);
    }

    public PrivateData getPrivateData(String str, String str2) {
        return ((PrivateDataIQ) connection().createPacketCollectorAndSend(new PrivateDataIQ(str, str2)).nextResultOrThrow()).getPrivateData();
    }

    public void setPrivateData(PrivateData privateData) {
        connection().createPacketCollectorAndSend(new PrivateDataIQ(privateData)).nextResultOrThrow();
    }

    private static String getProviderKey(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<").append(str).append("/><").append(str2).append("/>");
        return stringBuilder.toString();
    }
}
