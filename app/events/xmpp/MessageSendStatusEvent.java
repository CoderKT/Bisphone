package app.events.xmpp;

import app.common.entity.HistoryEntity.DeliveryStatus;

public class MessageSendStatusEvent {
    private String f2521a;
    private DeliveryStatus f2522b;
    private String f2523c;

    public MessageSendStatusEvent(String str, DeliveryStatus deliveryStatus, String str2) {
        this.f2521a = str;
        this.f2522b = deliveryStatus;
        this.f2523c = str2;
    }

    public String m4987a() {
        return this.f2521a;
    }

    public DeliveryStatus m4988b() {
        return this.f2522b;
    }

    public String m4989c() {
        return this.f2523c;
    }
}
