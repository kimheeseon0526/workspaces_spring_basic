package com.levelupseon.spring_basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class IndexController {
  @GetMapping("")
  @ResponseBody
  //@ResponseBody 생략시 jsp forward
  public Map<?,?> index(){
    return Map.of("test", 1234);
  }
}
