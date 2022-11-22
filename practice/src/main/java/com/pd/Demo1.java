package com.pd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: YCWB0382
 * @date: 2021-06-09 11:21
 */
public class Demo1 {
  public static void main(String[] args) {
    List<User> syncList = Collections.synchronizedList(new ArrayList<>());
    List<User> list = new ArrayList<>();
    for (int i = 0; i < 100; i++) {
      list.add(new User(i, "name" + i));
    }

    List<User> collect =
        list.parallelStream()
            .peek(
                l -> {
                  l.setAge(l.getId() * 10);
                  syncList.add(l);
                })
            .collect(Collectors.toList());
    list.forEach(System.out::println);
    System.out.println("-----------------------------");
    System.out.println("-----------------------------");
    collect.forEach(System.out::println);
    System.out.println("-----------------------------");
    System.out.println("-----------------------------");
    syncList.forEach(System.out::println);
    System.out.println("-----------------------------");
    System.out.println("-----------------------------");
    System.out.println("list.size=" + list.size());
    System.out.println("syncList.size=" + syncList.size());
  }
}

class User {
  private int id;
  private String name;
  private int age;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public User(int id, String name) {
    this.id = id;
    this.name = name;
  }

  @Override
  public String toString() {
    return "User{" + "id=" + id + ", name='" + name + '\'' + ", age=" + age + '}';
  }
}
