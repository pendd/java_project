package com.pd.jpa.repository;

import com.pd.jpa.entity.Person;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @description:
 * @author: pd
 * @date: 2020-08-21 10:09 下午
 */
@SpringBootTest
public class PersonRepositoryTest {

  @Autowired private PersonRepository repository;

  @Test
  void save() {
    //    repository.save(Person.builder().id(1).name("lucy").age(20).build());
    repository.save(Person.builder().name("john").age(25).build());
  }

  @Test
  void saveAll() {
    repository.saveAll(
        Arrays.asList(
            Person.builder().name("lily").age(25).build(),
            Person.builder().name("john").age(21).build(),
            Person.builder().name("lucy").age(21).build(),
            Person.builder().name("liu").age(26).build()));
  }

  @Test
  void findAll() {
    Iterable<Person> persons = repository.findAll();
    persons.forEach(System.out::print);
  }

  @Test
  void findById() {
    System.out.println(repository.findById(1));
  }

  @Test
  void findAllById() {
    // findAllById 参数是Iterable  Collection集合接口继承了Iterable接口
    repository.findAllById(Arrays.asList(1, 2));
  }

  @Test
  void existsById() {
    System.out.println(repository.existsById(1));
    System.out.println(repository.existsById(5));
  }

  @Test
  void count() {
    System.out.println(repository.count());
  }

  @Test
  void deleteById() {
    repository.deleteById(1);
    // EmptyResultDataAccessException
    repository.deleteById(5);
  }

  @Test
  void delete() {
    // 对象中要加入主键字段
    repository.delete(Person.builder().id(2).name("john").age(25).build());
  }

  @Test
  void deleteAllByIterable() {
    repository.deleteAll(
        Arrays.asList(Person.builder().id(3).build(), Person.builder().id(4).build()));
  }

  @Test
  void deleteAll() {
    repository.deleteAll();
  }

  @Test
  void countByLastName() {
    System.out.println(repository.countByName("lucy"));
  }

  @Test
  void deleteByLastName() {
    System.out.println(repository.deleteByName("liu"));
  }

  @Test
  void removeByLastName() {
    repository.removeByAge(21).forEach(System.out::println);
  }
}
