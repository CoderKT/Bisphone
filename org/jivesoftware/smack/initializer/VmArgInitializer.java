package org.jivesoftware.smack.initializer;

import java.util.Collections;
import java.util.List;

public class VmArgInitializer extends UrlInitializer {
    protected String getFilePath() {
        return System.getProperty("smack.provider.file");
    }

    public List<Exception> initialize() {
        if (getFilePath() != null) {
            super.initialize();
        }
        return Collections.emptyList();
    }
}
