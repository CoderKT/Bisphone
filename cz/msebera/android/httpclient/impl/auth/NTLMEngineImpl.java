package cz.msebera.android.httpclient.impl.auth;

import cz.msebera.android.httpclient.extras.Base64;
import cz.msebera.android.httpclient.util.EncodingUtils;
import java.security.Key;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Locale;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.jivesoftware.smack.util.StringUtils;

final class NTLMEngineImpl implements NTLMEngine {
    private static final SecureRandom f7527a;
    private static final byte[] f7528c;
    private String f7529b;

    public class CipherGen {
        protected final String f7477a;
        protected final String f7478b;
        protected final String f7479c;
        protected final byte[] f7480d;
        protected final String f7481e;
        protected final byte[] f7482f;
        protected byte[] f7483g;
        protected byte[] f7484h;
        protected byte[] f7485i;
        protected byte[] f7486j;
        protected byte[] f7487k;
        protected byte[] f7488l;
        protected byte[] f7489m;
        protected byte[] f7490n;
        protected byte[] f7491o;
        protected byte[] f7492p;
        protected byte[] f7493q;
        protected byte[] f7494r;
        protected byte[] f7495s;
        protected byte[] f7496t;
        protected byte[] f7497u;
        protected byte[] f7498v;
        protected byte[] f7499w;
        protected byte[] f7500x;
        protected byte[] f7501y;
        protected byte[] f7502z;

        public CipherGen(String str, String str2, String str3, byte[] bArr, String str4, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6) {
            this.f7487k = null;
            this.f7488l = null;
            this.f7489m = null;
            this.f7490n = null;
            this.f7491o = null;
            this.f7492p = null;
            this.f7493q = null;
            this.f7494r = null;
            this.f7495s = null;
            this.f7496t = null;
            this.f7497u = null;
            this.f7498v = null;
            this.f7499w = null;
            this.f7500x = null;
            this.f7501y = null;
            this.f7502z = null;
            this.f7477a = str;
            this.f7481e = str4;
            this.f7478b = str2;
            this.f7479c = str3;
            this.f7480d = bArr;
            this.f7482f = bArr2;
            this.f7483g = bArr3;
            this.f7484h = bArr4;
            this.f7485i = bArr5;
            this.f7486j = bArr6;
        }

        public CipherGen(String str, String str2, String str3, byte[] bArr, String str4, byte[] bArr2) {
            this(str, str2, str3, bArr, str4, bArr2, null, null, null, null);
        }

        public byte[] m11918a() {
            if (this.f7483g == null) {
                this.f7483g = NTLMEngineImpl.m11988d();
            }
            return this.f7483g;
        }

        public byte[] m11919b() {
            if (this.f7484h == null) {
                this.f7484h = NTLMEngineImpl.m11988d();
            }
            return this.f7484h;
        }

        public byte[] m11920c() {
            if (this.f7485i == null) {
                this.f7485i = NTLMEngineImpl.m11994e();
            }
            return this.f7485i;
        }

        public byte[] m11921d() {
            if (this.f7487k == null) {
                this.f7487k = NTLMEngineImpl.m12000h(this.f7479c);
            }
            return this.f7487k;
        }

        public byte[] m11922e() {
            if (this.f7488l == null) {
                this.f7488l = NTLMEngineImpl.m11990d(m11921d(), this.f7480d);
            }
            return this.f7488l;
        }

        public byte[] m11923f() {
            if (this.f7489m == null) {
                this.f7489m = NTLMEngineImpl.m12001i(this.f7479c);
            }
            return this.f7489m;
        }

        public byte[] m11924g() {
            if (this.f7490n == null) {
                this.f7490n = NTLMEngineImpl.m11990d(m11923f(), this.f7480d);
            }
            return this.f7490n;
        }

        public byte[] m11925h() {
            if (this.f7492p == null) {
                this.f7492p = NTLMEngineImpl.m11982c(this.f7477a, this.f7478b, m11923f());
            }
            return this.f7492p;
        }

        public byte[] m11926i() {
            if (this.f7491o == null) {
                this.f7491o = NTLMEngineImpl.m11989d(this.f7477a, this.f7478b, m11923f());
            }
            return this.f7491o;
        }

        public byte[] m11927j() {
            if (this.f7486j == null) {
                long currentTimeMillis = 10000 * (System.currentTimeMillis() + 11644473600000L);
                this.f7486j = new byte[8];
                for (int i = 0; i < 8; i++) {
                    this.f7486j[i] = (byte) ((int) currentTimeMillis);
                    currentTimeMillis >>>= 8;
                }
            }
            return this.f7486j;
        }

        public byte[] m11928k() {
            if (this.f7494r == null) {
                this.f7494r = NTLMEngineImpl.m11995e(m11919b(), this.f7482f, m11927j());
            }
            return this.f7494r;
        }

        public byte[] m11929l() {
            if (this.f7495s == null) {
                this.f7495s = NTLMEngineImpl.m11991d(m11926i(), this.f7480d, m11928k());
            }
            return this.f7495s;
        }

        public byte[] m11930m() {
            if (this.f7493q == null) {
                this.f7493q = NTLMEngineImpl.m11991d(m11925h(), this.f7480d, m11918a());
            }
            return this.f7493q;
        }

        public byte[] m11931n() {
            if (this.f7496t == null) {
                this.f7496t = NTLMEngineImpl.m11971a(m11923f(), this.f7480d, m11918a());
            }
            return this.f7496t;
        }

