package com.levelupseon.spring_basic.aop;

import com.levelupseon.spring_basic.config.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardServiceImplTest {
  @Autowired
  BoardService boardService;

  @Test
  void write() {
    boardService.write("title", "content");
  }

  @Test
  void get() {
    boardService.get(10L);
  }

  @Test
  void modify() {
    boardService.modify("title2", "content2");
  }

  @Test
  void remove() {
    boardService.remove(3L);
  }
}