package com.pd.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: pd
 * @date: 2020-08-21 10:02 下午
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Integer id;

  @Column private String name;

  @Column private Integer age;
}
