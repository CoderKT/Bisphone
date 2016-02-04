package app.messaging.channel;

public class ChannelCommunicationUnfollowEntity {
    private ViewHolder f3471a;
    private String f3472b;
    private boolean f3473c;

    public ChannelCommunicationUnfollowEntity(ViewHolder viewHolder, String str, boolean z) {
        this.f3471a = viewHolder;
        this.f3472b = str;
        this.f3473c = z;
    }

    public ViewHolder getHolder() {
        return this.f3471a;
    }

    public String getJabberId() {
        return this.f3472b;
    }

    public boolean m6099a() {
        return this.f3473c;
    }

    public void setHolder(ViewHolder viewHolder) {
        this.f3471a = viewHolder;
    }

    public void setJabberId(String str) {
        this.f3472b = str;
    }

    public void setSuccess(boolean z) {
        this.f3473c = z;
    }
}
