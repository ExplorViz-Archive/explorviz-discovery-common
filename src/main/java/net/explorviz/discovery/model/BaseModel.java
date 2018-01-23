package net.explorviz.discovery.model;

import java.util.concurrent.atomic.AtomicLong;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.github.jasminb.jsonapi.LongIdHandler;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

//Needed for cyclical serialization
@JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class, property = "id")
@Type("base-model")
public class BaseModel {

	private static final AtomicLong ID_GENERATOR = new AtomicLong();

	@Id(LongIdHandler.class)
	private Long id;

	@Relationship(value = "error-object")
	private ErrorObject errorObject;

	public BaseModel() {
		id = ID_GENERATOR.incrementAndGet();
	}

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public ErrorObject getErrorObject() {
		return errorObject;
	}

	public void setErrorObject(final ErrorObject errorObject) {
		this.errorObject = errorObject;
	}

}
