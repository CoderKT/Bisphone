package app.xmpp;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;
import app.Main;
import app.account.AccountManager;
import app.common.Constants;
import app.common.entity.ChatHistoryEntity;
import app.common.entity.ConversationNormalEntity;
import app.common.entity.HistoryChannelEntity;
import app.common.entity.HistoryEntity;
import app.common.entity.HistoryEntity.CallStatus;
import app.common.entity.HistoryEntity.DeliveryStatus;
import app.common.entity.HistoryEntity.RelationType;
import app.common.entity.HistoryEntity.Type;
import app.common.entity.HistoryGroupEntity;
import app.common.entity.HistoryNormalMessageEntity;
import app.common.entity.HistoryNormalMessageEntity.Builder;
import app.database.datasource.BroadcastListDataSource;
import app.database.datasource.BroadcastUsersDataSource;
import app.database.datasource.ChannelDataSource;
import app.database.datasource.ContactDataSource;
import app.database.datasource.ConversationGroupDataSource;
import app.database.datasource.ConversationNormalDataSource;
import app.database.datasource.HistoryChannelDataSource;
import app.database.datasource.HistoryGroupDataSource;
import app.database.datasource.HistoryNormalMessageDataSource;
import app.database.provider.ConversationNormalProvider;
import app.database.provider.HistoryChannelProvider;
import app.database.provider.HistoryGroupProvider;
import app.database.provider.HistoryOneToOneProvider;
import app.events.NewMessageEvent;
import app.events.net.MediaUploadProgressEvent;
import app.events.xmpp.AbstractSendPacketEvent.PacketStatusListener;
import app.events.xmpp.ChatStateEvent;
import app.events.xmpp.MessageSendEvent;
import app.events.xmpp.MessageSendStatusEvent;
import app.events.xmpp.SendBroadcastMessageEvent;
import app.events.xmpp.SendMessageEvent;
import app.http.HttpService;
import app.http.RequestType;
import app.http.TaskBuilder;
import app.http.TaskPriority;
import app.http.listener.TaskListener;
import app.logger.HandledException;
import app.notification.BadgeHandler;
import app.notification.NotificationCenter;
import app.storage.Storage;
import app.storage.StorageException;
import app.util.BitmapUtil;
import app.util.CallUtil;
import app.util.DeviceUtils;
import app.util.FileUtil;
import app.util.StringUtil;
import app.util.Utils;
import app.util.XMPPUtils;
import app.voip.VoipManager;
import app.xmpp.packet.CallSignalPX;
import app.xmpp.packet.TimePX;
import app.xmpp.packet.channel.CastPX;
import app.xmpp.packet.media.AudioPX;
import app.xmpp.packet.media.LocationPX;
import app.xmpp.packet.media.PhotoPX;
import app.xmpp.packet.media.StickerPX;
import app.xmpp.packet.media.VideoPX;
import app.xmpp.packet.sublist.SublistPush;
import com.crashlytics.android.Crashlytics;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import cz.msebera.android.httpclient.Header;
import de.greenrobot.event.EventBus;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.chatstates.packet.ChatStateExtension;
import org.jivesoftware.smackx.delay.DelayInformationManager;
import org.jivesoftware.smackx.delay.packet.DelayInformation;
import org.jivesoftware.smackx.receipts.DeliveryReceipt;
import org.jivesoftware.smackx.time.packet.Time;
import org.jivesoftware.smackx.xhtmlim.XHTMLText;
import org.json.JSONException;
import org.json.JSONObject;
import se.emilsjolander.stickylistheaders.C1128R;

public class NormalMessageHandler extends MessageHandler {
    private static NormalMessageHandler f4966d;
    String f4967a;
    private Context f4968e;
    private final Object f4969f;
    private volatile boolean f4970g;

    /* renamed from: app.xmpp.NormalMessageHandler.1 */
    class C05511 implements PacketStatusListener {
        final /* synthetic */ int f4942a;
        final /* synthetic */ NormalMessageHandler f4943b;

        C05511(NormalMessageHandler normalMessageHandler, int i) {
            this.f4943b = normalMessageHandler;
            this.f4942a = i;
        }

        public void m7397a(String str) {
            if (this.f4942a != 2) {
                this.f4943b.m7416a(this.f4942a, str, DeliveryStatus.SENT, null);
            }
        }

        public void m7398b(String str) {
            this.f4943b.m7416a(this.f4942a, str, DeliveryStatus.FAILED_TO_SEND, null);
        }
    }

    /* renamed from: app.xmpp.NormalMessageHandler.2 */
    class C05522 implements PacketStatusListener {
        final /* synthetic */ int f4944a;
        final /* synthetic */ NormalMessageHandler f4945b;

        C05522(NormalMessageHandler normalMessageHandler, int i) {
            this.f4945b = normalMessageHandler;
            this.f4944a = i;
        }

        public void m7399a(String str) {
            if (this.f4944a != 2) {
                this.f4945b.m7416a(this.f4944a, str, DeliveryStatus.SENT, null);
            }
        }

        public void m7400b(String str) {
            this.f4945b.m7416a(this.f4944a, str, DeliveryStatus.FAILED_TO_SEND, null);
        }
    }

    /* renamed from: app.xmpp.NormalMessageHandler.3 */
    class C05533 implements PacketStatusListener {
        final /* synthetic */ int f4946a;
        final /* synthetic */ NormalMessageHandler f4947b;

        C05533(NormalMessageHandler normalMessageHandler, int i) {
            this.f4947b = normalMessageHandler;
            this.f4946a = i;
        }

        public void m7401a(String str) {
            if (this.f4946a != 2) {
                this.f4947b.m7416a(this.f4946a, str, DeliveryStatus.SENT, null);
            }
        }

        public void m7402b(String str) {
            this.f4947b.m7416a(this.f4946a, str, DeliveryStatus.FAILED_TO_SEND, null);
        }
    }

    /* renamed from: app.xmpp.NormalMessageHandler.4 */
    class C05544 extends TaskListener {
        MediaUploadProgressEvent f4948a;
        final /* synthetic */ String f4949b;
        final /* synthetic */ int f4950c;
        final /* synthetic */ HistoryEntity f4951d;
        final /* synthetic */ ArrayList f4952e;
        final /* synthetic */ NormalMessageHandler f4953f;

        C05544(NormalMessageHandler normalMessageHandler, String str, int i, HistoryEntity historyEntity, ArrayList arrayList) {
            this.f4953f = normalMessageHandler;
            this.f4949b = str;
            this.f4950c = i;
            this.f4951d = historyEntity;
            this.f4952e = arrayList;
            this.f4948a = new MediaUploadProgressEvent(this.f4949b);
        }

