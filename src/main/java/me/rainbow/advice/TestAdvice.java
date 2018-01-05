package me.rainbow.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author guojinpeng
 * @date 18.1.5 10:45
 */
@Aspect
@Component
public class TestAdvice {
    /**
     * 环绕通知
     */
    @Around(value = "execution(* me.rainbow.service.impl.LogServiceImpl.testAspectJ(..))")
    public Object modify(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        System.out.println(Arrays.toString(args));
        Object proceed = joinPoint.proceed();
        System.out.println(proceed);
        return proceed;
    }
}