        public byte[] m11932o() {
            if (this.f7497u == null) {
                Object a = m11918a();
                this.f7497u = new byte[24];
                System.arraycopy(a, 0, this.f7497u, 0, a.length);
                Arrays.fill(this.f7497u, a.length, this.f7497u.length, (byte) 0);
            }
            return this.f7497u;
        }

        public byte[] m11933p() {
            if (this.f7498v == null) {
                this.f7498v = new byte[16];
                System.arraycopy(m11921d(), 0, this.f7498v, 0, 8);
                Arrays.fill(this.f7498v, 8, 16, (byte) 0);
            }
            return this.f7498v;
        }

        public byte[] m11934q() {
            if (this.f7499w == null) {
                MD4 md4 = new MD4();
                md4.m11940a(m11923f());
                this.f7499w = md4.m11942a();
            }
            return this.f7499w;
        }

        public byte[] m11935r() {
            if (this.f7500x == null) {
                byte[] i = m11926i();
                byte[] bArr = new byte[16];
                System.arraycopy(m11929l(), 0, bArr, 0, 16);
                this.f7500x = NTLMEngineImpl.m11970a(bArr, i);
            }
            return this.f7500x;
        }

        public byte[] m11936s() {
            if (this.f7501y == null) {
                Object o = m11932o();
                byte[] bArr = new byte[(this.f7480d.length + o.length)];
                System.arraycopy(this.f7480d, 0, bArr, 0, this.f7480d.length);
                System.arraycopy(o, 0, bArr, this.f7480d.length, o.length);
                this.f7501y = NTLMEngineImpl.m11970a(bArr, m11934q());
            }
            return this.f7501y;
        }

        public byte[] m11937t() {
            if (this.f7502z == null) {
                try {
                    byte[] bArr = new byte[14];
                    System.arraycopy(m11921d(), 0, bArr, 0, 8);
                    Arrays.fill(bArr, 8, bArr.length, (byte) -67);
                    Key a = NTLMEngineImpl.m11999g(bArr, 0);
                    Key a2 = NTLMEngineImpl.m11999g(bArr, 7);
                    Object obj = new byte[8];
                    System.arraycopy(m11922e(), 0, obj, 0, obj.length);
                    Cipher instance = Cipher.getInstance("DES/ECB/NoPadding");
                    instance.init(1, a);
                    Object doFinal = instance.doFinal(obj);
                    instance = Cipher.getInstance("DES/ECB/NoPadding");
                    instance.init(1, a2);
                    Object doFinal2 = instance.doFinal(obj);
                    this.f7502z = new byte[16];
                    System.arraycopy(doFinal, 0, this.f7502z, 0, doFinal.length);
                    System.arraycopy(doFinal2, 0, this.f7502z, doFinal.length, doFinal2.length);
                } catch (Throwable e) {
                    throw new NTLMEngineException(e.getMessage(), e);
                }
            }
            return this.f7502z;
        }
    }

    class HMACMD5 {
        protected byte[] f7503a;
        protected byte[] f7504b;
        protected MessageDigest f7505c;

        HMACMD5(byte[] bArr) {
            try {
                this.f7505c = MessageDigest.getInstance(StringUtils.MD5);
                this.f7503a = new byte[64];
                this.f7504b = new byte[64];
                int length = bArr.length;
                if (length > 64) {
                    this.f7505c.update(bArr);
                    bArr = this.f7505c.digest();
                    length = bArr.length;
                }
                int i = 0;
                while (i < length) {
                    this.f7503a[i] = (byte) (bArr[i] ^ 54);
                    this.f7504b[i] = (byte) (bArr[i] ^ 92);
                    i++;
                }
                for (length = i; length < 64; length++) {
                    this.f7503a[length] = (byte) 54;
                    this.f7504b[length] = (byte) 92;
                }
                this.f7505c.reset();
                this.f7505c.update(this.f7503a);
            } catch (Throwable e) {
                throw new NTLMEngineException("Error getting md5 message digest implementation: " + e.getMessage(), e);
            }
        }

        byte[] m11939a() {
            byte[] digest = this.f7505c.digest();
            this.f7505c.update(this.f7504b);
            return this.f7505c.digest(digest);
        }

        void m11938a(byte[] bArr) {
            this.f7505c.update(bArr);
        }
    }

    class MD4 {
        protected int f7506a;
        protected int f7507b;
        protected int f7508c;
        protected int f7509d;
        protected long f7510e;
        protected byte[] f7511f;

        MD4() {
            this.f7506a = 1732584193;
            this.f7507b = -271733879;
            this.f7508c = -1732584194;
            this.f7509d = 271733878;
            this.f7510e = 0;
            this.f7511f = new byte[64];
        }

        void m11940a(byte[] bArr) {
            int i = (int) (this.f7510e & 63);
            int i2 = 0;
            while ((bArr.length - i2) + i >= this.f7511f.length) {
                int length = this.f7511f.length - i;
                System.arraycopy(bArr, i2, this.f7511f, i, length);
                this.f7510e += (long) length;
                i2 += length;
                m11943b();
                i = 0;
            }
            if (i2 < bArr.length) {
                int length2 = bArr.length - i2;
                System.arraycopy(bArr, i2, this.f7511f, i, length2);
                this.f7510e += (long) length2;
                i2 = i + length2;
            }
        }

