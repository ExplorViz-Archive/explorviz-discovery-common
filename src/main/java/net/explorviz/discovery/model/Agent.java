package net.explorviz.discovery.model;

import java.util.ArrayList;
import java.util.List;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

@Type("agent")
public class Agent extends BaseModel {

	private String agentIP;
	private String agentPort;

	@Relationship(value = "processes")
	private List<Process> processes = new ArrayList<Process>();

	public Agent() {
		// For JSON deserialization
	}

	public Agent(final String agentIP, final String agentPort) {
		this.agentIP = agentIP;
		this.agentPort = agentPort;
	}

	public String getAgentIP() {
		return agentIP;
	}

	public void setAgentIP(final String remoteAddr) {
		this.agentIP = remoteAddr;
	}

	public String getAgentPort() {
		return agentPort;
	}

	public void setAgentPort(final String remotePort) {
		this.agentPort = remotePort;
	}

	public List<Process> getProcessList() {
		return processes;
	}

	public void setProcessList(final List<Process> processList) {
		this.processes = processList;
	}

	@Override
	public String toString() {
		return this.agentIP + ":" + this.agentPort;
	}

}
