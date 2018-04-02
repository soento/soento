package com.soento.core.lang;

import com.soento.core.util.JsonUtil;
import com.soento.core.util.StringUtil;

import java.io.Serializable;

/**
 * @author soento
 */
public class BaseObject implements Serializable {

    @Override
    public String toString() {
        return StringUtil.toSimpleString(this);
    }

    public String toJson() {
        return JsonUtil.toJson(this);
    }

    public String toJsonp(String function) {
        return JsonUtil.toJsonp(function, this);
    }
}
