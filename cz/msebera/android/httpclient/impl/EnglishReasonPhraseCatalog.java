package cz.msebera.android.httpclient.impl;

import app.C0110R;
import cz.msebera.android.httpclient.ReasonPhraseCatalog;
import cz.msebera.android.httpclient.util.Args;
import java.util.Locale;
import org.jivesoftware.smack.tcp.XMPPTCPConnection.PacketWriter;

public class EnglishReasonPhraseCatalog implements ReasonPhraseCatalog {
    public static final EnglishReasonPhraseCatalog f7451a;
    private static final String[][] f7452b;

    static {
        f7451a = new EnglishReasonPhraseCatalog();
        f7452b = new String[][]{null, new String[3], new String[8], new String[8], new String[25], new String[8]};
        m11867a(200, "OK");
        m11867a(201, "Created");
        m11867a(202, "Accepted");
        m11867a(204, "No Content");
        m11867a(301, "Moved Permanently");
        m11867a(302, "Moved Temporarily");
        m11867a(304, "Not Modified");
        m11867a(400, "Bad Request");
        m11867a(401, "Unauthorized");
        m11867a(403, "Forbidden");
        m11867a(404, "Not Found");
        m11867a((int) PacketWriter.QUEUE_SIZE, "Internal Server Error");
        m11867a(501, "Not Implemented");
        m11867a(502, "Bad Gateway");
        m11867a(503, "Service Unavailable");
        m11867a(100, "Continue");
        m11867a(307, "Temporary Redirect");
        m11867a(405, "Method Not Allowed");
        m11867a(409, "Conflict");
        m11867a(412, "Precondition Failed");
        m11867a(413, "Request Too Long");
        m11867a(414, "Request-URI Too Long");
        m11867a(415, "Unsupported Media Type");
        m11867a(300, "Multiple Choices");
        m11867a(303, "See Other");
        m11867a(305, "Use Proxy");
        m11867a(402, "Payment Required");
        m11867a(406, "Not Acceptable");
        m11867a(407, "Proxy Authentication Required");
        m11867a(408, "Request Timeout");
        m11867a((int) C0110R.styleable.Theme_buttonStyleSmall, "Switching Protocols");
        m11867a(203, "Non Authoritative Information");
        m11867a(205, "Reset Content");
        m11867a(206, "Partial Content");
        m11867a(504, "Gateway Timeout");
        m11867a(505, "Http Version Not Supported");
        m11867a(410, "Gone");
        m11867a(411, "Length Required");
        m11867a(416, "Requested Range Not Satisfiable");
        m11867a(417, "Expectation Failed");
        m11867a((int) C0110R.styleable.Theme_checkboxStyle, "Processing");
        m11867a(207, "Multi-Status");
        m11867a(422, "Unprocessable Entity");
        m11867a(419, "Insufficient Space On Resource");
        m11867a(420, "Method Failure");
        m11867a(423, "Locked");
        m11867a(507, "Insufficient Storage");
        m11867a(424, "Failed Dependency");
    }

    protected EnglishReasonPhraseCatalog() {
    }

    public String m11868a(int i, Locale locale) {
        boolean z = i >= 100 && i < 600;
        Args.m12724a(z, "Unknown category for status code " + i);
        int i2 = i / 100;
        int i3 = i - (i2 * 100);
        if (f7452b[i2].length > i3) {
            return f7452b[i2][i3];
        }
        return null;
    }

    private static void m11867a(int i, String str) {
        int i2 = i / 100;
        f7452b[i2][i - (i2 * 100)] = str;
    }
}
