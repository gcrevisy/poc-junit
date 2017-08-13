package fr.alteca.poc.service;

import fr.alteca.poc.exception.CustomException;

import java.util.List;

public interface Service<T> {

    List<T> getAll();

    List<T> getByCriteria(String title, String content, String hashTag) throws CustomException;

    T getById(Integer id) throws CustomException;

    void delete(T item) throws CustomException;

    void deleteById(Integer id) throws CustomException;

}
