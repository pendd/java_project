package com.pd.jpa.service;

import com.pd.jpa.entity.Person;
import com.pd.jpa.entity.Student;
import com.pd.jpa.repository.PersonRepository;
import com.pd.jpa.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author: pd
 * @date: 2021-03-14 下午1:11
 */
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
@Service
@Slf4j
public class TransactionalSupportService {

  @Resource private PersonRepository personRepository;
  @Resource private StudentRepository studentRepository;
  @Resource private TransactionalSupportService service;

  @Transactional(propagation = Propagation.REQUIRED)
  public void insertPerson() {
    personRepository.save(Person.builder().age(50).name("彭东").build());
    //    int i = 1 / 0;
    studentRepository.save(Student.builder().age(50).name("彭东").build());

    service.insertStudent();
    //    int i = 1 / 0;
  }

  //  @Transactional(propagation = Propagation.NESTED)
  public void insertStudent() {
    studentRepository.save(Student.builder().age(100).name("夏正航").build());
    int i = 1 / 0;
    personRepository.save(Person.builder().age(100).name("夏正航").build());
  }

  public void insertPersonNoTransaction() {
    personRepository.save(Person.builder().age(50).name("彭东").build());
    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void getPerson() {
    personRepository.findAll().forEach(System.out::println);
  }
}
