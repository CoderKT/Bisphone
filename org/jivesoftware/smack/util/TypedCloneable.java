package org.jivesoftware.smack.util;

public interface TypedCloneable<T> extends Cloneable {
    T clone();
}
