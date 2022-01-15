package com.learningkafka.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Service;

import com.learningkafka.constants.KafkaConstant;
import com.learningkafka.entites.ProducerEntity;


@Service
public class KafkaProducerServiceImpl extends CommonService implements KafkaProducerService {

	Logger logger = LoggerFactory.getLogger(KafkaProducerServiceImpl.class);
	@Value("${trial.topics}")
	private String topicProducer;
	
	@Override
	public Map<String, Object> exampleKafkaProducerSyncronous(ProducerEntity producerEntity) {
		Map<String, Object> result = new HashMap<>();

		Properties properties = producerConfiguration();
		KafkaProducer<String, ProducerEntity> kafkaProducer = new KafkaProducer<>(properties);
		ProducerRecord<String, ProducerEntity> record = new ProducerRecord<>(topicProducer, producerEntity);

		// try catch for handling if kafka server is down, etc
		try {
			RecordMetadata recordMetadata = kafkaProducer.send(record).get();
			result.put(KafkaConstant.returnMessage, KafkaConstant.success);
			logger.info( KafkaConstant.partitionLogger + recordMetadata.partition());
			logger.info( KafkaConstant.offsetLogger + recordMetadata.offset());
		}
		catch (Exception e) {
			result.put(KafkaConstant.returnMessage, KafkaConstant.failed);
			logger.info(KafkaConstant.failed , e);
		}
		finally {
			kafkaProducer.close();
		}
		return result;
	}

	public Map<String, Object> exampleKafkaProducerAsync(ProducerEntity producerEntity) {
		Map<String, Object> result = new HashMap<>();

		Properties properties = producerConfiguration();
		KafkaProducer<String, ProducerEntity> producer = new KafkaProducer<>(properties);
		ProducerRecord<String, ProducerEntity> record = new ProducerRecord<>(topicProducer, producerEntity);

		// try catch for handling if kafka server is down, etc
		try {
			producer.send(record, new OrderCallback());
			result.put(KafkaConstant.returnMessage, KafkaConstant.success);
		}
		catch (Exception e) {
			result.put(KafkaConstant.returnMessage, KafkaConstant.failed);
		}
		finally {
			producer.close();
		}
		return result;
	}
	
}
