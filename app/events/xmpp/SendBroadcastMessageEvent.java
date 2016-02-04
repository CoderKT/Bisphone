package app.events.xmpp;

import app.common.entity.HistoryNormalMessageEntity;
import java.util.ArrayList;
import org.jivesoftware.smack.packet.Message;

public class SendBroadcastMessageEvent extends AbstractSendPacketEvent {
    private Message f2524a;
    private HistoryNormalMessageEntity f2525b;
    private ArrayList<String> f2526c;

    public SendBroadcastMessageEvent(Message message, ArrayList<String> arrayList, HistoryNormalMessageEntity historyNormalMessageEntity) {
        this.f2524a = message;
        this.f2526c = arrayList;
        this.f2525b = historyNormalMessageEntity;
    }

    public Message m4990b() {
        return this.f2524a;
    }

    public ArrayList<String> m4991c() {
        return this.f2526c;
    }

    public HistoryNormalMessageEntity m4992d() {
        return this.f2525b;
    }
}
