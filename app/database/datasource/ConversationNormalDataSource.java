package app.database.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import app.Main;
import app.common.entity.ChatHistoryEntity;
import app.common.entity.ContactEntity;
import app.common.entity.ContactEntityLite;
import app.common.entity.ConversationNormalEntity;
import app.common.entity.ConversationNormalEntity.Builder;
import app.common.entity.HistoryEntity.DeliveryStatus;
import app.common.entity.HistoryEntity.Type;
import app.common.entity.HistoryNormalMessageEntity;
import app.database.DatabaseHelper;
import app.database.provider.ConversationNormalProvider;
import app.xmpp.JabberId;
import app.xmpp.NormalMessageManager;
import java.util.ArrayList;
import java.util.List;
import org.jivesoftware.smackx.muc.packet.MUCUser.Status;
import org.jivesoftware.smackx.xdatalayout.packet.DataLayout.Text;

public class ConversationNormalDataSource {
    private static ConversationNormalDataSource f2322c;
    DatabaseHelper f2323a;
    SQLiteDatabase f2324b;

    public static ConversationNormalDataSource m4623a() {
        ConversationNormalDataSource conversationNormalDataSource;
        synchronized (ConversationNormalDataSource.class) {
            if (f2322c == null) {
                f2322c = new ConversationNormalDataSource();
            }
            conversationNormalDataSource = f2322c;
        }
        return conversationNormalDataSource;
    }

    private ConversationNormalDataSource() {
        this.f2323a = DatabaseHelper.m4499a(Main.f1927b);
        this.f2324b = this.f2323a.getWritableDatabase();
    }

    public ContentValues m4625a(ConversationNormalEntity conversationNormalEntity) {
        ContentValues contentValues = new ContentValues();
        if (conversationNormalEntity.m4323a() != 0) {
            contentValues.put("_id", Long.valueOf(conversationNormalEntity.m4323a()));
        }
        contentValues.put("message_id", conversationNormalEntity.m4331c());
        contentValues.put("message_body", conversationNormalEntity.m4335e());
        contentValues.put("time_stamp", conversationNormalEntity.m4336f());
        contentValues.put("username", conversationNormalEntity.m4333d());
        contentValues.put("unread_count", Integer.valueOf(conversationNormalEntity.m4328b()));
        contentValues.put("unread_position", Integer.valueOf(conversationNormalEntity.m4339i()));
        contentValues.put("message_type", conversationNormalEntity.m4337g().toString());
        return contentValues;
    }

    public ContentValues m4626a(HistoryNormalMessageEntity historyNormalMessageEntity, long j) {
        ContentValues contentValues = new ContentValues();
        if (historyNormalMessageEntity.m4407a() != 0) {
            contentValues.put("_id", Long.valueOf(historyNormalMessageEntity.m4407a()));
        }
        contentValues.put("message_id", Long.valueOf(j));
        contentValues.put("message_body", historyNormalMessageEntity.m4416d());
        contentValues.put("time_stamp", historyNormalMessageEntity.m4419f());
        contentValues.put("username", historyNormalMessageEntity.m4456M());
        contentValues.put("message_type", historyNormalMessageEntity.m4418e().toString());
        return contentValues;
    }

    public ChatHistoryEntity m4627a(Cursor cursor) {
        Builder builder = new Builder();
        builder.m4319b(cursor.getLong(cursor.getColumnIndexOrThrow("_id"))).m4320b(cursor.getString(cursor.getColumnIndexOrThrow("message_id"))).m4322d(cursor.getString(cursor.getColumnIndexOrThrow("username"))).m4313a(cursor.getInt(cursor.getColumnIndexOrThrow("unread_count"))).m4318b(cursor.getInt(cursor.getColumnIndexOrThrow("unread_position"))).m4321c(cursor.getString(cursor.getColumnIndexOrThrow("message_body"))).m4315a(Type.m4442a(cursor.getString(cursor.getColumnIndexOrThrow("message_type")))).m4314a(cursor.getLong(cursor.getColumnIndexOrThrow("last_seen_timestamp"))).m4316a(cursor.getString(cursor.getColumnIndexOrThrow("time_stamp")));
        return new ChatHistoryEntity(builder.m4317a(), m4635b(cursor));
    }

    public ContactEntityLite m4635b(Cursor cursor) {
        return new ContactEntityLite.Builder().m4223a(ContactEntity.m4178a(cursor.getString(cursor.getColumnIndex("type")))).m4224a(cursor.getString(cursor.getColumnIndexOrThrow("contact_username"))).m4225a(cursor.getInt(cursor.getColumnIndexOrThrow("is_registered")) > 0).m4227b(cursor.getString(cursor.getColumnIndexOrThrow("contact_id"))).m4228c(cursor.getString(cursor.getColumnIndex("name"))).m4229d(cursor.getString(cursor.getColumnIndexOrThrow("photo_uri"))).m4230e(cursor.getString(cursor.getColumnIndexOrThrow("vcard_nickname"))).m4231f(cursor.getString(cursor.getColumnIndexOrThrow("vcard_avatar"))).m4226a();
    }

