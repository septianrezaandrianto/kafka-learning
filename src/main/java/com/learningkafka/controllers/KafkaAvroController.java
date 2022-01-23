package com.learningkafka.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learningkafka.services.avro.Category;
import com.learningkafka.services.avro.KafkaAvroConsumerService;
import com.learningkafka.services.avro.KafkaAvroProducerService;

@RestController
@RequestMapping("/kafka")
public class KafkaAvroController {

	@Autowired
	private KafkaAvroProducerService kafkaAvroProducerService;
	@Autowired
	private KafkaAvroConsumerService kafkaAvroConsumerService;
	
	@PostMapping("/avroProducer")
	public Map<String, Object> avroProducer(@RequestBody Category category) {
		return kafkaAvroProducerService.kafkaAvroProducer(category);
	}
	
	@PostMapping(path="/genericRecord/avroProducer", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> avroProducerGR(@RequestBody Category category) {
		return kafkaAvroProducerService.kafkaAvroGenericRecordProducer(category);
	}
	
	@GetMapping("/avroConsumer")
	public Map<String, Object> avroConsumer() {
		return kafkaAvroConsumerService.kafkaAvroConsumer();
	}
	
	@GetMapping("/genericRecord/avroConsumer")
	public Map<String, Object> avroConsumerGenericRecord() {
		return kafkaAvroConsumerService.kafkaAvroConsumerGenericRecord();
	}
}
