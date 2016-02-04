package app.database.datasource;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import app.Main;
import app.account.AccountManager;
import app.common.entity.ConversationGroupEntity;
import app.common.entity.HistoryEntity;
import app.common.entity.HistoryEntity.DeliveryStatus;
import app.common.entity.HistoryEntity.Type;
import app.common.entity.HistoryGroupEntity;
import app.common.entity.HistoryGroupEntity.Builder;
import app.database.DatabaseHelper;
import app.database.provider.ConversationGroupProvider;
import app.database.provider.HistoryGroupProvider;
import app.galley.MessagePreview;
import app.galley.internal.ImagePreviewModel;
import app.messaging.LoadEarlier;
import app.messaging.RecycleMessagingActivity.LoadDirection;
import app.storage.StorageException;
import app.util.StringUtil;
import app.util.Utils;
import app.xmpp.GroupManager;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import org.jivesoftware.smack.tcp.XMPPTCPConnection.PacketWriter;
import org.jivesoftware.smackx.muc.packet.MUCUser.Status;
import org.jivesoftware.smackx.xdatalayout.packet.DataLayout.Text;
import org.json.JSONException;
import org.json.JSONObject;

public class HistoryGroupDataSource {
    private static HistoryGroupDataSource f2346c;
    DatabaseHelper f2347a;
    SQLiteDatabase f2348b;

    /* renamed from: app.database.datasource.HistoryGroupDataSource.1 */
    class C01341 extends ArrayList<String> {
        final /* synthetic */ String f2341a;
        final /* synthetic */ HistoryGroupDataSource f2342b;

        C01341(HistoryGroupDataSource historyGroupDataSource, String str) {
            this.f2342b = historyGroupDataSource;
            this.f2341a = str;
            add(this.f2341a);
            add(DeliveryStatus.DRAFT.ordinal() + "");
        }
    }

    /* renamed from: app.database.datasource.HistoryGroupDataSource.2 */
    class C01352 extends ArrayList<String> {
        final /* synthetic */ String f2343a;
        final /* synthetic */ long f2344b;
        final /* synthetic */ HistoryGroupDataSource f2345c;

        C01352(HistoryGroupDataSource historyGroupDataSource, String str, long j) {
            this.f2345c = historyGroupDataSource;
            this.f2343a = str;
            this.f2344b = j;
            add(this.f2343a);
            add(DeliveryStatus.DRAFT.ordinal() + "");
            add(this.f2344b + "");
        }
    }

    public static HistoryGroupDataSource m4691a() {
        if (f2346c == null) {
            synchronized (HistoryGroupDataSource.class) {
                if (f2346c == null) {
                    f2346c = new HistoryGroupDataSource();
                }
            }
        }
        return f2346c;
    }

    private HistoryGroupDataSource() {
        this.f2347a = DatabaseHelper.m4499a(Main.f1927b);
        this.f2348b = this.f2347a.getWritableDatabase();
    }

