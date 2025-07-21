package com.levelupseon.spring_basic.repository;

import com.levelupseon.spring_basic.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
  // JpaRepository<Member,Long> <상속받을 엔터티, pk의 타입>
}
