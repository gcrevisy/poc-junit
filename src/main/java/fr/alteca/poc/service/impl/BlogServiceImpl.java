package fr.alteca.poc.service.impl;

import fr.alteca.poc.entity.Blog;
import fr.alteca.poc.service.BlogService;

import java.util.ArrayList;
import java.util.List;

public class BlogServiceImpl implements BlogService {
    public List<Blog> getAll() {
        List<Blog> result = new ArrayList<Blog>();

        return result;
    }

    public List<Blog> getByCriteria(String title, String content, String hashTag) {
        List<Blog> result = new ArrayList<Blog>();

        return result;
    }

    public Blog getById(int id) {
        Blog result = new Blog();

        return result;
    }

    public void delete(Blog item) {
        if (item != null) {
            deleteById(item.getId());
        }
    }

    public void deleteById(int id) {

    }
}
