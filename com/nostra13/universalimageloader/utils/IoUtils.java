package com.nostra13.universalimageloader.utils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class IoUtils {

    public interface CopyListener {
        boolean m11142a(int i, int i2);
    }

    public static boolean m11270a(InputStream inputStream, OutputStream outputStream, CopyListener copyListener, int i) {
        int available = inputStream.available();
        if (available <= 0) {
            available = 512000;
        }
        byte[] bArr = new byte[i];
        if (m11269a(copyListener, 0, available)) {
            return false;
        }
        int i2 = 0;
        do {
            int read = inputStream.read(bArr, 0, i);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
                i2 += read;
            } else {
                outputStream.flush();
                return true;
            }
        } while (!m11269a(copyListener, i2, available));
        return false;
    }

    private static boolean m11269a(CopyListener copyListener, int i, int i2) {
        if (copyListener == null || copyListener.m11142a(i, i2) || (i * 100) / i2 >= 75) {
            return false;
        }
        return true;
    }

    public static void m11268a(InputStream inputStream) {
        while (true) {
            try {
                if (inputStream.read(new byte[32768], 0, 32768) == -1) {
                    break;
                }
            } catch (IOException e) {
            } finally {
                m11267a((Closeable) inputStream);
            }
        }
    }

    public static void m11267a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
            }
        }
    }
}
