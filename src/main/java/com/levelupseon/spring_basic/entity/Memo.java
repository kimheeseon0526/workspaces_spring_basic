package com.levelupseon.spring_basic.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "tbl_memo")
@ToString
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Memo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long mno;

  @Column(nullable = false)
  private String memotext ="기본값";
}
