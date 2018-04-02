package com.soento.core.util;

import com.soento.core.enums.Charset;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.helpers.MessageFormatter;

import java.io.UnsupportedEncodingException;

/**
 * @author soento
 */
public final class StringUtil extends StringUtils {
    public static final ToStringStyle THE_STYLE = new SimpleMultiLineToStringStyle();

    /**
     * 用于产生去掉空值属性并以换行符分割各属性键值的toString字符串
     *
     * @param obj
     */
    public static String toSimpleString(Object obj) {
        String toStringResult = ToStringBuilder.reflectionToString(obj, THE_STYLE);
        String[] split = toStringResult.split(SimpleMultiLineToStringStyle.LINE_SEPARATOR);
        StringBuilder result = new StringBuilder();
        for (String string : split) {
            if (string.endsWith(SimpleMultiLineToStringStyle.NULL_TEXT)) {
                continue;
            }
            result.append(string + SimpleMultiLineToStringStyle.LINE_SEPARATOR);
        }
        if (result.length() == 0) {
            return "";
        }
        //如果没有非空的属性，就输出 <all null properties>
        if (StringUtils.countMatches(result, SimpleMultiLineToStringStyle.LINE_SEPARATOR) == 2) {
            return result.toString().split(SimpleMultiLineToStringStyle.LINE_SEPARATOR)[0] + "<all null values>]";
        }
        return result.deleteCharAt(result.length() - 1).toString();
    }

    /**
     * unicode转码
     */
    public static String unicode(String input) {
        StringBuilder builder = new StringBuilder();
        char[] chars = input.toCharArray();
        for (char ch : chars) {
            if (ch < 256) {
                builder.append(ch);
            } else {
                builder.append("\\u" + Integer.toHexString(ch & 0xffff));
            }
        }
        return builder.toString();
    }

    /**
     * 去除左边的空格
     */
    public static String leftTrim(String content) {
        if (isNotEmpty(content)) {
            return content;
        }
        return content.replaceAll("^[　 ]+", "");
    }

    /**
     * 去除右边的空格
     */
    public static String rightTrim(String content) {
        if (isNotEmpty(content)) {
            return content;
        }
        return content.replaceAll("[　 ]+$", "");
    }

    /**
     * 格式化文本, {} 表示占位符<br>
     * 此方法只是简单将占位符 {} 按照顺序替换为参数<br>
     * 如果想输出 {} 使用 \\转义 { 即可，如果想输出 {} 之前的 \ 使用双转义符 \\\\ 即可<br>
     * 例：<br>
     * 通常使用：format("this is {} for {}", "a", "b") =》 this is a for b<br>
     * 转义{}： format("this is \\{} for {}", "a", "b") =》 this is \{} for a<br>
     * 转义\： format("this is \\\\{} for {}", "a", "b") =》 this is \a for b<br>
     *
     * @param template 文本模板，被替换的部分用 {} 表示
     * @param params   参数值
     * @return 格式化后的文本
     */
    public static String format(CharSequence template, Object... params) {
        return MessageFormatter.arrayFormat(new StringBuffer().append(template).toString(), params).getMessage();
    }

    /**
     * 编码字符串<br>
     * 使用系统默认编码
     *
     * @param str 字符串
     * @return 编码后的字节码
     */
    public static byte[] bytes(CharSequence str) {
        return bytes(str, Charset.UTF8);
    }

    /**
     * 编码字符串
     *
     * @param str     字符串
     * @param charset 字符集，如果此字段为空，则解码的结果取决于平台
     * @return 编码后的字节码
     */
    public static byte[] bytes(CharSequence str, String charset) {
        return bytes(str, isBlank(charset) ? Charset.UTF8 : Charset.parse(charset));
    }

    /**
     * 编码字符串
     *
     * @param str     字符串
     * @param charset 字符集，如果此字段为空，则解码的结果取决于平台
     * @return 编码后的字节码
     */
    public static byte[] bytes(CharSequence str, Charset charset) {
        try {
            if (str == null) {
                return null;
            }
            if (null == charset) {
                return str.toString().getBytes(Charset.UTF8.value());
            }
            return str.toString().getBytes(charset.value());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean startsWithChar(String s, char c) {
        if (s.length() == 0) {
            return false;
        }
        return s.charAt(0) == c;
    }

    private static class SimpleMultiLineToStringStyle extends ToStringStyle {
        private static final String LINE_SEPARATOR = "\n";
        private static final String NULL_TEXT = "<null>";

        public SimpleMultiLineToStringStyle() {
            super();
            this.setContentStart("[");
            this.setFieldSeparator(LINE_SEPARATOR + "  ");
            this.setFieldSeparatorAtStart(true);
            this.setContentEnd(LINE_SEPARATOR + "]");
            this.setNullText(NULL_TEXT);
            this.setUseShortClassName(true);
            this.setUseIdentityHashCode(false);
        }
    }
}
