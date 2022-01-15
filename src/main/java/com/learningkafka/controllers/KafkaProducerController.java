package com.learningkafka.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learningkafka.entites.ProducerEntity;
import com.learningkafka.services.KafkaConsumerService;
import com.learningkafka.services.KafkaProducerService;

@RestController
@RequestMapping("/kafka")
public class KafkaProducerController {

	@Autowired
	private KafkaProducerService kafkaProducerService;
	@Autowired
	private KafkaConsumerService kafkaConsumerService;
	
	@PostMapping("/producerSyncronous")
	public Map<String, Object> producerSyncronousController(@RequestBody ProducerEntity entity) {
		Map<String, Object> result = kafkaProducerService.exampleKafkaProducerSyncronous(entity);
		return result;
	}

	@PostMapping("/producerAsync")
	public Map<String, Object> producerAsyncController(@RequestBody ProducerEntity entity) {
		Map<String, Object> result = kafkaProducerService.exampleKafkaProducerAsync(entity);
		return result;
	}

	@GetMapping("/consumer")
	public Map<String, Object> consumerController() {
		Map<String, Object> result = new HashMap<>();
		kafkaConsumerService.exampleKafkaConsumer();
		result.put("message", "Success");
		return result;
	}
}
