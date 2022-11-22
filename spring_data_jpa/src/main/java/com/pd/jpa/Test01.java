package com.pd.jpa;

import com.pd.jpa.entity.Person;
import com.pd.jpa.entity.Student;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @description:
 * @author: pd
 * @date: 2020-08-26 2:14 下午
 */
@Transactional(propagation = Propagation.SUPPORTS)
public class Test01 {
  public static void main(String[] args) {
    List<Person> list =
        Arrays.asList(
            Person.builder().id(1).build(),
            Person.builder().id(2).build(),
            Person.builder().id(3).build(),
            Person.builder().id(4).build(),
            Person.builder().id(5).build());

    list.stream()
        .filter(
            li -> {
              if (li.getId().equals(3)) return true;
              System.out.println("loading::::" + li.getId());
              return false;
            })
        .findFirst();
  }

  @Test
  public void test1() {
    Student student1 = new Student();
    BeanUtils.copyProperties(null, student1);
    System.out.println(student1);
  }

  @Test
  public void test2() {
    List<String> list = new ArrayList<>();
    list.add(null);
    list.add(null);
    list.add("1");
    boolean flag =
        Optional.ofNullable(list).orElse(Lists.newArrayList()).stream().anyMatch(Objects::isNull);
    System.out.println(flag);
  }
}
