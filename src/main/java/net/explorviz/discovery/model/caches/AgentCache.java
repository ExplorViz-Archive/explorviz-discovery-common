package net.explorviz.discovery.model.caches;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import net.explorviz.discovery.model.Agent;
import net.explorviz.discovery.model.Process;

public class AgentCache {

	private final List<Agent> agents = new ArrayList<Agent>();

	public Agent getAgentForIPAndPort(final String ip, final String port) {
		return agents.stream().filter(a -> a.getAgentIP().equals(ip) && a.getAgentPort().equals(port)).findFirst()
				.orElse(null);
	}

	public Agent getAgent(final Agent agent) {
		return agents.stream().filter(a -> a.equals(agent)).findFirst().orElse(null);
	}

	public boolean updateProcess(final Process process) {

		final Process processInCache = findProcessInCache(process);

		if (processInCache == null) {
			return false;
		}

		final String applicationName = process.getApplicationName();

		if (applicationName != null && applicationName.length() > 0) {
			processInCache.setApplicationName(applicationName);
		}

		final String shutdownCommand = process.getShutdownCommand();

		if (shutdownCommand != null && shutdownCommand.length() > 0) {
			processInCache.setShutdownCommand(shutdownCommand);
		}

		final boolean webserverFlag = process.isWebserverFlag();
		processInCache.setWebserverFlag(webserverFlag);

		final boolean monitoredFlag = process.isMonitoredFlag();
		processInCache.setMonitoredFlag(monitoredFlag);
		// if (monitoredFlag) {
		// clientService.postProcess(jsonService.getProcessAsByteArray(process));
		// }
		// if (monitoredFlag) {
		// clientService.postProcess(jsonService.getProcessAsByteArray(process));
		// }

		return true;

	}

	private Process findProcessInCache(final Process p) {

		final Agent responsibleAgent = getAgent(p.getResponsibleAgent());

		if (responsibleAgent != null) {
			return responsibleAgent.getProcessList().stream().filter(process -> process.equals(p)).findFirst()
					.orElse(null);
		}

		return null;

	}

	public List<Process> getAllProcessesOfAllAgents() {
		if (this.agents.size() > 0) {
			return this.agents.stream().flatMap(agent -> agent.getProcessList().stream()).collect(Collectors.toList());
		} else {
			return new ArrayList<Process>();
		}
	}

	public List<Agent> getAgents() {
		return agents;
	}

}
