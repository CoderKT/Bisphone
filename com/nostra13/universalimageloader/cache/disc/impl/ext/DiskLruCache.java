package com.nostra13.universalimageloader.cache.disc.impl.ext;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

final class DiskLruCache implements Closeable {
    static final Pattern f6816a;
    private static final OutputStream f6817r;
    final ThreadPoolExecutor f6818b;
    private final File f6819c;
    private final File f6820d;
    private final File f6821e;
    private final File f6822f;
    private final int f6823g;
    private long f6824h;
    private int f6825i;
    private final int f6826j;
    private long f6827k;
    private int f6828l;
    private Writer f6829m;
    private final LinkedHashMap<String, Entry> f6830n;
    private int f6831o;
    private long f6832p;
    private final Callable<Void> f6833q;

    /* renamed from: com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache.1 */
    class C09121 implements Callable<Void> {
        final /* synthetic */ DiskLruCache f6797a;

        C09121(DiskLruCache diskLruCache) {
            this.f6797a = diskLruCache;
        }

        public /* synthetic */ Object call() {
            return m10907a();
        }

        public Void m10907a() {
            synchronized (this.f6797a) {
                if (this.f6797a.f6829m == null) {
                } else {
                    this.f6797a.m10951h();
                    this.f6797a.m10952i();
                    if (this.f6797a.m10948f()) {
                        this.f6797a.m10944e();
                        this.f6797a.f6831o = 0;
                    }
                }
            }
            return null;
        }
    }

    /* renamed from: com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache.2 */
    final class C09132 extends OutputStream {
        C09132() {
        }

        public void write(int i) {
        }
    }

    public final class Editor {
        final /* synthetic */ DiskLruCache f6799a;
        private final Entry f6800b;
        private final boolean[] f6801c;
        private boolean f6802d;
        private boolean f6803e;

        class FaultHidingOutputStream extends FilterOutputStream {
            final /* synthetic */ Editor f6798a;

            private FaultHidingOutputStream(Editor editor, OutputStream outputStream) {
                this.f6798a = editor;
                super(outputStream);
            }

            public void write(int i) {
                try {
                    this.out.write(i);
                } catch (IOException e) {
                    this.f6798a.f6802d = true;
                }
            }

            public void write(byte[] bArr, int i, int i2) {
                try {
                    this.out.write(bArr, i, i2);
                } catch (IOException e) {
                    this.f6798a.f6802d = true;
                }
            }

            public void close() {
                try {
                    this.out.close();
                } catch (IOException e) {
                    this.f6798a.f6802d = true;
                }
            }

            public void flush() {
                try {
                    this.out.flush();
                } catch (IOException e) {
                    this.f6798a.f6802d = true;
                }
            }
        }

        private Editor(DiskLruCache diskLruCache, Entry entry) {
            this.f6799a = diskLruCache;
            this.f6800b = entry;
            this.f6801c = entry.f6807d ? null : new boolean[diskLruCache.f6826j];
        }

        public OutputStream m10911a(int i) {
            OutputStream b;
            synchronized (this.f6799a) {
                OutputStream fileOutputStream;
                if (this.f6800b.f6808e != this) {
                    throw new IllegalStateException();
                }
                if (!this.f6800b.f6807d) {
                    this.f6801c[i] = true;
                }
                r1 = this.f6800b.m10927b(i);
                try {
                    fileOutputStream = new FileOutputStream(r1);
                } catch (FileNotFoundException e) {
                    this.f6799a.f6819c.mkdirs();
                    try {
                        File b2;
                        fileOutputStream = new FileOutputStream(b2);
                    } catch (FileNotFoundException e2) {
                        b = DiskLruCache.f6817r;
                    }
                }
                b = new FaultHidingOutputStream(fileOutputStream, null);
            }
            return b;
        }

