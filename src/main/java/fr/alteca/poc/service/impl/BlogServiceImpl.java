package fr.alteca.poc.service.impl;

import fr.alteca.poc.entity.Blog;
import fr.alteca.poc.exception.CustomException;
import fr.alteca.poc.service.BlogService;

import java.util.ArrayList;
import java.util.List;

public class BlogServiceImpl implements BlogService {
    public List<Blog> getAll() {
        List<Blog> result = new ArrayList<Blog>();

        return result;
    }

    public List<Blog> getByCriteria(String title, String content, String hashTag) throws CustomException {
        if (title == null || content == null || hashTag == null)
            throw new CustomException("Parametre null");
        List<Blog> result = new ArrayList<Blog>();

        return result;
    }

    public Blog getById(Integer id) throws CustomException {
        if (id == null)
            throw new CustomException("Parametre null");

        Blog result = new Blog();

        return result;
    }

    public void delete(Blog item) throws CustomException {
        if (item == null)
            throw new CustomException("Parametre null");

        if (item != null) {
            deleteById(item.getId());
        }
    }

    public void deleteById(Integer id) throws CustomException {
        if (id == null)
            throw new CustomException("Parametre null");

    }
}
