package com.levelupseon.spring_basic.main;

import com.levelupseon.spring_basic.domain.Member;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class MemberMain2 {
  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("xml/bean-config-java.xml");
    Member m = context.getBean("member", Member.class);
    Member m2 = context.getBean("member", Member.class);  //같은 객체라서 true
    System.out.println(m == m2);
  }
}

