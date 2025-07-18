package com.levelupseon.spring_basic.aop;

import com.levelupseon.spring_basic.aop.advice.MyBeforeAdvice;
import com.levelupseon.spring_basic.aop.advice.LoggingAdvice;
import com.levelupseon.spring_basic.service.FirstService;
import com.levelupseon.spring_basic.service.SecondService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.aop.Advice;
import org.junit.jupiter.api.Test;
import org.springframework.aop.*;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Method;

@SpringBootTest
@Slf4j
public class proxyTest {

  @Autowired
  private BoardService  boardService;
  @Autowired
  private LoggingAdvice advice;
  @Autowired
  private MyBeforeAdvice before;
  @Autowired
  private AfterReturningAdvice afterReturn;
  @Autowired
  private ThrowsAdvice throwsAdvice;
  @Autowired
  private Pointcut pointcut;
  @Autowired
  private FirstService firstService;
  @Autowired
  private SecondService secondService;


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
    proxy.remove(3L);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  @Test
  public void testAdvisor() {
    ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.setTarget(boardService);

//    Advisor advisor;
    PointcutAdvisor pointcutAdvisor = new DefaultPointcutAdvisor(pointcut, before);
    proxyFactory.addAdvisor(pointcutAdvisor);

    proxy = (BoardService) proxyFactory.getProxy();

    proxy.write("제목", "내용");
    proxy.get(3L);
    proxy.remove(4L);
  }
  @Test
  public void test() {
    MethodBeforeAdvice beforeAdvice = (method, args, target) -> System.out.println("익명 출력");
      DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(new StaticMethodMatcherPointcut() {
        @Override
        public boolean matches(Method method, Class<?> targetClass) {
          return method.getName().equals("two") && targetClass == FirstService.class;
        }
      }, beforeAdvice);

      ProxyFactory proxyFactory = new ProxyFactory();
      proxyFactory.setTarget(firstService);
      proxyFactory.addAdvisor(advisor);

      ProxyFactory proxyFactory2 = new ProxyFactory();
      proxyFactory2.setTarget(secondService);
      proxyFactory2.addAdvisor(advisor);

      FirstService proxy = (FirstService) proxyFactory.getProxy();
      proxy.one();
      proxy.two();

      SecondService proxy2 = (SecondService) proxyFactory2.getProxy();
      proxy2.one();
      proxy2.two();

    }

  @Test
  public void testAspectj() {
  //사용하기 위한 필수 라이브러리 2개 필요
    AspectJExpressionPointcut pc = new AspectJExpressionPointcut();
    pc.setExpression("execution(void *.write*(..))"); //표현식을 위한 조건 정의 모든 메서드~

    DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pc, before);

    ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.setTarget(boardService);

    proxyFactory.addAdvisor(advisor);

    BoardService proxy = (BoardService) proxyFactory.getProxy();
    proxy.write("title", "content");
    proxy.get(3L);
  }
}
