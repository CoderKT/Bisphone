package app.database.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import app.Main;
import app.common.entity.CallLogEntity;
import app.common.entity.CallLogEntity.Builder;
import app.common.entity.CallLogEntity.Type;
import app.common.entity.CallLogHistoryEntity;
import app.common.entity.ContactEntity;
import app.common.entity.ContactEntityLite;
import app.database.DatabaseHelper;
import app.database.provider.HistoryProvider;
import app.home.CallFragment.DeleteCallModel;
import java.util.ArrayList;
import java.util.List;

public class CallsDataSource {
    private static CallsDataSource f2310c;
    DatabaseHelper f2311a;
    SQLiteDatabase f2312b;

    public static CallsDataSource m4527a() {
        if (f2310c == null) {
            synchronized (ContactDataSource.class) {
                if (f2310c == null) {
                    f2310c = new CallsDataSource();
                }
            }
        }
        return f2310c;
    }

    private CallsDataSource() {
        this.f2311a = DatabaseHelper.m4499a(Main.f1927b);
        this.f2312b = this.f2311a.getWritableDatabase();
    }

    private static ContentValues m4528b(CallLogEntity callLogEntity) {
        ContentValues contentValues = new ContentValues(6);
        if (callLogEntity.m4107a() != null) {
            contentValues.put("_id", callLogEntity.m4107a());
        }
        contentValues.put("username", callLogEntity.m4108b());
        contentValues.put("type", callLogEntity.m4109c().toString());
        contentValues.put("call_out", Boolean.valueOf(callLogEntity.m4110d()));
        contentValues.put("duration", Integer.valueOf(callLogEntity.m4111e()));
        contentValues.put("timestamp", Long.valueOf(callLogEntity.m4112f()));
        contentValues.put("credit", Float.valueOf(callLogEntity.m4113g()));
        return contentValues;
    }

    public CallLogHistoryEntity m4530a(Cursor cursor) {
        boolean z = true;
        CallLogEntity a = new Builder().m4103a(Integer.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow("_id")))).m4104a(cursor.getString(cursor.getColumnIndexOrThrow("username"))).m4102a(Type.valueOf(cursor.getString(cursor.getColumnIndexOrThrow("call_type")))).m4105a(cursor.getInt(cursor.getColumnIndexOrThrow("call_out")) != 0).m4100a(cursor.getInt(cursor.getColumnIndexOrThrow("call_duration"))).m4101a(cursor.getLong(cursor.getColumnIndexOrThrow("call_timestamp"))).m4099a(cursor.getFloat(cursor.getColumnIndexOrThrow("call_credit"))).m4106a();
        ContactEntityLite.Builder a2 = new ContactEntityLite.Builder().m4223a(ContactEntity.m4178a(cursor.getString(cursor.getColumnIndex("contact_type")))).m4224a(cursor.getString(cursor.getColumnIndexOrThrow("contact_username")));
        if (cursor.getInt(cursor.getColumnIndexOrThrow("contact_is_registered")) <= 0) {
            z = false;
        }
        return new CallLogHistoryEntity(a, a2.m4225a(z).m4227b(cursor.getString(cursor.getColumnIndexOrThrow("local_contact_id"))).m4228c(cursor.getString(cursor.getColumnIndex("local_name"))).m4229d(cursor.getString(cursor.getColumnIndexOrThrow("local_photo_uri"))).m4230e(cursor.getString(cursor.getColumnIndexOrThrow("remote_nickname"))).m4231f(cursor.getString(cursor.getColumnIndexOrThrow("remote_photo_token"))).m4226a());
    }

    public long m4529a(CallLogEntity callLogEntity) {
        return this.f2312b.insert("call_log_table", null, m4528b(callLogEntity));
    }

    public ArrayList<CallLogHistoryEntity> m4531a(String str, String str2, String str3) {
        ArrayList<CallLogHistoryEntity> arrayList = new ArrayList();
        if (str == null || str.isEmpty()) {
            return arrayList;
        }
        String[] strArr = new String[]{str, str2, str3};
        Cursor query = Main.f1927b.getContentResolver().query(HistoryProvider.f2388c, null, "username=? AND call_timestamp BETWEEN ? AND ?", strArr, "call_timestamp DESC");
        if (query == null || !query.moveToFirst()) {
            return arrayList;
        }
        do {
            arrayList.add(HistoryNormalMessageDataSource.m4728b(query));
        } while (query.moveToNext());
        return arrayList;
    }

    public void m4533a(List<DeleteCallModel> list) {
        if (list != null && !list.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder("");
            ArrayList arrayList = new ArrayList();
            for (DeleteCallModel deleteCallModel : list) {
                stringBuilder.append("username=? AND timestamp BETWEEN ? AND ? OR ");
                arrayList.add(deleteCallModel.m5226a());
                arrayList.add(deleteCallModel.m5227b());
                arrayList.add(deleteCallModel.m5228c());
            }
            stringBuilder.delete(stringBuilder.lastIndexOf("OR"), stringBuilder.lastIndexOf("OR") + 2);
            this.f2312b.delete("call_log_table", stringBuilder.toString().trim(), (String[]) arrayList.toArray(new String[arrayList.size()]));
        }
    }

    public List<CallLogHistoryEntity> m4532a(Context context) {
        List<CallLogHistoryEntity> arrayList = new ArrayList();
        Cursor query = context.getContentResolver().query(HistoryProvider.f2388c, null, null, null, "call_timestamp");
        if (query == null) {
            return arrayList;
        }
        if (query.moveToFirst()) {
            do {
                arrayList.add(m4530a(query));
            } while (query.moveToNext());
            query.close();
            return arrayList;
        }
        query.close();
        return arrayList;
    }
}
