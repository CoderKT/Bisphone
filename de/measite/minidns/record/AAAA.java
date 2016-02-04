package de.measite.minidns.record;

import java.io.DataInputStream;

public class AAAA implements Data {
    private byte[] f8124a;

    public void m12842a(DataInputStream dataInputStream, byte[] bArr, int i) {
        this.f8124a = new byte[16];
        dataInputStream.readFully(this.f8124a);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < this.f8124a.length; i += 2) {
            if (i != 0) {
                stringBuilder.append(':');
            }
            stringBuilder.append(Integer.toHexString(((this.f8124a[i] & 255) << 8) + (this.f8124a[i + 1] & 255)));
        }
        return stringBuilder.toString();
    }
}
