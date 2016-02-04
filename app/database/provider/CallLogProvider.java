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
import se.emilsjolander.stickylistheaders.C1128R;

public class CallLogProvider extends ContentProvider {
    public static final Uri f2357a;
    static final UriMatcher f2358b;
    private DatabaseHelper f2359c;

    static {
        f2357a = Uri.parse("content://com.bistalk.bisphone.provider.call_logs/call_log_table");
        f2358b = new UriMatcher(-1);
        f2358b.addURI("com.bistalk.bisphone.provider.call_logs", "call_log_table", 1);
        f2358b.addURI("com.bistalk.bisphone.provider.call_logs", "call_log_table/#", 2);
    }

    public boolean onCreate() {
        this.f2359c = DatabaseHelper.m4499a(getContext());
        return this.f2359c.getWritableDatabase() != null;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        if (m4766a(strArr)) {
            sQLiteQueryBuilder.setTables("call_log_table");
            switch (f2358b.match(uri)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    sQLiteQueryBuilder.appendWhere("_id=" + uri.getLastPathSegment());
                    break;
                default:
                    throw new IllegalArgumentException("Invalid Uri: " + uri);
            }
            Cursor query = sQLiteQueryBuilder.query(this.f2359c.getReadableDatabase(), strArr, str, strArr2, null, null, str2);
            query.setNotificationUri(getContext().getContentResolver(), uri);
            return query;
        }
        throw new IllegalArgumentException("CallLog Provider Query, Invalid Projection");
    }

    public String getType(Uri uri) {
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        int match = f2358b.match(uri);
        SQLiteDatabase writableDatabase = this.f2359c.getWritableDatabase();
        switch (match) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                long insertWithOnConflict = writableDatabase.insertWithOnConflict("call_log_table", null, contentValues, 4);
                getContext().getContentResolver().notifyChange(f2357a, null);
                getContext().getContentResolver().notifyChange(HistoryProvider.f2388c, null);
                return Uri.parse("call_log_table/" + insertWithOnConflict);
            default:
                throw new IllegalArgumentException("Invalid Uri: " + uri);
        }
    }

    public int delete(Uri uri, String str, String[] strArr) {
        int match = f2358b.match(uri);
        SQLiteDatabase writableDatabase = this.f2359c.getWritableDatabase();
        switch (match) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                match = writableDatabase.delete("call_log_table", str, strArr);
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                Object lastPathSegment = uri.getLastPathSegment();
                if (TextUtils.isDigitsOnly(lastPathSegment)) {
                    if (TextUtils.isEmpty(str)) {
                        match = writableDatabase.delete("call_log_table", "_id=?", new String[]{lastPathSegment});
                    } else {
                        match = writableDatabase.delete("call_log_table", "_id=" + lastPathSegment + " AND " + str, strArr);
                    }
                    getContext().getContentResolver().notifyChange(uri, null);
                    break;
                }
                throw new IllegalArgumentException("Invalid Uri: " + uri);
            default:
                throw new IllegalArgumentException("Invalid Uri:" + uri);
        }
        getContext().getContentResolver().notifyChange(f2357a, null);
        getContext().getContentResolver().notifyChange(HistoryProvider.f2388c, null);
        return match;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        int updateWithOnConflict;
        int match = f2358b.match(uri);
        SQLiteDatabase writableDatabase = this.f2359c.getWritableDatabase();
        switch (match) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                updateWithOnConflict = writableDatabase.updateWithOnConflict("call_log_table", contentValues, str, strArr, 4);
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                Object lastPathSegment = uri.getLastPathSegment();
                if (TextUtils.isDigitsOnly(lastPathSegment)) {
                    if (TextUtils.isEmpty(str)) {
                        String[] strArr2 = new String[]{lastPathSegment};
                        updateWithOnConflict = writableDatabase.updateWithOnConflict("call_log_table", contentValues, "_id=?", strArr2, 4);
                    } else {
                        updateWithOnConflict = writableDatabase.updateWithOnConflict("call_log_table", contentValues, "_id=" + lastPathSegment + " AND " + str, strArr, 4);
                    }
                    getContext().getContentResolver().notifyChange(uri, null);
                    break;
                }
                throw new IllegalArgumentException("Invalid Uri: " + uri);
            default:
                throw new IllegalArgumentException("Invalid Uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(f2357a, null);
        getContext().getContentResolver().notifyChange(HistoryProvider.f2388c, null);
        return updateWithOnConflict;
    }

    private boolean m4766a(String[] strArr) {
        if (strArr == null) {
            return true;
        }
        String[] strArr2 = new String[]{"_id", "username", "type", "call_out", "duration", "timestamp", "credit"};
        return new HashSet(Arrays.asList(strArr2)).containsAll(new HashSet(Arrays.asList(strArr)));
    }
}
