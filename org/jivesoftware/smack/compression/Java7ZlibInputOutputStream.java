package org.jivesoftware.smack.compression;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import org.jivesoftware.smack.compression.XMPPInputOutputStream.FlushMethod;

public class Java7ZlibInputOutputStream extends XMPPInputOutputStream {
    private static final int FULL_FLUSH_INT = 3;
    private static final int SYNC_FLUSH_INT = 2;
    private static final int compressionLevel = -1;
    private static final Method method;
    private static final boolean supported;

    /* renamed from: org.jivesoftware.smack.compression.Java7ZlibInputOutputStream.1 */
    class C09981 extends InflaterInputStream {
        C09981(InputStream inputStream, Inflater inflater, int i) {
            super(inputStream, inflater, i);
        }

        public int available() {
            if (this.inf.needsInput()) {
                return 0;
            }
            return super.available();
        }
    }

    /* renamed from: org.jivesoftware.smack.compression.Java7ZlibInputOutputStream.2 */
    class C09992 extends DeflaterOutputStream {
        final /* synthetic */ int val$flushMethodInt;

        C09992(OutputStream outputStream, Deflater deflater, int i) {
            this.val$flushMethodInt = i;
            super(outputStream, deflater);
        }

        public void flush() {
            if (Java7ZlibInputOutputStream.supported) {
                while (true) {
                    try {
                        int intValue = ((Integer) Java7ZlibInputOutputStream.method.invoke(this.def, new Object[]{this.buf, Integer.valueOf(0), Integer.valueOf(this.buf.length), Integer.valueOf(this.val$flushMethodInt)})).intValue();
                        if (intValue != 0) {
                            this.out.write(this.buf, 0, intValue);
                        } else {
                            super.flush();
                            return;
                        }
                    } catch (IllegalArgumentException e) {
                        throw new IOException("Can't flush");
                    } catch (IllegalAccessException e2) {
                        throw new IOException("Can't flush");
                    } catch (InvocationTargetException e3) {
                        throw new IOException("Can't flush");
                    }
                }
            }
            super.flush();
        }
    }

    static {
        boolean z;
        Method method = null;
        try {
            method = Deflater.class.getMethod("deflate", new Class[]{byte[].class, Integer.TYPE, Integer.TYPE, Integer.TYPE});
        } catch (SecurityException e) {
        } catch (NoSuchMethodException e2) {
        }
        method = method;
        if (method != null) {
            z = true;
        } else {
            z = false;
        }
        supported = z;
    }

    public Java7ZlibInputOutputStream() {
        super("zlib");
    }

    public boolean isSupported() {
        return supported;
    }

    public InputStream getInputStream(InputStream inputStream) {
        return new C09981(inputStream, new Inflater(), 512);
    }

    public OutputStream getOutputStream(OutputStream outputStream) {
        int i;
        if (flushMethod == FlushMethod.SYNC_FLUSH) {
            i = SYNC_FLUSH_INT;
        } else {
            i = FULL_FLUSH_INT;
        }
        return new C09992(outputStream, new Deflater(compressionLevel), i);
    }
}
