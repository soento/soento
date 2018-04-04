package com.soento.cms.system.service.impl;

import com.soento.cms.system.pojo.dto.LoginDto;
import com.soento.cms.system.service.SystemService;
import com.soento.cms.core.lang.AuthModel;
import com.soento.cms.core.lang.Menu;
import com.soento.cms.core.lang.Role;
import com.soento.cms.core.support.BaseService;
import com.soento.core.lang.LoginUser;
import com.soento.core.lang.Privilege;
import com.soento.core.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yantao.zeng
 */
@Slf4j
@Service
public class SystemServiceImpl extends BaseService implements SystemService {
    @Override
    public LoginUser doLogin(LoginDto req) {
        // TODO
        LoginUser login = new LoginUser();
        login.setUserId("id");
        login.setUserName("user_name");
        login.setNickName("nick_name");
        login.setAvatar("avatar");
        return login;
    }

    @Override
    public AuthModel getUserAuth(LoginUser user) {
        AuthModel result = new AuthModel();
        List<Role> roles = new ArrayList<>();
        log.debug("用户角色列表：{}", JsonUtil.toJson(roles));
        result.setRoles(roles);
        List<Privilege> privileges = new ArrayList<>();
        log.debug("用户权限列表：{}", JsonUtil.toJson(privileges));
        result.setPrivileges(privileges);
        List<Menu> meuns = new ArrayList<>();
        Menu menu = new Menu();
        menu.setLink("/tools");
        menu.setText("开发工具");
        menu.setName("Tools");
        List<Menu> subMenus = new ArrayList<>();
        Menu sub = new Menu();
        sub.setLink("/tools/codeGenerator");
        sub.setText("代码生成");
        sub.setName("ToolsCodeGenerator");
        subMenus.add(sub);
        menu.setChildren(subMenus);
        meuns.add(menu);
        log.debug("用户菜单列表：{}", JsonUtil.toJson(meuns));
        result.setMenus(meuns);
        List<String> auths = new ArrayList<>();
        for (Privilege privilege : privileges) {
            auths.add(privilege.getName());
        }
        log.debug("用户权限：{}", JsonUtil.toJson(auths));
        result.setAuths(auths);
        return result;
    }
}