        public void m7405a(long j, long j2) {
            this.f4948a.m4952a(((float) j) / ((float) j2));
            EventBus.m12779a().m12795d(this.f4948a);
        }

        public void m7403a(int i, Header[] headerArr, byte[] bArr) {
            try {
                if (new JSONObject(new String(bArr)).getString(Main.f1927b.getString(2131296537)).equals(this.f4949b)) {
                    this.f4953f.m7416a(this.f4950c, this.f4951d.m4412b(), DeliveryStatus.SENDING, this.f4952e);
                    if (this.f4950c == 4) {
                        this.f4953f.m7419a((HistoryNormalMessageEntity) this.f4951d, this.f4952e);
                        return;
                    } else {
                        this.f4953f.m7432a(this.f4951d, this.f4950c);
                        return;
                    }
                }
                String str = "response token does not match";
                Crashlytics.m7882a(new HandledException(str));
                Main.f1926a.m5679b(str);
            } catch (Throwable e) {
                this.f4953f.m7416a(this.f4950c, this.f4951d.m4412b(), DeliveryStatus.FAILED_TO_UPLOAD, this.f4952e);
                Main.f1926a.m5680b(e);
            }
        }

        public void m7404a(int i, Header[] headerArr, byte[] bArr, Throwable th) {
            this.f4953f.m7416a(this.f4950c, this.f4951d.m4412b(), DeliveryStatus.FAILED_TO_UPLOAD, this.f4952e);
        }

        public void m7406g() {
            this.f4953f.m7416a(this.f4950c, this.f4951d.m4412b(), DeliveryStatus.FAILED_TO_UPLOAD, this.f4952e);
        }
    }

    /* renamed from: app.xmpp.NormalMessageHandler.5 */
    class C05555 implements PacketStatusListener {
        final /* synthetic */ int f4954a;
        final /* synthetic */ NormalMessageHandler f4955b;

        C05555(NormalMessageHandler normalMessageHandler, int i) {
            this.f4955b = normalMessageHandler;
            this.f4954a = i;
        }

        public void m7407a(String str) {
            if (this.f4954a != 2) {
                this.f4955b.m7416a(this.f4954a, str, DeliveryStatus.SENT, null);
            }
        }

        public void m7408b(String str) {
            if (this.f4954a != 2) {
                this.f4955b.m7416a(this.f4954a, str, DeliveryStatus.FAILED_TO_SEND, null);
            }
        }
    }

    /* renamed from: app.xmpp.NormalMessageHandler.6 */
    class C05566 implements PacketStatusListener {
        final /* synthetic */ NormalMessageHandler f4956a;

        C05566(NormalMessageHandler normalMessageHandler) {
            this.f4956a = normalMessageHandler;
        }

        public void m7409a(String str) {
            this.f4956a.m7416a(4, str, DeliveryStatus.SENT, null);
        }

        public void m7410b(String str) {
            this.f4956a.m7416a(4, str, DeliveryStatus.FAILED_TO_SEND, null);
        }
    }

    /* renamed from: app.xmpp.NormalMessageHandler.7 */
    class C05587 implements Runnable {
        final /* synthetic */ HashMap f4959a;
        final /* synthetic */ NormalMessageHandler f4960b;

        /* renamed from: app.xmpp.NormalMessageHandler.7.1 */
        class C05571 extends ArrayList<String> {
            final /* synthetic */ HistoryNormalMessageEntity f4957a;
            final /* synthetic */ C05587 f4958b;

            C05571(C05587 c05587, HistoryNormalMessageEntity historyNormalMessageEntity) {
                this.f4958b = c05587;
                this.f4957a = historyNormalMessageEntity;
                add(this.f4957a.m4456M());
            }
        }

        C05587(NormalMessageHandler normalMessageHandler, HashMap hashMap) {
            this.f4960b = normalMessageHandler;
            this.f4959a = hashMap;
        }

        public void run() {
            List<HistoryEntity> a = HistoryNormalMessageDataSource.m4724a(this.f4960b.f4968e);
            List<HistoryEntity> a2 = HistoryChannelDataSource.m4670a(this.f4960b.f4968e);
            if (a != null && a.size() > 0) {
                for (HistoryEntity historyEntity : a) {
                    if (!NormalMessageManager.m7447a().m7465c(historyEntity.m4456M())) {
                        switch (C05609.f4965c[historyEntity.m4418e().ordinal()]) {
                            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                                this.f4960b.m7434a(historyEntity, new C05571(this, historyEntity), historyEntity.m4430q(), true, 1);
                                break;
                            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                                this.f4960b.m7435a(historyEntity, true, 1);
                                break;
                            case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                                this.f4960b.m7444b(historyEntity, true, 1);
                                break;
                            case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                                this.f4960b.m7446c(historyEntity, true, 1);
                                break;
                            default:
                                break;
                        }
                    }
                    ArrayList arrayList;
                    String M = historyEntity.m4456M();
                    if (this.f4959a.containsKey(M)) {
                        arrayList = new ArrayList((Collection) this.f4959a.get(M));
                    } else {
                        arrayList = BroadcastUsersDataSource.m4519a().m4521a(M);
                        this.f4959a.put(M, arrayList);
                    }
                    switch (C05609.f4965c[historyEntity.m4418e().ordinal()]) {
                        case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                        case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                        case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                            this.f4960b.m7434a(historyEntity, NormalMessageManager.m7447a().m7474h(), historyEntity.m4430q(), true, 4);
                            break;
                        default:
                            this.f4960b.m7419a((HistoryNormalMessageEntity) historyEntity, arrayList);
                            break;
                    }
                }
            }
            if (a2 != null && a2.size() > 0) {
                for (HistoryEntity historyEntity2 : a2) {
                    switch (C05609.f4965c[historyEntity2.m4418e().ordinal()]) {
                        case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                        case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                        case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                            this.f4960b.m7432a(historyEntity2, 3);
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                            this.f4960b.m7435a(historyEntity2, true, 3);
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                            this.f4960b.m7444b(historyEntity2, true, 3);
                            break;
                        case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                            this.f4960b.m7446c(historyEntity2, true, 3);
                            break;
                        default:
                            break;
                    }
                }
            }
            this.f4960b.f4970g = false;
        }
    }

    /* renamed from: app.xmpp.NormalMessageHandler.8 */
    class C05598 extends TextHttpResponseHandler {
        final /* synthetic */ boolean[] f4961a;
        final /* synthetic */ NormalMessageHandler f4962b;

        C05598(NormalMessageHandler normalMessageHandler, boolean[] zArr) {
            this.f4962b = normalMessageHandler;
            this.f4961a = zArr;
        }

        public void m7411a(int i, Header[] headerArr, String str) {
            try {
                this.f4961a[0] = new JSONObject(str).getBoolean("exists");
            } catch (JSONException e) {
            }
        }

