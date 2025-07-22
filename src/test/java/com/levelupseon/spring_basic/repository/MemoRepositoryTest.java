package com.levelupseon.spring_basic.repository;

import com.levelupseon.spring_basic.domain.dto.MemoDTO;
import com.levelupseon.spring_basic.entity.Memo;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

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
    IntStream.rangeClosed(1,100).forEach(i -> {
      Memo memo = Memo.builder().memotext("Sample..." + i).build();
      memoRepository.save(new Memo());
    });
  }

  @Test
  public void testSelect() {
    Memo memo = memoRepository.findById(3L).orElseThrow();
    log.info("memoRepository : {}", memo);
  }

  @Test
  public void testUpdate() {
    Memo memo = Memo.builder().mno(3L).memotext("0722 수정").build();
    memoRepository.save(memo);
    log.info("testUpdate : {} ", memo);
  }

  @Test
  public void testDelete() {
    Memo memo = entityManager.find(Memo.class, 1L);
    memoRepository.delete(memo);
  }

  @Test
  public void testPageDefault() { //1페이지 10개
    PageRequest pageRequest = PageRequest.of(0,10);
    Page<Memo> result = memoRepository.findAll(pageRequest);
    result.forEach(r-> log.info("{}", r));

    long totalelements = result.getTotalElements();
    int totalPages = result.getTotalPages();

    log.info("totalElements = {}", totalelements);  //전체 개수
    log.info("totalPages = {}", totalPages);  //전체 페이지수

    //현재 페이지 번호
    log.info("number = {}", result.getNumber());
    //페이지당 데이터 갯수
    log.info("size = {}", result.getSize());
    //다음 페이지 여부
    log.info("hasPrevious = {}", result.hasPrevious());
    log.info("hasNext = {}", result.hasNext());
    //시작 페이지 여부
    log.info("isFirst = {}", result.isFirst());
    log.info("isLast = {}", result.isLast());

    result.getContent().forEach(c -> log.info("{}", c));
  }

  @Test
  public void testSort() {
    Sort sort = Sort.by(Sort.Direction.DESC, "mno");
    PageRequest pageRequest = PageRequest.of(0, 5, sort);
    Page<Memo> result = memoRepository.findAll(pageRequest);
    result.forEach(r -> log.info("{}", r));
  }

  //mno의 총 갯수를 로깅
  @Test
  @Commit
  public void testCount() {
    log.info("{}", memoRepository.countByMno(3L));
  }

  //mno가 특정 long 이거나 memotext가 특정 문자열일 때의 query method
  @Test
  public void findByMnoOrMemotext() {
    memoRepository.findByMnoOrMemotext(3L, "객체").forEach(m -> log.info("{}", m));
  }
  @Test
  public void testgetListDesc() {
    memoRepository.getListDesc().forEach(m -> log.info("{}", m));
  }

  @Test
  public void testgetListDesc2() {
    memoRepository.getListDesc2().forEach(m -> log.info("{}" , m));
  }

  @Test
  public void testUpdateMemoText2() {
//    Memo memo = memoRepository.findById(2L).orElseThrow();
    memoRepository.updateMemoText(3L, "변경내용2");
  }

  @Test
  public void testUpdateMemoText3() {
//    Memo memo = memoRepository.findById(2L).orElseThrow();
    memoRepository.updateMemoText2(Memo.builder().mno(4L).memotext("변경내용3").build());
  }

  @Test
  public void testUpdateMemoText4() {
    memoRepository.updateMemoText3(6L, "순서 찾기로 param 생략");
  }

  @Test
  public void testListWithQueryObject() {
    Page<Object[]> objects = memoRepository.getListWithQueryObject(0L, PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "mno")));
    objects.forEach(r -> {
      for(Object o : r) {
        log.info("{}", o);
      }
    });
  }

  @Test
  public void testListWithQueryProjection() {
    Page<MemoDTO> dtos = memoRepository.getListWithQueryProjection(0L, PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "mno")));
    dtos.forEach(r -> log.info("{}", r.getMno()));
  }

}
