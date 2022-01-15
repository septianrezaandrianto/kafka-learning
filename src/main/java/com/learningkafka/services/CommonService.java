package com.learningkafka.services;

import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.learningkafka.constants.KafkaConstant;


public class CommonService {

	Logger logger = LoggerFactory.getLogger(CommonService.class);
		
	protected Properties producerConfiguration() {
		Properties properties = new Properties();
		
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstant.kafkaBootstrapServer);
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return properties;
	}
	
	protected Properties consumerConfiguration() {
		Properties properties = new Properties();
		
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstant.kafkaBootstrapServer);
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true); // for give information how many the app commit the last data
		properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 1000);
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaConstant.groupId);
		return properties;
	}
}