        public void m7412a(int i, Header[] headerArr, String str, Throwable th) {
            Main.f1926a.m5683d(i + " " + th.getMessage() + " " + th.getCause());
        }
    }

    /* renamed from: app.xmpp.NormalMessageHandler.9 */
    /* synthetic */ class C05609 {
        static final /* synthetic */ int[] f4963a;
        static final /* synthetic */ int[] f4964b;
        static final /* synthetic */ int[] f4965c;

        static {
            f4965c = new int[Type.values().length];
            try {
                f4965c[Type.PHOTO.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f4965c[Type.VIDEO.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f4965c[Type.AUDIO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f4965c[Type.TEXT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f4965c[Type.MAP.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f4965c[Type.STICKER.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f4965c[Type.CALL.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f4965c[Type.GROUP_STATUS.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            f4964b = new int[Message.Type.values().length];
            try {
                f4964b[Message.Type.error.ordinal()] = 1;
            } catch (NoSuchFieldError e9) {
            }
            try {
                f4964b[Message.Type.headline.ordinal()] = 2;
            } catch (NoSuchFieldError e10) {
            }
            try {
                f4964b[Message.Type.normal.ordinal()] = 3;
            } catch (NoSuchFieldError e11) {
            }
            try {
                f4964b[Message.Type.chat.ordinal()] = 4;
            } catch (NoSuchFieldError e12) {
            }
            f4963a = new int[CallSignalPX.Type.values().length];
            try {
                f4963a[CallSignalPX.Type.call.ordinal()] = 1;
            } catch (NoSuchFieldError e13) {
            }
            try {
                f4963a[CallSignalPX.Type.answer.ordinal()] = 2;
            } catch (NoSuchFieldError e14) {
            }
            try {
                f4963a[CallSignalPX.Type.busy.ordinal()] = 3;
            } catch (NoSuchFieldError e15) {
            }
            try {
                f4963a[CallSignalPX.Type.unavailable.ordinal()] = 4;
            } catch (NoSuchFieldError e16) {
            }
        }
    }

    static {
        f4966d = null;
    }

    private NormalMessageHandler(Context context) {
        super(context);
        this.f4969f = new Object();
        this.f4970g = false;
        this.f4968e = context;
        this.f4967a = AccountManager.m3934a().m3937c();
    }

    public static synchronized NormalMessageHandler m7415a() {
        NormalMessageHandler normalMessageHandler;
        synchronized (NormalMessageHandler.class) {
            if (f4966d == null) {
                f4966d = new NormalMessageHandler(Main.f1927b);
            }
            normalMessageHandler = f4966d;
        }
        return normalMessageHandler;
    }

    public void m7441a(Stanza stanza) {
        String str = (System.currentTimeMillis() * 1000) + "";
        Message message = (Message) stanza;
        Object obj = null;
        DelayInformation delayInformation = DelayInformationManager.getDelayInformation(message);
        if (delayInformation != null) {
            obj = 1;
            str = (delayInformation.getStamp().getTime() * 1000) + "";
            Main.f1926a.m5681c("Offline message with timestamp: " + str);
        }
        Object obj2 = obj;
        String str2 = str;
        String from = message.getFrom();
        Main.f1926a.m5681c("Message from: " + from);
        JabberId a = JabberId.m7386a(from);
        if (a == null) {
            Main.f1926a.m5679b("Invalid Jabber ID, RETURN!");
            return;
        }
        boolean c = XMPPUtils.m7095c(a);
        boolean d = XMPPUtils.m7096d(a);
        if (message.getType() != Message.Type.headline) {
            ExtensionElement extension = message.getExtension(DeliveryReceipt.ELEMENT, DeliveryReceipt.NAMESPACE);
            if (extension != null) {
                String id = ((DeliveryReceipt) extension).getId();
                Main.f1926a.m5681c("Received Delivery Receipt with ID: " + id);
                if (c) {
                    m7416a(3, id, DeliveryStatus.DELIVERED, null);
                    return;
                } else if (d) {
                    m7416a(1, id, DeliveryStatus.DELIVERED, null);
                    return;
                } else {
                    return;
                }
            }
            extension = message.getExtension(Time.ELEMENT, "http://bisphone.com/protocol/time");
            if (extension != null) {
                str2 = (((TimePX) extension).m7528a() * 1000) + "";
                Main.f1926a.m5681c("TimePX with timestamp: " + str2);
            }
            String str3 = str2;
            if (c || d) {
                String a2;
                String d2 = a.m7390d();
                from = message.getPacketID();
                if (from == null) {
                    str2 = (Long.parseLong(str3) / 60000) + "";
                } else {
                    str2 = from;
                }
                switch (C05609.f4964b[message.getType().ordinal()]) {
                    case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                        Main.f1926a.m5685e("XMPP Message Error : " + message.getError());
                        return;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                        Main.f1926a.m5685e("XMPP Message Headline " + message.toString());
                        return;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                        Main.f1926a.m5685e("XMPP Message Normal " + message.toString());
                        ExtensionElement extension2 = message.getExtension("push", "http://bisphone.com/protocol/sublist");
                        if (extension2 != null) {
                            SublistPush sublistPush = (SublistPush) extension2;
                            from = sublistPush.m7720a().m7711a();
                            Main.f1926a.m5683d("Received Sublist Push: " + sublistPush.m7721b());
                            if (XHTMLText.f8584A.equals(sublistPush.m7720a().m7713b())) {
                                ContactDataSource.m4560h(from);
                                NotificationCenter.m6606a().m6614b(from);
                                return;
                            }
                            return;
                        }
                        return;
                    case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                        a2 = a.m7387a();
                        extension = message.getExtension("call", "http://bisphone.com/protocol/media");
                        if (extension == null) {
                            str = a2;
                            break;
                        }
                        CallSignalPX callSignalPX = (CallSignalPX) extension;
                        Main.f1926a.m5681c("CallSignal from " + a.m7390d() + " of type " + callSignalPX.m7525a());
                        switch (C05609.f4963a[callSignalPX.m7525a().ordinal()]) {
                            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                                if (obj2 != null) {
                                    CallUtil.m7004a(this.f4968e, CallStatus.MISSED, a, 0, Long.parseLong(str3), VoipManager.m7265b().m7299a());
                                    return;
                                } else {
                                    VoipManager.m7265b().m7295a(this.f4968e, a, callSignalPX.m7526b());
                                    return;
                                }
                            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                                VoipManager.m7265b().m7297a(a, callSignalPX);
                                return;
                            default:
                                return;
                        }
                    default:
                        str = null;
                        break;
                }
                a2 = message.getBody();
                if (a2 != null && !"".equals(a2)) {
                    Type type;
                    HistoryEntity b;
                    Builder c2;
                    Uri insert;
                    Builder builder;
                    HistoryChannelEntity.Builder builder2;
                    CastPX castPX;
                    HistoryChannelEntity.Builder e;
                    HistoryChannelEntity.Builder builder3;
                    long parseId;
                    HistoryNormalMessageEntity a3;
                    Type type2 = Type.TEXT;
                    HistoryEntity.Builder builder4 = new HistoryEntity.Builder();
                    m7376a(message, builder4);
                    extension = message.getExtension("media", "http://bisphone.com/protocol/media");
                    if (extension != null) {
                        if (extension instanceof StickerPX) {
                            type2 = Type.STICKER;
                            StickerPX stickerPX = (StickerPX) extension;
                            builder4.m4375e(stickerPX.m7698a());
                            builder4.m4377f(stickerPX.m7699b());
                            type = type2;
                        } else if (extension instanceof PhotoPX) {
                            type2 = Type.PHOTO;
                            PhotoPX photoPX = (PhotoPX) extension;
                            builder4.m4380h(photoPX.m7695c());
                            builder4.m4361b(photoPX.m7694b());
                            builder4.m4350a(photoPX.m7693a());
                            builder4.m4379g(photoPX.m7697e());
                            String d3 = FileUtil.m7028d(photoPX.m7696d());
                            if (d3 == null) {
                                builder4.m4376e(photoPX.m7696d());
                            } else {
                                builder4.m4376e(d3);
                            }
                            type = type2;
                        } else if (extension instanceof AudioPX) {
                            type2 = Type.AUDIO;
                            AudioPX audioPX = (AudioPX) extension;
                            builder4.m4380h(audioPX.m7681c());
                            builder4.m4368c(audioPX.m7680b());
                            builder4.m4371d(audioPX.m7679a());
                            type = type2;
                        } else if (extension instanceof VideoPX) {
                            type2 = Type.VIDEO;
                            VideoPX videoPX = (VideoPX) extension;
                            builder4.m4380h(videoPX.m7704e());
                            builder4.m4361b(videoPX.m7701b());
                            builder4.m4350a(videoPX.m7700a());
                            builder4.m4368c(videoPX.m7703d());
                            builder4.m4371d(videoPX.m7702c());
                            builder4.m4376e(videoPX.m7705f());
                            builder4.m4379g(videoPX.m7706g());
                            type = type2;
                        } else if (extension instanceof LocationPX) {
                            type2 = Type.MAP;
                            LocationPX locationPX = (LocationPX) extension;
                            builder4.m4358a(Double.valueOf(locationPX.m7684c()));
                            builder4.m4364b(Double.valueOf(locationPX.m7685d()));
                            builder4.m4381i(locationPX.m7682a());
                            builder4.m4382j(locationPX.m7683b());
                            builder4.m4376e(locationPX.m7686e());
                            type = type2;
                        } else {
                            Main.f1926a.m5679b("MediaExtension of unknown type!");
                        }
                        builder4.m4365b(d2).m4353a(DeliveryStatus.RECEIVED).m4359a(str2).m4369c(a2).m4372d(str3).m4356a(type);
                        b = builder4.m4366b();
                        if (c) {
                            c2 = builder4.m4370c();
                            c2.m4455t(str);
                            insert = Main.f1927b.getContentResolver().insert(HistoryOneToOneProvider.f2383a, HistoryNormalMessageDataSource.m4718a(c2.m4453a()));
                            builder = c2;
                            builder2 = null;
                        } else {
                            extension = stanza.getExtension("cast", "http://bisphone.com/protocol/cast");
                            if (extension == null) {
                                castPX = (CastPX) extension;
                                e = builder4.m4374e();
                                e.m4357a(castPX.m7540a());
                                Main.f1926a.m5681c("CastPX Extension with cast: " + castPX.m7540a());
                                builder3 = e;
                                builder = null;
                                insert = Main.f1927b.getContentResolver().insert(HistoryChannelProvider.f2377a, HistoryChannelDataSource.m4665a(e.m4392a()));
                                builder2 = builder3;
                            } else {
                                builder2 = null;
                                builder = null;
                                insert = null;
                            }
                        }
                        Main.f1926a.m5683d("inserting message of type " + b.m4418e() + " into database");
                        if (insert == null) {
                            parseId = ContentUris.parseId(insert);
                        } else {
                            parseId = -1;
                        }
                        if (parseId != -1) {
                            Main.f1926a.m5679b("message not inserted, message_id was duplicate");
                        }
                        if (c) {
                            a3 = builder4.m4370c().m4453a();
                            a3.m4408a(parseId);
                            a3.m4459e(str);
                            m7445b(builder.m4453a(), false);
                            Main.f1927b.getContentResolver().insert(ConversationNormalProvider.f2373a, ConversationNormalDataSource.m4623a().m4626a(builder.m4453a(), parseId));
                        } else {
                            ChannelDataSource.m4541a(builder2.m4392a());
                        }
                        Main.f1926a.m5681c("inserted message with _id " + parseId + " into database");
                        if (message.getType() != Message.Type.chat && builder != null) {
                            BadgeHandler.m6597a().m6605b();
                            builder.m4351a(parseId);
                            NotificationCenter.m6606a().m6608a(builder.m4453a());
                            return;
                        } else if (c && builder2 != null) {
                            builder2.m4351a(parseId);
                            HistoryEntity a4 = builder2.m4392a();
                            EventBus.m12779a().m12795d(new NewMessageEvent(a4, a4.m4414c()));
                            return;
                        } else {
                            return;
                        }
                    }
                    type = type2;
                    builder4.m4365b(d2).m4353a(DeliveryStatus.RECEIVED).m4359a(str2).m4369c(a2).m4372d(str3).m4356a(type);
                    b = builder4.m4366b();
                    if (c) {
                        c2 = builder4.m4370c();
                        c2.m4455t(str);
                        insert = Main.f1927b.getContentResolver().insert(HistoryOneToOneProvider.f2383a, HistoryNormalMessageDataSource.m4718a(c2.m4453a()));
                        builder = c2;
                        builder2 = null;
                    } else {
                        extension = stanza.getExtension("cast", "http://bisphone.com/protocol/cast");
                        if (extension == null) {
                            builder2 = null;
                            builder = null;
                            insert = null;
                        } else {
                            castPX = (CastPX) extension;
                            e = builder4.m4374e();
                            e.m4357a(castPX.m7540a());
                            Main.f1926a.m5681c("CastPX Extension with cast: " + castPX.m7540a());
                            builder3 = e;
                            builder = null;
                            insert = Main.f1927b.getContentResolver().insert(HistoryChannelProvider.f2377a, HistoryChannelDataSource.m4665a(e.m4392a()));
                            builder2 = builder3;
                        }
                    }
                    Main.f1926a.m5683d("inserting message of type " + b.m4418e() + " into database");
                    if (insert == null) {
                        parseId = -1;
                    } else {
                        parseId = ContentUris.parseId(insert);
                    }
                    if (parseId != -1) {
                        if (c) {
                            a3 = builder4.m4370c().m4453a();
                            a3.m4408a(parseId);
                            a3.m4459e(str);
                            m7445b(builder.m4453a(), false);
                            Main.f1927b.getContentResolver().insert(ConversationNormalProvider.f2373a, ConversationNormalDataSource.m4623a().m4626a(builder.m4453a(), parseId));
                        } else {
                            ChannelDataSource.m4541a(builder2.m4392a());
                        }
                        Main.f1926a.m5681c("inserted message with _id " + parseId + " into database");
                        if (message.getType() != Message.Type.chat) {
                        }
                        if (c) {
                            return;
                        }
                        return;
                    }
                    Main.f1926a.m5679b("message not inserted, message_id was duplicate");
                }
            }
        } else if (message.hasExtension(ChatStateExtension.NAMESPACE)) {
            EventBus.m12779a().m12795d(new ChatStateEvent(a, ((ChatStateExtension) message.getExtension(ChatStateExtension.NAMESPACE)).getChatState()));
        }
    }

    public void m7439a(String str, ArrayList<String> arrayList, String str2, boolean z, int i, HistoryEntity historyEntity) {
        if (!arrayList.isEmpty()) {
            HistoryEntity.Builder a = new HistoryEntity.Builder().m4356a(Type.TEXT).m4369c(str).m4359a(StringUtil.m7065b()).m4365b((String) arrayList.get(0)).m4353a(DeliveryStatus.SENDING).m4372d(str2).m4355a(historyEntity == null ? RelationType.NONE : RelationType.REPLY);
            m7417a(a, historyEntity, i);
            switch (i) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        JabberId a2;
                        String str3 = (String) it.next();
                        if (str3.contains("@")) {
                            a2 = JabberId.m7386a(str3);
                        } else {
                            a2 = new JabberId(str3, "bisphone.com", null);
                        }
                        if (a2 != null) {
                            a.m4365b(a2.m7391e()).m4359a(StringUtil.m7065b());
                            Builder c = a.m4370c();
                            c.m4455t(a2.m7387a());
                            m7435a(c.m4453a(), z, 1);
                        }
                    }
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    HistoryGroupEntity.Builder d = a.m4373d();
                    d.m4447t(AccountManager.m3934a().m3937c());
                    m7435a(d.m4445a(), z, i);
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    m7435a(a.m4366b(), z, i);
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    Builder c2 = a.m4370c();
                    c2.m4455t("");
                    c2.m4365b(new JabberId(AccountManager.m3934a().m3937c(), "broadcast.bisphone.com", null).m7391e());
                    HistoryNormalMessageEntity a3 = c2.m4453a();
                    m7423a((ArrayList) arrayList, a3);
                    m7419a(a3, (ArrayList) arrayList);
                    EventBus.m12779a().m12795d(new MessageSendEvent(a3, i));
                default:
            }
        }
    }

    public void m7435a(HistoryEntity historyEntity, boolean z, int i) {
        if (!z) {
            historyEntity.m4408a(m7413a((Object) historyEntity, i));
            EventBus.m12779a().m12795d(new MessageSendEvent(historyEntity, i));
        }
        SendMessageEvent sendMessageEvent = new SendMessageEvent(XMPPUtils.m7091a(historyEntity, i));
        sendMessageEvent.m4977a(new C05511(this, i));
        EventBus.m12779a().m12795d(sendMessageEvent);
    }

    public void m7438a(String str, String str2, ArrayList<String> arrayList, String str3, boolean z, int i, HistoryEntity historyEntity) {
        if (!arrayList.isEmpty()) {
            RelationType relationType;
            HistoryEntity.Builder f = new HistoryEntity.Builder().m4356a(Type.MAP).m4369c("Map Message").m4353a(DeliveryStatus.SENDING).m4372d(str3).m4359a(StringUtil.m7065b()).m4365b((String) arrayList.get(0)).m4376e(str2).m4378f(str);
            if (historyEntity == null) {
                relationType = RelationType.NONE;
            } else {
                relationType = RelationType.REPLY;
            }
            HistoryEntity.Builder a = f.m4355a(relationType);
            m7417a(a, historyEntity, i);
            switch (i) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        JabberId a2;
                        String str4 = (String) it.next();
                        if (str4.contains("@")) {
                            a2 = JabberId.m7386a(str4);
                        } else {
                            a2 = new JabberId(str4, "bisphone.com", null);
                        }
                        if (a2 != null) {
                            a.m4365b(a2.m7391e()).m4359a(StringUtil.m7065b());
                            Builder c = a.m4370c();
                            c.m4455t(a2.m7387a());
                            m7444b(c.m4453a(), z, 1);
                        }
                    }
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    HistoryGroupEntity.Builder d = a.m4373d();
                    d.m4447t(AccountManager.m3934a().m3937c());
                    m7444b(d.m4445a(), z, i);
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    m7444b(a.m4366b(), z, i);
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    Builder c2 = a.m4370c();
                    c2.m4455t("");
                    c2.m4365b(new JabberId(AccountManager.m3934a().m3937c(), "broadcast.bisphone.com", null).m7391e());
                    HistoryNormalMessageEntity a3 = c2.m4453a();
                    m7423a((ArrayList) arrayList, a3);
                    m7419a(a3, (ArrayList) arrayList);
                    EventBus.m12779a().m12795d(new MessageSendEvent(a3, 4));
                default:
            }
        }
    }

    public void m7444b(HistoryEntity historyEntity, boolean z, int i) {
        if (!z) {
            historyEntity.m4408a(m7413a((Object) historyEntity, i));
            EventBus.m12779a().m12795d(new MessageSendEvent(historyEntity, i));
        }
        SendMessageEvent sendMessageEvent = new SendMessageEvent(XMPPUtils.m7091a(historyEntity, i));
        sendMessageEvent.m4977a(new C05522(this, i));
        EventBus.m12779a().m12795d(sendMessageEvent);
    }

    public void m7440a(ArrayList<String> arrayList, String str, boolean z, int i, int i2, int i3, HistoryEntity historyEntity) {
        if (!arrayList.isEmpty()) {
            RelationType relationType;
            HistoryEntity.Builder f = new HistoryEntity.Builder().m4356a(Type.STICKER).m4369c("Sticker Message").m4353a(DeliveryStatus.SENDING).m4372d(str).m4375e(i).m4359a(StringUtil.m7065b()).m4365b((String) arrayList.get(0)).m4377f(i2);
            if (historyEntity == null) {
                relationType = RelationType.NONE;
            } else {
                relationType = RelationType.REPLY;
            }
            HistoryEntity.Builder a = f.m4355a(relationType);
            m7417a(a, historyEntity, i3);
            switch (i3) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        JabberId a2;
                        String str2 = (String) it.next();
                        if (str2.contains("@")) {
                            a2 = JabberId.m7386a(str2);
                        } else {
                            a2 = new JabberId(str2, "bisphone.com", null);
                        }
                        if (a2 != null) {
                            a.m4365b(a2.m7391e()).m4359a(StringUtil.m7065b());
                            Builder c = a.m4370c();
                            c.m4455t(a2.m7387a());
                            m7446c(c.m4453a(), z, 1);
                        }
                    }
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    HistoryGroupEntity.Builder d = a.m4373d();
                    d.m4447t(AccountManager.m3934a().m3937c());
                    m7446c(d.m4445a(), z, i3);
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    m7446c(a.m4366b(), z, i3);
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    Builder c2 = a.m4370c();
                    c2.m4455t("");
                    c2.m4365b(new JabberId(AccountManager.m3934a().m3937c(), "broadcast.bisphone.com", null).m7391e());
                    HistoryNormalMessageEntity a3 = c2.m4453a();
                    m7419a(a3, (ArrayList) arrayList);
                    m7423a((ArrayList) arrayList, a3);
                    EventBus.m12779a().m12795d(new MessageSendEvent(a3, 4));
                default:
            }
        }
    }

    public void m7446c(HistoryEntity historyEntity, boolean z, int i) {
        if (!z) {
            historyEntity.m4408a(m7413a((Object) historyEntity, i));
            EventBus.m12779a().m12795d(new MessageSendEvent(historyEntity, i));
        }
        SendMessageEvent sendMessageEvent = new SendMessageEvent(XMPPUtils.m7091a(historyEntity, i));
        sendMessageEvent.m4977a(new C05533(this, i));
        EventBus.m12779a().m12795d(sendMessageEvent);
    }

    public void m7434a(HistoryEntity historyEntity, ArrayList<String> arrayList, String str, boolean z, int i) {
        if (historyEntity != null) {
            if (i != 4 || (arrayList != null && !arrayList.isEmpty())) {
                try {
                    File file = new File(Utils.m7079a(str, historyEntity.m4418e()));
                    if (file.exists()) {
                        historyEntity.m4409a(DeliveryStatus.UPLOADING);
                        if (historyEntity.m4418e() == Type.PHOTO && historyEntity.m4423j() != null && historyEntity.m4423j().startsWith("file://")) {
                            historyEntity.m4417d(m7431a(historyEntity));
                        }
                        switch (i) {
                            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                                if (z) {
                                    m7416a(i, historyEntity.m4412b(), DeliveryStatus.UPLOADING, null);
                                } else {
                                    m7413a((Object) historyEntity, i);
                                }
                                if (m7424a(historyEntity, str, i, file, null)) {
                                    m7432a(historyEntity, i);
                                    break;
                                }
                                break;
                            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                                HistoryNormalMessageEntity historyNormalMessageEntity = (HistoryNormalMessageEntity) historyEntity;
                                if (z) {
                                    m7416a(i, historyEntity.m4412b(), DeliveryStatus.UPLOADING, arrayList);
                                } else {
                                    m7423a((ArrayList) arrayList, historyNormalMessageEntity);
                                }
                                if (m7424a(historyEntity, str, i, file, (ArrayList) arrayList)) {
                                    m7419a(historyNormalMessageEntity, (ArrayList) arrayList);
                                    break;
                                }
                                break;
                            default:
                                break;
                        }
                        if (!z) {
                            EventBus.m12779a().m12795d(new MessageSendEvent(historyEntity, i));
                            return;
                        }
                        return;
                    }
                    Main.f1926a.m5679b("Token is not in the cache.");
                } catch (StorageException e) {
                }
            }
        }
    }

    private boolean m7424a(HistoryEntity historyEntity, String str, int i, File file, ArrayList<String> arrayList) {
        if (m7442a(str)) {
            Main.f1926a.m5681c("*** Media Is Available On Server, Skip Uploading ***");
            m7416a(i, historyEntity.m4412b(), DeliveryStatus.SENDING, arrayList);
            return true;
        }
        RequestParams requestParams = new RequestParams();
        requestParams.m10743a("username", this.f4967a);
        requestParams.m10743a("uuid", DeviceUtils.m7012a(Main.f1927b));
        requestParams.m10743a("host", "bisphone.com");
        try {
            requestParams.m10741a("uploadedfile", file);
            HttpService.m5567a(new TaskBuilder().m5590a(str).m5585a(this.f4968e).m5592b(Constants.m3959d()).m5589a(requestParams).m5586a(RequestType.post).m5587a(TaskPriority.medium).m5588a(new C05544(this, str, i, historyEntity, arrayList)).m5584a());
            return false;
        } catch (Throwable e) {
            Main.f1926a.m5684d(e);
            return false;
        }
    }

    public void m7432a(HistoryEntity historyEntity, int i) {
        SendMessageEvent sendMessageEvent = new SendMessageEvent(XMPPUtils.m7091a(historyEntity, i));
        sendMessageEvent.m4977a(new C05555(this, i));
        EventBus.m12779a().m12795d(sendMessageEvent);
    }

    public void m7433a(HistoryEntity historyEntity, int i, ArrayList<String> arrayList) {
        if (historyEntity.m4419f().length() < 16) {
            historyEntity.m4415c((Long.parseLong(historyEntity.m4419f()) * 1000) + "");
        }
        if (arrayList != null && !arrayList.isEmpty()) {
            if (arrayList.size() == 1) {
                if (historyEntity instanceof HistoryNormalMessageEntity) {
                    JabberId a = JabberId.m7386a((String) arrayList.get(0));
                    if (a != null) {
                        ((HistoryNormalMessageEntity) historyEntity).m4459e(a.m7387a());
                        historyEntity.m4413b((String) arrayList.get(0));
                    } else {
                        return;
                    }
                }
                historyEntity.m4409a(DeliveryStatus.SENDING);
                historyEntity.m4408a(m7413a((Object) historyEntity, i));
                EventBus.m12779a().m12795d(new MessageSendEvent(historyEntity, i));
                m7432a(historyEntity, i);
            } else if (arrayList.size() > 1) {
                String str;
                switch (i) {
                    case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            str = (String) it.next();
                            if (historyEntity instanceof HistoryNormalMessageEntity) {
                                JabberId jabberId = new JabberId(str, "bisphone.com", null);
                                ((HistoryNormalMessageEntity) historyEntity).m4459e(jabberId.m7387a());
                                historyEntity.m4413b(jabberId.m7391e());
                            }
                            historyEntity.m4409a(DeliveryStatus.SENDING);
                            historyEntity.m4410a(StringUtil.m7065b());
                            historyEntity.m4408a(m7413a((Object) historyEntity, i));
                            m7432a(historyEntity, i);
                        }
                    case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                        Collections.sort(arrayList);
                        str = Utils.m7080a((ArrayList) arrayList);
                        if (NormalMessageManager.m7447a().m7467d(str)) {
                            str = NormalMessageManager.m7447a().m7468e(str).m4168c();
                        } else {
                            str = BroadcastListDataSource.m4504a().m4508a(str);
                        }
                        historyEntity.m4413b(new JabberId(str, "broadcast.bisphone.com", null).m7391e());
                        EventBus.m12779a().m12795d(new MessageSendEvent(historyEntity, 4));
                        m7423a((ArrayList) arrayList, (HistoryNormalMessageEntity) historyEntity);
                        m7419a((HistoryNormalMessageEntity) historyEntity, (ArrayList) arrayList);
                    default:
                }
            }
        }
    }

    private void m7419a(HistoryNormalMessageEntity historyNormalMessageEntity, ArrayList<String> arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            m7422a((String) it.next(), 1);
        }
        SendBroadcastMessageEvent sendBroadcastMessageEvent = new SendBroadcastMessageEvent(XMPPUtils.m7091a((HistoryEntity) historyNormalMessageEntity, 4), arrayList, historyNormalMessageEntity);
        sendBroadcastMessageEvent.m4977a(new C05566(this));
        EventBus.m12779a().m12795d(sendBroadcastMessageEvent);
    }

    private void m7417a(HistoryEntity.Builder builder, HistoryEntity historyEntity, int i) {
        if (historyEntity != null) {
            builder.m4389q(historyEntity.m4412b()).m4362b(historyEntity.m4418e());
            builder.m4363b(m7426b(historyEntity));
            if (i == 2) {
                builder.m4391s(((HistoryGroupEntity) historyEntity).m4449M());
            } else if (i == 1) {
                builder.m4391s(historyEntity.m4422i() ? AccountManager.m3934a().m3937c() : ((HistoryNormalMessageEntity) historyEntity).m4456M());
            }
            if (historyEntity.m4418e() != Type.AUDIO && historyEntity.m4418e() != Type.MAP) {
                String d = historyEntity.m4418e() == Type.TEXT ? historyEntity.m4416d() : historyEntity.m4418e() == Type.STICKER ? historyEntity.m4397C() + "-" + historyEntity.m4396B() : BitmapUtil.m6981d(historyEntity.m4423j());
                builder.m4390r(d);
            }
        }
    }

    private void m7416a(int i, String str, DeliveryStatus deliveryStatus, ArrayList<String> arrayList) {
        if (i == 2 && deliveryStatus == DeliveryStatus.SENT) {
            Main.f1926a.m5679b("This is a warning. Don't ignore it, seriously!");
            return;
        }
        String str2 = (System.currentTimeMillis() * 1000) + "";
        switch (i) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                HistoryNormalMessageDataSource.m4720a().m4740a(str, deliveryStatus);
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                HistoryGroupDataSource.m4691a().m4711a(str, deliveryStatus);
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                HistoryChannelDataSource.m4667a().m4685a(str, deliveryStatus);
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                HistoryNormalMessageDataSource.m4720a().m4741a(str, (ArrayList) arrayList, deliveryStatus);
                break;
        }
        EventBus.m12779a().m12795d(new MessageSendStatusEvent(str, deliveryStatus, str2));
    }

