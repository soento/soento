package com.soento.core.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

/**
 * Spring对象操作
 *
 * @author soento
 */
@Slf4j
@Component
public class SpringHandler implements ApplicationContextAware, EmbeddedValueResolverAware {
    private final static String START = "${";
    private final static String END = "}";
    private final static String ACTIVE_PROFILE = "spring.profiles.default";
    private static ApplicationContext applicationContext;
    private static StringValueResolver resolver;

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        SpringHandler.resolver = resolver;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringHandler.applicationContext = applicationContext;
    }

    /**
     * 获取Spring上下文对象
     *
     * @return Spring上下文对象
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取Spring管理的对象
     *
     * @param clazz 对象类型
     * @return 结果对象
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * 获取Spring管理的对象
     *
     * @param name 对象名
     * @return 结果对象
     */
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    /**
     * 获取Spring管理的对象
     *
     * @param name  对象名
     * @param clazz 对象类型
     * @return 结果对象
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return applicationContext.getBean(name, clazz);
    }

    /**
     * 获取Spring管理的对象
     *
     * @param name 对象名
     * @param args 参数
     * @return 结果对象
     */
    public static Object getBean(String name, Object... args) {
        return applicationContext.getBean(name, args);
    }

    /**
     * 获取Spring管理的对象
     *
     * @param clazz 对象类型
     * @param args  参数
     * @return 结果对象
     */
    public static <T> T getBean(Class<T> clazz, Object... args) {
        return applicationContext.getBean(clazz, args);
    }

    /**
     * 获取Properties配置文件的内容
     *
     * @param name 键名
     * @return 键值
     */
    public static String getValue(String name) {
        if (name == null) {
            return null;
        }
        String key = null;
        if (name.startsWith(START) && name.endsWith(END)) {
            key = name;
        } else {
            key = START + name + END;
        }
        try {
            String value = resolver.resolveStringValue(key);
            if (!name.equals(value)) {
                return value;
            }
        } catch (IllegalArgumentException e) {
            log.warn(e.getMessage());
        }
        return null;
    }

    /**
     * 获取运行环境
     *
     * @return 运行环境
     */
    public static String getProfile() {
        return getValue(ACTIVE_PROFILE);
    }
}
