package app.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.Base64;
import app.Main;
import app.storage.Storage;
import app.storage.StorageException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.jivesoftware.smack.util.StringUtils;

public class FileUtil {
    public static boolean m7022a(File file, File file2, boolean z) {
        if (file2.exists() && !z) {
            return true;
        }
        if (!Storage.m6939a(Main.f1927b, file2.getParentFile())) {
            return false;
        }
        FileChannel channel = new FileInputStream(file).getChannel();
        FileChannel channel2 = new FileOutputStream(file2).getChannel();
        try {
            for (long j = 0; j < channel.size(); j += channel.transferTo(j, (long) 65536, channel2)) {
            }
            if (channel != null) {
                channel.close();
            }
            if (channel2 == null) {
                return true;
            }
            channel2.close();
            return true;
        } catch (Throwable th) {
            if (channel != null) {
                channel.close();
            }
            if (channel2 != null) {
                channel2.close();
            }
        }
    }

    public static boolean m7023a(String str, String str2, boolean z) {
        return m7022a(new File(str), new File(str2), z);
    }

    public static void m7019a(InputStream inputStream, OutputStream outputStream) {
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    public static boolean m7020a(AssetManager assetManager, String str, String str2) {
        try {
            InputStream open = assetManager.open(str);
            if (Storage.m6943b(Main.f1927b, new File(str2))) {
                OutputStream fileOutputStream = new FileOutputStream(str2);
                m7019a(open, fileOutputStream);
                open.close();
                fileOutputStream.flush();
                fileOutputStream.close();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static File m7016a(String str, String str2, String str3) {
        File file = new File(str3);
        Storage.m6939a(Main.f1927b, file);
        file = File.createTempFile(str, "", file);
        file.deleteOnExit();
        return file;
    }

    public static Bitmap m7015a(String str) {
        Options options = new Options();
        options.inPreferredConfig = Config.ARGB_8888;
        return BitmapFactory.decodeFile(str, options);
    }

    public static String m7017a(int i) {
        float f = (float) (i / 1024);
        float f2 = f / 1024.0f;
        if (f2 < 1.0f) {
            return ((int) f) + "KB";
        }
        String str = new String(f2 + "");
        int i2 = (int) f2;
        return i2 + "." + ((int) ((f2 - ((float) i2)) * 10.0f)) + "MB";
    }

    public static boolean m7021a(File file) {
        int i = 0;
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                int length = listFiles.length;
                while (i < length) {
                    File file2 = listFiles[i];
                    if (file2.isDirectory()) {
                        m7021a(file2);
                    } else {
                        file2.delete();
                    }
                    i++;
                }
            }
        }
        return file.delete();
    }

    public static void m7018a(Context context) {
        for (String deleteFile : context.fileList()) {
            context.deleteFile(deleteFile);
        }
    }

    public static String m7024b(File file) {
        return m7026c(file);
    }

    public static String m7025b(String str) {
        return m7026c(new File(str));
    }

    public static final String m7027c(String str) {
        String str2 = StringUtils.MD5;
        try {
            MessageDigest instance = MessageDigest.getInstance(StringUtils.MD5);
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : digest) {
                str2 = Integer.toHexString(b & 255);
                while (str2.length() < 2) {
                    str2 = "0" + str2;
                }
                stringBuilder.append(str2);
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String m7026c(File file) {
        String str = null;
        try {
            MessageDigest instance = MessageDigest.getInstance(StringUtils.MD5);
            try {
                InputStream fileInputStream = new FileInputStream(file);
                byte[] bArr = new byte[8192];
                while (true) {
                    try {
                        int read = fileInputStream.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        instance.update(bArr, 0, read);
                    } catch (Throwable e) {
                        throw new RuntimeException("Unable to process file for MD5", e);
                    } catch (Throwable th) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e2) {
                        }
                    }
                }
                str = new BigInteger(1, instance.digest()).toString(16);
                str = String.format("%32s", new Object[]{str}).replace(' ', '0');
                try {
                    fileInputStream.close();
                } catch (IOException e3) {
                }
            } catch (FileNotFoundException e4) {
            }
        } catch (NoSuchAlgorithmException e5) {
        }
        return str;
    }

    public static String m7028d(String str) {
        int i;
        Throwable e;
        byte[] decode = Base64.decode(str, 0);
        String c = m7027c(str);
        try {
            File file = new File(Storage.m6946d() + File.separator + c);
            if (!file.createNewFile()) {
                return "file://" + c;
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file, false);
            fileOutputStream.write(decode);
            fileOutputStream.flush();
            fileOutputStream.close();
            i = 1;
            if (i != 0) {
                return "file://" + c;
            }
            return null;
        } catch (IOException e2) {
            e = e2;
            Main.f1926a.m5678a(e);
            i = 0;
            if (i != 0) {
                return null;
            }
            return "file://" + c;
        } catch (StorageException e3) {
            e = e3;
            Main.f1926a.m5678a(e);
            i = 0;
            if (i != 0) {
                return "file://" + c;
            }
            return null;
        }
    }

    public static File m7029e(String str) {
        if (str.startsWith("file://")) {
            return new File(Storage.m6946d() + File.separator + str.substring("file://".length()));
        }
        throw new RuntimeException("You need first check statWith(FileUtil.PREFIX_THUMBNAIL), if you check don't substring value, send to me i fix that");
    }
}
