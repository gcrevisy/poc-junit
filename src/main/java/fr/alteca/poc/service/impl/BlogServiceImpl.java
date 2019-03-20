package fr.alteca.poc.service.impl;

import java.util.ArrayList;
import java.util.List;

import fr.alteca.poc.entity.Blog;
import fr.alteca.poc.exception.CustomException;
import fr.alteca.poc.pojo.RetourService;
import fr.alteca.poc.service.BlogService;

public class BlogServiceImpl implements BlogService {

	public RetourService<List<Blog>> getAll() {
		RetourService<List<Blog>> result = new RetourService<List<Blog>>(new ArrayList<Blog>());

		return result;
	}

	public RetourService<List<Blog>> getByCriteria(String title, String content, String hashTag) throws CustomException {
		if (title == null || content == null || hashTag == null) {
			throw new CustomException("Parametre null");
		}
		RetourService<List<Blog>> result = new RetourService<List<Blog>>(new ArrayList<Blog>());

		return result;
	}

	public RetourService<Blog> getById(Integer id) throws CustomException {
		if (id == null) {
			throw new CustomException("Parametre null");
		}

		RetourService<Blog> result = new RetourService<Blog>(new Blog());

		return result;
	}

	public void delete(Blog item) throws CustomException {
		if (item == null) {
			throw new CustomException("Parametre null");
		}

		if (item != null) {
			deleteById(item.getId());
		}
	}

	public void deleteById(Integer id) throws CustomException {
		if (id == null) {
			throw new CustomException("Parametre null");
		}

	}
}