    private void m7422a(String str, int i) {
        if (NormalMessageManager.m7447a().m7470f(str) != null && NormalMessageManager.m7447a().m7470f(str).m4172f() != 0) {
            NormalMessageManager.m7447a().m7470f(str).m4170d().m4329b(NormalMessageManager.m7447a().m7470f(str).m4170d().m4339i() + i);
            ConversationNormalDataSource.m4623a().m4631a(str, i);
        }
    }

    private long m7413a(Object obj, int i) {
        Uri uri = null;
        if (i == 1) {
            uri = this.f4968e.getContentResolver().insert(HistoryOneToOneProvider.f2383a, HistoryNormalMessageDataSource.m4718a((HistoryNormalMessageEntity) obj));
            m7445b((HistoryNormalMessageEntity) obj, true);
        } else if (i == 3) {
            HistoryChannelEntity historyChannelEntity;
            if (obj instanceof HistoryChannelEntity) {
                historyChannelEntity = (HistoryChannelEntity) obj;
            } else if (obj instanceof HistoryEntity) {
                historyChannelEntity = HistoryChannelEntity.m4440a((HistoryEntity) obj);
            } else {
                throw new RuntimeException("not define parameter, if you need that, define that");
            }
            uri = this.f4968e.getContentResolver().insert(HistoryChannelProvider.f2377a, HistoryChannelDataSource.m4665a(historyChannelEntity));
        } else if (i == 2) {
            HistoryGroupEntity historyGroupEntity;
            if (obj instanceof HistoryGroupEntity) {
                historyGroupEntity = (HistoryGroupEntity) obj;
            } else if (obj instanceof HistoryNormalMessageEntity) {
                historyGroupEntity = HistoryGroupEntity.m4448a((HistoryNormalMessageEntity) obj);
                historyGroupEntity.m4450e(AccountManager.m3934a().m3937c());
            } else {
                throw new RuntimeException("not define parameter, if you need that, define that");
            }
            ContentValues a = HistoryGroupDataSource.m4689a(historyGroupEntity);
            a.remove("message_time_stamp");
            uri = this.f4968e.getContentResolver().insert(HistoryGroupProvider.f2380a, a);
            historyGroupEntity.m4415c("1000");
            ConversationGroupDataSource.m4587a().m4598a(historyGroupEntity, ContentUris.parseId(uri));
        }
        if (uri == null) {
            return -1;
        }
        return ContentUris.parseId(uri);
    }

