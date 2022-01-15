package com.learningkafka.persistence.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learningkafka.persistence.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
