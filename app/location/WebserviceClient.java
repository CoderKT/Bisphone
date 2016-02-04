package app.location;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class WebserviceClient {
    public static byte[] m5676a(String str) {
        InputStream inputStream;
        IOException e;
        Throwable th;
        byte[] bArr = null;
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            URLConnection openConnection = new URL(str).openConnection();
            openConnection.connect();
            inputStream = openConnection.getInputStream();
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    byte[] bArr2 = new byte[5120];
                    while (true) {
                        int read = inputStream.read(bArr2, 0, bArr2.length);
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr2, 0, read);
                    }
                    bArr = byteArrayOutputStream.toByteArray();
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e2) {
                        }
                    }
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (IOException e3) {
                        }
                    }
                } catch (IOException e4) {
                    e = e4;
                }
            } catch (IOException e5) {
                e = e5;
                byteArrayOutputStream = null;
                try {
                    e.printStackTrace();
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e6) {
                        }
                    }
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (IOException e7) {
                        }
                    }
                    return bArr;
                } catch (Throwable th2) {
                    th = th2;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e8) {
                        }
                    }
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (IOException e9) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                byteArrayOutputStream = null;
                th = th3;
                if (inputStream != null) {
                    inputStream.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                throw th;
            }
        } catch (IOException e10) {
            e = e10;
            byteArrayOutputStream = null;
            inputStream = null;
            e.printStackTrace();
            if (inputStream != null) {
                inputStream.close();
            }
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            return bArr;
        } catch (Throwable th32) {
            byteArrayOutputStream = null;
            inputStream = null;
            th = th32;
            if (inputStream != null) {
                inputStream.close();
            }
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            throw th;
        }
        return bArr;
    }
}
