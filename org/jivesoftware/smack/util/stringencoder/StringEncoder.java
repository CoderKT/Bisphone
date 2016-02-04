package org.jivesoftware.smack.util.stringencoder;

public interface StringEncoder {
    String decode(String str);

    String encode(String str);
}
