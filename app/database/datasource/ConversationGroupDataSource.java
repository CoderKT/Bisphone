package app.database.datasource;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import app.Main;
import app.account.AccountManager;
import app.common.entity.ConversationGroupEntity;
import app.common.entity.ConversationGroupEntity.Builder;
import app.common.entity.ConversationGroupEntity.GroupInfoState;
import app.common.entity.HistoryEntity.DeliveryStatus;
import app.common.entity.HistoryGroupEntity;
import app.database.DatabaseHelper;
import app.database.provider.ConversationGroupProvider;
import app.xmpp.GroupManager;
import app.xmpp.packet.groupv2.GroupElement;
import app.xmpp.packet.groupv2.GroupElement.Type;
import app.xmpp.packet.groupv2.GroupStatisticAckXE;
import app.xmpp.packet.groupv2.SettingXE.NotificationState;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.jivesoftware.smackx.muc.packet.MUCUser.Status;
import org.jivesoftware.smackx.xdatalayout.packet.DataLayout.Text;
import org.json.JSONException;
import org.json.JSONObject;

public class ConversationGroupDataSource {
    private static ConversationGroupDataSource f2319c;
    DatabaseHelper f2320a;
    SQLiteDatabase f2321b;

    public static ConversationGroupDataSource m4587a() {
        if (f2319c == null) {
            synchronized (ConversationGroupDataSource.class) {
                if (f2319c == null) {
                    f2319c = new ConversationGroupDataSource();
                }
            }
        }
        return f2319c;
    }

    private ConversationGroupDataSource() {
        this.f2320a = DatabaseHelper.m4499a(Main.f1927b);
        this.f2321b = this.f2320a.getWritableDatabase();
    }

    public ArrayList<String> m4592a(Context context) {
        String[] strArr = new String[]{"JOINED"};
        Cursor query = this.f2321b.query("group_conversation_table", null, "group_state=?", strArr, null, null, null);
        ArrayList<String> arrayList = new ArrayList(query.getCount());
        if (query.moveToFirst()) {
            do {
                arrayList.add(query.getString(query.getColumnIndexOrThrow("group_jid")));
            } while (query.moveToNext());
        }
        query.close();
        return arrayList;
    }

    public String m4591a(Context context, String str) {
        String str2 = null;
        String[] strArr = new String[]{str};
        String[] strArr2 = new String[]{"name"};
        Cursor query = this.f2321b.query("group_conversation_table", strArr2, "group_jid=?", strArr, null, null, null);
        if (query.moveToFirst()) {
            str2 = query.getString(query.getColumnIndex("name"));
        }
        query.close();
        return str2;
    }

    public ConversationGroupEntity m4609b(Context context, String str) {
        ConversationGroupEntity conversationGroupEntity = null;
        if (str != null) {
            String[] strArr = new String[]{str};
            Cursor query = this.f2321b.query("group_conversation_table", null, "group_jid=?", strArr, null, null, null);
            if (query.moveToFirst()) {
                conversationGroupEntity = m4590a(query);
            }
            query.close();
        }
        return conversationGroupEntity;
    }

    public ConversationGroupEntity m4608b() {
        ConversationGroupEntity conversationGroupEntity = null;
        String[] strArr = new String[]{"CREATE_DRAFT"};
        Cursor query = this.f2321b.query("group_conversation_table", null, "group_state=?", strArr, null, null, null);
        if (query.moveToFirst()) {
            conversationGroupEntity = m4590a(query);
        }
        query.close();
        return conversationGroupEntity;
    }

    public long m4589a(Context context, ConversationGroupEntity conversationGroupEntity) {
        return ContentUris.parseId(context.getContentResolver().insert(ConversationGroupProvider.f2370c, m4585a(conversationGroupEntity)));
    }

    public void m4610b(Context context, ConversationGroupEntity conversationGroupEntity) {
        String[] strArr = new String[]{conversationGroupEntity.m4274b()};
        ContentValues a = m4585a(conversationGroupEntity);
        context.getContentResolver().update(ConversationGroupProvider.f2368a, a, "group_jid=?", strArr);
    }

