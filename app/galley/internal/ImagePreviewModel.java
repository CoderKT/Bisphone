package app.galley.internal;

import app.galley.MessagePreview;
import java.util.ArrayList;

public class ImagePreviewModel {
    private int f2701a;
    private ArrayList<MessagePreview> f2702b;

    public ImagePreviewModel() {
        this.f2702b = new ArrayList();
    }

    public int m5138a() {
        return this.f2701a;
    }

    public void m5139a(int i) {
        this.f2701a = i;
    }

    public ArrayList<MessagePreview> m5141b() {
        return this.f2702b;
    }

    public void m5140a(ArrayList<MessagePreview> arrayList) {
        this.f2702b = arrayList;
    }
}
