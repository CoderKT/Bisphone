package io.fabric.sdk.android.services.common;

import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.network.HttpMethod;
import io.fabric.sdk.android.services.network.HttpRequest;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import java.util.Collections;
import java.util.Map;
import java.util.regex.Pattern;

public abstract class AbstractSpiCall {
    private static final Pattern f5393b;
    protected final Kit f5394a;
    private final String f5395c;
    private final HttpRequestFactory f5396d;
    private final HttpMethod f5397e;
    private final String f5398f;

    static {
        f5393b = Pattern.compile("http(s?)://[^\\/]+", 2);
    }

    public AbstractSpiCall(Kit kit, String str, String str2, HttpRequestFactory httpRequestFactory, HttpMethod httpMethod) {
        if (str2 == null) {
            throw new IllegalArgumentException("url must not be null.");
        } else if (httpRequestFactory == null) {
            throw new IllegalArgumentException("requestFactory must not be null.");
        } else {
            this.f5394a = kit;
            this.f5398f = str;
            this.f5395c = m7989a(str2);
            this.f5396d = httpRequestFactory;
            this.f5397e = httpMethod;
        }
    }

    protected String m7991a() {
        return this.f5395c;
    }

    protected HttpRequest m7992b() {
        return m7990a(Collections.emptyMap());
    }

    protected HttpRequest m7990a(Map<String, String> map) {
        return this.f5396d.m13162a(this.f5397e, m7991a(), map).m13207a(false).m13197a(10000).m13200a("User-Agent", "Crashlytics Android SDK/" + this.f5394a.m7872d()).m13200a("X-CRASHLYTICS-DEVELOPER-TOKEN", "bca6990fc3c15a8105800c0673517a4b579634a1");
    }

    private String m7989a(String str) {
        if (CommonUtils.m13028c(this.f5398f)) {
            return str;
        }
        return f5393b.matcher(str).replaceFirst(this.f5398f);
    }
}
