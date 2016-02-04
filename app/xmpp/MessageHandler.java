package app.xmpp;

import android.content.Context;
import app.account.AccountManager;
import app.common.entity.HistoryEntity.Builder;
import app.common.entity.HistoryEntity.RelationType;
import app.common.entity.HistoryEntity.Type;
import app.xmpp.packet.reply.ReplyAudioXE;
import app.xmpp.packet.reply.ReplyMapXE;
import app.xmpp.packet.reply.ReplyPhotoXE;
import app.xmpp.packet.reply.ReplyStickerXE;
import app.xmpp.packet.reply.ReplyTextXE;
import app.xmpp.packet.reply.ReplyVideoXE;
import org.jivesoftware.smack.packet.Message;

public class MessageHandler {
    protected Context f4927b;
    protected String f4928c;

    public MessageHandler(Context context) {
        this.f4927b = context;
        this.f4928c = AccountManager.m3934a().m3937c();
    }

    public void m7376a(Message message, Builder builder) {
        RelationType relationType = RelationType.NONE;
        String a;
        RelationType relationType2;
        if (message.hasExtension("reply-audio", "bpn:common:v1:reply")) {
            ReplyAudioXE replyAudioXE = (ReplyAudioXE) message.getExtension("reply-audio", "bpn:common:v1:reply");
            a = replyAudioXE.m7707a();
            relationType2 = RelationType.REPLY;
            builder.m4389q(a);
            builder.m4362b(Type.AUDIO);
            builder.m4391s(replyAudioXE.m7709c());
            relationType = relationType2;
        } else if (message.hasExtension("reply-video", "bpn:common:v1:reply")) {
            ReplyVideoXE replyVideoXE = (ReplyVideoXE) message.getExtension("reply-video", "bpn:common:v1:reply");
            a = replyVideoXE.m7707a();
            relationType2 = RelationType.REPLY;
            builder.m4389q(a);
            builder.m4390r(replyVideoXE.m7708b());
            builder.m4362b(Type.VIDEO);
            builder.m4391s(replyVideoXE.m7709c());
            relationType = relationType2;
        } else if (message.hasExtension("reply-photo", "bpn:common:v1:reply")) {
            ReplyPhotoXE replyPhotoXE = (ReplyPhotoXE) message.getExtension("reply-photo", "bpn:common:v1:reply");
            a = replyPhotoXE.m7707a();
            relationType2 = RelationType.REPLY;
            builder.m4389q(a);
            builder.m4362b(Type.PHOTO);
            builder.m4390r(replyPhotoXE.m7708b());
            builder.m4391s(replyPhotoXE.m7709c());
            relationType = relationType2;
        } else if (message.hasExtension("reply-text", "bpn:common:v1:reply")) {
            ReplyTextXE replyTextXE = (ReplyTextXE) message.getExtension("reply-text", "bpn:common:v1:reply");
            a = replyTextXE.m7707a();
            relationType2 = RelationType.REPLY;
            builder.m4389q(a);
            builder.m4390r(replyTextXE.m7708b());
            builder.m4391s(replyTextXE.m7709c());
            builder.m4362b(Type.TEXT);
            builder.m4391s(replyTextXE.m7709c());
            relationType = relationType2;
        } else if (message.hasExtension("reply-map", "bpn:common:v1:reply")) {
            ReplyMapXE replyMapXE = (ReplyMapXE) message.getExtension("reply-map", "bpn:common:v1:reply");
            a = replyMapXE.m7707a();
            relationType2 = RelationType.REPLY;
            builder.m4389q(a);
            builder.m4362b(Type.MAP);
            builder.m4390r(replyMapXE.m7708b());
            builder.m4391s(replyMapXE.m7709c());
            relationType = relationType2;
        } else if (message.hasExtension("reply-sticker", "bpn:common:v1:reply")) {
            ReplyStickerXE replyStickerXE = (ReplyStickerXE) message.getExtension("reply-sticker", "bpn:common:v1:reply");
            a = replyStickerXE.m7707a();
            relationType2 = RelationType.REPLY;
            builder.m4389q(a);
            builder.m4362b(Type.STICKER);
            builder.m4390r(replyStickerXE.m7708b());
            builder.m4391s(replyStickerXE.m7709c());
            relationType = relationType2;
        }
        builder.m4355a(relationType);
    }
}
