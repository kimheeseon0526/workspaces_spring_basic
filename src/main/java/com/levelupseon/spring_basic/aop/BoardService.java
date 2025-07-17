package com.levelupseon.spring_basic.aop;

public interface BoardService {
  //인터페이스로 메서드 선언
  //글 작성
  void write(String title, String content);
  //글 조회
  Object get(Long bno);
  //글 수정
  void modify(String title, String content);
  //글 삭제
  void remove(Long bno);
}
