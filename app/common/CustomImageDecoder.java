package app.common;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder.ImageFileInfo;
import com.nostra13.universalimageloader.core.decode.ImageDecodingInfo;
import com.nostra13.universalimageloader.utils.C0926L;
import com.nostra13.universalimageloader.utils.IoUtils;
import java.io.Closeable;
import java.io.InputStream;

public class CustomImageDecoder extends BaseImageDecoder {
    public CustomImageDecoder(boolean z) {
        super(z);
    }

    public Bitmap m3984a(ImageDecodingInfo imageDecodingInfo) {
        Closeable b = m3982b(imageDecodingInfo);
        if (b != null) {
            try {
                ImageFileInfo a = m3981a((InputStream) b, imageDecodingInfo);
                b = m3983b(b, imageDecodingInfo);
                Bitmap decodeStream = BitmapFactory.decodeStream(b, null, m3979a(a.f7055a, imageDecodingInfo));
                if (decodeStream != null) {
                    return m3977a(decodeStream, imageDecodingInfo, a.f7056b.f7053a, a.f7056b.f7054b);
                }
                C0926L.m11277d("Image can't be decoded [%s]", imageDecodingInfo.m11198a());
                return decodeStream;
            } finally {
                IoUtils.m11267a(b);
            }
        } else if (imageDecodingInfo.m11198a().startsWith("content://com.android.contacts/")) {
            return null;
        } else {
            C0926L.m11277d("No stream for image [%s]", imageDecodingInfo.m11198a());
            return null;
        }
    }
}
