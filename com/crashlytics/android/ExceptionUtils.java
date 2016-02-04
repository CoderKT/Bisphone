package com.crashlytics.android;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.io.Closeable;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;

final class ExceptionUtils {
    public static void m8005a(Throwable th, OutputStream outputStream) {
        if (outputStream != null) {
            m8007b(th, outputStream);
        }
    }

    private static void m8007b(Throwable th, OutputStream outputStream) {
        Closeable printWriter;
        Throwable e;
        try {
            printWriter = new PrintWriter(outputStream);
            try {
                m8006a(th, (Writer) printWriter);
                CommonUtils.m13016a(printWriter, "Failed to close stack trace writer.");
            } catch (Exception e2) {
                e = e2;
                try {
                    Fabric.m12905g().m12874d("Fabric", "Failed to create PrintWriter", e);
                    CommonUtils.m13016a(printWriter, "Failed to close stack trace writer.");
                } catch (Throwable th2) {
                    e = th2;
                    CommonUtils.m13016a(printWriter, "Failed to close stack trace writer.");
                    throw e;
                }
            }
        } catch (Exception e3) {
            e = e3;
            printWriter = null;
            Fabric.m12905g().m12874d("Fabric", "Failed to create PrintWriter", e);
            CommonUtils.m13016a(printWriter, "Failed to close stack trace writer.");
        } catch (Throwable th3) {
            e = th3;
            printWriter = null;
            CommonUtils.m13016a(printWriter, "Failed to close stack trace writer.");
            throw e;
        }
    }

    private static void m8006a(Throwable th, Writer writer) {
        Object obj = 1;
        while (th != null) {
            try {
                String a = m8004a(th);
                writer.write((obj != null ? "" : "Caused by: ") + th.getClass().getName() + ": " + (a != null ? a : "") + "\n");
                for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                    writer.write("\tat " + stackTraceElement.toString() + "\n");
                }
                th = th.getCause();
                obj = null;
            } catch (Throwable e) {
                Fabric.m12905g().m12874d("Fabric", "Could not write stack trace", e);
                return;
            }
        }
    }

    private static String m8004a(Throwable th) {
        String localizedMessage = th.getLocalizedMessage();
        if (localizedMessage == null) {
            return null;
        }
        return localizedMessage.replaceAll("(\r\n|\n|\f)", " ");
    }
}
