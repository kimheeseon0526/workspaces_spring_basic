package com.levelupseon.spring_basic.config;

import com.levelupseon.spring_basic.domain.Member;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
  @Bean
  public Member member() {
    return new Member("so-ddong", 22);
  }
}
