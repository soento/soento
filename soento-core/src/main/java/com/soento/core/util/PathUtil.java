package com.soento.core.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author soento
 */
public class PathUtil {
    private static final String TIME = "time";
    private static final String FULL_YEAR = "yyyy";
    private static final String YEAR = "yy";
    private static final String MONTH = "mm";
    private static final String DAY = "dd";
    private static final String HOUR = "hh";
    private static final String MINUTE = "ii";
    private static final String SECOND = "ss";
    private static final String RAND = "rand";
    private static final String PATH_FILE = "file:/";
    private static final Pattern PATH_PATTERN = Pattern.compile("\\{([^\\}]+)\\}", Pattern.CASE_INSENSITIVE);

    public static String classpath() {
        URL classPathUrl = Thread.currentThread().getContextClassLoader().getResource("");
        String classpath = classPathUrl.toString();
        if (classpath.startsWith(PATH_FILE)) {
            classpath = classpath.substring(6);
        }
        return classpath;
    }

    public static String concat(String... paths) {
        StringBuilder sb = new StringBuilder();
        String tmp;
        for (String path : paths) {
            tmp = path;
            if (tmp.startsWith("/")) {
                tmp = tmp.substring(1);
            }
            if (!tmp.endsWith("/")) {
                tmp += "/";
            }
            sb.append(tmp);
        }
        return sb.toString();
    }

    public static String parse(String input) {
        Matcher matcher = PATH_PATTERN.matcher(input);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, getString(matcher.group(1), new Date()));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 格式化路径, 把windows路径替换成标准路径
     *
     * @param input 待格式化的路径
     * @return 格式化后的路径
     */
    public static String format(String input) {
        return input.replace("\\", "/");
    }

    public static String parse(String input, String filename) {
        Matcher matcher = PATH_PATTERN.matcher(input);
        String matchStr = null;

        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matchStr = matcher.group(1);
            if (matchStr.indexOf("filename") != -1) {
                filename = filename.replace("$", "\\$").replaceAll("[\\/:*?\"<>|]", "");
                matcher.appendReplacement(sb, filename);
            } else {
                matcher.appendReplacement(sb, getString(matchStr, new Date()));
            }
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    private static String getString(String pattern, Date date) {
        pattern = pattern.toLowerCase();
        // time 处理
        if (pattern.indexOf(TIME) != -1) {
            return getTimestamp();
        } else if (pattern.indexOf(FULL_YEAR) != -1) {
            return getFullYear(date);
        } else if (pattern.indexOf(YEAR) != -1) {
            return getYear(date);
        } else if (pattern.indexOf(MONTH) != -1) {
            return getMonth(date);
        } else if (pattern.indexOf(DAY) != -1) {
            return getDay(date);
        } else if (pattern.indexOf(HOUR) != -1) {
            return getHour(date);
        } else if (pattern.indexOf(MINUTE) != -1) {
            return getMinute(date);
        } else if (pattern.indexOf(SECOND) != -1) {
            return getSecond(date);
        } else if (pattern.indexOf(RAND) != -1) {
            return getRandom(pattern);
        }

        return pattern;

    }

    private static String getTimestamp() {
        return System.currentTimeMillis() + "";
    }

    private static String getFullYear(Date date) {
        return new SimpleDateFormat("yyyy").format(date);
    }

    private static String getYear(Date date) {
        return new SimpleDateFormat("yy").format(date);
    }

    private static String getMonth(Date date) {
        return new SimpleDateFormat("MM").format(date);
    }

    private static String getDay(Date date) {
        return new SimpleDateFormat("dd").format(date);
    }

    private static String getHour(Date date) {
        return new SimpleDateFormat("HH").format(date);
    }

    private static String getMinute(Date date) {
        return new SimpleDateFormat("mm").format(date);
    }

    private static String getSecond(Date date) {
        return new SimpleDateFormat("ss").format(date);
    }

    private static String getRandom(String pattern) {
        pattern = pattern.split(":")[1].trim();
        int length = Integer.parseInt(pattern);
        return (Math.random() + "").replace(".", "").substring(0, length);
    }

    public static Path resolve(Path base, String child) {
        if (StringUtil.startsWithChar(child, File.separatorChar)) {
            child = child.substring(1);
        }
        return base.resolve(child);
    }

    public static Path resolve(Path path, String... childs) {
        for (String child : childs) {
            path = resolve(path, child);
        }
        return path;
    }

    public static String readString(Path path) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            // flush & close not needed for StringWriter-instance
            StringWriter writer = new StringWriter();
            StreamUtil.copy(reader, writer);
            return writer.toString();
        }
    }

    public static void deleteFileTree(Path directory) throws IOException {
        Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
