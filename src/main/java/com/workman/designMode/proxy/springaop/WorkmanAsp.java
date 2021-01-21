package com.workman.designMode.proxy.springaop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class WorkmanAsp {

    @Pointcut("@annotation(com.source.workman.designMode.proxy.springaop.Workman)")
    private void pointcut() {

    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
//        System.out.println("joinPoint = " + joinPoint.getSignature().getName());
        String str = JSON.toJSONString(joinPoint.getArgs());
        System.out.println("aop before method,str=" + str);
//        Arrays.stream(joinPoint.getArgs()).forEach((str) -> {
//            System.out.println("str = " + str);
//        });

    }

    @After("pointcut()")
    public void after(JoinPoint joinPoint) {
//        System.out.println("joinPoint = " + joinPoint.getSignature().getName());
        Arrays.stream(joinPoint.getArgs()).forEach((str) -> {
            System.out.println("aop after method,str = " + str.toString());
        });

//        String str2 = JSON.toJSONString(joinPoint.getArgs().);
//        System.out.println("aop after method,str2=" + str2);
//        System.out.println("joinPoint getThis= " + joinPoint.getThis().toString());
//        System.out.println("joinPoint getDeclaringTypeName= " + joinPoint.getSignature().getDeclaringTypeName());
//        System.out.println("joinPoint getKind= " + joinPoint.getKind().toString());
    }
}
