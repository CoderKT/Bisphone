package app.xmpp;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import app.Main;
import app.account.AccountManager;
import app.common.GroupV2FetchHistoryModel;
import app.common.entity.ChannelEntity;
import app.common.entity.ConversationGroupEntity;
import app.contact.ContactSyncManager;
import app.database.datasource.ChannelDataSource;
import app.database.datasource.ContactDataSource;
import app.database.datasource.ConversationGroupDataSource;
import app.database.datasource.HistoryNormalMessageDataSource;
import app.events.ConnectionListenerEvent;
import app.events.ConnectionListenerEvent.ConnectionType;
import app.events.channel.ChannelEvent;
import app.events.channel.ChannelEvent.Action;
import app.events.channel.EnterAllChannels;
import app.events.group.AcceptGroupInvitationEvent;
import app.events.group.ChangeGroupSettingEvent;
import app.events.group.CreateGroupV2Event;
import app.events.group.DestroyEvent;
import app.events.group.GetGroupInfoEvent;
import app.events.group.GetGroupListEvent;
import app.events.group.GetGroupSettingEvent;
import app.events.group.GetGroupStatisticEvent;
import app.events.group.GetMemberListEvent;
import app.events.group.GroupFetchHistoryEvent;
import app.events.group.GroupSelectiveFetchHistoryEvent;
import app.events.group.InviteMemberToGroupEvent;
import app.events.group.KickMemberEvent;
import app.events.group.LeaveGroupEvent;
import app.events.group.RejectGroupInvitationEvent;
import app.events.net.NetworkConnectionEstablishedEvent;
import app.events.vcard.VCardLoadEvent;
import app.events.vcard.VCardSaveEvent;
import app.events.xmpp.LastActivityEvent;
import app.events.xmpp.SendBroadcastMessageEvent;
import app.events.xmpp.SendMessageEvent;
import app.events.xmpp.SendPacketEvent;
import app.events.xmpp.SendSublistEvent;
import app.events.xmpp.XMPPDisconnectEvent;
import app.logger.ILog;
import app.messaging.group.GroupInfoModel;
import app.notification.BadgeHandler;
import app.signin.LogoutReceiver;
import app.util.SharedPreferencesUtil;
import app.util.StringUtil;
import app.util.XMPPUtils;
import app.xmpp.packet.CallSignalPXProvider;
import app.xmpp.packet.TimePXProvider;
import app.xmpp.packet.broadcast.BroadcastIQ;
import app.xmpp.packet.channel.AbstractChannelIQ;
import app.xmpp.packet.channel.CastPXProvider;
import app.xmpp.packet.channel.ChannelElement;
import app.xmpp.packet.channel.ChannelIQEnter;
import app.xmpp.packet.channel.ChannelIQFollow;
import app.xmpp.packet.channel.ChannelIQInfo;
import app.xmpp.packet.channel.ChannelIQProvider;
import app.xmpp.packet.channel.ChannelIQUnfollow;
import app.xmpp.packet.channel.HistoryElement;
import app.xmpp.packet.channel.MultiChannelIQProvider;
import app.xmpp.packet.common.ContentXE;
import app.xmpp.packet.common.ContentXEProvider;
import app.xmpp.packet.common.OfflineXEProvider;
import app.xmpp.packet.common.TimeXEProvider;
import app.xmpp.packet.group.GroupIQProvider;
import app.xmpp.packet.group.GroupStatusProvider;
import app.xmpp.packet.groupv2.CreateXE;
import app.xmpp.packet.groupv2.DestroyGroupsXE;
import app.xmpp.packet.groupv2.GetGroupSettingXE;
import app.xmpp.packet.groupv2.GetGroupsXE;
import app.xmpp.packet.groupv2.GroupStatisticProvider;
import app.xmpp.packet.groupv2.GroupStatisticXE;
import app.xmpp.packet.groupv2.GroupXEProviderOther;
import app.xmpp.packet.groupv2.GroupXEProviderSimple;
import app.xmpp.packet.groupv2.GroupXEProviderWithGroupElement;
import app.xmpp.packet.groupv2.GroupXEProviderWithGroupList;
import app.xmpp.packet.groupv2.GroupXEProviderWithMemberList;
import app.xmpp.packet.groupv2.HistoryAckXE.Item;
import app.xmpp.packet.groupv2.HistoryXE;
import app.xmpp.packet.groupv2.InviteXE;
import app.xmpp.packet.groupv2.JoinXE;
import app.xmpp.packet.groupv2.KickXE;
import app.xmpp.packet.groupv2.LeaveXE;
import app.xmpp.packet.groupv2.ListGroupsXE;
import app.xmpp.packet.groupv2.MemberElement;
import app.xmpp.packet.groupv2.MembersXE;
import app.xmpp.packet.groupv2.RejectXE;
import app.xmpp.packet.groupv2.SelectiveHistoryXE;
import app.xmpp.packet.groupv2.SetInfoXE;
import app.xmpp.packet.groupv2.SettingXE.NotificationState;
import app.xmpp.packet.media.MediaPXProvider;
import app.xmpp.packet.reply.ReplyXEProvider;
import app.xmpp.packet.sublist.Sublist;
import app.xmpp.packet.sublist.SublistProvider;
import app.xmpp.packet.sublist.SublistPushProvider;
import app.xmpp.packet.vcard.VCardIQProvider;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.net.ssl.SSLSocketFactory;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnection.FromMode;
import org.jivesoftware.smack.XMPPException.XMPPErrorException;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Presence.Mode;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.parsing.ParsingExceptionCallback;
import org.jivesoftware.smack.parsing.UnparsablePacket;
import org.jivesoftware.smack.provider.ProviderManager;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.sasl.SASLError;
import org.jivesoftware.smack.sasl.SASLErrorException;
import org.jivesoftware.smack.sasl.packet.SaslStreamElements.AuthMechanism;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration.Builder;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.jivesoftware.smackx.iqlast.LastActivityManager;
import org.jivesoftware.smackx.iqlast.packet.LastActivity;
import org.jivesoftware.smackx.muc.packet.Destroy;
import org.jivesoftware.smackx.muc.packet.MUCInitialPresence.History;
import org.jivesoftware.smackx.muc.packet.MUCUser.Invite;
import org.jivesoftware.smackx.muc.packet.MUCUser.Status;
import org.jivesoftware.smackx.offline.packet.OfflineMessageRequest;
import org.jivesoftware.smackx.privacy.packet.PrivacyItem;
import org.jivesoftware.smackx.receipts.DeliveryReceipt;
import org.jivesoftware.smackx.receipts.DeliveryReceipt.Provider;
import org.jivesoftware.smackx.receipts.DeliveryReceiptManager;
import org.jivesoftware.smackx.receipts.DeliveryReceiptManager.AutoReceiptMode;
import org.jivesoftware.smackx.search.UserSearch;
import org.jivesoftware.smackx.time.packet.Time;
import org.jivesoftware.smackx.vcardtemp.packet.VCard;
import se.emilsjolander.stickylistheaders.C1128R;

public class XMPPService extends Service {
    private static int f5031j;
    private static int f5032k;
    HashMap<String, StanzaListener> f5033a;
    private CustomXMPPConnection f5034b;
    private AccountManager f5035c;
    private ConnectionListener f5036d;
    private StanzaListener f5037e;
    private volatile boolean f5038f;
    private boolean f5039g;
    private Handler f5040h;
    private final long f5041i;

    /* renamed from: app.xmpp.XMPPService.10 */
    class AnonymousClass10 implements Runnable {
        final /* synthetic */ LastActivityEvent f5009a;
        final /* synthetic */ XMPPService f5010b;

        AnonymousClass10(XMPPService xMPPService, LastActivityEvent lastActivityEvent) {
            this.f5010b = xMPPService;
            this.f5009a = lastActivityEvent;
        }

        public void run() {
            try {
                Main.f1926a.m5683d("Getting Last Activity for " + this.f5009a.m4982a().m7390d());
                LastActivity lastActivity = LastActivityManager.getInstanceFor(this.f5010b.f5034b).getLastActivity(this.f5009a.m4982a().m7390d());
                if (this.f5009a.m4984b() != null) {
                    this.f5009a.m4984b().m4981a(lastActivity.getIdleTime());
                }
            } catch (NoResponseException e) {
                Main.f1926a.m5683d("Failed to get Last Activity for " + this.f5009a.m4982a().m7390d());
                if (this.f5009a.m4984b() != null) {
                    this.f5009a.m4984b().m4980a();
                }
            } catch (XMPPErrorException e2) {
                Main.f1926a.m5683d("Failed to get Last Activity for " + this.f5009a.m4982a().m7390d());
                if (this.f5009a.m4984b() != null) {
                    this.f5009a.m4984b().m4980a();
                }
            } catch (NotConnectedException e3) {
                Main.f1926a.m5683d("Failed to get Last Activity for " + this.f5009a.m4982a().m7390d());
                if (this.f5009a.m4984b() != null) {
                    this.f5009a.m4984b().m4980a();
                }
            } catch (IllegalArgumentException e4) {
                Main.f1926a.m5683d("Failed to get Last Activity for " + this.f5009a.m4982a().m7390d());
                if (this.f5009a.m4984b() != null) {
                    this.f5009a.m4984b().m4980a();
                }
            } catch (ClassCastException e5) {
                Main.f1926a.m5683d("Failed to get Last Activity for " + this.f5009a.m4982a().m7390d());
                if (this.f5009a.m4984b() != null) {
                    this.f5009a.m4984b().m4980a();
                }
            }
        }
    }

