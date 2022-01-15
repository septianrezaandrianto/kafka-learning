package com.learningkafka.services;

import com.learningkafka.entites.ProducerEntity;

import java.util.Map;

public interface KafkaProducerService {

	public Map<String, Object> exampleKafkaProducerSyncronous(ProducerEntity producerEntity);
	public Map<String, Object> exampleKafkaProducerAsync(ProducerEntity producerEntity);
}
