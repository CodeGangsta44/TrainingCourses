package com.company.model.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T> extends AutoCloseable {
    void create (T entity);
    T findById(int id);
    List<T> findAll();
    void update(T entity);
    void delete(int id);
}
