package com.levelupseon.spring_basic.aop.pointcut;

import com.levelupseon.spring_basic.service.FirstService;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class MySimplePointcut extends StaticMethodMatcherPointcut {
  @Override
  public boolean matches(Method method, Class<?> targetClass) {
    //매개변수 갯수가 1개, 그리고 리턴 타입이 void 를 반환
//    return method.getReturnType() == void.class && method.getParameterCount() == 1;
    //메서드의 이름이 two이고 FirstClass 타입
//    System.out.println(method.getReturnType());
//    System.out.println(method.getParameterCount());
//    System.out.println(method.getName());
//    System.out.println(method.getDeclaringClass());
//    System.out.println(method.getAnnotatedReturnType());
//    System.out.println(method.getModifiers());
    return method.getName().equals("two") && targetClass == FirstService.class;
  }

}
