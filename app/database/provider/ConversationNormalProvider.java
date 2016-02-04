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
import se.emilsjolander.stickylistheaders.C1128R;

public class ConversationNormalProvider extends ContentProvider {
    public static final Uri f2373a;
    public static final Uri f2374b;
    private static final UriMatcher f2375c;
    private DatabaseHelper f2376d;

    static {
        f2373a = Uri.parse("content://com.bistalk.bisphone.provider.normal.messages/normal_conversation_table");
        f2374b = Uri.parse("content://com.bistalk.bisphone.provider.normal.messages/total_unread");
        f2375c = new UriMatcher(-1);
        f2375c.addURI("com.bistalk.bisphone.provider.normal.messages", "normal_conversation_table", 1);
        f2375c.addURI("com.bistalk.bisphone.provider.normal.messages", "normal_conversation_table/#", 2);
        f2375c.addURI("com.bistalk.bisphone.provider.normal.messages", "total_unread", 3);
    }

    public boolean onCreate() {
        this.f2376d = DatabaseHelper.m4499a(getContext());
        return this.f2376d.getWritableDatabase() != null;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        if (m4771a(strArr)) {
            sQLiteQueryBuilder.setTables("normal_conversation_table");
            Cursor a;
            switch (f2375c.match(uri)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    a = m4770a(str, strArr2);
                    a.setNotificationUri(getContext().getContentResolver(), uri);
                    return a;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    sQLiteQueryBuilder.appendWhere("_id=" + uri.getLastPathSegment());
                    a = sQLiteQueryBuilder.query(this.f2376d.getReadableDatabase(), strArr, str, strArr2, null, null, str2);
                    a.setNotificationUri(getContext().getContentResolver(), uri);
                    return a;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    a = this.f2376d.getReadableDatabase().rawQuery("SELECT SUM(unread_count) FROM normal_conversation_table", null);
                    a.setNotificationUri(getContext().getContentResolver(), f2374b);
                    return a;
                default:
                    throw new IllegalArgumentException("Invalid Uri: " + uri);
            }
        }
        throw new IllegalArgumentException("Invalid Projection");
    }

    public String getType(Uri uri) {
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        return Uri.parse("normal_conversation_table/" + this.f2376d.getWritableDatabase().insertWithOnConflict("normal_conversation_table", null, contentValues, 4));
    }

    public int delete(Uri uri, String str, String[] strArr) {
        int match = f2375c.match(uri);
        SQLiteDatabase writableDatabase = this.f2376d.getWritableDatabase();
        switch (match) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                match = writableDatabase.delete("normal_conversation_table", str, strArr);
                getContext().getContentResolver().notifyChange(f2373a, null);
                getContext().getContentResolver().notifyChange(f2374b, null);
                return match;
            default:
                throw new IllegalArgumentException("Invalid Uri: " + uri);
        }
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        int match = f2375c.match(uri);
        SQLiteDatabase writableDatabase = this.f2376d.getWritableDatabase();
        switch (match) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                match = writableDatabase.update("normal_conversation_table", contentValues, str, strArr);
                getContext().getContentResolver().notifyChange(f2373a, null);
                getContext().getContentResolver().notifyChange(f2374b, null);
                return match;
            default:
                throw new IllegalArgumentException("Invalid Uri: " + uri);
        }
    }

    private Cursor m4770a(String str, String[] strArr) {
        SQLiteDatabase readableDatabase = this.f2376d.getReadableDatabase();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT ").append("* FROM ").append("normal_conversation_table");
        stringBuilder.append(" T1").append(" LEFT JOIN ( SELECT ").append("username").append(" as contact_username, ").append("contact_id").append(", ").append("name").append(", ").append("vcard_avatar").append(", ").append("vcard_nickname").append(", ").append("recently_joined").append(", ").append("type").append(", ").append("photo_uri").append(", ").append("is_registered").append(" FROM  phone_contact_table").append(" GROUP BY username").append(") T2").append(" ON T1.").append("username").append("=").append("contact_username");
        if (str != null) {
            stringBuilder.append(" WHERE ").append(str);
        }
        stringBuilder.append(" GROUP BY T1.").append("username").append(" ORDER BY T1.").append("time_stamp").append(" DESC");
        return readableDatabase.rawQuery(stringBuilder.toString(), strArr);
    }

    private boolean m4771a(String[] strArr) {
        if (strArr == null) {
            return true;
        }
        String[] strArr2 = new String[]{"_id", "message_id", "message_body", "time_stamp", "unread_count", "username"};
        return new HashSet(Arrays.asList(strArr2)).containsAll(new HashSet(Arrays.asList(strArr)));
    }
}
