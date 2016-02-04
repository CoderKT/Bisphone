package org.jivesoftware.smack.initializer;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.SmackInitialization;
import org.jivesoftware.smack.provider.ProviderFileLoader;
import org.jivesoftware.smack.provider.ProviderManager;
import org.jivesoftware.smack.util.FileUtils;

public abstract class UrlInitializer implements SmackInitializer {
    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getLogger(UrlInitializer.class.getName());
    }

    public List<Exception> initialize() {
        ClassLoader classLoader = getClass().getClassLoader();
        List<Exception> linkedList = new LinkedList();
        String providersUrl = getProvidersUrl();
        if (providersUrl != null) {
            try {
                InputStream streamForUrl = FileUtils.getStreamForUrl(providersUrl, classLoader);
                if (streamForUrl != null) {
                    LOGGER.log(Level.FINE, "Loading providers for providerUrl [" + providersUrl + "]");
                    Object providerFileLoader = new ProviderFileLoader(streamForUrl, classLoader);
                    ProviderManager.addLoader(providerFileLoader);
                    linkedList.addAll(providerFileLoader.getLoadingExceptions());
                } else {
                    LOGGER.log(Level.WARNING, "No input stream created for " + providersUrl);
                    linkedList.add(new IOException("No input stream created for " + providersUrl));
                }
            } catch (Throwable e) {
                LOGGER.log(Level.SEVERE, "Error trying to load provider file " + providersUrl, e);
                linkedList.add(e);
            }
        }
        String configUrl = getConfigUrl();
        if (configUrl != null) {
            try {
                SmackInitialization.processConfigFile(FileUtils.getStreamForUrl(configUrl, classLoader), linkedList, classLoader);
            } catch (Exception e2) {
                linkedList.add(e2);
            }
        }
        return linkedList;
    }

    protected String getProvidersUrl() {
        return null;
    }

    protected String getConfigUrl() {
        return null;
    }
}
