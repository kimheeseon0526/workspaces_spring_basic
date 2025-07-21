package com.levelupseon.spring_basic.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_board")
public class Board {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long bno;

  @Column(length = 1000, nullable = true) //not null
  private String title;

  @Column(columnDefinition = "text")
  private String content;

  private LocalDateTime regdate;

  @ManyToOne
  @JoinColumn(name = "user_no")
  private Member member;
}
