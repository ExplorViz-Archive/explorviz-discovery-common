package net.explorviz.discovery.model;

import java.io.IOException;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

@Type("process")
public class Process extends BaseModel {

	private long pid;

	@JsonProperty("application-name")
	private String applicationName;

	@JsonProperty("execution-command")
	private String executionCommand;

	@JsonProperty("shutdown-command")
	private String shutdownCommand;

	@JsonProperty("monitored-flag")
	private boolean monitoredFlag;

	@JsonProperty("webserver-flag")
	private boolean webserverFlag;

	@Relationship(value = "agent")
	private Agent agent;

	public Process() {
		// For JSON deserialization
	}

	public Process(final long newPID, final String newCommand) {
		this.pid = newPID;
		this.executionCommand = newCommand;
	}

	public void kill() throws IOException {
		// Start respective service (POST to agent)
	}

	public void start() throws IOException {
		// Start respective service (POST to agent)
	}

	public String getShutdownCommand() {
		return shutdownCommand;
	}

	public void setShutdownCommand(final String shutdownCommand) {
		this.shutdownCommand = shutdownCommand;
	}

	public long getPid() {
		return pid;
	}

	public void setPid(final long pid) {
		this.pid = pid;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(final String applicationName) {
		this.applicationName = applicationName;
	}

	public String getExecutionCommand() {
		return executionCommand;
	}

	public void setExecutionCommand(final String executionCommand) {
		this.executionCommand = executionCommand;
	}

	public boolean isMonitoredFlag() {
		return monitoredFlag;
	}

	public void setMonitoredFlag(final boolean monitoredFlag) {
		this.monitoredFlag = monitoredFlag;
	}

	public boolean isWebserverFlag() {
		return webserverFlag;
	}

	public void setWebserverFlag(final boolean webserverFlag) {
		this.webserverFlag = webserverFlag;
	}

	public Agent getResponsibleAgent() {
		return agent;
	}

	public void setResponsibleAgent(final Agent responsibleAgent) {
		this.agent = responsibleAgent;
	}

	@Override
	public String toString() {
		return "\n" + this.pid + "\n" + this.executionCommand + "\n" + this.applicationName + "\n" + this.monitoredFlag
				+ "\n" + this.webserverFlag;
	}

	@Override
	public boolean equals(final Object o) {

		if (o == this) {
			return true;
		}
		if (!(o instanceof Process)) {
			return false;
		}

		final Process process = (Process) o;

		return process.agent.equals(agent) && process.pid == pid;
	}

	@Override
	public int hashCode() {
		return Objects.hash(agent, pid);
	}

}