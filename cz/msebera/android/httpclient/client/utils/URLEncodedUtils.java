package cz.msebera.android.httpclient.client.utils;

import cz.msebera.android.httpclient.Consts;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.message.BasicHeaderValueParser;
import cz.msebera.android.httpclient.message.BasicNameValuePair;
import cz.msebera.android.httpclient.message.ParserCursor;
import cz.msebera.android.httpclient.util.CharArrayBuffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;

public class URLEncodedUtils {
    private static final char[] f7322a;
    private static final String f7323b;
    private static final BitSet f7324c;
    private static final BitSet f7325d;
    private static final BitSet f7326e;
    private static final BitSet f7327f;
    private static final BitSet f7328g;
    private static final BitSet f7329h;
    private static final BitSet f7330i;

    static {
        int i;
        f7322a = new char[]{'&', ';'};
        f7323b = "[" + new String(f7322a) + "]";
        f7324c = new BitSet(256);
        f7325d = new BitSet(256);
        f7326e = new BitSet(256);
        f7327f = new BitSet(256);
        f7328g = new BitSet(256);
        f7329h = new BitSet(256);
        f7330i = new BitSet(256);
        for (i = 97; i <= 122; i++) {
            f7324c.set(i);
        }
        for (i = 65; i <= 90; i++) {
            f7324c.set(i);
        }
        for (i = 48; i <= 57; i++) {
            f7324c.set(i);
        }
        f7324c.set(95);
        f7324c.set(45);
        f7324c.set(46);
        f7324c.set(42);
        f7330i.or(f7324c);
        f7324c.set(33);
        f7324c.set(126);
        f7324c.set(39);
        f7324c.set(40);
        f7324c.set(41);
        f7325d.set(44);
        f7325d.set(59);
        f7325d.set(58);
        f7325d.set(36);
        f7325d.set(38);
        f7325d.set(43);
        f7325d.set(61);
        f7326e.or(f7324c);
        f7326e.or(f7325d);
        f7327f.or(f7324c);
        f7327f.set(47);
        f7327f.set(59);
        f7327f.set(58);
        f7327f.set(64);
        f7327f.set(38);
        f7327f.set(61);
        f7327f.set(43);
        f7327f.set(36);
        f7327f.set(44);
        f7329h.set(59);
        f7329h.set(47);
        f7329h.set(63);
        f7329h.set(58);
        f7329h.set(64);
        f7329h.set(38);
        f7329h.set(61);
        f7329h.set(43);
        f7329h.set(36);
        f7329h.set(44);
        f7329h.set(91);
        f7329h.set(93);
        f7328g.or(f7329h);
        f7328g.or(f7324c);
    }

    public static List<NameValuePair> m11602a(String str, Charset charset) {
        return m11603a(str, charset, f7322a);
    }

    public static List<NameValuePair> m11603a(String str, Charset charset, char... cArr) {
        if (str == null) {
            return Collections.emptyList();
        }
        BasicHeaderValueParser basicHeaderValueParser = BasicHeaderValueParser.f7865b;
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(str.length());
        charArrayBuffer.m12751a(str);
        ParserCursor parserCursor = new ParserCursor(0, charArrayBuffer.m12757c());
        List<NameValuePair> arrayList = new ArrayList();
        while (!parserCursor.m12664c()) {
            NameValuePair a = basicHeaderValueParser.m12590a(charArrayBuffer, parserCursor, cArr);
            if (a.m11403a().length() > 0) {
                arrayList.add(new BasicNameValuePair(m11607e(a.m11403a(), charset), m11607e(a.m11404b(), charset)));
            }
        }
        return arrayList;
    }

    public static String m11601a(List<? extends NameValuePair> list, String str) {
        return m11600a((List) list, '&', str);
    }

    public static String m11600a(List<? extends NameValuePair> list, char c, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (NameValuePair nameValuePair : list) {
            String a = m11597a(nameValuePair.m11403a(), str);
            String a2 = m11597a(nameValuePair.m11404b(), str);
            if (stringBuilder.length() > 0) {
                stringBuilder.append(c);
            }
            stringBuilder.append(a);
            if (a2 != null) {
                stringBuilder.append("=");
                stringBuilder.append(a2);
            }
        }
        return stringBuilder.toString();
    }

    public static String m11596a(Iterable<? extends NameValuePair> iterable, Charset charset) {
        return m11595a((Iterable) iterable, '&', charset);
    }

    public static String m11595a(Iterable<? extends NameValuePair> iterable, char c, Charset charset) {
        StringBuilder stringBuilder = new StringBuilder();
        for (NameValuePair nameValuePair : iterable) {
            String f = m11608f(nameValuePair.m11403a(), charset);
            String f2 = m11608f(nameValuePair.m11404b(), charset);
            if (stringBuilder.length() > 0) {
                stringBuilder.append(c);
            }
            stringBuilder.append(f);
            if (f2 != null) {
                stringBuilder.append("=");
                stringBuilder.append(f2);
            }
        }
        return stringBuilder.toString();
    }

    private static String m11598a(String str, Charset charset, BitSet bitSet, boolean z) {
        if (str == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        ByteBuffer encode = charset.encode(str);
        while (encode.hasRemaining()) {
            int i = encode.get() & 255;
            if (bitSet.get(i)) {
                stringBuilder.append((char) i);
            } else if (z && i == 32) {
                stringBuilder.append('+');
            } else {
                stringBuilder.append("%");
                char toUpperCase = Character.toUpperCase(Character.forDigit((i >> 4) & 15, 16));
                char toUpperCase2 = Character.toUpperCase(Character.forDigit(i & 15, 16));
                stringBuilder.append(toUpperCase);
                stringBuilder.append(toUpperCase2);
            }
        }
        return stringBuilder.toString();
    }

    private static String m11599a(String str, Charset charset, boolean z) {
        if (str == null) {
            return null;
        }
        ByteBuffer allocate = ByteBuffer.allocate(str.length());
        CharBuffer wrap = CharBuffer.wrap(str);
        while (wrap.hasRemaining()) {
            char c = wrap.get();
            if (c == '%' && wrap.remaining() >= 2) {
                c = wrap.get();
                char c2 = wrap.get();
                int digit = Character.digit(c, 16);
                int digit2 = Character.digit(c2, 16);
                if (digit == -1 || digit2 == -1) {
                    allocate.put((byte) 37);
                    allocate.put((byte) c);
                    allocate.put((byte) c2);
                } else {
                    allocate.put((byte) ((digit << 4) + digit2));
                }
            } else if (z && c == '+') {
                allocate.put((byte) 32);
            } else {
                allocate.put((byte) c);
            }
        }
        allocate.flip();
        return charset.decode(allocate).toString();
    }

    private static String m11607e(String str, Charset charset) {
        if (str == null) {
            return null;
        }
        if (charset == null) {
            charset = Consts.f7197a;
        }
        return m11599a(str, charset, true);
    }

    private static String m11597a(String str, String str2) {
        if (str == null) {
            return null;
        }
        return m11598a(str, str2 != null ? Charset.forName(str2) : Consts.f7197a, f7330i, true);
    }

    private static String m11608f(String str, Charset charset) {
        if (str == null) {
            return null;
        }
        if (charset == null) {
            charset = Consts.f7197a;
        }
        return m11598a(str, charset, f7330i, true);
    }

    static String m11604b(String str, Charset charset) {
        return m11598a(str, charset, f7326e, false);
    }

    static String m11605c(String str, Charset charset) {
        return m11598a(str, charset, f7328g, false);
    }

    static String m11606d(String str, Charset charset) {
        return m11598a(str, charset, f7327f, false);
    }
}
