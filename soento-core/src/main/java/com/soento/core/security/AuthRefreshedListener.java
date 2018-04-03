package com.soento.core.security;

import com.soento.core.annotation.Auth;
import com.soento.core.consts.Constants;
import com.soento.core.lang.Privilege;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author soento
 */
@Slf4j
@Component
public class AuthRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {
    private final static String CGLIB = "$$EnhancerBySpringCGLIB$$";
    private final static String PACKAGE_STRING = "org.springframework";
    @Autowired
    private AuthConfig authConfig;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            ApplicationContext context = event.getApplicationContext();
            Map<String, Object> ctrlBeans = context.getBeansWithAnnotation(Controller.class);
            Iterator<String> it = ctrlBeans.keySet().iterator();
            while (it.hasNext()) {
                readPrivilege(ctrlBeans.get(it.next()).getClass());
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void readPrivilege(Class clazz) throws ClassNotFoundException {
        if (clazz.getName().startsWith(PACKAGE_STRING)) {
            return;
        }
        if (clazz.getName().contains(CGLIB)) {
            clazz = Class.forName(clazz.getName().substring(0, clazz.getName().indexOf(CGLIB)));
        }
        log.debug(">>>>>> Class: {}", clazz.getName());
        RequestMapping clazzMapping = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
        String root = "";
        if (clazzMapping != null) {
            root = convertPath(clazzMapping.value()[0]);
        }
        Method[] ms = clazz.getDeclaredMethods();
        Privilege privilege;
        String uri;
        String name;
        String path;
        RequestMapping methodMapping;
        Auth auth;
        String[] methodValues;
        for (Method m : ms) {
            methodMapping = m.getDeclaredAnnotation(RequestMapping.class);
            if (methodMapping != null) {
                methodValues = methodMapping.value();
                if (methodValues == null || methodValues.length < 1) {
                    continue;
                }
                path = convertPath(methodValues[0]);
                uri = root + path;
                auth = m.getAnnotation(Auth.class);
                if (auth != null) {
                    privilege = new Privilege();
                    privilege.setUri(uri);
                    name = convertName(uri);
                    privilege.setName(name);
                    if (StringUtils.isBlank(auth.text())) {
                        privilege.setText(name);
                    } else {
                        privilege.setText(auth.text());
                    }
                    privilege.setParent(auth.parent());
                    privilege.setType(auth.type());
                    privilege.setIcon(auth.icon());
                    privilege.setSort(auth.sort());
                    if (!contains(authConfig.getAuthList(), uri)) {
                        authConfig.getAuthList().add(privilege);
                    }
                    log.debug("------ Method: {}, uri: {}, name: {}, Auth", m.getName(), uri, name);
                } else {
                    if (!authConfig.getWhitelist().contains(uri)) {
                        authConfig.getWhitelist().add(uri);
                    }
                    log.debug("------ Method: {}, uri: {}, Whitelist", m.getName(), uri);
                }
            } else {
                log.debug("------ Method: {}, Not RequestMapping", m.getName());
            }
        }
        log.debug("<<<<<<");
    }

    private String convertPath(String path) {
        String result = path;
        if (!result.startsWith(Constants.SLASH)) {
            result = Constants.SLASH + result;
        }
        if (result.endsWith(Constants.SLASH)) {
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }

    private boolean contains(List<Privilege> list, String content) {
        for (Privilege tmp : list) {
            if (StringUtils.equals(tmp.getUri(), content)) {
                return true;
            }
        }
        return false;
    }

    private String convertName(String path) {
        StringBuilder result = new StringBuilder();
        for (String p : path.split(Constants.SLASH)) {
            if (StringUtils.isNoneBlank(p)) {
                result.append(p.substring(0, 1).toUpperCase());
                result.append(p.substring(1));
            }
        }
        return result.toString();
    }
}
