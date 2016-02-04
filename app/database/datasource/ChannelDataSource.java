package app.database.datasource;

import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.RemoteException;
import app.Main;
import app.common.entity.ChannelEntity;
import app.common.entity.ChannelEntity.Builder;
import app.common.entity.ChannelEntity.State;
import app.common.entity.HistoryChannelEntity;
import app.database.DatabaseHelper;
import app.database.provider.ChannelProvider;
import app.database.provider.HistoryChannelProvider;
import app.messaging.channel.ChannelModel;
import app.xmpp.packet.channel.ChannelElement;
import java.util.ArrayList;
import java.util.List;

public class ChannelDataSource {
    private static ChannelDataSource f2313c;
    DatabaseHelper f2314a;
    SQLiteDatabase f2315b;

    public static ChannelDataSource m4538a() {
        if (f2313c == null) {
            synchronized (ChannelDataSource.class) {
                if (f2313c == null) {
                    f2313c = new ChannelDataSource();
                }
            }
        }
        return f2313c;
    }

    private ChannelDataSource() {
        this.f2314a = DatabaseHelper.m4499a(Main.f1927b);
        this.f2315b = this.f2314a.getWritableDatabase();
    }

    public static ChannelEntity m4536a(Cursor cursor) {
        return m4546b(cursor);
    }

    public static ChannelEntity m4537a(String str) {
        ChannelEntity channelEntity = null;
        Cursor query = Main.f1927b.getContentResolver().query(ChannelProvider.f2360a, null, "channel_jid=?", new String[]{str}, null);
        if (query.moveToFirst()) {
            channelEntity = m4546b(query);
        }
        query.close();
        return channelEntity;
    }

    public static int m4544b(String str) {
        String[] strArr = new String[]{str};
        return Main.f1927b.getContentResolver().delete(ChannelProvider.f2360a, "channel_jid=?", strArr);
    }

    public static int m4548c(String str) {
        String[] strArr = new String[]{str};
        ContentValues contentValues = new ContentValues();
        contentValues.put("following", Boolean.valueOf(false));
        return Main.f1927b.getContentResolver().update(ChannelProvider.f2360a, contentValues, "channel_jid=?", strArr);
    }

    public static Uri m4535a(ChannelModel channelModel) {
        return Main.f1927b.getContentResolver().insert(ChannelProvider.f2360a, m4545b(channelModel));
    }

    public static void m4543a(List<ChannelElement> list) {
        int i = 0;
        ArrayList arrayList = new ArrayList();
        for (ChannelElement a : list) {
            arrayList.add(ContentProviderOperation.newUpdate(ChannelProvider.f2360a).withSelection("channel_jid=?", new String[]{a.m7545a()}).withValues(m4534a((ChannelElement) a.m7545a())).build());
        }
        try {
            ContentProviderResult[] applyBatch = Main.f1927b.getContentResolver().applyBatch("com.bistalk.bisphone.provider.channels", arrayList);
            if (applyBatch != null) {
                for (ContentProviderResult contentProviderResult : applyBatch) {
                    i += contentProviderResult.count.intValue();
                }
                Main.f1926a.m5683d("channel update count: " + i);
            }
        } catch (RemoteException e) {
        } catch (OperationApplicationException e2) {
            e2.printStackTrace();
        }
    }

    public static void m4542a(String str, long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("unread_count", Integer.valueOf(0));
        contentValues.put("lastseen_timestamp", Long.valueOf(j));
        String[] strArr = new String[]{str};
        Main.f1927b.getContentResolver().update(ChannelProvider.f2360a, contentValues, "channel_jid=?", strArr);
    }

