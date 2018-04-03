package com.soento.cms.core.support;

import com.soento.cms.core.lang.AuthModel;
import com.soento.cms.core.lang.Breadcrumb;
import com.soento.cms.core.lang.Menu;
import com.soento.cms.core.lang.Role;
import com.soento.core.config.SystemConfig;
import com.soento.core.consts.AuthType;
import com.soento.core.consts.Constants;
import com.soento.core.lang.BaseObject;
import com.soento.core.lang.LoginUser;
import com.soento.core.lang.Privilege;
import com.soento.core.support.AbstractController;
import com.soento.core.util.CollectionUtil;
import com.soento.core.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author soento
 */
public abstract class BaseController extends AbstractController {
    protected final static String PAGE_DOMAIN = "domain";
    protected final static String PAGE_NAME = "name";
    protected final static String PAGE_SCRIPT = "script";
    protected final static String PAGE_STYLE = "style";
    protected final static String PAGE_CTRL = "ctrl";
    protected final static String PAGE_MENUS = "menus";
    protected final static String PAGE_BREADCRUMBS = "breadcrumbs";
    protected final static String PAGE_USER = "user";
    protected final static String PAGE_AUTHS = "auths";
    protected final static String PAGE_DATA = "data";

    protected final static String VIEW_TEMPLATE = "template";
    protected final static String VIEW_PAGE = "page";

    @Autowired
    protected SystemConfig config;

    /**
     * 获取登录用户
     */
    protected LoginUser getUser() {
        HttpSession session = getSession();
        if (session != null) {
            return (LoginUser) session.getAttribute(Constants.LOGIN_USER_INFO);
        }
        return null;
    }

    protected void setUser(LoginUser user) {
        HttpSession session = getSession();
        if (session != null) {
            session.setAttribute(Constants.LOGIN_USER_INFO, user);
        }
    }

    protected List<Privilege> getPrivileges() {
        HttpSession session = getSession();
        if (session != null) {
            return (List<Privilege>) session.getAttribute(Constants.PRIVILEGE_LIST);
        }
        return null;
    }

    protected List<Role> getRoles() {
        HttpSession session = getSession();
        if (session != null) {
            return (List<Role>) session.getAttribute(Constants.ROLE_LIST);
        }
        return null;
    }

    protected List<Menu> getMenus() {
        HttpSession session = getSession();
        if (session != null) {
            return (List<Menu>) session.getAttribute(Constants.USER_MENU);
        }
        return null;
    }

    protected List<String> getAuths() {
        HttpSession session = getSession();
        if (session != null) {
            return (List<String>) session.getAttribute(Constants.USER_AUTH);
        }
        return null;
    }

    protected void setAuthModel(AuthModel auth) {
        HttpSession session = getSession();
        if (session != null) {
            session.setAttribute(Constants.PRIVILEGE_LIST, auth.getPrivileges());
            session.setAttribute(Constants.ROLE_LIST, auth.getRoles());
            session.setAttribute(Constants.USER_MENU, auth.getMenus());
            session.setAttribute(Constants.USER_AUTH, auth.getAuths());
        }
    }

    protected ModelAndView result(String view, String name, BaseObject data, Plugin plugin) {
        List<Privilege> privileges = getPrivileges();
        return builder()
                .view(view)
                .addObject(PAGE_DOMAIN, config.getDomain())
                .addObject(PAGE_NAME, name)
                .addObject(PAGE_SCRIPT, plugin.script)
                .addObject(PAGE_STYLE, plugin.style)
                .addObject(PAGE_CTRL, plugin.ctrl)
                .addObject(PAGE_MENUS, menus(name, privileges))
                .addObject(PAGE_BREADCRUMBS, breadcrumbs(name, privileges))
                .addObject(PAGE_USER, getUser())
                .addObject(PAGE_AUTHS, JsonUtil.toJson(getAuths()))
                .addObject(PAGE_DATA, data)
                .build();
    }