        byte[] m11942a() {
            int i = (int) (this.f7510e & 63);
            i = i < 56 ? 56 - i : 120 - i;
            byte[] bArr = new byte[(i + 8)];
            bArr[0] = Byte.MIN_VALUE;
            for (int i2 = 0; i2 < 8; i2++) {
                bArr[i + i2] = (byte) ((int) ((this.f7510e * 8) >>> (i2 * 8)));
            }
            m11940a(bArr);
            byte[] bArr2 = new byte[16];
            NTLMEngineImpl.m11966a(bArr2, this.f7506a, 0);
            NTLMEngineImpl.m11966a(bArr2, this.f7507b, 4);
            NTLMEngineImpl.m11966a(bArr2, this.f7508c, 8);
            NTLMEngineImpl.m11966a(bArr2, this.f7509d, 12);
            return bArr2;
        }

        protected void m11943b() {
            int i;
            int[] iArr = new int[16];
            for (i = 0; i < 16; i++) {
                iArr[i] = (((this.f7511f[i * 4] & 255) + ((this.f7511f[(i * 4) + 1] & 255) << 8)) + ((this.f7511f[(i * 4) + 2] & 255) << 16)) + ((this.f7511f[(i * 4) + 3] & 255) << 24);
            }
            i = this.f7506a;
            int i2 = this.f7507b;
            int i3 = this.f7508c;
            int i4 = this.f7509d;
            m11941a(iArr);
            m11944b(iArr);
            m11945c(iArr);
            this.f7506a = i + this.f7506a;
            this.f7507b += i2;
            this.f7508c += i3;
            this.f7509d += i4;
        }

        protected void m11941a(int[] iArr) {
            this.f7506a = NTLMEngineImpl.m11962a((this.f7506a + NTLMEngineImpl.m11963a(this.f7507b, this.f7508c, this.f7509d)) + iArr[0], 3);
            this.f7509d = NTLMEngineImpl.m11962a((this.f7509d + NTLMEngineImpl.m11963a(this.f7506a, this.f7507b, this.f7508c)) + iArr[1], 7);
            this.f7508c = NTLMEngineImpl.m11962a((this.f7508c + NTLMEngineImpl.m11963a(this.f7509d, this.f7506a, this.f7507b)) + iArr[2], 11);
            this.f7507b = NTLMEngineImpl.m11962a((this.f7507b + NTLMEngineImpl.m11963a(this.f7508c, this.f7509d, this.f7506a)) + iArr[3], 19);
            this.f7506a = NTLMEngineImpl.m11962a((this.f7506a + NTLMEngineImpl.m11963a(this.f7507b, this.f7508c, this.f7509d)) + iArr[4], 3);
            this.f7509d = NTLMEngineImpl.m11962a((this.f7509d + NTLMEngineImpl.m11963a(this.f7506a, this.f7507b, this.f7508c)) + iArr[5], 7);
            this.f7508c = NTLMEngineImpl.m11962a((this.f7508c + NTLMEngineImpl.m11963a(this.f7509d, this.f7506a, this.f7507b)) + iArr[6], 11);
            this.f7507b = NTLMEngineImpl.m11962a((this.f7507b + NTLMEngineImpl.m11963a(this.f7508c, this.f7509d, this.f7506a)) + iArr[7], 19);
            this.f7506a = NTLMEngineImpl.m11962a((this.f7506a + NTLMEngineImpl.m11963a(this.f7507b, this.f7508c, this.f7509d)) + iArr[8], 3);
            this.f7509d = NTLMEngineImpl.m11962a((this.f7509d + NTLMEngineImpl.m11963a(this.f7506a, this.f7507b, this.f7508c)) + iArr[9], 7);
            this.f7508c = NTLMEngineImpl.m11962a((this.f7508c + NTLMEngineImpl.m11963a(this.f7509d, this.f7506a, this.f7507b)) + iArr[10], 11);
            this.f7507b = NTLMEngineImpl.m11962a((this.f7507b + NTLMEngineImpl.m11963a(this.f7508c, this.f7509d, this.f7506a)) + iArr[11], 19);
            this.f7506a = NTLMEngineImpl.m11962a((this.f7506a + NTLMEngineImpl.m11963a(this.f7507b, this.f7508c, this.f7509d)) + iArr[12], 3);
            this.f7509d = NTLMEngineImpl.m11962a((this.f7509d + NTLMEngineImpl.m11963a(this.f7506a, this.f7507b, this.f7508c)) + iArr[13], 7);
            this.f7508c = NTLMEngineImpl.m11962a((this.f7508c + NTLMEngineImpl.m11963a(this.f7509d, this.f7506a, this.f7507b)) + iArr[14], 11);
            this.f7507b = NTLMEngineImpl.m11962a((this.f7507b + NTLMEngineImpl.m11963a(this.f7508c, this.f7509d, this.f7506a)) + iArr[15], 19);
        }

