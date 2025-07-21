package com.levelupseon.spring_basic.repository;

import com.levelupseon.spring_basic.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoRepository extends JpaRepository<Memo,Long> {
}
