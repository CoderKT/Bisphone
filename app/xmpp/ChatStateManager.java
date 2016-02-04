package app.xmpp;

import app.Main;
import app.events.xmpp.SendPacketEvent;
import app.util.XMPPUtils;
import de.greenrobot.event.EventBus;
import java.util.HashMap;
import java.util.Map;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.chatstates.ChatState;

public class ChatStateManager {
    private static ChatStateManager f4881a;
    private Map<String, ChatState> f4882b;

    public ChatStateManager() {
        this.f4882b = new HashMap();
    }

    public static ChatStateManager m7307a() {
        if (f4881a == null) {
            f4881a = new ChatStateManager();
        }
        return f4881a;
    }

    public Map<String, ChatState> m7309b() {
        return this.f4882b;
    }

    public void m7308a(String str, ChatState chatState) {
        if (str != null && this.f4882b.get(str) != chatState) {
            this.f4882b.put(str, chatState);
            JabberId a = JabberId.m7386a(str);
            if (a != null) {
                Stanza a2 = XMPPUtils.m7093a(a, chatState);
                Main.f1926a.m5685e("Sending Chat State " + chatState + " to " + a.m7390d());
                EventBus.m12779a().m12795d(new SendPacketEvent(a2));
            }
        }
    }
}