    public static void m4540a(Context context) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("unread_count", "0");
        context.getContentResolver().update(ChannelProvider.f2360a, contentValues, null, null);
    }

    public static void m4541a(HistoryChannelEntity historyChannelEntity) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("timestamp", historyChannelEntity.m4419f());
        String[] strArr = new String[]{historyChannelEntity.m4414c()};
        Main.f1927b.getContentResolver().update(ChannelProvider.f2361b, contentValues, "channel_jid=?", strArr);
    }

    public static boolean m4550d(String str) {
        String[] strArr = new String[]{str};
        Cursor query = Main.f1927b.getContentResolver().query(ChannelProvider.f2360a, null, "channel_jid=?", strArr, null);
        if (query == null || query.getCount() <= 0) {
            return false;
        }
        query.close();
        return true;
    }

    public static List<ChannelEntity> m4547b() {
        Cursor query = Main.f1927b.getContentResolver().query(ChannelProvider.f2360a, null, "following=1", null, "timestamp");
        List<ChannelEntity> arrayList = new ArrayList(query.getCount());
        if (query.moveToFirst()) {
            do {
                arrayList.add(m4546b(query));
            } while (query.moveToNext());
        }
        query.close();
        return arrayList;
    }

    public static List<String> m4549c() {
        List<String> arrayList = new ArrayList();
        Cursor query = Main.f1927b.getContentResolver().query(ChannelProvider.f2360a, new String[]{"channel_jid"}, "following=1", null, null);
        if (query.moveToFirst()) {
            do {
                arrayList.add(query.getString(0));
            } while (query.moveToNext());
        }
        query.close();
        return arrayList;
    }

    private static ContentValues m4545b(ChannelModel channelModel) {
        ContentValues contentValues = new ContentValues(13);
        contentValues.put("channel_jid", channelModel.getId());
        contentValues.put("name", channelModel.getTitle());
        contentValues.put("description", channelModel.getDescription());
        contentValues.put("state", channelModel.getState().toString());
        contentValues.put("avatar_token", channelModel.getAvatar());
        contentValues.put("cover_token", channelModel.getCover());
        contentValues.put("followers_count", Long.valueOf(channelModel.getFollowers()));
        contentValues.put("following", channelModel.m6119c());
        contentValues.put("readonly", Boolean.valueOf(channelModel.m6117a()));
        contentValues.put("muted", Boolean.valueOf(channelModel.m6118b()));
        contentValues.put("timestamp", Long.valueOf(channelModel.getTimestamp()));
        contentValues.put("unread_count", Integer.valueOf(channelModel.getUnreadCount()));
        return contentValues;
    }

    private static ChannelEntity m4546b(Cursor cursor) {
        boolean z;
        boolean z2 = true;
        Builder builder = new Builder();
        Builder a = builder.m4138a(cursor.getString(cursor.getColumnIndexOrThrow("_id"))).m4142b(cursor.getString(cursor.getColumnIndexOrThrow("channel_jid"))).m4145c(cursor.getString(cursor.getColumnIndexOrThrow("name"))).m4147d(cursor.getString(cursor.getColumnIndexOrThrow("description"))).m4137a(State.valueOf(cursor.getString(cursor.getColumnIndexOrThrow("state")))).m4148e(cursor.getString(cursor.getColumnIndexOrThrow("avatar_token"))).m4149f(cursor.getString(cursor.getColumnIndexOrThrow("cover_token"))).m4136a((long) cursor.getInt(cursor.getColumnIndexOrThrow("followers_count")));
        if (cursor.getInt(cursor.getColumnIndexOrThrow("following")) != 0) {
            z = true;
        } else {
            z = false;
        }
        a = a.m4139a(z);
        if (cursor.getInt(cursor.getColumnIndexOrThrow("readonly")) != 0) {
            z = true;
        } else {
            z = false;
        }
        Builder a2 = a.m4143b(z).m4135a(cursor.getInt(cursor.getColumnIndexOrThrow("unread_count")));
        if (cursor.getInt(cursor.getColumnIndexOrThrow("muted")) == 0) {
            z2 = false;
        }
        a2.m4146c(z2).m4144c((long) cursor.getColumnIndexOrThrow("lastseen_timestamp")).m4141b(cursor.getLong(cursor.getColumnIndexOrThrow("timestamp")));
        return builder.m4140a();
    }

    public static ChannelModel m4539a(ChannelEntity channelEntity) {
        ChannelModel channelModel = new ChannelModel();
        channelModel.setFollowing(Boolean.valueOf(channelEntity.m4157g()));
        channelModel.setReadonly(channelEntity.m4158h());
        channelModel.setFollowers(channelEntity.m4156f());
        channelModel.setAvatar(channelEntity.m4154d());
        channelModel.setCover(channelEntity.m4155e());
        channelModel.setDescription(channelEntity.m4153c());
        channelModel.setId(channelEntity.m4150a());
        channelModel.setTitle(channelEntity.m4152b());
        return channelModel;
    }

    public static ContentValues m4534a(ChannelElement channelElement) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("cover_token", channelElement.m7559f());
        contentValues.put("avatar_token", channelElement.m7557e());
        contentValues.put("description", channelElement.m7553c());
        contentValues.put("name", channelElement.m7551b());
        contentValues.put("followers_count", Long.valueOf(channelElement.m7560g()));
        contentValues.put("readonly", Boolean.valueOf(channelElement.m7556d()));
        return contentValues;
    }

    public int m4551a(long j) {
        int delete = Main.f1927b.getContentResolver().delete(HistoryChannelProvider.f2377a, "timestamp < " + j, null);
        Cursor rawQuery = this.f2315b.rawQuery("select count(*),jabber_id FROM channel_message_table WHERE jabber_id IS NOT NULL GROUP BY jabber_id", null);
        if (rawQuery.getCount() > 0) {
            rawQuery.moveToFirst();
            do {
                ArrayList arrayList = new ArrayList();
                arrayList.add(rawQuery.getString(0));
                arrayList.add(rawQuery.getString(0));
                arrayList.add(rawQuery.getString(1));
                this.f2315b.rawQuery("UPDATE channel_table SET unread_count = ( CASE WHEN unread_count > ? THEN ? ELSE unread_count END ) WHERE channel_jid=?", (String[]) arrayList.toArray(new String[arrayList.size()]));
            } while (rawQuery.moveToNext());
        } else {
            ContentValues contentValues = new ContentValues(1);
            contentValues.put("unread_count", Integer.valueOf(0));
            Main.f1927b.getContentResolver().update(ChannelProvider.f2360a, contentValues, null, null);
        }
        rawQuery.close();
        return delete;
    }

    public int m4552e(String str) {
        String[] strArr = new String[]{str};
        Cursor query = this.f2315b.query("channel_table", new String[]{"unread_count"}, "channel_jid =?", strArr, null, null, null);
        if (query == null) {
            return 0;
        }
        int i;
        if (query.moveToFirst()) {
            i = query.getInt(query.getColumnIndex("unread_count"));
        } else {
            i = 0;
        }
        query.close();
        return i;
    }
}
