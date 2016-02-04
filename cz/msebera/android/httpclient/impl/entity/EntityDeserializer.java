package cz.msebera.android.httpclient.impl.entity;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpMessage;
import cz.msebera.android.httpclient.entity.BasicHttpEntity;
import cz.msebera.android.httpclient.entity.ContentLengthStrategy;
import cz.msebera.android.httpclient.impl.io.ChunkedInputStream;
import cz.msebera.android.httpclient.impl.io.ContentLengthInputStream;
import cz.msebera.android.httpclient.impl.io.IdentityInputStream;
import cz.msebera.android.httpclient.io.SessionInputBuffer;
import cz.msebera.android.httpclient.util.Args;

@Deprecated
public class EntityDeserializer {
    private final ContentLengthStrategy f7788a;

    public EntityDeserializer(ContentLengthStrategy contentLengthStrategy) {
        this.f7788a = (ContentLengthStrategy) Args.m12722a((Object) contentLengthStrategy, "Content length strategy");
    }

    protected BasicHttpEntity m12512a(SessionInputBuffer sessionInputBuffer, HttpMessage httpMessage) {
        BasicHttpEntity basicHttpEntity = new BasicHttpEntity();
        long a = this.f7788a.m11823a(httpMessage);
        if (a == -2) {
            basicHttpEntity.m11501a(true);
            basicHttpEntity.m11811a(-1);
            basicHttpEntity.m11812a(new ChunkedInputStream(sessionInputBuffer));
        } else if (a == -1) {
            basicHttpEntity.m11501a(false);
            basicHttpEntity.m11811a(-1);
            basicHttpEntity.m11812a(new IdentityInputStream(sessionInputBuffer));
        } else {
            basicHttpEntity.m11501a(false);
            basicHttpEntity.m11811a(a);
            basicHttpEntity.m11812a(new ContentLengthInputStream(sessionInputBuffer, a));
        }
        Header c = httpMessage.m10616c("Content-Type");
        if (c != null) {
            basicHttpEntity.m11499a(c);
        }
        c = httpMessage.m10616c("Content-Encoding");
        if (c != null) {
            basicHttpEntity.m11502b(c);
        }
        return basicHttpEntity;
    }

    public HttpEntity m12513b(SessionInputBuffer sessionInputBuffer, HttpMessage httpMessage) {
        Args.m12722a((Object) sessionInputBuffer, "Session input buffer");
        Args.m12722a((Object) httpMessage, "HTTP message");
        return m12512a(sessionInputBuffer, httpMessage);
    }
}
