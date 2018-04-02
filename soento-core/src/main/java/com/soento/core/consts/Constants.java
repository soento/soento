package com.soento.core.consts;

import com.soento.core.util.StringUtil;

/**
 * @author soento
 */
public class Constants {
    /**
     * 本地环境
     */
    public static final String PROFILE_LOCAL = "local";
    /**
     * 开发环境
     */
    public static final String PROFILE_DEV = "dev";
    /**
     * 测试环境
     */
    public static final String PROFILE_SIT = "sit";
    /**
     * 验收环境
     */
    public static final String PROFILE_UAT = "uat";
    /**
     * 生产环境
     */
    public static final String PROFILE_PRO = "pro";
    /**
     * 登录用户信息key
     */
    public static final String LOGIN_USER_INFO = "loginUserInfo";
    /**
     * 用户权限列表
     */
    public static final String PRIVILEGE_LIST = "loginUserPrivilegeList";
    /**
     * 用户角色列表
     */
    public static final String ROLE_LIST = "loginUserRoleList";
    /**
     * 用户权限
     */
    public static final String USER_AUTH = "loginUserAuth";
    /**
     * 用户菜单
     */
    public static final String USER_MENU = "loginUserMenus";
    public static final String EMPTY = StringUtil.EMPTY;
    public static final String SAPCE = StringUtil.SPACE;
    public static final String COMMA = ",";
    public static final String DOT = ".";
    public static final String DOUBLE_DOT = "..";
    public static final String REGEX_DOT = "\\.";
    public static final String COLON = ":";
    public static final String PLUS = "+";
    public static final String MINUS = "-";
    public static final String QUESTION = "?";
    public static final String SLASH = "/";
    public static final String BACK_SLASH = "\\";
    public static final String TAB = "\t";
    public static final String LF = StringUtil.LF;
    public static final String CR = StringUtil.CR;
    public static final String CRLF = "\r\n";
    public static final String UNDERLINE = "_";
    public static final String DERLINE = "-";

    public final static String ALGORITHM_PBE = "PBE";
    public final static String ALGORITHM_DES = "DES";

    public final static Integer DELETED = 0;
    public final static Integer UNDELETED = 1;
    public final static String SYSTEM = "SYSTEM";

    public final static String AMPERSAND = "&";
    public final static String AND = "and";
    public final static String AT = "@";
    public final static String ASTERISK = "*";
    public final static String STAR = ASTERISK;
    public final static String DASH = "-";
    public final static String DOLLAR = "$";
    public final static String DOTDOT = "..";
    public final static String DOT_CLASS = ".class";
    public final static String DOT_JAVA = ".java";
    public final static String EQUALS = "=";
    public final static String FALSE = "false";
    public final static String HASH = "#";
    public final static String HAT = "^";
    public final static String LEFT_BRACE = "{";
    public final static String LEFT_BRACKET = "(";
    public final static String LEFT_CHEV = "<";
    public final static String NEWLINE = "\n";
    public final static String N = "n";
    public final static String NO = "no";
    public final static String NULL = "null";
    public final static String OFF = "off";
    public final static String ON = "on";
    public final static String PERCENT = "%";
    public final static String PIPE = "|";
    public final static String QUESTION_MARK = "?";
    public final static String EXCLAMATION_MARK = "!";
    public final static String QUOTE = "\"";
    public final static String RETURN = "\r";
    public final static String RIGHT_BRACE = "}";
    public final static String RIGHT_BRACKET = ")";
    public final static String RIGHT_CHEV = ">";
    public final static String SEMICOLON = ";";
    public final static String SINGLE_QUOTE = "'";
    public final static String BACKTICK = "`";
    public final static String SPACE = " ";
    public final static String TILDA = "~";
    public final static String LEFT_SQ_BRACKET = "[";
    public final static String RIGHT_SQ_BRACKET = "]";
    public final static String TRUE = "true";
    public final static String UNDERSCORE = "_";
    public final static String UTF_8 = "UTF-8";
    public final static String US_ASCII = "US-ASCII";
    public final static String ISO_8859_1 = "ISO-8859-1";
    public final static String Y = "y";
    public final static String YES = "yes";
    public final static String ONE = "1";
    public final static String ZERO = "0";
    public final static String DOLLAR_LEFT_BRACE = "${";

    public final static String HTML_NBSP = "&nbsp;";
    public final static String HTML_AMP = "&amp";
    public final static String HTML_QUOTE = "&quot;";
    public final static String HTML_LT = "&lt;";
    public final static String HTML_GT = "&gt;";

    public final static byte[] BYTES_NEW_LINE = "\n".getBytes();


    public static final Character CHAR_OX01 = 0x01;
    public static final Character CHAR_0 = '0';
    public static final Character CHAR_9 = '9';
    public static final Character CHAR_A = 'A';
    public static final Character CHAR_F = 'F';
    public static final Character CHAR_A_LOW = 'a';
    public static final Character CHAR_F_LOW = 'f';

    public static final Integer INT_2 = 2;
}
