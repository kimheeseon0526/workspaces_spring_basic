package com.levelupseon.spring_basic.aop;

import com.levelupseon.spring_basic.aop.advice.BeforeAdvice;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.aop.Advice;
import org.junit.jupiter.api.Test;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class proxyTest {

  @Autowired
  private BoardService  boardService;
  @Autowired
  private LoggingAdvice advice;
  @Autowired
  private BeforeAdvice before;
  @Autowired
  private AfterReturningAdvice afterReturn;
  @Autowired
  private ThrowsAdvice throwsAdvice;

  private BoardService proxy;
  @PostConstruct
  public void init(){
    Advice[] advices = new Advice[] {afterReturn, throwsAdvice};
    ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.setTarget(boardService);
    for(Advice a : advices){
      proxyFactory.addAdvice(a);
    }
    proxy = (BoardService) proxyFactory.getProxy();
  }

  @Test
  public void proxyTest(){
    log.info("{}", boardService);
  }

  @Test
  public void testWrite() {
    boardService.write("원본 객체의 제목", "내용내용");

    ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.setTarget(boardService);
    proxyFactory.addAdvice(advice);
    BoardService proxy = (BoardService) proxyFactory.getProxy();
    
    proxy.write("프록시 객체의 제목", " 내용내용내용");
  }

  @Test
  public void testBefore() {
    ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.setTarget(boardService);
    proxyFactory.addAdvice(advice);
    proxyFactory.addAdvice(before);
    BoardService proxy = (BoardService) proxyFactory.getProxy();

    System.out.println("=================글쓰기=================");
    proxy.write("프록시 객체의 제목", " 내용내용내용");

    System.out.println("=================글조회=================");
    proxy.get(3L);
  }

  @Test
  public void testAfterReturn() {
    try{
    proxy.remove(1L);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