    public void m4595a(Context context, String str, String str2, long j) {
        ContentValues contentValues = new ContentValues(2);
        contentValues.put("group_state", str2);
        contentValues.put("unread_count", Integer.valueOf(0));
        contentValues.put("unread_position", Integer.valueOf(0));
        if (j > 0) {
            contentValues.put("message_time_stamp", Long.valueOf(j));
            contentValues.put("time_stamp", Long.valueOf(j));
        }
        context.getContentResolver().update(ConversationGroupProvider.f2368a, contentValues, "group_jid=?", new String[]{str});
    }

    public void m4594a(Context context, String str, long j) {
        ContentValues contentValues = new ContentValues(2);
        contentValues.put("group_state", "JOINED");
        contentValues.put("unread_count", Integer.valueOf(0));
        contentValues.put("unread_position", Integer.valueOf(0));
        if (j > 0) {
            contentValues.put("time_stamp", Long.valueOf(j));
            contentValues.put("message_time_stamp", Long.valueOf(j));
        }
        contentValues.put("group_jid", str);
        contentValues.put("message_id", "-1");
        context.getContentResolver().update(ConversationGroupProvider.f2368a, contentValues, "group_jid=?", new String[]{str});
    }

    public void m4611b(Context context, String str, long j) {
        ContentValues contentValues = new ContentValues(2);
        contentValues.put("group_state", "JOINED");
        contentValues.put("unread_count", Integer.valueOf(0));
        contentValues.put("unread_position", Integer.valueOf(0));
        contentValues.put("time_stamp", Long.valueOf(j));
        contentValues.put("message_time_stamp", Long.valueOf(j));
        contentValues.put("group_jid", str);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("all_invited_count", 0);
            jSONObject.put("member_count", 1);
            jSONObject.put("group_size", 200);
            jSONObject.put("user_invited_count", 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        contentValues.put("extra_data", jSONObject.toString());
        context.getContentResolver().update(ConversationGroupProvider.f2368a, contentValues, "group_jid=?", new String[]{str.substring(0, str.indexOf(64))});
    }

    public void m4593a(Context context, NotificationState notificationState, String str) {
        ContentValues contentValues = new ContentValues(1);
        contentValues.put("is_mute", Integer.valueOf(notificationState.ordinal()));
        context.getContentResolver().update(ConversationGroupProvider.f2368a, contentValues, "group_jid=?", new String[]{str + ""});
    }

    public void m4604a(String str, GroupStatisticAckXE groupStatisticAckXE) {
        Builder builder = new Builder();
        builder.m4251c(groupStatisticAckXE.m7619a());
        builder.m4253d(groupStatisticAckXE.m7621b());
        builder.m4255e(groupStatisticAckXE.m7623c());
        builder.m4257f(groupStatisticAckXE.m7625d());
        ContentValues contentValues = new ContentValues();
        contentValues.put("extra_data", builder.m4248a().m4308t().toString());
        this.f2321b.update("group_conversation_table", contentValues, "group_jid=?", new String[]{str});
    }

    public void m4613c(Context context, String str) {
        context.getContentResolver().delete(ConversationGroupProvider.f2368a, "group_jid=?", new String[]{str + ""});
    }

    private static ContentValues m4585a(ConversationGroupEntity conversationGroupEntity) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("group_jid", conversationGroupEntity.m4274b());
        contentValues.put("unread_count", Integer.valueOf(conversationGroupEntity.m4277c()));
        contentValues.put("group_state", conversationGroupEntity.m4283e());
        contentValues.put("name", conversationGroupEntity.m4286f());
        contentValues.put("avatar_token", conversationGroupEntity.m4289g());
        contentValues.put("time_stamp", conversationGroupEntity.m4297k());
        contentValues.put("is_mute", Integer.valueOf(conversationGroupEntity.m4295j().ordinal()));
        contentValues.put("message_username", conversationGroupEntity.m4293i());
        contentValues.put("message_body", conversationGroupEntity.m4291h());
        contentValues.put("message_id", conversationGroupEntity.m4280d());
        contentValues.put("message_time_stamp", conversationGroupEntity.m4299l());
        contentValues.put("group_type", Integer.valueOf(conversationGroupEntity.m4302n().ordinal()));
        contentValues.put("group_description", conversationGroupEntity.m4301m());
        contentValues.put("group_admin", conversationGroupEntity.m4304p());
        contentValues.put("group_info", Integer.valueOf(conversationGroupEntity.m4303o().ordinal()));
        contentValues.put("unread_position", Integer.valueOf(conversationGroupEntity.m4306r()));
        contentValues.put("last_seen_timestamp", Long.valueOf(conversationGroupEntity.m4307s()));
        contentValues.put("extra_data", conversationGroupEntity.m4308t() == null ? "" : conversationGroupEntity.m4308t().toString());
        return contentValues;
    }

