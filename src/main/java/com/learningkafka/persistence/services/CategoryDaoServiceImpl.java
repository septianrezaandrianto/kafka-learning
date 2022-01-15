package com.learningkafka.persistence.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learningkafka.persistence.entities.Category;

@Service
public class CategoryDaoServiceImpl implements CategoryDaoService {

	@Autowired
	private CategoryRepository repository;

	@Override
	public void saveOrUpdate(Category category) {
		repository.save(category);
	}
	
	
}
