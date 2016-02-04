package app.messaging.broadcast;

import app.common.entity.ContactEntityLite;
import app.common.entity.HistoryEntity.DeliveryStatus;

public class BroadcastEntityStatusModel {
    private ContactEntityLite f3314a;
    private DeliveryStatus f3315b;

    public BroadcastEntityStatusModel(ContactEntityLite contactEntityLite, DeliveryStatus deliveryStatus) {
        this.f3314a = contactEntityLite;
        this.f3315b = deliveryStatus;
    }

    public ContactEntityLite m5987a() {
        return this.f3314a;
    }

    public DeliveryStatus m5988b() {
        return this.f3315b;
    }
}
