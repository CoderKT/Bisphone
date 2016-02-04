package app.database.datasource;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import app.Main;
import app.common.entity.HistoryChannelEntity;
import app.common.entity.HistoryChannelEntity.Builder;
import app.common.entity.HistoryEntity;
import app.common.entity.HistoryEntity.DeliveryStatus;
import app.common.entity.HistoryEntity.Type;
import app.database.DatabaseHelper;
import app.database.provider.HistoryChannelProvider;
import app.galley.MessagePreview;
import app.galley.internal.ImagePreviewModel;
import app.messaging.LoadEarlier;
import app.messaging.RecycleMessagingActivity.LoadDirection;
import app.storage.StorageException;
import app.util.StringUtil;
import app.util.Utils;
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

public class HistoryChannelDataSource {
    private static HistoryChannelDataSource f2338c;
    DatabaseHelper f2339a;
    SQLiteDatabase f2340b;

    /* renamed from: app.database.datasource.HistoryChannelDataSource.1 */
    class C01321 extends ArrayList<String> {
        final /* synthetic */ String f2333a;
        final /* synthetic */ HistoryChannelDataSource f2334b;

        C01321(HistoryChannelDataSource historyChannelDataSource, String str) {
            this.f2334b = historyChannelDataSource;
            this.f2333a = str;
            add(this.f2333a);
            add(DeliveryStatus.DRAFT.ordinal() + "");
        }
    }

    /* renamed from: app.database.datasource.HistoryChannelDataSource.2 */
    class C01332 extends ArrayList<String> {
        final /* synthetic */ String f2335a;
        final /* synthetic */ long f2336b;
        final /* synthetic */ HistoryChannelDataSource f2337c;

        C01332(HistoryChannelDataSource historyChannelDataSource, String str, long j) {
            this.f2337c = historyChannelDataSource;
            this.f2335a = str;
            this.f2336b = j;
            add(this.f2335a);
            add(DeliveryStatus.DRAFT.ordinal() + "");
            add(this.f2336b + "");
        }
    }

    public static HistoryChannelDataSource m4667a() {
        if (f2338c == null) {
            synchronized (HistoryChannelDataSource.class) {
                if (f2338c == null) {
                    f2338c = new HistoryChannelDataSource();
                }
            }
        }
        return f2338c;
    }

    private HistoryChannelDataSource() {
        this.f2339a = DatabaseHelper.m4499a(Main.f1927b);
        this.f2340b = this.f2339a.getWritableDatabase();
    }