    public void m7437a(HistoryNormalMessageEntity historyNormalMessageEntity, boolean z) {
        synchronized (this.f4969f) {
            if (NormalMessageManager.m7447a().m7463b(historyNormalMessageEntity.m4456M())) {
                m7430d(historyNormalMessageEntity, z);
            } else if (CallStatus.MISSED.name().equals(historyNormalMessageEntity.m4435v())) {
                m7445b(historyNormalMessageEntity, z);
            }
        }
    }

    public void m7445b(HistoryNormalMessageEntity historyNormalMessageEntity, boolean z) {
        synchronized (this.f4969f) {
            if (NormalMessageManager.m7447a().m7463b(historyNormalMessageEntity.m4456M())) {
                m7430d(historyNormalMessageEntity, z);
            } else {
                m7429c(historyNormalMessageEntity, z);
            }
        }
    }

    private void m7429c(HistoryNormalMessageEntity historyNormalMessageEntity, boolean z) {
        ConversationNormalEntity.Builder builder = new ConversationNormalEntity.Builder();
        builder.m4322d(historyNormalMessageEntity.m4456M()).m4321c(historyNormalMessageEntity.m4416d()).m4320b(historyNormalMessageEntity.m4412b()).m4316a(historyNormalMessageEntity.m4419f()).m4315a(historyNormalMessageEntity.m4418e());
        ChatHistoryEntity chatHistoryEntity = new ChatHistoryEntity(builder.m4317a(), ContactDataSource.m4553a().m4582g(historyNormalMessageEntity.m4456M()));
        chatHistoryEntity.m4163a(z ? 0 : 1);
        ConversationNormalDataSource.m4623a().m4636b(chatHistoryEntity.m4170d());
        NormalMessageManager.m7447a().m7451a(chatHistoryEntity);
    }

