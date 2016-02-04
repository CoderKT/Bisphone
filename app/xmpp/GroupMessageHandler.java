package app.xmpp;

import android.content.Context;
import app.Main;
import app.account.AccountManager;
import app.common.GroupV2FetchHistoryModel;
import app.common.entity.ConversationGroupEntity;
import app.common.entity.ConversationGroupEntity.GroupInfoState;
import app.common.entity.HistoryEntity;
import app.common.entity.HistoryEntity.DeliveryStatus;
import app.common.entity.HistoryEntity.GroupStatus;
import app.common.entity.HistoryGroupEntity;
import app.common.entity.HistoryGroupEntity.Builder;
import app.database.datasource.ConversationGroupDataSource;
import app.database.datasource.GroupUsersDatasource;
import app.database.datasource.HistoryGroupDataSource;
import app.events.group.GroupMessageEvent;
import app.events.group.InvitationResponseUiEvent;
import app.events.group.KickResponseUiEvent;
import app.events.group.LeaveMemberEvent;
import app.events.group.UserStatusChangedEvent;
import app.events.xmpp.MessageSendStatusEvent;
import app.localization.Language;
import app.localization.Localize;
import app.messaging.vh.DisplayDataHandler;
import app.notification.BadgeHandler;
import app.notification.NotificationCenter;
import app.util.FileUtil;
import app.xmpp.packet.common.ContentXE;
import app.xmpp.packet.common.ContentXE.Type;
import app.xmpp.packet.common.TimeXE;
import app.xmpp.packet.groupv2.EventXE;
import app.xmpp.packet.groupv2.EventXE.Action;
import app.xmpp.packet.groupv2.GroupElement;
import app.xmpp.packet.groupv2.InfoXE;
import app.xmpp.packet.groupv2.OneToOneEventXE;
import app.xmpp.packet.groupv2.OneToOneEventXE.OneToOneEventAction;
import app.xmpp.packet.groupv2.SettingXE.NotificationState;
import app.xmpp.packet.media.AudioPX;
import app.xmpp.packet.media.LocationPX;
import app.xmpp.packet.media.PhotoPX;
import app.xmpp.packet.media.StickerPX;
import app.xmpp.packet.media.VideoPX;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import java.util.List;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.delay.DelayInformationManager;
import org.jivesoftware.smackx.time.packet.Time;
import se.emilsjolander.stickylistheaders.C1128R;

public class GroupMessageHandler extends MessageHandler {
    private static GroupMessageHandler f4929d;
    String f4930a;
    private Context f4931e;

