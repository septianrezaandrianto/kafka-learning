package com.learningkafka.services;

import java.util.Map;

import org.springframework.messaging.handler.annotation.Payload;

import com.learningkafka.services.entities.CategoryRequest;

public interface KafkaConsumerService {

	public Map<String, Object> exampleKafkaConsumer(CategoryRequest categoryRequest);
	public Map<String, Object> exampleKafkaConsumerUsingKafkaListener(CategoryRequest categoryRequest);
}