        protected void m11944b(int[] iArr) {
            this.f7506a = NTLMEngineImpl.m11962a(((this.f7506a + NTLMEngineImpl.m11972b(this.f7507b, this.f7508c, this.f7509d)) + iArr[0]) + 1518500249, 3);
            this.f7509d = NTLMEngineImpl.m11962a(((this.f7509d + NTLMEngineImpl.m11972b(this.f7506a, this.f7507b, this.f7508c)) + iArr[4]) + 1518500249, 5);
            this.f7508c = NTLMEngineImpl.m11962a(((this.f7508c + NTLMEngineImpl.m11972b(this.f7509d, this.f7506a, this.f7507b)) + iArr[8]) + 1518500249, 9);
            this.f7507b = NTLMEngineImpl.m11962a(((this.f7507b + NTLMEngineImpl.m11972b(this.f7508c, this.f7509d, this.f7506a)) + iArr[12]) + 1518500249, 13);
            this.f7506a = NTLMEngineImpl.m11962a(((this.f7506a + NTLMEngineImpl.m11972b(this.f7507b, this.f7508c, this.f7509d)) + iArr[1]) + 1518500249, 3);
            this.f7509d = NTLMEngineImpl.m11962a(((this.f7509d + NTLMEngineImpl.m11972b(this.f7506a, this.f7507b, this.f7508c)) + iArr[5]) + 1518500249, 5);
            this.f7508c = NTLMEngineImpl.m11962a(((this.f7508c + NTLMEngineImpl.m11972b(this.f7509d, this.f7506a, this.f7507b)) + iArr[9]) + 1518500249, 9);
            this.f7507b = NTLMEngineImpl.m11962a(((this.f7507b + NTLMEngineImpl.m11972b(this.f7508c, this.f7509d, this.f7506a)) + iArr[13]) + 1518500249, 13);
            this.f7506a = NTLMEngineImpl.m11962a(((this.f7506a + NTLMEngineImpl.m11972b(this.f7507b, this.f7508c, this.f7509d)) + iArr[2]) + 1518500249, 3);
            this.f7509d = NTLMEngineImpl.m11962a(((this.f7509d + NTLMEngineImpl.m11972b(this.f7506a, this.f7507b, this.f7508c)) + iArr[6]) + 1518500249, 5);
            this.f7508c = NTLMEngineImpl.m11962a(((this.f7508c + NTLMEngineImpl.m11972b(this.f7509d, this.f7506a, this.f7507b)) + iArr[10]) + 1518500249, 9);
            this.f7507b = NTLMEngineImpl.m11962a(((this.f7507b + NTLMEngineImpl.m11972b(this.f7508c, this.f7509d, this.f7506a)) + iArr[14]) + 1518500249, 13);
            this.f7506a = NTLMEngineImpl.m11962a(((this.f7506a + NTLMEngineImpl.m11972b(this.f7507b, this.f7508c, this.f7509d)) + iArr[3]) + 1518500249, 3);
            this.f7509d = NTLMEngineImpl.m11962a(((this.f7509d + NTLMEngineImpl.m11972b(this.f7506a, this.f7507b, this.f7508c)) + iArr[7]) + 1518500249, 5);
            this.f7508c = NTLMEngineImpl.m11962a(((this.f7508c + NTLMEngineImpl.m11972b(this.f7509d, this.f7506a, this.f7507b)) + iArr[11]) + 1518500249, 9);
            this.f7507b = NTLMEngineImpl.m11962a(((this.f7507b + NTLMEngineImpl.m11972b(this.f7508c, this.f7509d, this.f7506a)) + iArr[15]) + 1518500249, 13);
        }

        protected void m11945c(int[] iArr) {
            this.f7506a = NTLMEngineImpl.m11962a(((this.f7506a + NTLMEngineImpl.m11979c(this.f7507b, this.f7508c, this.f7509d)) + iArr[0]) + 1859775393, 3);
            this.f7509d = NTLMEngineImpl.m11962a(((this.f7509d + NTLMEngineImpl.m11979c(this.f7506a, this.f7507b, this.f7508c)) + iArr[8]) + 1859775393, 9);
            this.f7508c = NTLMEngineImpl.m11962a(((this.f7508c + NTLMEngineImpl.m11979c(this.f7509d, this.f7506a, this.f7507b)) + iArr[4]) + 1859775393, 11);
            this.f7507b = NTLMEngineImpl.m11962a(((this.f7507b + NTLMEngineImpl.m11979c(this.f7508c, this.f7509d, this.f7506a)) + iArr[12]) + 1859775393, 15);
            this.f7506a = NTLMEngineImpl.m11962a(((this.f7506a + NTLMEngineImpl.m11979c(this.f7507b, this.f7508c, this.f7509d)) + iArr[2]) + 1859775393, 3);
            this.f7509d = NTLMEngineImpl.m11962a(((this.f7509d + NTLMEngineImpl.m11979c(this.f7506a, this.f7507b, this.f7508c)) + iArr[10]) + 1859775393, 9);
            this.f7508c = NTLMEngineImpl.m11962a(((this.f7508c + NTLMEngineImpl.m11979c(this.f7509d, this.f7506a, this.f7507b)) + iArr[6]) + 1859775393, 11);
            this.f7507b = NTLMEngineImpl.m11962a(((this.f7507b + NTLMEngineImpl.m11979c(this.f7508c, this.f7509d, this.f7506a)) + iArr[14]) + 1859775393, 15);
            this.f7506a = NTLMEngineImpl.m11962a(((this.f7506a + NTLMEngineImpl.m11979c(this.f7507b, this.f7508c, this.f7509d)) + iArr[1]) + 1859775393, 3);
            this.f7509d = NTLMEngineImpl.m11962a(((this.f7509d + NTLMEngineImpl.m11979c(this.f7506a, this.f7507b, this.f7508c)) + iArr[9]) + 1859775393, 9);
            this.f7508c = NTLMEngineImpl.m11962a(((this.f7508c + NTLMEngineImpl.m11979c(this.f7509d, this.f7506a, this.f7507b)) + iArr[5]) + 1859775393, 11);
            this.f7507b = NTLMEngineImpl.m11962a(((this.f7507b + NTLMEngineImpl.m11979c(this.f7508c, this.f7509d, this.f7506a)) + iArr[13]) + 1859775393, 15);
            this.f7506a = NTLMEngineImpl.m11962a(((this.f7506a + NTLMEngineImpl.m11979c(this.f7507b, this.f7508c, this.f7509d)) + iArr[3]) + 1859775393, 3);
            this.f7509d = NTLMEngineImpl.m11962a(((this.f7509d + NTLMEngineImpl.m11979c(this.f7506a, this.f7507b, this.f7508c)) + iArr[11]) + 1859775393, 9);
            this.f7508c = NTLMEngineImpl.m11962a(((this.f7508c + NTLMEngineImpl.m11979c(this.f7509d, this.f7506a, this.f7507b)) + iArr[7]) + 1859775393, 11);
            this.f7507b = NTLMEngineImpl.m11962a(((this.f7507b + NTLMEngineImpl.m11979c(this.f7508c, this.f7509d, this.f7506a)) + iArr[15]) + 1859775393, 15);
        }
    }

