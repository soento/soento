package com.soento.cms.core.lang;

import com.soento.core.lang.BaseObject;
import com.soento.core.lang.Privilege;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author soento
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AuthModel extends BaseObject {
    private List<Privilege> privileges;
    private List<Role> roles;
    private List<Menu> menus;
    private List<String> auths;
}
