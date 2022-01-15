package com.learningkafka.services;

import java.util.Map;

import org.springframework.messaging.handler.annotation.Payload;

import com.learningkafka.services.entities.CategoryRequest;

public interface KafkaConsumerService {

	public Map<String, Object> exampleKafkaConsumer(String categoryRequest);
	public void exampleKafkaConsumerUsingKafkaListener(String categoryRequest);
}
