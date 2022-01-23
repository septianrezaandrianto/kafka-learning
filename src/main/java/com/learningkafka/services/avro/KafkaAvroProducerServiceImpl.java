package com.learningkafka.services.avro;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.avro.Schema;
import org.apache.avro.Schema.Parser;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.learningkafka.constants.KafkaConstant;
import com.learningkafka.services.CommonService;

@Service
public class KafkaAvroProducerServiceImpl extends CommonService implements KafkaAvroProducerService {

	@Value("${avro.topic}")
	private String avroTopic;
	
	@Override
	public Map<String, Object> kafkaAvroProducer(Category category) {
		Map<String, Object> result = new HashMap<>();
		
		Properties properties = producerAvroConfiguration();
		KafkaProducer<String, Category> kafkaProducer = new KafkaProducer<>(properties);
		
		ProducerRecord<String, Category> record = new ProducerRecord<>(avroTopic, category.getCategoryName().toString(), category);

		// try catch for handling if kafka server is down, etc
		try {
			kafkaProducer.send(record);
			result.put(KafkaConstant.RETURN_MESSAGE, KafkaConstant.SUCCESS);
			logger.info(KafkaConstant.RETURN_MESSAGE , KafkaConstant.SUCCESS);
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

	@Override
	public Map<String, Object> kafkaAvroGenericRecordProducer(Category category) {
		Map<String, Object> result = new HashMap<>();
		
		Properties properties = producerAvroConfiguration();
		KafkaProducer<String, GenericRecord> kafkaProducer = new KafkaProducer<>(properties);
		Parser parser = new Schema.Parser();
		Schema schema = parser.parse("{\n"
				+ "\"namespace\" : \"com.learningkafka.services.avro\",\n"
				+ "\"type\" : \"record\",\n"
				+ "\"name\" : \"Category\",\n"
				+ "\"fields\" : [\n"
				+ "	{\"name\" : \"categoryName\", \"type\" : \"string\"},\n"
				+ "	{\"name\" : \"createdBy\", \"type\" : \"long\"},\n"
				+ "	{\"name\" : \"lastModifiedBy\", \"type\" : \"long\"}\n"
				+ "]\n"
				+ "}");
		
		GenericRecord categoryGR = new GenericData.Record(schema);
		categoryGR.put("categoryName", category.getCategoryName());
		categoryGR.put("createdBy", category.getCreatedBy());
		categoryGR.put("lastModifiedBy", category.getLastModifiedBy());
		
		ProducerRecord<String, GenericRecord> record = new ProducerRecord<>(avroTopic, categoryGR.get("categoryName").toString(), categoryGR);

		// try catch for handling if kafka server is down, etc
		try {
			kafkaProducer.send(record);
			
			result.put(KafkaConstant.RETURN_MESSAGE, KafkaConstant.SUCCESS);
			logger.info(KafkaConstant.RETURN_MESSAGE , KafkaConstant.SUCCESS);
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
	
	

	
}
