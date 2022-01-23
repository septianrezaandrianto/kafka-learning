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

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroSerializer;


public class CommonService {

	protected Logger logger = LoggerFactory.getLogger(CommonService.class);
		
	protected Properties producerConfiguration() {
		Properties properties = new Properties();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstant.KAFKA_BOOTSTRAP_SERVER);
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return properties;
	}
	
	protected Properties consumerConfiguration() {
		Properties properties = new Properties();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstant.KAFKA_BOOTSTRAP_SERVER);
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true); // for give information how many the app commit the last data
//		properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 1000);
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaConstant.GROUP_ID);
		return properties;
	}
	
	
	protected Properties producerAvroConfiguration() {
		Properties properties = new Properties();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstant.KAFKA_BOOTSTRAP_SERVER);
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
		properties.put("schema.registry.url", "http://localhost:8081");
		return properties;
	}
	
	
	protected Properties consumerAvroConfiguration() {
		Properties properties = new Properties();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstant.KAFKA_BOOTSTRAP_SERVER);
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class.getName());
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class.getName());
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaConstant.GROUP_ID);
		properties.put("schema.registry.url", "http://localhost:8081");
		properties.put("spesific.avro.reader", true);
		return properties;
	}
}
