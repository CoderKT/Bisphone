package org.jivesoftware.smackx.pubsub;

public abstract class NodeEvent {
    private String nodeId;

    protected NodeEvent(String str) {
        this.nodeId = str;
    }

    public String getNodeId() {
        return this.nodeId;
    }
}