    public ConversationGroupEntity m4590a(Cursor cursor) {
        Builder builder = new Builder();
        builder.m4252c(cursor.getString(cursor.getColumnIndexOrThrow("_id"))).m4259g(cursor.getString(cursor.getColumnIndexOrThrow("group_jid"))).m4242a(cursor.getInt(cursor.getColumnIndex("unread_count"))).m4254d(cursor.getString(cursor.getColumnIndex("message_id"))).m4260h(cursor.getString(cursor.getColumnIndexOrThrow("group_state"))).m4262j(cursor.getString(cursor.getColumnIndexOrThrow("name"))).m4263k(cursor.getString(cursor.getColumnIndexOrThrow("avatar_token"))).m4247a(cursor.getString(cursor.getColumnIndexOrThrow("time_stamp"))).m4256e(cursor.getString(cursor.getColumnIndex("message_body"))).m4250b(cursor.getString(cursor.getColumnIndex("message_time_stamp"))).m4258f(cursor.getString(cursor.getColumnIndex("message_username"))).m4261i(cursor.getString(cursor.getColumnIndex("group_description"))).m4245a(Type.values()[cursor.getInt(cursor.getColumnIndex("group_type"))]).m4244a(GroupInfoState.values()[cursor.getInt(cursor.getColumnIndex("group_info"))]).m4246a(NotificationState.values()[cursor.getInt(cursor.getColumnIndexOrThrow("is_mute"))]).m4264l(cursor.getString(cursor.getColumnIndex("group_admin"))).m4265m(cursor.getString(cursor.getColumnIndex("group_info"))).m4266n(cursor.getString(cursor.getColumnIndex("extra_data"))).m4249b(cursor.getInt(cursor.getColumnIndex("unread_position"))).m4243a(cursor.getLong(cursor.getColumnIndex("last_seen_timestamp")));
        return builder.m4248a();
    }

    public HashMap<String, Long> m4612c() {
        String[] strArr = new String[]{"JOINED"};
        String[] strArr2 = new String[]{"group_jid", "time_stamp"};
        Cursor query = this.f2321b.query("group_conversation_table", strArr2, "group_state=?", strArr, null, null, null);
        HashMap<String, Long> hashMap = new HashMap(query.getCount());
        if (query.moveToFirst()) {
            do {
                hashMap.put(query.getString(0), Long.valueOf(query.getLong(1)));
            } while (query.moveToNext());
        }
        query.close();
        return hashMap;
    }

    public void m4601a(String str, long j) {
        ContentValues contentValues = new ContentValues(1);
        contentValues.put("unread_count", Integer.valueOf(0));
        contentValues.put("unread_position", Integer.valueOf(0));
        contentValues.put("last_seen_timestamp", Long.valueOf(j));
        String[] strArr = new String[]{str};
        Main.f1927b.getContentResolver().update(ConversationGroupProvider.f2368a, contentValues, "group_jid=?", strArr);
    }

    public void m4616d() {
        ContentValues contentValues = new ContentValues(1);
        contentValues.put("unread_count", Integer.valueOf(0));
        contentValues.put("unread_position", Integer.valueOf(0));
        Main.f1927b.getContentResolver().update(ConversationGroupProvider.f2368a, contentValues, null, null);
    }

    public HashMap<String, NotificationState> m4617e() {
        Cursor query = this.f2321b.query("group_conversation_table", new String[]{"group_jid", "is_mute"}, null, null, null, null, null);
        HashMap<String, NotificationState> hashMap = new HashMap(query.getCount());
        if (query.moveToFirst()) {
            do {
                hashMap.put(query.getString(query.getColumnIndex("group_jid")), NotificationState.values()[query.getInt(query.getColumnIndex("is_mute"))]);
            } while (query.moveToNext());
        }
        query.close();
        return hashMap;
    }

