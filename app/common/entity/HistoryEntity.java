package app.common.entity;

import app.Main;
import org.json.JSONException;
import org.json.JSONObject;

public class HistoryEntity implements Comparable<HistoryEntity> {
    protected JSONObject f2215a;
    private long f2216b;
    private String f2217c;
    private String f2218d;
    private String f2219e;
    private Type f2220f;
    private String f2221g;
    private DeliveryStatus f2222h;
    private String f2223i;

    public class Builder {
        private long f2206a;
        private String f2207b;
        private String f2208c;
        private String f2209d;
        private Type f2210e;
        private String f2211f;
        private DeliveryStatus f2212g;
        private String f2213h;
        private JSONObject f2214i;

        public Builder() {
            this.f2214i = new JSONObject();
        }

        public Builder m4351a(long j) {
            this.f2206a = j;
            return this;
        }

        public Builder m4359a(String str) {
            this.f2207b = str;
            return this;
        }

        public Builder m4365b(String str) {
            this.f2208c = str;
            return this;
        }

        public Builder m4369c(String str) {
            this.f2209d = str;
            return this;
        }

        public Builder m4356a(Type type) {
            this.f2210e = type;
            return this;
        }

        public Builder m4372d(String str) {
            this.f2211f = str;
            return this;
        }

        public Builder m4353a(DeliveryStatus deliveryStatus) {
            this.f2212g = deliveryStatus;
            return this;
        }

        public Builder m4376e(String str) {
            this.f2213h = str;
            return this;
        }

        public Builder m4378f(String str) {
            if (str == null) {
                this.f2214i = null;
            } else {
                try {
                    this.f2214i = new JSONObject(str);
                } catch (Throwable e) {
                    Main.f1926a.m5684d(e);
                }
            }
            return this;
        }

        public Builder m4350a(int i) {
            try {
                this.f2214i.put("width", i);
            } catch (Throwable e) {
                Main.f1926a.m5684d(e);
            }
            return this;
        }

        public Builder m4361b(int i) {
            try {
                this.f2214i.put("height", i);
            } catch (Throwable e) {
                Main.f1926a.m5684d(e);
            }
            return this;
        }

        public Builder m4379g(String str) {
            try {
                this.f2214i.put("caption", str);
            } catch (Throwable e) {
                Main.f1926a.m5684d(e);
            }
            return this;
        }

        public Builder m4380h(String str) {
            try {
                this.f2214i.put("token", str);
            } catch (Throwable e) {
                Main.f1926a.m5684d(e);
            }
            return this;
        }

        public Builder m4368c(int i) {
            try {
                this.f2214i.put("duration", i);
            } catch (Throwable e) {
                Main.f1926a.m5684d(e);
            }
            return this;
        }

        public Builder m4371d(int i) {
            try {
                this.f2214i.put("size", i);
            } catch (Throwable e) {
                Main.f1926a.m5684d(e);
            }
            return this;
        }

        public Builder m4381i(String str) {
            try {
                this.f2214i.put("short_address", str);
            } catch (Throwable e) {
                Main.f1926a.m5684d(e);
            }
            return this;
        }

        public Builder m4382j(String str) {
            try {
                this.f2214i.put("long_address", str);
            } catch (Throwable e) {
                Main.f1926a.m5684d(e);
            }
            return this;
        }

        public Builder m4358a(Double d) {
            try {
                this.f2214i.put("latitude", d);
            } catch (Throwable e) {
                Main.f1926a.m5684d(e);
            }
            return this;
        }

        public Builder m4364b(Double d) {
            try {
                this.f2214i.put("longitude", d);
            } catch (Throwable e) {
                Main.f1926a.m5684d(e);
            }
            return this;
        }

        public Builder m4352a(CallStatus callStatus) {
            try {
                this.f2214i.put("call_status", callStatus);
            } catch (Throwable e) {
                Main.f1926a.m5684d(e);
            }
            return this;
        }

