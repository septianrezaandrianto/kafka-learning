package com.learningkafka.services;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Service;

import com.learningkafka.constants.KafkaConstant;
import com.learningkafka.entites.ProducerEntity;


@Service
public class KafkaProducerServiceImpl extends CommonService implements KafkaProducerService {

	@Value("${example.topic.again}")
	private String topicProducer;
	
	@Override
	public void exampleKafkaProducer(ProducerEntity producerEntity) {
		Properties properties = producerConfiguration();
		KafkaProducer<String, ProducerEntity> kafkaProducer = new KafkaProducer<>(properties);
		ProducerRecord<String, ProducerEntity> record = new ProducerRecord<>(topicProducer, producerEntity);
		kafkaProducer.send(record);
		kafkaProducer.close();
	}
	
}
