package org.jivesoftware.smackx.muc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.PresenceListener;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException.XMPPErrorException;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.FromMatchesFilter;
import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.filter.MessageWithSubjectFilter;
import org.jivesoftware.smack.filter.NotFilter;
import org.jivesoftware.smack.filter.StanzaExtensionFilter;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.filter.StanzaTypeFilter;
import org.jivesoftware.smack.filter.ToFilter;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Presence.Mode;
import org.jivesoftware.smack.packet.Presence.Type;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.disco.packet.DiscoverInfo.Identity;
import org.jivesoftware.smackx.iqregister.packet.Registration;
import org.jivesoftware.smackx.muc.packet.Destroy;
import org.jivesoftware.smackx.muc.packet.MUCAdmin;
import org.jivesoftware.smackx.muc.packet.MUCInitialPresence;
import org.jivesoftware.smackx.muc.packet.MUCItem;
import org.jivesoftware.smackx.muc.packet.MUCOwner;
import org.jivesoftware.smackx.muc.packet.MUCUser;
import org.jivesoftware.smackx.muc.packet.MUCUser.Invite;
import org.jivesoftware.smackx.muc.packet.MUCUser.Status;
import org.jivesoftware.smackx.privacy.packet.PrivacyItem;
import org.jivesoftware.smackx.xdata.Form;
import org.jivesoftware.smackx.xdata.FormField;
import org.jivesoftware.smackx.xdata.packet.DataForm;
import se.emilsjolander.stickylistheaders.C1128R;

public class MultiUserChat {
    private static final Logger LOGGER;
    private final XMPPConnection connection;
    private final StanzaListener declinesListener;
    private final StanzaFilter fromRoomFilter;
    private final StanzaFilter fromRoomGroupchatFilter;
    private final Set<InvitationRejectionListener> invitationRejectionListeners;
    private boolean joined;
    private PacketCollector messageCollector;
    private final StanzaListener messageListener;
    private final Set<MessageListener> messageListeners;
    private final MultiUserChatManager multiUserChatManager;
    private String nickname;
    private final Map<String, Presence> occupantsMap;
    private final Set<ParticipantStatusListener> participantStatusListeners;
    private final StanzaListener presenceInterceptor;
    private final Set<PresenceListener> presenceInterceptors;
    private final StanzaListener presenceListener;
    private final Set<PresenceListener> presenceListeners;
    private final String room;
    private String subject;
    private final StanzaListener subjectListener;
    private final Set<SubjectUpdatedListener> subjectUpdatedListeners;
    private final Set<UserStatusListener> userStatusListeners;

    /* renamed from: org.jivesoftware.smackx.muc.MultiUserChat.1 */
    class C10811 implements StanzaListener {
        C10811() {
        }

        public void processPacket(Stanza stanza) {
            Message message = (Message) stanza;
            for (MessageListener processMessage : MultiUserChat.this.messageListeners) {
                processMessage.processMessage(message);
            }
        }
    }

    /* renamed from: org.jivesoftware.smackx.muc.MultiUserChat.2 */
    class C10822 implements StanzaListener {
        C10822() {
        }

        public void processPacket(Stanza stanza) {
            Message message = (Message) stanza;
            MultiUserChat.this.subject = message.getSubject();
            for (SubjectUpdatedListener subjectUpdated : MultiUserChat.this.subjectUpdatedListeners) {
                subjectUpdated.subjectUpdated(MultiUserChat.this.subject, message.getFrom());
            }
        }
    }

    /* renamed from: org.jivesoftware.smackx.muc.MultiUserChat.3 */
    class C10833 implements StanzaListener {
        C10833() {
        }

