package com.learningkafka.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.learningkafka.constants.KafkaConstant;
import com.learningkafka.services.entities.CategoryRequest;


@Service
public class KafkaProducerServiceImpl extends CommonService implements KafkaProducerService {
	
	@Value("${trial.topics}")
	private String topicProducer;
	
	@Override
	public Map<String, Object> exampleKafkaProducerSyncronous(CategoryRequest categoryRequest) {
		Map<String, Object> result = new HashMap<>();

		Properties properties = producerConfiguration();
		KafkaProducer<String, CategoryRequest> kafkaProducer = new KafkaProducer<>(properties);
		ProducerRecord<String, CategoryRequest> record = new ProducerRecord<>(topicProducer, categoryRequest);

		// try catch for handling if kafka server is down, etc
		try {
			RecordMetadata recordMetadata = kafkaProducer.send(record).get();
			result.put(KafkaConstant.RETURN_MESSAGE, KafkaConstant.SUCCESS);
			logger.info( KafkaConstant.PARTITION_LOGGER + recordMetadata.partition());
			logger.info( KafkaConstant.OFFSET_LOGGER + recordMetadata.offset());
		}
		catch (Exception e) {
			result.put(KafkaConstant.RETURN_MESSAGE, KafkaConstant.FAILED);
			logger.info(KafkaConstant.FAILED , e);
		}
		finally {
			kafkaProducer.close();
		}
		return result;
	}

	public Map<String, Object> exampleKafkaProducerAsync(CategoryRequest categoryRequest) {
		Map<String, Object> result = new HashMap<>();

		Properties properties = producerConfiguration();
		KafkaProducer<String, CategoryRequest> producer = new KafkaProducer<>(properties);
		ProducerRecord<String, CategoryRequest> record = new ProducerRecord<>(topicProducer, categoryRequest);

		// try catch for handling if kafka server is down, etc
		try {
			producer.send(record, new OrderCallback());
			result.put(KafkaConstant.RETURN_MESSAGE, KafkaConstant.SUCCESS);
		}
		catch (Exception e) {
			result.put(KafkaConstant.RETURN_MESSAGE, KafkaConstant.FAILED);
		}
		finally {
			producer.close();
		}
		return result;
	}
	
}
