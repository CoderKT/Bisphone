package app.messaging.channel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChannelResponseModel {
    ChannelMetaModel f3531a;
    ArrayList<ChannelModel> f3532b;
    ArrayList<ChannelModel> f3533c;

    public ChannelMetaModel getMeta() {
        return this.f3531a;
    }

    public ArrayList<ChannelModel> getFeatured() {
        return this.f3532b;
    }

    public ArrayList<ChannelModel> getNormal() {
        return this.f3533c;
    }

    public void setMeta(ChannelMetaModel channelMetaModel) {
        this.f3531a = channelMetaModel;
    }

    public void setFeatured(ArrayList<ChannelModel> arrayList) {
        this.f3532b = arrayList;
    }

    public void setNormal(ArrayList<ChannelModel> arrayList) {
        this.f3533c = arrayList;
    }
}
