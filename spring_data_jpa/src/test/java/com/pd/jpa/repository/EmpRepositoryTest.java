package com.pd.jpa.repository;

import com.pd.jpa.entity.Emp;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

/**
 * @author dpeng
 */
@SpringBootTest
public class EmpRepositoryTest {

    @Autowired private EmpRepository repository;

    @Test
    void findByNameAndAge() {
        System.out.println(repository.findByNameAndAge("lucy", 20));
    }

    @Test
    void findDistinctByName() {
        System.out.println(repository.findDistinctEmpByName("lucy"));
    }

    @Test
    void findByName() {
        Page<Emp> page = repository.findByName("lucy", PageRequest.of(0, 2));
        System.out.println(page);
    }

    @Test
    void findEmpByName() {
        Slice<Emp> slice = repository.findEmpByName("lucy", PageRequest.of(0, 2));
        System.out.println(slice);
    }

    @Test
    void findByNameSort() {
        // 多属性多方向排序
        Sort sort = Sort.by("age").ascending().and(Sort.by("name").descending());
        List<Emp> list = repository.findByName("lucy", Sort.by(Direction.DESC, "age"));
        System.out.println(list);
    }

    @Test
    void findDistinctEmpByName() {
        List<Emp> list = repository.findDistinctEmpByName("lucy", PageRequest.of(0, 2));
        System.out.println(list);
    }

    @Test
    void findByNameQuery() {
        System.out.println(repository.findByName("lucy"));
    }

    @Test
    void findByNameFromEntityName() {
        System.out.println(repository.findByNameFromEntityName("lucy"));
    }
}