    class NTLMMessage {
        private byte[] f7512a;
        private int f7513b;

        NTLMMessage() {
            this.f7512a = null;
            this.f7513b = 0;
        }

        NTLMMessage(String str, int i) {
            int i2 = 0;
            this.f7512a = null;
            this.f7513b = 0;
            this.f7512a = Base64.m11830a(EncodingUtils.m12763a(str, "ASCII"), 2);
            if (this.f7512a.length < NTLMEngineImpl.f7528c.length) {
                throw new NTLMEngineException("NTLM message decoding error - packet too short");
            }
            while (i2 < NTLMEngineImpl.f7528c.length) {
                if (this.f7512a[i2] != NTLMEngineImpl.f7528c[i2]) {
                    throw new NTLMEngineException("NTLM message expected - instead got unrecognized bytes");
                }
                i2++;
            }
            i2 = m11947a(NTLMEngineImpl.f7528c.length);
            if (i2 != i) {
                throw new NTLMEngineException("NTLM type " + Integer.toString(i) + " message expected - instead got type " + Integer.toString(i2));
            }
            this.f7513b = this.f7512a.length;
        }

        protected int m11946a() {
            return this.f7513b;
        }

        protected void m11951a(byte[] bArr, int i) {
            if (this.f7512a.length < bArr.length + i) {
                throw new NTLMEngineException("NTLM: Message too short");
            }
            System.arraycopy(this.f7512a, i, bArr, 0, bArr.length);
        }

        protected int m11947a(int i) {
            return NTLMEngineImpl.m11986d(this.f7512a, i);
        }

        protected byte[] m11953b(int i) {
            return NTLMEngineImpl.m11997f(this.f7512a, i);
        }

        protected void m11949a(int i, int i2) {
            this.f7512a = new byte[i];
            this.f7513b = 0;
            m11950a(NTLMEngineImpl.f7528c);
            m11955d(i2);
        }

        protected void m11948a(byte b) {
            this.f7512a[this.f7513b] = b;
            this.f7513b++;
        }

        protected void m11950a(byte[] bArr) {
            if (bArr != null) {
                for (byte b : bArr) {
                    this.f7512a[this.f7513b] = b;
                    this.f7513b++;
                }
            }
        }

        protected void m11954c(int i) {
            m11948a((byte) (i & 255));
            m11948a((byte) ((i >> 8) & 255));
        }

        protected void m11955d(int i) {
            m11948a((byte) (i & 255));
            m11948a((byte) ((i >> 8) & 255));
            m11948a((byte) ((i >> 16) & 255));
            m11948a((byte) ((i >> 24) & 255));
        }

        String m11952b() {
            byte[] bArr;
            if (this.f7512a.length > this.f7513b) {
                bArr = new byte[this.f7513b];
                System.arraycopy(this.f7512a, 0, bArr, 0, this.f7513b);
            } else {
                bArr = this.f7512a;
            }
            return EncodingUtils.m12760a(Base64.m11832b(bArr, 2));
        }
    }

    class Type1Message extends NTLMMessage {
        protected byte[] f7514a;
        protected byte[] f7515b;

        Type1Message(String str, String str2) {
            byte[] bArr = null;
            try {
                String c = NTLMEngineImpl.m11996f(str2);
                String d = NTLMEngineImpl.m11998g(str);
                this.f7514a = c != null ? c.getBytes("ASCII") : null;
                if (d != null) {
                    bArr = d.toUpperCase(Locale.ENGLISH).getBytes("ASCII");
                }
                this.f7515b = bArr;
            } catch (Throwable e) {
                throw new NTLMEngineException("Unicode unsupported: " + e.getMessage(), e);
            }
        }

        String m11956b() {
            m11949a(40, 1);
            m11955d(-1576500735);
            m11954c(0);
            m11954c(0);
            m11955d(40);
            m11954c(0);
            m11954c(0);
            m11955d(40);
            m11954c(261);
            m11955d(2600);
            m11954c(3840);
            return super.m11952b();
        }
    }

    class Type2Message extends NTLMMessage {
        protected byte[] f7516a;
        protected String f7517b;
        protected byte[] f7518c;
        protected int f7519d;

        Type2Message(String str) {
            super(str, 2);
            this.f7516a = new byte[8];
            m11951a(this.f7516a, 24);
            this.f7519d = m11947a(20);
            if ((this.f7519d & 1) == 0) {
                throw new NTLMEngineException("NTLM type 2 message indicates no support for Unicode. Flags are: " + Integer.toString(this.f7519d));
            }
            byte[] b;
            this.f7517b = null;
            if (m11946a() >= 20) {
                b = m11953b(12);
                if (b.length != 0) {
                    try {
                        this.f7517b = new String(b, "UnicodeLittleUnmarked");
                    } catch (Throwable e) {
                        throw new NTLMEngineException(e.getMessage(), e);
                    }
                }
            }
            this.f7518c = null;
            if (m11946a() >= 48) {
                b = m11953b(40);
                if (b.length != 0) {
                    this.f7518c = b;
                }
            }
        }

