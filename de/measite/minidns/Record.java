package de.measite.minidns;

import app.C0110R;
import de.measite.minidns.record.AAAA;
import de.measite.minidns.record.C0948A;
import de.measite.minidns.record.CNAME;
import de.measite.minidns.record.Data;
import de.measite.minidns.record.MX;
import de.measite.minidns.record.NS;
import de.measite.minidns.record.PTR;
import de.measite.minidns.record.SRV;
import de.measite.minidns.record.TXT;
import de.measite.minidns.util.NameUtil;
import java.io.DataInputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import se.emilsjolander.stickylistheaders.C1128R;

public class Record {
    private static final Logger f8116g;
    protected String f8117a;
    protected TYPE f8118b;
    protected CLASS f8119c;
    protected long f8120d;
    protected Data f8121e;
    protected boolean f8122f;

    /* renamed from: de.measite.minidns.Record.1 */
    /* synthetic */ class C09471 {
        static final /* synthetic */ int[] f8055a;

        static {
            f8055a = new int[TYPE.values().length];
            try {
                f8055a[TYPE.SRV.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f8055a[TYPE.MX.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f8055a[TYPE.AAAA.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f8055a[TYPE.A.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f8055a[TYPE.NS.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f8055a[TYPE.CNAME.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f8055a[TYPE.PTR.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f8055a[TYPE.TXT.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    public enum CLASS {
        IN(1),
        CH(3),
        HS(4),
        NONE(254),
        ANY(255);
        
        private static final HashMap<Integer, CLASS> f8061f;
        private final int f8063g;

        static {
            f8061f = new HashMap();
            CLASS[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                CLASS classR = values[i];
                f8061f.put(Integer.valueOf(classR.m12833a()), classR);
                i++;
            }
        }

        private CLASS(int i) {
            this.f8063g = i;
        }

        public int m12833a() {
            return this.f8063g;
        }

        public static CLASS m12832a(int i) {
            return (CLASS) f8061f.get(Integer.valueOf(i));
        }
    }

    public enum TYPE {
        A(1),
        NS(2),
        MD(3),
        MF(4),
        CNAME(5),
        SOA(6),
        MB(7),
        MG(8),
        MR(9),
        NULL(10),
        WKS(11),
        PTR(12),
        HINFO(13),
        MINFO(14),
        MX(15),
        TXT(16),
        RP(17),
        AFSDB(18),
        X25(19),
        ISDN(20),
        RT(21),
        NSAP(22),
        NSAP_PTR(23),
        SIG(24),
        KEY(25),
        PX(26),
        GPOS(27),
        AAAA(28),
        LOC(29),
        NXT(30),
        EID(31),
        NIMLOC(32),
        SRV(33),
        ATMA(34),
        NAPTR(35),
        KX(36),
        CERT(37),
        A6(38),
        DNAME(39),
        SINK(40),
        OPT(41),
        APL(42),
        DS(43),
        SSHFP(44),
        IPSECKEY(45),
        RRSIG(46),
        NSEC(47),
        DNSKEY(48),
        DHCID(49),
        NSEC3(50),
        NSEC3PARAM(51),
        HIP(55),
        NINFO(56),
        RKEY(57),
        TALINK(58),
        SPF(99),
        UINFO(100),
        UID(C0110R.styleable.Theme_buttonStyleSmall),
        GID(C0110R.styleable.Theme_checkboxStyle),
        TKEY(249),
        TSIG(250),
        IXFR(251),
        AXFR(252),
        MAILB(253),
        MAILA(254),
        ANY(255),
        TA(32768),
        DLV(32769);
        
        private static final HashMap<Integer, TYPE> ar;
        private final int aq;

        static {
            ar = new HashMap();
            TYPE[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                TYPE type = values[i];
                ar.put(Integer.valueOf(type.m12835a()), type);
                i++;
            }
        }

        private TYPE(int i) {
            this.aq = i;
        }

        public int m12835a() {
            return this.aq;
        }

        public static TYPE m12834a(int i) {
            return (TYPE) ar.get(Integer.valueOf(i));
        }
    }

    static {
        f8116g = Logger.getLogger(Client.class.getName());
    }

    public void m12837a(DataInputStream dataInputStream, byte[] bArr) {
        int i = 0;
        this.f8117a = NameUtil.m12853a(dataInputStream, bArr);
        this.f8118b = TYPE.m12834a(dataInputStream.readUnsignedShort());
        int readUnsignedShort = dataInputStream.readUnsignedShort();
        this.f8119c = CLASS.m12832a(readUnsignedShort & 32767);
        this.f8122f = (32768 & readUnsignedShort) > 0;
        if (this.f8119c == null) {
            f8116g.log(Level.FINE, "Unknown class " + readUnsignedShort);
        }
        this.f8120d = (((long) dataInputStream.readUnsignedShort()) << 32) + ((long) dataInputStream.readUnsignedShort());
        int readUnsignedShort2 = dataInputStream.readUnsignedShort();
        switch (C09471.f8055a[this.f8118b.ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                this.f8121e = new SRV();
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                this.f8121e = new MX();
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                this.f8121e = new AAAA();
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                this.f8121e = new C0948A();
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                this.f8121e = new NS();
                break;
            case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                this.f8121e = new CNAME();
                break;
            case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                this.f8121e = new PTR();
                break;
            case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                this.f8121e = new TXT();
                break;
            default:
                f8116g.log(Level.FINE, "Unparsed type " + this.f8118b);
                this.f8121e = null;
                while (i < readUnsignedShort2) {
                    dataInputStream.readByte();
                    i++;
                }
                break;
        }
        if (this.f8121e != null) {
            this.f8121e.m12840a(dataInputStream, bArr, readUnsignedShort2);
        }
    }

    public String toString() {
        if (this.f8121e == null) {
            return "RR " + this.f8118b + "/" + this.f8119c;
        }
        return "RR " + this.f8118b + "/" + this.f8119c + ": " + this.f8121e.toString();
    }

    public boolean m12838a(Question question) {
        return (question.m12828a() == this.f8118b || question.m12828a() == TYPE.ANY) && ((question.m12829b() == this.f8119c || question.m12829b() == CLASS.ANY) && question.m12830c().equals(this.f8117a));
    }

    public Data m12836a() {
        return this.f8121e;
    }

    public long m12839b() {
        return this.f8120d;
    }
}