    public void m4599a(String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("unread_count", Integer.valueOf(0));
        contentValues.put("unread_position", Integer.valueOf(0));
        contentValues.put("message_id", Integer.valueOf(-1));
        String[] strArr = new String[]{str};
        Main.f1927b.getContentResolver().update(ConversationGroupProvider.f2368a, contentValues, "group_jid=?", strArr);
    }

    public int m4607b(String str) {
        if (GroupManager.m7323a().m7358b(str) != null) {
            return GroupManager.m7323a().m7358b(str).m4306r();
        }
        String[] strArr = new String[]{str};
        Cursor query = this.f2321b.query("group_conversation_table", new String[]{"unread_position"}, "group_jid =?", strArr, null, null, null);
        if (query == null) {
            return 0;
        }
        int i;
        if (query.moveToFirst()) {
            i = query.getInt(query.getColumnIndex("unread_position"));
        } else {
            i = 0;
        }
        query.close();
        return i;
    }

    public void m4598a(HistoryGroupEntity historyGroupEntity, long j) {
        ConversationGroupEntity b = GroupManager.m7323a().m7358b(historyGroupEntity.m4414c());
        if (historyGroupEntity.m4449M().equals(AccountManager.m3934a().m3937c())) {
            b.m4288f(historyGroupEntity.m4416d());
            b.m4294i((System.currentTimeMillis() * 1000) + "");
            b.m4290g(historyGroupEntity.m4449M());
            b.m4276b(j + "");
        } else {
            b.m4268a(b.m4277c() + 1);
        }
        if (Long.parseLong(b.m4297k()) < Long.parseLong(historyGroupEntity.m4419f()) && !historyGroupEntity.m4449M().equals(AccountManager.m3934a().m3937c())) {
            b.m4292h(historyGroupEntity.m4419f());
            b.m4294i(historyGroupEntity.m4419f());
            b.m4288f(historyGroupEntity.m4416d());
            b.m4276b(j + "");
            b.m4290g(historyGroupEntity.m4449M());
        }
        Main.f1927b.getContentResolver().insert(ConversationGroupProvider.f2368a, m4585a(b));
    }

    public void m4614c(String str) {
        ConversationGroupEntity conversationGroupEntity = null;
        String str2 = "group_jid=?";
        String[] strArr = new String[]{str};
        Cursor query = this.f2321b.query("group_message_table", new String[]{"message_id", Text.ELEMENT, "jabber_id", "timestamp", Status.ELEMENT, "contact_username"}, "jabber_id=? AND status!=?", new String[]{str, DeliveryStatus.DRAFT + ""}, null, null, "timestamp DESC LIMIT 1");
        if (query != null) {
            if (query.getCount() > 0 && query.moveToFirst()) {
                conversationGroupEntity = m4588b(query);
            }
            query.close();
        }
        Main.f1927b.getContentResolver().update(ConversationGroupProvider.f2368a, m4586a(conversationGroupEntity, conversationGroupEntity == null ? "-1" : conversationGroupEntity.m4280d(), false), str2, strArr);
    }

    private ContentValues m4586a(ConversationGroupEntity conversationGroupEntity, String str, boolean z) {
        String str2 = null;
        ContentValues contentValues = new ContentValues();
        contentValues.put("message_body", conversationGroupEntity == null ? null : conversationGroupEntity.m4291h());
        contentValues.put("unread_count", Integer.valueOf(1));
        contentValues.put("unread_position", Integer.valueOf(1));
        contentValues.put("message_id", str);
        String str3 = "message_username";
        if (conversationGroupEntity != null) {
            str2 = conversationGroupEntity.m4293i();
        }
        contentValues.put(str3, str2);
        if (conversationGroupEntity != null) {
            contentValues.put("message_time_stamp", conversationGroupEntity.m4299l());
        }
        if (z) {
            contentValues.put("time_stamp", conversationGroupEntity == null ? (System.currentTimeMillis() * 1000) + "" : conversationGroupEntity.m4297k());
        }
        return contentValues;
    }

