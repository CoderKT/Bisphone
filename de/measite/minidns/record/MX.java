package de.measite.minidns.record;

import de.measite.minidns.util.NameUtil;
import java.io.DataInputStream;

public class MX implements Data {
    protected int f8126a;
    protected String f8127b;

    public void m12844a(DataInputStream dataInputStream, byte[] bArr, int i) {
        this.f8126a = dataInputStream.readUnsignedShort();
        this.f8127b = NameUtil.m12853a(dataInputStream, bArr);
    }

    public String toString() {
        return "MX " + this.f8127b + " p:" + this.f8126a;
    }
}
