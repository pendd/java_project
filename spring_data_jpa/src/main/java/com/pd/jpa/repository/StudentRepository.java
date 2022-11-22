package com.pd.jpa.repository;

import com.pd.jpa.entity.Student;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @description:
 * @author: pd
 * @date: 2020-08-22 9:47 上午
 */
public interface StudentRepository extends PagingAndSortingRepository<Student, Integer> {
}
