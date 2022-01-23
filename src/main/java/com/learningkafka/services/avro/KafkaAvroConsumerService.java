package com.learningkafka.services.avro;

import java.util.Map;

public interface KafkaAvroConsumerService {

	Map<String, Object> kafkaAvroConsumer();
	Map<String, Object> kafkaAvroConsumerGenericRecord();
}
