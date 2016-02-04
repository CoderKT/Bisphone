package app.database.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import app.database.DatabaseHelper;
import java.util.Arrays;
import java.util.HashSet;
import org.jivesoftware.smackx.muc.packet.MUCUser.Status;
import org.jivesoftware.smackx.xdatalayout.packet.DataLayout.Text;
import se.emilsjolander.stickylistheaders.C1128R;

public class HistoryGroupProvider extends ContentProvider {
    public static final Uri f2380a;
    private static final UriMatcher f2381b;
    private DatabaseHelper f2382c;

    static {
        f2380a = Uri.parse("content://com.bistalk.bisphone.provider.group.conversation/normal_message_table");
        f2381b = new UriMatcher(-1);
        f2381b.addURI("com.bistalk.bisphone.provider.group.conversation", "normal_message_table", 1);
        f2381b.addURI("com.bistalk.bisphone.provider.group.conversation", "normal_message_table/#", 2);
    }

    public boolean onCreate() {
        this.f2382c = DatabaseHelper.m4499a(getContext());
        return this.f2382c.getWritableDatabase() != null;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        if (m4773a(strArr)) {
            sQLiteQueryBuilder.setTables("group_message_table");
            switch (f2381b.match(uri)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    sQLiteQueryBuilder.appendWhere("_id=" + uri.getLastPathSegment());
                    break;
                default:
                    throw new IllegalArgumentException("Invalid Uri: " + uri);
            }
            Cursor query = sQLiteQueryBuilder.query(this.f2382c.getReadableDatabase(), strArr, str, strArr2, null, null, str2);
            query.setNotificationUri(getContext().getContentResolver(), uri);
            return query;
        }
        throw new IllegalArgumentException("Invalid Projection");
    }

    public String getType(Uri uri) {
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        int match = f2381b.match(uri);
        SQLiteDatabase writableDatabase = this.f2382c.getWritableDatabase();
        switch (match) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                long insertWithOnConflict = writableDatabase.insertWithOnConflict("group_message_table", null, contentValues, 5);
                getContext().getContentResolver().notifyChange(f2380a, null);
                return Uri.parse("normal_message_table/" + insertWithOnConflict);
            default:
                throw new IllegalArgumentException("Invalid Uri: " + uri);
        }
    }

    public int delete(Uri uri, String str, String[] strArr) {
        int match = f2381b.match(uri);
        SQLiteDatabase writableDatabase = this.f2382c.getWritableDatabase();
        switch (match) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                match = writableDatabase.delete("group_message_table", str, strArr);
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                String lastPathSegment = uri.getLastPathSegment();
                if (TextUtils.isEmpty(str)) {
                    match = writableDatabase.delete("group_message_table", "_id=?", new String[]{lastPathSegment});
                } else {
                    match = writableDatabase.delete("group_message_table", "_id=" + lastPathSegment + " AND " + str, strArr);
                }
                getContext().getContentResolver().notifyChange(uri, null);
                break;
            default:
                throw new IllegalArgumentException("Invalid Uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(f2380a, null);
        return match;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        int match = f2381b.match(uri);
        SQLiteDatabase writableDatabase = this.f2382c.getWritableDatabase();
        switch (match) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                match = writableDatabase.update("group_message_table", contentValues, str, strArr);
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                String lastPathSegment = uri.getLastPathSegment();
                if (!TextUtils.isEmpty(str)) {
                    match = writableDatabase.update("group_message_table", contentValues, "_id=" + lastPathSegment + " AND " + str, strArr);
                    break;
                }
                match = writableDatabase.update("group_message_table", contentValues, "_id=?", new String[]{lastPathSegment});
                break;
            default:
                throw new IllegalArgumentException("Invalid Uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(f2380a, null);
        return match;
    }

    private boolean m4773a(String[] strArr) {
        if (strArr == null) {
            return true;
        }
        String[] strArr2 = new String[]{"_id", "message_id", "contact_username", Text.ELEMENT, "type", "timestamp", Status.ELEMENT, "thumbnail", "extra_data", "max(timestamp)"};
        return new HashSet(Arrays.asList(strArr2)).containsAll(new HashSet(Arrays.asList(strArr)));
    }
}
