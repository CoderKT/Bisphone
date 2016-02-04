package de.measite.minidns.record;

import de.measite.minidns.util.NameUtil;
import java.io.DataInputStream;

public class SRV implements Data {
    protected int f8128a;
    protected int f8129b;
    protected int f8130c;
    protected String f8131d;

    public int m12845a() {
        return this.f8128a;
    }

    public int m12847b() {
        return this.f8129b;
    }

    public int m12848c() {
        return this.f8130c;
    }

    public String m12849d() {
        return this.f8131d;
    }

    public void m12846a(DataInputStream dataInputStream, byte[] bArr, int i) {
        this.f8128a = dataInputStream.readUnsignedShort();
        this.f8129b = dataInputStream.readUnsignedShort();
        this.f8130c = dataInputStream.readUnsignedShort();
        this.f8131d = NameUtil.m12853a(dataInputStream, bArr);
    }

    public String toString() {
        return "SRV " + this.f8131d + ":" + this.f8130c + " p:" + this.f8128a + " w:" + this.f8129b;
    }
}
