package de.measite.minidns;

import de.measite.minidns.Record.CLASS;
import de.measite.minidns.Record.TYPE;
import de.measite.minidns.util.NameUtil;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class Question {
    private final String f8050a;
    private final TYPE f8051b;
    private final CLASS f8052c;
    private final boolean f8053d;
    private byte[] f8054e;

    public Question(String str, TYPE type, CLASS classR, boolean z) {
        this.f8050a = str;
        this.f8051b = type;
        this.f8052c = classR;
        this.f8053d = z;
    }

    public Question(String str, TYPE type, CLASS classR) {
        this(str, type, classR, false);
    }

    public TYPE m12828a() {
        return this.f8051b;
    }

    public CLASS m12829b() {
        return this.f8052c;
    }

    public String m12830c() {
        return this.f8050a;
    }

    public static Question m12827a(DataInputStream dataInputStream, byte[] bArr) {
        return new Question(NameUtil.m12853a(dataInputStream, bArr), TYPE.m12834a(dataInputStream.readUnsignedShort()), CLASS.m12832a(dataInputStream.readUnsignedShort()));
    }

    public byte[] m12831d() {
        if (this.f8054e == null) {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream(512);
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            try {
                dataOutputStream.write(NameUtil.m12855a(this.f8050a));
                dataOutputStream.writeShort(this.f8051b.m12835a());
                dataOutputStream.writeShort((this.f8053d ? 32768 : 0) | this.f8052c.m12833a());
                dataOutputStream.flush();
                this.f8054e = byteArrayOutputStream.toByteArray();
            } catch (Throwable e) {
                throw new IllegalStateException(e);
            }
        }
        return this.f8054e;
    }

    public int hashCode() {
        return Arrays.hashCode(m12831d());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Question) {
            return Arrays.equals(m12831d(), ((Question) obj).m12831d());
        }
        return false;
    }

    public String toString() {
        return "Question/" + this.f8052c + "/" + this.f8051b + ": " + this.f8050a;
    }
}
