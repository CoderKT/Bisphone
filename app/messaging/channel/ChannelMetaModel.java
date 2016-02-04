package app.messaging.channel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChannelMetaModel {
    int f3503a;
    int f3504b;
    int f3505c;
    String f3506d;
    String f3507e;
    String f3508f;
    String f3509g;

    public void setTotal(int i) {
        this.f3503a = i;
    }

    public void setSkipped(int i) {
        this.f3504b = i;
    }

    public void setLimit(int i) {
        this.f3505c = i;
    }

    public void setStmt(String str) {
        this.f3506d = str;
    }

    public void setLang(String str) {
        this.f3507e = str;
    }

    public void setSort(String str) {
        this.f3508f = str;
    }

    public void setOrder(String str) {
        this.f3509g = str;
    }

    public int getTotal() {
        return this.f3503a;
    }

    public int getSkipped() {
        return this.f3504b;
    }

    public int getLimit() {
        return this.f3505c;
    }

    public String getStmt() {
        return this.f3506d;
    }

    public String getLang() {
        return this.f3507e;
    }

    public String getSort() {
        return this.f3508f;
    }

    public String getOrder() {
        return this.f3509g;
    }
}
