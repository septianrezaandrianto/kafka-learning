package com.learningkafka.services;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.learningkafka.constants.KafkaConstant;
import com.learningkafka.entites.ProducerEntity;

@Service
public class KafkaConsumerServiceImpl extends CommonService implements KafkaConsumerService {
	
	@Value("${trial.topics}")
	private String topicProducer;
	
	@Override
//	@KafkaListener(topics =  "${example.topic.again}", groupId = KafkaConstant.groupId) // able to using listener
	public void exampleKafkaConsumer() {
		Properties properties = consumerConfiguration();
		KafkaConsumer<String, ProducerEntity> kafkaConsumer = new KafkaConsumer<>(properties);
	
		kafkaConsumer.subscribe(Arrays.asList(topicProducer)); // or able to using subcribe
		
//		while (true) {
			ConsumerRecords<String, ProducerEntity> recordList = kafkaConsumer.poll(Duration.ofMillis(100));
			for (ConsumerRecord<String, ProducerEntity> record : recordList) {
				System.out.println("Receive Data : " + record.value());
			}
			
//		}
	}
	
}