        public void m10912a() {
            if (this.f6802d) {
                this.f6799a.m10933a(this, false);
                this.f6799a.m10956c(this.f6800b.f6805b);
            } else {
                this.f6799a.m10933a(this, true);
            }
            this.f6803e = true;
        }

        public void m10913b() {
            this.f6799a.m10933a(this, false);
        }
    }

    final class Entry {
        final /* synthetic */ DiskLruCache f6804a;
        private final String f6805b;
        private final long[] f6806c;
        private boolean f6807d;
        private Editor f6808e;
        private long f6809f;

        private Entry(DiskLruCache diskLruCache, String str) {
            this.f6804a = diskLruCache;
            this.f6805b = str;
            this.f6806c = new long[diskLruCache.f6826j];
        }

        public String m10926a() {
            StringBuilder stringBuilder = new StringBuilder();
            for (long append : this.f6806c) {
                stringBuilder.append(' ').append(append);
            }
            return stringBuilder.toString();
        }

        private void m10918a(String[] strArr) {
            if (strArr.length != this.f6804a.f6826j) {
                throw m10920b(strArr);
            }
            int i = 0;
            while (i < strArr.length) {
                try {
                    this.f6806c[i] = Long.parseLong(strArr[i]);
                    i++;
                } catch (NumberFormatException e) {
                    throw m10920b(strArr);
                }
            }
        }

        private IOException m10920b(String[] strArr) {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        public File m10925a(int i) {
            return new File(this.f6804a.f6819c, this.f6805b + "" + i);
        }

        public File m10927b(int i) {
            return new File(this.f6804a.f6819c, this.f6805b + "" + i + ".tmp");
        }
    }

    public final class Snapshot implements Closeable {
        final /* synthetic */ DiskLruCache f6810a;
        private final String f6811b;
        private final long f6812c;
        private File[] f6813d;
        private final InputStream[] f6814e;
        private final long[] f6815f;

        private Snapshot(DiskLruCache diskLruCache, String str, long j, File[] fileArr, InputStream[] inputStreamArr, long[] jArr) {
            this.f6810a = diskLruCache;
            this.f6811b = str;
            this.f6812c = j;
            this.f6813d = fileArr;
            this.f6814e = inputStreamArr;
            this.f6815f = jArr;
        }

        public File m10928a(int i) {
            return this.f6813d[i];
        }

        public void close() {
            for (Closeable a : this.f6814e) {
                Util.m10965a(a);
            }
        }
    }

    static {
        f6816a = Pattern.compile("[a-z0-9_-]{1,64}");
        f6817r = new C09132();
    }

    private DiskLruCache(File file, int i, int i2, long j, int i3) {
        this.f6827k = 0;
        this.f6828l = 0;
        this.f6830n = new LinkedHashMap(0, 0.75f, true);
        this.f6832p = 0;
        this.f6818b = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
        this.f6833q = new C09121(this);
        this.f6819c = file;
        this.f6823g = i;
        this.f6820d = new File(file, "journal");
        this.f6821e = new File(file, "journal.tmp");
        this.f6822f = new File(file, "journal.bkp");
        this.f6826j = i2;
        this.f6824h = j;
        this.f6825i = i3;
    }

