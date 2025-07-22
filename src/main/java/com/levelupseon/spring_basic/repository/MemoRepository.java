package com.levelupseon.spring_basic.repository;

import com.levelupseon.spring_basic.domain.dto.MemoDTO;
import com.levelupseon.spring_basic.entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MemoRepository extends JpaRepository<Memo,Long> {

  List<Memo> findByMnoBetweenOrderByMnoDesc(Long mno1, Long mno2);

  Page<Memo> findByMnoBetween(Long mno1, Long mno2, Pageable pageable);

  long countByMno(Long mno);

  List<Memo> findByMnoOrMemotext(Long mno, String memoText);

  @Query("select m from Memo m order by m.mno desc limit 10")
  //사전에 정의되지 않은 쿼리 메서드는 터진다 그래서 어노테이션으로 쿼리임을 정의해줌
  List<Memo> getListDesc();

  @Query(value = "select * from tbl_memo order by mno desc limit 10", nativeQuery = true)
  List<Memo> getListDesc2();

  @Transactional
  @Modifying
  @Query("update Memo m set m.memotext = :memoText where m.mno =:mno")
  int updateMemoText(@Param("mno") Long mno, @Param("memoText") String memoText);

  @Transactional
  @Modifying
  @Query("update Memo m set m.memotext = :#{#memo.memotext} where m.mno = :#{#memo.mno}")
  int updateMemoText2(@Param("memo") Memo memo);

  @Transactional
  @Modifying
  @Query("update Memo m set m.memotext = ?2 where m.mno = ?1")
  int updateMemoText3(Long mno, String memoText);

  @Query(value = "select  m.mno, m.memotext, CURRENT_DATE AS n from Memo m where m.mno > :mno",
          countQuery = "select count(m) from Memo m where m.mno > :mno")
  Page<Object[]> getListWithQueryObject(Long mno, Pageable pageable);

  @Query(value = "select  m.mno AS mno, m.memotext AS memoText, CURRENT_DATE AS n from Memo m where m.mno > :mno",
          countQuery = "select count(m) from Memo m where m.mno > :mno")
  Page<MemoDTO> getListWithQueryProjection(Long mno, Pageable pageable);

}
