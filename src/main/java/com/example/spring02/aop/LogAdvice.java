package com.example.spring02.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component //bean managed by spring
@Aspect //advice class supports AOP
public class LogAdvice {

    @Around("execution(* com.example.spring02.controller..*Controller.*(..))" +
            " || execution(* com.example.spring02.service..*Impl.*(..))" +
            " || execution(* com.example.spring02.model..dao.*Impl.*(..))")
    public Object logPrint(ProceedingJoinPoint joinPoint) throws Throwable {
        long start=System.currentTimeMillis();

        //Main action
        Object result = joinPoint.proceed();
        String type=joinPoint.getSignature().getDeclaringTypeName();
        String name="";

        if(type.contains("Controller")){ //type.indexOf("Controller") > -1
            name="Controller \t: ";
        }else if(type.contains("Service")){
            name="ServiceImpl \t: ";
        }else if(type.contains("DAO")){
            name="DAOImpl \t: ";
        }
        //method name
        System.out.println(name+type+"."+joinPoint.getSignature().getName()+"()");
        //parameter list
        System.out.println(Arrays.toString(joinPoint.getArgs()));

        long end=System.currentTimeMillis();
        long time=end-start;
        System.out.println("실행시간: "+time);
        return result;
    }
}
