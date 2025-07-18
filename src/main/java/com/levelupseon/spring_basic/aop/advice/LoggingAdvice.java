package com.levelupseon.spring_basic.aop.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

@Component
public class LoggingAdvice implements MethodInterceptor {
  //invoke
  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable{
    System.out.println("[로그] 호출 전 ::" + invocation.getMethod().getName());
    Object o = invocation.proceed();
    System.out.println("[로그] 호출 후 ::" + invocation.getMethod().getName());
    return o;
  }
}
