package com.levelupseon.spring_basic.repository;

import com.levelupseon.spring_basic.domain.Member;
import com.levelupseon.spring_basic.entity.Memo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
public class MemberRepositoryTest {
  @Autowired
  MemberRepository memberRepository;
  @Autowired
  private MemoRepository memoRepository;

  @Test
  public void testExist() {
    log.info("testExist : {} " , memberRepository);
    //testExist : org.springframework.data.jpa.repository.support.SimpleJpaRepository@33c5e52f
  }

  @Test
  @Transactional
  @Rollback(false)
  public void testInsert() {
    Member member = Member.builder().no(4L).id("sae").pw("1234").age(50).name("새똥이").build();
    memberRepository.save(member);
    //Hibernate: select m1_0.no,m1_0.age,m1_0.id,m1_0.name,m1_0.pw from tbl_member m1_0 where m1_0.no=?
  }

  @Test
  public void testFindById() {
    Member member = memberRepository.findById(1L).orElseThrow(() -> new RuntimeException("지정된 회원 번호가 없따!"));
    //Member member1 = memberRepository.findBy(Example.of(Member.builder().build()))
    log.info("testFindById : {} " , member);
    //testFindById : Member(no=1, name=새똥이, id=sae, pw=1234, age=13)
  }

  @Test
  public void testFindAll() {
    memberRepository.findAll().forEach(m -> log.info("testFindAll : {} ", m));
  }

  @Test
  @Transactional
  @Rollback(false)
  public void testUpdate() {
    Member member = memberRepository.findById(1L).orElseThrow(() -> new RuntimeException("지정된 번호 없음!"));
    member.setAge(230);
//    memberRepository.save(member);
  }

  @Test
  public void testDelete() {
    memberRepository.deleteById(2L);
  }

  @Test
  public void testQueryMethod() {
    memoRepository.findByMnoBetweenOrderByMnoDesc(70L, 80L).forEach( m -> log.info("{}", m));
  }
  @Test
  public void testQueryMethod2() {
    Page<Memo> memos = memoRepository
            .findByMnoBetween(10L, 50L, PageRequest.of(0, 10, Sort.Direction.DESC, "mno"));
    memos.forEach( m ->log.info("{}", m));
  }
  //memo의 총 갯수를 로깅하게끔
  @Test
  public void testCount() {
    log.info("{}", memoRepository.countByMno(1L));
  }

  //Mno가 특정 long이고 memoText가 특정 문자열일 때의 query method
}
