package com.pd.jpa.repository;

import com.pd.jpa.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

/**
 * @description:
 * @author: pd
 * @date: 2020-08-22 9:47 上午
 */
@SpringBootTest
class StudentRepositoryTest {

  @Autowired private StudentRepository repository;

  @Test
  void findAll() {
    // PageRequest.of  方法 page 下标从0开始，表示第一页
    Page<Student> all = repository.findAll(PageRequest.of(0, 5));
    all.get().forEach(System.out::println);
  }

  @Test
  void findAllByPageable() {
    repository
        .findAll(PageRequest.of(0, 5, Direction.ASC, "age", "name"))
        .get()
        .forEach(System.out::println);
  }

  @Test
  void findAllBySort() {
    repository
        .findAll(PageRequest.of(0, 5, Sort.by(Direction.ASC, "age", "name")))
        .get()
        .forEach(System.out::println);
  }

  @Test
  void findAllBySortOrder() {
    repository
        .findAll(Sort.by(new Order(Direction.ASC, "age"), new Order(Direction.DESC, "name")))
        .forEach(System.out::println);
  }
}
