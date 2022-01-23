package com.learningkafka.services.avro;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.learningkafka.constants.KafkaConstant;
import com.learningkafka.services.CommonService;

@Service
public class KafkaAvroConsumerServiceImpl extends CommonService implements KafkaAvroConsumerService {

	@Value("${avro.consumer.topic}")
	private String avroConsumerTopic;
	
	@Override
	public Map<String, Object> kafkaAvroConsumer() {
		Map<String, Object> result = new HashMap<>();
		
		Properties properties = consumerAvroConfiguration();
		
		try (KafkaConsumer<String, Category> kafkaConsumer = new KafkaConsumer<>(properties)) {
			kafkaConsumer.subscribe(Collections.singletonList(avroConsumerTopic));
			
			ConsumerRecords<String, Category> recordList = kafkaConsumer.poll(Duration.ofMillis(100));
			for (ConsumerRecord<String, Category> record : recordList) {
				String categoryName = record.key();
				Category category = record.value();
				
				result.put(KafkaConstant.RETURN_MESSAGE, KafkaConstant.SUCCESS_CONSUME);
				result.put(KafkaConstant.RETURN_DATA, category);
				logger.info("Category Name : " + categoryName);
				logger.info("Created By : " + category.getCreatedBy());
				logger.info("Last Modified By : " + category.getLastModifiedBy());	
			}
			kafkaConsumer.close();
		}
		return result;
	}

	@Override
	public Map<String, Object> kafkaAvroConsumerGenericRecord() {
		Map<String, Object> result = new HashMap<>();
		
		Properties properties = consumerAvroConfiguration();
		
		KafkaConsumer<String, GenericRecord> kafkaConsumer = new KafkaConsumer<>(properties);
		kafkaConsumer.subscribe(Collections.singletonList(avroConsumerTopic));
		
		try {	
			while(true) {
				ConsumerRecords<String, GenericRecord> recordList = kafkaConsumer.poll(Duration.ofMillis(100));
				for (ConsumerRecord<String, GenericRecord> record : recordList) {
					String categoryName = record.key();
					GenericRecord category = record.value();
					
					result.put(KafkaConstant.RETURN_MESSAGE, KafkaConstant.SUCCESS_CONSUME);
					result.put(KafkaConstant.RETURN_DATA, category);
					logger.info("Category Name : " + categoryName);
					logger.info("Created By : " + category.get("createdBy"));
					logger.info("Last Modified By : " + category.get("lastModifiedBy"));	
				}
				return result;
			}
		}
		finally {
			kafkaConsumer.close();
		}
	}
	
}
