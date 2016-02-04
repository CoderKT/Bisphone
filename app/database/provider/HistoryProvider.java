package app.database.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import app.database.DatabaseHelper;
import java.util.Arrays;
import java.util.HashSet;
import se.emilsjolander.stickylistheaders.C1128R;

public class HistoryProvider extends ContentProvider {
    public static final Uri f2386a;
    public static final Uri f2387b;
    public static final Uri f2388c;
    static final UriMatcher f2389d;
    private DatabaseHelper f2390e;

    static {
        f2386a = Uri.parse("content://com.bistalk.bisphone.provider.history/chats");
        f2387b = Uri.parse("content://com.bistalk.bisphone.provider.history/groups");
        f2388c = Uri.parse("content://com.bistalk.bisphone.provider.history/calls");
        f2389d = new UriMatcher(-1);
        f2389d.addURI("com.bistalk.bisphone.provider.history", "chats", 1);
        f2389d.addURI("com.bistalk.bisphone.provider.history", "groups", 2);
        f2389d.addURI("com.bistalk.bisphone.provider.history", "calls", 3);
    }

    public boolean onCreate() {
        this.f2390e = DatabaseHelper.m4499a(getContext());
        return this.f2390e.getWritableDatabase() != null;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        int match = f2389d.match(uri);
        if (m4775a(strArr, match)) {
            switch (match) {
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    sQLiteQueryBuilder.setTables("call_log_history_view");
                    Cursor query = sQLiteQueryBuilder.query(this.f2390e.getReadableDatabase(), strArr, str, strArr2, null, null, str2);
                    query.setNotificationUri(getContext().getContentResolver(), uri);
                    return query;
                default:
                    throw new IllegalArgumentException("Invalid Uri: " + uri);
            }
        }
        throw new IllegalArgumentException("CallLog Provider Query, Invalid Projection");
    }

    public String getType(Uri uri) {
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    private boolean m4775a(String[] strArr, int i) {
        if (strArr == null) {
            return true;
        }
        Object[] objArr;
        switch (i) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                objArr = new String[]{"_id", "message_id", "jabber_id", "username", "message_text", "message_type", "message_timestamp", "message_status", "message_unread", "message_outgoing", "badge", "_id_contact", "contact_type", "contact_username", "contact_is_registered", "local_contact_id", "local_name", "local_photo_uri", "remote_nickname", "remote_photo_token"};
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                objArr = new String[]{"_id", "group_jid", "group_state", "group_name", "group_photo_token", "group_members", "group_timestamp", "_id_message", "message_id", "message_jid", "message_username", "message_text", "message_type", "message_timestamp", "message_status", "message_unread", "message_outgoing"};
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                objArr = new String[]{"_id", "username", "call_type", "call_out", "call_duration", "call_timestamp", "call_credit", "_id_contact", "contact_type", "contact_username", "contact_is_registered", "local_contact_id", "local_name", "local_photo_uri", "remote_nickname", "remote_photo_token"};
                break;
            default:
                return false;
        }
        return new HashSet(Arrays.asList(objArr)).containsAll(new HashSet(Arrays.asList(strArr)));
    }
}
