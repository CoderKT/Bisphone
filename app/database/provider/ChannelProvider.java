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

public class ChannelProvider extends ContentProvider {
    public static final Uri f2360a;
    public static final Uri f2361b;
    static final UriMatcher f2362c;
    private DatabaseHelper f2363d;

    static {
        f2360a = Uri.parse("content://com.bistalk.bisphone.provider.channels/channel_table");
        f2361b = Uri.parse("content://com.bistalk.bisphone.provider.channels/increment_unread");
        f2362c = new UriMatcher(-1);
        f2362c.addURI("com.bistalk.bisphone.provider.channels", "channel_table", 1);
        f2362c.addURI("com.bistalk.bisphone.provider.channels", "channel_table/#", 2);
        f2362c.addURI("com.bistalk.bisphone.provider.channels", "increment_unread", 3);
    }

    public boolean onCreate() {
        this.f2363d = DatabaseHelper.m4499a(getContext());
        return this.f2363d.getWritableDatabase() != null;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        if (m4767a(strArr)) {
            sQLiteQueryBuilder.setTables("channel_table");
            switch (f2362c.match(uri)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    sQLiteQueryBuilder.appendWhere("_id=" + uri.getLastPathSegment());
                    break;
                default:
                    throw new IllegalArgumentException("Invalid Uri: " + uri);
            }
            Cursor query = sQLiteQueryBuilder.query(this.f2363d.getReadableDatabase(), strArr, str, strArr2, null, null, str2);
            query.setNotificationUri(getContext().getContentResolver(), uri);
            return query;
        }
        throw new IllegalArgumentException("Channel Provider Query, Invalid Projection");
    }

    public String getType(Uri uri) {
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        int match = f2362c.match(uri);
        SQLiteDatabase writableDatabase = this.f2363d.getWritableDatabase();
        switch (match) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                long insertWithOnConflict = writableDatabase.insertWithOnConflict("channel_table", null, contentValues, 5);
                getContext().getContentResolver().notifyChange(f2360a, null);
                return Uri.parse("channel_table/" + insertWithOnConflict);
            default:
                throw new IllegalArgumentException("Invalid Uri: " + uri);
        }
    }

    public int delete(Uri uri, String str, String[] strArr) {
        int match = f2362c.match(uri);
        SQLiteDatabase writableDatabase = this.f2363d.getWritableDatabase();
        switch (match) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                match = writableDatabase.delete("channel_table", str, strArr);
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                Object lastPathSegment = uri.getLastPathSegment();
                if (TextUtils.isDigitsOnly(lastPathSegment)) {
                    if (TextUtils.isEmpty(str)) {
                        match = writableDatabase.delete("channel_table", "_id=?", new String[]{lastPathSegment});
                    } else {
                        match = writableDatabase.delete("channel_table", "_id=" + lastPathSegment + " AND " + str, strArr);
                    }
                    getContext().getContentResolver().notifyChange(uri, null);
                    break;
                }
                throw new IllegalArgumentException("Invalid Uri: " + uri);
            default:
                throw new IllegalArgumentException("Invalid Uri:" + uri);
        }
        getContext().getContentResolver().notifyChange(f2360a, null);
        return match;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        int updateWithOnConflict;
        int match = f2362c.match(uri);
        SQLiteDatabase writableDatabase = this.f2363d.getWritableDatabase();
        switch (match) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                updateWithOnConflict = writableDatabase.updateWithOnConflict("channel_table", contentValues, str, strArr, 4);
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                Object lastPathSegment = uri.getLastPathSegment();
                if (TextUtils.isDigitsOnly(lastPathSegment)) {
                    if (TextUtils.isEmpty(str)) {
                        String[] strArr2 = new String[]{lastPathSegment};
                        updateWithOnConflict = writableDatabase.updateWithOnConflict("channel_table", contentValues, "_id=?", strArr2, 4);
                    } else {
                        updateWithOnConflict = writableDatabase.updateWithOnConflict("channel_table", contentValues, "_id=" + lastPathSegment + " AND " + str, strArr, 4);
                    }
                    getContext().getContentResolver().notifyChange(uri, null);
                    break;
                }
                throw new IllegalArgumentException("Invalid Uri: " + uri);
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                Object asLong;
                if (contentValues != null) {
                    asLong = contentValues.getAsLong("timestamp");
                } else {
                    asLong = null;
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("UPDATE channel_table SET ").append("unread_count = unread_count + 1 ");
                if (asLong != null) {
                    stringBuilder.append(", timestamp = MAX(timestamp, ").append(asLong).append(") ");
                }
                if (str != null) {
                    stringBuilder.append("WHERE ").append(str);
                }
                Cursor rawQuery = writableDatabase.rawQuery(stringBuilder.toString(), strArr);
                updateWithOnConflict = rawQuery.getCount();
                rawQuery.close();
                break;
            default:
                throw new IllegalArgumentException("Invalid Uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(f2360a, null);
        return updateWithOnConflict;
    }

    private boolean m4767a(String[] strArr) {
        if (strArr == null) {
            return true;
        }
        String[] strArr2 = new String[]{"_id", "channel_jid", "name", "description", "state", "avatar_token", "cover_token", "followers_count", "following", "readonly", "muted", "unread_count", "timestamp"};
        return new HashSet(Arrays.asList(strArr2)).containsAll(new HashSet(Arrays.asList(strArr)));
    }
}