    private ConversationGroupEntity m4588b(Cursor cursor) {
        Builder builder = new Builder();
        builder.m4259g(cursor.getString(cursor.getColumnIndexOrThrow("jabber_id"))).m4254d(cursor.getString(cursor.getColumnIndex("message_id"))).m4260h("JOINED").m4247a(cursor.getString(cursor.getColumnIndexOrThrow("timestamp"))).m4250b(cursor.getString(cursor.getColumnIndexOrThrow("timestamp"))).m4256e(cursor.getString(cursor.getColumnIndex(Text.ELEMENT))).m4258f(cursor.getString(cursor.getColumnIndex("contact_username")));
        return builder.m4248a();
    }

    public int m4619f() {
        int i;
        Cursor query = Main.f1927b.getContentResolver().query(ConversationGroupProvider.f2369b, null, null, null, null);
        if (query == null || !query.moveToFirst()) {
            i = 0;
        } else {
            i = query.getInt(0) + 0;
        }
        if (query != null) {
            query.close();
        }
        return i;
    }

    public void m4620g() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("unread_count", Integer.valueOf(0));
        contentValues.put("unread_position", Integer.valueOf(0));
        contentValues.put("message_id", Integer.valueOf(-1));
        contentValues.put("time_stamp", Long.valueOf(System.currentTimeMillis() * 1000));
        Main.f1927b.getContentResolver().update(ConversationGroupProvider.f2368a, contentValues, null, null);
    }

    public void m4603a(String str, GroupElement groupElement, GroupInfoState groupInfoState) {
        String[] strArr = new String[]{str};
        ContentValues contentValues = new ContentValues();
        contentValues.put("group_type", Integer.valueOf(groupElement.m7609b().ordinal()));
        contentValues.put("avatar_token", groupElement.m7617f());
        contentValues.put("group_description", groupElement.m7613d());
        contentValues.put("name", groupElement.m7611c());
        contentValues.put("group_info", Integer.valueOf(groupInfoState.ordinal()));
        this.f2321b.update("group_conversation_table", contentValues, "group_jid=?", strArr);
    }

    public void m4605a(List<ConversationGroupEntity> list) {
        if (list != null && list.size() != 0) {
            List arrayList = new ArrayList();
            for (ConversationGroupEntity a : list) {
                ContentValues a2 = m4585a(a);
                a2.put("message_id", Integer.valueOf(-1));
                a2.put("unread_count", Integer.valueOf(0));
                a2.put("unread_position", Integer.valueOf(0));
                arrayList.add(a2);
            }
            Main.f1927b.getContentResolver().bulkInsert(ConversationGroupProvider.f2368a, (ContentValues[]) arrayList.toArray(new ContentValues[arrayList.size()]));
        }
    }

    public ConversationGroupEntity m4615d(String str) {
        String[] strArr = new String[]{str, GroupInfoState.NONE.ordinal() + ""};
        Cursor query = this.f2321b.query("group_conversation_table", null, "group_jid=? AND group_info!=?", strArr, null, null, null);
        if (query == null) {
            return null;
        }
        if (query.moveToFirst()) {
            ConversationGroupEntity a = m4590a(query);
            query.close();
            return a;
        }
        query.close();
        return null;
    }

    public List<ConversationGroupEntity> m4621h() {
        List<ConversationGroupEntity> arrayList = new ArrayList();
        Cursor query = this.f2321b.query("group_conversation_table", null, null, null, null, null, null);
        if (query == null) {
            return arrayList;
        }
        if (query.moveToFirst()) {
            do {
                arrayList.add(m4590a(query));
            } while (query.moveToNext());
            query.close();
            return arrayList;
        }
        query.close();
        return arrayList;
    }

    public void m4618e(String str) {
        String[] strArr = new String[]{"CREATE_DRAFT"};
        ContentValues contentValues = new ContentValues();
        contentValues.put("group_jid", str);
        this.f2321b.update("group_conversation_table", contentValues, "group_state=?", strArr);
    }

    public void m4597a(HistoryGroupEntity historyGroupEntity, int i, int i2) {
        ConversationGroupEntity b = GroupManager.m7323a().m7358b(historyGroupEntity.m4414c());
        if (b != null) {
            ContentValues a = m4585a(b);
            a.put("unread_count", Integer.valueOf(i));
            a.put("unread_position", Integer.valueOf(i2));
            String str = "group_jid=?";
            String[] strArr = new String[]{b.m4274b()};
            a.put("time_stamp", Long.valueOf(Math.max(Long.parseLong(historyGroupEntity.m4419f()), Long.parseLong(b.m4297k()))));
            a.put("message_time_stamp", Long.valueOf(Math.max(Long.parseLong(historyGroupEntity.m4419f()), Long.parseLong(b.m4299l()))));
            if (Long.parseLong(historyGroupEntity.m4419f()) > Long.parseLong(b.m4297k())) {
                a.put("message_body", historyGroupEntity.m4416d());
                a.put("message_username", historyGroupEntity.m4449M());
                a.put("message_id", Long.valueOf(historyGroupEntity.m4407a()));
            }
            Main.f1927b.getContentResolver().update(ConversationGroupProvider.f2368a, a, str, strArr);
        }
    }

    public void m4622i() {
        String[] strArr = new String[]{"INVITED"};
        Main.f1927b.getContentResolver().delete(ConversationGroupProvider.f2368a, "group_state=?", strArr);
    }

    public void m4596a(ConversationGroupEntity conversationGroupEntity, String str) {
        ConversationGroupEntity a = GroupManager.m7323a().m7339a(str, false);
        if (a == null) {
            a = GroupManager.m7323a().m7339a(conversationGroupEntity.m4274b(), false);
        }
        if (a == null) {
            conversationGroupEntity.m4268a(0);
            conversationGroupEntity.m4276b("-1");
            Main.f1927b.getContentResolver().insert(ConversationGroupProvider.f2368a, m4585a(conversationGroupEntity));
            return;
        }
        String str2 = "group_jid=?";
        String[] strArr = new String[]{str};
        if (a.m4297k() == null) {
            a.m4292h((System.currentTimeMillis() * 1000) + "");
        } else if (a.m4297k().length() < 16) {
            a.m4292h((Long.parseLong(a.m4297k()) * 1000) + "");
        }
        if (a.m4299l() == null) {
            a.m4294i((System.currentTimeMillis() * 1000) + "");
        } else if (a.m4299l().length() < 16) {
            a.m4294i((Long.parseLong(a.m4299l()) * 1000) + "");
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("group_jid", conversationGroupEntity.m4274b());
        contentValues.put("group_admin", conversationGroupEntity.m4304p());
        contentValues.put("group_info", Integer.valueOf(conversationGroupEntity.m4303o().ordinal()));
        contentValues.put("time_stamp", Long.valueOf(Math.min(Long.parseLong(conversationGroupEntity.m4297k()), Long.parseLong(a.m4297k()))));
        contentValues.put("message_time_stamp", Long.valueOf(Math.min(Long.parseLong(conversationGroupEntity.m4299l()), Long.parseLong(a.m4299l()))));
        contentValues.put("group_description", conversationGroupEntity.m4301m());
        contentValues.put("group_type", Integer.valueOf(conversationGroupEntity.m4302n().ordinal()));
        contentValues.put("is_mute", Integer.valueOf(conversationGroupEntity.m4295j().ordinal()));
        contentValues.put("avatar_token", conversationGroupEntity.m4289g());
        contentValues.put("name", conversationGroupEntity.m4286f());
        contentValues.put("group_state", conversationGroupEntity.m4283e());
        Main.f1927b.getContentResolver().update(ConversationGroupProvider.f2368a, contentValues, str2, strArr);
    }

    public void m4602a(String str, Type type) {
        String[] strArr = new String[]{str};
        ContentValues contentValues = new ContentValues();
        contentValues.put("group_type", Integer.valueOf(type.ordinal()));
        this.f2321b.update("group_conversation_table", contentValues, "group_jid=?", strArr);
    }

    public void m4606a(Set<String> set) {
        String[] strArr = new String[]{"CREATE_DRAFT", "INVITED", "JOINED"};
        this.f2321b.delete("group_conversation_table", "group_state!=? AND group_state!=? AND group_state!=?", strArr);
        if (set != null && set.size() != 0) {
            String str = "";
            for (String str2 : set) {
                str = str + "group_jid='" + str2 + "' OR ";
            }
            this.f2321b.delete("group_conversation_table", str.substring(0, str.length() - 3), null);
        }
    }

    public void m4600a(String str, int i) {
        String[] strArr = new String[]{i + "", str};
        this.f2321b.rawQuery("UPDATE group_conversation_table SET unread_position=unread_position+? WHERE group_jid=?", strArr);
    }
}
