package net.explorviz.discovery.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

@Type("procezz")
public class Procezz extends BaseModel {

	private long pid;

	@JsonProperty("application-name")
	private String applicationName;

	// executionCommand obtained by agent
	@JsonProperty("os-execution-command")
	private String osExecutionCommand;

	// user-defined (in frontend-extension) executionCommand
	// e.g. extended by javaagent
	@JsonProperty("user-execution-command")
	private String userExecutionCommand;

	// execution command used by agent to restart
	@JsonProperty("agent-execution-command")
	private String agentExecutionCommand;

	@JsonProperty("working-directory")
	private String workingDirectory;

	@JsonProperty("shutdown-command")
	private String shutdownCommand;

	@JsonProperty("monitored-flag")
	private boolean monitoredFlag;

	@JsonProperty("webserver-flag")
	private boolean webserverFlag;

	@JsonProperty("is-stopped")
	private boolean isStopped = false;

	@Relationship(value = "agent")
	private Agent agent;

	public Procezz() {
		// For JSON deserialization
	}

	public Procezz(final long newPID, final String newCommand) {
		this.pid = newPID;
		this.osExecutionCommand = newCommand;
	}

	public String getShutdownCommand() {
		return shutdownCommand;
	}

	public void setShutdownCommand(final String shutdownCommand) {
		if (shutdownCommand != null && shutdownCommand.length() > 0) {
			this.shutdownCommand = shutdownCommand;
		}
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
		if (applicationName != null && applicationName.length() > 0) {
			this.applicationName = applicationName;
		}
	}

	public String getOSExecutionCommand() {
		return osExecutionCommand;
	}

	public void setOSExecutionCommand(final String executionCommand) {
		this.osExecutionCommand = executionCommand;
	}

	public String getUserExecutionCommand() {
		return userExecutionCommand;
	}

	public void setUserExecutionCommand(final String executionCommand) {
		this.userExecutionCommand = executionCommand;
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

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(final Agent responsibleAgent) {
		this.agent = responsibleAgent;
	}

	public String getWorkingDirectory() {
		return workingDirectory;
	}

	public void setWorkingDirectory(final String workingDirectory) {
		this.workingDirectory = workingDirectory;
	}

	public void setStopped(final boolean isStopped) {
		this.isStopped = isStopped;
	}

	public void setIsStopped(final boolean isStopped) {
		this.isStopped = isStopped;
	}

	public String getAgentExecutionCommand() {
		return agentExecutionCommand;
	}

	public void setAgentExecutionCommand(final String agentExecutionCommand) {
		this.agentExecutionCommand = agentExecutionCommand;
	}

	@Override
	public String toString() {
		return "\n" + this.pid + "\n" + this.osExecutionCommand + "\n" + this.applicationName + "\n"
				+ this.monitoredFlag + "\n" + this.webserverFlag;
	}

	@Override
	public boolean equals(final Object o) {

		if (o == this) {
			return true;
		}
		if (!(o instanceof Procezz)) {
			return false;
		}

		final Procezz process = (Procezz) o;

		return process.agent.equals(agent) && process.pid == pid;
	}

	@Override
	public int hashCode() {
		return Objects.hash(agent, pid);
	}

}