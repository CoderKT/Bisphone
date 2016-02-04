package org.jivesoftware.smack.sasl.provided;

import javax.security.auth.callback.CallbackHandler;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.sasl.SASLMechanism;
import org.jivesoftware.smack.util.ByteUtils;
import org.jivesoftware.smack.util.MD5;
import org.jivesoftware.smack.util.StringUtils;
import se.emilsjolander.stickylistheaders.C1128R;

public class SASLDigestMD5Mechanism extends SASLMechanism {
    static final /* synthetic */ boolean $assertionsDisabled;
    private static final String INITAL_NONCE = "00000001";
    public static final String NAME = "DIGEST-MD5";
    private static final String QOP_VALUE = "auth";
    private static boolean verifyServerResponse;
    private String cnonce;
    private String digestUri;
    private String hex_hashed_a1;
    private String nonce;
    private State state;

    /* renamed from: org.jivesoftware.smack.sasl.provided.SASLDigestMD5Mechanism.1 */
    /* synthetic */ class C10161 {
        static final /* synthetic */ int[] f8580xa1fe489b;

        static {
            f8580xa1fe489b = new int[State.values().length];
            try {
                f8580xa1fe489b[State.INITIAL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f8580xa1fe489b[State.RESPONSE_SENT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    enum DigestType {
        ClientResponse,
        ServerResponse
    }

    enum State {
        INITIAL,
        RESPONSE_SENT,
        VALID_SERVER_RESPONSE
    }

    static {
        boolean z;
        if (SASLDigestMD5Mechanism.class.desiredAssertionStatus()) {
            z = $assertionsDisabled;
        } else {
            z = true;
        }
        $assertionsDisabled = z;
        verifyServerResponse = true;
    }

    public SASLDigestMD5Mechanism() {
        this.state = State.INITIAL;
    }

    public static void setVerifyServerResponse(boolean z) {
        verifyServerResponse = z;
    }

    protected void authenticateInternal(CallbackHandler callbackHandler) {
        throw new UnsupportedOperationException("CallbackHandler not (yet) supported");
    }

    protected byte[] getAuthenticationText() {
        return null;
    }

    public String getName() {
        return NAME;
    }

    public int getPriority() {
        return 210;
    }

    public SASLDigestMD5Mechanism newInstance() {
        return new SASLDigestMD5Mechanism();
    }

    public void checkIfSuccessfulOrThrow() {
        if (verifyServerResponse && this.state != State.VALID_SERVER_RESPONSE) {
            throw new SmackException("DIGEST-MD5 no valid server response");
        }
    }

    protected byte[] evaluateChallenge(byte[] bArr) {
        if (bArr.length == 0) {
            throw new SmackException("Initial challenge has zero length");
        }
        String[] split = new String(bArr).split(",");
        switch (C10161.f8580xa1fe489b[this.state.ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                int length = split.length;
                int i = 0;
                while (i < length) {
                    String[] split2 = split[i].split("=");
                    if ($assertionsDisabled || split2.length == 2) {
                        String replaceFirst = split2[0].replaceFirst("^\\s+", "");
                        String str = split2[1];
                        if ("nonce".equals(replaceFirst)) {
                            if (this.nonce != null) {
                                throw new SmackException("Nonce value present multiple times");
                            }
                            this.nonce = str.replace("\"", "");
                        } else if ("qop".equals(replaceFirst)) {
                            str = str.replace("\"", "");
                            if (!str.equals(QOP_VALUE)) {
                                throw new SmackException("Unsupported qop operation: " + str);
                            }
                        } else {
                            continue;
                        }
                        i++;
                    } else {
                        throw new AssertionError();
                    }
                }
                if (this.nonce == null) {
                    throw new SmackException("nonce value not present in initial challenge");
                }
                byte[] bytes = MD5.bytes(this.authenticationId + ':' + this.serviceName + ':' + this.password);
                this.cnonce = StringUtils.randomString(32);
                bytes = ByteUtils.concact(bytes, SASLMechanism.toBytes(':' + this.nonce + ':' + this.cnonce));
                this.digestUri = "xmpp/" + this.serviceName;
                this.hex_hashed_a1 = StringUtils.encodeHex(MD5.bytes(bytes));
                bytes = SASLMechanism.toBytes("username=\"" + this.authenticationId + '\"' + ",realm=\"" + this.serviceName + '\"' + ",nonce=\"" + this.nonce + '\"' + ",cnonce=\"" + this.cnonce + '\"' + ",nc=" + INITAL_NONCE + ",qop=auth" + ",digest-uri=\"" + this.digestUri + '\"' + ",response=" + calcResponse(DigestType.ClientResponse) + ",charset=utf-8");
                this.state = State.RESPONSE_SENT;
                return bytes;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                if (verifyServerResponse) {
                    String str2;
                    int length2 = split.length;
                    int i2 = 0;
                    while (i2 < length2) {
                        String[] split3 = split[i2].split("=");
                        if ($assertionsDisabled || split3.length == 2) {
                            Object obj = split3[0];
                            str2 = split3[1];
                            if (!"rspauth".equals(obj)) {
                                i2++;
                            } else if (str2 == null) {
                                throw new SmackException("No server response received while performing DIGEST-MD5 authentication");
                            } else if (!str2.equals(calcResponse(DigestType.ServerResponse))) {
                                throw new SmackException("Invalid server response  while performing DIGEST-MD5 authentication");
                            }
                        }
                        throw new AssertionError();
                    }
                    str2 = null;
                    if (str2 == null) {
                        throw new SmackException("No server response received while performing DIGEST-MD5 authentication");
                    } else if (str2.equals(calcResponse(DigestType.ServerResponse))) {
                        throw new SmackException("Invalid server response  while performing DIGEST-MD5 authentication");
                    }
                }
                this.state = State.VALID_SERVER_RESPONSE;
                return null;
            default:
                throw new IllegalStateException();
        }
    }

    private String calcResponse(DigestType digestType) {
        StringBuilder stringBuilder = new StringBuilder();
        if (digestType == DigestType.ClientResponse) {
            stringBuilder.append("AUTHENTICATE");
        }
        stringBuilder.append(':');
        stringBuilder.append(this.digestUri);
        String encodeHex = StringUtils.encodeHex(MD5.bytes(stringBuilder.toString()));
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(this.hex_hashed_a1);
        stringBuilder2.append(':');
        stringBuilder2.append(this.nonce);
        stringBuilder2.append(':');
        stringBuilder2.append(INITAL_NONCE);
        stringBuilder2.append(':');
        stringBuilder2.append(this.cnonce);
        stringBuilder2.append(':');
        stringBuilder2.append(QOP_VALUE);
        stringBuilder2.append(':');
        stringBuilder2.append(encodeHex);
        return StringUtils.encodeHex(MD5.bytes(stringBuilder2.toString()));
    }
}