    private void m7430d(HistoryNormalMessageEntity historyNormalMessageEntity, boolean z) {
        ChatHistoryEntity f = NormalMessageManager.m7447a().m7470f(historyNormalMessageEntity.m4456M());
        if (Long.parseLong(f.m4170d().m4336f()) < Long.parseLong(historyNormalMessageEntity.m4419f())) {
            f.m4170d().m4330b(historyNormalMessageEntity.m4416d());
            f.m4170d().m4327a(historyNormalMessageEntity.m4412b());
            f.m4170d().m4334d(historyNormalMessageEntity.m4419f());
            f.m4170d().m4326a(historyNormalMessageEntity.m4418e());
        }
        if (!z) {
            f.m4177k();
        }
        NormalMessageManager.m7447a().m7451a(f);
        ConversationNormalDataSource.m4623a().m4636b(f.m4170d());
    }

    private void m7423a(ArrayList<String> arrayList, HistoryNormalMessageEntity historyNormalMessageEntity) {
        String a = Utils.m7080a((ArrayList) arrayList);
        if (NormalMessageManager.m7447a().m7467d(a)) {
            String c = NormalMessageManager.m7447a().m7468e(a).m4168c();
            historyNormalMessageEntity.m4458a(true);
            historyNormalMessageEntity.m4413b(new JabberId(c, "bisphone.com", null).m7391e());
            HistoryNormalMessageDataSource.m4720a().m4737a(historyNormalMessageEntity, c);
            BroadcastListDataSource.m4504a().m4510a(c, historyNormalMessageEntity.m4416d(), historyNormalMessageEntity.m4418e(), (System.currentTimeMillis() * 1000) + "");
            m7418a(historyNormalMessageEntity, c, a);
        }
    }

