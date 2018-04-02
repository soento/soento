package com.soento.core.enums;

import com.soento.core.consts.Constants;

/**
 * @author soento
 */
public enum Profile {
    /**
     * 本地环境
     */
    LOCAL("local"),
    /**
     * 开发环境
     */
    DEV("dev"),
    /**
     * 测试环境
     */
    SIT("sit"),
    /**
     * 验收环境
     */
    UAT("uat"),
    /**
     * 生产环境
     */
    PRO("pro");
    private String value;

    Profile(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Profile parse(String profile) {
        if (Constants.PROFILE_LOCAL.equals(profile)) {
            return LOCAL;
        }
        if (Constants.PROFILE_DEV.equals(profile)) {
            return DEV;
        }
        if (Constants.PROFILE_SIT.equals(profile)) {
            return SIT;
        }
        if (Constants.PROFILE_UAT.equals(profile)) {
            return UAT;
        }
        if (Constants.PROFILE_PRO.equals(profile)) {
            return PRO;
        }
        return PRO;
    }
}
