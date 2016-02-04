package app.database.datasource;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import app.Main;
import app.common.entity.CallLogEntity;
import app.common.entity.CallLogHistoryEntity;
import app.common.entity.ChatHistoryEntity;
import app.common.entity.ContactEntity;
import app.common.entity.ContactEntityLite;
import app.common.entity.HistoryEntity;
import app.common.entity.HistoryEntity.DeliveryStatus;
import app.common.entity.HistoryEntity.Type;
import app.common.entity.HistoryNormalMessageEntity;
import app.common.entity.HistoryNormalMessageEntity.Builder;
import app.database.DatabaseHelper;
import app.database.provider.ConversationNormalProvider;
import app.database.provider.HistoryOneToOneProvider;
import app.galley.MessagePreview;
import app.galley.internal.ImagePreviewModel;
import app.messaging.LoadEarlier;
import app.messaging.RecycleMessagingActivity.LoadDirection;
import app.messaging.broadcast.BroadcastEntityStatusModel;
import app.storage.StorageException;
import app.util.StringUtil;
import app.util.Utils;
import app.xmpp.JabberId;
import app.xmpp.NormalMessageManager;
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

public class HistoryNormalMessageDataSource {
    private static HistoryNormalMessageDataSource f2354c;
    DatabaseHelper f2355a;
    SQLiteDatabase f2356b;

    /* renamed from: app.database.datasource.HistoryNormalMessageDataSource.1 */
    class C01361 extends ArrayList<String> {
        final /* synthetic */ JabberId f2349a;
        final /* synthetic */ HistoryNormalMessageDataSource f2350b;

        C01361(HistoryNormalMessageDataSource historyNormalMessageDataSource, JabberId jabberId) {
            this.f2350b = historyNormalMessageDataSource;
            this.f2349a = jabberId;
            add(this.f2349a.m7387a());
            add(DeliveryStatus.DRAFT.ordinal() + "");
        }
    }

    /* renamed from: app.database.datasource.HistoryNormalMessageDataSource.2 */
    class C01372 extends ArrayList<String> {
        final /* synthetic */ JabberId f2351a;
        final /* synthetic */ long f2352b;
        final /* synthetic */ HistoryNormalMessageDataSource f2353c;

        C01372(HistoryNormalMessageDataSource historyNormalMessageDataSource, JabberId jabberId, long j) {
            this.f2353c = historyNormalMessageDataSource;
            this.f2351a = jabberId;
            this.f2352b = j;
            add(this.f2351a.m7387a());
            add(DeliveryStatus.DRAFT.ordinal() + "");
            add(this.f2352b + "");
        }
    }

    public static HistoryNormalMessageDataSource m4720a() {
        if (f2354c == null) {
            synchronized (HistoryNormalMessageDataSource.class) {
                if (f2354c == null) {
                    f2354c = new HistoryNormalMessageDataSource();
                }
            }
        }
        return f2354c;
    }

    private HistoryNormalMessageDataSource() {
        this.f2355a = DatabaseHelper.m4499a(Main.f1927b);
        this.f2356b = this.f2355a.getWritableDatabase();
    }

