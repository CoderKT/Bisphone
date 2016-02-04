package android.support.multidex;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.zip.CRC32;
import java.util.zip.ZipException;
import org.jivesoftware.smack.sm.packet.StreamManagement.AckRequest;

final class ZipUtil {

    class CentralDirectory {
        long f5a;
        long f6b;

        CentralDirectory() {
        }
    }

    static long m34a(File file) {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, AckRequest.ELEMENT);
        try {
            long a = m35a(randomAccessFile, m36a(randomAccessFile));
            return a;
        } finally {
            randomAccessFile.close();
        }
    }

    static CentralDirectory m36a(RandomAccessFile randomAccessFile) {
        long j = 0;
        long length = randomAccessFile.length() - 22;
        if (length < 0) {
            throw new ZipException("File too short to be a zip file: " + randomAccessFile.length());
        }
        long j2 = length - 65536;
        if (j2 >= 0) {
            j = j2;
        }
        int reverseBytes = Integer.reverseBytes(101010256);
        j2 = length;
        do {
            randomAccessFile.seek(j2);
            if (randomAccessFile.readInt() == reverseBytes) {
                randomAccessFile.skipBytes(2);
                randomAccessFile.skipBytes(2);
                randomAccessFile.skipBytes(2);
                randomAccessFile.skipBytes(2);
                CentralDirectory centralDirectory = new CentralDirectory();
                centralDirectory.f6b = ((long) Integer.reverseBytes(randomAccessFile.readInt())) & 4294967295L;
                centralDirectory.f5a = ((long) Integer.reverseBytes(randomAccessFile.readInt())) & 4294967295L;
                return centralDirectory;
            }
            j2--;
        } while (j2 >= j);
        throw new ZipException("End Of Central Directory signature not found");
    }

    static long m35a(RandomAccessFile randomAccessFile, CentralDirectory centralDirectory) {
        CRC32 crc32 = new CRC32();
        long j = centralDirectory.f6b;
        randomAccessFile.seek(centralDirectory.f5a);
        byte[] bArr = new byte[16384];
        int read = randomAccessFile.read(bArr, 0, (int) Math.min(16384, j));
        while (read != -1) {
            crc32.update(bArr, 0, read);
            j -= (long) read;
            if (j == 0) {
                break;
            }
            read = randomAccessFile.read(bArr, 0, (int) Math.min(16384, j));
        }
        return crc32.getValue();
    }
}
