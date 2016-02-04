package app.xmpp.packet.groupv2;

public class GroupStatisticAckXE extends AbstractXE {
    private int f5153a;
    private int f5154b;
    private int f5155c;
    private int f5156d;
    private int f5157e;
    private int f5158f;

    public int m7619a() {
        return this.f5153a;
    }

    public int m7621b() {
        return this.f5156d;
    }

    public int m7623c() {
        return this.f5157e;
    }

    public int m7625d() {
        return this.f5158f;
    }

    public void m7620a(int i) {
        this.f5153a = i;
    }

    public void m7622b(int i) {
        this.f5154b = i;
    }

    public void m7624c(int i) {
        this.f5155c = i;
    }

    public void m7626d(int i) {
        this.f5156d = i;
    }

    public void m7627e(int i) {
        this.f5157e = i;
    }

    public void m7628f(int i) {
        this.f5158f = i;
    }

    public CharSequence toXML() {
        CharSequence stringBuilder = new StringBuilder();
        stringBuilder.append("<").append(getElementName()).append(" ").append("xmlns").append("='").append("bpn:group:v2").append("'>");
        stringBuilder.append("<").append("jc").append(">").append(this.f5153a).append("</").append("jc").append(">");
        stringBuilder.append("<").append("kc").append(">").append(this.f5154b).append("</").append("kc").append(">");
        stringBuilder.append("<").append("lc").append(">").append(this.f5155c).append("</").append("lc").append(">");
        stringBuilder.append("<").append("ic").append(">").append(this.f5156d).append("</").append("ic").append(">");
        stringBuilder.append("<").append("mi").append(">").append(this.f5157e).append("</").append("mi").append(">");
        stringBuilder.append("<").append("ml").append(">").append(this.f5158f).append("</").append("ml").append(">");
        stringBuilder.append("</").append("group-statistics-ack").append(">");
        return stringBuilder;
    }

    public String getElementName() {
        return "group-statistics-ack";
    }
}
