package app.database.datasource;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import app.Main;
import app.common.entity.ChatHistoryEntity;
import app.common.entity.ConversationNormalEntity.Builder;
import app.common.entity.HistoryEntity.DeliveryStatus;
import app.common.entity.HistoryEntity.Type;
import app.database.DatabaseHelper;
import app.xmpp.JabberId;
import app.xmpp.NormalMessageManager;
import java.util.ArrayList;
import java.util.List;
import org.jivesoftware.smackx.muc.packet.MUCUser.Status;
import org.jivesoftware.smackx.xdatalayout.packet.DataLayout.Text;

public class BroadcastListDataSource {
    private static BroadcastListDataSource f2304c;
    private DatabaseHelper f2305a;
    private SQLiteDatabase f2306b;

    public static BroadcastListDataSource m4504a() {
        if (f2304c == null) {
            synchronized (BroadcastListDataSource.class) {
                if (f2304c == null) {
                    f2304c = new BroadcastListDataSource();
                }
            }
        }
        return f2304c;
    }

    private BroadcastListDataSource() {
        this.f2305a = DatabaseHelper.m4499a(Main.f1927b);
        this.f2306b = this.f2305a.getWritableDatabase();
    }

    public ContentValues m4505a(ChatHistoryEntity chatHistoryEntity) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("broadcast_id", chatHistoryEntity.m4168c());
        contentValues.put("name", chatHistoryEntity.m4173g());
        contentValues.put("avatar_token", chatHistoryEntity.m4174h());
        contentValues.put("message_body", chatHistoryEntity.m4170d().m4335e());
        contentValues.put("message_type", Integer.valueOf(chatHistoryEntity.m4170d().m4337g().ordinal()));
        contentValues.put("message_time_stamp", chatHistoryEntity.m4170d().m4336f());
        contentValues.put("users_hash", chatHistoryEntity.m4175i());
        return contentValues;
    }

    public ChatHistoryEntity m4506a(Cursor cursor) {
        String string = cursor.getString(cursor.getColumnIndexOrThrow("broadcast_id"));
        String string2 = cursor.getString(cursor.getColumnIndexOrThrow("name"));
        String string3 = cursor.getString(cursor.getColumnIndexOrThrow("avatar_token"));
        String string4 = cursor.getString(cursor.getColumnIndexOrThrow("users_hash"));
        Builder builder = new Builder();
        builder.m4319b(0).m4320b("").m4322d("").m4313a(0).m4315a(Type.values()[cursor.getInt(cursor.getColumnIndexOrThrow("message_type"))]).m4321c(cursor.getString(cursor.getColumnIndexOrThrow("message_body"))).m4316a(cursor.getString(cursor.getColumnIndexOrThrow("message_time_stamp")));
        return new ChatHistoryEntity(builder.m4317a(), string, string2, string3, string4);
    }

    public void m4512b() {
        this.f2306b.delete("broadcast_list_table", null, null);
    }

    public List<ChatHistoryEntity> m4517c() {
        List<ChatHistoryEntity> arrayList = new ArrayList();
        Cursor query = this.f2306b.query("broadcast_list_table", null, null, null, null, null, "message_time_stamp");
        if (query == null) {
            return arrayList;
        }
        if (query.moveToFirst()) {
            do {
                arrayList.add(m4506a(query));
            } while (query.moveToNext());
            query.close();
            return arrayList;
        }
        query.close();
        return arrayList;
    }

    public void m4513b(ChatHistoryEntity chatHistoryEntity) {
        this.f2306b.insert("broadcast_list_table", null, m4505a(chatHistoryEntity));
    }

    public void m4510a(String str, String str2, Type type, String str3) {
        String[] strArr = new String[]{str};
        ContentValues contentValues = new ContentValues();
        contentValues.put("message_body", str2);
        contentValues.put("message_type", Integer.valueOf(type.ordinal()));
        contentValues.put("message_time_stamp", str3);
        this.f2306b.update("broadcast_list_table", contentValues, "broadcast_id=?", strArr);
    }

    public void m4509a(String str, String str2) {
        String[] strArr = new String[]{str};
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", str2);
        this.f2306b.update("broadcast_list_table", contentValues, "broadcast_id=?", strArr);
    }

    public void m4515b(String str, String str2) {
        String[] strArr = new String[]{str};
        ContentValues contentValues = new ContentValues();
        contentValues.put("avatar_token", str2);
        this.f2306b.update("broadcast_list_table", contentValues, "broadcast_id=?", strArr);
    }

    public void m4518c(String str, String str2) {
        String[] strArr = new String[]{str};
        ContentValues contentValues = new ContentValues();
        contentValues.put("users_hash", str2);
        this.f2306b.update("broadcast_list_table", contentValues, "broadcast_id=?", strArr);
    }

    public void m4511a(List<String> list) {
        this.f2306b.delete("broadcast_list_table", "broadcast_id IN (" + m4507a(list.size()) + ")", (String[]) list.toArray(new String[list.size()]));
    }

    public void m4516b(List<String> list) {
        if (list != null && !list.isEmpty()) {
            String str = "broadcast_id IN (" + m4507a(list.size()) + ")";
            String[] strArr = (String[]) list.toArray(new String[list.size()]);
            ContentValues contentValues = new ContentValues();
            contentValues.put("message_body", Main.f1927b.getString(2131296599));
            this.f2306b.update("broadcast_list_table", contentValues, str, strArr);
            for (String g : list) {
                NormalMessageManager.m7447a().m7472g(g).m4170d().m4330b(Main.f1927b.getString(2131296599));
            }
        }
    }

    public String m4508a(String str) {
        String str2 = null;
        String[] strArr = new String[]{str};
        Cursor query = this.f2306b.query("broadcast_list_table", null, "users_hash=?", strArr, null, null, null);
        if (query != null) {
            if (query.moveToFirst()) {
                str2 = m4506a(query).m4168c();
            }
            query.close();
        }
        return str2;
    }

    public void m4514b(String str) {
        JabberId a;
        if (str.contains("@")) {
            a = JabberId.m7386a(str);
        } else {
            a = new JabberId(str, "bisphone.com", null);
        }
        if (a != null) {
            String[] strArr = new String[]{"message_id", Text.ELEMENT, "timestamp", "contact_username", "type", Status.ELEMENT};
            String[] strArr2 = new String[]{a.m7387a(), DeliveryStatus.DRAFT.ordinal() + ""};
            Cursor query = this.f2306b.query("normal_message_table", strArr, "contact_username=? AND status!=?", strArr2, null, null, "timestamp DESC LIMIT 1");
            ContentValues contentValues;
            if (query == null || !query.moveToFirst()) {
                contentValues = new ContentValues();
                contentValues.put("message_body", "");
                this.f2306b.update("broadcast_list_table", contentValues, "broadcast_id=?", new String[]{a.m7387a()});
                if (query != null) {
                    query.close();
                    return;
                }
                return;
            }
            contentValues = new ContentValues();
            contentValues.put("message_body", query.getString(query.getColumnIndex(Text.ELEMENT)));
            this.f2306b.update("broadcast_list_table", contentValues, "broadcast_id=?", new String[]{a.m7387a()});
            query.close();
        }
    }

    String m4507a(int i) {
        int i2 = 1;
        if (i < 1) {
            throw new RuntimeException("No placeholders");
        }
        StringBuilder stringBuilder = new StringBuilder((i * 2) - 1);
        stringBuilder.append("?");
        while (i2 < i) {
            stringBuilder.append(",?");
            i2++;
        }
        return stringBuilder.toString();
    }
}
