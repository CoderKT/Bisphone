package app.xmpp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import app.Main;
import app.account.AccountManager;
import app.common.Constants;
import app.common.GroupV2FetchHistoryModel;
import app.common.collection.ObserverHashMap;
import app.common.collection.ObserverHashMap.HashMapListener;
import app.common.entity.ConversationGroupEntity;
import app.common.entity.ConversationGroupEntity.Builder;
import app.common.entity.ConversationGroupEntity.GroupInfoState;
import app.common.entity.HistoryEntity;
import app.common.entity.HistoryEntity.Type;
import app.common.entity.HistoryGroupEntity;
import app.database.datasource.ConversationGroupDataSource;
import app.database.datasource.GroupUsersDatasource;
import app.database.datasource.HistoryGroupDataSource;
import app.events.group.AcceptGroupInvitationEvent;
import app.events.group.ChangeGroupSettingEvent;
import app.events.group.CreateGroupResponseUiEvent;
import app.events.group.CreateGroupV2Event;
import app.events.group.DestroyEvent;
import app.events.group.GetGroupInfoEvent;
import app.events.group.GetGroupListEvent;
import app.events.group.GetGroupMemberChanged;
import app.events.group.GetGroupSettingEvent;
import app.events.group.GetGroupStatisticEvent;
import app.events.group.GetMemberListEvent;
import app.events.group.GroupFetchHistoryEvent;
import app.events.group.GroupMigrateFinishEvent;
import app.events.group.GroupSelectiveFetchHistoryEvent;
import app.events.group.InvitationAcceptResponseUiEvent;
import app.events.group.InvitationResponseUiEvent;
import app.events.group.InviteMemberToGroupEvent;
import app.events.group.KickMemberEvent;
import app.events.group.KickResponseUiEvent;
import app.events.group.LeaveGroupEvent;
import app.events.group.LeaveGroupEvent.LeaveGroupListener;
import app.events.group.LeaveResponseUiEvent;
import app.events.group.RejectGroupInvitationEvent;
import app.events.xmpp.XMPPDisconnectEvent;
import app.http.HttpService;
import app.http.RequestType;
import app.http.TaskBuilder;
import app.http.TaskPriority;
import app.http.listener.TaskListener;
import app.messaging.group.GroupInfoModel;
import app.messaging.group.GroupListener;
import app.storage.StorageException;
import app.util.DeviceUtils;
import app.util.SharedPreferencesUtil;
import app.util.Utils;
import app.util.XMPPUtils;
import app.xmpp.packet.common.ContentXE;
import app.xmpp.packet.common.ErrorXE;
import app.xmpp.packet.common.ErrorXE.ErrorType;
import app.xmpp.packet.common.TimeXE;
import app.xmpp.packet.groupv2.CreateXE;
import app.xmpp.packet.groupv2.GetGroupSettingAckXE;
import app.xmpp.packet.groupv2.GetInfoAckXE;
import app.xmpp.packet.groupv2.GroupElement;
import app.xmpp.packet.groupv2.GroupStatisticAckXE;
import app.xmpp.packet.groupv2.HistoryAckXE;
import app.xmpp.packet.groupv2.HistoryXE;
import app.xmpp.packet.groupv2.InviteAckXE;
import app.xmpp.packet.groupv2.KickAckXE;
import app.xmpp.packet.groupv2.ListGroupsAckXE;
import app.xmpp.packet.groupv2.ListGroupsXE;
import app.xmpp.packet.groupv2.ListGroupsXE.GetGroupType;
import app.xmpp.packet.groupv2.MemberElement;
import app.xmpp.packet.groupv2.MemberElement.State;
import app.xmpp.packet.groupv2.MembersAckXE;
import app.xmpp.packet.groupv2.MembersXE;
import app.xmpp.packet.groupv2.MembersXE.MemberState;
import app.xmpp.packet.groupv2.MigrationGroupElement;
import app.xmpp.packet.groupv2.SetInfoXE;
import app.xmpp.packet.groupv2.SettingXE;
import app.xmpp.packet.groupv2.SettingXE.NotificationState;
import com.loopj.android.http.RequestParams;
import cz.msebera.android.httpclient.Header;
import de.greenrobot.event.EventBus;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smackx.delay.DelayInformationManager;
import org.jivesoftware.smackx.muc.packet.Destroy;
import org.jivesoftware.smackx.muc.packet.MUCInitialPresence.History;
import org.jivesoftware.smackx.muc.packet.MUCUser.Invite;
import org.jivesoftware.smackx.offline.packet.OfflineMessageRequest;
import org.jivesoftware.smackx.time.packet.Time;
import se.emilsjolander.stickylistheaders.C1128R;

public class GroupManager {
    private static GroupManager f4902k;
    public HashMap<String, GroupV2FetchHistoryModel> f4903a;
    HashMap<String, Method> f4904b;
    public final HashMap<String, Long> f4905c;
    public Set<String> f4906d;
    List<HistoryGroupEntity> f4907e;
    public List<String> f4908f;
    HashMap<Message, Message> f4909g;
    public Set<String> f4910h;
    private final PriorityQueue<GMRecord> f4911i;
    private Timer f4912j;
    private Context f4913l;
    private ConversationGroupEntity f4914m;
    private ConversationGroupEntity f4915n;
    private ObserverHashMap<String, ConversationGroupEntity> f4916o;
    private final Object f4917p;
    private final Object f4918q;
    private final Object f4919r;
    private final Object f4920s;
    private final Object f4921t;
    private final Object f4922u;

    /* renamed from: app.xmpp.GroupManager.1 */
    class C05411 extends TimerTask {
        final /* synthetic */ GroupManager f4887a;

        C05411(GroupManager groupManager) {
            this.f4887a = groupManager;
        }

        public void run() {
            this.f4887a.m7337j();
        }
    }

    /* renamed from: app.xmpp.GroupManager.2 */
    class C05422 extends TimerTask {
        final /* synthetic */ GroupManager f4888a;

        C05422(GroupManager groupManager) {
            this.f4888a = groupManager;
        }

        public void run() {
            this.f4888a.m7337j();
        }
    }

    /* renamed from: app.xmpp.GroupManager.3 */
    class C05433 extends ArrayList<Message> {
        final /* synthetic */ Message f4889a;
        final /* synthetic */ GroupManager f4890b;

        C05433(GroupManager groupManager, Message message) {
            this.f4890b = groupManager;
            this.f4889a = message;
            add(this.f4889a);
            Main.f1926a.m5683d(this.f4889a.getBody() + " ONE");
        }
    }

    /* renamed from: app.xmpp.GroupManager.4 */
    class C05444 extends ArrayList<String> {
        final /* synthetic */ String f4891a;
        final /* synthetic */ GroupManager f4892b;

        C05444(GroupManager groupManager, String str) {
            this.f4892b = groupManager;
            this.f4891a = str;
            add(this.f4891a);
        }
    }

    /* renamed from: app.xmpp.GroupManager.5 */
    class C05455 extends Thread {
        final /* synthetic */ List f4893a;
        final /* synthetic */ GroupManager f4894b;

        C05455(GroupManager groupManager, List list) {
            this.f4894b = groupManager;
            this.f4893a = list;
        }

