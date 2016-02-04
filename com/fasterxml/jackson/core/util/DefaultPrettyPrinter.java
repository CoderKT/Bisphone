package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.SerializedString;
import java.io.Serializable;
import java.util.Arrays;

public class DefaultPrettyPrinter implements PrettyPrinter, Serializable {
    public static final SerializedString DEFAULT_ROOT_VALUE_SEPARATOR;
    protected Indenter _arrayIndenter;
    protected transient int _nesting;
    protected Indenter _objectIndenter;
    protected final SerializableString _rootSeparator;
    protected boolean _spacesInObjectEntries;

    public interface Indenter {
    }

    public class NopIndenter implements Indenter, Serializable {
        public static final NopIndenter instance;

        static {
            instance = new NopIndenter();
        }
    }

    public class FixedSpaceIndenter extends NopIndenter {
        public static final FixedSpaceIndenter instance;

        static {
            instance = new FixedSpaceIndenter();
        }
    }

    public class Lf2SpacesIndenter extends NopIndenter {
        static final char[] SPACES;
        private static final String SYS_LF;
        public static final Lf2SpacesIndenter instance;
        protected final String _lf;

        static {
            String str = null;
            try {
                str = System.getProperty("line.separator");
            } catch (Throwable th) {
            }
            if (str == null) {
                str = "\n";
            }
            SYS_LF = str;
            SPACES = new char[64];
            Arrays.fill(SPACES, ' ');
            instance = new Lf2SpacesIndenter();
        }

        public Lf2SpacesIndenter() {
            this(SYS_LF);
        }

        public Lf2SpacesIndenter(String str) {
            this._lf = str;
        }
    }

    static {
        DEFAULT_ROOT_VALUE_SEPARATOR = new SerializedString(" ");
    }

    public DefaultPrettyPrinter() {
        this(DEFAULT_ROOT_VALUE_SEPARATOR);
    }

    public DefaultPrettyPrinter(SerializableString serializableString) {
        this._arrayIndenter = FixedSpaceIndenter.instance;
        this._objectIndenter = Lf2SpacesIndenter.instance;
        this._spacesInObjectEntries = true;
        this._nesting = 0;
        this._rootSeparator = serializableString;
    }
}
