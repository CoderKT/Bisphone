package org.jivesoftware.smackx.pubsub;

public enum ConfigureNodeFields {
    access_model,
    body_xslt,
    collection,
    dataform_xslt,
    deliver_payloads,
    itemreply,
    children_association_policy,
    children_association_whitelist,
    children,
    children_max,
    max_items,
    max_payload_size,
    node_type,
    notify_config,
    notify_delete,
    notify_retract,
    persist_items,
    presence_based_delivery,
    publish_model,
    replyroom,
    replyto,
    roster_groups_allowed,
    subscribe,
    title,
    type;

    public String getFieldName() {
        return "pubsub#" + toString();
    }
}
