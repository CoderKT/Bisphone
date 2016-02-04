package app.database.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import app.database.DatabaseHelper;
import java.util.Arrays;
import java.util.HashSet;
import se.emilsjolander.stickylistheaders.C1128R;

public class StickerProvider extends ContentProvider {
    public static final Uri f2394a;
    static final UriMatcher f2395b;
    private DatabaseHelper f2396c;

    static {
        f2394a = Uri.parse("content://com.bistalk.bisphone.provider.stickers/sticker_table");
        f2395b = new UriMatcher(-1);
        f2395b.addURI("com.bistalk.bisphone.provider.stickers", "sticker_table", 1);
        f2395b.addURI("com.bistalk.bisphone.provider.stickers", "sticker_table/#", 2);
    }

    public boolean onCreate() {
        this.f2396c = DatabaseHelper.m4499a(getContext());
        return this.f2396c.getWritableDatabase() != null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized android.database.Cursor query(android.net.Uri r9, java.lang.String[] r10, java.lang.String r11, java.lang.String[] r12, java.lang.String r13) {
        /*
        r8 = this;
        monitor-enter(r8);
        r0 = new android.database.sqlite.SQLiteQueryBuilder;	 Catch:{ all -> 0x0030 }
        r0.<init>();	 Catch:{ all -> 0x0030 }
        r8.m4777a(r10);	 Catch:{ all -> 0x0030 }
        r1 = "sticker_table";
        r0.setTables(r1);	 Catch:{ all -> 0x0030 }
        r1 = f2395b;	 Catch:{ all -> 0x0030 }
        r1 = r1.match(r9);	 Catch:{ all -> 0x0030 }
        switch(r1) {
            case 1: goto L_0x004d;
            case 2: goto L_0x0033;
            default: goto L_0x0017;
        };	 Catch:{ all -> 0x0030 }
    L_0x0017:
        r0 = new java.lang.IllegalArgumentException;	 Catch:{ all -> 0x0030 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0030 }
        r1.<init>();	 Catch:{ all -> 0x0030 }
        r2 = "Unknown URI: ";
        r1 = r1.append(r2);	 Catch:{ all -> 0x0030 }
        r1 = r1.append(r9);	 Catch:{ all -> 0x0030 }
        r1 = r1.toString();	 Catch:{ all -> 0x0030 }
        r0.<init>(r1);	 Catch:{ all -> 0x0030 }
        throw r0;	 Catch:{ all -> 0x0030 }
    L_0x0030:
        r0 = move-exception;
        monitor-exit(r8);
        throw r0;
    L_0x0033:
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0030 }
        r1.<init>();	 Catch:{ all -> 0x0030 }
        r2 = "_id=";
        r1 = r1.append(r2);	 Catch:{ all -> 0x0030 }
        r2 = r9.getLastPathSegment();	 Catch:{ all -> 0x0030 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0030 }
        r1 = r1.toString();	 Catch:{ all -> 0x0030 }
        r0.appendWhere(r1);	 Catch:{ all -> 0x0030 }
    L_0x004d:
        r1 = r8.f2396c;	 Catch:{ all -> 0x0030 }
        r1 = r1.getReadableDatabase();	 Catch:{ all -> 0x0030 }
        r5 = 0;
        r6 = 0;
        r2 = r10;
        r3 = r11;
        r4 = r12;
        r7 = r13;
        r0 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ all -> 0x0030 }
        r1 = r8.getContext();	 Catch:{ all -> 0x0030 }
        r1 = r1.getContentResolver();	 Catch:{ all -> 0x0030 }
        r0.setNotificationUri(r1, r9);	 Catch:{ all -> 0x0030 }
        monitor-exit(r8);
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: app.database.provider.StickerProvider.query(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String):android.database.Cursor");
    }

    public String getType(Uri uri) {
        return null;
    }

    public synchronized Uri insert(Uri uri, ContentValues contentValues) {
        long insertWithOnConflict;
        int match = f2395b.match(uri);
        SQLiteDatabase writableDatabase = this.f2396c.getWritableDatabase();
        switch (match) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                insertWithOnConflict = writableDatabase.insertWithOnConflict("sticker_table", null, contentValues, 4);
                getContext().getContentResolver().notifyChange(uri, null);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        return Uri.parse("sticker_table/" + insertWithOnConflict);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int delete(android.net.Uri r6, java.lang.String r7, java.lang.String[] r8) {
        /*
        r5 = this;
        monitor-enter(r5);
        r0 = f2395b;	 Catch:{ all -> 0x0029 }
        r0 = r0.match(r6);	 Catch:{ all -> 0x0029 }
        r1 = r5.f2396c;	 Catch:{ all -> 0x0029 }
        r1 = r1.getWritableDatabase();	 Catch:{ all -> 0x0029 }
        switch(r0) {
            case 1: goto L_0x002c;
            case 2: goto L_0x0040;
            default: goto L_0x0010;
        };	 Catch:{ all -> 0x0029 }
    L_0x0010:
        r0 = new java.lang.IllegalArgumentException;	 Catch:{ all -> 0x0029 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0029 }
        r1.<init>();	 Catch:{ all -> 0x0029 }
        r2 = "Unknown URI: ";
        r1 = r1.append(r2);	 Catch:{ all -> 0x0029 }
        r1 = r1.append(r6);	 Catch:{ all -> 0x0029 }
        r1 = r1.toString();	 Catch:{ all -> 0x0029 }
        r0.<init>(r1);	 Catch:{ all -> 0x0029 }
        throw r0;	 Catch:{ all -> 0x0029 }
    L_0x0029:
        r0 = move-exception;
        monitor-exit(r5);
        throw r0;
    L_0x002c:
        r0 = "sticker_table";
        r0 = r1.delete(r0, r7, r8);	 Catch:{ all -> 0x0029 }
    L_0x0032:
        r1 = r5.getContext();	 Catch:{ all -> 0x0029 }
        r1 = r1.getContentResolver();	 Catch:{ all -> 0x0029 }
        r2 = 0;
        r1.notifyChange(r6, r2);	 Catch:{ all -> 0x0029 }
        monitor-exit(r5);
        return r0;
    L_0x0040:
        r0 = r6.getLastPathSegment();	 Catch:{ all -> 0x0029 }
        r2 = android.text.TextUtils.isEmpty(r7);	 Catch:{ all -> 0x0029 }
        if (r2 == 0) goto L_0x0065;
    L_0x004a:
        r2 = "sticker_table";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0029 }
        r3.<init>();	 Catch:{ all -> 0x0029 }
        r4 = "_id=";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0029 }
        r0 = r3.append(r0);	 Catch:{ all -> 0x0029 }
        r0 = r0.toString();	 Catch:{ all -> 0x0029 }
        r3 = 0;
        r0 = r1.delete(r2, r0, r3);	 Catch:{ all -> 0x0029 }
        goto L_0x0032;
    L_0x0065:
        r2 = "sticker_table";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0029 }
        r3.<init>();	 Catch:{ all -> 0x0029 }
        r4 = "_id=";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0029 }
        r0 = r3.append(r0);	 Catch:{ all -> 0x0029 }
        r3 = " AND ";
        r0 = r0.append(r3);	 Catch:{ all -> 0x0029 }
        r0 = r0.append(r7);	 Catch:{ all -> 0x0029 }
        r0 = r0.toString();	 Catch:{ all -> 0x0029 }
        r0 = r1.delete(r2, r0, r8);	 Catch:{ all -> 0x0029 }
        goto L_0x0032;
        */
        throw new UnsupportedOperationException("Method not decompiled: app.database.provider.StickerProvider.delete(android.net.Uri, java.lang.String, java.lang.String[]):int");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int update(android.net.Uri r6, android.content.ContentValues r7, java.lang.String r8, java.lang.String[] r9) {
        /*
        r5 = this;
        monitor-enter(r5);
        r0 = f2395b;	 Catch:{ all -> 0x0029 }
        r0 = r0.match(r6);	 Catch:{ all -> 0x0029 }
        r1 = r5.f2396c;	 Catch:{ all -> 0x0029 }
        r1 = r1.getWritableDatabase();	 Catch:{ all -> 0x0029 }
        switch(r0) {
            case 1: goto L_0x002c;
            case 2: goto L_0x0040;
            default: goto L_0x0010;
        };	 Catch:{ all -> 0x0029 }
    L_0x0010:
        r0 = new java.lang.IllegalArgumentException;	 Catch:{ all -> 0x0029 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0029 }
        r1.<init>();	 Catch:{ all -> 0x0029 }
        r2 = "Unknown URI: ";
        r1 = r1.append(r2);	 Catch:{ all -> 0x0029 }
        r1 = r1.append(r6);	 Catch:{ all -> 0x0029 }
        r1 = r1.toString();	 Catch:{ all -> 0x0029 }
        r0.<init>(r1);	 Catch:{ all -> 0x0029 }
        throw r0;	 Catch:{ all -> 0x0029 }
    L_0x0029:
        r0 = move-exception;
        monitor-exit(r5);
        throw r0;
    L_0x002c:
        r0 = "sticker_table";
        r0 = r1.update(r0, r7, r8, r9);	 Catch:{ all -> 0x0029 }
    L_0x0032:
        r1 = r5.getContext();	 Catch:{ all -> 0x0029 }
        r1 = r1.getContentResolver();	 Catch:{ all -> 0x0029 }
        r2 = 0;
        r1.notifyChange(r6, r2);	 Catch:{ all -> 0x0029 }
        monitor-exit(r5);
        return r0;
    L_0x0040:
        r0 = r6.getLastPathSegment();	 Catch:{ all -> 0x0029 }
        r2 = android.text.TextUtils.isEmpty(r8);	 Catch:{ all -> 0x0029 }
        if (r2 == 0) goto L_0x0065;
    L_0x004a:
        r2 = "sticker_table";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0029 }
        r3.<init>();	 Catch:{ all -> 0x0029 }
        r4 = "_id=";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0029 }
        r0 = r3.append(r0);	 Catch:{ all -> 0x0029 }
        r0 = r0.toString();	 Catch:{ all -> 0x0029 }
        r3 = 0;
        r0 = r1.update(r2, r7, r0, r3);	 Catch:{ all -> 0x0029 }
        goto L_0x0032;
    L_0x0065:
        r2 = "sticker_table";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0029 }
        r3.<init>();	 Catch:{ all -> 0x0029 }
        r4 = "_id=";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0029 }
        r0 = r3.append(r0);	 Catch:{ all -> 0x0029 }
        r3 = " AND ";
        r0 = r0.append(r3);	 Catch:{ all -> 0x0029 }
        r0 = r0.append(r8);	 Catch:{ all -> 0x0029 }
        r0 = r0.toString();	 Catch:{ all -> 0x0029 }
        r0 = r1.update(r2, r7, r0, r9);	 Catch:{ all -> 0x0029 }
        goto L_0x0032;
        */
        throw new UnsupportedOperationException("Method not decompiled: app.database.provider.StickerProvider.update(android.net.Uri, android.content.ContentValues, java.lang.String, java.lang.String[]):int");
    }

    private void m4777a(String[] strArr) {
        String[] strArr2 = new String[]{"_id", "pack_id"};
        if (strArr != null) {
            if (!new HashSet(Arrays.asList(strArr2)).containsAll(new HashSet(Arrays.asList(strArr)))) {
                throw new IllegalArgumentException("Unknown columns in projection");
            }
        }
    }
}
