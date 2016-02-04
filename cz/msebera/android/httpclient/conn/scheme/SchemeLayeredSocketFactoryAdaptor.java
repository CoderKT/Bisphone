package cz.msebera.android.httpclient.conn.scheme;

import cz.msebera.android.httpclient.params.HttpParams;
import java.net.Socket;

@Deprecated
class SchemeLayeredSocketFactoryAdaptor extends SchemeSocketFactoryAdaptor implements SchemeLayeredSocketFactory {
    private final LayeredSocketFactory f7375a;

    SchemeLayeredSocketFactoryAdaptor(LayeredSocketFactory layeredSocketFactory) {
        super(layeredSocketFactory);
        this.f7375a = layeredSocketFactory;
    }

    public Socket m11737a(Socket socket, String str, int i, HttpParams httpParams) {
        return this.f7375a.m10705a(socket, str, i, true);
    }
}
