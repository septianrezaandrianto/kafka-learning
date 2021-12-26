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
	
	@PostMapping("/producer")
	public Map<String, Object> producerController(@RequestBody ProducerEntity entity) {
		Map<String, Object> result = new HashMap<>();
		kafkaProducerService.exampleKafkaProducer(entity);
		result.put("message", "Success");
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
