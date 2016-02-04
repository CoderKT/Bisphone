package app.messaging.channel;

public class ChannelCommunicationFollowEntity {
    private ViewHolder f3468a;
    private String f3469b;
    private boolean f3470c;

    public ChannelCommunicationFollowEntity(ViewHolder viewHolder, String str, boolean z) {
        this.f3468a = viewHolder;
        this.f3469b = str;
        this.f3470c = z;
    }

    public boolean m6098a() {
        return this.f3470c;
    }

    public ViewHolder getHolder() {
        return this.f3468a;
    }

    public String getJabberId() {
        return this.f3469b;
    }

    public void setHolder(ViewHolder viewHolder) {
        this.f3468a = viewHolder;
    }

    public void setSuccess(boolean z) {
        this.f3470c = z;
    }

    public void setJabberId(String str) {
        this.f3469b = str;
    }
}
