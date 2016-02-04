package org.jivesoftware.smack;

import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.net.ssl.HostnameVerifier;
import org.jivesoftware.smack.compression.XMPPInputOutputStream;
import org.jivesoftware.smack.debugger.ReflectionDebuggerFactory;
import org.jivesoftware.smack.debugger.SmackDebugger;
import org.jivesoftware.smack.debugger.SmackDebuggerFactory;
import org.jivesoftware.smack.parsing.ExceptionThrowingCallback;
import org.jivesoftware.smack.parsing.ParsingExceptionCallback;

public final class SmackConfiguration {
    public static boolean DEBUG;
    static final List<XMPPInputOutputStream> compressionHandlers;
    private static SmackDebuggerFactory debuggerFactory;
    private static ParsingExceptionCallback defaultCallback;
    private static HostnameVerifier defaultHostnameVerififer;
    private static List<String> defaultMechs;
    private static int defaultPacketReplyTimeout;
    static Set<String> disabledSmackClasses;
    private static int packetCollectorSize;
    static boolean smackInitialized;

    static {
        defaultPacketReplyTimeout = 5000;
        packetCollectorSize = 5000;
        defaultMechs = new ArrayList();
        disabledSmackClasses = new HashSet();
        compressionHandlers = new ArrayList(2);
        smackInitialized = false;
        debuggerFactory = new ReflectionDebuggerFactory();
        DEBUG = false;
        defaultCallback = new ExceptionThrowingCallback();
    }

    public static String getVersion() {
        return SmackInitialization.SMACK_VERSION;
    }

    public static int getDefaultPacketReplyTimeout() {
        if (defaultPacketReplyTimeout <= 0) {
            defaultPacketReplyTimeout = 5000;
        }
        return defaultPacketReplyTimeout;
    }

    public static void setDefaultPacketReplyTimeout(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException();
        }
        defaultPacketReplyTimeout = i;
    }

    public static int getPacketCollectorSize() {
        return packetCollectorSize;
    }

    public static void setPacketCollectorSize(int i) {
        packetCollectorSize = i;
    }

    public static void addSaslMech(String str) {
        if (!defaultMechs.contains(str)) {
            defaultMechs.add(str);
        }
    }

    public static void addSaslMechs(Collection<String> collection) {
        for (String addSaslMech : collection) {
            addSaslMech(addSaslMech);
        }
    }

    public static void setDebuggerFactory(SmackDebuggerFactory smackDebuggerFactory) {
        debuggerFactory = smackDebuggerFactory;
    }

    public static SmackDebuggerFactory getDebuggerFactory() {
        return debuggerFactory;
    }

    public static SmackDebugger createDebugger(XMPPConnection xMPPConnection, Writer writer, Reader reader) {
        SmackDebuggerFactory debuggerFactory = getDebuggerFactory();
        if (debuggerFactory == null) {
            return null;
        }
        return debuggerFactory.create(xMPPConnection, writer, reader);
    }

    public static void removeSaslMech(String str) {
        defaultMechs.remove(str);
    }

    public static void removeSaslMechs(Collection<String> collection) {
        defaultMechs.removeAll(collection);
    }

    public static List<String> getSaslMechs() {
        return Collections.unmodifiableList(defaultMechs);
    }

    public static void setDefaultParsingExceptionCallback(ParsingExceptionCallback parsingExceptionCallback) {
        defaultCallback = parsingExceptionCallback;
    }

    public static ParsingExceptionCallback getDefaultParsingExceptionCallback() {
        return defaultCallback;
    }

    public static void addCompressionHandler(XMPPInputOutputStream xMPPInputOutputStream) {
        compressionHandlers.add(xMPPInputOutputStream);
    }

    public static List<XMPPInputOutputStream> getCompresionHandlers() {
        List<XMPPInputOutputStream> arrayList = new ArrayList(compressionHandlers.size());
        for (XMPPInputOutputStream xMPPInputOutputStream : compressionHandlers) {
            if (xMPPInputOutputStream.isSupported()) {
                arrayList.add(xMPPInputOutputStream);
            }
        }
        return arrayList;
    }

    public static void setDefaultHostnameVerifier(HostnameVerifier hostnameVerifier) {
        defaultHostnameVerififer = hostnameVerifier;
    }

    public static void addDisabledSmackClass(Class<?> cls) {
        addDisabledSmackClass(cls.getName());
    }

    public static void addDisabledSmackClass(String str) {
        disabledSmackClasses.add(str);
    }

    public static boolean isDisabledSmackClass(String str) {
        for (String str2 : disabledSmackClasses) {
            if (str2.equals(str)) {
                return true;
            }
            int lastIndexOf = str2.lastIndexOf(46);
            if (str2.length() > lastIndexOf && !Character.isUpperCase(str2.charAt(lastIndexOf + 1)) && str.startsWith(str2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSmackInitialized() {
        return smackInitialized;
    }

    static HostnameVerifier getDefaultHostnameVerifier() {
        return defaultHostnameVerififer;
    }
}
