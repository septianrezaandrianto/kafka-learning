package com.learningkafka.persistence.services;

import com.learningkafka.persistence.entities.Category;

public interface CategoryDaoService {

	void saveOrUpdate(Category category);
}
