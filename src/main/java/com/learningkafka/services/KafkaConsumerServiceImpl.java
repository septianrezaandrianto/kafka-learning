package com.learningkafka.services;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.learningkafka.constants.KafkaConstant;
import com.learningkafka.persistence.entities.Category;
import com.learningkafka.persistence.services.CategoryDaoService;
import com.learningkafka.services.entities.CategoryRequest;

@Service
public class KafkaConsumerServiceImpl extends CommonService implements KafkaConsumerService {

	@Autowired
	private CategoryDaoService categoryDaoService;
	
	@Value("${trial.topics}")
	private String topicProducer;


	// this service haven't work, I'm still RnD
	@Override
	public Map<String, Object> exampleKafkaConsumer(CategoryRequest categoryRequest) {
		Map<String, Object> result = new HashMap<>();

		Properties properties = consumerConfiguration();
		logger.error("{}{}{} PROPERTIES : {}{}{} " + properties);
		
		KafkaConsumer<String, CategoryRequest> kafkaConsumer = new KafkaConsumer<>(properties);
		kafkaConsumer.subscribe(Collections.singletonList(topicProducer));

//			ObjectMapper objectMapper = new ObjectMapper();
//			CategoryRequest cr = null;
//			
//			try {
//				cr = objectMapper.readValue(categoryRequest, CategoryRequest.class);
//			} catch (JsonProcessingException e) {
//				result.put(KafkaConstant.returnMessage, KafkaConstant.failed);
//				logger.error(KafkaConstant.failed, e);
//			}
//			
//			logger.error("{}{}{} CR : {}{}{} " +cr.toString());
		try {
			while(true) {
				ConsumerRecords<String, CategoryRequest> recordList = kafkaConsumer.poll(Duration.ofMillis(100));
				for (ConsumerRecord<String, CategoryRequest> record : recordList) {
					CategoryRequest data = record.value();
					logger.error("{}{}{} DATA : {}{}{} " +data);	
					// set value for saving to db
					Category category = new Category();
					category.setCategoryName(data.getCategoryName());
					category.setCreatedBy(data.getCreatedBy());
					category.setLastModifiedBy(data.getLastModifiedBy());
					category.setIsDeleted(false);
					categoryDaoService.saveOrUpdate(category);
					
					logger.error("{}{}{} SUCCESS SAVE  : {}{}{} " +category.toString());
						
					result.put(KafkaConstant.RETURN_MESSAGE, KafkaConstant.SUCCESS_CONSUME);
					logger.info(KafkaConstant.PARTITION_LOGGER, record.partition());
					logger.info(KafkaConstant.OFFSET_LOGGER, record.offset());
				}
				return result;
			}
		}
		finally {
			kafkaConsumer.close();
		}
	}
	// this service haven't work, I'm still RnD
	
	@Override
	@KafkaListener(topics =  "${trial.topics}", groupId = KafkaConstant.GROUP_ID) 
	public Map<String, Object> exampleKafkaConsumerUsingKafkaListener(CategoryRequest data) {
		Map<String, Object> result = new HashMap<>();
			
		Category category = new Category();
		category.setCategoryName(data.getCategoryName());
		category.setCreatedBy(data.getCreatedBy());
		category.setLastModifiedBy(data.getLastModifiedBy());
		category.setIsDeleted(false);
		categoryDaoService.saveOrUpdate(category);
		
		logger.error("{}{}{} SUCCESS SAVE  : {}{}{} " +category.toString());
		result.put(KafkaConstant.RETURN_MESSAGE, KafkaConstant.SUCCESS_CONSUME);
		return result;
	}
	
}
