package net.explorviz.discovery.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

@Type("error-object")
public class ErrorObject extends BaseModel {

	@Relationship(value = "related-object")
	private final BaseModel relatedObject;

	@JsonProperty("error-message")
	private final String errorMessage;

	public ErrorObject(final BaseModel relatedObject, final String errorMessage) {
		this.relatedObject = relatedObject;
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public BaseModel getRelatedObject() {
		return relatedObject;
	}

}
