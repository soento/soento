package com.soento.core.acpects;

import com.soento.core.annotation.MethodLog;
import com.soento.core.support.AbstractLogAspect;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author soento
 */
@Slf4j
@Aspect
@Component
public class MethodLogAspect extends AbstractLogAspect {

    @Pointcut("@annotation(com.soento.core.annotation.MethodLog)")
    public void methodLog() {
    }

    @Around("methodLog()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodLog method = getAnnotation(joinPoint, MethodLog.class);
        log.info("↓↓↓↓↓↓↓↓↓↓ 【{}】{} ↓↓↓↓↓↓↓↓↓↓", method.value(), "处理开始");
        Object result = logAround(joinPoint);
        log.info("↑↑↑↑↑↑↑↑↑↑ 【{}】{} ↑↑↑↑↑↑↑↑↑↑", method.value(), "处理结束");
        return result;
    }
}
