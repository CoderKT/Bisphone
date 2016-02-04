package app.database.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import app.database.DatabaseHelper;
import java.util.Arrays;
import java.util.HashSet;
import org.jivesoftware.smackx.muc.packet.MUCUser.Status;
import se.emilsjolander.stickylistheaders.C1128R;

public class ContactProvider extends ContentProvider {
    public static final Uri f2364a;
    public static final Uri f2365b;
    static final UriMatcher f2366c;
    private DatabaseHelper f2367d;

    static {
        f2364a = Uri.parse("content://com.bistalk.bisphone.provider.contacts/phone_contact_table");
        f2365b = Uri.parse("content://com.bistalk.bisphone.provider.contacts/with_recently_joined");
        f2366c = new UriMatcher(-1);
        f2366c.addURI("com.bistalk.bisphone.provider.contacts", "phone_contact_table", 1);
        f2366c.addURI("com.bistalk.bisphone.provider.contacts", "phone_contact_table/#", 2);
        f2366c.addURI("com.bistalk.bisphone.provider.contacts", "with_recently_joined", 3);
    }

    public boolean onCreate() {
        this.f2367d = DatabaseHelper.m4499a(getContext());
        return this.f2367d.getWritableDatabase() != null;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        if (m4768a(strArr)) {
            Cursor rawQuery;
            sQLiteQueryBuilder.setTables("phone_contact_table");
            switch (f2366c.match(uri)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    sQLiteQueryBuilder.appendWhere("_id=" + uri.getLastPathSegment());
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    String str3;
                    String[] strArr3 = new String[2];
                    if (str == null) {
                        str3 = null;
                    } else {
                        str3 = " AND " + str;
                    }
                    strArr3[0] = "SELECT * FROM (" + sQLiteQueryBuilder.buildQuery(strArr, "recently_joined!='0'" + str3, null, null, str2, null) + ")";
                    strArr3[1] = "SELECT * FROM (" + sQLiteQueryBuilder.buildQuery(strArr, str, null, null, str2, null) + ")";
                    String[] strArr4 = null;
                    if (strArr2 != null) {
                        strArr4 = new String[(strArr2.length * 2)];
                        System.arraycopy(strArr2, 0, strArr4, 0, strArr2.length);
                        System.arraycopy(strArr2, 0, strArr4, strArr2.length, strArr2.length);
                    }
                    rawQuery = this.f2367d.getReadableDatabase().rawQuery(sQLiteQueryBuilder.buildUnionQuery(strArr3, null, null), strArr4);
                    rawQuery.setNotificationUri(getContext().getContentResolver(), f2364a);
                    return rawQuery;
                default:
                    throw new IllegalArgumentException("Invalid Uri: " + uri);
            }
            rawQuery = sQLiteQueryBuilder.query(this.f2367d.getReadableDatabase(), strArr, str, strArr2, null, null, str2);
            rawQuery.setNotificationUri(getContext().getContentResolver(), uri);
            return rawQuery;
        }
        throw new IllegalArgumentException("Contact Provider Query, Invalid Projection");
    }

    public String getType(Uri uri) {
        return null;
    }

