package cz.msebera.android.httpclient.impl.entity;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpMessage;
import cz.msebera.android.httpclient.entity.ContentLengthStrategy;
import cz.msebera.android.httpclient.impl.io.ChunkedOutputStream;
import cz.msebera.android.httpclient.impl.io.ContentLengthOutputStream;
import cz.msebera.android.httpclient.impl.io.IdentityOutputStream;
import cz.msebera.android.httpclient.io.SessionOutputBuffer;
import cz.msebera.android.httpclient.util.Args;
import java.io.OutputStream;

@Deprecated
public class EntitySerializer {
    private final ContentLengthStrategy f7789a;

    public EntitySerializer(ContentLengthStrategy contentLengthStrategy) {
        this.f7789a = (ContentLengthStrategy) Args.m12722a((Object) contentLengthStrategy, "Content length strategy");
    }

    protected OutputStream m12514a(SessionOutputBuffer sessionOutputBuffer, HttpMessage httpMessage) {
        long a = this.f7789a.m11823a(httpMessage);
        if (a == -2) {
            return new ChunkedOutputStream(sessionOutputBuffer);
        }
        if (a == -1) {
            return new IdentityOutputStream(sessionOutputBuffer);
        }
        return new ContentLengthOutputStream(sessionOutputBuffer, a);
    }

    public void m12515a(SessionOutputBuffer sessionOutputBuffer, HttpMessage httpMessage, HttpEntity httpEntity) {
        Args.m12722a((Object) sessionOutputBuffer, "Session output buffer");
        Args.m12722a((Object) httpMessage, "HTTP message");
        Args.m12722a((Object) httpEntity, "HTTP entity");
        OutputStream a = m12514a(sessionOutputBuffer, httpMessage);
        httpEntity.m10541a(a);
        a.close();
    }
}