        byte[] m11957c() {
            return this.f7516a;
        }

        String m11958d() {
            return this.f7517b;
        }

        byte[] m11959e() {
            return this.f7518c;
        }

        int m11960f() {
            return this.f7519d;
        }
    }

    class Type3Message extends NTLMMessage {
        protected int f7520a;
        protected byte[] f7521b;
        protected byte[] f7522c;
        protected byte[] f7523d;
        protected byte[] f7524e;
        protected byte[] f7525f;
        protected byte[] f7526g;

        Type3Message(String str, String str2, String str3, String str4, byte[] bArr, int i, String str5, byte[] bArr2) {
            byte[] t;
            byte[] bytes;
            this.f7520a = i;
            String c = NTLMEngineImpl.m11996f(str2);
            String d = NTLMEngineImpl.m11998g(str);
            CipherGen cipherGen = new CipherGen(d, str3, str4, bArr, str5, bArr2);
            if ((8388608 & i) != 0 && bArr2 != null && str5 != null) {
                try {
                    this.f7525f = cipherGen.m11929l();
                    this.f7524e = cipherGen.m11930m();
                    if ((i & 128) != 0) {
                        t = cipherGen.m11937t();
                    } else {
                        t = cipherGen.m11935r();
                    }
                } catch (NTLMEngineException e) {
                    this.f7525f = new byte[0];
                    this.f7524e = cipherGen.m11922e();
                    if ((i & 128) != 0) {
                        t = cipherGen.m11937t();
                    } else {
                        t = cipherGen.m11933p();
                    }
                }
            } else if ((524288 & i) != 0) {
                this.f7525f = cipherGen.m11931n();
                this.f7524e = cipherGen.m11932o();
                if ((i & 128) != 0) {
                    t = cipherGen.m11937t();
                } else {
                    t = cipherGen.m11936s();
                }
            } else {
                this.f7525f = cipherGen.m11924g();
                this.f7524e = cipherGen.m11922e();
                if ((i & 128) != 0) {
                    t = cipherGen.m11937t();
                } else {
                    t = cipherGen.m11934q();
                }
            }
            if ((i & 16) == 0) {
                this.f7526g = null;
            } else if ((1073741824 & i) != 0) {
                this.f7526g = NTLMEngineImpl.m11977b(cipherGen.m11920c(), t);
            } else {
                this.f7526g = t;
            }
            if (c != null) {
                try {
                    bytes = c.getBytes("UnicodeLittleUnmarked");
                } catch (Throwable e2) {
                    throw new NTLMEngineException("Unicode not supported: " + e2.getMessage(), e2);
                }
            }
            bytes = null;
            this.f7522c = bytes;
            this.f7521b = d != null ? d.toUpperCase(Locale.ENGLISH).getBytes("UnicodeLittleUnmarked") : null;
            this.f7523d = str3.getBytes("UnicodeLittleUnmarked");
        }

        String m11961b() {
            int length;
            int i = 0;
            int length2 = this.f7525f.length;
            int length3 = this.f7524e.length;
            int length4 = this.f7521b != null ? this.f7521b.length : 0;
            if (this.f7522c != null) {
                length = this.f7522c.length;
            } else {
                length = 0;
            }
            int length5 = this.f7523d.length;
            if (this.f7526g != null) {
                i = this.f7526g.length;
            }
            int i2 = length3 + 72;
            int i3 = i2 + length2;
            int i4 = i3 + length4;
            int i5 = i4 + length5;
            int i6 = i5 + length;
            m11949a(i6 + i, 3);
            m11954c(length3);
            m11954c(length3);
            m11955d(72);
            m11954c(length2);
            m11954c(length2);
            m11955d(i2);
            m11954c(length4);
            m11954c(length4);
            m11955d(i3);
            m11954c(length5);
            m11954c(length5);
            m11955d(i4);
            m11954c(length);
            m11954c(length);
            m11955d(i5);
            m11954c(i);
            m11954c(i);
            m11955d(i6);
            m11955d(((((((((((((this.f7520a & 128) | (this.f7520a & 512)) | (this.f7520a & 524288)) | 33554432) | (this.f7520a & 32768)) | (this.f7520a & 32)) | (this.f7520a & 16)) | (this.f7520a & 536870912)) | (this.f7520a & Integer.MIN_VALUE)) | (this.f7520a & 1073741824)) | (this.f7520a & 8388608)) | (this.f7520a & 1)) | (this.f7520a & 4));
            m11954c(261);
            m11955d(2600);
            m11954c(3840);
            m11950a(this.f7524e);
            m11950a(this.f7525f);
            m11950a(this.f7521b);
            m11950a(this.f7523d);
            m11950a(this.f7522c);
            if (this.f7526g != null) {
                m11950a(this.f7526g);
            }
            return super.m11952b();
        }
    }

    NTLMEngineImpl() {
        this.f7529b = "ASCII";
    }

    static {
        SecureRandom secureRandom = null;
        try {
            secureRandom = SecureRandom.getInstance("SHA1PRNG");
        } catch (Exception e) {
        }
        f7527a = secureRandom;
        Object a = EncodingUtils.m12763a("NTLMSSP", "ASCII");
        f7528c = new byte[(a.length + 1)];
        System.arraycopy(a, 0, f7528c, 0, a.length);
        f7528c[a.length] = (byte) 0;
    }

