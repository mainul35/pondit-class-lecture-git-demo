package com.spring5.practice.config.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerRequestInterceptor {

    @Around("@annotation(Audit)")
    public void executeBeforeMethod(JoinPoint point) {
        System.out.println(point.toString());
        System.out.println("Executing....");
    }

    @After("execution(public com.spring5.practice.controllers.* *(..))")
    public void executeAfterMethod(JoinPoint point) {
        System.out.println(point.toString());
        System.out.println("Executed successfully....");
    }
}
