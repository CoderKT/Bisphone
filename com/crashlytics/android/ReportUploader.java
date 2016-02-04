package com.crashlytics.android;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.services.common.ApiKey;
import io.fabric.sdk.android.services.common.BackgroundPriorityRunnable;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class ReportUploader {
    static final Map<String, String> f5436a;
    private static final FilenameFilter f5437b;
    private static final short[] f5438c;
    private final Object f5439d;
    private final CreateReportSpiCall f5440e;
    private Thread f5441f;

    /* renamed from: com.crashlytics.android.ReportUploader.1 */
    final class C06121 implements FilenameFilter {
        C06121() {
        }

        public boolean accept(File file, String str) {
            return str.endsWith(".cls") && !str.contains("Session");
        }
    }

    class Worker extends BackgroundPriorityRunnable {
        final /* synthetic */ ReportUploader f5434a;
        private final float f5435b;

        Worker(ReportUploader reportUploader, float f) {
            this.f5434a = reportUploader;
            this.f5435b = f;
        }

        public void m8051a() {
            try {
                m8050b();
            } catch (Throwable e) {
                Fabric.m12905g().m12874d("Fabric", "An unexpected error occurred while attempting to upload crash reports.", e);
            }
            this.f5434a.f5441f = null;
        }

        private void m8050b() {
            Fabric.m12905g().m12867a("Fabric", "Starting report processing in " + this.f5435b + " second(s)...");
            if (this.f5435b > 0.0f) {
                try {
                    Thread.sleep((long) (this.f5435b * 1000.0f));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            Crashlytics f = Crashlytics.m7891f();
            CrashlyticsUncaughtExceptionHandler m = f.m7907m();
            List<Report> a = this.f5434a.m8054a();
            if (!m.m7981a()) {
                if (a.isEmpty() || f.m7918x()) {
                    List list = a;
                    int i = 0;
                    while (!r0.isEmpty() && !Crashlytics.m7891f().m7907m().m7981a()) {
                        Fabric.m12905g().m12867a("Fabric", "Attempting to send " + r0.size() + " report(s)");
                        for (Report a2 : r0) {
                            this.f5434a.m8056a(a2);
                        }
                        List a3 = this.f5434a.m8054a();
                        if (a3.isEmpty()) {
                            list = a3;
                        } else {
                            int i2 = i + 1;
                            long j = (long) ReportUploader.f5438c[Math.min(i, ReportUploader.f5438c.length - 1)];
                            Fabric.m12905g().m12867a("Fabric", "Report submisson: scheduling delayed retry in " + j + " seconds");
                            try {
                                Thread.sleep(j * 1000);
                                i = i2;
                                list = a3;
                            } catch (InterruptedException e2) {
                                Thread.currentThread().interrupt();
                                return;
                            }
                        }
                    }
                    return;
                }
                Fabric.m12905g().m12867a("Fabric", "User declined to send. Removing " + a.size() + " Report(s).");
                for (Report a22 : a) {
                    a22.m8044a();
                }
            }
        }
    }

    static {
        f5437b = new C06121();
        f5436a = Collections.singletonMap("X-CRASHLYTICS-INVALID-SESSION", "1");
        f5438c = new short[]{(short) 10, (short) 20, (short) 30, (short) 60, (short) 120, (short) 300};
    }

    public ReportUploader(CreateReportSpiCall createReportSpiCall) {
        this.f5439d = new Object();
        if (createReportSpiCall == null) {
            throw new IllegalArgumentException("createReportCall must not be null.");
        }
        this.f5440e = createReportSpiCall;
    }

    public synchronized void m8055a(float f) {
        if (this.f5441f == null) {
            this.f5441f = new Thread(new Worker(this, f), "Crashlytics Report Uploader");
            this.f5441f.start();
        }
    }

    boolean m8056a(Report report) {
        boolean z = false;
        synchronized (this.f5439d) {
            try {
                boolean a = this.f5440e.m7988a(new CreateReportRequest(new ApiKey().m12991a(Crashlytics.m7891f().m7860C()), report));
                Fabric.m12905g().m12870b("Fabric", "Crashlytics report upload " + (a ? "complete: " : "FAILED: ") + report.m8045b());
                if (a) {
                    report.m8044a();
                    z = true;
                }
            } catch (Throwable e) {
                Fabric.m12905g().m12874d("Fabric", "Error occurred sending report " + report, e);
            }
        }
        return z;
    }

    List<Report> m8054a() {
        Fabric.m12905g().m12867a("Fabric", "Checking for crash reports...");
        synchronized (this.f5439d) {
            File[] listFiles = Crashlytics.m7891f().m7915u().listFiles(f5437b);
        }
        List<Report> linkedList = new LinkedList();
        for (File file : listFiles) {
            Fabric.m12905g().m12867a("Fabric", "Found crash report " + file.getPath());
            linkedList.add(new SessionReport(file));
        }
        if (linkedList.isEmpty()) {
            Fabric.m12905g().m12867a("Fabric", "No reports found.");
        }
        return linkedList;
    }
}