    public synchronized Uri insert(Uri uri, ContentValues contentValues) {
        long insert;
        int match = f2366c.match(uri);
        SQLiteDatabase writableDatabase = this.f2367d.getWritableDatabase();
        switch (match) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                insert = writableDatabase.insert("phone_contact_table", null, contentValues);
                getContext().getContentResolver().notifyChange(uri, null);
                break;
            default:
                throw new IllegalArgumentException("Invalid Uri: " + uri);
        }
        return Uri.parse("phone_contact_table/" + insert);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int delete(android.net.Uri r6, java.lang.String r7, java.lang.String[] r8) {
        /*
        r5 = this;
        monitor-enter(r5);
        r0 = f2366c;	 Catch:{ all -> 0x0029 }
        r0 = r0.match(r6);	 Catch:{ all -> 0x0029 }
        r1 = r5.f2367d;	 Catch:{ all -> 0x0029 }
        r1 = r1.getWritableDatabase();	 Catch:{ all -> 0x0029 }
        switch(r0) {
            case 1: goto L_0x002c;
            case 2: goto L_0x0042;
            default: goto L_0x0010;
        };	 Catch:{ all -> 0x0029 }
    L_0x0010:
        r0 = new java.lang.IllegalArgumentException;	 Catch:{ all -> 0x0029 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0029 }
        r1.<init>();	 Catch:{ all -> 0x0029 }
        r2 = "Invalid Uri: ";
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
        r0 = "phone_contact_table";
        r0 = r1.delete(r0, r7, r8);	 Catch:{ all -> 0x0029 }
    L_0x0032:
        r1 = r5.getContext();	 Catch:{ all -> 0x0029 }
        r1 = r1.getContentResolver();	 Catch:{ all -> 0x0029 }
        r2 = f2364a;	 Catch:{ all -> 0x0029 }
        r3 = 0;
        r1.notifyChange(r2, r3);	 Catch:{ all -> 0x0029 }
        monitor-exit(r5);
        return r0;
    L_0x0042:
        r0 = r6.getLastPathSegment();	 Catch:{ all -> 0x0029 }
        r2 = android.text.TextUtils.isEmpty(r7);	 Catch:{ all -> 0x0029 }
        if (r2 == 0) goto L_0x0073;
    L_0x004c:
        r2 = "phone_contact_table";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0029 }
        r3.<init>();	 Catch:{ all -> 0x0029 }
        r4 = "_id=";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0029 }
        r0 = r3.append(r0);	 Catch:{ all -> 0x0029 }
        r0 = r0.toString();	 Catch:{ all -> 0x0029 }
        r3 = 0;
        r0 = r1.delete(r2, r0, r3);	 Catch:{ all -> 0x0029 }
    L_0x0066:
        r1 = r5.getContext();	 Catch:{ all -> 0x0029 }
        r1 = r1.getContentResolver();	 Catch:{ all -> 0x0029 }
        r2 = 0;
        r1.notifyChange(r6, r2);	 Catch:{ all -> 0x0029 }
        goto L_0x0032;
    L_0x0073:
        r2 = "phone_contact_table";
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
        goto L_0x0066;
        */
        throw new UnsupportedOperationException("Method not decompiled: app.database.provider.ContactProvider.delete(android.net.Uri, java.lang.String, java.lang.String[]):int");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int update(android.net.Uri r7, android.content.ContentValues r8, java.lang.String r9, java.lang.String[] r10) {
        /*
        r6 = this;
        monitor-enter(r6);
        r0 = f2366c;	 Catch:{ all -> 0x0029 }
        r0 = r0.match(r7);	 Catch:{ all -> 0x0029 }
        r1 = r6.f2367d;	 Catch:{ all -> 0x0029 }
        r1 = r1.getWritableDatabase();	 Catch:{ all -> 0x0029 }
        switch(r0) {
            case 1: goto L_0x002c;
            case 2: goto L_0x0042;
            default: goto L_0x0010;
        };	 Catch:{ all -> 0x0029 }
    L_0x0010:
        r0 = new java.lang.IllegalArgumentException;	 Catch:{ all -> 0x0029 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0029 }
        r1.<init>();	 Catch:{ all -> 0x0029 }
        r2 = "Invalid Uri: ";
        r1 = r1.append(r2);	 Catch:{ all -> 0x0029 }
        r1 = r1.append(r7);	 Catch:{ all -> 0x0029 }
        r1 = r1.toString();	 Catch:{ all -> 0x0029 }
        r0.<init>(r1);	 Catch:{ all -> 0x0029 }
        throw r0;	 Catch:{ all -> 0x0029 }
    L_0x0029:
        r0 = move-exception;
        monitor-exit(r6);
        throw r0;
    L_0x002c:
        r0 = "phone_contact_table";
        r0 = r1.update(r0, r8, r9, r10);	 Catch:{ all -> 0x0029 }
    L_0x0032:
        r1 = r6.getContext();	 Catch:{ all -> 0x0029 }
        r1 = r1.getContentResolver();	 Catch:{ all -> 0x0029 }
        r2 = f2364a;	 Catch:{ all -> 0x0029 }
        r3 = 0;
        r1.notifyChange(r2, r3);	 Catch:{ all -> 0x0029 }
        monitor-exit(r6);
        return r0;
    L_0x0042:
        r0 = r7.getLastPathSegment();	 Catch:{ all -> 0x0029 }
        r2 = android.text.TextUtils.isEmpty(r9);	 Catch:{ all -> 0x0029 }
        if (r2 == 0) goto L_0x0067;
    L_0x004c:
        r2 = "phone_contact_table";
        r3 = "2 =?";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ all -> 0x0029 }
        r5 = 0;
        r4[r5] = r0;	 Catch:{ all -> 0x0029 }
        r0 = r1.update(r2, r8, r3, r4);	 Catch:{ all -> 0x0029 }
    L_0x005a:
        r1 = r6.getContext();	 Catch:{ all -> 0x0029 }
        r1 = r1.getContentResolver();	 Catch:{ all -> 0x0029 }
        r2 = 0;
        r1.notifyChange(r7, r2);	 Catch:{ all -> 0x0029 }
        goto L_0x0032;
    L_0x0067:
        r2 = "phone_contact_table";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0029 }
        r3.<init>();	 Catch:{ all -> 0x0029 }
        r4 = "2=";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0029 }
        r0 = r3.append(r0);	 Catch:{ all -> 0x0029 }
        r3 = " AND ";
        r0 = r0.append(r3);	 Catch:{ all -> 0x0029 }
        r0 = r0.append(r9);	 Catch:{ all -> 0x0029 }
        r0 = r0.toString();	 Catch:{ all -> 0x0029 }
        r0 = r1.update(r2, r8, r0, r10);	 Catch:{ all -> 0x0029 }
        goto L_0x005a;
        */
        throw new UnsupportedOperationException("Method not decompiled: app.database.provider.ContactProvider.update(android.net.Uri, android.content.ContentValues, java.lang.String, java.lang.String[]):int");
    }

    private boolean m4768a(String[] strArr) {
        if (strArr == null) {
            return true;
        }
        String[] strArr2 = new String[]{"_id", "contact_id", "contact_data_table_id", "name", "username", "version", Status.ELEMENT, "is_registered", "is_favorite", "vcard_nickname", "photo_uri", "phone_number", "vcard_avatar", "last_seen", "background_json", "recently_joined", "type", "sum(is_registered) AS is_registered"};
        return new HashSet(Arrays.asList(strArr2)).containsAll(new HashSet(Arrays.asList(strArr)));
    }
}
