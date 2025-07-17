package com.levelupseon.spring_basic.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
//@Component
@AllArgsConstructor
@NoArgsConstructor
public class Member {
//  @Value("gae-ddong")
  private String name;
//  @Value("32")
  private int age;
}
