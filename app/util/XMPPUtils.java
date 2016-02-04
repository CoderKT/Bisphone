package app.util;

import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import app.Main;
import app.common.entity.ContactEntity;
import app.common.entity.ContactEntity.STATUS;
import app.common.entity.HistoryEntity;
import app.common.entity.HistoryEntity.Type;
import app.xmpp.JabberId;
import app.xmpp.packet.CallSignalPX;
import app.xmpp.packet.common.ContentXE;
import app.xmpp.packet.media.AudioPX;
import app.xmpp.packet.media.LocationPX;
import app.xmpp.packet.media.PhotoPX;
import app.xmpp.packet.media.StickerPX;
import app.xmpp.packet.media.VideoPX;
import app.xmpp.packet.reply.ReplyAudioXE;
import app.xmpp.packet.reply.ReplyMapXE;
import app.xmpp.packet.reply.ReplyPhotoXE;
import app.xmpp.packet.reply.ReplyStickerXE;
import app.xmpp.packet.reply.ReplyTextXE;
import app.xmpp.packet.reply.ReplyVideoXE;
import app.xmpp.packet.sublist.Sublist;
import app.xmpp.packet.sublist.Sublist.Item;
import java.util.List;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.sm.packet.StreamManagement.AckRequest;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smackx.chatstates.ChatState;
import org.jivesoftware.smackx.chatstates.packet.ChatStateExtension;
import org.jivesoftware.smackx.receipts.DeliveryReceiptRequest;
import org.jivesoftware.smackx.xhtmlim.XHTMLText;
import se.emilsjolander.stickylistheaders.C1128R;

public class XMPPUtils {

