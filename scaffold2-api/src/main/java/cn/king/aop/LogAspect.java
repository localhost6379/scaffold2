package cn.king.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogAspect {

    @Around(value = "@annotation(cn.king.annotation.Log)")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Object result = null;
        // 得到方法执行所需的参数
        Object[] args = joinPoint.getArgs();
        try {
            log.info("AOP => begin, method: {}, args: {}", methodSignature, args);
            // 调用切入的方法
            result = joinPoint.proceed(args);
            log.info("AOP => success, method: {}", methodSignature);
            log.info("AOP => success, return: {}", result);
        } catch (Throwable t) {
            log.error("AOP => error, method: {}", methodSignature);
            log.error("AOP => error, args: {}", args);
            log.error("AOP => error, getStackTrace: {},{}", t, t.getStackTrace());
            log.error("AOP => error, getMessage: {}", t.getMessage());
        }
        return result;
    }

}