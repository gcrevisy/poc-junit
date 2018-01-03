package fr.alteca.poc.service;

import fr.alteca.poc.exception.CustomException;
import fr.alteca.poc.pojo.RetourService;

import java.util.List;

public interface Service<T> {

    RetourService<List<T>> getAll();

    RetourService<List<T>> getByCriteria(String title, String content, String hashTag) throws CustomException;

    RetourService<T> getById(Integer id) throws CustomException;

    void delete(T item) throws CustomException;

    void deleteById(Integer id) throws CustomException;

}
