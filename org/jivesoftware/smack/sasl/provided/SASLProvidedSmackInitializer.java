package org.jivesoftware.smack.sasl.provided;

import java.util.List;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.initializer.SmackInitializer;

public class SASLProvidedSmackInitializer implements SmackInitializer {
    public List<Exception> initialize() {
        SASLAuthentication.registerSASLMechanism(new SASLDigestMD5Mechanism());
        SASLAuthentication.registerSASLMechanism(new SASLExternalMechanism());
        SASLAuthentication.registerSASLMechanism(new SASLPlainMechanism());
        return null;
    }
}
