package org.jivesoftware.smack;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.compression.Java7ZlibInputOutputStream;
import org.jivesoftware.smack.initializer.SmackInitializer;
import org.jivesoftware.smack.packet.Bind;
import org.jivesoftware.smack.provider.BindIQProvider;
import org.jivesoftware.smack.provider.ProviderManager;
import org.jivesoftware.smack.sasl.core.SASLXOauth2Mechanism;
import org.jivesoftware.smack.sasl.core.SCRAMSHA1Mechanism;
import org.jivesoftware.smack.util.FileUtils;
import org.jivesoftware.smack.util.StringUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public final class SmackInitialization {
    private static final String DEFAULT_CONFIG_FILE = "classpath:org.jivesoftware.smack/smack-config.xml";
    private static final Logger LOGGER;
    static final String SMACK_VERSION;

    static {
        String readLine;
        int i = 0;
        LOGGER = Logger.getLogger(SmackInitialization.class.getName());
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(FileUtils.getStreamForUrl("classpath:org.jivesoftware.smack/version", null)));
            readLine = bufferedReader.readLine();
            try {
                bufferedReader.close();
            } catch (Throwable e) {
                LOGGER.log(Level.WARNING, "IOException closing stream", e);
            }
        } catch (Throwable e2) {
            LOGGER.log(Level.SEVERE, "Could not determine Smack version", e2);
            readLine = "unkown";
        }
        SMACK_VERSION = readLine;
        readLine = System.getProperty("smack.disabledClasses");
        if (readLine != null) {
            for (Object add : readLine.split(",")) {
                SmackConfiguration.disabledSmackClasses.add(add);
            }
        }
        try {
            FileUtils.addLines("classpath:org.jivesoftware.smack/disabledClasses", SmackConfiguration.disabledSmackClasses);
            try {
                String[] strArr = (String[]) Class.forName("org.jivesoftware.smack.CustomSmackConfiguration").getField("DISABLED_SMACK_CLASSES").get(null);
                if (strArr != null) {
                    LOGGER.warning("Using CustomSmackConfig is deprecated and will be removed in a future release");
                    int length = strArr.length;
                    while (i < length) {
                        SmackConfiguration.disabledSmackClasses.add(strArr[i]);
                        i++;
                    }
                }
            } catch (ClassNotFoundException e3) {
            } catch (NoSuchFieldException e4) {
            } catch (SecurityException e5) {
            } catch (IllegalArgumentException e6) {
            } catch (IllegalAccessException e7) {
            }
            try {
                try {
                    processConfigFile(FileUtils.getStreamForUrl(DEFAULT_CONFIG_FILE, null), null);
                    SmackConfiguration.compressionHandlers.add(new Java7ZlibInputOutputStream());
                    try {
                        if (Boolean.getBoolean("smack.debugEnabled")) {
                            SmackConfiguration.DEBUG = true;
                        }
                    } catch (Exception e8) {
                    }
                    SASLAuthentication.registerSASLMechanism(new SCRAMSHA1Mechanism());
                    SASLAuthentication.registerSASLMechanism(new SASLXOauth2Mechanism());
                    ProviderManager.addIQProvider(Bind.ELEMENT, Bind.NAMESPACE, new BindIQProvider());
                    SmackConfiguration.smackInitialized = true;
                } catch (Throwable e22) {
                    throw new IllegalStateException(e22);
                }
            } catch (Throwable e222) {
                throw new IllegalStateException(e222);
            }
        } catch (Throwable e2222) {
            throw new IllegalStateException(e2222);
        }
    }

    public static void processConfigFile(InputStream inputStream, Collection<Exception> collection) {
        processConfigFile(inputStream, collection, SmackInitialization.class.getClassLoader());
    }

    public static void processConfigFile(InputStream inputStream, Collection<Exception> collection, ClassLoader classLoader) {
        XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
        newPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
        newPullParser.setInput(inputStream, StringUtils.UTF8);
        int eventType = newPullParser.getEventType();
        do {
            if (eventType == 2) {
                if (newPullParser.getName().equals("startupClasses")) {
                    parseClassesToLoad(newPullParser, false, collection, classLoader);
                } else if (newPullParser.getName().equals("optionalStartupClasses")) {
                    parseClassesToLoad(newPullParser, true, collection, classLoader);
                }
            }
            eventType = newPullParser.next();
        } while (eventType != 1);
        try {
            inputStream.close();
        } catch (Throwable e) {
            LOGGER.log(Level.SEVERE, "Error while closing config file input stream", e);
        }
    }

    private static void parseClassesToLoad(XmlPullParser xmlPullParser, boolean z, Collection<Exception> collection, ClassLoader classLoader) {
        String name = xmlPullParser.getName();
        while (true) {
            int next = xmlPullParser.next();
            String name2 = xmlPullParser.getName();
            if (next == 2 && "className".equals(name2)) {
                String nextText = xmlPullParser.nextText();
                if (!SmackConfiguration.isDisabledSmackClass(nextText)) {
                    try {
                        loadSmackClass(nextText, z, classLoader);
                    } catch (Exception e) {
                        if (collection != null) {
                            collection.add(e);
                        } else {
                            break;
                            throw e;
                        }
                    }
                }
            }
            if (next == 3 && name.equals(name2)) {
                return;
            }
        }
        throw e;
    }

    private static void loadSmackClass(String str, boolean z, ClassLoader classLoader) {
        try {
            Class cls = Class.forName(str, true, classLoader);
            if (SmackInitializer.class.isAssignableFrom(cls)) {
                List<Exception> initialize = ((SmackInitializer) cls.newInstance()).initialize();
                if (initialize == null || initialize.size() == 0) {
                    LOGGER.log(Level.FINE, "Loaded SmackInitializer " + str);
                    return;
                }
                for (Exception log : initialize) {
                    LOGGER.log(Level.SEVERE, "Exception in loadSmackClass", log);
                }
                return;
            }
            LOGGER.log(Level.FINE, "Loaded " + str);
        } catch (ClassNotFoundException e) {
            Level level;
            if (z) {
                level = Level.FINE;
            } else {
                level = Level.WARNING;
            }
            LOGGER.log(level, "A startup class '" + str + "' could not be loaded.");
            if (!z) {
                throw e;
            }
        }
    }
}