    private void m7418a(HistoryNormalMessageEntity historyNormalMessageEntity, String str, String str2) {
        if (NormalMessageManager.m7447a().m7465c(str)) {
            m7436a(historyNormalMessageEntity, str);
        } else {
            m7428b(historyNormalMessageEntity, str, str2);
        }
    }

    private void m7428b(HistoryNormalMessageEntity historyNormalMessageEntity, String str, String str2) {
        if (!NormalMessageManager.m7447a().m7465c(str)) {
            ConversationNormalEntity.Builder builder = new ConversationNormalEntity.Builder();
            builder.m4322d("").m4321c(historyNormalMessageEntity.m4416d()).m4320b("").m4316a((System.currentTimeMillis() * 1000) + "").m4315a(Type.TEXT);
            NormalMessageManager.m7447a().m7459b(new ChatHistoryEntity(builder.m4317a(), str, str2));
        }
    }

    public void m7436a(HistoryNormalMessageEntity historyNormalMessageEntity, String str) {
        ChatHistoryEntity g = NormalMessageManager.m7447a().m7472g(str);
        g.m4170d().m4330b(historyNormalMessageEntity.m4416d());
        g.m4170d().m4334d((System.currentTimeMillis() * 1000) + "");
        g.m4170d().m4326a(Type.TEXT);
        g.m4170d().m4332c("");
        g.m4170d().m4327a("");
        NormalMessageManager.m7447a().m7459b(g);
    }

