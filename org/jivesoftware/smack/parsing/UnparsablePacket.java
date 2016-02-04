package org.jivesoftware.smack.parsing;

public class UnparsablePacket {
    private final CharSequence content;
    private final Exception f8577e;

    public UnparsablePacket(CharSequence charSequence, Exception exception) {
        this.content = charSequence;
        this.f8577e = exception;
    }

    public Exception getParsingException() {
        return this.f8577e;
    }

    public CharSequence getContent() {
        return this.content;
    }
}
