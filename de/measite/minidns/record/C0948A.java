package de.measite.minidns.record;

import java.io.DataInputStream;

/* renamed from: de.measite.minidns.record.A */
public class C0948A implements Data {
    private byte[] f8123a;

    public void m12841a(DataInputStream dataInputStream, byte[] bArr, int i) {
        this.f8123a = new byte[4];
        dataInputStream.readFully(this.f8123a);
    }

    public String toString() {
        return Integer.toString(this.f8123a[0] & 255) + "." + Integer.toString(this.f8123a[1] & 255) + "." + Integer.toString(this.f8123a[2] & 255) + "." + Integer.toString(this.f8123a[3] & 255);
    }
}
