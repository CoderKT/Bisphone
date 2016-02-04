package org.jivesoftware.smackx.jiveproperties.packet;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.logging.Logger;
import org.jivesoftware.smack.packet.ExtensionElement;

public class JivePropertiesExtension implements ExtensionElement {
    public static final String ELEMENT = "properties";
    private static final Logger LOGGER;
    public static final String NAMESPACE = "http://www.jivesoftware.com/xmlns/xmpp/properties";
    private final Map<String, Object> properties;

    static {
        LOGGER = Logger.getLogger(JivePropertiesExtension.class.getName());
    }

    public JivePropertiesExtension() {
        this.properties = new HashMap();
    }

    public JivePropertiesExtension(Map<String, Object> map) {
        this.properties = map;
    }

    public synchronized Object getProperty(String str) {
        Object obj;
        if (this.properties == null) {
            obj = null;
        } else {
            obj = this.properties.get(str);
        }
        return obj;
    }

    public synchronized void setProperty(String str, Object obj) {
        if (obj instanceof Serializable) {
            this.properties.put(str, obj);
        } else {
            throw new IllegalArgumentException("Value must be serialiazble");
        }
    }

    public synchronized void deleteProperty(String str) {
        if (this.properties != null) {
            this.properties.remove(str);
        }
    }

    public synchronized Collection<String> getPropertyNames() {
        Collection<String> emptySet;
        if (this.properties == null) {
            emptySet = Collections.emptySet();
        } else {
            emptySet = Collections.unmodifiableSet(new HashSet(this.properties.keySet()));
        }
        return emptySet;
    }

    public synchronized Map<String, Object> getProperties() {
        Map<String, Object> emptyMap;
        if (this.properties == null) {
            emptyMap = Collections.emptyMap();
        } else {
            emptyMap = Collections.unmodifiableMap(new HashMap(this.properties));
        }
        return emptyMap;
    }

    public String getElementName() {
        return ELEMENT;
    }

