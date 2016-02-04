package cz.msebera.android.httpclient.message;

public class ParserCursor {
    private final int f7902a;
    private final int f7903b;
    private int f7904c;

    public ParserCursor(int i, int i2) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("Lower bound cannot be negative");
        } else if (i > i2) {
            throw new IndexOutOfBoundsException("Lower bound cannot be greater then upper bound");
        } else {
            this.f7902a = i;
            this.f7903b = i2;
            this.f7904c = i;
        }
    }

    public int m12661a() {
        return this.f7903b;
    }

    public int m12663b() {
        return this.f7904c;
    }

    public void m12662a(int i) {
        if (i < this.f7902a) {
            throw new IndexOutOfBoundsException("pos: " + i + " < lowerBound: " + this.f7902a);
        } else if (i > this.f7903b) {
            throw new IndexOutOfBoundsException("pos: " + i + " > upperBound: " + this.f7903b);
        } else {
            this.f7904c = i;
        }
    }

    public boolean m12664c() {
        return this.f7904c >= this.f7903b;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        stringBuilder.append(Integer.toString(this.f7902a));
        stringBuilder.append('>');
        stringBuilder.append(Integer.toString(this.f7904c));
        stringBuilder.append('>');
        stringBuilder.append(Integer.toString(this.f7903b));
        stringBuilder.append(']');
        return stringBuilder.toString();
    }
}