    private List<Menu> menus(String name, List<Privilege> privileges) {
        String menuName = getMenuName(name, privileges);
        List<Menu> menus = getMenus();
        initMenuActive(menus);
        Menu menu = getActiveMenu(menuName, menus);
        if (menu != null) {
            menu.setActive("active");
        }
        return menus;
    }

    private List<Breadcrumb> breadcrumbs(String name, List<Privilege> privileges) {
        List<Breadcrumb> breadcrumbs = new ArrayList<>();
        Privilege page = null;
        if (CollectionUtil.isNotEmpty(privileges)) {
            for (Privilege privilege : privileges) {
                if (name.equals(privilege.getName())) {
                    page = privilege;
                }
            }
        }
        if (page == null) {
            Breadcrumb home = new Breadcrumb();
            home.setIcon("fa fa-home home-icon");
            home.setLink("/index");
            home.setText("主页");
            breadcrumbs.add(home);
            return breadcrumbs;
        } else if (AuthType.MENU.equals(page.getType()) || AuthType.PAGE.equals(page.getType())) {
            Breadcrumb breadcrumb = new Breadcrumb();
            breadcrumb.setIcon(page.getIcon());
            breadcrumb.setLink(page.getUri());
            breadcrumb.setText(page.getText());
            breadcrumbs.add(breadcrumb);
        }
        breadcrumbs.addAll(breadcrumbs(page.getParent(), privileges));
        return breadcrumbs;
    }

    private String getMenuName(String name, List<Privilege> privileges) {
        Privilege menu = null;
        if (CollectionUtil.isNotEmpty(privileges)) {
            for (Privilege privilege : privileges) {
                if (name.equals(privilege.getName())) {
                    menu = privilege;
                }
            }
        }
        if (menu == null) {
            return Privilege.ROOT;
        } else if (AuthType.MENU.equals(menu.getType())) {
            return menu.getName();
        } else {
            return getMenuName(menu.getParent(), privileges);
        }
    }

    private Menu getActiveMenu(String name, List<Menu> menus) {
        Menu result = null;
        if (CollectionUtil.isNotEmpty(menus)) {
            for (Menu menu : menus) {
                if (name.equals(menu.getName())) {
                    result = menu;
                } else {
                    result = getActiveMenu(name, menu.getChildren());
                }
                if (result != null) {
                    menu.setActive("active open");
                    break;
                }
            }
        }
        return result;
    }

    private void initMenuActive(List<Menu> menus) {
        if (CollectionUtil.isNotEmpty(menus)) {
            for (Menu menu : menus) {
                menu.setActive("");
                initMenuActive(menu.getChildren());
            }
        }
    }

    protected ModelAndView template(String name, BaseObject data, Plugin plugin) {
        return result(VIEW_TEMPLATE, name, data, plugin);
    }

    protected ModelAndView template(String name, Plugin plugin) {
        return template(name, null, plugin);
    }

    protected ModelAndView template(String name, BaseObject data) {
        return template(name, data, plugin());
    }

    protected ModelAndView template(String name) {
        return template(name, null, plugin());
    }

    protected ModelAndView page(String name, BaseObject data, Plugin plugin) {
        return result(VIEW_PAGE, name, data, plugin);
    }

    protected ModelAndView page(String name, Plugin plugin) {
        return page(name, null, plugin);
    }

    protected ModelAndView page(String name, BaseObject data) {
        return page(name, data, plugin());
    }

    protected ModelAndView page(String name) {
        return page(name, null, plugin());
    }

    protected static BaseController.Plugin plugin() {
        return new BaseController.Plugin();
    }

    protected static class Plugin {
        private boolean script;
        private boolean style;
        private boolean ctrl;

        Plugin() {
            this.script = false;
            this.style = false;
            this.ctrl = false;
        }

        public BaseController.Plugin script(boolean script) {
            this.script = script;
            return this;
        }

        public BaseController.Plugin style(boolean style) {
            this.style = style;
            return this;
        }

        public BaseController.Plugin ctrl(boolean ctrl) {
            this.ctrl = ctrl;
            return this;
        }
    }
}
