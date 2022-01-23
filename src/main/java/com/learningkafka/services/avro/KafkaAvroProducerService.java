package com.learningkafka.services.avro;

import java.util.Map;

public interface KafkaAvroProducerService {

	Map<String, Object> kafkaAvroProducer(Category category);
	Map<String, Object> kafkaAvroGenericRecordProducer(Category category);
}
