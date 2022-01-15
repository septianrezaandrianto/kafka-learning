package com.learningkafka.services;

import com.learningkafka.constants.KafkaConstant;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderCallback implements Callback {

    Logger logger = LoggerFactory.getLogger(KafkaProducerServiceImpl.class);

    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
        logger.info( KafkaConstant.partitionLogger + recordMetadata.partition());
        logger.info( KafkaConstant.offsetLogger + recordMetadata.offset());

        if (e != null) {
            logger.info(KafkaConstant.failed , e);
        }
    }
}
