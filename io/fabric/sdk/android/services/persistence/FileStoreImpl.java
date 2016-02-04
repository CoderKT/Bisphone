package io.fabric.sdk.android.services.persistence;

import android.content.Context;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import java.io.File;

public class FileStoreImpl {
    private final Context f8368a;
    private final String f8369b;
    private final String f8370c;

    public FileStoreImpl(Kit kit) {
        if (kit.m7860C() == null) {
            throw new IllegalStateException("Cannot get directory before context has been set. Call Fabric.with() first");
        }
        this.f8368a = kit.m7860C();
        this.f8369b = kit.m7862E();
        this.f8370c = "Android/" + this.f8368a.getPackageName();
    }

    public File m13245a() {
        return m13246a(this.f8368a.getFilesDir());
    }

    File m13246a(File file) {
        if (file == null) {
            Fabric.m12905g().m12867a("Fabric", "Null File");
        } else if (file.exists() || file.mkdirs()) {
            return file;
        } else {
            Fabric.m12905g().m12871c("Fabric", "Couldn't create file");
        }
        return null;
    }
}