    /* renamed from: app.util.XMPPUtils.1 */
    /* synthetic */ class C05081 {
        static final /* synthetic */ int[] f4624a;
        static final /* synthetic */ int[] f4625b;

        static {
            f4625b = new int[STATUS.values().length];
            try {
                f4625b[STATUS.ADDED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f4625b[STATUS.MODIFIED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f4625b[STATUS.REMOVED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            f4624a = new int[Type.values().length];
            try {
                f4624a[Type.TEXT.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f4624a[Type.STICKER.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f4624a[Type.PHOTO.ordinal()] = 3;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f4624a[Type.AUDIO.ordinal()] = 4;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f4624a[Type.VIDEO.ordinal()] = 5;
            } catch (NoSuchFieldError e8) {
            }
            try {
                f4624a[Type.MAP.ordinal()] = 6;
            } catch (NoSuchFieldError e9) {
            }
            try {
                f4624a[Type.GROUP_STATUS.ordinal()] = 7;
            } catch (NoSuchFieldError e10) {
            }
            try {
                f4624a[Type.CALL.ordinal()] = 8;
            } catch (NoSuchFieldError e11) {
            }
        }
    }

    public static String m7089a(JabberId jabberId) {
        return "+" + jabberId.m7387a();
    }

    public static boolean m7094b(JabberId jabberId) {
        return jabberId.m7388b().startsWith("egc");
    }

    public static boolean m7095c(JabberId jabberId) {
        return "channel.bisphone.com".equalsIgnoreCase(jabberId.m7388b());
    }

    public static boolean m7096d(JabberId jabberId) {
        return "bisphone.com".equalsIgnoreCase(jabberId.m7388b());
    }

    public static Message.Type m7097e(JabberId jabberId) {
        if (m7094b(jabberId) || m7095c(jabberId)) {
            return Message.Type.groupchat;
        }
        return Message.Type.chat;
    }

    public static Message m7091a(HistoryEntity historyEntity, int i) {
        ExtensionElement extensionElement;
        ExtensionElement extensionElement2 = null;
        StringBuilder stringBuilder = new StringBuilder("");
        if (historyEntity.m4414c().equals("chan-bisphone-01@channel.bisphone.com")) {
            try {
                stringBuilder.append("\n\nApp version : ").append(Main.f1927b.getPackageManager().getPackageInfo(Main.f1927b.getPackageName(), 0).versionCode).append(" Android").append("\n");
            } catch (NameNotFoundException e) {
                stringBuilder.append("App version : ").append("Unknown").append("\n");
                e.printStackTrace();
            }
            stringBuilder.append("Device name: ").append(Build.MANUFACTURER).append(" ").append(Build.MODEL).append("\n");
            stringBuilder.append("Android SDK Version: ").append(VERSION.SDK_INT).append("\n");
            stringBuilder.append("CPU Architecture: ").append(System.getProperty("os.arch")).append("\n");
        }
        Message message = new Message();
        message.setTo(historyEntity.m4414c());
        message.setBody(historyEntity.m4416d() + stringBuilder.toString());
        message.setStanzaId(historyEntity.m4412b());
        JabberId a = JabberId.m7386a(historyEntity.m4414c());
        message.setType(m7097e(a));
        switch (C05081.f4624a[historyEntity.m4418e().ordinal()]) {
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                extensionElement = null;
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                extensionElement = new StickerPX(historyEntity.m4396B(), historyEntity.m4397C());
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                extensionElement = new PhotoPX(historyEntity.m4425l(), historyEntity.m4427n(), historyEntity.m4430q(), historyEntity.m4423j(), historyEntity.m4426m());
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                extensionElement = new AudioPX(historyEntity.m4429p(), historyEntity.m4428o(), historyEntity.m4430q());
                break;
            case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                extensionElement = new VideoPX(historyEntity.m4425l(), historyEntity.m4427n(), historyEntity.m4429p(), historyEntity.m4428o(), historyEntity.m4430q(), historyEntity.m4423j(), historyEntity.m4426m());
                break;
            case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                Object locationPX = new LocationPX(historyEntity.m4431r(), historyEntity.m4432s(), historyEntity.m4433t().doubleValue(), historyEntity.m4434u().doubleValue(), historyEntity.m4423j());
                break;
            default:
                throw new IllegalStateException("MessageEntity Type is not supported!");
        }
        if (!m7094b(a)) {
            message.addExtension(new DeliveryReceiptRequest());
        }
        if (i == 2) {
            message.addExtension(new ContentXE(ContentXE.Type.conversation));
        }
        if (historyEntity.m4402H() != null) {
            switch (C05081.f4624a[historyEntity.m4406L().ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    extensionElement2 = new ReplyTextXE(historyEntity.m4402H(), historyEntity.m4404J(), historyEntity.m4403I(), historyEntity.m4405K());
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    extensionElement2 = new ReplyStickerXE(historyEntity.m4402H(), historyEntity.m4404J(), historyEntity.m4405K());
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    extensionElement2 = new ReplyPhotoXE(historyEntity.m4402H(), historyEntity.m4404J(), historyEntity.m4403I(), historyEntity.m4405K());
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                    extensionElement2 = new ReplyAudioXE(historyEntity.m4402H(), historyEntity.m4403I(), historyEntity.m4405K());
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                    extensionElement2 = new ReplyVideoXE(historyEntity.m4402H(), historyEntity.m4404J(), historyEntity.m4403I(), historyEntity.m4405K());
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                    extensionElement2 = new ReplyMapXE(historyEntity.m4402H(), historyEntity.m4404J(), historyEntity.m4405K());
                    break;
            }
            message.addExtension(extensionElement2);
        }
        if (extensionElement != null) {
            message.addExtension(extensionElement);
        }
        return message;
    }

    public static Message m7093a(JabberId jabberId, ChatState chatState) {
        Message message = new Message(jabberId.m7390d(), Message.Type.headline);
        message.addExtension(new ChatStateExtension(chatState));
        return message;
    }

    public static Message m7092a(JabberId jabberId, CallSignalPX.Type type, String str) {
        Message message = new Message();
        message.setTo(jabberId.m7390d());
        message.setBody(type == CallSignalPX.Type.call ? "Incoming Call" : "Phone Call");
        message.setType(Message.Type.chat);
        message.addExtension(new CallSignalPX(type, str));
        return message;
    }

    public static Sublist m7088a(List<ContactEntity> list, String str) {
        Sublist sublist = new Sublist();
        sublist.setStanzaId(str);
        sublist.setType(IQ.Type.set);
        for (ContactEntity contactEntity : list) {
            String str2 = null;
            switch (C05081.f4625b[contactEntity.m4201h().ordinal()]) {
                case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                    str2 = XHTMLText.f8584A;
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    str2 = "m";
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    str2 = AckRequest.ELEMENT;
                    break;
            }
            if (str2 == null) {
                return sublist;
            }
            sublist.m7718a(new Item(contactEntity.m4200g(), str2, m7090a(contactEntity.m4196e())));
        }
        return sublist;
    }

    private static String m7090a(String str) {
        if (str == null) {
            return "";
        }
        return StringUtils.escapeForXML(str) + "";
    }
}