    public ContactEntityLite m4638c(Cursor cursor) {
        return new ContactEntityLite.Builder().m4223a(ContactEntity.m4178a(cursor.getString(cursor.getColumnIndex("contact_type")))).m4224a(cursor.getString(cursor.getColumnIndexOrThrow("contact_username"))).m4225a(cursor.getInt(cursor.getColumnIndexOrThrow("is_registered")) > 0).m4227b(cursor.getString(cursor.getColumnIndexOrThrow("contact_id"))).m4228c(cursor.getString(cursor.getColumnIndex("name"))).m4229d(cursor.getString(cursor.getColumnIndexOrThrow("photo_uri"))).m4230e(cursor.getString(cursor.getColumnIndexOrThrow("vcard_nickname"))).m4231f(cursor.getString(cursor.getColumnIndexOrThrow("vcard_avatar"))).m4226a();
    }

    public void m4636b(ConversationNormalEntity conversationNormalEntity) {
        ChatHistoryEntity f = NormalMessageManager.m7447a().m7470f(conversationNormalEntity.m4333d());
        if (f != null) {
            conversationNormalEntity.m4324a(f.m4172f());
            conversationNormalEntity.m4329b(f.m4170d().m4339i());
            String[] strArr = new String[]{conversationNormalEntity.m4333d()};
            Main.f1927b.getContentResolver().update(ConversationNormalProvider.f2373a, m4625a(conversationNormalEntity), "username=?", strArr);
            return;
        }
        Main.f1927b.getContentResolver().insert(ConversationNormalProvider.f2373a, m4625a(conversationNormalEntity));
    }

    public List<ChatHistoryEntity> m4629a(Context context) {
        List<ChatHistoryEntity> arrayList = new ArrayList();
        Cursor query = context.getContentResolver().query(ConversationNormalProvider.f2373a, null, null, null, "time_stamp");
        if (query == null) {
            return arrayList;
        }
        if (query.moveToFirst()) {
            do {
                arrayList.add(m4627a(query));
            } while (query.moveToNext());
            query.close();
            return arrayList;
        }
        query.close();
        return arrayList;
    }

    public void m4632a(String str, long j) {
        ContentValues contentValues = new ContentValues(1);
        contentValues.put("unread_count", Integer.valueOf(0));
        contentValues.put("unread_position", Integer.valueOf(0));
        contentValues.put("last_seen_timestamp", Long.valueOf(j));
        String[] strArr = new String[]{str};
        Main.f1927b.getContentResolver().update(ConversationNormalProvider.f2373a, contentValues, "username=?", strArr);
    }

    public void m4630a(String str) {
        JabberId a;
        if (str.contains("@")) {
            a = JabberId.m7386a(str);
        } else {
            a = new JabberId(str, "bisphone.com", null);
        }
        if (a != null) {
            String[] strArr = new String[]{"message_id", Text.ELEMENT, "timestamp", "contact_username", "type", Status.ELEMENT};
            String[] strArr2 = new String[]{a.m7387a(), DeliveryStatus.DRAFT.ordinal() + ""};
            String str2 = "username=?";
            String[] strArr3 = new String[]{a.m7387a()};
            Cursor query = this.f2324b.query("normal_message_table", strArr, "contact_username=? AND status!=?", strArr2, null, null, "timestamp DESC LIMIT 1");
            if (query == null) {
                Main.f1927b.getContentResolver().delete(ConversationNormalProvider.f2373a, str2, strArr3);
                return;
            }
            if (query.getCount() <= 0 || !query.moveToFirst()) {
                Main.f1927b.getContentResolver().delete(ConversationNormalProvider.f2373a, str2, strArr3);
            } else {
                Main.f1927b.getContentResolver().update(ConversationNormalProvider.f2373a, m4625a(m4624d(query)), "username=?", new String[]{a.m7387a()});
            }
            query.close();
        }
    }

    private ConversationNormalEntity m4624d(Cursor cursor) {
        Builder builder = new Builder();
        builder.m4320b(cursor.getString(cursor.getColumnIndexOrThrow("message_id"))).m4321c(cursor.getString(cursor.getColumnIndexOrThrow(Text.ELEMENT))).m4316a(cursor.getString(cursor.getColumnIndexOrThrow("timestamp"))).m4313a(0).m4318b(0).m4315a(Type.m4442a(cursor.getString(cursor.getColumnIndexOrThrow("type")))).m4322d(cursor.getString(cursor.getColumnIndex("contact_username")));
        return builder.m4317a();
    }

    public int m4634b() {
        Cursor query = Main.f1927b.getContentResolver().query(ConversationNormalProvider.f2374b, null, null, null, null);
        if (!query.moveToFirst()) {
            return 0;
        }
        int i = query.getInt(0);
        query.close();
        return i;
    }

    public void m4637b(String str) {
        String[] strArr = new String[]{str};
        Main.f1927b.getContentResolver().delete(ConversationNormalProvider.f2373a, "username=?", strArr);
    }

    public void m4633a(ArrayList<String> arrayList) {
        Main.f1927b.getContentResolver().delete(ConversationNormalProvider.f2373a, "username IN (" + m4628a(arrayList.size()) + ")", (String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    String m4628a(int i) {
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

    public void m4631a(String str, int i) {
        String[] strArr = new String[]{i + "", str};
        this.f2324b.rawQuery("UPDATE normal_conversation_table SET unread_position=unread_position+? WHERE username=?", strArr);
    }
}
