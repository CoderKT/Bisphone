package app.database.datasource;

import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteFullException;
import android.net.Uri;
import app.Main;
import app.common.AddressBookHelper;
import app.common.entity.ContactEntity;
import app.common.entity.ContactEntity.STATUS;
import app.common.entity.ContactEntity.TYPE;
import app.common.entity.ContactEntityLite;
import app.common.entity.ContactEntityLite.Builder;
import app.database.DatabaseHelper;
import app.database.provider.ContactProvider;
import app.events.ContactLocalSyncFinishedEvent;
import app.logger.HandledException;
import app.util.SharedPreferencesUtil;
import app.util.TimeUtils;
import app.xmpp.JabberId;
import com.crashlytics.android.Crashlytics;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jivesoftware.smackx.muc.packet.MUCUser.Status;

public class ContactDataSource {
    private static ContactDataSource f2316c;
    DatabaseHelper f2317a;
    SQLiteDatabase f2318b;

    public static ContactDataSource m4553a() {
        if (f2316c == null) {
            synchronized (ContactDataSource.class) {
                if (f2316c == null) {
                    f2316c = new ContactDataSource();
                }
            }
        }
        return f2316c;
    }

    private ContactDataSource() {
        this.f2317a = DatabaseHelper.m4499a(Main.f1927b);
        this.f2318b = this.f2317a.getWritableDatabase();
    }

