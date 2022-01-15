package com.learningkafka.constants;

public interface KafkaConstant {

	final String kafkaBootstrapServer = "localhost:9092";
	final String groupId = "trial.group.dev";

	final String returnMessage = "message";
	final String returnData = "data";
	final String success = "Send successfully";
	final String failed = "Failed, something happend!";
	final String partitionLogger = "Partision {}{} : ";
	final String offsetLogger = "Offset {}{} : ";
	final String successConsume = "Consume successfully";
}
