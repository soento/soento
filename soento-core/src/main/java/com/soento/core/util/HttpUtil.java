package com.soento.core.util;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

/**
 * @author yantao.zeng
 */
public class HttpUtil {

    public static boolean isAjax(HttpServletRequest request) {
        String pattern = ".*application/json.*";

        String accept = request.getHeader("Accept");
        if (Pattern.compile(pattern).matcher(accept).matches()) {
            return true;
        }
        String contentType = request.getHeader("Content-Type");
        if (Pattern.compile(pattern).matcher(contentType).matches()) {
            return true;
        }
        String requested = request.getHeader("x-requested-with");
        if ("XMLHttpRequest".equals(requested)) {
            return true;
        }
        return false;
    }
}
