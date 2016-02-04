package org.jivesoftware.smack.sasl.core;

import app.C0110R;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.security.auth.callback.CallbackHandler;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.sasl.SASLMechanism;
import org.jivesoftware.smack.util.ByteUtils;
import org.jivesoftware.smack.util.MAC;
import org.jivesoftware.smack.util.SHA1;
import org.jivesoftware.smack.util.stringencoder.Base64;
import org.jxmpp.util.cache.Cache;
import org.jxmpp.util.cache.LruCache;
import se.emilsjolander.stickylistheaders.C1128R;

public class SCRAMSHA1Mechanism extends SASLMechanism {
    private static final Cache<String, Keys> CACHE;
    private static final byte[] CLIENT_KEY_BYTES;
    private static final String DEFAULT_GS2_HEADER = "n,,";
    public static final String NAME = "SCRAM-SHA-1";
    private static final byte[] ONE;
    private static final SecureRandom RANDOM;
    private static final int RANDOM_ASCII_BYTE_COUNT = 32;
    private static final byte[] SERVER_KEY_BYTES;
    private String clientFirstMessageBare;
    private String clientRandomAscii;
    private byte[] serverSignature;
    private State state;

    /* renamed from: org.jivesoftware.smack.sasl.core.SCRAMSHA1Mechanism.1 */
    /* synthetic */ class C10151 {
        static final /* synthetic */ int[] f8579x569b47df;

        static {
            f8579x569b47df = new int[State.values().length];
            try {
                f8579x569b47df[State.AUTH_TEXT_SENT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f8579x569b47df[State.RESPONSE_SENT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    class Keys {
        private final byte[] clientKey;
        private final byte[] serverKey;

        public Keys(byte[] bArr, byte[] bArr2) {
            this.clientKey = bArr;
            this.serverKey = bArr2;
        }
    }

    enum State {
        INITIAL,
        AUTH_TEXT_SENT,
        RESPONSE_SENT,
        VALID_SERVER_RESPONSE
    }

    public SCRAMSHA1Mechanism() {
        this.state = State.INITIAL;
    }

    static {
        CLIENT_KEY_BYTES = SASLMechanism.toBytes("Client Key");
        SERVER_KEY_BYTES = SASLMechanism.toBytes("Server Key");
        ONE = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 1};
        RANDOM = new SecureRandom();
        CACHE = new LruCache(10);
    }

    protected void authenticateInternal(CallbackHandler callbackHandler) {
        throw new UnsupportedOperationException("CallbackHandler not (yet) supported");
    }

    protected byte[] getAuthenticationText() {
        this.clientRandomAscii = getRandomAscii();
        this.clientFirstMessageBare = "n=" + escape(SASLMechanism.saslPrep(this.authenticationId)) + ",r=" + this.clientRandomAscii;
        String str = DEFAULT_GS2_HEADER + this.clientFirstMessageBare;
        this.state = State.AUTH_TEXT_SENT;
        return SASLMechanism.toBytes(str);
    }

    public String getName() {
        return NAME;
    }

    public int getPriority() {
        return 110;
    }

    public SCRAMSHA1Mechanism newInstance() {
        return new SCRAMSHA1Mechanism();
    }

    public void checkIfSuccessfulOrThrow() {
        if (this.state != State.VALID_SERVER_RESPONSE) {
            throw new SmackException("SCRAM-SHA1 is missing valid server response");
        }
    }

    protected byte[] evaluateChallenge(byte[] bArr) {
        String str = new String(bArr);
        switch (C10151.f8579x569b47df[this.state.ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                Map parseAttributes = parseAttributes(str);
                String str2 = (String) parseAttributes.get(Character.valueOf('r'));
                if (str2 == null) {
                    throw new SmackException("Server random ASCII is null");
                } else if (str2.length() <= this.clientRandomAscii.length()) {
                    throw new SmackException("Server random ASCII is shorter then client random ASCII");
                } else if (str2.substring(0, this.clientRandomAscii.length()).equals(this.clientRandomAscii)) {
                    String str3 = (String) parseAttributes.get(Character.valueOf('i'));
                    if (str3 == null) {
                        throw new SmackException("Iterations attribute not set");
                    }
                    try {
                        int parseInt = Integer.parseInt(str3);
                        str3 = (String) parseAttributes.get(Character.valueOf('s'));
                        if (str3 == null) {
                            throw new SmackException("SALT not send");
                        }
                        byte[] hi;
                        byte[] hmac;
                        String str4 = "c=" + Base64.encode(DEFAULT_GS2_HEADER) + ",r=" + str2;
                        byte[] toBytes = SASLMechanism.toBytes(this.clientFirstMessageBare + ',' + str + ',' + str4);
                        String str5 = this.password + ',' + str3;
                        Keys keys = (Keys) CACHE.get(str5);
                        if (keys == null) {
                            hi = hi(SASLMechanism.saslPrep(this.password), Base64.decode(str3), parseInt);
                            hmac = hmac(hi, SERVER_KEY_BYTES);
                            hi = hmac(hi, CLIENT_KEY_BYTES);
                            CACHE.put(str5, new Keys(hi, hmac));
                        } else {
                            hmac = keys.serverKey;
                            hi = keys.clientKey;
                        }
                        this.serverSignature = hmac(hmac, toBytes);
                        toBytes = hmac(SHA1.bytes(hi), toBytes);
                        byte[] bArr2 = new byte[hi.length];
                        for (int i = 0; i < bArr2.length; i++) {
                            bArr2[i] = (byte) (hi[i] ^ toBytes[i]);
                        }
                        str2 = str4 + ",p=" + Base64.encodeToString(bArr2);
                        this.state = State.RESPONSE_SENT;
                        return SASLMechanism.toBytes(str2);
                    } catch (Throwable e) {
                        throw new SmackException("Exception parsing iterations", e);
                    }
                } else {
                    throw new SmackException("Received client random ASCII does not match client random ASCII");
                }
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                if (("v=" + Base64.encodeToString(this.serverSignature)).equals(str)) {
                    this.state = State.VALID_SERVER_RESPONSE;
                    return null;
                }
                throw new SmackException("Server final message does not match calculated one");
            default:
                throw new SmackException("Invalid state");
        }
    }

    private static Map<Character, String> parseAttributes(String str) {
        if (str.length() == 0) {
            return Collections.emptyMap();
        }
        String[] split = str.split(",");
        Map<Character, String> hashMap = new HashMap(split.length, 1.0f);
        for (String str2 : split) {
            if (str2.length() < 3) {
                throw new SmackException("Invalid Key-Value pair: " + str2);
            }
            char charAt = str2.charAt(0);
            if (str2.charAt(1) != '=') {
                throw new SmackException("Invalid Key-Value pair: " + str2);
            }
            hashMap.put(Character.valueOf(charAt), str2.substring(2));
        }
        return hashMap;
    }

    String getRandomAscii() {
        int i = 0;
        char[] cArr = new char[RANDOM_ASCII_BYTE_COUNT];
        while (i < RANDOM_ASCII_BYTE_COUNT) {
            char nextInt = (char) RANDOM.nextInt(128);
            if (isPrintableNonCommaAsciiChar(nextInt)) {
                int i2 = i + 1;
                cArr[i] = nextInt;
                i = i2;
            }
        }
        return new String(cArr);
    }

    private static boolean isPrintableNonCommaAsciiChar(char c) {
        if (c != ',' && c >= ' ' && c < '\u007f') {
            return true;
        }
        return false;
    }

    private static String escape(String str) {
        StringBuilder stringBuilder = new StringBuilder((int) (((double) str.length()) * 1.1d));
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            switch (charAt) {
                case C0110R.styleable.Theme_listDividerAlertDialog /*44*/:
                    stringBuilder.append("=2C");
                    break;
                case C0110R.styleable.Theme_popupWindowStyle /*61*/:
                    stringBuilder.append("=3D");
                    break;
                default:
                    stringBuilder.append(charAt);
                    break;
            }
        }
        return stringBuilder.toString();
    }

    private static byte[] hmac(byte[] bArr, byte[] bArr2) {
        try {
            return MAC.hmacsha1(bArr, bArr2);
        } catch (Throwable e) {
            throw new SmackException("SCRAM-SHA-1 HMAC-SHA1 Exception", e);
        }
    }

    private static byte[] hi(String str, byte[] bArr, int i) {
        byte[] bytes = str.getBytes();
        Object hmac = hmac(bytes, ByteUtils.concact(bArr, ONE));
        byte[] bArr2 = (byte[]) hmac.clone();
        byte[] bArr3 = hmac;
        int i2 = 1;
        while (i2 < i) {
            byte[] hmac2 = hmac(bytes, bArr3);
            for (int i3 = 0; i3 < hmac2.length; i3++) {
                bArr2[i3] = (byte) (bArr2[i3] ^ hmac2[i3]);
            }
            i2++;
            bArr3 = hmac2;
        }
        return bArr2;
    }
}
