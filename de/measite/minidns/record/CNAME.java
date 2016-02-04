package de.measite.minidns.record;

import de.measite.minidns.util.NameUtil;
import java.io.DataInputStream;

public class CNAME implements Data {
    protected String f8125a;

    public void m12843a(DataInputStream dataInputStream, byte[] bArr, int i) {
        this.f8125a = NameUtil.m12853a(dataInputStream, bArr);
    }

    public String toString() {
        return "to \"" + this.f8125a + "\"";
    }
}