    public synchronized long m4568b() {
        long j;
        j = 0;
        Cursor rawQuery = this.f2318b.rawQuery("SELECT SUM(version) FROM phone_contact_table WHERE status!='REMOVED'", null);
        if (rawQuery.moveToFirst()) {
            j = rawQuery.getLong(0);
        }
        rawQuery.close();
        return j;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void m4567a(java.util.List<app.common.entity.ContactEntity> r15, boolean r16) {
        /*
        r14 = this;
        if (r15 != 0) goto L_0x0008;
    L_0x0002:
        r15 = new java.util.ArrayList;
        r0 = 0;
        r15.<init>(r0);
    L_0x0008:
        r7 = r15.size();
        r0 = "type=?";
        r1 = 1;
        r1 = new java.lang.String[r1];
        r2 = 0;
        r3 = app.common.entity.ContactEntity.TYPE.LOCAL;
        r3 = r3.toString();
        r1[r2] = r3;
        r0 = r14.m4557b(r0, r1);
        if (r0 != 0) goto L_0x0189;
    L_0x0020:
        r0 = new java.util.ArrayList;
        r0.<init>(r7);
        r2 = r0;
    L_0x0026:
        r8 = r2.size();
        r3 = 0;
        r5 = 0;
        r4 = 0;
    L_0x002d:
        r0 = r7 + -1;
        if (r5 <= r0) goto L_0x0043;
    L_0x0031:
        if (r4 >= r8) goto L_0x006b;
    L_0x0033:
        r3 = r3 + 1;
        r0 = r2.get(r4);
        r0 = (app.common.entity.ContactEntity) r0;
        r1 = app.common.entity.ContactEntity.STATUS.REMOVED;
        r0.m4181a(r1);
        r4 = r4 + 1;
        goto L_0x0031;
    L_0x0043:
        r0 = r8 + -1;
        if (r4 <= r0) goto L_0x005d;
    L_0x0047:
        if (r5 >= r7) goto L_0x006b;
    L_0x0049:
        r3 = r3 + 1;
        r0 = r15.get(r5);
        r0 = (app.common.entity.ContactEntity) r0;
        r1 = app.common.entity.ContactEntity.STATUS.ADDED;
        r0 = r0.m4181a(r1);
        r2.add(r0);
        r5 = r5 + 1;
        goto L_0x0047;
    L_0x005d:
        r0 = r15.get(r5);
        r0 = (app.common.entity.ContactEntity) r0;
        r1 = r2.get(r4);
        r1 = (app.common.entity.ContactEntity) r1;
        if (r0 != 0) goto L_0x00a9;
    L_0x006b:
        r1 = new java.util.ArrayList;
        r1.<init>(r3);
        r4 = new java.util.ArrayList;
        r4.<init>(r3);
        r2 = r2.iterator();
    L_0x0079:
        r0 = r2.hasNext();
        if (r0 == 0) goto L_0x0178;
    L_0x007f:
        r0 = r2.next();
        r0 = (app.common.entity.ContactEntity) r0;
        r3 = r0.m4201h();
        r5 = app.common.entity.ContactEntity.STATUS.SYNCED;
        if (r3 == r5) goto L_0x009d;
    L_0x008d:
        r3 = r0.m4201h();
        r5 = app.common.entity.ContactEntity.STATUS.MODIFIED_LOCAL;
        if (r3 == r5) goto L_0x009d;
    L_0x0095:
        r3 = app.common.entity.ContactEntity.TYPE.LOCAL;
        r0.m4184a(r3);
        r1.add(r0);
    L_0x009d:
        r3 = r0.m4201h();
        r5 = app.common.entity.ContactEntity.STATUS.MODIFIED_LOCAL;
        if (r3 != r5) goto L_0x0079;
    L_0x00a5:
        r4.add(r0);
        goto L_0x0079;
    L_0x00a9:
        if (r1 == 0) goto L_0x006b;
    L_0x00ab:
        r6 = r0.m4198f();
        r9 = r1.m4198f();
        r6 = r6.compareTo(r9);
        if (r6 != 0) goto L_0x015a;
    L_0x00b9:
        r6 = 0;
        r9 = r0.m4196e();
        if (r9 != 0) goto L_0x0149;
    L_0x00c0:
        r9 = r1.m4196e();
        if (r9 == 0) goto L_0x00c7;
    L_0x00c6:
        r6 = 1;
    L_0x00c7:
        if (r6 == 0) goto L_0x00ec;
    L_0x00c9:
        r6 = r0.m4196e();
        r1.m4194d(r6);
        r10 = r0.m4203i();
        r1.m4186b(r10);
        r6 = r0.m4192c();
        r1.m4190c(r6);
        r10 = r0.m4193d();
        r1.m4180a(r10);
        r3 = r3 + 1;
        r6 = app.common.entity.ContactEntity.STATUS.MODIFIED;
        r1.m4181a(r6);
    L_0x00ec:
        r10 = r0.m4203i();
        r12 = r1.m4203i();
        r6 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1));
        if (r6 == 0) goto L_0x010c;
    L_0x00f8:
        r10 = r0.m4203i();
        r1.m4186b(r10);
        r6 = r1.m4201h();
        r9 = app.common.entity.ContactEntity.STATUS.MODIFIED;
        if (r6 == r9) goto L_0x010c;
    L_0x0107:
        r6 = app.common.entity.ContactEntity.STATUS.MODIFIED_LOCAL;
        r1.m4181a(r6);
    L_0x010c:
        r6 = r0.m4192c();
        r9 = r1.m4192c();
        r6 = r6.equals(r9);
        if (r6 == 0) goto L_0x0126;
    L_0x011a:
        r10 = r0.m4193d();
        r12 = r1.m4193d();
        r6 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1));
        if (r6 == 0) goto L_0x0141;
    L_0x0126:
        r6 = r0.m4192c();
        r1.m4190c(r6);
        r10 = r0.m4193d();
        r1.m4180a(r10);
        r0 = r1.m4201h();
        r6 = app.common.entity.ContactEntity.STATUS.MODIFIED;
        if (r0 == r6) goto L_0x0141;
    L_0x013c:
        r0 = app.common.entity.ContactEntity.STATUS.MODIFIED_LOCAL;
        r1.m4181a(r0);
    L_0x0141:
        r1 = r5 + 1;
        r0 = r4 + 1;
    L_0x0145:
        r4 = r0;
        r5 = r1;
        goto L_0x002d;
    L_0x0149:
        r9 = r0.m4196e();
        r10 = r1.m4196e();
        r9 = r9.equals(r10);
        if (r9 != 0) goto L_0x00c7;
    L_0x0157:
        r6 = 1;
        goto L_0x00c7;
    L_0x015a:
        if (r6 <= 0) goto L_0x016b;
    L_0x015c:
        r3 = r3 + 1;
        r1 = app.common.entity.ContactEntity.STATUS.ADDED;
        r0.m4181a(r1);
        r2.add(r0);
        r0 = r5 + 1;
        r1 = r0;
        r0 = r4;
        goto L_0x0145;
    L_0x016b:
        if (r6 >= 0) goto L_0x0186;
    L_0x016d:
        r3 = r3 + 1;
        r0 = app.common.entity.ContactEntity.STATUS.REMOVED;
        r1.m4181a(r0);
        r0 = r4 + 1;
        r1 = r5;
        goto L_0x0145;
    L_0x0178:
        r14.m4558e(r1);
        r14.m4559f(r4);
        r0 = m4553a();
        r0.m4575d();
        return;
    L_0x0186:
        r0 = r4;
        r1 = r5;
        goto L_0x0145;
    L_0x0189:
        r2 = r0;
        goto L_0x0026;
        */
        throw new UnsupportedOperationException("Method not decompiled: app.database.datasource.ContactDataSource.a(java.util.List, boolean):void");
    }

    public List<ContactEntity> m4572c() {
        List<ContactEntity> list = null;
        Cursor query = this.f2318b.query("phone_contact_table", null, "status!=?", new String[]{STATUS.SYNCED.toString()}, null, null, null);
        if (query.moveToFirst()) {
            list = new ArrayList(query.getCount());
            do {
                list.add(m4556b(query));
            } while (query.moveToNext());
        }
        query.close();
        return list;
    }

    public List<ContactEntity> m4563a(String str) {
        Cursor query = this.f2318b.query("phone_contact_table", null, "contact_id=?  GROUP BY (contact_id),(username)", new String[]{str}, null, null, null);
        List<ContactEntity> arrayList = new ArrayList(query.getCount());
        if (query.moveToFirst()) {
            do {
                arrayList.add(m4556b(query));
            } while (query.moveToNext());
        }
        query.close();
        return arrayList;
    }

    public List<Long> m4564a(String str, String[] strArr) {
        List<Long> arrayList = new ArrayList();
        Cursor query = this.f2318b.query("phone_contact_table", new String[]{"contact_id"}, str, strArr, "contact_id", null, null);
        if (query != null) {
            while (query.moveToNext()) {
                arrayList.add(Long.valueOf(query.getLong(query.getColumnIndex("contact_id"))));
            }
            query.close();
        }
        return arrayList;
    }

    public void m4575d() {
        Throwable e;
        int i = 0;
        List a = m4564a("type=?", new String[]{TYPE.LOCAL.toString()});
        List arrayList = new ArrayList();
        if (a != null) {
            int i2;
            for (i2 = 0; i2 < a.size(); i2++) {
                Uri a2 = AddressBookHelper.m3941a(((Long) a.get(i2)).longValue());
                Object obj = null;
                if (a2 != null) {
                    obj = a2.toString();
                }
                arrayList.add(obj);
            }
            ArrayList arrayList2 = new ArrayList();
            for (i2 = 0; i2 < a.size(); i2++) {
                ContentValues contentValues = new ContentValues(1);
                contentValues.put("photo_uri", (String) arrayList.get(i2));
                arrayList2.add(ContentProviderOperation.newUpdate(ContactProvider.f2364a).withSelection("contact_id=?", new String[]{Long.toString(((Long) a.get(i2)).longValue())}).withValues(contentValues).build());
            }
            try {
                ContentProviderResult[] applyBatch = Main.f1927b.getContentResolver().applyBatch("com.bistalk.bisphone.provider.contacts", arrayList2);
                int i3 = 0;
                while (i < applyBatch.length) {
                    i3 += applyBatch[i].count.intValue();
                    i++;
                }
            } catch (Throwable e2) {
                Main.f1926a.m5679b("RemoteException when updating photo uri, operations: " + arrayList2.toString());
                Main.f1926a.m5680b(e2);
            } catch (OperationApplicationException e3) {
                e2 = e3;
                Main.f1926a.m5679b("OperationApplicationException when updating photo uri, operations: " + arrayList2.toString());
                Main.f1926a.m5680b(e2);
            } catch (SQLiteFullException e4) {
                e2 = e4;
                Main.f1926a.m5679b("OperationApplicationException when updating photo uri, operations: " + arrayList2.toString());
                Main.f1926a.m5680b(e2);
            }
        }
    }

    public ContactEntity m4570b(String str) {
        ContactEntity contactEntity = null;
        if (str != null) {
            Cursor query = this.f2318b.query("phone_contact_table", null, "username=?", new String[]{str}, null, null, null);
            if (query.moveToLast()) {
                contactEntity = m4556b(query);
            }
            query.close();
        }
        return contactEntity;
    }

    public List<ContactEntity> m4565a(List<String> list) {
        List<ContactEntity> arrayList = new ArrayList();
        if (list == null || list.size() == 0) {
            return null;
        }
        for (String str : list) {
            Cursor query = this.f2318b.query("phone_contact_table", null, "username=?", new String[]{str}, null, null, null);
            if (query != null) {
                if (query.moveToFirst()) {
                    arrayList.add(m4556b(query));
                }
                query.close();
            }
        }
        return arrayList;
    }

    public void m4573c(String str) {
        ContactEntity contactEntity = new ContactEntity();
        contactEntity.m4184a(TYPE.SELF);
        contactEntity.m4185a(Long.valueOf(0));
        contactEntity.m4194d("Me");
        contactEntity.m4181a(STATUS.SYNCED);
        contactEntity.m4197f(SharedPreferencesUtil.m7052a(Main.f1927b.getString(2131296925)) + SharedPreferencesUtil.m7052a(Main.f1927b.getString(2131296935)));
        contactEntity.m4188b(true);
        contactEntity.m4195e(SharedPreferencesUtil.m7052a(Main.f1927b.getString(2131296935)));
        contactEntity.m4205j(SharedPreferencesUtil.m7052a(Main.f1927b.getString(2131296919)));
        contactEntity.m4199g(str);
        this.f2318b.insert("phone_contact_table", null, m4555b(contactEntity));
    }

    public void m4578e() {
        String[] strArr = new String[]{TYPE.SELF.toString()};
        this.f2318b.delete("phone_contact_table", "type=?", strArr);
    }

    public boolean m4580f() {
        Cursor query = this.f2318b.query("phone_contact_table", new String[]{"username"}, "type=?", new String[]{TYPE.SELF.toString()}, null, null, null);
        if (query == null) {
            return false;
        }
        boolean z;
        if (query.getCount() > 0) {
            z = true;
        } else {
            z = false;
        }
        query.close();
        return z;
    }

    public ContactEntity m4581g() {
        Cursor query = this.f2318b.query("phone_contact_table", null, "type=?", new String[]{TYPE.SELF.toString()}, null, null, null);
        ContactEntity contactEntity = new ContactEntity();
        if (query == null || !query.moveToFirst()) {
            return contactEntity;
        }
        contactEntity = m4556b(query);
        query.close();
        return contactEntity;
    }

    public void m4566a(ContactEntity contactEntity) {
        ContentValues b = m4555b(contactEntity);
        b.remove("_id");
        int update = this.f2318b.update("phone_contact_table", b, "type=?", new String[]{TYPE.SELF.toString()});
        if (update > 1) {
            Crashlytics.m7882a(new HandledException("Updated " + update + " rows with type self. Deleted " + this.f2318b.delete("phone_contact_table", "type=? AND _id!=?", new String[]{TYPE.SELF.toString(), contactEntity.m4189b()}) + " rows with type self."));
        }
    }

    public void m4583h() {
        this.f2318b.rawQuery("DELETE FROM phone_contact_table WHERE type = '" + TYPE.REMOTE + "' AND " + "_id" + " NOT IN " + "( SELECT MIN(" + "_id" + ") FROM " + "phone_contact_table" + " GROUP BY " + "username" + " HAVING " + "type" + "= '" + TYPE.REMOTE + "')", null);
    }

    public Cursor m4569b(List<String> list) {
        List arrayList = new ArrayList();
        for (String str : list) {
            JabberId a;
            if (str.contains("@")) {
                a = JabberId.m7386a(str);
            } else {
                a = new JabberId(str, "bisphone.com", null);
            }
            if (a != null) {
                arrayList.add(a.m7387a());
            }
        }
        return m4574d(arrayList);
    }

    public void m4584i() {
        Cursor query = this.f2318b.query("phone_contact_table", null, "recently_joined> 0", null, null, null, null, "2000");
        List arrayList = new ArrayList();
        if (query == null || !query.moveToFirst()) {
            if (query != null) {
                query.close();
            }
            if (arrayList.size() == 0) {
                String str = "_id IN (" + m4562a(arrayList.size()) + ")";
                String[] strArr = (String[]) arrayList.toArray(new String[arrayList.size()]);
                ContentValues contentValues = new ContentValues(1);
                contentValues.put("recently_joined", Integer.valueOf(0));
                this.f2318b.update("phone_contact_table", contentValues, str, strArr);
            }
        }
        do {
            if (TimeUtils.m7075c(Long.valueOf(query.getLong(query.getColumnIndex("recently_joined")))) > 2) {
                arrayList.add(query.getString(query.getColumnIndex("_id")));
            }
        } while (query.moveToNext());
        if (query != null) {
            query.close();
        }
        if (arrayList.size() == 0) {
            String str2 = "_id IN (" + m4562a(arrayList.size()) + ")";
            String[] strArr2 = (String[]) arrayList.toArray(new String[arrayList.size()]);
            ContentValues contentValues2 = new ContentValues(1);
            contentValues2.put("recently_joined", Integer.valueOf(0));
            this.f2318b.update("phone_contact_table", contentValues2, str2, strArr2);
        }
    }

    public void m4576d(String str) {
        ContentValues contentValues = new ContentValues(1);
        contentValues.put("recently_joined", Integer.valueOf(0));
        this.f2318b.update("phone_contact_table", contentValues, "username=?", new String[]{str});
    }

    public int m4577e(String str) {
        String[] strArr = new String[]{str + "%", TYPE.LOCAL + ""};
        Cursor query = this.f2318b.query("phone_contact_table", null, "username LIKE ? AND type=?", strArr, null, null, null);
        if (query == null) {
            return 0;
        }
        int count = query.getCount();
        query.close();
        return count;
    }

    public Cursor m4579f(String str) {
        String[] strArr = new String[]{str + "%", TYPE.LOCAL + ""};
        return this.f2318b.query("phone_contact_table", null, "username LIKE ? AND type=?", strArr, null, null, null);
    }

    public ContactEntityLite m4582g(String str) {
        if (str == null) {
            return null;
        }
        Builder builder = new Builder();
        String[] strArr = new String[]{"contact_id", "name", "vcard_avatar", "vcard_nickname", "recently_joined", "type", "photo_uri", "is_registered"};
        Cursor query = this.f2318b.query("phone_contact_table", strArr, "username=?", new String[]{str}, null, null, null);
        if (query == null) {
            return null;
        }
        if (query.moveToFirst()) {
            builder.m4223a(TYPE.valueOf(query.getString(query.getColumnIndex("type"))));
            builder.m4224a(str);
            builder.m4225a(query.getInt(query.getColumnIndex("is_registered")) != 0);
            builder.m4227b(query.getString(query.getColumnIndex("contact_id")));
            builder.m4228c(query.getString(query.getColumnIndex("name")));
            builder.m4229d(query.getString(query.getColumnIndex("photo_uri")));
            builder.m4230e(query.getString(query.getColumnIndex("vcard_nickname")));
            builder.m4231f(query.getString(query.getColumnIndex("vcard_avatar")));
        }
        query.close();
        return builder.m4226a();
    }

    public ArrayList<ContactEntityLite> m4571c(List<String> list) {
        ArrayList<ContactEntityLite> arrayList = new ArrayList();
        Cursor query = this.f2318b.query("phone_contact_table", new String[]{"contact_id", "username", "name", "vcard_avatar", "vcard_nickname", "recently_joined", "type", "photo_uri", "is_registered"}, "username IN (" + m4562a(list.size()) + ")", (String[]) list.toArray(new String[list.size()]), "username", null, null);
        if (query == null || !query.moveToFirst()) {
            return arrayList;
        }
        do {
            Builder builder = new Builder();
            builder.m4223a(TYPE.valueOf(query.getString(query.getColumnIndex("type"))));
            builder.m4224a(query.getString(query.getColumnIndex("username")));
            builder.m4225a(query.getInt(query.getColumnIndex("is_registered")) != 0);
            builder.m4227b(query.getString(query.getColumnIndex("contact_id")));
            builder.m4228c(query.getString(query.getColumnIndex("name")));
            builder.m4229d(query.getString(query.getColumnIndex("photo_uri")));
            builder.m4230e(query.getString(query.getColumnIndex("vcard_nickname")));
            builder.m4231f(query.getString(query.getColumnIndex("vcard_avatar")));
            arrayList.add(builder.m4226a());
        } while (query.moveToNext());
        query.close();
        return arrayList;
    }

    private void m4558e(List<ContactEntity> list) {
        this.f2318b.beginTransactionNonExclusive();
        try {
            for (ContactEntity b : list) {
                this.f2318b.insertWithOnConflict("phone_contact_table", null, m4555b(b), 5);
            }
            this.f2318b.setTransactionSuccessful();
            EventBus.m12779a().m12796e(new ContactLocalSyncFinishedEvent());
        } finally {
            this.f2318b.endTransaction();
        }
    }

    private void m4559f(List<ContactEntity> list) {
        this.f2318b.beginTransactionNonExclusive();
        try {
            for (ContactEntity b : list) {
                String[] strArr = new String[]{b.m4189b()};
                this.f2318b.update("phone_contact_table", m4555b(b), "_id=?", strArr);
            }
            this.f2318b.setTransactionSuccessful();
        } finally {
            this.f2318b.endTransaction();
        }
    }

    private List<ContactEntity> m4557b(String str, String[] strArr) {
        List<ContactEntity> list = null;
        Cursor query = this.f2318b.query("phone_contact_table", null, str, strArr, null, null, "phone_number COLLATE NOCASE DESC");
        if (query.moveToFirst()) {
            list = new ArrayList(query.getCount());
            do {
                list.add(m4556b(query));
            } while (query.moveToNext());
        }
        query.close();
        return list;
    }

    public static int m4560h(String str) {
        ContentValues contentValues = new ContentValues(1);
        contentValues.put("is_registered", Boolean.valueOf(true));
        contentValues.put("recently_joined", Long.valueOf(System.currentTimeMillis()));
        return Main.f1927b.getContentResolver().update(ContactProvider.f2364a, contentValues, "username=?", new String[]{str});
    }

    public Cursor m4574d(List<String> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        return this.f2318b.query("phone_contact_table", null, "username IN (" + m4562a(list.size()) + ")", (String[]) list.toArray(new String[list.size()]), null, null, "username COLLATE NOCASE DESC, type ASC");
    }

    String m4562a(int i) {
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

    public static HashMap<String, ContactEntity> m4554a(Cursor cursor) {
        HashMap<String, ContactEntity> hashMap = new HashMap();
        if (cursor != null) {
            ContactEntity g;
            if (cursor.isClosed() || !cursor.moveToFirst()) {
                g = m4553a().m4581g();
                if (g != null) {
                    hashMap.put(g.m4200g(), g);
                }
            } else {
                do {
                    String string = cursor.getString(cursor.getColumnIndexOrThrow("username"));
                    if (string != null) {
                        hashMap.put(string, m4556b(cursor));
                    }
                } while (cursor.moveToNext());
                g = m4553a().m4581g();
                if (g != null) {
                    hashMap.put(g.m4200g(), g);
                }
            }
        }
        return hashMap;
    }

    public static ContactEntity m4556b(Cursor cursor) {
        boolean z;
        boolean z2 = true;
        ContactEntity contactEntity = new ContactEntity();
        contactEntity.m4187b(cursor.getString(cursor.getColumnIndex("_id")));
        contactEntity.m4184a(ContactEntity.m4178a(cursor.getString(cursor.getColumnIndex("type"))));
        contactEntity.m4190c(cursor.getString(cursor.getColumnIndexOrThrow("contact_id")));
        contactEntity.m4195e(cursor.getString(cursor.getColumnIndexOrThrow("phone_number")));
        contactEntity.m4197f(cursor.getString(cursor.getColumnIndexOrThrow("username")));
        contactEntity.m4181a(STATUS.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(Status.ELEMENT)) == null ? "SYNCED" : cursor.getString(cursor.getColumnIndexOrThrow(Status.ELEMENT))));
        contactEntity.m4186b(cursor.getLong(cursor.getColumnIndexOrThrow("version")));
        if (cursor.getInt(cursor.getColumnIndexOrThrow("is_registered")) > 0) {
            z = true;
        } else {
            z = false;
        }
        contactEntity.m4188b(z);
        if (cursor.getInt(cursor.getColumnIndexOrThrow("is_favorite")) <= 0) {
            z2 = false;
        }
        contactEntity.m4191c(z2);
        contactEntity.m4199g(cursor.getString(cursor.getColumnIndexOrThrow("vcard_nickname")));
        contactEntity.m4204i(cursor.getString(cursor.getColumnIndexOrThrow("photo_uri")));
        contactEntity.m4205j(cursor.getString(cursor.getColumnIndexOrThrow("vcard_avatar")));
        contactEntity.m4207k(cursor.getString(cursor.getColumnIndexOrThrow("last_seen")));
        contactEntity.m4185a(Long.valueOf(cursor.getLong(cursor.getColumnIndexOrThrow("recently_joined"))));
        contactEntity.m4194d(cursor.getString(cursor.getColumnIndex("name")));
        contactEntity.m4202h(cursor.getString(cursor.getColumnIndex("background_json")));
        contactEntity.m4180a(cursor.getLong(cursor.getColumnIndex("contact_data_table_id")));
        return contactEntity;
    }