    /* renamed from: app.xmpp.XMPPService.12 */
    class AnonymousClass12 implements Runnable {
        final /* synthetic */ SendBroadcastMessageEvent f5012a;
        final /* synthetic */ XMPPService f5013b;

        /* renamed from: app.xmpp.XMPPService.12.1 */
        class C05671 implements StanzaListener {
            final /* synthetic */ AnonymousClass12 f5011a;

            C05671(AnonymousClass12 anonymousClass12) {
                this.f5011a = anonymousClass12;
            }

            public void processPacket(Stanza stanza) {
                if (!(stanza instanceof IQ)) {
                    return;
                }
                if (((IQ) stanza).getType() != Type.error) {
                    HistoryNormalMessageDataSource.m4720a().m4738a(this.f5011a.f5012a.m4992d(), this.f5011a.f5012a.m4991c());
                    if (this.f5011a.f5012a.m4976a() != null) {
                        this.f5011a.f5012a.m4976a().m4974a(this.f5011a.f5012a.m4990b().getStanzaId());
                    }
                } else if (this.f5011a.f5012a.m4976a() != null) {
                    this.f5011a.f5012a.m4976a().m4975b(this.f5011a.f5012a.m4990b().getStanzaId());
                }
            }
        }

        AnonymousClass12(XMPPService xMPPService, SendBroadcastMessageEvent sendBroadcastMessageEvent) {
            this.f5013b = xMPPService;
            this.f5012a = sendBroadcastMessageEvent;
        }

