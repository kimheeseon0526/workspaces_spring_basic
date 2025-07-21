package com.levelupseon.spring_basic.repository;

import com.levelupseon.spring_basic.entity.Memo;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
public class MemoRepositoryTest {
  @Autowired
  private MemoRepository memoRepository;

  @Autowired
  private EntityManager entityManager;

  @Test
  public void testExist() {
    log.info("testExist : {} ", memoRepository);
  }

  @Test
  @Transactional
  @Rollback(false)
  public void testEntityManager() {
    log.info("testEntityManager : {} ", entityManager);

    entityManager.persist(new Memo());
  }

  @Test
  @Transactional
  @Rollback(false)
  public void testEntityManager2() {

    //JPA 는 dirty checking을 통해 값 변경 감지
    Memo memo = entityManager.find(Memo.class, 1L); //findById
    //영속 객체
    memo.setMemotext("하이하잉");
  }

  @Test
  @Transactional
  @Rollback(false)
  public void testEntityManager3() {
    Memo memo = new Memo();
    memo.setMno(2L);
    memo.setMemotext("비영속 객체");

    entityManager.merge(memo);
    //merge : 비영속 객체를 병합
    //merge 전에 findBy 한 후에 하나의 값을 먼저 변경하는것이 베스트
  }

  @Test
  public void testInsert() {
//    Memo memo = Memo.builder().memotext("지유하이").build();
    //없었을 때 비영속 객체를 persist 하는 법 : save
    memoRepository.save(new Memo());
  }

  @Test
  public void testDelete() {
    Memo memo = entityManager.find(Memo.class, 1L);
    memoRepository.delete(memo);
  }

}