        public void processPacket(Stanza stanza) {
            Presence presence = (Presence) stanza;
            String from = presence.getFrom();
            String str = MultiUserChat.this.room + "/" + MultiUserChat.this.nickname;
            boolean equals = presence.getFrom().equals(str);
            switch (C10877.$SwitchMap$org$jivesoftware$smack$packet$Presence$Type[presence.getType().ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    Presence presence2 = (Presence) MultiUserChat.this.occupantsMap.put(from, presence);
                    if (presence2 == null) {
                        if (!equals) {
                            for (ParticipantStatusListener joined : MultiUserChat.this.participantStatusListeners) {
                                joined.joined(from);
                            }
                            break;
                        }
                    }
                    MUCUser from2 = MUCUser.from(presence2);
                    MUCAffiliation affiliation = from2.getItem().getAffiliation();
                    MUCRole role = from2.getItem().getRole();
                    MUCUser from3 = MUCUser.from(stanza);
                    MUCAffiliation affiliation2 = from3.getItem().getAffiliation();
                    MultiUserChat.this.checkRoleModifications(role, from3.getItem().getRole(), equals, from);
                    MultiUserChat.this.checkAffiliationModifications(affiliation, affiliation2, equals, from);
                    break;
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    MultiUserChat.this.occupantsMap.remove(from);
                    MUCUser from4 = MUCUser.from(stanza);
                    if (from4 == null || !from4.hasStatus()) {
                        if (!equals) {
                            for (ParticipantStatusListener joined2 : MultiUserChat.this.participantStatusListeners) {
                                joined2.left(from);
                            }
                            break;
                        }
                    }
                    MultiUserChat.this.checkPresenceCode(from4.getStatus(), presence.getFrom().equals(str), from4, from);
                    break;
                    break;
            }
            for (PresenceListener processPresence : MultiUserChat.this.presenceListeners) {
                processPresence.processPresence(presence);
            }
        }
    }

    /* renamed from: org.jivesoftware.smackx.muc.MultiUserChat.4 */
    class C10844 implements StanzaListener {
        C10844() {
        }

        public void processPacket(Stanza stanza) {
            MUCUser from = MUCUser.from(stanza);
            if (from.getDecline() != null) {
                MultiUserChat.this.fireInvitationRejectionListeners(from.getDecline().getFrom(), from.getDecline().getReason());
            }
        }
    }

    /* renamed from: org.jivesoftware.smackx.muc.MultiUserChat.5 */
    class C10855 implements StanzaListener {
        C10855() {
        }

        public void processPacket(Stanza stanza) {
            Presence presence = (Presence) stanza;
            for (PresenceListener processPresence : MultiUserChat.this.presenceInterceptors) {
                processPresence.processPresence(presence);
            }
        }
    }

    /* renamed from: org.jivesoftware.smackx.muc.MultiUserChat.6 */
    class C10866 implements StanzaFilter {
        final /* synthetic */ String val$subject;

        C10866(String str) {
            this.val$subject = str;
        }

        public boolean accept(Stanza stanza) {
            return this.val$subject.equals(((Message) stanza).getSubject());
        }
    }

