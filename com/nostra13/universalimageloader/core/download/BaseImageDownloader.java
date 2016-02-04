package com.nostra13.universalimageloader.core.download;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.ContactsContract.Contacts;
import android.provider.MediaStore.Video.Thumbnails;
import android.webkit.MimeTypeMap;
import com.nostra13.universalimageloader.core.assist.ContentLengthInputStream;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;
import com.nostra13.universalimageloader.utils.IoUtils;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import se.emilsjolander.stickylistheaders.C1128R;

public class BaseImageDownloader implements ImageDownloader {
    protected final Context f7068a;
    protected final int f7069b;
    protected final int f7070c;

    /* renamed from: com.nostra13.universalimageloader.core.download.BaseImageDownloader.1 */
    /* synthetic */ class C09241 {
        static final /* synthetic */ int[] f7067a;

        static {
            f7067a = new int[Scheme.values().length];
            try {
                f7067a[Scheme.HTTP.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f7067a[Scheme.HTTPS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f7067a[Scheme.FILE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f7067a[Scheme.CONTENT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f7067a[Scheme.ASSETS.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f7067a[Scheme.DRAWABLE.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f7067a[Scheme.UNKNOWN.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    public BaseImageDownloader(Context context) {
        this(context, 5000, 20000);
    }

    public BaseImageDownloader(Context context, int i, int i2) {
        this.f7068a = context.getApplicationContext();
        this.f7069b = i;
        this.f7070c = i2;
    }

    public InputStream m11213a(String str, Object obj) {
        switch (C09241.f7067a[Scheme.m11222a(str).ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                return m11215b(str, obj);
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                return m11217d(str, obj);
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                return m11218e(str, obj);
            case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                return m11219f(str, obj);
            case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                return m11220g(str, obj);
            default:
                return m11221h(str, obj);
        }
    }

    protected InputStream m11215b(String str, Object obj) {
        HttpURLConnection c = m11216c(str, obj);
        int i = 0;
        while (c.getResponseCode() / 100 == 3 && i < 5) {
            c = m11216c(c.getHeaderField("Location"), obj);
            i++;
        }
        try {
            Closeable inputStream = c.getInputStream();
            if (m11214a(c)) {
                return new ContentLengthInputStream(new BufferedInputStream(inputStream, 32768), c.getContentLength());
            }
            IoUtils.m11267a(inputStream);
            throw new IOException("Image request failed with response code " + c.getResponseCode());
        } catch (IOException e) {
            IoUtils.m11268a(c.getErrorStream());
            throw e;
        }
    }

    protected boolean m11214a(HttpURLConnection httpURLConnection) {
        return httpURLConnection.getResponseCode() == 200;
    }

    protected HttpURLConnection m11216c(String str, Object obj) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(Uri.encode(str, "@#&=*+-_.,:!?()/~'%")).openConnection();
        httpURLConnection.setConnectTimeout(this.f7069b);
        httpURLConnection.setReadTimeout(this.f7070c);
        return httpURLConnection;
    }

    protected InputStream m11217d(String str, Object obj) {
        String c = Scheme.FILE.m11225c(str);
        if (m11211b(str)) {
            return m11209a(c);
        }
        return new ContentLengthInputStream(new BufferedInputStream(new FileInputStream(c), 32768), (int) new File(c).length());
    }

    @TargetApi(8)
    private InputStream m11209a(String str) {
        if (VERSION.SDK_INT >= 8) {
            Bitmap createVideoThumbnail = ThumbnailUtils.createVideoThumbnail(str, 2);
            if (createVideoThumbnail != null) {
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                createVideoThumbnail.compress(CompressFormat.PNG, 0, byteArrayOutputStream);
                return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            }
        }
        return null;
    }

    protected InputStream m11218e(String str, Object obj) {
        ContentResolver contentResolver = this.f7068a.getContentResolver();
        Uri parse = Uri.parse(str);
        if (m11210b(parse)) {
            Bitmap thumbnail = Thumbnails.getThumbnail(contentResolver, Long.valueOf(parse.getLastPathSegment()).longValue(), 1, null);
            if (thumbnail != null) {
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                thumbnail.compress(CompressFormat.PNG, 0, byteArrayOutputStream);
                return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            }
        } else if (str.startsWith("content://com.android.contacts/")) {
            return m11212a(parse);
        }
        return contentResolver.openInputStream(parse);
    }

    @TargetApi(14)
    protected InputStream m11212a(Uri uri) {
        ContentResolver contentResolver = this.f7068a.getContentResolver();
        if (VERSION.SDK_INT >= 14) {
            return Contacts.openContactPhotoInputStream(contentResolver, uri, true);
        }
        return Contacts.openContactPhotoInputStream(contentResolver, uri);
    }

    protected InputStream m11219f(String str, Object obj) {
        return this.f7068a.getAssets().open(Scheme.ASSETS.m11225c(str));
    }

    protected InputStream m11220g(String str, Object obj) {
        return this.f7068a.getResources().openRawResource(Integer.parseInt(Scheme.DRAWABLE.m11225c(str)));
    }

    protected InputStream m11221h(String str, Object obj) {
        throw new UnsupportedOperationException(String.format("UIL doesn't support scheme(protocol) by default [%s]. You should implement this support yourself (BaseImageDownloader.getStreamFromOtherSource(...))", new Object[]{str}));
    }

    private boolean m11210b(Uri uri) {
        String type = this.f7068a.getContentResolver().getType(uri);
        return type != null && type.startsWith("video/");
    }

    private boolean m11211b(String str) {
        String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(str));
        return mimeTypeFromExtension != null && mimeTypeFromExtension.startsWith("video/");
    }
}
