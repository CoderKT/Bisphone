package app.common.entity;

import app.Main;
import app.common.collection.ObservableCollectionItem;
import app.xmpp.packet.groupv2.GroupElement.Type;
import app.xmpp.packet.groupv2.SettingXE.NotificationState;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class ConversationGroupEntity extends ObservableCollectionItem implements Serializable, Comparable<ConversationGroupEntity> {
    private String f2168b;
    private String f2169c;
    private int f2170d;
    private String f2171e;
    private String f2172f;
    private String f2173g;
    private String f2174h;
    private String f2175i;
    private String f2176j;
    private String f2177k;
    private NotificationState f2178l;
    private String f2179m;
    private String f2180n;
    private long f2181o;
    private int f2182p;
    private String f2183q;
    private String f2184r;
    private Type f2185s;
    private GroupInfoState f2186t;
    private JSONObject f2187u;

    public class Builder {
        private String f2139a;
        private String f2140b;
        private int f2141c;
        private String f2142d;
        private String f2143e;
        private String f2144f;
        private String f2145g;
        private String f2146h;
        private String f2147i;
        private NotificationState f2148j;
        private String f2149k;
        private String f2150l;
        private GroupInfoState f2151m;
        private String f2152n;
        private Type f2153o;
        private String f2154p;
        private String f2155q;
        private JSONObject f2156r;
        private int f2157s;
        private long f2158t;

        public Builder() {
            this.f2156r = new JSONObject();
        }

        public Builder m4247a(String str) {
            if (str == null) {
                str = "0";
            }
            long parseLong = Long.parseLong(str);
            if (parseLong != 0 && (this.f2150l == null || parseLong >= Long.parseLong(this.f2150l))) {
                this.f2150l = str;
            }
            return this;
        }

        public Builder m4250b(String str) {
            this.f2149k = str;
            return this;
        }

        public Builder m4243a(long j) {
            this.f2158t = j;
            return this;
        }

        public Builder m4252c(String str) {
            this.f2139a = str;
            return this;
        }

        public Builder m4242a(int i) {
            this.f2141c = i;
            return this;
        }

        public Builder m4249b(int i) {
            this.f2157s = i;
            return this;
        }

        public Builder m4254d(String str) {
            this.f2142d = str;
            return this;
        }

        public Builder m4256e(String str) {
            this.f2146h = str;
            return this;
        }

        public Builder m4258f(String str) {
            this.f2147i = str;
            return this;
        }

        public Builder m4259g(String str) {
            this.f2140b = str;
            return this;
        }

        public Builder m4260h(String str) {
            this.f2143e = str;
            return this;
        }

        public Builder m4244a(GroupInfoState groupInfoState) {
            this.f2151m = groupInfoState;
            return this;
        }

        public Builder m4245a(Type type) {
            this.f2153o = type;
            return this;
        }

        public Builder m4261i(String str) {
            this.f2152n = str;
            return this;
        }

        public Builder m4262j(String str) {
            this.f2144f = str;
            return this;
        }

        public Builder m4263k(String str) {
            this.f2145g = str;
            return this;
        }

        public Builder m4246a(NotificationState notificationState) {
            this.f2148j = notificationState;
            return this;
        }

        public Builder m4264l(String str) {
            this.f2154p = str;
            return this;
        }

        public Builder m4265m(String str) {
            this.f2155q = str;
            return this;
        }

        public Builder m4266n(String str) {
            if (str == null) {
                this.f2156r = new JSONObject();
                try {
                    this.f2156r.put("group_size", 150);
                    this.f2156r.put("member_count", 1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    this.f2156r = new JSONObject(str);
                } catch (Throwable e2) {
                    Main.f1926a.m5684d(e2);
                }
            }
            return this;
        }

        public Builder m4251c(int i) {
            try {
                this.f2156r.put("member_count", i);
            } catch (Throwable e) {
                Main.f1926a.m5684d(e);
            }
            return this;
        }

        public Builder m4253d(int i) {
            try {
                int i2 = this.f2156r.getInt("group_size");
                if (i > i2 && i2 > 0) {
                    i = i2;
                }
                this.f2156r.put("all_invited_count", i);
            } catch (Throwable e) {
                Main.f1926a.m5684d(e);
            }
            return this;
        }

        public Builder m4255e(int i) {
            try {
                this.f2156r.put("user_invited_count", i);
            } catch (Throwable e) {
                Main.f1926a.m5684d(e);
            }
            return this;
        }

        public Builder m4257f(int i) {
            try {
                this.f2156r.put("group_size", i);
            } catch (Throwable e) {
                Main.f1926a.m5684d(e);
            }
            return this;
        }

        public ConversationGroupEntity m4248a() {
            return new ConversationGroupEntity(this.f2139a, this.f2140b, this.f2143e, this.f2144f, this.f2145g, this.f2150l, this.f2149k, this.f2148j, this.f2141c, this.f2142d, this.f2146h, this.f2147i, this.f2152n, this.f2153o, this.f2151m, this.f2154p, this.f2155q, this.f2156r, this.f2158t, this.f2157s);
        }
    }

    public enum GroupInfoState {
        All,
        TITLE_DESCRIPTION,
        AVATAR,
        TITLE,
        DESCRIPTION,
        TITLE_AVATAR,
        DESCRIPTION_AVATAR,
        NONE
    }

    public /* synthetic */ int compareTo(Object obj) {
        return m4267a((ConversationGroupEntity) obj);
    }

    public ConversationGroupEntity(String str, String str2, String str3, String str4, String str5, String str6, String str7, NotificationState notificationState, int i, String str8, String str9, String str10, String str11, Type type, GroupInfoState groupInfoState, String str12, String str13, JSONObject jSONObject, long j, int i2) {
        this.f2168b = str;
        this.f2169c = str2;
        this.f2172f = str3;
        this.f2173g = str4;
        this.f2174h = str5;
        this.f2179m = str6;
        this.f2180n = str7;
        this.f2178l = notificationState;
        this.f2170d = i;
        this.f2171e = str8;
        this.f2175i = str9;
        this.f2176j = str10;
        this.f2183q = str11;
        this.f2185s = type;
        this.f2186t = groupInfoState;
        this.f2177k = str12;
        this.f2184r = str13;
        this.f2187u = jSONObject;
        this.f2181o = j;
        this.f2182p = i2;
    }

    public String m4274b() {
        return this.f2169c;
    }

    public int m4277c() {
        return this.f2170d;
    }

    public String m4280d() {
        return this.f2171e;
    }

    public String m4283e() {
        return this.f2172f;
    }

    public String m4286f() {
        return this.f2173g;
    }

    public String m4289g() {
        return this.f2174h;
    }

    public String m4291h() {
        return this.f2175i;
    }

    public String m4293i() {
        return this.f2176j;
    }

    public NotificationState m4295j() {
        return this.f2178l;
    }

    public String m4297k() {
        return this.f2179m == null ? "0" : this.f2179m;
    }

    public String m4299l() {
        return this.f2180n == null ? "0" : this.f2180n;
    }

    public String m4301m() {
        return this.f2183q;
    }

    public Type m4302n() {
        return this.f2185s;
    }

    public GroupInfoState m4303o() {
        return this.f2186t;
    }

    public String m4304p() {
        return this.f2177k;
    }

    public String m4305q() {
        return this.f2184r;
    }

    public int m4306r() {
        return this.f2182p;
    }

    public long m4307s() {
        return this.f2181o;
    }

    public void m4273a(String str) {
        this.f2169c = str;
    }

    public void m4268a(int i) {
        this.f2170d = i;
        m4056a();
    }

    public void m4276b(String str) {
        this.f2171e = str;
    }

    public void m4279c(String str) {
        this.f2172f = str;
        m4056a();
    }

    public void m4282d(String str) {
        this.f2173g = str;
        m4056a();
    }

    public void m4285e(String str) {
        this.f2174h = str;
        m4056a();
    }

    public void m4288f(String str) {
        this.f2175i = str;
        m4056a();
    }

    public void m4290g(String str) {
        this.f2176j = str;
        m4056a();
    }

    public void m4272a(NotificationState notificationState) {
        this.f2178l = notificationState;
    }

    public void m4292h(String str) {
        long parseLong = Long.parseLong(str);
        if (parseLong == 0) {
            return;
        }
        if (this.f2179m == null || parseLong >= Long.parseLong(this.f2179m)) {
            this.f2179m = str;
            m4056a();
        }
    }

    public void m4294i(String str) {
        this.f2180n = str;
        m4056a();
    }

    public void m4296j(String str) {
        this.f2183q = str;
    }

    public void m4271a(Type type) {
        this.f2185s = type;
    }

    public void m4270a(GroupInfoState groupInfoState) {
        this.f2186t = groupInfoState;
    }

    public void m4298k(String str) {
        this.f2184r = str;
    }

    public JSONObject m4308t() {
        return this.f2187u;
    }

    public void m4300l(String str) {
        if (str == null || str.isEmpty()) {
            this.f2186t = GroupInfoState.NONE;
        } else if (str.contains("av") && str.contains("de") && str.contains("ti")) {
            this.f2186t = GroupInfoState.All;
        } else if (str.contains("av") && str.contains("de")) {
            this.f2186t = GroupInfoState.DESCRIPTION_AVATAR;
        } else if (str.contains("av") && str.contains("ti")) {
            this.f2186t = GroupInfoState.TITLE_AVATAR;
        } else if (str.contains("de") && str.contains("ti")) {
            this.f2186t = GroupInfoState.TITLE_DESCRIPTION;
        } else if (str.contains("ti")) {
            this.f2186t = GroupInfoState.TITLE;
        } else if (str.contains("de")) {
            this.f2186t = GroupInfoState.DESCRIPTION;
        } else if (str.contains("av")) {
            this.f2186t = GroupInfoState.AVATAR;
        }
    }

    public void m4269a(long j) {
        this.f2181o = j;
    }

    public void m4275b(int i) {
        this.f2182p = i;
    }

    public int m4309u() {
        try {
            return this.f2187u.getInt("member_count");
        } catch (Throwable e) {
            Main.f1926a.m5684d(e);
            return 0;
        }
    }

    public int m4310v() {
        try {
            return this.f2187u.getInt("all_invited_count");
        } catch (Throwable e) {
            Main.f1926a.m5684d(e);
            return 0;
        }
    }

    public int m4311w() {
        try {
            return this.f2187u.getInt("user_invited_count");
        } catch (Throwable e) {
            Main.f1926a.m5684d(e);
            return 0;
        }
    }

    public int m4312x() {
        try {
            return this.f2187u.getInt("group_size");
        } catch (Throwable e) {
            Main.f1926a.m5684d(e);
            return 150;
        }
    }

    public void m4278c(int i) {
        try {
            this.f2187u.put("member_count", i);
        } catch (Throwable e) {
            Main.f1926a.m5684d(e);
        }
    }

    public void m4281d(int i) {
        if (i > m4312x() && m4312x() > 0) {
            i = m4312x();
        }
        try {
            this.f2187u.put("all_invited_count", i);
        } catch (Throwable e) {
            Main.f1926a.m5684d(e);
        }
    }

    public void m4284e(int i) {
        try {
            this.f2187u.put("user_invited_count", i);
        } catch (Throwable e) {
            Main.f1926a.m5684d(e);
        }
    }

    public void m4287f(int i) {
        try {
            this.f2187u.put("group_size", i);
        } catch (Throwable e) {
            Main.f1926a.m5684d(e);
        }
    }

    public int m4267a(ConversationGroupEntity conversationGroupEntity) {
        long parseLong = Long.parseLong(m4299l());
        long parseLong2 = Long.parseLong(conversationGroupEntity.m4299l());
        if (parseLong < parseLong2) {
            return -1;
        }
        return parseLong == parseLong2 ? 0 : 1;
    }
}