        public Builder m4354a(GroupStatus groupStatus) {
            try {
                this.f2214i.put("group_status", groupStatus);
            } catch (Throwable e) {
                Main.f1926a.m5684d(e);
            }
            return this;
        }

        public Builder m4383k(String str) {
            try {
                this.f2214i.put("group_events", str);
            } catch (Throwable e) {
                Main.f1926a.m5684d(e);
            }
            return this;
        }

        public Builder m4384l(String str) {
            try {
                this.f2214i.put("group_events", str);
            } catch (Throwable e) {
                Main.f1926a.m5684d(e);
            }
            return this;
        }

        public Builder m4385m(String str) {
            try {
                this.f2214i.put("group_events", str);
            } catch (Throwable e) {
                Main.f1926a.m5684d(e);
            }
            return this;
        }

        public Builder m4386n(String str) {
            try {
                this.f2214i.put("changed_info", str);
            } catch (Throwable e) {
                Main.f1926a.m5684d(e);
            }
            return this;
        }

        public Builder m4375e(int i) {
            try {
                this.f2214i.put("sticker_id", i);
            } catch (Throwable e) {
                Main.f1926a.m5684d(e);
            }
            return this;
        }

        public Builder m4377f(int i) {
            try {
                this.f2214i.put("sticker_pack_id", i);
            } catch (Throwable e) {
                Main.f1926a.m5684d(e);
            }
            return this;
        }

        public Builder m4357a(app.xmpp.packet.channel.CastPX.Type type) {
            try {
                this.f2214i.put("cast", type);
            } catch (Throwable e) {
                Main.f1926a.m5684d(e);
            }
            return this;
        }

        public Builder m4387o(String str) {
            try {
                this.f2214i.put("changed_title", str);
            } catch (Throwable e) {
                Main.f1926a.m5684d(e);
            }
            return this;
        }

        public Builder m4388p(String str) {
            try {
                this.f2214i.put("changed_desc", str);
            } catch (Throwable e) {
                Main.f1926a.m5684d(e);
            }
            return this;
        }

        public Builder m4355a(RelationType relationType) {
            try {
                this.f2214i.put("relation_type", relationType.ordinal());
            } catch (Throwable e) {
                Main.f1926a.m5684d(e);
            }
            return this;
        }

        public Builder m4389q(String str) {
            try {
                this.f2214i.put("related_id", str);
            } catch (Throwable e) {
                Main.f1926a.m5684d(e);
            }
            return this;
        }

        public Builder m4363b(app.xmpp.packet.channel.CastPX.Type type) {
            try {
                this.f2214i.put("related_cast", type);
            } catch (Throwable e) {
                Main.f1926a.m5684d(e);
            }
            return this;
        }

