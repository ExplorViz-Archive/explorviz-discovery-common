package net.explorviz.discovery.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.github.jasminb.jsonapi.LongIdHandler;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

//Needed for cyclical serialization
@JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class, property = "id")
@Type("base-model")
public class BaseModel {

	@JsonProperty("name")
	protected String name;

	@JsonProperty("last-discovery-time")
	protected long lastDiscoveryTime;

	@JsonProperty("is-hidden")
	protected boolean hidden;

	@Id(LongIdHandler.class)
	private Long id;

	@Relationship(value = "error-object")
	private ErrorObject errorObject;

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(final boolean hidden) {
		this.hidden = hidden;
	}

	public long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public ErrorObject getErrorObject() {
		return errorObject;
	}

	public void setErrorObject(final ErrorObject errorObject) {
		this.errorObject = errorObject;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public long getLastDiscoveryTime() {
		return lastDiscoveryTime;
	}

	public void setLastDiscoveryTime(final long lastDiscoveryTime) {
		this.lastDiscoveryTime = lastDiscoveryTime;
	}

}
