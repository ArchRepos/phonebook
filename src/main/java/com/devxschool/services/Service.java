package com.devxschool.services;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface Service<T> {

    Optional<T> getByID(Long id);
    List<T> searchByName(String name);
    long count();
    void save(T t);
    void delete(T t);

}
