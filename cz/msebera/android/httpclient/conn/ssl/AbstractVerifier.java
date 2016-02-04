package cz.msebera.android.httpclient.conn.ssl;

import cz.msebera.android.httpclient.conn.util.InetAddressUtils;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import java.net.InetAddress;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

public abstract class AbstractVerifier implements X509HostnameVerifier {
    private static final String[] f7377b;
    public HttpClientAndroidLog f7378a;

    static {
        f7377b = new String[]{"ac", "co", "com", "ed", "edu", "go", "gouv", "gov", "info", "lg", "ne", "net", "or", "org"};
        Arrays.sort(f7377b);
    }

    public AbstractVerifier() {
        this.f7378a = new HttpClientAndroidLog(getClass());
    }

    public final void m11751a(String str, SSLSocket sSLSocket) {
        if (str == null) {
            throw new NullPointerException("host to verify is null");
        }
        SSLSession session = sSLSocket.getSession();
        if (session == null) {
            sSLSocket.getInputStream().available();
            session = sSLSocket.getSession();
            if (session == null) {
                sSLSocket.startHandshake();
                session = sSLSocket.getSession();
            }
        }
        m11750a(str, (X509Certificate) session.getPeerCertificates()[0]);
    }

    public final boolean verify(String str, SSLSession sSLSession) {
        try {
            m11750a(str, (X509Certificate) sSLSession.getPeerCertificates()[0]);
            return true;
        } catch (SSLException e) {
            return false;
        }
    }

    public final void m11750a(String str, X509Certificate x509Certificate) {
        m11743a(str, m11744a(x509Certificate), m11745a(x509Certificate, str));
    }

    public final void m11752a(String str, String[] strArr, String[] strArr2, boolean z) {
        int i;
        LinkedList linkedList = new LinkedList();
        if (!(strArr == null || strArr.length <= 0 || strArr[0] == null)) {
            linkedList.add(strArr[0]);
        }
        if (strArr2 != null) {
            for (Object obj : strArr2) {
                if (obj != null) {
                    linkedList.add(obj);
                }
            }
        }
        if (linkedList.isEmpty()) {
            throw new SSLException("Certificate for <" + str + "> doesn't contain CN or DNS subjectAlt");
        }
        StringBuilder stringBuilder = new StringBuilder();
        String e = m11749e(str.trim().toLowerCase(Locale.ENGLISH));
        Iterator it = linkedList.iterator();
        boolean z2 = false;
        while (it.hasNext()) {
            String toLowerCase = ((String) it.next()).toLowerCase(Locale.ENGLISH);
            stringBuilder.append(" <");
            stringBuilder.append(toLowerCase);
            stringBuilder.append('>');
            if (it.hasNext()) {
                stringBuilder.append(" OR");
            }
            String[] split = toLowerCase.split("\\.");
            i = (split.length < 3 || !split[0].endsWith("*") || !m11753a(toLowerCase) || m11748d(str)) ? 0 : 1;
            if (i != 0) {
                String str2 = split[0];
                if (str2.length() > 1) {
                    String substring = str2.substring(0, str2.length() - 1);
                    str2 = toLowerCase.substring(str2.length());
                    String substring2 = e.substring(substring.length());
                    if (e.startsWith(substring) && substring2.endsWith(str2)) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                } else {
                    z2 = e.endsWith(toLowerCase.substring(1));
                }
                if (z2 && z) {
                    if (m11747c(e) == m11747c(toLowerCase)) {
                        z2 = true;
                        continue;
                    } else {
                        z2 = false;
                        continue;
                    }
                }
            } else {
                z2 = e.equals(m11749e(toLowerCase));
                continue;
            }
            if (z2) {
                break;
            }
        }
        if (!z2) {
            throw new SSLException("hostname in certificate didn't match: <" + str + "> !=" + stringBuilder);
        }
    }

    boolean m11753a(String str) {
        String[] split = str.split("\\.");
        if (split.length == 3 && split[2].length() == 2 && Arrays.binarySearch(f7377b, split[1]) >= 0) {
            return false;
        }
        return true;
    }

    public static String[] m11744a(X509Certificate x509Certificate) {
        try {
            return m11746b(x509Certificate.getSubjectX500Principal().toString());
        } catch (SSLException e) {
            return null;
        }
    }

    static String[] m11746b(String str) {
        if (str == null) {
            return null;
        }
        LinkedList linkedList = new LinkedList();
        StringTokenizer stringTokenizer = new StringTokenizer(str, ",");
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            int indexOf = nextToken.indexOf("CN=");
            if (indexOf >= 0) {
                linkedList.add(nextToken.substring(indexOf + 3));
            }
        }
        if (linkedList.isEmpty()) {
            return null;
        }
        String[] strArr = new String[linkedList.size()];
        linkedList.toArray(strArr);
        return strArr;
    }

    private static String[] m11745a(X509Certificate x509Certificate, String str) {
        int i;
        if (m11748d(str)) {
            i = 7;
        } else {
            i = 2;
        }
        LinkedList linkedList = new LinkedList();
        Collection subjectAlternativeNames;
        try {
            subjectAlternativeNames = x509Certificate.getSubjectAlternativeNames();
        } catch (CertificateParsingException e) {
            subjectAlternativeNames = null;
        }
        if (r0 != null) {
            for (List list : r0) {
                if (((Integer) list.get(0)).intValue() == i) {
                    linkedList.add((String) list.get(1));
                }
            }
        }
        if (linkedList.isEmpty()) {
            return null;
        }
        String[] strArr = new String[linkedList.size()];
        linkedList.toArray(strArr);
        return strArr;
    }

    public static int m11747c(String str) {
        int i = 0;
        int i2 = 0;
        while (i < str.length()) {
            if (str.charAt(i) == '.') {
                i2++;
            }
            i++;
        }
        return i2;
    }

    private static boolean m11748d(String str) {
        return str != null && (InetAddressUtils.m11764a(str) || InetAddressUtils.m11767d(str));
    }

    private String m11749e(String str) {
        if (str != null && InetAddressUtils.m11767d(str)) {
            try {
                str = InetAddress.getByName(str).getHostAddress();
            } catch (Throwable e) {
                this.f7378a.m11838b("Unexpected error converting " + str, e);
            }
        }
        return str;
    }
}
