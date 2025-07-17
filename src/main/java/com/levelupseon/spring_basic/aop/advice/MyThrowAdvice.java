package com.levelupseon.spring_basic.aop.advice;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Component;

@Component
public class MyThrowAdvice implements ThrowsAdvice {
  public void afterThrowing(Throwable throwable) throws Throwable {
    //예외가 터졌을 때의 할일
    System.out.println("나눈 afterThrow야 빵빵터져");

  }
}
