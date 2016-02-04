package de.measite.minidns.record;

import java.io.DataInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TXT implements Data {
    protected byte[] f8132a;

    public String m12850a() {
        List b = m12852b();
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        while (i < b.size() - 1) {
            stringBuilder.append(new String((byte[]) b.get(i))).append(" / ");
            i++;
        }
        stringBuilder.append(new String((byte[]) b.get(i)));
        return stringBuilder.toString();
    }

    public List<byte[]> m12852b() {
        List arrayList = new ArrayList();
        int i = 0;
        while (i < this.f8132a.length) {
            int i2 = this.f8132a[i] & 255;
            i++;
            arrayList.add(Arrays.copyOfRange(this.f8132a, i, i + i2));
            i += i2;
        }
        return arrayList;
    }

    public void m12851a(DataInputStream dataInputStream, byte[] bArr, int i) {
        this.f8132a = new byte[i];
        dataInputStream.readFully(this.f8132a);
    }

    public String toString() {
        return "\"" + m12850a() + "\"";
    }
}