    public static DiskLruCache m10931a(File file, int i, int i2, long j, int i3) {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        } else if (i3 <= 0) {
            throw new IllegalArgumentException("maxFileCount <= 0");
        } else if (i2 <= 0) {
            throw new IllegalArgumentException("valueCount <= 0");
        } else {
            File file2 = new File(file, "journal.bkp");
            if (file2.exists()) {
                File file3 = new File(file, "journal");
                if (file3.exists()) {
                    file2.delete();
                } else {
                    m10936a(file2, file3, false);
                }
            }
            DiskLruCache diskLruCache = new DiskLruCache(file, i, i2, j, i3);
            if (diskLruCache.f6820d.exists()) {
                try {
                    diskLruCache.m10939c();
                    diskLruCache.m10941d();
                    diskLruCache.f6829m = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(diskLruCache.f6820d, true), Util.f6847a));
                    return diskLruCache;
                } catch (IOException e) {
                    System.out.println("DiskLruCache " + file + " is corrupt: " + e.getMessage() + ", removing");
                    diskLruCache.m10954a();
                }
            }
            file.mkdirs();
            diskLruCache = new DiskLruCache(file, i, i2, j, i3);
            diskLruCache.m10944e();
            return diskLruCache;
        }
    }

    private void m10939c() {
        int i;
        Closeable strictLineReader = new StrictLineReader(new FileInputStream(this.f6820d), Util.f6847a);
        try {
            String a = strictLineReader.m10964a();
            String a2 = strictLineReader.m10964a();
            String a3 = strictLineReader.m10964a();
            String a4 = strictLineReader.m10964a();
            String a5 = strictLineReader.m10964a();
            if ("libcore.io.DiskLruCache".equals(a) && "1".equals(a2) && Integer.toString(this.f6823g).equals(a3) && Integer.toString(this.f6826j).equals(a4) && "".equals(a5)) {
                i = 0;
                while (true) {
                    m10942d(strictLineReader.m10964a());
                    i++;
                }
            } else {
                throw new IOException("unexpected journal header: [" + a + ", " + a2 + ", " + a4 + ", " + a5 + "]");
            }
        } catch (EOFException e) {
            this.f6831o = i - this.f6830n.size();
            Util.m10965a(strictLineReader);
        } catch (Throwable th) {
            Util.m10965a(strictLineReader);
        }
    }

    private void m10942d(String str) {
        int indexOf = str.indexOf(32);
        if (indexOf == -1) {
            throw new IOException("unexpected journal line: " + str);
        }
        String str2;
        int i = indexOf + 1;
        int indexOf2 = str.indexOf(32, i);
        if (indexOf2 == -1) {
            String substring = str.substring(i);
            if (indexOf == "REMOVE".length() && str.startsWith("REMOVE")) {
                this.f6830n.remove(substring);
                return;
            }
            str2 = substring;
        } else {
            str2 = str.substring(i, indexOf2);
        }
        Entry entry = (Entry) this.f6830n.get(str2);
        if (entry == null) {
            entry = new Entry(str2, null);
            this.f6830n.put(str2, entry);
        }
        if (indexOf2 != -1 && indexOf == "CLEAN".length() && str.startsWith("CLEAN")) {
            String[] split = str.substring(indexOf2 + 1).split(" ");
            entry.f6807d = true;
            entry.f6808e = null;
            entry.m10918a(split);
        } else if (indexOf2 == -1 && indexOf == "DIRTY".length() && str.startsWith("DIRTY")) {
            entry.f6808e = new Editor(entry, null);
        } else if (indexOf2 != -1 || indexOf != "READ".length() || !str.startsWith("READ")) {
            throw new IOException("unexpected journal line: " + str);
        }
    }

    private void m10941d() {
        m10935a(this.f6821e);
        Iterator it = this.f6830n.values().iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            int i;
            if (entry.f6808e == null) {
                for (i = 0; i < this.f6826j; i++) {
                    this.f6827k += entry.f6806c[i];
                    this.f6828l++;
                }
            } else {
                entry.f6808e = null;
                for (i = 0; i < this.f6826j; i++) {
                    m10935a(entry.m10925a(i));
                    m10935a(entry.m10927b(i));
                }
                it.remove();
            }
        }
    }

    private synchronized void m10944e() {
        if (this.f6829m != null) {
            this.f6829m.close();
        }
        Writer bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f6821e), Util.f6847a));
        try {
            bufferedWriter.write("libcore.io.DiskLruCache");
            bufferedWriter.write("\n");
            bufferedWriter.write("1");
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.f6823g));
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.f6826j));
            bufferedWriter.write("\n");
            bufferedWriter.write("\n");
            for (Entry entry : this.f6830n.values()) {
                if (entry.f6808e != null) {
                    bufferedWriter.write("DIRTY " + entry.f6805b + '\n');
                } else {
                    bufferedWriter.write("CLEAN " + entry.f6805b + entry.m10926a() + '\n');
                }
            }
            bufferedWriter.close();
            if (this.f6820d.exists()) {
                m10936a(this.f6820d, this.f6822f, true);
            }
            m10936a(this.f6821e, this.f6820d, false);
            this.f6822f.delete();
            this.f6829m = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f6820d, true), Util.f6847a));
        } catch (Throwable th) {
            bufferedWriter.close();
        }
    }

    private static void m10935a(File file) {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    private static void m10936a(File file, File file2, boolean z) {
        if (z) {
            m10935a(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache.Snapshot m10953a(java.lang.String r11) {
        /*
        r10 = this;
        r2 = 0;
        r1 = 0;
        monitor-enter(r10);
        r10.m10950g();	 Catch:{ all -> 0x008c }
        r10.m10946e(r11);	 Catch:{ all -> 0x008c }
        r0 = r10.f6830n;	 Catch:{ all -> 0x008c }
        r0 = r0.get(r11);	 Catch:{ all -> 0x008c }
        r0 = (com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache.Entry) r0;	 Catch:{ all -> 0x008c }
        if (r0 != 0) goto L_0x0015;
    L_0x0013:
        monitor-exit(r10);
        return r1;
    L_0x0015:
        r3 = r0.f6807d;	 Catch:{ all -> 0x008c }
        if (r3 == 0) goto L_0x0013;
    L_0x001b:
        r3 = r10.f6826j;	 Catch:{ all -> 0x008c }
        r6 = new java.io.File[r3];	 Catch:{ all -> 0x008c }
        r3 = r10.f6826j;	 Catch:{ all -> 0x008c }
        r7 = new java.io.InputStream[r3];	 Catch:{ all -> 0x008c }
        r3 = r2;
    L_0x0024:
        r4 = r10.f6826j;	 Catch:{ FileNotFoundException -> 0x0038 }
        if (r3 >= r4) goto L_0x004a;
    L_0x0028:
        r4 = r0.m10925a(r3);	 Catch:{ FileNotFoundException -> 0x0038 }
        r6[r3] = r4;	 Catch:{ FileNotFoundException -> 0x0038 }
        r5 = new java.io.FileInputStream;	 Catch:{ FileNotFoundException -> 0x0038 }
        r5.<init>(r4);	 Catch:{ FileNotFoundException -> 0x0038 }
        r7[r3] = r5;	 Catch:{ FileNotFoundException -> 0x0038 }
        r3 = r3 + 1;
        goto L_0x0024;
    L_0x0038:
        r0 = move-exception;
        r0 = r2;
    L_0x003a:
        r2 = r10.f6826j;	 Catch:{ all -> 0x008c }
        if (r0 >= r2) goto L_0x0013;
    L_0x003e:
        r2 = r7[r0];	 Catch:{ all -> 0x008c }
        if (r2 == 0) goto L_0x0013;
    L_0x0042:
        r2 = r7[r0];	 Catch:{ all -> 0x008c }
        com.nostra13.universalimageloader.cache.disc.impl.ext.Util.m10965a(r2);	 Catch:{ all -> 0x008c }
        r0 = r0 + 1;
        goto L_0x003a;
    L_0x004a:
        r1 = r10.f6831o;	 Catch:{ all -> 0x008c }
        r1 = r1 + 1;
        r10.f6831o = r1;	 Catch:{ all -> 0x008c }
        r1 = r10.f6829m;	 Catch:{ all -> 0x008c }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x008c }
        r2.<init>();	 Catch:{ all -> 0x008c }
        r3 = "READ ";
        r2 = r2.append(r3);	 Catch:{ all -> 0x008c }
        r2 = r2.append(r11);	 Catch:{ all -> 0x008c }
        r3 = 10;
        r2 = r2.append(r3);	 Catch:{ all -> 0x008c }
        r2 = r2.toString();	 Catch:{ all -> 0x008c }
        r1.append(r2);	 Catch:{ all -> 0x008c }
        r1 = r10.m10948f();	 Catch:{ all -> 0x008c }
        if (r1 == 0) goto L_0x007b;
    L_0x0074:
        r1 = r10.f6818b;	 Catch:{ all -> 0x008c }
        r2 = r10.f6833q;	 Catch:{ all -> 0x008c }
        r1.submit(r2);	 Catch:{ all -> 0x008c }
    L_0x007b:
        r1 = new com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache$Snapshot;	 Catch:{ all -> 0x008c }
        r4 = r0.f6809f;	 Catch:{ all -> 0x008c }
        r8 = r0.f6806c;	 Catch:{ all -> 0x008c }
        r9 = 0;
        r2 = r10;
        r3 = r11;
        r1.<init>(r3, r4, r6, r7, r8, r9);	 Catch:{ all -> 0x008c }
        goto L_0x0013;
    L_0x008c:
        r0 = move-exception;
        monitor-exit(r10);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache.a(java.lang.String):com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache$Snapshot");
    }

    public Editor m10955b(String str) {
        return m10930a(str, -1);
    }

    private synchronized Editor m10930a(String str, long j) {
        Editor editor;
        m10950g();
        m10946e(str);
        Entry entry = (Entry) this.f6830n.get(str);
        if (j == -1 || (entry != null && entry.f6809f == j)) {
            Entry entry2;
            if (entry == null) {
                entry = new Entry(str, null);
                this.f6830n.put(str, entry);
                entry2 = entry;
            } else if (entry.f6808e != null) {
                editor = null;
            } else {
                entry2 = entry;
            }
            editor = new Editor(entry2, null);
            entry2.f6808e = editor;
            this.f6829m.write("DIRTY " + str + '\n');
            this.f6829m.flush();
        } else {
            editor = null;
        }
        return editor;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void m10933a(com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache.Editor r11, boolean r12) {
        /*
        r10 = this;
        r0 = 0;
        monitor-enter(r10);
        r2 = r11.f6800b;	 Catch:{ all -> 0x0012 }
        r1 = r2.f6808e;	 Catch:{ all -> 0x0012 }
        if (r1 == r11) goto L_0x0015;
    L_0x000c:
        r0 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x0012 }
        r0.<init>();	 Catch:{ all -> 0x0012 }
        throw r0;	 Catch:{ all -> 0x0012 }
    L_0x0012:
        r0 = move-exception;
        monitor-exit(r10);
        throw r0;
    L_0x0015:
        if (r12 == 0) goto L_0x0058;
    L_0x0017:
        r1 = r2.f6807d;	 Catch:{ all -> 0x0012 }
        if (r1 != 0) goto L_0x0058;
    L_0x001d:
        r1 = r0;
    L_0x001e:
        r3 = r10.f6826j;	 Catch:{ all -> 0x0012 }
        if (r1 >= r3) goto L_0x0058;
    L_0x0022:
        r3 = r11.f6801c;	 Catch:{ all -> 0x0012 }
        r3 = r3[r1];	 Catch:{ all -> 0x0012 }
        if (r3 != 0) goto L_0x0046;
    L_0x002a:
        r11.m10913b();	 Catch:{ all -> 0x0012 }
        r0 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x0012 }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0012 }
        r2.<init>();	 Catch:{ all -> 0x0012 }
        r3 = "Newly created entry didn't create value for index ";
        r2 = r2.append(r3);	 Catch:{ all -> 0x0012 }
        r1 = r2.append(r1);	 Catch:{ all -> 0x0012 }
        r1 = r1.toString();	 Catch:{ all -> 0x0012 }
        r0.<init>(r1);	 Catch:{ all -> 0x0012 }
        throw r0;	 Catch:{ all -> 0x0012 }
    L_0x0046:
        r3 = r2.m10927b(r1);	 Catch:{ all -> 0x0012 }
        r3 = r3.exists();	 Catch:{ all -> 0x0012 }
        if (r3 != 0) goto L_0x0055;
    L_0x0050:
        r11.m10913b();	 Catch:{ all -> 0x0012 }
    L_0x0053:
        monitor-exit(r10);
        return;
    L_0x0055:
        r1 = r1 + 1;
        goto L_0x001e;
    L_0x0058:
        r1 = r10.f6826j;	 Catch:{ all -> 0x0012 }
        if (r0 >= r1) goto L_0x0093;
    L_0x005c:
        r1 = r2.m10927b(r0);	 Catch:{ all -> 0x0012 }
        if (r12 == 0) goto L_0x008f;
    L_0x0062:
        r3 = r1.exists();	 Catch:{ all -> 0x0012 }
        if (r3 == 0) goto L_0x008c;
    L_0x0068:
        r3 = r2.m10925a(r0);	 Catch:{ all -> 0x0012 }
        r1.renameTo(r3);	 Catch:{ all -> 0x0012 }
        r1 = r2.f6806c;	 Catch:{ all -> 0x0012 }
        r4 = r1[r0];	 Catch:{ all -> 0x0012 }
        r6 = r3.length();	 Catch:{ all -> 0x0012 }
        r1 = r2.f6806c;	 Catch:{ all -> 0x0012 }
        r1[r0] = r6;	 Catch:{ all -> 0x0012 }
        r8 = r10.f6827k;	 Catch:{ all -> 0x0012 }
        r4 = r8 - r4;
        r4 = r4 + r6;
        r10.f6827k = r4;	 Catch:{ all -> 0x0012 }
        r1 = r10.f6828l;	 Catch:{ all -> 0x0012 }
        r1 = r1 + 1;
        r10.f6828l = r1;	 Catch:{ all -> 0x0012 }
    L_0x008c:
        r0 = r0 + 1;
        goto L_0x0058;
    L_0x008f:
        m10935a(r1);	 Catch:{ all -> 0x0012 }
        goto L_0x008c;
    L_0x0093:
        r0 = r10.f6831o;	 Catch:{ all -> 0x0012 }
        r0 = r0 + 1;
        r10.f6831o = r0;	 Catch:{ all -> 0x0012 }
        r0 = 0;
        r2.f6808e = r0;	 Catch:{ all -> 0x0012 }
        r0 = r2.f6807d;	 Catch:{ all -> 0x0012 }
        r0 = r0 | r12;
        if (r0 == 0) goto L_0x0100;
    L_0x00a4:
        r0 = 1;
        r2.f6807d = r0;	 Catch:{ all -> 0x0012 }
        r0 = r10.f6829m;	 Catch:{ all -> 0x0012 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0012 }
        r1.<init>();	 Catch:{ all -> 0x0012 }
        r3 = "CLEAN ";
        r1 = r1.append(r3);	 Catch:{ all -> 0x0012 }
        r3 = r2.f6805b;	 Catch:{ all -> 0x0012 }
        r1 = r1.append(r3);	 Catch:{ all -> 0x0012 }
        r3 = r2.m10926a();	 Catch:{ all -> 0x0012 }
        r1 = r1.append(r3);	 Catch:{ all -> 0x0012 }
        r3 = 10;
        r1 = r1.append(r3);	 Catch:{ all -> 0x0012 }
        r1 = r1.toString();	 Catch:{ all -> 0x0012 }
        r0.write(r1);	 Catch:{ all -> 0x0012 }
        if (r12 == 0) goto L_0x00de;
    L_0x00d4:
        r0 = r10.f6832p;	 Catch:{ all -> 0x0012 }
        r4 = 1;
        r4 = r4 + r0;
        r10.f6832p = r4;	 Catch:{ all -> 0x0012 }
        r2.f6809f = r0;	 Catch:{ all -> 0x0012 }
    L_0x00de:
        r0 = r10.f6829m;	 Catch:{ all -> 0x0012 }
        r0.flush();	 Catch:{ all -> 0x0012 }
        r0 = r10.f6827k;	 Catch:{ all -> 0x0012 }
        r2 = r10.f6824h;	 Catch:{ all -> 0x0012 }
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 > 0) goto L_0x00f7;
    L_0x00eb:
        r0 = r10.f6828l;	 Catch:{ all -> 0x0012 }
        r1 = r10.f6825i;	 Catch:{ all -> 0x0012 }
        if (r0 > r1) goto L_0x00f7;
    L_0x00f1:
        r0 = r10.m10948f();	 Catch:{ all -> 0x0012 }
        if (r0 == 0) goto L_0x0053;
    L_0x00f7:
        r0 = r10.f6818b;	 Catch:{ all -> 0x0012 }
        r1 = r10.f6833q;	 Catch:{ all -> 0x0012 }
        r0.submit(r1);	 Catch:{ all -> 0x0012 }
        goto L_0x0053;
    L_0x0100:
        r0 = r10.f6830n;	 Catch:{ all -> 0x0012 }
        r1 = r2.f6805b;	 Catch:{ all -> 0x0012 }
        r0.remove(r1);	 Catch:{ all -> 0x0012 }
        r0 = r10.f6829m;	 Catch:{ all -> 0x0012 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0012 }
        r1.<init>();	 Catch:{ all -> 0x0012 }
        r3 = "REMOVE ";
        r1 = r1.append(r3);	 Catch:{ all -> 0x0012 }
        r2 = r2.f6805b;	 Catch:{ all -> 0x0012 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0012 }
        r2 = 10;
        r1 = r1.append(r2);	 Catch:{ all -> 0x0012 }
        r1 = r1.toString();	 Catch:{ all -> 0x0012 }
        r0.write(r1);	 Catch:{ all -> 0x0012 }
        goto L_0x00de;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache.a(com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache$Editor, boolean):void");
    }

    private boolean m10948f() {
        return this.f6831o >= 2000 && this.f6831o >= this.f6830n.size();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean m10956c(java.lang.String r7) {
        /*
        r6 = this;
        r1 = 0;
        monitor-enter(r6);
        r6.m10950g();	 Catch:{ all -> 0x0063 }
        r6.m10946e(r7);	 Catch:{ all -> 0x0063 }
        r0 = r6.f6830n;	 Catch:{ all -> 0x0063 }
        r0 = r0.get(r7);	 Catch:{ all -> 0x0063 }
        r0 = (com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache.Entry) r0;	 Catch:{ all -> 0x0063 }
        if (r0 == 0) goto L_0x0018;
    L_0x0012:
        r2 = r0.f6808e;	 Catch:{ all -> 0x0063 }
        if (r2 == 0) goto L_0x0036;
    L_0x0018:
        r0 = r1;
    L_0x0019:
        monitor-exit(r6);
        return r0;
    L_0x001b:
        r2 = r6.f6827k;	 Catch:{ all -> 0x0063 }
        r4 = r0.f6806c;	 Catch:{ all -> 0x0063 }
        r4 = r4[r1];	 Catch:{ all -> 0x0063 }
        r2 = r2 - r4;
        r6.f6827k = r2;	 Catch:{ all -> 0x0063 }
        r2 = r6.f6828l;	 Catch:{ all -> 0x0063 }
        r2 = r2 + -1;
        r6.f6828l = r2;	 Catch:{ all -> 0x0063 }
        r2 = r0.f6806c;	 Catch:{ all -> 0x0063 }
        r4 = 0;
        r2[r1] = r4;	 Catch:{ all -> 0x0063 }
        r1 = r1 + 1;
    L_0x0036:
        r2 = r6.f6826j;	 Catch:{ all -> 0x0063 }
        if (r1 >= r2) goto L_0x0066;
    L_0x003a:
        r2 = r0.m10925a(r1);	 Catch:{ all -> 0x0063 }
        r3 = r2.exists();	 Catch:{ all -> 0x0063 }
        if (r3 == 0) goto L_0x001b;
    L_0x0044:
        r3 = r2.delete();	 Catch:{ all -> 0x0063 }
        if (r3 != 0) goto L_0x001b;
    L_0x004a:
        r0 = new java.io.IOException;	 Catch:{ all -> 0x0063 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0063 }
        r1.<init>();	 Catch:{ all -> 0x0063 }
        r3 = "failed to delete ";
        r1 = r1.append(r3);	 Catch:{ all -> 0x0063 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0063 }
        r1 = r1.toString();	 Catch:{ all -> 0x0063 }
        r0.<init>(r1);	 Catch:{ all -> 0x0063 }
        throw r0;	 Catch:{ all -> 0x0063 }
    L_0x0063:
        r0 = move-exception;
        monitor-exit(r6);
        throw r0;
    L_0x0066:
        r0 = r6.f6831o;	 Catch:{ all -> 0x0063 }
        r0 = r0 + 1;
        r6.f6831o = r0;	 Catch:{ all -> 0x0063 }
        r0 = r6.f6829m;	 Catch:{ all -> 0x0063 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0063 }
        r1.<init>();	 Catch:{ all -> 0x0063 }
        r2 = "REMOVE ";
        r1 = r1.append(r2);	 Catch:{ all -> 0x0063 }
        r1 = r1.append(r7);	 Catch:{ all -> 0x0063 }
        r2 = 10;
        r1 = r1.append(r2);	 Catch:{ all -> 0x0063 }
        r1 = r1.toString();	 Catch:{ all -> 0x0063 }
        r0.append(r1);	 Catch:{ all -> 0x0063 }
        r0 = r6.f6830n;	 Catch:{ all -> 0x0063 }
        r0.remove(r7);	 Catch:{ all -> 0x0063 }
        r0 = r6.m10948f();	 Catch:{ all -> 0x0063 }
        if (r0 == 0) goto L_0x009c;
    L_0x0095:
        r0 = r6.f6818b;	 Catch:{ all -> 0x0063 }
        r1 = r6.f6833q;	 Catch:{ all -> 0x0063 }
        r0.submit(r1);	 Catch:{ all -> 0x0063 }
    L_0x009c:
        r0 = 1;
        goto L_0x0019;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nostra13.universalimageloader.cache.disc.impl.ext.DiskLruCache.c(java.lang.String):boolean");
    }

    private void m10950g() {
        if (this.f6829m == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public synchronized void close() {
        if (this.f6829m != null) {
            Iterator it = new ArrayList(this.f6830n.values()).iterator();
            while (it.hasNext()) {
                Entry entry = (Entry) it.next();
                if (entry.f6808e != null) {
                    entry.f6808e.m10913b();
                }
            }
            m10951h();
            m10952i();
            this.f6829m.close();
            this.f6829m = null;
        }
    }

    private void m10951h() {
        while (this.f6827k > this.f6824h) {
            m10956c((String) ((java.util.Map.Entry) this.f6830n.entrySet().iterator().next()).getKey());
        }
    }

    private void m10952i() {
        while (this.f6828l > this.f6825i) {
            m10956c((String) ((java.util.Map.Entry) this.f6830n.entrySet().iterator().next()).getKey());
        }
    }

    public void m10954a() {
        close();
        Util.m10966a(this.f6819c);
    }

    private void m10946e(String str) {
        if (!f6816a.matcher(str).matches()) {
            throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,64}: \"" + str + "\"");
        }
    }
}
