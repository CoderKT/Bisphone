package app.common.collection;

public class ObservableCollectionItem {
    protected ObjectObserver f2017a;

    public interface ObjectObserver {
        void m4055a();
    }

    public void m4057a(ObjectObserver objectObserver) {
        this.f2017a = objectObserver;
    }

    protected void m4056a() {
        if (this.f2017a != null) {
            this.f2017a.m4055a();
        }
    }
}
