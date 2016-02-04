package app.database.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import app.Main;
import app.account.AccountManager;
import app.common.GroupV2RolesPrivilege;
import app.common.entity.ConversationGroupEntity;
import app.database.DatabaseHelper;
import app.xmpp.GroupManager;
import app.xmpp.packet.groupv2.GroupElement.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import se.emilsjolander.stickylistheaders.C1128R;

public class GroupUsersDatasource {
    private static GroupUsersDatasource f2330c;
    DatabaseHelper f2331a;
    SQLiteDatabase f2332b;

    /* renamed from: app.database.datasource.GroupUsersDatasource.1 */
    class C01291 extends ArrayList<String> {
        final /* synthetic */ String f2325a;
        final /* synthetic */ GroupUsersDatasource f2326b;

        C01291(GroupUsersDatasource groupUsersDatasource, String str) {
            this.f2326b = groupUsersDatasource;
            this.f2325a = str;
            add(this.f2325a);
        }
    }

    /* renamed from: app.database.datasource.GroupUsersDatasource.2 */
    class C01302 extends ArrayList<String> {
        final /* synthetic */ String f2327a;
        final /* synthetic */ GroupUsersDatasource f2328b;

        C01302(GroupUsersDatasource groupUsersDatasource, String str) {
            this.f2328b = groupUsersDatasource;
            this.f2327a = str;
            add(this.f2327a);
        }
    }

