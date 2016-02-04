package com.crashlytics.android;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;

class ClsFileOutputStream extends FileOutputStream {
    public static final FilenameFilter f5293a;
    private final String f5294b;
    private File f5295c;
    private File f5296d;
    private boolean f5297e;

    /* renamed from: com.crashlytics.android.ClsFileOutputStream.1 */
    final class C05901 implements FilenameFilter {
        C05901() {
        }

        public boolean accept(File file, String str) {
            return str.endsWith(".cls_temp");
        }
    }

    public ClsFileOutputStream(File file, String str) {
        super(new File(file, str + ".cls_temp"));
        this.f5297e = false;
        this.f5294b = file + File.separator + str;
        this.f5295c = new File(this.f5294b + ".cls_temp");
    }

    public synchronized void close() {
        if (!this.f5297e) {
            this.f5297e = true;
            super.flush();
            super.close();
            File file = new File(this.f5294b + ".cls");
            if (this.f5295c.renameTo(file)) {
                this.f5295c = null;
                this.f5296d = file;
            } else {
                String str = "";
                if (file.exists()) {
                    str = " (target already exists)";
                } else if (!this.f5295c.exists()) {
                    str = " (source does not exist)";
                }
                throw new IOException("Could not rename temp file: " + this.f5295c + " -> " + file + str);
            }
        }
    }

    public void m7777a() {
        if (!this.f5297e) {
            this.f5297e = true;
            super.flush();
            super.close();
        }
    }

    static {
        f5293a = new C05901();
    }
}
