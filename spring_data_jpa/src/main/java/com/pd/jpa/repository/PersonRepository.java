package com.pd.jpa.repository;

import com.pd.jpa.entity.Person;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description:
 * @author: pd
 * @date: 2020-08-21 10:08 下午
 */
public interface PersonRepository extends CrudRepository<Person, Integer> {
  long countByName(String name);

  @Transactional
  long deleteByName(String name);

  @Transactional
  List<Person> removeByAge(Integer age);

}
