package org.jivesoftware.smackx.pubsub;

import java.util.Collections;
import java.util.List;

public class ItemDeleteEvent extends SubscriptionEvent {
    private List<String> itemIds;

    public ItemDeleteEvent(String str, List<String> list, List<String> list2) {
        super(str, list2);
        this.itemIds = Collections.emptyList();
        if (list == null) {
            throw new IllegalArgumentException("deletedItemIds cannot be null");
        }
        this.itemIds = list;
    }

    public List<String> getItemIds() {
        return Collections.unmodifiableList(this.itemIds);
    }

    public String toString() {
        return getClass().getName() + "  [subscriptions: " + getSubscriptions() + "], [Deleted Items: " + this.itemIds + ']';
    }
}
