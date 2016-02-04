package io.fabric.sdk.android.services.settings;

import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.network.HttpMethod;
import io.fabric.sdk.android.services.network.HttpRequestFactory;

public class CreateAppSpiCall extends AbstractAppSpiCall {
    public /* bridge */ /* synthetic */ boolean m13259a(AppRequestData appRequestData) {
        return super.m13256a(appRequestData);
    }

    public CreateAppSpiCall(Kit kit, String str, String str2, HttpRequestFactory httpRequestFactory) {
        super(kit, str, str2, httpRequestFactory, HttpMethod.POST);
    }
}
