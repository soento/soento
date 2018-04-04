package com.soento.core.lang;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author soento
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LoginUser extends BaseObject {
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 用户Id
     */
    private String userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户昵称
     */
    private String nickName;

}