    public synchronized void m7443b() {
        if (!this.f4970g) {
            this.f4970g = true;
            new Thread(new C05587(this, new HashMap()), "Thread-Send-Failed-Messages").start();
        }
    }

    public boolean m7442a(String str) {
        if (NetworkConnectivityChangeReceiver.m7394a(this.f4968e)) {
            return false;
        }
        SyncHttpClient syncHttpClient = new SyncHttpClient();
        syncHttpClient.m10580a(5);
        boolean[] zArr = new boolean[]{false};
        syncHttpClient.m10575a("https://chatng.bisphone.com:443/api/v1/posts/info", new RequestParams("token", str), new C05598(this, zArr));
        return zArr[0];
    }

    protected String m7431a(HistoryEntity historyEntity) {
        if (historyEntity == null) {
            return null;
        }
        if (historyEntity.m4423j() == null) {
            return m7427b(historyEntity.m4430q());
        }
        if (!historyEntity.m4423j().startsWith("file://")) {
            return historyEntity.m4423j();
        }
        try {
            File e = FileUtil.m7029e(historyEntity.m4423j());
            if (e != null && e.exists()) {
                Bitmap decodeFile = BitmapFactory.decodeFile(e.getPath());
                if (decodeFile == null) {
                    return m7427b(historyEntity.m4430q());
                }
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                decodeFile.compress(CompressFormat.JPEG, 100, byteArrayOutputStream);
                return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
            }
        } catch (Throwable e2) {
            Main.f1926a.m5678a(e2);
        }
        return m7427b(historyEntity.m4430q());
    }

    private String m7427b(String str) {
        Throwable e;
        try {
            File file = new File(Storage.m6941b() + File.separator + str);
            if (file.exists()) {
                return FileUtil.m7024b(file);
            }
            return null;
        } catch (StorageException e2) {
            e = e2;
            Main.f1926a.m5682c(e);
            return null;
        } catch (IOException e3) {
            e = e3;
            Main.f1926a.m5682c(e);
            return null;
        }
    }

    private CastPX.Type m7426b(HistoryEntity historyEntity) {
        JabberId a = JabberId.m7386a(historyEntity.m4414c());
        if (a == null || !XMPPUtils.m7095c(a) || historyEntity.m4422i()) {
            return null;
        }
        return historyEntity.m4398D();
    }
}
