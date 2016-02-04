package org.jivesoftware.smackx.time.provider;

import org.jivesoftware.smack.provider.IntrospectionProvider.IQIntrospectionProvider;
import org.jivesoftware.smackx.time.packet.Time;

public class TimeProvider extends IQIntrospectionProvider<Time> {
    public TimeProvider() {
        super(Time.class);
    }
}
