package com.levelupseon.spring_basic.service;

import com.levelupseon.spring_basic.domain.Member;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("mem1")
@ToString
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

  
  //생성자 정의 및 주입 ->유일한 생성자의 경우 @Autowired 없어도 됨

//  public MemberServiceImpl(Member member){
//    this.member=member;
//  }

  @Override
  public void register(Member member) {

  }

}