    /* renamed from: app.xmpp.GroupMessageHandler.1 */
    /* synthetic */ class C05481 {
        static final /* synthetic */ int[] f4923a;
        static final /* synthetic */ int[] f4924b;
        static final /* synthetic */ int[] f4925c;
        static final /* synthetic */ int[] f4926d;

        static {
            f4926d = new int[Action.values().length];
            try {
                f4926d[Action.j.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f4926d[Action.k.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f4926d[Action.l.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f4926d[Action.c.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            f4925c = new int[OneToOneEventAction.values().length];
            try {
                f4925c[OneToOneEventAction.i.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f4925c[OneToOneEventAction.d.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f4925c[OneToOneEventAction.k.ordinal()] = 3;
            } catch (NoSuchFieldError e7) {
            }
            f4924b = new int[Type.values().length];
            try {
                f4924b[Type.conversation.ordinal()] = 1;
            } catch (NoSuchFieldError e8) {
            }
            try {
                f4924b[Type.event.ordinal()] = 2;
            } catch (NoSuchFieldError e9) {
            }
            f4923a = new int[Message.Type.values().length];
            try {
                f4923a[Message.Type.error.ordinal()] = 1;
            } catch (NoSuchFieldError e10) {
            }
            try {
                f4923a[Message.Type.headline.ordinal()] = 2;
            } catch (NoSuchFieldError e11) {
            }
            try {
                f4923a[Message.Type.normal.ordinal()] = 3;
            } catch (NoSuchFieldError e12) {
            }
            try {
                f4923a[Message.Type.chat.ordinal()] = 4;
            } catch (NoSuchFieldError e13) {
            }
        }
    }

    static {
        f4929d = null;
    }

    private GroupMessageHandler(Context context) {
        super(context);
        this.f4931e = context;
        this.f4930a = AccountManager.m3934a().m3937c();
    }

    public static synchronized GroupMessageHandler m7379a() {
        GroupMessageHandler groupMessageHandler;
        synchronized (GroupMessageHandler.class) {
            if (f4929d == null) {
                f4929d = new GroupMessageHandler(Main.f1927b);
            }
            groupMessageHandler = f4929d;
        }
        return groupMessageHandler;
    }

    private Builder m7378a(Stanza stanza) {
        String str = (System.currentTimeMillis() * 1000) + "";
        Message message = (Message) stanza;
        String from = message.getFrom();
        Main.f1926a.m5681c("Message from: " + from);
        JabberId a = JabberId.m7386a(from);
        if (a == null) {
            Main.f1926a.m5679b("Invalid Jabber ID, RETURN!");
            return null;
        }
        ExtensionElement extension = message.getExtension(Time.ELEMENT, "bpn:common:v1");
        if (extension != null) {
            from = ((TimeXE) extension).m7579a() + "";
            Main.f1926a.m5681c("TimeXE with timestamp: " + from);
        } else {
            from = str;
        }
        String c = a.m7389c();
        String d = a.m7390d();
        String stanzaId = stanza.getStanzaId();
        switch (C05481.f4923a[message.getType().ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                Main.f1926a.m5685e("XMPP Message Error : " + message.getError());
                return null;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                Main.f1926a.m5685e("XMPP Message Headline " + message.toString());
                return null;
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                Main.f1926a.m5685e("XMPP Message Normal " + message.toString());
                return null;
            default:
                String body = message.getBody();
                if (body == null || "".equals(body)) {
                    return null;
                }
                HistoryEntity.Type type;
                Builder d2;
                HistoryEntity.Type type2 = HistoryEntity.Type.TEXT;
                HistoryEntity.Builder builder = new HistoryEntity.Builder();
                m7376a(message, builder);
                ExtensionElement extension2 = message.getExtension("media", "http://bisphone.com/protocol/media");
                if (extension2 != null) {
                    if (extension2 instanceof StickerPX) {
                        type2 = HistoryEntity.Type.STICKER;
                        StickerPX stickerPX = (StickerPX) extension2;
                        builder.m4375e(stickerPX.m7698a());
                        builder.m4377f(stickerPX.m7699b());
                        type = type2;
                    } else if (extension2 instanceof PhotoPX) {
                        type2 = HistoryEntity.Type.PHOTO;
                        PhotoPX photoPX = (PhotoPX) extension2;
                        builder.m4380h(photoPX.m7695c());
                        builder.m4361b(photoPX.m7694b());
                        builder.m4350a(photoPX.m7693a());
                        builder.m4379g(photoPX.m7697e());
                        String d3 = FileUtil.m7028d(photoPX.m7696d());
                        if (d3 == null) {
                            builder.m4376e(photoPX.m7696d());
                        } else {
                            builder.m4376e(d3);
                        }
                        type = type2;
                    } else if (extension2 instanceof AudioPX) {
                        type2 = HistoryEntity.Type.AUDIO;
                        AudioPX audioPX = (AudioPX) extension2;
                        builder.m4380h(audioPX.m7681c());
                        builder.m4368c(audioPX.m7680b());
                        builder.m4371d(audioPX.m7679a());
                        type = type2;
                    } else if (extension2 instanceof VideoPX) {
                        type2 = HistoryEntity.Type.VIDEO;
                        VideoPX videoPX = (VideoPX) extension2;
                        builder.m4380h(videoPX.m7704e());
                        builder.m4361b(videoPX.m7701b());
                        builder.m4350a(videoPX.m7700a());
                        builder.m4368c(videoPX.m7703d());
                        builder.m4371d(videoPX.m7702c());
                        builder.m4376e(videoPX.m7705f());
                        builder.m4379g(videoPX.m7706g());
                        type = type2;
                    } else if (extension2 instanceof LocationPX) {
                        type2 = HistoryEntity.Type.MAP;
                        LocationPX locationPX = (LocationPX) extension2;
                        builder.m4358a(Double.valueOf(locationPX.m7684c()));
                        builder.m4364b(Double.valueOf(locationPX.m7685d()));
                        builder.m4381i(locationPX.m7682a());
                        builder.m4382j(locationPX.m7683b());
                        builder.m4376e(locationPX.m7686e());
                        type = type2;
                    } else {
                        Main.f1926a.m5679b("MediaExtension of unknown type!");
                    }
                    builder.m4365b(d).m4353a(DeliveryStatus.RECEIVED).m4359a(stanzaId).m4369c(body).m4372d(from).m4356a(type);
                    d2 = builder.m4373d();
                    d2.m4447t(c);
                    return d2;
                }
                type = type2;
                builder.m4365b(d).m4353a(DeliveryStatus.RECEIVED).m4359a(stanzaId).m4369c(body).m4372d(from).m4356a(type);
                d2 = builder.m4373d();
                d2.m4447t(c);
                return d2;
        }
    }

    public long m7383a(List<Message> list) {
        if (list == null || list.size() == 0) {
            return 0;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        String c = AccountManager.m3934a().m3937c();
        for (Message message : list) {
            ExtensionElement extension = message.getExtension("content", "bpn:common:v1");
            if (extension != null) {
                Builder a;
                switch (C05481.f4924b[((ContentXE) extension).m7573a().ordinal()]) {
                    case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                        a = m7378a((Stanza) message);
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                        if (message.getType() != Message.Type.chat) {
                            if (DelayInformationManager.getDelayInformation(message) == null) {
                                a = m7382b(message);
                                break;
                            }
                            a = null;
                            break;
                        }
                        a = m7377a(message);
                        break;
                    default:
                        a = null;
                        break;
                }
                if (a != null) {
                    HistoryGroupEntity a2 = a.m4445a();
                    arrayList.add(a2);
                    if (!a2.m4449M().equals(c) || a2.m4436w() == GroupStatus.CHANGE_INFO) {
                        arrayList2.add(a2);
                    } else {
                        HistoryGroupDataSource.m4688a(this.f4931e, a2.m4412b(), a2.m4419f());
                        EventBus.m12779a().m12795d(new MessageSendStatusEvent(a2.m4412b(), DeliveryStatus.SENT, a2.m4419f()));
                    }
                }
            }
        }
        if (arrayList.size() == 0) {
            return 0;
        }
        if (!arrayList2.isEmpty()) {
            JabberId a3 = JabberId.m7386a(((HistoryGroupEntity) arrayList.get(0)).m4414c());
            EventBus.m12779a().m12795d(new GroupMessageEvent(a3 == null ? "" : a3.m7392f(), arrayList2));
            BadgeHandler.m6597a().m6603a(arrayList.size());
            NotificationCenter.m6606a().m6612a(arrayList);
        }
        return HistoryGroupDataSource.m4691a().m4702a(arrayList);
    }

    private Builder m7377a(Message message) {
        if (message != null) {
            JabberId a = JabberId.m7386a(message.getFrom());
            if (a != null) {
                long currentTimeMillis = System.currentTimeMillis() * 1000;
                TimeXE timeXE = (TimeXE) message.getExtension(Time.ELEMENT, "bpn:common:v1");
                if (timeXE != null) {
                    currentTimeMillis = Long.parseLong(timeXE.m7579a());
                }
                OneToOneEventXE oneToOneEventXE = (OneToOneEventXE) message.getExtension("oto-event", "bpn:group:v2");
                if (oneToOneEventXE != null) {
                    switch (C05481.f4925c[oneToOneEventXE.m7673b().ordinal()]) {
                        case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                            if (a.m7389c() != null) {
                                GroupElement a2 = oneToOneEventXE.m7592a();
                                ConversationGroupEntity.Builder l = new ConversationGroupEntity.Builder().m4259g(a.m7392f()).m4262j(a2.m7611c()).m4260h("INVITED").m4247a(currentTimeMillis + "").m4250b(currentTimeMillis + "").m4246a(NotificationState.sound).m4261i(a2.m7613d()).m4245a(a2.m7609b()).m4263k(a2.m7617f()).m4244a(GroupInfoState.NONE).m4253d(0).m4255e(0).m4257f(150).m4251c(1).m4264l(a2.m7615e());
                                GroupManager.m7323a().m7367d(l.m4248a());
                                ConversationGroupDataSource.m4587a().m4589a(Main.f1927b, l.m4248a());
                                GroupUsersDatasource.m4639a().m4644a(Main.f1927b, a.m7392f(), a.m7389c());
                                if (a2.m7609b() != GroupElement.Type.unmoderated) {
                                    GroupUsersDatasource.m4639a().m4653b(a.m7392f(), a2.m7615e());
                                }
                                NotificationCenter.m6606a().m6611a(a.m7392f(), a.m7389c());
                                break;
                            }
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                            GroupManager.m7323a().m7358b(a.m7392f()).m4279c("DESTROYED");
                            GroupUsersDatasource.m4639a().m4658d(a.m7392f());
                            ConversationGroupDataSource.m4587a().m4595a(Main.f1927b, a.m7392f(), "DESTROYED", currentTimeMillis);
                            EventBus.m12779a().m12795d(new UserStatusChangedEvent("DESTROYED", a.m7392f()));
                            GroupManager.m7323a().f4905c.remove(a.m7392f());
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                            GroupManager.m7323a().f4906d.remove(a.m7392f());
                            GroupManager.m7323a().m7358b(a.m7392f()).m4279c("KICKED");
                            ConversationGroupDataSource.m4587a().m4595a(Main.f1927b, a.m7392f(), "KICKED", currentTimeMillis);
                            GroupManager.m7323a().f4905c.remove(a.m7392f());
                            EventBus.m12779a().m12795d(new UserStatusChangedEvent("KICKED", a.m7392f()));
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        return null;
    }

    private Builder m7382b(Message message) {
        if (message == null) {
            return null;
        }
        JabberId a = JabberId.m7386a(message.getFrom());
        long currentTimeMillis = 1000 * System.currentTimeMillis();
        Builder builder = new Builder();
        if (a == null) {
            return null;
        }
        ExtensionElement extension = message.getExtension(Time.ELEMENT, "bpn:common:v1");
        if (extension != null) {
            long parseLong = Long.parseLong(((TimeXE) extension).m7579a());
            Main.f1926a.m5681c("TimePX with timestamp: " + parseLong);
            currentTimeMillis = parseLong;
        }
        String str = "";
        ExtensionElement extension2 = message.getExtension("event", "bpn:group:v2");
        if (extension2 == null) {
            return null;
        }
        JabberId a2 = JabberId.m7386a(((EventXE) extension2).m7599b());
        if (a2 == null) {
            return null;
        }
        String str2;
        String a3 = a2.m7387a();
        Action a4 = ((EventXE) extension2).m7598a();
        String str3 = "";
        if (a4 == Action.k || a4 == Action.j) {
            JabberId a5 = JabberId.m7386a(((EventXE) extension2).m7600c());
            if (a5 == null) {
                return null;
            }
            str = a5.m7387a();
            str2 = str3;
        } else {
            if (a4 == Action.c && message.hasExtension("info", "bpn:group:v2")) {
                InfoXE infoXE = (InfoXE) message.getExtension("info", "bpn:group:v2");
                GroupElement a6 = infoXE.m7592a();
                builder.m4385m(a.m7389c());
                if (GroupManager.m7323a().f4903a == null || GroupManager.m7323a().f4903a.get(a.m7392f()) == null) {
                    GroupManager.m7323a().m7358b(a.m7392f()).m4270a(GroupInfoState.NONE);
                    ConversationGroupEntity b = GroupManager.m7323a().m7358b(a.m7392f());
                    b.m4271a(a6.m7609b());
                    b.m4285e(a6.m7617f());
                    b.m4296j(a6.m7613d());
                    b.m4270a(GroupInfoState.NONE);
                    b.m4282d(a6.m7611c());
                    ConversationGroupDataSource.m4587a().m4603a(a.m7392f(), a6, GroupInfoState.NONE);
                } else {
                    ((GroupV2FetchHistoryModel) GroupManager.m7323a().f4903a.get(a.m7392f())).m4032a(a6, currentTimeMillis);
                }
                str3 = infoXE.m7654b();
                if (str3 == null) {
                    str3 = "";
                }
                builder.m4369c(m7381a(str3, DisplayDataHandler.m6540b(a.m7389c())));
                builder.m4387o(a6.m7611c());
                builder.m4388p(a6.m7613d());
            }
            str2 = str3;
        }
        if (a4 == null) {
            return null;
        }
        str3 = message.getStanzaId();
        if (str3 == null) {
            str3 = (currentTimeMillis / 60000) + "";
        }
        builder.m4447t(a3);
        builder.m4365b(a.m7392f());
        builder.m4353a(DeliveryStatus.RECEIVED);
        builder.m4372d(currentTimeMillis + "");
        builder.m4359a(str3);
        builder.m4356a(HistoryEntity.Type.GROUP_STATUS);
        switch (C05481.f4926d[a4.ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                builder.m4354a(GroupStatus.JOIN);
                builder.m4384l(str);
                builder.m4369c(Main.f1927b.getString(2131296570));
                GroupUsersDatasource.m4639a().m4659d(a.m7392f(), a3);
                GroupUsersDatasource.m4639a().m4656c(a.m7392f(), a3);
                EventBus.m12779a().m12795d(new InvitationResponseUiEvent(a.m7392f(), a3));
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                builder.m4369c(Main.f1927b.getString(2131296571));
                builder.m4354a(GroupStatus.KICK);
                builder.m4383k(str);
                GroupUsersDatasource.m4639a().m4647a(a.m7392f(), a3);
                EventBus.m12779a().m12795d(new KickResponseUiEvent(a.m7392f(), a3));
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                builder.m4369c(Main.f1927b.getString(2131296572));
                builder.m4354a(GroupStatus.LEAVE);
                EventBus.m12779a().m12795d(new LeaveMemberEvent(a.m7392f(), a3));
                if (GroupManager.m7323a().m7358b(a.m7392f()).m4302n() == GroupElement.Type.unmoderated || !a3.equals(GroupUsersDatasource.m4639a().m4664i(a.m7392f()))) {
                    GroupUsersDatasource.m4639a().m4647a(a.m7392f(), a3);
                    return null;
                }
                GroupUsersDatasource.m4639a().m4647a(a.m7392f(), a3);
                GroupManager.m7323a().m7358b(a.m7392f()).m4271a(GroupElement.Type.unmoderated);
                ConversationGroupDataSource.m4587a().m4602a(a.m7392f(), GroupElement.Type.unmoderated);
                return builder;
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                builder.m4386n(str2);
                builder.m4354a(GroupStatus.CHANGE_INFO);
                break;
        }
        return builder;
    }

    private String m7381a(String str, String str2) {
        String string;
        StringBuilder stringBuilder = new StringBuilder(str2 + " ");
        if (str2.equals(Main.f1927b.getString(2131296787))) {
            string = Main.f1927b.getString(2131296521);
        } else {
            string = Main.f1927b.getString(2131296517);
        }
        if (Localize.m5603d().equals(Language.PERSIAN)) {
            stringBuilder.append(m7380a(str)).append(" ").append(string);
        } else {
            stringBuilder.append(string).append(" ").append(m7380a(str));
        }
        return stringBuilder.toString();
    }

    private String m7380a(String str) {
        StringBuilder stringBuilder = new StringBuilder("");
        String string = Main.f1927b.getString(2131296783);
        String str2 = " " + Main.f1927b.getString(2131296782);
        if (str.isEmpty()) {
            stringBuilder.append(Main.f1927b.getString(2131296512)).append(string).append(" ");
        }
        if (str.contains("av")) {
            stringBuilder.append(Main.f1927b.getString(2131296503)).append(string).append(" ");
        }
        if (str.contains("ti")) {
            stringBuilder.append(Main.f1927b.getString(2131296519)).append(string).append(" ");
        }
        if (str.contains("de")) {
            stringBuilder.append(Main.f1927b.getString(2131296505)).append(string).append(" ");
        }
        while (stringBuilder.length() > 0 && Character.isWhitespace(stringBuilder.charAt(stringBuilder.length() - 1))) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        if (stringBuilder.length() > 0 && stringBuilder.lastIndexOf(string) == stringBuilder.length() - string.length()) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        int lastIndexOf = stringBuilder.lastIndexOf(string);
        if (lastIndexOf > -1) {
            stringBuilder.replace(lastIndexOf, string.length() + lastIndexOf, str2);
        }
        return stringBuilder.toString();
    }
}
