package app.messaging.channel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChannelCategoryModel {
    String f3449a;
    String f3450b;
    String f3451c;
    String f3452d;
    int f3453e;

    public String getTitle() {
        return this.f3449a;
    }

    public String getId() {
        return this.f3450b;
    }

    public String getIcon() {
        return this.f3451c;
    }

    public String getLang() {
        return this.f3452d;
    }

    public int getChannels() {
        return this.f3453e;
    }

    public void setTitle(String str) {
        this.f3449a = str;
    }

    public void setId(String str) {
        this.f3450b = str;
    }

    public void setIcon(String str) {
        this.f3451c = str;
    }

    public void setLang(String str) {
        this.f3452d = str;
    }

    public void setChannels(int i) {
        this.f3453e = i;
    }
}
