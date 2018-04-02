package com.soento.core.enums;

/**
 * 编码格式
 *
 * @author soento
 */
public enum Charset {
    /**
     * ISO-8859-1
     */
    ISO88591("ISO-8859-1"),
    /**
     * UTF-8
     */
    UTF_8("UTF-8"),
    /**
     * UTF8
     */
    UTF8("UTF8"),
    /**
     * GBK
     */
    GBK("GBK");

    private final String value;

    Charset(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public java.nio.charset.Charset instance() {
        return java.nio.charset.Charset.forName(value);
    }

    public Charset defaultCharset() {
        return Charset.UTF8;
    }

    public static Charset parse(String charset) {
        if (Charset.ISO88591.value().equalsIgnoreCase(charset)
                || "ISO8859-1".equalsIgnoreCase(charset)
                || "ISO88591".equalsIgnoreCase(charset)) {
            return Charset.ISO88591;
        }
        if (Charset.UTF8.value().equalsIgnoreCase(charset) || Charset.UTF_8.value.equalsIgnoreCase(charset)) {
            return Charset.UTF_8;
        }
        if (Charset.GBK.value().equalsIgnoreCase(charset)) {
            return Charset.GBK;
        }
        throw new RuntimeException("cannot find charset " + charset);
    }
}
