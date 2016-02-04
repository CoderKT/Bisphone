package com.loopj.android.http;

public class Base64 {

    abstract class Coder {
        public byte[] f6538a;
        public int f6539b;

        public abstract int m10599a(int i);

        public abstract boolean m10600a(byte[] bArr, int i, int i2, boolean z);

        Coder() {
        }
    }

    class Decoder extends Coder {
        private static final int[] f6540c;
        private static final int[] f6541d;
        private final int[] f6542e;
        private int f6543f;
        private int f6544g;

        static {
            f6540c = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
            f6541d = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        }

        public Decoder(int i, byte[] bArr) {
            this.a = bArr;
            this.f6542e = (i & 8) == 0 ? f6540c : f6541d;
            this.f6543f = 0;
            this.f6544g = 0;
        }

        public int m10601a(int i) {
            return ((i * 3) / 4) + 10;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean m10602a(byte[] r11, int r12, int r13, boolean r14) {
            /*
            r10 = this;
            r0 = r10.f6543f;
            r1 = 6;
            if (r0 != r1) goto L_0x0007;
        L_0x0005:
            r0 = 0;
        L_0x0006:
            return r0;
        L_0x0007:
            r4 = r13 + r12;
            r3 = r10.f6543f;
            r1 = r10.f6544g;
            r0 = 0;
            r5 = r10.a;
            r6 = r10.f6542e;
            r2 = r12;
        L_0x0013:
            if (r2 >= r4) goto L_0x0133;
        L_0x0015:
            if (r3 != 0) goto L_0x0067;
        L_0x0017:
            r7 = r2 + 4;
            if (r7 > r4) goto L_0x005a;
        L_0x001b:
            r1 = r11[r2];
            r1 = r1 & 255;
            r1 = r6[r1];
            r1 = r1 << 18;
            r7 = r2 + 1;
            r7 = r11[r7];
            r7 = r7 & 255;
            r7 = r6[r7];
            r7 = r7 << 12;
            r1 = r1 | r7;
            r7 = r2 + 2;
            r7 = r11[r7];
            r7 = r7 & 255;
            r7 = r6[r7];
            r7 = r7 << 6;
            r1 = r1 | r7;
            r7 = r2 + 3;
            r7 = r11[r7];
            r7 = r7 & 255;
            r7 = r6[r7];
            r1 = r1 | r7;
            if (r1 < 0) goto L_0x005a;
        L_0x0044:
            r7 = r0 + 2;
            r8 = (byte) r1;
            r5[r7] = r8;
            r7 = r0 + 1;
            r8 = r1 >> 8;
            r8 = (byte) r8;
            r5[r7] = r8;
            r7 = r1 >> 16;
            r7 = (byte) r7;
            r5[r0] = r7;
            r0 = r0 + 3;
            r2 = r2 + 4;
            goto L_0x0017;
        L_0x005a:
            if (r2 < r4) goto L_0x0067;
        L_0x005c:
            r2 = r1;
        L_0x005d:
            if (r14 != 0) goto L_0x0105;
        L_0x005f:
            r10.f6543f = r3;
            r10.f6544g = r2;
            r10.b = r0;
            r0 = 1;
            goto L_0x0006;
        L_0x0067:
            r12 = r2 + 1;
            r2 = r11[r2];
            r2 = r2 & 255;
            r2 = r6[r2];
            switch(r3) {
                case 0: goto L_0x0076;
                case 1: goto L_0x0086;
                case 2: goto L_0x0097;
                case 3: goto L_0x00b7;
                case 4: goto L_0x00ed;
                case 5: goto L_0x00fc;
                default: goto L_0x0072;
            };
        L_0x0072:
            r2 = r3;
        L_0x0073:
            r3 = r2;
            r2 = r12;
            goto L_0x0013;
        L_0x0076:
            if (r2 < 0) goto L_0x007e;
        L_0x0078:
            r1 = r3 + 1;
            r9 = r2;
            r2 = r1;
            r1 = r9;
            goto L_0x0073;
        L_0x007e:
            r7 = -1;
            if (r2 == r7) goto L_0x0072;
        L_0x0081:
            r0 = 6;
            r10.f6543f = r0;
            r0 = 0;
            goto L_0x0006;
        L_0x0086:
            if (r2 < 0) goto L_0x008e;
        L_0x0088:
            r1 = r1 << 6;
            r1 = r1 | r2;
            r2 = r3 + 1;
            goto L_0x0073;
        L_0x008e:
            r7 = -1;
            if (r2 == r7) goto L_0x0072;
        L_0x0091:
            r0 = 6;
            r10.f6543f = r0;
            r0 = 0;
            goto L_0x0006;
        L_0x0097:
            if (r2 < 0) goto L_0x009f;
        L_0x0099:
            r1 = r1 << 6;
            r1 = r1 | r2;
            r2 = r3 + 1;
            goto L_0x0073;
        L_0x009f:
            r7 = -2;
            if (r2 != r7) goto L_0x00ae;
        L_0x00a2:
            r2 = r0 + 1;
            r3 = r1 >> 4;
            r3 = (byte) r3;
            r5[r0] = r3;
            r0 = 4;
            r9 = r2;
            r2 = r0;
            r0 = r9;
            goto L_0x0073;
        L_0x00ae:
            r7 = -1;
            if (r2 == r7) goto L_0x0072;
        L_0x00b1:
            r0 = 6;
            r10.f6543f = r0;
            r0 = 0;
            goto L_0x0006;
        L_0x00b7:
            if (r2 < 0) goto L_0x00d1;
        L_0x00b9:
            r1 = r1 << 6;
            r1 = r1 | r2;
            r2 = r0 + 2;
            r3 = (byte) r1;
            r5[r2] = r3;
            r2 = r0 + 1;
            r3 = r1 >> 8;
            r3 = (byte) r3;
            r5[r2] = r3;
            r2 = r1 >> 16;
            r2 = (byte) r2;
            r5[r0] = r2;
            r0 = r0 + 3;
            r2 = 0;
            goto L_0x0073;
        L_0x00d1:
            r7 = -2;
            if (r2 != r7) goto L_0x00e4;
        L_0x00d4:
            r2 = r0 + 1;
            r3 = r1 >> 2;
            r3 = (byte) r3;
            r5[r2] = r3;
            r2 = r1 >> 10;
            r2 = (byte) r2;
            r5[r0] = r2;
            r0 = r0 + 2;
            r2 = 5;
            goto L_0x0073;
        L_0x00e4:
            r7 = -1;
            if (r2 == r7) goto L_0x0072;
        L_0x00e7:
            r0 = 6;
            r10.f6543f = r0;
            r0 = 0;
            goto L_0x0006;
        L_0x00ed:
            r7 = -2;
            if (r2 != r7) goto L_0x00f3;
        L_0x00f0:
            r2 = r3 + 1;
            goto L_0x0073;
        L_0x00f3:
            r7 = -1;
            if (r2 == r7) goto L_0x0072;
        L_0x00f6:
            r0 = 6;
            r10.f6543f = r0;
            r0 = 0;
            goto L_0x0006;
        L_0x00fc:
            r7 = -1;
            if (r2 == r7) goto L_0x0072;
        L_0x00ff:
            r0 = 6;
            r10.f6543f = r0;
            r0 = 0;
            goto L_0x0006;
        L_0x0105:
            switch(r3) {
                case 0: goto L_0x0108;
                case 1: goto L_0x010f;
                case 2: goto L_0x0115;
                case 3: goto L_0x011e;
                case 4: goto L_0x012d;
                default: goto L_0x0108;
            };
        L_0x0108:
            r10.f6543f = r3;
            r10.b = r0;
            r0 = 1;
            goto L_0x0006;
        L_0x010f:
            r0 = 6;
            r10.f6543f = r0;
            r0 = 0;
            goto L_0x0006;
        L_0x0115:
            r1 = r0 + 1;
            r2 = r2 >> 4;
            r2 = (byte) r2;
            r5[r0] = r2;
            r0 = r1;
            goto L_0x0108;
        L_0x011e:
            r1 = r0 + 1;
            r4 = r2 >> 10;
            r4 = (byte) r4;
            r5[r0] = r4;
            r0 = r1 + 1;
            r2 = r2 >> 2;
            r2 = (byte) r2;
            r5[r1] = r2;
            goto L_0x0108;
        L_0x012d:
            r0 = 6;
            r10.f6543f = r0;
            r0 = 0;
            goto L_0x0006;
        L_0x0133:
            r2 = r1;
            goto L_0x005d;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.loopj.android.http.Base64.Decoder.a(byte[], int, int, boolean):boolean");
        }
    }

    class Encoder extends Coder {
        private static final byte[] f6545g;
        private static final byte[] f6546h;
        public final boolean f6547c;
        public final boolean f6548d;
        public final boolean f6549e;
        int f6550f;
        private final byte[] f6551i;
        private final byte[] f6552j;
        private int f6553k;

        static {
            f6545g = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 43, (byte) 47};
            f6546h = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 45, (byte) 95};
        }

        public Encoder(int i, byte[] bArr) {
            boolean z;
            boolean z2 = true;
            this.a = bArr;
            this.f6547c = (i & 1) == 0;
            if ((i & 2) == 0) {
                z = true;
            } else {
                z = false;
            }
            this.f6548d = z;
            if ((i & 4) == 0) {
                z2 = false;
            }
            this.f6549e = z2;
            this.f6552j = (i & 8) == 0 ? f6545g : f6546h;
            this.f6551i = new byte[2];
            this.f6550f = 0;
            this.f6553k = this.f6548d ? 19 : -1;
        }

        public int m10603a(int i) {
            return ((i * 8) / 5) + 10;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean m10604a(byte[] r11, int r12, int r13, boolean r14) {
            /* JADX: method processing error */
/*
            Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:42)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:66)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
            /*
            r10 = this;
            r6 = r10.f6552j;
            r7 = r10.a;
            r1 = 0;
            r0 = r10.f6553k;
            r8 = r13 + r12;
            r2 = -1;
            r3 = r10.f6550f;
            switch(r3) {
                case 0: goto L_0x00a7;
                case 1: goto L_0x00aa;
                case 2: goto L_0x00cd;
                default: goto L_0x000f;
            };
        L_0x000f:
            r3 = r12;
        L_0x0010:
            r4 = -1;
            if (r2 == r4) goto L_0x0220;
        L_0x0013:
            r4 = 1;
            r5 = r2 >> 18;
            r5 = r5 & 63;
            r5 = r6[r5];
            r7[r1] = r5;
            r1 = 2;
            r5 = r2 >> 12;
            r5 = r5 & 63;
            r5 = r6[r5];
            r7[r4] = r5;
            r4 = 3;
            r5 = r2 >> 6;
            r5 = r5 & 63;
            r5 = r6[r5];
            r7[r1] = r5;
            r1 = 4;
            r2 = r2 & 63;
            r2 = r6[r2];
            r7[r4] = r2;
            r0 = r0 + -1;
            if (r0 != 0) goto L_0x0220;
        L_0x0039:
            r0 = r10.f6549e;
            if (r0 == 0) goto L_0x0224;
        L_0x003d:
            r0 = 5;
            r2 = 13;
            r7[r1] = r2;
        L_0x0042:
            r1 = r0 + 1;
            r2 = 10;
            r7[r0] = r2;
            r0 = 19;
            r5 = r0;
            r4 = r1;
        L_0x004c:
            r0 = r3 + 3;
            if (r0 > r8) goto L_0x00f0;
        L_0x0050:
            r0 = r11[r3];
            r0 = r0 & 255;
            r0 = r0 << 16;
            r1 = r3 + 1;
            r1 = r11[r1];
            r1 = r1 & 255;
            r1 = r1 << 8;
            r0 = r0 | r1;
            r1 = r3 + 2;
            r1 = r11[r1];
            r1 = r1 & 255;
            r0 = r0 | r1;
            r1 = r0 >> 18;
            r1 = r1 & 63;
            r1 = r6[r1];
            r7[r4] = r1;
            r1 = r4 + 1;
            r2 = r0 >> 12;
            r2 = r2 & 63;
            r2 = r6[r2];
            r7[r1] = r2;
            r1 = r4 + 2;
            r2 = r0 >> 6;
            r2 = r2 & 63;
            r2 = r6[r2];
            r7[r1] = r2;
            r1 = r4 + 3;
            r0 = r0 & 63;
            r0 = r6[r0];
            r7[r1] = r0;
            r3 = r3 + 3;
            r1 = r4 + 4;
            r0 = r5 + -1;
            if (r0 != 0) goto L_0x0220;
        L_0x0092:
            r0 = r10.f6549e;
            if (r0 == 0) goto L_0x021d;
        L_0x0096:
            r0 = r1 + 1;
            r2 = 13;
            r7[r1] = r2;
        L_0x009c:
            r1 = r0 + 1;
            r2 = 10;
            r7[r0] = r2;
            r0 = 19;
            r5 = r0;
            r4 = r1;
            goto L_0x004c;
        L_0x00a7:
            r3 = r12;
            goto L_0x0010;
        L_0x00aa:
            r3 = r12 + 2;
            if (r3 > r8) goto L_0x000f;
        L_0x00ae:
            r2 = r10.f6551i;
            r3 = 0;
            r2 = r2[r3];
            r2 = r2 & 255;
            r2 = r2 << 16;
            r3 = r12 + 1;
            r4 = r11[r12];
            r4 = r4 & 255;
            r4 = r4 << 8;
            r2 = r2 | r4;
            r12 = r3 + 1;
            r3 = r11[r3];
            r3 = r3 & 255;
            r2 = r2 | r3;
            r3 = 0;
            r10.f6550f = r3;
            r3 = r12;
            goto L_0x0010;
        L_0x00cd:
            r3 = r12 + 1;
            if (r3 > r8) goto L_0x000f;
        L_0x00d1:
            r2 = r10.f6551i;
            r3 = 0;
            r2 = r2[r3];
            r2 = r2 & 255;
            r2 = r2 << 16;
            r3 = r10.f6551i;
            r4 = 1;
            r3 = r3[r4];
            r3 = r3 & 255;
            r3 = r3 << 8;
            r2 = r2 | r3;
            r3 = r12 + 1;
            r4 = r11[r12];
            r4 = r4 & 255;
            r2 = r2 | r4;
            r4 = 0;
            r10.f6550f = r4;
            goto L_0x0010;
        L_0x00f0:
            if (r14 == 0) goto L_0x01e7;
        L_0x00f2:
            r0 = r10.f6550f;
            r0 = r3 - r0;
            r1 = r8 + -1;
            if (r0 != r1) goto L_0x0152;
        L_0x00fa:
            r2 = 0;
            r0 = r10.f6550f;
            if (r0 <= 0) goto L_0x014c;
        L_0x00ff:
            r0 = r10.f6551i;
            r1 = 1;
            r0 = r0[r2];
        L_0x0104:
            r0 = r0 & 255;
            r2 = r0 << 4;
            r0 = r10.f6550f;
            r0 = r0 - r1;
            r10.f6550f = r0;
            r1 = r4 + 1;
            r0 = r2 >> 6;
            r0 = r0 & 63;
            r0 = r6[r0];
            r7[r4] = r0;
            r0 = r1 + 1;
            r2 = r2 & 63;
            r2 = r6[r2];
            r7[r1] = r2;
            r1 = r10.f6547c;
            if (r1 == 0) goto L_0x012f;
        L_0x0123:
            r1 = r0 + 1;
            r2 = 61;
            r7[r0] = r2;
            r0 = r1 + 1;
            r2 = 61;
            r7[r1] = r2;
        L_0x012f:
            r1 = r10.f6548d;
            if (r1 == 0) goto L_0x0145;
        L_0x0133:
            r1 = r10.f6549e;
            if (r1 == 0) goto L_0x013e;
        L_0x0137:
            r1 = r0 + 1;
            r2 = 13;
            r7[r0] = r2;
            r0 = r1;
        L_0x013e:
            r1 = r0 + 1;
            r2 = 10;
            r7[r0] = r2;
            r0 = r1;
        L_0x0145:
            r4 = r0;
        L_0x0146:
            r10.b = r4;
            r10.f6553k = r5;
            r0 = 1;
            return r0;
        L_0x014c:
            r0 = r3 + 1;
            r0 = r11[r3];
            r1 = r2;
            goto L_0x0104;
        L_0x0152:
            r0 = r10.f6550f;
            r0 = r3 - r0;
            r1 = r8 + -2;
            if (r0 != r1) goto L_0x01cb;
        L_0x015a:
            r2 = 0;
            r0 = r10.f6550f;
            r1 = 1;
            if (r0 <= r1) goto L_0x01be;
        L_0x0160:
            r0 = r10.f6551i;
            r1 = 1;
            r0 = r0[r2];
            r2 = r3;
        L_0x0166:
            r0 = r0 & 255;
            r3 = r0 << 10;
            r0 = r10.f6550f;
            if (r0 <= 0) goto L_0x01c6;
        L_0x016e:
            r0 = r10.f6551i;
            r2 = r1 + 1;
            r0 = r0[r1];
            r1 = r2;
        L_0x0175:
            r0 = r0 & 255;
            r0 = r0 << 2;
            r0 = r0 | r3;
            r2 = r10.f6550f;
            r1 = r2 - r1;
            r10.f6550f = r1;
            r1 = r4 + 1;
            r2 = r0 >> 12;
            r2 = r2 & 63;
            r2 = r6[r2];
            r7[r4] = r2;
            r2 = r1 + 1;
            r3 = r0 >> 6;
            r3 = r3 & 63;
            r3 = r6[r3];
            r7[r1] = r3;
            r1 = r2 + 1;
            r0 = r0 & 63;
            r0 = r6[r0];
            r7[r2] = r0;
            r0 = r10.f6547c;
            if (r0 == 0) goto L_0x021b;
        L_0x01a0:
            r0 = r1 + 1;
            r2 = 61;
            r7[r1] = r2;
        L_0x01a6:
            r1 = r10.f6548d;
            if (r1 == 0) goto L_0x01bc;
        L_0x01aa:
            r1 = r10.f6549e;
            if (r1 == 0) goto L_0x01b5;
        L_0x01ae:
            r1 = r0 + 1;
            r2 = 13;
            r7[r0] = r2;
            r0 = r1;
        L_0x01b5:
            r1 = r0 + 1;
            r2 = 10;
            r7[r0] = r2;
            r0 = r1;
        L_0x01bc:
            r4 = r0;
            goto L_0x0146;
        L_0x01be:
            r1 = r3 + 1;
            r0 = r11[r3];
            r9 = r2;
            r2 = r1;
            r1 = r9;
            goto L_0x0166;
        L_0x01c6:
            r0 = r2 + 1;
            r0 = r11[r2];
            goto L_0x0175;
        L_0x01cb:
            r0 = r10.f6548d;
            if (r0 == 0) goto L_0x0146;
        L_0x01cf:
            if (r4 <= 0) goto L_0x0146;
        L_0x01d1:
            r0 = 19;
            if (r5 == r0) goto L_0x0146;
        L_0x01d5:
            r0 = r10.f6549e;
            if (r0 == 0) goto L_0x0219;
        L_0x01d9:
            r0 = r4 + 1;
            r1 = 13;
            r7[r4] = r1;
        L_0x01df:
            r4 = r0 + 1;
            r1 = 10;
            r7[r0] = r1;
            goto L_0x0146;
        L_0x01e7:
            r0 = r8 + -1;
            if (r3 != r0) goto L_0x01f9;
        L_0x01eb:
            r0 = r10.f6551i;
            r1 = r10.f6550f;
            r2 = r1 + 1;
            r10.f6550f = r2;
            r2 = r11[r3];
            r0[r1] = r2;
            goto L_0x0146;
        L_0x01f9:
            r0 = r8 + -2;
            if (r3 != r0) goto L_0x0146;
        L_0x01fd:
            r0 = r10.f6551i;
            r1 = r10.f6550f;
            r2 = r1 + 1;
            r10.f6550f = r2;
            r2 = r11[r3];
            r0[r1] = r2;
            r0 = r10.f6551i;
            r1 = r10.f6550f;
            r2 = r1 + 1;
            r10.f6550f = r2;
            r2 = r3 + 1;
            r2 = r11[r2];
            r0[r1] = r2;
            goto L_0x0146;
        L_0x0219:
            r0 = r4;
            goto L_0x01df;
        L_0x021b:
            r0 = r1;
            goto L_0x01a6;
        L_0x021d:
            r0 = r1;
            goto L_0x009c;
        L_0x0220:
            r5 = r0;
            r4 = r1;
            goto L_0x004c;
        L_0x0224:
            r0 = r1;
            goto L_0x0042;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.loopj.android.http.Base64.Encoder.a(byte[], int, int, boolean):boolean");
        }
    }
}
