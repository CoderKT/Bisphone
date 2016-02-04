package app.common.entity;

import app.Main;
import app.common.collection.ObservableCollectionItem;
import app.common.entity.ConversationNormalEntity.Builder;

public class ChatHistoryEntity extends ObservableCollectionItem implements Comparable<ChatHistoryEntity> {
    private ConversationNormalEntity f2089b;
    private ContactEntityLite f2090c;
    private boolean f2091d;
    private String f2092e;
    private String f2093f;
    private String f2094g;
    private String f2095h;

    public /* synthetic */ int compareTo(Object obj) {
        return m4162a((ChatHistoryEntity) obj);
    }

    public ChatHistoryEntity(ConversationNormalEntity conversationNormalEntity, ContactEntityLite contactEntityLite) {
        this.f2091d = false;
        this.f2092e = "";
        this.f2093f = "";
        this.f2094g = null;
        this.f2095h = "";
        this.f2089b = conversationNormalEntity;
        this.f2090c = contactEntityLite;
    }

    public ChatHistoryEntity(ConversationNormalEntity conversationNormalEntity, String str, String str2) {
        this.f2091d = false;
        this.f2092e = "";
        this.f2093f = "";
        this.f2094g = null;
        this.f2095h = "";
        this.f2089b = conversationNormalEntity;
        this.f2092e = str;
        this.f2095h = str2;
        this.f2093f = Main.f1927b.getString(2131296350);
        this.f2090c = null;
        this.f2091d = true;
    }

    public ChatHistoryEntity(ConversationNormalEntity conversationNormalEntity, String str, String str2, String str3, String str4) {
        this.f2091d = false;
        this.f2092e = "";
        this.f2093f = "";
        this.f2094g = null;
        this.f2095h = "";
        this.f2089b = conversationNormalEntity;
        this.f2092e = str;
        this.f2093f = str2;
        this.f2094g = str3;
        this.f2095h = str4;
        this.f2090c = null;
        this.f2091d = true;
    }

    public ChatHistoryEntity(HistoryNormalMessageEntity historyNormalMessageEntity, ContactEntityLite contactEntityLite) {
        this.f2091d = false;
        this.f2092e = "";
        this.f2093f = "";
        this.f2094g = null;
        this.f2095h = "";
        this.f2090c = contactEntityLite;
        this.f2089b = new Builder().m4319b(historyNormalMessageEntity.m4407a()).m4320b(historyNormalMessageEntity.m4412b()).m4316a(historyNormalMessageEntity.m4419f()).m4314a(Long.parseLong(historyNormalMessageEntity.m4419f())).m4321c(historyNormalMessageEntity.m4416d()).m4315a(historyNormalMessageEntity.m4418e()).m4322d(historyNormalMessageEntity.m4456M()).m4313a(0).m4318b(0).m4317a();
    }

    public boolean m4167b() {
        return this.f2091d;
    }

    public String m4168c() {
        return this.f2092e;
    }

    public ConversationNormalEntity m4170d() {
        return this.f2089b;
    }

    public ContactEntityLite m4171e() {
        return this.f2090c;
    }

    public int m4172f() {
        return this.f2089b.m4328b();
    }

    public String m4173g() {
        return this.f2093f;
    }

    public void m4165a(String str) {
        this.f2093f = str;
    }

    public String m4174h() {
        return this.f2094g == null ? "" : this.f2094g;
    }

    public void m4166b(String str) {
        this.f2094g = str;
    }

    public String m4175i() {
        return this.f2095h;
    }

    public void m4169c(String str) {
        this.f2095h = str;
    }

    public String m4176j() {
        return m4167b() ? m4168c() : this.f2089b.m4333d();
    }

    public void m4164a(ContactEntityLite contactEntityLite) {
        this.f2090c = contactEntityLite;
    }

    public void m4163a(int i) {
        this.f2089b.m4324a(i);
        this.f2089b.m4329b(i);
        m4056a();
    }

    public void m4177k() {
        this.f2089b.m4340j();
        m4056a();
    }

    public int m4162a(ChatHistoryEntity chatHistoryEntity) {
        if (chatHistoryEntity == null) {
            return -1;
        }
        return chatHistoryEntity.m4170d().m4336f().compareTo(m4170d().m4336f());
    }
}