    String m12005b(String str, String str2) {
        return new Type1Message(str2, str).m11956b();
    }

    String m12004a(String str, String str2, String str3, String str4, byte[] bArr, int i, String str5, byte[] bArr2) {
        return new Type3Message(str4, str3, str, str2, bArr, i, str5, bArr2).m11961b();
    }

    private static String m11993e(String str) {
        if (str == null) {
            return null;
        }
        int indexOf = str.indexOf(".");
        if (indexOf != -1) {
            return str.substring(0, indexOf);
        }
        return str;
    }

    private static String m11996f(String str) {
        return m11993e(str);
    }

    private static String m11998g(String str) {
        return m11993e(str);
    }

    private static int m11986d(byte[] bArr, int i) {
        if (bArr.length >= i + 4) {
            return (((bArr[i] & 255) | ((bArr[i + 1] & 255) << 8)) | ((bArr[i + 2] & 255) << 16)) | ((bArr[i + 3] & 255) << 24);
        }
        throw new NTLMEngineException("NTLM authentication - buffer too small for DWORD");
    }

    private static int m11992e(byte[] bArr, int i) {
        if (bArr.length >= i + 2) {
            return (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8);
        }
        throw new NTLMEngineException("NTLM authentication - buffer too small for WORD");
    }

    private static byte[] m11997f(byte[] bArr, int i) {
        int e = m11992e(bArr, i);
        int d = m11986d(bArr, i + 4);
        if (bArr.length < d + e) {
            throw new NTLMEngineException("NTLM authentication - buffer too small for data item");
        }
        Object obj = new byte[e];
        System.arraycopy(bArr, d, obj, 0, e);
        return obj;
    }

    private static byte[] m11988d() {
        if (f7527a == null) {
            throw new NTLMEngineException("Random generator not available");
        }
        byte[] bArr = new byte[8];
        synchronized (f7527a) {
            f7527a.nextBytes(bArr);
        }
        return bArr;
    }

    private static byte[] m11994e() {
        if (f7527a == null) {
            throw new NTLMEngineException("Random generator not available");
        }
        byte[] bArr = new byte[16];
        synchronized (f7527a) {
            f7527a.nextBytes(bArr);
        }
        return bArr;
    }

    static byte[] m11970a(byte[] bArr, byte[] bArr2) {
        HMACMD5 hmacmd5 = new HMACMD5(bArr2);
        hmacmd5.m11938a(bArr);
        return hmacmd5.m11939a();
    }

    static byte[] m11977b(byte[] bArr, byte[] bArr2) {
        try {
            Cipher instance = Cipher.getInstance("RC4");
            instance.init(1, new SecretKeySpec(bArr2, "RC4"));
            return instance.doFinal(bArr);
        } catch (Throwable e) {
            throw new NTLMEngineException(e.getMessage(), e);
        }
    }

    static byte[] m11971a(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        try {
            MessageDigest instance = MessageDigest.getInstance(StringUtils.MD5);
            instance.update(bArr2);
            instance.update(bArr3);
            byte[] bArr4 = new byte[8];
            System.arraycopy(instance.digest(), 0, bArr4, 0, 8);
            return m11990d(bArr, bArr4);
        } catch (Throwable e) {
            if (e instanceof NTLMEngineException) {
                throw ((NTLMEngineException) e);
            }
            throw new NTLMEngineException(e.getMessage(), e);
        }
    }

    private static byte[] m12000h(String str) {
        try {
            Object bytes = str.toUpperCase(Locale.ENGLISH).getBytes(StringUtils.USASCII);
            Object obj = new byte[14];
            System.arraycopy(bytes, 0, obj, 0, Math.min(bytes.length, 14));
            Key g = m11999g(obj, 0);
            Key g2 = m11999g(obj, 7);
            byte[] bytes2 = "KGS!@#$%".getBytes(StringUtils.USASCII);
            Cipher instance = Cipher.getInstance("DES/ECB/NoPadding");
            instance.init(1, g);
            bytes = instance.doFinal(bytes2);
            instance.init(1, g2);
            Object doFinal = instance.doFinal(bytes2);
            obj = new byte[16];
            System.arraycopy(bytes, 0, obj, 0, 8);
            System.arraycopy(doFinal, 0, obj, 8, 8);
            return obj;
        } catch (Throwable e) {
            throw new NTLMEngineException(e.getMessage(), e);
        }
    }

    private static byte[] m12001i(String str) {
        try {
            byte[] bytes = str.getBytes("UnicodeLittleUnmarked");
            MD4 md4 = new MD4();
            md4.m11940a(bytes);
            return md4.m11942a();
        } catch (Throwable e) {
            throw new NTLMEngineException("Unicode not supported: " + e.getMessage(), e);
        }
    }

    private static byte[] m11982c(String str, String str2, byte[] bArr) {
        try {
            HMACMD5 hmacmd5 = new HMACMD5(bArr);
            hmacmd5.m11938a(str2.toUpperCase(Locale.ENGLISH).getBytes("UnicodeLittleUnmarked"));
            if (str != null) {
                hmacmd5.m11938a(str.toUpperCase(Locale.ENGLISH).getBytes("UnicodeLittleUnmarked"));
            }
            return hmacmd5.m11939a();
        } catch (Throwable e) {
            throw new NTLMEngineException("Unicode not supported! " + e.getMessage(), e);
        }
    }

