package org.jivesoftware.smackx.bytestreams;

public interface BytestreamRequest {
    BytestreamSession accept();

    String getFrom();

    String getSessionID();

    void reject();
}