        public Builder m4390r(String str) {
            try {
                this.f2214i.put("related_data", str);
            } catch (Throwable e) {
                Main.f1926a.m5684d(e);
            }
            return this;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public app.common.entity.HistoryEntity.Builder m4391s(java.lang.String r3) {
            /*
            r2 = this;
            if (r3 == 0) goto L_0x0008;
        L_0x0002:
            r0 = r3.isEmpty();	 Catch:{ JSONException -> 0x0018 }
            if (r0 == 0) goto L_0x0010;
        L_0x0008:
            r0 = app.account.AccountManager.m3934a();	 Catch:{ JSONException -> 0x0018 }
            r3 = r0.m3937c();	 Catch:{ JSONException -> 0x0018 }
        L_0x0010:
            r0 = r2.f2214i;	 Catch:{ JSONException -> 0x0018 }
            r1 = "related_username";
            r0.put(r1, r3);	 Catch:{ JSONException -> 0x0018 }
        L_0x0017:
            return r2;
        L_0x0018:
            r0 = move-exception;
            r1 = app.Main.f1926a;
            r1.m5684d(r0);
            goto L_0x0017;
            */
            throw new UnsupportedOperationException("Method not decompiled: app.common.entity.HistoryEntity.Builder.s(java.lang.String):app.common.entity.HistoryEntity$Builder");
        }

        public Builder m4362b(Type type) {
            try {
                this.f2214i.put("related_type", type.ordinal());
            } catch (Throwable e) {
                Main.f1926a.m5684d(e);
            }
            return this;
        }

        public HistoryEntity m4366b() {
            return new HistoryEntity(this);
        }

        public app.common.entity.HistoryNormalMessageEntity.Builder m4370c() {
            app.common.entity.HistoryNormalMessageEntity.Builder builder = new app.common.entity.HistoryNormalMessageEntity.Builder();
            builder.m4351a(this.f2206a);
            builder.m4359a(this.f2207b);
            builder.m4365b(this.f2208c);
            builder.m4369c(this.f2209d);
            builder.m4356a(this.f2210e);
            builder.m4353a(this.f2212g);
            builder.m4376e(this.f2213h);
            builder.m4372d(this.f2211f);
            builder.m4378f(this.f2214i.toString());
            return builder;
        }

        public app.common.entity.HistoryGroupEntity.Builder m4373d() {
            app.common.entity.HistoryGroupEntity.Builder builder = new app.common.entity.HistoryGroupEntity.Builder();
            builder.m4351a(this.f2206a);
            builder.m4359a(this.f2207b);
            builder.m4365b(this.f2208c);
            builder.m4369c(this.f2209d);
            builder.m4356a(this.f2210e);
            builder.m4353a(this.f2212g);
            builder.m4376e(this.f2213h);
            builder.m4372d(this.f2211f);
            builder.m4378f(this.f2214i.toString());
            return builder;
        }

        public app.common.entity.HistoryChannelEntity.Builder m4374e() {
            app.common.entity.HistoryChannelEntity.Builder builder = new app.common.entity.HistoryChannelEntity.Builder();
            builder.m4351a(this.f2206a);
            builder.m4359a(this.f2207b);
            builder.m4365b(this.f2208c);
            builder.m4369c(this.f2209d);
            builder.m4356a(this.f2210e);
            builder.m4353a(this.f2212g);
            builder.m4376e(this.f2213h);
            builder.m4372d(this.f2211f);
            builder.m4378f(this.f2214i.toString());
            return builder;
        }

        public HistoryEntity m4367b(long j) {
            Builder builder = new app.common.entity.HistoryChannelEntity.Builder();
            builder.m4356a(Type.NEW_MESSAGE_DECLARATION);
            builder.m4351a(-120);
            builder.m4359a(null);
            builder.m4365b(null);
            builder.m4369c(null);
            builder.m4353a(null);
            builder.m4376e(null);
            builder.m4372d("" + j);
            builder.m4378f(null);
            return new HistoryEntity(builder);
        }

        public HistoryEntity m4360a(String str, String str2) {
            Builder builder = new app.common.entity.HistoryChannelEntity.Builder();
            builder.m4356a(Type.TIMESTAMP);
            builder.m4351a(Long.parseLong(str));
            builder.m4359a(null);
            builder.m4365b(null);
            builder.m4369c(str2);
            builder.m4353a(null);
            builder.m4376e(null);
            builder.m4372d(str);
            builder.m4378f(null);
            return new HistoryEntity(builder);
        }
    }

    public enum CallStatus {
        MISSED,
        INCOMING,
        OUTGOING;

        public static CallStatus m4441a(String str) {
            try {
                return valueOf(str.toUpperCase());
            } catch (Exception e) {
                return null;
            }
        }
    }

    public enum DeliveryStatus {
        UPLOADING,
        FAILED_TO_UPLOAD,
        SENDING,
        FAILED_TO_SEND,
        SENT,
        DELIVERED,
        DRAFT,
        RECEIVED
    }

    public enum GroupStatus {
        CREATE,
        JOIN,
        LEAVE,
        CHANGE_SUBJECT,
        KICK,
        CHANGE_INFO
    }

    public enum RelationType {
        NONE,
        LINK,
        REPLY
    }

    public enum Type {
        TEXT,
        PHOTO,
        VIDEO,
        AUDIO,
        MAP,
        CALL,
        STICKER,
        GROUP_STATUS,
        KICK,
        LEAVE,
        CHANGE_GROUP_INFO,
        NEW_MESSAGE_DECLARATION,
        TIMESTAMP;

