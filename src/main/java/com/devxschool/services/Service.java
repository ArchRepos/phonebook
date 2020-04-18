package com.devxschool.services;

import java.util.List;
import java.util.Optional;

public interface Service<T> {
    Optional<T> getByID(String id);
    Optional<T> getByNameAndSurname(String name, String surname);
    List<T> searchByFilter(String filter);
    long count();
    void save(T t);
    void delete(String id);
}
