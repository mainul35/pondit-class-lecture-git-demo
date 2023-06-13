package com.mainul35;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.logging.Logger;

@Aspect
public class EmployeeCRUDAspect {

    private static final Logger LOGGER = Logger.getLogger("com.mainul35.EmployeeCRUDAspect");


    //    @Before("execution(* com.mainul35.service.EmployeeManager.getEmployeeById(..))")
    @Before("execution(* com.mainul35.service.EmployeeManager.getEmployeeById(..))")
    public void logBeforeV1(JoinPoint joinPoint)
    {
        System.out.println("EmployeeCRUDAspect.logBeforeV1() : " + joinPoint.getSignature().getName());
    }

    @Before("execution(* com.mainul35.service.EmployeeManager.*(..))")
    public void logBeforeV2(JoinPoint joinPoint)
    {
        System.out.println("EmployeeCRUDAspect.logBeforeV2() : " + joinPoint.getSignature().getName());
    }

    @After("execution(* com.mainul35.service.EmployeeManager.getEmployeeById(..))")
    public void logAfterV1(JoinPoint joinPoint)
    {
        System.out.println("EmployeeCRUDAspect.logAfterV1() : " + joinPoint.getSignature().getName());
    }

    @After("execution(* com.mainul35.service.EmployeeManager.*(..))")
    public void logAfterV2(JoinPoint joinPoint)
    {
        System.out.println("EmployeeCRUDAspect.logAfterV2() : " + joinPoint.getSignature().getName());
    }

    @Before("@annotation(Audit)")
    public void intercept(final JoinPoint point) throws Throwable {
        var sig = (MethodSignature) point.getSignature();
        Method method = sig.getMethod();
        method.getDeclaredAnnotation(Audit.class);

        var declaredAnnotationValue = method.getDeclaredAnnotation(Audit.class).role();
        if (declaredAnnotationValue != "ROLE_ADMIN") {
            LOGGER.warning(String.format("Invalid role %s on method %s", declaredAnnotationValue, sig.getMethod().getName()));
        }
        LOGGER.info(String.format("Found role %s on method %s", declaredAnnotationValue, sig.getMethod().getName()));
    }
}