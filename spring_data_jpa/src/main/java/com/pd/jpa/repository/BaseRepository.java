package com.pd.jpa.repository;

import java.util.Optional;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

/**
 * @author dpeng
 */
@NoRepositoryBean //告诉JPA不要创建对应接口的bean对象
public interface BaseRepository<T, ID> extends Repository<T, ID> {

    Optional<T> findById(ID id);

    <S extends T> S save(S entity);
}
