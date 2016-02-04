package app.util;

import app.Main;
import app.storage.Storage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Unzipper {
    public static boolean m7076a(File file, String str) {
        try {
            ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(str)));
            byte[] bArr = new byte[1024];
            if (Storage.m6939a(Main.f1927b, file)) {
                while (true) {
                    ZipEntry nextEntry = zipInputStream.getNextEntry();
                    if (nextEntry == null) {
                        break;
                    }
                    String name = nextEntry.getName();
                    if (nextEntry.isDirectory()) {
                        if (!Storage.m6939a(Main.f1927b, new File(file.getAbsolutePath() + File.separator + name))) {
                            break;
                        }
                    } else {
                        FileOutputStream fileOutputStream = new FileOutputStream(file.getAbsolutePath() + File.separator + name);
                        while (true) {
                            int read = zipInputStream.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            fileOutputStream.write(bArr, 0, read);
                        }
                        fileOutputStream.close();
                        zipInputStream.closeEntry();
                    }
                }
                zipInputStream.close();
            }
            return true;
        } catch (Throwable e) {
            Main.f1926a.m5680b(e);
            return false;
        }
    }
}
