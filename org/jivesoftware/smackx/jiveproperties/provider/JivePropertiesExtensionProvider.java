package org.jivesoftware.smackx.jiveproperties.provider;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smack.util.stringencoder.Base64;
import org.jivesoftware.smackx.jiveproperties.JivePropertiesManager;
import org.jivesoftware.smackx.jiveproperties.packet.JivePropertiesExtension;
import org.xmlpull.v1.XmlPullParser;

public class JivePropertiesExtensionProvider extends ExtensionElementProvider<JivePropertiesExtension> {
    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getLogger(JivePropertiesExtensionProvider.class.getName());
    }

    public JivePropertiesExtension parse(XmlPullParser xmlPullParser, int i) {
        Map hashMap = new HashMap();
        while (true) {
            int next = xmlPullParser.next();
            if (next == 2 && xmlPullParser.getName().equals("property")) {
                Object obj = null;
                Object obj2 = null;
                Object obj3 = null;
                Object obj4 = null;
                String str = null;
                while (obj4 == null) {
                    int next2 = xmlPullParser.next();
                    if (next2 == 2) {
                        String name = xmlPullParser.getName();
                        if (name.equals("name")) {
                            obj3 = xmlPullParser.nextText();
                        } else if (name.equals("value")) {
                            obj2 = xmlPullParser.getAttributeValue("", "type");
                            str = xmlPullParser.nextText();
                        }
                    } else if (next2 == 3 && xmlPullParser.getName().equals("property")) {
                        if ("integer".equals(obj2)) {
                            obj4 = Integer.valueOf(str);
                        } else if ("long".equals(obj2)) {
                            Long valueOf = Long.valueOf(str);
                        } else if ("float".equals(obj2)) {
                            Float valueOf2 = Float.valueOf(str);
                        } else if ("double".equals(obj2)) {
                            Double valueOf3 = Double.valueOf(str);
                        } else if ("boolean".equals(obj2)) {
                            Boolean valueOf4 = Boolean.valueOf(str);
                        } else if ("string".equals(obj2)) {
                            String str2 = str;
                        } else {
                            if ("java-object".equals(obj2)) {
                                if (JivePropertiesManager.isJavaObjectEnabled()) {
                                    try {
                                        obj4 = new ObjectInputStream(new ByteArrayInputStream(Base64.decode(str))).readObject();
                                    } catch (Throwable e) {
                                        LOGGER.log(Level.SEVERE, "Error parsing java object", e);
                                        obj4 = obj;
                                    }
                                } else {
                                    LOGGER.severe("JavaObject is not enabled. Enable with JivePropertiesManager.setJavaObjectEnabled(true)");
                                }
                            }
                            obj4 = obj;
                        }
                        if (!(obj3 == null || obj4 == null)) {
                            hashMap.put(obj3, obj4);
                        }
                        Object obj5 = obj4;
                        obj4 = 1;
                        obj = obj5;
                    }
                }
            } else if (next == 3 && xmlPullParser.getName().equals(JivePropertiesExtension.ELEMENT)) {
                return new JivePropertiesExtension(hashMap);
            }
        }
    }
}
