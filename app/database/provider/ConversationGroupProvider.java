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
import org.jivesoftware.smackx.muc.packet.MUCUser.Invite;
import se.emilsjolander.stickylistheaders.C1128R;

public class ConversationGroupProvider extends ContentProvider {
    public static final Uri f2368a;
    public static final Uri f2369b;
    public static final Uri f2370c;
    private static final UriMatcher f2371d;
    private DatabaseHelper f2372e;

    static {
        f2368a = Uri.parse("content://com.bistalk.bisphone.provider.group.messages/group_conversation_table");
        f2369b = Uri.parse("content://com.bistalk.bisphone.provider.group.messages/total_unread");
        f2370c = Uri.parse("content://com.bistalk.bisphone.provider.group.messages/invite");
        f2371d = new UriMatcher(-1);
        f2371d.addURI("com.bistalk.bisphone.provider.group.messages", "group_conversation_table", 1);
        f2371d.addURI("com.bistalk.bisphone.provider.group.messages", "group_conversation_table/#", 2);
        f2371d.addURI("com.bistalk.bisphone.provider.group.messages", "total_unread", 3);
        f2371d.addURI("com.bistalk.bisphone.provider.group.messages", Invite.ELEMENT, 4);
    }

    public boolean onCreate() {
        this.f2372e = DatabaseHelper.m4499a(getContext());
        return this.f2372e.getWritableDatabase() != null;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        if (m4769a(strArr)) {
            Cursor rawQuery;
            sQLiteQueryBuilder.setTables("group_conversation_table");
            switch (f2371d.match(uri)) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    sQLiteQueryBuilder.appendWhere("_id=" + uri.getLastPathSegment());
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    rawQuery = this.f2372e.getReadableDatabase().rawQuery("SELECT SUM(unread_count) FROM group_conversation_table WHERE group_state!='CREATE_DRAFT' AND group_state!='INVITED'", null);
                    rawQuery.setNotificationUri(getContext().getContentResolver(), f2369b);
                    return rawQuery;
                default:
                    throw new IllegalArgumentException("Invalid Uri: " + uri);
            }
            rawQuery = sQLiteQueryBuilder.query(this.f2372e.getReadableDatabase(), strArr, str, strArr2, null, null, str2);
            rawQuery.setNotificationUri(getContext().getContentResolver(), uri);
            return rawQuery;
        }
        throw new IllegalArgumentException("Invalid Projection");
    }

    public String getType(Uri uri) {
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        f2371d.match(uri);
        SQLiteDatabase writableDatabase = this.f2372e.getWritableDatabase();
        if (contentValues.containsKey("time_stamp") && contentValues.containsKey("message_body") && contentValues.containsKey("message_id") && contentValues.containsKey("message_username") && contentValues.containsKey("message_time_stamp") && contentValues.containsKey("unread_count") && contentValues.containsKey("name") && contentValues.containsKey("group_jid") && contentValues.containsKey("group_state") && contentValues.containsKey("avatar_token") && contentValues.containsKey("is_mute") && contentValues.containsKey("group_type") && contentValues.containsKey("group_description") && contentValues.containsKey("group_admin") && contentValues.containsKey("extra_data") && contentValues.containsKey("group_info")) {
            writableDatabase.insertWithOnConflict("group_conversation_table", null, contentValues, 5);
            getContext().getContentResolver().notifyChange(f2368a, null);
            getContext().getContentResolver().notifyChange(f2369b, null);
            return Uri.parse("group_conversation_table/" + -1);
        }
        throw new RuntimeException("Invalid parameter for insert into GroupConversationProvider.\nSome Parameter that you must send not passed to insert method. check document of insert method.");
    }

    public int delete(Uri uri, String str, String[] strArr) {
        int match = f2371d.match(uri);
        SQLiteDatabase writableDatabase = this.f2372e.getWritableDatabase();
        switch (match) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                match = writableDatabase.delete("group_conversation_table", str, strArr);
                getContext().getContentResolver().notifyChange(f2368a, null);
                getContext().getContentResolver().notifyChange(f2369b, null);
                return match;
            default:
                throw new IllegalArgumentException("Invalid Uri: " + uri);
        }
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        int match = f2371d.match(uri);
        SQLiteDatabase writableDatabase = this.f2372e.getWritableDatabase();
        switch (match) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                match = writableDatabase.update("group_conversation_table", contentValues, str, strArr);
                getContext().getContentResolver().notifyChange(f2368a, null);
                getContext().getContentResolver().notifyChange(f2369b, null);
                return match;
            default:
                throw new IllegalArgumentException("Invalid Uri: " + uri);
        }
    }

    private boolean m4769a(String[] strArr) {
        if (strArr == null) {
            return true;
        }
        String[] strArr2 = new String[]{"_id", "message_id", "message_body", "message_username", "time_stamp", "unread_count", "group_jid", "avatar_token", "is_mute", "name", "group_state", "group_description", "group_type", "group_admin"};
        return new HashSet(Arrays.asList(strArr2)).containsAll(new HashSet(Arrays.asList(strArr)));
    }
}
