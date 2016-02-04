package de.measite.minidns;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager;

public class DNSMessage {
    protected int f8029a;
    protected OPCODE f8030b;
    protected RESPONSE_CODE f8031c;
    protected boolean f8032d;
    protected boolean f8033e;
    protected boolean f8034f;
    protected boolean f8035g;
    protected boolean f8036h;
    protected boolean f8037i;
    protected boolean f8038j;
    protected Question[] f8039k;
    protected Record[] f8040l;
    protected Record[] f8041m;
    protected Record[] f8042n;
    protected long f8043o;

    public enum OPCODE {
        QUERY(0),
        INVERSE_QUERY(1),
        STATUS(2),
        NOTIFY(4),
        UPDATE(5);
        
        private static final OPCODE[] f8012f;
        private final byte f8014g;

        static {
            f8012f = new OPCODE[]{QUERY, INVERSE_QUERY, STATUS, null, NOTIFY, UPDATE, null, null, null, null, null, null, null, null, null};
        }

        private OPCODE(int i) {
            this.f8014g = (byte) i;
        }

        public byte m12814a() {
            return this.f8014g;
        }

        public static OPCODE m12813a(int i) {
            if (i >= 0 && i <= 15) {
                return f8012f[i];
            }
            throw new IllegalArgumentException();
        }
    }

    public enum RESPONSE_CODE {
        NO_ERROR(0),
        FORMAT_ERR(1),
        SERVER_FAIL(2),
        NX_DOMAIN(3),
        NO_IMP(4),
        REFUSED(5),
        YXDOMAIN(6),
        YXRRSET(7),
        NXRRSET(8),
        NOT_AUTH(9),
        NOT_ZONE(10);
        
        private static final RESPONSE_CODE[] f8026l;
        private final byte f8028m;

        static {
            f8026l = new RESPONSE_CODE[]{NO_ERROR, FORMAT_ERR, SERVER_FAIL, NX_DOMAIN, NO_IMP, REFUSED, YXDOMAIN, YXRRSET, NXRRSET, NOT_AUTH, NOT_ZONE, null, null, null, null, null};
        }

        private RESPONSE_CODE(int i) {
            this.f8028m = (byte) i;
        }

        public byte m12816a() {
            return this.f8028m;
        }

        public static RESPONSE_CODE m12815a(int i) {
            if (i >= 0 && i <= 15) {
                return f8026l[i];
            }
            throw new IllegalArgumentException();
        }
    }

    public int m12818a() {
        return this.f8029a;
    }

    public void m12819a(int i) {
        this.f8029a = InBandBytestreamManager.MAXIMUM_BLOCK_SIZE & i;
    }

    public long m12822b() {
        return this.f8043o;
    }

    public void m12820a(boolean z) {
        this.f8035g = z;
    }

    public byte[] m12823c() {
        int i;
        int i2 = 0;
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream(512);
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        if (this.f8032d) {
            i = 32768;
        } else {
            i = 0;
        }
        if (this.f8030b != null) {
            i += this.f8030b.m12814a() << 11;
        }
        if (this.f8033e) {
            i += 1024;
        }
        if (this.f8034f) {
            i += 512;
        }
        if (this.f8035g) {
            i += 256;
        }
        if (this.f8036h) {
            i += 128;
        }
        if (this.f8037i) {
            i += 32;
        }
        if (this.f8038j) {
            i += 16;
        }
        if (this.f8031c != null) {
            i += this.f8031c.m12816a();
        }
        dataOutputStream.writeShort((short) this.f8029a);
        dataOutputStream.writeShort((short) i);
        if (this.f8039k == null) {
            dataOutputStream.writeShort(0);
        } else {
            dataOutputStream.writeShort((short) this.f8039k.length);
        }
        if (this.f8040l == null) {
            dataOutputStream.writeShort(0);
        } else {
            dataOutputStream.writeShort((short) this.f8040l.length);
        }
        if (this.f8041m == null) {
            dataOutputStream.writeShort(0);
        } else {
            dataOutputStream.writeShort((short) this.f8041m.length);
        }
        if (this.f8042n == null) {
            dataOutputStream.writeShort(0);
        } else {
            dataOutputStream.writeShort((short) this.f8042n.length);
        }
        Question[] questionArr = this.f8039k;
        int length = questionArr.length;
        while (i2 < length) {
            dataOutputStream.write(questionArr[i2].m12831d());
            i2++;
        }
        dataOutputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }

    public static DNSMessage m12817a(byte[] bArr) {
        boolean z;
        boolean z2 = true;
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        DNSMessage dNSMessage = new DNSMessage();
        dNSMessage.f8029a = dataInputStream.readUnsignedShort();
        int readUnsignedShort = dataInputStream.readUnsignedShort();
        dNSMessage.f8032d = ((readUnsignedShort >> 15) & 1) == 0;
        dNSMessage.f8030b = OPCODE.m12813a((readUnsignedShort >> 11) & 15);
        if (((readUnsignedShort >> 10) & 1) == 1) {
            z = true;
        } else {
            z = false;
        }
        dNSMessage.f8033e = z;
        if (((readUnsignedShort >> 9) & 1) == 1) {
            z = true;
        } else {
            z = false;
        }
        dNSMessage.f8034f = z;
        if (((readUnsignedShort >> 8) & 1) == 1) {
            z = true;
        } else {
            z = false;
        }
        dNSMessage.f8035g = z;
        if (((readUnsignedShort >> 7) & 1) == 1) {
            z = true;
        } else {
            z = false;
        }
        dNSMessage.f8036h = z;
        if (((readUnsignedShort >> 5) & 1) == 1) {
            z = true;
        } else {
            z = false;
        }
        dNSMessage.f8037i = z;
        if (((readUnsignedShort >> 4) & 1) != 1) {
            z2 = false;
        }
        dNSMessage.f8038j = z2;
        dNSMessage.f8031c = RESPONSE_CODE.m12815a(readUnsignedShort & 15);
        dNSMessage.f8043o = System.currentTimeMillis();
        readUnsignedShort = dataInputStream.readUnsignedShort();
        int readUnsignedShort2 = dataInputStream.readUnsignedShort();
        int readUnsignedShort3 = dataInputStream.readUnsignedShort();
        int readUnsignedShort4 = dataInputStream.readUnsignedShort();
        dNSMessage.f8039k = new Question[readUnsignedShort];
        while (true) {
            int i = readUnsignedShort - 1;
            if (readUnsignedShort <= 0) {
                break;
            }
            dNSMessage.f8039k[i] = Question.m12827a(dataInputStream, bArr);
            readUnsignedShort = i;
        }
        dNSMessage.f8040l = new Record[readUnsignedShort2];
        while (true) {
            readUnsignedShort = readUnsignedShort2 - 1;
            if (readUnsignedShort2 <= 0) {
                break;
            }
            Record record = new Record();
            record.m12837a(dataInputStream, bArr);
            dNSMessage.f8040l[readUnsignedShort] = record;
            readUnsignedShort2 = readUnsignedShort;
        }
        dNSMessage.f8041m = new Record[readUnsignedShort3];
        while (true) {
            readUnsignedShort2 = readUnsignedShort3 - 1;
            if (readUnsignedShort3 <= 0) {
                break;
            }
            Record record2 = new Record();
            record2.m12837a(dataInputStream, bArr);
            dNSMessage.f8041m[readUnsignedShort2] = record2;
            readUnsignedShort3 = readUnsignedShort2;
        }
        dNSMessage.f8042n = new Record[readUnsignedShort4];
        while (true) {
            readUnsignedShort3 = readUnsignedShort4 - 1;
            if (readUnsignedShort4 <= 0) {
                return dNSMessage;
            }
            Record record3 = new Record();
            record3.m12837a(dataInputStream, bArr);
            dNSMessage.f8042n[readUnsignedShort3] = record3;
            readUnsignedShort4 = readUnsignedShort3;
        }
    }

    public void m12821a(Question... questionArr) {
        this.f8039k = questionArr;
    }

    public RESPONSE_CODE m12824d() {
        return this.f8031c;
    }

    public Record[] m12825e() {
        return this.f8040l;
    }

    public Record[] m12826f() {
        return this.f8042n;
    }

    public String toString() {
        return "-- DNSMessage " + this.f8029a + " --\n" + "Q" + Arrays.toString(this.f8039k) + "NS" + Arrays.toString(this.f8041m) + "A" + Arrays.toString(this.f8040l) + "ARR" + Arrays.toString(this.f8042n);
    }
}