        public static Type m4442a(String str) {
            try {
                return valueOf(str.toUpperCase());
            } catch (Exception e) {
                return TEXT;
            }
        }
    }

    public /* synthetic */ int compareTo(Object obj) {
        return m4411b((HistoryEntity) obj);
    }

    public long m4407a() {
        return this.f2216b;
    }

    public String m4412b() {
        return this.f2217c;
    }

    public String m4414c() {
        return this.f2218d;
    }

    public String m4416d() {
        return this.f2219e;
    }

    public Type m4418e() {
        return this.f2220f;
    }

    public String m4419f() {
        return this.f2221g;
    }

    public DeliveryStatus m4420g() {
        return this.f2222h;
    }

    public boolean m4421h() {
        return this.f2222h == DeliveryStatus.RECEIVED;
    }

    public boolean m4422i() {
        return (this.f2222h == DeliveryStatus.RECEIVED || this.f2222h == DeliveryStatus.DRAFT) ? false : true;
    }

    public String m4423j() {
        return this.f2223i;
    }

    public JSONObject m4424k() {
        return this.f2215a;
    }

    public void m4408a(long j) {
        this.f2216b = j;
    }

    public void m4410a(String str) {
        this.f2217c = str;
    }

    public void m4413b(String str) {
        this.f2218d = str;
    }

    public void m4415c(String str) {
        this.f2221g = str;
    }

    public void m4409a(DeliveryStatus deliveryStatus) {
        this.f2222h = deliveryStatus;
    }

    public void m4417d(String str) {
        this.f2223i = str;
    }

    public int m4425l() {
        try {
            return this.f2215a.getInt("width");
        } catch (Throwable e) {
            Main.f1926a.m5684d(e);
            return 0;
        }
    }

    public String m4426m() {
        try {
            return this.f2215a.getString("caption");
        } catch (JSONException e) {
            return "";
        }
    }

    public int m4427n() {
        try {
            return this.f2215a.getInt("height");
        } catch (Throwable e) {
            Main.f1926a.m5684d(e);
            return 0;
        }
    }

    public int m4428o() {
        try {
            return this.f2215a.getInt("duration");
        } catch (Throwable e) {
            Main.f1926a.m5684d(e);
            return 0;
        }
    }

    public int m4429p() {
        try {
            return this.f2215a.getInt("size");
        } catch (Throwable e) {
            Main.f1926a.m5684d(e);
            return 0;
        }
    }

    public String m4430q() {
        try {
            return this.f2215a.getString("token");
        } catch (Throwable e) {
            Main.f1926a.m5684d(e);
            return null;
        }
    }

    public String m4431r() {
        String str = null;
        try {
            str = this.f2215a.getString("short_address");
        } catch (Throwable e) {
            Main.f1926a.m5684d(e);
        }
        return str;
    }

    public String m4432s() {
        String str = null;
        try {
            str = this.f2215a.getString("long_address");
        } catch (Throwable e) {
            Main.f1926a.m5684d(e);
        }
        return str;
    }

    public Double m4433t() {
        Double d = null;
        try {
            d = Double.valueOf(this.f2215a.getDouble("latitude"));
        } catch (Throwable e) {
            Main.f1926a.m5684d(e);
        }
        return d;
    }

    public Double m4434u() {
        Double d = null;
        try {
            d = Double.valueOf(this.f2215a.getDouble("longitude"));
        } catch (Throwable e) {
            Main.f1926a.m5684d(e);
        }
        return d;
    }

    public String m4435v() {
        try {
            return this.f2215a.getString("call_status");
        } catch (Throwable e) {
            Main.f1926a.m5684d(e);
            return null;
        }
    }

    public GroupStatus m4436w() {
        try {
            return GroupStatus.valueOf(this.f2215a.getString("group_status"));
        } catch (Throwable e) {
            Main.f1926a.m5684d(e);
            return null;
        }
    }

