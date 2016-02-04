package org.jivesoftware.smack.debugger;

import java.io.Reader;
import java.io.Writer;
import org.jivesoftware.smack.StanzaListener;

public interface SmackDebugger {
    Reader getReader();

    StanzaListener getReaderListener();

    Writer getWriter();

    StanzaListener getWriterListener();

    Reader newConnectionReader(Reader reader);

    Writer newConnectionWriter(Writer writer);

    void userHasLogged(String str);
}
