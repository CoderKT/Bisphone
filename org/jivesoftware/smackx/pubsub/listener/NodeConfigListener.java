package org.jivesoftware.smackx.pubsub.listener;

import org.jivesoftware.smackx.pubsub.ConfigurationEvent;

public interface NodeConfigListener {
    void handleNodeConfiguration(ConfigurationEvent configurationEvent);
}
