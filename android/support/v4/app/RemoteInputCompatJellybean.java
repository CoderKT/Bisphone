package android.support.v4.app;

import android.os.Bundle;
import android.support.v4.app.RemoteInputCompatBase.RemoteInput;

class RemoteInputCompatJellybean {
    static Bundle m594a(RemoteInput remoteInput) {
        Bundle bundle = new Bundle();
        bundle.putString("resultKey", remoteInput.m583a());
        bundle.putCharSequence("label", remoteInput.m584b());
        bundle.putCharSequenceArray("choices", remoteInput.m585c());
        bundle.putBoolean("allowFreeFormInput", remoteInput.m586d());
        bundle.putBundle("extras", remoteInput.m587e());
        return bundle;
    }

    static Bundle[] m595a(RemoteInput[] remoteInputArr) {
        if (remoteInputArr == null) {
            return null;
        }
        Bundle[] bundleArr = new Bundle[remoteInputArr.length];
        for (int i = 0; i < remoteInputArr.length; i++) {
            bundleArr[i] = m594a(remoteInputArr[i]);
        }
        return bundleArr;
    }
}
