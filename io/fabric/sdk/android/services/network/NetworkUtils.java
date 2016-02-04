package io.fabric.sdk.android.services.network;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import org.jivesoftware.smack.util.TLSUtils;

public final class NetworkUtils {
    public static final SSLSocketFactory m13235a(PinningInfoProvider pinningInfoProvider) {
        SSLContext instance = SSLContext.getInstance(TLSUtils.TLS);
        PinningTrustManager pinningTrustManager = new PinningTrustManager(new SystemKeyStore(pinningInfoProvider.m7926a(), pinningInfoProvider.m7927b()), pinningInfoProvider);
        instance.init(null, new TrustManager[]{pinningTrustManager}, null);
        return instance.getSocketFactory();
    }
}
