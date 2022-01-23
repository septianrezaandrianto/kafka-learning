package com.learningkafka.constants;

public interface KafkaConstant {

	final String KAFKA_BOOTSTRAP_SERVER = "localhost:9092";
	final String GROUP_ID = "trial.group.dev";

	final String RETURN_MESSAGE = "message";
	final String RETURN_DATA = "data";
	final String SUCCESS = "Send successfully";
	final String FAILED = "Failed, something happend!";
	final String PARTITION_LOGGER = "Partision {}{} : ";
	final String OFFSET_LOGGER = "Offset {}{} : ";
	final String SUCCESS_CONSUME = "Consume successfully";
}
