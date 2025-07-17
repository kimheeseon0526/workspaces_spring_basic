package com.levelupseon.spring_basic.service;

import com.levelupseon.spring_basic.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("mem2")
public class MemberServiceImpl2 implements MemberService {
  @Autowired
  private Member member;

  @Override
  public void register(Member member) {

  }

}
