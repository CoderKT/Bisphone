package android.support.v4.app;

import android.app.RemoteInput;
import android.app.RemoteInput.Builder;

class RemoteInputCompatApi20 {
    static RemoteInput[] m593a(RemoteInputCompatBase.RemoteInput[] remoteInputArr) {
        if (remoteInputArr == null) {
            return null;
        }
        RemoteInput[] remoteInputArr2 = new RemoteInput[remoteInputArr.length];
        for (int i = 0; i < remoteInputArr.length; i++) {
            RemoteInputCompatBase.RemoteInput remoteInput = remoteInputArr[i];
            remoteInputArr2[i] = new Builder(remoteInput.m583a()).setLabel(remoteInput.m584b()).setChoices(remoteInput.m585c()).setAllowFreeFormInput(remoteInput.m586d()).addExtras(remoteInput.m587e()).build();
        }
        return remoteInputArr2;
    }
}