    public String getNamespace() {
        return NAMESPACE;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.CharSequence toXML() {
        /*
        r10 = this;
        r3 = 0;
        r5 = new org.jivesoftware.smack.util.XmlStringBuilder;
        r5.<init>(r10);
        r5.rightAngleBracket();
        r0 = r10.getPropertyNames();
        r6 = r0.iterator();
    L_0x0011:
        r0 = r6.hasNext();
        if (r0 == 0) goto L_0x010e;
    L_0x0017:
        r0 = r6.next();
        r0 = (java.lang.String) r0;
        r1 = r10.getProperty(r0);
        r2 = "property";
        r5.openElement(r2);
        r2 = "name";
        r5.element(r2, r0);
        r0 = "value";
        r5.halfOpenElement(r0);
        r0 = r1 instanceof java.lang.Integer;
        if (r0 == 0) goto L_0x0058;
    L_0x0034:
        r2 = "integer";
        r0 = r1;
        r0 = (java.lang.Integer) r0;
        r0 = r0.intValue();
        r1 = java.lang.Integer.toString(r0);
        r0 = r2;
    L_0x0042:
        r2 = "type";
        r5.attribute(r2, r0);
        r5.rightAngleBracket();
        r5.escape(r1);
        r0 = "value";
        r5.closeElement(r0);
        r0 = "property";
        r5.closeElement(r0);
        goto L_0x0011;
    L_0x0058:
        r0 = r1 instanceof java.lang.Long;
        if (r0 == 0) goto L_0x0069;
    L_0x005c:
        r0 = "long";
        r1 = (java.lang.Long) r1;
        r8 = r1.longValue();
        r1 = java.lang.Long.toString(r8);
        goto L_0x0042;
    L_0x0069:
        r0 = r1 instanceof java.lang.Float;
        if (r0 == 0) goto L_0x007a;
    L_0x006d:
        r0 = "float";
        r1 = (java.lang.Float) r1;
        r1 = r1.floatValue();
        r1 = java.lang.Float.toString(r1);
        goto L_0x0042;
    L_0x007a:
        r0 = r1 instanceof java.lang.Double;
        if (r0 == 0) goto L_0x008b;
    L_0x007e:
        r0 = "double";
        r1 = (java.lang.Double) r1;
        r8 = r1.doubleValue();
        r1 = java.lang.Double.toString(r8);
        goto L_0x0042;
    L_0x008b:
        r0 = r1 instanceof java.lang.Boolean;
        if (r0 == 0) goto L_0x009c;
    L_0x008f:
        r0 = "boolean";
        r1 = (java.lang.Boolean) r1;
        r1 = r1.booleanValue();
        r1 = java.lang.Boolean.toString(r1);
        goto L_0x0042;
    L_0x009c:
        r0 = r1 instanceof java.lang.String;
        if (r0 == 0) goto L_0x00a5;
    L_0x00a0:
        r0 = "string";
        r1 = (java.lang.String) r1;
        goto L_0x0042;
    L_0x00a5:
        r4 = new java.io.ByteArrayOutputStream;	 Catch:{ Exception -> 0x00cb, all -> 0x0100 }
        r4.<init>();	 Catch:{ Exception -> 0x00cb, all -> 0x0100 }
        r2 = new java.io.ObjectOutputStream;	 Catch:{ Exception -> 0x011f, all -> 0x011a }
        r2.<init>(r4);	 Catch:{ Exception -> 0x011f, all -> 0x011a }
        r2.writeObject(r1);	 Catch:{ Exception -> 0x0123 }
        r0 = "java-object";
        r1 = r4.toByteArray();	 Catch:{ Exception -> 0x0123 }
        r1 = org.jivesoftware.smack.util.stringencoder.Base64.encodeToString(r1);	 Catch:{ Exception -> 0x0123 }
        if (r2 == 0) goto L_0x00c1;
    L_0x00be:
        r2.close();	 Catch:{ Exception -> 0x0112 }
    L_0x00c1:
        if (r4 == 0) goto L_0x0042;
    L_0x00c3:
        r4.close();	 Catch:{ Exception -> 0x00c8 }
        goto L_0x0042;
    L_0x00c8:
        r2 = move-exception;
        goto L_0x0042;
    L_0x00cb:
        r0 = move-exception;
        r1 = r0;
        r2 = r3;
        r4 = r3;
    L_0x00cf:
        r0 = LOGGER;	 Catch:{ all -> 0x011d }
        r7 = java.util.logging.Level.SEVERE;	 Catch:{ all -> 0x011d }
        r8 = "Error encoding java object";
        r0.log(r7, r8, r1);	 Catch:{ all -> 0x011d }
        r0 = "java-object";
        r7 = new java.lang.StringBuilder;	 Catch:{ all -> 0x011d }
        r7.<init>();	 Catch:{ all -> 0x011d }
        r8 = "Serializing error: ";
        r7 = r7.append(r8);	 Catch:{ all -> 0x011d }
        r1 = r1.getMessage();	 Catch:{ all -> 0x011d }
        r1 = r7.append(r1);	 Catch:{ all -> 0x011d }
        r1 = r1.toString();	 Catch:{ all -> 0x011d }
        if (r2 == 0) goto L_0x00f6;
    L_0x00f3:
        r2.close();	 Catch:{ Exception -> 0x0114 }
    L_0x00f6:
        if (r4 == 0) goto L_0x0042;
    L_0x00f8:
        r4.close();	 Catch:{ Exception -> 0x00fd }
        goto L_0x0042;
    L_0x00fd:
        r2 = move-exception;
        goto L_0x0042;
    L_0x0100:
        r0 = move-exception;
        r2 = r3;
        r4 = r3;
    L_0x0103:
        if (r2 == 0) goto L_0x0108;
    L_0x0105:
        r2.close();	 Catch:{ Exception -> 0x0116 }
    L_0x0108:
        if (r4 == 0) goto L_0x010d;
    L_0x010a:
        r4.close();	 Catch:{ Exception -> 0x0118 }
    L_0x010d:
        throw r0;
    L_0x010e:
        r5.closeElement(r10);
        return r5;
    L_0x0112:
        r2 = move-exception;
        goto L_0x00c1;
    L_0x0114:
        r2 = move-exception;
        goto L_0x00f6;
    L_0x0116:
        r1 = move-exception;
        goto L_0x0108;
    L_0x0118:
        r1 = move-exception;
        goto L_0x010d;
    L_0x011a:
        r0 = move-exception;
        r2 = r3;
        goto L_0x0103;
    L_0x011d:
        r0 = move-exception;
        goto L_0x0103;
    L_0x011f:
        r0 = move-exception;
        r1 = r0;
        r2 = r3;
        goto L_0x00cf;
    L_0x0123:
        r0 = move-exception;
        r1 = r0;
        goto L_0x00cf;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smackx.jiveproperties.packet.JivePropertiesExtension.toXML():java.lang.CharSequence");
    }
}
