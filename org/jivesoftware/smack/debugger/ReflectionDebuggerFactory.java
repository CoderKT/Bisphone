package org.jivesoftware.smack.debugger;

import java.io.Reader;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPConnection;

public class ReflectionDebuggerFactory implements SmackDebuggerFactory {
    private static final String DEBUGGER_CLASS_PROPERTY_NAME = "smack.debuggerClass";
    private static final String[] DEFAULT_DEBUGGERS;
    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getLogger(ReflectionDebuggerFactory.class.getName());
        DEFAULT_DEBUGGERS = new String[]{"org.jivesoftware.smackx.debugger.EnhancedDebugger", "org.jivesoftware.smackx.debugger.android.AndroidDebugger", "org.jivesoftware.smack.debugger.ConsoleDebugger", "org.jivesoftware.smack.debugger.LiteDebugger", "org.jivesoftware.smack.debugger.JulDebugger"};
    }

    public static void setDebuggerClass(Class<? extends SmackDebugger> cls) {
        if (cls == null) {
            System.clearProperty(DEBUGGER_CLASS_PROPERTY_NAME);
        } else {
            System.setProperty(DEBUGGER_CLASS_PROPERTY_NAME, cls.getCanonicalName());
        }
    }

    public static Class<SmackDebugger> getDebuggerClass() {
        String customDebuggerClassName = getCustomDebuggerClassName();
        if (customDebuggerClassName == null) {
            return getOneOfDefaultDebuggerClasses();
        }
        try {
            return Class.forName(customDebuggerClassName);
        } catch (Throwable e) {
            LOGGER.log(Level.WARNING, "Unable to instantiate debugger class " + customDebuggerClassName, e);
            return null;
        }
    }

    public SmackDebugger create(XMPPConnection xMPPConnection, Writer writer, Reader reader) {
        Class debuggerClass = getDebuggerClass();
        if (debuggerClass == null) {
            return null;
        }
        try {
            return (SmackDebugger) debuggerClass.getConstructor(new Class[]{XMPPConnection.class, Writer.class, Reader.class}).newInstance(new Object[]{xMPPConnection, writer, reader});
        } catch (Throwable e) {
            throw new IllegalArgumentException("Can't initialize the configured debugger!", e);
        }
    }

    private static String getCustomDebuggerClassName() {
        try {
            return System.getProperty(DEBUGGER_CLASS_PROPERTY_NAME);
        } catch (Throwable th) {
            return null;
        }
    }

    private static Class<SmackDebugger> getOneOfDefaultDebuggerClasses() {
        for (String str : DEFAULT_DEBUGGERS) {
            if (!SmackConfiguration.isDisabledSmackClass(str)) {
                try {
                    return Class.forName(str);
                } catch (ClassNotFoundException e) {
                    LOGGER.fine("Did not find debugger class '" + str + "'");
                } catch (ClassCastException e2) {
                    LOGGER.warning("Found debugger class that does not appears to implement SmackDebugger interface");
                } catch (Exception e3) {
                    LOGGER.warning("Unable to instantiate either Smack debugger class");
                }
            }
        }
        return null;
    }
}
