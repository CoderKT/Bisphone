package app.database;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;
import app.database.provider.ChannelProvider;
import app.database.provider.ContactProvider;
import app.database.provider.ConversationGroupProvider;
import app.database.provider.ConversationNormalProvider;
import app.database.provider.HistoryChannelProvider;
import app.database.provider.HistoryGroupProvider;
import app.database.provider.HistoryOneToOneProvider;
import app.database.provider.StickerPackProvider;
import app.database.provider.StickerProvider;
import app.database.table.BroadcastListTable;
import app.database.table.BroadcastTable;
import app.database.table.BroadcastUsersTable;
import app.database.table.CallLogHistoryDatabaseView;
import app.database.table.CallLogTable;
import app.database.table.ChannelTable;
import app.database.table.ChatHistoryDatabaseView;
import app.database.table.ContactTable;
import app.database.table.ConversationGroupTable;
import app.database.table.ConversationOneToOneTable;
import app.database.table.GroupHistoryDatabaseView;
import app.database.table.GroupTable;
import app.database.table.GroupsUsers;
import app.database.table.HistoryChannelMessageTable;
import app.database.table.HistoryGroupMessageTable;
import app.database.table.HistoryOneToOneMessageTable;
import app.database.table.MessageTable;
import app.database.table.StickerPackTable;
import app.database.table.StickerTable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static volatile boolean f2296a;
    public static volatile boolean f2297b;
    private static DatabaseHelper f2298d;
    private static final Object f2299e;
    Context f2300c;

    static {
        f2296a = false;
        f2297b = false;
        f2299e = new Object();
    }

    public static DatabaseHelper m4499a(Context context) {
        if (f2298d == null) {
            synchronized (f2299e) {
                if (f2298d == null) {
                    f2298d = new DatabaseHelper(context);
                }
            }
        }
        return f2298d;
    }

    private DatabaseHelper(Context context) {
        super(context, "bisphone.db", null, 21);
        this.f2300c = context;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        ContactTable.m4798a(sQLiteDatabase);
        StickerPackTable.m4840a(sQLiteDatabase);
        StickerTable.m4842a(sQLiteDatabase);
        ChannelTable.m4789a(sQLiteDatabase);
        CallLogTable.m4787a(sQLiteDatabase);
        ConversationGroupTable.m4802a(sQLiteDatabase);
        ConversationOneToOneTable.m4809a(sQLiteDatabase);
        GroupsUsers.m4823a(sQLiteDatabase);
        HistoryChannelMessageTable.m4826a(sQLiteDatabase);
        HistoryOneToOneMessageTable.m4830a(sQLiteDatabase);
        HistoryGroupMessageTable.m4828a(sQLiteDatabase);
        CallLogHistoryDatabaseView.m4784a(sQLiteDatabase);
        BroadcastListTable.m4778a(sQLiteDatabase);
        BroadcastUsersTable.m4782a(sQLiteDatabase);
        PreferenceManager.getDefaultSharedPreferences(this.f2300c).edit().putString("udf", "t").apply();
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.f2300c);
        if (i < 13) {
            ContentResolver contentResolver = this.f2300c.getContentResolver();
            contentResolver.delete(HistoryOneToOneProvider.f2383a, null, null);
            contentResolver.delete(HistoryGroupProvider.f2380a, null, null);
            contentResolver.delete(HistoryChannelProvider.f2377a, null, null);
            contentResolver.delete(ConversationNormalProvider.f2373a, null, null);
            contentResolver.delete(HistoryOneToOneProvider.f2383a, null, null);
            contentResolver.delete(ContactProvider.f2364a, null, null);
            contentResolver.delete(ConversationGroupProvider.f2368a, null, null);
            contentResolver.delete(StickerProvider.f2394a, null, null);
            contentResolver.delete(StickerPackProvider.f2391a, null, null);
            contentResolver.delete(ChannelProvider.f2360a, null, null);
            onCreate(sQLiteDatabase);
            return;
        }
        if (i >= 18 || i2 < 18) {
            m4500a(sQLiteDatabase, i, i2);
        } else {
            defaultSharedPreferences.edit().putString("udf", "f-" + i + "-" + i2).apply();
            f2296a = true;
            new UpgradeDatabase(sQLiteDatabase, i, i2).execute(new Void[0]);
        }
        if (i < 20 && i2 >= 20) {
            defaultSharedPreferences.edit().putBoolean("ggln", true).apply();
        }
    }

    private void m4500a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        ConversationGroupTable.m4803a(sQLiteDatabase, i, i2);
        ConversationOneToOneTable.m4810a(sQLiteDatabase, i, i2);
        GroupsUsers.m4824a(sQLiteDatabase, i, i2);
        HistoryChannelMessageTable.m4827a(sQLiteDatabase, i, i2);
        HistoryOneToOneMessageTable.m4831a(sQLiteDatabase, i, i2);
        HistoryGroupMessageTable.m4829a(sQLiteDatabase, i, i2);
        MessageTable.m4833a(sQLiteDatabase, i, i2);
        ContactTable.m4799a(sQLiteDatabase, i, i2);
        GroupTable.m4817a(sQLiteDatabase, i, i2);
        StickerPackTable.m4841a(sQLiteDatabase, i, i2);
        StickerTable.m4843a(sQLiteDatabase, i, i2);
        ChannelTable.m4790a(sQLiteDatabase, i, i2);
        CallLogTable.m4788a(sQLiteDatabase, i, i2);
        ChatHistoryDatabaseView.m4794a(sQLiteDatabase, i, i2);
        GroupHistoryDatabaseView.m4814a(sQLiteDatabase, i, i2);
        CallLogHistoryDatabaseView.m4785a(sQLiteDatabase, i, i2);
        BroadcastListTable.m4779a(sQLiteDatabase, i, i2);
        BroadcastUsersTable.m4783a(sQLiteDatabase, i, i2);
        GroupTable.m4819b(sQLiteDatabase, i, i2);
        MessageTable.m4834b(sQLiteDatabase, i, i2);
        BroadcastTable.m4781b(sQLiteDatabase, i, i2);
    }
}
