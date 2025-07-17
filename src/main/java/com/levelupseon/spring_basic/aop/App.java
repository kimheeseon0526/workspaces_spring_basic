package com.levelupseon.spring_basic.aop;

import java.lang.reflect.Proxy;

public class App {
  public static void main(String[] args) {
    //타켓 생성
    BoardService target = new BoardServiceImpl();
    System.out.println("================= target의 결과물================");
    target.write("제목", "내용");

    //프록시 생성
    BoardService proxy = (BoardService) Proxy.newProxyInstance(
            BoardService.class.getClassLoader(),
            new Class[] {BoardService.class},
            new LoggingInvocationHandler(target)
    );
    System.out.println("================= target의 결과물================");
    proxy.write("title", "content");
  }
}
