package com.example.spring02.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class MessageAdvice {

    @Before("execution(* com.example.spring02.service.message.MessageService*.*(..))")
    public void startLog(JoinPoint jp){
        System.out.println("핵심업무코드의 정보: "+jp.getSignature());
        System.out.println("method: "+jp.getSignature().getName());
        System.out.println("매개변수: "+ Arrays.toString(jp.getArgs()));
    }

    @Around("execution(* com.example.spring02.service.message.MessageService*.*(..))")
    public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = pjp.proceed();
        long end = System.currentTimeMillis();
        System.out.println(pjp.getSignature().getName()+":"+(end-start));
        System.out.println("=====================================");
        return result;
    }
}
