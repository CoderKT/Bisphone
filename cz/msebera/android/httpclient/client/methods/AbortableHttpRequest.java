package cz.msebera.android.httpclient.client.methods;

import cz.msebera.android.httpclient.conn.ClientConnectionRequest;
import cz.msebera.android.httpclient.conn.ConnectionReleaseTrigger;

@Deprecated
public interface AbortableHttpRequest {
    void m10638a(ClientConnectionRequest clientConnectionRequest);

    void m10639a(ConnectionReleaseTrigger connectionReleaseTrigger);
}
