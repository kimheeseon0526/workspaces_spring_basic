package com.levelupseon.spring_basic.domain;

import jakarta.persistence.*;
import jdk.jshell.Snippet;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_member") //테이블명
@Getter
@Setter
@ToString(exclude = "boards")
public class Member {
  @Id // pk
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long no;
  private String name;
  private String id;
  private String pw;
  private int age;

  @OneToMany(mappedBy = "member")
  private List<Board> boards;
}
