package com.nhnacademy.edu.springframework.project.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;


@Aspect
@Component
public class LoggingAspect {

    private static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("within(com.nhnacademy.edu.springframework.project.service.*)")
    public Object loggingExecutionTime(ProceedingJoinPoint pjp) {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object rt = null;
        try {
            rt = pjp.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            stopWatch.stop();
            logger.info("[" + pjp.getSignature().getDeclaringTypeName() + "].[" + pjp.getSignature().getName() + "] ["
                + String.valueOf(stopWatch.getTotalTimeMillis()) + "]ms");
        }
        return rt;
    }
}
