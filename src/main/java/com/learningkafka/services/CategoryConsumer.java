package com.learningkafka.services;

import java.io.IOException;

import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learningkafka.constants.KafkaConstant;
import com.learningkafka.services.entities.CategoryRequest;

public class CategoryConsumer implements Deserializer<CategoryRequest> {

	Logger logger = LoggerFactory.getLogger(CategoryConsumer.class);
	
	@Override
	public CategoryRequest deserialize(String topic, byte[] data) {
		ObjectMapper objectMapper = new ObjectMapper();
		CategoryRequest request = null;
		try {
			request = objectMapper.readValue(data, CategoryRequest.class);
		} catch (IOException e) {
			logger.error(KafkaConstant.failed, e);
		}
		
		return request;
	}
	
}