    private static ContentValues m4555b(ContactEntity contactEntity) {
        ContentValues contentValues = new ContentValues();
        if (contactEntity.m4189b() != null) {
            contentValues.put("_id", contactEntity.m4189b());
        }
        contentValues.put("type", contactEntity.m4210m() == null ? TYPE.REMOTE.toString() : contactEntity.m4210m().toString());
        contentValues.put("contact_id", contactEntity.m4192c());
        contentValues.put("phone_number", contactEntity.m4198f());
        contentValues.put("username", contactEntity.m4200g());
        contentValues.put("version", Long.valueOf(contactEntity.m4203i()));
        contentValues.put(Status.ELEMENT, contactEntity.m4201h() == null ? STATUS.SYNCED.toString() : contactEntity.m4201h().toString());
        contentValues.put("is_registered", Boolean.valueOf(contactEntity.m4206j()));
        contentValues.put("is_favorite", Boolean.valueOf(contactEntity.m4208k()));
        contentValues.put("vcard_nickname", contactEntity.m4209l());
        contentValues.put("photo_uri", contactEntity.m4211n());
        contentValues.put("vcard_avatar", contactEntity.m4212o());
        contentValues.put("last_seen", contactEntity.m4213p());
        contentValues.put("recently_joined", contactEntity.m4214q());
        contentValues.put("background_json", contactEntity.m4182a());
        contentValues.put("name", contactEntity.m4196e());
        contentValues.put("contact_data_table_id", Long.valueOf(contactEntity.m4193d()));
        return contentValues;
    }

    public long m4561a(String str, ContentValues contentValues, boolean z) {
        String str2 = "username=?";
        String[] strArr = new String[]{str};
        if (z) {
            return ContentUris.parseId(Main.f1927b.getContentResolver().insert(ContactProvider.f2364a, contentValues));
        }
        return (long) Main.f1927b.getContentResolver().update(ContactProvider.f2364a, contentValues, str2, strArr);
    }
}
