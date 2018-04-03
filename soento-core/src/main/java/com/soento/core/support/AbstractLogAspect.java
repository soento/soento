package com.soento.core.support;

import com.soento.core.lang.BaseObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author soento
 */
@Slf4j
public abstract class AbstractLogAspect {
    protected Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        logStart(getClass(joinPoint).getSimpleName(), getMethod(joinPoint).getName());
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            logRequest(args[i], i);
        }
        Long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        Long end = System.currentTimeMillis();
        if (result != null) {
            logResponse(result);
        }
        logEnd(getClass(joinPoint).getSimpleName(), getMethod(joinPoint).getName(), end - start);
        return result;
    }

    protected Class getClass(ProceedingJoinPoint joinPoint) {
        return joinPoint.getTarget().getClass();
    }

    protected Method getMethod(ProceedingJoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        return methodSignature.getMethod();
    }

    protected <T extends Annotation> T getAnnotation(ProceedingJoinPoint joinPoint, Class<T> clazz) {
        return getMethod(joinPoint).getAnnotation(clazz);
    }

    protected void logStart(String className, String methodName) {
        log.info("→→→→→→→→→ {}.{}() 【{}】 →→→→→→→→→", className, methodName, "Start");
    }

    protected void logEnd(String className, String methodName) {
        log.info("←←←←←←←←← {}.{}() 【{}】 ←←←←←←←←←←", className, methodName, "End");
    }

    protected void logEnd(String className, String methodName, long time) {
        log.info("→→→→→→→→→ {}.{}() 【{}】耗时[{}]毫秒 →→", className, methodName, "End", time);
    }

    protected void logRequest(Object request) {
        String print = convert2String(request);
        if (StringUtils.isNoneBlank(print)) {
            log.info("======== 【{}】: {} ========", "请求", print);
        }
    }

    protected void logRequest(Object request, int index) {
        String print = convert2String(request);
        if (StringUtils.isNoneBlank(print)) {
            log.info("======== 【{}】[{}]: {} ========", "请求", index, print);
        }
    }

    protected void logResponse(Object response) {
        String print = convert2String(response);
        if (StringUtils.isNoneBlank(print)) {
            log.info("======== 【{}】: {} ========", "响应", print);
        }
    }

    private String convert2String(Object obj) {
        if (obj == null) {
            return "null";
        }
        if (obj instanceof BaseObject) {
            return ((BaseObject) obj).toJson();
        }
        return obj.toString();
    }
}
