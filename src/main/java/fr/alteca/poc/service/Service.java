package fr.alteca.poc.service;

import java.util.List;

public interface Service<T> {

    List<T> getAll();

    List<T> getByCriteria(String title, String content, String hashTag);

    T getById(int id);

    void delete(T item);

    void deleteById(int id);

}