    public String m4437x() {
        try {
            return this.f2215a.getString("group_events");
        } catch (Throwable e) {
            Main.f1926a.m5684d(e);
            return null;
        }
    }

    public String m4438y() {
        try {
            return this.f2215a.getString("group_events");
        } catch (Throwable e) {
            Main.f1926a.m5684d(e);
            return null;
        }
    }

    public String m4439z() {
        try {
            return this.f2215a.getString("group_events");
        } catch (Throwable e) {
            Main.f1926a.m5684d(e);
            return null;
        }
    }

    public String m4395A() {
        try {
            return this.f2215a.getString("changed_info");
        } catch (Throwable e) {
            Main.f1926a.m5684d(e);
            return null;
        }
    }

    public int m4396B() {
        try {
            return this.f2215a.getInt("sticker_id");
        } catch (Throwable e) {
            Main.f1926a.m5684d(e);
            return 0;
        }
    }

    public int m4397C() {
        try {
            return this.f2215a.getInt("sticker_pack_id");
        } catch (Throwable e) {
            Main.f1926a.m5684d(e);
            return 0;
        }
    }

    public app.xmpp.packet.channel.CastPX.Type m4398D() {
        try {
            return app.xmpp.packet.channel.CastPX.Type.m7539a(this.f2215a.getString("cast"));
        } catch (Throwable e) {
            Main.f1926a.m5684d(e);
            return null;
        }
    }

    public String m4399E() {
        try {
            return this.f2215a.getString("changed_title");
        } catch (Throwable e) {
            Main.f1926a.m5684d(e);
            return null;
        }
    }

    public String m4400F() {
        try {
            return this.f2215a.getString("changed_desc");
        } catch (Throwable e) {
            Main.f1926a.m5684d(e);
            return null;
        }
    }

    public RelationType m4401G() {
        try {
            return RelationType.values()[this.f2215a.getInt("relation_type")];
        } catch (Throwable e) {
            Main.f1926a.m5684d(e);
            return RelationType.NONE;
        }
    }

    public String m4402H() {
        try {
            return this.f2215a.getString("related_id");
        } catch (Throwable e) {
            Main.f1926a.m5684d(e);
            return null;
        }
    }

    public app.xmpp.packet.common.CastXE.Type m4403I() {
        try {
            return app.xmpp.packet.common.CastXE.Type.m7570a(this.f2215a.getString("related_cast"));
        } catch (Throwable e) {
            Main.f1926a.m5684d(e);
            return null;
        }
    }

    public String m4404J() {
        try {
            return this.f2215a.getString("related_data");
        } catch (Throwable e) {
            Main.f1926a.m5684d(e);
            return null;
        }
    }

    public String m4405K() {
        try {
            return this.f2215a.getString("related_username");
        } catch (Throwable e) {
            Main.f1926a.m5684d(e);
            return null;
        }
    }

    public Type m4406L() {
        try {
            return Type.values()[this.f2215a.getInt("related_type")];
        } catch (Throwable e) {
            Main.f1926a.m5684d(e);
            return Type.TEXT;
        }
    }

    protected HistoryEntity(Builder builder) {
        this.f2216b = builder.f2206a;
        this.f2217c = builder.f2207b;
        this.f2218d = builder.f2208c;
        this.f2219e = builder.f2209d;
        this.f2220f = builder.f2210e;
        this.f2221g = builder.f2211f;
        this.f2222h = builder.f2212g;
        this.f2223i = builder.f2213h;
        this.f2215a = builder.f2214i;
    }

    public int m4411b(HistoryEntity historyEntity) {
        return m4419f().compareTo(historyEntity.m4419f());
    }

    public static String m4394a(String str, String str2, Double d, Double d2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("short_address", str);
            jSONObject.put("long_address", str2);
            jSONObject.put("latitude", d);
            jSONObject.put("longitude", d2);
            return jSONObject.toString();
        } catch (Throwable e) {
            Main.f1926a.m5684d(e);
            return null;
        }
    }
}