    public static ContentValues m4718a(HistoryNormalMessageEntity historyNormalMessageEntity) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("message_id", historyNormalMessageEntity.m4412b());
        contentValues.put(Text.ELEMENT, historyNormalMessageEntity.m4416d());
        contentValues.put("contact_username", historyNormalMessageEntity.m4456M());
        contentValues.put("type", Integer.valueOf(historyNormalMessageEntity.m4418e().ordinal()));
        contentValues.put("timestamp", historyNormalMessageEntity.m4419f());
        contentValues.put(Status.ELEMENT, Integer.valueOf(historyNormalMessageEntity.m4420g().ordinal()));
        contentValues.put("thumbnail", historyNormalMessageEntity.m4423j());
        contentValues.put("extra_data", historyNormalMessageEntity.m4424k().toString());
        return contentValues;
    }

    public static HistoryNormalMessageEntity m4719a(Cursor cursor) {
        Builder builder = new Builder();
        builder.m4351a(cursor.getLong(cursor.getColumnIndexOrThrow("_id"))).m4359a(cursor.getString(cursor.getColumnIndexOrThrow("message_id"))).m4369c(cursor.getString(cursor.getColumnIndexOrThrow(Text.ELEMENT))).m4356a(Type.values()[cursor.getInt(cursor.getColumnIndexOrThrow("type"))]).m4372d(cursor.getString(cursor.getColumnIndexOrThrow("timestamp"))).m4353a(DeliveryStatus.values()[cursor.getInt(cursor.getColumnIndexOrThrow(Status.ELEMENT))]).m4376e(cursor.getString(cursor.getColumnIndexOrThrow("thumbnail"))).m4365b(new JabberId(cursor.getString(cursor.getColumnIndex("contact_username")), "bisphone.com", null).m7391e()).m4378f(cursor.getString(cursor.getColumnIndexOrThrow("extra_data")));
        builder.m4455t(cursor.getString(cursor.getColumnIndexOrThrow("contact_username")));
        return builder.m4453a();
    }

    public HistoryNormalMessageEntity m4733a(String str) {
        Cursor query = Main.f1927b.getContentResolver().query(HistoryOneToOneProvider.f2383a, null, "message_id=?", new String[]{str}, null);
        if (query.moveToFirst()) {
            return m4719a(query);
        }
        return null;
    }

    public static List<HistoryNormalMessageEntity> m4724a(Context context) {
        List<HistoryNormalMessageEntity> list = null;
        Cursor query = context.getContentResolver().query(HistoryOneToOneProvider.f2383a, null, "status=? OR status=?", new String[]{DeliveryStatus.FAILED_TO_SEND.ordinal() + "", DeliveryStatus.SENDING.ordinal() + ""}, null);
        if (query != null) {
            if (query.moveToFirst()) {
                list = new ArrayList(query.getCount());
                do {
                    HistoryNormalMessageEntity a = m4719a(query);
                    if (!a.m4457N()) {
                        list.add(a);
                    }
                } while (query.moveToNext());
            }
            query.close();
        }
        return list;
    }

    public static int m4727b(Context context) {
        ContentValues contentValues = new ContentValues(1);
        contentValues.put(Status.ELEMENT, Integer.valueOf(DeliveryStatus.FAILED_TO_UPLOAD.ordinal()));
        return context.getContentResolver().update(HistoryOneToOneProvider.f2383a, contentValues, "status=?", new String[]{DeliveryStatus.UPLOADING.ordinal() + ""});
    }

    public static void m4725a(Context context, String[] strArr) {
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
            contentResolver.delete(HistoryOneToOneProvider.f2383a, str, strArr);
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
            contentResolver.delete(HistoryOneToOneProvider.f2383a, str2, (String[]) arrayList.toArray(new String[arrayList.size()]));
        }
    }

    public void m4743a(String[] strArr) {
        this.f2356b.delete("normal_message_table", "message_id IN (" + m4722a(strArr.length) + ")", strArr);
    }

    public static long m4717a(Context context, HistoryNormalMessageEntity historyNormalMessageEntity) {
        return ContentUris.parseId(context.getContentResolver().insert(HistoryOneToOneProvider.f2383a, m4718a(historyNormalMessageEntity)));
    }

    public static ImagePreviewModel m4721a(String str, String str2, long j) {
        Throwable e;
        int i = 0;
        ImagePreviewModel imagePreviewModel = new ImagePreviewModel();
        String[] strArr = new String[]{str, Type.PHOTO.ordinal() + "", j + ""};
        Cursor query = Main.f1927b.getContentResolver().query(HistoryOneToOneProvider.f2383a, new String[]{"extra_data", "_id", "message_id", "thumbnail"}, "contact_username=? AND type=? AND timestamp >=?", strArr, "timestamp");
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
                        arrayList.add(new MessagePreview(str3, string, query.getString(query.getColumnIndex("thumbnail")), query.getLong(query.getColumnIndex("_id")), 1));
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

    public static void m4726a(String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Text.ELEMENT, str2);
        if (Main.f1927b.getContentResolver().update(HistoryOneToOneProvider.f2383a, contentValues, "contact_username=? AND status=?", new String[]{str, DeliveryStatus.DRAFT.ordinal() + ""}) == 0) {
            contentValues.put("contact_username", str);
            contentValues.put(Status.ELEMENT, Integer.valueOf(DeliveryStatus.DRAFT.ordinal()));
            contentValues.put("timestamp", Long.toString(System.currentTimeMillis()));
            contentValues.put("type", Integer.valueOf(Type.TEXT.ordinal()));
            contentValues.put("message_id", StringUtil.m7062a());
            Main.f1927b.getContentResolver().insert(HistoryOneToOneProvider.f2383a, contentValues);
        }
    }

    public static String m4729b(String str) {
        JabberId a;
        if (str.contains("@")) {
            a = JabberId.m7386a(str);
        } else {
            a = new JabberId(str, "bisphone.com", null);
        }
        if (a == null) {
            return "";
        }
        String[] strArr = new String[]{a.m7387a(), DeliveryStatus.DRAFT.ordinal() + ""};
        String[] strArr2 = new String[]{Text.ELEMENT};
        Cursor query = Main.f1927b.getContentResolver().query(HistoryOneToOneProvider.f2383a, strArr2, "contact_username=? AND status=?", strArr, null);
        String str2 = "";
        if (query != null && query.moveToFirst()) {
            str2 = query.getString(query.getColumnIndex(Text.ELEMENT));
        }
        if (query == null) {
            return str2;
        }
        query.close();
        return str2;
    }

    public static String m4730c(String str) {
        String[] strArr = new String[]{str, DeliveryStatus.DRAFT.ordinal() + ""};
        String[] strArr2 = new String[]{Text.ELEMENT};
        Cursor query = Main.f1927b.getContentResolver().query(HistoryOneToOneProvider.f2383a, strArr2, "contact_username=? AND status=?", strArr, null);
        String str2 = "";
        if (query != null && query.moveToFirst()) {
            str2 = query.getString(query.getColumnIndex(Text.ELEMENT));
        }
        if (query != null) {
            query.close();
        }
        return str2;
    }

    public void m4739a(LoadEarlier loadEarlier, String str, long j, long j2, long j3, long j4, LoadDirection loadDirection) {
        if (str == null) {
            throw new RuntimeException("invalid jabberID");
        }
        JabberId a;
        if (str.contains("@")) {
            a = JabberId.m7386a(str);
        } else {
            a = new JabberId(str, "bisphone.com", null);
        }
        if (a == null) {
            loadEarlier.m5895f();
            return;
        }
        String str2;
        int i;
        String str3;
        String str4 = "contact_username=? AND status!=?";
        ArrayList c01361 = new C01361(this, a);
        if (loadDirection == LoadDirection.downToUp) {
            str2 = " DESC ";
            loadEarlier.m5891c(false);
        } else {
            str2 = " ASC ";
            loadEarlier.m5891c(true);
        }
        if (j2 == 0 && loadDirection == LoadDirection.downToUp) {
            int i2;
            ChatHistoryEntity f = NormalMessageManager.m7447a().m7470f(a.m7387a());
            if (f == null) {
                i2 = 0;
            } else {
                i2 = f.m4170d().m4339i();
            }
            if (((long) i2) >= j) {
                loadEarlier.m5890b(true);
                str2 = " ASC ";
                loadEarlier.m5891c(true);
                if (j4 > 0) {
                    str4 = str4 + " AND timestamp>?";
                    c01361.add(j4 + "");
                } else {
                    j = (long) (i2 + 10);
                    str2 = " DESC ";
                    loadEarlier.m5891c(false);
                }
            }
            i = i2;
            str3 = str4;
        } else {
            Object obj;
            str3 = str4 + " AND timestamp" + (loadDirection == LoadDirection.downToUp ? "<?" : ">?");
            if (loadDirection == LoadDirection.downToUp) {
                obj = j2 + "";
            } else {
                obj = j3 + "";
            }
            c01361.add(obj);
            i = 0;
        }
        Cursor query = Main.f1927b.getContentResolver().query(HistoryOneToOneProvider.f2383a, null, str3, (String[]) c01361.toArray(new String[c01361.size()]), "timestamp" + str2 + "LIMIT " + j);
        loadEarlier.m5886a(query);
        boolean z;
        if (loadDirection == LoadDirection.downToUp) {
            z = query != null && ((long) query.getCount()) == j;
            loadEarlier.m5887a(z);
        } else {
            z = query != null && ((long) query.getCount()) == j;
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

    public ArrayList<HistoryEntity> m4734a(String str, long j, long j2) {
        ArrayList<HistoryEntity> arrayList = new ArrayList();
        if (str == null) {
            throw new RuntimeException("invalid jabberID");
        }
        JabberId a;
        if (str.contains("@")) {
            a = JabberId.m7386a(str);
        } else {
            a = new JabberId(str, "bisphone.com", null);
        }
        if (a == null) {
            return arrayList;
        }
        ArrayList c01372 = new C01372(this, a, j2);
        String[] strArr = (String[]) c01372.toArray(new String[c01372.size()]);
        String str2 = j + "";
        Cursor query = this.f2356b.query("normal_message_table", null, "contact_username=? AND status!=? AND timestamp>=?", strArr, null, null, "timestamp ASC ", str2);
        if (query == null) {
            return arrayList;
        }
        if (query.moveToFirst()) {
            do {
                arrayList.add(m4719a(query));
            } while (query.moveToNext());
        }
        query.close();
        return arrayList;
    }

    public Cursor m4732a(String str, long j, int i) {
        String[] strArr = new String[]{str, j + "", DeliveryStatus.DRAFT.ordinal() + "", (i / 2) + "", str, j + "", DeliveryStatus.DRAFT.ordinal() + "", (i / 2) + ""};
        return this.f2356b.rawQuery("SELECT * FROM (SELECT * FROM normal_message_table WHERE " + "contact_username=? AND timestamp < ? AND status!=?" + " ORDER BY " + "timestamp" + " DESC LIMIT ?)" + " UNION " + "SELECT * FROM (" + "SELECT * FROM " + "normal_message_table" + " WHERE " + "contact_username=? AND timestamp >= ?  AND status!=?" + " ORDER BY " + "timestamp" + " ASC LIMIT ?)" + " ORDER BY " + "timestamp" + " ASC", strArr);
    }

    public HistoryEntity m4747d(String str) {
        HistoryEntity historyEntity = null;
        String[] strArr = new String[]{str};
        Cursor query = this.f2356b.query("normal_message_table", null, "contact_username=?", strArr, null, null, "timestamp DESC", "1");
        if (query != null) {
            if (query.moveToFirst()) {
                do {
                    historyEntity = m4719a(query);
                } while (query.moveToNext());
            }
            query.close();
        }
        return historyEntity;
    }

    public Cursor m4745b() {
        ArrayList j = NormalMessageManager.m7447a().m7477j();
        return this.f2356b.query("normal_message_table", null, "contact_username IN (" + m4722a(j.size()) + ")", (String[]) j.toArray(new String[j.size()]), "contact_username", null, null);
    }

    public void m4737a(HistoryNormalMessageEntity historyNormalMessageEntity, String str) {
        historyNormalMessageEntity.m4458a(false);
        ContentValues a = m4718a(historyNormalMessageEntity);
        a.put("contact_username", str);
        a.put("message_id", historyNormalMessageEntity.m4412b());
        Main.f1927b.getContentResolver().insert(HistoryOneToOneProvider.f2383a, a);
    }

    public void m4738a(HistoryNormalMessageEntity historyNormalMessageEntity, ArrayList<String> arrayList) {
        historyNormalMessageEntity.m4458a(true);
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            ContentValues a = m4718a(historyNormalMessageEntity);
            a.put("contact_username", str);
            a.put("message_id", historyNormalMessageEntity.m4412b() + "_" + str);
            a.put(Status.ELEMENT, Integer.valueOf(DeliveryStatus.SENT.ordinal()));
            arrayList2.add(a);
        }
        Main.f1927b.getContentResolver().bulkInsert(HistoryOneToOneProvider.f2383a, (ContentValues[]) arrayList2.toArray(new ContentValues[arrayList2.size()]));
    }

    public synchronized void m4740a(String str, DeliveryStatus deliveryStatus) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Status.ELEMENT, Integer.valueOf(deliveryStatus.ordinal()));
        String str2 = "message_id=?";
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        if (deliveryStatus == DeliveryStatus.SENT || deliveryStatus == DeliveryStatus.DELIVERED) {
            str2 = str2 + " AND status<?";
            arrayList.add(deliveryStatus.ordinal() + "");
        }
        this.f2356b.update("normal_message_table", contentValues, str2, (String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    public synchronized void m4741a(String str, ArrayList<String> arrayList, DeliveryStatus deliveryStatus) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Status.ELEMENT, Integer.valueOf(deliveryStatus.ordinal()));
        String[] strArr = new String[]{str};
        this.f2356b.update("normal_message_table", contentValues, "message_id=?", strArr);
    }

    public static HashMap<String, Boolean> m4723a(long j) {
        HashMap<String, Boolean> hashMap = new HashMap();
        Cursor query = Main.f1927b.getContentResolver().query(HistoryOneToOneProvider.f2383a, new String[]{"extra_data", "type"}, "(type=? OR type=? ) AND timestamp< ?", new String[]{Type.PHOTO.ordinal() + "", Type.VIDEO.ordinal() + "", j + ""}, null);
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

    public int m4744b(long j) {
        ArrayList i = NormalMessageManager.m7447a().m7476i();
        if (i == null || i.isEmpty()) {
            return 0;
        }
        int delete = Main.f1927b.getContentResolver().delete(HistoryOneToOneProvider.f2383a, "contact_username IN (" + m4722a(i.size()) + ") AND " + "timestamp" + " < " + j, (String[]) i.toArray(new String[i.size()]));
        Cursor rawQuery = this.f2356b.rawQuery("select count(*),contact_username FROM normal_message_table WHERE contact_username IS NOT NULL AND status!='" + DeliveryStatus.DRAFT.ordinal() + "' GROUP BY " + "contact_username", null);
        if (rawQuery.getCount() > 0) {
            rawQuery.moveToFirst();
            do {
                i.remove(rawQuery.getString(rawQuery.getColumnIndex("contact_username")));
                if (rawQuery.getInt(0) > 0) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(rawQuery.getString(0));
                    arrayList.add(rawQuery.getString(0));
                    arrayList.add(rawQuery.getString(1));
                    this.f2356b.rawQuery("UPDATE normal_conversation_table SET unread_count = ( CASE WHEN unread_count > ? THEN ? ELSE unread_count END ) WHERE username=?", (String[]) arrayList.toArray(new String[arrayList.size()]));
                } else {
                    String[] strArr = new String[]{rawQuery.getString(1)};
                    Main.f1927b.getContentResolver().delete(ConversationNormalProvider.f2373a, "username=?", strArr);
                    NormalMessageManager.m7447a().m7452a(rawQuery.getString(1));
                }
            } while (rawQuery.moveToNext());
            if (i.size() > 0) {
                NormalMessageManager.m7447a().m7453a((List) i);
                Main.f1927b.getContentResolver().delete(ConversationNormalProvider.f2373a, "username IN (" + m4722a(i.size()) + ")", (String[]) i.toArray(new String[i.size()]));
                m4742a(i);
            }
        } else {
            Main.f1927b.getContentResolver().delete(ConversationNormalProvider.f2373a, null, null);
            NormalMessageManager.m7447a().m7453a((List) i);
            m4742a(i);
        }
        rawQuery.close();
        return delete;
    }

    public int m4746c(long j) {
        List j2 = NormalMessageManager.m7447a().m7477j();
        if (j2.size() == 0) {
            return 0;
        }
        int delete = this.f2356b.delete("normal_message_table", "contact_username IN (" + m4722a(j2.size()) + ") AND " + "timestamp" + " < " + j, (String[]) j2.toArray(new String[j2.size()]));
        NormalMessageManager.m7447a().m7461b(BroadcastListDataSource.m4504a().m4517c(), true, false);
        if (delete == 0) {
            return delete;
        }
        Cursor b = m4745b();
        if (b == null || !b.moveToFirst()) {
            BroadcastListDataSource.m4504a().m4516b(j2);
            if (b == null) {
                return delete;
            }
            b.close();
            return delete;
        }
        do {
            j2.remove(b.getString(b.getColumnIndex("contact_username")));
        } while (b.moveToNext());
        BroadcastListDataSource.m4504a().m4516b(j2);
        return delete;
    }

    public static void m4731d(long j) {
        Iterator it = m4723a(j).entrySet().iterator();
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

    public static CallLogHistoryEntity m4728b(Cursor cursor) {
        boolean z = true;
        CallLogEntity a = new CallLogEntity.Builder().m4103a(Integer.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow("_id")))).m4104a(cursor.getString(cursor.getColumnIndexOrThrow("username"))).m4102a(CallLogEntity.Type.valueOf(cursor.getString(cursor.getColumnIndexOrThrow("call_type")))).m4105a(cursor.getInt(cursor.getColumnIndexOrThrow("call_out")) != 0).m4100a(cursor.getInt(cursor.getColumnIndexOrThrow("call_duration"))).m4101a(cursor.getLong(cursor.getColumnIndexOrThrow("call_timestamp"))).m4099a(cursor.getFloat(cursor.getColumnIndexOrThrow("call_credit"))).m4106a();
        ContactEntityLite.Builder a2 = new ContactEntityLite.Builder().m4223a(ContactEntity.m4178a(cursor.getString(cursor.getColumnIndex("contact_type")))).m4224a(cursor.getString(cursor.getColumnIndexOrThrow("contact_username")));
        if (cursor.getInt(cursor.getColumnIndexOrThrow("contact_is_registered")) <= 0) {
            z = false;
        }
        return new CallLogHistoryEntity(a, a2.m4225a(z).m4227b(cursor.getString(cursor.getColumnIndexOrThrow("local_contact_id"))).m4228c(cursor.getString(cursor.getColumnIndex("local_name"))).m4229d(cursor.getString(cursor.getColumnIndexOrThrow("local_photo_uri"))).m4230e(cursor.getString(cursor.getColumnIndexOrThrow("remote_nickname"))).m4231f(cursor.getString(cursor.getColumnIndexOrThrow("remote_photo_token"))).m4226a());
    }

    public void m4736a(long j, String str) {
        ContentValues contentValues = new ContentValues(1);
        contentValues.put("thumbnail", str);
        String[] strArr = new String[]{j + ""};
        this.f2356b.update("normal_message_table", contentValues, "_id=?", strArr);
    }

    public List<HistoryEntity> m4735a(List<String> list) {
        if (list == null) {
            return null;
        }
        List<HistoryEntity> arrayList = new ArrayList();
        Cursor query = this.f2356b.query("normal_message_table", null, "message_id IN (" + m4722a(list.size()) + ")", (String[]) list.toArray(new String[list.size()]), null, null, "timestamp");
        if (query == null) {
            return arrayList;
        }
        if (query.moveToFirst()) {
            do {
                arrayList.add(m4719a(query));
            } while (query.moveToNext());
        }
        query.close();
        return arrayList;
    }

    public List<BroadcastEntityStatusModel> m4748e(String str) {
        List<BroadcastEntityStatusModel> arrayList = new ArrayList();
        String[] strArr = new String[]{str + "_%"};
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT ").append("T1.").append(Status.ELEMENT).append(",").append("T2.*").append(" FROM ").append("normal_message_table");
        stringBuilder.append(" T1").append(" LEFT JOIN ( SELECT ").append("username").append(", ").append("contact_id").append(", ").append("name").append(", ").append("vcard_avatar").append(", ").append("vcard_nickname").append(", ").append("recently_joined").append(", ").append("type").append(", ").append("photo_uri").append(", ").append("is_registered").append(" FROM  phone_contact_table").append(" GROUP BY username").append(") T2").append(" ON T1.").append("contact_username").append("=T2.").append("username");
        stringBuilder.append(" WHERE ").append("message_id LIKE ?");
        stringBuilder.append(" GROUP BY T1.").append("contact_username").append(" ORDER BY T1.").append("timestamp").append(" DESC");
        Cursor rawQuery = this.f2356b.rawQuery(stringBuilder.toString(), strArr);
        if (rawQuery == null) {
            return arrayList;
        }
        if (rawQuery.moveToFirst()) {
            do {
                ContactEntityLite.Builder builder = new ContactEntityLite.Builder();
                builder.m4223a(ContactEntity.m4178a(rawQuery.getString(rawQuery.getColumnIndex("type"))));
                builder.m4224a(rawQuery.getString(rawQuery.getColumnIndex("username")));
                builder.m4225a(rawQuery.getInt(rawQuery.getColumnIndex("is_registered")) != 0);
                builder.m4227b(rawQuery.getString(rawQuery.getColumnIndex("contact_id")));
                builder.m4228c(rawQuery.getString(rawQuery.getColumnIndex("name")));
                builder.m4229d(rawQuery.getString(rawQuery.getColumnIndex("photo_uri")));
                builder.m4230e(rawQuery.getString(rawQuery.getColumnIndex("vcard_nickname")));
                builder.m4231f(rawQuery.getString(rawQuery.getColumnIndex("vcard_avatar")));
                arrayList.add(new BroadcastEntityStatusModel(builder.m4226a(), DeliveryStatus.values()[rawQuery.getInt(rawQuery.getColumnIndex(Status.ELEMENT))]));
            } while (rawQuery.moveToNext());
        }
        rawQuery.close();
        return arrayList;
    }

    public void m4742a(ArrayList<String> arrayList) {
        if (arrayList != null && arrayList.size() != 0) {
            this.f2356b.delete("normal_message_table", "contact_username IN (" + m4722a(arrayList.size()) + ")", (String[]) arrayList.toArray(new String[arrayList.size()]));
        }
    }

    private String m4722a(int i) {
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

    public List<ChatHistoryEntity> m4749f(String str) {
        List arrayList = new ArrayList();
        if (str != null) {
            String[] strArr = new String[]{"%" + str + "%"};
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SELECT ").append("* FROM ").append("normal_message_table");
            stringBuilder.append(" T1").append(" LEFT JOIN ( SELECT ").append("username").append(", ").append("contact_id").append(", ").append("name").append(", ").append("vcard_avatar").append(", ").append("vcard_nickname").append(", ").append("recently_joined").append(", ").append("type").append(" as contact_type, ").append("photo_uri").append(", ").append("is_registered").append(" FROM  phone_contact_table").append(" GROUP BY username").append(") T2").append(" ON T1.").append("contact_username").append("=").append("username");
            stringBuilder.append(" WHERE ").append("text LIKE ?");
            stringBuilder.append(" ORDER BY T1.").append("timestamp").append(" DESC");
            Cursor rawQuery = this.f2356b.rawQuery(stringBuilder.toString(), strArr);
            if (rawQuery != null) {
                if (rawQuery.moveToFirst()) {
                    do {
                        HistoryNormalMessageEntity a = m4719a(rawQuery);
                        if (!(NormalMessageManager.m7447a().m7470f(a.m4456M()) == null || NormalMessageManager.m7447a().m7470f(a.m4456M()).m4167b())) {
                            arrayList.add(new ChatHistoryEntity(a, ConversationNormalDataSource.m4623a().m4638c(rawQuery)));
                        }
                    } while (rawQuery.moveToNext());
                }
                rawQuery.close();
            }
        }
        return arrayList;
    }
}
