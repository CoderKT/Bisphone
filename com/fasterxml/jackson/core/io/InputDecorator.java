package com.fasterxml.jackson.core.io;

import java.io.Reader;
import java.io.Serializable;

public abstract class InputDecorator implements Serializable {
    public abstract Reader decorate(IOContext iOContext, Reader reader);
}