        public void run() {
            if (this.f5013b.f5034b.isAuthenticated()) {
                try {
                    this.f5013b.f5034b.sendIqWithResponseCallback(new BroadcastIQ(this.f5012a.m4991c(), this.f5012a.m4990b()), new C05671(this));
                } catch (NotConnectedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* renamed from: app.xmpp.XMPPService.13 */
    /* synthetic */ class AnonymousClass13 {
        static final /* synthetic */ int[] f5014a;
        static final /* synthetic */ int[] f5015b;

        static {
            f5015b = new int[Action.values().length];
            try {
                f5015b[Action.follow.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f5015b[Action.unfollow.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f5015b[Action.info.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            f5014a = new int[SASLError.values().length];
            try {
                f5014a[SASLError.not_authorized.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f5014a[SASLError.account_disabled.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f5014a[SASLError.credentials_expired.ordinal()] = 3;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f5014a[SASLError.aborted.ordinal()] = 4;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f5014a[SASLError.encryption_required.ordinal()] = 5;
            } catch (NoSuchFieldError e8) {
            }
            try {
                f5014a[SASLError.incorrect_encoding.ordinal()] = 6;
            } catch (NoSuchFieldError e9) {
            }
            try {
                f5014a[SASLError.invalid_authzid.ordinal()] = 7;
            } catch (NoSuchFieldError e10) {
            }
            try {
                f5014a[SASLError.invalid_mechanism.ordinal()] = 8;
            } catch (NoSuchFieldError e11) {
            }
            try {
                f5014a[SASLError.malformed_request.ordinal()] = 9;
            } catch (NoSuchFieldError e12) {
            }
            try {
                f5014a[SASLError.mechanism_too_weak.ordinal()] = 10;
            } catch (NoSuchFieldError e13) {
            }
            try {
                f5014a[SASLError.temporary_auth_failure.ordinal()] = 11;
            } catch (NoSuchFieldError e14) {
            }
        }
    }

    /* renamed from: app.xmpp.XMPPService.1 */
    class C05681 extends ParsingExceptionCallback {
        final /* synthetic */ XMPPService f5016a;

        C05681(XMPPService xMPPService) {
            this.f5016a = xMPPService;
        }

        public void handleUnparsablePacket(UnparsablePacket unparsablePacket) {
            String charSequence = unparsablePacket.getContent().toString();
            if (charSequence.startsWith("<message") && charSequence.contains("type='push'")) {
                Stanza parseStanza = PacketParserUtils.parseStanza(charSequence.replace("type='push'", "type='normal'").replace("<push>", "<push xmlns='http://bisphone.com/protocol/sublist'>"));
                if (parseStanza instanceof Message) {
                    NormalMessageHandler.m7415a().m7441a(parseStanza);
                }
            }
        }
    }

    /* renamed from: app.xmpp.XMPPService.2 */
    class C05692 implements StanzaListener {
        final /* synthetic */ XMPPService f5017a;

        C05692(XMPPService xMPPService) {
            this.f5017a = xMPPService;
        }

        public void processPacket(Stanza stanza) {
            if (stanza instanceof Message) {
                String from = stanza.getFrom();
                JabberId a = JabberId.m7386a(from);
                if (a == null) {
                    if (from.equals("egc*.bisphone.com")) {
                        GroupManager.m7323a().handleIncomingMessage((Message) stanza);
                    }
                    Main.f1926a.m5679b("Invalid Jabber ID, RETURN!");
                } else if (XMPPUtils.m7094b(a)) {
                    GroupManager.m7323a().handleIncomingMessage((Message) stanza);
                } else {
                    NormalMessageHandler.m7415a().m7441a(stanza);
                }
            } else if (stanza instanceof Sublist) {
                IQHandler.m7384a().m7385a((Sublist) stanza);
            }
        }
    }

    /* renamed from: app.xmpp.XMPPService.3 */
    class C05703 implements ConnectionListener {
        final /* synthetic */ XMPPService f5018a;

        C05703(XMPPService xMPPService) {
            this.f5018a = xMPPService;
        }

        public void connected(XMPPConnection xMPPConnection) {
            Main.f1926a.m5681c("xmpp connection listener: connected");
            EventBus.m12779a().m12795d(new ConnectionListenerEvent(ConnectionType.connecting));
        }

        public void authenticated(XMPPConnection xMPPConnection, boolean z) {
            Main.f1926a.m5681c("xmpp connection listener: authenticated, resumed: " + z);
            EventBus.m12779a().m12795d(new ConnectionListenerEvent(ConnectionType.authenticated));
            ContactSyncManager.m4495a().m4498b(true);
            if (!Main.m3912e()) {
                this.f5018a.m7522e();
            }
            List arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (ConversationGroupEntity conversationGroupEntity : GroupManager.m7323a().m7374h().values()) {
                if (conversationGroupEntity.m4283e() != null) {
                    if (conversationGroupEntity.m4283e().equals("REJECTING") || conversationGroupEntity.m4283e().equals("LEAVING") || conversationGroupEntity.m4283e().equals("LEFT_DELETE") || conversationGroupEntity.m4283e().equals("JOINING") || conversationGroupEntity.m4283e().equals("DESTROYING")) {
                        arrayList.add(conversationGroupEntity);
                    } else if (conversationGroupEntity.m4295j() == NotificationState.disablePending) {
                        conversationGroupEntity.m4272a(NotificationState.disable);
                        arrayList2.add(conversationGroupEntity);
                    } else if (conversationGroupEntity.m4295j() == NotificationState.soundPending) {
                        conversationGroupEntity.m4272a(NotificationState.sound);
                        arrayList2.add(conversationGroupEntity);
                    } else if (conversationGroupEntity.m4295j() == NotificationState.vibratePending) {
                        conversationGroupEntity.m4272a(NotificationState.vibrate);
                        arrayList2.add(conversationGroupEntity);
                    } else if (conversationGroupEntity.m4295j() == NotificationState.getPending) {
                        arrayList2.add(conversationGroupEntity);
                    }
                }
            }
            if (SharedPreferencesUtil.m7056a("ggln", true)) {
                SharedPreferencesUtil.m7059b("ggl");
                GroupManager.m7323a().m7345a(null);
                return;
            }
            synchronized (GroupManager.m7323a().f4905c) {
                GroupManager.m7323a().f4905c.clear();
                GroupManager.m7323a().f4905c.putAll(ConversationGroupDataSource.m4587a().m4612c());
            }
            GroupManager.m7323a().f4906d.clear();
            synchronized (GroupManager.m7323a().f4905c) {
                for (String str : GroupManager.m7323a().f4905c.keySet()) {
                    GroupManager.m7323a().m7340a(((Long) GroupManager.m7323a().f4905c.get(str)).longValue(), str, null);
                }
            }
            NormalMessageHandler.m7415a().m7443b();
            GroupManager.m7323a().m7359b();
            if (!SharedPreferencesUtil.m7060b(this.f5018a.getString(2131296929), Boolean.valueOf(true))) {
                VCardHandler.m7499a().m7504b();
            }
            GroupManager.m7323a().handlePendingGroupStatus(arrayList);
        }

        public void connectionClosed() {
            this.f5018a.f5039g = false;
            Main.f1926a.m5681c("xmpp connection listener: connectionClosed");
            XMPPService.f5031j = XMPPService.f5032k = 0;
            ContactSyncManager.m4495a().m4498b(false);
            EventBus.m12779a().m12795d(new ConnectionListenerEvent(ConnectionType.waitingForNetwork));
        }

        public void connectionClosedOnError(Exception exception) {
            this.f5018a.f5039g = false;
            Main.f1926a.m5681c("xmpp connection listener: connectionClosedOnError");
            Main.f1926a.m5682c((Throwable) exception);
            XMPPService.f5031j = XMPPService.f5032k = 0;
            ContactSyncManager.m4495a().m4498b(false);
            EventBus.m12779a().m12795d(new ConnectionListenerEvent(ConnectionType.waitingForNetwork));
        }

        public void reconnectingIn(int i) {
            Main.f1926a.m5681c("xmpp connection listener: reconnecting");
            EventBus.m12779a().m12795d(new ConnectionListenerEvent(NetworkConnectivityChangeReceiver.m7394a(this.f5018a) ? ConnectionType.connecting : ConnectionType.waitingForNetwork));
        }

        public void reconnectionSuccessful() {
            Main.f1926a.m5681c("xmpp connection listener: reconnectionSuccessful");
        }

        public void reconnectionFailed(Exception exception) {
            Main.f1926a.m5681c("xmpp connection listener: reconnectionFailed");
            Main.f1926a.m5682c((Throwable) exception);
        }
    }

    /* renamed from: app.xmpp.XMPPService.4 */
    class C05714 implements Runnable {
        final /* synthetic */ XMPPService f5019a;

        C05714(XMPPService xMPPService) {
            this.f5019a = xMPPService;
        }

        public void run() {
            for (ChannelEntity channelEntity : ChannelDataSource.m4547b()) {
                if (String.valueOf(channelEntity.m4159i()).length() > 13) {
                    channelEntity.m4151a(channelEntity.m4159i() / 1000);
                }
                this.f5019a.m7513a(channelEntity.m4150a(), HistoryElement.Type.since, channelEntity.m4159i());
            }
        }
    }

    /* renamed from: app.xmpp.XMPPService.5 */
    class C05725 implements Runnable {
        final /* synthetic */ String f5020a;
        final /* synthetic */ String f5021b;
        final /* synthetic */ XMPPService f5022c;

        C05725(XMPPService xMPPService, String str, String str2) {
            this.f5022c = xMPPService;
            this.f5020a = str;
            this.f5021b = str2;
        }

        public void run() {
            try {
                if (!this.f5022c.f5034b.isConnected()) {
                    this.f5022c.f5034b.connect();
                }
                this.f5022c.f5034b.login(this.f5020a, this.f5021b, "android");
            } catch (SASLErrorException e) {
                SASLErrorException sASLErrorException = e;
                Main.f1926a.m5683d("xmppConnectAndLogin SASLErrorException " + sASLErrorException.getSASLFailure().getSASLErrorString());
                SASLError fromString = SASLError.fromString(sASLErrorException.getSASLFailure().getSASLErrorString());
                if (fromString == null) {
                    Main.f1926a.m5679b("SASL Error String Is Not In ENUM");
                    if (sASLErrorException.getSASLFailure().getSASLErrorString().contains(AuthMechanism.ELEMENT)) {
                        fromString = SASLError.not_authorized;
                    } else {
                        fromString = SASLError.aborted;
                    }
                }
                switch (AnonymousClass13.f5014a[fromString.ordinal()]) {
                    case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                        Main.f1926a.m5679b("SASLErrorException, LOGOUT");
                        this.f5022c.sendBroadcast(new Intent(this.f5022c, LogoutReceiver.class).setAction("com.bistalk.bisphone.signin.LogoutReceiver.ACTION_LOGOUT"));
                        break;
                    default:
                        break;
                }
            } catch (Throwable e2) {
                Main.f1926a.m5683d("xmppConnectAndLogin NoResponseException");
                Main.f1926a.m5684d(e2);
                this.f5022c.f5034b.disconnect();
                Main.f1926a.m5683d("xmppConnectAndLogin Lock Released");
                this.f5022c.f5038f = false;
                this.f5022c.m7523f();
                return;
            } catch (Throwable e22) {
                Main.f1926a.m5683d("xmppConnectAndLogin " + e22.getClass().toString());
                Main.f1926a.m5684d(e22);
            }
            Main.f1926a.m5683d("xmppConnectAndLogin Lock Released");
            this.f5022c.f5038f = false;
        }
    }

    /* renamed from: app.xmpp.XMPPService.6 */
    class C05736 implements StanzaListener {
        final /* synthetic */ SendMessageEvent f5023a;
        final /* synthetic */ XMPPService f5024b;

        C05736(XMPPService xMPPService, SendMessageEvent sendMessageEvent) {
            this.f5024b = xMPPService;
            this.f5023a = sendMessageEvent;
        }

        public void processPacket(Stanza stanza) {
            StanzaListener stanzaListener = (StanzaListener) this.f5024b.f5033a.get(stanza.getStanzaId());
            if (stanzaListener != null && this.f5024b.f5034b != null) {
                if (this.f5023a.m4976a() != null) {
                    this.f5023a.m4976a().m4974a(stanza.getStanzaId());
                }
                this.f5024b.f5034b.removeStanzaAcknowledgedListener(stanzaListener);
            }
        }
    }

    /* renamed from: app.xmpp.XMPPService.7 */
    class C05747 implements Runnable {
        final /* synthetic */ VCardSaveEvent f5025a;
        final /* synthetic */ XMPPService f5026b;

        C05747(XMPPService xMPPService, VCardSaveEvent vCardSaveEvent) {
            this.f5026b = xMPPService;
            this.f5025a = vCardSaveEvent;
        }

        public void run() {
            Throwable e;
            if (this.f5026b.f5034b.isAuthenticated()) {
                try {
                    this.f5025a.m4964a().setType(Type.set);
                    this.f5026b.f5034b.createPacketCollectorAndSend(this.f5025a.m4964a()).nextResultOrThrow();
                    if (this.f5025a.m4965b() != null) {
                        this.f5025a.m4965b().m4962a();
                    }
                } catch (NoResponseException e2) {
                    e = e2;
                    Main.f1926a.m5684d(e);
                    if (this.f5025a.m4965b() == null) {
                        this.f5025a.m4965b().m4963b();
                    }
                } catch (XMPPErrorException e3) {
                    e = e3;
                    Main.f1926a.m5684d(e);
                    if (this.f5025a.m4965b() == null) {
                        this.f5025a.m4965b().m4963b();
                    }
                } catch (NotConnectedException e4) {
                    e = e4;
                    Main.f1926a.m5684d(e);
                    if (this.f5025a.m4965b() == null) {
                        this.f5025a.m4965b().m4963b();
                    }
                } catch (IllegalArgumentException e5) {
                    e = e5;
                    Main.f1926a.m5684d(e);
                    if (this.f5025a.m4965b() == null) {
                        this.f5025a.m4965b().m4963b();
                    }
                }
            } else if (this.f5025a.m4965b() != null) {
                this.f5025a.m4965b().m4963b();
            }
        }
    }

    /* renamed from: app.xmpp.XMPPService.8 */
    class C05758 implements Runnable {
        final /* synthetic */ VCardLoadEvent f5027a;
        final /* synthetic */ XMPPService f5028b;

        C05758(XMPPService xMPPService, VCardLoadEvent vCardLoadEvent) {
            this.f5028b = xMPPService;
            this.f5027a = vCardLoadEvent;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            r6 = this;
            r3 = new java.util.ArrayList;
            r1 = r6.f5027a;
            r1 = r1.m4957a();
            r3.<init>(r1);
            r1 = r6.f5028b;
            r1 = r1.f5034b;
            r1 = r1.isAuthenticated();
            if (r1 != 0) goto L_0x0047;
        L_0x0017:
            r1 = r6.f5027a;
            r1 = r1.m4959b();
            r2 = r1.iterator();
        L_0x0021:
            r1 = r2.hasNext();
            if (r1 == 0) goto L_0x0035;
        L_0x0027:
            r1 = r2.next();
            r1 = (java.lang.String) r1;
            r4 = app.xmpp.VCardHandler.m7499a();
            r4.m7505b(r1);
            goto L_0x0021;
        L_0x0035:
            r1 = r6.f5027a;
            r1 = r1.m4960c();
            if (r1 == 0) goto L_0x0046;
        L_0x003d:
            r1 = r6.f5027a;
            r1 = r1.m4960c();
            r1.m4956a(r3);
        L_0x0046:
            return;
        L_0x0047:
            r2 = "";
            r1 = r6.f5027a;	 Catch:{ NoResponseException -> 0x009f, XMPPErrorException -> 0x00be, NotConnectedException -> 0x00c0, IllegalArgumentException -> 0x00c2 }
            r1 = r1.m4959b();	 Catch:{ NoResponseException -> 0x009f, XMPPErrorException -> 0x00be, NotConnectedException -> 0x00c0, IllegalArgumentException -> 0x00c2 }
            r4 = r1.iterator();	 Catch:{ NoResponseException -> 0x009f, XMPPErrorException -> 0x00be, NotConnectedException -> 0x00c0, IllegalArgumentException -> 0x00c2 }
        L_0x0053:
            r1 = r4.hasNext();	 Catch:{ NoResponseException -> 0x009f, XMPPErrorException -> 0x00be, NotConnectedException -> 0x00c0, IllegalArgumentException -> 0x00c2 }
            if (r1 == 0) goto L_0x00ac;
        L_0x0059:
            r1 = r4.next();	 Catch:{ NoResponseException -> 0x009f, XMPPErrorException -> 0x00be, NotConnectedException -> 0x00c0, IllegalArgumentException -> 0x00c2 }
            r0 = r1;
            r0 = (java.lang.String) r0;	 Catch:{ NoResponseException -> 0x009f, XMPPErrorException -> 0x00be, NotConnectedException -> 0x00c0, IllegalArgumentException -> 0x00c2 }
            r2 = r0;
            r1 = new app.xmpp.packet.vcard.VCard;	 Catch:{ ClassCastException -> 0x0084 }
            r1.<init>();	 Catch:{ ClassCastException -> 0x0084 }
            r1.setTo(r2);	 Catch:{ ClassCastException -> 0x0084 }
            r5 = r6.f5028b;	 Catch:{ ClassCastException -> 0x0084 }
            r5 = r5.f5034b;	 Catch:{ ClassCastException -> 0x0084 }
            r1 = r5.createPacketCollectorAndSend(r1);	 Catch:{ ClassCastException -> 0x0084 }
            r1 = r1.nextResultOrThrow();	 Catch:{ ClassCastException -> 0x0084 }
            r1 = (app.xmpp.packet.vcard.VCard) r1;	 Catch:{ ClassCastException -> 0x0084 }
            r3.add(r1);	 Catch:{ ClassCastException -> 0x0084 }
            r1 = app.xmpp.VCardHandler.m7499a();	 Catch:{ ClassCastException -> 0x0084 }
            r1.m7505b(r2);	 Catch:{ ClassCastException -> 0x0084 }
            goto L_0x0053;
        L_0x0084:
            r1 = move-exception;
            r1 = app.Main.f1926a;	 Catch:{ NoResponseException -> 0x009f, XMPPErrorException -> 0x00be, NotConnectedException -> 0x00c0, IllegalArgumentException -> 0x00c2 }
            r5 = "ClassCastException to VCard: vCard is not available.";
            r1.m5685e(r5);	 Catch:{ NoResponseException -> 0x009f, XMPPErrorException -> 0x00be, NotConnectedException -> 0x00c0, IllegalArgumentException -> 0x00c2 }
            r1 = new app.xmpp.packet.vcard.VCard;	 Catch:{ NoResponseException -> 0x009f, XMPPErrorException -> 0x00be, NotConnectedException -> 0x00c0, IllegalArgumentException -> 0x00c2 }
            r1.<init>();	 Catch:{ NoResponseException -> 0x009f, XMPPErrorException -> 0x00be, NotConnectedException -> 0x00c0, IllegalArgumentException -> 0x00c2 }
            r1.setFrom(r2);	 Catch:{ NoResponseException -> 0x009f, XMPPErrorException -> 0x00be, NotConnectedException -> 0x00c0, IllegalArgumentException -> 0x00c2 }
            r3.add(r1);	 Catch:{ NoResponseException -> 0x009f, XMPPErrorException -> 0x00be, NotConnectedException -> 0x00c0, IllegalArgumentException -> 0x00c2 }
            r1 = app.xmpp.VCardHandler.m7499a();	 Catch:{ NoResponseException -> 0x009f, XMPPErrorException -> 0x00be, NotConnectedException -> 0x00c0, IllegalArgumentException -> 0x00c2 }
            r1.m7505b(r2);	 Catch:{ NoResponseException -> 0x009f, XMPPErrorException -> 0x00be, NotConnectedException -> 0x00c0, IllegalArgumentException -> 0x00c2 }
            goto L_0x0053;
        L_0x009f:
            r1 = move-exception;
        L_0x00a0:
            r4 = app.Main.f1926a;
            r4.m5684d(r1);
            r1 = app.xmpp.VCardHandler.m7499a();
            r1.m7505b(r2);
        L_0x00ac:
            r1 = r6.f5027a;
            r1 = r1.m4960c();
            if (r1 == 0) goto L_0x0046;
        L_0x00b4:
            r1 = r6.f5027a;
            r1 = r1.m4960c();
            r1.m4956a(r3);
            goto L_0x0046;
        L_0x00be:
            r1 = move-exception;
            goto L_0x00a0;
        L_0x00c0:
            r1 = move-exception;
            goto L_0x00a0;
        L_0x00c2:
            r1 = move-exception;
            goto L_0x00a0;
            */
            throw new UnsupportedOperationException("Method not decompiled: app.xmpp.XMPPService.8.run():void");
        }
    }

    /* renamed from: app.xmpp.XMPPService.9 */
    class C05769 implements Runnable {
        final /* synthetic */ ChannelEvent f5029a;
        final /* synthetic */ XMPPService f5030b;

        C05769(XMPPService xMPPService, ChannelEvent channelEvent) {
            this.f5030b = xMPPService;
            this.f5029a = channelEvent;
        }

        public void run() {
            if (this.f5030b.f5034b.isAuthenticated()) {
                IQ channelIQFollow;
                switch (AnonymousClass13.f5015b[this.f5029a.m4853b().ordinal()]) {
                    case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                        channelIQFollow = new ChannelIQFollow();
                        channelIQFollow.setType(Type.set);
                        break;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                        channelIQFollow = new ChannelIQUnfollow();
                        channelIQFollow.setType(Type.set);
                        break;
                    default:
                        channelIQFollow = new ChannelIQInfo();
                        channelIQFollow.setType(Type.get);
                        break;
                }
                ChannelElement channelElement = new ChannelElement();
                channelElement.m7549a(this.f5029a.m4851a());
                channelIQFollow.m7534a(channelElement);
                try {
                    Stanza nextResultOrThrow = this.f5030b.f5034b.createPacketCollectorAndSend(channelIQFollow).nextResultOrThrow();
                    if (nextResultOrThrow instanceof AbstractChannelIQ) {
                        AbstractChannelIQ abstractChannelIQ = (AbstractChannelIQ) nextResultOrThrow;
                        if (abstractChannelIQ.getType() == Type.result) {
                            Long l;
                            if (abstractChannelIQ.m7537b() == null) {
                                l = null;
                            } else {
                                l = Long.valueOf(abstractChannelIQ.m7537b().m7568a());
                            }
                            if (this.f5029a.m4854c() != null) {
                                this.f5029a.m4854c().m4850a(abstractChannelIQ.m7533a(), l);
                            }
                            if (this.f5029a.m4853b() == Action.follow) {
                                this.f5030b.m7513a(this.f5029a.m4851a(), HistoryElement.Type.count, 20);
                                return;
                            }
                            return;
                        }
                        Main.f1926a.m5679b("Error on channel iq response: " + nextResultOrThrow.getError().getConditionText());
                    }
                } catch (XMPPErrorException e) {
                    Main.f1926a.m5685e("onEvent ChannelEvent XMPPErrorException: " + this.f5029a.m4851a());
                } catch (NotConnectedException e2) {
                    Main.f1926a.m5685e("onEvent ChannelEvent NotConnectedException: " + this.f5029a.m4851a());
                } catch (NoResponseException e3) {
                    Main.f1926a.m5685e("onEvent ChannelEvent NoResponseException: " + this.f5029a.m4851a());
                }
                if (this.f5029a.m4854c() != null) {
                    this.f5029a.m4854c().m4849a();
                    return;
                }
                return;
            }
            this.f5030b.m7523f();
            if (this.f5029a.m4854c() != null) {
                this.f5029a.m4854c().m4849a();
            }
        }
    }

    public XMPPService() {
        this.f5038f = false;
        this.f5041i = 3000;
        Main.f1926a.m5685e("XmppService Constructor");
    }

    public void onCreate() {
        Main.f1926a.m5685e("XmppService onCreate");
        this.f5040h = new Handler(Looper.getMainLooper());
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        Main.f1926a.m5681c("XmppService onStartCommand");
        if (SharedPreferencesUtil.m7058b("udf", PrivacyItem.SUBSCRIPTION_NONE).charAt(0) != 'f') {
            BadgeHandler.m6597a().m6604a(true);
            if (this.f5034b == null) {
                Main.f1926a.m5681c("XmppService onStartCommand: starting xmpp");
                m7510a();
            } else if (this.f5034b.isConnected() && this.f5034b.isAuthenticated()) {
                Main.f1926a.m5681c("XmppService onStartCommand: xmpp is connected");
            } else {
                Main.f1926a.m5681c("XmppService onStartCommand: connect and login xmpp");
                m7523f();
            }
        }
        return 1;
    }

    public IBinder onBind(Intent intent) {
        Main.f1926a.m5685e("XmppService onBind");
        return null;
    }

    public void onRebind(Intent intent) {
        Main.f1926a.m5685e("XmppService onRebind");
        super.onRebind(intent);
    }

    public boolean onUnbind(Intent intent) {
        Main.f1926a.m5685e("XmppService onUnbind");
        return super.onUnbind(intent);
    }

    public void onLowMemory() {
        Main.f1926a.m5685e("XmppService onLowMemory");
        super.onLowMemory();
    }

    public void onTrimMemory(int i) {
        Main.f1926a.m5685e("XmppService onTrimMemory: level " + i);
        super.onTrimMemory(i);
    }

    public void onDestroy() {
        Main.f1926a.m5685e("XmppService onDestroy");
        super.onDestroy();
    }

    private void m7510a() {
        SmackConfiguration.setPacketCollectorSize(1024);
        ProviderManager.addExtensionProvider(DeliveryReceipt.ELEMENT, DeliveryReceipt.NAMESPACE, new Provider());
        ProviderManager.addIQProvider(UserSearch.ELEMENT, "http://bisphone.com/protocol/sublist", new SublistProvider());
        ProviderManager.addExtensionProvider("push", "http://bisphone.com/protocol/sublist", new SublistPushProvider());
        ProviderManager.addIQProvider(UserSearch.ELEMENT, "http://bisphone.com/protocol/group#accept_invitation", new GroupIQProvider());
        ProviderManager.addIQProvider(UserSearch.ELEMENT, "http://bisphone.com/protocol/group#create_group", new GroupIQProvider());
        ProviderManager.addIQProvider(UserSearch.ELEMENT, "http://bisphone.com/protocol/group#invite_member", new GroupIQProvider());
        ProviderManager.addIQProvider(UserSearch.ELEMENT, "http://bisphone.com/protocol/group#leave_group", new GroupIQProvider());
        ProviderManager.addIQProvider(UserSearch.ELEMENT, "http://bisphone.com/protocol/group#get_members", new GroupIQProvider());
        ProviderManager.addExtensionProvider(Status.ELEMENT, "http://bisphone.com/protocol/group#set_member_status", new GroupStatusProvider());
        ProviderManager.addIQProvider(UserSearch.ELEMENT, "http://bisphone.com/protocol/channel/consumer/action#follow", new ChannelIQProvider());
        ProviderManager.addIQProvider(UserSearch.ELEMENT, "http://bisphone.com/protocol/channel/consumer/action#unfollow", new ChannelIQProvider());
        ProviderManager.addIQProvider(UserSearch.ELEMENT, "http://bisphone.com/protocol/channel/common/action#enter", new ChannelIQProvider());
        ProviderManager.addIQProvider(UserSearch.ELEMENT, "http://bisphone.com/protocol/channel/common/action#leave", new ChannelIQProvider());
        ProviderManager.addIQProvider(UserSearch.ELEMENT, "http://bisphone.com/protocol/channel/common/discover#info", new ChannelIQProvider());
        ProviderManager.addIQProvider(UserSearch.ELEMENT, "http://bisphone.com/protocol/channel/common/discover#list", new MultiChannelIQProvider());
        ProviderManager.addExtensionProvider("cast", "http://bisphone.com/protocol/cast", new CastPXProvider());
        ProviderManager.addExtensionProvider(Time.ELEMENT, "http://bisphone.com/protocol/time", new TimePXProvider());
        ProviderManager.addExtensionProvider("media", "http://bisphone.com/protocol/media", new MediaPXProvider());
        ProviderManager.addExtensionProvider("call", "http://bisphone.com/protocol/media", new CallSignalPXProvider());
        ProviderManager.removeIQProvider(VCard.ELEMENT, VCard.NAMESPACE);
        ProviderManager.addIQProvider(VCard.ELEMENT, VCard.NAMESPACE, new VCardIQProvider());
        ProviderManager.addExtensionProvider("create", "bpn:group:v2", new GroupXEProviderWithGroupElement());
        ProviderManager.addExtensionProvider("create-ack", "bpn:group:v2", new GroupXEProviderOther());
        ProviderManager.addExtensionProvider("content", "bpn:common:v1", new ContentXEProvider());
        ProviderManager.addExtensionProvider(Invite.ELEMENT, "bpn:group:v2", new GroupXEProviderWithMemberList());
        ProviderManager.addExtensionProvider("invite-ack", "bpn:group:v2", new GroupXEProviderWithMemberList());
        ProviderManager.addExtensionProvider("leave", "bpn:group:v2", new GroupXEProviderSimple());
        ProviderManager.addExtensionProvider("leave-ack", "bpn:group:v2", new GroupXEProviderSimple());
        ProviderManager.addExtensionProvider("kick", "bpn:group:v2", new GroupXEProviderWithMemberList());
        ProviderManager.addExtensionProvider("kick-ack", "bpn:group:v2", new GroupXEProviderWithMemberList());
        ProviderManager.addExtensionProvider("reject", "bpn:group:v2", new GroupXEProviderSimple());
        ProviderManager.addExtensionProvider("reject-ack", "bpn:group:v2", new GroupXEProviderSimple());
        ProviderManager.addExtensionProvider("join", "bpn:group:v2", new GroupXEProviderSimple());
        ProviderManager.addExtensionProvider("join-ack", "bpn:group:v2", new GroupXEProviderSimple());
        ProviderManager.addExtensionProvider("get-info", "bpn:group:v2", new GroupXEProviderSimple());
        ProviderManager.addExtensionProvider("get-info-ack", "bpn:group:v2", new GroupXEProviderWithGroupElement());
        ProviderManager.addExtensionProvider("info", "bpn:group:v2", new GroupXEProviderOther());
        ProviderManager.addExtensionProvider("set-info", "bpn:group:v2", new GroupXEProviderWithGroupElement());
        ProviderManager.addExtensionProvider("set-info-ack", "bpn:group:v2", new GroupXEProviderSimple());
        ProviderManager.addExtensionProvider("group-statistics", "bpn:group:v2", new GroupXEProviderSimple());
        ProviderManager.addExtensionProvider("group-statistics-ack", "bpn:group:v2", new GroupStatisticProvider());
        ProviderManager.addExtensionProvider(Destroy.ELEMENT, "bpn:group:v2", new GroupXEProviderSimple());
        ProviderManager.addExtensionProvider("destroy-ack", "bpn:group:v2", new GroupXEProviderSimple());
        ProviderManager.addExtensionProvider("members", "bpn:group:v2", new GroupXEProviderOther());
        ProviderManager.addExtensionProvider("members-ack", "bpn:group:v2", new GroupXEProviderWithMemberList());
        ProviderManager.addExtensionProvider(History.ELEMENT, "bpn:group:v2", new GroupXEProviderOther());
        ProviderManager.addExtensionProvider("history-ack", "bpn:group:v2", new GroupXEProviderOther());
        ProviderManager.addExtensionProvider("oto-event", "bpn:group:v2", new GroupXEProviderOther());
        ProviderManager.addExtensionProvider("event", "bpn:group:v2", new GroupXEProviderOther());
        ProviderManager.addExtensionProvider("get-groups", "bpn:group:v2", new GroupXEProviderOther());
        ProviderManager.addExtensionProvider("get-groups-ack", "bpn:group:v2", new GroupXEProviderWithGroupList());
        ProviderManager.addExtensionProvider(OfflineMessageRequest.ELEMENT, "bpn:common:v1", new OfflineXEProvider());
        ProviderManager.addExtensionProvider(Time.ELEMENT, "bpn:common:v1", new TimeXEProvider());
        ProviderManager.addExtensionProvider("err", "bpn:fault:v2", new GroupXEProviderOther());
        ProviderManager.addExtensionProvider("reply-audio", "bpn:common:v1:reply", new ReplyXEProvider());
        ProviderManager.addExtensionProvider("reply-photo", "bpn:common:v1:reply", new ReplyXEProvider());
        ProviderManager.addExtensionProvider("reply-sticker", "bpn:common:v1:reply", new ReplyXEProvider());
        ProviderManager.addExtensionProvider("reply-map", "bpn:common:v1:reply", new ReplyXEProvider());
        ProviderManager.addExtensionProvider("reply-text", "bpn:common:v1:reply", new ReplyXEProvider());
        ProviderManager.addExtensionProvider("reply-video", "bpn:common:v1:reply", new ReplyXEProvider());
        this.f5035c = AccountManager.m3934a();
        this.f5034b = new CustomXMPPConnection(m7517b());
        this.f5034b.setFromMode(FromMode.USER);
        this.f5034b.setUseStreamManagementResumption(true);
        this.f5034b.setPreferredResumptionTime(60);
        if (!EventBus.m12779a().m12793b(this)) {
            EventBus.m12779a().m12791a((Object) this);
        }
        this.f5034b.setPacketReplyTimeout(15000);
        this.f5037e = m7519c();
        this.f5034b.addAsyncStanzaListener(this.f5037e, null);
        this.f5036d = m7521d();
        this.f5034b.addConnectionListener(this.f5036d);
        ReconnectionManager.m7480a(this.f5034b).m7491b();
        DeliveryReceiptManager.getInstanceFor(this.f5034b).setAutoReceiptMode(AutoReceiptMode.always);
        Roster.getInstanceFor(this.f5034b).setRosterLoadedAtLogin(false);
        LastActivityManager.getInstanceFor(this.f5034b).enable();
        this.f5034b.setParsingExceptionCallback(new C05681(this));
        this.f5033a = new HashMap();
        m7523f();
    }

    private XMPPTCPConnectionConfiguration m7517b() {
        Builder builder = XMPPTCPConnectionConfiguration.builder();
        builder.setSecurityMode(SecurityMode.disabled);
        builder.setSocketFactory(SSLSocketFactory.getDefault());
        builder.setCompressionEnabled(true);
        builder.setSendPresence(true);
        builder.setHost("chatng.bisphone.com");
        builder.setPort(6623);
        builder.setServiceName("bisphone.com");
        builder.setConnectTimeout(30000);
        return builder.build();
    }

    private StanzaListener m7519c() {
        return new C05692(this);
    }

    private ConnectionListener m7521d() {
        return new C05703(this);
    }

    private void m7522e() {
        if (!this.f5039g) {
            this.f5039g = true;
            new Handler(Looper.getMainLooper()).postDelayed(new C05714(this), 2500);
        }
    }

    public void onEvent(EnterAllChannels enterAllChannels) {
        if (this.f5034b.isAuthenticated()) {
            m7522e();
        }
    }

    private synchronized void m7523f() {
        if (this.f5038f) {
            Main.f1926a.m5683d("xmppConnectAndLogin Locked, return");
        } else {
            Main.f1926a.m5683d("xmppConnectAndLogin Lock Acquired");
            this.f5038f = true;
            if (this.f5034b.isAuthenticated()) {
                Main.f1926a.m5683d("xmppConnectAndLogin isAuthenticated, return");
                Main.f1926a.m5683d("xmppConnectAndLogin Lock Released");
                this.f5038f = false;
            } else {
                String c = this.f5035c.m3937c();
                String d = this.f5035c.m3938d();
                if (c == null || d == null) {
                    Main.f1926a.m5679b("xmppConnectAndLogin User or pass not found, LOGOUT");
                    sendBroadcast(new Intent(this, LogoutReceiver.class).setAction("com.bistalk.bisphone.signin.LogoutReceiver.ACTION_LOGOUT"));
                    Main.f1926a.m5683d("xmppConnectAndLogin Lock Released");
                    this.f5038f = false;
                } else {
                    EventBus.m12779a().m12795d(new ConnectionListenerEvent(NetworkConnectivityChangeReceiver.m7394a(this) ? ConnectionType.connecting : ConnectionType.waitingForNetwork));
                    new Thread(new C05725(this, c, d)).start();
                }
            }
        }
    }

    public void onEvent(NetworkConnectionEstablishedEvent networkConnectionEstablishedEvent) {
        m7523f();
    }

    static {
        f5031j = 0;
        f5032k = 0;
    }

    public void onEvent(SendMessageEvent sendMessageEvent) {
        Object obj = 1;
        Stanza b = sendMessageEvent.m4993b();
        JabberId a = JabberId.m7386a(b.getTo());
        if (a != null && XMPPUtils.m7094b(a)) {
            synchronized (GroupManager.m7323a().f4905c) {
                if (!GroupManager.m7323a().f4905c.containsKey(a.m7390d())) {
                    obj = null;
                }
            }
        }
        if (obj != null) {
            try {
                StanzaListener c05736 = new C05736(this, sendMessageEvent);
                if (this.f5033a == null) {
                    this.f5033a = new HashMap();
                }
                this.f5033a.put(b.getStanzaId(), c05736);
                this.f5034b.addStanzaAcknowledgedListener(c05736);
                this.f5034b.sendStanza(b);
                ILog iLog = Main.f1926a;
                Object[] objArr = new Object[3];
                objArr[0] = "Total Sent:";
                int i = f5031j + 1;
                f5031j = i;
                objArr[1] = Integer.valueOf(i);
                objArr[2] = b.getStanzaId();
                iLog.m5683d(String.format("%-15s %4d id: %1s", objArr));
                return;
            } catch (NotConnectedException e) {
                m7523f();
            }
        }
        if (sendMessageEvent.m4976a() != null) {
            sendMessageEvent.m4976a().m4975b(b.getStanzaId());
        }
    }

    public void onEvent(SendPacketEvent sendPacketEvent) {
        try {
            this.f5034b.sendStanza(sendPacketEvent.m4994b());
            if (sendPacketEvent.m4976a() != null) {
                sendPacketEvent.m4976a().m4974a(sendPacketEvent.m4994b().getStanzaId());
            }
        } catch (NotConnectedException e) {
            m7523f();
            if (sendPacketEvent.m4976a() != null) {
                sendPacketEvent.m4976a().m4975b(sendPacketEvent.m4994b().getStanzaId());
            }
        }
    }

    public void onEvent(SendSublistEvent sendSublistEvent) {
        List c = ContactDataSource.m4553a().m4572c();
        if (c == null || c.size() == 0) {
            Main.f1926a.m5681c("sendSublistEvent: no contacts to report, return");
            return;
        }
        try {
            this.f5034b.sendStanza(XMPPUtils.m7088a(c, "sync"));
        } catch (NotConnectedException e) {
            Main.f1926a.m5685e("Not Connected Exception");
        }
    }

    public void onEvent(XMPPDisconnectEvent xMPPDisconnectEvent) {
        EventBus.m12779a().m12794c(this);
        Main.f1926a.m5681c("XmppService XmppDisconnect onEvent: disconnected");
        if (!SharedPreferencesUtil.m7061c("ggln")) {
            EventBus.m12779a().m12795d(new ConnectionListenerEvent(ConnectionType.disconnected));
        }
        this.f5034b.removeAsyncStanzaListener(this.f5037e);
        this.f5034b.removeConnectionListener(this.f5036d);
        try {
            this.f5034b.disconnect(new Presence(Presence.Type.unavailable, "", 1, Mode.chat));
            Main.f1926a.m5681c("XmppService XmppDisconnect onEvent: disconnected");
        } catch (NotConnectedException e) {
            e.printStackTrace();
        }
        stopSelf();
    }

    public void onEvent(VCardSaveEvent vCardSaveEvent) {
        new Thread(new C05747(this, vCardSaveEvent)).start();
    }

    public void onEvent(VCardLoadEvent vCardLoadEvent) {
        new Thread(new C05758(this, vCardLoadEvent)).start();
    }

    public void onEvent(ChannelEvent channelEvent) {
        new Thread(new C05769(this, channelEvent)).start();
    }

    private void m7513a(String str, HistoryElement.Type type, long j) {
        Stanza channelIQEnter = new ChannelIQEnter();
        channelIQEnter.setType(Type.set);
        ChannelElement channelElement = new ChannelElement();
        channelElement.m7549a(str);
        channelIQEnter.m7534a(channelElement);
        channelIQEnter.m7535a(new HistoryElement(type, j));
        try {
            this.f5034b.sendStanza(channelIQEnter);
        } catch (NotConnectedException e) {
            Main.f1926a.m5685e("enterChannel NotConnectedException");
        }
    }

    public void onEvent(LastActivityEvent lastActivityEvent) {
        new Thread(new AnonymousClass10(this, lastActivityEvent)).start();
    }

    public void onEvent(GroupInfoModel groupInfoModel) {
        if (this.f5034b.isConnected() && this.f5034b.isAuthenticated()) {
            ExtensionElement setInfoXE = new SetInfoXE(groupInfoModel.m6247a());
            ExtensionElement contentXE = new ContentXE(ContentXE.Type.command);
            Stanza message = new Message(groupInfoModel.m6249b(), Message.Type.groupchat);
            message.setStanzaId(StringUtil.m7062a());
            message.addExtension(setInfoXE);
            message.addExtension(contentXE);
            try {
                this.f5034b.sendStanza(message);
                GroupManager.m7323a().m7357a(new GMRecord(System.currentTimeMillis() + 3000, 3000, message, 2));
                if (groupInfoModel.m6250c() != null) {
                    groupInfoModel.m6250c().m6209a();
                    return;
                }
                return;
            } catch (NotConnectedException e) {
                e.printStackTrace();
                if (groupInfoModel.m6250c() != null) {
                    groupInfoModel.m6250c().m6210a(null);
                    return;
                }
                return;
            }
        }
        m7523f();
        if (groupInfoModel.m6250c() != null) {
            groupInfoModel.m6250c().m6210a(null);
        }
    }

    public void onEvent(GetGroupListEvent getGroupListEvent) {
        ExtensionElement contentXE = new ContentXE(ContentXE.Type.command);
        ExtensionElement listGroupsXE = new ListGroupsXE(getGroupListEvent.m4880a());
        Stanza message = new Message("egc*.bisphone.com", Message.Type.groupchat);
        message.setStanzaId(StringUtil.m7062a());
        message.addExtension(contentXE);
        message.addExtension(listGroupsXE);
        try {
            this.f5034b.sendStanza(message);
            GroupManager.m7323a().m7357a(new GMRecord(System.currentTimeMillis() + 3000, 3000, message, 2));
            if (getGroupListEvent.m4883b() != null) {
                getGroupListEvent.m4883b().m6209a();
            }
        } catch (NotConnectedException e) {
            e.printStackTrace();
            if (getGroupListEvent.m4883b() != null) {
                getGroupListEvent.m4883b().m6210a(null);
            }
        }
    }

    public void onEvent(GetGroupInfoEvent getGroupInfoEvent) {
        ExtensionElement contentXE = new ContentXE(ContentXE.Type.command);
        ExtensionElement getGroupsXE = new GetGroupsXE();
        Stanza message = new Message(getGroupInfoEvent.m4879b(), Message.Type.groupchat);
        message.setStanzaId(StringUtil.m7062a());
        message.addExtension(contentXE);
        message.addExtension(getGroupsXE);
        try {
            this.f5034b.sendStanza(message);
            GroupManager.m7323a().m7357a(new GMRecord(System.currentTimeMillis() + 3000, 3000, message, 2));
            if (getGroupInfoEvent.m4876a() != null) {
                getGroupInfoEvent.m4876a().m6209a();
            }
        } catch (NotConnectedException e) {
            e.printStackTrace();
            if (getGroupInfoEvent.m4876a() != null) {
                getGroupInfoEvent.m4876a().m6210a(null);
            }
        }
    }

    public void onEvent(GetMemberListEvent getMemberListEvent) {
        ExtensionElement contentXE = new ContentXE(ContentXE.Type.command);
        ExtensionElement membersXE = new MembersXE(getMemberListEvent.m4892a());
        Stanza message = new Message(getMemberListEvent.m4896b(), Message.Type.groupchat);
        message.setStanzaId(StringUtil.m7062a());
        message.addExtension(contentXE);
        message.addExtension(membersXE);
        try {
            this.f5034b.sendStanza(message);
            GroupManager.m7323a().m7357a(new GMRecord(System.currentTimeMillis() + 3000, 3000, message, 2));
            if (getMemberListEvent.m4897c() != null) {
                getMemberListEvent.m4897c().m6209a();
            }
        } catch (NotConnectedException e) {
            e.printStackTrace();
            if (getMemberListEvent.m4897c() != null) {
                getMemberListEvent.m4897c().m6210a(null);
            }
        }
    }

    public void onEvent(KickMemberEvent kickMemberEvent) {
        ExtensionElement contentXE = new ContentXE(ContentXE.Type.command);
        ExtensionElement kickXE = new KickXE();
        if (kickMemberEvent.m4924a() != null) {
            for (MemberElement a : kickMemberEvent.m4924a()) {
                kickXE.m7596a(a);
            }
            Stanza message = new Message(kickMemberEvent.m4928b(), Message.Type.groupchat);
            message.setStanzaId(StringUtil.m7062a());
            message.addExtension(contentXE);
            message.addExtension(kickXE);
            try {
                this.f5034b.sendStanza(message);
                GroupManager.m7323a().m7357a(new GMRecord(System.currentTimeMillis() + 3000, 3000, message, 2));
                if (kickMemberEvent.m4929c() != null) {
                    kickMemberEvent.m4929c().m6209a();
                }
            } catch (NotConnectedException e) {
                e.printStackTrace();
                if (kickMemberEvent.m4929c() != null) {
                    kickMemberEvent.m4929c().m6210a(null);
                }
            }
        }
    }

    public void onEvent(DestroyEvent destroyEvent) {
        ExtensionElement contentXE = new ContentXE(ContentXE.Type.command);
        ExtensionElement destroyGroupsXE = new DestroyGroupsXE();
        Stanza message = new Message(destroyEvent.m4875b(), Message.Type.groupchat);
        message.setStanzaId(StringUtil.m7062a());
        message.addExtension(contentXE);
        message.addExtension(destroyGroupsXE);
        try {
            this.f5034b.sendStanza(message);
            GroupManager.m7323a().m7357a(new GMRecord(System.currentTimeMillis() + 3000, 3000, message, 2));
            if (destroyEvent.m4873a() != null) {
                destroyEvent.m4873a().m6209a();
            }
        } catch (NotConnectedException e) {
            e.printStackTrace();
            if (destroyEvent.m4873a() != null) {
                destroyEvent.m4873a().m6210a(null);
            }
        }
    }

    public void onEvent(InviteMemberToGroupEvent inviteMemberToGroupEvent) {
        ExtensionElement contentXE = new ContentXE(ContentXE.Type.command);
        ExtensionElement inviteXE = new InviteXE();
        if (inviteMemberToGroupEvent.m4918a() != null) {
            for (MemberElement a : inviteMemberToGroupEvent.m4918a()) {
                inviteXE.m7596a(a);
            }
            Stanza message = new Message(inviteMemberToGroupEvent.m4922b(), Message.Type.groupchat);
            message.setStanzaId(StringUtil.m7062a());
            message.addExtension(contentXE);
            message.addExtension(inviteXE);
            try {
                this.f5034b.sendStanza(message);
                GroupManager.m7323a().m7357a(new GMRecord(System.currentTimeMillis() + 3000, 3000, message, 2));
                if (inviteMemberToGroupEvent.m4923c() != null) {
                    inviteMemberToGroupEvent.m4923c().m6209a();
                }
            } catch (NotConnectedException e) {
                e.printStackTrace();
                if (inviteMemberToGroupEvent.m4923c() != null) {
                    inviteMemberToGroupEvent.m4923c().m6210a(null);
                }
            }
        }
    }

    public void onEvent(GroupFetchHistoryEvent groupFetchHistoryEvent) {
        ExtensionElement contentXE = new ContentXE(ContentXE.Type.command);
        ExtensionElement historyXE = new HistoryXE(groupFetchHistoryEvent.m4902b().m7652a(), groupFetchHistoryEvent.m4902b().m7653b());
        Stanza message = new Message(groupFetchHistoryEvent.m4903c(), Message.Type.groupchat);
        message.setStanzaId(StringUtil.m7062a());
        message.addExtension(contentXE);
        message.addExtension(historyXE);
        try {
            this.f5034b.sendStanza(message);
            GroupManager.m7323a().m7357a(new GMRecord(System.currentTimeMillis() + 3000, 3000, message, 2));
            if (GroupManager.m7323a().f4903a.get(groupFetchHistoryEvent.m4903c()) == null) {
                GroupManager.m7323a().f4903a.put(groupFetchHistoryEvent.m4903c(), new GroupV2FetchHistoryModel());
            }
            ((GroupV2FetchHistoryModel) GroupManager.m7323a().f4903a.get(groupFetchHistoryEvent.m4903c())).m4043h();
            ((GroupV2FetchHistoryModel) GroupManager.m7323a().f4903a.get(groupFetchHistoryEvent.m4903c())).m4038c(message);
            if (groupFetchHistoryEvent.m4898a() != null) {
                groupFetchHistoryEvent.m4898a().m6209a();
            }
        } catch (NotConnectedException e) {
            e.printStackTrace();
            if (groupFetchHistoryEvent.m4898a() != null) {
                groupFetchHistoryEvent.m4898a().m6210a(null);
            }
        }
    }

    public void onEvent(GroupSelectiveFetchHistoryEvent groupSelectiveFetchHistoryEvent) {
        if (groupSelectiveFetchHistoryEvent != null && groupSelectiveFetchHistoryEvent.m4913d() != null && groupSelectiveFetchHistoryEvent.m4913d().size() != 0) {
            ExtensionElement contentXE = new ContentXE(ContentXE.Type.command);
            ExtensionElement selectiveHistoryXE = new SelectiveHistoryXE();
            for (String item : groupSelectiveFetchHistoryEvent.m4913d()) {
                selectiveHistoryXE.m7674a(new Item(item));
            }
            Stanza message = new Message(groupSelectiveFetchHistoryEvent.m4912c(), Message.Type.groupchat);
            message.setStanzaId(StringUtil.m7062a());
            message.addExtension(contentXE);
            message.addExtension(selectiveHistoryXE);
            try {
                this.f5034b.sendStanza(message);
                GroupManager.m7323a().m7357a(new GMRecord(System.currentTimeMillis() + 3000, 3000, message, groupSelectiveFetchHistoryEvent.m4911b()));
                if (GroupManager.m7323a().f4903a.get(groupSelectiveFetchHistoryEvent.m4912c()) == null) {
                    GroupManager.m7323a().f4903a.put(groupSelectiveFetchHistoryEvent.m4912c(), new GroupV2FetchHistoryModel());
                }
                ((GroupV2FetchHistoryModel) GroupManager.m7323a().f4903a.get(groupSelectiveFetchHistoryEvent.m4912c())).m4038c(message);
                if (groupSelectiveFetchHistoryEvent.m4906a() != null) {
                    groupSelectiveFetchHistoryEvent.m4906a().m6209a();
                }
            } catch (NotConnectedException e) {
                e.printStackTrace();
                if (groupSelectiveFetchHistoryEvent.m4906a() != null) {
                    groupSelectiveFetchHistoryEvent.m4906a().m6210a(null);
                }
            }
        }
    }

    public void onEvent(LeaveGroupEvent leaveGroupEvent) {
        ExtensionElement contentXE = new ContentXE(ContentXE.Type.command);
        ExtensionElement leaveXE = new LeaveXE();
        Stanza message = new Message(leaveGroupEvent.m4936a(), Message.Type.groupchat);
        message.setStanzaId(StringUtil.m7062a());
        message.addExtension(contentXE);
        message.addExtension(leaveXE);
        try {
            this.f5034b.sendStanza(message);
            GroupManager.m7323a().m7357a(new GMRecord(System.currentTimeMillis() + 3000, 3000, message, 2));
            if (leaveGroupEvent.m4937b() != null) {
                leaveGroupEvent.m4937b().m4934a();
            }
        } catch (NotConnectedException e) {
            e.printStackTrace();
            if (leaveGroupEvent.m4937b() != null) {
                leaveGroupEvent.m4937b().m4935a(null);
            }
        }
    }

    public void onEvent(CreateGroupV2Event createGroupV2Event) {
        ExtensionElement contentXE = new ContentXE(ContentXE.Type.command);
        ExtensionElement createXE = new CreateXE(createGroupV2Event.m4872b());
        Stanza message = new Message(createGroupV2Event.m4872b().m7606a() + "@" + "egc.bisphone.com", Message.Type.groupchat);
        message.setStanzaId(StringUtil.m7062a());
        message.addExtension(contentXE);
        message.addExtension(createXE);
        try {
            this.f5034b.sendStanza(message);
            GroupManager.m7323a().m7357a(new GMRecord(System.currentTimeMillis() + 3000, 3000, message, 2));
            if (createGroupV2Event.m4869a() != null) {
                createGroupV2Event.m4869a().m6209a();
            }
        } catch (NotConnectedException e) {
            e.printStackTrace();
            if (createGroupV2Event.m4869a() != null) {
                createGroupV2Event.m4869a().m6210a(null);
            }
        }
    }

    public void onEvent(AcceptGroupInvitationEvent acceptGroupInvitationEvent) {
        ExtensionElement contentXE = new ContentXE(ContentXE.Type.command);
        ExtensionElement joinXE = new JoinXE();
        Stanza message = new Message(acceptGroupInvitationEvent.m4859b(), Message.Type.groupchat);
        message.setStanzaId(StringUtil.m7062a());
        message.addExtension(contentXE);
        message.addExtension(joinXE);
        try {
            this.f5034b.sendStanza(message);
            GroupManager.m7323a().m7357a(new GMRecord(System.currentTimeMillis() + 3000, 3000, message, 2));
            if (acceptGroupInvitationEvent.m4856a() != null) {
                acceptGroupInvitationEvent.m4856a().m6209a();
            }
        } catch (NotConnectedException e) {
            e.printStackTrace();
            if (acceptGroupInvitationEvent.m4856a() != null) {
                acceptGroupInvitationEvent.m4856a().m6210a(null);
            }
        }
    }

    public void onEvent(RejectGroupInvitationEvent rejectGroupInvitationEvent) {
        ExtensionElement contentXE = new ContentXE(ContentXE.Type.command);
        ExtensionElement rejectXE = new RejectXE();
        Stanza message = new Message(rejectGroupInvitationEvent.m4943b(), Message.Type.groupchat);
        message.setStanzaId(StringUtil.m7062a());
        message.addExtension(contentXE);
        message.addExtension(rejectXE);
        try {
            this.f5034b.sendStanza(message);
            GroupManager.m7323a().m7357a(new GMRecord(System.currentTimeMillis() + 3000, 3000, message, 2));
            if (rejectGroupInvitationEvent.m4940a() != null) {
                rejectGroupInvitationEvent.m4940a().m6209a();
            }
        } catch (NotConnectedException e) {
            e.printStackTrace();
            if (rejectGroupInvitationEvent.m4940a() != null) {
                rejectGroupInvitationEvent.m4940a().m6210a(null);
            }
        }
    }

    public void onEvent(ChangeGroupSettingEvent changeGroupSettingEvent) {
        ExtensionElement contentXE = new ContentXE(ContentXE.Type.command);
        Stanza message = new Message(changeGroupSettingEvent.m4865c(), Message.Type.groupchat);
        message.setStanzaId(StringUtil.m7062a());
        message.addExtension(contentXE);
        message.addExtension(changeGroupSettingEvent.m4864b());
        try {
            this.f5034b.sendStanza(message);
            GroupManager.m7323a().m7357a(new GMRecord(System.currentTimeMillis() + 3000, 3000, message, 2));
            if (changeGroupSettingEvent.m4860a() != null) {
                changeGroupSettingEvent.m4860a().m6209a();
            }
        } catch (NotConnectedException e) {
            e.printStackTrace();
            if (changeGroupSettingEvent.m4860a() != null) {
                changeGroupSettingEvent.m4860a().m6210a(null);
            }
        }
    }

    public void onEvent(GetGroupStatisticEvent getGroupStatisticEvent) {
        ExtensionElement contentXE = new ContentXE(ContentXE.Type.command);
        ExtensionElement groupStatisticXE = new GroupStatisticXE();
        Stanza message = new Message(getGroupStatisticEvent.m4890a(), Message.Type.groupchat);
        message.setStanzaId(StringUtil.m7062a());
        message.addExtension(contentXE);
        message.addExtension(groupStatisticXE);
        try {
            this.f5034b.sendStanza(message);
            GroupManager.m7323a().m7357a(new GMRecord(System.currentTimeMillis() + 3000, 3000, message, 2));
            if (getGroupStatisticEvent.m4891b() != null) {
                getGroupStatisticEvent.m4891b().m6209a();
            }
        } catch (NotConnectedException e) {
            e.printStackTrace();
            if (getGroupStatisticEvent.m4891b() != null) {
                getGroupStatisticEvent.m4891b().m6210a(null);
            }
        }
    }

    public void onEvent(GetGroupSettingEvent getGroupSettingEvent) {
        ExtensionElement contentXE = new ContentXE(ContentXE.Type.command);
        ExtensionElement getGroupSettingXE = new GetGroupSettingXE();
        Stanza message = new Message(getGroupSettingEvent.m4886a(), Message.Type.groupchat);
        message.setStanzaId(StringUtil.m7062a());
        message.addExtension(contentXE);
        message.addExtension(getGroupSettingXE);
        try {
            this.f5034b.sendStanza(message);
            GroupManager.m7323a().m7357a(new GMRecord(System.currentTimeMillis() + 3000, 3000, message, 2));
            if (getGroupSettingEvent.m4889b() != null) {
                getGroupSettingEvent.m4889b().m6209a();
            }
        } catch (NotConnectedException e) {
            e.printStackTrace();
            if (getGroupSettingEvent.m4889b() != null) {
                getGroupSettingEvent.m4889b().m6210a(null);
            }
        }
    }

    public void onEvent(SendBroadcastMessageEvent sendBroadcastMessageEvent) {
        new Thread(new AnonymousClass12(this, sendBroadcastMessageEvent)).start();
    }
}
