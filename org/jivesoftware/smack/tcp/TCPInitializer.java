package org.jivesoftware.smack.tcp;

import org.jivesoftware.smack.initializer.UrlInitializer;

public class TCPInitializer extends UrlInitializer {
    protected String getProvidersUrl() {
        return "classpath:org.jivesoftware.smack.tcp/smacktcp.providers";
    }
}
