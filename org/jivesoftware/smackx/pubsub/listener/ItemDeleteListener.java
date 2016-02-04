package org.jivesoftware.smackx.pubsub.listener;

import org.jivesoftware.smackx.pubsub.ItemDeleteEvent;

public interface ItemDeleteListener {
    void handleDeletedItems(ItemDeleteEvent itemDeleteEvent);

    void handlePurge();
}
