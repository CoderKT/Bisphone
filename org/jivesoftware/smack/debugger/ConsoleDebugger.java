package org.jivesoftware.smack.debugger;

import java.io.Reader;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.jivesoftware.smack.XMPPConnection;

public class ConsoleDebugger extends AbstractDebugger {
    private final SimpleDateFormat dateFormatter;

    public ConsoleDebugger(XMPPConnection xMPPConnection, Writer writer, Reader reader) {
        super(xMPPConnection, writer, reader);
        this.dateFormatter = new SimpleDateFormat("hh:mm:ss aaa");
    }

    protected void log(String str) {
        String format;
        synchronized (this.dateFormatter) {
            format = this.dateFormatter.format(new Date());
        }
        System.out.println(format + ' ' + str);
    }
}
