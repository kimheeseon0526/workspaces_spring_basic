package com.levelupseon.spring_basic.service;

import com.levelupseon.spring_basic.domain.Member;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {

  void register(Member member);

}
