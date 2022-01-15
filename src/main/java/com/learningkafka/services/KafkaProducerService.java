package com.learningkafka.services;

import java.util.Map;

import com.learningkafka.services.entities.CategoryRequest;

public interface KafkaProducerService {

	public Map<String, Object> exampleKafkaProducerSyncronous(CategoryRequest categoryRequest);
	public Map<String, Object> exampleKafkaProducerAsync(CategoryRequest categoryRequest);
}
