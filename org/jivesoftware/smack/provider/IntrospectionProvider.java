package org.jivesoftware.smack.provider;

import java.lang.reflect.InvocationTargetException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.util.ParserUtils;
import org.jivesoftware.smackx.rsm.packet.RSMSet;
import org.xmlpull.v1.XmlPullParser;
import se.emilsjolander.stickylistheaders.C1128R;

public class IntrospectionProvider {

    public abstract class IQIntrospectionProvider<I extends IQ> extends IQProvider<I> {
        private final Class<I> elementClass;

        protected IQIntrospectionProvider(Class<I> cls) {
            this.elementClass = cls;
        }

        public I parse(XmlPullParser xmlPullParser, int i) {
            Throwable e;
            try {
                return (IQ) IntrospectionProvider.parseWithIntrospection(this.elementClass, xmlPullParser, i);
            } catch (NoSuchMethodException e2) {
                e = e2;
                throw new SmackException(e);
            } catch (SecurityException e3) {
                e = e3;
                throw new SmackException(e);
            } catch (InstantiationException e4) {
                e = e4;
                throw new SmackException(e);
            } catch (IllegalAccessException e5) {
                e = e5;
                throw new SmackException(e);
            } catch (IllegalArgumentException e6) {
                e = e6;
                throw new SmackException(e);
            } catch (InvocationTargetException e7) {
                e = e7;
                throw new SmackException(e);
            } catch (ClassNotFoundException e8) {
                e = e8;
                throw new SmackException(e);
            }
        }
    }

    public abstract class PacketExtensionIntrospectionProvider<PE extends ExtensionElement> extends ExtensionElementProvider<PE> {
        private final Class<PE> elementClass;

        protected PacketExtensionIntrospectionProvider(Class<PE> cls) {
            this.elementClass = cls;
        }

        public PE parse(XmlPullParser xmlPullParser, int i) {
            Throwable e;
            try {
                return (ExtensionElement) IntrospectionProvider.parseWithIntrospection(this.elementClass, xmlPullParser, i);
            } catch (NoSuchMethodException e2) {
                e = e2;
                throw new SmackException(e);
            } catch (SecurityException e3) {
                e = e3;
                throw new SmackException(e);
            } catch (InstantiationException e4) {
                e = e4;
                throw new SmackException(e);
            } catch (IllegalAccessException e5) {
                e = e5;
                throw new SmackException(e);
            } catch (IllegalArgumentException e6) {
                e = e6;
                throw new SmackException(e);
            } catch (InvocationTargetException e7) {
                e = e7;
                throw new SmackException(e);
            } catch (ClassNotFoundException e8) {
                e = e8;
                throw new SmackException(e);
            }
        }
    }

    public static Object parseWithIntrospection(Class<?> cls, XmlPullParser xmlPullParser, int i) {
        ParserUtils.assertAtStartTag(xmlPullParser);
        Object newInstance = cls.newInstance();
        while (true) {
            switch (xmlPullParser.next()) {
                case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                    String name = xmlPullParser.getName();
                    String nextText = xmlPullParser.nextText();
                    Object decode = decode(newInstance.getClass().getMethod("get" + Character.toUpperCase(name.charAt(0)) + name.substring(1), new Class[0]).getReturnType(), nextText);
                    newInstance.getClass().getMethod(RSMSet.ELEMENT + Character.toUpperCase(name.charAt(0)) + name.substring(1), new Class[]{r3}).invoke(newInstance, new Object[]{decode});
                    break;
                case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                    if (xmlPullParser.getDepth() != i) {
                        break;
                    }
                    ParserUtils.assertAtEndTag(xmlPullParser);
                    return newInstance;
                default:
                    break;
            }
        }
    }

    private static Object decode(Class<?> cls, String str) {
        String name = cls.getName();
        Object obj = -1;
        switch (name.hashCode()) {
            case -1325958191:
                if (name.equals("double")) {
                    obj = 5;
                    break;
                }
                break;
            case -530663260:
                if (name.equals("java.lang.Class")) {
                    obj = 8;
                    break;
                }
                break;
            case 104431:
                if (name.equals("int")) {
                    obj = 2;
                    break;
                }
                break;
            case 3039496:
                if (name.equals("byte")) {
                    obj = 7;
                    break;
                }
                break;
            case 3327612:
                if (name.equals("long")) {
                    obj = 3;
                    break;
                }
                break;
            case 64711720:
                if (name.equals("boolean")) {
                    obj = 1;
                    break;
                }
                break;
            case 97526364:
                if (name.equals("float")) {
                    obj = 4;
                    break;
                }
                break;
            case 109413500:
                if (name.equals("short")) {
                    obj = 6;
                    break;
                }
                break;
            case 1195259493:
                if (name.equals("java.lang.String")) {
                    obj = null;
                    break;
                }
                break;
        }
        switch (obj) {
            case C1128R.styleable.StickyListHeadersListView_android_scrollbarStyle /*0*/:
                return str;
            case C1128R.styleable.StickyListHeadersListView_android_padding /*1*/:
                return Boolean.valueOf(str);
            case C1128R.styleable.StickyListHeadersListView_android_paddingLeft /*2*/:
                return Integer.valueOf(str);
            case C1128R.styleable.StickyListHeadersListView_android_paddingTop /*3*/:
                return Long.valueOf(str);
            case C1128R.styleable.StickyListHeadersListView_android_paddingRight /*4*/:
                return Float.valueOf(str);
            case C1128R.styleable.StickyListHeadersListView_android_paddingBottom /*5*/:
                return Double.valueOf(str);
            case C1128R.styleable.StickyListHeadersListView_android_scrollbars /*6*/:
                return Short.valueOf(str);
            case C1128R.styleable.StickyListHeadersListView_android_fadingEdgeLength /*7*/:
                return Byte.valueOf(str);
            case C1128R.styleable.StickyListHeadersListView_android_clipToPadding /*8*/:
                return Class.forName(str);
            default:
                return null;
        }
    }
}
