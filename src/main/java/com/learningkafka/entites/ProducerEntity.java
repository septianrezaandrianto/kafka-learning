package com.learningkafka.entites;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProducerEntity {

	@JsonProperty
	private String id;
	@JsonProperty
	private String status;
	
}
