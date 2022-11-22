package com.pd.kafka.entity;

/**
 * @author: YCWB0382
 * @date: 2021-06-16 14:22
 */
public class Student {
  private Integer id;
  private Integer age;
  private String name;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getAge() {
    return age;
  }

  @Override
  public String toString() {
    return "Student{" + "id=" + id + ", age=" + age + ", name='" + name + '\'' + '}';
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Student(Integer id, Integer age, String name) {
    this.id = id;
    this.age = age;
    this.name = name;
  }

  public Student() {}
}
