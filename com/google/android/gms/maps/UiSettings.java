package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.internal.IUiSettingsDelegate;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public final class UiSettings {
    private final IUiSettingsDelegate f6334a;

    UiSettings(IUiSettingsDelegate iUiSettingsDelegate) {
        this.f6334a = iUiSettingsDelegate;
    }

    public void m9671a(boolean z) {
        try {
            this.f6334a.m9956a(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void m9672b(boolean z) {
        try {
            this.f6334a.m9960c(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
