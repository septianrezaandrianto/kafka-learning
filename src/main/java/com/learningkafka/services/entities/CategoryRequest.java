package com.learningkafka.services.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryRequest {

	@JsonProperty("category_name")
	private String categoryName;
	@JsonProperty("created_by")
	private Long createdBy;
	@JsonProperty("last_modified_by")
	private Long lastModifiedBy;
}
