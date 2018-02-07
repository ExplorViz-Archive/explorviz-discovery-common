package net.explorviz.discovery.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

@Type("agent")
public class Agent extends BaseModel {

	private String ip;
	private String port;

	@Relationship(value = "procezzes")
	private List<Procezz> procezzes = new ArrayList<Procezz>();

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

	public List<Procezz> getProcezzes() {
		return procezzes;
	}

	public void setProcezzes(final List<Procezz> processes) {
		this.procezzes = processes;
	}

	@JsonIgnore
	public String getIPPortOrName() {
		if (this.name == null || this.name.isEmpty()) {
			return this.ip + ":" + this.port;
		} else {
			return this.name;
		}
	}

	@Override
	public String toString() {
		return this.ip + ":" + this.port;
	}

	@Override
	public boolean equals(final Object o) {

		if (o == this) {
			return true;
		}
		if (!(o instanceof Agent)) {
			return false;
		}

		final Agent agent = (Agent) o;

		return agent.ip.equals(ip) && agent.port.equals(port);
	}

	@Override
	public int hashCode() {
		return Objects.hash(ip, port);
	}

	public void updateProcezz(final Procezz newProcezz) {
		final Procezz oldProcezz = this.procezzes.stream().filter(Objects::nonNull)
				.filter(p -> p.getId() == newProcezz.getId()).findFirst().orElse(null);

		if (oldProcezz != null) {
			final int index = this.procezzes.indexOf(oldProcezz);
			this.procezzes.set(index, newProcezz);
		}
	}

}
