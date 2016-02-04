package io.fabric.sdk.android.services.events;

import android.content.Context;
import io.fabric.sdk.android.services.common.CommonUtils;

public class TimeBasedFileRollOverRunnable implements Runnable {
    private final Context f8330a;
    private final FileRollOverManager f8331b;

    public TimeBasedFileRollOverRunnable(Context context, FileRollOverManager fileRollOverManager) {
        this.f8330a = context;
        this.f8331b = fileRollOverManager;
    }

    public void run() {
        try {
            CommonUtils.m13014a(this.f8330a, "Performing time based file roll over.");
            if (!this.f8331b.m8136e()) {
                this.f8331b.m8135d();
            }
        } catch (Throwable e) {
            CommonUtils.m13015a(this.f8330a, "Failed to roll over file", e);
        }
    }
}
