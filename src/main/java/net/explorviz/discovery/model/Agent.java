package net.explorviz.discovery.model;

import java.util.ArrayList;
import java.util.List;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

@Type("agent")
public class Agent extends BaseModel {

	private String ip;
	private String port;

	@Relationship(value = "processes")
	private List<Process> processes = new ArrayList<Process>();

	public Agent() {
		// For JSON deserialization
	}

	public Agent(final String agentIP, final String agentPort) {
		this.ip = agentIP;
		this.port = agentPort;
	}

	public String getIP() {
		return ip;
	}

	public void setIP(final String remoteAddr) {
		this.ip = remoteAddr;
	}

	public String getPort() {
		return port;
	}

	public void setPort(final String remotePort) {
		this.port = remotePort;
	}

	public List<Process> getProcesses() {
		return processes;
	}

	public void setProcesses(final List<Process> processes) {
		this.processes = processes;
	}

	@Override
	public String toString() {
		return this.ip + ":" + this.port;
	}

}