    public static ContentValues m4665a(HistoryChannelEntity historyChannelEntity) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("message_id", historyChannelEntity.m4412b());
        contentValues.put(Text.ELEMENT, historyChannelEntity.m4416d());
        contentValues.put("type", Integer.valueOf(historyChannelEntity.m4418e().ordinal()));
        contentValues.put("timestamp", historyChannelEntity.m4419f());
        contentValues.put(Status.ELEMENT, Integer.valueOf(historyChannelEntity.m4420g().ordinal()));
        contentValues.put("thumbnail", historyChannelEntity.m4423j());
        contentValues.put("extra_data", historyChannelEntity.m4424k().toString());
        contentValues.put("jabber_id", historyChannelEntity.m4414c());
        return contentValues;
    }

    public static HistoryChannelEntity m4666a(Cursor cursor) {
        Builder builder = new Builder();
        builder.m4351a(cursor.getLong(cursor.getColumnIndexOrThrow("_id"))).m4359a(cursor.getString(cursor.getColumnIndexOrThrow("message_id"))).m4365b(cursor.getString(cursor.getColumnIndex("jabber_id"))).m4369c(cursor.getString(cursor.getColumnIndexOrThrow(Text.ELEMENT))).m4356a(Type.values()[cursor.getInt(cursor.getColumnIndexOrThrow("type"))]).m4372d(cursor.getString(cursor.getColumnIndexOrThrow("timestamp"))).m4353a(DeliveryStatus.values()[cursor.getInt(cursor.getColumnIndexOrThrow(Status.ELEMENT))]).m4376e(cursor.getString(cursor.getColumnIndexOrThrow("thumbnail"))).m4378f(cursor.getString(cursor.getColumnIndexOrThrow("extra_data")));
        return builder.m4392a();
    }

    public HistoryEntity m4679a(String str) {
        Cursor query = Main.f1927b.getContentResolver().query(HistoryChannelProvider.f2377a, null, "message_id=?", new String[]{str}, null);
        if (query.moveToFirst()) {
            return m4666a(query);
        }
        return null;
    }

    public static List<HistoryChannelEntity> m4670a(Context context) {
        List<HistoryChannelEntity> list = null;
        Cursor query = context.getContentResolver().query(HistoryChannelProvider.f2377a, null, "status=? OR status=?", new String[]{DeliveryStatus.FAILED_TO_SEND.ordinal() + "", DeliveryStatus.SENDING.ordinal() + ""}, null);
        if (query.moveToFirst()) {
            list = new ArrayList(query.getCount());
            do {
                list.add(m4666a(query));
            } while (query.moveToNext());
            query.close();
        }
        return list;
    }

    public static int m4673b(Context context) {
        ContentValues contentValues = new ContentValues(1);
        contentValues.put(Status.ELEMENT, Integer.valueOf(DeliveryStatus.FAILED_TO_UPLOAD.ordinal()));
        return context.getContentResolver().update(HistoryChannelProvider.f2377a, contentValues, "status=?", new String[]{DeliveryStatus.UPLOADING.ordinal() + ""});
    }

    public static void m4671a(Context context, String[] strArr) {
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
            contentResolver.delete(HistoryChannelProvider.f2377a, str, strArr);
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
            contentResolver.delete(HistoryChannelProvider.f2377a, str2, (String[]) arrayList.toArray(new String[arrayList.size()]));
        }
    }

    public static int m4674b(String str) {
        String[] strArr = new String[]{str};
        return Main.f1927b.getContentResolver().delete(HistoryChannelProvider.f2377a, "jabber_id=?", strArr);
    }

    public static ImagePreviewModel m4668a(String str, String str2, long j) {
        Throwable e;
        int i = 0;
        ImagePreviewModel imagePreviewModel = new ImagePreviewModel();
        String[] strArr = new String[]{str, Type.PHOTO.ordinal() + "", j + ""};
        Cursor query = Main.f1927b.getContentResolver().query(HistoryChannelProvider.f2377a, new String[]{"extra_data", "_id", "message_id", "thumbnail"}, "jabber_id=? AND type=? AND timestamp >=?", strArr, "timestamp");
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
                        arrayList.add(new MessagePreview(str3, string, query.getString(query.getColumnIndex("thumbnail")), query.getLong(query.getColumnIndex("_id")), 3));
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

    public static void m4672a(String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Text.ELEMENT, str2);
        if (Main.f1927b.getContentResolver().update(HistoryChannelProvider.f2377a, contentValues, "jabber_id=? AND status=?", new String[]{str, DeliveryStatus.DRAFT.ordinal() + ""}) == 0) {
            contentValues.put("jabber_id", str);
            contentValues.put(Status.ELEMENT, Integer.valueOf(DeliveryStatus.DRAFT.ordinal()));
            contentValues.put("timestamp", Long.toString(System.currentTimeMillis()));
            contentValues.put("type", Integer.valueOf(Type.TEXT.ordinal()));
            contentValues.put("message_id", StringUtil.m7062a());
            Main.f1926a.m5683d("channel insert uri is " + Main.f1927b.getContentResolver().insert(HistoryChannelProvider.f2377a, contentValues));
        }
    }

    public static String m4676c(String str) {
        String[] strArr = new String[]{str, DeliveryStatus.DRAFT.ordinal() + ""};
        String[] strArr2 = new String[]{Text.ELEMENT};
        Cursor query = Main.f1927b.getContentResolver().query(HistoryChannelProvider.f2377a, strArr2, "jabber_id=? AND status=?", strArr, null);
        if (query == null || !query.moveToFirst()) {
            return "";
        }
        return query.getString(query.getColumnIndex(Text.ELEMENT));
    }

    public void m4684a(LoadEarlier loadEarlier, String str, long j, long j2, long j3, long j4, LoadDirection loadDirection) {
        String str2;
        int i;
        String str3;
        String str4 = "jabber_id=? AND status!=?";
        ArrayList c01321 = new C01321(this, str);
        if (loadDirection == LoadDirection.downToUp) {
            str2 = " DESC ";
            loadEarlier.m5891c(false);
        } else {
            str2 = " ASC ";
            loadEarlier.m5891c(true);
        }
        if (j2 == 0 && loadDirection == LoadDirection.downToUp) {
            int e = ChannelDataSource.m4538a().m4552e(str);
            if (((long) e) >= j) {
                loadEarlier.m5890b(true);
                String str5 = " ASC ";
                loadEarlier.m5891c(true);
                if (j4 > 0) {
                    str4 = str4 + " AND timestamp>?";
                    c01321.add(j4 + "");
                    i = e;
                    str3 = str5;
                } else {
                    j = (long) (e + 10);
                    loadEarlier.m5891c(false);
                    i = e;
                    str3 = " DESC ";
                }
            } else {
                i = e;
                str3 = str2;
            }
        } else {
            Object obj;
            str4 = str4 + " AND timestamp" + (loadDirection == LoadDirection.downToUp ? "<?" : ">?");
            if (loadDirection == LoadDirection.downToUp) {
                obj = j2 + "";
            } else {
                obj = j3 + "";
            }
            c01321.add(obj);
            i = 0;
            str3 = str2;
        }
        Cursor query = Main.f1927b.getContentResolver().query(HistoryChannelProvider.f2377a, null, str4, (String[]) c01321.toArray(new String[c01321.size()]), "timestamp" + str3 + "LIMIT " + j);
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

    public ArrayList<HistoryEntity> m4681a(String str, long j, long j2) {
        ArrayList<HistoryEntity> arrayList = new ArrayList();
        if (str == null) {
            return arrayList;
        }
        ArrayList c01332 = new C01332(this, str, j2);
        String[] strArr = (String[]) c01332.toArray(new String[c01332.size()]);
        String str2 = j + "";
        Cursor query = this.f2340b.query("channel_message_table", null, "jabber_id=? AND status!=? AND timestamp>=?", strArr, null, null, "timestamp ASC ", str2);
        if (query == null) {
            return arrayList;
        }
        if (query.moveToFirst()) {
            do {
                arrayList.add(m4666a(query));
            } while (query.moveToNext());
        }
        query.close();
        return arrayList;
    }

    public HistoryEntity m4686d(String str) {
        HistoryEntity historyEntity = null;
        String[] strArr = new String[]{str};
        Cursor query = this.f2340b.query("channel_message_table", null, "jabber_id=?", strArr, null, null, "timestamp DESC", "1");
        if (query != null) {
            if (query.moveToFirst()) {
                historyEntity = m4666a(query);
            }
            query.close();
        }
        return historyEntity;
    }

    public static void m4677c(Context context) {
        ChannelDataSource.m4540a(context);
    }

    public synchronized void m4685a(String str, DeliveryStatus deliveryStatus) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Status.ELEMENT, Integer.valueOf(deliveryStatus.ordinal()));
        String[] strArr = new String[]{str};
        this.f2340b.update("channel_message_table", contentValues, "message_id=?", strArr);
    }

    public static HashMap<String, Boolean> m4669a(long j) {
        HashMap<String, Boolean> hashMap = new HashMap();
        Cursor query = Main.f1927b.getContentResolver().query(HistoryChannelProvider.f2377a, new String[]{"extra_data", "type"}, "(type=? OR type=? ) AND timestamp< ?", new String[]{Type.PHOTO.ordinal() + "", Type.VIDEO.ordinal() + "", j + ""}, null);
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

    public static void m4675b(long j) {
        Iterator it = m4669a(j).entrySet().iterator();
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

    public void m4683a(long j, String str) {
        ContentValues contentValues = new ContentValues(1);
        contentValues.put("thumbnail", str);
        String[] strArr = new String[]{j + ""};
        this.f2340b.update("channel_message_table", contentValues, "_id=?", strArr);
    }

    public List<HistoryEntity> m4682a(List<String> list) {
        if (list == null) {
            return null;
        }
        List<HistoryEntity> arrayList = new ArrayList();
        Cursor query = this.f2340b.query("channel_message_table", null, "message_id IN (" + m4680a(list.size()) + ")", (String[]) list.toArray(new String[list.size()]), null, null, "timestamp");
        if (query == null) {
            return arrayList;
        }
        if (query.moveToFirst()) {
            do {
                arrayList.add(m4666a(query));
            } while (query.moveToNext());
        }
        query.close();
        return arrayList;
    }

    public Cursor m4678a(String str, long j, int i) {
        String[] strArr = new String[]{str, j + "", DeliveryStatus.DRAFT.ordinal() + "", (i / 2) + "", str, j + "", DeliveryStatus.DRAFT.ordinal() + "", (i / 2) + ""};
        return this.f2340b.rawQuery("SELECT * FROM (SELECT * FROM channel_message_table WHERE " + "jabber_id=? AND timestamp < ? AND status!=?" + " ORDER BY " + "timestamp" + " DESC LIMIT ?)" + " UNION " + "SELECT * FROM (" + "SELECT * FROM " + "channel_message_table" + " WHERE " + "jabber_id=? AND timestamp >= ?  AND status!=?" + " ORDER BY " + "timestamp" + " ASC LIMIT ?)" + " ORDER BY " + "timestamp" + " ASC", strArr);
    }

    String m4680a(int i) {
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
