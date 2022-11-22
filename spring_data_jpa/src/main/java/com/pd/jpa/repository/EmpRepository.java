package com.pd.jpa.repository;

import com.pd.jpa.entity.Emp;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;

/**
 * @author dpeng
 */
public interface EmpRepository extends BaseRepository<Emp, Integer> {
    List<Emp> findByNameAndAge(String name, Integer age);

    List<Emp> findDistinctEmpByName(String name);

    // 不分页的话传入 Pageable.unpaged()
    Page<Emp> findByName(String name, Pageable pageable);

    // Page 和 Slice 区别：
    // Page 继承自 Slice
    // Page 每次查询都会返回总数，所以对于查询数据量大的情况下效率不高 count + query
    // Slice 查询不返回总数
    Slice<Emp> findEmpByName(String name, Pageable pageable);

    // 不排序的话传入 Sort.unsorted()
    List<Emp> findByName(String name, Sort sort);

    List<Emp> findDistinctEmpByName(String name, Pageable pageable);

    // 这里from 后面的 Emp 对应的是 @Entity 注解里name的值，只是默认和类名一致而已
    @Query("select e from Emp e where e.name = :name")
    List<Emp> findByName(String name);

    // #{#entityName} 自动获取 @Entity 注解里name的值
    // 这种方式注入可以避免@Entity注解中name值得变化照成其他@Query语句中的修改
    @Query("select e from #{#entityName} e where e.name = ?1")
    List<Emp> findByNameFromEntityName(String name);
}