    /* renamed from: org.jivesoftware.smackx.muc.MultiUserChat.7 */
    /* synthetic */ class C10877 {
        static final /* synthetic */ int[] $SwitchMap$org$jivesoftware$smack$packet$Presence$Type;

        static {
            $SwitchMap$org$jivesoftware$smack$packet$Presence$Type = new int[Type.values().length];
            try {
                $SwitchMap$org$jivesoftware$smack$packet$Presence$Type[Type.available.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$org$jivesoftware$smack$packet$Presence$Type[Type.unavailable.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    static {
        LOGGER = Logger.getLogger(MultiUserChat.class.getName());
    }

    MultiUserChat(XMPPConnection xMPPConnection, String str, MultiUserChatManager multiUserChatManager) {
        this.occupantsMap = new ConcurrentHashMap();
        this.invitationRejectionListeners = new CopyOnWriteArraySet();
        this.subjectUpdatedListeners = new CopyOnWriteArraySet();
        this.userStatusListeners = new CopyOnWriteArraySet();
        this.participantStatusListeners = new CopyOnWriteArraySet();
        this.messageListeners = new CopyOnWriteArraySet();
        this.presenceListeners = new CopyOnWriteArraySet();
        this.presenceInterceptors = new CopyOnWriteArraySet();
        this.nickname = null;
        this.joined = false;
        this.connection = xMPPConnection;
        this.room = str.toLowerCase(Locale.US);
        this.multiUserChatManager = multiUserChatManager;
        this.fromRoomFilter = FromMatchesFilter.create(str);
        this.fromRoomGroupchatFilter = new AndFilter(this.fromRoomFilter, MessageTypeFilter.GROUPCHAT);
        this.messageListener = new C10811();
        this.subjectListener = new C10822();
        this.presenceListener = new C10833();
        this.declinesListener = new C10844();
        this.presenceInterceptor = new C10855();
    }

    public String getRoom() {
        return this.room;
    }

    private Presence enter(String str, String str2, DiscussionHistory discussionHistory, long j) {
        XMPPErrorException e;
        StringUtils.requireNotNullOrEmpty(str, "Nickname must not be null or blank.");
        Stanza presence = new Presence(Type.available);
        presence.setTo(this.room + "/" + str);
        ExtensionElement mUCInitialPresence = new MUCInitialPresence();
        if (str2 != null) {
            mUCInitialPresence.setPassword(str2);
        }
        if (discussionHistory != null) {
            mUCInitialPresence.setHistory(discussionHistory.getMUCHistory());
        }
        presence.addExtension(mUCInitialPresence);
        StanzaFilter andFilter = new AndFilter(FromMatchesFilter.createFull(this.room + "/" + str), new StanzaTypeFilter(Presence.class));
        this.connection.addSyncStanzaListener(this.messageListener, this.fromRoomGroupchatFilter);
        this.connection.addSyncStanzaListener(this.presenceListener, new AndFilter(this.fromRoomFilter, StanzaTypeFilter.PRESENCE));
        this.connection.addSyncStanzaListener(this.subjectListener, new AndFilter(this.fromRoomFilter, MessageWithSubjectFilter.INSTANCE));
        this.connection.addSyncStanzaListener(this.declinesListener, new AndFilter(new StanzaExtensionFilter(DataForm.ELEMENT, MUCUser.NAMESPACE), new NotFilter(MessageTypeFilter.ERROR)));
        this.connection.addPacketInterceptor(this.presenceInterceptor, new AndFilter(new ToFilter(this.room), StanzaTypeFilter.PRESENCE));
        this.messageCollector = this.connection.createPacketCollector(this.fromRoomGroupchatFilter);
        try {
            Presence presence2 = (Presence) this.connection.createPacketCollectorAndSend(andFilter, presence).nextResultOrThrow(j);
            this.nickname = str;
            this.joined = true;
            this.multiUserChatManager.addJoinedRoom(this.room);
            return presence2;
        } catch (NoResponseException e2) {
            e = e2;
            removeConnectionCallbacks();
            throw e;
        } catch (XMPPErrorException e3) {
            e = e3;
            removeConnectionCallbacks();
            throw e;
        }
    }

    public synchronized void create(String str) {
        if (this.joined) {
            throw new IllegalStateException("Creation failed - User already joined the room.");
        } else if (!createOrJoin(str)) {
            leave();
            throw new SmackException("Creation failed - Missing acknowledge of room creation.");
        }
    }

    public synchronized boolean createOrJoin(String str) {
        return createOrJoin(str, null, null, this.connection.getPacketReplyTimeout());
    }

    public synchronized boolean createOrJoin(String str, String str2, DiscussionHistory discussionHistory, long j) {
        boolean z;
        if (this.joined) {
            throw new IllegalStateException("Creation failed - User already joined the room.");
        }
        MUCUser from = MUCUser.from(enter(str, str2, discussionHistory, j));
        if (from == null || !from.getStatus().contains(Status.ROOM_CREATED_201)) {
            z = false;
        } else {
            z = true;
        }
        return z;
    }

    public void join(String str) {
        join(str, null, null, this.connection.getPacketReplyTimeout());
    }

    public void join(String str, String str2) {
        join(str, str2, null, this.connection.getPacketReplyTimeout());
    }

    public synchronized void join(String str, String str2, DiscussionHistory discussionHistory, long j) {
        if (this.joined) {
            leave();
        }
        enter(str, str2, discussionHistory, j);
    }

    public boolean isJoined() {
        return this.joined;
    }

    public synchronized void leave() {
        if (this.joined) {
            Stanza presence = new Presence(Type.unavailable);
            presence.setTo(this.room + "/" + this.nickname);
            this.connection.sendStanza(presence);
            this.occupantsMap.clear();
            this.nickname = null;
            this.joined = false;
            userHasLeft();
        }
    }

    public Form getConfigurationForm() {
        IQ mUCOwner = new MUCOwner();
        mUCOwner.setTo(this.room);
        mUCOwner.setType(IQ.Type.get);
        return Form.getFormFrom((IQ) this.connection.createPacketCollectorAndSend(mUCOwner).nextResultOrThrow());
    }

    public void sendConfigurationForm(Form form) {
        IQ mUCOwner = new MUCOwner();
        mUCOwner.setTo(this.room);
        mUCOwner.setType(IQ.Type.set);
        mUCOwner.addExtension(form.getDataFormToSend());
        this.connection.createPacketCollectorAndSend(mUCOwner).nextResultOrThrow();
    }

    public Form getRegistrationForm() {
        IQ registration = new Registration();
        registration.setType(IQ.Type.get);
        registration.setTo(this.room);
        return Form.getFormFrom((IQ) this.connection.createPacketCollectorAndSend(registration).nextResultOrThrow());
    }

    public void sendRegistrationForm(Form form) {
        IQ registration = new Registration();
        registration.setType(IQ.Type.set);
        registration.setTo(this.room);
        registration.addExtension(form.getDataFormToSend());
        this.connection.createPacketCollectorAndSend(registration).nextResultOrThrow();
    }

    public void destroy(String str, String str2) {
        IQ mUCOwner = new MUCOwner();
        mUCOwner.setTo(this.room);
        mUCOwner.setType(IQ.Type.set);
        Destroy destroy = new Destroy();
        destroy.setReason(str);
        destroy.setJid(str2);
        mUCOwner.setDestroy(destroy);
        this.connection.createPacketCollectorAndSend(mUCOwner).nextResultOrThrow();
        this.occupantsMap.clear();
        this.nickname = null;
        this.joined = false;
        userHasLeft();
    }

    public void invite(String str, String str2) {
        invite(new Message(), str, str2);
    }

    public void invite(Message message, String str, String str2) {
        message.setTo(this.room);
        ExtensionElement mUCUser = new MUCUser();
        Invite invite = new Invite();
        invite.setTo(str);
        invite.setReason(str2);
        mUCUser.setInvite(invite);
        message.addExtension(mUCUser);
        this.connection.sendStanza(message);
    }

    public boolean addInvitationRejectionListener(InvitationRejectionListener invitationRejectionListener) {
        return this.invitationRejectionListeners.add(invitationRejectionListener);
    }

    public boolean removeInvitationRejectionListener(InvitationRejectionListener invitationRejectionListener) {
        return this.invitationRejectionListeners.remove(invitationRejectionListener);
    }

    private void fireInvitationRejectionListeners(String str, String str2) {
        synchronized (this.invitationRejectionListeners) {
            InvitationRejectionListener[] invitationRejectionListenerArr = new InvitationRejectionListener[this.invitationRejectionListeners.size()];
            this.invitationRejectionListeners.toArray(invitationRejectionListenerArr);
        }
        for (InvitationRejectionListener invitationDeclined : invitationRejectionListenerArr) {
            invitationDeclined.invitationDeclined(str, str2);
        }
    }

    public boolean addSubjectUpdatedListener(SubjectUpdatedListener subjectUpdatedListener) {
        return this.subjectUpdatedListeners.add(subjectUpdatedListener);
    }

    public boolean removeSubjectUpdatedListener(SubjectUpdatedListener subjectUpdatedListener) {
        return this.subjectUpdatedListeners.remove(subjectUpdatedListener);
    }

    public void addPresenceInterceptor(PresenceListener presenceListener) {
        this.presenceInterceptors.add(presenceListener);
    }

    public void removePresenceInterceptor(StanzaListener stanzaListener) {
        this.presenceInterceptors.remove(stanzaListener);
    }

    public String getSubject() {
        return this.subject;
    }

    public String getReservedNickname() {
        try {
            Iterator it = ServiceDiscoveryManager.getInstanceFor(this.connection).discoverInfo(this.room, "x-roomuser-item").getIdentities().iterator();
            if (it.hasNext()) {
                return ((Identity) it.next()).getName();
            }
        } catch (Throwable e) {
            LOGGER.log(Level.SEVERE, "Error retrieving room nickname", e);
        }
        return null;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void changeNickname(String str) {
        StringUtils.requireNotNullOrEmpty(str, "Nickname must not be null or blank.");
        if (this.joined) {
            Stanza presence = new Presence(Type.available);
            presence.setTo(this.room + "/" + str);
            this.connection.createPacketCollectorAndSend(new AndFilter(FromMatchesFilter.createFull(this.room + "/" + str), new StanzaTypeFilter(Presence.class)), presence).nextResultOrThrow();
            this.nickname = str;
            return;
        }
        throw new IllegalStateException("Must be logged into the room to change nickname.");
    }

    public void changeAvailabilityStatus(String str, Mode mode) {
        StringUtils.requireNotNullOrEmpty(this.nickname, "Nickname must not be null or blank.");
        if (this.joined) {
            Stanza presence = new Presence(Type.available);
            presence.setStatus(str);
            presence.setMode(mode);
            presence.setTo(this.room + "/" + this.nickname);
            this.connection.sendStanza(presence);
            return;
        }
        throw new IllegalStateException("Must be logged into the room to change the availability status.");
    }

    public void kickParticipant(String str, String str2) {
        changeRole(str, MUCRole.none, str2);
    }

    public void requestVoice() {
        ExtensionElement dataForm = new DataForm(DataForm.Type.submit);
        FormField formField = new FormField(FormField.FORM_TYPE);
        formField.addValue("http://jabber.org/protocol/muc#request");
        dataForm.addField(formField);
        formField = new FormField("muc#role");
        formField.setType(FormField.Type.text_single);
        formField.setLabel("Requested role");
        formField.addValue("participant");
        dataForm.addField(formField);
        Stanza message = new Message(this.room);
        message.addExtension(dataForm);
        this.connection.sendStanza(message);
    }

    public void grantVoice(Collection<String> collection) {
        changeRole(collection, MUCRole.participant);
    }

    public void grantVoice(String str) {
        changeRole(str, MUCRole.participant, null);
    }

    public void revokeVoice(Collection<String> collection) {
        changeRole(collection, MUCRole.visitor);
    }

    public void revokeVoice(String str) {
        changeRole(str, MUCRole.visitor, null);
    }

    public void banUsers(Collection<String> collection) {
        changeAffiliationByAdmin((Collection) collection, MUCAffiliation.outcast);
    }

    public void banUser(String str, String str2) {
        changeAffiliationByAdmin(str, MUCAffiliation.outcast, str2);
    }

    public void grantMembership(Collection<String> collection) {
        changeAffiliationByAdmin((Collection) collection, MUCAffiliation.member);
    }

    public void grantMembership(String str) {
        changeAffiliationByAdmin(str, MUCAffiliation.member, null);
    }

    public void revokeMembership(Collection<String> collection) {
        changeAffiliationByAdmin((Collection) collection, MUCAffiliation.none);
    }

    public void revokeMembership(String str) {
        changeAffiliationByAdmin(str, MUCAffiliation.none, null);
    }

    public void grantModerator(Collection<String> collection) {
        changeRole(collection, MUCRole.moderator);
    }

    public void grantModerator(String str) {
        changeRole(str, MUCRole.moderator, null);
    }

    public void revokeModerator(Collection<String> collection) {
        changeRole(collection, MUCRole.participant);
    }

    public void revokeModerator(String str) {
        changeRole(str, MUCRole.participant, null);
    }

    public void grantOwnership(Collection<String> collection) {
        changeAffiliationByAdmin((Collection) collection, MUCAffiliation.owner);
    }

    public void grantOwnership(String str) {
        changeAffiliationByAdmin(str, MUCAffiliation.owner, null);
    }

    public void revokeOwnership(Collection<String> collection) {
        changeAffiliationByAdmin((Collection) collection, MUCAffiliation.admin);
    }

    public void revokeOwnership(String str) {
        changeAffiliationByAdmin(str, MUCAffiliation.admin, null);
    }

    public void grantAdmin(Collection<String> collection) {
        changeAffiliationByAdmin((Collection) collection, MUCAffiliation.admin);
    }

    public void grantAdmin(String str) {
        changeAffiliationByAdmin(str, MUCAffiliation.admin);
    }

    public void revokeAdmin(Collection<String> collection) {
        changeAffiliationByAdmin((Collection) collection, MUCAffiliation.admin);
    }

    public void revokeAdmin(String str) {
        changeAffiliationByAdmin(str, MUCAffiliation.member);
    }

    private void changeAffiliationByAdmin(String str, MUCAffiliation mUCAffiliation) {
        changeAffiliationByAdmin(str, mUCAffiliation, null);
    }

    private void changeAffiliationByAdmin(String str, MUCAffiliation mUCAffiliation, String str2) {
        IQ mUCAdmin = new MUCAdmin();
        mUCAdmin.setTo(this.room);
        mUCAdmin.setType(IQ.Type.set);
        mUCAdmin.addItem(new MUCItem(mUCAffiliation, str, str2));
        this.connection.createPacketCollectorAndSend(mUCAdmin).nextResultOrThrow();
    }

    private void changeAffiliationByAdmin(Collection<String> collection, MUCAffiliation mUCAffiliation) {
        IQ mUCAdmin = new MUCAdmin();
        mUCAdmin.setTo(this.room);
        mUCAdmin.setType(IQ.Type.set);
        for (String mUCItem : collection) {
            mUCAdmin.addItem(new MUCItem(mUCAffiliation, mUCItem));
        }
        this.connection.createPacketCollectorAndSend(mUCAdmin).nextResultOrThrow();
    }

    private void changeRole(String str, MUCRole mUCRole, String str2) {
        IQ mUCAdmin = new MUCAdmin();
        mUCAdmin.setTo(this.room);
        mUCAdmin.setType(IQ.Type.set);
        mUCAdmin.addItem(new MUCItem(mUCRole, str, str2));
        this.connection.createPacketCollectorAndSend(mUCAdmin).nextResultOrThrow();
    }

    private void changeRole(Collection<String> collection, MUCRole mUCRole) {
        IQ mUCAdmin = new MUCAdmin();
        mUCAdmin.setTo(this.room);
        mUCAdmin.setType(IQ.Type.set);
        for (String mUCItem : collection) {
            mUCAdmin.addItem(new MUCItem(mUCRole, mUCItem));
        }
        this.connection.createPacketCollectorAndSend(mUCAdmin).nextResultOrThrow();
    }

    public int getOccupantsCount() {
        return this.occupantsMap.size();
    }

    public List<String> getOccupants() {
        return Collections.unmodifiableList(new ArrayList(this.occupantsMap.keySet()));
    }

    public Presence getOccupantPresence(String str) {
        return (Presence) this.occupantsMap.get(str);
    }

    public Occupant getOccupant(String str) {
        Presence presence = (Presence) this.occupantsMap.get(str);
        if (presence != null) {
            return new Occupant(presence);
        }
        return null;
    }

    public boolean addParticipantListener(PresenceListener presenceListener) {
        return this.presenceListeners.add(presenceListener);
    }

    public boolean removeParticipantListener(PresenceListener presenceListener) {
        return this.presenceListeners.remove(presenceListener);
    }

    public List<Affiliate> getOwners() {
        return getAffiliatesByAdmin(MUCAffiliation.owner);
    }

    public List<Affiliate> getAdmins() {
        return getAffiliatesByAdmin(MUCAffiliation.admin);
    }

    public List<Affiliate> getMembers() {
        return getAffiliatesByAdmin(MUCAffiliation.member);
    }

    public List<Affiliate> getOutcasts() {
        return getAffiliatesByAdmin(MUCAffiliation.outcast);
    }

    private List<Affiliate> getAffiliatesByAdmin(MUCAffiliation mUCAffiliation) {
        IQ mUCAdmin = new MUCAdmin();
        mUCAdmin.setTo(this.room);
        mUCAdmin.setType(IQ.Type.get);
        mUCAdmin.addItem(new MUCItem(mUCAffiliation));
        MUCAdmin mUCAdmin2 = (MUCAdmin) this.connection.createPacketCollectorAndSend(mUCAdmin).nextResultOrThrow();
        List<Affiliate> arrayList = new ArrayList();
        for (MUCItem affiliate : mUCAdmin2.getItems()) {
            arrayList.add(new Affiliate(affiliate));
        }
        return arrayList;
    }

    public List<Occupant> getModerators() {
        return getOccupants(MUCRole.moderator);
    }

    public List<Occupant> getParticipants() {
        return getOccupants(MUCRole.participant);
    }

    private List<Occupant> getOccupants(MUCRole mUCRole) {
        IQ mUCAdmin = new MUCAdmin();
        mUCAdmin.setTo(this.room);
        mUCAdmin.setType(IQ.Type.get);
        mUCAdmin.addItem(new MUCItem(mUCRole));
        MUCAdmin mUCAdmin2 = (MUCAdmin) this.connection.createPacketCollectorAndSend(mUCAdmin).nextResultOrThrow();
        List<Occupant> arrayList = new ArrayList();
        for (MUCItem occupant : mUCAdmin2.getItems()) {
            arrayList.add(new Occupant(occupant));
        }
        return arrayList;
    }

    public void sendMessage(String str) {
        Stanza createMessage = createMessage();
        createMessage.setBody(str);
        this.connection.sendStanza(createMessage);
    }

    public Chat createPrivateChat(String str, ChatMessageListener chatMessageListener) {
        return ChatManager.getInstanceFor(this.connection).createChat(str, chatMessageListener);
    }

    public Message createMessage() {
        return new Message(this.room, Message.Type.groupchat);
    }

    public void sendMessage(Message message) {
        message.setTo(this.room);
        message.setType(Message.Type.groupchat);
        this.connection.sendStanza(message);
    }

    public Message pollMessage() {
        if (this.messageCollector != null) {
            return (Message) this.messageCollector.pollResult();
        }
        throw new MUCNotJoinedException(this);
    }

    public Message nextMessage() {
        if (this.messageCollector != null) {
            return (Message) this.messageCollector.nextResult();
        }
        throw new MUCNotJoinedException(this);
    }

    public Message nextMessage(long j) {
        if (this.messageCollector != null) {
            return (Message) this.messageCollector.nextResult(j);
        }
        throw new MUCNotJoinedException(this);
    }

    public boolean addMessageListener(MessageListener messageListener) {
        return this.messageListeners.add(messageListener);
    }

    public boolean removeMessageListener(MessageListener messageListener) {
        return this.messageListeners.remove(messageListener);
    }

    public void changeSubject(String str) {
        Stanza createMessage = createMessage();
        createMessage.setSubject(str);
        this.connection.createPacketCollectorAndSend(new AndFilter(this.fromRoomGroupchatFilter, new C10866(str)), createMessage).nextResultOrThrow();
    }

    private void removeConnectionCallbacks() {
        this.connection.removeSyncStanzaListener(this.messageListener);
        this.connection.removeSyncStanzaListener(this.presenceListener);
        this.connection.removeSyncStanzaListener(this.declinesListener);
        this.connection.removePacketInterceptor(this.presenceInterceptor);
        if (this.messageCollector != null) {
            this.messageCollector.cancel();
            this.messageCollector = null;
        }
    }

    private synchronized void userHasLeft() {
        this.multiUserChatManager.removeJoinedRoom(this.room);
        removeConnectionCallbacks();
    }

    public boolean addUserStatusListener(UserStatusListener userStatusListener) {
        return this.userStatusListeners.add(userStatusListener);
    }

    public boolean removeUserStatusListener(UserStatusListener userStatusListener) {
        return this.userStatusListeners.remove(userStatusListener);
    }

    public boolean addParticipantStatusListener(ParticipantStatusListener participantStatusListener) {
        return this.participantStatusListeners.add(participantStatusListener);
    }

    public boolean removeParticipantStatusListener(ParticipantStatusListener participantStatusListener) {
        return this.participantStatusListeners.remove(participantStatusListener);
    }

    private void checkRoleModifications(MUCRole mUCRole, MUCRole mUCRole2, boolean z, String str) {
        if (("visitor".equals(mUCRole) || PrivacyItem.SUBSCRIPTION_NONE.equals(mUCRole)) && "participant".equals(mUCRole2)) {
            if (z) {
                for (UserStatusListener voiceGranted : this.userStatusListeners) {
                    voiceGranted.voiceGranted();
                }
            } else {
                for (ParticipantStatusListener voiceGranted2 : this.participantStatusListeners) {
                    voiceGranted2.voiceGranted(str);
                }
            }
        } else if ("participant".equals(mUCRole) && ("visitor".equals(mUCRole2) || PrivacyItem.SUBSCRIPTION_NONE.equals(mUCRole2))) {
            if (z) {
                for (UserStatusListener voiceGranted3 : this.userStatusListeners) {
                    voiceGranted3.voiceRevoked();
                }
            } else {
                for (ParticipantStatusListener voiceGranted22 : this.participantStatusListeners) {
                    voiceGranted22.voiceRevoked(str);
                }
            }
        }
        if (!"moderator".equals(mUCRole) && "moderator".equals(mUCRole2)) {
            if ("visitor".equals(mUCRole) || PrivacyItem.SUBSCRIPTION_NONE.equals(mUCRole)) {
                if (z) {
                    for (UserStatusListener voiceGranted32 : this.userStatusListeners) {
                        voiceGranted32.voiceGranted();
                    }
                } else {
                    for (ParticipantStatusListener voiceGranted222 : this.participantStatusListeners) {
                        voiceGranted222.voiceGranted(str);
                    }
                }
            }
            if (z) {
                for (UserStatusListener voiceGranted322 : this.userStatusListeners) {
                    voiceGranted322.moderatorGranted();
                }
                return;
            }
            for (ParticipantStatusListener voiceGranted2222 : this.participantStatusListeners) {
                voiceGranted2222.moderatorGranted(str);
            }
        } else if ("moderator".equals(mUCRole) && !"moderator".equals(mUCRole2)) {
            if ("visitor".equals(mUCRole2) || PrivacyItem.SUBSCRIPTION_NONE.equals(mUCRole2)) {
                if (z) {
                    for (UserStatusListener voiceGranted3222 : this.userStatusListeners) {
                        voiceGranted3222.voiceRevoked();
                    }
                } else {
                    for (ParticipantStatusListener voiceGranted22222 : this.participantStatusListeners) {
                        voiceGranted22222.voiceRevoked(str);
                    }
                }
            }
            if (z) {
                for (UserStatusListener voiceGranted32222 : this.userStatusListeners) {
                    voiceGranted32222.moderatorRevoked();
                }
                return;
            }
            for (ParticipantStatusListener voiceGranted222222 : this.participantStatusListeners) {
                voiceGranted222222.moderatorRevoked(str);
            }
        }
    }

    private void checkAffiliationModifications(MUCAffiliation mUCAffiliation, MUCAffiliation mUCAffiliation2, boolean z, String str) {
        if (!"owner".equals(mUCAffiliation) || "owner".equals(mUCAffiliation2)) {
            if (!"admin".equals(mUCAffiliation) || "admin".equals(mUCAffiliation2)) {
                if ("member".equals(mUCAffiliation) && !"member".equals(mUCAffiliation2)) {
                    if (z) {
                        for (UserStatusListener membershipRevoked : this.userStatusListeners) {
                            membershipRevoked.membershipRevoked();
                        }
                    } else {
                        for (ParticipantStatusListener membershipRevoked2 : this.participantStatusListeners) {
                            membershipRevoked2.membershipRevoked(str);
                        }
                    }
                }
            } else if (z) {
                for (UserStatusListener membershipRevoked3 : this.userStatusListeners) {
                    membershipRevoked3.adminRevoked();
                }
            } else {
                for (ParticipantStatusListener membershipRevoked22 : this.participantStatusListeners) {
                    membershipRevoked22.adminRevoked(str);
                }
            }
        } else if (z) {
            for (UserStatusListener membershipRevoked32 : this.userStatusListeners) {
                membershipRevoked32.ownershipRevoked();
            }
        } else {
            for (ParticipantStatusListener membershipRevoked222 : this.participantStatusListeners) {
                membershipRevoked222.ownershipRevoked(str);
            }
        }
        if ("owner".equals(mUCAffiliation) || !"owner".equals(mUCAffiliation2)) {
            if ("admin".equals(mUCAffiliation) || !"admin".equals(mUCAffiliation2)) {
                if (!"member".equals(mUCAffiliation) && "member".equals(mUCAffiliation2)) {
                    if (z) {
                        for (UserStatusListener membershipRevoked322 : this.userStatusListeners) {
                            membershipRevoked322.membershipGranted();
                        }
                        return;
                    }
                    for (ParticipantStatusListener membershipRevoked2222 : this.participantStatusListeners) {
                        membershipRevoked2222.membershipGranted(str);
                    }
                }
            } else if (z) {
                for (UserStatusListener membershipRevoked3222 : this.userStatusListeners) {
                    membershipRevoked3222.adminGranted();
                }
            } else {
                for (ParticipantStatusListener membershipRevoked22222 : this.participantStatusListeners) {
                    membershipRevoked22222.adminGranted(str);
                }
            }
        } else if (z) {
            for (UserStatusListener membershipRevoked32222 : this.userStatusListeners) {
                membershipRevoked32222.ownershipGranted();
            }
        } else {
            for (ParticipantStatusListener membershipRevoked222222 : this.participantStatusListeners) {
                membershipRevoked222222.ownershipGranted(str);
            }
        }
    }

    private void checkPresenceCode(Set<Status> set, boolean z, MUCUser mUCUser, String str) {
        if (set.contains(Status.KICKED_307)) {
            if (z) {
                this.joined = false;
                for (UserStatusListener kicked : this.userStatusListeners) {
                    kicked.kicked(mUCUser.getItem().getActor(), mUCUser.getItem().getReason());
                }
                this.occupantsMap.clear();
                this.nickname = null;
                userHasLeft();
            } else {
                for (ParticipantStatusListener kicked2 : this.participantStatusListeners) {
                    kicked2.kicked(str, mUCUser.getItem().getActor(), mUCUser.getItem().getReason());
                }
            }
        }
        if (set.contains(Status.BANNED_301)) {
            if (z) {
                this.joined = false;
                for (UserStatusListener kicked3 : this.userStatusListeners) {
                    kicked3.banned(mUCUser.getItem().getActor(), mUCUser.getItem().getReason());
                }
                this.occupantsMap.clear();
                this.nickname = null;
                userHasLeft();
            } else {
                for (ParticipantStatusListener kicked22 : this.participantStatusListeners) {
                    kicked22.banned(str, mUCUser.getItem().getActor(), mUCUser.getItem().getReason());
                }
            }
        }
        if (set.contains(Status.REMOVED_AFFIL_CHANGE_321) && z) {
            this.joined = false;
            for (UserStatusListener kicked32 : this.userStatusListeners) {
                kicked32.membershipRevoked();
            }
            this.occupantsMap.clear();
            this.nickname = null;
            userHasLeft();
        }
        if (set.contains(Status.NEW_NICKNAME_303)) {
            for (ParticipantStatusListener kicked222 : this.participantStatusListeners) {
                kicked222.nicknameChanged(str, mUCUser.getItem().getNick());
            }
        }
    }

    public String toString() {
        return "MUC: " + this.room + "(" + this.connection.getUser() + ")";
    }
}
