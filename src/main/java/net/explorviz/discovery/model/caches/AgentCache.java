package net.explorviz.discovery.model.caches;

import java.util.ArrayList;
import java.util.List;

import net.explorviz.discovery.model.Agent;
import net.explorviz.discovery.model.Process;

public class AgentCache {

	// TODO test classic for loop, still nullpointer? No? => Try forEach

	private final List<Agent> agents = new ArrayList<Agent>();

	public Agent getAgentForIPAndPort(final String ip, final String port) {
		final int size = this.agents.size();

		for (int i = 0; i < size; i++) {
			final Agent tempAgent = this.agents.get(i);
			if (tempAgent.getAgentIP().equals(ip) && tempAgent.getAgentPort().equals(port)) {
				return tempAgent;
			}
		}
		return null;
	}

	public Agent getAgent(final Agent agent) {
		final int size = this.agents.size();

		for (int i = 0; i < size; i++) {
			if (this.agents.get(i).equals(agent)) {
				return this.agents.get(i);
			}
		}
		return null;
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
			final List<Process> agentsProcessList = responsibleAgent.getProcessList();
			final int size = agentsProcessList.size();

			for (int i = 0; i < size; i++) {
				if (agentsProcessList.get(i).equals(p)) {
					return agentsProcessList.get(i);
				}
			}
		}

		return null;

	}

	public List<Process> getAllProcessesOfAllAgents() {
		final List<Process> processList = new ArrayList<Process>();
		final int size = this.agents.size();

		for (int i = 0; i < size; i++) {
			processList.addAll(this.agents.get(i).getProcessList());
		}

		return processList;
	}

	public List<Agent> getAgents() {
		return agents;
	}

}