    private static byte[] m11989d(String str, String str2, byte[] bArr) {
        try {
            HMACMD5 hmacmd5 = new HMACMD5(bArr);
            hmacmd5.m11938a(str2.toUpperCase(Locale.ENGLISH).getBytes("UnicodeLittleUnmarked"));
            if (str != null) {
                hmacmd5.m11938a(str.getBytes("UnicodeLittleUnmarked"));
            }
            return hmacmd5.m11939a();
        } catch (Throwable e) {
            throw new NTLMEngineException("Unicode not supported! " + e.getMessage(), e);
        }
    }

    private static byte[] m11990d(byte[] bArr, byte[] bArr2) {
        try {
            Object obj = new byte[21];
            System.arraycopy(bArr, 0, obj, 0, 16);
            Key g = m11999g(obj, 0);
            Key g2 = m11999g(obj, 7);
            Key g3 = m11999g(obj, 14);
            Cipher instance = Cipher.getInstance("DES/ECB/NoPadding");
            instance.init(1, g);
            Object doFinal = instance.doFinal(bArr2);
            instance.init(1, g2);
            Object doFinal2 = instance.doFinal(bArr2);
            instance.init(1, g3);
            obj = instance.doFinal(bArr2);
            Object obj2 = new byte[24];
            System.arraycopy(doFinal, 0, obj2, 0, 8);
            System.arraycopy(doFinal2, 0, obj2, 8, 8);
            System.arraycopy(obj, 0, obj2, 16, 8);
            return obj2;
        } catch (Throwable e) {
            throw new NTLMEngineException(e.getMessage(), e);
        }
    }

    private static byte[] m11991d(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        HMACMD5 hmacmd5 = new HMACMD5(bArr);
        hmacmd5.m11938a(bArr2);
        hmacmd5.m11938a(bArr3);
        Object a = hmacmd5.m11939a();
        Object obj = new byte[(a.length + bArr3.length)];
        System.arraycopy(a, 0, obj, 0, a.length);
        System.arraycopy(bArr3, 0, obj, a.length, bArr3.length);
        return obj;
    }

    private static byte[] m11995e(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        Object obj = new byte[]{(byte) 1, (byte) 1, (byte) 0, (byte) 0};
        Object obj2 = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0};
        Object obj3 = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0};
        Object obj4 = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0};
        Object obj5 = new byte[((((((obj.length + obj2.length) + bArr3.length) + 8) + obj3.length) + bArr2.length) + obj4.length)];
        System.arraycopy(obj, 0, obj5, 0, obj.length);
        int length = obj.length + 0;
        System.arraycopy(obj2, 0, obj5, length, obj2.length);
        length += obj2.length;
        System.arraycopy(bArr3, 0, obj5, length, bArr3.length);
        length += bArr3.length;
        System.arraycopy(bArr, 0, obj5, length, 8);
        length += 8;
        System.arraycopy(obj3, 0, obj5, length, obj3.length);
        length += obj3.length;
        System.arraycopy(bArr2, 0, obj5, length, bArr2.length);
        length += bArr2.length;
        System.arraycopy(obj4, 0, obj5, length, obj4.length);
        length += obj4.length;
        return obj5;
    }

    private static Key m11999g(byte[] bArr, int i) {
        r0 = new byte[7];
        System.arraycopy(bArr, i, r0, 0, 7);
        byte[] bArr2 = new byte[]{r0[0], (byte) ((r0[0] << 7) | ((r0[1] & 255) >>> 1)), (byte) ((r0[1] << 6) | ((r0[2] & 255) >>> 2)), (byte) ((r0[2] << 5) | ((r0[3] & 255) >>> 3)), (byte) ((r0[3] << 4) | ((r0[4] & 255) >>> 4)), (byte) ((r0[4] << 3) | ((r0[5] & 255) >>> 5)), (byte) ((r0[5] << 2) | ((r0[6] & 255) >>> 6)), (byte) (r0[6] << 1)};
        m11965a(bArr2);
        return new SecretKeySpec(bArr2, "DES");
    }

    private static void m11965a(byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            Object obj;
            byte b = bArr[i];
            if ((((b >>> 1) ^ ((((((b >>> 7) ^ (b >>> 6)) ^ (b >>> 5)) ^ (b >>> 4)) ^ (b >>> 3)) ^ (b >>> 2))) & 1) == 0) {
                obj = 1;
            } else {
                obj = null;
            }
            if (obj != null) {
                bArr[i] = (byte) (bArr[i] | 1);
            } else {
                bArr[i] = (byte) (bArr[i] & -2);
            }
        }
    }

    static void m11966a(byte[] bArr, int i, int i2) {
        bArr[i2] = (byte) (i & 255);
        bArr[i2 + 1] = (byte) ((i >> 8) & 255);
        bArr[i2 + 2] = (byte) ((i >> 16) & 255);
        bArr[i2 + 3] = (byte) ((i >> 24) & 255);
    }

    static int m11963a(int i, int i2, int i3) {
        return (i & i2) | ((i ^ -1) & i3);
    }

    static int m11972b(int i, int i2, int i3) {
        return ((i & i2) | (i & i3)) | (i2 & i3);
    }

    static int m11979c(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    static int m11962a(int i, int i2) {
        return (i << i2) | (i >>> (32 - i2));
    }

    public String m12002a(String str, String str2) {
        return m12005b(str2, str);
    }

    public String m12003a(String str, String str2, String str3, String str4, String str5) {
        Type2Message type2Message = new Type2Message(str5);
        return m12004a(str, str2, str4, str3, type2Message.m11957c(), type2Message.m11960f(), type2Message.m11958d(), type2Message.m11959e());
    }
}