    public static ContentValues m4689a(HistoryGroupEntity historyGroupEntity) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("message_id", historyGroupEntity.m4412b());
        contentValues.put(Text.ELEMENT, historyGroupEntity.m4416d());
        contentValues.put("jabber_id", historyGroupEntity.m4414c());
        contentValues.put("contact_username", historyGroupEntity.m4449M());
        contentValues.put("type", Integer.valueOf(historyGroupEntity.m4418e().ordinal()));
        contentValues.put("timestamp", historyGroupEntity.m4419f());
        contentValues.put(Status.ELEMENT, Integer.valueOf(historyGroupEntity.m4420g().ordinal()));
        contentValues.put("thumbnail", historyGroupEntity.m4423j());
        contentValues.put("extra_data", historyGroupEntity.m4424k().toString());
        return contentValues;
    }

    public static HistoryGroupEntity m4690a(Cursor cursor) {
        Builder builder = new Builder();
        builder.m4351a(cursor.getLong(cursor.getColumnIndexOrThrow("_id"))).m4359a(cursor.getString(cursor.getColumnIndexOrThrow("message_id"))).m4365b(cursor.getString(cursor.getColumnIndexOrThrow("jabber_id"))).m4369c(cursor.getString(cursor.getColumnIndexOrThrow(Text.ELEMENT))).m4356a(Type.values()[cursor.getInt(cursor.getColumnIndexOrThrow("type"))]).m4372d(cursor.getString(cursor.getColumnIndexOrThrow("timestamp"))).m4353a(DeliveryStatus.values()[cursor.getInt(cursor.getColumnIndexOrThrow(Status.ELEMENT))]).m4376e(cursor.getString(cursor.getColumnIndexOrThrow("thumbnail"))).m4378f(cursor.getString(cursor.getColumnIndexOrThrow("extra_data")));
        builder.m4447t(cursor.getString(cursor.getColumnIndexOrThrow("contact_username")));
        return builder.m4445a();
    }

    public HistoryGroupEntity m4704a(String str) {
        Cursor query = Main.f1927b.getContentResolver().query(HistoryGroupProvider.f2380a, null, "message_id=?", new String[]{str}, null);
        if (query.moveToFirst()) {
            return m4690a(query);
        }
        return null;
    }

    public static List<HistoryGroupEntity> m4694a(Context context) {
        List<HistoryGroupEntity> list = null;
        Cursor query = context.getContentResolver().query(HistoryGroupProvider.f2380a, null, "status=? OR status=?", new String[]{DeliveryStatus.FAILED_TO_SEND.ordinal() + "", DeliveryStatus.SENDING.ordinal() + ""}, null);
        if (query.moveToFirst()) {
            list = new ArrayList(query.getCount());
            do {
                list.add(m4690a(query));
            } while (query.moveToNext());
            query.close();
        }
        return list;
    }

    public static int m4697b(Context context) {
        ContentValues contentValues = new ContentValues(1);
        contentValues.put(Status.ELEMENT, Integer.valueOf(DeliveryStatus.FAILED_TO_UPLOAD.ordinal()));
        return context.getContentResolver().update(HistoryGroupProvider.f2380a, contentValues, "status=?", new String[]{DeliveryStatus.UPLOADING.toString()});
    }

    public static void m4695a(Context context, String[] strArr) {
        ContentResolver contentResolver = context.getContentResolver();
        int i;
        if (strArr.length < PacketWriter.QUEUE_SIZE) {
            String str = "";
            for (i = 0; i < strArr.length; i++) {
                str = str + "message_id=?";
                if (i < strArr.length - 1) {
                    str = str + " OR ";
                }
            }
            contentResolver.delete(HistoryGroupProvider.f2380a, str, strArr);
            return;
        }
        for (i = 0; i * PacketWriter.QUEUE_SIZE < strArr.length; i++) {
            int length = strArr.length > (i + 1) * PacketWriter.QUEUE_SIZE ? (i + 1) * PacketWriter.QUEUE_SIZE : strArr.length;
            List arrayList = new ArrayList();
            String str2 = "";
            for (int i2 = i * PacketWriter.QUEUE_SIZE; i2 < length; i2++) {
                arrayList.add(strArr[i2]);
                str2 = str2 + "message_id=?";
                if (i2 < length - 1) {
                    str2 = str2 + " OR ";
                }
            }
            contentResolver.delete(HistoryGroupProvider.f2380a, str2, (String[]) arrayList.toArray(new String[arrayList.size()]));
        }
    }

    public static ImagePreviewModel m4692a(String str, String str2, long j) {
        Throwable e;
        int i = 0;
        ImagePreviewModel imagePreviewModel = new ImagePreviewModel();
        String[] strArr = new String[]{str, Type.PHOTO.ordinal() + "", j + ""};
        Cursor query = Main.f1927b.getContentResolver().query(HistoryGroupProvider.f2380a, new String[]{"extra_data", "_id", "message_id", "thumbnail"}, "jabber_id=? AND type=? AND timestamp >=?", strArr, "timestamp");
        ArrayList arrayList = new ArrayList();
        if (query != null && query.moveToFirst()) {
            do {
                try {
                    JSONObject jSONObject = new JSONObject(query.getString(query.getColumnIndex("extra_data")));
                    String str3 = "";
                    try {
                        str3 = jSONObject.getString("caption");
                    } catch (Exception e2) {
                    }
                    String string = jSONObject.getString("token");
                    if (new File(Utils.m7079a(string, Type.PHOTO)).exists()) {
                        int size;
                        arrayList.add(new MessagePreview(str3, string, query.getString(query.getColumnIndex("thumbnail")), query.getLong(query.getColumnIndex("_id")), 2));
                        if (query.getString(query.getColumnIndex("message_id")).equals(str2)) {
                            size = arrayList.size() - 1;
                        } else {
                            size = i;
                        }
                        i = size;
                    }
                } catch (JSONException e3) {
                    e = e3;
                    Main.f1926a.m5682c(e);
                    if (!query.moveToNext()) {
                        query.close();
                        imagePreviewModel.m5139a(i);
                        imagePreviewModel.m5140a(arrayList);
                        return imagePreviewModel;
                    }
                } catch (StorageException e4) {
                    e = e4;
                    Main.f1926a.m5682c(e);
                    if (query.moveToNext()) {
                        query.close();
                        imagePreviewModel.m5139a(i);
                        imagePreviewModel.m5140a(arrayList);
                        return imagePreviewModel;
                    }
                }
            } while (query.moveToNext());
            query.close();
        }
        imagePreviewModel.m5139a(i);
        imagePreviewModel.m5140a(arrayList);
        return imagePreviewModel;
    }

    public static void m4696a(String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Text.ELEMENT, str2);
        if (Main.f1927b.getContentResolver().update(HistoryGroupProvider.f2380a, contentValues, "jabber_id=? AND status=?", new String[]{str, DeliveryStatus.DRAFT.ordinal() + ""}) == 0) {
            contentValues.put("jabber_id", str);
            contentValues.put(Status.ELEMENT, Integer.valueOf(DeliveryStatus.DRAFT.ordinal()));
            contentValues.put("timestamp", Long.toString(System.currentTimeMillis()));
            contentValues.put("type", Integer.valueOf(Type.TEXT.ordinal()));
            contentValues.put("message_id", StringUtil.m7062a());
            Main.f1927b.getContentResolver().insert(HistoryGroupProvider.f2380a, contentValues);
        }
    }

    public static String m4698b(String str) {
        String[] strArr = new String[]{str, DeliveryStatus.DRAFT.ordinal() + ""};
        String[] strArr2 = new String[]{Text.ELEMENT};
        Cursor query = Main.f1927b.getContentResolver().query(HistoryGroupProvider.f2380a, strArr2, "jabber_id=? AND status=?", strArr, null);
        String str2 = "";
        if (query != null) {
            if (query.moveToFirst()) {
                str2 = query.getString(query.getColumnIndex(Text.ELEMENT));
            }
            query.close();
        }
        return str2;
    }

    public static void m4701c(String str) {
        String[] strArr = new String[]{str, DeliveryStatus.DRAFT.ordinal() + ""};
        Main.f1927b.getContentResolver().delete(HistoryGroupProvider.f2380a, "jabber_id=? AND status=?", strArr);
    }

    public void m4710a(LoadEarlier loadEarlier, String str, long j, long j2, long j3, long j4, LoadDirection loadDirection) {
        String str2;
        int i;
        String str3;
        String str4 = "jabber_id=? AND status!=?";
        ArrayList c01341 = new C01341(this, str);
        if (loadDirection == LoadDirection.downToUp) {
            str2 = " DESC ";
            loadEarlier.m5891c(false);
        } else {
            str2 = " ASC ";
            loadEarlier.m5891c(true);
        }
        if (j2 == 0 && loadDirection == LoadDirection.downToUp) {
            int b = ConversationGroupDataSource.m4587a().m4607b(str);
            if (((long) b) >= j) {
                loadEarlier.m5890b(true);
                String str5 = " ASC ";
                loadEarlier.m5891c(true);
                if (j4 > 0) {
                    str4 = str4 + " AND timestamp>?";
                    c01341.add(j4 + "");
                    i = b;
                    str3 = str5;
                } else {
                    j = (long) (b + 10);
                    loadEarlier.m5891c(false);
                    i = b;
                    str3 = " DESC ";
                }
            } else {
                i = b;
                str3 = str2;
            }
        } else {
            str4 = str4 + " AND timestamp" + (loadDirection == LoadDirection.downToUp ? "<?" : ">?");
            c01341.add(loadDirection == LoadDirection.downToUp ? j2 + "" : j3 + "");
            i = 0;
            str3 = str2;
        }
        Cursor query = Main.f1927b.getContentResolver().query(HistoryGroupProvider.f2380a, null, str4, (String[]) c01341.toArray(new String[c01341.size()]), "timestamp" + str3 + "LIMIT " + j);
        loadEarlier.m5886a(query);
        boolean z;
        if (loadDirection == LoadDirection.downToUp) {
            z = query == null || (query != null && ((long) query.getCount()) == j);
            loadEarlier.m5887a(z);
        } else {
            z = query == null || (query != null && ((long) query.getCount()) == j);
            loadEarlier.m5890b(z);
        }
        loadEarlier.m5889b(i);
        if (((long) i) >= j) {
            loadEarlier.m5885a((int) Math.min((long) i, j));
        } else if (query != null && i == query.getCount()) {
            loadEarlier.m5885a(0);
        } else if (i <= 0 || query == null) {
            loadEarlier.m5885a(-1);
        } else {
            loadEarlier.m5885a(query.getCount() - i);
        }
    }

    public List<HistoryGroupEntity> m4707a(String str, int i) {
        List<HistoryGroupEntity> arrayList = new ArrayList();
        String[] strArr = new String[]{str};
        Cursor query = this.f2348b.query("group_message_table", null, "jabber_id=?", strArr, null, null, "timestamp DESC", String.valueOf(i));
        if (query == null) {
            return arrayList;
        }
        if (query.moveToFirst()) {
            do {
                arrayList.add(m4690a(query));
            } while (query.moveToNext());
            query.close();
            return arrayList;
        }
        query.close();
        return arrayList;
    }

    public ArrayList<HistoryEntity> m4706a(String str, long j, long j2) {
        ArrayList<HistoryEntity> arrayList = new ArrayList();
        if (str == null) {
            return arrayList;
        }
        ArrayList c01352 = new C01352(this, str, j2);
        String[] strArr = (String[]) c01352.toArray(new String[c01352.size()]);
        String str2 = j + "";
        Cursor query = this.f2348b.query("group_message_table", null, "jabber_id=? AND status!=? AND timestamp>=?", strArr, null, null, "timestamp ASC ", str2);
        if (query == null) {
            return arrayList;
        }
        if (query.moveToFirst()) {
            do {
                arrayList.add(m4690a(query));
            } while (query.moveToNext());
        }
        query.close();
        return arrayList;
    }

    public HistoryEntity m4714d(String str) {
        HistoryEntity historyEntity = null;
        String[] strArr = new String[]{str, DeliveryStatus.DELIVERED.ordinal() + "", DeliveryStatus.SENT.ordinal() + "", DeliveryStatus.RECEIVED.ordinal() + ""};
        Cursor query = this.f2348b.query("group_message_table", null, "jabber_id=? AND (status=? OR status=? OR status=?)", strArr, null, null, "timestamp DESC", "1");
        if (query != null) {
            if (query.moveToFirst()) {
                historyEntity = m4690a(query);
            }
            query.close();
        }
        return historyEntity;
    }

    public static int m4688a(Context context, String str, String str2) {
        ContentValues contentValues = new ContentValues(1);
        contentValues.put(Status.ELEMENT, Integer.valueOf(DeliveryStatus.SENT.ordinal()));
        contentValues.put("timestamp", str2);
        contentValues.put("timestamp", str2);
        return context.getContentResolver().update(HistoryGroupProvider.f2380a, contentValues, "message_id=? AND status!=?", new String[]{str + "", DeliveryStatus.DELIVERED.ordinal() + ""});
    }

    public synchronized void m4711a(String str, DeliveryStatus deliveryStatus) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Status.ELEMENT, Integer.valueOf(deliveryStatus.ordinal()));
        String[] strArr = new String[]{str};
        this.f2348b.update("group_message_table", contentValues, "message_id=?", strArr);
    }

    public static HashMap<String, Boolean> m4693a(long j) {
        HashMap<String, Boolean> hashMap = new HashMap();
        Cursor query = Main.f1927b.getContentResolver().query(HistoryGroupProvider.f2380a, new String[]{"extra_data", "type"}, "(type=? OR type=? ) AND timestamp< ?", new String[]{Type.PHOTO.ordinal() + "", Type.VIDEO.ordinal() + "", j + ""}, null);
        if (query != null && query.moveToFirst()) {
            do {
                String string = query.getString(query.getColumnIndex("extra_data"));
                if (string != null) {
                    try {
                        hashMap.put(new JSONObject(string).getString("token"), Boolean.valueOf(query.getInt(query.getColumnIndex("type")) == Type.PHOTO.ordinal()));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            } while (query.moveToNext());
            query.close();
        }
        return hashMap;
    }

    public static void m4699b(long j) {
        ImageLoader.m11076a().m11089b();
        Iterator it = m4693a(j).entrySet().iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            try {
                File file = new File(Utils.m7079a(entry.getKey().toString(), Boolean.parseBoolean(entry.getValue().toString()) ? Type.PHOTO : Type.VIDEO));
                if (file.exists()) {
                    file.delete();
                }
            } catch (Throwable e) {
                Main.f1926a.m5682c(e);
            }
            it.remove();
        }
    }

    public int m4713c(long j) {
        int delete = Main.f1927b.getContentResolver().delete(HistoryGroupProvider.f2380a, "timestamp < " + j, null);
        Cursor rawQuery = this.f2348b.rawQuery("select count(*),jabber_id FROM group_message_table WHERE jabber_id IS NOT NULL AND status!='" + DeliveryStatus.DRAFT.ordinal() + "' GROUP BY " + "jabber_id", null);
        if (rawQuery.getCount() > 0) {
            rawQuery.moveToFirst();
            do {
                if (rawQuery.getInt(0) > 0) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(rawQuery.getString(0));
                    arrayList.add(rawQuery.getString(0));
                    arrayList.add(rawQuery.getString(1));
                    this.f2348b.rawQuery("UPDATE group_conversation_table SET unread_count = ( CASE WHEN unread_count > ? THEN ? ELSE unread_count END ) WHERE group_jid=?", (String[]) arrayList.toArray(new String[arrayList.size()]));
                } else {
                    ConversationGroupDataSource.m4587a().m4599a(rawQuery.getString(1));
                }
            } while (rawQuery.moveToNext());
        } else {
            ContentValues contentValues = new ContentValues(1);
            contentValues.put("message_id", Integer.valueOf(0));
            contentValues.put("message_body", Main.f1927b.getString(2131296579));
            contentValues.put("message_username", "");
            contentValues.put("unread_count", Integer.valueOf(0));
            contentValues.put("time_stamp", Long.valueOf(System.currentTimeMillis() * 1000));
            ConversationGroupDataSource.m4587a().m4620g();
            Main.f1927b.getContentResolver().update(ConversationGroupProvider.f2368a, contentValues, null, null);
        }
        rawQuery.close();
        GroupManager.m7323a().m7355a(ConversationGroupDataSource.m4587a().m4621h(), true);
        return delete;
    }

    public void m4709a(long j, String str) {
        ContentValues contentValues = new ContentValues(1);
        contentValues.put("thumbnail", str);
        String[] strArr = new String[]{j + ""};
        this.f2348b.update("group_message_table", contentValues, "_id=?", strArr);
    }

    public long m4702a(ArrayList<HistoryGroupEntity> arrayList) {
        int i = 0;
        if (arrayList == null || arrayList.size() == 0) {
            return 0;
        }
        ArrayList arrayList2 = new ArrayList();
        long parseLong = Long.parseLong(GroupManager.m7323a().m7358b(((HistoryGroupEntity) arrayList.get(0)).m4414c()).m4297k());
        String c = AccountManager.m3934a().m3937c();
        long j = parseLong;
        int i2 = 0;
        int i3 = 0;
        while (i < arrayList.size()) {
            ContentValues a = m4689a((HistoryGroupEntity) arrayList.get(i));
            if (!(((HistoryGroupEntity) arrayList.get(i)).m4449M() == null || ((HistoryGroupEntity) arrayList.get(i)).m4449M().equals(c)) || ((HistoryGroupEntity) arrayList.get(i)).m4418e() == Type.GROUP_STATUS) {
                arrayList2.add(a);
                i3++;
            }
            if (((HistoryGroupEntity) arrayList.get(i)).m4419f() != null && Long.parseLong(((HistoryGroupEntity) arrayList.get(i)).m4419f()) > j) {
                j = Long.parseLong(((HistoryGroupEntity) arrayList.get(i)).m4419f());
                i2 = i;
            }
            i++;
        }
        if (arrayList2.size() != 0) {
            i = Main.f1927b.getContentResolver().bulkInsert(HistoryGroupProvider.f2380a, (ContentValues[]) arrayList2.toArray(new ContentValues[arrayList2.size()]));
            if (i2 >= 0 && i2 < arrayList.size()) {
                ConversationGroupEntity b = GroupManager.m7323a().m7358b(((HistoryGroupEntity) arrayList.get(i2)).m4414c());
                b.m4268a(GroupManager.m7323a().m7358b(((HistoryGroupEntity) arrayList.get(i2)).m4414c()).m4277c() + i3);
                b.m4275b(GroupManager.m7323a().m7358b(((HistoryGroupEntity) arrayList.get(i2)).m4414c()).m4306r() + i3);
                m4700b((HistoryGroupEntity) arrayList.get(i2));
            }
            return i <= 0 ? -1 : j;
        } else if (i2 < 0 || i2 >= arrayList.size()) {
            return j;
        } else {
            m4700b((HistoryGroupEntity) arrayList.get(i2));
            return j;
        }
    }

    private void m4700b(HistoryGroupEntity historyGroupEntity) {
        ConversationGroupEntity b = GroupManager.m7323a().m7358b(historyGroupEntity.m4414c());
        if (Long.parseLong(GroupManager.m7323a().m7358b(historyGroupEntity.m4414c()).m4297k()) <= Long.parseLong(historyGroupEntity.m4419f())) {
            b.m4288f(historyGroupEntity.m4416d());
            b.m4292h(historyGroupEntity.m4419f());
            b.m4294i(historyGroupEntity.m4419f());
            b.m4290g(historyGroupEntity.m4449M());
            b.m4276b(historyGroupEntity.m4412b());
        }
        ConversationGroupDataSource.m4587a().m4597a(historyGroupEntity, b.m4277c(), b.m4306r());
    }

    public void m4715e(String str) {
        String[] strArr = new String[]{str};
        this.f2348b.delete("group_message_table", "jabber_id=?", strArr);
    }

    public void m4712b(String str, String str2) {
        String[] strArr = new String[]{str2};
        ContentValues contentValues = new ContentValues();
        contentValues.put("jabber_id", str);
        this.f2348b.update("group_message_table", contentValues, "jabber_id=?", strArr);
    }

    public boolean m4716f(String str) {
        String[] strArr = new String[]{str};
        String[] strArr2 = new String[]{"_id"};
        Cursor query = this.f2348b.query("group_message_table", strArr2, "jabber_id=?", strArr, null, null, null, "1");
        if (query == null) {
            return false;
        }
        boolean z;
        if (!query.moveToFirst() || query.getCount() <= 0) {
            z = false;
        } else {
            z = true;
        }
        query.close();
        return z;
    }

    public List<HistoryEntity> m4708a(List<String> list) {
        if (list == null) {
            return null;
        }
        List<HistoryEntity> arrayList = new ArrayList();
        Cursor query = this.f2348b.query("group_message_table", null, "message_id IN (" + m4705a(list.size()) + ")", (String[]) list.toArray(new String[list.size()]), null, null, "timestamp");
        if (query == null) {
            return arrayList;
        }
        if (query.moveToFirst()) {
            do {
                arrayList.add(m4690a(query));
            } while (query.moveToNext());
        }
        query.close();
        return arrayList;
    }

    public Cursor m4703a(String str, long j, int i) {
        String[] strArr = new String[]{str, j + "", DeliveryStatus.DRAFT.ordinal() + "", (i / 2) + "", str, j + "", DeliveryStatus.DRAFT.ordinal() + "", (i / 2) + ""};
        return this.f2348b.rawQuery("SELECT * FROM (SELECT * FROM group_message_table WHERE " + "jabber_id=? AND timestamp < ? AND status!=?" + " ORDER BY " + "timestamp" + " DESC LIMIT ?)" + " UNION " + "SELECT * FROM (" + "SELECT * FROM " + "group_message_table" + " WHERE " + "jabber_id=? AND timestamp >= ?  AND status!=?" + " ORDER BY " + "timestamp" + " ASC LIMIT ?)" + " ORDER BY " + "timestamp" + " ASC", strArr);
    }

    String m4705a(int i) {
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
