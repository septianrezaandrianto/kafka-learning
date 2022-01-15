package com.learningkafka.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learningkafka.constants.KafkaConstant;
import com.learningkafka.services.KafkaConsumerService;
import com.learningkafka.services.KafkaProducerService;
import com.learningkafka.services.entities.CategoryRequest;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

	@Autowired
	private KafkaProducerService kafkaProducerService;
	@Autowired
	private KafkaConsumerService kafkaConsumerService;
	
	@PostMapping("/producerSyncronous")
	public Map<String, Object> producerSyncronousController(@RequestBody CategoryRequest entity) {
		Map<String, Object> result = kafkaProducerService.exampleKafkaProducerSyncronous(entity);
		return result;
	}

	@PostMapping("/producerAsync")
	public Map<String, Object> producerAsyncController(@RequestBody CategoryRequest entity) {
		Map<String, Object> result = kafkaProducerService.exampleKafkaProducerAsync(entity);
		return result;
	}

	@PostMapping("/consumer")
	public Map<String, Object> consumerController(@RequestBody String categoryRequest) {
		Map<String, Object> result= kafkaConsumerService.exampleKafkaConsumer(categoryRequest);
		return result;
	}
	
	@PostMapping("/consumerListener")
	public String consumerListenerController(String categoryRequest) {
		kafkaConsumerService.exampleKafkaConsumerUsingKafkaListener(categoryRequest);
		return KafkaConstant.successConsume;
	}
}
