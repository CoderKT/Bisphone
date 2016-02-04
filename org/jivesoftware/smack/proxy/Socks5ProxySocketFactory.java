package org.jivesoftware.smack.proxy;

import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import javax.net.SocketFactory;
import org.jivesoftware.smack.proxy.ProxyInfo.ProxyType;

public class Socks5ProxySocketFactory extends SocketFactory {
    private ProxyInfo proxy;

    public Socks5ProxySocketFactory(ProxyInfo proxyInfo) {
        this.proxy = proxyInfo;
    }

    public Socket createSocket(String str, int i) {
        return socks5ProxifiedSocket(str, i);
    }

    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) {
        return socks5ProxifiedSocket(str, i);
    }

    public Socket createSocket(InetAddress inetAddress, int i) {
        return socks5ProxifiedSocket(inetAddress.getHostAddress(), i);
    }

    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) {
        return socks5ProxifiedSocket(inetAddress.getHostAddress(), i);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.net.Socket socks5ProxifiedSocket(java.lang.String r13, int r14) {
        /*
        r12 = this;
        r0 = 1;
        r3 = 0;
        r2 = 0;
        r1 = r12.proxy;
        r4 = r1.getProxyAddress();
        r1 = r12.proxy;
        r5 = r1.getProxyPort();
        r1 = r12.proxy;
        r6 = r1.getProxyUsername();
        r1 = r12.proxy;
        r7 = r1.getProxyPassword();
        r1 = new java.net.Socket;	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x0165 }
        r1.<init>(r4, r5);	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x0165 }
        r2 = r1.getInputStream();	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r4 = r1.getOutputStream();	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r5 = 1;
        r1.setTcpNoDelay(r5);	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r5 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r5 = new byte[r5];	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r8 = 0;
        r9 = 1;
        r10 = 5;
        r5[r8] = r10;	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r8 = 2;
        r10 = 2;
        r5[r9] = r10;	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r9 = 3;
        r10 = 0;
        r5[r8] = r10;	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r8 = 4;
        r10 = 2;
        r5[r9] = r10;	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r9 = 0;
        r4.write(r5, r9, r8);	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r8 = 2;
        r12.fill(r2, r5, r8);	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r8 = 1;
        r8 = r5[r8];	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r8 = r8 & 255;
        switch(r8) {
            case 0: goto L_0x0052;
            case 1: goto L_0x0051;
            case 2: goto L_0x0063;
            default: goto L_0x0051;
        };
    L_0x0051:
        r0 = r3;
    L_0x0052:
        if (r0 != 0) goto L_0x00b0;
    L_0x0054:
        r1.close();	 Catch:{ Exception -> 0x015e, RuntimeException -> 0x0061 }
    L_0x0057:
        r0 = new org.jivesoftware.smack.proxy.ProxyException;	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r2 = org.jivesoftware.smack.proxy.ProxyInfo.ProxyType.SOCKS5;	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r3 = "fail in SOCKS5 proxy";
        r0.<init>(r2, r3);	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        throw r0;	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
    L_0x0061:
        r0 = move-exception;
        throw r0;
    L_0x0063:
        if (r6 == 0) goto L_0x0051;
    L_0x0065:
        if (r7 != 0) goto L_0x0069;
    L_0x0067:
        r0 = r3;
        goto L_0x0052;
    L_0x0069:
        r8 = 0;
        r9 = 1;
        r10 = 1;
        r5[r8] = r10;	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r8 = 2;
        r10 = r6.length();	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r10 = (byte) r10;	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r5[r9] = r10;	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r9 = r6.getBytes();	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r10 = 0;
        r11 = r6.length();	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        java.lang.System.arraycopy(r9, r10, r5, r8, r11);	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r6 = r6.length();	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r6 = r6 + 2;
        r8 = r6 + 1;
        r9 = r7.length();	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r9 = (byte) r9;	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r5[r6] = r9;	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r6 = r7.getBytes();	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r9 = 0;
        r10 = r7.length();	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        java.lang.System.arraycopy(r6, r9, r5, r8, r10);	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r6 = r7.length();	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r6 = r6 + r8;
        r7 = 0;
        r4.write(r5, r7, r6);	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r6 = 2;
        r12.fill(r2, r5, r6);	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r6 = 1;
        r6 = r5[r6];	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        if (r6 != 0) goto L_0x0051;
    L_0x00af:
        goto L_0x0052;
    L_0x00b0:
        r0 = 0;
        r3 = 1;
        r6 = 5;
        r5[r0] = r6;	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r0 = 2;
        r6 = 1;
        r5[r3] = r6;	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r3 = 3;
        r6 = 0;
        r5[r0] = r6;	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r0 = r13.getBytes();	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r6 = r0.length;	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r7 = 4;
        r8 = 3;
        r5[r3] = r8;	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r3 = 5;
        r8 = (byte) r6;	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r5[r7] = r8;	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r7 = 0;
        java.lang.System.arraycopy(r0, r7, r5, r3, r6);	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r0 = r6 + 5;
        r3 = r0 + 1;
        r6 = r14 >>> 8;
        r6 = (byte) r6;	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r5[r0] = r6;	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r0 = r3 + 1;
        r6 = r14 & 255;
        r6 = (byte) r6;	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r5[r3] = r6;	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r3 = 0;
        r4.write(r5, r3, r0);	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r0 = 4;
        r12.fill(r2, r5, r0);	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r0 = 1;
        r0 = r5[r0];	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        if (r0 == 0) goto L_0x0135;
    L_0x00eb:
        r1.close();	 Catch:{ Exception -> 0x0161, RuntimeException -> 0x0061 }
    L_0x00ee:
        r0 = new org.jivesoftware.smack.proxy.ProxyException;	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r2 = org.jivesoftware.smack.proxy.ProxyInfo.ProxyType.SOCKS5;	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r3 = new java.lang.StringBuilder;	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r3.<init>();	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r4 = "server returns ";
        r3 = r3.append(r4);	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r4 = 1;
        r4 = r5[r4];	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r3 = r3.append(r4);	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r3 = r3.toString();	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r0.<init>(r2, r3);	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        throw r0;	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
    L_0x010c:
        r0 = move-exception;
    L_0x010d:
        if (r1 == 0) goto L_0x0112;
    L_0x010f:
        r1.close();	 Catch:{ Exception -> 0x0163 }
    L_0x0112:
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "ProxySOCKS5: ";
        r1 = r1.append(r2);
        r2 = r0.toString();
        r1 = r1.append(r2);
        r1 = r1.toString();
        r2 = r0 instanceof java.lang.Throwable;
        if (r2 == 0) goto L_0x0158;
    L_0x012d:
        r2 = new org.jivesoftware.smack.proxy.ProxyException;
        r3 = org.jivesoftware.smack.proxy.ProxyInfo.ProxyType.SOCKS5;
        r2.<init>(r3, r1, r0);
        throw r2;
    L_0x0135:
        r0 = 3;
        r0 = r5[r0];	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r0 = r0 & 255;
        switch(r0) {
            case 1: goto L_0x013e;
            case 2: goto L_0x013d;
            case 3: goto L_0x0143;
            case 4: goto L_0x0152;
            default: goto L_0x013d;
        };	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
    L_0x013d:
        return r1;
    L_0x013e:
        r0 = 6;
        r12.fill(r2, r5, r0);	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        goto L_0x013d;
    L_0x0143:
        r0 = 1;
        r12.fill(r2, r5, r0);	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r0 = 0;
        r0 = r5[r0];	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        r0 = r0 & 255;
        r0 = r0 + 2;
        r12.fill(r2, r5, r0);	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        goto L_0x013d;
    L_0x0152:
        r0 = 18;
        r12.fill(r2, r5, r0);	 Catch:{ RuntimeException -> 0x0061, Exception -> 0x010c }
        goto L_0x013d;
    L_0x0158:
        r0 = new java.io.IOException;
        r0.<init>(r1);
        throw r0;
    L_0x015e:
        r0 = move-exception;
        goto L_0x0057;
    L_0x0161:
        r0 = move-exception;
        goto L_0x00ee;
    L_0x0163:
        r1 = move-exception;
        goto L_0x0112;
    L_0x0165:
        r0 = move-exception;
        r1 = r2;
        goto L_0x010d;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smack.proxy.Socks5ProxySocketFactory.socks5ProxifiedSocket(java.lang.String, int):java.net.Socket");
    }

    private void fill(InputStream inputStream, byte[] bArr, int i) {
        int i2 = 0;
        while (i2 < i) {
            int read = inputStream.read(bArr, i2, i - i2);
            if (read <= 0) {
                throw new ProxyException(ProxyType.SOCKS5, "stream is closed");
            }
            i2 += read;
        }
    }
}
