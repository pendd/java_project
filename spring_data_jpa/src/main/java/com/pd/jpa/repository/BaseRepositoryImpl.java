package com.pd.jpa.repository;

import java.util.Optional;

/**
 * @author dpeng
 */
public class BaseRepositoryImpl implements BaseRepository {
    @Override
    public Optional findById(Object o) {
        return Optional.empty();
    }

    @Override
    public Object save(Object entity) {
        return null;
    }

}
