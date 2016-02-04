package app.database;

import android.content.ContentResolver;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import app.Main;
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
import app.util.SharedPreferencesUtil;
import de.greenrobot.event.EventBus;

public class UpgradeDatabase extends AsyncTask<Void, Integer, Void> {
    SQLiteDatabase f2301a;
    int f2302b;
    int f2303c;

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m4501a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m4502a((Void) obj);
    }

    protected /* synthetic */ void onProgressUpdate(Object[] objArr) {
        m4503a((Integer[]) objArr);
    }

    public UpgradeDatabase(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        this.f2301a = sQLiteDatabase;
        this.f2302b = i;
        this.f2303c = i2;
    }

    protected Void m4501a(Void... voidArr) {
        if (this.f2302b < 13) {
            ContentResolver contentResolver = Main.f1927b.getContentResolver();
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
            DatabaseHelper.m4499a(Main.f1927b).onCreate(this.f2301a);
            SharedPreferencesUtil.m7055a("udf", "t-" + this.f2302b + "-" + this.f2303c);
        } else {
            DatabaseHelper.f2296a = true;
            ConversationGroupTable.m4803a(this.f2301a, this.f2302b, this.f2303c);
            ConversationOneToOneTable.m4810a(this.f2301a, this.f2302b, this.f2303c);
            BroadcastListTable.m4779a(this.f2301a, this.f2302b, this.f2303c);
            BroadcastUsersTable.m4783a(this.f2301a, this.f2302b, this.f2303c);
            GroupsUsers.m4824a(this.f2301a, this.f2302b, this.f2303c);
            HistoryChannelMessageTable.m4827a(this.f2301a, this.f2302b, this.f2303c);
            HistoryOneToOneMessageTable.m4831a(this.f2301a, this.f2302b, this.f2303c);
            HistoryGroupMessageTable.m4829a(this.f2301a, this.f2302b, this.f2303c);
            publishProgress(new Integer[]{Integer.valueOf(5), Integer.valueOf(49)});
            MessageTable.m4833a(this.f2301a, this.f2302b, this.f2303c);
            publishProgress(new Integer[]{Integer.valueOf(50), Integer.valueOf(59)});
            ContactTable.m4799a(this.f2301a, this.f2302b, this.f2303c);
            publishProgress(new Integer[]{Integer.valueOf(60), Integer.valueOf(66)});
            BroadcastTable.m4780a(this.f2301a, this.f2302b, this.f2303c);
            GroupTable.m4817a(this.f2301a, this.f2302b, this.f2303c);
            StickerPackTable.m4841a(this.f2301a, this.f2302b, this.f2303c);
            StickerTable.m4843a(this.f2301a, this.f2302b, this.f2303c);
            ChannelTable.m4790a(this.f2301a, this.f2302b, this.f2303c);
            CallLogTable.m4788a(this.f2301a, this.f2302b, this.f2303c);
            publishProgress(new Integer[]{Integer.valueOf(67), Integer.valueOf(84)});
            GroupHistoryDatabaseView.m4814a(this.f2301a, this.f2302b, this.f2303c);
            CallLogHistoryDatabaseView.m4785a(this.f2301a, this.f2302b, this.f2303c);
            ChatHistoryDatabaseView.m4794a(this.f2301a, this.f2302b, this.f2303c);
            SharedPreferencesUtil.m7055a("udf", "t-" + this.f2302b + "-" + this.f2303c);
            publishProgress(new Integer[]{Integer.valueOf(85), Integer.valueOf(99)});
            GroupTable.m4819b(this.f2301a, this.f2302b, this.f2303c);
            MessageTable.m4834b(this.f2301a, this.f2302b, this.f2303c);
        }
        return null;
    }

    protected void m4503a(Integer... numArr) {
        super.onProgressUpdate(numArr);
        EventBus.m12779a().m12795d(new DatabaseEvent(numArr[0].intValue(), numArr[1].intValue()));
    }

    protected void m4502a(Void voidR) {
        super.onPostExecute(voidR);
        DatabaseHelper.f2296a = false;
        EventBus.m12779a().m12795d(new DatabaseEvent(100, 100));
    }
}
