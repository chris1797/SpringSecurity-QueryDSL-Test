package com.security.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;


@Aspect
public class TimeTraceAop {

    @Around("execution(* com.security.demo..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start  = System.currentTimeMillis();
        System.out.println("Start: " + joinPoint.toString());

        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("end : " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
