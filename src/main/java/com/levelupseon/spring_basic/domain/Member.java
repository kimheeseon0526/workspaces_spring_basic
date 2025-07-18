package com.levelupseon.spring_basic.domain;

import jdk.jshell.Snippet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
//@Component
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
//  @Value("gae-ddong")
  private String name;
//  @Value("32")
  private int age;

  private String id;

  public Member(String s, int i) {
    this.name = s;
    this.age = i;
  }

}
