package com.crashlytics.android;

import io.fabric.sdk.android.Fabric;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class SessionReport implements Report {
    private final File f5451a;
    private final Map<String, String> f5452b;

    public SessionReport(File file) {
        this(file, Collections.emptyMap());
    }

    public SessionReport(File file, Map<String, String> map) {
        this.f5451a = file;
        this.f5452b = new HashMap(map);
        if (this.f5451a.length() == 0) {
            this.f5452b.putAll(ReportUploader.f5436a);
        }
    }

    public File m8091d() {
        return this.f5451a;
    }

    public String m8089b() {
        return m8091d().getName();
    }

    public String m8090c() {
        String b = m8089b();
        return b.substring(0, b.lastIndexOf(46));
    }

    public Map<String, String> m8092e() {
        return Collections.unmodifiableMap(this.f5452b);
    }

    public boolean m8088a() {
        Fabric.m12905g().m12867a("Fabric", "Removing report at " + this.f5451a.getPath());
        return this.f5451a.delete();
    }
}
