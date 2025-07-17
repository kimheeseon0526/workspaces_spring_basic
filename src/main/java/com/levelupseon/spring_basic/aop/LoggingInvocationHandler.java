package com.levelupseon.spring_basic.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LoggingInvocationHandler implements InvocationHandler {
  private Object target;
  public LoggingInvocationHandler(Object target) {
    this.target = target;
  }

  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    System.out.println("[로그] 호출 전 :: " + method.getName());
    Object o = method.invoke(target, args); //메서드 대리호출
    System.out.println("[로그] 호출 후 :: " + method.getName());
    return o;
  }
}
