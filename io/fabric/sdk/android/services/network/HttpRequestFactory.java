package io.fabric.sdk.android.services.network;

import java.util.Map;

public interface HttpRequestFactory {
    HttpRequest m13162a(HttpMethod httpMethod, String str, Map<String, String> map);

    void m13163a(PinningInfoProvider pinningInfoProvider);
}