    /* renamed from: app.database.datasource.GroupUsersDatasource.3 */
    /* synthetic */ class C01313 {
        static final /* synthetic */ int[] f2329a;

        static {
            f2329a = new int[Type.values().length];
            try {
                f2329a[Type.moderated.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f2329a[Type.semimoderated.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f2329a[Type.unmoderated.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public static GroupUsersDatasource m4639a() {
        if (f2330c == null) {
            synchronized (GroupUsersDatasource.class) {
                if (f2330c == null) {
                    f2330c = new GroupUsersDatasource();
                }
            }
        }
        return f2330c;
    }

    private GroupUsersDatasource() {
        this.f2331a = DatabaseHelper.m4499a(Main.f1927b);
        this.f2332b = this.f2331a.getWritableDatabase();
    }

    public void m4646a(Context context, String str, List<String> list) {
        if (list != null) {
            String[] strArr = new String[]{str, "1"};
            this.f2332b.delete("groups_users_table", "group_jid=? AND user_status=?", strArr);
            for (int i = 0; i < list.size(); i++) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("group_jid", str);
                contentValues.put("contact_username", (String) list.get(i));
                contentValues.put("user_status", Integer.valueOf(1));
                this.f2332b.insert("groups_users_table", null, contentValues);
            }
        }
    }

    public void m4644a(Context context, String str, String str2) {
        if (str2 != null && str != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("group_jid", str);
            contentValues.put("contact_username", str2);
            contentValues.put("user_status", Integer.valueOf(6));
            this.f2332b.insert("groups_users_table", null, contentValues);
        }
    }

    public void m4645a(Context context, String str, ArrayList<String> arrayList, String str2) {
        if (arrayList == null) {
            arrayList = new ArrayList();
        }
        String[] strArr = new String[]{str, "3"};
        this.f2332b.delete("groups_users_table", "group_jid=? AND user_status=?", strArr);
        for (int i = 0; i < arrayList.size(); i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("group_jid", str);
            contentValues.put("contact_username", (String) arrayList.get(i));
            contentValues.put("user_status", Integer.valueOf(3));
            this.f2332b.insert("groups_users_table", null, contentValues);
        }
        if (str2 != null && str2.length() > 0) {
            strArr = new String[]{str, "2"};
            this.f2332b.delete("groups_users_table", "group_jid=? AND user_status=?", strArr);
            ContentValues contentValues2 = new ContentValues();
            contentValues2.put("group_jid", str);
            contentValues2.put("contact_username", str2);
            contentValues2.put("user_status", Integer.valueOf(2));
            this.f2332b.insert("groups_users_table", null, contentValues2);
        }
    }

    public void m4647a(String str, String str2) {
        m4648a(str, new C01291(this, str2));
    }

    public void m4648a(String str, List<String> list) {
        if (list != null && list.size() != 0) {
            String str2 = "group_jid=? AND contact_username IN (" + m4643a(list.size()) + ")";
            ArrayList arrayList = new ArrayList();
            arrayList.add(str);
            arrayList.addAll(list);
            this.f2332b.delete("groups_users_table", str2, (String[]) arrayList.toArray(new String[arrayList.size()]));
        }
    }

    String m4643a(int i) {
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

    public Cursor m4641a(String str) {
        String[] strArr = new String[]{str};
        return this.f2332b.rawQuery("SELECT * FROM groups_users_table LEFT JOIN phone_contact_table ON contact_username = username WHERE group_jid=? GROUP BY contact_username ORDER BY 'type' ASC ", strArr);
    }

    public void m4653b(String str, String str2) {
        if (str2 != null && str != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("group_jid", str);
            contentValues.put("contact_username", str2);
            contentValues.put("user_status", Integer.valueOf(2));
            this.f2332b.insert("groups_users_table", null, contentValues);
        }
    }

    public void m4656c(String str, String str2) {
        m4654b(str, new C01302(this, str2));
    }

    public void m4654b(String str, List<String> list) {
        if (list != null && list.size() != 0 && str != null) {
            for (String str2 : list) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("group_jid", str);
                contentValues.put("contact_username", str2);
                contentValues.put("user_status", Integer.valueOf(3));
                this.f2332b.insert("groups_users_table", null, contentValues);
            }
        }
    }

    public String m4651b(String str) {
        String[] strArr = new String[]{str, "6"};
        Cursor query = this.f2332b.query("groups_users_table", null, "group_jid=? AND user_status=?", strArr, null, null, null);
        if (query == null) {
            return Main.f1927b.getResources().getString(2131296307);
        }
        if (query.moveToFirst()) {
            String string = query.getString(query.getColumnIndex("contact_username"));
            query.close();
            return string;
        }
        query.close();
        return Main.f1927b.getResources().getString(2131296307);
    }

    public void m4652b() {
        this.f2332b.delete("groups_users_table", null, null);
    }

    public GroupV2RolesPrivilege m4642a(String str, int i) {
        boolean j = m4640j(str);
        Type type = Type.values()[i];
        GroupV2RolesPrivilege groupV2RolesPrivilege = new GroupV2RolesPrivilege();
        switch (C01313.f2329a[type.ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                if (j) {
                    groupV2RolesPrivilege.m4045a(true);
                    groupV2RolesPrivilege.m4047b(true);
                    groupV2RolesPrivilege.m4049c(true);
                    groupV2RolesPrivilege.m4051d(true);
                    break;
                }
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                groupV2RolesPrivilege.m4047b(true);
                if (j) {
                    groupV2RolesPrivilege.m4045a(true);
                    groupV2RolesPrivilege.m4049c(true);
                    groupV2RolesPrivilege.m4051d(true);
                    break;
                }
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                groupV2RolesPrivilege.m4045a(true);
                groupV2RolesPrivilege.m4047b(true);
                break;
        }
        return groupV2RolesPrivilege;
    }

    private boolean m4640j(String str) {
        boolean z;
        ConversationGroupEntity b = GroupManager.m7323a().m7358b(str);
        if (b == null || b.m4304p() == null) {
            String[] strArr = new String[]{str, "2", AccountManager.m3934a().m3937c()};
            Cursor query = this.f2332b.query("groups_users_table", null, "group_jid=? AND user_status=? AND contact_username=?", strArr, null, null, null);
            if (query == null) {
                return false;
            }
            if (query.moveToFirst()) {
                z = true;
            } else {
                z = false;
            }
            query.close();
        } else {
            z = b.m4304p().equals(AccountManager.m3934a().m3937c());
        }
        return z;
    }

    public void m4655c(String str) {
        String[] strArr = new String[]{str};
        this.f2332b.delete("groups_users_table", "group_jid=?", strArr);
    }

    public void m4658d(String str) {
        String[] strArr = new String[]{str, "3"};
        this.f2332b.delete("groups_users_table", "group_jid=? AND user_status=?", strArr);
    }

    public void m4657c(String str, List<String> list) {
        if (str != null && list != null && list.size() != 0) {
            for (String str2 : list) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("group_jid", str);
                contentValues.put("contact_username", str2);
                contentValues.put("user_status", Integer.valueOf(4));
                this.f2332b.insert("groups_users_table", null, contentValues);
            }
        }
    }

    public void m4650a(HashMap<String, String> hashMap, int i) {
        if (hashMap != null && hashMap.size() != 0) {
            for (String str : hashMap.keySet()) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("group_jid", str);
                contentValues.put("contact_username", (String) hashMap.get(str));
                contentValues.put("user_status", Integer.valueOf(i));
                this.f2332b.insert("groups_users_table", null, contentValues);
            }
        }
    }

    public int m4660e(String str) {
        int i = 0;
        String[] strArr = new String[]{str, "1"};
        Cursor rawQuery = this.f2332b.rawQuery("SELECT COUNT(*) FROM groups_users_table WHERE group_jid =? AND user_status=?", strArr);
        if (rawQuery != null) {
            if (rawQuery.moveToFirst()) {
                i = rawQuery.getInt(0);
            }
            rawQuery.close();
        }
        return i;
    }

    public void m4661f(String str) {
        String[] strArr = new String[]{str};
        this.f2332b.delete("groups_users_table", "group_jid=?", strArr);
    }

    public void m4649a(String str, List<String> list, int i) {
        if (list != null && list.size() != 0) {
            this.f2332b.beginTransaction();
            try {
                for (String str2 : list) {
                    String[] strArr = new String[]{str, str2};
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("user_status", Integer.valueOf(i));
                    this.f2332b.update("groups_users_table", contentValues, "group_jid= ? AND contact_username = ?", strArr);
                }
                this.f2332b.setTransactionSuccessful();
            } finally {
                this.f2332b.endTransaction();
            }
        }
    }

    public void m4662g(String str) {
        String[] strArr = new String[]{str, "5"};
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_status", Integer.valueOf(3));
        this.f2332b.update("groups_users_table", contentValues, "group_jid=? AND user_status=?", strArr);
    }

    public Cursor m4663h(String str) {
        String[] strArr = new String[]{str, "5", "4"};
        return this.f2332b.query("groups_users_table", null, "group_jid=? AND ( user_status=? OR user_status=? )", strArr, null, null, null);
    }

    public String m4664i(String str) {
        ConversationGroupEntity b = GroupManager.m7323a().m7358b(str);
        if (b != null && b.m4304p() != null) {
            return b.m4304p();
        }
        String[] strArr = new String[]{str, "2"};
        Cursor query = this.f2332b.query("groups_users_table", null, "group_jid=? AND user_status=?", strArr, null, null, null);
        String str2 = "";
        if (query == null) {
            return str2;
        }
        if (query.moveToFirst() && query.getCount() > 0) {
            str2 = query.getString(query.getColumnIndex("contact_username"));
        }
        query.close();
        return str2;
    }

    public void m4659d(String str, String str2) {
        String[] strArr = new String[]{str, str2};
        this.f2332b.delete("groups_users_table", "group_jid=? AND contact_username=?", strArr);
    }
}