        public void run() {
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (this.f4893a != null && this.f4893a.size() != 0) {
                for (ConversationGroupEntity conversationGroupEntity : this.f4893a) {
                    String e2 = conversationGroupEntity.m4283e();
                    Object obj = -1;
                    switch (e2.hashCode()) {
                        case -978118429:
                            if (e2.equals("LEFT_DELETE")) {
                                obj = 2;
                                break;
                            }
                            break;
                        case -712688040:
                            if (e2.equals("JOINING")) {
                                obj = null;
                                break;
                            }
                            break;
                        case 768877972:
                            if (e2.equals("LEAVING")) {
                                obj = 3;
                                break;
                            }
                            break;
                        case 1103076291:
                            if (e2.equals("REJECTING")) {
                                obj = 4;
                                break;
                            }
                            break;
                        case 1945184680:
                            if (e2.equals("DESTROYING")) {
                                obj = 1;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                            this.f4894b.m7349a(conversationGroupEntity.m4274b(), null);
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                            this.f4894b.m7365c(conversationGroupEntity.m4274b(), null);
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                        case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                            this.f4894b.m7353a(conversationGroupEntity.m4274b(), conversationGroupEntity.m4283e().equals("LEFT_DELETE"), null);
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                            this.f4894b.m7361b(conversationGroupEntity.m4274b(), null);
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    /* renamed from: app.xmpp.GroupManager.6 */
    class C05466 extends TaskListener {
        final /* synthetic */ ConversationGroupEntity f4895a;
        final /* synthetic */ GroupManager f4896b;

        C05466(GroupManager groupManager, ConversationGroupEntity conversationGroupEntity) {
            this.f4896b = groupManager;
            this.f4895a = conversationGroupEntity;
        }

        public void m7321a(int i, Header[] headerArr, byte[] bArr) {
            GroupManager.m7323a().m7344a(this.f4895a, null);
        }

        public void m7322a(int i, Header[] headerArr, byte[] bArr, Throwable th) {
            Main.f1926a.m5683d("Group Avatar Upload Failed");
        }
    }

    /* renamed from: app.xmpp.GroupManager.7 */
    /* synthetic */ class C05477 {
        static final /* synthetic */ int[] f4897a;
        static final /* synthetic */ int[] f4898b;
        static final /* synthetic */ int[] f4899c;
        static final /* synthetic */ int[] f4900d;
        static final /* synthetic */ int[] f4901e;

        static {
            f4901e = new int[Type.values().length];
            try {
                f4901e[Type.TEXT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f4901e[Type.MAP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f4901e[Type.STICKER.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f4901e[Type.PHOTO.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f4901e[Type.VIDEO.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f4901e[Type.AUDIO.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f4901e[Type.CALL.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f4901e[Type.GROUP_STATUS.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            f4900d = new int[NotificationState.values().length];
            try {
                f4900d[NotificationState.disable.ordinal()] = 1;
            } catch (NoSuchFieldError e9) {
            }
            try {
                f4900d[NotificationState.sound.ordinal()] = 2;
            } catch (NoSuchFieldError e10) {
            }
            try {
                f4900d[NotificationState.vibrate.ordinal()] = 3;
            } catch (NoSuchFieldError e11) {
            }
            f4899c = new int[ErrorType.values().length];
            try {
                f4899c[ErrorType.Group_Not_Exist.ordinal()] = 1;
            } catch (NoSuchFieldError e12) {
            }
            try {
                f4899c[ErrorType.User_Not_Exist.ordinal()] = 2;
            } catch (NoSuchFieldError e13) {
            }
            try {
                f4899c[ErrorType.User_Already_Joined.ordinal()] = 3;
            } catch (NoSuchFieldError e14) {
            }
            try {
                f4899c[ErrorType.User_Not_Allowed.ordinal()] = 4;
            } catch (NoSuchFieldError e15) {
            }
            try {
                f4899c[ErrorType.Group_Was_Destroyed.ordinal()] = 5;
            } catch (NoSuchFieldError e16) {
            }
            try {
                f4899c[ErrorType.Invitation_Expire.ordinal()] = 6;
            } catch (NoSuchFieldError e17) {
            }
            try {
                f4899c[ErrorType.User_Not_Authorized.ordinal()] = 7;
            } catch (NoSuchFieldError e18) {
            }
            try {
                f4899c[ErrorType.Reach_Max_Member.ordinal()] = 8;
            } catch (NoSuchFieldError e19) {
            }
            try {
                f4899c[ErrorType.User_Was_Kicked.ordinal()] = 9;
            } catch (NoSuchFieldError e20) {
            }
            f4898b = new int[MemberState.values().length];
            try {
                f4898b[MemberState.invited.ordinal()] = 1;
            } catch (NoSuchFieldError e21) {
            }
            try {
                f4898b[MemberState.joined.ordinal()] = 2;
            } catch (NoSuchFieldError e22) {
            }
            try {
                f4898b[MemberState.kicked.ordinal()] = 3;
            } catch (NoSuchFieldError e23) {
            }
            try {
                f4898b[MemberState.left.ordinal()] = 4;
            } catch (NoSuchFieldError e24) {
            }
            f4897a = new int[ContentXE.Type.values().length];
            try {
                f4897a[ContentXE.Type.conversation.ordinal()] = 1;
            } catch (NoSuchFieldError e25) {
            }
            try {
                f4897a[ContentXE.Type.event.ordinal()] = 2;
            } catch (NoSuchFieldError e26) {
            }
            try {
                f4897a[ContentXE.Type.result.ordinal()] = 3;
            } catch (NoSuchFieldError e27) {
            }
            try {
                f4897a[ContentXE.Type.command.ordinal()] = 4;
            } catch (NoSuchFieldError e28) {
            }
        }
    }

    static {
        f4902k = null;
    }

    public static synchronized GroupManager m7323a() {
        GroupManager groupManager;
        synchronized (GroupManager.class) {
            if (f4902k == null) {
                f4902k = new GroupManager();
            }
            groupManager = f4902k;
        }
        return groupManager;
    }

    public void m7341a(Context context) {
        this.f4913l = context;
    }

    private GroupManager() {
        this.f4911i = new PriorityQueue();
        this.f4912j = new Timer();
        this.f4905c = new HashMap();
        this.f4917p = new Object();
        this.f4918q = new Object();
        this.f4919r = new Object();
        this.f4920s = new Object();
        this.f4921t = new Object();
        this.f4922u = new Object();
        this.f4916o = new ObserverHashMap();
        this.f4903a = new HashMap();
        this.f4906d = new HashSet();
        this.f4909g = new HashMap();
        this.f4904b = new HashMap();
        this.f4908f = new ArrayList();
        this.f4910h = new HashSet();
        m7355a(ConversationGroupDataSource.m4587a().m4621h(), true);
        Class cls = getClass();
        try {
            this.f4904b.put("set-member-setting-ack", cls.getMethod("handleAckSettingRes", new Class[]{Message.class}));
            this.f4904b.put("join-ack", cls.getMethod("handleAcceptInvitationResponseRes", new Class[]{Message.class}));
            this.f4904b.put("reject-ack", cls.getMethod("handleRejectInvitationResponseRes", new Class[]{Message.class}));
            this.f4904b.put("set-info-ack", cls.getMethod("handleSetGroupInfoRes", new Class[]{Message.class}));
            this.f4904b.put("get-groups-ack", cls.getMethod("handleGetGroupListRes", new Class[]{Message.class}));
            this.f4904b.put("members-ack", cls.getMethod("handleGetMemberListRes", new Class[]{Message.class}));
            this.f4904b.put("kick-ack", cls.getMethod("handleKickMemberRes", new Class[]{Message.class}));
            this.f4904b.put("invite-ack", cls.getMethod("handleInviteMemberRes", new Class[]{Message.class}));
            this.f4904b.put("leave-ack", cls.getMethod("handleLeaveGroupRes", new Class[]{Message.class}));
            this.f4904b.put("create-ack", cls.getMethod("handleCreateGroupRes", new Class[]{Message.class}));
            this.f4904b.put("history-ack", cls.getMethod("handleGetHistoryAck", new Class[]{Message.class}));
            this.f4904b.put("destroy-ack", cls.getMethod("handleDestroyAck", new Class[]{Message.class}));
            this.f4904b.put("get-info-ack", cls.getMethod("handleGetGroupInfo", new Class[]{Message.class}));
            this.f4904b.put("group-statistics-ack", cls.getMethod("handleGetGroupStatistic", new Class[]{Message.class}));
            this.f4904b.put("get-member-setting-ack", cls.getMethod("handleGetGroupSettingAck", new Class[]{Message.class}));
            this.f4904b.put("set-member-setting", cls.getMethod("handleFailedSetSettingRes", new Class[]{Message.class}));
            this.f4904b.put("set-member-setting", cls.getMethod("handleFailedGetSettingRes", new Class[]{Message.class}));
            this.f4904b.put("join", cls.getMethod("handleFailedAcceptResponses", new Class[]{Message.class}));
            this.f4904b.put("reject", cls.getMethod("handleFailedRejectResponses", new Class[]{Message.class}));
            this.f4904b.put("set-info", cls.getMethod("handleFailedSetGroupInfoRes", new Class[]{Message.class}));
            this.f4904b.put("members", cls.getMethod("handleFailedGetMemberListRes", new Class[]{Message.class}));
            this.f4904b.put("kick", cls.getMethod("handleFailedKickMemberRes", new Class[]{Message.class}));
            this.f4904b.put(Invite.ELEMENT, cls.getMethod("handleFailedInviteMemberRes", new Class[]{Message.class}));
            this.f4904b.put("leave", cls.getMethod("handleFailedLeaveGroupRes", new Class[]{Message.class}));
            this.f4904b.put("create", cls.getMethod("handleFailedCreateGroupRes", new Class[]{Message.class}));
            this.f4904b.put(History.ELEMENT, cls.getMethod("handleFailedGetHistoryAck", new Class[]{Message.class}));
            this.f4904b.put(Destroy.ELEMENT, cls.getMethod("handleFailedDestroyAck", new Class[]{Message.class}));
            this.f4904b.put("get-info", cls.getMethod("handleFailedGetGroupInfoRes", new Class[]{Message.class}));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("why this method not exists?");
        }
    }

    public boolean m7357a(GMRecord gMRecord) {
        boolean add;
        synchronized (this.f4917p) {
            add = this.f4911i.add(gMRecord);
            if (this.f4911i.size() == 1) {
                this.f4912j.schedule(new C05411(this), gMRecord.m7314a());
            }
        }
        return add;
    }

    private boolean m7328b(GMRecord gMRecord) {
        if (gMRecord == null) {
            return false;
        }
        return this.f4911i.remove(gMRecord);
    }

    private Message m7324a(Message message) {
        if (message == null) {
            return null;
        }
        Message c;
        Iterator it = this.f4911i.iterator();
        while (it.hasNext()) {
            GMRecord gMRecord = (GMRecord) it.next();
            if (gMRecord.equals(GMRecord.m7311a(message))) {
                c = gMRecord.m7319c();
                break;
            }
        }
        c = null;
        return c;
    }

    private void m7337j() {
        synchronized (this.f4918q) {
            long currentTimeMillis = System.currentTimeMillis();
            GMRecord gMRecord = (GMRecord) this.f4911i.peek();
            while (gMRecord != null && gMRecord.m7317b() <= currentTimeMillis) {
                gMRecord = (GMRecord) this.f4911i.peek();
                if (gMRecord.m7320d() > 0) {
                    gMRecord.m7318b(gMRecord.m7314a() * 2);
                    gMRecord.m7316a(System.currentTimeMillis() + (gMRecord.m7314a() * 2));
                    gMRecord.m7315a(gMRecord.m7320d() - 1);
                    if (m7329b(gMRecord.m7319c())) {
                        handleFailedGetMessage(gMRecord);
                    } else {
                        EventBus.m12779a().m12795d(gMRecord.m7319c());
                        m7357a(gMRecord);
                    }
                } else {
                    if (m7329b(gMRecord.m7319c())) {
                        this.f4910h.add(gMRecord.m7319c().getTo());
                        m7338k();
                    }
                    m7331c(gMRecord.m7319c());
                }
                this.f4911i.poll();
                gMRecord = (GMRecord) this.f4911i.peek();
            }
            if (gMRecord != null) {
                this.f4912j.schedule(new C05422(this), gMRecord.m7317b() - currentTimeMillis < 0 ? 10 : gMRecord.m7317b() - currentTimeMillis);
            }
        }
    }

    private boolean m7329b(Message message) {
        return message.hasExtension(History.ELEMENT, "bpn:group:v2") || message.hasExtension("selective-history", "bpn:group:v2");
    }

    public void handleIncomingMessage(Message message) {
        ExtensionElement extension = message.getExtension("content", "bpn:common:v1");
        if (extension != null) {
            switch (C05477.f4897a[((ContentXE) extension).m7573a().ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    if (DelayInformationManager.getDelayInformation(message) == null) {
                        m7326a(message, ContentXE.Type.conversation);
                        break;
                    }
                    return;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    m7326a(message, ContentXE.Type.event);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    m7331c(message);
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    Main.f1926a.m5683d("this is command");
                    m7331c(message);
                    break;
            }
            m7333d(message);
        }
    }

    private void m7326a(Message message, ContentXE.Type type) {
        JabberId a = JabberId.m7386a(message.getFrom());
        if (a != null) {
            String f = a.m7392f();
            boolean hasExtension = message.hasExtension(OfflineMessageRequest.ELEMENT, "bpn:common:v1");
            if (m7358b(f) != null || type != ContentXE.Type.conversation) {
                if (type == ContentXE.Type.conversation && !m7358b(f).m4283e().equals("JOINED")) {
                    ConversationGroupDataSource.m4587a().m4594a(this.f4913l, f, 0);
                    ((ConversationGroupEntity) this.f4916o.get(f)).m4279c("JOINED");
                }
                if (this.f4903a.get(f) == null) {
                    GroupMessageHandler.m7379a().m7383a(new C05433(this, message));
                } else if (hasExtension && ((GroupV2FetchHistoryModel) this.f4903a.get(f)).m4034a(message)) {
                    m7330c(f);
                } else if (!hasExtension) {
                    ((GroupV2FetchHistoryModel) this.f4903a.get(f)).m4036b(message);
                }
            }
        }
    }

    private void m7331c(Message message) {
        if (message != null && this.f4911i.contains(GMRecord.m7311a(message))) {
            ExtensionElement extension = message.getExtension("bpn:group:v2");
            if (extension != null) {
                Method method = (Method) this.f4904b.get(extension.getElementName());
                if (method != null) {
                    try {
                        method.invoke(this, new Object[]{message});
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e2) {
                        Main.f1926a.m5682c(e2.getTargetException());
                        e2.printStackTrace();
                    }
                }
            }
        }
    }

    private void m7333d(Message message) {
        if (message != null && !message.hasExtension("history-ack", "bpn:group:v2")) {
            m7328b(new GMRecord(0, 0, message, 0));
        }
    }

    public void handleAckSettingRes(Message message) {
        Message a = m7324a(message);
        if (a != null) {
            String from = message.getFrom();
            SettingXE settingXE = (SettingXE) a.getExtension("set-member-setting", "bpn:group:v2");
            if (settingXE != null) {
                ((ConversationGroupEntity) this.f4916o.get(from)).m4272a(settingXE.m7676a());
                ConversationGroupDataSource.m4587a().m4593a(Main.f1927b, settingXE.m7676a(), from);
            }
        }
    }

    public void handleAcceptInvitationResponseRes(Message message) {
        if (m7324a(message) != null) {
            long parseLong;
            long currentTimeMillis;
            String from = message.getFrom();
            TimeXE timeXE = (TimeXE) message.getExtension(Time.ELEMENT, "bpn:common:v1");
            if (timeXE != null) {
                parseLong = Long.parseLong(timeXE.m7579a());
            } else {
                parseLong = 0;
            }
            if (parseLong == 0) {
                currentTimeMillis = System.currentTimeMillis() * 1000;
            } else {
                currentTimeMillis = parseLong;
            }
            ((ConversationGroupEntity) this.f4916o.get(from)).m4279c("JOINED");
            ConversationGroupDataSource.m4587a().m4594a(Main.f1927b, from, currentTimeMillis);
            this.f4905c.put(from, Long.valueOf(currentTimeMillis));
            EventBus.m12779a().m12795d(new InvitationAcceptResponseUiEvent(from, true));
            m7368d(from, null);
            m7350a(from, MemberState.joined, null);
        }
    }

    public void handleRejectInvitationResponseRes(Message message) {
        Message a = m7324a(message);
        if (a != null) {
            String to = a.getTo();
            if (HistoryGroupDataSource.m4691a().m4716f(to)) {
                ((ConversationGroupEntity) this.f4916o.get(to)).m4279c("LEFT");
                ConversationGroupDataSource.m4587a().m4595a(Main.f1927b, to, "LEFT", 0);
            } else {
                this.f4916o.remove(to);
                ConversationGroupDataSource.m4587a().m4613c(Main.f1927b, to);
            }
            EventBus.m12779a().m12795d(new InvitationAcceptResponseUiEvent(to, false));
        }
    }

    public void handleSetGroupInfoRes(Message message) {
        Message a = m7324a(message);
        if (a != null) {
            String from = message.getFrom();
            GroupElement a2 = ((SetInfoXE) a.getExtension("set-info", "bpn:group:v2")).m7592a();
            if (a2 != null) {
                ((ConversationGroupEntity) this.f4916o.get(from)).m4270a(GroupInfoState.NONE);
                ConversationGroupDataSource.m4587a().m4603a(from, a2, GroupInfoState.NONE);
            }
        }
    }

    public void handleGetGroupListRes(Message message) {
        if (this.f4909g.size() <= 1) {
            Message a = m7324a(message);
            if (a != null && ((ListGroupsXE) a.getExtension("get-groups", "bpn:group:v2")) != null) {
                ListGroupsAckXE listGroupsAckXE = (ListGroupsAckXE) message.getExtension("get-groups-ack", "bpn:group:v2");
                if (listGroupsAckXE != null && listGroupsAckXE.m7593a() != null) {
                    if (this.f4909g.size() == 0) {
                        this.f4909g.put(message, a);
                        return;
                    }
                    this.f4909g.put(message, a);
                    EventBus.m12779a().m12795d(new XMPPDisconnectEvent());
                    HashMap hashMap = new HashMap();
                    HashMap hashMap2 = new HashMap();
                    List arrayList = new ArrayList();
                    Set<String> hashSet = new HashSet(ConversationGroupDataSource.m4587a().m4592a(Main.f1927b));
                    Object obj = null;
                    for (Message message2 : this.f4909g.keySet()) {
                        ListGroupsAckXE listGroupsAckXE2 = (ListGroupsAckXE) message2.getExtension("get-groups-ack", "bpn:group:v2");
                        if (listGroupsAckXE2 != null && listGroupsAckXE2.m7593a() != null) {
                            ListGroupsXE listGroupsXE = (ListGroupsXE) ((Message) this.f4909g.get(message2)).getExtension("get-groups", "bpn:group:v2");
                            if (listGroupsXE != null) {
                                if (listGroupsAckXE2.m7593a().size() > 0 && r4 == null) {
                                    obj = 1;
                                    m7327b(false);
                                }
                                Object obj2 = obj;
                                long j = 0;
                                TimeXE timeXE = (TimeXE) message.getExtension(Time.ELEMENT, "bpn:common:v1");
                                if (timeXE != null) {
                                    j = Long.parseLong(timeXE.m7579a());
                                }
                                if (j == 0) {
                                    j = System.currentTimeMillis() * 1000;
                                }
                                for (MigrationGroupElement migrationGroupElement : listGroupsAckXE2.m7593a()) {
                                    String str = migrationGroupElement.m7606a() + "@" + migrationGroupElement.m7668h();
                                    Builder builder = new Builder();
                                    builder.m4245a(migrationGroupElement.m7609b()).m4261i(migrationGroupElement.m7613d()).m4262j(migrationGroupElement.m7611c()).m4259g(str).m4264l(migrationGroupElement.m7615e()).m4242a(0).m4246a(NotificationState.getPending).m4244a(GroupInfoState.NONE).m4247a(j + "").m4250b(j + "").m4263k(migrationGroupElement.m7617f());
                                    ConversationGroupEntity a2;
                                    if (listGroupsXE.m7656a() == GetGroupType.invited) {
                                        hashMap2.put(str, migrationGroupElement.m7669i());
                                        builder.m4254d("-1").m4260h("INVITED");
                                        a2 = builder.m4248a();
                                        this.f4916o.put(a2.m4274b(), a2);
                                        arrayList.add(a2);
                                    } else if (listGroupsXE.m7656a() == GetGroupType.joined) {
                                        hashSet.remove(migrationGroupElement.m7670j());
                                        builder.m4260h("JOINED");
                                        a2 = builder.m4248a();
                                        hashSet.remove(a2.m4274b());
                                        ConversationGroupDataSource.m4587a().m4596a(a2, migrationGroupElement.m7670j());
                                        HistoryGroupDataSource.m4691a().m4712b(str, migrationGroupElement.m7670j());
                                        this.f4916o.put(a2.m4274b(), a2);
                                        this.f4916o.remove(migrationGroupElement.m7670j());
                                    }
                                    hashMap.put(str, migrationGroupElement.m7615e());
                                }
                                if (listGroupsXE.m7656a() == GetGroupType.invited) {
                                    ConversationGroupDataSource.m4587a().m4622i();
                                    ConversationGroupDataSource.m4587a().m4605a(arrayList);
                                }
                                obj = obj2;
                            } else {
                                return;
                            }
                        }
                        return;
                    }
                    Set hashSet2 = new HashSet();
                    for (String str2 : hashSet) {
                        JabberId a3 = JabberId.m7386a(str2);
                        if (!(a3 == null || a3.m7388b().startsWith("egc"))) {
                            hashSet2.add(str2);
                        }
                    }
                    ConversationGroupDataSource.m4587a().m4606a(hashSet2);
                    GroupUsersDatasource.m4639a().m4650a(hashMap, 2);
                    GroupUsersDatasource.m4639a().m4650a(hashMap2, 6);
                    m7327b(true);
                    SharedPreferencesUtil.m7054a("ggln", Boolean.valueOf(false));
                    EventBus.m12779a().m12795d(new GroupMigrateFinishEvent());
                    this.f4909g.clear();
                }
            }
        }
    }

    public void handleGetMemberListRes(Message message) {
        Message a = m7324a(message);
        if (a != null) {
            MembersXE membersXE = (MembersXE) a.getExtension("members", "bpn:group:v2");
            if (membersXE != null) {
                String from = message.getFrom();
                MembersAckXE membersAckXE = (MembersAckXE) message.getExtension("members-ack", "bpn:group:v2");
                if (membersAckXE != null && membersAckXE.m7595a() != null) {
                    List arrayList = new ArrayList();
                    for (MemberElement a2 : membersAckXE.m7595a()) {
                        arrayList.add(JabberId.m7386a(a2.m7659a()).m7387a());
                    }
                    switch (C05477.f4898b[membersXE.m7663a().ordinal()]) {
                        case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                            GroupUsersDatasource.m4639a().m4646a(Main.f1927b, from, arrayList);
                            EventBus.m12779a().m12795d(new GetGroupMemberChanged(from, arrayList, MemberState.invited));
                        case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                            GroupUsersDatasource.m4639a().m4645a(Main.f1927b, from, arrayList, null);
                            EventBus.m12779a().m12795d(new GetGroupMemberChanged(from, arrayList, MemberState.joined));
                        case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                        default:
                    }
                }
            }
        }
    }

    public void handleKickMemberRes(Message message) {
        if (m7324a(message) != null) {
            String from = message.getFrom();
            KickAckXE kickAckXE = (KickAckXE) message.getExtension("kick-ack", "bpn:group:v2");
            List arrayList = new ArrayList();
            for (MemberElement memberElement : kickAckXE.m7595a()) {
                if (memberElement.m7660b() != null) {
                    arrayList.add(JabberId.m7386a(memberElement.m7659a()).m7387a());
                }
            }
            GroupUsersDatasource.m4639a().m4648a(from, arrayList);
        }
    }

    public void handleInviteMemberRes(Message message) {
        Message a = m7324a(message);
        if (a != null) {
            String to = a.getTo();
            InviteAckXE inviteAckXE = (InviteAckXE) message.getExtension("invite-ack", "bpn:group:v2");
            List arrayList = new ArrayList();
            List arrayList2 = new ArrayList();
            for (MemberElement memberElement : inviteAckXE.m7595a()) {
                if (memberElement.m7660b() != null) {
                    if (memberElement.m7660b() == State.invited) {
                        arrayList.add(JabberId.m7386a(memberElement.m7659a()).m7387a());
                    } else if (memberElement.m7660b() == State.joined) {
                        arrayList2.add(JabberId.m7386a(memberElement.m7659a()).m7387a());
                    }
                }
            }
            GroupUsersDatasource.m4639a().m4649a(to, arrayList, 1);
            GroupUsersDatasource.m4639a().m4649a(to, arrayList2, 3);
        }
    }

    public void handleLeaveGroupRes(Message message) {
        if (m7324a(message) != null) {
            String from = message.getFrom();
            ConversationGroupEntity conversationGroupEntity = (ConversationGroupEntity) this.f4916o.get(from);
            EventBus.m12779a().m12795d(new LeaveResponseUiEvent(from));
            this.f4905c.remove(from);
            this.f4906d.remove(from);
            if (conversationGroupEntity != null && conversationGroupEntity.m4283e() != null) {
                long parseLong;
                TimeXE timeXE = (TimeXE) message.getExtension(Time.ELEMENT, "bpn:common:v1");
                if (timeXE != null) {
                    parseLong = Long.parseLong(timeXE.m7579a());
                } else {
                    parseLong = 0;
                }
                if (parseLong == 0) {
                    parseLong = System.currentTimeMillis() * 1000;
                }
                if (conversationGroupEntity.m4283e().equals("LEFT_DELETE")) {
                    this.f4916o.remove(from);
                    ConversationGroupDataSource.m4587a().m4613c(Main.f1927b, from);
                    GroupUsersDatasource.m4639a().m4655c(from);
                    HistoryGroupDataSource.m4691a().m4715e(from);
                } else if (conversationGroupEntity.m4283e().equals("LEAVING")) {
                    ConversationGroupDataSource.m4587a().m4595a(Main.f1927b, from, "LEFT", parseLong);
                    ((ConversationGroupEntity) this.f4916o.get(from)).m4279c("LEFT");
                }
            }
        }
    }

    public void handleCreateGroupRes(Message message) {
        Message a = m7324a(message);
        if (a != null) {
            String from = message.getFrom();
            CreateXE createXE = (CreateXE) a.getExtension("create", "bpn:group:v2");
            if (createXE != null) {
                long parseLong;
                long currentTimeMillis;
                if (createXE.m7592a().m7609b() != GroupElement.Type.unmoderated) {
                    GroupUsersDatasource.m4639a().m4653b(from, AccountManager.m3934a().m3937c());
                }
                TimeXE timeXE = (TimeXE) message.getExtension(Time.ELEMENT, "bpn:common:v1");
                if (timeXE != null) {
                    parseLong = Long.parseLong(timeXE.m7579a());
                } else {
                    parseLong = 0;
                }
                if (parseLong == 0) {
                    currentTimeMillis = System.currentTimeMillis() * 1000;
                } else {
                    currentTimeMillis = parseLong;
                }
                ConversationGroupEntity conversationGroupEntity = null;
                for (String str : this.f4916o.keySet()) {
                    ConversationGroupEntity conversationGroupEntity2;
                    if ("CREATE_DRAFT".equals(((ConversationGroupEntity) this.f4916o.get(str)).m4283e())) {
                        conversationGroupEntity2 = (ConversationGroupEntity) this.f4916o.get(str);
                    } else {
                        conversationGroupEntity2 = conversationGroupEntity;
                    }
                    conversationGroupEntity = conversationGroupEntity2;
                }
                if (conversationGroupEntity != null) {
                    this.f4916o.remove(conversationGroupEntity.m4274b());
                    conversationGroupEntity.m4273a(from);
                    conversationGroupEntity.m4281d(0);
                    conversationGroupEntity.m4284e(0);
                    conversationGroupEntity.m4287f(200);
                    conversationGroupEntity.m4278c(1);
                    conversationGroupEntity.m4279c("JOINED");
                    m7367d(conversationGroupEntity);
                    ConversationGroupDataSource.m4587a().m4611b(Main.f1927b, from, currentTimeMillis);
                    this.f4905c.put(from, Long.valueOf(currentTimeMillis));
                    EventBus.m12779a().m12795d(new CreateGroupResponseUiEvent(from, true));
                }
            }
        }
    }

    public void handleGetHistoryAck(Message message) {
        if (m7324a(message) != null) {
            String from = message.getFrom();
            HistoryAckXE historyAckXE = (HistoryAckXE) message.getExtension("history-ack", "bpn:group:v2");
            GroupV2FetchHistoryModel groupV2FetchHistoryModel = (GroupV2FetchHistoryModel) this.f4903a.get(from);
            if (groupV2FetchHistoryModel == null) {
                throw new RuntimeException("you must add this object to hashMap on sending message");
            }
            groupV2FetchHistoryModel.m4033a(historyAckXE);
            if (groupV2FetchHistoryModel.m4042g()) {
                m7330c(from);
            }
        }
    }

    public void handleDestroyAck(Message message) {
        if (m7324a(message) != null) {
            long parseLong;
            String from = message.getFrom();
            this.f4905c.remove(from);
            TimeXE timeXE = (TimeXE) message.getExtension(Time.ELEMENT, "bpn:common:v1");
            if (timeXE != null) {
                parseLong = Long.parseLong(timeXE.m7579a());
            } else {
                parseLong = 0;
            }
            if (parseLong == 0) {
                parseLong = 1000 * System.currentTimeMillis();
            }
            GroupUsersDatasource.m4639a().m4655c(from);
            ConversationGroupDataSource.m4587a().m4595a(Main.f1927b, from, "LEFT", parseLong);
            ((ConversationGroupEntity) this.f4916o.get(from)).m4279c("LEFT");
        }
    }

    public void handleGetGroupStatistic(Message message) {
        Message a = m7324a(message);
        if (a != null) {
            GroupStatisticAckXE groupStatisticAckXE = (GroupStatisticAckXE) message.getExtension("group-statistics-ack", "bpn:group:v2");
            if (groupStatisticAckXE != null) {
                ((ConversationGroupEntity) this.f4916o.get(message.getFrom())).m4278c(groupStatisticAckXE.m7619a());
                ((ConversationGroupEntity) this.f4916o.get(message.getFrom())).m4284e(groupStatisticAckXE.m7623c());
                ((ConversationGroupEntity) this.f4916o.get(message.getFrom())).m4281d(groupStatisticAckXE.m7621b());
                ((ConversationGroupEntity) this.f4916o.get(message.getFrom())).m4287f(groupStatisticAckXE.m7625d());
                ConversationGroupDataSource.m4587a().m4604a(message.getFrom(), groupStatisticAckXE);
                if (GroupUsersDatasource.m4639a().m4660e(a.getTo()) != groupStatisticAckXE.m7623c()) {
                    m7350a(a.getTo(), MemberState.invited, null);
                }
            }
        }
    }

    public void handleGetGroupSettingAck(Message message) {
        if (m7324a(message) != null) {
            GetGroupSettingAckXE getGroupSettingAckXE = (GetGroupSettingAckXE) message.getExtension("get-member-setting-ack", "bpn:group:v2");
            if (getGroupSettingAckXE != null) {
                ((ConversationGroupEntity) this.f4916o.get(message.getFrom())).m4272a(getGroupSettingAckXE.m7601a());
                ConversationGroupDataSource.m4587a().m4593a(Main.f1927b, getGroupSettingAckXE.m7601a(), message.getFrom());
            }
        }
    }

    public void handleGetGroupInfo(Message message) {
        Message a = m7324a(message);
        if (a != null) {
            GetInfoAckXE getInfoAckXE = (GetInfoAckXE) message.getExtension("get-info-ack", "bpn:group:v2");
            if (getInfoAckXE != null) {
                ((ConversationGroupEntity) this.f4916o.get(a.getTo())).m4270a(GroupInfoState.NONE);
                ((ConversationGroupEntity) this.f4916o.get(a.getTo())).m4282d(getInfoAckXE.m7592a().m7611c());
                ((ConversationGroupEntity) this.f4916o.get(a.getTo())).m4296j(getInfoAckXE.m7592a().m7613d());
                ((ConversationGroupEntity) this.f4916o.get(a.getTo())).m4285e(getInfoAckXE.m7592a().m7617f());
                ((ConversationGroupEntity) this.f4916o.get(a.getTo())).m4271a(getInfoAckXE.m7592a().m7609b());
                ConversationGroupDataSource.m4587a().m4603a(a.getTo(), getInfoAckXE.m7592a(), GroupInfoState.NONE);
            }
        }
    }

    public void handleFailedSetSettingRes(Message message) {
        if (message != null) {
            Message a = m7324a(message);
            if (a != null) {
                ErrorXE errorXE = (ErrorXE) message.getExtension("err", "bpn:fault:v2");
                if (errorXE != null) {
                    switch (C05477.f4899c[errorXE.m7576a().ordinal()]) {
                        case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                        case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                            ((ConversationGroupEntity) this.f4916o.get(a.getTo())).m4279c("LEFT");
                            ConversationGroupDataSource.m4587a().m4595a(Main.f1927b, a.getTo(), "LEFT", -1);
                        default:
                    }
                }
            }
        }
    }

    public void handleFailedGetSettingRes(Message message) {
        if (message != null) {
            Message a = m7324a(message);
            if (a != null) {
                ErrorXE errorXE = (ErrorXE) message.getExtension("err", "bpn:fault:v2");
                if (errorXE != null) {
                    switch (C05477.f4899c[errorXE.m7576a().ordinal()]) {
                        case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                        case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                            ((ConversationGroupEntity) this.f4916o.get(a.getTo())).m4279c("LEFT");
                            ConversationGroupDataSource.m4587a().m4595a(Main.f1927b, a.getTo(), "LEFT", -1);
                        default:
                    }
                }
            }
        }
    }

    public void handleFailedAcceptResponses(Message message) {
        Main.f1926a.m5683d("handleFailedAccept");
        if (message != null) {
            Message a = m7324a(message);
            if (a != null) {
                ErrorXE errorXE = (ErrorXE) message.getExtension("err", "bpn:fault:v2");
                if (errorXE == null) {
                    Main.f1926a.m5683d("error does not exists");
                    return;
                }
                Main.f1926a.m5683d("error is exists");
                String a2 = ConversationGroupDataSource.m4587a().m4591a(Main.f1927b, a.getTo());
                switch (C05477.f4899c[errorXE.m7576a().ordinal()]) {
                    case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                        this.f4916o.remove(a.getTo());
                        ConversationGroupDataSource.m4587a().m4613c(Main.f1927b, a.getTo());
                        m7336g(String.format(this.f4913l.getString(2131296508), new Object[]{a2}));
                    case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                        Main.f1926a.m5683d("error is OK, need to update ");
                        ((ConversationGroupEntity) this.f4916o.get(a.getTo())).m4279c("JOINED");
                        ConversationGroupDataSource.m4587a().m4595a(Main.f1927b, a.getTo(), "JOINED", -1);
                    case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                        ((ConversationGroupEntity) this.f4916o.get(a.getTo())).m4279c("INVITED");
                        ConversationGroupDataSource.m4587a().m4595a(Main.f1927b, a.getTo(), "INVITED", -1);
                        m7336g(String.format(this.f4913l.getString(2131296511), new Object[]{a2}));
                    case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                        this.f4916o.remove(a.getTo());
                        ConversationGroupDataSource.m4587a().m4613c(Main.f1927b, a.getTo());
                        m7336g(String.format(this.f4913l.getString(2131296508), new Object[]{a2}));
                    case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                        this.f4916o.remove(a.getTo());
                        ConversationGroupDataSource.m4587a().m4613c(Main.f1927b, a.getTo());
                    default:
                }
            }
        }
    }

    public void handleFailedRejectResponses(Message message) {
        if (message != null) {
            Message a = m7324a(message);
            if (a != null) {
                ErrorXE errorXE = (ErrorXE) message.getExtension("err", "bpn:fault:v2");
                if (errorXE != null) {
                    switch (C05477.f4899c[errorXE.m7576a().ordinal()]) {
                        case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                        case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                        case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                        case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                            this.f4916o.remove(a.getTo());
                            ConversationGroupDataSource.m4587a().m4613c(Main.f1927b, a.getTo());
                        default:
                    }
                }
            }
        }
    }

    public void handleFailedSetGroupInfoRes(Message message) {
        if (message != null) {
            Message a = m7324a(message);
            if (a != null) {
                ErrorXE errorXE = (ErrorXE) message.getExtension("err", "bpn:fault:v2");
                if (errorXE != null) {
                    String a2 = ConversationGroupDataSource.m4587a().m4591a(Main.f1927b, a.getTo());
                    switch (C05477.f4899c[errorXE.m7576a().ordinal()]) {
                        case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                            m7368d(a.getTo(), null);
                            m7336g(String.format(this.f4913l.getString(2131296509), new Object[]{a2}));
                        case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                            m7368d(a.getTo(), null);
                            m7336g(String.format(this.f4913l.getString(2131296509), new Object[]{a2}));
                        case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                            ((ConversationGroupEntity) this.f4916o.get(a.getTo())).m4279c("LEFT");
                            ConversationGroupDataSource.m4587a().m4595a(Main.f1927b, a.getTo(), "LEFT", -1);
                            m7336g(String.format(this.f4913l.getString(2131296509), new Object[]{a2}));
                        default:
                    }
                }
            }
        }
    }

    public void handleFailedGetGroupInfoRes(Message message) {
        if (message != null) {
            Message a = m7324a(message);
            if (a != null) {
                ErrorXE errorXE = (ErrorXE) message.getExtension("err", "bpn:fault:v2");
                if (errorXE != null) {
                    switch (C05477.f4899c[errorXE.m7576a().ordinal()]) {
                        case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                            ((ConversationGroupEntity) this.f4916o.get(a.getTo())).m4279c("DESTROYED");
                            ConversationGroupDataSource.m4587a().m4595a(Main.f1927b, a.getTo(), "DESTROYED", -1);
                        default:
                    }
                }
            }
        }
    }

    public void handleFailedGetMemberListRes(Message message) {
        if (message != null) {
            Message a = m7324a(message);
            if (a != null) {
                this.f4906d.remove(a.getTo());
                ErrorXE errorXE = (ErrorXE) message.getExtension("err", "bpn:fault:v2");
                if (errorXE != null) {
                    switch (C05477.f4899c[errorXE.m7576a().ordinal()]) {
                        case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                        case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                            ((ConversationGroupEntity) this.f4916o.get(a.getTo())).m4279c("LEFT");
                            ConversationGroupDataSource.m4587a().m4595a(Main.f1927b, a.getTo(), "LEFT", -1);
                        case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                            ((ConversationGroupEntity) this.f4916o.get(a.getTo())).m4279c("DESTROYED");
                            ConversationGroupDataSource.m4587a().m4595a(Main.f1927b, a.getTo(), "DESTROYED", -1);
                        default:
                    }
                }
            }
        }
    }

    public void handleFailedKickMemberRes(Message message) {
        if (message != null) {
            Message a = m7324a(message);
            if (a != null) {
                ErrorXE errorXE = (ErrorXE) message.getExtension("err", "bpn:fault:v2");
                if (errorXE == null) {
                    EventBus.m12779a().m12795d(new KickResponseUiEvent(ErrorType.Time_out));
                    return;
                }
                switch (C05477.f4899c[errorXE.m7576a().ordinal()]) {
                    case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                        GroupUsersDatasource.m4639a().m4662g(a.getTo());
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                        ((ConversationGroupEntity) this.f4916o.get(a.getTo())).m4279c("DESTROYED");
                        GroupUsersDatasource.m4639a().m4662g(a.getTo());
                        ConversationGroupDataSource.m4587a().m4595a(Main.f1927b, a.getTo(), "DESTROYED", -1);
                        break;
                }
                EventBus.m12779a().m12795d(new KickResponseUiEvent(errorXE.m7576a()));
            }
        }
    }

    public void handleFailedInviteMemberRes(Message message) {
        if (message != null) {
            Message a = m7324a(message);
            if (a != null) {
                ErrorXE errorXE = (ErrorXE) message.getExtension("err", "bpn:fault:v2");
                if (errorXE == null) {
                    EventBus.m12779a().m12795d(new InvitationResponseUiEvent(ErrorType.Time_out));
                    return;
                }
                String a2 = ConversationGroupDataSource.m4587a().m4591a(Main.f1927b, a.getTo());
                switch (C05477.f4899c[errorXE.m7576a().ordinal()]) {
                    case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                        GroupUsersDatasource.m4639a().m4661f(a.getTo());
                        m7336g(String.format(this.f4913l.getString(2131296510), new Object[]{a2}));
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                        GroupUsersDatasource.m4639a().m4661f(a.getTo());
                        m7336g(String.format(this.f4913l.getString(2131296510), new Object[]{a2}));
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                        ((ConversationGroupEntity) this.f4916o.get(a.getTo())).m4279c("DESTROYED");
                        ConversationGroupDataSource.m4587a().m4595a(Main.f1927b, a.getTo(), "DESTROYED", -1);
                        m7336g(String.format(this.f4913l.getString(2131296510), new Object[]{a2}));
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                        GroupUsersDatasource.m4639a().m4661f(a.getTo());
                        m7336g(String.format(this.f4913l.getString(2131296510), new Object[]{a2}));
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                        GroupUsersDatasource.m4639a().m4661f(a.getTo());
                        m7336g(String.format(this.f4913l.getString(2131296507), new Object[]{a2}) + "\n" + this.f4913l.getString(2131296510));
                        break;
                }
                EventBus.m12779a().m12795d(new InvitationResponseUiEvent(errorXE.m7576a()));
            }
        }
    }

    public void handleFailedLeaveGroupRes(Message message) {
        if (message != null) {
            Message a = m7324a(message);
            if (a == null) {
                return;
            }
            if (((ErrorXE) message.getExtension("err", "bpn:fault:v2")) == null) {
                EventBus.m12779a().m12795d(new InvitationResponseUiEvent(ErrorType.Time_out));
                return;
            }
            ((ConversationGroupEntity) this.f4916o.get(a.getTo())).m4279c("LEFT");
            ConversationGroupDataSource.m4587a().m4595a(Main.f1927b, a.getTo(), "LEFT", -1);
        }
    }

    public void handleFailedCreateGroupRes(Message message) {
        if (message != null) {
            ErrorXE errorXE = (ErrorXE) message.getExtension("err", "bpn:fault:v2");
            if (errorXE == null) {
                EventBus.m12779a().m12795d(new CreateGroupResponseUiEvent(ErrorType.Time_out));
                return;
            }
            EventBus.m12779a().m12795d(new CreateGroupResponseUiEvent(errorXE.m7576a()));
        }
    }

    public void handleFailedGetMessage(GMRecord gMRecord) {
        synchronized (this.f4919r) {
            GroupV2FetchHistoryModel groupV2FetchHistoryModel = (GroupV2FetchHistoryModel) this.f4903a.get(gMRecord.m7319c().getTo());
            if (groupV2FetchHistoryModel == null || groupV2FetchHistoryModel.m4041f() == null || !gMRecord.m7319c().equals(groupV2FetchHistoryModel.m4041f())) {
                return;
            }
            List d = groupV2FetchHistoryModel.m4039d();
            if (d == null) {
                Message f = groupV2FetchHistoryModel.m4041f();
                EventBus.m12779a().m12795d(f);
                m7357a(gMRecord);
                groupV2FetchHistoryModel.m4043h();
                groupV2FetchHistoryModel.m4038c(f);
            } else if (gMRecord.m7319c().hasExtension("selective-history", "bpn:group:v2")) {
                m7354a(d, gMRecord.m7319c().getTo(), null, gMRecord.m7320d());
            } else {
                m7354a(d, gMRecord.m7319c().getTo(), null, 2);
            }
        }
    }

    public void handleFailedGetHistoryAck(Message message) {
        if (message != null) {
            Message a = m7324a(message);
            if (a != null) {
                ErrorXE errorXE = (ErrorXE) message.getExtension("err", "bpn:fault:v2");
                if (errorXE != null) {
                    m7338k();
                    switch (C05477.f4899c[errorXE.m7576a().ordinal()]) {
                        case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                        case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                            ((ConversationGroupEntity) this.f4916o.get(a.getTo())).m4279c("LEFT");
                            ConversationGroupDataSource.m4587a().m4595a(Main.f1927b, a.getTo(), "LEFT", -1);
                        case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                            ((ConversationGroupEntity) this.f4916o.get(a.getTo())).m4279c("DESTROYED");
                            ConversationGroupDataSource.m4587a().m4595a(Main.f1927b, a.getTo(), "DESTROYED", -1);
                        case C1128R.styleable.StickyListHeadersListView_android_listSelector /*9*/:
                            ((ConversationGroupEntity) this.f4916o.get(a.getTo())).m4279c("KICKED");
                            ConversationGroupDataSource.m4587a().m4595a(Main.f1927b, a.getTo(), "KICKED", -1);
                        default:
                    }
                }
            }
        }
    }

    public void handleFailedSelectiveBatch(Message message) {
        if (message != null) {
            Message a = m7324a(message);
            if (a != null) {
                ErrorXE errorXE = (ErrorXE) message.getExtension("err", "bpn:fault:v2");
                if (errorXE != null) {
                    switch (C05477.f4899c[errorXE.m7576a().ordinal()]) {
                        case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                        case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                            ((ConversationGroupEntity) this.f4916o.get(a.getTo())).m4279c("LEFT");
                            ConversationGroupDataSource.m4587a().m4595a(Main.f1927b, a.getTo(), "LEFT", -1);
                        case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                            ((ConversationGroupEntity) this.f4916o.get(a.getTo())).m4279c("DESTROYED");
                            ConversationGroupDataSource.m4587a().m4595a(Main.f1927b, a.getTo(), "DESTROYED", -1);
                        default:
                    }
                }
            }
        }
    }

    public void handleFailedDestroyAck(Message message) {
        if (message != null) {
            Message a = m7324a(message);
            if (a != null) {
                ErrorXE errorXE = (ErrorXE) message.getExtension("err", "bpn:fault:v2");
                if (errorXE != null) {
                    switch (C05477.f4899c[errorXE.m7576a().ordinal()]) {
                        case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                        case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                            ((ConversationGroupEntity) this.f4916o.get(a.getTo())).m4279c("DESTROYED");
                            ConversationGroupDataSource.m4587a().m4595a(Main.f1927b, a.getTo(), "DESTROYED", -1);
                        case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                            ((ConversationGroupEntity) this.f4916o.get(a.getTo())).m4279c("JOINED");
                            ConversationGroupDataSource.m4587a().m4595a(Main.f1927b, a.getTo(), "JOINED", -1);
                        default:
                    }
                }
            }
        }
    }

    public void handleFailedGetGroupStatisticRes(Message message) {
        if (message != null) {
            Message a = m7324a(message);
            if (a != null) {
                ErrorXE errorXE = (ErrorXE) message.getExtension("err", "bpn:fault:v2");
                if (errorXE != null) {
                    switch (C05477.f4899c[errorXE.m7576a().ordinal()]) {
                        case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                        case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                            ((ConversationGroupEntity) this.f4916o.get(a.getTo())).m4279c("LEFT");
                            ConversationGroupDataSource.m4587a().m4595a(Main.f1927b, a.getTo(), "LEFT", -1);
                        case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                            ((ConversationGroupEntity) this.f4916o.get(a.getTo())).m4279c("DESTROYED");
                            ConversationGroupDataSource.m4587a().m4595a(Main.f1927b, a.getTo(), "DESTROYED", -1);
                        default:
                    }
                }
            }
        }
    }

    private void m7330c(String str) {
        synchronized (this.f4920s) {
            if (this.f4903a == null) {
                m7338k();
            } else if (this.f4903a.get(str) == null) {
                m7338k();
            } else {
                long a = GroupMessageHandler.m7379a().m7383a(((GroupV2FetchHistoryModel) this.f4903a.get(str)).m4031a());
                if (a == -1) {
                    if (((GroupV2FetchHistoryModel) this.f4903a.get(str)).m4041f() == null) {
                        m7338k();
                        return;
                    }
                    EventBus.m12779a().m12795d(((GroupV2FetchHistoryModel) this.f4903a.get(str)).m4041f());
                } else if (((GroupV2FetchHistoryModel) this.f4903a.get(str)).m4040e()) {
                    m7333d(((GroupV2FetchHistoryModel) this.f4903a.get(str)).m4041f());
                    m7340a(a, str, null);
                } else {
                    m7333d(((GroupV2FetchHistoryModel) this.f4903a.get(str)).m4041f());
                    List c = ((GroupV2FetchHistoryModel) this.f4903a.get(str)).m4037c();
                    GroupElement i = ((GroupV2FetchHistoryModel) this.f4903a.get(str)).m4044i();
                    this.f4903a.remove(str);
                    GroupMessageHandler.m7379a().m7383a(c);
                    if (i == null) {
                        m7332d(str);
                    } else {
                        ((ConversationGroupEntity) this.f4916o.get(str)).m4270a(GroupInfoState.NONE);
                        ConversationGroupDataSource.m4587a().m4603a(str, i, GroupInfoState.NONE);
                        m7368d(str, null);
                    }
                    m7334e(str);
                    m7335f(str);
                    m7338k();
                }
            }
        }
    }

    private void m7338k() {
        synchronized (this.f4921t) {
            if (this.f4908f.size() == 0) {
                return;
            }
            String str = (String) this.f4908f.get(0);
            this.f4908f.remove(0);
            if (str.equals("990c25fc-5170-4446-ba2f-fad3c21f5704@egc2.bisphone.com")) {
                return;
            }
            Main.f1926a.m5681c("send next pending history size is " + this.f4908f.size() + " " + str);
            if (this.f4905c.get(str) == null) {
                return;
            }
            m7340a(((Long) this.f4905c.get(str)).longValue(), str, null);
        }
    }

    public void m7340a(long j, String str, GroupListener groupListener) {
        if (j == 0) {
            j = System.currentTimeMillis() * 1000;
        }
        HistoryXE historyXE = new HistoryXE(1 + j, 20);
        GroupFetchHistoryEvent groupFetchHistoryEvent = new GroupFetchHistoryEvent();
        groupFetchHistoryEvent.m4901a(str);
        groupFetchHistoryEvent.m4900a(historyXE);
        groupFetchHistoryEvent.m4899a(groupListener);
        EventBus.m12779a().m12795d(groupFetchHistoryEvent);
    }

    public void m7354a(List<String> list, String str, GroupListener groupListener, int i) {
        GroupSelectiveFetchHistoryEvent groupSelectiveFetchHistoryEvent = new GroupSelectiveFetchHistoryEvent();
        groupSelectiveFetchHistoryEvent.m4908a(groupListener);
        groupSelectiveFetchHistoryEvent.m4910a((List) list);
        groupSelectiveFetchHistoryEvent.m4909a(str);
        groupSelectiveFetchHistoryEvent.m4907a(i);
        EventBus.m12779a().m12795d(groupSelectiveFetchHistoryEvent);
    }

    public void m7344a(ConversationGroupEntity conversationGroupEntity, GroupListener groupListener) {
        if (conversationGroupEntity != null) {
            JabberId a = JabberId.m7386a(conversationGroupEntity.m4274b());
            if (a != null) {
                GroupElement groupElement = new GroupElement(a.m7387a());
                groupElement.m7614d(conversationGroupEntity.m4289g());
                groupElement.m7610b(conversationGroupEntity.m4301m());
                groupElement.m7608a(conversationGroupEntity.m4286f());
                groupElement.m7607a(conversationGroupEntity.m4302n());
                groupElement.m7612c(conversationGroupEntity.m4304p());
                groupElement.m7616e(conversationGroupEntity.m4305q());
                GroupInfoModel groupInfoModel = new GroupInfoModel(groupElement, conversationGroupEntity.m4274b());
                groupInfoModel.m6248a(groupListener);
                ConversationGroupEntity b = m7358b(conversationGroupEntity.m4274b());
                if (b != null) {
                    b.m4270a(conversationGroupEntity.m4303o());
                    ConversationGroupDataSource.m4587a().m4603a(conversationGroupEntity.m4274b(), groupElement, conversationGroupEntity.m4303o());
                    EventBus.m12779a().m12795d(groupInfoModel);
                }
            }
        }
    }

    public void m7345a(GroupListener groupListener) {
        GetGroupListEvent getGroupListEvent = new GetGroupListEvent();
        getGroupListEvent.m4881a(groupListener);
        getGroupListEvent.m4882a(GetGroupType.invited);
        EventBus.m12779a().m12795d(getGroupListEvent);
        getGroupListEvent.m4882a(GetGroupType.joined);
        EventBus.m12779a().m12795d(getGroupListEvent);
    }

    public void m7350a(String str, MemberState memberState, GroupListener groupListener) {
        if (!this.f4906d.contains(str) || memberState != MemberState.joined) {
            if (memberState == MemberState.joined) {
                this.f4906d.add(str);
            }
            GetMemberListEvent getMemberListEvent = new GetMemberListEvent();
            getMemberListEvent.m4895a(str);
            getMemberListEvent.m4893a(groupListener);
            getMemberListEvent.m4894a(memberState);
            EventBus.m12779a().m12795d(getMemberListEvent);
        }
    }

    public void m7351a(String str, String str2, GroupListener groupListener) {
        m7352a(str, new C05444(this, str2), groupListener);
    }

    public void m7352a(String str, List<String> list, GroupListener groupListener) {
        if (list != null && list.size() != 0) {
            GroupUsersDatasource.m4639a().m4649a(str, (List) list, 5);
            List arrayList = new ArrayList();
            for (String jabberId : list) {
                arrayList.add(new MemberElement(new JabberId(jabberId, "bisphone.com", null).m7391e(), null));
            }
            KickMemberEvent kickMemberEvent = new KickMemberEvent();
            kickMemberEvent.m4926a(str);
            kickMemberEvent.m4925a(groupListener);
            kickMemberEvent.m4927a(arrayList);
            EventBus.m12779a().m12795d(kickMemberEvent);
        }
    }

    public void m7362b(String str, List<String> list, GroupListener groupListener) {
        if (list != null && list.size() != 0) {
            List arrayList = new ArrayList();
            for (String jabberId : list) {
                arrayList.add(new MemberElement(new JabberId(jabberId, "bisphone.com", null).m7391e(), null));
            }
            GroupUsersDatasource.m4639a().m4657c(str, (List) list);
            InviteMemberToGroupEvent inviteMemberToGroupEvent = new InviteMemberToGroupEvent();
            inviteMemberToGroupEvent.m4919a(groupListener);
            inviteMemberToGroupEvent.m4920a(str);
            inviteMemberToGroupEvent.m4921a(arrayList);
            EventBus.m12779a().m12795d(inviteMemberToGroupEvent);
        }
    }

    public void m7353a(String str, boolean z, LeaveGroupListener leaveGroupListener) {
        EventBus.m12779a().m12795d(new LeaveGroupEvent(str, leaveGroupListener));
        ConversationGroupEntity b = m7358b(str);
        if (b != null) {
            b.m4279c(z ? "LEFT_DELETE" : "LEAVING");
            ConversationGroupDataSource.m4587a().m4595a(Main.f1927b, str, z ? "LEFT_DELETE" : "LEAVING", -1);
        }
    }

    public void m7346a(GroupElement groupElement, GroupListener groupListener) {
        CreateGroupV2Event createGroupV2Event = new CreateGroupV2Event();
        createGroupV2Event.m4870a(groupListener);
        createGroupV2Event.m4871a(groupElement);
        EventBus.m12779a().m12795d(createGroupV2Event);
    }

    public void m7349a(String str, GroupListener groupListener) {
        AcceptGroupInvitationEvent acceptGroupInvitationEvent = new AcceptGroupInvitationEvent();
        acceptGroupInvitationEvent.m4857a(groupListener);
        acceptGroupInvitationEvent.m4858a(str);
        ConversationGroupEntity b = m7358b(str);
        if (b != null) {
            b.m4279c("JOINING");
            ConversationGroupDataSource.m4587a().m4595a(Main.f1927b, str, "JOINING", -1);
            EventBus.m12779a().m12795d(acceptGroupInvitationEvent);
        }
    }

    public void m7361b(String str, GroupListener groupListener) {
        RejectGroupInvitationEvent rejectGroupInvitationEvent = new RejectGroupInvitationEvent();
        rejectGroupInvitationEvent.m4941a(groupListener);
        rejectGroupInvitationEvent.m4942a(str);
        ConversationGroupEntity b = m7358b(str);
        if (b != null) {
            b.m4279c("REJECTING");
            ConversationGroupDataSource.m4587a().m4595a(Main.f1927b, str, "REJECTING", -1);
            EventBus.m12779a().m12795d(rejectGroupInvitationEvent);
        }
    }

    public void m7347a(NotificationState notificationState, Integer num, String str, GroupListener groupListener) {
        NotificationState notificationState2;
        SettingXE settingXE = new SettingXE();
        settingXE.m7677a(notificationState);
        settingXE.m7678a(num);
        ChangeGroupSettingEvent changeGroupSettingEvent = new ChangeGroupSettingEvent();
        changeGroupSettingEvent.m4862a(settingXE);
        changeGroupSettingEvent.m4861a(groupListener);
        changeGroupSettingEvent.m4863a(str);
        switch (C05477.f4900d[notificationState.ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                notificationState2 = NotificationState.disablePending;
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                notificationState2 = NotificationState.soundPending;
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                notificationState2 = NotificationState.vibratePending;
                break;
            default:
                throw new RuntimeException("invalid state, you must pass non pending state to this method");
        }
        ((ConversationGroupEntity) this.f4916o.get(str)).m4272a(notificationState2);
        ConversationGroupDataSource.m4587a().m4593a(Main.f1927b, notificationState2, str);
        EventBus.m12779a().m12795d(changeGroupSettingEvent);
    }

    public void m7365c(String str, GroupListener groupListener) {
        DestroyEvent destroyEvent = new DestroyEvent(str);
        destroyEvent.m4874a(groupListener);
        EventBus.m12779a().m12795d(destroyEvent);
        ConversationGroupEntity b = m7358b(str);
        if (b != null) {
            b.m4279c("DESTROYING");
            ConversationGroupDataSource.m4587a().m4595a(Main.f1927b, str, "DESTROYING", -1);
        }
    }

    public void m7368d(String str, GroupListener groupListener) {
        GetGroupInfoEvent getGroupInfoEvent = new GetGroupInfoEvent();
        getGroupInfoEvent.m4877a(groupListener);
        getGroupInfoEvent.m4878a(str);
        EventBus.m12779a().m12795d(getGroupInfoEvent);
    }

    public void m7370e(String str, GroupListener groupListener) {
        EventBus.m12779a().m12795d(new GetGroupStatisticEvent(str, groupListener));
    }

    public void m7372f(String str, GroupListener groupListener) {
        GetGroupSettingEvent getGroupSettingEvent = new GetGroupSettingEvent();
        getGroupSettingEvent.m4887a(groupListener);
        getGroupSettingEvent.m4888a(str);
        EventBus.m12779a().m12795d(getGroupSettingEvent);
    }

    private void m7332d(String str) {
        ConversationGroupEntity d = ConversationGroupDataSource.m4587a().m4615d(str);
        if (d != null) {
            if (d.m4303o() == GroupInfoState.DESCRIPTION_AVATAR || d.m4303o() == GroupInfoState.TITLE_AVATAR || d.m4303o() == GroupInfoState.All) {
                m7343a(d);
            } else {
                m7344a(d, null);
            }
        }
    }

    private void m7334e(String str) {
        Cursor h = GroupUsersDatasource.m4639a().m4663h(str);
        if (h != null) {
            if (h.moveToFirst()) {
                List arrayList = new ArrayList();
                Object arrayList2 = new ArrayList();
                do {
                    switch (h.getInt(h.getColumnIndex("user_status"))) {
                        case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                            arrayList2.add(h.getString(h.getColumnIndex("contact_username")));
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                            arrayList.add(h.getString(h.getColumnIndex("contact_username")));
                            break;
                    }
                } while (h.moveToNext());
                h.close();
                m7352a(str, arrayList, null);
                m7362b(str, arrayList2, null);
                return;
            }
            h.close();
        }
    }

    public void handlePendingGroupStatus(List<ConversationGroupEntity> list) {
        new C05455(this, list).start();
    }

    public void handlePendingGroupSetting(ArrayList<ConversationGroupEntity> arrayList) {
        if (arrayList != null && arrayList.size() != 0) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ConversationGroupEntity conversationGroupEntity = (ConversationGroupEntity) it.next();
                if (conversationGroupEntity.m4295j() != NotificationState.getPending) {
                    m7347a(conversationGroupEntity.m4295j(), null, conversationGroupEntity.m4274b(), null);
                } else {
                    m7372f(conversationGroupEntity.m4274b(), null);
                }
            }
        }
    }

    public void m7359b() {
        this.f4907e = HistoryGroupDataSource.m4694a(Main.f1927b);
    }

    private void m7335f(String str) {
        JabberId a = JabberId.m7386a(str);
        if (a != null && XMPPUtils.m7094b(a) && this.f4907e != null && this.f4907e.size() > 0) {
            for (HistoryEntity historyEntity : this.f4907e) {
                if (historyEntity.m4414c().equals(str)) {
                    switch (C05477.f4901e[historyEntity.m4418e().ordinal()]) {
                        case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                            NormalMessageHandler.m7415a().m7435a(historyEntity, true, 2);
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                            NormalMessageHandler.m7415a().m7444b(historyEntity, true, 2);
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                            NormalMessageHandler.m7415a().m7446c(historyEntity, true, 2);
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                        case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                        case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                            NormalMessageHandler.m7415a().m7434a(historyEntity, NormalMessageManager.m7447a().m7474h(), historyEntity.m4430q(), true, 2);
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    public void m7343a(ConversationGroupEntity conversationGroupEntity) {
        try {
            File file = new File(Utils.m7079a(conversationGroupEntity.m4289g(), Type.PHOTO));
            try {
                RequestParams requestParams = new RequestParams();
                requestParams.m10743a("username", AccountManager.m3934a().m3937c());
                requestParams.m10743a("uuid", DeviceUtils.m7012a(Main.f1927b));
                requestParams.m10743a("host", "bisphone.com");
                requestParams.m10741a("uploadedfile", file);
                HttpService.m5567a(new TaskBuilder().m5590a(conversationGroupEntity.m4289g()).m5585a(this.f4913l).m5592b(Constants.m3959d()).m5589a(requestParams).m5586a(RequestType.post).m5587a(TaskPriority.high).m5588a(new C05466(this, conversationGroupEntity)).m5584a());
            } catch (FileNotFoundException e) {
            }
        } catch (StorageException e2) {
        }
    }

    public ConversationGroupEntity m7363c() {
        return this.f4914m;
    }

    public void m7360b(ConversationGroupEntity conversationGroupEntity) {
        this.f4914m = conversationGroupEntity;
    }

    public ConversationGroupEntity m7366d() {
        return this.f4915n;
    }

    public void m7364c(ConversationGroupEntity conversationGroupEntity) {
        this.f4915n = conversationGroupEntity;
    }

    public void m7367d(ConversationGroupEntity conversationGroupEntity) {
        this.f4916o.put(conversationGroupEntity.m4274b(), conversationGroupEntity);
    }

    public void m7369e() {
        if (this.f4916o != null) {
            this.f4916o.clear();
        }
    }

    public void m7348a(String str) {
        this.f4916o.remove(str);
    }

    public void m7342a(HashMapListener hashMapListener) {
        this.f4916o.m4090a(hashMapListener);
    }

    public void m7356a(boolean z) {
        this.f4916o.m4091a(z);
    }

    public void m7371f() {
        this.f4914m = null;
    }

    private void m7336g(String str) {
        if (this.f4913l != null) {
            Intent intent = new Intent();
            intent.putExtra("result_key", str);
            intent.setAction("result_key");
            this.f4913l.sendBroadcast(intent);
        }
    }

    private void m7327b(boolean z) {
        if (this.f4913l != null) {
            Intent intent = new Intent();
            intent.putExtra("result_key", " ");
            intent.putExtra("result_migration", z);
            intent.setAction("result_key");
            this.f4913l.sendBroadcast(intent);
        }
    }

    public ConversationGroupEntity m7358b(String str) {
        return m7339a(str, true);
    }

    public ConversationGroupEntity m7339a(String str, boolean z) {
        ConversationGroupEntity conversationGroupEntity = (ConversationGroupEntity) this.f4916o.get(str);
        if (conversationGroupEntity != null || !z) {
            return conversationGroupEntity;
        }
        conversationGroupEntity = ConversationGroupDataSource.m4587a().m4609b(this.f4913l, str);
        if (conversationGroupEntity == null) {
            return null;
        }
        this.f4916o.put(str, conversationGroupEntity);
        return conversationGroupEntity;
    }

    public void m7373g() {
        for (String str : this.f4916o.keySet()) {
            ConversationGroupEntity conversationGroupEntity = (ConversationGroupEntity) this.f4916o.get(str);
            if (conversationGroupEntity != null) {
                conversationGroupEntity.m4268a(0);
                conversationGroupEntity.m4275b(0);
            }
        }
    }

    public ObserverHashMap<String, ConversationGroupEntity> m7374h() {
        ObserverHashMap<String, ConversationGroupEntity> observerHashMap;
        synchronized (this.f4922u) {
            observerHashMap = this.f4916o;
        }
        return observerHashMap;
    }

    public List<ConversationGroupEntity> m7375i() {
        List<ConversationGroupEntity> arrayList;
        synchronized (this.f4922u) {
            arrayList = new ArrayList(this.f4916o.values());
            Collections.sort(arrayList);
        }
        return arrayList;
    }

    public void m7355a(List<ConversationGroupEntity> list, boolean z) {
        for (ConversationGroupEntity conversationGroupEntity : list) {
            if (!this.f4916o.containsKey(conversationGroupEntity.m4274b()) || z) {
                m7367d(conversationGroupEntity);
            }
        }
    }
}
